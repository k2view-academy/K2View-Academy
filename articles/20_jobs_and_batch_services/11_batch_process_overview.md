# **Fabric Batch Overview** 
The Batch process is a Fabric utility that can be used to run multiple commands on a list of instances, regardless of its size or type. 

The Batch process is used to trigger the following:
- [Sync instances](/articles/14_sync_LU_instance/01_sync_LUI_overview.md), to perform multiple syncs on all (or a subset of) instances (IIDs) for a specific Logical Unit. This process is also referred to as the Migration Process, handled by the *migrate* command.
- [Broadway flow](/articles/19_Broadway/01_broadway_overview.md), to run a given flow on multiple instances.
- Republish data changes using the [CDC](/articles/18_fabric_cdc/01_change_data_capture_overview.md) notification mechanism.


# **Fabric Batch Features**
Fabric's integrated Batch process provides the following advantages to ensure the smooth execution of heavy-resources demanding processes:
- Efficient distribution of jobs between nodes.
- Dynamic adjusting of work balance configuration between nodes during execution phases.
- Monitoring of instances' synchronization progress (batch sync) at cluster, DC or node levels from the command line or using the [Batch Monitor Dashboard](/articles/20_jobs_and_batch_services/18_batch_monitor.md).
- Process failure recovering. (e.g non-responding nodes).
- Stopping and resuming of the migration process (LUI synchronization).
- Process tracking at entity level. (e.g. timing, duration, handling node or failure management).

# **Fabric Batch Use Cases**

**Instances Sync (Migration)**

```BATCH LU ('LUI','LUI2','LUI3','LUI4') FABRIC_COMMAND="sync_instance LU.?" with ASYNC='true';```

**Broadway Flows Execution**

```BATCH LU FABRIC_COMMAND="broadway LU.SampleFlow SampleIID=?" with async=true;```

**CDC Republish**

```BATCH LU from fabric FABRIC_COMMAND="cdc_republish_instance Customer.?" with async=true;```



[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/10_jobs_and_batches_affinity.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/12_batch_sync_commands.md)
