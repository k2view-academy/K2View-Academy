# Creating a New Reference Table in Fabric

A  reference table is a table that holds information common to all LU instances or even common to multiple Logical Units. For optimization reasons, it makes sense to store this information in an external table and not within each LUI micro-database.



## How Can I Create a New Reference Table in Fabric?

- Go to **Project Tree** > **References** , right click **Create References Based On DB Tables** to display the DB browser menu .
  - *The Context menu is displayed*
  - **The References popup window is displayed.*

  

- Select the Data Source Interface from the DB Connection drop-down menu (at the top of the window).

  - *The Directory of tables in the source DB is displayed in the window*

  ![image](/articles/22_reference(commonDB)_tables/images/01_create_new_common_tables.PNG)

  

- Select a table to add as a Reference Table.
  
  - Note: Multiple tables may be selected by pressing the Ctrl key.

![image](/articles/22_reference(commonDB)_tables/images/02_create_new_common_tables_schema.PNG)



- Optional: If required, enter the prefix for the Reference table name in the File name prefix field (in the footer of the window).
  - If there is more than one object in the project with the same name, you can set a prefix to be added to differentiate between Reference tables and LU tables that have similar names. 

  - We recommend to use a prefix that indicates the project name and that this is a Reference table. For example <proj-prefix>_REF.

    

- Click the Create Tables button.

  - The New Reference table appears in Project Tree under References. 
  - Note: If a new table does not appear immediately, click Refresh icon ![image](/articles/22_reference(commonDB)_tables/images/03_create_new_common_tables_refreshbutton.PNG) at the top of the Project Tree and the new table appear.

  

## Editing and Viewing Reference Tables

Reference tables may be edited in many ways. You can change the default data mapping, add transformations, and/or add or remove columns in the same manner as LU tables as explained [here](/articles/07_table_population/01_table_population_overview.md).

### Viewing the table data 

- The Reference Viewer can be accessed  by accessing  **Project Tree** > **References** , right click **References Viewer**, and then select the appropriate table:

*The Data viewer window is displayed with data file hierarchy in the Instances Tree pane.*

- Click the data file to display.
  *The Hierarchy of components is displayed in the Instance DB Tree pane.*

  ![image](/articles/22_reference(commonDB)_tables/images/05_create_new_common_tables_dataviewer.PNG)

- Click the table name.
  *The Table data is displayed in the main Data Viewer window.*

   ![image](/articles/22_reference(commonDB)_tables/images/04_create_new_common_tables_dataviewer.PNG)





### Reference Tables Properties

Additional properties can be defined from the table properties panel located in the right pane of the selected table tab.



#### Main properties

- Name - can be defined or modified
- Primary Key Column - to ensure records uniqueness
- Column collation type
  - BINARY - Compares string data regardless of text encoding.
  - NOCASE -  folds upper cases characters to their lower case equivalents.
  - RTRIM - ignores trailing space characters.



#### Sync Method

Reference tables sync is done in the background by default for each table according to sync policy defined. Yet different options can be selected from the table properties panel:

- None - default value, synchronization happens according to sync policy defined.

- Time Interval - can be set using the following format ```days.hrs:min:sec```

- Decision function - will sync the table according to a [decision function](/articles/14_sync_LU_instance/05_sync_decision_functions.md) that can be defined under the **References** -> **Java** folder. 

  

#### Miscellaneous

Functions or other tables can be attached to the reference table:

- [Enrichment Functions](/articles/10_enrichment_function/01_enrichment_function_overview.md) - to perform data manipulations on the table's content
- Reference Tables - to add other reference tables as dependencies 

![image](/articles/22_reference(commonDB)_tables/images/06_create_new_common_tables_properties.PNG)



## Attach the Reference Table to an LU Schema

Before accessing the Reference Table from a specific LU, it must be attached to the LU.

### Configure LU to use a Reference Table

- Open the LU Schema Window as described 

- In the right panel, select the [References](/articles/03_logical_units/15_LU_schema_edit_reference_tab.md) tab in the right panel .

- Select boxes for relevant reference tables.

  ![image](/articles/22_reference(commonDB)_tables/images/07_create_new_common_tables_LU_Ref)

- Click the Save icon or press CTRL+S to save the association. 

Note: Reference tables can also be accessed via [lookup tables](/articles/07_table_population/11_lookup_tables.md), [web-services](/articles/15_web_services_and_graphit/01_web_services_overview.md) or [functions](/articles/10_enrichment_function/01_enrichment_function_overview.md).



## Deploying Reference Tables

Reference Tables must be deployed before being used.

- Right-click the References item in the Project Tree pane.
- Select the Deploy to Server option.
  *The menu of configured server scripts is displayed.*
- Select the Server on which to deploy the Reference Table.
  



<img align="right" width="60" height="54" src="/articles/images/Previous.png">](/articles/22_reference(commonDB)__tables/01_fabric_commonDB_overview)_

<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/22_reference(commonDB)_tables/03_commonDB.md) 