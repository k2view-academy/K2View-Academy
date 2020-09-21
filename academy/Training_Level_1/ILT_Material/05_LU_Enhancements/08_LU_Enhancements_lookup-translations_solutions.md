

![](/academy/Training_Level_1/05_LU_Enhancements/images/Solution.png) 

#### **Solution Exercise 1 - Lookup table**

            Step 1: 
            In the table from customer LU, it is much more efficient to use the data already populated into the LU table 
            than the CRM table.
            
            Step 2: 
            The k2_concat5 function will allow you to concatenate up to 5 strings and to also define a delimiter !. In this specific 
            example, only 2 strings are needed and the delimiter is set to ".". 
            The next stage of the exercise could be to add a UID (to avoid duplicate names), and an internet address string such as
            "@yourcompany.com" to provide an email address for your customers.
            
            Step 3:
            The "Lookup Not Found" configuration variable sets up the system's behaviour if a specific value does not exist  
            in the lookup table. The different options are: "Reject record", "Reject Instance", "Continue" and 
            "Report and Continue". In our example, we use a table that is very likely to have all the records (first name 
            and last name are mandatory fields) and therefore should be set to "Continue".
            
            Step 4: 
            Rana Bradshaw



![](/academy/Training_Level_1/05_LU_Enhancements/images/Exercise.png) 



[](/academy/Training_Level_1/05_LU_Enhancements/images/Solution.png)

#### **Solution Exercise 2 - Translations**

            Steps 1 and 2:
Translation table schema:
![image](/academy/Training_Level_1/05_LU_Enhancements/images/TransExe2-OverviewCapture%20(3).PNG) 

Translation table data:
![image](/academy/Training_Level_1/05_LU_Enhancements/images/TransExe2-OverviewCapture%20(2).PNG) 

Address population diagram featuring translation table and LAT/LONG concatenation function:
![image](/academy/Training_Level_1/05_LU_Enhancements/images/TransExe2-OverviewCapture%20(1).PNG)

            Step 3:
            InstanceID 1000: 39.7771::-86.1458
            InstanceID 2217: 1840034016


![](/academy/Training_Level_1/05_LU_Enhancements/images/Exercise.png) 



[](/academy/Training_Level_1/05_LU_Enhancements/images/Solution.png) 

####  **Solution Exercise 3 - Globals**

            Step 1:
            SQL Query with embedded reference to Globals:
                        Select INVOICE.* From INVOICE Where INVOICE.ISSUED_DATE < '@OLDINVOICES@'
                        
            Step 2:
            1. Decision function based on Globals:

```
// This function will decide to synchronize an LUI if the number of cases is higher than an arbitrary hardcoded threshold
Boolean syncInd = false;
String count = db("CRM_DB").fetch("SELECT count(*) FROM CASES").firstValue().toString();
//puts the number of rows in CASES DB into variable count
int cnt=Integer.parseInt(count);
// using the RUN_POP object defined as Globals
if (cnt > Integer.parseInt(RUN_POP)){
syncInd = true;	
}
else {
syncInd = false;
}
return syncInd;
```

            2. Answer: 5.
            
            3. The sync did not happen.
    
            4. Yes the sync happened (since there are more than 20000 entries in the CRM_ DB CASES table). 
               This time, the sync process did happen since you defined RUN_POP as a non-final GLOBAL and since you have overridden 
               its value for all the *.RUN_POP instances in the scope of this Fabric session.


​            
Globals definition:    
![image](/academy/Training_Level_1/05_LU_Enhancements/images/GlobalExe3OverviewCapture.png)
​            
​                      
​            
            Step 3:

```
reportUserMessage("Invoice Cleaning fonction is running");
String SQLINVOICES="SELECT * FROM INVOICE";
String SQLInvoicesDelete="DELETE FROM INVOICE WHERE ISSUED_DATE = ?";
Db.Rows rows = ludb().fetch(SQLINVOICES);
for (Db.Row row:rows){
String cellCaseDate=""+row.get("ISSUED_DATE");
String[] date = cellCaseDate.split("\\s+");
if(date[0].compareTo(OLDINVOICES) < 0) {
reportUserMessage("invoice date is earlier than 2015/12/31");
fabric().execute(SQLInvoicesDelete,cellCaseDate);
}
}
```
            Step 4: Answer: 19 entries.
            
            Step 5: Exercise 1 of Enrichment Functions and the adjusted line below in the if statement

```
if ((cellValue.matches("(.*)+(.*)") == false))
{
formattedNumber = INTERCODE_UK + cellValue; 
//Makes use of the GLOBAL here w/o having to declare it
fabric().execute(SQLFormattedNumber,formattedNumber,cellValue);
//ending the if statement		
}
```



------
