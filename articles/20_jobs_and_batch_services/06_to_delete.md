# How Do I Create a New CDC Job?

## Purpose
Fabric can execute [CDC](/articles/18_cdc_and_search/02_cdc_messages.md) Republish Jobs (Change Data Capture) to re-publish LU tables data for a given list of instances.

Jobs can also execute cross-instance searches using Fabric's Search capability - (Search command syntax should be used).

## Job Type
Set the **Job** type to **cdc_republish_instance_job**

## Example:
Referring to the [Data Republish Example](/articles/18_cdc_and_search/02_cdc_messages.md#data-republish) in the CDC section, the [startjob](/articles/20_jobs_and_batch_services/07_jobs_commands.md#startjob-jobtype-namename-uiduid-affinityaffinity-argsargs-exec_intervalexecinterval) command will be as follow:

```
startjob cdc_republish_instance_job CTN.1 tables='T1,address'
```



[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/05_create_a_new_broadway_job.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/07_jobs_commands.md)
