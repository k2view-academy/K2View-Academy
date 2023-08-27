# Cassandra Loader Overview

The **Cassandra Loader** is a robust Fabric mechanism that can be used for massive WRITE operations to the Cassandra DB efficiently, when Cassandra is used as [Fabric's System Databse](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md). 

By default, the loader / session ratio is 1:1. The loader's default architecture can be modified whereby the configuration change does not require Fabric restart and enables updating the ratio of processes per loader and loaders per session. The best configuration for a project should be found using a  trial and error fine-tuning process based on the project's hardware and the expected transaction volumes. 

The default loader is executed in an **asynchronous single** mode. The configuration enables overriding the default settings such as a mode (batch or single), queue size, number of threads, etc. 

The loader's configuration also enables it to work according to a predefined priority and to provide more resources to higher priority processes so that they are processed first.

The loader can be invoked by the user code in the Fabric project's implementation from batch processes, the iidFinder, Web Services, etc.



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_loader_architecture.md)

