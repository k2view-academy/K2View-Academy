# Customer Notification Task

1. Create a new Stage called **Customer Notification**. 

![image](../images/01_02_03_DSAR_Customer_Notification_Stage.png)

2. Add a new Task under this Stage, called **E-mail Customer**.

![image](../images/01_02_03_DSAR_Customer_Notification_Task.png)

3. Click the **Operations** tab and select the **SendDPMEmailTo** operation. This automatic operation will send a notification to the customer once the Request is successfully completed, and the PDF can be delivered to the customer. Configure the **Inputs** parameters as shown below. 

   
   
   * **File Name** - YourData
   * **PDF** - link to the PDF that was created in the previous steps.
   * **E-mail type** - The value we use here is DSAR_CUST_FOUND, which is the e-mail layout we created for this task (personalized in your real-life implementation).
   * **Customer ID** - is obtained from the task Get Customer ID from the first stage
   * **Subject** - The E-mail subject - set it to be **Your K2View Data Subject Access Request** 
   * **Recipients** - configured to be obtained from the customer when they submit the request. Set the label to be: **What is your email address?**. We use the same text as used in the task of sending confirmation mail to the customer, so that it would be asked only once as input. 


![image](../images/01_02_03_DSAR_Customer_Notification_Operations.png)

4. Click ![image](../images/ICON_Save.png) to save the new Task.

Your DSAR Flow is now complete. The final result should look similar to the following image.

![image](../images/01_02_03_DSAR_Final_Flow.png)



**Note:** If you have any doubt about the configuration described above, you can also review the DSAR Flow implementation that comes built-in with your DPM installation (with the exception of the review tasks which are not part of the built-in automated flow).

![image](../images/01_02_03_DSAR_Built_In_Flow.png)



[![Previous](../images/Previous.png)](01_02_03_DSAR_Tasks_Data_Collection.md)[<img align="right" width="60" height="54" src="../images/Next.png">](01_02_03_DSAR_Tasks_Customer_Notification.md)
