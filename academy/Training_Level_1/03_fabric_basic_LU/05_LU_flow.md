#   Logical Unit Flow

 ![](/academy/Training_Level_1/03_fabric_basic_LU/images/fabric_main_flow_05.png)                                                    

You have just set up your Fabric project, configured its components and defined the interfaces for the Customer 360 view. Based on the business requirements, let’s look at your next steps:

You need to understand how to model data retrieval, storage and its view options.

What should be considered?

- Defining the Digital Entity based on data consumption requirements.
- Modeling the LU based on the defined Digital Entity. 

 

### LU and Digital Entity Considerations

Please read the [LU Overview](/articles/03_logical_units/01_LU_overview.md) and [Create LU Overview](/articles/03_logical_units/02_create_a_logical_unit_flow.md) articles. 

Let’s look at the considerations needed to accommodate requests to view the data using a specific Digital Entity together with LU modelling.  

You may  remember that the main business requirement is to create a 360 Customer view with the following key data information:

- How many lines do I subscribe to? 
- What is the current balance on a specific line? Or on all the lines? 
- When is my next invoice due? 
- How much do I owe? 
- Are my private details up-to-date? 

Based on the above, the Digital Entity should be a **Customer** and the LU should be defined using various DBs. 

Let’s start from the **CRM_DB.CUSTOMER** database which holds the Customer entity and create the LU using Auto Discovery.

### Creating a New LU 
Please read the [Create New Logical Unit](/articles/03_logical_units/05_create_a_new_LU_object.md) article. 

We have already acknowledged that the Digital Entity should be the Customer ID that uses the CRM_DB.Customer table as the root of the LU’s modelling. You can use the [Auto Discovery Wizard](/articles/03_logical_units/06_auto_discovery_wizard.md) to create the LU.


### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/example.png)Example – Auto Discovering an LU

Following the Auto Discovery Tutorial, you can generate the **CustomerLU** using the Fabric Studio Wizard. 

Please open your Fabric project and do the following:

1. Select the **New Logical Unit** and name it **CustomerLU**.
2. Set the **DB Connection** as **CRM_DB** and click **Next**.
3. Select the **CUSTOMER_ID.CUSTOMER** as a **Column Name** so that the **Table Name** is also populated. Note that you can uncheck the **Add Schema Name** option.
4. Select the **Fast** **Auto Discovery**.
5. Click **Next** to review the suggested **ERD** and if you are satisfied, click **Finish**.

Note that the **Root Table** and **InstanceID** are created automatically.

Yay! your **CustomerLU** is now defined:

![](/academy/Training_Level_1/03_fabric_basic_LU/images/CustomerLU.png) 

`Question: Are all the tables in the CRM_DB Schema part of the CustomerLU? Why?`



Let’s review some Logical Unit Schema properties:

-  [Logical Unit Schema Window](/articles/03_logical_units/03_LU_schema_window.md)

-  [LU Properties](/articles/03_logical_units/04_LU_properties.md)

   

![](/academy/03_fabric_basic_LU/images/information.png)There are other options for using theAuto Discovery Wizard, like overriding or enhancing a Logical Unit. Please read   [Auto Discovery Build or Update LU](/articles/03_logical_units/07_build__or_update_an_LU_schema.md).

 

 If you are not using the Auto Discovery Wizard, make sure that your Root Table and Instance ID are defined. Please read [Set Root Table and Instance ID](/articles/03_logical_units/08_define_root_table_and_instance_ID_LU_schema.md).

### Editing an LU

You may have noticed that although the Auto Discovery Wizard is quick, it doesn’t necessarily construct LU that have all the required source Schema tables for the implementation. Source tables can be added to an implementation as part of the LU Schema or can be created manually as part of the implementation. Please read the [LU Table Overview](/articles/06_LU_tables/01_LU_tables_overview.md) article.

The following describe how to create an LU table with its properties and indexes, specifically, a manual table:

-  [Create New LU Table](/articles/06_LU_tables/02_create_an_LU_table.md)

-  [Table Indexes](/articles/06_LU_tables/03_table_indexes.md)

-  [Table Properties](/articles/06_LU_tables/04_table_properties.md)

To add another table to the LU Schema, follow the steps in [Add a Table to a Schema](/articles/03_logical_units/09_add_table_to_a_schema.md).

To remove a specific table from an LU, follow the steps in [Delete Table from LU schema](/articles/03_logical_units/10_delete_table_from_a_schema.md).

### How Do I View and Review an LU?

You have created your first LU in a few clicks and understood its components and how to edit it. However, does it really fit the basic requirements? How do you know that the Schema you have just modeled retrieves the data the way you would like to view it? Can you validate the implementation and view at least one LUI’s data?

Well … the Fabric Studio can do this using its built-in Data Viewer. Let’s look at the Data Viewer, see how it is used and learn about its options. Please read the 
[Data Viewer Capabilities](/articles/13_LUDB_viewer_and_studio_debug_capabilities/01_data_viewer.md) article.

 

###   ![](/academy/Training_Level_1/03_fabric_basic_LU/images/example.png)  Example – Run the Data Viewer 

Let’s test an LU Instance and see the result. 

Open the CustomerLU Data Viewer and execute Instance ID 215:

1. Check that in the **Instance Tree** there is a **DB file** that has the **Current Date** and **Timestamp**.

2. Look at the **tables** that have been populated under the **Instance DB Tree **and their **data**.

3. Validate the _ K2 generated tables. 

4. Execute the following **query** in the **Data Viewer**:

   

   `select cases.status,cases.activity_id, activity.ACTIVITY_DATE,activity.ACTIVITY_NOTE from cases, activity where cases.ACTIVITY_ID=activity.ACTIVITY_ID` 

   

5. Do the same using the **CRM_DB Query Builder** and add the **CUSTOMER_ID** number. Is the displayed data the same?

 

### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png)Exercise – Add New Tables to The LU and Validate Them

The LU you have just created using the Auto Discovery Wizard contains basic Customer information only. However, you also need to see their Subscription and Billing info.

Using the training materials and examples covered so far, add the **CRM_DB.SUBSCRIBER** to the **CustomerLU**. 

1. `Question: How are you connecting the additional table?`

2. `Question: How many subscribers has Customer 82 got? What are their IDs?`

Add the BILLING_DB.BALANCEtable to the CustomerLU.

3. `Question: How long did it take to populate the CUSTOMER table?`

4.  `Question: What is the first AVAILABLE_AMOUNT for Subscriber 209?`


5.   `Question: What is total AVAILABLE_AMOUNT for Subscriber 209?`

​       

### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png)Solution - Add New Tables to the LU and Validate Them

1. `Answer: SUBSCRIBER.MSISDN to CONTRACT.ASSOCIATED_LINE_FMT`
2. `Answer: 2 Subscribers, 209 &210`
3. `Answer: 1.002 sec`
4. `Answer: 335 from 2015-01-04`
5. `Answer: select sum(AVAILABLE_AMOUNT) from balance; 3411`










[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/03_fabric_basic_LU/03_04_define_the_interfaces.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/03_fabric_basic_LU/06_table_population_and_sync_strategies.md)

 

 

 

 

 

------

