![](/academy/Training_Level_1/05_LU_Enhancements/images/EnhancementTablesState.PNG) 

#   LU Enhancement Tables & Globals


### Using tables for data manipulations

Now we have seen how functions can be attached to LU tables to modify data, let's see how we can use tables (and functions) 
to enrich data.
In this section we will address the following types of tables:

[Lookup tables](/articles/07_table_population/11_lookup_tables.md)

[Translation tables](/articles/09_translations/01_translations_overview_and_use_cases.md)

[Creating a new translation table](/articles/09_translations/02_creating_a_new_translation_in_fabric.md) 

[Data population in a translation](/articles/09_translations/03_data_population_in_a_translation.md)

[Using Translations in Fabric](/articles/09_translations/04_using_translations_in_fabric.md)

[Translations code example](/articles/09_translations/05_translations_code_examples.md)



### Globals

[Globals Overview](/articles/08_globals/01_globals_overview.md)

[Globals Use Cases](/articles/08_globals/02_globals_use_cases.md)

[Globals in Fabric Commands](/articles/08_globals/03_set_globals.md)

[Globals code example](/articles/08_globals/04_globals_code_examples.md)





> #### Exercise 1 - Lookup tables
>
> Using the CustomerLU schema, let's look at the Address population map. We wish to add the first name and last name of the 
> customer to the address LU table so the full details of the customers are readily available.
>
> ##### Step 1: 
> Which table should you consider and why ?
> ##### Step 2: 
> Which function would you use to make sure that the first name and last name are concatenated into one field in 
> the Address table ?
> ##### Step 3: 
> In the properties panel, what does the configuration variable "Lookup Not Found" stands for ? What are the options 
> available ? Using our example what will be the most likely value of this configuration variable
> ##### Step 4: 
> Save the lookup table, deploy and run instance 2472. Which name features in the address table ?




>#### Solution Exercise 1 - Lookup table
>
>##### Step 1: 
>The table from customerLU. It is much more efficient to use the data already populated into the LU table rather that use
>the CRM table
>##### Step 2: 
>k2_concat5 function will allow you to concatenate up to 5 strings and also define a delimiter !. In this particular example we 
>need only 2 strings and we have set the delimiter to ".". (A next stage of the exercise could be to add a UID (to avoid duplicate
>names), and an internet address string such as "@yourcompany.com" and therefore provide an email address to your customers)
>##### Step 3:
>This sets up the systems' behaviour if a specific value does not exist in the lookup table. The different options are: "Reject record", 
>"Reject Instance", "Continue" and "Report and Continue"
>In our example, we are using a table that is very likely to have all the records (first name & last name are mandatory fields) 
>therefore it should be set to "Continue".
>##### Step 4: 
>Rana Bradshaw




>#### Exercise 2 - Translations
>We wish to associate a unique geo-location code to the address table, to build a heat map of customers location.
>##### Step 1: 
>Using the GeoLocUSCities0620 CSV file provided with this course, create a new translation called trnCityGeoLoc that will 
>return the Latitude and Longitude of the Customer's address into a new field that you will have previously created in the 
>CustomerLU Address table
>##### Step 2: 
>Do the same with the international city code.
>##### Step 3: 
>What is the Lat/Long value for Instance LUI: 1000, What is the city international code for LUI: 2217



>#### Solution Exercise 2 - Translations
>
>##### Step 1 & 2:
>
>Translation table schema:
>![image](/academy/Training_Level_1/05_LU_Enhancements/images/TransExe2-OverviewCapture%20(3).PNG) 
>
>
>Translation table data:
>![image](/academy/Training_Level_1/05_LU_Enhancements/images/TransExe2-OverviewCapture%20(2).PNG) 
>
>
>Address population diagram featuring translation table and LAT/LONG concatenation function:
>![image](/academy/Training_Level_1/05_LU_Enhancements/images/TransExe2-OverviewCapture%20(1).PNG)  
>
>
>##### Step 3:
>InstanceID 1000: 39.7771::-86.1458
>InstanceID 2217: 1840034016


#### Exercise 3 - Globals
##### Step 1: 
Create a new Global (Final) named OLDInvoices of the type "date" which will indicate which invoice records can be 
deleted from the CustomerLU INVOICE table depending on the value of ISSUED_DATE field.
##### Step 2: 
Create new Global value (Not Final) “RUN_POPULATION” to be used by the Decision function we created 
“CasesUpdateMonitor”. In your function use the Global value to decide whether to run the population or not, depending on the number 
of records in the CASES tables of the CRM_DB database. 
##### Step 3: 
Create Enrichment function to loop throw all Invoices and delete all invoices that are older than the barrier date.
Set the globals OLDINVOICES value to "2015-12-31". (Use this exact format so you can use the java "compareTO" function to compare dates. 
##### Step 4: 
Attach both functions to their relevant table.
Deploy and run the enrichment function on InstanceID=1000. How may invoices records are left for InstanceID=1000? (Using the Data 
viewer on the invoice table of InstanceID 1000, check that all invoices records have an issued date that is later than 2015-12-31)
Deploy and run the decision function on InstanceID=1472. Are you witnessing the same behavior as in the decision function exercise ? 
##### Step 5:
Using a new Global called "*InterCode_UK*", modify the PhoneFormat enrichment function so all the phone entries of Instance ID=1000
(that do not have an international code already) to a new international code set to "+44"




#### Solution Exercise 3 - Globals
##### Step 1 & 2:
Globals definition:    
![image](/academy/Training_Level_1/05_LU_Enhancements/images/GlobalExe3OverviewCapture.png)

##### Step 3:      
      
```java
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
###### Step 4: Answer: 19 entries/Yes
###### Step 5: c.f. Exercise 1 of Enrichment Functions and the adjusted line below in the if statement
            
                if ((cellValue.matches("(.*)+(.*)") == false))
                {
                    //reportUserMessage(cellValue);
                    ** formattedNumber = INTERCODE_UK + cellValue; //Makes use of the GLOBAL here w/o having to declare it **
                    fabric().execute(SQLFormattedNumber,formattedNumber,cellValue);
            // ending the if statement		
                }
        }


[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/05_LU_Enhancements/03_LU_Enhancements_Functions_flow.md)
[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/05_LU_Enhancements/05_LU_Enhancements_Quiz.md)

