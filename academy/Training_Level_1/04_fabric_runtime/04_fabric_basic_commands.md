# Fabric Basic Commands

![](/academy/Training_Level_1/04_fabric_runtime/images/fabric_execute_04.png)

You have just deployed your project and  the objects along with, you were able to list them,in particular the CustomerLU you have built. Now that you know how to use the Fabric utilities and are able to login into Fabric, let's review some basic commands that will assist in retrieving and querying  the data

[Fabric Commands](/articles/02_fabric_architecture/04_fabric_commands.md)



### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/example.png)Example- Fabric Commands

Let’s test an LU Instance and see the result:

1. Open your Fabric console on  windows or login into Fabric on the Linux server

2. Run **get CustomerLU.215;

3. Execute **select * from customer**;

4. You should get the following results:

   `fabric>get CustomerLU.215;`
   `|luName    |iid|version      |`
   `+----------+---+-------------+`
   `|CustomerLU|215|1592207994983|`

   `(1 rows)`



![](/academy/Training_Level_1/03_fabric_basic_LU/images/information.png) To learn more about Fabric Configuration Files, refer to: 

[Fabric Main Configuration Files](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md)



### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png)Exercise – Fabric Commands

Using the training materials and examples covered so far:

1. `Question: How many tables are in the CustomerLU?`
2. `Question: What are the columns that are not nullables in ACTIVITY table?`
3. `Question: How many subscribers has Customer 82? What are their IDs?`
4. `Question: What is the status of CRM_DB interface?`
5. `Advanced Question: Update the CUSTOMER.FIRST_NAME of Instance 215 to your own name . Perform a get CustomerLU.215 again , what will be the CUSTOMER.FIRST_NAME? How will you change it to source value?`

### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png)Solution - Fabric Commands

1. `Answer: 12 tables including the _K2 tables`

   `fabric>describe schema CustomerLU;`
   `|schema    |table                 |`
   `+----------+----------------------+`
   `|CustomerLU|ACTIVITY              |`
   `|CustomerLU|ADDRESS               |`
   `|CustomerLU|BALANCE               |`
   `|CustomerLU|CASES                 |`
   `|CustomerLU|CASE_NOTE             |`
   `|CustomerLU|CONTRACT              |`
   `|CustomerLU|CONTRACT_OFFER_MAPPING|`
   `|CustomerLU|CUSTOMER              |`
   `|CustomerLU|SUBSCRIBER            |`
   `|CustomerLU|_k2_main_info         |
   `|CustomerLU|_k2_objects_info      |`
   `|CustomerLU|_k2_transactions_info |`

   `(12 rows)`

2. `Answer: ACTIVITY_ID`

3. `Answer: 2 Subscriber, 209 & 210`

4. `Answer: Active (true)`

   `fabric>test_connection DbInterface='CRM_DB';`
   `|interface|environment|passed|error_message|`
   `+---------+-----------+------+-------------+`
   `|CRM_DB   |_dev       |true  |             |`

   `(1 rows)`

5. `Answer: My First Name, set syc force will be bring the data from source`

 [![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/04_fabric_runtime/03_fabric_deployment.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/04_fabric_runtime/04_fabric_basic_commands.md)

 

