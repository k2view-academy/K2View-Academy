# Centralized Catalog for Multiple Fabric Instances

### Overview

Starting from V8.2, it is possible to configure one centralized Catalog for multiple Fabric instances.

This is useful when, for example, several users need to work on the same project in parallel. Working on separate Fabric instance, the users can define different Catalog settings (e.g. different regular expressions for data classification) and run the Discovery independently from one another, on different data sources.

Since all Fabric instances are pointing to a single Neo4j GraphDB, each user can view the combined Catalog. 

In addition, some of the Fabric instances can be connected to the Catalog in a read-only mode, that is, they will be able to view the Catalog tree but would not be able to run the Discovery job or perform any manual overrides. Having only read-only permissions in the Neo4j, these users would still be able to update the Catalog Settings and create an artifact in the Fabric instance.

![](images/central_arc.png)



### Setup Steps

The following steps should be performed to configure a centralized Catalog for multiple Fabric instances:

**Step 1**: Creation of a Central instance. 

- Create the Central Neo4j.

**Step 2**: Setup of a client Fabric instance with a regular Neo4j user.  

- A client Fabric instance should point to the Central Neo4j instead of the local one. 

  - To achieve it, update the client Fabric instance's ```GRAPH_DB_URL``` parameter in the [discovery] section of the config.ini file to the URL of the Central Neo4j. 
  - It is recommended to create a new Neo4j user and password instead of the default one. Connect to the Central Neo4j cypher-shell to create a new Neo4j user with read & write permissions using the following commands:

  ~~~
  CREATE USER <USER_NAME> SET PASSWORD '<PASSWORD>' CHANGE NOT REQUIRED;
  GRANT ROLE architect TO <USER_NAME>;
  GRANT ACCESS ON DATABASE neo4j TO architect;
  ~~~

- Update the client Fabric instance's parameter in the [discovery] section of the config.ini file to the URL of the Central Neo4j.  In addition, set ```GRAPH_DB_USER``` and ```GRAPH_DB_PASSWORD``` parameters to the credentials of the newly created user:

  ~~~
  [data_discovery]
  GRAPH_DB_URL=<Central Neo4j URL>
  GRAPH_DB_USER=<User Name>
  GRAPH_DB_PASSWORD=<Password>
  ~~~

**Step 3 (optional)**: Setup of a client Fabric instance with a read-only permissions for the Neo4j user. 

- A client Fabric instance should point to the Central Neo4j instead of the local one. In addition, it should be connected to Neo4j using the Neo4j user with read-only permissions.

  - Connect to the Central Neo4j cypher-shell to create a new Neo4j user with read-only permissions:

  ~~~
  CREATE USER <READ_ONLY_USER> SET PASSWORD '<READ_ONLY_PASS>' CHANGE NOT REQUIRED;
  GRANT ROLE reader TO <READ_ONLY_USER>;
  GRANT ACCESS ON DATABASE neo4j TO reader;
  ~~~

- Update the client Fabric instance's parameter in the [discovery] section of the config.ini file to the URL of the Central Neo4j. In addition, set ```GRAPH_DB_USER``` and ```GRAPH_DB_PASSWORD``` parameters to the credentials of the newly created user:

  ~~~
  [data_discovery]
  GRAPH_DB_URL=<Central Neo4j URL>
  GRAPH_DB_USER=<Name of user with read-only permissions>
  GRAPH_DB_PASSWORD=<Password of user with read-only permissions>
  ~~~


- Note: this client will have the read-only permissions only in Neo4j, while still being able to update the Catalog Settings and create an artifact in the Fabric instance.











