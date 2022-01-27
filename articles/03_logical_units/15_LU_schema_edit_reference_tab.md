# LU Schema - Editing References Tab

### What Are Reference Tables?
Fabric enables creating [Reference tables](/articles/22_reference(commonDB)_tables/01_fabric_commonDB_overview.md) which can be used by all Logical Units.
A Reference table typically contains metadata that is referenced by different LU instances of a specific LU, by instances from a different LU or by Web Services. For example, a postal-code table that identifies the postal code of customer addresses. 


### LU Schema - Reference Tab
The Reference tab displays a list of the common Reference tables defined in the project. 
To ensure that a [Reference table](/articles/22_reference(commonDB)_tables/02_reference_table_fabric_studio.md#sync-method) in an LU schema is always populated when synching an instance for the first time, check the Reference table in the list in the Reference tab.

Click [here](/articles/22_reference%28commonDB%29_tables/02_reference_table_fabric_studio.md) for more information about Reference Tables.

### How Do I Edit a Reference Tab? 
1. Open the **LU Schema Window** and click the **References tab** to display the **Reference Tables** list. 
1. Check the **Reference Table** to create a **Lookup Object** in the **Reference Table** in the **LU Population**.


**Notes:** 
* A Reference table must be checked to enable creating a Lookup  for the table.   
* A Reference table can be accessed using code (for example, function) also when the table is not checked in the References tab. 
* It is recommended to limit the number of checked reference tables in the Reference Tab to avoid a massive sync of the Reference tables when synchronizing a LU instance for the first time.

[![Previous](/articles/images/Previous.png)](/articles/03_logical_units/14_edit%20enrichment%20order.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/03_logical_units/16_LU_schema_group_and_ungroup_tables.md)
