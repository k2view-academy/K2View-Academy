# Fabric API for DB Interfaces

### Connect DB Interface - Db Class
Fabric provides a Java Db class to use the methods and functions that invoke database (SQL) queries and statements and  Fabric commands.

### How Can I Create a Db Object?
The following Fabric **UserCode** methods can be used to create a Db object:
<table width="630">
<thead>
<tr>
<td width="220pxl">
<p><strong>User Code Method</strong></p>
</td>
<td width="600pxl">
<p><strong>Description</strong></p>
</td>
</tr>
</thead>
<tbody>
<tr>
<td width="213">
<p><strong>db(String interfaceName)</strong></p>
</td>
<td width="417">
<p>Obtain the Db object to connect the given logical interface name whose input must be populated by a DB interface defined in Fabric Studio.</p>
</td>
</tr>
<tr>
<td width="213">
<p><strong>ludb(), fabric()</strong></p>
</td>
<td width="417">
<p>Obtain a Db object to connect the current Fabric context.</p>
</td>
</tr>
<tr>
<td width="213">
<p><strong>ludb(String lutype, String luid)<strong></p>
</td>
<td width="417">
<p>Obtain a &nbsp;Db connection to the local Fabric and GET the specific Logical Unit Instance. If the Logical Unit is the same as the last one referenced by this method, GET is not invoked.</p>
</td>
</tr>
</tbody>
</table>


### DB Creation - Use Cases and Examples
<table width="630">
<thead>
<tr>
<td width="300pxl">
<p><strong>Use Cases</strong></p>
</td>
<td width="300pxl">
<p><strong>User Code Method</strong></p>
</td>
<td width="300pxl">
<p><strong>Examples</strong></p>
</td>
</tr>
</thead>
<tbody>
<tr>
<td width="204">
<p><strong><h4>Connect to the local Fabric via an LU sync. Invoke the currently-synced LU Instance.&nbsp;</strong></p>
</td>
<td width="198">
<p>db(String interfaceName)</p>
<p>ludb()</p>
<p>fabric()</p>
</td>
<td width="228">
<p>Db conn = db(&ldquo;fabric&rdquo;);</p>
<p>Note: you do not need to define the &ldquo;fabric&rdquo; interface in the Fabric Studio.</p>
<p>Db conn = ludb();</p>
<p>Db conn = fabric();</p>
</td>
</tr>
<tr>
<td width="204">
<p><strong><h4>Connect to the local Fabric using a Web Service.</strong></p>
</td>
<td width="198">
<p>ludb(String lutype, String luid)</p>
</td>
<td width="228">
<p>Db Conn = ludb("Customer", 2);</p>
</td>
</tr>
<tr>
<td width="204">
<p><strong><h4>Connect to the local Fabric via an LU sync.&nbsp; Get the LU Instance from another LU.</strong></p>
</td>
<td width="198">
<p>ludb(String lutype, String luid)</p>
</td>
<td width="228">
<p>Db Conn = ludb("Customer", 2);</p>
</td>
</tr>
<tr>
<td width="204">
<p><strong><h4>Connect to another DB interface.</strong></p>
</td>
<td width="198">
<p>db(String interfaceName)</p>
</td>
<td width="228">
<p>Db conn = db(&ldquo;CRM_DB&rdquo;);</p>
<p>Db Conn = db("dbFabricRemote");</p>
<p>Note:</p>
<p>Execute the GET instance command on the Db object to get the LU instance before running SQL statements on the Fabric Remote interface.</p>
</td>
</tr>
</tbody>
</table>


### Execute Statements and Queries on the DB Interface

**DB Class Methods - Common Use Cases**

The following table describes common use cases when working with DB interfaces.\
To view the list of Fabric APIs, click **http://[Fabric IP address]:3213/static/doc/user-api/index.html**
<table>
<tbody>
<tr>
<td width="300pxl">
<p><strong>Use Case</strong></p>
</td>
<td width="300pxl">
<p><strong>Db Method</strong></p>
</td>
<td width="274">
<p><strong>Example</strong></p>
</td>
</tr>
<tr>
<td width="300pxl">
<p><strong><h4>Execute Fabric Command</strong></p>
<p><strong>&nbsp;</strong></p>
</td>
<td width="225">
<p>execute or fetch methods. Depends if you get an output from the executed Fabric command.</p>
</td>
<td width="274">
<p>fabric().execute("get CRM.3");</p>
<p>ludb().execute(&ldquo;"set environment='"+src_env_name+ "'");</p>
<p>ludb().fetch(&ldquo;"DESCRIBE TABLE " + tblNameFabric);</p>	
</td>
</tr>
<tr>
<td width="129">
<p><strong>Execute DB Statement. For example, run update statement on a table</strong></p>
</td>
<td width="225">
<p>public&nbsp;void&nbsp;execute(String sql, Object params)</p>
</td>
<td width="274">
<p>String sql = "UPDATE CONTRACT SET NO_OF_OPEN_ORDERS =&hellip; WHERE CONTRACT_ID = ? &rdquo;; <br /> ludb().execute(sql, cotractId);</p>
</td>
</tr>
<tr>
<td width="129">
<p><strong>Run select statement on DB interface</strong></p>
</td>
<td width="225">
<p>public Db.Rows fetch(String sql,</p>
<p>Object params)</p>
<p>&nbsp;</p>
</td>
<td width="274">
<p>String sql = "SELECT COUNT(*) AS TOT_NUM_OF_ORDERS FROM ORDERS WHERE CONTRACT_ID = ? and ORDER_STATUS != ?";<br /> Db.Rows rows = ludb().fetch(sql, contractId, orderStatus);</p>
</td>
</tr>
</tbody>
</table>

### Using Binding Variables on SQL Statements
In specific cases, a query or statement may require input parameters. For example, Select all Customers by Customer Status.\
When using prepared statement queries always use binding parameters: 
* Add a question mark in the SQL statement for each parameter in the SQL query.
* Each input parameter must be sent as a parameter to the execute() or fetch() methods.
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
* This map cannot be changed or edited. Trying to change this object will result in a runtime exception.

To view the list of Fabric APIs, click **http://[Fabric IP address]:3213/static/doc/user-api/index.html**

### Loop on the Result Set Methods

The following methods can be used to loop a result set:
1.	Using **forEach/each** loop also closes the ResultSet and is the recommended way to iterate over a result.\
Note that the **each** method is similar to the **forEach** method and uses a Lambda interface to let  exceptions pass through and also enables closing a result set when an exception breaks the loop.

**Example 1**

Iterating the result set and checking the column name and value for each record:
```java
Db.Rows rows = db(..).fetch(...);
row.forEach(r->{
   // each r is a map that represents a row.
   // Iteration order is guaranteed to follow the column order.
   r.forEach((col,value)->{
      ...
   });
 });
```

**Example 2** 

Iterating the result set and yielding the values of each record:

```java
Db.Rows rows = db(..).fetch(...);
rows.each(row-> yield(row.cells()));
```

2. Using for loop:

```java
try (Db.Rows rows = db(..).fetch(...)) {
   for (Db.Row row:rows) {
      	
   }
} 
```

Note that since you are not using the each/forEach method the ResultSet will not close at the end of the loop and is deferred to the end of the current execution (sync/webservice/job). 



### Code Examples

1. Select the number of orders by contract_id and order_status. The Select statement returns only one record and one field. Therefore, use the **firstValue()** method.

```java
String sql = "SELECT COUNT(*) AS TOT_NUM_OF_ORDERS FROM ORDERS WHERE CONTRACT_ID = ? and ORDER_STATUS != ?";
Db.Rows rows = ludb().fetch(sql, contractId, orderStatus);
Integer noOfOrders = Integer.parseInt(rows.firstValue().toString());
```

Note that calling firstValue also closes the result set.

2. Select a single record from the DB. Use the **firstRow()** method to get the single record returned by the DB query.

```java
String sql = "SELECT * from CONTRACT WHERE CONTRACT_ID = ? “;
Db.Row row = ludb().fetch(sql, contractId).firstRow();
```

Note that calling firstRow also closes the result set.

3. [Root function](/articles/07_table_population/02_source_object_types.md), select records from the CASES table and yield each record: 

```java
String sql = "SELECT ACTIVITY_ID, CASE_ID, CASE_DATE, CASE_TYPE, STATUS FROM CASES where activity_id = ?";
Db.Rows rows = db("CRM_DB").fetch(sql, input);
// Option 1 - using each method 
rows.each(row-> yield(row.cells()));

// Option 2- using for loop
for (Db.Row row:rows) {
   yield(row.cells());
}
```

4. Run a Select and check the first value of each record.

```java
Db.Rows rows = ludb().fetch(sql);
   
for (Db.Row row : rows) {
   if (row.cell(0) != null) { // check the value of first column of the select statement
      values.append("\"" + row.cell(0) + "\",");
   }
}
```

### Fabric - DB Interface - Deprecated Methods

Fabric has the following set of deprecated methods for handling DB interfaces:
* DBExecute
* DBExecutePrepared
* DBQuery
* DBQueryPrepared
* DBSelectValue
* DBPrepapredStatement

Although they work, a warning message is displayed when deprecated methods are used in Fabric code. It is recommended to use the new Fabric methods to invoke DB interfaces instead of working with deprecated methods. 

[![Previous](/articles/images/Previous.png)](07_deleting_disabling_an_interface.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](10_database_types.md)






