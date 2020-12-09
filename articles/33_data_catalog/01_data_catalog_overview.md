# Data Catalog Overview

Fabric Data Catalog is a graphical tool that visualizes the Project’s data structure and relationships between the entities. 

![image](images/33_01_tree.PNG)

The Data Catalog describes how an organization collects, transforms and stores its data inventory. Due to its user-friendly UI, the catalog enables users to follow a data flow from its source to its target and by that drastically increases a user’s comprehension of an organization’s data model. For example, when developing a Web Service, it can assist to clarify the source system of the Web Service’s required output fields.

Moreover, the solution answers data privacy requirements for compliance with GDPR, CCPA and other global data protection regulations by enabling users to know how Fabric brings data from source systems, where in Fabric it is stored and how it is published externally.

### Data Catalog Architecture

![image](images/33_architecture.PNG)

Data Catalog is a web application based on a distributed graph database OrientDB (Apache 2). The Fabric creates a JSON-format representation of the project, deploys it to the Fabric server and uses the OrientDB for creating and saving the graph structure. Then the catalog becomes available for the Web Framework for navigation.

[Click for more information about the E2E Catalog creation process](02_e2e_catalog_creation_process).

### Data Catalog Components

The Data Catalog introduces a list of data entities with relations between them. The data entities are Project, Interface, Schema, Table, Column, etc. 

The data relations indicate the connections between the data entities that determine their hierarchy. For example:

* *{Interface} CONTAINED in a {Project}*
* *{Schema} CONTAINED in a {Project}*
* *{Api} USE a {Table}*

The entities and the connections between them are used to parse the data, analyze it and display it from the Project level, through the Schema to the API and Table fields level. 

The solution has a Build Catalog option that creates JSON format files representing the entire project. The automatic catalog files can be overridden if needed.

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_e2e_catalog_creation_process.md)

