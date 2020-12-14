# Catalog Commands

**CATALOG** commands are used to build the catalog files that represent the project entities and to deploy them to the environment or to drop the catalog deployed to the server. 

The following operations are supported:

* **CATALOG WRITE**, writes the catalog files (created by the Build Catalog option using the Fabric Studio) using the provided interface or using the default interface (if the interface is not provided).  Note that if the files don’t exist (the Catalog Build has not yet run), the command will fail.   

  * Use the following syntax:

    ~~~
    CATALOG WRITE [INTERFACE=<name>];
    ~~~

  * INTERFACE (optional), the Data Catalog interface used. If not provided, the **catalogdb** interface is used.

  * Prior to running the command, execute the [Catalog Build](/articles/33_data_catalog/03_build_and_write_catalog.md) using the Fabric Studio and then perform the [Offline Deploy](/articles/16_deploy_fabric/03_offline_deploy.md) of the project's components.

* **CATALOG DROP**, drops the provided project using the provided interface.

  * Use the following syntax:

    ~~~
    CATALOG DROP [PROJECT=<name>] [INTERFACE=<name>];
    ~~~

  * PROJECT (optional), the project to drop. If not provided, the deployed project is chosen.

  * INTERFACE (optional), the Data Catalog interface to use. If not provided, the **catalogdb** interface is used.
  
  

[![Previous](/articles/images/Previous.png)](07_OrientDB_setup.md)
