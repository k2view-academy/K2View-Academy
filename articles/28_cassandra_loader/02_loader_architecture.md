# Cassandra Loader Architecture

### Default Architecture

<img src="images/28_01_1.PNG" alt="default" style="zoom:67%;" />

### Custom Architecture

<img src="images/28_01_2.PNG" alt="default" style="zoom:67%;" />

The default architecture defines two session objects: one is used by the loaders to perform WRITE operations to the Cassandra DB and another one is used by the Fabric internal processes like Deploy. The internal processes run on a separate session to prevent the dependency on other heavy processes.

This architecture can be changed by the configuration to reach the optimal efficiency using the *config.ini* file. The optimal configuration is based on the combination of the effective project's hardware consumption and the best possible performance. 

The configuration parameters override the default settings, thus several loaders can be defined via the configuration, each one with a separate session. For example, one loader per each parser or separate loaders for parsers and for iidFinder. 

The concurrent number of messages to be sent to Cassandra by each session object is set to a default value of 1K. The loader has a built-in queue management mechanism which puts a message in a queue in case the Cassandra reached full capacity. The concurrency limit can be fine-tuned by the configuration optimization process to a lower or higher number, while checking whether more load can be moved to Cassandra without causing the system to slow down or get stuck.  



[![Previous](/articles/images/Previous.png)](01_cassandra_loader_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_loader_configuration.md) 

