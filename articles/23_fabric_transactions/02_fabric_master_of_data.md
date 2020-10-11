# Fabric as a Master of Data

### Overview

Fabric as a master of data is the ability to update a specific [LU table](/articles/06_LU_tables/01_LU_tables_overview.md) for the given LUI ([Instance ID](/articles/01_fabric_overview/02_fabric_glossary.md#instance-id)) in the Fabric database or an entry in the [Reference table](/articles/22_commonDB/01_fabric_commonDB_overview.md) instead of synchronizing the LUI or the Reference table from the source. 

The update is performed in the following way:

* Begin transaction.
* Run the required INSERT, UPDATE or DELETE command.
* End transaction by either commit or rollback.

Several insert, update or delete commands can be executed on the instance ID in the same transaction.

Note that after the update is completed, the data is only available on the current node. It becomes available to other nodes of the [Fabric Cluster](articles/02_fabric_architecture/01_fabric_architecture_overview.md#61-fabric-cluster) after the distribution process is completed. 


### Update LU Instance

The transaction is performed on LUI level whereby get the LUI is required before running the commands. It is not possible to get several instances inside a transaction even if you do not run insert, update or delete commands on them.

For example, you can get CUSTOMER 1 and ADDRESS 100 in one transaction, but it is not allowed to get CUSTOMER 1 and CUSTOMER 2 in one transaction.

The LU tables that will be populated by the update process must be part of the [LU schema](/articles/03_logical_units/03_LU_schema_window.md) and they can be defined either with our without a population. 

Note that it is recommended not to define a population for these tables in order to prevent data conflicts, however the creation of a population for LU tables that will be populated by the update process in not blocked in Fabric. It is the implementation responsibility to verify that the data, populated by the population object when the LUI is synced, does not conflict or override the changes of the LU table.

There is a write lock per LUI during the process. It means that you can begin several transactions on one LUI only if the transactions are open in different nodes. It is not allowed to begin several transactions on the same LUI on the same node. 


### Parallel Transactions

Fabric can be configured as a [cluster](/articles/02_fabric_architecture/01_fabric_architecture_overview.md#61-fabric-cluster) with multiple nodes spread over multiple datacenters and it can support parallel transactions on the same LUI. 

The OPTIMISTIC_LOCKING parameter in **config.ini** can be set per node to support the lightweight transaction as follows:

- **NONE** (default). The latest transaction overrides the LUI (Instance ID).
- **QUORUM**. The first transaction locks the LUI. Latest transaction fails until the first transaction is committed (the commit requires a quorum).
- **LOCAL QUORUM**. The first transaction locks the LUI. Latest transaction fails until the first transaction is committed (the commit requires a local quorum on the DC).

**Example**

The transaction 1 runs on Node 1 and the transaction 2 runs on Node 4.

* If OPTIMISTIC_LOCKING=‘NONE’, the transaction 2 (the latest one) overrides the transaction 1.

* If OPTIMISTIC_LOCKING=‘QUORUM’,  the transaction 1 locks the LUI until the transaction is committed and updates at least 2 nodes of each DC.

* If OPTIMISTIC_LOCKING=‘LOCAL QUORUM', the transaction 1 locks the instance until the transaction is committed and updates at least 2 nodes of DC1.

<img src="images/23_02_1.PNG" alt="image" style="zoom: 67%;" />

### Asynchronous Mode

Fabric supports an asynchronous mode of INSERT, UPDATE or DELETE transactions simulating the **iidFinder** mechanism. 

These transactions can be populated in the iidFInder delta table whereby the delta table is created under the [Cassandra **k2staging** keyspace](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md). The iidFinder then gets the transactions from the delta table and updates the LUI.

The asynchronous mode can be using the following command:

~~~
set ASYNC_TRX=true;
~~~

**Example**

~~~
> set async_trx=true;
> begin;
> get Customer.22;
> insert/update/delete command...
> commit;
~~~

Notes:

* The transaction is added to the delta table “as is” without any validation.

* The **set async_trx** command must be executed outside the transaction and prior to it.

* The get of the LUI must run inside the transaction.

### Update Reference Tables

It is possible to update the Reference tables and similar to the LU tables, a Reference table can be defined without a population. This table can be populated by an update transaction.

The transaction is done in an asynchronous mode thus you cannot view the updated data until the commit is performed and the Fabric updates the Common DB. The transaction is sent to Kafka and is saved into Kafka or Cassandra, depending on its size:

* The TRANSACTION_BULK_SIZE parameter in **config.ini** defines the maximum number of commands in each bulk.

For example, Run 2500 insert commands whereby the TRANSACTION_BULK_SIZE = 1000. 

* Each bulk of 1000 commands it sent to Kafka, the commands are kept in Cassandra, and Kafka gets the transaction ID. 
* The 2500 inserts are divided into 3 transactions (1000 + 1000 + 500).
* Then, run another 900 inserts. The 900 inserts are sent and stored in Kafka.

Parallel transactions are supported on Reference tables as follows:

* The first commit updates the table. The commit is initiated either by:
  * Short transaction - the user runs the commit command.
  * Large transaction - the commit is initiated internally for each bulk size, populated in Cassandra.

[Click for more information about running the INSERT, UPDATE or DELETE commands on Reference tables](/articles/22_reference(commonDB)_tables/03_fabric_commonDB_runtime.md).



[![Previous](/articles/images/Previous.png)](01_fabric_transactions_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_update_lui_code_examples.md)

