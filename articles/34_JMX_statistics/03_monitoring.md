# Fabric Monitoring

K2view Fabric exposes runtime and application telemetry through JMX MBeans and a bundled Prometheus JMX Exporter. This data can be collected, stored, and visualized using the K2view monitoring stack — or consumed by any compatible third-party platform.

Monitoring enables early detection of issues, informs resource allocation decisions, and provides operational visibility into Fabric health, JVM condition, and infrastructure state.


## How Fabric Exposes Monitoring Data

K2view provides support for external monitoring through three output types:

* **Metrics** — Fabric and JVM telemetry exposed via JMX MBeans and served in Prometheus format by the bundled JMX Exporter. This is the primary path for production monitoring.
* **Log files** — Application logs available for collection and analysis. See [Fabric Troubleshooting Log Files](/articles/21_Fabric_troubleshooting/02_Fabric_troubleshooting_log_files.md).
* **Tracing files** — Request and flow traces. See [Fabric Tracing](/articles/29_tracing/README.md).


## Deployment Models

The monitoring architecture differs significantly between deployment models. The components involved, how the JMX Exporter is enabled, and how metrics are collected all differ. Read the section that applies to your environment.

### Kubernetes — K2cloud SaaS / Self-Hosted (AKS, GKE, EKS)

Monitoring is enabled through the space profile, which is managed by K2view. Recent space profiles have monitoring enabled by default — confirm with K2view that your space profile includes this setting. K2cloud Orchestrator injects the `MONITORING=default` environment variable, which triggers the monitor setup chain at container startup. Grafana Agent acts as the local metrics collector, scraping Fabric pods and forwarding metrics to Prometheus. Thanos provides cross-cluster visibility across cloud environments.

Start here:

* [K2view Kubernetes Monitoring Stack for Fabric](/articles/34_JMX_statistics/K2view_Kubernetes_Monitoring_Stack_for_Fabric.md)
* [Deploying the K2view Monitoring Stack on Kubernetes](/articles/34_JMX_statistics/Deploying_the_K2view_Monitoring_Stack_on_Kubernetes.md)

### Kubernetes — Air-Gapped (Customer-Owned Cluster)

For customer-owned AKS, GKE, or EKS clusters without K2cloud Orchestrator, monitoring is enabled manually by setting the `MONITORING` environment variable in the Fabric pod spec. The K2view Terraform blueprints are used to deploy the observability stack into the cluster.

Start here:

* [Fabric Monitoring in Air-Gapped Kubernetes Deployments](/articles/34_JMX_statistics/Fabric_Monitoring_Air-Gapped_Kubernetes.md)

### VM / Bare-Metal

Fabric runs as a native process. Monitoring is enabled manually by running the monitor setup script or editing `jvm.options` directly. Prometheus scrapes Fabric hosts using static scrape targets. Promtail ships logs to Loki.

Start here:

* [K2view VM / Bare-Metal Monitoring Stack for Fabric](/articles/34_JMX_statistics/K2view_VM_Bare-Metal_Monitoring_Stack_for_Fabric.md)
* [Monitoring Dashboard Example Setup](/articles/34_JMX_statistics/05_monitoring_dashboard_example_setup.md)


## The Monitoring Stack

The standard K2view monitoring stack combines the following components, depending on deployment model:

<table>
  <thead>
    <tr>
      <th>Component</th>
      <th>Kubernetes</th>
      <th>VM / Bare-Metal</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>JMX Exporter</td>
      <td>✓ Inside Fabric container</td>
      <td>✓ On each Fabric host</td>
    </tr>
    <tr>
      <td>Grafana Agent</td>
      <td>✓ Cluster-local collector</td>
      <td>—</td>
    </tr>
    <tr>
      <td>Prometheus</td>
      <td>✓ Per-cluster (receives remote-write)</td>
      <td>✓ On monitoring machine (scrapes directly)</td>
    </tr>
    <tr>
      <td>Thanos</td>
      <td>✓ Cross-cluster federation</td>
      <td>—</td>
    </tr>
    <tr>
      <td>Node Exporter</td>
      <td>✓ DaemonSet on worker nodes</td>
      <td>✓ On each host</td>
    </tr>
    <tr>
      <td>kube-state-metrics</td>
      <td>✓ Cluster singleton</td>
      <td>—</td>
    </tr>
    <tr>
      <td>Promtail / log collection</td>
      <td>✓ Via Grafana Agent</td>
      <td>✓ Promtail on each host</td>
    </tr>
    <tr>
      <td>Loki</td>
      <td>✓ Central log store</td>
      <td>✓ On monitoring machine</td>
    </tr>
    <tr>
      <td>Grafana</td>
      <td>✓ Visualization</td>
      <td>✓ Visualization</td>
    </tr>
  </tbody>
</table>


## Where to Start

If you are new to Fabric monitoring, the [K2view Fabric Observability — Guide to the Documentation](/articles/34_JMX_statistics/K2view_Fabric_Observability_Guide_to_the_Guide.md) maps all available documents to reading paths based on your deployment model and goal. It is the recommended starting point before reading any individual article.

A [Monitoring Dashboard Example](/articles/34_JMX_statistics/04_monitoring_dashboard_example.md) is also available as a reference Grafana dashboard that illustrates how Fabric observability data can be visualized in practice.

On VMs and bare-metal hosts, Fabric runs as a native process. Monitoring is enabled manually by running the monitor setup script or editing `jvm.options` directly. Prometheus scrapes Fabric hosts using static scrape targets. Promtail ships logs to Loki.

Start here:

* [K2view VM / Bare-Metal Monitoring Stack for Fabric](/articles/34_JMX_statistics/K2view_VM_Bare-Metal_Monitoring_Stack_for_Fabric.md)
* [Monitoring Dashboard Example Setup](/articles/34_JMX_statistics/05_monitoring_dashboard_example_setup.md)



## The Monitoring Stack

The standard K2view monitoring stack combines the following components, depending on deployment model:

<table>
  <thead>
    <tr>
      <th>Component</th>
      <th>Kubernetes</th>
      <th>VM / Bare-Metal</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>JMX Exporter</td>
      <td>✓ Inside Fabric container</td>
      <td>✓ On each Fabric host</td>
    </tr>
    <tr>
      <td>Grafana Agent</td>
      <td>✓ Cluster-local collector</td>
      <td>—</td>
    </tr>
    <tr>
      <td>Prometheus</td>
      <td>✓ Per-cluster (receives remote-write)</td>
      <td>✓ On monitoring machine (scrapes directly)</td>
    </tr>
    <tr>
      <td>Thanos</td>
      <td>✓ Cross-cluster federation</td>
      <td>—</td>
    </tr>
    <tr>
      <td>Node Exporter</td>
      <td>✓ DaemonSet on worker nodes</td>
      <td>✓ On each host</td>
    </tr>
    <tr>
      <td>kube-state-metrics</td>
      <td>✓ Cluster singleton</td>
      <td>—</td>
    </tr>
    <tr>
      <td>Promtail / log collection</td>
      <td>✓ Via Grafana Agent</td>
      <td>✓ Promtail on each host</td>
    </tr>
    <tr>
      <td>Loki</td>
      <td>✓ Central log store</td>
      <td>✓ On monitoring machine</td>
    </tr>
    <tr>
      <td>Grafana</td>
      <td>✓ Visualization</td>
      <td>✓ Visualization</td>
    </tr>
  </tbody>
</table>



## Where to Start

If you are new to Fabric monitoring, the [K2view Fabric Observability — Guide to the Documentation](/articles/34_JMX_statistics/K2view_Fabric_Observability_Guide_to_the_Guide.md) maps all available documents to reading paths based on your deployment model and goal. It is the recommended starting point before reading any individual article.

A [Monitoring Dashboard Example](/articles/34_JMX_statistics/04_monitoring_dashboard_example.md) is also available as a reference Grafana dashboard that illustrates how Fabric observability data can be visualized in practice.
