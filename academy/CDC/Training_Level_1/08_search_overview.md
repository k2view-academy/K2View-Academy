# Search Overview

### What Will You Experience In This Learning Item?

By the end of the Search Introduction learning item you will know about:

- Searches and use cases.
- Search implementation and configuration.
- Fabric's built-in integration with Elasticsearch.
- Search limitations.

To get started with CDC, please read [Search Overview and Use Cases](/articles/18_fabric_cdc/cdc_consumers/search/01_search_overview_and_use_cases.md).

### Search Configuration

The Search configuration has two main parts:

- Configuration of the CDC sections in the Fabric config.ini file.
- Configuration of the Elasticsearch server.

#### Prerequisites

- Kafka and Zookeeper installations.
- Elasticsearch server installation. 

#### Fabric CDC Configuration

Read [CDC Configuration](/articles/18_fabric_cdc/06_cdc_configuration.md). Note that you must configure both the **cdc_data_publish** and **cdc_data_consume** sections to enable the Search functionality. 

#### Elasticsearch Configuration

Read [How to configure Elasticsearch](/articles/18_fabric_cdc/cdc_consumers/search/07_search_configuration.md#elasticsearch-configuration) to learn how to configure the Elasticsearch sever.

### Search Implementation

The Search implementation includes two main parts:

- Definition of a a Search interface to enable Fabric to connect to the Elasticsearch engine when running Search commands.
- Definition of Search fields of the selected LU tables columns.

Read [Search Implementation Steps](/articles/18_fabric_cdc/cdc_consumers/search/02_search_implementation.md) to learn how to implement a Search in a Fabric project.

#### Search Templates

In specific circumstances the default Search field types available in Fabric Studio may require special settings.

**Example:**

- Search by fields that contain special characters like an email address. By default, Elasticsearch splits a field with special characters into separate tokens. For example,  johndoe@gmail.com is split into **johndoe** and **gmail.com** tokens. As a result, searching by johndoe@gmail.com returns also johndoe or johndoe@yahoo.com. 

- johndoe@gmail.com matches JohnDoe@gmail.com, but does not match johndoe@yahoo.com.



Fabric enables adding templates for Search fields when the default settings do not match a search's needs. Define the Email field using the **case-insensitive-match.json** template in the example above.

Read [Search Templates](/articles/18_fabric_cdc/cdc_consumers/search/04_search_templates.md) to learn more about the Search Templates.

### Built-in Fabric Integration with Elasticsearch

A Fabric Search is based on the [CDC mechanism](/articles/18_fabric_cdc/01_change_data_capture_overview.md). The **CDC_TRANSACTION_CONSUMER** Fabric job consumes the Search topic from Kafka and updates Elasticsearch.

When deploying an LU with Search fields, Fabric creates indexes in Elasticsearch. Read [Creating Elasticsearch Indexes on Search Fields](/articles/18_fabric_cdc/cdc_consumers/search/03_creating_elasticsearch_indexes_on_search_fields.md) to learn more about the Elasticsearch indexes created for Search fields.

**CDC Delete Tables** and **CDC Table Change Info** [CDC Messages](/articles/18_fabric_cdc/03_cdc_messages.md) also update the data in Elasticsearch using the Search fields in each Fabric LUI.

### Search Limitations

Read [Search Limitations](/articles/18_fabric_cdc/cdc_consumers/search/06_search_solution_limitations.md) to learn more about Search limitations.

That's it, you now know how to configure and implement a Search in your project. Please go to the next lesson to learn how to run a search in Elasticsearch using the Search command.

[![Previous](/articles/images/Previous.png)](07_cdc_implementation_and_configuration_exercise_solution.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](09_search_command.md)
