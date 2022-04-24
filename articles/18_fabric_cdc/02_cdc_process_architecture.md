# CDC Process Architecture

The Fabric CDC process aggregates LUI data updates in the [MicroDB](/articles/02_fabric_architecture/01_fabric_architecture_overview.md#211-microdb-) and publishes a CDC message to the CDC consumer about committed changes. 

The following diagram describes the CDC process:

![CDC flow](images/cdc_data_flow_diagram.png)

### MicroDB Update

A transaction on an LUI may involve several updates in several of its LU tables. Each update (write) in the MicroDB SQLite file of the LUI activates a Fabric trigger that sends the change to the **CDC Message**. The CDC Message publishes a message to Kafka for each INSERT, UPDATE, or DELETE event in the MicroDB. Each message has the LUI (iid), event type, old and new values of each CDC column, PK columns of the LU table and transaction ID.

-  If the transaction is committed, a **Commit message** is sent by the **CDC Message**. 

-  If the transaction is interrupted, rolled back or failed, a **Rollback message** is sent by the **CDC Message**. 

### CDC Message

The CDC Message publishes transaction messages to **Kafka**  for each UPDATE, INSERT or DELETE activity. Kafka uses the **CDC_TOPIC**  topic name for keeping transaction messages. The partition key is the LUI (iid).

### CDC Publisher

When the MicroDB is saved into Cassandra, the transaction's thread sends a **Publish Acknowledge**  message to the Kafka **CDC_TOPIC**. 

The CDC_TRANSACTION_PUBLISHER job consumes the transaction messages from Kafka and creates [CDC messages](03_cdc_messages.md) for each transaction. **Each CDC consumer has its own Kafka topic and its own CDC Publisher job**. 
Note that Fabric concatenates the cluster id to each topic name if there are several Fabric clusters on one Cassandra cluster.

By default, Fabric starts one instance of a  CDC Publisher job per CDC type. In case the **Kafka topic has multiple partitions, it is recommended to start additional instances of the CDC Publisher jobs and start one CDC Publisher jobs instance per each Kafka partition**. Kafka binds a partition to a job instance. 

See an example how to start a CDC Publisher job instance using the **startjob** Fabric command:

startjob CDC_TRANSACTION_PUBLISHER NAME='CDC_TRANSACTION_PUBLISHER' UID='PUBLISHER1' ARGS='{"consume_topic":"CDC_TOPIC","publish_topic":"Search","cdc_type":"Search","env":"_dev","group_id":"Search_gid", "max.poll.interval.ms":"100", "max.poll.records":"100", "session.timeout.ms:" "1000"} ';

A separate UID needs to be set on each job instance. The "publish_topic" attribute needs to be populated by the CDC type, and the "group_id" needs to be populated by a concatenation of the CDC type and "_gid".

Notes: 

- Each transaction can generate multiple CDC messages. For example, if an LUI sync inserts five records into an LU table, a separate CDC message is generated for each insert.

- All CDC messages initiated by a given transaction have the same value in their **trxId** property.

- Each CDC message has its own value in the **msgNo** property.

- The **msgCount** property of each CDC message is populated by the number of CDC messages initiated by a transaction for a given CDC consumer. 

  


#### TRANSACTION_ACKNOWLEDGE_TIME_SEC Parameter

The Fabric [config.ini](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#configini) file defines the following parameter which sets the maximum waiting time between the commit of the transaction and the Publish Acknowledge message which is sent when the transaction is successfully saved into Cassandra: 

- TRANSACTION_ACKNOWLEDGE_TIME_SEC=60

The default value of this parameter is 60 seconds.

The following diagram displays how Fabric handles this parameter:

![acknowledge time](images/cdc_publish_acknowledge_time_seq.png)

Note that the [OPTIMISTIC_LOCKING](/articles/23_fabric_transactions/02_fabric_transactions.md) parameter in the config.ini file can be set per node to support lightweight transactions between nodes when saving the LUIs into Cassandra.

### CDC Consumer

Fabric has built-in integration with Elasticsearch. The CDC_TRANSACTION_CONSUMER jobs starts automatically when deploying an LU with Search indexes. The Jobs UID is **Search**. The CDC consumer job consumes the messages in the Kafka **Search** topic and creates search indexes in Elasticsearch.

[Click for more information about Fabric Search capabilities](cdc_consumers/search/01_search_overview_and_use_cases.md).

### CDC Transaction Debug 

The **DEBUG_CDC_JOB** Fabric job can be run as a CDC consumer to debug a CDC topic whereby it consumes the CDC messages of a given CDC topic and writes them to the log file. 

Example: 

startjob DEBUG_CDC_JOB name='DEBUG_CDC_JOB' ARGS='{"topic":Tableau", "group_id": "tableau"}';



[![Previous](/articles/images/Previous.png)](01_change_data_capture_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_cdc_messages.md)
