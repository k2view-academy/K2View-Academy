# LU Schema - Editing References Tab

## What Are Reference Tables?
Fabric enables creating Reference tables which can be used by all Logical Units.
A Reference table typically contains metadata that is referenced by different LU instances of a specific LU, by instances from a different LU or by Web Services. For example, a postal-code table that identifies the postal code of customer addresses. 
 

## LU Schema - Reference Tab
The Reference tab displays a list of the common Reference tables defined in the project. 
To ensure that a Reference table in an LU schema is always synched, check the Reference table in the list in the Reference tab. When Fabric synchronizes the LU instance, Fabric searches for the checked Reference tables, checks if they need to be synchronized and then synchronizes them.

Click for more information about Reference Tables.

## How Do I Edit a Reference Tab? 
1. Open the **LU Schema Window** and click the **References tab** to display the **Reference Tables** list. 
1. Check the **Reference Table** to create a **Lookup Object** in the **Reference Table** in the **LU Population**.

**Notes:** 
* A Reference table must be checked to enable creating a  Lookup  for the table.   
* A Reference table can be accessed using code (for example, function) also when the table is not checked in the References tab. 
* It is recommended to limit the number of checked reference tables in the Reference Tab to avoid a massive sync of the Reference tables when synchronizing the LU instance and as a result, slow the sync of the LU instance.

[![Previous](/articles/images/Previous.png)](/articles/03_logical_units/14_edit%20enrichment%20order.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/03_logical_units/16_LU_schema_group_and_ungroup_tables.md)
