# Enrichment Function Overview

### What Is an Enrichment Function? 

An Enrichment function is a [Project function](/articles/07_table_population/08_project_functions.md) without input/output parameters that is used to insert, update, or delete an LU table data after it has already been populated from a source object. It is defined as a specific category of the Fabric [regular functions](/articles/07_table_population/08_project_functions.md).

The unique features of an Enrichment function are:

- The Enrichment functions are executed once all [LU Tables](/articles/06_LU_tables/01_LU_tables_overview.md) of the LU Schema have been populated. The [enrichment order](/articles/03_logical_units/14_edit%20enrichment%20order.md#edit-enrichment-order) is determined at the [LU Schema](/articles/03_logical_units/03_LU_schema_window.md) level. The execution is performed only when an [instance is synced](/articles/14_sync_LU_instance/01_sync_LUI_overview.md) .

- An Enrichment function does not have any input and output parameters. It can extract data from any LU Table and use it as needed.

- One and more enrichment function is attached to an LU table using the Enrichment drop down list in [LU Table’s Properties tab](/articles/06_LU_tables/04_table_properties.md#enrichment-functions). 

- LU table’s columns can be updated by an Enrichment function if the LU table column type is set to [Computed Field](/articles/06_LU_tables/02_create_an_LU_table.md#column-type).

- Unlike a [Root function](/articles/07_table_population/11_1_creating_or_editing_a_root_function.md) which is executed on each record of the [LU Table](/articles/06_LU_tables/01_LU_tables_overview.md),  the Enrichment function is executed only once per LU Table and [LUI](/articles/01_fabric_overview/02_fabric_glossary.md#lui). 

  **Example:**

  Customer 123 has 5 INVOICE record. The Root function which populates INVOICE LU Table runs 5 times for Customer 123. The Enrichment function on INVOICE LU Table runs once for Customer 123.

### When Should I Use an Enrichment Function?

An Enrichment function is needed when a complex logic must be applied on all LU Table's records. In this case, you need to use an Enrichment function rather than a Root function, because the enrichment function is executed once per LU Table and LUI, while the Root function is executed on every record of the LU Table.

[Click for more information about the differences between Enrichment function and Root function](/articles/10_enrichment_function/02_enrichment_vs_root_func_comparison_analysis.md).

**Enrichment Functions Most Common Use Cases:**

- Populate an LU table based on the calculated data from other LU tables. For example, calculate the total amount of customer’s payments and update the value in CUSTOMER LU table.
- Update an external system based on the LUI data. For example, after you populate CUSTOMER table, you need to notify the customer’s status to another system. 



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/10_enrichment_function/02_enrichment_vs_root_func_comparison_analysis.md)

 

 