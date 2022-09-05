# CommonDB - Fabric Configuration 

## CommonDB Configuration Settings

All configuration parameters are located in the *common_area_config* section of the config.ini file located in the *Fabric Home directory* of each Fabric Node.


### Update Size
The allowed size of an update message can be configured using the UPDATE_SIZE and SNAPSHOT_SIZE parameters in the *common_area_memory_queues_config* section.

On Kafka:
```
# Memory queue size for update - e.g. 10 MB
UPDATE_SIZE=10000
```

```
# Memory queue size for snapshot
#SNAPSHOT_SIZE=100
```

### Update Distribution
Fabric allows users to use server's RAM to distribute messages (in a single-node configuration only) in order to avoid the need for Kafka configurations. It is very important to note that in that case, overall data persistency cannot be ensured.

```
# Messages distribution mode - MEMORY / KAFKA
MESSAGES_BROKER_TYPE=MEMORY
```

### Message Access Retry
When a message is not processed properly, Fabric allows for a configurable number of retrials to be executed.
```
# Max retry for a poisoned message
PROCESS_UPDATE_MESSAGE_RETRIES_COUNT=3
```

### Snapshot Idle Time

```
# Max idle time when consuming snapshot messages, if processing hangs on longer the consumer will return IDLE_TIMOUT
# 16.6 hrs
CONSUMER_IDLE_TIME=60000
```

### Max Retry for Common Area Operation
```OPERATION_RETRIES_COUNT=3```

### Affinity 

Using the [affinity](/articles/20_jobs_and_batch_services/10_jobs_and_batches_affinity.md) parameter, the table synchronization process can be allocated to a specific node (by default, no affinity is set).

```
SYNC_JOBS_AFFINITY=10.23.10.11
```

### Drop Topics

When set to true, all topics for tables being dropped will be removed automatically:

```
DELETE_TOPICS_ON_DROP=true
```

### Bulk Size

If 2500 insert commands are required, each bulk of 1000 commands is sent to Kafka, while the update content is kept in Cassandra. 
These 2500 inserts are divided into 3 transactions, the first 2 containing 1000 rows to insert, and the third one 500.

```
TRANSACTION_BULK_SIZE=1000
```


### Maximum Transactions in Bulk

```
MAX_TRANSACTIONS_COMMIT=100
```


### Memory Queue Size for Snapshot
```
# defines maximum snapshot size - in the case below set to 100 MB
#SNAPSHOT_SIZE=100
```

### Snapshot Table TTL

```
# in seconds - Default is one week
# below has been set to one day
COMMONS_TABLE_TTL=86400 
```

## CommonDB Kafka-Related Configuration Settings

All configuration parameters are located in the *common_area_kafka_producer* and *common_area_kafka_consumer* sections of the Config.ini file located in the *Fabric Home directory* of each Fabric Node:

### Kafka Producer

```
#ACKNOWLEDGMENT=all
#RETRIES=0
#BATCH_SIZE=16384
#BOOTSTRAP_SERVERS=localhost:9093
#LINGER_MS=1
#MAX_BLOCK_MS=60000
#REPLICATION=3
#BUFFER_MEMORY=33554432
#KEY_SERIALIZER=org.apache.kafka.common.serialization.LongSerializer
#VALUE_SERIALIZER=org.apache.kafka.common.serialization.ByteArraySerializer
```

### Kafka Consumer

```
#AUTO_OFFSET_RESET=earliest
#BOOTSTRAP_SERVERS=localhost:9093
#ENABLE_AUTO_COMMIT=false
#MAX_POLL_RECORDS=500
#MAX_POLL_INTERVAL_MS=5000
#SESSION_TIMEOUT_MS=30000
#KEY_DESERIALIZER=org.apache.kafka.common.serialization.LongDeserializer
#VALUE_DESERIALIZER=org.apache.kafka.common.serialization.ByteArrayDeserializer
#DEFAULT_DURATION_TIME=100
```

### Kafka Security

Fabric supports the following Kafka security encryption, authentication and authorization configuration parameters: 

```
#SSL_ENABLED=false
#SECURITY_PROTOCOL=SSL
#TRUSTSTORE_LOCATION=kafka.client.truststore.jks
#TRUSTSTORE_PASSWORD=
#KEYSTORE_LOCATION=kafka.client.keystore.jks
#KEYSTORE_PASSWORD=
#KEY_PASSWORD=
#ENDPOINT_IDENTIFICATION_ALGORITHM=
#SSL_CIPHER_SUITES=
#SSL_ENABLED_PROTOCOLS=
#SSL_TRUSTSTORE_TYPE=
```




## Cassandra Snapshots Settings

### SNAPSHOTS NAME
```
COMMONS_SNAP_TABLE=snapshots
```

### Entry TTL 
```
Snapshot table TTL in seconds - Default is one week
#COMMONS_TABLE_TTL=8640
```

### TIMEOUT

Maximum idle time when consuming snapshot messages
```
CASSANDRA_WAIT_MESSAGE_TIMEOUT=60000
```



## CommonDB Statistics

Fabric provides the following statistics about CommonDB-related operations. These can be viewed sing the JMX metrics tool located at: ```http://<IP address>:3213/static/status/status.html```


**commonUpdate**

Common area update messages count per table formatted as follow: count, timestamp, sinceDate.


**commonTransactions**

Common area transaction/commit count per table formatted as follow: count, timestamp, sinceDate.


**commonKafkaRead**

Common area Kafka message read count per table formatted as follow: 

count, timestamp, sinceDate.


**commonKafkaCommit**

Common area Kafka commit count per table" formatted as follow: 

count, timestamp, sinceDate.


**commonSnapshot**

Common area snapshot bulk and statement count per table formatted as follow: 

last, totalLast, average, count, timestamp, sinceDate.


**commonSnapshotDuration**

Common area snapshot duration per table formatted as follow: 

last, totalLast, average, count, timestamp, sinceDate.


**commonMessagesBulkProcessDuration**

Common area process bulk messages duration, including failures and retries, formatted as follow: 

last, totalLast, average, count, timestamp, sinceDate.







[<img align="left" width="60" height="54" src="/articles/images/Previous.png">](06_fabric_commonDB_misc.md)

