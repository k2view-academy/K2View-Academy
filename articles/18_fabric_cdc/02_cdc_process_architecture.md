# CDC Process Architecture

The Fabric CDC process aggregates LUI data updates in a [MicroDB](/articles/02_fabric_architecture/01_fabric_architecture_overview.md#211-microdb-) and publishes CDC message(s) with the committed changes to the CDC consumer(s). 

The following diagram describes the CDC process:

![CDC flow](images/cdc_data_flow_diagram.png)

### MicroDB Update

A transaction on an LUI may involve several updates on some of its LU tables. Each update (write) in the LUI's MicroDB SQLite file activates a Fabric trigger that sends the change to the **CDC Message**. The CDC Message publishes a message to Kafka for each INSERT, UPDATE or DELETE event in the MicroDB. Each message contains an  LUI (IID), an event type, old and new values of each CDC column, LU table's PK columns and a transaction ID.

If the transaction is committed, a **Commit message** is sent by the **CDC Message**. If the transaction is interrupted, rolled back or failed, a **Rollback message** is sent by the **CDC Message**.

Each transaction can generate multiple CDC messages. For example, if an LUI sync inserts five records into an LU table, a separate CDC message is generated for each insert.

All CDC messages initiated by a given transaction have the same value in their **trxId** property.

### CDC Message

The CDC Message publishes transaction messages to **Kafka** **CDC Consumer** topics for each UPDATE, INSERT or DELETE activity. The partition key is the LUI (IID).

Each CDC message has its own value in the **msgNo** property.

The **msgCount** property of each CDC message is populated by the number of CDC messages initiated by a transaction for a given CDC consumer. 

### CDC Consumer

Fabric has built-in integration with Elasticsearch. The CDC_TRANSACTION_CONSUMER jobs starts automatically when deploying an LU with Search indexes. The Jobs UID is **Search**. The CDC consumer job consumes the messages in the Kafka **Search** topic and creates search indexes in Elasticsearch.

[Click for more information about Fabric Search capabilities](cdc_consumers/search/01_search_overview_and_use_cases.md).

### CDC Transaction Debug 

The **DEBUG_CDC_JOB** Fabric job can be run as a CDC consumer to debug a CDC topic whereby it consumes the CDC messages of a given CDC topic and writes them to the log file. 

Example: 

~~~
startjob DEBUG_CDC_JOB name='DEBUG_CDC_JOB' ARGS='{"topic":Tableau", "group_id": "tableau"}';
~~~





[![Previous](/articles/images/Previous.png)](01_change_data_capture_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_cdc_messages.md)
