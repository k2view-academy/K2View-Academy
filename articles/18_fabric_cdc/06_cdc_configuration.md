# CDC Configuration

All of the Kafka connection settings are defined in the **[default_pubsub]** section of the config.ini and are applicable across various Fabric processes, including the CDC connection to Kafka.

When different Kafka settings for CDC are required, use the **[cdc]** section. This section does not have to include all the parameters, but only those that should override the default section's settings. 

[Click for more information about PubSub Configuration](/articles/24_non_DB_interfaces/02a_pubsub_config.md).



[![Previous](/articles/images/Previous.png)](05_cdc_consumers_implementation.md)
