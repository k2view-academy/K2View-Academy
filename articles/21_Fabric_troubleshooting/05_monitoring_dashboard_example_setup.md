# Monitoring Dashboard Example Setup

Below are the guidelines for setting up Fabric components and monitoring tools, that support the [Monitoring dashboard example](/articles/21_Fabric_troubleshooting/04_monitoring_dashboard_example.md), by referring to each of the Fabric workload nodes and to the monitoring machine.

The supportive monitoring tools used for the dashboard example are: [Grafana](https://grafana.com/) (the example dashboard is being tested on several Grafana versions - 8.3.4 and up), [Prometheus](https://prometheus.io/), [JMX Exporter](https://github.com/prometheus/jmx_exporter), [Prometheus Node Exporter](https://prometheus.io/docs/guides/node-exporter/) , [Promtail](https://grafana.com/docs/loki/latest/clients/promtail), [Loki](https://grafana.com/docs/loki/).

## Installation and Execution Guidelines

- JMX Exporter should be running on all instances. The default configurations should be sufficient.
- Node Exporter should be installed and should run on all machines that have the Fabric/Cassandra/Kafka app running.
- Grafana should be installed on the monitoring machine.
  - Import the [dashboard example](/articles/21_Fabric_troubleshooting/resources/grafana_fabric_all_base_reference.json) and choose data sources as required.

- Prometheus should be installed on the monitoring machine, and it should listen to Node Exporter and JMX.
- Log metrics 

  - Promtail should be installed and should run on all machines.
  - Loki should be installed on the monitoring machine and should listen to all Promtail instances.
  - Promtail instances should send to Loki the application's log files to be monitored.
- Promtheus and Loki should be added as data sources in Grafana.
- Loki should be already running when you start running Promtail.
- The Dashboard example assume that the Fabric logs files are located on the fabric cluster nodes at "/opt/apps/k2view/logs/k2fabric.log". In case your deployment is located in another location, change the Fabric Log metric's query. A similar change may be needed in the Promtail config file.

## Configure the Monitor Log Files Tools

For illustration, below are links to configuration samples for Loki and Promtail. These configurations allow a safe Loki execution, without collecting too much chunk data. 

### Loki 

[Here](/articles/21_Fabric_troubleshooting/resources/loki-local-config-example.yaml) you can find Loki config example yaml.

After making the changes and adjustments, per your deployment, locate it in the Loki installation directory and rename it to "loki-local-config.yaml".

### Promtail 

To illustrate Fabric cluster with 2 nodes, [here](/articles/21_Fabric_troubleshooting/resources/promtail-config-example-fabric1.yaml) you can find Promtail config example yaml for node 1 and [here](/articles/21_Fabric_troubleshooting/resources/promtail-config-example-fabric1.yaml) for node 2.

After making the changes and adjustments, per your deployment, locate the Promtail config file, in the Promtail installation directory, and rename it to "promtail-fabric-config.yaml".

The required adjustments are, for example, the machine's IPs:

* line 7 - the IP of the monitoring machine (where Loki is installed).
* line 24 - the IP of the Fabric node.



[![Previous](/articles/images/Previous.png)](/articles/21_Fabric_troubleshooting/04_monitoring_dashboard_example.md)
