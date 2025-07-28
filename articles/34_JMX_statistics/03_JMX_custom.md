# JMX Fabric Custom Statistics

### Extending Monitoring with Custom JMX Statistics
K2view Fabric allows customers and developers to enhance observability by implementing custom JMX monitors tailored to their operational needs. By using the provided statistics APIs, teams can define and expose business-specific or performance-related metrics directly into the JMX subsystem. These custom statistics are then available for external monitoring tools (e.g., Grafana, Prometheus) to ingest and visualize alongside system-level metrics.

This capability is particularly useful for surfacing application-specific counters—such as the number of processed records by status, the frequency of specific business events, or the duration of key operations. Developers can integrate these probes into their logical unit (LU) code using the `statsCount` and `statsDuration` methods provided by the Fabric API.

### Fabric Statistics APIs

#### statsCount

This method can be invoked to count statistics, such as events, bytes, and counts. 

```java
public static void statsCount(String entry, String key, long value)
```

```entry``` - designates the primary key for this statistic implementation

`key` - designates the sub key for these statistics

`value` - the measure of the value for this statistic.

The statistical method will use this value to calculate the total, last, and average values for this measure. Additionally, this function also tracks the number of times it has been called and provides a timestamp for the most recent call.

#### statsDuration

```java
public static AutoCloseable statsDuration(String entry, String key)
```

The purpose of this method is to measure the duration of the function's call between the method invocation and the invocation of the ```.close()``` function on the return object.

`entry` -  designates the primary key for this statistic

`key` - designates the sub key for these statistics

This method is returned by calling ```.close()``` on this object to indicate the end of the duration measurement.


### Code Example:

~~~java
  public static String fnCustomStats() throws Exception { 
  	
	String status= "success";

	Db ci = db("fabric");
	
	String sql = "SELECT COUNT(*) NO_OF_INVOICES, STATUS FROM INVOICE group by status";
	
	Db.Rows rows = ci.fetch(sql);
	
	for (Db.Row row:rows){
	
		String invStatus = row.cell(1).toString();
		Long noOfInv=  Long.valueOf(row.cell(0).toString()).longValue();
	
	
		UserCode.statsCount("NoOfInvoices", invStatus, noOfInv);
	
		//Alternative syntax
		//CustomStats.count("NoOfInvoices", invStatus, noOfInv);
	
		AutoCloseable sw = statsDuration("tali", "test");
		sleep(10);
		sw.close();
	
                     //Alternative syntax
		//Stopwatch sw2 = CustomStats.duration("tali", "test");
		//sleep(10);
		//sw2.close();
	}
	
	return status;
}
~~~




[![Previous](/articles/images/Previous.png)](/articles/34_JMX_statistics/02_JMX_format.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/34_JMX_statistics/04_monitoring_dashboard_example.md)
