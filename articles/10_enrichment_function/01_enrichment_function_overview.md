# Enrichment Function Overview

### What Is an Enrichment Function? 

An Enrichment function is a [Project function](/articles/07_table_population/08_project_functions.md) without Input/Output parameters that is used to insert, update, or delete an [LU Table's](/articles/06_LU_tables/01_LU_tables_overview.md) data after it has already been populated from a source object. It is defined as a specific category of Fabric [Regular functions](/articles/07_table_population/08_project_functions.md).

Enrichment function features include:

- Enrichment functions are executed once all [LU Tables](/articles/06_LU_tables/01_LU_tables_overview.md) in an [LU Schema](/articles/03_logical_units/03_LU_schema_window.md) have been populated. The [enrichment order](/articles/03_logical_units/14_edit%20enrichment%20order.md#edit-enrichment-order) is determined at the LU Schema level. An execution is performed only when an [instance is synced](/articles/14_sync_LU_instance/01_sync_LUI_overview.md).

- An Enrichment function does not have Input and Output parameters. It can extract data from any LU Table or other data sources and use it as needed.

- One or more Enrichment function can be attached to an LU Table using the **Enrichment Functions** field in [LU Table’s Properties tab](/articles/06_LU_tables/04_table_properties.md#enrichment-functions). 

- An LU Table’s columns can be updated by an Enrichment function if the LU Table column type is set to [Computed Field](/articles/06_LU_tables/02_create_an_LU_table.md#column-type).

- Unlike a [Root function](/articles/07_table_population/11_1_creating_or_editing_a_root_function.md), which is executed on each record of an LU Table, an Enrichment function is executed only once per LU Table and [LUI](/articles/01_fabric_overview/02_fabric_glossary.md#lui). 

  **Example:**

  Customer 123 has five INVOICE records. The Root function that populates the INVOICE LU Table runs five times for Customer 123. The Enrichment function on INVOICE LU Table runs once for Customer 123.

### When Should I Use Enrichment Functions?

Enrichment functions are needed when complex logic must be applied on all LU Table records. In this case, use an Enrichment function rather than a Root function, since an Enrichment function is executed once per LU Table and LUI, while a Root function is executed on every record of the LU Table.

[Click for more information about the differences between Enrichment Functions and Root Functions](/articles/10_enrichment_function/02_enrichment_vs_root_func_comparison_analysis.md).

#### Most common use cases of Enrichment functions:

- Populating an LU Table based on the calculated data from other LU Tables. For example, calculating the total amount of a customer's payments and updating the value in CUSTOMER LU table.
- Updating an external system based on the LUI data. For example, after populating the CUSTOMER table, the Fabric should notify the customer’s status to another system. 
- Masking a sensitive dara on the LU tables



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/10_enrichment_function/02_enrichment_vs_root_func_comparison_analysis.md)

 

 
