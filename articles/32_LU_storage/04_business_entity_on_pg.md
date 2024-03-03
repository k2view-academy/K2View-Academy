# Business Entity on PostgreSQL

Fabric can use PostgreSQL as a Logical Unit's storage layer, where each business entity's instance is saved in separate rows in PostgreSQL.

This functionality should be used when the main use case is driven mostly from cross-instance queries, server reportings, dashboards or data analytics systems.

This is the preffered option when asking the following:

1. Query the number of customers per credit class.
2. List the customers with over payments.
3. Query how many customers live in New York city.

The Logical Unit's data is saved in PostgreSQL in 2 different schemas:

1. __{Logical Unit Name}, contains all the Logical Unit table names and data per instance; each table holds:

   1.1 __iid + Logical Unit table's original PK as the PostgreSQL table's primary key.

2. {Logical Unit Name}, contains views on all the Logical Unit tables for Fabric's internal use.

This structure opesn the ability to query data in the Business Entity's instance level when needed.

When using the DbLoad Actor in Broadway, it is required to use the batch mode in order to ensure good sync performance results.

The common tables solution is aligned with this functionality, thus the data is saved under {common table schema name} or common, if the schema is set to default.

This mode is supported in the system level; it is not possible to store some of the Logical Units as MicroDB and the others on PostgreSQL.

Data can be encrypted using PostgreSQL encryption at several levels. This encryption provides flexibility in protecting data from disclosure (due to database server theft), unscrupulous administrators and insecure networks. Encryption may also be required to secure sensitive data, such as medical records or financial transactions. For further reading, click [here](https://www.postgresql.org/docs/current/encryption-options.html).

### Configuration

In order to turn this functionality on, the following steps should be taken:

1. Set the System DB to PostgreSQL, [click for more information about Fabric System Database setup](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md).
2. Set FABRICDB_ENGINE=postgresql in the **[fabric]** section of the **config.ini** file.
3. Restart Fabric.

### Business Entity on PostgreSQL vs CDC on Elasticsearch

For the purpose of getting data of cross-business entities' instances, it is possible to publish data to Elasticsearch by using the Fabric CDC solution.

Business Entity on PostgreSQL is the preferred solution, as it requires to maintain one DB less (no Elasticsearch); however, if scalability is crutual, the Fabric CDC data publishing to Elasticsearch solution should be considered.

### Limitations

Since data is not stored as a MicroDB, the following Fabric capabilities are not supported in this mode:

1. Logical Unit DB viewer.
2. Trigger functions.
3. LUDB functions.
4. Lookup tables must be defined in a string type.
5. When using System of record functionality, it is required to specify the field names.
6. The Logical Unit table's delete mode property doesn't support a 'Not updated' value.
7. In order to change an LU table column type, it is required drop and redeploy the LU.
8. Logical Unit table column collation property supports only BINARY value.
9. CDC is not supported.
10. Partial LUI encryption is not supported.
11. TTL is not supported.
12. Index can't include more than 32 columns.
13. Logical Unit table names, column names and schema names can't exceed 63 characters.

[![Previous](/articles/images/Previous.png)](03_big_lu_storage.md)







