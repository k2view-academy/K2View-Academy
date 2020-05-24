# LU Tables Overview

LU Tables are the basic building blocks for creating [Logical Units](/articles/03_logical_units/01_LU_overview.md) and their [Schemas](/articles/03_logical_units/03_LU_schema_window.md). 
Their purpose is to hold data retrieved from various sources after it has been transformed according to a Project’s requirements. An LU Table can also act as the Master of Data so that they hold the data generated in Fabric and not synced from source systems.

[Click for more information about How to Add an LU Table to an LU Schema](/articles/03_logical_units/09_add_table_to_a_schema.md). 

LU Tables can be created either automatically, for example, using the [Auto Discovery Wizard](/articles/03_logical_units/06_auto_discovery_wizard.md) or manually. 
When building a table, first, define the table properties, columns, and indexes. Then, create the Table Population  map which holds the transformation and mapping rules.
Creating a population for an LU Table is optional. When a table acts as the Master of Data, it does not get feeds from source systems. In such case, the [Table Population](/articles/07_table_population/01_table_population_overview.md) is redundant.  


### LU Table Structure
An LU Table window has the following tabs:
*	[Table Columns](/articles/06_LU_tables/02_create_an_LU_table.md), holding a list of columns and their definitions.
*	[Table Indexes](/articles/06_LU_tables/03_table_indexes.md#index-definition), holding a list of indexes which are added to an LU Table to improve the selection process. Each LU Table can have several indexes where each index can contain several columns.
*	Search, holding  a list of fields available for a cross-instance search.
*	[CDC topic], holding a list of fields to be published to an external system (CDC topic) using the CDC mechanism.
*	[Table Properties](/articles/06_LU_tables/04_table_properties.md),  where the table’s properties like the Primary Key, [Sync Method](/articles/14_sync_LU_instance/04_sync_methods.md) or related Enrichment Functions  are defined. 
  
[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/06_LU_tables/02_create_an_LU_table.md)
