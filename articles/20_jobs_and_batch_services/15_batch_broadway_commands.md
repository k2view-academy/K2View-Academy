# Broadway Flows Execution


## Use Case

A Broadway Batch process can be executed to loop through a list of external parameters such as a list of payments records (of a specific set of instances), or over a list of files. 
In addition, this process can benefit from all Batch configuration parameters, such as nodes [affinity, capacity (max_nodes, max_workers_per_node) and multiplicity factor (allow_multiply)](/articles/20_jobs_and_batch_services/12_batch_sync_commands.md#batch-ludc-fabric_commandfabric-command--with-affinityaffinity-job_affinityjob-affinity-asynctruefalse-generate_entities_firsttruefalse-allow_multiplytruefalse-max_workers_per_nodenumberh6-).



## Broadway Batch Commands
The *FABRIC_COMMAND* parameter must be set using the usual [Broadway command](/articles/19_Broadway/17_tutorial_and_flow_examples.md) syntax.

Example:
```BATCH CUSTOMER FABRIC_COMMAND="broadway LU.SampleFlow SampleIID=?" with async=true;```



[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/14_instances_groups.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/16_batch_CDC_commands.md)




