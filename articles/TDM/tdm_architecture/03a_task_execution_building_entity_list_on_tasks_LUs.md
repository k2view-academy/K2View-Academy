# Building Entity Lists on a Task's LUs

The task execution process executes the task's LUs from parent to child. 

Click for more information about the [execution order of hierarchical LUs](/articles/TDM/tdm_overview/03_business_entity_overview.md#task-execution-of-hierarchical-business-entities).

The task execution process builds an entity list for each LU: 

## Root LUs

### Extract Tasks

The entity list depends on the task:

- **[Select All Entities](/articles/TDM/tdm_gui/16_extract_task.md#select-all-entities)** is checked, run the SQL query or the Broadway flow defined in the [trnMigrateList](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md#trnmigratelist) translation object for the LU. Note that  the Broadway flow defined in the trnMigrateList object, populates a dedicated Cassandra table with the list of entities. TDM task execution process runs the batch command to sync the entities, populated in the Cassandra table with the task execution id of the current execution. 
- **Selected All Entities** is unchecked, build the entity list based on the entities in the task's [Requested Entities](/articles/TDM/tdm_gui/16_extract_task.md#requested-entities) tab.

### Load Tasks - Regular Mode

The entity list depends on the task's selection method in the [Requested Entities](/articles/TDM/tdm_gui/18_load_task_requested_entities_regular_mode.md) tab: 

- **Entities List**, build the entity list based on the entities in the task's Requested Entities tab.

- **Random Selection**, randomly select the entities from the  [`<LU Name>_<params>`](/articles/TDM/tdm_implementation/07_tdm_implementation_parameters_handling.md#tdm-parameter-tables) table.

- **Create Synthetic Entities**, duplicate the Entity ID, set in the task. Attach a different clone_id on each clone. 

  Example: 

  Select Customer 1 from the ENV1 source environment and clone it four times. The following LUIs are generated: 

  - ENV1_1#params#{"clone_id"=1}

  - ENV1_1#params#{"clone_id"=2}

  - ENV1_1#params#{"clone_id"=3}

  - ENV1_1#params#{"clone_id"=4}

- **Parameters**, select the entities based on the task's parameters from a [DB view], created in the TDM DB for each BE and source environment combination.  

### Load Tasks - Data Flux Mode

-  [Select All Entities](/articles/TDM/tdm_gui/20_load_task_dataflux_mode.md#select-all-entities) is checked, copy the full list of successfully synced LUIs into Fabric by the selected version. The full list it taken from the Cassandra table:

  - Get the **batch_id** of the extract task that created the selected version from the task_execution_list in the TDM DB table.

  - Get the full list of completed entities by the selected batch id: 

    ```sql
    SELECT entityID FROM k2batchprocess.batchprocess_entities_info 
    WHERE bid = <selected batch id> and status =  'COMPLETED' ALLOW FILTERING;
    ```

- **Select All Entities** is unchecked, get the entities list from the task.

## Children LUs

The entity list of a child LU must include all IDs related to parent IDs that have been successfully processed by the task execution.

Click for an [execution of hierarchical BE](/articles/TDM/tdm_overview/03_business_entity_overview.md#task-execution-of-hierarchical-business-entities) example.

The generated entity list is based on a JOIN of the [task_execution_entities](02_tdm_database.md#task_execution_entities) and the [TDM relationship tables](/articles/TDM/tdm_implementation/06_tdm_implementation_support_hierarchy.md#tdm-relationship-tables):

### Insert without Delete Load Task

Select the children IDs from the task_execution_entities and [tdm_lu_type_relation_eid](/articles/TDM/tdm_implementation/06_tdm_implementation_support_hierarchy.md#tdm_lu_type_relation_eid) tables:

```sql
SELECT rel.lu_type2_eid as child_entity_id
FROM task_execution_entities t, tdm_lu_type_relation_eid rel 
where t.task_execution_id= <task execution id> 
and t.execution_status = 'completed' 
and t.lu_name = <parent lu name> 
and t.source_env  = rel.source_env 
and t.lu_name = rel.lu_type_1 
and t.iid = rel. lu_type1_eid 
and rel.lu_type_2= <child lu name> 
and rel.version_name = <empty string on a regular task and the selected version name on a Data Flux task>;
```



### Delete before Load Task

Select the children IDs from the task_execution_entities and TDM relationship tables: [tdm_lu_type_relation_eid](/articles/TDM/tdm_implementation/06_tdm_implementation_support_hierarchy.md#tdm_lu_type_relation_eid) and [tdm_lu_type_rel_tar_eid](/articles/TDM/tdm_implementation/06_tdm_implementation_support_hierarchy.md#tdm_lu_type_rel_tar_eid) to get the children IDs from the source and target environments: 

```sql
SELECT rel.lu_type2_eid as child_entity_id
FROM task_execution_entities t, tdm_lu_type_relation_eid rel 
where t.task_execution_id= <task execution id> 
and t.execution_status = 'completed' 
and t.lu_name = <parent lu name> 
and t.source_env  = rel.source_env 
and t.lu_name = rel.lu_type_1 
and t.iid = rel. lu_type1_eid 
and rel.lu_type_2= <child lu name> 
and rel.version_name = <empty string on a regular task and the selected version name on a Data Flux task>
UNION
SELECT rel.lu_type2_eid as child_entity_id
FROM task_execution_entities t, tdm_lu_type_rel_tar_eid rel 
where t.task_execution_id= <task execution id> 
and t.execution_status = 'completed' 
and t.lu_name = <parent lu name> 
and rel.target_env = <target environment name> 
and t.lu_name = rel.lu_type_1 
and t.iid = rel. lu_type1_eid 
and rel.lu_type_2= <child lu name>; 
```



### Delete Entity without Load Task

Select the children IDs from the task_execution_entities and [tdm_lu_type_rel_tar_eid](/articles/TDM/tdm_implementation/06_tdm_implementation_support_hierarchy.md#tdm_lu_type_rel_tar_eid) to get the target children IDs: 

```sql
SELECT rel.lu_type2_eid as child_entity_id
FROM task_execution_entities t, tdm_lu_type_rel_tar_eid rel 
where t.task_execution_id= <task execution id> 
and t.execution_status = 'completed' 
and t.lu_name = <parent lu name> 
and rel.target_env = <target environment name> 
and t.lu_name = rel.lu_type_1 
and t.iid = rel. lu_type1_eid 
and rel.lu_type_2= <child lu name>; 
```





[![Previous](/articles/images/Previous.png)](03_task_execution_processes.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_task_execution_overridden_parameters.md)





