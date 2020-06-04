# Enrichment Functions - Code Examples
### Simple Example of an Enrichment Function that Populates a Computed Field

Use an Enrichment function to validate the retrieved data and update the Computed Field on an LU Schema's table. For example, to calculate the number of months a case is not Closed and populate this information in the CASES table for each case, whereby for all Closed cases - set the number of month to 0.

1. Create an **Enrichment function**.
 <pre><code>
String caseStatus = "Closed";
String sqlNotClosed = "UPDATE CASES SET CASE_OPEN_MONTHS = "+
  "(SELECT round((julianday('now') - julianday(C2.CASE_DATE))/365*12) from CASES C2 "+
  " WHERE C2.CASE_ID = CASES.CASE_ID AND C2.STATUS != ?)";
ludb().execute(sqlNotClosed,caseStatus);
String sqlClosed = "UPDATE CASES SET CASE_OPEN_MONTHS = 0 WHERE STATUS = ?";
ludb().execute(sqlClosed,caseStatus);
</code></pre>
   
2. Add a new column to the CASES table with **Column Type** = **Computed Field** and attach the **Enrichment function** to the CASES table via the **Table Properties** tab. 

[Click to display an example of the **fnMonthsOpenCase** Enrichment function in the Demo project.]

### Complex Example of Several Validations, Including the Use of an Enrichment Function 

To perform a number of validations on retrieved data after a sync and to save the results in a dedicated table, develop business logic that includes using an Enrichment function, Root function and several Regular functions. For example, to check if a customer has roaming services and Open cases, to save validation results in a table, and to populate a specific indicator in the CUSTOMER table, do the following:

1. Create the validations as a set of **Regular Project functions** - one per each validation. For example, the **fnHasRoaming** function:

   <pre><code>
   String roamingService = "Roaming special";
   String sql = "SELECT count(*) FROM SUBSCRIBER WHERE CONTRACT_DESCRIPTION=? ";
   Db.Rows rows = ludb().fetch(sql, roamingService);
   Integer hasRoaming = Integer.parseInt(rows.firstValue().toString());
   if (hasRoaming > 0){
   	return  "Customer has Roaming services !!";
   }
   else
   	return  "OK";
   </code></pre>

2. Create a **new table** populated by a **Root function** that executes the validations and populates the results in the table. For example, the **EXEC_VALIDATIONS** table is populated by the **fnExecuteValidations** Root function in the **popExecValidations** population.

   ![10_03_create_enrichment_1](/articles/10_enrichment_function/images/10_04_enrichment_code_examples_1.PNG)

3. To do this generically, define a [new translation](/articles/09_translations/02_creating_a_new_translation_in_fabric.md) and then populate the [translation data](/articles/09_translations/03_data_population_in_a_translation.md) using the validation functions described in step 1. Check the relevant translation entries as **Active** so that you can include their validations as needed. For example, a **trnValidationFuncList** translation.

   ![10_03_create_enrichment_2](/articles/10_enrichment_function/images/10_04_enrichment_code_examples_2.PNG)

4. Create an **Enrichment function**. For example, an **fnCheckValidationsResults** Enrichment function that will go over the results in the table and update a specific indicator in the CUSTOMER table.

   <pre><code>
   String sqlSelect = "SELECT count(*) FROM EXEC_VALIDATIONS WHERE RESULT !='OK'";
   Db.Rows rows = ludb().fetch(sqlSelect);
   Integer validationsNotOK = Integer.parseInt(rows.firstValue().toString());
   if (validationsNotOK > 0){
   	String sqlUpdate = "UPDATE CUSTOMER SET VALIDATIONS_NOT_PASSED = ?";
   	ludb().execute(sqlUpdate, validationsNotOK);
   }
   </code></pre>

5. Attach the **Enrichment function** to the **EXEC_VALIDATIONS** table via the **Table Properties** tab. 

[Click to display full code example of all the above functions in the Demo project.]



### Example of an Enrichment Function that Populates the Param Table

A common scenario  widely used in TDM is to populate the Param table using an Enrichment function. A Param table is the table which aggregates a number of parameters which are used in the LU for the business logic and validations. For example, in Orders LU you need to gather various Orders parameters, such as number of MSISDNS, number of open orders, etc. The definition which parameters should be gathered can be defined in the Translation. The gathering will be done by the Enrichment function.

1. Create a new translation **trnOrdersParams** that will define a list of parameters and an SQL query for calculating each parameters.

   ![10_03_create_enrichment_3](/articles/10_enrichment_function/images/10_04_enrichment_code_examples_3.PNG)

2. Create an Enrichment function **fnEnrichmentOrderParam** that will retrieve and loop over the translation's data, and for each entry - calculate the parameter and populate it into the target table ORDERS_PARAMS.

~~~
   Map<String,Map<String,String>> data = getTranslationsData("trnOrdersParams");
   StringBuilder stringInsertFabricLuParam = 
   new StringBuilder().append("INSERT OR REPLACE INFO ORDERS_PARAMS (IID, ");
   String prefix = "";
   ...
   if(data.size() > 0){
   	...
    // Get the SQL query for each parameter
	   String sql = valMap.get("SQL");
    ...
   	//Run the SQL statement on the LU
	   Db.Rows rows = ludb().fetch(sql);
   	...
   	// Insert a new record to ORDERS_PARAM
    String insertSQL = stringInsertFabricLuParam.append(sqlInsertBind).toString();
    ludb().execute(insertSQL, params);
   }
~~~
[Click to display an example of the **fnEnrichmentOrderParam** Enrichment function in the Demo project.]

[![Previous](/articles/images/Previous.png)](h/articles/10_enrichment_function/03_create_edit_enrichment_function.md)
