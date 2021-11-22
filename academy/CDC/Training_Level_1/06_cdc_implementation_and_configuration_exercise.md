# CDC Implementation and Configuration

### ![](/academy/images/Exercise.png) Exercise - Adding CDC Fields to a Customer LU

In this exercise you will add a new CDC consumer named CDCTraining to your project and then add CDC fields to an Invoice LU table in the Customer LU to publish the invoice's changes to the Kafka CDCTraining topic. 

This exercise includes the following steps:

* Adding the CDCTraining consumer to your project.
* Adding CDC fields to the Invoice LU table. 
* Configuring CDC sections in the config.ini file.
* Deploying the updated Customer LU and checking Kafka messages in the CDCTraining topic.
* Getting an LUI into Customer LU and checking CDCTraining topic Kafka messages. 

Please read [CDC Implementation Steps](/articles/18_fabric_cdc/05_cdc_consumers_implementation.md) and [CDC Configuration](/articles/18_fabric_cdc/06_cdc_configuration.md) to learn how to add a CDC implementation and CDC configurations to your working environment.

### Prerequisites

- Download the [Demo Project](/articles/demo_project) and its related SQLite interfaces to your Fabric Studio.
- Install Zookeeper and Kafka servers.
- Start Zookeeper and Kafka servers.

[Click for installation instructions](/articles/demo_project/Fabric_Demo_Project/01_local_installation_of_zookeper_kafka_and_ES.md).  

### **Exercise Steps** 

#### Step 1 - CDC Data Publish Configuration

To open the **config.ini file** of the local Fabric server, do the following:

- Open the **Demo Project** in Fabric Studio. Go to the **Project tree**, right click the **project name** > **Open Folder** to display the project's folder in a  popup window.
- Select the **FabricHome/config** directory under the project's folder and open the **config.ini** file.
- Edit the **cdc_data_publish** section, uncomment **BOOTSTRAP_SERVERS** and populate it using the Kafka server's **host** and **port**. Note that when using a local installation of the Kafka server, edit this parameter as follows: **BOOTSTRAP_SERVERS=localhost:9092**.

   [Click to read more about CDC configuration](/articles/18_fabric_cdc/06_cdc_configuration.md). 

#### Step 2 - Adding a CDC Consumer to the Fabric Project

1. Open the **Demo Project** in Fabric Studio. Go to the **Project tree**, right click the **project name** > **Open Folder** to open the project's folder in a popup window.

2. Open the **[project name].k2proj** file to be edited.

3. Add a a new CDC consumer named **CDCTraining** to the **DataChangeIndicators** tag. Set the data type of the **CDC** field in the **option** tag to **CDCdata**.

4. Save and close the **k2proj** file.

5. Close and reopen the project in Fabric Studio to reload the updated **k2proj** file.

[Click to read more about adding CDC consumers to a Fabric project](/articles/18_fabric_cdc/05_cdc_consumers_implementation.md#adding-cdc-consumers).

#### Step 3 - Editing a Customer LU, Adding CDC Fields to an Invoice LU Table

1. Open the **Invoice  LU table** of Customer LU. Verify that the **CDCTraining** tab is added to the table's window.

2. Define **CDCTraining** fields (indexes) on the following LU table columns:
   - INVOICE_ID
   - SUBSCRIBER_ID
   - STATUS
   - DUE_DATE
   - BALANCE

3. Save the changes and close the LU table window.

[Click to read more about adding CDC fields to an LU table](/articles/18_fabric_cdc/05_cdc_consumers_implementation.md#creating-indexes-for-other-cdc-consumers).

#### Step 4 - Deploy the Customer LU 

1. Open the Fabric console and check the list of CDC jobs using the **jobstatus** command.

2. Deploy the **Customer LU** to the local Fabric server.

3. Recheck the list of CDC jobs using the **jobstatus** command.

**Questions** 

<ul>
<pre><code>
A. Which Job has been added to the Jobs list after the Customer LU's deployment?
B. What is the new Job's Type and UID? 
C. What happens to the Jobs list if CDC fields for an additional CDC consumer are added and deployed to the updated LU? 
</code></pre>
</ul>

4. Open the Kafka server console and run a command to get the list of Kafka topics. When using a local Kafka installation, do the following:

   - Open a **cmd window** from **[local Kafka Windows directory]\bin\windows** directory.
   - Run the **kafka-topics.bat --zookeeper localhost:2181 -list** command.

   [Click to open the list of basic Kafka commands](/articles/02_fabric_architecture/08_kafka_basic_commands.md).

**Question**

<ul>
<pre><code>
A. Which topic has been added for the CDCTraining consumer? What is the relationship between the CDC Consumer name and the Kafka topic name?
</code></pre>
</ul>

5. Get the Kafka topic messages from the CDCTraining consumer topic. When using the local Kafka installation, do the following:

   - Open a **cmd window** from the **[local Kafka Windows directory]\bin\windows** directory. 

   - Run the **kafka-console-consumer.bat --topic [topic name] --bootstrap-server localhost:9092 --from-beginning** command. 



**Question**

<ul>
<pre><code>
A. What is the dataType of the Kafka message published to the CDCTraining topic? 
</code></pre>
</ul>




#### Step 5 - Get and Update Instances into a Customer LU

1. Get Customer 1 into the Customer LU.

2. Check the Kafka messages initiated by the Get command.

**Question**

<ul>
<pre><code>
A. What is the <strong>dataType</strong> of the Kafka messages published to the CDCTraining topic? 
B. How many Invoice records does this customer have? 
C. How many Kafka messages have been published by the Get instance of Customer 1? 
</code></pre>
</ul>


3. Set the Sync Mode to Force (run **set sync force** command) and get again Customer 1 to re-sync its data. Note that the source data has not been changed between the previous and the current sync.

   **Question**

   <ul>
   <pre><code>
   A. Has the second sync published Kafka messages to the CDCTraining topic? 
   </code></pre>
   </ul>

4. Begin a transaction and update the Invoice data of LUI 1:

   - Update one of the Status of one of the Invoice records.
   - Insert a new record to the Invoice LU.
   - Delete one of the Invoice records. 

5. Commit the transaction and check the CDCTraining Kafka topic.

   **Question**

   <ul>
   <pre><code>
   A. How many Kafka messages have been published to the CDCTraining topic? 
   </code></pre>
   </ul>

6. Get Customers 2, 3, and 4 into Customer LU.

7. Redeploy the Customer LU and check the Kafka messages.  Note that if you remove the **--from-beginning** of the command, you get only new Kafka messages.

   **Question**

   <ul>
   <pre><code>
   A. Has the redeployment published a transation to the CDCTraining Kafka topic? Why?  
   </code></pre>
   </ul

   

8. Update the Customer LU schema, add a CDC field (index) to Invoice.ISSUE_DATE column. 

9. Redeploy  the updated LU and check the Kafka messages.

**Question**

<ul>
<pre><code>
A. Has the redeployment published a transation to the CDCTraining Kafka topic? Why?  
B. How many transactions have been published to the CDCTraining Kafka topic? What are the <strong>dataType</strong> values of the published transactions? Please explain. 
</code></pre>
</ul>




[![Previous](/articles/images/Previous.png)](05_cdc_consumer_example_using_broadway_as_cdc_consumer.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](07_cdc_implementation_and_configuration_exercise_solution.md)

