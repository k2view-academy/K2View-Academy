<web>

# Centralized Catalog for Multiple Fabrics

### Overview

Starting from V8.2, it is possible to configure one centralized Catalog for multiple Fabric instances. This is useful when, for example, several users need to work on the same project in parallel. Working in separate Fabric instances (e.g. spaces), the users can define different Catalog settings and run the Discovery independently one from another on different interfaces. At the end, the Catalog artifacts from each Fabric can be combined together, as explained [here](09_build_artifacts.md#splitting-and-combining-artifacts). 

In addition, the setup allows that some users will be connected to the Catalog in a read-only mode. They will be able to view the Catalog tree but not to run the Discovery job or do any manual overrides. These users will have read-only permissions in Neo4j only, while they still will be able to update the Catalog Settings and create an artifact in the Fabric instance.

Utilizing this feature requires creating a Central Neo4j space and the Child spaces that will point to the “Central Neo4j” GraphDB. The steps how to do it are described below.

### Setup Steps

The following steps should be performed to configure a centralized Catalog for multiple Fabric instances:

**Step 1**: Creation of a Central Catalog's space. 

- Create the Central Neo4j space using a dedicated space profile.

**Step 2**: Creation of a 'Child' space with a regular user.  

- Prior to creating a 'Child' space, open the Advanced Settings of your Project's profile:

  <img src="images/profile_advaced.png"  />

- Add the following to the config.ini section of the Advanced Settings:

  ~~~
  [data_discovery]
  CENTRAL_SPACE_NAME_TENANT_NAME=<space_name-tenant_name>
  ~~~

- The ```<space_name-tenant_name>``` is the Central Neo4j space and tenant name.

- Create the 'Child' space. This space will point to the Central Neo4j GraphDB rather than to its local one.

**Step 3 (optional)**: Creation of a 'Child' space with a  read-only Neo4j user. 

- Prior to creating a 'Child' space, open the Advanced Settings of your Project's profile:


  <img src="images/profile_advaced.png"  />

- Add the following to the config.ini section of the Advanced Settings:

  ~~~
  [data_discovery]
  CENTRAL_SPACE_NAME_TENANT_NAME=<space_name-tenant_name>
  IS_READONLY=Y
  ~~~

- The ```<space_name-tenant_name>``` is the Central Neo4j space and tenant name.

- Create the 'Child' space. This space will point to the Central Neo4j GraphDB rather than to its local one and it will connect to it using the default read-only user.

  - Note that the read-only user is created automatically upon the first run of the Discovery job (in a regular space). So, it is possible to connect to the "read-only space" only after the first Discovery run.


</web>









