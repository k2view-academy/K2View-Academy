# Broadway Summary Exercise

You have completed the Broadway Training where you have learned what is a Broadway flow, an Actor and a Stage. You acquired the knowledge and saw the examples of how to:

* Create a flow which includes several Stages with one or more Actors at each step. 
* Utilize the existing built-in Actors and extend them via the internal inheritance mechanism.
* Split the flow into branches under certain If-Else conditions.
* Re-use the same business logic by creating the inner flows.
* Manage the transactions and error handling process.
* Use a Broadway flow as a population of LU tables in Fabric.
* Invoke a Broadway flow using a SFTP listener, a job or a Fabric command.



Please proceed to the summary exercise to apply your knowledge.



### Summary Exercise Steps

**Step 1 - Create a Common Table and Populate it Using Broadway Flow**

Prior to starting this exercise, do the following:

a. Create a **emp_list_lookup.csv** file with the following values:

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

b. b. Create a **Local File System** Interface and locate the CSV file into the **Working Directory** of the Interface.

1. Create a common table EMPLOYEE_LOOKUP with the columns EMP_ID and EMP_FIRST_NAME. Save it and deploy the **References** LU.
2. Create a new **EmployeeLookupPopulation** flow.
3. Add a **FileRead** Actor and set its **interface** to the local file system interface which you created prior to this exercise and its path to **emp_list_lookup.csv**. 
4. Add a **CsvParser** Actor to the next Stage and connect it to the **FileRead** Actor.
5. Add a **DbLoad** Actor to the next Stage, connect its **interface** to **fabric** and table to **EMPLOYEE_LOOKUP**. 
6. Connect the fields using the **Iterate** link type.
7. Set the transaction at Stages 2 and 3. 
8. Save the flow and run it. Verify that the table is populated with the **EMPLOYEE_LOOKUP** data.

**Step 2 - Create an LU, Lookup Data & Add It Into a Population**

1. Create a new **SummaryExercise** LU:

   * DB Connection: CRM_DB.
   * Schema: Customer (root table), Address and Contract

2. Modify Customer LU table:

   * Add two new columns: FULL_NAME (Text) and IS_EMP (Integer).

3. In the **Customer.population** flow:

   * Modify the CUSTOMER **DbLoad** Actor by addition two new fields to the **fields** input arguments.

   * Add a **Concat** Actor to the empty Stage 1 and using it concatenate the FIRST_NAME and the LAST_NAME of the **Query** Actor's output. Connect the **Concat** Actor's output to the CUSTOMER **DbLoad** Actor's input.

   * Add a **DbCommand** Actor to Stage 1 and rename it to **Lookup**. Define the Actor's interface = **fabric**. Write the query:

     ~~~sql
     SELECT COUNT(*) AS IS_EMP FROM EMPLOYEE_LIST
     WHERE emp_name = ${emp_name}
     ~~~

     To check if the customer exists in the **EMPLOYEE_LIST** table.

   * Connect the  **Query** Actor's FIRST_NAME to the newly created **emp_name** input argument of the **Lookup** Actor.

   * Add a Stage 2 after Stage 1 and add a **JavaScript** Actor. Add a **res** input argument to it and connect it with the **result** output argument of the **Lookup** Actor. Write in the script:

     ~~~javascript
     res.IS_EMP;
     ~~~

     To return either 1 or 0. Connect the **JavaScript** Actor's output with IS_EMP input of the  CUSTOMER **DbLoad** Actor using the **First** link type.

4. Save all the changes, deploy the **SummaryExercise** LU and sync an instance. 

**Question 1:**

What should be changed in the flow in order to have the following population logic:

* If the customer is an employee (meaning the customer is found in the EMPLOYEE_LIST table), populate the EMP_ID into the CUSTOMER LU table. Otherwise - set it to 0.

**Question 2:**

What should be changed in the flow in order to have the following population logic:

* If the customer is not an employee (not in the EMPLOYEE_LIST table), throw an error. Otherwise continue and populate the EMP_ID into the CUSTOMER LU table.

**Step 3 - Read Data from File**

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

b. Locate the CSV file into the **Working Directory** of the **Local File System** Interface.

1. Create a new **lookupSubscType** flow.

2. Add a **FileRead** Actor, set its **interface** to the local file system interface which you created prior to this exercise and its path to **subs_type.csv.**

3. Add a **CsvParser** Actor to Stage 2 and connect it to the **FileRead** Actor.

4. Add a **JavScript** Actor to Stage 3 and connect it to the  **CsvParser** Actor using the **Iterate** link type.

   * Add **input1** and **input2** input arguments, and set input2 to **External** setting its external name to **input_subs_type**.

   * Write the following **script**:

     ~~~javascript
     if (input1.SUBSCRIBER_TYPE == flowArgs["input_subs_type"]) {
         contextLoop.stop();
         input1.SUBSCRIBER_DESC;
     }
     ~~~

5. Save the flow, deploy the **SummaryExercise** LU and run the flow with different input values. Validate the output.

**Question 3**:

* Does the JavaScript Actor iterate over the whole CSV file? Explain.

**Step 4 - Add an Inner Flow to the Population**

1. Open the **lookupSubscType** flow save it as an Actor.
2. Open the **Subscriber.population** flow and add the **lookupSubscType_Actor** to the empty Stage 1. 
3. Connect its input to the SUBSCRIBER_TYPE column of the **Query** Actor's output using the **Iterate** link type.
4. Connect its output to the SUBSCRIBER_TYPE column of the SUBSCRIBER **DbLoad** Actor's input.
5. Save all the changes, deploy the **SummaryExercise** LU and sync an instance. Check the values in the Subscriber LU table.

**Step 5 - Use Actor Inheritance in the Flow**

1. Open the **Contract.population** flow and add a **DateFormat** Actor to the empty Stage 1.  
2. Set the **format** to 'dd-MM-yy' and **tz** to GMT. 
3. Export the Actor setting the **format** to **final**. Call the new Actor **exerciseDateFormat**. 
4. Connect **exerciseDateFormat** Actor to the FROM_DATE column of the **Query** Actor's output using the **Iterate** link type.
5. Connect its output to the FROM_DATE column of the CONTRACT **DbLoad** Actor's input.
6. Add another **exerciseDateFormat** Actor and connect it to the TO_DATE columns.
7. Save all the changes, deploy the **SummaryExercise** LU and sync an instance. Check the values in the Contract LU table.

**Question 4**:

* Is it possible to modify the input/output arguments setting of the inherited Actor? If yes - how and does it impact other instances of the same Actor?

**Step 6 - Split the Stages and Write into File**

1. Split the Stage 1 and add a Stage condition to it using a **JavaScript** Actor with the following script:

   ~~~javascript
   input1 != "Roaming special"
   ~~~

   * Add a new input1 input argument and connect it to CONTRACT_DESCRIPTION column of the **Query** Actor's output using the **Iterate** link type.

2. Set the new Stage (Stage 2) as **else** and add a **JsonStringify** Actor to it.  Connect the Actor's input to the **Query** Actor's output using the **Iterate** link type.

3. Split the LU Table Stage too and add a **FileWrite** Actor to it. Define the Actor's **interface** and path input arguments and connect its **stream** input to the  **JsonStringify** Actor's output. 

4. Save all the changes, deploy the **SummaryExercise** LU and sync an instance. Check the values in the Contract LU table.

**Question 5**:

Explain the purpose of this split and what changes in the LU data.



[![Previous](/articles/images/Previous.png)](21_broadway_and_fabric_example.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](22a_broadway_summary_exercise_solution.md)
