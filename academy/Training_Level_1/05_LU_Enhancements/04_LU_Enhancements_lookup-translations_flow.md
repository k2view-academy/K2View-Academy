![](/academy/Training_Level_1/05_LU_Enhancements/images/EnhancementTablesState.PNG) 

#   LU Enhancement Tables & Globals


### Using tables for data manipulations

Now you have seen how functions can be attached to LU tables to modify data, let's see how tables and functions can be used to enrich data.

In this section the following topics are addressed:

-  [Lookup tables](/articles/07_table_population/11_lookup_tables.md)

-  [Translation tables](/articles/09_translations/01_translations_overview_and_use_cases.md)

-  [Creating a new translation table](/articles/09_translations/02_creating_a_new_translation_in_fabric.md) 

-  [Data population in a translation](/articles/09_translations/03_data_population_in_a_translation.md)

-  [Using Translations in Fabric](/articles/09_translations/04_using_translations_in_fabric.md)

-  [Translations code example](/articles/09_translations/05_translations_code_examples.md)



### Globals

-  [Globals overview](/articles/08_globals/01_globals_overview.md)

-  [Globals use cases](/articles/08_globals/02_globals_use_cases.md)

-  [Globals in Fabric commands](/articles/08_globals/03_set_globals.md)

-  [Globals code example](/articles/08_globals/04_globals_code_examples.md)



#### ![](/academy/Training_Level_1/05_LU_Enhancements/images/Exercise.png) 

**Exercise 1 - Lookup tables**
  
  Using the CustomerLU schema, let's look at the Address population map. 

  Add the first name and last name of the customer to the Address LU table so that full information about the customer is readily available.

            Step 1: 
            Which table should you consider using and why ? 
            
            Step 2: 
            Which function would you use to make sure that the first name and last name are concatenated into one field in 
            the Address table ?
            
            Step 3: 
            In the Properties panel, what does the "Lookup Not Found" configuration variable mean ? What are the
            available options ? 
            Using our example, what will most likely be the value of this configuration variable ?
            
            Step 4: 
            Save the lookup table and deploy and run instance 2472. Which name appears in the Address table ?


#### ![](/academy/Training_Level_1/05_LU_Enhancements/images/Solution.png) 

**Solution Exercise 1 - Lookup table**

            Step 1: 
            In the table from customerLU, it is much more efficient to use the data already populated into the LU table 
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



#### ![](/academy/Training_Level_1/05_LU_Enhancements/images/Exercise.png) 

**Exercise 2 - Translations**

Associate a unique geo-location code to the Address table to build a heat map of the customer's location.
            
            Step 1: 
            Using the GeoLocUSCities0620 CSV file provided with this course, create a new translation called 
            trnCityGeoLoc that will return the Latitude and Longitude of the Customer's address into a new field 
            that you created in the CustomerLU Address table.
            
            Step 2: 
            Do the same for the International City Code.
            
            Step 3: 
            What is the Lat / Long value for LUI 1000 ? 
            
            What is the International City Code for LUI 2217 ?


#### [](/academy/Training_Level_1/05_LU_Enhancements/images/Solution.png)
#### ![](/academy/Training_Level_1/05_LU_Enhancements/images/Solution.png) 

**Solution Exercise 2 - Translations**

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
            

#### ![](/academy/Training_Level_1/05_LU_Enhancements/images/Exercise.png) 

**Exercise 3 - Globals**

Let's use Globals rather than values hardcoded into Java functions or SQL statements. 

Using the enrichment and decision functions from the previous exercises, please execute the following steps: 

            Step 1: 
            1. Create a new Global (Final) named OLDInvoices of the "date" type which will indicate which invoice records 
               can be deleted from the CustomerLU INVOICE table depending on the value of the ISSUED_DATE field of the
               INVOICES table.
            
            2. Using the Query Builder, execute an SQL query with a WHERE statement that selects all records older than 
               OLDINVOICES globals set to "2015-12-31"
             
            Step 2: 
            1. Create new Global value (Not Final) “RUN_POP” to be used by the “CasesUpdateMonitor” Decision function 
               you created. 
            2. In your function, use the Global value to decide whether to run the population or not, depending on 
               the number of records in the CASES tables of the CRM_DB database.
               How many records do you see in the LU Cases table of Instance ID 1472 if RUN_POP = 25000 ?
               How many records do you see in the LU Cases table of Instance ID 1472 if RUN_POP = 30000 ?
               
            3. Open the Fabric console and type in the following command: set_global global '*.RUN_POP=20000'.
            
            4. Run Instance ID 1472 again. Will the sync happen ? Why were you able to override the RUN_POP value ?
            
            Step 3: 
            1. Create an enrichment function to loop throw all invoices and delete all invoices that are older than 
               the barrier date.
            
            2. Set the globals OLDINVOICES value to "2015-12-31". Use this exact format so you can use the Java 
               "compareTO" function to compare dates. 
            
            Step 4: 
            1. Attach both functions to their relevant table and deploy and run the enrichment function on InstanceID=1000.
               How may invoices records are left for InstanceID=1000 ? 
            
            2. Using the Data Viewer on the InstanceID 1000 Invoice table, check that all invoice records have an issued 
               date that is later than 2015-12-31).
               
            Step 5:
            Using a new Global named "*InterCode_UK*", modify the PhoneFormat enrichment function so that all phone entries
            of Instance ID=1000 that do not already have an international code are set to "+44".



#### [](/academy/Training_Level_1/05_LU_Enhancements/images/Solution.png) 

**Solution Exercise 3 - Globals**

            Step 1:
            SQL Query with embedded reference to Globals:
                        Select INVOICE.* From BILLING_DB.INVOICE Where INVOICE.ISSUED_DATE < '@OLDINVOICES@'
                        
            Step 2:
            1. Decision function based on Globals:

            // This function will decide to synchronize an LUI if the number of cases is higher than an arbitrary hardcoded threshold
            Boolean syncInd = false;
            String count = db("CRM_DB").fetch("SELECT count(*) FROM CRM_DB.CASES").firstValue().toString();
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
            

            2. Answer: 5.
            
            3. The sync did not happen.

            4. Yes the sync happened (since there are more than 20000 entries in the CRM_ DB CASES table). 
               This time, the sync process did happen since you defined RUN_POP as a non-final GLOBAL and since you have overridden 
               its value for all the *.RUN_POP instances in the scope of this Fabric session.
            
            
Globals definition:    
![image](/academy/Training_Level_1/05_LU_Enhancements/images/GlobalExe3OverviewCapture.png)
            
                      
            
            Step 3:
           
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

            Step 4: Answer: 19 entries.
            
            Step 5: Exercise 1 of Enrichment Functions and the adjusted line below in the if statement

                if ((cellValue.matches("(.*)+(.*)") == false))
                {
                    formattedNumber = INTERCODE_UK + cellValue; 
                    //Makes use of the GLOBAL here w/o having to declare it
                    fabric().execute(SQLFormattedNumber,formattedNumber,cellValue);
                    //ending the if statement		
                }
                    
          
[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/05_LU_Enhancements/03_LU_Enhancements_Functions_flow.md)
[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/05_LU_Enhancements/05_LU_Enhancements_Quiz.md)

