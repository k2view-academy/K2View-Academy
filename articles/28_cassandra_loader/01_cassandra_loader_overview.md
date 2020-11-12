# Cassandra Loader Overview

Fabric uses the [Cassandra DB](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md) as a storage layer and as a Fabric application management database. Various Fabric processes, like batch or iidFinder, must be able to simultaneously insert massive amounts of data into the Cassandra DB without getting a *Busy pool exception* and to consume the hardware effectively. In addition, Fabric must be able to run internal processes, like [Project Deployment](/articles/16_deploy_fabric/01_deploy_Fabric_project.md), without depending on other processes running in parallel.

The **Cassandra Loader** is a robust Fabric mechanism that supports massive WRITE operations to the Cassandra DB efficiently. By default, the loader / session ratio is 1:1. The loader's default architecture can be modified whereby the configuration does not require Fabric to restart and enables updating the ratio of processes per loader and loaders per session. The best configuration for a project should be found using a  trial and error fine-tuning process based on the project's hardware and the expected transactions volumes. 

The default loader is executed in an **asynchronous single** mode. The configuration enables overriding the default settings such as a mode (batch or single), queue size, number of threads, etc. 

The loader's configuration also enables the loader to work according to a predefined priority and to provide more resources to higher priority processes and by doing so, enabling their processing before the others.

The loader can be invoked by the user code in the Fabric's projects implementation from the batch processes, iidFinder, Web Services, etc.



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_loader_architecture.md)

