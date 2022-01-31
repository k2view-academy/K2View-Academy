# Creating a New Table Population

A **Table Population** can be created by:

<studio>

* Dragging the table from the **LU Schema DB Objects tab** into the [LU schema](/articles/03_logical_units/03_LU_schema_window.md) working area to create the [LU table](/articles/06_LU_tables/01_LU_tables_overview.md) including its Table Population object.

* Creating an **LU table** using the **New Table from SQL Based DB Query** or **New Table from SQL Based Root Function** options  in the LU Schema window to create the LU table including its Table Population object.

* Using the [**Auto Discovery Wizard**](/articles/03_logical_units/06_auto_discovery_wizard.md) to  automatically create the LU table and a DB query population for each LU table.

  </studio>

* By right clicking the [**Table Name**] under the **Project Tree** and then clicking **New Population**. 

<studio>

LU tables can be populated by either a DB query or a Root function source object. To decide which source object to use, refer to the comparison analysis of [Table Population Source Object Types](/articles/07_table_population/02_source_object_types.md).

</studio>

A source object can return one or many records whereby each output record is mapped and populated into a target LU table. 
Note that a Table Population can extract data from other [LU tables](/articles/06_LU_tables/01_LU_tables_overview.md) in the same Logical Unit. It is recommended to always check the [execution order](/articles/07_table_population/13_LU_table_population_execution_order.md) of a source table’s population objects to verify that the source LU tables are populated **before** the target LU table. For example, the SUBSCRIBER LU table must be populated before the OFFER LU table to enable populating the OFFER LU table based on data from the SUBSCRIBER LU table.

When an LU table has two populations, a second Table Population can extract data from the same LU table. For example, the ADDRESS LU table has two Table Populations:
*	Population 1 extracts data from the CRM DB.
*	Population 2 extracts data from the ADDRESS LU table that has been inserted by Population 1.

[Click for more information about Creating Table Population Objects.](/articles/07_table_population/03_creating_a_new_table_population.md)

<studio>

### How Do I Create a New Table Population From a DB Query? 

1.	Go to **Project Tree > Logical Units** > [**LU Name**].
2.	Click **Tables**, right click [**Table Name**] > **New Table Population** to open the working area for creating the mapping.
3.	Click the **Objects tab** in the right panel of the **Table Population** working area.
4.	Click **Databases** to display the list of interfaces defined in the project or a built-in Fabric interface. 
5.	Double-click on the required table name or drag it into the working area. 
6.	Click **Create as DB Query** to create the DB query source object in the working area. 
7.	Optional: use the **Edit Query** option to edit the input query via the [Query Builder](/articles/11_query_builder/01_query_builder_overview.md#query-builder-overview), for example, to choose specific columns or conditions in SQL statements. Do either:
    * Click the **source object**, then go to the **Properties tab** > **Edit Query** in the right panel of the working area.
    * Double click the **source object**.
8.	To connect the source object to the target LU table, right click the **DB Query object** and either:
    * Click **Auto Connect to Table**.
    * Connect the fields **manually**.

### How Do I Create a New Table Population From a Root Function? 

1.	Go to **Project Tree > Logical Units** > [**LU Name**].
2.	Click **Tables**, right click [**Table Name**] > **New Table Population** to open the working area for creating the mapping.
3.	Click the **Objects** tab in the right panel of the **Table Population** working area.
4.	Click **Root Functions** and do either:
    * Select an existing **Root function** and drag it into the working area. 
    * Click **Create New Root Function**.
5.	Right click the **source object** and do either:
    * Check **Auto Connect to Table**.
    * Connect the fields **manually**.

### How Do I Create a Population Based on a Broadway Flow?

The starting points for creating a population based on a Broadway flow are:

* [Auto Discovery Wizard](/articles/03_logical_units/06_auto_discovery_wizard.md), check the **Table population based Broadway flow** checkbox in step 2 of the Wizard.
* [LU Schema window](/articles/03_logical_units/03_LU_schema_window.md#logical-unit-lu-schema), either:
  * Right click and select **New table from SQL based Broadway flow**.
  * Drag a DB table and select **Create table based Broadway flow**.
* Project Tree, right click a table object and select **Create Table Population based Broadway Flow**.
* Reference, right click and select **Create References from DB tables**.

The population is created as a template with predefined Stages and designated Actors. When creating a flow from the Auto Discovery Wizard or from the LU Schema, the input fields, interface, and SQL statement are added automatically based on the selected table's fields. Complete the missing information and if needed, update the flow and then connect the table population to the LU hierarchy via the LU Schema window.

Note that for the population to be effective on the server side, LU deployment is required. When running in debug mode, the deployment to debug is performed automatically.

[Click for more information about deployment from the Fabric Studio](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md).



[![Previous](/articles/images/Previous.png)](/articles/07_table_population/02_source_object_types.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](14_table_population_based_Broadway.md)

</studio>

<web>

### How Do I Create a Population Flow?

1.	Go to **Project Tree > Logical Units** > [**LU Name**].
2.	Click **Tables**, right click [**Table Name**] > **New Population** to open the [population template](14_table_population_based_Broadway.md#flow-population-template) with predefined Stages and designated Actors.
3.	Complete the missing information such as input arguments, DB interface, and save the flow.
4.	In the LU Schema window connect the table population to the LU hierarchy.

Note that for the population to be effective on the server side, LU deployment is required. When running in debug mode, the deployment to debug is performed automatically.

[Click for more information about deployment from the Fabric Studio](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md).



[![Previous](/articles/images/Previous.png)](01_table_population_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](14_table_population_based_Broadway.md)

</web>
