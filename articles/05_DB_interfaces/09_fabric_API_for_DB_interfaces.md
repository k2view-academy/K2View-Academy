# Fabric API for DB Interfaces

### Connect DB Interface - DB Class
Fabric provides a Java Db class to use the methods and functions that will invoke database (SQL) queries and statements, and  Fabric commands.

### How Can I Create a DB Object?
The following Fabric UserCode methods can be used to create a Db object:

### DB Creation - Use Cases and Examples

### Execute Statements and Queries ti the DB Interface

**DB Class Methods - Common Use Cases**

The following table describes common use cases when working with DB Interfaces.\
**Click to display the Fabric API list:** http://<Fabric IP address>:3213/static/doc/user-api/index.html

### Using Binding Variables on SQL Statements
In specific cases, a query or statement may require input parameters. For example, Select all Customers by Customer Status.\
When using prepared statement queries use a binding parameter: 
* Add a question mark in the SQL statement when using binding parameters in the SQL query.
* Each input parameter must be sent as a parameter to/by the execute() or fetch() methods.
* The order of the input parameters must be aligned with the order of these parameters in the SQL statement.

See the example in the table above.

### How Can I Invoke the Result Set of the DB Query?
**DB.ROWS and DB.ROW Classes**\
The fetch() method returns the Db.Rows object which represents a result set of a query.\
You can iterate the result set and get a Db.Row object for each record, or alternatively, get the first value or the first row of the result set using firstValue() and firstRow() methods. 

**Notes** 
* Db.Row class represents a row in the result and implements Map<String,Object>.
* Use the following methods to access the array of the column values of the Db.Row object:
  * cells() - returns the array of cells
  * cell(int i) - returns the cell at position I (starts by zero), null if row is empty.
* Use the isEmpty() method to know if the row has data.
* To iterate column names and data simultaneously, use the values() method.
* This Map cannot be changed and edited. Trying to change this object will result in a runtime exception.

**Click for more information about Db.Rows and DB.Row classes:** http://<Fabric IP address>:3213/static/doc/user-api/index.html

### Loop on the Result Set Methods

The following methods can be used to loop a result set:
1.	Using **forEach/each** loop also closes the ResultSet and is the recommended way to iterate over a result.\
Note that the **each** method is similar to the **forEach** method and uses a Lambda interface to let  exceptions pass through and also enables closing a result set when an exception breaks the loop.

**Example 1**

Iterating the result set and checking the column name and value for each record:

Db.Rows rows = db(..).fetch(...);
row.forEach(r->{
      // each r is a map that represents a row.
      // Iteration order is guaranteed to follow the column order.
                r.forEach((col,value)->{
                        ...
                });
 });


**Example 2** 

Iterating the result set and yielding the values of each record:

Db.Rows rows = db(..).fetch(...);
rows.each(row-> yield(row.cells()));

2. Using for loop:

Db.Rows rows = db(..).fetch(...);
for (Db.Row row:rows) {
	….
 }

Note that since you are not using the each/forEach method the ResultSet will not close immediately and is deferred to the end of the current execution (sync/webservice/job).

To use a loop and control closure of the result set, use the try-resource structure:

 try (Db.Rows rows = db(..).fetch(...)) {
      for (Db.Row row:rows) {
      		…
}
 } finally {
	rows.close();
} 


### Code Examples

1. Select the number of orders by contract_id and order_status. The Select statement returns only one record and one field. Therefore, use the **firstValue()** method.

String sql = "SELECT COUNT(*) AS TOT_NUM_OF_ORDERS FROM ORDERS WHERE CONTRACT_ID = ? and ORDER_STATUS != ?";
Db.Rows rows = ludb().fetch(sql, contractId, orderStatus);
Integer noOfOrders = Integer.parseInt(rows.firstValue().toString());

2. Select a single record from the DB. Use the **firstRow()** method to get the single record returned by the DB query.

String sql = "SELECT * from CONTRACT WHERE CONTRACT_ID = ? “;
Db.Row row = ludb().fetch(sql, contractId).firstRow();


3. [Root function], select records from the CASES table and yield each record: 

String sql = "SELECT ACTIVITY_ID, CASE_ID, CASE_DATE, CASE_TYPE, STATUS FROM CRM_DB.CASES where activity_id = ?";
Db.Rows rows = db("CRM_DB").fetch(sql, input);
// Option 1 - using each method 
rows.each(row-> yield(row.cells()));

// Option 2- using for loop
//for (Db.Row row:rows)
//{
//	yield(row.cells());
//}


4. Run a Select and check the first value of each record.

Db.Rows rows = ludb().fetch(sql);
   
for (Db.Row row : rows) {
 if (row.cell(0) != null) // check the value of first column of the select statement
  values.append("\"" + row.cell(0) + "\",");
}

### Fabric - DB Interface - Deprecated Methods

Fabric has the following set of deprecated methods for handling DB interfaces:
* DBExecute
* DBExecutePrepared
* DBQuery
* DBQueryPrepared
* DBSelectValue
* DBPrepapredStatement

Although they work, a warning message is displayed when deprecated methods are used in Fabric code. It is recommended to use the new Fabric methods to invoke DB interfaces instead of working with deprecated methods. 








 





