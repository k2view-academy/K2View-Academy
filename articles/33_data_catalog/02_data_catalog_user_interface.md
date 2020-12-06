# Data Catalog User Interface

The Data Catalog is a web application based on a distributed graph database (OLTP) – OrientDb (Apache 2). It can be accessed from the [K2View Web Framework](/articles/30_web_framework/01_web_framework_overview.md) and includes two parts:

* Data Dictionary introduces a list of data entities with relations between them. The data entities are Project, Interface, Schema, Table, Column, etc. The relations are, for example, *at least one table for each schema*.

* Data Lineage indicates the connections between the data entities that determine their hierarchy. For example:
  * *{Interface} CONTAINED in a {Project}*
  * *{Schema} CONTAINED in a {Project}*
  * *{Table level x} POPULATION_KEYS a {Table level x-1}*
  * *{Api} USE a {Table}*

The entities, the relationships and the connections between them are used to parse the data, analyze it and display it from the project (highest level), through the schema to the API level and table fields. 

The catalog's UI enables the users to move through a data model’s graph, zoom in / out from one level to another and filter specific levels. The solution also has a Build Catalog option that creates a JSON file representing an entire project.



[![Previous](/articles/images/Previous.png)](01_data_catalog_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](xxx.md) 