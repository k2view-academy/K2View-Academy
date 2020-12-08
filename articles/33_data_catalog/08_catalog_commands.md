# The Catalog Commands

The **CATALOG** commands are used to build catalog files that represents the project entities and to deploy them to the environment or to drop the catalog deployed to the server. 

The following operations are supported:

* **CATALOG WRITE**, writes the catalog files (that were created by the Build Catalog option from the Fabric Studio) using the provided interface or using the default interface (if the interface is not provided).  Note that if the files don’t exist (the Catalog Build didn’t run yet) , the command will fail.   

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
  
  

[![Previous](/articles/images/Previous.png)](07_OrientDB_setup.md)
