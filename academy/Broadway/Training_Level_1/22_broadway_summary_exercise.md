# Broadway Summary Exercise

You have completed Broadway Training and learnt about a Broadway flow, an Actor and a Stage. 

You have also seen the following How To examples:

* Create a flow which includes several Stages with one or more Actors in each step. 
* Utilize built-in Actors and extend them via the internal inheritance mechanism.
* Split a flow into branches under specific If-Else conditions.
* Re-use the same business logic by creating inner flows.
* Manage Transactions and Error Handling processes.
* Use a Broadway flow as a population for LU tables in Fabric.
* Invoke a Broadway flow using an SFTP listener or a Fabric command.

### Summary Exercise Overview

Now let’s go to the following exercise which summarizes what you have learned.

In this exercise you will do the following:

* Create a reference table and populate it using a Broadway flow.
* Create a Logical Unit including 4 tables whereby all tables are populated by Broadway flows.
* Perform data manipulations using various Actors, for example:
  * Looking up the data in a reference table.
  * Concatenating the values of separate columns. 
  * Updating the date format.
  * Performing checks using JavaScript.
  * Reading from a file and writing into a file.
* Practice various Broadway features such as:
  * Transactions, to populate and save the data into the Reference table.
  * Error Handling, to fail a flow if the data is not found in the lookup.
  * Inner flow, to invoke one flow from another.
  * Setup SFTP listener, to invoke a flow that reads data from a file.
* Publish the data from Fabric to a Kafka topic.
* Run a Broadway flow as a batch process.
* Write into a target DB while handling DB errors.

To do this exercise, please download and open the [Demo Project](/articles/demo_project) in the Fabric Studio.

### Summary Exercise Steps

**Step 1 - Create a Reference Table and Populate it Using a Broadway Flow**

Before starting the exercise, do the following:

a. Create an **emp_list_lookup.csv** file with the following values:

<table>
<tbody>
<tr>
<td>EMP_ID</td>
<td>EMP_FIRST_NAME</td>
</tr>
<tr>
<td>1001</td>
<td>Dione</td>
</tr>
<tr>
<td>1002</td>
<td>Tu</td>
</tr>
<tr>
<td>1003</td>
<td>Sebastian</td>
</tr>
<tr>
<td>1004</td>
<td>Lois</td>
</tr>
<tr>
<td>1005</td>
<td>Serina</td>
</tr>
</tbody>
</table>
More entries can be added into the above CSV file, using the first names in the CUSTOMER table of the CRM_DB.

b. Create a **Local File System** interface and save the CSV file into the **Working Directory** of the interface.

1. Create a common EMPLOYEE_LIST table with EMP_ID and EMP_FIRST_NAME columns. Save it and deploy the **References** LU.
2. Create a new **EmployeeLookupPopulation** flow.
3. Add a **FileRead** Actor and set its **interface** to the local file system interface you created prior to this exercise. Set its path to **emp_list_lookup.csv**. 
4. Add a **CsvParser** Actor to the next Stage and connect it to the **FileRead** Actor.
5. Add a **DbLoad** Actor to the next Stage, connect its **interface** to **fabric** and its table to **EMPLOYEE_LIST**. 
6. Connect the fields using an **Iterate** link type.
7. Mark Stages 2 and 3 as **Transaction**. 
8. Save the flow and run it. Verify that the table is populated with the **EMPLOYEE_LIST** data.

**Question 1:**

What should be changed in the flow to support automatic file upload to the Reference table once it is copied to an interface?

**Step 2 - Create an LU and Make the Data Modifications in a Customer Population**

1. Create a new **SummaryExercise** LU:

   * DB Connection: CRM_DB.
   * Schema: Customer (root table), Address, Contract and Subscriber.

2. Modify the Customer LU table:

   * Add the FULL_NAME (Text) and IS_EMP (Integer) columns.

   ![image](images/exam_schema.PNG)

3. Perform data manipulations in the **Customer.population** flow as follows:

   * Modify the CUSTOMER **DbLoad** Actor by adding two new fields to the **fields** input arguments.

   * Add a **Concat** Actor to the empty Stage 1 and use it to concatenate the FIRST_NAME and the LAST_NAME of the **Query** Actor's output. Connect the **Concat** Actor's output to the FULL_NAME of the CUSTOMER **DbLoad** Actor's input. 

   * Add a **DbCommand** Actor to Stage 1 and rename it **Lookup**. Set the Actor's interface to **fabric**. Write the following query to check that the customer exists in the **EMPLOYEE_LIST** table.

     ~~~sql
     SELECT COUNT(*) AS IS_EMP FROM EMPLOYEE_LIST
     WHERE emp_first_name = ${emp_name}
     ~~~

   * Connect the  **Query** Actor's FIRST_NAME to the newly created **emp_name** input argument of the **Lookup** Actor.

   * Add Stage 2 after Stage 1 and add a **JavaScript** Actor. Add a **res** input argument to it and connect it to the **result** output argument of the **Lookup** Actor. Write the following script to return either 1 or 0. 

     ~~~ javascript
     res.IS_EMP;
     ~~~

   * Connect the **JavaScript** Actor's input with the **result** output argument of the **Lookup** (the **DbCommand** Actor) using the **First** link type because the **DbCommand** returns a result set of rows while we need to use the first row.

4. Save the changes, deploy the **SummaryExercise** LU and sync an instance. 

**Question 2a:**

What should be changed in the flow to get the following population logic:

* If the customer is an employee, populate the EMP_ID output of the **Lookup** Actor into the IS_EMP column of the CUSTOMER **DbLoad** Actor. Otherwise, set it to 0.

**Question 2b:**

What should be changed in the flow to get the following population logic:

* If the customer is not an employee, throw an error. Otherwise continue and populate the EMP_ID into the IS_EMP column of the CUSTOMER **DbLoad** Actor.

**Step 3 - Create a Flow that Reads Data from the File and Searches for a Value**

Prior to starting this exercise, do the following:

a. Create a **subs_type.csv** file with the following values:

<table>
<tbody>
<tr>
<td>SUBSCRIBER_TYPE</td>
<td>SUBSCRIBER_DESC</td>
</tr>
<tr>
<td>1</td>
<td>Prepaid</td>
</tr>
<tr>
<td>2</td>
<td>Residential Simple</td>
</tr>
<tr>
<td>3</td>
<td>Residential Plus</td>
</tr>
<tr>
<td>4</td>
<td>SOHO</td>
</tr>
<tr>
<td>5</td>
<td>SOHO Plus</td>
</tr>
</tbody>
</table>

b. Save the CSV file into the **Working Directory** of the **Local File System** Interface.

1. Create a new **lookupSubscType** flow.

2. Add a **FileRead** Actor, set its **interface** to the local file system interface you created prior to this exercise. Set its path to **subs_type.csv.**

3. Add a **CsvParser** Actor to Stage 2 and connect it to the **FileRead** Actor.

4. Add a **JavaScript** Actor to Stage 3 and connect it to the **CsvParser** Actor's output as follows:

   * Add **input1** and **input2** input arguments.
   * Connect **input1** to the **CsvParser** Actor using an **Iterate** link.
   * Set **input2** to **External** and update its external name to **input_subs_type**.
   * Set the Actor's output **result** to **External** as well.
   * Write the following **script**:
~~~javascript
if (input1.SUBSCRIBER_TYPE == flowArgs["input_subs_type"]) {
    contextLoop.stop();
    input1.SUBSCRIBER_DESC;
}
~~~

5. Save the flow, deploy the **SummaryExercise** LU and run the flow with different input values. Validate the output.

**Question 3**:

Does the **JavaScript** Actor iterate over the entire CSV file? Explain.

**Step 4 - Create an Inner Flow and Add it to the Subscriber Population**

1. Save the **lookupSubscType** flow as an Actor.
2. Open the **Subscriber.population** flow and add the **lookupSubscType_Actor** to the empty Stage 1. 
3. Connect its input to the SUBSCRIBER_TYPE column of the **Query** Actor's output using an **Iterate** link type.
4. Connect its output to the SUBSCRIBER_TYPE column of the SUBSCRIBER **DbLoad** Actor's input.
5. Save the changes, deploy the **SummaryExercise** LU and sync an instance. Check the values in the Subscriber LU table.

**Question 4:**

Describe the impact of adding an inner flow on the target population.

What happens if the value is not found in the CSV file?

**Step 5 - Use Actor Inheritance in the Contract Population**

1. Open the **Contract.population** flow and add a **DateFormat** Actor to the empty Stage 1.  
2. Set the **format** to 'dd-MM-yy' and the **tz** to GMT. 
3. Export the Actor by setting the name to **exerciseDateFormat** and the **format** to **final**. 
4. Connect the **exerciseDateFormat** Actor to the FROM_DATE column of the **Query** Actor's output using an **Iterate** link type.
5. Connect its output to the FROM_DATE column of the CONTRACT **DbLoad** Actor's input.
6. Add another **exerciseDateFormat** Actor and connect it to the TO_DATE columns.
7. Save the changes, deploy the **SummaryExercise** LU and sync an instance. Check the values in the Contract LU table.

**Question 5**:

Can the inherited Actor's input / output argument settings be modified? If yes, how? Does this impact other instances of the same Actor?

**Step 6 - Split the Stages, Set the Condition and Write Data into the File**

1. In the **Contract.population** flow split Stage 1 and add a Stage condition to it using a **JavaScript** Actor with the following script:

   ~~~javascript
   input1 != "Roaming special"
   ~~~

2. Add an **input1** input argument to the  **JavaScript** Actor and connect it to the CONTRACT_DESCRIPTION column of the **Query** Actor's output using an **Iterate** link type.

3. Set the new split Stage (Stage 2) as **else** and add a **JsonStringify** Actor to it. Connect the Actor's input to the **Query** Actor's output using an **Iterate** link type.

4. Split the LU Table Stage and add a **FileWrite** Actor to the new split Stage, so that it is in the **else** branch's condition. Define the Actor's **interface** and **path** input arguments and connect its **stream** input to the **JsonStringify** Actor's output. 

5. Save the changes, deploy the **SummaryExercise** LU and sync an instance. Check the values in the Contract LU table.

**Question 6**:

Explain the purpose of this split and what changes in the LU data.

**Step 7 - Publish the Customer Data to Kafka Topic**

Prior to starting this exercise:

* Follow the installation guidelines to [install the Zookeeper and Kafka locally](/articles/demo_project/Fabric_Demo_Project/01_local_installation_of_zookeper_kafka_and_ES.md). 

* Create a **Kafka** interface to connect to the Apache Kafka server and set the **Data Type** to **JSON**.

1. Create a new **publishCustomer** flow.

2. Add a **FabricGet** Actor to Stage 1 and do the following:

   * Set the **luType** input argument to **SummaryExercise**.
   * Change the **iid** to **External** population type.

3. Add a **DbCommand** Actor to Stage 2 and do the following:

   * Set its **interface** to **fabric** and populate its **sql** input argument with the following query:

   ~~~sql
   SELECT * FROM CUSTOMER
   ~~~

4. Add a **Publish** Actor to Stage 3 and connect its **message** to the **result** of the **DbCommand**. Set the interface to the **Kafka** interface you defined earlier.

5. Save the changes, deploy the **SummaryExercise** LU and run the flow.

**Question 7a:**

What should be changed in order to publish the Customer's data to the Kafka topic as part of the sync process?

**Question 7b:**

How do you publish the data of a group of customers to the Kafka topic?

**Step 8 - Write into the Target DB and Handle a DB Error**

1. Create a new **Contract.populationCopy** flow that retrieves the data from the CONTRACT LU table of the **fabric** interface and loads the data to the CONTRACT_COPY table of the  **CRM_DB** interface.

2. In the LU Schema, connect a new population to its parent CUSTOMER table using the CUSTOMER_ID and set the population execution order to 4.

3. In the  **Contract.populationCopy** flow, add an **ErrorHandler** Actor to the LU Table Stage and set its input arguments as follows:

   * Set **Sql Error** to the **Unique constraint** value.
   * Set **Suppress** indicator to true.
   
4. Add a new Count Stage after the LU Table Stage and add an **Aggregate** Actor to it. Connect its **input** argument to the **affected** output argument of the **DbLoad** Actor.

5. Add a **Count** Actor to the same Stage.

6. Add a **Logger** Actor to the Post Load Stage and do the following:

   * Write the **message**: *There are ${cnt} contracts. CONTRACT_COPY was populated with ${sum} entries*.
   * Connect the **Logger** Actor's **cnt** input to the output of the **Count** Actor.
   * Connect the **Logger** Actor's **sum** input to the **sum** output of the **Aggregate** Actor.

7. Save the changes, deploy the **SummaryExercise** LU and sync an instance. Check the log.

**Question 8:**

What is the purpose of the Error Handler? What will happen if there is no Error Handler in this flow?



[![Previous](/articles/images/Previous.png)](21_broadway_and_fabric_example.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](22a_broadway_summary_exercise_solution.md)
