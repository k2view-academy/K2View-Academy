# K2view Observability Architecture for Fabric

*Reference Architecture — Kubernetes and VM / Bare-Metal Deployments*

## Table of Contents

* [How to Use This Article](#how-to-use-this-article)
* [1. Purpose and Scope](#1-purpose-and-scope)
* [2. Architecture Overview](#2-architecture-overview)
  * [2.1 Kubernetes](#21-kubernetes)
  * [2.2 VM / Bare-Metal](#22-vm-bare-metal)
* [3. Fabric Metrics Exposure Model](#3-fabric-metrics-exposure-model)
  * [3.1 How Fabric Exposes Metrics](#31-how-fabric-exposes-metrics)
  * [3.2 How the Exporter is Activated](#32-how-the-exporter-is-activated)
    * [Kubernetes](#kubernetes)
    * [VM / Bare-Metal](#vm-bare-metal)
* [4. Collection Layer](#4-collection-layer)
  * [4.1 Kubernetes [ K8s ]](#41-kubernetes-k8s)
    * [Monitoring Enablement Across Spaces](#monitoring-enablement-across-spaces)
  * [4.2 VM / Bare-Metal [ VM ]](#42-vm-bare-metal-vm)
  * [4.3 What Each Collector Answers](#43-what-each-collector-answers)
* [5. What Runs Inside the Fabric Container vs. Outside](#5-what-runs-inside-the-fabric-container-vs-outside)
  * [5.1 Kubernetes [ K8s ]](#51-kubernetes-k8s)
  * [5.2 VM / Bare-Metal [ VM ]](#52-vm-bare-metal-vm)
* [6. Log Collection](#6-log-collection)
  * [6.1 Kubernetes [ K8s ]](#61-kubernetes-k8s)
  * [6.2 VM / Bare-Metal [ VM ]](#62-vm-bare-metal-vm)
* [7. Cross-Cluster Aggregation with Thanos [ K8s ]](#7-cross-cluster-aggregation-with-thanos-k8s)
* [8. Filtering, Relabeling, and Cardinality Control](#8-filtering-relabeling-and-cardinality-control)
* [9. What a Consuming Monitoring Environment Sees](#9-what-a-consuming-monitoring-environment-sees)
* [10. Consuming from Non-Prometheus Platforms](#10-consuming-from-non-prometheus-platforms)
* [11. Operational Considerations](#11-operational-considerations)
  * [11.1 Enabling the Exporter is Not Sufficient on Its Own](#111-enabling-the-exporter-is-not-sufficient-on-its-own)
  * [11.2 Cardinality Must be Managed Continuously](#112-cardinality-must-be-managed-continuously)
  * [11.3 Logs and Metrics are Complementary, Not Interchangeable](#113-logs-and-metrics-are-complementary-not-interchangeable)
  * [11.4 Responsibility Boundaries](#114-responsibility-boundaries)
  * [11.5 Credentials in Configuration Files](#115-credentials-in-configuration-files)
* [Appendix: Quick Reference](#appendix-quick-reference)
  * [Key Ports](#key-ports)
  * [Key Files (inside Fabric container / host)](#key-files-inside-fabric-container-host)
  * [Validation](#validation)
  * [Activation Chain (K8s)](#activation-chain-k8s)
  * [Activation (VM / Bare-Metal)](#activation-vm-bare-metal)
  * [Deployment Model Summary](#deployment-model-summary)

# How to Use This Article

This article covers two distinct deployment models. The observability architecture, components, and configuration mechanisms differ significantly between them. Each major section identifies which model it applies to.

> **[ K8s ] Kubernetes deployments on AKS (Azure), GKE (GCP), or EKS (AWS)**
>
> Fabric runs as a pod. For K2cloud SaaS and K2cloud Self-hosted customers, monitoring enablement is managed through the space profile — recent profiles have monitoring enabled by default. Confirm with K2view that your space profile has monitoring enabled. For air-gapped deployments, see Deploying Fabric Monitoring in Air-Gapped Environments. The local observability stack uses Grafana Agent as the metrics collector, feeding a per-cluster Prometheus instance. Thanos federates across clusters. There are alternatives to Thanos, such as Mimir.
>
> **[ VM / Bare-Metal ] Virtual machines or physical servers**
>
> Fabric runs as a native process. Monitoring is enabled manually by editing jvm.options or running the monitor setup scripts. The local observability stack uses Prometheus directly with static scrape targets. There is no Grafana Agent layer and no Thanos in the base VM model.

# 1. Purpose and Scope

This article explains how K2view exposes, collects, and aggregates observability data for Fabric-based deployments. It covers the standard monitoring model across both Kubernetes and VM / bare-metal environments, describing how Fabric exposes metrics, how those metrics are collected at the cluster or host level, how logs are collected alongside metrics, and how data is aggregated for cross-cluster visibility.

This article is an architectural reference. Step-by-step procedures are covered in the accompanying how-to topics.

# 2. Architecture Overview

The K2view observability model is built in layers. The layers differ between Kubernetes and VM deployments, but the fundamental principle is the same: Fabric exposes metrics through a bundled JMX Exporter, a collection layer gathers those metrics alongside infrastructure and cluster-state signals, and a unified view is presented in Grafana.

## 2.1 Kubernetes

The Kubernetes observability stack is organized as follows:

* Application layer: Fabric exposes JVM and product metrics through the bundled Prometheus JMX Exporter, which runs as a Java agent inside the Fabric container. Metrics are served at port 7170. The iid_finder process exposes metrics separately at port 7270.

* Collection layer: Grafana Agent runs in a dedicated observability namespace and scrapes the Fabric metrics endpoint, node-exporter (for host metrics), and kube-state-metrics (for Kubernetes state). Grafana Agent remote_writes to a per-cluster Prometheus instance.

* Log layer: Grafana Agent also collects pod logs and forwards them to Loki.

* Aggregation layer: Each per-cluster Prometheus instance has a Thanos sidecar. A central Thanos Query layer federates across all clusters, providing a unified cross-cluster view across AKS, GKE, and EKS deployments.

* Visualization: Grafana queries both Prometheus (via Thanos Query) and Loki to present a unified operational view.

## 2.2 VM / Bare-Metal

The VM observability stack is simpler and operates without Grafana Agent or Thanos:

* Application layer: Fabric exposes JVM and product metrics through the same bundled Prometheus JMX Exporter, running as a Java agent. Metrics are served at port 7170 on localhost.

* Collection layer: Prometheus runs on a dedicated monitoring machine and scrapes Fabric metrics and node-exporter metrics using static scrape targets. There is no Grafana Agent and no service discovery.

* Log layer: Promtail runs on each Fabric machine, tails the Fabric log file, and ships logs to Loki on the monitoring machine.

* Visualization: Grafana queries Prometheus and Loki from the monitoring machine.

The following diagram summarizes the data flow for both models:

```
[ K8s ]

Fabric pod
JMX Exporter (:7170) ──────────────────────────┐
iid_finder (:7270) ────────────────────────────┤
node-exporter (worker node, DaemonSet) ────────┤
kube-state-metrics (cluster, singleton) ───────┤
Pod logs ──────────────────────────────────────┤
                                               ▼
                                   Grafana Agent (per cluster)
                                               │ remote_write
                                               ▼
                             Prometheus (per cluster) + Thanos sidecar
                                               │
                                               ▼
                                      Thanos Query (central)
                                               │
                                               ▼
                                            Grafana

[ VM / Bare-Metal ]

Fabric host
JMX Exporter (:7170, localhost) ───────────────┐
node-exporter (same host) ─────────────────────┤
Promtail (tails k2fabric.log) ───────► Loki    │
                                               ▼
                                 Prometheus (monitoring machine)
                                               │
                                               ▼
                                            Grafana
```

# 3. Fabric Metrics Exposure Model

This section applies to both Kubernetes and VM / bare-metal deployments. The Fabric-side exposure mechanism is the same in both cases.

## 3.1 How Fabric Exposes Metrics

Fabric exposes runtime and application telemetry through JMX MBeans. The bundled Prometheus JMX Exporter runs as a Java agent inside the Fabric JVM, reads those MBeans locally, and serves the resulting metrics over HTTP in Prometheus format.

The exporter is packaged with Fabric under the monitor directory:

```
$K2_HOME/monitor/jmx_exporter/
   jmx_prometheus_javaagent-1.5.0.jar
   fabric_config.yaml
```

The exporter configuration (fabric_config.yaml) is intentionally minimal. Fabric exposes all metrics it can expose by default. Most filtering is applied at the collection layer, not in the exporter itself.

Once active, the exporter serves metrics at:

```
http://localhost:7170/metrics # Fabric JVM and application metrics
http://localhost:7270/metrics # iid_finder metrics (separate JVM process)
```

The response includes JVM metrics, Fabric product metrics, and Tomcat-related metrics where applicable.

## 3.2 How the Exporter is Activated

The exporter is activated by appending a -javaagent line to jvm.options. This is done differently depending on the deployment model.

### Kubernetes

In Kubernetes, activation depends on whether K2cloud is present:

**K2cloud SaaS and K2cloud Self-hosted**

Activation is automated through the following chain:

* The K2cloud space profile controls monitoring enablement. Recent K2view space profiles have monitoring enabled by default — confirm with K2view that your space profile includes this setting.

* When monitoring is enabled, K2cloud Orchestrator injects MONITORING=default as an environment variable into the Fabric pod via a Kubernetes secret (common-env-secrets)

* At container startup, docker-entrypoint.sh calls init_monitoring() in cloud_common.sh

* init_monitoring() checks that MONITORING equals 'default' or 'true', then runs monitor_setup.sh

* monitor_setup.sh calls fabric_7_monitor.sh, which checks whether the javaagent line is already present in jvm.options and appends it if not (idempotent)

* The appended line takes effect when the Fabric JVM starts

**Air-Gapped Kubernetes**

K2cloud Orchestrator is not present in air-gapped deployments. The MONITORING=default environment variable must be set manually in the Fabric pod specification. Once set, the same container startup chain runs — docker-entrypoint.sh calls init_monitoring(), which calls monitor_setup.sh, which calls fabric_7_monitor.sh — and the javaagent line is appended to jvm.options identically to the K2cloud path. See Fabric Monitoring in Air-Gapped Kubernetes Deployments for the full procedure.

The line appended to jvm.options is the same regardless of how MONITORING=default was set:

```
-javaagent:$K2_HOME/monitor/jmx_exporter/jmx_prometheus_javaagent-1.5.0.jar=7170:$K2_HOME/monitor/jmx_exporter/fabric_config.yaml
```

**Note:** The javaagent line does not pre-exist in jvm.options. It is appended at container startup by fabric_7_monitor.sh. The script includes an idempotency check so it is only appended once.

### VM / Bare-Metal

On VMs, there is no K2cloud Orchestrator automation. The javaagent line must be added manually to jvm.options, or the fabric_7_monitor.sh script can be run directly if the monitor directory has been deployed to the host.

After editing jvm.options, Fabric must be restarted for the change to take effect.

Validation in both cases:

```
curl http://localhost:7170/metrics
```

# 4. Collection Layer

## 4.1 Kubernetes [ K8s ]

In Kubernetes, Grafana Agent is the local metrics collector. It is deployed in a dedicated observability namespace on each monitored cluster and is responsible for:

* Discovering and scraping the Fabric JMX Exporter endpoint at port 7170

* Scraping node-exporter instances (deployed as a DaemonSet on worker nodes)

* Scraping kube-state-metrics (deployed as a singleton, talks to the control plane)

* Collecting pod logs and forwarding them to Loki

Grafana Agent remote_writes scraped metrics to a per-cluster Prometheus instance. This is distinct from the VM model, where Prometheus scrapes directly.

> **Important:** In Kubernetes, node-exporter runs on the worker nodes as a DaemonSet — it does not run inside the Fabric container. kube-state-metrics runs as a singleton and does not need to run once per node.

### Monitoring Enablement Across Spaces

Monitoring is consistently enabled across spaces via the space profile mechanism. When monitoring is enabled in the space profile, K2cloud Orchestrator injects the MONITORING=default environment variable, which triggers the full monitor setup chain at container startup. This enables repeatable monitoring without per-pod manual configuration.

## 4.2 VM / Bare-Metal [ VM ]

On VMs, Prometheus runs on a dedicated monitoring machine and scrapes targets using a static configuration. There is no Grafana Agent and no service discovery. Each Fabric host must be explicitly listed as a scrape target.

A minimal Prometheus scrape job for Fabric looks like:

```yaml
scrape_configs:
- job_name: fabric-jmx
   metrics_path: /metrics
   static_configs:
      - targets:
           - fabric-host-1:7170
           - fabric-host-2:7170
```

Unlike Kubernetes, there is no automatic target discovery. Each new Fabric host must be added to the Prometheus configuration manually.

## 4.3 What Each Collector Answers

The different collectors exist because they answer different operational questions:

* JMX Exporter: How is the Fabric JVM and application behaving?

* node-exporter: How is the host or worker node behaving?

* kube-state-metrics: How is Kubernetes managing the workloads? (K8s only)

* Promtail / Grafana Agent log collection: What did the application and platform log?

> **K8s only:** kube-state-metrics is a Kubernetes-specific component. It does not exist in VM / bare-metal deployments.

# 5. What Runs Inside the Fabric Container vs. Outside

This is one of the most commonly misunderstood boundaries. The answer differs between Kubernetes and VM deployments.

## 5.1 Kubernetes [ K8s ]

Inside the Fabric container:

* JMX Exporter (runs as a Java agent within the Fabric JVM)

* iid_finder with its own JMX Exporter instance

Outside the Fabric container (on the worker node or cluster):

* node-exporter (DaemonSet on worker nodes)

* kube-state-metrics (cluster singleton)

* Grafana Agent (observability namespace)

* Prometheus (observability namespace)

* Thanos sidecar (alongside Prometheus)

> **Note:** The monitor directory inside the Fabric image also contains node_exporter and promtail binaries. These are legacy artifacts from the VM monitoring model. In Kubernetes, they are not started.

## 5.2 VM / Bare-Metal [ VM ]

On VMs, the monitor_setup.sh script starts additional processes within the same host context as Fabric:

* JMX Exporter (Java agent within the Fabric JVM)

* node_exporter (background process on the same host)

* Promtail (background process on the same host, if LOKI_HOST is set)

All three processes run on the Fabric host itself. Prometheus and Loki run on a separate dedicated monitoring machine.

# 6. Log Collection

## 6.1 Kubernetes [ K8s ]

In Kubernetes, Grafana Agent collects pod logs from the cluster and forwards them to Loki. This is configured as part of the Grafana Agent Helm chart. Loki is the centralized log store and query backend.

## 6.2 VM / Bare-Metal [ VM ]

On VMs, Promtail runs on each Fabric host as the log shipping agent. The promtail_config.sh script configures Promtail at startup, substituting the Loki host, hostname, and log path into the configuration template.

Promtail only starts if the LOKI_HOST environment variable is set. If LOKI_HOST is not configured, log shipping is silently skipped.

The Fabric log path tailed by Promtail is:

```
$K2_HOME/logs/k2fabric.log
```

Loki runs on the monitoring machine and receives logs from all Fabric hosts.

# 7. Cross-Cluster Aggregation with Thanos [ K8s ]

This section applies only to Kubernetes deployments. VM / bare-metal deployments do not include Thanos in the base model.

Each monitored Kubernetes cluster has a local Prometheus instance with a Thanos sidecar. A central Thanos Query layer federates across all per-cluster Prometheus instances, providing a unified view across AWS (EKS), GCP (GKE), and Azure (AKS) deployments.

This means:

* Collection remains cluster-local. Grafana Agent scrapes within the cluster and writes to the local Prometheus.

* Thanos sidecars expose the local Prometheus data to the central Thanos Query layer.

* Thanos Query provides cross-cluster visibility without making Prometheus itself globally distributed.

* New clusters are added by repeating the same local observability pattern and enrolling in the Thanos federation.

> **Note:** The Terraform blueprints (AKS, GKE, EKS) deploy Grafana Agent as the local scraper. The Thanos layer sits above what the blueprints configure and is managed separately as part of the central observability infrastructure.

# 8. Filtering, Relabeling, and Cardinality Control

This section applies to both Kubernetes and VM / bare-metal deployments.

Prometheus should not blindly store everything it scrapes. The standard K2view model keeps the Fabric exporter configuration minimal and applies observability policy centrally at the collection layer.

* Filtering drops low-value metric families that are not used in dashboards or alerts.

* Relabeling reduces label explosion on useful metrics by trimming the label set to only what is operationally meaningful.

* Active series is the most important operational metric for Prometheus health. It reflects the true storage and query footprint. A single metric name can expand into many series through label combinations.

* Retention targets are only meaningful if storage growth is controlled. Storage pressure can cause effective retention to fall below the configured target.

> **VM note:** On VMs, filtering and relabeling are configured directly in the Prometheus scrape configuration on the monitoring machine. On Kubernetes, they are configured in the Grafana Agent pipeline.

# 9. What a Consuming Monitoring Environment Sees

Regardless of deployment model, a consuming Grafana environment sees the same categories of data:

* Fabric and JVM metrics from the JMX Exporter — application health, memory, GC, threads, Fabric product counters

* Host and node metrics from node-exporter — CPU, memory, filesystem, network

* Kubernetes state metrics from kube-state-metrics — pod state, readiness, restarts, deployment health (K8s only)

* Logs from Promtail or Grafana Agent — detailed event context for troubleshooting

* Cross-cluster roll-up via Thanos Query — fleet-level health across AWS, GCP, and Azure (K8s only)

The value of the architecture is that these signals are normalized before they reach the consumer. By the time data is available in Grafana, it is already organized into operationally useful time series regardless of whether it originated from a JVM, a node, or Kubernetes state.

# 10. Consuming from Non-Prometheus Platforms

The producer side of the architecture is relatively stable, even when the downstream consumer changes.

Fabric exposes telemetry through the JMX Exporter as a standard Prometheus HTTP endpoint. This endpoint is the durable architectural asset — not any specific downstream tool.

If the downstream platform can consume Prometheus endpoints, the bundled JMX Exporter can be used unchanged. K2view's responsibility is to expose and document the metrics endpoint. The platform team is responsible for ingestion, dashboards, alerts, and governance in their own tool.

Supported consumption patterns:

* Direct scrape: the platform scrapes the Fabric /metrics endpoint directly (Dynatrace, Datadog, Elastic)

* Collector bridge: a vendor agent or OpenTelemetry Collector scrapes the endpoint and forwards data to the backend

* Alternate exporter: if the platform cannot consume Prometheus format, the Java agent path can be replaced with a different exporter JAR

> **Portability note:** Metrics are more portable than logs. The metrics endpoint is a standard scrapeable format. Log portability depends on the target platform's chosen log ingestion model.

# 11. Operational Considerations

## 11.1 Enabling the Exporter is Not Sufficient on Its Own

The exporter is the starting point of the observability path, not the full solution. For monitoring to be functional, the full chain must be in place: the exporter must be active, the collection layer configured, metrics scraped, filtering applied, and downstream visualization and alerting built.

## 11.2 Cardinality Must be Managed Continuously

Cardinality is an ongoing design consideration. As new namespaces, workloads, labels, and exporters are added, active series can grow over time. The observability team should regularly monitor active series counts, storage growth, and filtering effectiveness.

## 11.3 Logs and Metrics are Complementary, Not Interchangeable

Metrics are structured, numerical, and well-suited for alerting and trend analysis. Logs are event-based and best for explanation and forensic troubleshooting. They both exist within the same observability layer but should not be mistaken for each other as data types.

## 11.4 Responsibility Boundaries

K2view's role is to expose the telemetry surface and establish the standard architecture. The monitoring team that consumes this data owns the dashboards, alerts, retention policies, platform-specific ingestion, and operational processes.

## 11.5 Credentials in Configuration Files

The fabric_config.yaml file located in the monitor directory includes default JMX credentials. These should be reviewed and updated for production deployments to align with your security policies.

# Appendix: Quick Reference

## Key Ports

* 7170 — Fabric JMX Exporter (/metrics endpoint)

* 7270 — iid_finder JMX Exporter (/metrics endpoint)

* 9100 — node-exporter (default)

* 9080 — Promtail HTTP (VM model)

* 3100 — Loki push endpoint

## Key Files (inside Fabric container / host)

* $K2_HOME/monitor/jmx_exporter/jmx_prometheus_javaagent-1.5.0.jar

* $K2_HOME/monitor/jmx_exporter/fabric_config.yaml

* $K2_HOME/config/jvm.options (javaagent line appended here at startup)

* $K2_HOME/scripts/monitor_setup.sh (orchestrates monitor initialization)

* $K2_HOME/monitor/jmx_exporter/fabric_7_monitor.sh (appends javaagent, enables JMX)

* $K2_HOME/monitor/promtail/promtail_config.sh (configures and starts Promtail)

## Validation

```
curl http://localhost:7170/metrics # Fabric metrics
curl http://localhost:7270/metrics # iid_finder metrics
```

## Activation Chain (K8s)

```
Space profile: monitoring enabled (managed by K2view)
→ K2cloud Orchestrator injects MONITORING=default (Kubernetes secret)
→ docker-entrypoint.sh → init_monitoring() [cloud_common.sh]
→ monitor_setup.sh
→ fabric_7_monitor.sh (appends javaagent to jvm.options, idempotent)
→ Fabric JVM starts with exporter active
```

## Activation (VM / Bare-Metal)

```
Manual: edit $K2_HOME/config/jvm.options
OR: run $K2_HOME/monitor/jmx_exporter/fabric_7_monitor.sh
Then restart Fabric
```

## Deployment Model Summary

* K8s: Grafana Agent → per-cluster Prometheus + Thanos sidecar → central Thanos Query → Grafana

* VM: Prometheus (static targets, monitoring machine) → Grafana

* Logs K8s: Grafana Agent → Loki → Grafana

* Logs VM: Promtail → Loki → Grafana
