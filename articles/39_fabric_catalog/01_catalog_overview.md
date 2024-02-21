# Fabric Catalog Overview

Today, nearly all companies are required to combine and integrate data from multiple sources, while collecting huge amounts of data on their customers, markets, suppliers, etc. Each day, more diverse sources and new data formats are added. Many of these data sources are neither well structured nor have predefined relations. As a result, decision makers are drowning in data and yet seeking for insights. On top of that, the ever-growing data security risks cause companies to give this issue a higher priority.

Data Discovery is a process that can help companies cope with the above-mentioned issues. Its purpose is to:

* Keep pace with the ongoing changes and adapt to them quickly.
* Uncover and investigate hidden yet potentially useful data insights.
* Find and flag sensitive information, enabling a better protection of it.

Fabric's Discovery and Catalog solution provides an insight into the Fabric interfaces, starting with the RDBMS interface type. The Discovery process includes the following steps:

- Auto-discovery of the data source's elements (schemas, tables, fields) and the existing PK-FK relations between them, while modeling the data source in the *neo4j* GraphDB. 
- Enrichment of the data model by creating additional relations between the data source elements when the PK-FK relations don’t exist. 
- Auto-profiling of the data model elements by both the metadata (field name) and data (field value) to identify PII information and classify it based on pre-defined categories. The auto-profiling process uses a set of pre-defined rules, which can be modified on the project level.

The Discovery process results are presented in a new Catalog application, which allows to:

- Display the Catalog data model as a tree and navigate through its elements.
- View the Catalog’s elements properties and edit them manually, if needed.
- Support multiple Catalog versions and their comparison.
- Search any node by either keywords or an advanced search parameters.
- View and modify the profiling rules.

The Logical Unit creation is based on the discovered and enriched data model. The Masking mechanism can be based on the Catalog profiling and classification.

The Catalog information - including the data model, properties and classifications - is exposed via the Fabric REST APIs.

<studio>

Note that when using the .NET Studio, few setup steps must be taken prior to running the Discovery process. Some solution limitations are applied, for example the Logical Unit creation in the .NET Studio cannot be based on the Catalog.

</studio>

Further articles provide more details about the Discovery process, the Catalog application and the exposed APIs. 

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_catalog_vocabulary.md) 


