
![](/academy/Training_Level_1/05_LU_Enhancements/images/Exercise.png) 

#### **Exercise 1 - Lookup tables**

  Using the Customer LU schema, let's look at the Address population map. 

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


#### **Exercise 2 - Translations**

Associate a unique geo-location code to the Address table to build a heat map of the customer's location.
            
            Step 1: 
            Using the GeoLocUSCities0620 CSV file provided with this course, create a new translation called 
            trnCityGeoLoc that will return the Latitude and Longitude of the Customer's address into a new field 
            that you created in the Customer Address table.
            
            Step 2: 
            Do the same for the International City Code.
            
            Step 3: 
            What is the Lat / Long value for LUI 1000 ? 
            
            What is the International City Code for LUI 2217 ?


​            
​            
#### **Exercise 3 - Globals**

Let's use Globals rather than values hardcoded into Java functions or SQL statements. 

Using the enrichment and decision functions from the previous exercises, please execute the following steps: 

            Step 1: 
            1. Create a new Global (Final) named OLDInvoices of the "date" type which will indicate which invoice records 
               can be deleted from the Customer INVOICE table depending on the value of the ISSUED_DATE field of the
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






------
