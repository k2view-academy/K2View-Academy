# CDC

## Slide 1: Fabric Change Data Capture (CDC)

## Slide 2: What is Fabric CDC

The Fabric Change Data Capture (CDC) solution provides a mechanism to:
  - Detect and capture data changes in the LUI in real time.
  - Notify external systems about those changes.
CDC enables event-driven integration between Fabric and downstream consumers.

## Slide 3: Architecture and Features

- The Fabric CDC uses Kafka to publish change events to the external consumer.
- Enables fine-tuned granular control and customer-specific data exposure through custom field selection per LU table and consumer.
- Supports multiple consumers for CDC data, where each consumer has a dedicated Kafka topic and each topic receives only the relevant data changes.
- Provides a built-in Elastic Search integration (via CDC_TRANSACTION_CONSUMER job) to push changes to be indexed in Elasticsearch for search and analytics use cases.
- Note:
- The integration is being done through an internal process that uses the reserved consumer named “Search”.

## Slide 4: Benefits

- Real-time data sync across downstream systems.
- Scalable, Kafka-based delivery
- Targeted data exposure improves security and efficiency.
- Simplifies integration with downstream systems.
- Built-in support with Elasticsearch for search, analytics, and monitoring.

## Slide 5: Implementation Steps for CDC

## Slide 6: Configure Consumers of CDC

Configure Consumers in .k2proj File
  - CDC Consumers must be declared in the project configuration.
  - Default .k2proj includes only the Search consumer, which is reserved for the integration with Elasticsearch. Don’t alter or reuse this name.
  - Add customer consumers by adding DataChange elements.
  - The Fabric Studio requires a restart to reload changes.
Note:
The “option” allows for “tagging” a column in the CDC message to be used as needed by the consumer process. The most common one for custom consumers is “data”

## Slide 7: Configure LU Tables of CDC

Open the required LU table in Fabric Studio.
Access the Table Properties pane.
Expand the “Data Change Indexes” section.
  - The subsection for each consumer will be displayed.
  - Add columns using the ‘+’ button for an individual column or the ‘select all’  button to add multiple/all columns at once.

## Slide 8: Create Consumer Topics

- Kafka topic is created (if does not exist) at the time of LU deployment – for each consumer that has CDC index added in LU table(s)
- Name of the Kafka topic is identical to the name defined in .k2proj file (same name is exposed in Studio table window tab)
- Fabric concatenates cluster_id to each topic name if cluster_id is defined in node.id file
- Note:
- Fabric creates topic with single partition by default. Therefore it is recommended either to create topics upfront or alter number of partitions after first LU deployment
- Note:
- If consumer defined in .k2proj file, but has no any LU table column indexed for it – no topic will be created

## Slide 9: Deploy LU & Schema Publishing

First-time LU deploy to Fabric
  - Kafka Topic will be created
    - Topic name = Consumer name
    - Cluster ID concatenated to topic name (if cluster ID is configured for the Fabric cluster)
  - CDC schema messages published to KAFKA (per consumer/topic)
LU redeploy to Fabric
  - CDC schema update messages published (per consumer, if the schema changed)

## Slide 10: CDC Message Types

## Slide 11: CDC SCHEMA Message

- The CDC Schema message (datatype=SCHEMA) is triggered when the logical unit (LU) with the CDC configuration is deployed for the first time.
- Contains LU name, tables, columns, tags, and primary key (PK) flags.
- Note:
- SCHEMA message for each consumer contains info only for tables and columns with CDC indexes defined for that consumer

## Slide 12: CDC SCHEMA UPDATE Message

- The CDC Schema Update message (datatype=SCHEMA_UPDATE) is triggered when a logical unit (LU) with a CDC configuration change is redeployed or dropped.
- Captures table and column-level changes (UPDATED, REMOVED, and ADDED).
- Note:
- The addition of new CDC indexes on the table or column level will trigger the CDC Republish Instance flow for all LUI instances.
- Note:
- LU redeploy triggers update only for impacted consumers
- LU drop triggers update for all consumers

## Slide 13: CDC SCHEMA REPUBLISH Message

- The CDC Republish Schema message (datatype=SCHEMA_REPUBLISH) is triggered when a CDC_REPUBLISH_SCHEMA command is being executed.
- Like CDC Schema message (datatype=SCHEMA) it  contains LU name, tables, columns, tags, and primary key (PK) flags.
- Note:
- SHEMA_REPUBLISH message generated only for impacted consumers

## Slide 14: CDC DATA CHANGE Message

- The CDC Data Change message (datatype=DATA_CHANGE) is triggered when the MicroDB data is changed for indexed fields in LU tables (insert/update/delete) .
- Contains LU name, LU ID, table, PK columns, columns, oldValues (UPDATE/DELETE), and newValues (INSERT/UPDATE).

## Slide 15

- The CDC Data Republish message (datatype=DATA_REPUBLISH) is triggered when a CDC_REPUBLISH_INSTANCE command is executed.
- The message is being sent for each row of each table the CDC is configured for, unless the ‘tables’ flag is used to filter tables as part of the CDC_REPUBLISH_INSTANCE command.
- Similar to a message triggered by an INSERT statement, it contains LU name, LU ID, table, PK columns, columns, and newValues
- Note:
- The CDC DELETE_TABLES and DATA_REPUBLISH messages will be triggered automatically for all LUIs upon deploying an LU with new or additional CDC indexes.
- CDC DATA REPUBLISH Message

## Slide 16: CDC DELETE TABLES Message

- The CDC Delete Tables message (datatype=DELETE_TABLES) is triggered when delete instance command is executed to delete a specific instance from Fabric or CDC_REPUBLISH_SCHEMA command is issued with drop_table flag set to true.
- Contains LU name, LU ID, and tables.
- Note:
- CDC_REPUBLISH_SCHEMA command is covered in the section below.
- Note:
- The CDC DELETE_TABLES and DATA_REPUBLISH messages will be triggered automatically for all LUIs upon deploying an LU with new or additional CDC indexes.

## Slide 17: CDC Commands

## Slide 18: CDC Republish

- Key Benefits
- Shema Update Republish – Initiated by CDC_REPUBLISH_SCHEMA command.
- Data Update Republish – Initiated by CDC_REPUBLISH_INSTACE command.
- On-demand republishing of LU schemas
- Selective republishing to specific consumers
- Instance-level data republishing
- Flexible table filtering
- Controlled truncation option for data update republishing
- Two Main Republish Categories

## Slide 19: CDC_REPUBLISH_SCHEMA

Purpose
  - Republish by demand a full LU schema definition or the schema of a selected list of LU tables to all CDC consumers or to a selected list of CDC consumers.
Syntax
Parameters
cdc_republish_schema <LUT_NAME> [types='<type1,type2...>'] [tables='table1,... '] [drop_table=true/false]

| Parameter | Description | Optional |
| --- | --- | --- |
| LUT_NAME | Name of the Logical Unit Type | No |
| types | Comma-separated list of CDC consumer types | Yes |
| tables | Comma-separated list of specific tables | Yes |
| drop_table | Whether to drop table before republishing (true/false) | Yes |

## Slide 20: CDC_REPUBLISH_SCHEMA Command Examples

Basic Schema Republishing to all consumers
  - Republishes the entire CUSTOMER LU schema to all CDC consumers.
Target Consumer Type
  - Republishes CUSTOMER LU schema only to SNOW consumer.
Target Specific Tables with Drop option
  - Republishes CUSTOMER LU schema for customer table only, dropping the table first.
cdc_republish_schema CUSTOMER;
cdc_republish_schema CUSTOMER types='SNOW';
cdc_republish_schema CUSTOMER types='SNOW' tables='customer' drop_table=true ;

## Slide 21: CDC_REPUBLISH_INSTANCE

Purpose
  - Republish the CDC data of a selected LUI (Logical Unit Instance) with flexible filtering options for tables and consumers.
Syntax
Parameters
cdc_republish_instance <LUT_NAME>.<INSTANCE_ID> [types='type1,type2...']
[tables='table1,...'] [truncate=true/false]

| Parameter | Description | Optional |
| --- | --- | --- |
| LUT_NAME | Name of the Logical Unit Type. | No |
| INSTANCE_ID | Logical Unit Instance ID. | No |
| types | Comma-separated list of CDC consumer types. | Yes |
| tables | Comma-separated list of specific tables. | Yes |
| truncate | Send CDC Delete Tables message before republishing. | Yes |

## Slide 22: CDC_REPUBLISH_INSTANCE Command Examples

Basic Instance Republishing to all Consumers
  - Republishes all data for CUSTOMER instance ID 1  to all CDC consumers.
Target Consumer Type
  - Republishes all data for CUSTOMER instance ID 1 schema only to SNOW consumer.
Specific Tables with Drop option
  - Republishes only CUSTOMER table data of CUSTOMER instance ID 1 to all consumers.
No Truncation
  - Republishes all data for CUSTOMER instance ID 1  to all CDC consumers without sending a delete message first.
Can be used as a fabric command for a batch execution
  - Republishes all data for a specific IG for a CUSTOMER  LUT to all CDC consumers.
cdc_republish_instance CUSTOMER.1;
cdc_republish_instance CUSTOMER.1 types='SNOW';
cdc_republish_instance CUSTOMER.1 tables='customer';
cdc_republish_instance CUSTOMER.1 types='SNOW’ truncate=false;
Batch LUT[@DC].<IG> FABRIC_COMMAND=”cdc_republish_instance CSUTOMER.?” with async=true

## Slide 23: CDC Republish Key Takeaways

- CDC_REPUBLISH_SCHEMA
- Republishes LUI data
- Flexible table and consumer filtering
- Configurable truncation behavior
- Instance-specific data changes.
- Use when you need to refresh data for a specific instance, recover from data inconsistency, or sync a particular consumer with the latest LUI  data.
- Republishes LU Schema definitions
- Supports selective table republishing
- Can target specific consumer
- Includes drop_table option
- Use when you need to force schema definition updates messages to CDC consumers.
- CDC_REPUBLISH_INSTANCE

## Slide 24: CDC Configuration Parameters

## Slide 25: Fabric Configuration for CDC – config.ini

All of the kafka connection settings are defined under [default_pubsub] section in the config.ini
When (if) different Kafka settings required for CDC, they should be set in [cdc] or/and [search_loader_pubsub] section of config.ini
  - These sections may include only those parameters that should override default settings
  - [search_loader_pubsub] section is used for predefined ‘Search’ consumer when enabled

## Slide 26: CDC Configuration & Control

CDC_PUBLISH_MODE
  - Controls whether the CDC messages are being published to CDC consumers. This parameter can be configured globally in the config.ini file.
  - ON – default value. When implemented, CDC messages are published to CDC consumer topics.
  - OFF – CDC messages are never published to external systems, regardless of the implementation. NOTE: The session level doesn’t override the OFF setting.
  - Session-Level Override – Use the SET CDC_PUBLISH command to control CDC publication at the session level. The default session value is true.
    - SET CDC_PUBLISH true
    - SET CDC_PUBLISH false
TRANSACTION_MODE
  - Determines how the publisher handles transactions.
  - This parameter should be set in [cdc] section of config.ini file to BROKER that enables transactional producer mode and allows sending data to multiple partitions and guarantees all these writes are either committed or discarded. This is done by grouping multiple calls to be sent into a transaction. Once a transaction is started, you can either commit or rollback to complete it.
ASYNC_HANDLER_QUEUE
  - Defines max number of messages that can be sent asynchronously to Kafka (when value is > 0)
  - If  0 – all messages will be processed synchronously

## Slide 27: CDC Configuration & Control

TRANSACTION_TIMEOUT_MS
  - Defines the maximum amount of time (in milliseconds) that a transaction can remain open without being committed or aborted (default 5 minutes)
CDC_CONSUMER_JOB_AFFINITY
  - Defines the affinity setting only for the SEARCH consumer (for the ElasticSearch integration), allowing control over which node(s) will host the process for better resource management and load distribution.
CDC Transaction Debug
  - The DEBUG_CDC_JOB fabric job can be run as a CDC consumer to debug a CDC topic. It consumes messages from a specified topic and writes them to log files for troubleshooting purposes.
startjob DEBUG_CDC_JOB name='DEBUG_CDC_JOB' ARGS='{"topic":"<CDC_TOPIC>", "group_id":"<group>"} '

## Slide 28: CDC Consumer Implementation

## Slide 29: CDC Consumer – Broadway Flow

Broadway has several queue management built-in Actors for Pub / Sub asynchronous message handling that can subscribe to Kafka .
Consumer interface must be defined in project:
  - PubSub Interface Type (referring to valid config.ini section – for example ‘cdc’ or ‘default_pubsub’)
  - In the config.ini under relevant pubsub section (default_pubsub or cdc section) populate Bootstrap Servers with kafka details (comma separated for multiple servers)
  - Kafka interface Type

## Slide 30: CDC Consumer – Broadway Flow

Create Broadway Flow to Consume messages from Kafka using build-in actors:
  - Subscribe – actor subscribes to message broker and returns messages one by one
  - SubscribeBatch – actor subscribes to message broker and returns messages in batches
  - SubscribeWithMetadata – actor similar to SubscriberBatch, but for every message in the batch, this actor exposes key metadata fields in addition to the actual payload

## Slide 31: CDC Deep Dive

## Slide 32: MDB/LUI Changes Process

Each update (write) in the LUI’s MicroDB activates a Fabric trigger that capture INSERT, UPDATE, and DELETE operations.
Each change initiates a CDC message, which is published asynchronously to Kafka.
Kafka transactions are used to group multiple changes into a single, atomic operation, ensuring consistency with the corresponding MicroDB transaction. This means either all records within a transaction are committed, or none are, preserving data integrity.
To maintain this behavior, CDC Kafka consumers must be configured with isolation.level=read_committed, which guarantees:
  - Only records from committed transactions are delivered to the Consumer
  - Aborted/rolled-back transactions are entirely ignored
  - Records within each committed transaction are delivered in order
  - Kafka waits for transaction markers before delivering records to the consumer for processing.

## Slide 33: CDC Behavior – LUI Sync

Initial Sync (LUI not in fabric): During the first synchronization, when the LUI is not present in the fabric, a CDC Data Change message (datatype=DATA_CHANGE) with type=INSERT is generated for each row in every table that has CDC indexing enabled.
Subsequent Syncs: For subsequent synchronizations of the same LUI, CDC behavior depends on the configured Sync Delete Mode:
  - When Sync Delete Mode = ALL: The process generates two types of CDC Data Change messages for each row in every CDC-enabled table:
    - First: CDC Data Change message (datatype=DATA_CHANGE) with type=DELETE for each existing row
    - Second: CDC Data Change message (datatype=DATA_CHANGE) with type=INSERT for each newly inserted row
  - When Sync Delete Mode = NonUpdated: The process generates CDC Data Change messages only for modified rows in each CDC-enabled table:
    - CDC Data Change message (datatype=DATA_CHANGE) with type=UPDATE for rows where data has changed
    - CDC Data Change message (datatype=DATA_CHANGE) with type=DELETE for rows that no longer exist in the source

## Slide 34: CDC Data Change Message

- The CDC Data Change message (datatype=DATA_CHANGE) is triggered when the MicroDB data changes (insert/update/delete) .
- Contains LU name, LU ID, table, PK columns, columns, oldValues (UPDATE/DELETE), and newValues (INSERT/UPDATE).
- The JSON schema for the CDC SCHEMA message is available here: CDC_DATA_CHANGE.json.

## Slide 35: CDC DELETE TABLES Message

- The CDC Delete Tables message (datatype=DELETE_TABLES) is triggered when delete instance command is executed to delete a specific instance from Fabric or CDC_REPUBLISH_SCHEMA command is issued with drop_table flag set to true.
- Contains LU name, LU ID, and tables.
- The JSON schema for the CDC SCHEMA message is available here: CDC_DELETE_TABLES.json
- Note:
- CDC_REPUBLISH_SCHEMA is covered in the section below.

## Slide 36: CDC Behavior – LU Update and Redeploy

Whenever CDC metadata is updated, LU must be redeployed to Fabric.
CDC index added on existing LU table
  - Publish the changes in the LU schema (for impacted tables & consumers) – by sending SCHEMA_UPDATE message to each impacted table consumer with “updateType”:“ADDED”
  - Sending DELETE_TABLES message (for impacted tables & consumers)
  - Republish LUI data itself by sending DATA_CHANGE message for all LUIs (for impacted tables consumers) by executing batch command:
    - BATCH <LUT_NAME> FROM fabric FABRIC_COMMAND="cdc_republish_instance <LUT_NAME>.?" with async=true
CDC index changed on existing LU table
  - Republish the changes in the LU schema (for impacted tables & consumers) – by sending SCHEMA_UPDATE message to each impacted table consumer with “updateType”:“UPDATED”
  - No LUI data republished

## Slide 37: CDC Behavior – LU Update and Redeploy

CDC index deleted on existing LU table
  - Republish the changes in the LU schema (for impacted tables & consumers) – by sending SCHEMA_UPDATE message to each table consumer with “updateType”:“REMOVED”
  - No LUI data republished
New table is added into LU with CDC indexes
  - Publish the changes in the LU schema (for impacted tables & consumers) – by sending SCHEMA_UPDATE message to each impacted table consumer with “updateType”:“ADDED”
  - Sending DELETE_TABLES message (for impacted tables & consumers)
  - No LUI data republished – re-sync is needed

## Slide 38: Monitoring and Stats

## Slide 39: CDC Monitoring and Stats

- Fabric Statistics for CDC
- cdc
- search
- custom (if added in consumer or any other part of the implementation)
- Kafka Statistics
- Consumers stats

## Slide 40: Fabric Statistics for CDC

cdc
  - cdcWaitTime – overhead time that CDC adds to transaction to finish
  - cdcFailedTrx – count of CDC failed transactions
  - cdcClosedTransactions – count of CDC closed transactions
  - cdcOpenTransactions – count of CDC open transactions
  - cdcPublishMessages – count of successfully published messages
search
  - searchLoaderUpdate
  - searchLoaderDelete
  - searchLoaderInsert
  - searchLoaderRead
  - searchLoaderErrors
custom (if added in implementation)
  - <customStatsKey> as created in implementation

## Slide 41: Best Practices

## Slide 42: CDC Best Practices

Use transactions (read_committed) in consumer
Use batches to read from kafka
Define only required fields for CDC
Set the Delete Mode = NonUpdated when the LU table has CDC fields in order to send CDC messages only for the updated records.
  - If the Delete Mode is set to All, Fabric sends delete messages for all the truncated records and inserts messages for the newly inserted records.
  - If the Delete Mode is NonUpdated, it is recommended to define a PK on the LU table and set the LU table population mode to Upsert or Update in order to delete only the old data. If the LU table does not have a PK, new records are added to the LU table and all previous records are deleted
Filter / preprocess data as needed as business table in LU
Create kafka topics upfront with multiple partitions based on

## Slide 43: Common Issues

## Slide 44: Common Issues

Consumer topic(s) lag is growing
  - Consumers are down
  - Consumers are stuck (corrupted message)
  - Frequent rebalancing
Sync was successful but CDC messages were not published
  - No CDC indexes
  - Timeout on CDC transaction wait time (big LUI)

## Slide 45: Assignment

## Slide 46: Assignment

Create Web Studio Space in Alpinist tenant
  - Use Project Alpinist and Profile 8.3-training
  - Use Customer LU from project
Install Kafka client in Web Studio:
  - cd project-resources && curl -L -o ./kafka.tar.gz https://download.k2view.com/index.php/s/IWGBLNyPrYhamzC/download && mkdir apps && tar -xzf ./kafka.tar.gz -C ./apps/
Configure two CDC consumers in project:
  - Customer – to publish CDC only from customer table for customer_id,first_name,last_name
  - Alpinist_cdc – to publish all data from customer and activity tables
Create BW flow to subscribe on alpinist consumer topic, read messages, print it in the log
Use Kafka cli to monitor customer consumer topic (from project-resources/apps/kafka/bin/)
  - ./kafka-console-consumer --topic <topic> --bootstrap-server kafka-service:9093 --from-beginning
Deploy Customer LU and check CDC messages
  - What messages do you see in consumers?

## Slide 47: Assignment

Execute Sync for Customer.300 and check CDC messages
Republish data for Customer.300 only for table customer and only for ‘customer’ consumer
In fabric terminal start transaction and manually execute 3 subsequent updates for Customer.300, table - customer, column – first_name and check messages in customer consumer topic
  - When do you get messages in consumer?
Reconnect to customer consumer topic with flag --isolation_level=read-committed and execute another 3 updates for Customer.300 table customer column first_name
  - When do you get messages in consumer?
Update table activity population to publish cdc messages only for last 10 years (based on activity_date
  - How do you control CDC from population ?
  - How many INSERT messages was generated for Customer.300 resync in force mode?
