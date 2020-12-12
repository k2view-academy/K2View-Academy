## What is JMX?

Fabric come tightly pre-integrated with JMX ([Java Management eXtensions](http://www.oracle.com/technetwork/java/javase/tech/javamanagement-140525.html)) technology to enable comprehensive and low-resolution monitoring and managing of  applications. JMX uses objects called MBeans (Managed Beans) to expose data and resources from Fabric.

### Why would I want to enable JMX monitoring within Fabric?

For large clusters of Fabric Nodes, enabling JMX allows you to more easily monitor the consumption of resources per services used. 

This enables you to make better decisions about how to maintain your implementation and optimize the machine resources allocated to your project.

### What can I monitor with JMX?

Various statistics using JMX counters within Fabric can be gathered. Below are some examples of some statistics that can be monitored:

- Processes - provides information about the major services at play across Fabric during execution.
- Actions - such as schema maintenances and fabric commands

- Transactions - web-services statistics
- Resources - such as cassandra, tasks, jdbc sessions, mdb sessions etc ...
- Custom - Provides statistics to beans you would have manually added to your java code

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/34_JMX_statistics/02_JMX_infoformat.md)
