# Broadway Flows Execution


## Use Case

A Broadway Batch process can be executed to loop through a list of external parameters such as a list of payments records (of a specific set of instances), or over a list of files. 

Broadway batch processes can also be run on multiple instances using Instance Groups or embedded SQL statements as described in the [Batch Commands](/articles/20_jobs_and_batch_services/12_batch_sync_commands.md) section.

In addition, this process can benefit from all [Batch configuration parameters](/articles/20_jobs_and_batch_services/12_batch_sync_commands.md), such as nodes affinity, capacity (max_nodes, max_workers_per_node) and the ability to run multiple executions of the same Batch process (allow_multiple).

## Broadway Batch Commands
The *FABRIC_COMMAND* parameter must be set using the Broadway command syntax as described below:

**Example:**


```BATCH CUSTOMER.customer_IG_600To700 FABRIC_COMMAND="broadway Customer.SampleFlow customer_id=?" with async=true;```

Refer to the [Batch Command](/articles/20_jobs_and_batch_services/12_batch_sync_commands.md#batch-commands-summary) description.


[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/14_instances_groups.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/16_batch_CDC_commands.md)



