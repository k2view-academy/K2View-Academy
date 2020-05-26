# Edit Enrichment Order

## What is an Enrichment Function?
An Enrichment Function is a Java function without input/output parameters that is used to insert, update or delete an [LU table](/articles/06_LU_tables/01_LU_tables_overview.md).
* Enrichment Functions are executed after the execution of all [population objects](/articles/07_table_population/01_table_population_overview.md) of  all LU tables. 
* One or several Enrichment Functions can be attached to each LU Table.

Click for more information about Enrichment Functions. 

## What is an Enrichment Order?
The execution order of all Enrichment Functions is determined at the [LU Schema](/articles/03_logical_units/03_LU_schema_window.md) level in the Enrichment Order tab. Only Enrichment Functions that are directly related to specific   tables are included in the display.

## How Can I Change the Enrichment Order? 
Use the **Up** and **Down** arrows in the **Enrichment Order** tab of the [**LU schema**](/articles/03_logical_units/03_LU_schema_window.md) to change the execution order of its Enrichment functions. 


[![Previous](/articles/images/Previous.png)](/articles/03_logical_units/13_disable_enable_populations_in_schema.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/03_logical_units/15_LU_schema_edit_reference_tab.md)
