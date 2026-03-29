# How to Control Metric Volume with Filtering and Relabeling

*Kubernetes (Grafana Agent) and VM / Bare-Metal (Prometheus)*

## Table of Contents

* [Purpose](#purpose)
* [Scope](#scope)
* [1. Core Principle](#1-core-principle)
* [2. Why This Matters](#2-why-this-matters)
  * [2.1 Active Series — The Most Important Metric](#21-active-series-the-most-important-metric)
  * [2.2 Retention Is Constrained by Both Time and Storage](#22-retention-is-constrained-by-both-time-and-storage)
* [3. The Two Levers](#3-the-two-levers)
  * [3.1 Filtering](#31-filtering)
  * [3.2 Relabeling](#32-relabeling)
* [4. Where to Apply Control](#4-where-to-apply-control)
  * [4.1 Kubernetes — Grafana Agent (River)](#41-kubernetes-grafana-agent-river)
  * [4.2 VM / Bare-Metal — Prometheus YAML](#42-vm-bare-metal-prometheus-yaml)
* [5. Which Exporters Need the Most Attention](#5-which-exporters-need-the-most-attention)
  * [5.1 Node Exporter](#51-node-exporter)
  * [5.2 kube-state-metrics](#52-kube-state-metrics)
  * [5.3 Fabric JMX Exporter](#53-fabric-jmx-exporter)
* [6. Procedure](#6-procedure)
  * [Step 1 — Establish a baseline](#step-1-establish-a-baseline)
  * [Step 2 — Identify the highest-volume exporters](#step-2-identify-the-highest-volume-exporters)
  * [Step 3 — Drop low-value metric families](#step-3-drop-low-value-metric-families)
  * [Step 4 — Reduce label explosion on retained metrics](#step-4-reduce-label-explosion-on-retained-metrics)
  * [Step 5 — Review active series and storage growth](#step-5-review-active-series-and-storage-growth)
  * [Step 6 — Reassess retention](#step-6-reassess-retention)
* [7. What Not To Do](#7-what-not-to-do)
* [8. Troubleshooting](#8-troubleshooting)
  * [Storage is still growing too quickly after filtering](#storage-is-still-growing-too-quickly-after-filtering)
  * [Dashboard panels broke after filtering](#dashboard-panels-broke-after-filtering)
  * [Active series did not decrease as expected](#active-series-did-not-decrease-as-expected)
* [9. Quick Checklist](#9-quick-checklist)
* [Related Topics](#related-topics)

# Purpose

Prometheus can collect far more data than is operationally useful. Without deliberate control, storage fills faster than expected, queries slow down, and effective retention falls below the configured target. The goal of filtering and relabeling is to keep:

* The right metric families

* The right labels

* A manageable number of active series

This is how K2view keeps the monitoring system sustainable as environments grow. The principles are the same for both Kubernetes and VM / bare-metal deployments. The syntax for applying them differs between Grafana Agent (Kubernetes) and Prometheus YAML (VM).

# Scope

This how-to covers:

* Why metric volume must be controlled

* The two main levers: filtering and relabeling

* Where to apply control in each deployment model

* Syntax examples for Grafana Agent (River) and Prometheus (YAML)

* Which exporters need the most attention

* How to validate that changes are working

It does not define a fixed allowlist. The exact rules should reflect the environment and the operational questions you need to answer.

# 1. Core Principle

> **[ K8s + VM ]** Applies to both deployment models.

The standard K2view model is:

* Fabric exposes broadly — the JMX Exporter publishes all available metrics by default

* The collection layer decides what to retain — filtering and relabeling happen in Grafana Agent or Prometheus, not in the exporter configuration itself

This keeps the Fabric-side exporter configuration simple and stable. Observability policy is centralized in the collection layer, where storage and retention effects are directly visible and can be adjusted without restarting Fabric or rebuilding the container image.

# 2. Why This Matters

> **[ K8s + VM ]** Applies to both deployment models.

Without filtering and relabeling, the collection layer can ingest:

* Too many metric families, most of which are never used in dashboards or alerts

* Too many labels, which multiply the number of distinct time series

* Too many active series, which drive storage consumption and query cost

The result is faster storage consumption, slower queries, and a Prometheus instance where configured retention is never fully achieved because storage fills sooner than the time window.

## 2.1 Active Series — The Most Important Metric

The real scaling pressure in Prometheus is not the number of metric names — it is the number of active series. A single metric with multiple labels can expand into hundreds or thousands of distinct series depending on the number of unique label value combinations.

For example, a metric with labels for namespace, pod, container, and device, each with many possible values, can generate far more series than its single metric name suggests.

Always monitor active series as your primary Prometheus health signal:

> prometheus_tsdb_head_series
>
> **Note:** A change that keeps metric names the same but reduces label cardinality can significantly reduce active series. A change that drops metric names but keeps high-cardinality labels may have less impact than expected. Focus on series count, not name count.

## 2.2 Retention Is Constrained by Both Time and Storage

Prometheus retention has two limits: a time window and a storage size cap. Whichever is reached first determines effective retention. If storage fills before the time window expires, Prometheus starts dropping older data early.

Filtering directly extends effective retention. Fewer active series means slower storage growth, which means the time-based retention target is more likely to be met in practice.

# 3. The Two Levers

> **[ K8s + VM ]** Applies to both deployment models.

## 3.1 Filtering

Filtering decides which metric families to keep and which to drop entirely.

Use filtering when:

* A metric family has no operational value — it is not used in any dashboard or alert

* An exporter exposes far more than you need

* A noisy metric family is driving storage growth

Ask these questions about each metric family:

* Do we use this in any dashboard?

* Do we use this in any alert?

* Does it support troubleshooting a real operational problem?

* Is the storage cost worth the benefit?

If the answer to all four is no, drop the family.

## 3.2 Relabeling

Relabeling decides which labels to keep, normalize, or drop on metrics that are worth retaining.

Use relabeling when:

* The metric itself is useful

* The default label set is too large

* Some labels are creating too many distinct time series

Ask these questions about each label on a high-volume metric:

* Is this label used in any dashboard filter or aggregation?

* Is this label used in any alert condition?

* Is this label value stable, or does it change frequently (high cardinality)?

Labels that encode ephemeral or overly detailed dimensions — such as individual pod names, request IDs, or content-addressed hashes — are typical candidates for removal.

> **Note:** The distinction matters: sometimes the right answer is to drop the metric; sometimes the right answer is to keep the metric but reduce its labels. Evaluate each case separately.

# 4. Where to Apply Control

## 4.1 Kubernetes — Grafana Agent (River)

> **[ K8s ]** In Kubernetes, filtering and relabeling are configured in the Grafana Agent River pipeline using prometheus.relabel components.

The prometheus.relabel component sits between the scrape and the remote_write destination. It applies rules to the scraped metrics before they are forwarded.

A complete example showing filtering to a curated set of Fabric and JVM families, plus a label drop:

```
prometheus.relabel "fabric_filter" {
  // Keep only the metric families we care about
  rule {
    source_labels = ["__name__"]
    regex = "fabric_.*|jvm_.*|tomcat_.*|process_.*"
    action = "keep"
  }
  // Drop a high-cardinality label not needed for dashboards
  rule {
    action = "labeldrop"
    regex = "some_volatile_label"
  }
  forward_to = [prometheus.relabel.metrics_service.receiver]
}
```

Wire the scrape component to forward to this relabel component:

```
prometheus.scrape "fabric_jmx" {
  targets = discovery.relabel.fabric_pods.output
  job_name = "fabric-jmx"
  forward_to = [prometheus.relabel.fabric_filter.receiver]
}
```

## 4.2 VM / Bare-Metal — Prometheus YAML

> **[ VM / Bare-Metal ]** In VM deployments, filtering and relabeling are configured using metric_relabel_configs inside each scrape job in prometheus.yml.

metric_relabel_configs runs after the scrape and controls what gets written to the Prometheus time-series database. A complete example:

```
- job_name: fabric-jmx
  metrics_path: /metrics
  static_configs:
    - targets:
      - <FABRIC_HOST>:7170
  metric_relabel_configs:
    # Keep only the metric families we care about
    - source_labels: [__name__]
      regex: 'fabric_.*|jvm_.*|tomcat_.*|process_.*'
      action: keep
    # Drop a high-cardinality label not needed for dashboards
      - action: labeldrop
      regex: 'some_volatile_label'
```

**Note:** metric_relabel_configs controls what is stored. relabel_configs (without the metric_ prefix) controls target selection before the scrape. Use metric_relabel_configs for metric family filtering and label management.

# 5. Which Exporters Need the Most Attention

## 5.1 Node Exporter

> **[ K8s + VM ]** Node Exporter is the most common source of excessive metric volume. It exposes many metric families by default, most of which have little operational value in typical deployments.

Node Exporter exposes dozens of metric families covering every aspect of the operating system. A practical starting point is to keep only the families directly useful for infrastructure monitoring:

* node_cpu_seconds_total — CPU utilization

* node_memory_* — memory usage and pressure

* node_filesystem_* — disk usage and availability

* node_disk_* — disk I/O

* node_network_* — network throughput

* node_load* — system load averages

* node_up — availability signal

Families that are rarely needed and worth dropping first:

* node_scrape_collector_* — internal exporter metrics

* node_textfile_* — unless you are actively using textfile collectors

* node_nfs_* — unless NFS is relevant to your environment

* node_xfs_*, node_zfs_* — unless these filesystems are in use

## 5.2 kube-state-metrics

> **[ K8s ]** kube-state-metrics is a Kubernetes-only component. It does not exist in VM / bare-metal deployments.

kube-state-metrics exposes Kubernetes object state metrics. The volume depends on the number of namespaces, deployments, pods, and other objects in the cluster. In large clusters it can generate significant series counts.

Focus on retaining the families that support workload health monitoring:

* kube_pod_status_* — pod readiness and state

* kube_deployment_status_* — deployment convergence

* kube_pod_container_status_restarts_total — restart tracking

* kube_node_status_* — node health

Consider dropping or limiting:

* kube_*_labels — these can have very high cardinality if pods carry many labels

* kube_*_annotations — similarly high cardinality

* kube_*_created — creation timestamps rarely needed in dashboards

## 5.3 Fabric JMX Exporter

> **[ K8s + VM ]** The Fabric exporter exposes broadly by default. Most environments only need a curated subset.

The core families worth retaining for operational monitoring:

* fabric_* — Fabric product counters and gauges

* jvm_memory_* — heap and non-heap memory

* jvm_gc_* — garbage collection behavior

* jvm_threads_* — thread pool health

* tomcat_* — connector and request metrics where applicable

* process_* — process-level CPU and file descriptor usage

Review your dashboards and alerts to determine which specific metric names within these families are actually used, and consider tightening the regex further.

# 6. Procedure

> **[ K8s + VM ]** Applies to both deployment models.

## Step 1 — Establish a baseline

Before making changes, record the current active series count. This is your baseline for measuring improvement:

```
prometheus_tsdb_head_series
```

Also note which jobs are contributing the most series. A per-job breakdown is available via:

```
sum by (job) (scrape_series_added)
```

## Step 2 — Identify the highest-volume exporters

Start with the exporters that contribute the most series. Node Exporter and kube-state-metrics (K8s only) are almost always the largest contributors in unfiltered environments. Fabric metrics are typically more manageable but should still be reviewed.

## Step 3 — Drop low-value metric families

Apply a keep rule that retains only the families you have identified as operationally useful. Start conservatively — it is easier to add back a metric family later than to discover a missing dashboard panel after dropping it.

After applying the rule, reload or redeploy the collection layer configuration and monitor active series.

## Step 4 — Reduce label explosion on retained metrics

For metrics that are useful but generating too many series, review the label set. Drop labels that are not used in any dashboard filter, alert condition, or aggregation.

Check the active series count again after applying labeldrop rules.

## Step 5 — Review active series and storage growth

After filtering changes, confirm that active series has decreased and that storage growth rate has improved. Also confirm that configured dashboards and alerts still function correctly — check each panel and alert condition individually.

## Step 6 — Reassess retention

After stabilizing active series, review whether the configured retention target is now realistically achievable given current storage. Adjust the storage size cap or retention window if needed to match the new, lower ingestion rate.

# 7. What Not To Do

> **[ K8s + VM ]** Applies to both deployment models.

* Do not rely on the exporter config as the main control point. Keep exporter config minimal and apply policy in the collection layer.

* Do not keep every metric just in case. This leads to storage pressure and weaker retention without operational benefit.

* Do not drop labels blindly. Verify that labels being dropped are not used in any current alert, dashboard, or aggregation rule.

* Do not evaluate success only by counting metric names. The real measure is the number of active series and the storage growth rate.

* Do not make multiple filtering changes at the same time. Change one thing, observe the effect, then proceed. This makes it easy to identify what caused an unexpected dashboard break.

# 8. Troubleshooting

## Storage is still growing too quickly after filtering

* Check which jobs contribute the most series: sum by (job) (scrape_series_added)

* Confirm the filtering rules are actually being applied — check the active series before and after a scrape

* Look for high-cardinality labels on retained metrics — a single label with thousands of unique values can dominate series count

* Check whether any new exporters or workloads have been added that are not yet covered by filtering rules

## Dashboard panels broke after filtering

* A required metric family was dropped — check the panel query against the filter regex and add the family back

* A required label was dropped — check the panel's groupBy or filter expressions against the labeldrop regex

* Recording rules or alert rules may reference dropped metrics — review all rules after changing filters

## Active series did not decrease as expected

* The filtering change may have targeted metric names but not the labels driving cardinality

* A high-cardinality label on a retained metric may be dominating — use sum by (__name__) (scrape_series_added) to find which metric names have the most series

* New workloads or namespaces may have added series at the same rate as the filtering removed others

# 9. Quick Checklist

**Both deployment models:**

* Baseline active series recorded before changes

* Highest-volume exporters identified

* Keep rules applied for Fabric, JVM, and operational infrastructure families

* Low-value families dropped from Node Exporter

* High-cardinality labels reviewed and dropped where not needed

* Active series count confirmed as lower after changes

* Storage growth rate confirmed as improved

* All dashboards and alerts verified as still working

* Retention target reassessed against new ingestion rate

**Kubernetes only:**

* kube-state-metrics label and annotation families reviewed for cardinality

* Grafana Agent River pipeline updated and redeployed

**VM / Bare-Metal only:**

* metric_relabel_configs added to each affected scrape job

* Prometheus reloaded after configuration change

# Related Topics

* [How to Configure the Collection Layer to Scrape Fabric Metrics](/articles/34_JMX_statistics/How_to_Configure_the_Collection_Layer_to_Scrape_Fabric_Metrics.md)
* [K2view Observability Architecture for Fabric](/articles/34_JMX_statistics/K2view_Observability_Architecture_for_Fabric.md)
* [K2view VM / Bare-Metal Monitoring Stack for Fabric](/articles/34_JMX_statistics/K2view_VM_Bare-Metal_Monitoring_Stack_for_Fabric.md)
