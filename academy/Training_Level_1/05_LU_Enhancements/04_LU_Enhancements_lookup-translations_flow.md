#   LU Enhancement Tables & Globals
                                               

 

### Using tables for data manipulations

Now we have seen how functions can be attached to LU tables to modify data, let's see how we can use tables (and functions) to enrich data.
In this section we will address the following types of tables:

[Lookup tables](/articles/07_table_population/11_lookup_tables.md)

and translations:

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



#### Exercise 1 - Lookup tables

Using the CustomerLU schema, let's look at the Address population map. We wish to add the first name and last name of the customer to the address LU table so the full details of the customers are readily available.

Question 1 : Which table should you consider and why ?

Question 2:  Which function would you use to make sure that the first name and last name are concatenated into one field in the Address table ?

Question 3: In the properties panel, what does the configuration variable "Lookup Not Found" stands for ? What are the options available ? In our example what will be the most likely value of this configuration variable

Question 4: Save the lookup table, deploy and run instance 2472. Which name features in the address table ?


#### Solution Exercise 1 - Lookup table

Question 1: 
Customer table from customerLU. It is much more efficient to use the data already populated into the LU table rather that use the CRM table

Question 2: 
k2_concat5 function will allow you to concatenate up to 5 strings and also define a delimiter !. In this particular example we need only 2 strings and we have set the delimiter to ".". (A next stage of the exercise could be to add a UID (to avoid duplicate names), and an internet address string such as "@yourcompany.com" and therefore provide an email address to your customers)

Question 3:
This sets up the systems' behavior if a specific value does not exist in the lookup table. The different options are: "Reject record", "Reject Instance", "Continue" and "Report and Continue"
In our example, we are using a table that is very likely to have all the records (first name & last name are mandatory fields) therefore it could be set to "Continue".

Question 4: 
Rana Bradshaw


#### Exercise 2 - Translations

We wish to associate a unique geo-location code to the address table, to build a heat map of customers location.

Question 1: Using the worldcities.csv file provided with the course, create a new translation called trnCityGeoLoc that will return the Latitude and Longitude of the Customer's address into a new field that you will have previously created in the CustomerLU Address table

Question 2: Do the same with the international city code.

Question 3: What is the Lat/Long value for Instance LUI: 1000, What is the city international code for LUI: 2217


#### Solution Exercise 2 - Translations

Question 1 & 2:

![image](/academy/Training_Level_1/05_LU_Enhancements/images/TransExe2-OverviewCapture%20(1).PNG=100x40)  

![image](/academy/Training_Level_1/05_LU_Enhancements/images/TransExe2-OverviewCapture%20(2).PNG=100x40) 

![image](/academy/Training_Level_1/05_LU_Enhancements/images/TransExe2-OverviewCapture%20(3).PNG=100x40) 



Question 3:

InstanceID 1000: 39.7771::-86.1458

InstanceID 2217: 1840034016

[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/05_LU_Enhancements/03_LU_Enhancements_Functions_flow.md)

 

 

 

 

 

------

