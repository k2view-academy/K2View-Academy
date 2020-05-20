# LU Schema - Editing References Tab

## What are Reference Tables?
Fabric enables creating Reference Tables which can be used by all Logical Units.
A Reference Table typically contains metadata that is referenced by different LU instances of a specific LU, by instances from a different LU or by Web Services. For example, a postal-code table that identifies the postal code of customer addresses. 
 

## LU Schema - Reference Tab
The Reference tab displays a list of the common Reference Tables defined in the project. 
To ensure that a Reference Table in an LU Schema is always synched, check the Reference Table in the list in the Reference tab. When Fabric synchronizes the LU instance, Fabric searches for the checked Reference tables, checks if they need to be synchronized and then synchronizes them.

Click for more information about Reference Tables.

## How Do I Edit a Reference Tab? 
1. Open the **LU Schema Window** and click the **References tab** to display the **Reference Tables** list. 
1. Check the **Reference Table** to create a **Lookup Object** in the **Reference Table** in the **LU Population**.

**Notes** 
* A Reference Table must be checked to enable creating a  Lookup  for the table. . 
* A Reference Table can be accessed using code (for example, function) also when the table is not checked in the References tab. 
* It is recommended to limit the number of checked reference tables in the Reference Tab to avoid a massive sync of the reference tables when synchronizing the LU instance and as a result, slow the sync of the LU instance.

[![Previous](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Previous.png)](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/14_edit%20enrichment%20order.md)[<img align="right" width="60" height="54" src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Next.png">](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/16_LU_schema_group_and_ungroup_tables.md)
