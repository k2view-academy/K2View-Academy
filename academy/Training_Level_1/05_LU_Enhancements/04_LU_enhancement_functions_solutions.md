

![](/academy/Training_Level_1/05_LU_Enhancements/images/Solution.png) 

#### **Solution - Enrichment functions**

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





![](/academy/Training_Level_1/05_LU_Enhancements/images/Solution.png) 

#### **Solution - Decision functions**

##### Question 1:

```java
// this function will decide to synchronize an LUI if the number of cases is higher than an arbitrary hardcoded threshold
in the CRMCases_threshold=25000; //latest known number of cases in CRM_DB.CASES.
Boolean syncInd = false;

String count = db("CRM_DB").fetch("SELECT count(*) FROM CASES").firstValue().toString();
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



[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/05_LU_Enhancements/04_LU_enhancement_functions_exercises.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/05_LU_Enhancements/05_LU_enhancements_functions_solutions.md)
