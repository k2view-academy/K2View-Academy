# Monitoring Dashboard Example Setup

Here are guidelines for setting up Fabric components and monitoring tools that support the [Monitoring dashboard example](/articles/34_JMX_statistics/04_monitoring_dashboard_example.md), which refer to each of the Fabric workload nodes and the monitoring machine.

The monitoring tools used for the dashboard example are: [Grafana](https://grafana.com/) (the example dashboard is being tested on several Grafana versions - 8.3.4 and up), [Prometheus](https://prometheus.io/), [JMX Exporter](https://github.com/prometheus/jmx_exporter), [Prometheus Node Exporter](https://prometheus.io/docs/guides/node-exporter/), [Promtail](https://grafana.com/docs/loki/latest/clients/promtail), [Loki](https://grafana.com/docs/loki/).

## Installation and Execution Guidelines

- JMX Exporter should be running on all instances. The default configurations should be sufficient.
- Node Exporter should be installed and should run on all machines that have the Fabric/Cassandra/Kafka app running.
- Grafana should be installed on the monitoring machine.
  - Import the [dashboard example](/articles/34_JMX_statistics/resources/grafana_fabric_all_base_reference.json) and choose data sources as required.

- Prometheus should be installed on the monitoring machine, and it should listen to Node Exporter and JMX.
- Log metrics 

  - Promtail should be installed and should run on all machines.
  - Loki should be installed on the monitoring machine and should listen to all Promtail instances.
  - Promtail instances should send the application's log files to Loki to be monitored.
- Prometheus and Loki should be added as data sources in Grafana.
- When you start running Promtail, Loki should already be running.
- The Dashboard example assumes that the Fabric log files are located on the Fabric cluster nodes at "/opt/apps/k2view/logs/k2fabric.log". If your deployment is located in a different location, modify the Fabric Log metric's query. A similar change may be needed in the Promtail config file.

## Configure the Monitor Log Files Tools

For illustration, below are links to configuration samples for Loki and Promtail. These configurations enable safe Loki execution without collecting excessive data.

### Loki 

[Here](/articles/21_Fabric_troubleshooting/resources/loki-local-config-example.yaml), you can find a Loki config example.

After making the changes and adjustments, as per your deployment, locate the file in the Loki installation directory and rename it to "loki-local-config.yaml".

### Promtail 

To illustrate a Fabric cluster with two nodes, you can find Promtail config example files [here](/articles/34_JMX_statistics/resources/promtail-config-example-fabric1.yaml) for node 1 and [here](/articles/34_JMX_statistics/resources/promtail-config-example-fabric2.yaml) for node 2.

After making the changes and adjustments as per your deployment, locate the Promtail config file in the Promtail installation directory and rename it to "promtail-fabric-config.yaml".

The required adjustments are, for example, the machine's IPs:

* line 7 - the IP of the monitoring machine (where Loki is installed).
* line 24 - the IP of the Fabric node.



[![Previous](/articles/images/Previous.png)](/articles/34_JMX_statistics/04_monitoring_dashboard_example.md)
