# CDC Republish Execution


## Use Case

The Batch command can be used to run the cdc_republish_instance command on different instances using Instance Groups or embedded SQL statements as described in the [Batch Commands](/articles/20_jobs_and_batch_services/12_batch_sync_commands.md) section.

In addition, this process can benefit from all Batch configuration parameters, such as nodes affinity, capacity (max_nodes, max_workers_per_node) and multiplicity factor (allow_multiply).

## CDC Batch Commands
The *FABRIC_COMMAND* parameter must be set using the usual [CDC_republish_instance](/articles/18_cdc_and_search/02_cdc_messages.md) syntax.

```BATCH LUT[@<DC>].<IG>  FABRIC_COMMAND="cdc_republish_instance OracleLU.?" with async=true;```


[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/14_batch_broadway_commands.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/16_batch_process_flow.md)
