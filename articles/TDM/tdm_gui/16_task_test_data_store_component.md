# Task - Test Data Store Component

The **Test Data Store** component is the [Fabric](/articles/TDM/tdm_architecture/01_tdm_architecture.md#fabric) ETL and storage layer, and it is mandatory on each task. It has 2 attributes:

## Create Data Snapshot Checkbox

Check the checkbox to create a [Data snapshot (version)](15_data_flux_task.md) for the extracted entities and/or tables. 

### Entities

By default this checkbox is cleared. The checkbox can be checked whenever the Source' **Policy for fetching data** gets the data from the the source environment or the task synthetically generates new entities. 

### Tables 

This checkbox is checked since each task execution that gets the data from the source environment,  creates a new data snapshot (version) on each extracted table.

## Retention Period

The retention period is set on the extracted entities and/or tables. When this period ends, the task's entities and/or tables are **automatically deleted** from Fabric and are no longer available. 

#### Retention Period Values

- **Do not delete** - do not delete from Fabric. 

- **Do not retain** - avoid saving the task's entities and/or tables in Fabric (instead of saving and deleting). This option can be used, for example, to run an extract task on a large subset of entities in order to populate the TDM parameter’s tables on each entity without saving the entities into Fabric.

- Setting of a time measuring unit (minutes/hours/days...) and a value. For example, save the data in Fabric for 2 days. After 2 days the data is automatically deleted from Fabric.

  Note that the **retention period** can be set in **minutes**, **hours**, **days**, **weeks** or **years**, depending on the maximum retention period set in the TDM DB. Both parameters - default retention period and maximum retention period - are set in the [TDM DB](/articles/TDM/tdm_configuration/02_tdmdb_general_parameters.md).

#### Default Retention Period

- Data versioning checkbox is clear - Do not Delete.
- Data versioning checkbox is checked - 
  - Entities: 5 days. 
  - Tables: Do not delete.


 [![Previous](/articles/images/Previous.png)](15_task_subset_component.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](17_task_target_component.md)

