# JMX Custom Statistics

## Extending Monitoring with Custom JMX Statistics

K2view Fabric allows customers and developers to enhance observability by implementing custom JMX monitors tailored to their operational needs. By using the provided statistics APIs, teams can define and expose business-specific or performance-related metrics directly into the JMX subsystem. These custom statistics are then available through the Prometheus HTTP endpoint at `http://localhost:7170/metrics`, alongside standard Fabric and JVM metrics, for collection by Prometheus, Grafana Agent, or any compatible monitoring platform.

This capability is particularly useful for surfacing application-specific counters — such as the number of processed records by status, the frequency of specific business events, or the duration of key operations. Developers can integrate these probes into their Logical Unit (LU) code using the `statsCount` and `statsDuration` methods provided by the Fabric API.



## Fabric Statistics APIs

### statsCount

Use this method to count events, bytes, records, or any other incrementing value.

```java
public static void statsCount(String entry, String key, long value)
```

| Parameter | Description                                                  |
| --------- | ------------------------------------------------------------ |
| `entry`   | The primary key for this statistic — appears as the metric group name |
| `key`     | The sub-key for this statistic — appears as a label or sub-metric |
| `value`   | The numeric value to record                                  |

Fabric uses this value to track the total, last value, average, call count, and timestamp of the most recent call.

### statsDuration

Use this method to measure the elapsed time of an operation. The measurement begins when the method is called and ends when `.close()` is called on the returned object.

```java
public static AutoCloseable statsDuration(String entry, String key)
```

| Parameter | Description                        |
| --------- | ---------------------------------- |
| `entry`   | The primary key for this statistic |
| `key`     | The sub-key for this statistic     |

Call `.close()` on the returned object to mark the end of the measured interval.



## Code Example

The following example queries invoice counts by status and records them as custom JMX statistics:

```java
public static String fnCustomStats() throws Exception {

    String status = "success";

    Db ci = db("fabric");

    String sql = "SELECT COUNT(*) NO_OF_INVOICES, STATUS FROM INVOICE GROUP BY STATUS";

    Db.Rows rows = ci.fetch(sql);

    for (Db.Row row : rows) {

        String invStatus = row.cell(1).toString();
        Long noOfInv = Long.valueOf(row.cell(0).toString()).longValue();

        // Record invoice count by status
        UserCode.statsCount("NoOfInvoices", invStatus, noOfInv);

        // Alternative syntax
        // CustomStats.count("NoOfInvoices", invStatus, noOfInv);

        // Measure duration of an operation
        AutoCloseable sw = statsDuration("InvoiceProcessing", "fetchTime");
        sleep(10);
        sw.close();

        // Alternative syntax
        // Stopwatch sw2 = CustomStats.duration("InvoiceProcessing", "fetchTime");
        // sleep(10);
        // sw2.close();
    }

    return status;
}
```



## How Custom Statistics Appear in the Prometheus Endpoint

Once registered, custom statistics appear in the `/metrics` endpoint output alongside standard Fabric and JVM metrics. They are visible to any collector or platform scraping that endpoint — Prometheus, Grafana Agent, Datadog, Dynatrace, or any other Prometheus-compatible tool.

For the `statsCount("NoOfInvoices", invStatus, noOfInv)` call in the example above, the output at `/metrics` will include lines similar to:

```
# HELP fabric_NoOfInvoices_count Count of NoOfInvoices events
# TYPE fabric_NoOfInvoices_count counter
fabric_NoOfInvoices_count{key="success"} 1482.0
fabric_NoOfInvoices_count{key="failure"} 37.0
fabric_NoOfInvoices_count{key="pending"} 94.0
```

Each unique `key` value becomes a separate labeled time series. This means that if `invStatus` takes many distinct values, the metric will expand into many series in Prometheus. Apply the same cardinality discipline to custom metrics as to built-in ones — use a bounded, predictable set of key values where possible.

For `statsDuration`, the output will include duration-related fields such as `last`, `average`, and `total` exposed as separate metric lines under a common name prefix.

To verify that your custom metrics are appearing, query the endpoint after the LU function has executed:

```bash
curl -s http://localhost:7170/metrics | grep NoOfInvoices
```



## Filtering Custom Metrics in the Collection Layer

Custom metrics are included in the full `/metrics` output by default. If you are applying metric family filters in Prometheus or Grafana Agent to control storage volume, ensure your filter rules include the custom metric names you want to retain.

For example, in a Prometheus `metric_relabel_configs` rule:

```yaml
metric_relabel_configs:
  - source_labels: [__name__]
    regex: 'fabric_.*|jvm_.*|tomcat_.*|NoOfInvoices.*'
    action: keep
```

Or in a Grafana Agent River pipeline:

```
rule {
  source_labels = ["__name__"]
  regex         = "fabric_.*|jvm_.*|tomcat_.*|NoOfInvoices.*"
  action        = "keep"
}
```

See [How to Control Metric Volume with Filtering and Relabeling](/articles/34_JMX_statistics/How_to_Control_Metric_Volume_with_Filtering_and_Relabeling.md) for the full filtering and relabeling guide.



## Further Reading

- [JMX Format](/articles/34_JMX_statistics/02_JMX_format.md) — the full description of what the `/metrics` endpoint exposes, including the Prometheus output format
- [How to Verify That Fabric Is Exposing Metrics](/articles/34_JMX_statistics/How_to_Verify_That_Fabric_Is_Exposing_Metrics.md) — how to confirm that custom metrics are appearing in the endpoint output
- [How to Control Metric Volume with Filtering and Relabeling](/articles/34_JMX_statistics/How_to_Control_Metric_Volume_with_Filtering_and_Relabeling.md)



