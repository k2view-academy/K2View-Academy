<studio>

# Root Functions - Code Examples

### Example of a Root Function that Includes a Fabric Command
Use a Root function to set an environment prior to fetching the data, for example to ensure that source data is not extracted from Production to avoid overloading the source system. The Root function sets the environment on a session level and selects the data from the source environment:
1. Create a **Root function** using the generated code, add the **WHERE** clause and the parameters binding.

~~~java
String sql = "SELECT * From ACTIVITY WHERE CUSTOMER_ID = ? ";
ludb().fetch(sql, i_customer_id).each(row->{
   yield(row.cells());
});
~~~

2. Add a Fabric command, for example **set environment** using a predefined [Global](/articles/08_globals/01_globals_overview.md) variable:

~~~
fabric().execute("set environment='" + SOURCE_ENV_NAME + "'");
~~~

Full example of the entire fnPop_NEW_ACTIVITY Root Function can be found in the Demo project.

### Example of a Root Function that Retrieves Data from Several Data Sources
Use a Root function to retrieve source data from several data sources, for example the current LU and an additional DB interface. Add an SQL query per each data source.  
1. Create a **Root function** using the generated code for the first query that retrieves the data from the LU:

~~~java
String sql1 = "Select * From ACTIVITY where CUSTOMER_ID = ?";
Db.Rows rows = ludb().fetch(sql1,i_customer_id);
~~~

2. Then, to bring information from other data sources, create an additional SQL query to retrieve the data and populate it into the output Object[] structure:

~~~java
ludb().fetch(sql1, i_customer_id ).each(row-> {
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
   });
}
~~~

Full example of the entire **fnPop_ACT_CASE_NOTE** Root Function can be found in the Demo project.

### Example of a Dummy Root Function
Create a dummy Root function when the LU table is populated by an [Enrichment function](/articles/10_enrichment_function/01_enrichment_function_overview.md) or a population of another LU table:

~~~java
if (1 == 2) yield(new Object[]{null});
~~~
[![Previous](/articles/images/Previous.png)](11_1_creating_or_editing_a_root_function.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](11_3_creating_an_LUDB_function.md)

</studio>

