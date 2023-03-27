# CDC Republish Execution


## Use Case

The Batch command can be used to run the cdc_republish_instance command on different instances using Instance Groups or embedded SQL statements as described in the [Batch Commands](/articles/20_jobs_and_batch_services/12_batch_sync_commands.md) section.
For example, if an external system became out-of-sync, it can send a request to Fabric to republish all the instances that were modified, and Fabric will use the batch process to execute this request. 

In addition, this process can benefit from all [Batch configuration parameters](/articles/20_jobs_and_batch_services/12_batch_sync_commands.md), such as nodes affinity, capacity (max_nodes, max_workers_per_node) and multiplicity factor (allow_multiple).

## CDC Batch Commands
The *FABRIC_COMMAND* parameter must be set using the usual [CDC_republish_instance](/articles/18_fabric_cdc/04_cdc_publication_flow.md#cdc_republish_instance) syntax.

```BATCH LUT[@<DC>].<IG>  FABRIC_COMMAND="cdc_republish_instance CUSTOMER.?" with async=true;```


[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/15_batch_broadway_commands.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/17_batch_process_flow.md)
