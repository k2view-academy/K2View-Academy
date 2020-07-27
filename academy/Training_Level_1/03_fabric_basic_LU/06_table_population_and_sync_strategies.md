# Table Population and Sync Strategies

​      ![](/academy/Training_Level_1/03_fabric_basic_LU/images/fabric_main_flow_06.png)              

Your LU is now constructed using tables from different source DBs and has a hierarchical parent-child representation. You have reviewed the implementation and were able to see the data quickly after moving it from the source into Fabric. 

Some questions have arisen:

- Do you have all the data you need? Does it need to be manipulated? 
- How can you make sure that the Customer LU’s data is always updated? 

### Table Population 

Your LU has been built using the Auto Discovery Wizard or using a Drag and Drop table object option based on the additional info that needs tobe displayed. The LU tables invoke a **Table Population** component, which is by default, the DB query that selects the data from its source. Let’s learn about Table Populations and how they can be best used for data retrieval. 

Please read:

-  [Table Population Overview](/articles/07_table_population/01_table_population_overview.md)

-  [Population Types and Comparison Analysis](/articles/07_table_population/02_source_object_types.md)

Now that you understand the benefits of a Table Population and its use cases, whether it is a DB query or a Java function known as a **Root function**, let’s see how to create one. 

Please read:

-  [Create New Table Population](/articles/07_table_population/03_creating_a_new_table_population.md)

-  [Debug Table Population](/articles/13_LUDB_viewer_and_studio_debug_capabilities/03_debug_table_population.md) 

 

### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/example.png)Example - Debug Table Population

1. Open the **BALANCE** table population map.
2. Click **Debug** and select **Instance 82** from the Instances List. 
3. Click **Execute Debug** or **Debug Record**.

`Question: What is the BALANCE_ID for the first record? Answer:2322`

 

You now understand how to adjust or manipulate the extracted data for your business requirements. However, how can your Call Center continue to see the most accurate data? 

### Fabric Sync Concept

Let’s understand the Fabric Sync concept and modes. Please read:

-  [Sync LUI Overview](/articles/14_sync_LU_instance/01_sync_LUI_overview.md).

-  [Sync Modes](/articles/14_sync_LU_instance/02_sync_modes.md).

We will continue discussing the synchronization modes and methods later on in our Training. 


[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/03_fabric_basic_LU/05_LU_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/03_fabric_basic_LU/07_quiz.md)


 

 





