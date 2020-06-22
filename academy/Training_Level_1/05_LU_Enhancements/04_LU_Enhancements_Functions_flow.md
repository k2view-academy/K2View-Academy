#   LU Enhancement Functions

 ![](/academy/05_LU_Enhancements/images/fabric_main_flow_05.png)                                                    

 

### Different types of data manipulations

We will now look at the different types of functions that can be used to apply data manipulations on your Logical Unit tables.

First let's look into a specific set of utility functions that can be used for any type of data transformation:

[Built-in functions](\articles\07_table_population\07_fabric_built_in_functions.md)



### Fabric functions

First of all let's understand the different types of project functions that can be developed and used by reading through this article: [Project Functions](articles/07_table_population/08_project_functions.md).



- [LUDB functions](articles/07_table_population/09_creating_an_LUDB_function.md) that can be invoked from within an SQL query.
- [Project functions](articles/07_table_population/10_creating_a_project_function.md) added to the project implementation to perform complex data manipulations or to execute queries on a specific instance.



### Root functions

- [Creating a root function](articles/07_table_population/11_1_creating_or_editing_a_root_function.md)
- [Examples of root functions and code example](articles/07_table_population/11_2_root_functions_code_examples)



### Enrichment functions

In order to work directly on LU tables data, once and only once they have been populated, you will need to use enrichment functions. Such functions have specific properties and features as you will discover by reading the following articles:

- [Enrichment function Overview](/articles/10_enrichment_function/01_enrichment_function_overview.md)
- [Root functions & Enrichment functions differences](/articles/10_enrichment_function/02_enrichment_vs_root_func_comparison_analysis.md)
- [Create & Edit an Enrichment function](/articles/10_enrichment_function/03_create_edit_enrichment_function.md)
- [Code Example](/articles/10_enrichment_function/04_enrichment_function_code_examples.md)



### Decision functions

- [Sync Methods](\articles\14_sync_LU_instance\04_sync_methods.md)
- [Sync Decision Functions](\articles\14_sync_LU_instance\05_sync_decision_functions.md)
- [Decision Functions Recommendation](\articles\14_sync_LU_instance\06_sync_decision_functions_recommendations.md)



### Exercises - Enrichment functions

As part of the company's marketing initiative, we need to ensure that all 5G/LTE contract lines will be in international format, so an new data roaming offer can be sent by text to the owners while they are abroad. 

Let's focus for now on the Customer LU in the project. 

Using the data viewer for the CRM table and the appropriate SQL (SELECT ... WHERE) statement, let's start by building a population for 3 specifics entities:

**a. Explore the sources** 

Retrieve the Customer IDs of the following entities:

- Luci Mcmahon

- Larry Collier

- Tamar Forbes

**b. Standardization** 

Using the Customer LU data viewer, retrieve the instances belonging to Luci, Larry & Tamar

- How many distinct lines are associated with Luci (in the contract table)?
- How many lines are associated to a 5G/LTE offer and how many do match the international standard format for US numbers?
  - +1-xxx-xxx-xxxx (we will disregard parenthesis & minus signs)
- Lets write a java function that will modify the line associated with contract_ID 2787 & 2788, into the proper format:
  - tips:
    - use dbRows to loop in the table
    - use regular expressions to match the correct fields
    - operate LUDB execute function
- We only wish to apply the previous data transformation telephone lines belonging to a 5G/LTE contract. Modify the code accordingly.

**c. Case Notes Clean-up**

Tamar keeps on receiving to her mailing address old bills as well as apology letters about issues she is experimenting with the network.

1. let's first list the contracts pertaining to Tamar. How many contracts does she own ?

    		2. lets look at the different notes in the Case_Note table of Tamar's LUI
               		1. What is the ID number of the note suggesting that Tamar has been alienated  and the associated Case Type description (in the cases table)?
                     		2. How many cases are still opened ?

3. Write a java function that operates the following data transformations:

- All case notes belonging to cases of the type "Billing issues" should be changed to "billing"

- All case notes belonging to cases of the type "Network issues" should be changed to "Customer has been assimilated to a phone and is no longer network compatible"

- All cases should be set to Status="closed"

4. **Attach the function**

   What happens if you attach the enrichment function to the table: CASES ? To which table should the function be attached ? 



ANSWERS

**a. Explore the sources**

1. Luci -> 1123
2. Larry -> 1125
3. Tamar -> 2472

**b. Standardization**

1. 4

2. 1 & 2

3. 

   1. First part : update all the phone numbers fields missing international code 	

   ```java
   
   String SQLNumber="SELECT ASSOCIATED_LINE, CONTRACT_DESCRIPTION FROM CONTRACT";
   String interCode="+1 ";
   
   Db.Rows rows = ludb().fetch(SQLNumber);
   
   //start loop over all rows of LUDB
   for (Db.Row row:rows){   
   	String SQLFormattedNumber="";
   	String formattedNumber="";
   	String cellValue=""+row.get("ASSOCIATED_LINE");
   	String cellValueContDesc=""+row.get("CONTRACT_DESCRIPTION");
   
   //start matching test
   if ((cellValue.matches("(.*)+1(.*)") == false)
   {
   	reportUserMessage(cellValue);
   	
   	formattedNumber = interCode + cellValue;
   //SQL statement for the fields updates (where applicable)
       SQLFormattedNumber="UPDATE CONTRACT SET ASSOCIATED_LINE  = ? where  ASSOCIATED_LINE = ?";
   	ludb().execute(SQLFormattedNumber,formattedNumber,cellValue);
   
   } // end for statement
       
   }// end loop through rows
   ```

2. update line numbers only for 5G or LTE contracts

   `if ((cellValue.matches("(.*)+1(.*)") == false)&&(cellValueContDesc.matches("(.*)5G(.*)"))){ ... }`

**c. Case Notes Clean-up**

1. none

2. Q1 -> 3708; Q2 -> 4

3. ​	

   ```java
   String Contracts="SELECT COUNT (*) FROM CONTRACT";
   String SQLCASENote="SELECT CASE_ID, NOTE_TEXT, NOTE_DATE FROM CASE_NOTE";
   String SQLCASES="SELECT CASE_ID, CASE_TYPE, STATUS FROM CASES";
   ArrayList<String> open_cases = new ArrayList<String>();
   ArrayList<String> billing_cases = new ArrayList<String>();
   ArrayList<String> network_cases = new ArrayList<String>();
   
   String newBillingNote ="insolvent customer due to alien assimilation";
   String newNetworkNote ="customer has been assimilated into a phone and is no longer network compatible";
   String statusClose="CLOSED";
   
   Db.Rows rowsC = ludb().fetch(SQLCASES);
   for (Db.Row row:rowsC){
   	String cellStatus=""+row.get("STATUS");
   	String cellCaseID=""+row.get("CASE_ID");
   	String cellCaseType=""+row.get("CASE_Type");
   	if (cellStatus.matches("Open"))
   	{
   	open_cases.add(cellStatus);
   	String SQLCaseStatus="UPDATE CASES SET STATUS = ? where STATUS = ?";
   	ludb().execute(SQLCaseStatus,statusClose,cellStatus);
   	}
   	if (cellCaseType.matches("Billing Issue"))
   	{
   	billing_cases.add(cellCaseID);
   	}
   	if (cellCaseType.matches("Network Issue"))
   	{
   	network_cases.add(cellCaseID);
   	}	
   	
   }
   	
   Db.Rows rowsN = ludb().fetch(SQLCASENote);
   for (Db.Row row:rowsN){
   String cellNoteText=""+row.get("NOTE_TEXT");
   String cellCaseID=""+row.get("CASE_ID");
   //reportUserMessage(cellCaseID);
   boolean ans1 = billing_cases.contains(cellCaseID);
   boolean ans2 = network_cases.contains(cellCaseID);
   if (ans1){
   	String SQLBillingNote="UPDATE CASE_NOTE SET NOTE_TEXT = ? where  CASE_ID = ?";
   	ludb().execute(SQLBillingNote,newBillingNote,cellCaseID);	
   }
   if (ans2){
   	String SQLNetworkNote="UPDATE CASE_NOTE SET NOTE_TEXT = ? where  CASE_ID = ?";
   	ludb().execute(SQLNetworkNote,newNetworkNote,cellCaseID);	
   }
   }
   ```




4. Nothing as the CASE_NOTE table has not been populated yet. 



[![Previous](/articles/images/Previous.png)](\academy\Training_Level_1\05_LU_Enhancements\01_LU_Enhancement_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](\academy\Training_Level_1\05_LU_Enhancements\01_LU_Enhancement_PopMappingExercises.md)

 

 

 

 

 

------

