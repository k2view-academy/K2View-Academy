# E2E Catalog Creation Process

This article is a description of the E2E Catalog Creation process. The details for each step can be accessed via the links in the instructions below.

To create a Data Catalog from the Fabric project and to start using it, do the following:

![image](images/33_02_e2e.PNG)

1. Create a new project or use the existing one.
2. Create a [Data Catalog interface](02a_data_catalog_interface.md) named **catalogdb**. This is a one-time only activity. 
   * This interface is the default Data Catalog interface and must exist in the project. 
   * If needed, additional Data Catalog interfaces can be defined, for example for an OrientDB cluster.
3. (Optional) Use the [Build Catalog](03_build_and_write_catalog.md) option using the Fabric Studio to build the catalog files for all entities (each LU, WS, Common) in the project. 
   * This step is optional and is only performed when there is a requirement to override catalog files prior to uploading the catalog to the server.
4. (Optional) [Override the automatic files](06_override_data_catalog.md) to include the elements that the automatic process doesn't recognize them or they are not part of the project implementation.
5. [Install OrientDB](07_OrientDB_setup) and start it. This is a one-time only activity. 
6. Use the [Write Catalog](03_build_and_write_catalog.md) option in the Fabric Studio to deploy the Catalog to the server. This includes building the catalog files and uploading them to OrientDB. 
7. Login to the Data Catalog using the K2View Web Framework and [navigate the catalog](05_data_catalog_navigation.md).



[![Previous](/articles/images/Previous.png)](01_data_catalog_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02a_data_catalog_interface.md) 

