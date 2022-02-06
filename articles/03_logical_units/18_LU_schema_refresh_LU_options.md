<studio>

# Refresh LU Options

The **Logical Unit toolbar** has two Refresh icons:

![image](images/03_18_01_toolbar.png)

* Blue: Refresh Items refreshes project objects into the [LU schema](/articles/03_logical_units/03_LU_schema_window.md). 
* Green: Updates tables from the database, thereby rebuilding [LU tables](/articles/06_LU_tables/01_LU_tables_overview.md) based on the source DB. This is used to implement source DB updates into an LU schema. When needed, Update Tables from Database adds new columns to the LU table. 

Note that Update Tables from Database does not delete or add tables to the LU schema and does not delete columns from LU tables. These updates must be implemented  manually. 



[![Previous](/articles/images/Previous.png)](/articles/03_logical_units/17_LU_schema_change_root_table.md)

</studio>
