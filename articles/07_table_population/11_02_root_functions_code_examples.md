# Root Functions - Code Examples

### Example of a Root Function that Includes a Fabric Command
Use a Root Function to set an environment prior to fetching the data, for example to ensure that source data is not extracted from Production to avoid overloading the source system. The Root Function sets the environment on a session level and selects the data from the source environment:
1.	Create a **Root Function** using the generated code, add the **WHERE** clause and the parameters binding.
 
 <pre><code>
String sql =
		"SELECT * From ACTIVITY WHERE CUSTOMER_ID = ? ";
ludb().fetch(sql, i_customer_id).each(row->{
	yield(row.cells());
});
</code></pre>

2.	Add a Fabric command, for example ‘set environment’ using a predefined Global variable:

fabric().execute("set environment='" + SOURCE_ENV_NAME + "'");

[Click to display an example of the entire fnPop_NEW_ACTIVITY Root Function in the Demo project.]  

### Example pf a Root Function that Retireves Data from Several Data Sources
Use a Root Function to retrieve source data from several data sources, for example the current LU and an additional DB Interface. Add an SQL query per each data source.  
1.	Create a **Root Function** using the generated code for the first query that retrieves the data from the LU:

<pre><code>
String sql1 = "Select * From ACTIVITY where CUSTOMER_ID = ?";
Db.Rows rows = ludb().fetch(sql1,i_customer_id);
</code></pre>

2.	Then, to bring information from other data sources, create an additional SQL query to retrieve the data and populate it into the output Object[] structure:

<pre><code>
Db.Rows rows = ludb().fetch(sql1, i_customer_id );
for (Db.Row row:rows) {
	...
	caseRows = db("CRM_DB").fetch(sql2,activityID);	
	for (Db.Row caseRow:caseRows) {
		//retrieve the required data
...
//create an Array to add the output into Object[]
		List<String> caseList = new ArrayList<String>();
		caseList.add(customerID);
...
		yield(result);
	}
}
</code></pre>

[Click to display an example of the entire **fnPop_ACT_CASE_NOTE** Root Function in the Demo project.]  

