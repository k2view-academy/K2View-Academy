# Sync Method Levels

### What are Sync Method Levels?

[Sync properties](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/14_sync_LU_instance/04_sync_methods.md) can be defined on [LU Schema,](https://github.com/k2view-academy/K2View-Academy/wiki/Logical-Unit-Schema-Window) [LU Table](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/06_LU_tables/01_LU_tables_overview.md) or [Table Population](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/01_table_population_overview.md) levels. By default, LU Tables, Table Population objects and [Enrichment Functions] inherit the Sync properties defined for the LU Schema.\
The Sync properties of an LU Schema can be overridden, as follows:
* Setting Sync properties on the LU Table. By default, the related Table Population objects and the enrichment functions attached to the LU Table inherit the Sync properties of the LU Table.
* Setting Sync properties on each Table Population object. 

![image](https://k2vacademy.s3.amazonaws.com/Fabric/6_Sync/6_6_sync_levels.png)

## Overriding Sync Properties in an LU Schema - Use Cases

You may need to override the LU Schema Sync properties on LU Tables or Table Populations that require specific handling. The following table describes use cases for overriding the Sync properties on each object:
* LU Table.
* Table Population.

[![Previous](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Previous.png)](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/14_sync_LU_instance/06_sync_decision_functions_recommendations.md)[<img align="right" width="60" height="54" src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Next.png">](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/14_sync_LU_instance/08_sync_timeout.md)





 
