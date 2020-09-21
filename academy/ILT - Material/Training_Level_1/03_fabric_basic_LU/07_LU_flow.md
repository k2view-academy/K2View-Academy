#   Logical Unit Flow

 ![](/academy/Training_Level_1/03_fabric_basic_LU/images/fabric_main_flow_05.png)                                                    


NTT -> Insist on following points:

- Understanding how to model data retrieval, storage and its view options.
- Defining the Digital Entity based on data consumption requirements.
- Modeling the LU based on the defined Digital Entity. 

 

### LU and Digital Entity Considerations

NTT -> Present [LU Overview](/articles/03_logical_units/01_LU_overview.md)
NTT -> Present [Create LU Overview](/articles/03_logical_units/02_create_a_logical_unit_flow.md) articles. 

Remind that the main business requirement is to create a 360 Customer view with the following key data information:

- How many lines do I subscribe to? 
- What is the current balance on a specific line? Or on all the lines? 
- When is my next invoice due? 
- How much do I owe? 
- Are my private details up-to-date? 

Based on the above, the Digital Entity should be a **Customer** and the LU should be defined using various DBs. 

Let’s start from the **CRM_DB** interface which holds the Customer entity and create the LU using Auto Discovery.

### Creating a New LU 

NTT -> Present: [Create New Logical Unit](/articles/03_logical_units/05_create_a_new_LU_object.md) article. 

NTT -> Reminder - the Digital Entity should be the Customer ID that uses the CRM_DB.Customer table as the root of the LU’s modelling. 

Present the [Auto Discovery Wizard](/articles/03_logical_units/06_auto_discovery_wizard.md) to create the LU.


![](/academy/Training_Level_1/03_fabric_basic_LU/images/example.png)

### Example – Auto Discovering an LU

Following the Auto Discovery Tutorial, you can generate the **Customer** using the Fabric Studio Wizard. 

Please open your Fabric project and do the following:

1. Select the **New Logical Unit** and name it **Customer**.
2. Set the **DB Connection** as **CRM_DB** and click **Next**.
3. Select the **CUSTOMER.CUSTOMER_ID** as a **Column Name** so that the **Table Name** is also populated. Note that you can uncheck the **Add Schema Name** option.
4. Select **Fast** **Auto Discovery**.
5. Click **Next** to review the suggested **ERD** and if you are satisfied, click **Finish**.

Note that the **Root Table** and **InstanceID** are created automatically.

Your **Customer** LU is now defined:

![](/academy/Training_Level_1/03_fabric_basic_LU/images/CustomerLU.png) 

`Question: Are all the tables in the CRM_DB Schema part of the Customer? Why?`



NTT -> Show the following articles:

-  [Logical Unit Schema Window](/articles/03_logical_units/03_LU_schema_window.md)

-  [LU Properties](/articles/03_logical_units/04_LU_properties.md)

   

![](/academy/03_fabric_basic_LU/images/information.png)

NTT -> Optional: There are other options for using the Auto Discovery Wizard, like overriding or enhancing a Logical Unit.
- Use [Auto Discovery Build or Update LU](/articles/03_logical_units/07_build__or_update_an_LU_schema.md).

NTT -> Important reminder - when not using the Auto Discovery Wizard, make sure that your Root Table and Instance ID are defined. 
Read [Set Root Table and Instance ID](/articles/03_logical_units/08_define_root_table_and_instance_ID_LU_schema.md).


### Editing an LU

You may have noticed that although the Auto Discovery Wizard is quick, it doesn’t necessarily construct LU that have all the required source Schema tables for the implementation. Source tables can be added to an implementation as part of the LU Schema or can be created manually as part of the implementation. 

Display: 

- [LU Table Overview](/articles/06_LU_tables/01_LU_tables_overview.md) article.

- [Create New LU Table](/articles/06_LU_tables/02_create_an_LU_table.md)

Remove: - [Table Indexes](/articles/06_LU_tables/03_table_indexes.md)

Remove: - [Table Properties](/articles/06_LU_tables/04_table_properties.md)

- [Add a Table to a Schema](/articles/03_logical_units/09_add_table_to_a_schema.md).

- [Delete Table from LU schema](/articles/03_logical_units/10_delete_table_from_a_schema.md).


### How Do I View and Review an LU?

NTT -> 3 validations points:

Validate if LU fits the basic requirements. 
Validate if the schema model retrieves the right data.
Validate the implementation and view at least one LUI’s data?

Use the [Data Viewer Capabilities](/articles/13_LUDB_viewer_and_studio_debug_capabilities/01_data_viewer.md)



 

------


