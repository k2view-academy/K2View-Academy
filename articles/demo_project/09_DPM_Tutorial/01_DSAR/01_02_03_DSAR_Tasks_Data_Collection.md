# Data Collection Tasks

In this tutorial, additional Stages and Tasks are created in order to complete the configuration of our DSAR Tutorial Flow. 

The sample Stages we will create include: 

- **Data Collection** - This Stage is an automated task that collects customer data from the different corporate systems. In the sample implementation of this tutorial, the information is collected from Odoo and Open Source Billing systems.
- **File Generation** - Once the information is collected, an automated activity creates a PDF that includes the customer data. 
- **Review and Approval**  - Review of the resulting PDF. In our example, this review should be done by both the Legal Data Steward team and the Case Owner. 
- **Customer Notification** - After the PDF is both generated and reviewed, the Request fulfilment is completed. The customer can then be notified and delivered the results. 

### Data Collection

1. Create a new Stage called **Data Collection**.

<img src="../images/01_02_03_DSAR_Data_Collection_Stage.png" width="50%" height="30%">

2. Add a new Task under this Stage, called **Collect Customer Data**.

![image](../images/01_02_03_DSAR_Data_Collection_Task.png)

3. Click the **Operations** tab and select the **CollectCaseCustomerData** operation. This automatic operation retrieves the customer data from any integrated system. Populate the information for the customer ID field as presented in the image below.

![image](../images/01_02_03_DSAR_Data_Collection_operations.png)

4. Click ![image](../images/ICON_Save.png) to save the new Task.

### File Generation

1. Add a new Stage called **File Creation**. 

<img src="../images/01_02_03_DSAR_File_Generation_Stage" width="50%" height="30%">

2. Add a new Task under this Stage called **Generate PDF File**.

![image](../images/01_02_03_DSAR_File_Generation_Task1.png)

3. Click the **Operations** tab and select the **GenerateCustomerPDF** operation. This automatic operation formats the data that was collected about the customer in a PDF. 

![image](../images/01_02_03_DSAR_File_Generation_Operations.png)

4. Click ![image](../images/ICON_Save.png) to save the new Task.

[![Previous](../images/Previous.png)](01_02_02_DSAR_Tasks_First_Stage.md)[<img align="right" width="60" height="54" src="../images/Next.png">](01_02_04_DSAR_Finalize_Flow.md)
