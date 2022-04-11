# Legacy Support

## Migrate Commands

The Migrate command is a specific use-case of the Batch command which deals exclusively with the migration of instances into the Fabric database.

```MIGRATE LU[@<DC>] with ASYNC='true';```

Behind the scenes, Fabric activates the Batch command when running the Migrate command. 
All the verbs defined for the [Batch process commands](/articles/20_jobs_and_batch_services/12_batch_sync_commands.md#batch-commands-summary) can be applied to the Migrate command **without specifying the FABRIC_COMMAND parameter**.

For example:
The following two commands are equivallent.

```BATCH <LU>[@<DC>] FABRIC_COMMAND='<fabric command> ?';``` is the same as ```migrate <LU>[@<DC>];```

Using the same example as in previous article, using the same Instance Group pertaining to the Customer LU **customer_IG_600To700**:
```MIGRATE Customer.customer_IG_600To700;```
The results are the same as when running the Batch command: 

```BATCH Customer.customer_IG_600To700 FABRIC_COMMAND="sync_instance CUSTOMER.?";```

```
|Added|Updated|Unchanged|Failed|Total|Duration|
+-----+-------+---------+------+-----+--------+
|99   |0      |0        |0     |99   |875     |
```


## Migrate Monitoring Commands 

The Migrate and Migrate Monitoring commands do operate in the exact same way and with the same parameters as the batch command. Click [here](/articles/20_jobs_and_batch_services/12_batch_sync_commands.md#batch-monitoring-commands-summary) for a comprehensive list of the all the parameters and options available.



## Invoking an Instance Group from the Migrate Command 
As for the Batch command, the [Instance Group](/articles/20_jobs_and_batch_services/14_instances_groups.md) can be created from within Fabric Studio.


[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/12_batch_sync_commands.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/14_instances_groups.md)
