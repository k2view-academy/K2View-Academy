# K2view Fabric Observability — Guide to the Documentation

*A roadmap for understanding and implementing Fabric monitoring — from first principles to production deployment*

## Table of Contents

* [Introduction](#introduction)
* [How to Use This Guide](#how-to-use-this-guide)
* [The Complete Document Map](#the-complete-document-map)
  * [Stage 0 — Why Monitoring Matters *— context for all readers*](#stage-0-why-monitoring-matters-context-for-all-readers)
  * [Stage 1 — Understand the Architecture *— choose your path here*](#stage-1-understand-the-architecture-choose-your-path-here)
  * [Stage 2 — Understand What Fabric Exposes *— the metric surface*](#stage-2-understand-what-fabric-exposes-the-metric-surface)
  * [Stage 3 — Enable and Verify *— get metrics flowing*](#stage-3-enable-and-verify-get-metrics-flowing)
  * [Stage 4 — Deploy and Configure *— paths diverge again here*](#stage-4-deploy-and-configure-paths-diverge-again-here)
  * [Stage 5 — Control Metric Volume *— for all deployment models*](#stage-5-control-metric-volume-for-all-deployment-models)
  * [Stage 6 — Extend to Third-Party Platforms *— optional — if not using the K2view default stack*](#stage-6-extend-to-third-party-platforms-optional-if-not-using-the-k2view-default-stack)
* [Reading Path Summary](#reading-path-summary)
* [What This Documentation Does Not Cover](#what-this-documentation-does-not-cover)
* [Key Concepts Quick Reference](#key-concepts-quick-reference)
* [Version Note](#version-note)

# Introduction

K2view Fabric produces rich observability data — application metrics, JVM telemetry, infrastructure signals, and logs. A set of documents, how-to guides, and K2view Academy articles exists to help you understand, deploy, and operate this monitoring capability.

This guide is your starting point. It explains what each document covers, where it fits in the overall learning sequence, and which ones apply to your specific situation. Rather than reading everything in order, use this guide to navigate directly to what you need.

The documentation covers two distinct deployment models. Everything about monitoring works differently depending on which one applies to you:

* VM / Bare-Metal — Fabric runs as a native process on virtual machines or physical servers. Monitoring is enabled manually and uses Prometheus with static scrape targets.

* Kubernetes (K2cloud SaaS / Self-hosted) — Fabric runs as a pod. Monitoring is enabled through the space profile, which is managed by K2view. Recent profiles have monitoring enabled by default. Grafana Agent as the local collector.

* Kubernetes (Air-Gapped) — Fabric runs as a pod on a customer-owned AKS, GKE, or EKS cluster without K2cloud Orchestrator. Monitoring must be enabled manually by setting the MONITORING environment variable in the Fabric pod spec.

Every document in this set is clearly labeled with which deployment model it applies to. When you see [ VM / Bare-Metal ] or [ Kubernetes ], that section applies only to that context. When you see [ All ], it applies to both.

# How to Use This Guide

Find your situation below and follow the recommended reading path. Each path is a sequence — read the documents in order the first time through. Return to individual documents later as a reference when you need specific details.

Most readers will need one of three paths:

* Path A — I am setting up monitoring on VMs or bare-metal hosts

* Path B — I am setting up monitoring on a new Kubernetes cluster (K2cloud SaaS / Self-hosted)

* Path C — I have an existing Kubernetes cluster and want to add or improve Fabric monitoring

* Path D — I am on an air-gapped Kubernetes cluster without K2cloud Orchestrator

All three paths share a common foundation in Stages 0 and 1. After that, they diverge. The paths reconverge in Stages 4 and 5 for configuration tuning and third-party integration.

# The Complete Document Map

The following table lists every document in this set, its type, and the deployment model it covers. Use it as a quick reference index.

<table>
  <thead>
    <tr>
      <th>Document</th>
      <th>Type</th>
      <th>Covers</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Fabric Monitoring</td>
      <td>Concept</td>
      <td>Why monitoring matters for Fabric in production</td>
    </tr>
    <tr>
      <td>JMX Overview</td>
      <td>Concept</td>
      <td>What JMX and MBeans are, why Fabric uses them</td>
    </tr>
    <tr>
      <td>JMX Format</td>
      <td>Reference</td>
      <td>The metric format Fabric exposes — names, labels, types</td>
    </tr>
    <tr>
      <td>JMX Custom Statistics</td>
      <td>How-to</td>
      <td>How to add custom metrics to the Fabric JMX surface</td>
    </tr>
    <tr>
      <td>K2view Observability Architecture for Fabric</td>
      <td>Reference</td>
      <td>All — full architecture across K8s and VM/bare-metal</td>
    </tr>
    <tr>
      <td>K2view VM / Bare-Metal Monitoring Stack for Fabric</td>
      <td>Reference</td>
      <td>VM only — components, data flow, configuration</td>
    </tr>
    <tr>
      <td>K2view Kubernetes Monitoring Stack for Fabric</td>
      <td>Reference</td>
      <td>K8s only — Grafana Agent, Thanos, enablement chain</td>
    </tr>
    <tr>
      <td>Fabric Monitoring in Air-Gapped Kubernetes Deployments</td>
      <td>Reference + How-to</td>
      <td>K8s only — customer-owned clusters without K2cloud Orchestrator</td>
    </tr>    
    <tr>
      <td>How to Enable the JMX Exporter for Fabric</td>
      <td>How-to</td>
      <td>All — K8s automated path and VM manual path</td>
    </tr>
    <tr>
      <td>How to Verify That Fabric Is Exposing Metrics</td>
      <td>How-to</td>
      <td>All — validation procedure for both models</td>
    </tr>
    <tr>
      <td>How to Configure the Collection Layer to Scrape Fabric Metrics</td>
      <td>How-to</td>
      <td>All — Grafana Agent (K8s) and Prometheus (VM)</td>
    </tr>
    <tr>
      <td>How to Control Metric Volume with Filtering and Relabeling</td>
      <td>How-to</td>
      <td>All — River pipeline (K8s) and Prometheus YAML (VM)</td>
    </tr>
    <tr>
      <td>How to Use the Shipped JMX Exporter with Third-Party Platforms</td>
      <td>How-to</td>
      <td>All — integration patterns for non-K2view monitoring stacks</td>
    </tr>
    <tr>
      <td>Deploying the K2view Monitoring Stack on Kubernetes</td>
      <td>How-to</td>
      <td>K8s only — Terraform/Helm for Azure, GCP, AWS</td>
    </tr>
    <tr>
      <td>Monitoring Dashboard Example</td>
      <td>Reference</td>
      <td>VM — the reference Grafana dashboard panels and queries</td>
    </tr>
    <tr>
      <td>Monitoring Dashboard Example Setup</td>
      <td>How-to</td>
      <td>VM — setting up Prometheus, Loki, and Grafana for the dashboard</td>
    </tr>
  </tbody>
</table>

## Stage 0 — Why Monitoring Matters *— context for all readers*

> **[ All deployment models ]** Read these first, regardless of your deployment model. They take 10 minutes and frame everything that follows.

Before diving into setup and configuration, it helps to understand what Fabric monitoring is trying to achieve and how JMX — the underlying technology — works. These two Academy articles provide that context.

> **Academy article Fabric Monitoring**
>
> *Why monitoring is important in Fabric deployments, what it enables operationally, and what K2view provides to support it.*
>
> **Academy article JMX Overview**
>
> *What JMX is, what MBeans are, why Fabric uses JMX to expose telemetry, and why this matters for the monitoring architecture.*

After reading these two articles you will understand why the JMX Exporter exists and what problem it solves. Everything in the rest of the documentation builds on this foundation.

## Stage 1 — Understand the Architecture *— choose your path here*

Stage 1 is where the reading paths diverge. Start with the full architecture overview, then read the stack document that matches your deployment model.

> **[ All deployment models ]** Read this first — it covers both models and is the anchor for all other documents.
>
> **Reference document K2view Observability Architecture for Fabric**
>
> *The complete layered architecture covering Kubernetes and VM/bare-metal: how Fabric exposes metrics, how the collection layer works, how Thanos federates across clusters, and how the VM stack differs. Clearly labeled throughout so you can skip sections that don't apply to you.*

Then read the stack document that matches your environment:

> **[ VM / Bare-Metal ]** If you are on VMs or bare-metal:
>
> **Reference document K2view VM / Bare-Metal Monitoring Stack for Fabric**
>
> *The VM-specific architecture: JMX Exporter and Node Exporter running on the Fabric host, Prometheus scraping via static targets on a dedicated monitoring machine, Promtail shipping logs to Loki, and Grafana unifying the view. Includes the reference architecture diagram.*
>
> **[ Kubernetes ]** If you are on Kubernetes (AKS, GKE, or EKS):
>
> **Reference document K2view Kubernetes Monitoring Stack for Fabric**
>
> *The Kubernetes-specific architecture: what runs inside the Fabric pod vs. outside it, how Grafana Agent scrapes metrics and collects logs, how the observability namespace is organized, the full enablement chain from space profile to active exporter, and how Thanos federates across clusters.*

For air-gapped Kubernetes deployments on customer-owned clusters without K2cloud Orchestrator:

> **Reference document Fabric Monitoring in Air-Gapped Kubernetes Deployments**
>
> *This document applies to air-gapped Kubernetes deployments on customer-owned AKS, GKE, or EKS clusters that do not use K2cloud Orchestrator or space profiles. For K2cloud SaaS and K2cloud Self-hosted customers, see K2view Kubernetes Monitoring Stack for Fabric. For VM and bare-metal deployments, see K2view VM / Bare-Metal Monitoring Stack for Fabric.*

After Stage 1 you will have a clear mental model of the monitoring system for your environment. Stages 2 onward are hands-on.

## Stage 2 — Understand What Fabric Exposes *— the metric surface*

> **[ All deployment models ]** These documents cover what Fabric actually exposes and how to read it. Both deployment models.

Before configuring any collection layer, understand what the Fabric JMX Exporter produces. These two Academy articles describe the metric format and how to extend it.

> **Academy article JMX Format**
>
> *The Prometheus-format metric output that Fabric exposes through the JMX Exporter: metric naming conventions, label structure, metric types (counters, gauges, histograms), and what the raw /metrics endpoint looks like.*
>
> **Academy article JMX Custom Statistics**
>
> *How to add your own custom metrics to the Fabric JMX surface using Fabric's custom statistics API. Relevant when you need project-specific counters or gauges to appear alongside the standard Fabric and JVM metrics.*

These are reference articles — you do not need to memorize them before proceeding. Return to JMX Format when you are writing filtering rules and need to know which metric families exist.

## Stage 3 — Enable and Verify *— get metrics flowing*

> **[ All deployment models ]** This stage is hands-on. The procedure differs between K8s and VM but the validation step is the same.

This is the first operational stage. By the end of Stage 3, Fabric will be actively exposing metrics and you will have confirmed that the endpoint is working.

> **How-to How to Enable the JMX Exporter for Fabric**
>
> *The complete enablement procedure for both deployment models. For Kubernetes: the full chain is setup in the space profile through K2cloud Orchestrator, MONITORING=default, monitor_setup.sh, and fabric_7_monitor.sh appending the javaagent line to jvm.options. For VM/bare-metal: running fabric_7_monitor.sh directly or editing jvm.options manually.*
>
> **How-to How to Verify That Fabric Is Exposing Metrics**
>
> *How to confirm the JMX Exporter is active and serving metrics. Covers where to run the curl command (from inside the pod in Kubernetes, or on the host for VMs), what a successful response looks like, and a full set of common failure modes and their causes.*

After Stage 3, you should be able to run:

```
curl http://localhost:7170/metrics
```

and receive Prometheus-format output. Do not proceed to Stage 4 until this is working.

## Stage 4 — Deploy and Configure *— paths diverge again here*

Stage 4 is where you set up the collection layer that will scrape Fabric metrics and make them available in Prometheus and Grafana. The steps are very different between VM and Kubernetes deployments.

> **[ VM / Bare-Metal ]** VM / Bare-Metal path:

On VMs, you set up Prometheus, Loki, and Grafana on a dedicated monitoring machine, configure static scrape targets, and import the reference dashboard.

> **Academy how-to Monitoring Dashboard Example Setup**
>
> *The complete setup guide for the VM monitoring stack: installing and configuring Prometheus with scrape jobs for Fabric and Node Exporter, setting up Loki, configuring Grafana with both data sources, and importing the reference dashboard JSON. This is the practical companion to the VM stack reference document.*
>
> **Academy reference Monitoring Dashboard Example**
>
> *The reference Grafana dashboard panels and their queries. Covers Fabric Health, Fabric Performance, JVM, Cassandra, Kafka, and log panels. Use this as the template for building your operational monitoring view. The dashboard JSON is available for download from the Academy.*
>
> **How-to How to Configure the Collection Layer to Scrape Fabric Metrics**
>
> *Prometheus static scrape configuration for Fabric (port 7170), iid_finder (port 7270), and Node Exporter (port 9100). Includes how to reload Prometheus after configuration changes and how to validate that targets are being scraped.*
>
> **[ Kubernetes ]** Kubernetes path:

On Kubernetes, you use the Terraform blueprints to provision Grafana Agent and the supporting observability stack, then add the Fabric-specific scrape configuration.

> **How-to Deploying the K2view Monitoring Stack on Kubernetes**
>
> *The Terraform/Helm deployment guide for all three cloud platforms: Azure (AKS), GCP (GKE), and AWS (EKS). Covers what the blueprints deploy, what inputs are required, the per-cloud procedure, and — critically — what needs to be added after deployment to connect Fabric metrics to Grafana Agent.*
>
> **How-to How to Configure the Collection Layer to Scrape Fabric Metrics**
>
> *How to configure Grafana Agent to discover and scrape Fabric pods: annotation-based autodiscovery vs. explicit River pipeline. Includes filtering rules to apply before forwarding to Prometheus, and how to validate that scraping is working.*

## Stage 5 — Control Metric Volume *— for all deployment models*

> **[ All deployment models ]** Read this after Stage 4 is working. It applies to both VM and Kubernetes.

Once metrics are flowing, the next step is making sure you are collecting the right amount of the right data. Without filtering, Prometheus can ingest far more than is operationally useful, which leads to storage pressure, slower queries, and reduced effective retention.

> **How-to How to Control Metric Volume with Filtering and Relabeling**
>
> *How to filter metric families, reduce label cardinality, and manage active series in both Grafana Agent (River pipeline syntax) and Prometheus (YAML syntax). Covers the most common high-volume exporters — Node Exporter, kube-state-metrics, and the Fabric JMX Exporter — with practical examples and a step-by-step procedure for establishing a baseline and improving it.*

The key principle: the Fabric exporter exposes broadly by default. The collection layer decides what to retain. Filtering is not an optional afterthought — it is part of the core design.

## Stage 6 — Extend to Third-Party Platforms *— optional — if not using the K2view default stack*

> **[ All deployment models ]** Read this only if you are integrating with a platform other than the K2view standard Prometheus/Grafana/Loki stack.

If your organization uses a different observability platform — such as Datadog, Dynatrace, Elastic, New Relic, or any other tool that can consume Prometheus metrics — the JMX Exporter endpoint is reusable without any changes to Fabric.

> **How-to How to Use the Shipped JMX Exporter with Third-Party Monitoring Platforms**
>
> *The four integration patterns for third-party consumption: direct platform scrape, collector or agent bridge, Prometheus-style forwarder, and alternate exporter. Covers the important difference in how the endpoint is accessed between Kubernetes (inside the pod) and VM deployments (localhost or host IP). Includes the division of responsibility between K2view and the platform team.*

The core message of this document: the durable architectural asset is the /metrics endpoint, not any specific downstream tool. K2view provides and documents the endpoint. The platform team owns how it is consumed.

# Reading Path Summary

Use the table below to find the fastest path to your goal.

<table>
  <thead>
    <tr>
      <th>Your situation</th>
      <th>Recommended reading path</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>I am new to Fabric monitoring and want to understand the overall picture</td>
      <td>Stage 0 → Stage 1 (architecture overview only) → return here to choose a path</td>
    </tr>
    <tr>
      <td>I need to set up monitoring on VMs or bare-metal hosts</td>
      <td>Stage 0 → Stage 1 (VM stack doc) → Stage 2 → Stage 3 → Stage 4 (VM path) → Stage 5</td>
    </tr>
    <tr>
      <td>I need to deploy a new Kubernetes cluster with monitoring on Azure, GCP, or AWS</td>
      <td>Stage 0 → Stage 1 (K8s stack doc) → Stage 2 → Stage 3 → Stage 4 (K8s path) → Stage 5</td>
    </tr>
    <tr>
      <td>I am on an air-gapped Kubernetes cluster without K2cloud Orchestrator</td>
      <td>Stage 1 (Air-Gapped doc) → Stage 3 → connect your own observability stack to port 7170</td>
    </tr>
    <tr>
      <td>I have an existing Kubernetes cluster and want to add or improve Fabric monitoring</td>
      <td>Stage 1 (K8s stack doc) → Stage 3 → Stage 4 (K8s path, start at 'Connecting Fabric Metrics') → Stage 5</td>
    </tr>
    <tr>
      <td>I need to integrate Fabric metrics with a third-party platform</td>
      <td>Stage 3 (verify endpoint is working) → Stage 6</td>
    </tr>
    <tr>
      <td>I need to reduce Prometheus storage or improve retention</td>
      <td>Stage 5 directly — How to Control Metric Volume with Filtering and Relabeling</td>
    </tr>
    <tr>
      <td>I want to add custom metrics to Fabric</td>
      <td>Stage 2 (JMX Custom Statistics) → Stage 3 to confirm they appear in the output</td>
    </tr>
    <tr>
      <td>Something is broken and I need to troubleshoot</td>
      <td>Stage 3 (How to Verify) → Stage 4 (How to Configure, troubleshooting section)</td>
    </tr>
  </tbody>
</table>

# What This Documentation Does Not Cover

To set accurate expectations, here is what falls outside the scope of this document set:

* Dashboard creation from scratch — the reference dashboard JSON is provided as a starting point, not a complete finished product. Extend it with your own panels and queries.

* Alert rule design — alerting thresholds and conditions are environment-specific. The documents describe the metrics available but do not prescribe alert rules.

* Fabric application development — how to instrument Fabric applications with custom metrics is covered in JMX Custom Statistics, but the broader topic of Fabric development is outside scope.

* Grafana Cloud account setup — the Terraform blueprints reference Grafana Cloud endpoints. Setting up a Grafana Cloud account and obtaining API tokens is outside the scope of this documentation.

* Thanos central infrastructure setup — the documents describe how Thanos federation works and how each cluster participates, but the central Thanos Query layer is managed separately and its setup is not covered here.

* Cassandra and Kafka monitoring — the reference Grafana dashboard includes panels for Cassandra and Kafka. Their monitoring setup is not covered in this document set.

# Key Concepts Quick Reference

If you encounter a term and are unsure what it means in this context, use this table.

<table>
  <thead>
    <tr>
      <th>Term</th>
      <th>What it means in this context</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>JMX Exporter</td>
      <td>The open-source Prometheus JMX Exporter JAR that K2view bundles with Fabric. Reads JMX MBeans from the Fabric JVM and serves them as Prometheus-format HTTP metrics.</td>
    </tr>
    <tr>
      <td>MBeans</td>
      <td>Managed Beans — the JMX objects through which Fabric exposes its runtime telemetry. The JMX Exporter reads MBeans and converts them to Prometheus format.</td>
    </tr>
    <tr>
      <td>/metrics endpoint</td>
      <td>The HTTP endpoint served by the JMX Exporter. Prometheus or Grafana Agent scrapes this endpoint. Port 7170 for Fabric, port 7270 for iid_finder.</td>
    </tr>
    <tr>
      <td>Grafana Agent</td>
      <td>The local metrics collector used in Kubernetes deployments. Scrapes Fabric pods and other sources, then remote-writes to Prometheus. Configured via River pipelines.</td>
    </tr>
    <tr>
      <td>River pipeline</td>
      <td>The configuration language used by Grafana Agent to define discovery, scraping, filtering, and forwarding of metrics and logs.</td>
    </tr>
    <tr>
      <td>Thanos</td>
      <td>The federation layer above per-cluster Prometheus instances. Provides cross-cluster visibility across AKS, GKE, and EKS deployments.</td>
    </tr>
    <tr>
      <td>Active series</td>
      <td>The number of distinct time series Prometheus is currently storing and updating. The primary measure of Prometheus storage pressure — driven by label cardinality, not just metric name count.</td>
    </tr>
    <tr>
      <td>Space profile monitoring setting</td>
      <td>The K2view-managed configuration that controls monitoring enablement. When monitoring is enabled, K2cloud Orchestrator injects MONITORING=default into the Fabric pod. Managed exclusively by K2view — contact K2view to confirm or request this setting.</td>
    </tr>
    <tr>
      <td>MONITORING=default</td>
      <td>The environment variable injected by K2cloud Orchestrator that causes the monitor_setup.sh script to run at container startup, appending the javaagent line and starting monitoring processes.</td>
    </tr>
    <tr>
      <td>k8s-monitoring chart</td>
      <td>The Grafana Helm chart deployed by the Terraform blueprints. Installs Grafana Agent, node-exporter, kube-state-metrics, and supporting components into the grafana-agent namespace.</td>
    </tr>
    <tr>
      <td>Promtail</td>
      <td>The log shipping agent. On VMs it runs as a background process on the Fabric host. In Kubernetes, log collection is handled by Grafana Agent instead of a standalone Promtail.</td>
    </tr>
    <tr>
      <td>Relabeling</td>
      <td>Prometheus logic that alters, drops, or normalizes labels during or after scraping. Used to reduce label cardinality and control active series count.</td>
    </tr>
  </tbody>
</table>

# Version Note

All documents in this set reflect the following component versions:

* JMX Exporter: jmx_prometheus_javaagent-1.5.0.jar

* Grafana k8s-monitoring Helm chart: as referenced in the Terraform blueprints (February 2025)

* Fabric: 8.4

The K2view Academy articles (JMX Overview, JMX Format, JMX Custom Statistics, Monitoring Dashboard Example, and Monitoring Dashboard Example Setup) reflect the current Fabric 8.4 documentation. Earlier versions of these articles are available through the version selector on each Academy page.
