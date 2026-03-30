# How to Enable the JMX Exporter for Fabric

*Kubernetes and VM / Bare-Metal*

## Table of Contents

* [Purpose](#purpose)
* [Scope](#scope)
* [1. Where the Exporter Lives](#1-where-the-exporter-lives)
* [2. Enabling the Exporter — Kubernetes](#2-enabling-the-exporter-kubernetes)
  * [2.1 How It Works](#21-how-it-works)
  * [2.2 The K2cloud Space Profile](#22-the-k2cloud-space-profile)
  * [2.3 What Gets Started in the Container](#23-what-gets-started-in-the-container)
  * [2.4 Suppressing Monitoring](#24-suppressing-monitoring)
* [3. Enabling the Exporter — VM / Bare-Metal](#3-enabling-the-exporter-vm-bare-metal)
  * [Option A — Run fabric_7_monitor.sh (recommended)](#option-a-run-fabric-7-monitorsh-recommended)
  * [Option B — Edit jvm.options manually](#option-b-edit-jvmoptions-manually)
  * [Starting Node Exporter and Promtail on VMs](#starting-node-exporter-and-promtail-on-vms)
* [4. Validating the Exporter is Active](#4-validating-the-exporter-is-active)
* [5. Expected Outcome](#5-expected-outcome)
* [6. Troubleshooting](#6-troubleshooting)
  * [/metrics returns nothing or connection refused](#metrics-returns-nothing-or-connection-refused)
  * [jvm.options does not contain the javaagent line](#jvmoptions-does-not-contain-the-javaagent-line)
  * [Exporter active but Prometheus or Grafana Agent is not scraping](#exporter-active-but-prometheus-or-grafana-agent-is-not-scraping)
  * [Metrics endpoint works but output seems incomplete](#metrics-endpoint-works-but-output-seems-incomplete)
* [7. Quick Checklist](#7-quick-checklist)
* [Related Topics](#related-topics)

# Purpose

This topic explains how to enable the Prometheus JMX Exporter for Fabric so that Fabric and JVM metrics are exposed on a Prometheus-format /metrics endpoint. The mechanism for enabling the exporter differs significantly between Kubernetes and VM / bare-metal deployments. Both paths are covered here.

In both cases, the end result is the same: the JMX Exporter runs as a Java agent inside the Fabric JVM, and metrics are available at:

```
http://localhost:7170/metrics # Fabric metrics
http://localhost:7270/metrics # iid_finder metrics
```

# Scope

This how-to covers:

* What the JMX Exporter is and where it lives
* How enabling works in Kubernetes (automated)
* How enabling works on VMs and bare-metal (manual or script-driven)
* How to validate that the exporter is active

It does not cover Prometheus scrape configuration, dashboard creation, or alert setup. Those are covered in related topics.

# 1. Where the Exporter Lives

The JMX Exporter is bundled with Fabric in both Kubernetes and VM deployments. It is located under the monitor directory of the Fabric installation:

```
$K2_HOME/monitor/jmx_exporter/
  jmx_prometheus_javaagent-1.5.0.jar # The exporter JAR
  fabric_config.yaml # Fabric exporter configuration
  iidfinder_config.yaml # iid_finder exporter configuration
  fabric_7_monitor.sh # Setup script that activates the exporter
```

The exporter JAR is the open-source Prometheus JMX Exporter. It is not a K2view-developed binary. K2view packages it with Fabric and provides the configuration and activation scripts around it.

The exporter configuration (fabric_config.yaml) is intentionally minimal. Fabric exposes all metrics it can by default. Filtering is applied at the Prometheus or Grafana Agent collection layer, not in the exporter itself.

> **Note:** The -javaagent line that activates the exporter does not pre-exist in jvm.options. It is written to jvm.options at setup time by fabric_7_monitor.sh. The script includes an idempotency check — it only appends the line if it is not already present, so it is safe to run more than once.

# 2. Enabling the Exporter — Kubernetes

> **[ K8s ]** Kubernetes deployments on AKS, GKE, or EKS. Monitoring is enabled automatically through K2cloud Orchestrator and the space profile.

For K2cloud SaaS and K2cloud Self-hosted customers, the JMX Exporter is enabled through the space profile, which is managed by K2view. Recent space profiles have monitoring enabled by default — confirm with K2view that your space profile includes this setting. No manual editing of jvm.options is required. For air-gapped Kubernetes deployments, see the VM / bare-metal path in Section 3, or the dedicated air-gapped document.

## 2.1 How It Works

The enablement chain is:

```
Monitoring is enabled in the space profile (confirm with K2view if unsure)
  ↓
K2cloud Orchestrator injects MONITORING=default
  into the Fabric pod as a Kubernetes secret (common-env-secrets)
  ↓
docker-entrypoint.sh runs at container startup
  calls init_monitoring() in cloud_common.sh
  ↓
init_monitoring() checks MONITORING == 'default' or 'true'
  calls monitor_setup.sh
  ↓
monitor_setup.sh runs in sequence:
  1. setup_monitor() — copies monitor/ dir to $FABRIC_HOME if needed
  2. init_monitor() — calls fabric_7_monitor.sh
  3. start_monitor() — starts Node Exporter and Promtail as background processes
  ↓
fabric_7_monitor.sh:
  checks if javaagent line already in jvm.options (idempotent)
  appends: -javaagent:.../jmx_prometheus_javaagent-1.5.0.jar=7170:.../fabric_config.yaml
  appends: -javaagent:.../jmx_prometheus_javaagent-1.5.0.jar=7270:.../iidfinder_config.yaml
  enables JMX remote management settings
  ↓
Fabric JVM starts with the exporter active metrics available at localhost:7170 and localhost:7270
```

## 2.2 The K2cloud Space Profile

The K2cloud space profile is the K2view-managed configuration that controls monitoring enablement for each Fabric space. Recent K2view space profiles have monitoring enabled by default. If you need to confirm or request that monitoring is enabled for your space, contact K2view. When monitoring is enabled in the space profile, Cloud Manager injects the MONITORING=default environment variable into the Fabric pod. This variable is what triggers the monitor setup chain at container startup.

## 2.3 What Gets Started in the Container

When MONITORING=default, the following run inside the Fabric container or on the same host:

* JMX Exporter — via the Fabric JVM (port 7170)
* iid_finder JMX Exporter — via the iid_finder JVM (port 7270)

The following run outside the Fabric container on the worker node:

* Node Exporter — deployed as a DaemonSet on the Kubernetes worker node, not inside the Fabric pod
* kube-state-metrics — cluster singleton, not inside the Fabric pod

> **Note:** The monitor directory inside the Fabric image contains node_exporter and promtail binaries. These are present due to legacy VM-era packaging. In Kubernetes, start_monitor() does attempt to start node_exporter as a background process inside the container. However, node-level metrics in Kubernetes are collected by the DaemonSet node-exporter on the worker node, which is the authoritative infrastructure metrics source. The in-container node_exporter should not be relied on for Kubernetes deployments.

## 2.4 Suppressing Monitoring

To suppress monitoring for a specific pod or space, set the MONITORING environment variable to NONE. When MONITORING=NONE, monitor_setup.sh runs setup but does not start any monitoring processes.

# 3. Enabling the Exporter — VM / Bare-Metal

> **[ VM / Bare-Metal ]** Virtual machines or physical servers. Monitoring must be enabled manually or by running the monitor setup script.

On VMs and bare-metal hosts, there is no K2cloud Orchestrator automation. The JMX Exporter must be enabled manually or by running the setup script directly. There are two options.

## Option A — Run fabric_7_monitor.sh (recommended)

If the monitor directory has been deployed to the Fabric host, run the setup script:

```
$K2_HOME/monitor/jmx_exporter/fabric_7_monitor.sh
```

This script performs all of the following automatically:

* Checks whether the javaagent line is already present in jvm.options — safe to run more than once
* Appends the javaagent line for Fabric metrics at port 7170
* Appends the javaagent line for iid_finder metrics at port 7270
* Enables JMX remote management settings in jvm.options
* Sets correct file permissions on JMX credential files if present

Alternatively, if running Fabric as a container on a VM (non-Kubernetes), set the MONITORING environment variable before starting the container:

```
MONITORING=default
```

This triggers the same monitor_setup.sh chain that runs in Kubernetes, including starting Node Exporter and Promtail as background processes on the same host.

## Option B — Edit jvm.options manually

If you prefer to edit jvm.options directly, add the following line:

```
-javaagent:$K2_HOME/monitor/jmx_exporter/jmx_prometheus_javaagent-1.5.0.jar=7170:$K2_HOME/monitor/jmx_exporter/fabric_config.yaml
```

For iid_finder metrics, also add:

```
-javaagent:$K2_HOME/monitor/jmx_exporter/jmx_prometheus_javaagent-1.5.0.jar=7270:$K2_HOME/monitor/jmx_exporter/iidfinder_config.yaml
```

**Important:** Fabric must be restarted after either option. The exporter is loaded at JVM startup — changes to jvm.options have no effect on a running Fabric process.

## Starting Node Exporter and Promtail on VMs

On VMs, Node Exporter and Promtail are not managed by Kubernetes. They must be started separately on each Fabric host. The monitor_setup.sh script handles this when MONITORING=default is set, or you can start them manually:

```
# Node Exporter
nohup $K2_HOME/monitor/node_exporter/node_exporter >/dev/null 2>&1 &

# Promtail (only if LOKI_HOST is set)
$K2_HOME/monitor/promtail/promtail_config.sh -lk <LOKI_HOST> -s fabric
```

# 4. Validating the Exporter is Active

Validation is the same for both Kubernetes and VM deployments. From inside the Fabric runtime context (inside the pod in Kubernetes, or on the host on VMs):

```
curl http://localhost:7170/metrics
```

A successful response returns Prometheus-format text. The output includes:

* JVM metrics (memory, GC, threads, class loading)
* Fabric product metrics (reads, writes, API activity, MBean-backed counters)
* Tomcat metrics where applicable

To validate iid_finder metrics:

> curl http://localhost:7270/metrics
>
> **Kubernetes note:** Run the curl command from inside the Fabric pod. The exporter binds to localhost, so it is not accessible from outside the pod without port-forwarding. Use: kubectl exec -it <fabric-pod> -- curl http://localhost:7170/metrics

# 5. Expected Outcome

After successful enablement:

* The JMX Exporter JAR is loaded into the Fabric JVM as a Java agent
* Fabric and JVM metrics are served at localhost:7170/metrics
* iid_finder metrics are served at localhost:7270/metrics
* The /metrics endpoint returns Prometheus-format text
* The endpoint is ready to be scraped by Prometheus (VM) or Grafana Agent (Kubernetes)

# 6. Troubleshooting

## /metrics returns nothing, or connection refused

* Confirm the javaagent line was written to jvm.options — check the file directly
* Confirm Fabric was restarted after the javaagent line was added
* Confirm the port in the curl command matches the port in jvm.options (default: 7170)
* Confirm the exporter JAR exists at the expected path

## jvm.options does not contain the javaagent line

**Kubernetes:** Check that the MONITORING environment variable is present in the Fabric pod: kubectl exec <pod> -- env | grep MONITORING. If MONITORING is missing, monitoring may not be enabled in your space profile — contact K2view to confirm.

**VM:** Run fabric_7_monitor.sh manually, or add the javaagent line to jvm.options directly. Restart Fabric after.

## Exporter is active but Prometheus or Grafana Agent is not scraping

This means the Fabric-side exposure is working correctly. The problem is in the collection layer:

* Kubernetes: confirm the Grafana Agent is configured to discover and scrape port 7170 on Fabric pods
* VM: confirm the Prometheus static scrape target lists the correct host and port
* Both: check for network reachability between the collector and the Fabric host or pod
* Both: check for metric filtering rules that may be dropping the series

## Metrics endpoint works, but the output seems incomplete

* The exporter exposes all MBeans by default — no filtering is applied at the exporter level
* If expected Fabric metrics are missing, the MBeans may not be registered yet (Fabric may still be initializing)
* If metrics are being dropped before reaching dashboards, check the filtering rules in Prometheus or Grafana Agent

# 7. Quick Checklist

**Kubernetes:**

* Monitoring is enabled in the space profile (confirm with K2view if unsure)
* MONITORING=default is present in the pod environment
* fabric_7_monitor.sh has run (check jvm.options for the javaagent line)
* Fabric has started with the javaagent active
* curl http://localhost:7170/metrics returns Prometheus-format output
* Grafana Agent is configured to scrape port 7170

**VM / Bare-Metal:**

* fabric_7_monitor.sh has been run OR javaagent line added to jvm.options manually
* Fabric has been restarted
* curl http://localhost:7170/metrics returns Prometheus-format output
* Node Exporter running on the Fabric host (port 9100)
* Promtail running on the Fabric host (if Loki is configured)
* Prometheus static scrape target added for this host

# Related Topics

* [K2view Observability Architecture for Fabric](/articles/34_JMX_statistics/K2view_Observability_Architecture_for_Fabric.md)
* [How to Configure the Collection Layer to Scrape Fabric Metrics](/articles/34_JMX_statistics/How_to_Configure_the_Collection_Layer_to_Scrape_Fabric_Metrics.md)
* [K2view VM / Bare-Metal Monitoring Stack for Fabric](/articles/34_JMX_statistics/K2view_VM_Bare-Metal_Monitoring_Stack_for_Fabric.md)
