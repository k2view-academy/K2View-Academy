# Broadway Flows Execution


## Use Case

The Batch command can be used to run multiple broadway flows on different instances usinf Instance Groups or embedded SQL statements as described in the [Batch Commands](/articles/20_jobs_and_batch_services/12_batch_sync_commands.md) section.

In addition, this process can benefit from all Batch configuration parameters, such as nodes affinity, capacity (max_nodes, max_workers_per_node) and multiplicity factor (allow_multiply).

## Broadway Batch Commands
The *FABRIC_COMMAND* parameter must be set using the usual [Broadway command](/articles/19_Broadway/17_tutorial_and_flow_examples.md) syntax.

```BATCH LUT[@<DC>].<IG> FABRIC_COMMAND="broadway LU.SampleFlow SampleIID=?" with async=true;```



[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/13_migrate_commands.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/15_batch_CDC_commands.md)




