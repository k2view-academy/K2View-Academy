
![](/academy/Training_Level_1/03_fabric_basic_LU/images/example.png)  

###   Example – Run the Data Viewer 

Let’s test an LU Instance and see the result. 

Go to the Project Tree, right click the **Customer** LU, and select **Deploy To debug**. Now you can open the Customer Data Viewer window and execute Instance ID 215:

1. Check that in the **Instance Tree** there is a **DB file** that has the **Current Date** and **Timestamp**.

2. Look at the **Tables** that have been populated under the **Instance DB Tree** and their data.

3. Validate the generated tables with the k2_ prefix. 

4. Execute the following query in the **Data Viewer**:
   

   `select cases.status,cases.activity_id, activity.ACTIVITY_DATE,activity.ACTIVITY_NOTE from cases, activity where cases.ACTIVITY_ID=activity.ACTIVITY_ID` 

   

5. Do the same using the **CRM_DB Query Builder** and add the **CUSTOMER_ID** number to the WHERE statement. Is the displayed data the same?

 

![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png)

### Exercise – Add New Tables to The LU and Validate Them

The LU you have just created using the Auto Discovery Wizard contains basic Customer information only. However, you also need to see their Subscription and Billing info.

Using the training materials and examples covered so far, add the **CRM_DB.SUBSCRIBER** table to the **Customer** LU. Redeploy the LU to the debug server, and run the Data Viewer for Instance ID 82.

1. `Question: How are you connecting the additional table?`

2. `Question: How many subscribers has Customer 82 got? What are their IDs?`

Add the BILLING_DB.BALANCE table to the **Customer** LU. Redeploy the LU to the debug server, and run again the Data Viewer for Instance ID 82.

3. `Question: How long did it take to populate the CUSTOMER table?`

4.  `Question: What is the first AVAILABLE_AMOUNT for Subscriber 209?`

5.   `Question: What is total AVAILABLE_AMOUNT for Subscriber 209?`

