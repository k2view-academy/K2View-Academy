![](/academy/Training_Level_1/05_LU_Enhancements/images/EnhancementFuncState.PNG)    

#   LU Enhancement Functions
                           

 

### Different types of data manipulations

We will now look at the different types of functions that can be used to apply data manipulations on your Logical Unit tables.

First let's look into a specific set of utility functions that can be used for any type of data transformation:

[Built-in functions](/articles/07_table_population/07_fabric_built_in_functions.md)



### Fabric functions

First of all let's understand the different types of project functions that can be developed and used by reading through this article: [Project Functions](/articles/07_table_population/08_project_functions.md).



- [LUDB functions](/articles/07_table_population/09_creating_an_LUDB_function.md) that can be invoked from within an SQL query.
- [Project functions](/articles/07_table_population/10_creating_a_project_function.md) added to the project implementation to perform complex data manipulations or to execute queries on a specific instance.



### Root functions

- [Creating a root function](/articles/07_table_population/11_1_creating_or_editing_a_root_function.md)
- [Examples of root functions and code example](/articles/07_table_population/11_2_root_functions_code_examples.md)



### Enrichment functions

In order to work directly on LU tables data, once and only once they have been populated, you will need to use enrichment functions. Such functions have specific properties and features as you will discover by reading the following articles:

- [Enrichment function Overview](/articles/10_enrichment_function/01_enrichment_function_overview.md)
- [Root functions & Enrichment functions differences](/articles/10_enrichment_function/02_enrichment_vs_root_func_comparison_analysis.md)
- [Create & Edit an Enrichment function](/articles/10_enrichment_function/03_create_edit_enrichment_function.md)
- [Enrichment order](\articles\03_logical_units\14_edit_enrichment_order.md)
- [Code Example](/articles/10_enrichment_function/04_enrichment_function_code_examples.md)



#### Exercise 1 - Enrichment functions

As we have seen in the [Course User Story](/academy/Training_Level_1/01_Fabric_Introduction/1_3_course_user_story.md), at the start of this training, and as part of the company's marketing initiative, we need to ensure that all 5G/LTE contract lines will be in international format, so an new data roaming offer can be sent by text to the owners while they are abroad. 

Let's focus for now on the CustomerLU in the course's project. 

    Question 1. Standardization of the phone number
    
    In order to develop the functions pertaining to this course's section, we will use the following LUIs (instances)
    Using the CustomerLU data viewer, retrieve the LU instances with the following IDs 1123, 1125 & 1472  
    
         a. How many distinct lines are associated with Luci (in the contract table)?
    
         b. How many lines are associated to a 5G/LTE offer and how many do match the international standard format for US numbers?
               +1-xxx-xxx-xxxx (we will disregard parenthesis & minus signs)
    
         c. Lets write a java function that will modify any LUI *<u>Associated Line</u>* fields to the international format using the 
         following IDs contract_ID (2787 & 2788) to validate the code. 
              - tips:
                   - use the ludb class to fetch data from the LU database  
                   - use regular expressions to identify the fields that need to be modified
                   - use LUDB (or fabric()) execute function to update data 
    
         d. We only wish to apply the previous data transformation to telephone lines that belong to a 5G/LTE contract. Please, modify
         the code accordingly.



    Question 2. Case Notes Clean-up
    
    The Case_Notes table stores all notes belonging to a particular case that was opened for a specific owner.  
    Tamar (Instance ID = 1472) keeps on receiving to her mailing address old bills as well as apology letters about issues she is experimenting with the 
    network. The case notes reflect issues of cases that are still opened.
    
        a. Let's first list the contracts owned by Tamar in the data viewer.
               - How many contracts does she own ?
    
           Lets look at the different notes in the Case_Note table of Tamar's LUI
               - What is the ID number of the note suggesting that Tamar has been alienated  and the associated Case Type description 
               (in the cases table)
               - How many cases are still opened ?
    
        b. Write a java function that operates the following data transformations:
    
           - All case notes belonging to cases of the type "Billing issues" should be changed to "insolvent customer due to alien assimilation"
           - All case notes belonging to cases of the type "Network issues" should be changed to "Customer has been assimilated to a phone and 
           is no longer network compatible"
           - All open cases should be set to Status="closed"

 


    Question 4. Attaching the enrichment function to the appropriate table
    
          - What happens if you attach the enrichment function to the table: CASES ? 
          - To which table should the function be attached ? 



#### Solution - Enrichment functions

    Question 1. Standardization
           a. 4
           b. 1 & 2
           c. Code: update all the phone numbers fields missing international code 	
   ```java
   String SQLNumber="SELECT ASSOCIATED_LINE, CONTRACT_DESCRIPTION FROM CONTRACT";
   String interCode="+1 ";
   //SQL statement for the fields updates
   String SQLFormattedNumber="UPDATE CONTRACT SET ASSOCIATED_LINE  = ? where  ASSOCIATED_LINE = ?";
   Db.Rows rows = fabric().fetch(SQLNumber);
   
   //start loop over all rows of LUDB
   for (Db.Row row:rows){   
   	String formattedNumber="";
   	String cellValue=""+row.get("ASSOCIATED_LINE");
   	String cellValueContDesc=""+row.get("CONTRACT_DESCRIPTION");
   
   //start matching test
   if ((cellValue.matches("(.*)+1(.*)") == false)
   {
    formattedNumber = interCode + cellValue;
    fabric().execute(SQLFormattedNumber,formattedNumber,cellValue);
   } // end for statement       
   }// end loop through rows
   ```



d. Update line numbers only for 5G/LTE contracts
 The statement should reflect the contract description cell value:

```java
 `if ((cellValue.matches("(.*)+1(.*)") == false)&&(cellValueContDesc.matches("(.*)5G(.*)"))){ ... }*`
 
```

Note: you will note that +1 & 5G are parameters that we currently define in the function. We will see later in this section, how we can turn these constants into global  parameters pertaining to the entire project and its multiple LUs. 



Question 2. Case Notes Clean-up

a. none
b. Q1 -> 3708; Q2 -> 4
c. Code sample	

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
   String SQLBillingNote="UPDATE CASE_NOTE SET NOTE_TEXT = ? where  CASE_ID = ?";
   String SQLCaseStatus="UPDATE CASES SET STATUS = ? where STATUS = ?";
   String SQLNetworkNote="UPDATE CASE_NOTE SET NOTE_TEXT = ? where  CASE_ID = ?";
   
   Db.Rows rowsC = ludb().fetch(SQLCASES);
   for (Db.Row row:rowsC){
   	String cellStatus=""+row.get("STATUS");
   	String cellCaseID=""+row.get("CASE_ID");
   	String cellCaseType=""+row.get("CASE_Type");
   	if (cellStatus.matches("Open"))
   	{
   	open_cases.add(cellStatus);
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
   ludb().execute(SQLBillingNote,newBillingNote,cellCaseID);	
   }
   if (ans2){
   ludb().execute(SQLNetworkNote,newNetworkNote,cellCaseID);	
   }
   }
   ```

​                                                                           

    Question 4. 
        Nothing as the CASE_NOTE table has not been sync-ed yet. The function needs to be attached to the case_notes table



### Decision functions

- [Sync Methods](/articles/14_sync_LU_instance/04_sync_methods.md)

- [Sync Decision Functions](/articles/14_sync_LU_instance/05_sync_decision_functions.md)

- [Decision Functions Recommendation](/articles/14_sync_LU_instance/06_sync_decision_functions_recommendations.md)

### Sync strategy for LU schemas and Decision functions

- [Sync Method Levels](/articles/14_sync_LU_instance/07_sync_levels.md)
- [Sync Timeout](/articles/14_sync_LU_instance/08_sync_timeout.md)
- [Skip Sync](/articles/14_sync_LU_instance/09_skip_sync.md)
- [Sync Behavior](/articles/14_sync_LU_instance/10_sync_behavior_summary.md)


  

#### Exercise 2 - Decision functions


    In order to save network resources, we have decided to ensure that data synchronization of LUIs will only happen if a change 
    happens on the external source.
    
    Building on top of previous exercise, build a decision function based on the following criteria:
    
    -  If the number of entries in the CASES table of the CRM_DB database has increased then the sync function will return a boolean 
    variable - *syncind* set to TRUE
    
    - At this stage you should hardcode the value of the current number of entries of the CASES table. You should extract that value 
    using a "select count *" statement in the query builder

  




#### Solution - Decision functions

```java
// this function will monitor whether a change has 
// happened in the CRM_CASES table will print a user report 
// with the Notes associated
String SQLCASES="SELECT count(*) FROM CRM_DB.CASES";
int LASTNumberRows=25144; //latest known number of cases in CRM_DB.CASES
Boolean syncInd = false;

String count = db("CRM_DB").fetch("SELECT count(*) FROM CRM_DB.CASES").firstValue().toString();
reportUserMessage(count);
int cnt=Integer.parseInt(count);

if ( cnt >= LASTNumberRows){
reportUserMessage("new case in !!");
syncInd = true;	
}

return syncInd;
```





[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/05_LU_Enhancements/02_LU_Enhancements_PopulationMap_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/05_LU_Enhancements/04_LU_Enhancements_lookup-translations_flow.md)

 

 

------

