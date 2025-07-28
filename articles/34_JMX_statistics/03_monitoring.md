# Fabric Monitoring

Monitoring and dashboards play an important role in supporting the stability, performance, and availability of systems such as Fabric, particularly in production environments where reliability is critical to business operations.

Effective monitoring enables early detection of potential issues, supports incident resolution, and helps inform resource management decisions. It also aids in identifying trends related to system load, performance degradation, and service responsiveness.

Many K2view customers incorporate monitoring dashboards as part of their standard IT and DevOps practices. These dashboards typically visualize key health and performance metrics from Fabric, enabling operational teams to track uptime, monitor system load (CPU, memory, storage), and observe data access and service behavior over time.

Operational data such as read/write activity, web service response times, and error rates can help identify patterns or anomalies—for example, inefficient queries or potential violations of service-level expectations. In some environments, such metrics may also have implications for cost optimization or compliance with external SLAs.

K2view provides support for external monitoring through [JMX metrics, statistics](/articles/34_JMX_statistics/README.md), [log files](/articles/21_Fabric_troubleshooting/02_Fabric_troubleshooting_log_files.md), and [tracing files](/articles/29_tracing/README.md). These outputs can be integrated with customers’ existing monitoring tools (e.g., Grafana, Prometheus) to align with their internal operational standards.

An example Fabric Monitoring Dashboard [is available](/articles/34_JMX_statistics/04_monitoring_dashboard_example.md) to illustrate how this integration can be achieved and how Fabric observability data may be used in practice.

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/34_JMX_statistics/01_JMX_overview.md)

