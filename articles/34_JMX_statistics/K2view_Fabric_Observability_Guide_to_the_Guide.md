# K2view Fabric Observability — Guide to the Documentation

*A roadmap for understanding and implementing Fabric monitoring — from first principles to production deployment*

## Table of Contents

- [Introduction](https://claude.ai/chat/7fee4fd2-d075-4023-a8ba-a3f0eb21408b#introduction)
- [How to Use This Guide](https://claude.ai/chat/7fee4fd2-d075-4023-a8ba-a3f0eb21408b#how-to-use-this-guide)
- [The Content Journey](https://claude.ai/chat/7fee4fd2-d075-4023-a8ba-a3f0eb21408b#the-content-journey)
- [What This Documentation Does Not Cover](https://claude.ai/chat/7fee4fd2-d075-4023-a8ba-a3f0eb21408b#what-this-documentation-does-not-cover)
- [Key Concepts Quick Reference](https://claude.ai/chat/7fee4fd2-d075-4023-a8ba-a3f0eb21408b#key-concepts-quick-reference)
- [Version Note](https://claude.ai/chat/7fee4fd2-d075-4023-a8ba-a3f0eb21408b#version-note)

# Introduction

K2view Fabric produces rich observability data — application metrics, JVM telemetry, infrastructure signals, and logs. This guide explains what each document in the set covers, where it fits in the overall sequence, and which ones apply to your deployment model.

The documentation covers three deployment contexts:

- **VM / Bare-Metal** — Fabric runs as a native process. Monitoring is enabled manually. Prometheus scrapes static targets.
- **Kubernetes (K2cloud SaaS / Self-hosted)** — Fabric runs as a pod. Monitoring is enabled through the space profile, managed by K2view. Grafana Agent is the local collector.
- **Kubernetes (Air-Gapped)** — Fabric runs on a customer-owned cluster without K2cloud Orchestrator. Monitoring is enabled manually via the `MONITORING` environment variable in the pod spec.

Every document is labeled `[ K8s ]`, `[ VM / Bare-Metal ]`, or `[ K8s + VM ]` so you can skip sections that don't apply to your environment.

# How to Use This Guide

Follow the Content Journey in order the first time through. The sections are sequenced so that each builds on the previous one. Return to individual documents later as a reference when you need specific details.

If you already know your deployment model, you can skip the architecture documents that don't apply and go straight to the enablement and deployment sections for your environment.

# The Content Journey

<strong>Start Here</strong>

<ul> <li><a href="/articles/34_JMX_statistics/K2view_Fabric_Observability_Guide_to_the_Guide.md">K2view Fabric Observability — Guide to the Documentation</a> <br>  <em>This document. Start here to orient yourself before reading anything else.</em></li> </ul>

<strong>Concepts and Architecture</strong>

<ul>   <li><a href="/articles/34_JMX_statistics/03_monitoring.md">Fabric Monitoring</a><br>   <em>Why monitoring matters in Fabric deployments, what K2view provides to support it, and how to navigate to the right starting point for your environment.</em></li>   <li><a href="/articles/34_JMX_statistics/01_JMX_overview.md">JMX Overview</a><br>   <em>What JMX is, what MBeans are, why Fabric uses JMX to expose telemetry, and the two ways to access JMX data — the Admin panel and the Prometheus endpoint.</em></li>   <li><a href="/articles/34_JMX_statistics/K2view_Observability_Architecture_for_Fabric.md">K2view Observability Architecture for Fabric</a><br>   <em>The complete layered architecture for both deployment models — how Fabric exposes metrics, how the collection layer works, how logs are collected in parallel, and how Thanos federates across clusters.</em></li>   <li><a href="/articles/34_JMX_statistics/K2view_VM_Bare-Metal_Monitoring_Stack_for_Fabric.md">K2view VM / Bare-Metal Monitoring Stack for Fabric</a><br>   <em>The VM-specific stack: JMX Exporter and Node Exporter on each Fabric host, Prometheus with static scrape targets on a dedicated monitoring machine, Promtail shipping logs to Loki, and Grafana unifying the view. Includes the reference architecture diagram.</em></li>   <li><a href="/articles/34_JMX_statistics/K2view_Kubernetes_Monitoring_Stack_for_Fabric.md">K2view Kubernetes Monitoring Stack for Fabric</a><br>   <em>The Kubernetes-specific stack: what runs inside the Fabric pod vs. outside it, Grafana Agent as the cluster-local collector, the full enablement chain from space profile to active exporter, and Thanos federation across clusters. Includes the reference architecture diagram.</em></li>   <li><a href="/articles/34_JMX_statistics/Fabric_Monitoring_Air-Gapped_Kubernetes.md">Fabric Monitoring in Air-Gapped Kubernetes Deployments</a><br>   <em>Monitoring for customer-owned AKS, GKE, or EKS clusters without K2cloud Orchestrator — manual enablement, blueprint deployment, and validation in an air-gapped environment.</em></li> </ul>

<strong>Metric Format and Custom Statistics</strong>

<ul>   <li><a href="/articles/34_JMX_statistics/02_JMX_format.md">JMX Format</a><br>   <em>The Prometheus-format output that Fabric exposes: metric naming, label structure, metric types (counters, gauges, histograms), and what the raw /metrics endpoint looks like. Reference this when writing filtering rules.</em></li>   <li><a href="/articles/34_JMX_statistics/03_JMX_custom.md">JMX Custom Statistics</a><br>   <em>How to add project-specific counters and duration measurements to the Fabric JMX surface using the statsCount and statsDuration APIs, and how they appear in the /metrics output.</em></li> </ul>

<strong>Enable, Verify, and Deploy</strong>

<ul>   <li><a href="/articles/34_JMX_statistics/How_to_Enable_the_JMX_Exporter_for_Fabric.md">How to Enable the JMX Exporter for Fabric</a><br>   <em>The complete enablement procedure for both deployment models — the Kubernetes automation chain from space profile through fabric_7_monitor.sh, and the VM path via script or manual jvm.options editing.</em></li>   <li><a href="/articles/34_JMX_statistics/How_to_Verify_That_Fabric_Is_Exposing_Metrics.md">How to Verify That Fabric Is Exposing Metrics</a><br>   <em>How to confirm the JMX Exporter is active — where to run the curl command for each deployment model, what a successful response looks like, and how to diagnose common failure modes.</em></li>   <li><a href="/articles/34_JMX_statistics/05_monitoring_dashboard_example_setup.md">Monitoring Dashboard Example Setup</a><br>   <em>Step-by-step VM stack setup: Prometheus with scrape jobs for Fabric and Node Exporter, Loki, Promtail, Grafana data sources, and dashboard import.</em></li>   <li><a href="/articles/34_JMX_statistics/04_monitoring_dashboard_example.md">Monitoring Dashboard Example</a><br>   <em>The reference Grafana dashboard for Fabric, Cassandra, and Kafka — panel descriptions, queries, and downloadable dashboard JSON.</em></li>   <li><a href="/articles/34_JMX_statistics/Deploying_the_K2view_Monitoring_Stack_on_Kubernetes.md">Deploying the K2view Monitoring Stack on Kubernetes</a><br>   <em>Terraform/Helm deployment of the observability stack for AKS, GKE, and EKS using the K2view blueprints — what the blueprints deploy, required inputs, and the per-cloud procedure.</em></li> </ul>

<strong>Configure and Tune</strong>

<ul>   <li><a href="/articles/34_JMX_statistics/How_to_Configure_the_Collection_Layer_to_Scrape_Fabric_Metrics.md">How to Configure the Collection Layer to Scrape Fabric Metrics</a><br>   <em>Grafana Agent (Kubernetes) and Prometheus (VM) scrape configuration for Fabric (:7170), iid_finder (:7270), and Node Exporter (:9100) — with validation steps for each.</em></li>   <li><a href="/articles/34_JMX_statistics/How_to_Control_Metric_Volume_with_Filtering_and_Relabeling.md">How to Control Metric Volume with Filtering and Relabeling</a><br>   <em>How to filter metric families and reduce label cardinality to keep Prometheus storage and active series manageable — with examples in both River pipeline and Prometheus YAML syntax.</em></li> </ul>

<strong>Third-Party Integration</strong>

<ul>  <li><a href="/articles/34_JMX_statistics/How_to_Use_the_Shipped_JMX_Exporter_with_Third_Party_Platforms.md">How to Use the Shipped JMX Exporter with Third-Party Monitoring Platforms</a> <br>  <em>Four integration patterns for consuming the /metrics endpoint outside the K2view default stack — direct platform scrape, collector bridge, remote-write forwarder, and alternate exporter. Covers endpoint access differences between Kubernetes and VM deployments.</em></li> </ul>

# What This Documentation Does Not Cover

- **Dashboard creation from scratch** — the reference dashboard JSON is a starting point. Extend it with your own panels and queries.
- **Alert rule design** — alerting thresholds are environment-specific. The documents describe available metrics but do not prescribe alert rules.
- **JMX Custom Statistics beyond the API** — the broader topic of Fabric application development is outside the scope.
- **Grafana Cloud account setup** — the Terraform blueprints reference Grafana Cloud endpoints. Account setup and API token management are outside the scope.
- **Central Thanos Query layer setup** — the documents describe how each cluster participates in Thanos federation, but the central Thanos infrastructure is managed separately.
- **Cassandra and Kafka monitoring setup** — the reference dashboard includes panels for both, but their monitoring configuration is not covered here.

# Key Concepts Quick Reference

<table>   <thead>     <tr>       <th>Term</th>       <th>What it means in this context</th>     </tr>   </thead>   <tbody>     <tr>       <td>JMX Exporter</td>       <td>The open-source Prometheus JMX Exporter JAR bundled with Fabric. Reads JMX MBeans from the Fabric JVM and serves them as Prometheus-format HTTP metrics.</td>     </tr>     <tr>       <td>MBeans</td>       <td>Managed Beans — the JMX objects through which Fabric exposes its runtime telemetry. The JMX Exporter reads MBeans and converts them to Prometheus format.</td>     </tr>     <tr>       <td>/metrics endpoint</td>       <td>The HTTP endpoint served by the JMX Exporter. Port 7170 for Fabric, port 7270 for iid_finder. Scraped by Prometheus or Grafana Agent.</td>     </tr>     <tr>       <td>Grafana Agent</td>       <td>The local metrics collector used in Kubernetes deployments. Scrapes Fabric pods and other sources, then remote-writes to Prometheus. Configured via River pipelines.</td>     </tr>     <tr>       <td>River pipeline</td>       <td>The configuration language used by Grafana Agent to define discovery, scraping, filtering, and forwarding of metrics and logs.</td>     </tr>     <tr>       <td>Thanos</td>       <td>The federation layer above per-cluster Prometheus instances. Provides cross-cluster visibility across AKS, GKE, and EKS deployments.</td>     </tr>     <tr>       <td>Active series</td>       <td>The number of distinct time series Prometheus is currently storing and updating. The primary measure of Prometheus storage pressure — driven by label cardinality, not just metric name count.</td>     </tr>     <tr>       <td>Space profile monitoring setting</td>       <td>The K2view-managed configuration that controls monitoring enablement for each Fabric space. When enabled, K2cloud Orchestrator injects MONITORING=default into the Fabric pod. Managed exclusively by K2view.</td>     </tr>     <tr>       <td>MONITORING=default</td>       <td>The environment variable injected by K2cloud Orchestrator that triggers monitor_setup.sh at container startup, appending the javaagent line and starting monitoring processes.</td>     </tr>     <tr>       <td>k8s-monitoring chart</td>       <td>The Grafana Helm chart deployed by the Terraform blueprints. Installs Grafana Agent, node-exporter, kube-state-metrics, and supporting components into the grafana-agent namespace.</td>     </tr>     <tr>       <td>Promtail</td>       <td>The log shipping agent. Runs as a background process on each VM host. In Kubernetes, log collection is handled by Grafana Agent instead.</td>     </tr>     <tr>       <td>Relabeling</td>       <td>Prometheus logic that alters, drops, or normalizes labels during or after scraping. Used to reduce label cardinality and control active series count.</td>     </tr>   </tbody> </table>

# Version Note

All documents in this set reflect the following component versions:

- JMX Exporter: jmx_prometheus_javaagent-1.5.0.jar
- Grafana k8s-monitoring Helm chart: as referenced in the Terraform blueprints (February 2025)
- Fabric: 8.4
