# Fabric as a Master of Data

### Overview

Fabric as a master of data is the ability to update a specific  [LU table](/articles/06_LU_tables/01_LU_tables_overview.md) for the given [Instance ID](/articles/01_fabric_overview/02_fabric_glossary.md#instance-id) in the Fabric database or an entry in the [Common table](/articles/22_commonDB/01_fabric_commonDB_overview.md) instead of synchronizing the entire instance ID or the Common table from the source. 

The update is performed in the following way:

* Begin transaction.
* Run the required INSERT, UPDATE or DELETE command.
* End transaction by either commit or rollback.

Several insert, update or delete commands can be executed on the instance ID in the same transaction.

### Update LU Instance

The transaction is performed on LUI level whereby you need to get the LUI before running the commands. It is not possible to get several instances inside a transaction even if you do not run insert, update or delete commands on them.

For example, you can get CUSTOMER 1 and ADDRESS 100 in one transaction, but it is not allowed to get CUSTOMER 1 and CUSTOMER 2 in one transaction.

The LU tables that will be populated by this process must be part of the [LU schema](/articles/03_logical_units/03_LU_schema_window.md)  but should have no population in the [LU](/articles/03_logical_units/01_LU_overview.md). They will not be populated by the Sync process but can be populated later by an external process, such as User job, Parser, Web Service or using the Fabric Console.

Note that even though it is possible to run this process on an LU table with population, it is not recommended to do so in order to prevent conflict or override the changes in the table. 

There is a write lock per LUI during the process. It means that you can begin several transactions on one LUI only if the transactions are open in different nodes. You cannot begin several transactions on the same LUI on one node. 

### Parallel Transactions

The OPTIMISTIC_LOCKING parameter in **config.ini** can be set per node to support the lightweight transaction as follows:

- **NONE** (default). The latest transaction overrides the instance ID.
- **QUORUM**. The first transaction locks the instance ID. Latest transaction fails until the first transaction is committed (the commit requires a quorum).
- **LOCAL QUORUM**. The first transaction locks the instance ID. Latest transaction fails until the first transaction is committed (the commit requires a local quorum on the DC).

**Example**

The transaction 1 runs on Node 1 and the transaction 2 runs on Node 4.

* If OPTIMISTIC_LOCKING=‘NONE’, the transaction 2 (the latest one) overrides the transaction 1.

* If OPTIMISTIC_LOCKING=‘QUORUM’,  the transaction 1 locks the instance until the transaction is committed and updates at least 2 nodes of each DC.

* If OPTIMISTIC_LOCKING=‘LOCAL QUORUM', the transaction 1 locks the instance until the transaction is committed and updates at least 2 nodes of DC1.

<img src="images/23_02_1.PNG" alt="image" style="zoom: 67%;" />

### Asynchronous Mode

This transaction can be populated in the delta table of the iidFinder mechanism whereby the delta table is created under the [Cassandra **k2staging** keyspace](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md). 

The asynchronous mode can be using the following command:

~~~
set ASYNC_TRX=true;
~~~

**Example**

~~~
> set async_trx=true;
> begin;
> get Customer.22;
//Run the insert/update/delete command
> commit;
~~~

Notes:

* The transaction is added to the delta table “as is” without any validation.

* The **set async_trx** command must be executed outside the transaction.

* The get of the LUI must run inside the transaction.



[![Previous](/articles/images/Previous.png)](01_fabric_transactions_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_update_lui_code_examples.md)

