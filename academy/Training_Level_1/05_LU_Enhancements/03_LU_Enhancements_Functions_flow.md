![](/academy/Training_Level_1/05_LU_Enhancements/images/EnhancementFuncState.PNG)    

#   LU Enhancement Functions
                           

 

### Types of data manipulation

Let's take a look at the different functions that can be used to apply data manipulations on LU tables. But first, read about [Built-in functions](/articles/07_table_population/07_fabric_built_in_functions.md) and a specific set of utility functions that can be used in any type of data transformation.



### Fabric functions
Please read the following articles about specific Fabric functions:
- [LUDB functions](/articles/07_table_population/09_creating_an_LUDB_function.md) that can be invoked from within an SQL query.
- [Project functions](/articles/07_table_population/10_creating_a_project_function.md) that can be added to the project implementation to perform complex data manipulations or to execute queries on a specific instance.




### Root functions
Please read the following articles about root functions:
- [Creating a root function](/articles/07_table_population/11_1_creating_or_editing_a_root_function.md).
- [Examples of root functions and code example](/articles/07_table_population/11_2_root_functions_code_examples.md).



### Enrichment functions

Use enrichment functions to work directly on the data in the LU tables AFTER they have been populated. Enrichment functions have specific properties and features as you will discover by reading the following articles:

- [Enrichment Function Overview](/articles/10_enrichment_function/01_enrichment_function_overview.md).
- [Root Functions and Enrichment Functions Differences](/articles/10_enrichment_function/02_enrichment_vs_root_func_comparison_analysis.md).
- [Create & Edit an Enrichment function](/articles/10_enrichment_function/03_create_edit_enrichment_function.md).
- [Enrichment Order](\articles\03_logical_units\14_edit_enrichment_order.md).
- [Code Example](/articles/10_enrichment_function/04_enrichment_function_code_examples.md).



#### ![](/academy/Training_Level_1/05_LU_Enhancements/images/Exercise.png)

**Exercise 1 - Enrichment functions**

As you saw in the [Course User Story](/academy/Training_Level_1/01_Fabric_Introduction/1_3_course_user_story.md) at the beginning of this training, and as part of the company's marketing initiative, you need to ensure that all 5G/LTE contract lines are in international format. This enables new data roaming offers to be sent as text to the owners while they are abroad. 

Let's focus for now on the CustomerLU in the course's project. 

##### Question 1: Standardization of the phone number

To develop the functions pertaining to this course's section, use the CustomerLU data viewer to retrieve the LUI with the following IDs: 1123, 1125 and 1472.   

    1. How many distinct lines are associated with the LUI in the Contract table?
    

    2. How many lines are associated to a 5G/LTE offer and how many match the international standard format for US numbers?
       +1-xxx-xxx-xxxx (disregard the parenthesis and minus signs).
         

    3. Let's write a Java function that will modify any LUI "Associated Line" fields to the international format using the 
       contract_ID 2787 and 2788 to validate the code. 
        Tips:
            - Use the LUDB class to fetch data from the LUDB.  
            - Use regular expressions to identify the fields that need to be modified.
            - Use the fabric() execute function to update data. 
            

    4. Apply the previous data transformation to telephone lines that belong to a 5G/LTE contract. 
       Please modify the code accordingly.




##### Question 2: Case notes clean-up

The Case_Notes table stores notes about a specific case that has been opened for a specific owner.  
Tamar (Instance ID = 1472) recieves old bills and apology letters to her mailing address about issues she 
is experiencing with the network. The case notes reflect issues that are still open.

    1. Let's first list the contracts owned by Tamar in the data viewer.
           - How many contracts does she own ?
           

    2.  Let's look at the different notes in the Case_Note table of Tamar's LUI.
           - What is the ID number of the note suggesting that Tamar has been alienated and the associated Case Type description 
             (in the Cases table).
           - How many cases are still open ?
           

    3. Write a Java function that operates the following data transformations:
       - All case notes belonging to cases of the "Billing issues" type should be changed to "insolvent customer due to 
         alien assimilation".
       - All case notes belonging to cases of the "Network issues" type should be changed to "Customer has been 
         assimilated to a phone and is no longer network compatible".
       - All open cases should be set to Status="closed".

 
##### Question 3: Attaching the enrichment function to the appropriate table
      1. What happens if you attach the enrichment function to the CASES table ? 
      
      2. To which table should the function be attached ? 



#### ![](/academy/Training_Level_1/05_LU_Enhancements/images/Solution.png) 

**Solution - Enrichment functions**

##### Question 1: Standardization
    1. 4.
    
    2. 1 and 2.
    .
    3. Code, update all the phone number fields missing international code:     
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
     if ((cellValue.matches("(.*)+1(.*)") == false){
       formattedNumber = interCode + cellValue;
       fabric().execute(SQLFormattedNumber,formattedNumber,cellValue);
     } // end for statement       
   }// end loop through rows
   ```

    4. Update line numbers only for 5G/LTE contracts
     The if statement below should reflect the contract description cell value:

```java
  if ((cellValue.matches("(.*)+1(.*)") == false) && (cellValueContDesc.matches("(.*)5G(.*)"))){ ... }
```

    *Note that +1 & 5G parameters are currently defined in the function. You will see an * later in this section.



##### Question 2: Case notes clean-up
   
    1. None.
    
    2. Q1 -> 3708; Q2 -> 4.
    
    3. Code sample:	
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
   
   Db.Rows rowsC = fabric().fetch(SQLCASES);
   for (Db.Row row:rowsC){
   	String cellStatus=""+row.get("STATUS");
   	String cellCaseID=""+row.get("CASE_ID");
   	String cellCaseType=""+row.get("CASE_Type");
   	if (cellStatus.matches("Open"))
   	{
   	open_cases.add(cellStatus);
    fabric().execute(SQLCaseStatus,statusClose,cellStatus);
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
   	
   Db.Rows rowsN = fabric().fetch(SQLCASENote);
   for (Db.Row row:rowsN){
   String cellNoteText=""+row.get("NOTE_TEXT");
   String cellCaseID=""+row.get("CASE_ID");
   //reportUserMessage(cellCaseID);
   boolean ans1 = billing_cases.contains(cellCaseID);
   boolean ans2 = network_cases.contains(cellCaseID);
   if (ans1){
   fabric().execute(SQLBillingNote,newBillingNote,cellCaseID);	
   }
   if (ans2){
   fabric().execute(SQLNetworkNote,newNetworkNote,cellCaseID);	
   }
   }
   ```
                                                                       
##### Question 3: 
    Nothing since the CASE_NOTE table has not been synced yet. The function needs to be attached to the case_notes table.



### Decision functions

- [Sync Methods](/articles/14_sync_LU_instance/04_sync_methods.md).

- [Sync Decision Functions](/articles/14_sync_LU_instance/05_sync_decision_functions.md).

- [Decision Functions Recommendation](/articles/14_sync_LU_instance/06_sync_decision_functions_recommendations.md).

### Sync strategy for LU schemas and decision functions

- [Sync Method Levels](/articles/14_sync_LU_instance/07_sync_levels.md).
- [Sync Timeout](/articles/14_sync_LU_instance/08_sync_timeout.md).
- [Skip Sync](/articles/14_sync_LU_instance/09_skip_sync.md.md).
- [Sync Behavior](/articles/14_sync_LU_instance/10_sync_behavior_summary.md).


  
#### ![](/academy/Training_Level_1/05_LU_Enhancements/images/Exercise.png)

**Exercise 2 - Decision functions**

To save network resources, ensure that data synchronization of LUIs only occurs if the number of entries in a given external source's table is above the CRMCases_threshold threshold.

##### Question 1: Build a decision function based on the following criteria:

    - If the number of entries in the CASES table in the CRM_DB database is higher than CRMCases_threshold=25000 
      then the sync function will return the *syncind* boolean variable set to TRUE.
    
    - Hardcode the value of the threshold. You will return to this section later to see how to set this threshold 
      as a global parameter.

##### Question 2: Validate the function
    1. Run Instance ID: 1472. How many entries do you see in the Cases table of the LUI ? 
    
    2. Change the value of the CRMCases_threshold to 30000. 
    
    3. Check whether the sync process has been implemented by looking at the Cases table of the 1472 instance. 
       How many cases entries can you see?

  

#### ![](/academy/Training_Level_1/05_LU_Enhancements/images/Solution.png) 

**Solution - Decision functions**

##### Question 1:

```java
// this function will decide to synchronize an LUI if the number of cases is higher than an arbitrary hardcoded threshold
in the CRMCases_threshold=25000; //latest known number of cases in CRM_DB.CASES.
Boolean syncInd = false;

String count = db("CRM_DB").fetch("SELECT count(*) FROM CRM_DB.CASES").firstValue().toString();
//puts the number of rows in the CASES DB into a variable count.

reportUserMessage(count);
int cnt=Integer.parseInt(count);

if ( cnt >= CRMCases_threshold){
reportUserMessage("new case in !!");
syncInd = true;	
}

return syncInd;
```

##### Question 2:
    CRMCases_threshold=25000 -> 10 entries.
    CRMCases_threshold=30000 -> 0 entries.




[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/05_LU_Enhancements/02_LU_Enhancements_PopulationMap_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/05_LU_Enhancements/04_LU_Enhancements_lookup-translations_flow.md)

 

 

------

