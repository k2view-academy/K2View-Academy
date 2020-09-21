# Table Population and Sync Strategies

![](/academy/Training_Level_1/03_fabric_basic_LU/images/fabric_main_flow_06.png)              

NTT -> Raise following questions:

- Do you have all the data you need? Does it need to be manipulated? 
- How can you make sure that the Customer LU’s data is always updated? 

### Table Population 


LU tables invoke a **Table Population** component, which is by default, the DB query that selects the data from its source. 

NTT -> Introduce Table Populations and how they can be best used for data retrieval. 

Present:

-  [Table Population Overview](/articles/07_table_population/01_table_population_overview.md)

-  [Population Types and Comparison Analysis](/articles/07_table_population/02_source_object_types.md)

Remove: -  [Broadway as a Population](/articles/14_table_population_based_Broadway.md)

Introduce difference between DB query or a Java **Root function**, let’s see how to create one. 

Present:
- [Create New Table Population](/articles/07_table_population/03_creating_a_new_table_population.md)

- [Debug Table Population](/articles/13_LUDB_viewer_and_studio_debug_capabilities/03_debug_table_population.md) 



NTT -> Reminder on how to adjust or manipulate the extracted data depending on business requirements. 
NTT -> Question how can your Call Center continue to see the most accurate data? 

### Fabric Sync Concept

NTT -> Explain the Fabric Sync concept and modes. Please read:

-  [Sync LUI Overview](/articles/14_sync_LU_instance/01_sync_LUI_overview.md).

-  [Sync Modes](/articles/14_sync_LU_instance/02_sync_modes.md).





------

 





