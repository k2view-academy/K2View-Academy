# Broadway High Level Components

Broadway combines the following capabilities:

* ETL (Extract Transform Load) - Broadway can integrate with all Fabric and all interfaces supported by Fabric, such as: http/s, publish and subscriber messages from JMS or Kafka, etc... to extract the data, transform it and load it to the required target.
* BPM (Business Process Management) - manage the business processes, covering both design and execution, adding the ability to define the business flow, the order of the activities and the reaction to each flow stage results, plus advanced error handling on a stage level that leads to the desired reaction.
* Data Inspection - live data inspection capabilities with holistic and dynamic view on meta data (data structure) and actual data flow.

The BPM is defined by a **Flow** object. Each Flow must  have **1-N Stages**. 

The **Stage** represents an execution order level in the flow. Each Stage must have **1-N Actors**. 

The **Actor** represents an activity (action) like reading a file, creating a table, parsing and object, load a table. An Actor can send parameters to other Actors in the next Stages.

Broadway has a vast list of [built in Actors](Add a link) that can create various types of activities and can be added to each Flow. 

The Flow execution starts with the execution of the first Stage (on the left side) and then executes the Stages by their order- from left to right.  

**For example:**

​	Define a business flow to extract, transform and load a customer's financial activities:

![image-20200629101940357](/articles/99_Broadway/images/customer_map_financial_activities_flow_example.png)

​	This flow has four Stages and each Stage has different Actors:  

- Stage 1 - extracting the the financial activities of the customer.
- Stage 2 - complete missing data of the financial activities.
- Stage 3- link the payments to the open invoices.
- Stage 4- load the data to the target DB. 


Broadway also has a **Data Inspection** capability to view the complex data structure, returned by an **Actor** by building a dynamic schema representing the meta data of the returned object.

See below an example of a Broadway Flow which populates a table by financial activities and builds an invoice's list.

![image](/articles/99_Broadway/images/Broadway_full.png)



