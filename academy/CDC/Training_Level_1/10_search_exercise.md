# Search Exercise

### ![](/academy/images/Exercise.png) Exercise - Adding Search Fields to a Customer LU and Running a Search on Customers

### Search Requirements

In this exercise you will add Search fields to a Customer LU to enable the following:

- Searching for customers using a combination of First Name and Last Name.
- Searching for customers using a combination of First Name, Last Name, and SSN.
- Searching for customers using a combination of First Name, Last Name, SSN and Address fields.

### Prerequisites

- Download the [Demo Project](/articles/demo_project) and its related SQLite interfaces to your Fabric Studio. 
- Install Zookeeper, Kafka and Elasticsearch servers.
- Start Zookeeper, Kafka and Elasticsearch servers.

[Click to get installation instructions](/articles/demo_project/Fabric_Demo_Project/01_local_installation_of_zookeper_kafka_and_ES.md).  

### **Exercise Steps** 

#### Step 1 - Search Configuration

You have already configured the **cdc_data_publish** sections of the Fabric config.ini file for the [CDC exercise](06_cdc_implementation_and_configuration_exercise.md) and it's now time to configure the **cdc_data_consume** sections. 

1.  To open the config.ini file of the local Fabric server, do the following:

     - Open the Demo Project using Fabric Studio. Go to the **project tree**, right click the **project name** > **Open Folder**. A popup window opens displaying the project's folder.
     - Select **FabricHome/config directory** under the project's folder and open the **config.ini** file.
     - Edit the **cdc_data_consume** section, uncomment **BOOTSTRAP_SERVERS** and populate it with the **host** and **port** of the Kafka server. Note that when using a local installation of the Kafka server, edit this parameter as follows: **BOOTSTRAP_SERVERS=localhost:9092**

2. Edit the **elasticsearch.yml** configuration file. To open the **elasticsearch.yml** of the local Elasticsearch installation, go to the **[Elasticsearch local directory]\config**. Update the following parameters:

   - network.host: populated by the Elasticsearch IP address.
   - discovery.seed_hosts, populated by the Elasticsearch IP address. For example: discovery.seed_hosts: ["localhost"]
   - Add **bootstrap.system_call_filter: false** to the Memory section. 

   [Click to read more about Search configuration](/articles/18_fabric_cdc/cdc_consumers/search/07_search_configuration.md).

#### Step 2 - Search Implementation - Adding a Search Interface

Open the Demo Project using Fabric Studio and create a **Search** interface. When using a local Elasticsearch, populate the Hosts setting with **localhost:9200**. Set the interface name to **SEARCH** and save the interface.

   [Click to read more about the Search interface](/articles/18_fabric_cdc/cdc_consumers/search/02_search_implementation.md#creating-a-search-engine-interface).

#### Step 3 - Search Implementation - Adding Search Fields

1. Following the above Search requirements, run a Search on a combination of columns related to the different Customer and Address LU tables. To enable a Search on all  combinations, add a new LU table to the SEARCH_PARAMETERS Customer LU. The new LU must be linked to the Customer LU table and be populated based on the Customer and Address LU tables. Set the execution order to run after the Address LU table. The SEARCH_PARAMETERS LU table must include the following columns:

   - CUSTOMER_ID
   - SSN
   - FIRST_NAME
   - LAST_NAME
   - STEET_ADDRESS_1
   - CITY
   - STATE
   - COUNTRY

   

   Note: it is recommended to build the DB Query based on the Customer LU table and add the Address LU table as a Lookup to the SEARCH_PARAMETER population. Set the **Lookup Not Found** property to **Continue** to enable a search on customers that do not have an address.

   **Question**

   <ul>
   <pre><code>
   A. Read <a href="/articles/18_fabric_cdc/cdc_consumers/search/06_search_solution_limitations.md">Search Limitations</a> and explain why a new LU table must be built for the Search? 
   B. Had the Search been based only on the First Name, Last Name and SSN columns, would a separate LU table need to be built?
   </code></pre>
   </ul>

2. Deploy the updated Customer LU and run a data viewer to test the population of the new SEARCH_PARAMETERS LU table.

3. Open the SEARCH_PARAMETERS and add Search fields (indexes) to all LU table columns apart from the CUSTOMER_ID:

   - Set the **SSN** type **STEET_ADDRESS_1**, **CITY**, **STATE** and **COUNTRY** columns to **keyword**. 

     Read [Search Implementation](/articles/18_fabric_cdc/cdc_consumers/search/02_search_implementation.md) to learn how to add Search fields.

   - The FIRST_NAME and LAST_NAME may contain special characters like **&**. To support a search for fields that contain special characters, use the **case-insensitive-match** template.

   Read [Search Templates](/articles/18_fabric_cdc/cdc_consumers/search/04_search_templates.md) and define the Search type of the FIRST_NAME and LAST_NAME columns as  **case-insensitive-match**.

     

4. Re-deploy the updated Customer LU. Open the Fabric console and check the list of CDC jobs using the **jobstatus** command.

   **Questions**

   <ul>
   <pre><code>
   A. Which job has been added to the Jobs list after the Customer LU's deployment?
   B. What are the Type and the UID of the new Job? 
   </code></pre>
   </ul>

   

   #### Step 4 - Search Implementation - Migrating Customers to Fabric

1. Run a batch process to migrate all customers to Fabric and populate their data in Elasticsearch. Use the **batch Customer from CRM_DB using ('select customer_id from customer')  fabric_command="sync_instance Customer.?" with async=true;** command.

   

   #### Step 5 - Search Customers

   Read the [Search Command](/articles/18_fabric_cdc/cdc_consumers/search/05_search_command.md) article and its examples to run Search commands on the Fabric console. 

1. Search for all customers named 'Danny Watson' (First Name = Danny and Last Name = Watson).  Check for an exact match. Do not include the **fuzziness** parameter in the Search query.

2. Search for all customers named  'Danny Watson'. Add the **fuzziness** parameter to the Search query to support up to two differences in the searched value. 

   **Questions**

   <ul>
   <pre><code>
   A. Which customers have been returned by the first Search command? 
   B. Which customers have been returned by the second Search command?
   C. Please explain the difference between both Search commands in the returned results.
   </code></pre>
   </ul>

3. Get Customer #7800 to Fabric. Search for all customers with the same First Name, Last Name, SSN, Street_Address_1, City, State and Country as Customer #7800. 

   **Question**

   <ul>
   <pre><code>
   A. Which customers have been returned by the Search command? 
   </code></pre>
   </ul>

4. Search for all customers whose First Name is 'Marshall&Ross'. Then search for all customers whose First Name is 'Marshall'.

   **Question**

   <ul>
   <pre><code>
   A. Did you get the same results for both Search commands? If not, explain the difference.
   </code></pre>
   </ul>



[![Previous](/articles/images/Previous.png)](09_search_command.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](11_search_exercise_solution.md)

