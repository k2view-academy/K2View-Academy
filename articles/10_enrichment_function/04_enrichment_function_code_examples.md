# Enrichment Functions - Code Examples

### Simple Example of an Enrichment Function which Populates a Computed Field

Use an Enrichment function to validate the retrieved data and update the Computed Field on one of LU Schema tables. For example, you need to calculate the number of months the case is not Closed and populate this information in the table CASES per each such case.

1. Create an **Enrichment function**.

   <pre><code>
   String caseStatus = "Closed";
   String sqlCountMonths = 
   "SELECT round((julianday('now') - julianday(CASE_DATE))/365*12) as CASE_OPEN_MONTHS, CASE_ID, STATUS from CASES ";
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

2. Add a new column to table CASES with **Column Type** = **Computed Field** and attach the **Enrichment function** to table CASES via the **Table Properties tab**. 

[Click to display an example of the **fnMonthsOpenCase** Enrichment Function in the Demo project.]

### Complex Example of Several Validation, Including the Use of an Enrichment Function 

When you need to perform several validations on the retrieved data after the sync and keep the validations results in a dedicated table, you can use an Enrichment function, a Root function and several regular functions to support it. For example, you need to check if the customer has Roaming services and if he has Open cases, to keep the validations results in a table and also populate a special indicator in the table CUSTOMER. To support this scenario, do the following steps:

1. Create the validations as a set of **regular Project functions** - one per each validation. For example, a function **fnHasRoaming**:

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

2. Create a **new table** that will keep the validation results and a **Root function** that will execute these validations and populate the results in the table. For example, a table **EXEC_VALIDATIONS** is populated by the Root function **fnExecuteValidations** in the population **popExecValidations**.

   ![10_03_create_enrichment_1](/articles/10_enrichment_function/images/10_04_enrichment_code_examples_1.PNG)

3. In order to do it in a generic way, you can define a **new translation** and define the list of functions in it. You can also check each translation entry as Active or not, by that you can include or exclude the validations as needed. For example, a translation **trnValidationFuncList**.

   ![10_03_create_enrichment_1](/articles/10_enrichment_function/images/10_04_enrichment_code_examples_2.PNG)

4. Create an **Enrichment function**. For example, an enrichment function **fnCheckValidationsResults** will go over the results in the table and update a special indicator on table **CUSTOMER**.

   <pre><code>
   String sqlSelect = "SELECT count(*) FROM EXEC_VALIDATIONS WHERE RESULT !='OK'";
   Db.Rows rows = ludb().fetch(sqlSelect);
   Integer validationsNotOK = Integer.parseInt(rows.firstValue().toString());
   if (validationsNotOK > 0){
   	String sqlUpdate = "UPDATE CUSTOMER SET VALIDATIONS_NOT_PASSED = ?";
   	ludb().execute(sqlUpdate, validationsNotOK);
   }
   </code></pre>

5. Attach the **Enrichment function** to table **EXEC_VALIDATIONS** via the **Table Properties tab**. 

[Click to display an example of the **fnCheckValidationsResultsEnrichment** Function in the Demo project.]

[![Previous](/articles/images/Previous.png)](h/articles/10_enrichment_function/03_create_edit_enrichment_function.md)
