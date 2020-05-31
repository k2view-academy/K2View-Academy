# Creating or Editing an Enrichment Function

An Enrichment function is a [project function](/articles/07_table_population/08_project_functions.md) without input/output parameters that is used to insert, update, or delete an LU table data after it has already been populated from a source object. It is defined as a specific category of the Fabric [regular functions](/articles/07_table_population/08_project_functions.md).

### How Can I Create or Edit an Enrichment Function?

The steps for creating an Enrichment  function in Fabric Studio are the same as those for a regular function. Set the **Function Type** to **Regular Function** and the **Category** to **Enrichment**. Do not define any input/output parameters.

![10_03_create_enrichment_1](/articles/10_enrichment_function/images/10_03_create_enrichment_1.PNG)

[Click for more information about How to Create a Project Function.](/articles/07_table_population/10_creating_a_project_function.md)

### How Do I Attach an Enrichment Function to an LU Table?

In order for the Enrichment function to be executed during the [LU sync process](/articles/14_sync_LU_instance/01_sync_LUI_overview.md), it must be attached to one or more [LU Tables](articles/06_LU_tables/01_LU_tables_overview.md) in the [LU Schema](articles/03_logical_units/03_LU_schema_window.md). Do the following steps:

1. Go to **Project Tree** > **Logical Units** > [**LU Name**] > **Tables** > [**Table Name**] to display the **Columns tab** in the **Table Schema** window.

2. To attach the [Enrichment function in the Table Properties tab](articles/06_LU_tables/04_table_properties.md#enrichment-functions), click the three dots next to the Enrichment Functions option to open the Enrichment Item Collection Editor pop-up. <!--Add the link from /articles/06_LU_tables/04_table_properties.md#enrichment-functions to here -->

   ![10_03_create_enrichment_2](/articles/10_enrichment_function/images/10_03_create_enrichment_2.PNG)

3. Click **Add** button and then click the area next to Name to display the list of Enrichment functions.

   - Only the functions without input and output parameters are displayed there.

4. Select the function from the list. 

5. (Optional) To add more Enrichment functions to the same LU Table, click **Add** again and select the additional functions. In this case, you can define the order of Enrichment functions execution using the arrows next to the function names in the Editor pop-up.

   ![10_03_create_enrichment_3](/articles/10_enrichment_function/images/10_03_create_enrichment_3.PNG)

6. Click **OK** to close the Editor pop-up.

7. **Save** the table.

8. Open the **LU Schema > Enrichment Order tab** to verify that the enrichment order across all the Enrichment functions in the [LU](/articles/03_logical_units/01_LU_overview.md#logical-unit-lu-overview) is correct. Note that you can [edit the enrichment order](/articles/03_logical_units/14_edit%20enrichment%20order.md#edit-enrichment-order) on the LU Schema level.



[![Previous](/articles/images/Previous.png)](//articles/10_enrichment_function/02_enrichment_vs_root_func_comparison_analysis.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/10_enrichment_function/04_enrichment_function_code_examples.md)