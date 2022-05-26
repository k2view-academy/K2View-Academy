# Search Configuration

### Fabric Config.ini File

Configure the [CDC sections](/articles/18_fabric_cdc/06_cdc_configuration.md) including the **cdc_data_consume** sections. The cdc_data_consume sections are used by the 

### Elasticsearch Configuration

Edit the **elasticsearch.yml** configuration file parameters:

- network.host: populated by the Elasticsearch IP address.

- discovery.seed_hosts: [Populated by the Elasticsearch IP address]. For example:
  - discovery.seed_hosts: ["localhost"]

- Add the following parameter to the Memory section:
  - **bootstrap.system_call_filter: false**

Note that an installation of Elasticsearch is required. The Elasticsearch is not included in the Fabric installation package.



[![Previous](/articles/images/Previous.png)](06_search_solution_limitations.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](08_search_troubleshooting.md)
