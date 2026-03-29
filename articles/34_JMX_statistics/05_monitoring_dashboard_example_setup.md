# Monitoring Dashboard Example Setup

> **Deployment model:** This setup guide applies to **VM and bare-metal deployments** where Fabric, Cassandra, and Kafka run as native processes on virtual machines or physical servers. For Kubernetes deployments on AKS, GKE, or EKS, see [Deploying the K2view Monitoring Stack on Kubernetes](/articles/34_JMX_statistics/Deploying_the_K2view_Monitoring_Stack_on_Kubernetes.md) and [K2view Kubernetes Monitoring Stack for Fabric](/articles/34_JMX_statistics/K2view_Kubernetes_Monitoring_Stack_for_Fabric.md).

This guide explains how to set up the components that support the [Monitoring Dashboard Example](/articles/34_JMX_statistics/04_monitoring_dashboard_example.md). It covers each Fabric workload node and the dedicated monitoring machine.



## Components

| Component     | Runs on                          | Purpose                                          |
| ------------- | -------------------------------- | ------------------------------------------------ |
| JMX Exporter  | Each Fabric node                 | Exposes Fabric and JVM metrics at port 7170      |
| Node Exporter | Each Fabric/Cassandra/Kafka node | Exposes host metrics at port 9100                |
| Promtail      | Each Fabric/Cassandra/Kafka node | Tails log files and ships them to Loki           |
| Prometheus    | Monitoring machine               | Scrapes and stores metrics                       |
| Loki          | Monitoring machine               | Receives and stores log streams                  |
| Grafana       | Monitoring machine               | Queries Prometheus and Loki; hosts the dashboard |



## Step 1 — Enable the JMX Exporter on Each Fabric Node

The JMX Exporter must be running on every Fabric node before Prometheus can scrape it.

Follow the procedure in [How to Enable the JMX Exporter for Fabric](/articles/34_JMX_statistics/How_to_Enable_the_JMX_Exporter_for_Fabric.md). The exporter is bundled with Fabric at:

```
$K2_HOME/monitor/jmx_exporter/jmx_prometheus_javaagent-1.5.0.jar
```

Once enabled, validate that the endpoint is responding on each node:

```bash
curl http://localhost:7170/metrics
```

A successful response returns Prometheus-format text. If it does not respond, resolve this before proceeding. See [How to Verify That Fabric Is Exposing Metrics](/articles/34_JMX_statistics/How_to_Verify_That_Fabric_Is_Exposing_Metrics.md).



## Step 2 — Install and Start Node Exporter on Each Node

Node Exporter must run on every machine hosting Fabric, Cassandra, or Kafka. It exposes host-level metrics (CPU, memory, filesystem, network) at port 9100.

Start Node Exporter as a background process:

```bash
nohup $K2_HOME/monitor/node_exporter/node_exporter >/dev/null 2>&1 &
```

Validate it is running:

```bash
curl http://localhost:9100/metrics
```



## Step 3 — Configure Prometheus on the Monitoring Machine

Install Prometheus on the dedicated monitoring machine. Configure it to scrape the JMX Exporter and Node Exporter endpoints on each Fabric node.

A representative `prometheus.yml` scrape configuration:

```yaml
scrape_configs:

  # Fabric JMX Exporter — one entry per Fabric node
  - job_name: fabric-jmx
    metrics_path: /metrics
    static_configs:
      - targets:
          - <FABRIC_NODE_1_IP>:7170
          - <FABRIC_NODE_2_IP>:7170

  # Node Exporter — one entry per Fabric/Cassandra/Kafka node
  - job_name: node-exporter
    metrics_path: /metrics
    static_configs:
      - targets:
          - <FABRIC_NODE_1_IP>:9100
          - <FABRIC_NODE_2_IP>:9100
          - <CASSANDRA_NODE_IP>:9100
          - <KAFKA_NODE_IP>:9100
```

Replace each `<..._IP>` placeholder with the actual IP address or hostname of that node. Add one entry per host.

After saving the configuration, start or reload Prometheus:

```bash
curl -X POST http://localhost:9090/-/reload
```



## Step 4 — Validate Prometheus Is Scraping

Before importing the dashboard, confirm that Prometheus can reach all targets.

Open the Prometheus Targets UI:

```
http://<MONITORING_MACHINE_IP>:9090/targets
```

All targets should show **State: UP**. If any target shows **State: DOWN**, check:

- The JMX Exporter or Node Exporter is running on that node
- The port is reachable from the monitoring machine (firewall rules)
- The IP address in the scrape configuration is correct

Do not proceed to Grafana setup until all targets are healthy.



## Step 5 — Configure Log Shipping (Promtail and Loki)

### Loki (monitoring machine)

Install Loki on the monitoring machine. A configuration example is available here:

[loki-local-config-example.yaml](https://support.k2view.com/Academy/articles/21_Fabric_troubleshooting/resources/loki-local-config-example.yaml)

After adjusting the configuration for your deployment, place the file in the Loki installation directory and name it `loki-local-config.yaml`. Start Loki before starting any Promtail instances.

### Promtail (each Fabric node)

Install Promtail on each node hosting Fabric, Cassandra, or Kafka. Configuration examples for a two-node Fabric cluster:

- Node 1: [promtail-config-example-fabric1.yaml](https://support.k2view.com/Academy/articles/34_JMX_statistics/resources/promtail-config-example-fabric1.yaml)
- Node 2: [promtail-config-example-fabric2.yaml](https://support.k2view.com/Academy/articles/34_JMX_statistics/resources/promtail-config-example-fabric2.yaml)

For each config file, update the following before deploying:

- **Line 7** — the IP address of the monitoring machine (where Loki is running)
- **Line 24** — the IP address of the Fabric node this Promtail instance is running on

The dashboard assumes Fabric log files are located at:

```
/opt/apps/k2view/logs/k2fabric.log
```

If your deployment uses a different path, update the `__path__` value in the Promtail configuration and the corresponding log query in the Grafana dashboard panels.

After adjusting the configuration, place it in the Promtail installation directory as `promtail-fabric-config.yaml` and start Promtail.



## Step 6 — Configure Grafana

Install Grafana on the monitoring machine (tested on version 8.3.4 and above).

Add two data sources:

1. **Prometheus** — point to `http://localhost:9090` (or the monitoring machine IP if Grafana is on a separate host)
2. **Loki** — point to `http://localhost:3100`

Then import the dashboard:

1. In Grafana, go to **Dashboards > Import**
2. Upload or paste the contents of the [dashboard JSON](https://support.k2view.com/Academy/articles/34_JMX_statistics/resources/grafana_fabric_all_base_reference.json)
3. Select the Prometheus and Loki data sources when prompted



## Step 7 — Validate the Dashboard

After importing, open the dashboard and confirm that panels are showing data:

- **Fabric Health** panels should show node count, CPU, memory, and heap
- **Fabric Logs** panel should show recent entries from `k2fabric.log`
- **Node** panels should show host-level CPU and memory from Node Exporter

If panels show "No data":

- Confirm the correct data source is selected for each panel
- Confirm Prometheus targets are UP (Step 4)
- Confirm Promtail is running and Loki is receiving logs
- Allow one full scrape interval (default 30–60 seconds) after starting Prometheus before expecting data



## Further Reading

- [How to Enable the JMX Exporter for Fabric](/articles/34_JMX_statistics/How_to_Enable_the_JMX_Exporter_for_Fabric.md)
- [How to Verify That Fabric Is Exposing Metrics](/articles/34_JMX_statistics/How_to_Verify_That_Fabric_Is_Exposing_Metrics.md)
- [How to Configure the Collection Layer to Scrape Fabric Metrics](/articles/34_JMX_statistics/How_to_Configure_the_Collection_Layer_to_Scrape_Fabric_Metrics.md)
- [How to Control Metric Volume with Filtering and Relabeling](/articles/34_JMX_statistics/How_to_Control_Metric_Volume_with_Filtering_and_Relabeling.md)
- [K2view VM / Bare-Metal Monitoring Stack for Fabric](/articles/34_JMX_statistics/K2view_VM_Bare-Metal_Monitoring_Stack_for_Fabric.md)
- [Monitoring Dashboard Example](/articles/34_JMX_statistics/04_monitoring_dashboard_example.md)

