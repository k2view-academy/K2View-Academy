# CommonDB - Fabric Configuration 

## Configuration Settings

All configuration parameters are located in the *common_area_config* section of the Config.ini file located in the *Fabric Home directory* of each Fabric Node.

### Memory queue size for update
```
#UPDATE_SIZE=10000
```

### Messages distribution mode - MEMORY / KAFKA
```MESSAGES_BROKER_TYPE=MEMORY```

### Max retry for a poisoned message
```PROCESS_UPDATE_MESSAGE_RETRIES_COUNT=3```

### Max idle time when consuming snapshot messages, if processing hangs longer the consumer will return IDLE_TIMOUT
```CONSUMER_IDLE_TIME=60000```

### Max retry for common area operation
```OPERATION_RETRIES_COUNT=3```

### Affinity to common sync job (comma separated), default: no affinity
```SYNC_JOBS_AFFINITY=10.23.10.11```

### If true, remove all topics that not in use on drop
```DELETE_TOPICS_ON_DROP=true```

#### Size of transaction bulk size
```TRANSACTION_BULK_SIZE= 1000```
If 2500 insert commands are required, each bulk of 1000 commands is sent to Kafka, while the update content is kept in Cassandra. These 2500 inserts are divided into 3 transactions, the first 2 containing 1000 rows to insert, and the third one 500.

### TIMEOUT
Maximum idle time when consuming snapshot messages
```
CASSANDRA_WAIT_MESSAGE_TIMEOUT=60000
```

### MAXIMUM TRANSACTIONS in BULK
```
MAX_TRANSACTIONS_COMMIT=100
```

### SNAPSHOTS
```
COMMONS_SNAP_TABLE=snapshots
```

### Memory queue size for snapshot
```
#SNAPSHOT_SIZE=100
```

### Snapshot table TTL in seconds - Default is one week

```
# set to one day
COMMONS_TABLE_TTL=86400 
```

## Cassandra Snapshots Settings

One separate keyspace is dedicated to long messages, each one of the messages being stored in a table.
