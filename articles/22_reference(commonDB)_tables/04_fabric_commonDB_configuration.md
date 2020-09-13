# CommonDB - Fabric Configuration 

## Configuration Settings

All configuration parameters are located in the *common_area_config* section of the Config.ini file located in the *Fabric Home directory* of each Fabric Node.


### Update Size
The allowed size for an update must be configured using the UPDATE_SIZE parameter

```
# Memory queue size for update - e.g. 10 MB
UPDATE_SIZE=10000
```

### Update Distribution
Fabric allows user whether to use Fabric or server's RAM to distribute a message - for single-node configuration only

```
# Messages distribution mode - MEMORY / KAFKA
MESSAGES_BROKER_TYPE=MEMORY
```

### Message Access Retry

```
# Max retry for a poisoned message
PROCESS_UPDATE_MESSAGE_RETRIES_COUNT=3
```

### Snapshot Idle Time

```
# Max idle time when consuming snapshot messages, if processing hangs longer the consumer will return IDLE_TIMOUT
# 16.6 hrs
CONSUMER_IDLE_TIME=60000
```

### Max retry for common area operation
```OPERATION_RETRIES_COUNT=3```

### Affinity 

Using the [affinity](/articles/20_jobs_and_batch_services/10_jobs_and_batches_affinity.md) parameter, The sync table process can be allocated to a specific node (default: no affinity)
```
SYNC_JOBS_AFFINITY=10.23.10.11
```

### Drop Topics

When set to true, all topics for tables being dropped will be removed automatically.
```
DELETE_TOPICS_ON_DROP=true
```

#### Bulk Size

If 2500 insert commands are required, each bulk of 1000 commands is sent to Kafka, while the update content is kept in Cassandra. 
These 2500 inserts are divided into 3 transactions, the first 2 containing 1000 rows to insert, and the third one 500.

```
TRANSACTION_BULK_SIZE= 1000
```




### MAXIMUM TRANSACTIONS in BULK

```
MAX_TRANSACTIONS_COMMIT=100
```

### SNAPSHOTS NAME

```
COMMONS_SNAP_TABLE=snapshots
```

### Memory queue size for snapshot
```
# defines maximum snapshot size - in the case below set to 100 MB
#SNAPSHOT_SIZE=100
```

### Snapshot table TTL

```
# in seconds - Default is one week
# below has been set to one day
COMMONS_TABLE_TTL=86400 
```

## Cassandra Snapshots Settings

### TIMEOUT

Maximum idle time when consuming snapshot messages

```
CASSANDRA_WAIT_MESSAGE_TIMEOUT=60000
```

One separate keyspace is dedicated to long messages, each one of the messages being stored in a table.
