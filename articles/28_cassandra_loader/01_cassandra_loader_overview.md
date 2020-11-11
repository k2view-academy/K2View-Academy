# Cassandra Loader Overview

Fabric uses the [Cassandra DB](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md) as a storage layer and a Fabric application management database. Various Fabric processes, such as batch processes or iidFinder, need to be able to simultaneously insert massive amount of data into the Cassandra DB without getting the *Busy pool exception* yet consuming the hardware effectively. In addition, Fabric needs to be able to run internal processes, such as [Project Deployment](/articles/16_deploy_fabric/01_deploy_Fabric_project.md), without the dependency on other processes running in parallel.

**Cassandra Loader** is a Fabric robust mechanism that supports massive WRITE operations to Cassandra DB efficiently. By default, the loader / session ratio is 1:1. However the loader's default architecture can be modified by the configuration that does not require Fabric restart and allows updating the ratio of processes per loader and loaders per session. The best configuration for a project should be found by the "trial and error", given the project's hardware and the expected transactions volumes. 

The default loader is executed in an **asynchronous single** mode. The configuration enables overriding the default settings such as a mode (batch or single), queue size, number of threads, etc. 

The loader's configuration also enables the loader to work by predefined priority, providing more resources to the higher priority processes and by that enabling their processing before the others.

The loader exposes a simple API to be invoked by the user code in the Fabric's projects implementation which can be accessed from the [batch processes](/articles/20_jobs_and_batch_services/11_batch_process_overview.md), iidFinder and parsers.

<!-- what about WS?? -->

<!-- in case of WS - to which section will it write??-->

<!-- can we add a new section to config.ini??-->

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_loader_architecture.md)

