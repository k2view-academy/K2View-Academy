# The Catalog Commands

The **CATALOG** commands are used to build catalog files that represents the project entities and to deploy them to the environment or to drop the catalog deployed to the server. 

The following operations are supported:

* **CATALOG WRITE**, deploys the Catalog of the project to the server, creates the catalog files and uploads them to OrientDB using the provided interface or using the default interface (if the interface is not provided). 

  * Use the following syntax:

    ~~~
    CATALOG WRITE [INTERFACE=<name>];
    ~~~

  * INTERFACE (optional), the Data Catalog interface to use. If not provided, the **catalogdb** interface is used.

* **CATALOG DROP**, drops the provided project using the provided interface.

  * Use the following syntax:

    ~~~
    CATALOG DROP [PROJECT=<name>] [INTERFACE=<name>];
    ~~~

  * PROJECT (optional), the project to drop. If not provided, the deployed project is chosen.

  * INTERFACE (optional), the Data Catalog interface to use. If not provided, the **catalogdb** interface is used.
  
  

[![Previous](/articles/images/Previous.png)](03_dc_configuration.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_build_catalog_from_Fabric_Studio.md) 
