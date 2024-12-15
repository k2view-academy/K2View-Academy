<web>

# Centralized Catalog for Multiple Fabrics

### Overview

Starting from V8.2, it is possible to configure one centralized Catalog for multiple Fabric instances. This is useful when, for example, several users need to work on the same project with multiple interfaces. Working in separate Fabric instances (e.g. spaces), the users can define different Catalog settings and run the Discovery separately one from another. At the end, the Catalog artifacts can be combined, as explained [here](09_build_artifacts.md#splitting-and-combining-artifacts). 

In addition, some users may be connected to the Catalog in a read-only mode. They will be able view the Catalog tree but not update it by running the Discovery or by any manual overrides. These users will have read-only permissions only in Neo4j, while they still will be able to update the Catalog Settings and create an artifact in the Fabric instance.

Utilizing this feature requires creating a “Central Neo4j” space as well as other spaces that will point to the “Central Neo4j” GraphDB. In addition, a read-only Neo4j user can be created, so that some of the users will be connected to the Catalog as readers only. The steps how to do it are described below.

### Setup Steps

The following steps should be performed to configure a centralized Catalog for multiple Fabric instances:

**Step 1**: Creation of a centralized Catalog's space. 

- Create the Central Neo4j space using a dedicated space profile.

**Step 2**: Creation of the 'child' spaces using a regular space profile.  

- Prior to creating the 'child' spaces, update the Advanced Settings of your space profile, by setting the following in the config.ini section:

  ~~~
  [data_discovery]
  GRAPH_DB_URL=neo4j://neo4j-service.<space_name-tenant_name>.svc.cluster.local:7687
  ~~~

- The ```<space_name-tenant_name>``` should be replaced by the Central Neo4j space name.

- Create the 'child' spaces. They will now point to the Central Neo4j rather than to their local ones.

**Step 3 (optional)**: Creation of a read-only Neo4j user. 

- Prior to creating the read-only 'child' space, create a read-only Neo4j user  - TBD.

- Add the user's credentials via the Advanced Settings of your space profile, by setting the following in the config.ini section:

  ~~~
  [data_discovery]
  GRAPH_DB_URL=neo4j://neo4j-service.<space_name-tenant_name>.svc.cluster.local:7687

  GRAPH_DB_USER=<read-only user name>
  GRAPH_DB_PASSWORD=<read-only user password>
  ~~~

- Create the read-only 'child' spaces. They will connect to the Central Neo4j using a read-only user.



</web>









