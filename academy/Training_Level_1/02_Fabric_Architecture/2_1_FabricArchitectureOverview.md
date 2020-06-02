# Fabric Architecture Overview

Three different kind of actors interact when using Fabric:

 

**Sources**

These refer to external databases or data streams that you will be using in order to design and extract the digital entities information they contain. Fabric supports the most popular types of DB (relational or unstructured) from a large set of technology providers. 

In addition, Fabric can connect directly to live data streams or to consume messages from stream-processing platforms such as Kafka by subscribing to providers topics

 

**Fabric server**

The server provides 4 main services:

\-     Management of the migration of data from external sources to Fabric Storage for later processing 

\-     Provisioning of traditional ETL functions to retrieve data necessary to populate Logical Units instances

\-     Access control through authentications, authorization and encryption capabilities

\-     Data publishing through web-services API 

 

**Fabric User Interfaces**

We distinguish 3 types of applications:

\-     The Fabric studio that provides a full development environment to create Logical Units and to code any java functions (or SQL queries) needed in order to operate data manipulations.

\-     The Admin panel where all RBAC, connections and security settings happen

\-     Testing suite

 

Now, let’s look deeper into these individual components, their dependencies and how they relate one to the other, by reading the following document:

*Fabric Architecture* 



[![img](https://github.com/k2view-academy/K2View-Academy/raw/master/articles/images/Next.png)](https://github.com/k2view-academy/K2View-Academy/wiki/FabricArchitectureOverview)

 



​                                