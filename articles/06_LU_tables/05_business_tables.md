# Business Tables

The ability to create business tables and to store them in the LU schema has been added to Fabric V6.5.4. 
Business tables are tables that give the user the ability to decide whether a table should have one or more populations and whether these populations should be connected, or not, to other tables in the LU schema.

Such tables allow the user to compute, transform and store new data inside the LU microDBs. 
Such data could be statistics, LUI-based calculations, or a mix of different functions and populations. 

In the example below, we can see different examples for such tables:

- **Business** table: 1 population, not connected to an LU parent table,

- **Business Child** table: 2 populations with 1 connected to its parent *Business* table, and the other population connected to the LU invoice table.

- **Business2** table: Unconnected to any other table in the schema and with no population.

![image](images/business_tables.PNG)

It is important to note that considering the flexibility of the populations types introduced with these tables, the appropriate population *execution order* will need to be carefully selected by the user, otherwise the table/field in question will not be filled. 



[![Previous](/articles/images/Previous.png)](04_table_properties.md)

