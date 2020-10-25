![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png) 

# Reference Tables - Exercise 1

In this exercise you will create a Reference Table and attach it to our Customer LU Schema.

The purpose of this exercise is to provide a reference table for new cases of Cov19 and updates numbers and use Fabric to support the international crisis at play.

You will find a table in the BILLING_DB which data is extracted from the health authorities and that provides the following information: States, New cases, New cases per 100K etc ... 

We wish to create a Reference table based upon this table, so the Network Operator can send alerts to users roaming in specific states and alert them on the specific table's fields value changes.

We have added a table called 'USA_CoV19_cases_Oct2020' to the BILLING_DB so that the reference table can be populated easily.

#### Step 1

  - Create a reference table based on the table mentioned above

    - add "ref_" as a prefix to the name of the table 

  - Attach the Reference Table to the Customer LU schema

  - Deploy both reference table and LU
  
  - Using the REF_STATUS command, make sure that the reference table has been synchronized into Fabric when it was deployed. 

    

#### Step 2

Using Fabric console, and a ```SELECT JOIN``` statement between the LU ADDRESS table and the Reference Table, retrieve:
  - the STATE, 
  - the number of weekly new Cov19 cases,
  - and the rate of new cases per 100K,
for the State in which Customer 1472 lives.

  
    

#### Step 3

  - We would like to add latest cov19 for a new state (Guam) to the table, as many customers do frequently fly there.
  - Using the [transaction mechanism](/articles/23_fabric_transactions/02_fabric_transactions.md#update-reference-tables) described in the course, execute an ```SQL insert query``` on the table with the following data:
    - STATE = GUAM
    - TOTALCASES = 400
    - CASES_LAST_7 = 14
    - CASES_PER_100K = 800
    - FATALITIES_LAST_7 = 1
    - FATALITIES_PER_100K = 100    


  - Verify whether the new entry was added.

 
  - Which other State has a *number of cases per 100K* higher than the one for GUAM, by 100.

    
    
#### Step 4
  We would like to add a new field named **STATE_STATUS** to the *Subscriber* table of Customer LU.
  To do so:
  - Write an [enrichment function](/articles/10_enrichment_function/03_create_edit_enrichment_function.md) that returns the status of the State (from the address table) in which a customer lives by comparing it to the Reference table field describing the number of CASES_PER_100K.
  - Consider the following values and rules:
      ```
      Less than 1500 cases per 100K -> Status is set to Green
      Between 1500 and 2700 cases -> Status is set to Orange
      Over 2700 cases -> Status is set to Red
      ```  
  - Attach the enrichment function to Customer LU Schema.
  - Which Status color is set for Customer ID : 1000


[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/08_reference(commonDB)_tables/02_commonDB_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/08_reference(commonDB)_tables/04_commonDB_solutions.md)

------

