![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png) 

### Reference Tables - Exercise 1

In this exercise you will create a Reference Table and add it to the Customer LU Schema.

The purpose of this exercise is to provide a reference table for new cases of Corona and updates numbers 

For this purpose, you will find a CSV file in the repository published by the health authorities and that provide the following information: States, New cases, New cases per 100K etc ... 

We wish to create a Reference table based upon this table, so the Network Operator can send alerts to users roaming in specific states and alert them on the specific table's fields value changes

We have added a table called 'USA_CoV19_cases_Oct2020' to the Billing_DB so that the reference table can be populated easily. In the Broadway course we will show you how to parse the data directly from the CSV.

- Step 1

  - Create a reference table based on the table mentioned below

    - add "ref_" as a prefix to the name of the table 

  - Add the Reference Table to the Customer LU schema

  - Deploy both reference table and LU

    

- Step 2

  - Using Fabric console, and a SELECT JOIN statement, retrieve the STATE, the number of weekly new Cov19 cases  and the rate of new cases per 100K, in the State where lives Customer with customer_id=1472
  
    

- Step 3

  - We would like to add latest cov19 for Guam to the table as many customers do frequently fly there.
  - Using the transaction mechanism described in the course, execute an SQL insert query on the table with the following data:
    - STATE = GUAM
    - TOTALCASES = 400
    - CASES_LAST_7 = 14
    - CASES_PER_100K = 800
    - FATALITIES_LAST_7 = 1
    - FATALITIES_PER_100K = 100    

  

  fabric>set COMMON_LOCAL_TRX=true;
  (1 row affected)
  fabric>begin;
  (0 rows affected)
  fabric>insert into ref_USA_CoV19_cases_Oct2020(STATE,TOTALCASES,CASES_LAST_7,CASES_PER_100K,FATALITIES_LAST_7,FATALITIES_PER_100K) VALUES ('GUAM','400','14','800','1','100');

  

  

  - Verify whether the new entry was added.

  |TX   |756004    |32085       |2634          |531              |55                 |
  |UT   |75157     |6627        |2378          |26               |14                 |
  |VA   |150803    |5395        |1770          |126              |38                 |
  |VT   |1768      |29          |282           |0                |9                  |
  |WA   |88810     |3584        |1178          |43               |28                 |
  |WI   |134948    |17619       |2321          |79               |23                 |
  |WV   |16307     |1354        |903           |25               |19                 |
  |WY   |6214      |794         |1076          |3                |9                  |
  |USA  |7310625   |301539      |2209          |4940             |63                 |
  |GUAM |400       |14          |800           |1                |100                |

  - Which other State has the closest number of cases per 100K to GUAM

    





[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/07_jobs_and_batch_services/02_jobs_and_batches_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/07_jobs_and_batch_services/04_jobs_and_batches_flow_solutions.md)

------





------

