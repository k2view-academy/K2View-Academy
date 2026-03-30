# How to Configure the Collection Layer to Scrape Fabric Metrics

*Grafana Agent (Kubernetes) and Prometheus (VM / Bare-Metal)*

## Table of Contents

* [Purpose](#purpose)
* [Scope](#scope)
* [1. Before You Begin](#1-before-you-begin)
* [2. Kubernetes — Grafana Agent](#2-kubernetes-grafana-agent)
  * [2.1 How Grafana Agent Finds Fabric Endpoints](#21-how-grafana-agent-finds-fabric-endpoints)
  * [2.2 Option A — Annotation-Based Autodiscovery](#22-option-a-annotation-based-autodiscovery)
  * [2.3 Option B — Explicit River Pipeline](#23-option-b-explicit-river-pipeline)
  * [2.4 Confirming Grafana Agent Is Scraping Fabric](#24-confirming-grafana-agent-is-scraping-fabric)
* [3. VM / Bare-Metal — Prometheus Static Targets](#3-vm-bare-metal-prometheus-static-targets)
  * [3.1 Creating the Prometheus Scrape Job](#31-creating-the-prometheus-scrape-job)
  * [3.2 Reloading Prometheus](#32-reloading-prometheus)
  * [3.3 Confirming Prometheus Is Scraping Fabric](#33-confirming-prometheus-is-scraping-fabric)
* [4. Filtering and Relabeling](#4-filtering-and-relabeling)
  * [4.1 What to Filter](#41-what-to-filter)
  * [4.2 Filtering in Grafana Agent (Kubernetes)](#42-filtering-in-grafana-agent-kubernetes)
  * [4.3 Filtering in Prometheus (VM / Bare-Metal)](#43-filtering-in-prometheus-vm-bare-metal)
  * [4.4 Reducing Label Explosion](#44-reducing-label-explosion)
* [5. Validation](#5-validation)
  * [5.1 Confirm the Target Is Being Scraped](#51-confirm-the-target-is-being-scraped)
  * [5.2 Validate with a Simple Query](#52-validate-with-a-simple-query)
* [6. Common Problems](#6-common-problems)
  * [Target present but State: DOWN](#target-present-but-state-down)
  * [Target not visible at all](#target-not-visible-at-all)
  * [Target is UP but no Fabric metrics appear](#target-is-up-but-no-fabric-metrics-appear)
  * [Too many active series](#too-many-active-series)
* [7. Quick Checklist](#7-quick-checklist)
* [Related Topics](#related-topics)

# Purpose

This topic explains how to configure the metrics collection layer to scrape Fabric metrics from the bundled JMX Exporter. The collection layer differs between Kubernetes and VM / bare-metal deployments:

* Kubernetes: Grafana Agent is the local collector. It scrapes Fabric pods and remote-writes to a per-cluster Prometheus instance.
* VM / Bare-Metal: Prometheus scrapes Fabric hosts directly using static scrape targets.

Both paths result in Fabric and JVM metrics being available in Prometheus for dashboards, alerting, and Thanos federation.

# Scope

This how-to covers:

* How the collection layer finds Fabric endpoints in each deployment model
* Kubernetes: configuring Grafana Agent to scrape Fabric pods
* VM / Bare-Metal: configuring Prometheus static scrape targets
* Filtering and relabeling to control metric volume
* Validation and common problems for both models

It does not cover dashboard creation, alert rules, Thanos configuration, or Loki log collection. Those are covered in related topics.

# 1. Before You Begin

> **[ K8s + VM ]** Applies to both deployment models.

Before configuring the collection layer, confirm that Fabric is already exposing metrics through the JMX Exporter. You should be able to run the following from inside the Fabric runtime context and receive Prometheus-format output:

```
curl http://localhost:7170/metrics
```

If this does not return metrics, resolve that first. See How to Enable the JMX Exporter for Fabric and How to Verify That Fabric Is Exposing Metrics.

You should also know:

* The Fabric metrics port — default 7170 for Fabric, 7270 for iid_finder
* The host or pod name(s) running Fabric
* Which collection layer applies to your deployment (Grafana Agent or Prometheus)

# 2. Kubernetes — Grafana Agent

> **[ K8s ]** In Kubernetes, Grafana Agent is the metrics collector. Prometheus is not configured directly to scrape Fabric. Grafana Agent scrapes Fabric pods and remote-writes to a per-cluster Prometheus instance.

## 2.1 How Grafana Agent Finds Fabric Endpoints

Grafana Agent uses Kubernetes service discovery to find pods and services in the cluster. It does not use static target lists. There are two ways to configure it to scrape Fabric metrics:

* Annotation-based autodiscovery — Fabric pods are annotated to signal that they should be scraped
* Explicit River pipeline — a custom discovery and scrape component is added to the Grafana Agent configuration

The K2view Grafana Agent Helm chart (k8s-monitoring) supports both patterns. Annotation-based autodiscovery is disabled by default in the chart and must be explicitly enabled.

## 2.2 Option A — Annotation-Based Autodiscovery

When annotation-based autodiscovery is enabled in the Grafana Agent chart, any pod annotated with the scrape annotation will be automatically discovered and scraped. To enable:

In your Grafana Agent values override file, set:

```
metrics:
  autoDiscover:
    enabled: true
```

Then annotate the Fabric pod or deployment with the following annotations:

```
k8s.grafana.com/scrape: "true"
k8s.grafana.com/metrics.portNumber: "7170"
```

For iid_finder, add a separate annotation entry pointing to port 7270, or configure it as a second scrape target.

> **Note:** Annotation-based autodiscovery discovers pods and services cluster-wide. Apply the scrape annotation only to pods you intend to monitor, and use metric filtering (see Section 4) to control what is retained.

## 2.3 Option B — Explicit River Pipeline

For more control over discovery, scraping, and relabeling, add an explicit River pipeline to the Grafana Agent configuration. This is the recommended approach when you need to apply specific filtering or label transformations to Fabric metrics.

A minimal River pipeline that discovers Fabric pods by label and scrapes port 7170:

```
discovery.relabel "fabric_pods" {
  targets = discovery.kubernetes.pods.targets
  rule {
    source_labels = ["__meta_kubernetes_pod_label_app"]
    regex = "fabric"
    action = "keep"
  }
  rule {
    source_labels = ["__meta_kubernetes_pod_container_port_number"]
    regex = "7170"
    action = "keep"
  }
}

prometheus.scrape "fabric_jmx" {
  targets = discovery.relabel.fabric_pods.output
  job_name = "fabric-jmx"
  forward_to = [prometheus.relabel.metrics_service.receiver]
}
```

Adjust the label selector (app=fabric) to match the actual labels on your Fabric pods. The forward_to destination (prometheus.relabel.metrics_service) is the standard metrics forwarding component created by the k8s-monitoring chart.

To also scrape iid_finder, add a second discovery.relabel and prometheus.scrape block targeting port 7270.

Pass the River configuration to the Grafana Agent chart:

```
helm upgrade grafana-agent . \\
  --namespace grafana-agent \\
  --values grafana-agent-values.yaml \\
  --set-file extraConfig=fabric-scrape.river
```

## 2.4 Confirming Grafana Agent Is Scraping Fabric

After applying the configuration, confirm that Grafana Agent is discovering and scraping Fabric endpoints:

1.  Check the Grafana Agent pod logs for scrape activity:

```
kubectl logs -n grafana-agent -l app.kubernetes.io/name=grafana-agent | grep fabric
```

2.  Query Prometheus for a known Fabric metric to confirm data is flowing:

```
curl -s http://<PROMETHEUS_HOST>:9090/api/v1/query?query=jvm_memory_bytes_used | jq .
```

**Note:** Grafana Agent remote-writes to Prometheus. It may take up to one scrape interval (default 60s) for metrics to appear in Prometheus after configuration is applied.

# 3. VM / Bare-Metal — Prometheus Static Targets

> **[ VM / Bare-Metal ]** In VM deployments, Prometheus scrapes Fabric hosts directly. There is no Grafana Agent and no service discovery. Each Fabric host must be listed explicitly as a static scrape target.

## 3.1 Creating the Prometheus Scrape Job

Add scrape jobs to your Prometheus configuration file (prometheus.yml) on the monitoring machine. You need one job for Fabric metrics and one for Node Exporter metrics on each host:

```
scrape_configs:

  # Fabric JMX Exporter — one entry per Fabric host
  - job_name: fabric-jmx
    metrics_path: /metrics
    static_configs:
      - targets:
          - <FABRIC_HOST_1>:7170
          - <FABRIC_HOST_2>:7170
        labels:
          env: production

# iid_finder JMX Exporter — add if iid_finder is running
- job_name: iidfinder-jmx
  metrics_path: /metrics
  static_configs:
    - targets:
      - <FABRIC_HOST_1>:7270
      - <FABRIC_HOST_2>:7270

# Node Exporter — one entry per Fabric host
- job_name: node-exporter
  metrics_path: /metrics
  static_configs:
    - targets:
      - <FABRIC_HOST_1>:9100
      - <FABRIC_HOST_2>:9100
```

**Note:** Replace <FABRIC_HOST_1> and <FABRIC_HOST_2> with the actual hostnames or IP addresses of your Fabric machines. Add one entry per host. The Node Exporter default port is 9100.

## 3.2 Reloading Prometheus

After adding or updating scrape targets, reload Prometheus to apply the configuration without restarting:

```
curl -X POST http://localhost:9090/-/reload
```

If hot-reload is not enabled, restart Prometheus:

```
systemctl restart prometheus
```

## 3.3 Confirming Prometheus Is Scraping Fabric

3.  Open the Prometheus Targets UI in a browser:

```
http://<PROMETHEUS_HOST>:9090/targets
```

4.  Confirm the fabric-jmx job is present and all targets show State: UP

5.  Run a test query for a known Fabric or JVM metric:

```
http://<PROMETHEUS_HOST>:9090/graph?g0.expr=jvm_memory_bytes_used
```

A target showing State: DOWN means Prometheus can see the target in its configuration but cannot reach it. Check network reachability between the Prometheus machine and the Fabric host, and confirm the JMX Exporter is running on the expected port.

# 4. Filtering and Relabeling

> **[ K8s + VM ]** Applies to both deployment models. The principle is the same; the syntax differs between Grafana Agent River config and Prometheus YAML.

The Fabric JMX Exporter exposes all available metrics by default. Without filtering, Prometheus can ingest far more data than is operationally useful. Filtering and relabeling should be applied at the collection layer to:

* Drop low-value metric families that are not used in dashboards or alerts
* Reduce label explosion on useful metrics to keep active series manageable
* Control storage growth and protect configured retention windows

## 4.1 What to Filter

Start from a known useful set and drop everything else. For Fabric, the typically useful families are:

* fabric_* — Fabric product metrics
* jvm_* — JVM memory, GC, threads, class loading
* tomcat_* — where applicable
* process_* — process-level CPU and file descriptors

The families most commonly worth dropping are high-volume exporters that produce many series with little operational value. Node Exporter in particular exposes a very large number of metric families — review which ones are actually used in your dashboards and drop the rest.

> **Note:** The real scaling pressure in Prometheus comes from active series, not from the number of metric names. A single metric with many label values can generate hundreds of distinct series. Always review active series counts after changing filtering rules.

## 4.2 Filtering in Grafana Agent (Kubernetes)

In a River pipeline, use a prometheus.relabel component to filter metrics before forwarding:

```
prometheus.relabel "fabric_jmx" {
  rule {
    source_labels = ["__name__"]
    regex = "fabric_.*|jvm_.*|tomcat_.*|process_.*"
    action = "keep"
  }
  forward_to = [prometheus.relabel.metrics_service.receiver]
}
```

Update the prometheus.scrape component to forward to this relabel component instead of directly to metrics_service:

```
prometheus.scrape "fabric_jmx" {
  targets = discovery.relabel.fabric_pods.output
  job_name = "fabric-jmx"
  forward_to = [prometheus.relabel.fabric_jmx.receiver]
}
```

## 4.3 Filtering in Prometheus (VM / Bare-Metal)

In Prometheus YAML, use metric_relabel_configs within the scrape job:

```
- job_name: fabric-jmx
  metrics_path: /metrics
  static_configs:
    - targets:
      - <FABRIC_HOST_1>:7170
    metric_relabel_configs:
      - source_labels: [__name__]
        regex: \'fabric_.*|jvm_.*|tomcat_.*|process_.*\'
        action: keep
```

**Note:** metric_relabel_configs runs after the scrape. It filters what gets stored in Prometheus. relabel_configs runs before the scrape and controls target selection. Use metric_relabel_configs for metric family filtering.

## 4.4 Reducing Label Explosion

If a metric is useful but has too many labels creating excessive series, drop unwanted labels using a labeldrop action:

Grafana Agent (River):

```
rule {
  action = "labeldrop"
  regex = "some_high_cardinality_label"
}
```

Prometheus YAML:

```
metric_relabel_configs:
  - action: labeldrop
    regex: \'some_high_cardinality_label\'
```

For detailed guidance on filtering strategy, cardinality management, and retention impact, see How to Control Metric Volume with Filtering and Relabeling.

# 5. Validation

> **[ K8s + VM ]** Run these checks after configuring the collection layer.

## 5.1 Confirm the Target Is Being Scraped

**Kubernetes:** Check Grafana Agent logs for scrape activity and confirm metrics are flowing to Prometheus.

```
kubectl logs -n grafana-agent -l app.kubernetes.io/name=grafana-agent | grep -i "fabric\\|scrape"
```

**VM:** Open the Prometheus Targets UI and confirm the fabric-jmx job shows State: UP for all configured targets.

```
http://<PROMETHEUS_HOST>:9090/targets
```

## 5.2 Validate with a Simple Query

Once the collection layer is scraping, run a simple query for known Fabric or JVM metrics. You do not need a full dashboard yet — the important validation is that time series are present:

```
# JVM heap memory
jvm_memory_bytes_used{area="heap"}

# Fabric reads
fabric_read_total

# Active series in Prometheus (health check)
prometheus_tsdb_head_series
```

# 6. Common Problems

## Target present but State: DOWN

**Kubernetes:** The Grafana Agent cannot reach the Fabric pod on port 7170. Check that the JMX Exporter is running inside the pod (curl from inside the pod), and check network policy rules between the Grafana Agent namespace and the Fabric namespace.

**VM:** Prometheus cannot reach the Fabric host on port 7170. Check firewall rules between the monitoring machine and the Fabric host. Confirm Node Exporter and the JMX Exporter are both running on the Fabric host.

## Target not visible at all

**Kubernetes:** Autodiscovery is not finding the Fabric pods. Check that the scrape annotation is present on the pod (Option A) or that the River pipeline label selector matches the actual pod labels (Option B).

**VM:** The static target is missing from prometheus.yml, or Prometheus was not reloaded after the configuration change.

## Target is UP but no Fabric metrics appear

* Filtering rules may be too restrictive — confirm the regex covers the fabric_* and jvm_* families
* The wrong endpoint may be configured — confirm port 7170 is used, not 9100 (Node Exporter) or another port
* Fabric may still be initializing — wait for full startup and retry

## Too many active series

* Node Exporter is the most common culprit — it exposes many high-cardinality families by default
* Apply metric filtering to drop families not used in dashboards or alerts
* Review label sets on high-volume metrics and apply labeldrop rules where labels are not needed
* See How to Control Metric Volume with Filtering and Relabeling for a systematic approach

# 7. Quick Checklist

**Kubernetes:**

* Fabric /metrics endpoint verified from inside the pod
* Grafana Agent chart deployed with correct values
* Annotation autodiscovery enabled OR explicit River pipeline added
* Fabric pods annotated with scrape annotation (if using Option A)
* River pipeline label selector matches Fabric pod labels (if using Option B)
* Grafana Agent logs show scrape activity for Fabric
* Prometheus shows Fabric and JVM metrics in query
* Filtering applied to control active series

**VM / Bare-Metal:**

* Fabric /metrics endpoint verified from Fabric host
* Prometheus scrape job added for fabric-jmx (port 7170)
* Prometheus scrape job added for iidfinder-jmx (port 7270, if applicable)
* Prometheus scrape job added for node-exporter (port 9100)
* Prometheus reloaded after configuration change
* Prometheus Targets UI shows fabric-jmx job with State: UP
* Prometheus query returns Fabric and JVM metrics
* metric_relabel_configs applied to filter low-value metric families

# Related Topics

* [How to Enable the JMX Exporter for Fabric](/articles/34_JMX_statistics/How_to_Enable_the_JMX_Exporter_for_Fabric.md)
* [How to Verify That Fabric Is Exposing Metrics](/articles/34_JMX_statistics/How_to_Verify_That_Fabric_Is_Exposing_Metrics.md)
* [How to Control Metric Volume with Filtering and Relabeling](/articles/34_JMX_statistics/How_to_Control_Metric_Volume_with_Filtering_and_Relabeling.md)
* [K2view Observability Architecture for Fabric](/articles/34_JMX_statistics/K2view_Observability_Architecture_for_Fabric.md)
* [K2view VM / Bare-Metal Monitoring Stack for Fabric](/articles/34_JMX_statistics/K2view_VM_Bare-Metal_Monitoring_Stack_for_Fabric.md)
