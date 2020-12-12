## JMX Fabric Custom Statistics

Fabric provides developers 2 methods to add JMX probes to their implementation Java code and to expose their values as JMX counters.  

### Fabric Statistics APIs

#### statsCount

This method can be invoked to count statistics such as events, bytes, counts etc. 

```java
public static void statsCount(String entry, String key, long value)
```

```entry``` - designates the primary key for this statistic implementation

`key` - designates the sub key for this statistics

`value` - the measure of the value for this statistic.

The statistic method will use this value to calculate total, last and average. values for this measure. In addition this function also counts the number of times the function has been called and will provide a timestamp for the last call.

#### statsDuration

```java
public static AutoCloseable statsDuration(String entry, String key)
```

The purpose of this method is to measure the duration of the function's call between the method invocation and the invocation of the ```.close()``` function on the return object.

`entry` -  designates the primary key for this statistic

`key` - designates the sub key for this statistics

This method is returned by calling ```.close()``` on this object to indicate the end of the duration measurement