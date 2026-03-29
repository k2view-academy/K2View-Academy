# How to Use the Shipped JMX Exporter with Third-Party Monitoring Platforms

*Kubernetes and VM / Bare-Metal*

## Table of Contents

* [Purpose](#purpose)
* [Scope](#scope)
* [1. Before You Begin](#1-before-you-begin)
* [2. What the Endpoint Exposes](#2-what-the-endpoint-exposes)
* [3. How the Endpoint Is Accessed](#3-how-the-endpoint-is-accessed)
  * [3.1 Kubernetes](#31-kubernetes)
  * [3.2 VM / Bare-Metal](#32-vm-bare-metal)
* [4. Integration Patterns](#4-integration-patterns)
  * [Pattern 1 — Direct Platform Scrape](#pattern-1-direct-platform-scrape)
  * [Pattern 2 — Collector or Agent Bridge](#pattern-2-collector-or-agent-bridge)
  * [Pattern 3 — Prometheus-Style Forwarder](#pattern-3-prometheus-style-forwarder)
  * [Pattern 4 — Alternate Exporter or Agent](#pattern-4-alternate-exporter-or-agent)
* [5. Division of Responsibility](#5-division-of-responsibility)
  * [5.1 What K2view Provides](#51-what-k2view-provides)
  * [5.2 What the Platform Team Provides](#52-what-the-platform-team-provides)
* [6. Metrics Portability vs. Log Portability](#6-metrics-portability-vs-log-portability)
* [7. What Not To Do](#7-what-not-to-do)
  * [Do not expose raw JMX / RMI](#do-not-expose-raw-jmx-rmi)
  * [Do not assume a third-party platform needs Prometheus server and Grafana](#do-not-assume-a-third-party-platform-needs-prometheus-server-and-grafana)
  * [Do not write metric text to logs](#do-not-write-metric-text-to-logs)
  * [Do not filter at the exporter level for third-party integrations](#do-not-filter-at-the-exporter-level-for-third-party-integrations)
* [8. Quick Checklist](#8-quick-checklist)
* [Related Topics](#related-topics)

# Purpose

This topic explains how to reuse the Prometheus JMX Exporter that K2view ships with Fabric when the downstream observability platform is not the standard K2view Prometheus / Grafana / Loki stack.

The core idea is straightforward: the durable architectural asset is not any particular monitoring server — it is the metrics endpoint that the JMX Exporter produces. Fabric exposes a standard Prometheus HTTP endpoint. Any platform that can consume that format can ingest Fabric metrics, regardless of whether it is the K2view default stack or a third-party product.

This document is platform-agnostic. Third-party platforms are referenced only as examples within each integration pattern. The patterns themselves apply equally to any compatible observability product.

# Scope

This how-to covers:

* What the JMX Exporter produces and why it is broadly reusable

* How the endpoint is accessed in Kubernetes vs. VM / bare-metal contexts

* Four integration patterns for third-party consumption

* What K2view provides vs. what the platform team provides

* What not to do

It does not cover vendor-specific dashboard design, alert content, or retention policy in third-party platforms. Those are owned by the platform team consuming the metrics.

# 1. Before You Begin

> **[ K8s + VM ]** The JMX Exporter must be enabled before any third-party platform can consume metrics from it.

Confirm that Fabric is already exposing metrics locally. Before configuring any third-party platform, verify:

```
curl http://localhost:7170/metrics
```

A successful response returns Prometheus-format text. If this does not work, the exporter is not yet active. Resolve that first using the appropriate path for your deployment:

* Kubernetes (K2cloud SaaS / Self-hosted): monitoring enablement is managed through the space profile by K2view — confirm with K2view that monitoring is enabled for your space. For air-gapped Kubernetes deployments, follow the manual enablement path

* VM / Bare-Metal: run fabric_7_monitor.sh or add the javaagent line manually to jvm.options, then restart Fabric

See How to Enable the JMX Exporter for Fabric for the full procedure.

# 2. What the Endpoint Exposes

> **[ K8s + VM ]** Applies to both deployment models.

Once the JMX Exporter is active, Fabric exposes two HTTP endpoints in standard Prometheus text format:

```
http://localhost:7170/metrics # Fabric JVM and application metrics
http://localhost:7270/metrics # iid_finder metrics (if iid_finder is running)
```

The response from port 7170 includes:

* JVM metrics — memory, garbage collection, threads, class loading

* Fabric product metrics — reads, writes, API activity, MBean-backed counters

* Tomcat metrics — where applicable to the Fabric runtime

The format is the standard Prometheus text exposition format — plain HTTP, no authentication required by default, no special client library needed. Any tool that can make an HTTP GET request and parse the Prometheus text format can consume it.

> **Note:** The exporter configuration (fabric_config.yaml) is intentionally minimal. Fabric exposes all available metrics by default. Filtering to a relevant subset is the responsibility of the consuming platform or collector, not the exporter itself.

# 3. How the Endpoint Is Accessed

This is the most important practical difference between deployment models. The endpoint exists in both cases, but how a third-party platform or collector reaches it differs significantly.

## 3.1 Kubernetes

> **[ K8s ]** The JMX Exporter binds to localhost inside the Fabric container. It is not directly reachable from outside the pod without additional configuration.

In Kubernetes, a third-party platform or collector must use one of these access models:

* Pod annotation-based scraping — if the platform's agent or collector supports Kubernetes pod autodiscovery, annotate the Fabric pod with the scrape endpoint and port. The collector discovers and scrapes the pod directly from within the cluster.

* Kubernetes Service — expose the JMX Exporter port (7170) as a named port on a Kubernetes Service. The third-party collector can then scrape the Service endpoint.

* In-cluster collector — deploy the third-party collector or agent as a DaemonSet or Deployment in the same cluster. It can then reach Fabric pods via the Kubernetes pod network.

* Port-forward — for temporary validation only, not for production use: kubectl port-forward <pod> 7170:7170

> **Note:** The Fabric JMX Exporter binds to localhost (127.0.0.1) by default. For a third-party collector running in a different pod to reach it, the Fabric pod must expose port 7170 in its container spec and a Kubernetes Service or direct pod IP must be used.

## 3.2 VM / Bare-Metal

> **[ VM / Bare-Metal ]** The JMX Exporter binds to localhost on the Fabric host. A collector running on the same host can reach it directly. A remote collector needs the host IP to be accessible.

On VMs, the access models are simpler:

* Same-host collector — if the third-party agent or collector runs on the same host as Fabric, it can scrape localhost:7170 directly. This is the cleanest and most secure approach.

* Remote scrape — if the platform scrapes from a remote location, use the Fabric host IP: http://<FABRIC_HOST_IP>:7170/metrics. Ensure the port is reachable through any firewall or security group rules between the platform and the Fabric host.

> **Note:** Keep the endpoint as local as possible where feasible. A collector agent running on the same Fabric host avoids exposing the metrics port broadly and reduces network-level attack surface.

# 4. Integration Patterns

> **[ K8s + VM ]** These four patterns cover the full range of ways a third-party platform can consume Fabric metrics. Choose based on what the platform supports.

The producer side — Fabric exposing /metrics — does not change across these patterns. What changes is only the consumer side.

## Pattern 1 — Direct Platform Scrape

The third-party platform scrapes the Fabric /metrics endpoint directly, without any intermediate collector.

This is the cleanest pattern when the platform natively supports scraping Prometheus HTTP endpoints. The platform team configures a scrape target pointing at the Fabric endpoint, selects the metric families they want to retain, and builds dashboards and alerts in their own tool.

**When to use:** the platform has a built-in Prometheus scraping capability and can reach the Fabric endpoint from its collection infrastructure.

**Examples of platforms that support this:** Dynatrace (OpenMetrics scraping), Datadog (OpenMetrics integration), Elastic (Prometheus integration), New Relic (Prometheus remote write or scrape). Check the platform's own documentation for its specific Prometheus ingestion path.

**K8s consideration:** the platform's scraping agent must be deployed in-cluster or have a path to reach the Fabric pod IP or Service. Annotation-based pod discovery is the most common mechanism.

**VM consideration:** the platform's agent should ideally run on the same host as Fabric and scrape localhost:7170 directly.

## Pattern 2 — Collector or Agent Bridge

A local collector or vendor agent scrapes the Fabric /metrics endpoint and forwards the data to the third-party platform backend. The platform itself does not scrape Fabric directly.

This pattern is common when the platform uses a push model (data must be sent to it) rather than a pull model (it scrapes endpoints itself). It is also useful when network policy or governance requires an intermediate layer between Fabric and the external platform.

**When to use:** the platform prefers or requires a local agent that collects and forwards; or network policy prevents the platform from reaching Fabric directly; or the platform uses OTLP/remote-write ingestion rather than direct scraping.

**Common collector options:**

* OpenTelemetry Collector with the Prometheus receiver — scrapes the Fabric /metrics endpoint and exports to the platform using OTLP or remote-write. Vendor-neutral and widely supported. (https://opentelemetry.io/docs/collector/)

* Vendor-specific agents — most major platforms ship their own agent that can be configured to scrape Prometheus endpoints and forward data to the platform backend. Consult the platform's agent documentation for the Prometheus scraping configuration.

**K8s consideration:** deploy the collector or agent as a DaemonSet (one per node) or as a Deployment in the same cluster. Configure it to discover Fabric pods using Kubernetes service discovery and scrape port 7170.

**VM consideration:** run the collector on the same Fabric host and point it at localhost:7170. This avoids network exposure and keeps the collection local.

## Pattern 3 — Prometheus-Style Forwarder

A lightweight Prometheus-compatible collector scrapes the Fabric endpoint and remote-writes the data to the third-party platform's storage backend, without exposing a full Prometheus/Grafana stack as the customer-facing solution.

This is a variation of Pattern 2, distinguished by the fact that the forwarding mechanism is Prometheus remote-write rather than a vendor-specific protocol. It is relevant when the platform accepts Prometheus remote-write as an ingestion path.

**When to use:** the platform accepts Prometheus remote-write; you want a simple forwarding path without a full Prometheus deployment on-site; or you are migrating from a Prometheus-centric stack to a third-party backend.

**Examples:** Elastic, New Relic, and several other platforms document Prometheus remote-write ingestion paths. A minimal Prometheus instance with remote-write configured, or an OTel Collector with a remote-write exporter, can serve this role.

## Pattern 4 — Alternate Exporter or Agent

If the third-party platform cannot consume Prometheus format at all — neither through direct scraping, a collector bridge, nor remote-write — the translation layer itself may need to change. Instead of the shipped Prometheus JMX Exporter, a different Java agent is attached to the Fabric JVM that emits metrics in the format the platform expects.

**When to use:** the platform genuinely cannot consume any Prometheus path, direct or bridged. This should be a last resort — most modern observability platforms support at least one of the first three patterns.

**How it works:** the -javaagent line in jvm.options points to a different JAR. The source telemetry is still JMX/MBeans — only the translation layer changes. The rest of the Fabric runtime is unaffected.

> **Important:** This is not the default K2view path. K2view ships and supports only the Prometheus JMX Exporter JAR. If a different agent is required, the platform team is responsible for sourcing, configuring, and supporting that agent.

# 5. Division of Responsibility

> **[ K8s + VM ]** Applies to both deployment models.

Understanding the boundary between what K2view provides and what the platform team owns is important for setting correct expectations in any third-party integration.

## 5.1 What K2view Provides

* The JMX Exporter JAR (jmx_prometheus_javaagent-1.5.0.jar), bundled with Fabric

* The exporter configuration (fabric_config.yaml), intentionally minimal

* The activation mechanism — jvm.options modification via fabric_7_monitor.sh, or automated via MONITORING=default in Kubernetes

* The metrics endpoint at localhost:7170 (Fabric) and localhost:7270 (iid_finder)

* Documentation of what the endpoint exposes and how to validate it

K2view's responsibility ends at the metrics endpoint. The endpoint is stable, documented, and follows the Prometheus standard. Beyond that, K2view does not own how external platforms consume, store, or visualize the data.

## 5.2 What the Platform Team Provides

* Scrape configuration or collector setup in the third-party platform

* Selection of which metric families to retain (filtering)

* Label normalization or enrichment rules specific to the platform

* Dashboards, monitors, alerts, and SLOs in the third-party tool

* Retention policy and storage governance in the platform

* Any network configuration needed to reach the Fabric endpoint from the platform's collection infrastructure

* Validation that the platform is receiving and correctly interpreting the metrics

> **Note:** This division is intentional. K2view keeps the exporter configuration stable and minimal. The platform team applies their own observability policy — what to keep, how long to keep it, and what to alert on — in their own tool, where they have full control.

# 6. Metrics Portability vs. Log Portability

> **[ K8s + VM ]** Applies to both deployment models.

When moving to a third-party platform, metrics and logs have different portability characteristics.

Metrics are relatively easy to port. The JMX Exporter produces a standard HTTP endpoint in Prometheus format. This format is widely understood and supported across the observability industry. Swapping the downstream platform typically requires only reconfiguring the scrape target or collector, not changing anything on the Fabric side.

Logs are more tightly coupled to the chosen logging stack. In the K2view standard model, Promtail ships logs to Loki. If the third-party platform uses a different log ingestion model — for example Elastic, Datadog, or Splunk — the log forwarding agent and pipeline will need to be replaced or reconfigured. The log content itself does not change, but how it is collected and sent does.

This means a full third-party platform migration typically requires:

* Reconfigure the scrape target or deploy a collector bridge for metrics — relatively straightforward

* Replace or reconfigure the log forwarding pipeline for logs — more involved, platform-specific

# 7. What Not To Do

> **[ K8s + VM ]** Applies to both deployment models.

## Do not expose raw JMX / RMI

The JMX Exporter exists precisely so that consumers do not need to connect directly to the JVM's JMX/RMI interface. Raw JMX/RMI is harder to secure, requires Java-specific client tooling, and is not a standard observability format. Always use the /metrics HTTP endpoint as the integration surface.

## Do not assume a third-party platform needs Prometheus server and Grafana

The JMX Exporter is Prometheus-compatible, but the downstream consumer does not have to be Prometheus or Grafana. Any platform that can consume Prometheus text format or OpenMetrics format is a valid consumer. The JMX Exporter 1.5.0 serves Prometheus text format by default and will serve OpenMetrics format when the scraping client requests it via the HTTP Accept header. Do not introduce the full K2view Prometheus/Grafana stack unnecessarily when a third-party platform already handles collection and visualization.

## Do not write metric text to logs

It is technically possible to fetch the /metrics endpoint and write the raw text output into a log stream. This should not be used as an integration approach. The Prometheus format is designed for time-series ingestion, not log storage. Writing it to logs discards the metric semantics — labels, types, and timestamps — and makes the data difficult or impossible to query meaningfully. If a proper metrics ingestion path is not immediately available, work toward Pattern 1, 2, or 3 rather than routing metrics through the log pipeline.

## Do not filter at the exporter level for third-party integrations

The fabric_config.yaml exporter configuration should remain minimal. Apply metric selection and filtering in the collector or platform, not in the exporter. This keeps the Fabric-side configuration stable and allows different consumers to apply different filtering policies without requiring changes to Fabric or its configuration.

# 8. Quick Checklist

**Before starting:**

* JMX Exporter is active and curl http://localhost:7170/metrics returns valid output

* Deployment model is clear (Kubernetes or VM / bare-metal)

* Integration pattern has been selected (Pattern 1, 2, 3, or 4)

* Network path from the collector or platform to the Fabric endpoint has been confirmed

**Kubernetes:**

* Fabric pod exposes port 7170 in its container spec

* Kubernetes Service or pod annotations configured for scrape discovery

* Third-party collector or agent deployed in-cluster

* Collector configured to discover Fabric pods and scrape port 7170

**VM / Bare-Metal:**

* Collector agent running on the Fabric host (preferred) or reachable from a remote location

* Collector configured to scrape localhost:7170 or <FABRIC_HOST_IP>:7170

* Firewall or security group allows the collection path if scraping remotely

**Both:**

* Metric families filtered to a useful subset in the collector or platform

* Expected metrics visible in the third-party platform

* Dashboards or monitors can be built from the ingested metrics

* Log forwarding path reviewed separately if logs are also required

# Related Topics

* [How to Enable the JMX Exporter for Fabric](/articles/34_JMX_statistics/How_to_Enable_the_JMX_Exporter_for_Fabric.md)
* [How to Verify That Fabric Is Exposing Metrics](/articles/34_JMX_statistics/How_to_Verify_That_Fabric_Is_Exposing_Metrics.md)
* [How to Configure the Collection Layer to Scrape Fabric Metrics](/articles/34_JMX_statistics/How_to_Configure_the_Collection_Layer_to_Scrape_Fabric_Metrics.md)
* [How to Control Metric Volume with Filtering and Relabeling](/articles/34_JMX_statistics/How_to_Control_Metric_Volume_with_Filtering_and_Relabeling.md)
* [K2view Observability Architecture for Fabric](/articles/34_JMX_statistics/K2view_Observability_Architecture_for_Fabric.md)
