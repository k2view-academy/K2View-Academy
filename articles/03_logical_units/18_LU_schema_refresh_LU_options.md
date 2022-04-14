# Refresh LU

<studio>

The **Logical Unit toolbar** has two Refresh icons:

![image](images/03_18_01_toolbar.png)

* Blue: Refresh project objects into the [LU schema](/articles/03_logical_units/03_LU_schema_window.md). 
* Green: Updates tables from the database, thereby rebuilding [LU tables](/articles/06_LU_tables/01_LU_tables_overview.md) based on the source DB. This is used to implement source DB updates into an LU schema. When needed, Update Tables from Database adds new columns to the LU table. 

Note that Update Tables from Database does not delete or add tables to the LU schema and does not delete columns from LU tables. These updates must be implemented  manually. 

</studio>

<web>

You can refresh you logical unit schema by using the Logical Unit Schema toolbar action icon <img src="images/web/schema_refresh.png" style="zoom:80%;" />



When clicked, schema is refreshed with updates which were done outside the schema. For example - when table's populations were added or removed or when tables structure were changed (if table was updated via the project > LU > tables folder or was updated via Git pull action).

In such cases you might see a popup notification at right bottom of the screen, indicating and hinting you about the change. it will look like this:

![](C:/K2View/K2View-Academy-7.0/articles/03_logical_units/images/web/11_delete_refresh_toaster.png)

Open the "Problems" panel view (can be opened from the top menu > View  or by pressing CTRL+SHIFT+M), to understand better what was changed and if you have action items to do.

For example, when a table population was added, you will see a similar entries at the Problems panel. In the example below, the Studio hints that a population was added and that links shall be set to the parent table:



![](C:/K2View/K2View-Academy-7.0/articles/03_logical_units/images/web/18_refresh_problems_panel.png)

</web>

[![Previous](/articles/images/Previous.png)](/articles/03_logical_units/17_LU_schema_change_root_table.md)

