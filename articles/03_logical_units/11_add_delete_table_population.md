# Add or Delete Population from LU Schema

A Table Population object holds the transformation and mapping rules of the data and populates the data into the LU table. Each table can have one or several populations. 

### How Do I Add a Table Population To the LU Schema?

<web>

**From the Schema Window**

1. Go to the **Project Tree** > **Logical Unit** > **Schema** to open the Schema window.
1. Click on the **LU table** > select **Add Population** from the context menu. 
1. Populate the population name. The new population flow is open. 
1. Add input argument(s) to the **PopulationArgs** Actor, make it **External** and save the population. 
1. Re-open the schema. The new population is automatically added to the LU schema. 
6. Link the new **population** to its **parent** table.

</web>

**From the Project Tree**

1. Go to the **Project Tree** > **Logical Unit** > right click the **LU table** > select **New Population** to create a new population object.  
1. Open the **LU schema **window. The new population is automatically added to the LU schema. 
1. Link the new **population** to its **parent **table.

<web>[Click for more information about Population Flow.](/articles/07_table_population/14_table_population_based_Broadway.md)</web>

<studio>[Click for more information about Table Population.](/articles/07_table_population/01_table_population_overview.md)</studio>

Note that you can edit the execution order of the new population if needed.

[Click for more information about Editing the Execution Order of a Table Population.](/articles/07_table_population/13_LU_table_population_execution_order.md)

### How Do I Delete a Table Population From an LU Schema? 
1. Go to the **Project Tree** > **Population Object** > press the **Delete** key. 
1. Re-open the **LU schema** screen.
1. **Save** the changes to remove the deleted population object from the LU schema.

<studio>Note that if the LU schema is open, you can click the **Refresh** icon on the toolbar.</studio>

[![Previous](/articles/images/Previous.png)](10_delete_table_from_a_schema.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](12_LU_hierarchy_and_linking_table_population.md)
