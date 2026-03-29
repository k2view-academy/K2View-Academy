# Fabric Monitoring in Air-Gapped Kubernetes Deployments

*Enabling and exposing the Fabric metrics interface on customer-owned AKS, GKE, and EKS clusters*

## Table of Contents

* [1. Purpose](#1-purpose)
* [2. How Air-Gapped Differs from K2cloud](#2-how-air-gapped-differs-from-k2cloud)
* [3. What K2view Provides](#3-what-k2view-provides)
  * [3.1 Metrics Interface](#31-metrics-interface)
  * [3.2 Log Interface](#32-log-interface)
* [4. Enabling the Metrics Interface](#4-enabling-the-metrics-interface)
  * [4.1 Set the MONITORING Environment Variable](#41-set-the-monitoring-environment-variable)
  * [4.2 Expose the Metrics Port](#42-expose-the-metrics-port)
  * [4.3 What the Startup Chain Does](#43-what-the-startup-chain-does)
* [5. Validating the Metrics Interface](#5-validating-the-metrics-interface)
  * [5.1 Check the Environment Variable](#51-check-the-environment-variable)
  * [5.2 Confirm the javaagent Line Was Written](#52-confirm-the-javaagent-line-was-written)
  * [5.3 Validate the Metrics Endpoint](#53-validate-the-metrics-endpoint)
* [6. Connecting Your Observability Stack](#6-connecting-your-observability-stack)
  * [6.1 The K2view Blueprints as a Reference](#61-the-k2view-blueprints-as-a-reference)
  * [6.2 Scraping the Metrics Endpoint](#62-scraping-the-metrics-endpoint)
  * [6.3 Log Collection](#63-log-collection)
* [7. Multi-Cluster Considerations](#7-multi-cluster-considerations)
* [8. Troubleshooting](#8-troubleshooting)
  * [MONITORING env var is missing from the pod](#monitoring-env-var-is-missing-from-the-pod)
  * [jvm.options does not contain the javaagent line](#jvmoptions-does-not-contain-the-javaagent-line)
  * [Port 7170 is not reachable from the collector](#port-7170-is-not-reachable-from-the-collector)
  * [Metrics endpoint responds but output is very sparse](#metrics-endpoint-responds-but-output-is-very-sparse)
  * [Custom metrics are not appearing](#custom-metrics-are-not-appearing)
* [9. Quick Checklist](#9-quick-checklist)
* [Related Topics](#related-topics)

> **[ Air-Gapped / Kubernetes ]** This document applies to air-gapped Kubernetes deployments on customer-owned AKS, GKE, or EKS clusters where K2view K2cloud Orchestrator and space profiles are not used. For K2cloud SaaS and K2cloud Self-hosted customers, see K2view Kubernetes Monitoring Stack for Fabric. For VM and bare-metal deployments, see K2view VM / Bare-Metal Monitoring Stack for Fabric.

# 1. Purpose

This document explains how to enable and validate the Fabric metrics interface in air-gapped Kubernetes deployments where K2view K2cloud Orchestrator is not present. It describes what K2view provides, how to activate it, and how your observability infrastructure can connect to it.

In air-gapped deployments, the customer owns and operates the Kubernetes cluster and all supporting infrastructure. K2view provides the Fabric platform and the metrics interface. What you do with that interface — which monitoring stack you connect to it, how you store and visualize the data — is your decision and your responsibility.

# 2. How Air-Gapped Differs from K2cloud

In K2cloud SaaS and K2cloud Self-hosted deployments, monitoring enablement is handled automatically through the K2view K2cloud Orchestrator and space profile mechanism. That automation is not available in air-gapped deployments.

The following table summarizes the key differences:

<table>
  <thead>
    <tr>
      <th>Aspect</th>
      <th>K2cloud (SaaS / Self-hosted)</th>
      <th>Air-Gapped</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Cluster ownership</td>
      <td>K2view-managed or customer-managed with K2view oversight</td>
      <td>Customer-owned and operated</td>
    </tr>
    <tr>
      <td>K2cloud Orchestrator</td>
      <td>Present — manages space lifecycle and monitoring enablement</td>
      <td>Not present</td>
    </tr>
    <tr>
      <td>Space profiles</td>
      <td>Used — controls monitoring via managed configuration</td>
      <td>Not used</td>
    </tr>
    <tr>
      <td>MONITORING env var</td>
      <td>Injected automatically by K2cloud Orchestrator</td>
      <td>Must be set manually in the Fabric pod spec</td>
    </tr>
    <tr>
      <td>Observability stack</td>
      <td>K2view-provided Grafana Agent + Prometheus + Thanos + Loki</td>
      <td>Customer-provided — K2view blueprints available as a reference</td>
    </tr>
    <tr>
      <td>Terraform blueprints</td>
      <td>Available</td>
      <td>Available at github.com/k2view/blueprints</td>
    </tr>
  </tbody>
</table>

# 3. What K2view Provides

In an air-gapped deployment, K2view provides two monitoring interfaces. These are available once the Fabric pod is running with monitoring enabled:

## 3.1 Metrics Interface

The Prometheus JMX Exporter is bundled with every Fabric image. When activated, it serves Fabric and JVM metrics in Prometheus exposition format over HTTP:

```
http://<FABRIC_POD_IP>:7170/metrics # Fabric JVM and application metrics
http://<FABRIC_POD_IP>:7270/metrics # iid_finder metrics (if iid_finder is running)
```

This endpoint serves standard Prometheus text format. Any monitoring platform or collector that can scrape a Prometheus-format HTTP endpoint can consume it — no changes to the Fabric image or configuration are required on your side.

> **Note:** The exporter binds to the pod's network interface. To scrape it from outside the pod, the port must be exposed in the container spec and reachable from your collector. See Section 5.

## 3.2 Log Interface

Fabric writes application logs to the filesystem inside the pod at:

```
$K2_HOME/logs/k2fabric.log
```

How you collect these logs is your decision. Common approaches include shipping via a sidecar log agent, using your cloud provider\'s native log collection (such as Azure Monitor, AWS CloudWatch, or GCP Cloud Logging), or deploying Promtail to forward logs to Loki. K2view does not prescribe the log collection path for air-gapped deployments.

# 4. Enabling the Metrics Interface

Without K2cloud Orchestrator, you must activate the JMX Exporter by setting the MONITORING environment variable in the Fabric pod specification. This is the equivalent of what K2cloud Orchestrator does automatically in K2cloud deployments.

## 4.1 Set the MONITORING Environment Variable

In your Fabric pod or deployment spec, add the following environment variable:

```
env:
   - name: MONITORING
     value: "default"
```

When the Fabric container starts with MONITORING=default, the container startup script runs monitor_setup.sh, which calls fabric_7_monitor.sh. This script appends the javaagent line to jvm.options and enables JMX remote management. The Fabric JVM then starts with the exporter active.

The environment variable can be delivered as a plain environment variable in the pod spec, or as a Kubernetes Secret. If using a Secret, the Secret should contain:

> data:
>
>    MONITORING: ZGVmYXVsdA== # base64 of \'default\'
>
> **Note:** Setting MONITORING=NONE suppresses monitoring entirely. If MONITORING is absent from the pod environment, the monitor setup scripts do not run and the exporter is not activated.

## 4.2 Expose the Metrics Port

For your collector to scrape the endpoint, port 7170 must be exposed in the Fabric container spec:

```
ports:
   - name: jmx-metrics
     containerPort: 7170
     protocol: TCP
```

If iid_finder is running in your deployment, also expose port 7270:

```
- name: iid-metrics
  containerPort: 7270
  protocol: TCP
```

To make the ports discoverable by a Kubernetes-native collector, create a Service that exposes these ports, or rely on pod annotation-based discovery if your collector supports it.

## 4.3 What the Startup Chain Does

For reference, the full chain triggered by MONITORING=default at container startup is:

```
docker-entrypoint.sh
→ init_monitoring() in cloud_common.sh
  → monitor_setup.sh
    → setup_monitor() — copies monitor/ dir to $FABRIC_HOME if needed
    → init_monitor() — calls fabric_7_monitor.sh
    → start_monitor() — starts node_exporter and promtail as background processes

fabric_7_monitor.sh:
→ checks if javaagent line already in jvm.options (idempotent)
→ appends: -javaagent:.../jmx_prometheus_javaagent-1.5.0.jar=7170:.../fabric_config.yaml
→ enables JMX remote management settings in jvm.options
```

**Note on start_monitor():** The start_monitor() function also attempts to start node_exporter and promtail as background processes inside the container. In Kubernetes, node-level metrics are more appropriately collected by a DaemonSet node-exporter on the worker node rather than from inside the Fabric container. Whether you use the in-container node_exporter or a DaemonSet-based one is your decision.

# 5. Validating the Metrics Interface

After the Fabric pod starts with MONITORING=default, validate that the endpoint is active before connecting your collector.

## 5.1 Check the Environment Variable

Confirm MONITORING is set correctly in the running pod:

```
kubectl exec -it <fabric-pod> -n <namespace> -- env | grep MONITORING
```

Expected output:

```
MONITORING=default
```

## 5.2 Confirm the javaagent Line Was Written

Confirm that fabric_7_monitor.sh ran successfully and appended the javaagent line to jvm.options:

```
kubectl exec -it <fabric-pod> -n <namespace> -- grep jmx_prometheus $K2_HOME/config/jvm.options
```

Expected output (line may wrap):

```
-javaagent:$K2_HOME/monitor/jmx_exporter/jmx_prometheus_javaagent-1.5.0.jar=7170:$K2_HOME/monitor/jmx_exporter/fabric_config.yaml
```

## 5.3 Validate the Metrics Endpoint

Query the endpoint from inside the pod:

```
kubectl exec -it <fabric-pod> -n <namespace> -- curl http://localhost:7170/metrics
```

A successful response returns Prometheus text format output including jvm\_*, fabric\_*, and tomcat\_* metric families. If the endpoint does not respond, see the troubleshooting section below.

To validate from outside the pod (e.g., from a collector pod in the same cluster), use the pod IP or Service endpoint:

```
curl http://<POD_IP>:7170/metrics
```

For iid_finder metrics:

```
kubectl exec -it <fabric-pod> -n <namespace> -- curl http://localhost:7270/metrics
```

# 6. Connecting Your Observability Stack

Once the metrics endpoint is validated, connecting it to your observability infrastructure is straightforward. The endpoint is a standard Prometheus-format HTTP endpoint — any collector, agent, or platform that can scrape this format can consume it.

K2view does not prescribe which observability stack you use. The following describes the interface you are connecting to, not a required implementation.

## 6.1 The K2view Blueprints as a Reference

The K2view Terraform blueprints, available at:

```
https://github.com/k2view/blueprints
```

include a Grafana Agent k8s-monitoring Helm chart deployment that represents one way to implement the collection layer. The blueprints deploy:

* Grafana Agent — scrapes metrics and collects logs, remote-writes to an external Prometheus endpoint

* prometheus-node-exporter — host metrics from each worker node (DaemonSet)

* kube-state-metrics — Kubernetes object and workload state

You can use these blueprints as-is, adapt them, or replace them entirely with your own observability tooling. The Fabric metrics endpoint at port 7170 is the stable interface regardless of which collection layer you choose.

> **Note:** The Grafana Agent in the K2view blueprints does not automatically scrape Fabric pods. You must add Fabric-specific scrape configuration after deployment. See How to Configure the Collection Layer to Scrape Fabric Metrics for the annotation-based and River pipeline approaches.

## 6.2 Scraping the Metrics Endpoint

At minimum, your collector needs to:

1.  Discover the Fabric pods — by label selector, annotation, or static IP, depending on your collector's discovery model

2.  Scrape port 7170 on each Fabric pod at your chosen interval

3.  Apply metric filtering before storage — the exporter exposes all available metrics by default. See How to Control Metric Volume with Filtering and Relabeling for guidance on which families to retain and how to reduce cardinality.

The core useful metric families to retain:

* fabric\_* — Fabric product metrics

* jvm\_* — JVM memory, GC, threads

* tomcat\_* — web layer throughput and errors

* process\_* — process-level CPU and file descriptors

## 6.3 Log Collection

Fabric logs are written to $K2_HOME/logs/k2fabric.log inside the container. Your options for collection include:

* Cloud-native log collection — Azure Monitor, AWS CloudWatch Logs, GCP Cloud Logging — using the node-level log agents provided by your cloud platform

* Sidecar log agent — deploy a log shipping container alongside the Fabric container in the same pod

* DaemonSet log collector — deploy a log collection DaemonSet across worker nodes

* Promtail to Loki — the K2view blueprints include Promtail configuration templates; the Grafana Agent in the k8s-monitoring chart also handles pod log collection

The Fabric log path assumed by the K2view reference configurations is:

```
/opt/apps/fabric/workspace/logs/k2fabric.log
```

If your deployment uses a different path, update any log collection configuration accordingly.

# 7. Multi-Cluster Considerations

If you are running Fabric across multiple Kubernetes clusters, you will need a strategy for aggregating metrics across them. Each cluster should have its own local collection layer scraping its Fabric pods.

K2view uses Thanos federation in its own multi-cluster deployments — one Prometheus instance per cluster with a Thanos sidecar, and a central Thanos Query layer federating across all clusters. This is one well-understood approach. Whether it is the right approach for your environment depends on your existing observability infrastructure and operational preferences.

For an overview of how the Thanos federation model works in the context of K2view deployments, see K2view Observability Architecture for Fabric.

> **Note:** The Terraform blueprints at github.com/k2view/blueprints include the Grafana Agent and supporting Helm charts but do not include Thanos configuration. Thanos is managed separately as central observability infrastructure in K2view's own deployments.

# 8. Troubleshooting

## MONITORING env var is missing from the pod

* Check the pod spec or deployment template — confirm the MONITORING environment variable is defined

* If using a Kubernetes Secret, confirm the Secret exists and is referenced correctly in the pod spec

* Redeploy the pod after adding the environment variable — the monitor setup runs only at container startup

## jvm.options does not contain the javaagent line

* Confirm MONITORING=default is present in the pod environment (Section 5.1)

* Check Fabric startup logs for errors in the monitor setup scripts:

```
kubectl logs <fabric-pod> -n <namespace> | grep -i monitor
```

* Confirm the monitor directory is present at $K2_HOME/monitor/jmx_exporter/ inside the pod

## Port 7170 is not reachable from the collector

* Confirm port 7170 is declared in the container spec (Section 4.2)

* If using a Kubernetes Service, confirm the Service exposes port 7170 and selects the correct pods

* Check network policies — if your cluster uses NetworkPolicy resources, ensure the collector namespace is permitted to reach the Fabric pod namespace on port 7170

* Test reachability from a pod in the collector's namespace:

```
kubectl run test --image=curlimages/curl --restart=Never --rm -it -- curl http://<FABRIC_POD_IP>:7170/metrics
```

## Metrics endpoint responds but output is very sparse

* Fabric may still be initializing — the JVM starts before all MBeans are registered. Wait for full Fabric startup and retry.

* Confirm the javaagent line is correctly formed in jvm.options (Section 5.2)

## Custom metrics are not appearing

* Confirm the LU function using statsCount or statsDuration has been executed at least once since Fabric started

* Query the endpoint and filter by the custom metric name:

```
kubectl exec -it <fabric-pod> -- curl -s http://localhost:7170/metrics | grep <metric_name>
```

# 9. Quick Checklist

**Enabling:**

* MONITORING=default is set in the Fabric pod spec

* Port 7170 is declared in the container spec

* Port 7270 is declared in the container spec (if iid_finder is running)

* Pod has been deployed or restarted with the new environment variable

**Validating:**

* kubectl exec confirms MONITORING=default in pod environment

* jvm.options contains the jmx_prometheus_javaagent-1.5.0.jar javaagent line

* curl http://localhost:7170/metrics from inside the pod returns Prometheus-format output

* fabric\_* and jvm\_* metrics are present in the output

**Connecting:**

* Collector can reach port 7170 on Fabric pods from its namespace

* Scrape job or discovery rule configured in your collector

* Metric filtering applied to retain useful families and control cardinality

* Log collection configured separately using your preferred approach

# Related Topics

* [K2view Observability Architecture for Fabric](/articles/34_JMX_statistics/K2view_Observability_Architecture_for_Fabric.md) — full architecture overview including Thanos federation
* [K2view Kubernetes Monitoring Stack for Fabric](/articles/34_JMX_statistics/K2view_Kubernetes_Monitoring_Stack_for_Fabric.md) — the K2cloud equivalent of this document
* [How to Enable the JMX Exporter for Fabric](/articles/34_JMX_statistics/How_to_Enable_the_JMX_Exporter_for_Fabric.md) — detailed enablement procedure
* [How to Verify That Fabric Is Exposing Metrics](/articles/34_JMX_statistics/How_to_Verify_That_Fabric_Is_Exposing_Metrics.md) — validation procedure
* [How to Configure the Collection Layer to Scrape Fabric Metrics](/articles/34_JMX_statistics/How_to_Configure_the_Collection_Layer_to_Scrape_Fabric_Metrics.md) — Grafana Agent and Prometheus scrape configuration
* [How to Control Metric Volume with Filtering and Relabeling](/articles/34_JMX_statistics/How_to_Control_Metric_Volume_with_Filtering_and_Relabeling.md) — managing active series and storage
* [How to Use the Shipped JMX Exporter with Third-Party Monitoring Platforms](/articles/34_JMX_statistics/How_to_Use_the_Shipped_JMX_Exporter_with_Third_Party_Platforms.md) — integration patterns for non-standard stacks
* K2view blueprints repository: https://github.com/k2view/blueprints
