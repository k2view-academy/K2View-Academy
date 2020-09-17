# Fabric Basic Commands

### ![](/academy/Training_Level_1/04_fabric_runtime/images/fabric_execute_04.png)

You have just deployed your project and  its objects (including the Customer you built) and have listed them. Now that you know how to use Fabric tools and can log into Fabric, let's review some basic commands that will help you to retrieve and query data.
 
Please refer to [Fabric Commands](/articles/02_fabric_architecture/04_fabric_commands.md)



![](/academy/Training_Level_1/03_fabric_basic_LU/images/example.png) 

### Example- Fabric Commands

Let’s test an LU Instance and see the results:

1. Open the **Fabric console** in **Windows** or log in to **Fabric** on the **Linux server**.

2. Run **get Customer.215**;

3. Execute **select * from customer**. You should get the following results:

   `fabric>select * from customer;`
   
   
   |CUSTOMER_ID|SSN       |FIRST_NAME|LAST_NAME|
   
   +-----------+----------+----------+---------+
  
   |215        |5455651083|Talieee   |Sears    |
   
   
   
   

![](/academy/Training_Level_1/03_fabric_basic_LU/images/information.png) To learn more about Fabric Configuration Files, refer to: [Fabric Main Configuration Files](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md).



![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png) 

### Exercise – Fabric Commands

Using the training materials and examples covered so far:

1. `Question: How many tables does the Customer have?`

2. `Question: Which columns are not nullables in the ACTIVITY table?`

3. `Question: How many subscribers does Customer 82 have? What are their IDs?`

4. `Question: What is the status of the CRM_DB interface?`

5. `Advanced Question: Update the CRM_DB source database: Update the CUSTOMER.FIRST_NAME of Instance 215 to your own name. Run thebset sync force; command and then run the get Customer.215; command again. What is the CUSTOMER.FIRST_NAME? How will you change it to become the source value?`






 [![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/04_fabric_runtime/03_fabric_deployment.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/04_fabric_runtime/05_fabric_basic_commands_solutions.md)

 

------

