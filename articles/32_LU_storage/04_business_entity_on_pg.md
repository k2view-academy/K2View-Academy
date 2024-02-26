# Business Entity on PostgreSQL

Fabric can use PostgreSQL as a Logical Unit's storage layer, where each business entity's instance is saved as separate rows in PostgreSQL.

This functionality should be used when the main use case is driven mostly from cross-instance queries, server reportings, dashboards or analytics systems.

This is the preffered option when asking the following:

1. Query the number of customers per credit class.
2. List the customers with over payments.
3. Query how many customers live in New York city.

The Logical Unit's data is saved in PostgreSQL in 2 different schemas:

1. __{Logical Unit Name}, contains all the Logical Unit table names and data per instance; each table holds:

   1.1 __iid + Logical Unit table original PK as the PostgreSQL table's primary key.

2. {Logical Unit Name}, contains views on all the Logical Unit tables for Fabric's internal use.

This structure open the ability to query data in the Business Entity's instance level when needed.

When using DbLoad Actor in Broadway, it is required to use batch mode in order to ensure good sync performance results.

Common tables solution is aligned with this functionality so the data is saved under {common table schema name} or common if the schema is set to default.

This mode is supported in the system level, it is not possible to store some of the Logical Units as MicroDB and the others on PostgreSQL.

Data can be encrypted using PostgreSQL encryption at several levels capabilities, it provides flexibility in protecting data from disclosure due to database server theft, unscrupulous administrators, and insecure networks. Encryption might also be required to secure sensitive data such as medical records or financial transactions. You can read further [here](https://www.postgresql.org/docs/current/encryption-options.html).

### Configuration

In order to turn this functionality on the following should be done:

1. Set the System DB to PostgreSQL, [click for more information about Fabric System Database setup](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md).
2. Set FABRICDB_ENGINE=postgresql in the **[fabric]** section of the **config.ini** file.
3. Restart Fabric.

### Business Entity on PostgreSQL vs CDC on Elastic Search

In order to get cross business entities's instances data it is possible to publish data to  Elastic Search using Fabric CDC solution.

Business Entity on PostgreSQL is the preferred solution, as it requires to maintain one DB less (no Elastic Search), however if scalability is crutual, Fabric CDC to Elastic Search solution should be considered.

### Limitations

Since data is not stored as a MicroDB the following Fabric capabilities are not supported in this mode:

1. Logical Unit DB viewer.
2. Trigger functions.
3. LUDB functions.
4. Lookup tables must defined in String type.
5. When using System of record functionality, it is required to specify the fields names.
6. Logical Unit table delete mode property doesn't support Not updated value.
7. In order to change LU table column type, it is required drop and redeploy the LU.
8. Logical Unit table column collation property supports only BINARY value.
9. Index can't include more than 32 columns.
10. Logical Unit tables names, column names and schema names can't exceed 63 characters.

[![Previous](/articles/images/Previous.png)](03_big_lu_storage.md)







