# ![](/academy/images/Exercise.png) Exercise - Broadway Flow as a Population

In this exercise you will do the following:

* Create a new population for CASES table in the Customer LU based on a Broadway flow.
* Filter the retrieved cases by the status = Open.
* Load the data into Fabric.



##### Exercise Steps:

1. Go to the **Project Tree** > **Logical Units** > **Customer** and delete the CASES table with its population from both the tables list and the LU Schema of the Customer LU.

2. Drag the CASES table from the **DB Objects tab** of the LU Schema window to the LU Schema main area and click on the **Table Based Broadway Flow** in the context menu. 

   * The CASES table and the population flow are created.

3. Connect the CASES  table to its parent ACTIVITY table in the LU Schema by ACTIVITY_ID.

4. Connect the CASES  table to the CASE_NOTES table by CASE_ID and save the LU Schema.

5. Save the population and deploy the Customer LU.

   To learn how to deploy the Logical Unit, refer to [Deploy from Fabric Studio](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md).

   

Your flow is ready now! Run the Data viewer to sync a customer instance and verify the population results.

   ![cases population](images/12_cases_table_population_1.PNG)

6. Now modify the flow by adding a filter. To do so, open the **population.flow** of the CASES table. 

7. In the CASES population flow, click the **Query** Actor to edit the **sql** input argument. 

   * To filter the cases by their status, edit the SQL statement to be:

     ~~~sql
     Select * From CASES where STATUS = ${case_sts}
     ~~~

   * Note that a new input argument **case_sts** is added to the **Query** Actor.

8. Add a **Const** Actor to the **Input** Stage and populate its value as **Open**. Connect the output of the  **Const** Actor to the **case_sts** input argument of the **Query** Actor.

9. Save the population and deploy the Customer LU again before running the sync.

   ![cases population](images/12_cases_table_population_2.PNG)



**Questions**:

<ul>
<pre><code>
1. Which values are populated in the output arguments of the <strong>PopulationArgs</strong> Actor?
2. Which SQL statement is executed on the DB Interface by the <strong>Query</strong> Actor?
3. If you need to edit the data before loading it to the target DB, how can you do it?
</code></pre>
</ul>



**Answers:**

<ul>
<pre><code>
1. The <strong>iid</strong> holds the Instance ID of the execution, in the above example - customer ID. 
The <strong>parent_rows</strong> is the iterator of the parent rows, in the example - the list of activity IDs under the given customer.
2. The SQL statement is: SELECT * FROM CASES WHERE ACTIVITY_ID IN (...) AND STATUS = ...
3. Add an Actor to <strong>Stage 1</strong> in the template flow to perform the required data manipulations.

</code></pre>
</ul>

[![Previous](/articles/images/Previous.png)](11_integration_with_fabric_studio.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](xxx.md)

