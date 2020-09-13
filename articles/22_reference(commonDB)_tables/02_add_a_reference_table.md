# New Reference Table in Fabric Studio

A  Reference table holds information common to all LU instances or to multiple LU. For obvious optimization reasons, this information is stored in an external table and not in each LUI MicroDB.



## How Do I Create a New Reference Table in Fabric?

1.  Go to **Project Tree** > **References** , right click **Create References Based On DB Tables** to display the DB Browser menu, Context menu and References popup window. 

2.  Click **DB Connection** (top of the window) and select the **Data Source Interface** to display the Tables directory in the source DB.

3. Select the **table**. To select multiple tables, press the Ctrl key.
  
![image](/articles/22_reference(commonDB)_tables/images/01_create_new_common_tables.PNG)



4. Optional: 
- Add a prefix to the Reference table's name in the **File Name Prefix** field (window's footer). 
- If there are objects in the project with the same name, add a prefix to differentiate between Reference tables and LU tables with similar names.
- We recommend using a prefix that indicates the project name as a Reference table. For example <proj-prefix>_REF.


6. Click **Create Tables** to add the new Reference table under References in the Project Tree. 
If the table does not appear immediately, click the Refresh icon: ![image](/articles/22_reference(commonDB)_tables/images/03_create_new_common_tables_refreshbutton.PNG) at the top of the Project Tree.

![image](/articles/22_reference(commonDB)_tables/images/02_create_new_common_tables_schema.PNG)



## Editing and Viewing Reference Tables

Reference tables can be edited by either changing the default data mapping, adding transformations or adding or removing columns like in [LU tables](/articles/07_table_population/01_table_population_overview.md).

### Viewing Table Data 

To access the Reference Viewer do the following:
1.  Go to **Project Tree** > **References**, right click **References Viewer**, and then select the **table**. The Data Viewer window is displayed according to its hierarchy in the Instances Tree pane.

2.  Click the **data file** to display the components hierarchy in the Instance DB Tree pane.

![image](/articles/22_reference(commonDB)_tables/images/05_create_new_common_tables_dataviewer.PNG)

2.  Click the **table name** to display the table's data in the main Data Viewer window. 

![image](/articles/22_reference(commonDB)_tables/images/04_create_new_common_tables_dataviewer.PNG)



### Reference Tables Properties

Additional properties can be defined in the **Table Properties** panel in the right pane of the selected Table tab.



#### Main Properties

- Name, can be defined or modified.
- Primary Key column, ensures records uniqueness.
- Column collation type:
  - BINARY, compares string data regardless of text encoding.
  - NOCASE, folds upper case characters to their lower case equivalents.
  - RTRIM, ignores trailing space characters.



#### Sync Method

By default Reference tables are synched in the background of each table according to the defined Sync policy. The following Sync options can be selected in the Table Properties panel:

- None, default value, synchronization is according to the Sync policy defined.

- Time Interval, set in ```days.hrs:min:sec``` format.

- Decision function, syncs the table according to the [Decision function](/articles/14_sync_LU_instance/05_sync_decision_functions.md) defined under the **References**  > **Java** folder. 

  

#### Miscellaneous

Functions or other tables can be attached to the Reference table:

- [Enrichment Functions](/articles/10_enrichment_function/01_enrichment_function_overview.md), to perform data manipulations on the table's content.
- Reference Tables, to add other Reference tables as dependencies. 

![image](/articles/22_reference(commonDB)_tables/images/06_create_new_common_tables_properties.PNG)



## Attach the Reference Table to an LU Schema

Before accessing the Reference Table from a specific LU, it must be attached to the LU.

### Configure LU to Use a Reference Table

1. Open the **LU Schema Window** as described above?? 

2. In the right panel, select the [References](/articles/03_logical_units/15_LU_schema_edit_reference_tab.md) tab (right panel).

3. Check the **Reference tables**.

![image](/articles/22_reference(commonDB)_tables/images/07_create_new_common_tables_LU_Ref.PNG)

4. Click **Save** to save the association. 

Note: Reference tables can also be accessed via [Lookup tables](/articles/07_table_population/11_lookup_tables.md), [Web Services](/articles/15_web_services_and_graphit/01_web_services_overview.md) or [functions](/articles/10_enrichment_function/01_enrichment_function_overview.md).



## Deploy the Reference Tables

Reference Tables must be deployed before being used.

Go to the **Project Tree**, right click **References**, select **Deploy to Server** and then the **Server** to deploy to the Reference table.  
  

[<img align="left" width="60" height="54" src="/articles/images/Previous.png">](/articles/22_reference%28commonDB%29_tables/01_fabric_commonDB_overview.md)

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/22_reference%28commonDB%29_tables/03_fabric_commonDB_flow.md)

