# Deploying the K2view Monitoring Stack on Kubernetes

*Azure AKS, GCP GKE, and AWS EKS — Terraform and Helm provisioning guide*

## Table of Contents

* [1. Purpose](#1-purpose)
* [2. Overview — What the Blueprints Deploy](#2-overview-what-the-blueprints-deploy)
* [3. Prerequisites](#3-prerequisites)
  * [3.1 Tools](#31-tools)
  * [3.2 Grafana Cloud or Compatible Endpoints](#32-grafana-cloud-or-compatible-endpoints)
  * [3.3 K2view K2cloud Orchestrator Access](#33-k2view-k2cloud-orchestrator-access)
* [4. Deployment Procedure](#4-deployment-procedure)
  * [4.1 Azure — AKS](#41-azure-aks)
  * [4.2 GCP — GKE](#42-gcp-gke)
  * [4.3 AWS — EKS](#43-aws-eks)
* [5. What Gets Deployed](#5-what-gets-deployed)
* [6. Connecting Fabric Metrics — What Needs to Be Added](#6-connecting-fabric-metrics-what-needs-to-be-added)
  * [6.1 Option A — Annotation-Based Autodiscovery](#61-option-a-annotation-based-autodiscovery)
  * [6.2 Option B — Explicit River Pipeline (Recommended)](#62-option-b-explicit-river-pipeline-recommended)
* [7. Verifying the Deployment](#7-verifying-the-deployment)
  * [7.1 Confirm Grafana Agent is Running](#71-confirm-grafana-agent-is-running)
  * [7.2 Check Grafana Agent Logs](#72-check-grafana-agent-logs)
  * [7.3 Confirm Metrics Are Reaching Prometheus](#73-confirm-metrics-are-reaching-prometheus)
  * [7.4 Confirm Logs Are Reaching Loki](#74-confirm-logs-are-reaching-loki)
* [8. Key Differences Between Clouds](#8-key-differences-between-clouds)
* [9. Common Problems](#9-common-problems)
  * [Grafana Agent pods not starting](#grafana-agent-pods-not-starting)
  * [Metrics not appearing in Prometheus](#metrics-not-appearing-in-prometheus)
  * [Fabric metrics not appearing](#fabric-metrics-not-appearing)
  * [Node metrics missing](#node-metrics-missing)
  * [kube-state-metrics missing](#kube-state-metrics-missing)
* [10. Quick Checklist](#10-quick-checklist)
* [Related Topics](#related-topics)

# 1. Purpose

This document explains how the K2view monitoring infrastructure is provisioned on Kubernetes across Azure (AKS), GCP (GKE), and AWS (EKS) using the K2view Terraform blueprints. It covers what the blueprints deploy, the required inputs, how to run the deployment, and what needs to be configured after deployment to connect Fabric metrics.

This document is for platform engineers and DevOps teams responsible for standing up or maintaining the K2view observability stack on cloud-managed Kubernetes clusters.

# 2. Overview — What the Blueprints Deploy

> **[ Azure + GCP + AWS ]** The Grafana Agent Helm chart is the same across all three cloud platforms.

The K2view Terraform blueprints provision the cluster infrastructure and deploy the Grafana Agent observability stack during the same Terraform run. The monitoring deployment is controlled by a single flag:

```
deploy_grafana_agent = true
```

When this flag is true, Terraform deploys the Grafana k8s-monitoring Helm chart into the cluster. This chart installs:

* Grafana Agent — the local metrics collector and log forwarder
* prometheus-node-exporter — host metrics from each worker node (DaemonSet)
* kube-state-metrics — Kubernetes object and workload state
* Prometheus Operator CRDs — required by the monitoring stack
* OpenCost — Kubernetes cost monitoring (enabled by default in GCP and AWS)

Grafana Agent is configured to remote-write metrics to an external Prometheus endpoint and forward logs to an external Loki endpoint. Both endpoints are provided as Terraform input variables. The cluster name, credentials, and endpoint URLs are the three things you must supply before running.

> **Important:** The blueprints deploy the Grafana Agent and its supporting components. They do NOT automatically configure Grafana Agent to scrape Fabric pods. Fabric metric collection requires additional configuration after the stack is deployed. See Section 6.

# 3. Prerequisites

> **[ Azure + GCP + AWS ]** Applies to all three cloud platforms.

Before running the Terraform deployment, confirm the following:

## 3.1 Tools

* Terraform >= 1.0 installed and configured
* kubectl configured to access the target cluster (or will be configured after cluster creation)
* Helm 3 installed
* Cloud CLI authenticated: az (Azure), gcloud (GCP), aws (AWS)

## 3.2 Grafana Cloud or Compatible Endpoints

The Grafana Agent requires two external endpoints to send data to:

* A Prometheus remote-write endpoint (metrics destination)
* A Loki push endpoint (logs destination)

These can be Grafana Cloud endpoints or self-hosted Prometheus and Loki instances. You will need:

* The Prometheus host URL
* The Prometheus basic auth username
* The Loki host URL
* The Loki basic auth username
* An access token or password for both (typically a single Grafana Cloud access policy token)

> **Note:** The GCP and AWS modules also support a Tempo (distributed tracing) endpoint. Tracing is disabled by default. If not using Tempo, the token placeholder is still required in the Terraform variable but traces will not be sent.

## 3.3 K2view K2cloud Orchestrator Access

The blueprints also deploy the K2view Agent (k2v_agent), which connects the cluster to K2view K2cloud Orchestrator via a mailbox ID. This is separate from monitoring but is deployed in the same Terraform run. You will need:

* A mailbox ID from K2view K2cloud Orchestrator
* The mailbox URL (default: https://cloud.k2view.com/api/mailbox)

# 4. Deployment Procedure

## 4.1 Azure — AKS

> **[ Azure / AKS ]** Blueprint path: blueprints/Azure/terraform/AKS/

The Azure blueprint deploys an AKS cluster and, optionally, Grafana Agent. The Grafana Agent values are supplied via a separate YAML file rather than individual Terraform variables.

### Step 1 — Configure the tfvars file

Copy or edit the template:

```
blueprints/Azure/terraform/AKS/terraform.tfvars.template
```

Key variables to set:

```
cluster_name = "your-cluster-name"
resource_group_name = "your-resource-group"
location = "your-azure-region"
deploy_grafana_agent = true
mailbox_id = "your-k2view-mailbox-id"
```

### Step 2 — Configure grafana-agent-values.yaml

Edit the Grafana Agent values file in the AKS directory:

```
blueprints/Azure/terraform/AKS/grafana-agent-values.yaml
```

Replace all placeholder tokens:

```yaml
cluster:
   name: <YOUR_CLUSTER_NAME>
externalServices:
   prometheus:
      host: <PROMETHEUS_URL>
      basicAuth:
         username: <PROMETHEUS_USER>
         password: <GRAFANA_TOKEN>
   loki:
      host: <LOKI_URL>
      basicAuth:
         username: <LOKI_USER>
         password: <GRAFANA_TOKEN>

**Note:** The Azure blueprint uses a local copy of the k8s-monitoring chart from blueprints/Azure/helm/charts/grafana-agent/k8s-monitoring/. GCP and AWS pull from the published Grafana Helm registry. The chart behavior is the same.
```

### Step 3 — Initialize and apply

```
cd blueprints/Azure/terraform/AKS
terraform init
terraform plan
terraform apply

**Private clusters:** If private_cluster_enabled = true in your tfvars, Grafana Agent and other Helm-based components will not be deployed by Terraform. They must be deployed manually after the cluster is created and a private network path is available.
```

## 4.2 GCP — GKE

> **[ GCP / GKE ]** Blueprint path: blueprints/gcp/terraform/GKE/

The GCP blueprint deploys a GKE cluster and, optionally, Grafana Agent. All Grafana Agent configuration is passed as Terraform variables.

### Step 1 — Configure the tfvars file

Copy or edit the template:

```
blueprints/gcp/terraform/GKE/terraform.tfvars.template
```

Key variables to set:

```
project_id = "your-gcp-project-id"
cluster_name = "your-cluster-name"
region = "gcp-region"
deploy_grafana_agent = true
grafana_token = "your-grafana-access-policy-token"
mailbox_id = "your-k2view-mailbox-id"
```

The Prometheus and Loki host URLs and usernames are pre-populated in the module variables with Grafana Cloud defaults. If using a different endpoint, override it in the tfvars file:

> # Only needed if NOT using the Grafana Cloud defaults
> # externalservices_prometheus_host = "https://<your-prometheus>"
> # externalservices_prometheus_username = <your-username>
> # externalservices_loki_host = "https://<your-loki>"
> # externalservices_loki_username = <your-username>

### Step 2 — Initialize and apply

```
cd blueprints/gcp/terraform/GKE
terraform init
terraform plan
terraform apply
```

## 4.3 AWS — EKS

> **[ AWS / EKS ]** Blueprint path: blueprints/aws/terraform/EKS/

The AWS blueprint deploys an EKS cluster and, optionally, Grafana Agent. The pattern is the same as GCP — all configurations are passed as Terraform variables.

### Step 1 — Configure the tfvars file

Create a tfvars file from the variables:

```
cluster_name = "your-cluster-name"
region = "aws-region"
deploy_grafana_agent = true
grafana_token = "your-grafana-access-policy-token"
mailbox_id = "your-k2view-mailbox-id"
```

As with GCP, the Prometheus and Loki host URLs use Grafana Cloud defaults from the module variables. Override if using different endpoints.

> **Note:** deploy_grafana_agent defaults to false in the AWS blueprint. You must explicitly set it to true in your tfvars file for Grafana Agent to be deployed.

### Step 2 — Initialize and apply

```
cd blueprints/aws/terraform/EKS
terraform init
terraform plan
terraform apply
```

# 5. What Gets Deployed

> **[ Azure + GCP + AWS ]** The Grafana Agent k8s-monitoring chart deploys the same components on all three clouds.

After a successful Terraform apply with deploy_grafana_agent = true, the following are present in the cluster:

<table>
  <thead>
    <tr>
      <th>Component</th>
      <th>Enabled by default</th>
      <th>Purpose</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Grafana Agent</td>
      <td>Yes</td>
      <td>Scrapes metrics, collects logs, remote-writes to Prometheus and Loki</td>
    </tr>
    <tr>
      <td>prometheus-node-exporter</td>
      <td>Yes</td>
      <td>Host metrics from each worker node (DaemonSet)</td>
    </tr>
    <tr>
      <td>kube-state-metrics</td>
      <td>Yes</td>
      <td>Kubernetes object and workload state from the control plane</td>
    </tr>
    <tr>
      <td>Prometheus Operator CRDs</td>
      <td>Yes</td>
      <td>Custom resource definitions required by the monitoring stack</td>
    </tr>
    <tr>
      <td>OpenCost</td>
      <td>Yes (GCP/AWS) No (Azure)</td>
      <td>Kubernetes workload cost monitoring</td>
    </tr>
    <tr>
      <td>Tempo tracing receiver</td>
      <td>No (disabled)</td>
      <td>Distributed tracing — enable via traces.enabled = true</td>
    </tr>
  </tbody>
</table>

All components are deployed into the grafana-agent namespace.

To confirm the deployment:

```
kubectl get pods -n grafana-agent
```

All pods should reach Running status within a few minutes of the Helm release completing.

# 6. Connecting Fabric Metrics — What Needs to Be Added

> **[ Azure + GCP + AWS ]** Applies to all three cloud platforms.
>
> **Critical gap:** The Grafana Agent deployment does NOT automatically scrape Fabric pods. The k8s-monitoring chart collects node metrics, cluster state, and pod logs by default — but it has no built-in knowledge of the Fabric JMX Exporter endpoint. You must add Fabric metric collection explicitly after deployment.

There are two approaches. Choose based on the level of control you need.

## 6.1 Option A — Annotation-Based Autodiscovery

The k8s-monitoring chart supports annotation-based autodiscovery. When enabled, any pod annotated with the scrape annotation is automatically discovered and scraped.

### Step 1 — Enable autodiscovery in the chart values

Add the following to your Grafana Agent values override (grafana-agent-values.yaml for Azure, or as Terraform variable overrides for GCP/AWS):

```
metrics:
   autoDiscover:
      enabled: true
```

### Step 2 — Annotate Fabric pods

Add the following annotations to the Fabric pod spec or deployment template:

```
annotations:
   k8s.grafana.com/scrape: "true"
   k8s.grafana.com/metrics.portNumber: "7170"
```

For iid_finder metrics on port 7270, configure a second scrape annotation or use Option B below for per-port control.

> **Note:** Annotation-based autodiscovery will scrape any pod in the cluster with the scrape annotation set to true. Apply the annotation only to pods you intend to monitor and ensure metric filtering rules are in place to control volume. See How to Control Metric Volume with Filtering and Relabeling.

## 6.2 Option B — Explicit River Pipeline (Recommended)

For production deployments, an explicit River pipeline gives you full control over which pods are scraped, how metrics are filtered, and what labels are applied. This is the recommended approach.

Create a River configuration file (e.g., fabric-scrape.river):

```
// Discover Fabric pods by label
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

// Filter to useful metric families before forwarding
prometheus.relabel "fabric_filter" {
   rule {
      source_labels = ["__name__"]
      regex = "fabric_.*|jvm_.*|tomcat_.*|process_.*"
      action = "keep"
   }
      forward_to = [prometheus.relabel.metrics_service.receiver]
   }

// Scrape Fabric pods and forward to filter
prometheus.scrape "fabric_jmx" {
   targets = discovery.relabel.fabric_pods.output
   job_name = "fabric-jmx"
   forward_to = [prometheus.relabel.fabric_filter.receiver]
}
```

Adjust the label selector (app=fabric) to match the actual labels on your Fabric pods.

### Passing the River config to the chart

For Azure (values file):

```
# In grafana-agent-values.yaml, add:
extraConfig: |
<paste River config inline here>
```

Or pass it as a file during Helm upgrade:

```
helm upgrade grafana-k8s-monitoring . 
--namespace grafana-agent 
--values grafana-agent-values.yaml 
--set-file extraConfig=fabric-scrape.river
```

For GCP and AWS (Terraform), add the River config as an additional Helm set in the grafana-agent module, or run a separate helm upgrade after the initial Terraform apply.

# 7. Verifying the Deployment

> **[ Azure + GCP + AWS ]** Applies to all three cloud platforms.

## 7.1 Confirm Grafana Agent is Running

```
kubectl get pods -n grafana-agent
```

Expected: all pods in Running state with no restart loops.

## 7.2 Check Grafana Agent Logs

```
kubectl logs -n grafana-agent -l app.kubernetes.io/name=grafana-agent --tail=50
```

Look for:

* Successful remote-write connections to the Prometheus and Loki endpoints
* Scrape activity for fabric-jmx job (if Fabric scraping is configured)
* No authentication errors against the remote endpoints

## 7.3 Confirm Metrics Are Reaching Prometheus

Query your Prometheus endpoint for infrastructure metrics that should be present immediately after deployment:

```
# Node metrics — from prometheus-node-exporter
node_cpu_seconds_total
# Kubernetes state — from kube-state-metrics
kube_pod_status_ready
```

Once Fabric scraping is configured, also check:

```
# Fabric JVM metrics
jvm_memory_bytes_used
# Fabric product metrics
fabric_read_total
```

## 7.4 Confirm Logs Are Reaching Loki

In Grafana, query Loki for recent logs from the cluster:

```
{cluster="<YOUR_CLUSTER_NAME>"}
```

Pod logs should appear within one scrape interval (default 60 seconds) of Grafana Agent starting.

# 8. Key Differences Between Clouds

<table>
  <thead>
    <tr>
      <th>Aspect</th>
      <th>Azure / AKS</th>
      <th>GCP / GKE</th>
      <th>AWS / EKS</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Helm chart source</td>
      <td>Local copy in blueprints repo</td>
      <td>Published Grafana Helm registry</td>
      <td>Published Grafana Helm registry</td>
    </tr>
    <tr>
      <td>Agent config method</td>
      <td>grafana-agent-values.yaml file</td>
      <td>Terraform variables</td>
      <td>Terraform variables</td>
    </tr>
    <tr>
      <td>deploy_grafana_agent default</td>
      <td>false</td>
      <td>false (flag required)</td>
      <td>false (flag required)</td>
    </tr>
    <tr>
      <td>OpenCost enabled</td>
      <td>No</td>
      <td>Yes (default)</td>
      <td>Yes (default)</td>
    </tr>
    <tr>
      <td>Tempo tracing</td>
      <td>No</td>
      <td>Configurable (off by default)</td>
      <td>Configurable (off by default)</td>
    </tr>
    <tr>
      <td>Private cluster support</td>
      <td>Manual Helm deploy required</td>
      <td>No restriction in blueprint</td>
      <td>No restriction in blueprint</td>
    </tr>
    <tr>
      <td>Fabric scraping</td>
      <td>Not configured by default — requires additional setup</td>
      <td>Not configured by default — requires additional setup</td>
      <td>Not configured by default — requires additional setup</td>
    </tr>
  </tbody>
</table>

# 9. Common Problems

## Grafana Agent pods not starting

* Check events: kubectl describe pod -n grafana-agent <pod-name>
* Authentication failure against the Prometheus or Loki endpoint — verify the token value in the values file or Terraform variable
* Incorrect endpoint URL — confirm the Prometheus and Loki host URLs are reachable from inside the cluster
* Azure private cluster: Helm-based deployments fail on private clusters — deploy Grafana Agent manually

## Metrics not appearing in Prometheus

* Confirm Grafana Agent pods are running with no crash loops
* Check Grafana Agent logs for remote-write errors
* Confirm the Prometheus endpoint URL and credentials are correct
* Check firewall or security group rules between the cluster and the Prometheus endpoint

## Fabric metrics not appearing

* Fabric scraping is not configured by default — Section 6 must be completed
* If using annotation-based autodiscovery, confirm autoDiscover.enabled: true is set, and Fabric pods have the scrape annotation
* If using an explicit River pipeline, confirm the label selector matches the actual labels on Fabric pods
* Confirm the Fabric JMX Exporter is active: kubectl exec -it <fabric-pod> -- curl http://localhost:7170/metrics
* Confirm MONITORING=default is present in the Fabric pod environment: kubectl exec <pod> -- env | grep MONITORING. If absent, contact K2view to confirm monitoring is enabled in your space profile.

## Node metrics missing

* Confirm prometheus-node-exporter DaemonSet is running: kubectl get ds -n grafana-agent
* If metrics.node-exporter.enabled is false in the chart values, re-enable it

## kube-state-metrics missing

* Confirm kube-state-metrics deployment is running: kubectl get deploy -n grafana-agent
* If kube-state-metrics.enabled is false in the chart values, re-enable it

# 10. Quick Checklist

**Before deployment:**

* Grafana Cloud or compatible Prometheus and Loki endpoints available
* Access token or credentials for both endpoints
* K2view mailbox ID available
* terraform.tfvars configured for the target cloud
* grafana-agent-values.yaml populated (Azure) or Terraform variables set (GCP/AWS)

**After deployment:**

* kubectl get pods -n grafana-agent — all pods Running
* Grafana Agent logs show no authentication or connection errors
* node_cpu_seconds_total visible in Prometheus
* kube_pod_status_ready visible in Prometheus
* Pod logs visible in Loki

**After adding Fabric scraping:**

* Fabric JMX Exporter active: curl http://localhost:7170/metrics from inside pod
* Monitoring is enabled in the space profile (confirm with K2view)
* Fabric pods annotated OR River pipeline configured
* jvm_memory_bytes_used and fabric_* metrics visible in Prometheus
* Metric filtering rules applied to control volume

# Related Topics


* [K2view Kubernetes Monitoring Stack for Fabric](/articles/34_JMX_statistics/K2view_Kubernetes_Monitoring_Stack_for_Fabric.md)
* [K2view Observability Architecture for Fabric](/articles/34_JMX_statistics/K2view_Observability_Architecture_for_Fabric.md)
* [How to Enable the JMX Exporter for Fabric](/articles/34_JMX_statistics/How_to_Enable_the_JMX_Exporter_for_Fabric.md)
* [How to Configure the Collection Layer to Scrape Fabric Metrics](/articles/34_JMX_statistics/How_to_Configure_the_Collection_Layer_to_Scrape_Fabric_Metrics.md)
* [How to Control Metric Volume with Filtering and Relabeling](/articles/34_JMX_statistics/How_to_Control_Metric_Volume_with_Filtering_and_Relabeling.md)

