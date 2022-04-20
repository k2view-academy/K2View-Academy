# What is JMX?

Fabric arrives tightly pre-integrated with JMX - [Java Management eXtensions](http://www.oracle.com/technetwork/java/javase/tech/javamanagement-140525.html) - technology to enable comprehensive and low-resolution monitoring and management of applications. JMX uses objects called MBeans (Managed Beans) to expose data and resources from Fabric.

### Why would I want to enable JMX monitoring in Fabric?

When working with large clusters of Fabric nodes, enabling JMX allows you to more easily monitor the consumption of resources per services used. This enables you to make better decisions about how to maintain your implementation and optimize the machine resources allocated to your project.

Using the JMX Report page will also provide you with valuable information about the behavior of specific objects in your project, including LUIs, Web Services, Jobs and other functions that you might deem necessary to monitor.


### What can I monitor with JMX?

Various statistics using JMX counters in Fabric can be gathered. The following is a list of the main Statistics fields that can be monitored:

- Processes, provides information about the major services running in Fabric during execution. 
- Actions, like schema maintenances and Fabric commands.
- Transactions, Web Services statistics.
- Resources, like Cassandra, tasks and JDBC or mdb sessions.
- iidFinder, messages related to iidFinder queries and events. 
- Broadway flows, with performance metrics per Flow / Stage / Actor / Iteration.
- Custom, provides statistics to the MBeans you would have manually added to the Java code.

Note that all JMX metrics can be accessed using monitoring tools such as Grafana or Kibana.


[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/34_JMX_statistics/02_JMX_format.md)
