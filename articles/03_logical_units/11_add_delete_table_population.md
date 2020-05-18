# Edit Schema - Adding or Deleting Table Population

A Table Population object holds the transformation and mapping rules of the data and populates the data into the LU table. Each table can have one or several populations. 

## How Do I Add a Table Population to the LU Schema?
1. Go to the **Project Tree**, right click the **LU table** and select **New Table Population** to create a new population object.  A notification opens informing you that the change requires a manual update of the LU schema file.
1. Click **Yes** to open the **LU schema** and to go to the **LU Schema screen**. The new population is automatically added to the LU schema. 
1. Link the new **population** to its **parent**.
1. Optional: edit the execution order of the new population if needed.

Click for more information about Table Population.

[Click for more information about Linking Table Populations to a Parent Population.](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/12_LU_hierarchy_and_linking_table_population.md)

Click for more information about Editing the Execution Order of a Table Population.

## How Do I Delete a Table Population From an LU Schema? 
1. Go to the **Project Tree**, click the **Population Object** and then press the **Delete** key. 
1. Do either:\
  a. Re-open the **LU schema** screen. \
  b. If the LU schema screen is open, click the **Refresh** icon on the toolbar.
1. Save the changes to remove the deleted population object from the LU schema.
