# How to Verify That Fabric Is Exposing Metrics

*Kubernetes and VM / Bare-Metal*

## Table of Contents

* [Purpose](#purpose)
* [Scope](#scope)
* [1. What You Are Verifying](#1-what-you-are-verifying)
* [2. Before You Begin](#2-before-you-begin)
* [3. Where to Run the Verification Command](#3-where-to-run-the-verification-command)
  * [3.1 Kubernetes](#31-kubernetes)
  * [3.2 VM / Bare-Metal](#32-vm-bare-metal)
* [4. Inspecting the Response](#4-inspecting-the-response)
* [5. Expected Outcome](#5-expected-outcome)
* [6. Common Failure Cases](#6-common-failure-cases)
  * [No response or connection refused](#no-response-or-connection-refused)
  * [Endpoint responds but output is not Prometheus format](#endpoint-responds-but-output-is-not-prometheus-format)
  * [Response is empty or very short](#response-is-empty-or-very-short)
  * [Fabric metrics visible but iid_finder metrics are not](#fabric-metrics-visible-but-iid-finder-metrics-are-not)
  * [Metrics endpoint works but Prometheus or Grafana Agent is not scraping](#metrics-endpoint-works-but-prometheus-or-grafana-agent-is-not-scraping)
* [7. Quick Verification Checklist](#7-quick-verification-checklist)
* [Related Topics](#related-topics)

# Purpose

This topic explains how to confirm that Fabric is successfully exposing Prometheus-format metrics through the bundled JMX Exporter. The goal is to validate the Fabric-side metrics surface before moving on to collection layer configuration, dashboards, or alerting.

Verification is the same whether you are on Kubernetes or a VM — the curl command and the expected output are identical. What differs is where you run the command from.

# Scope

This how-to focuses on verification only, not initial setup. It assumes the JMX Exporter has already been enabled for Fabric. If it has not, see How to Enable the JMX Exporter for Fabric first.

This how-to covers:

* What you are verifying and why
* Where to run the verification command depending on deployment model
* What a successful response looks like
* Common failure modes and how to diagnose them

# 1. What You Are Verifying

> **[ K8s + VM ]** Applies to both Kubernetes and VM / bare-metal deployments.

You are verifying four things:

* The JMX Exporter JAR was loaded by the Fabric JVM as a Java agent
* The exporter is listening on the expected port
* The /metrics endpoint is reachable from the Fabric runtime context
* The endpoint returns Prometheus-format metrics including Fabric and JVM output

There are two endpoints to verify — one for Fabric itself and one for the iid_finder process, which runs as a separate JVM:

```
http://localhost:7170/metrics # Fabric JVM and application metrics
http://localhost:7270/metrics # iid_finder metrics
```

At minimum, verify port 7170. Port 7270 is relevant if iid_finder is running in your deployment.

# 2. Before You Begin

Confirm the following before running verification:

* The JMX Exporter has been enabled — the javaagent line is present in jvm.options
* Fabric has been started or restarted since the javaagent line was added
* You know the port configured in the javaagent line (default: 7170 for Fabric, 7270 for iid_finder)

The javaagent line in jvm.options should look like:

```
-javaagent:$K2_HOME/monitor/jmx_exporter/jmx_prometheus_javaagent-1.5.0.jar=7170:$K2_HOME/monitor/jmx_exporter/fabric_config.yaml
```

# 3. Where to Run the Verification Command

The curl command is the same in both environments, but where you run it differs.

## 3.1 Kubernetes

> **[ K8s ]** Run the verification from inside the Fabric pod.

The JMX Exporter binds to localhost inside the container. It is not accessible from outside the pod without port-forwarding. Run the verification from inside the pod:

```
kubectl exec -it <fabric-pod-name> -n <namespace> -- curl http://localhost:7170/metrics
```

Or open an interactive shell inside the pod first:

```
kubectl exec -it <fabric-pod-name> -n <namespace> -- /bin/bash

curl http://localhost:7170/metrics
```

To verify the iid_finder endpoint:

```
kubectl exec -it <fabric-pod-name> -n <namespace> -- curl http://localhost:7270/metrics
```

**Note:** Replace <fabric-pod-name> and <namespace> with the actual pod name and namespace for your Fabric deployment. Use 'kubectl get pods -n <namespace>' to find the pod name.

## 3.2 VM / Bare-Metal

> **[ VM / Bare-Metal ]** Run the verification directly on the Fabric host.

On a VM or bare-metal host, the exporter binds to localhost on the Fabric machine. Run the curl command directly on that host:

```
curl http://localhost:7170/metrics
```

To verify the iid_finder endpoint:

```
curl http://localhost:7270/metrics
```

If you need to test reachability from a remote machine (such as the Prometheus monitoring machine), use the Fabric host IP:

> curl http://<FABRIC_HOST_IP>:7170/metrics
>
> **Note:** The JMX Exporter binds to 127.0.0.1 by default in some configurations. If remote access returns a connection refused error, confirm the bind address in the javaagent line and check firewall rules between the monitoring machine and the Fabric host.

# 4. Inspecting the Response

> **[ K8s + VM ]** Applies to both deployment models.

A successful response returns Prometheus-format text. It is a plain text format with one metric per line, using the pattern:

```
# HELP <metric_name> <description>
# TYPE <metric_name> <type>
<metric_name>{<labels>} <value>
```

The response from a working Fabric exporter includes:

* JVM metrics — memory usage, garbage collection, thread counts, class loading
* Fabric product metrics — reads, writes, API activity, and MBean-backed Fabric counters
* Tomcat metrics — where applicable to the Fabric runtime

You do not need to validate every metric at this stage. The key checks are:

* The response is not empty
* The response contains lines starting with # HELP, # TYPE, and metric values
* You can see at least some jvm_ prefixed metrics and some fabric_ prefixed metrics

To quickly check for Fabric-specific metrics:

```
curl -s http://localhost:7170/metrics | grep '^fabric_'
```

To check for JVM metrics:

```
curl -s http://localhost:7170/metrics | grep '^jvm_'
```

# 5. Expected Outcome

> **[ K8s + VM ]** Applies to both deployment models.

Verification is successful when all of the following are true:

* The javaagent line is present in jvm.options
* Fabric started with the exporter attached — confirmed by the JVM process loading the agent at startup
* curl http://localhost:7170/metrics returns a non-empty Prometheus-format response
* The response includes JVM metrics and Fabric metrics

At this point the Fabric-side metrics surface is working. The next step is to confirm that the collection layer (Grafana Agent on Kubernetes, or Prometheus on VMs) is successfully scraping the endpoint.

# 6. Common Failure Cases

## No response or connection refused

The most common causes:

* Fabric was not restarted after the javaagent line was added to jvm.options — restart Fabric and try again
* The javaagent line is missing from jvm.options — check the file directly
* The port in the curl command does not match the port in the javaagent line
* The exporter JAR does not exist at the path specified in the javaagent line

**Kubernetes specific:** Confirm you are running the curl from inside the pod, not from outside. The exporter is not exposed externally by default.

**VM specific:** Confirm the exporter is not bound to 127.0.0.1 if you are testing from a remote machine. Check the bind address in the javaagent line.

## Endpoint responds but the output is not in Prometheus format

* Another service may be running on port 7170 — confirm the process listening on that port is the Fabric JVM
* The exporter may have failed to load — check the Fabric startup log for javaagent errors

**VM specific:** Use 'lsof -i :7170' or 'ss -tlnp | grep 7170' on the Fabric host to confirm which process is listening on that port.

## Response is empty or very short

* Fabric may still be initializing — the exporter starts when the JVM starts, but MBeans may not all be registered until Fabric has fully started
* Wait a moment and retry — a fully started Fabric instance should return hundreds of metric lines

## Fabric metrics visible, but iid_finder metrics are not

* iid_finder may not be running in your deployment — this is expected in some configurations
* Confirm iid_finder is running, then check port 7270
* If iid_finder is running, confirm the second javaagent line is present in jvm.iid_finder.options

## Metrics endpoint works, but Prometheus or Grafana Agent is not scraping

This means the Fabric-side exposure is correct. The problem is in the collection layer, not in Fabric:

* Kubernetes: confirm Grafana Agent is configured to discover and scrape port 7170 on Fabric pods
* VM: confirm the Prometheus static scrape target lists the correct Fabric host and port
* Both: check for network reachability between the collector and the Fabric endpoint
* Both: check for filtering rules in the collector that may be dropping the series

> **Note:** A Fabric-side verification failure and a collection-layer failure look different. If curl returns valid metrics, Fabric is doing its job correctly. Investigate the collection layer separately.

# 7. Quick Verification Checklist

**Both deployment models:**

* javaagent line present in $K2_HOME/config/jvm.options
* Fabric restarted since javaagent line was added
* curl returns a non-empty Prometheus-format response
* Response contains jvm_ and fabric_ prefixed metrics
* Port 7170 used for Fabric, port 7270 used for iid_finder

**Kubernetes only:**

* curl run from inside the Fabric pod (not from outside the cluster)
* Grafana Agent configured to scrape port 7170 on Fabric pods

**VM / Bare-Metal only:**

* curl run on the Fabric host
* If testing remotely, bind address in javaagent allows remote access
* Prometheus static target configured for this host

# Related Topics

* [How to Enable the JMX Exporter for Fabric](/articles/34_JMX_statistics/How_to_Enable_the_JMX_Exporter_for_Fabric.md)
* [How to Configure the Collection Layer to Scrape Fabric Metrics](/articles/34_JMX_statistics/How_to_Configure_the_Collection_Layer_to_Scrape_Fabric_Metrics.md)
* [How to Control Metric Volume with Filtering and Relabeling](/articles/34_JMX_statistics/How_to_Control_Metric_Volume_with_Filtering_and_Relabeling.md)
* [K2view Observability Architecture for Fabric](/articles/34_JMX_statistics/K2view_Observability_Architecture_for_Fabric.md)
* [K2view VM / Bare-Metal Monitoring Stack for Fabric](/articles/34_JMX_statistics/K2view_VM_Bare-Metal_Monitoring_Stack_for_Fabric.md)
