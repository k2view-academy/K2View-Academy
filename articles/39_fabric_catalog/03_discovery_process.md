# E2E Discovery Process

The K2View Discovery process is depicted in the below diagram and it includes the following major steps:

* Define a Fabric interface for a given data source and initiate the Discovery job.
* The first step of the Discovery job is a Crawler. It scans the data source, while identifying the existing entities and the relationships between them. The Crawler's output is the Discovery Schema.
* Next, a Plugin Framework is triggered automatically upon the Crawler completion. The Plugin Framework is a platform for executing predefined rules (plugins) to run on the Discovery Schema and to enhance it accordingly. Examples of plugins are: 
  * Creating a link (relation) between 2 objects based on matching the field names.
  * Classification of the fields based on their data or metadata as Email, Phone, etc.
  * Determining whether a field holds sensitive data and thus should be marked as PII.


* Upon the Plugin Framework execution completion, the Discovery Schema is saved into the *neo4j* Graph DB and can be viewed via the Catalog application.

The Catalog support the following actions:


* Versioning - a new version is created when the Discovery job is executed and the changes from the previous version are found. By default, the Catalog application displays the latest version.
* Manual overrides - Edit Catalog is available for updating the properties and the relations created by the Discovery job.
* The Catalog artifacts (including the identified classifications and PII indications) can be built and saved into the project tree, in order to be used by the masking mechanism. 
* Once the process is completed, the Logical Unit schema can be created based on the Catalog's version.

![](images/DiscoveryE2E.png)

Further articles in this section provide more details about each of the above steps and activities.



[![Previous](/articles/images/Previous.png)](02_catalog_vocabulary.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_plugin_framework.md) 

