# Enrichment Function Overview

### What Is an Enrichment Function? 

An Enrichment function is a [Project function](/articles/07_table_population/08_project_functions.md) without Input/Output parameters that is used to insert, update, or delete an [LU table's](/articles/06_LU_tables/01_LU_tables_overview.md) data after it has already been populated from a source object. It is defined as a specific category of Fabric [Regular functions](/articles/07_table_population/08_project_functions.md#regular-function).

Note the following about **Enrichment functions**:

- Enrichment functions are executed once all [LU tables](/articles/06_LU_tables/01_LU_tables_overview.md) in an [LU schema](/articles/03_logical_units/03_LU_schema_window.md) have been populated. The [enrichment order](/articles/03_logical_units/14_edit%20enrichment%20order.md#edit-enrichment-order) is determined at the LU schema level. An execution is performed only when an [instance is synced](/articles/14_sync_LU_instance/01_sync_LUI_overview.md).

- An Enrichment function does not have Input and Output parameters. It can extract data from any LU table or other data sources and use it as needed.

- One or more Enrichment function can be attached to an LU table using the [Enrichment Functions](/articles/06_LU_tables/04_table_properties.md#enrichment-functions) field in the LU table’s Properties tab.


<studio>

Unlike a [Root function](/articles/07_table_population/11_1_creating_or_editing_a_root_function.md), which is executed on each record of an LU table, an Enrichment function is executed only once per LU table and [LUI](/articles/01_fabric_overview/02_fabric_glossary.md#lui). 

For example, Customer 123 has five Subscribers and each Subscriber has 10 INVOICE records. The Root function that populates the INVOICE LU table runs five times for Customer 123. The Enrichment function on the INVOICE LU table runs only once per instance, after all populations run on all tables in the LU schema.

</studio>

<web>

An Enrichment function is executed only once per LU table and [LUI](/articles/01_fabric_overview/02_fabric_glossary.md#lui). 

For example, Customer 123 has five Subscribers and each Subscriber has 10 INVOICE records. The Enrichment function on the INVOICE LU table runs only once per instance, after all populations run on all tables in the LU schema.

</web>



### When Should I Use Enrichment Functions?

Enrichment functions are needed when complex logic must be applied on all LU table records. <studio> In this case, use an Enrichment function rather than a Root function, since an Enrichment function is executed once per LU table and LUI, while a Root function is executed on every record of the LU table.

[Click for more information about the differences between Enrichment Functions and Root Functions](/articles/10_enrichment_function/02_enrichment_vs_root_func_comparison_analysis.md).

</studio>

#### Most Common Use Cases of Enrichment Functions 

- Populating an LU table based on the calculated data from other LU tables. For example, calculating the total amount of a customer's payments and updating the value in the CUSTOMER LU table.
- Updating an external system based on the LUI data. For example, after populating the CUSTOMER table, Fabric should notify another system about the customer’s status. 
- Masking sensitive data in LU tables.

<br>
**Please note:** While enrichment functions usage is very beneficial, we recommend using the population flows instead, for better control and visibility.

<studio>

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/10_enrichment_function/02_enrichment_vs_root_func_comparison_analysis.md)

</studio>

<web>

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/10_enrichment_function/03_create_edit_enrichment_function.md)

</web>

 

 
