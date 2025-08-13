# Exclude LU Tables from Broadway Flow Creation 

The **TDMFilterOutTargetTables** Actor contains a list of LU tables that do not require the creation of load and delete flows. By default, this Actor is populated by both TDM tables and [target LU tables](08_tdm_implement_delete_of_entities.md#lu-structure---target-tables) that were added to the LU for enabling entity deletion:

![example](images/TDMFilterOutTargetTables_example.png)

To filter out additional tables, open the **TDMFilterOutTargetTables** Actor and edit its **table** object. Use the following attributes to populate the **lu_name** column:

* ALL_LUS — when a filtered-out table applies to all TDM LUs.
* LU name — when a table belongs to a specific LU. In some cases, tables may need to be added to the LU schema in order to retrieve child IDs and support the [BE hierarchy](06_tdm_implementation_support_hierarchy.md). For example, the addition of the Orders table to the Customer LU generates a list of the customer's order IDs.

 If a [data generation flow](16_tdm_data_generation_implementation.md) should not be generated for the table, the **generator_filterout** column checkbox needs to be checked (i.e., set to **true**).

These tables should be added to the **TDMFilterOutTargetTables** Actor as it would prevent the load/delete flows creation for the tables; these tables are already loaded/deleted by the child LUs. 

Following completion of the Actor's update, refresh the project by clicking the ![image](images/11_tdm_refresh.PNG) button (top of the Project tree). This act applies the changes in the **TDMFilterOutTargetTables** Actor and deploys the LU.  
