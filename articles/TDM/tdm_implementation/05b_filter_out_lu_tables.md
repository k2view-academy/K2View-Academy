# Excluding LU Tables from Broadway Flow Creation 

The **TDMFilterOutTargetTables** Actor contains a list of LU tables that do not require the creation of load and delete flows. By default, this Actor is populated by both TDM tables and [target LU tables](08_tdm_implement_delete_of_entities.md#lu-structure---target-tables) that were added to the LU for enabling entity deletion:

![example](images/TDMFilterOutTargetTables_example.png)

To filter out additional tables, open the **TDMFilterOutTargetTables** Actor and edit its **table** object. Use the following attributes to populate the **lu_name** column:

* ALL_LUS — when a filtered-out table applies to all TDM LUs.
* LU name — when a table belongs to a specific LU.

## Generator Filterout Checkbox 
In some cases, tables may need to be added to the LU schema to retrieve child IDs and support the [BE hierarchy](06_tdm_implementation_support_hierarchy.md). For example, the addition of the Orders table to the Customer LU generates a list of the customer's order IDs. For these tables, TDM needs to create data generation flows, and therefore the **generation_filterout** checkbox is cleared. These tables still need to be added to the **TDMFilterOutTargetTables** Actor to prevent the creation of load and delete flows for them; such tables are already loaded/deleted by the child LUs. 
In other scenarios, [data generation flows](16_tdm_data_generation_implementation.md) should not be created for LU tables, such as TDM tables in the LU. In these cases, the **generator_filterout** column checkbox must be checked (i.e., set to **true**).

## Applying Changes to the TDMFilterOutTargetTables Actor
After updating the Actor, refresh the project by clicking the ![image](images/11_tdm_refresh.PNG) button at the top of the Project tree. This action applies the changes in the **TDMFilterOutTargetTables** Actor and deploys the LU.  
