
![](/academy/Training_Level_1/05_LU_Enhancements/images/Exercise.png)

#### **Exercise 2 - Decision functions**

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





![](/academy/Training_Level_1/05_LU_Enhancements/images/Exercise.png)

#### **Exercise 2 - Decision functions**

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




[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/05_LU_Enhancements/03_LU_Enhancements_Functions_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/05_LU_Enhancements/05_LU_enhancements_functions_solutions.md)
