# Enrichment Functions - Code Examples

### Simple Example of an Enrichment Function that Populates a Computed Field

Use an Enrichment function to validate the retrieved data and update the Computed Field on an LU Schema's table. For example, to calculate the number of months a case is not Closed and populate this information in the CASES table for each case.

1. Create an **Enrichment function**.
 <!--Tali- I think that we are missing the point of enrichment function if we scan and update each record. In this case- why not having the calculation in the population? 
We can build the enrichment function to update all records by one statement: 
"update CASES set CASE_OPEN_MONTHS=  round((julianday('now') - julianday(CASE_DATE))/365*12)  
where case_status = 'Open'";
You can find a similar example in our project- fnEnrichContract fubnction
-->
   <pre><code>
   String caseStatus = "Closed";
   String sqlCountMonths = 
   "SELECT round((julianday('now') - julianday(CASE_DATE))/365*12) as CASE_OPEN_MONTHS, CASE_ID, STATUS from CASES";
   String sqlUpdate = "UPDATE CASES SET CASE_OPEN_MONTHS = ? WHERE CASE_ID = ?";
   ludb().fetch(sqlCountMonths).each(row->{
   	Integer caseID = Integer.parseInt(row.cell(1).toString());	
   	if (row.cell(2).toString().equals(caseStatus)){
   		ludb().execute(sqlUpdate, "0", caseID);
   	}
   	else {
   		double d = Double.parseDouble(row.cell(0).toString());
   		int noOfMonths = (int) d;
   		ludb().execute(sqlUpdate, noOfMonths, caseID);	
   	}
   });
   </code></pre>

2. Add a new column to the CASES table with **Column Type** = **Computed Field** and attach the **Enrichment function** to the CASES table via the **Table Properties** tab. 

[Click to display an example of the **fnMonthsOpenCase** Enrichment Function in the Demo project.]

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

3. To do this generically, define a **new translation** and then populate the **translation data** using the validation functions described in step 1. Check the relevant translation entries as **Active** so that you can include their validations as needed. For example, a **trnValidationFuncList** translation.

   ![10_03_create_enrichment_1](/articles/10_enrichment_function/images/10_04_enrichment_code_examples_2.PNG)

4. Create an **Enrichment function**. For example, an **fnCheckValidationsResults** Enrichment function that will go over the results in the table and update a specific indicator in the **CUSTOMER** table.

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

[Click to display an example of the **fnCheckValidationsResultsEnrichment** Function in the Demo project.]

[![Previous](/articles/images/Previous.png)](h/articles/10_enrichment_function/03_create_edit_enrichment_function.md)
