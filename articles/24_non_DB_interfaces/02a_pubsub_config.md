# PubSub Configuration Interface

### Overview

A PubSub Configuration interface type defines the Fabric connection to Apache Kafka using the Kafka abstraction layer. The configuration is performed via the **[default_pubsub]** or additional custom sections of config.ini, as described further in this article.

The PubSub Configuration interface replaces the existing [Kafka Interface](03_kafka_interface.md). Kafka interface is still supported for backward compatibility. For more details about moving the existing project to the new abstraction layer configuration, refer to the Fabric Upgrade Procedure to 7.0.

### Default PubSub Settings

All the Kafka connection settings are defined in the **[default_pubsub]** section of config.ini and not in the interface. The **[default_pubsub]** section allows to define the Kafka settings in one location and apply them across various Fabric processes. 

The only parameter included in the interface definition is the **Config Section**. It holds the name of the config.ini section(s) where the connection settings are defined. By default, it is set to **default_pubsub**. 

When it is required to have different Kafka settings for different processes, it can be achieved by creating additional section(s) in the config.ini and adding their names to the **Config Section** parameter of the interface, separated by comma. The first section name from the list that is found in the config.ini will be used. If for any reason the section name defined in the interface does not exist in the config.ini, the settings will be taken from the **[default_pubsub]** section. The new section does not have to include all the parameters, but only those which override the default section's settings.

The **[default_pubsub]** section is also used by CDC and Common DB processes for the same purpose of connecting to Kafka. For more details about overriding the CDC and Common DB connection settings, refer to the [CDC Configuration](/articles/18_fabric_cdc/06_cdc_configuration.md) and [CommonDB Configuration](/articles/22_reference(commonDB)_tables/07_fabric_commonDB_configuration.md) articles.

### Parameters Description

The main configuration setting of the **[default_pubsub]** section of config.ini are:

* **TYPE**, can be either KAFKA (default) or MEMORY (internal queue which runs on localhost and can be used for development or debug purpose only).
* **BOOTSTRAP_SERVERS**, holds the IP address of the Kafka servers. It is possible to populate several IP addresses separated by a comma.
* **TRANSACTION_MODE**, determines how the publisher handles transactions. There are three modes:
  * **ASYNC** (default). Messages are sent asynchronously. Acknowledgement is received only after commit is performed. Async mode doesn't guarantee that all messages are committed.
  * **BROKER**. The transactional producer allows to send data to multiple partitions and guarantees all these writes are either committed or discarded. This is done by grouping multiple calls to send into a transaction. Once a transaction is started, you can either commit or rollback to complete it.
  * **IGNORE**. Messages are sent synchronously. Commit is done one by one.
* Security protocol and other SSL/SASL properties, mandatory to be set when Kafka is defined with either SSL or SASL. 
  * The supported protocols are: PLAINTEXT, SSL, SASL_PLAINTEXT, SASL_SSL.
  * The supported SASL flavors are: SASL_SCRAM, SASL_LDAP, SASL_GSSAPI, SASL_PLAIN.

### Group ID, Topic and Partitions

The **GROUP_ID**, **TOPIC** and **PARTITIONS** parameters are mandatory when setting up Kafka. These parameters should either be defined as:

* The [Pub / Sub Actors](/articles/19_Broadway/actors/04_queue_actors.md)' input arguments, or 
* In the custom section of config.ini while adding the section name to the PubSub Configuration interface that the Actor is using. 

If the GROUP_ID, TOPIC and PARTITIONS are not found in either of the above, the exception will be thrown at run time.



[![Previous](/articles/images/Previous.png)](02_SFTP_interface.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_kafka_interface.md) 
