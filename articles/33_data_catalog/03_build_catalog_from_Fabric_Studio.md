# Build Catalog from Fabric Studio

The Data Catalog implementation must be deployed to the server side so that all changes are compiled and are ready to be used. 

* **Build Catalog** option in the Fabric Studio builds the catalog files for all the project entities (each LU, WS, Common). The files are created in JSON format in the Resources folder of the project entities  - one file per each project entity. All the files are called **auto.catalog**, except for the file created under Shared Objects that is called **shared.catalog**. The catalog files can be edited manually to include the elements that don't exist in the project implementation or to modify the existing elements.

  [Click for more information about how to override the Data Catalog tree](05_override_data_catalog_tree.md).

* **Write Catalog** option deploys the Catalog to the server and it includes building the catalog and updating the catalog files prior to the deployment. Write Catalog can be performed in the Fabric Studio or by running the [CATALOG WRITE](04_catalog_command.md) command on the Fabric Server.

### How Do I Build the Catalog?

1. In the Fabric Studio right click the **Project** and select **Data Catalog > Build Catalog**. 

   ![image](images/33_05_build.png)

2. Build is executed and the results are displayed in the popup screen indicating the status for each project entity (LU, WS, Common). 

   * In case of issues, the error will be displayed in the popup screen.

3. Open the created catalog files. For example, to open the catalog file of the WS:

   * Go to **Web Services > Resources** and double click on **auto.catalog**.

### How Do I Write the Catalog?

1. Do either:

   * Select **Data Catalog > Write Catalog To debug** or **Write Catalog To Server**. 
   * Run the [CATALOG WRITE](04_catalog_command.md) command on the Fabric Server.
2. Write is executed and the results are displayed in the popup screen indicating the status for each project entity (LU, WS, Common). 
   * In case of issues, the error will be displayed in the popup screen.

Note that you must perform **Write Catalog** in order to apply your catalog changes to the server.



[![Previous](/articles/images/Previous.png)](02_data_catalog_user_interface.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_catalog_command.md) 