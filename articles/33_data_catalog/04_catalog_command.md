# Catalog Command

The **CATALOG** command builds a catalog file that represents the project entities and its relations. The file can then be deployed to the relevant environment. 

Use the following syntax:

~~~
CATALOG <OPERATION> <KEY=VALUE ARGS>
~~~

The following operations are supported:

* CATALOG BUILD, builds a catalog files for all the entities of the project. The files will be created in the resources folder of each entity (LU, WS, Common). The command can be executed only from the Fabric Studio.

* CATALOG WRITE, writes all the catalog files (that were created by the build command) of the project using the provided interface or using the default interface (if the interface is not provided). 

  * Use the following syntax:

    ~~~
    CATALOG WRITE [INTERFACE=<name>] [LU=<name>];
    ~~~

  * INTERFACE (optional), the Data Catalog interface to use. If not provided, the **catalogdb** interface is used.

* CATALOG DROP, drops the provided project using the provided interface.

  * Use the following syntax:

    ~~~
    CATALOG DROP [PROJECT=<name>] [INTERFACE=<name>];
    ~~~

  * PROJECT (optional), the project to drop. If not provided, the deployed project is chosen.

  * INTERFACE (optional), the Data Catalog interface to use. If not provided, the **catalogdb** interface is used.

