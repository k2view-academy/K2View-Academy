# Creating a Trigger Function

### What Is a Trigger Function?

A Trigger function is a Project function invoked from the **On Change** [LU table property](/articles/06_LU_tables/04_table_properties.md#on-change) when the LU table's data has been modified.

* Any trigger function on Shared or LU level can be attached to an LU table.
* The same trigger function can be attached to several LU tables of the same LU.
* Several trigger functions can be attached to one LU table and their execution order is established at the time of the functions attachment to an LU table.

### How Do I Create or Edit a Trigger Function?

To create a Trigger function, refer to the steps in [How to Create Project Functions](10_creating_a_project_function.md).

![image](images/07_091_01.png)

When creating a Trigger function, make sure that:

*	**Function Type = Trigger Function.** 
*	The function has an input parameter **tableDataChange** of the data type = **TableDataChange**.

The **TableDataChange** data type exposes a set of methods which allows to get additional information about the change such as:

* What kind of change occurred? Possible values are INSERT / UPDATE / DELETE.
* Which table was changed?
* Which fields were changed?
* What are the old values and the new values?

The Trigger function can analyze the change using the above information and execute a business logic, for example write the old and the new values into a log table.

### Example of a Trigger Function

1. Create a new function with **Function Type = Trigger Function**.

2. Write the business logic, for example if the change type = INSERT populate the table name,  the old values and the new values into a specific log table defined in the Fabric Common DB.

   ~~~java
   String tbl = tableDataChange.getTable().toString();
   DataChangeType change = tableDataChange.getType();
   List newValuesList = tableDataChange.newValuesAsList();
   String newValues = newValuesList.toString();
   List oldValuesList = tableDataChange.oldValuesAsList();
   String oldValues = oldValuesList.toString();
   
   if( change == DataChangeType.INSERT) {
       Db ci = db("fabric");
       ci.beginTransaction();
       ci.execute("insert into DATA_CHANGES values (?,?,?)",tbl,oldValues,newValues);
       ci.commit();
   }
   ~~~

3. Open LU table's properties window and attach the Trigger function to the table's **On Change** property.

