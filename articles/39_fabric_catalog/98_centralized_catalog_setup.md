# Centralized Catalog for Multiple Fabric Instances

### Overview

Starting from V8.2, you can now configure a centralized Catalog for multiple Fabric instances, offering significant advantages for collaborative projects. This setup is particularly useful when multiple users need to work on the same project simultaneously. By operating on separate Fabric instances, users can define different Catalog settings—such as unique regular expressions for data classification—and run Discovery processes independently, each accessing distinct data sources.

All Fabric instances are linked to a single Neo4j GraphDB, allowing users to view the combined Catalog. Moreover, certain Fabric instances can be configured to connect to the Catalog in a read-only mode. In this mode, users can browse the Catalog tree but cannot initiate Discovery jobs or perform manual overrides. Despite having only read-only permissions in Neo4j, these users can still update Catalog settings and create artifacts within their Fabric instance.

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











