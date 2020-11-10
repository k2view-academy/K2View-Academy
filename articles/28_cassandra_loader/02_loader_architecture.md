# Cassandra Loader Architecture

**Default Architecture**

<img src="images/28_01_1.PNG" alt="default" style="zoom:67%;" />

**Custom Architecture**

<img src="images/28_01_2.PNG" alt="default" style="zoom:67%;" />

The default loader configuration makes a distinction between the Fabric processes that need to perform WRITE operations to the Cassandra DB and the Fabric internal processes like Deploy. 

By default, all the processes that need to write into the Cassandra run via one default loader and one session with maximum up to 1K concurrent requests. The internal processes run on a separate session, to prevent the dependency on other heavy processes.

The loader can be configured to the optimal efficiency using the configuration parameters in *config.ini*. The optimal configuration is based on the combination of the effective project's hardware consumption, the best possible performance and the least number of *Busy pool exceptions*. 

The configuration parameters override the default settings, thus via the configuration it is possible to define several loaders, each one with separate session. For example, one loader per each parser or separate loaders for parsers and for iidFinder. 

The default loader's [execution mode]() is BATCH with predefined batch size. The transaction management is performed by the loader's exposed API, thus when the processes needs to write 1,000,000 records into the Cassandra DB, the loader splits the data into batches and commits every statement. 



[![Previous](/articles/images/Previous.png)](01_cassandra_loader_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_loader_configuration.md) 

