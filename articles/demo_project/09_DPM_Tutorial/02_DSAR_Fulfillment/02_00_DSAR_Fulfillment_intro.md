# DSAR Fulfillment Process

In the previous section of this tutorial, we prepared the Flow that implements the fulfilment process of a Data Subject Access Request (DSAR) activity, and associated it to an Activity that can be requested by a customer.  

We can now demonstrate the fulfilment process itself, simulating the scenario where a customer accesses the DPM Customer User Interface, and submits a DSAR Request. 

This section will walk you through the steps this Request follows, from submission until its completion, based on the Stages and Tasks we configured in the previous section of this tutorial.

Remember that the Tasks we created are only an example. The actual sequence and content of Tasks is fully configurable, and you can implement the process to fit your company's needs within your own project.

So far in this tutorial, we demonstrated the activities of a user that holds the Administrator role, such as configuring a new Flow, Regulation, and Activity. As part of the customer request fulfillment Flow, you will be presented with some of the additional user roles that comprise the DPM tool, such as:

- **Customer** - This role is an example of a user that can submit data privacy related Requests to the company. It could be a company customer, prospect, employee, or any other end user about whom the company may hold data. 
- **Representative** -  This role is performed by users such a call center representative or an internet "bot", who responds to customer's calls or chats. A Representative can perform actions on behalf of the customer, submitting a DSAR Request, report Request status to a customer, or alter the customer's Consent preferences.
- **Data Steward** - This role can be associated with any user that performs back-office activities as part of the fulfillment process. In our example, we defined that the Legal team should review the data before sending it to the customer, so we defined the users of the Legal team to a role that has Data Steward permission.
- **Case Owner** - The Case Owner is responsible for the timely execution of all customer Requests and can review the status of all Tasks. In the example we created, and in addition to the overall responsibility over the Request, we also allocated to the Case Owner the Task of final review. We will demonstrate this scenario as well.

To demonstrate how the DSAR fulfillment process is orchestrated by the DPM tool, we will guide you through the following steps:

1. [**Customer Submits a DSAR Request**](02_01_DSAR_Fulfillment_Customer_Request.md) - This is based on the Activity we created in the previous chapter.
2. **[Case Owner Views the Submitted Request](02_02_DSAR_Fulfillment_Case_Owner_View.md)**  - This step is not necessary during the Request fulfilment process, but we will use it to show how the Tasks we created in the previous chapter are translated to a sequence of actions in the submitted Request fulfillment process.
3. **[Legal Data Steward Reviews the Results](02_03_DSAR_Fulfillment_Steward_View.md)** - As we configured the fulfillment process to include a review of the results by the Legal team, once the automatic process collected all of the customer's data, a task is created for this team to perform the review.
4. [**Case Owner Reviews the Results**](02_04_DSAR_Fulfillment_Case_Owner_Perform_Task.md) - This will show how the Case Owner can view the results and confirm sending them to the customer. 
5. **[Customer Views the Completed Request](02_05_DSAR_Fulfillment_Customer_View_Completed_Request.md)** - The Request was completed and the Results were provided to the customer. Here we see how it looks from the customer side. 



[![Previous](/articles/images/Previous.png)](../README.md#data-subject-requests)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_01_DSAR_Fulfillment_Customer_Request.md)
