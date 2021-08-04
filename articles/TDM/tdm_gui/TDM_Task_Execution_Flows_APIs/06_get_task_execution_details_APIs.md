# Get Task Execution Details APIs

## Get the List of Previous Executions on a Given Task ID

### API URL

/task/{taskId}/summary

### PI URL

wsTaskMonitor/{taskId}

### HTTP Method

GET

### API Category

TDM_Tasks

### API Description

Returns the list of all executions of the input task. Returns one summary record for each execution.

### API Input

- taskId

### API Input Example

```
http://localhost:3213/api/task/293/summary
```

### API Output Example

```json
{
  "result": [
    {
      "be_id": 10,
      "environment_id": 10,
      "tot_num_of_succeeded_post_executions": 0,
      "task_execution_id": 487,
      "task_id": 293,
      "source_environment_id": 9,
      "version_datetime": "2021-06-16 15:35:01.947",
      "execution_status": "completed",
      "source_env_name": "SRC",
      "tot_num_of_processed_root_entities": 50,
      "fabric_environment_name": null,
      "environment_name": "TAR",
      "tot_num_of_failed_ref_tables": 0,
      "start_execution_time": "2021-06-16 15:35:12.000",
      "tot_num_of_processed_post_executions": 0,
      "creation_date": "2021-06-16 15:35:01.949",
      "tot_num_of_copied_root_entities": 50,
      "be_name": "CUSTOMER",
      "tot_num_of_copied_ref_tables": 0,
      "update_date": "2021-06-16 15:35:32.580",
      "tot_num_of_failed_post_executions": 0,
      "version_expiration_date": null,
      "end_execution_time": "2021-06-16 15:35:28.000",
      "tot_num_of_processed_ref_tables": 0,
      "task_type": "LOAD",
      "tot_num_of_failed_root_entities": 0,
      "task_executed_by": null
    },
    {
      "be_id": 10,
      "environment_id": 10,
      "tot_num_of_succeeded_post_executions": 0,
      "task_execution_id": 488,
      "task_id": 293,
      "source_environment_id": 9,
      "version_datetime": "2021-06-16 15:36:00.055",
      "execution_status": "completed",
      "source_env_name": "SRC",
      "tot_num_of_processed_root_entities": 50,
      "fabric_environment_name": null,
      "environment_name": "TAR",
      "tot_num_of_failed_ref_tables": 0,
      "start_execution_time": "2021-06-16 15:36:02.000",
      "tot_num_of_processed_post_executions": 0,
      "creation_date": "2021-06-16 15:36:00.058",
      "tot_num_of_copied_root_entities": 50,
      "be_name": "CUSTOMER",
      "tot_num_of_copied_ref_tables": 0,
      "update_date": "2021-06-16 15:36:33.832",
      "tot_num_of_failed_post_executions": 0,
      "version_expiration_date": null,
      "end_execution_time": "2021-06-16 15:36:27.000",
      "tot_num_of_processed_ref_tables": 0,
      "task_type": "LOAD",
      "tot_num_of_failed_root_entities": 0,
      "task_executed_by": null
    },
    {
      "be_id": 10,
      "environment_id": 10,
      "tot_num_of_succeeded_post_executions": 0,
      "task_execution_id": 489,
      "task_id": 293,
      "source_environment_id": 9,
      "version_datetime": "2021-06-16 15:51:39.623",
      "execution_status": "completed",
      "source_env_name": "SRC",
      "tot_num_of_processed_root_entities": 50,
      "fabric_environment_name": null,
      "environment_name": "TAR",
      "tot_num_of_failed_ref_tables": 0,
      "start_execution_time": "2021-06-16 15:51:45.000",
      "tot_num_of_processed_post_executions": 0,
      "creation_date": "2021-06-16 15:51:39.625",
      "tot_num_of_copied_root_entities": 50,
      "be_name": "CUSTOMER",
      "tot_num_of_copied_ref_tables": 0,
      "update_date": "2021-06-16 15:52:07.418",
      "tot_num_of_failed_post_executions": 0,
      "version_expiration_date": null,
      "end_execution_time": "2021-06-16 15:52:00.000",
      "tot_num_of_processed_ref_tables": 0,
      "task_type": "LOAD",
      "tot_num_of_failed_root_entities": 0,
      "task_executed_by": null
    }
  ],
  "errorCode": "SUCCESS",
  "message": null
}
```

## Get the Summary Execution Details on Last Execution

### API URL

wsTaskMonitor/{taskId}

### HTTP Method

GET

### API Category

TDM_Tasks

### API Description

Returns the details of the current or last execution of the given task_id. If the task is pending, it will return only its status, else it will return the statistics of the entities handled by the task.

### API Input

- taskId

### API Input Example

```
http://localhost:3213/api/wsTaskMonitor/293
```

### API Output Example

#### Completed Task Execution



```json
{
  "result": {
    "Task ID": "293",
    "Task Details": [
      {
        "Fabric Batch ID": "07d813fa-1585-4380-ac19-1920d7074f54",
        "Task Statistics": [
          {
            "Status": "",
            "Ent./sec (avg.)": "25.6",
            "Ent./sec (pace)": "25.6",
            "Failed": "0",
            "Duration": "00:00:02",
            "End time": "2021-06-16 15:36:04.447",
            "Name": "2373be01-f751-47ee-926b-c6e9312aab6e",
            "Succeeded": "50",
            "Total": "--",
            "Level": "Node",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-16 15:36:02.494",
            "% Completed": "100"
          },
          {
            "Status": "",
            "Ent./sec (avg.)": "25.6",
            "Ent./sec (pace)": "25.6",
            "Failed": "0",
            "Duration": "00:00:02",
            "End time": "2021-06-16 15:36:04.447",
            "Name": "DC1",
            "Succeeded": "50",
            "Total": "--",
            "Level": "DC",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-16 15:36:02.494",
            "% Completed": "100"
          },
          {
            "Status": "DONE",
            "Ent./sec (avg.)": "25.6",
            "Ent./sec (pace)": "25.6",
            "Failed": "0",
            "Duration": "00:00:02",
            "End time": "2021-06-16 15:36:04.447",
            "Name": "--",
            "Succeeded": "50",
            "Total": "50",
            "Level": "Cluster",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-16 15:36:02.494",
            "% Completed": "100"
          }
        ],
        "Task Status": "completed",
        "LU Name": "Customer"
      },
      {
        "Fabric Batch ID": "5f5958d1-83e4-45cd-a64c-ce4a845ee183",
        "Task Statistics": [
          {
            "Status": "",
            "Ent./sec (avg.)": "22.04",
            "Ent./sec (pace)": "22.04",
            "Failed": "0",
            "Duration": "00:00:01",
            "End time": "2021-06-16 15:36:24.017",
            "Name": "2373be01-f751-47ee-926b-c6e9312aab6e",
            "Succeeded": "32",
            "Total": "--",
            "Level": "Node",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-16 15:36:22.565",
            "% Completed": "100"
          },
          {
            "Status": "",
            "Ent./sec (avg.)": "22.04",
            "Ent./sec (pace)": "22.04",
            "Failed": "0",
            "Duration": "00:00:01",
            "End time": "2021-06-16 15:36:24.017",
            "Name": "DC1",
            "Succeeded": "32",
            "Total": "--",
            "Level": "DC",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-16 15:36:22.565",
            "% Completed": "100"
          },
          {
            "Status": "DONE",
            "Ent./sec (avg.)": "22.04",
            "Ent./sec (pace)": "22.04",
            "Failed": "0",
            "Duration": "00:00:01",
            "End time": "2021-06-16 15:36:24.017",
            "Name": "--",
            "Succeeded": "32",
            "Total": "32",
            "Level": "Cluster",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-16 15:36:22.565",
            "% Completed": "100"
          }
        ],
        "Task Status": "completed",
        "LU Name": "Collection"
      },
      {
        "Fabric Batch ID": "8182b3ab-0b1d-4a18-89b9-82913772dc76",
        "Task Statistics": [
          {
            "Status": "",
            "Ent./sec (avg.)": "20.61",
            "Ent./sec (pace)": "20.61",
            "Failed": "0",
            "Duration": "00:00:05",
            "End time": "2021-06-16 15:36:27.163",
            "Name": "2373be01-f751-47ee-926b-c6e9312aab6e",
            "Succeeded": "95",
            "Total": "--",
            "Level": "Node",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-16 15:36:22.554",
            "% Completed": "100"
          },
          {
            "Status": "",
            "Ent./sec (avg.)": "20.61",
            "Ent./sec (pace)": "20.61",
            "Failed": "0",
            "Duration": "00:00:05",
            "End time": "2021-06-16 15:36:27.163",
            "Name": "DC1",
            "Succeeded": "95",
            "Total": "--",
            "Level": "DC",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-16 15:36:22.554",
            "% Completed": "100"
          },
          {
            "Status": "DONE",
            "Ent./sec (avg.)": "20.61",
            "Ent./sec (pace)": "20.61",
            "Failed": "0",
            "Duration": "00:00:05",
            "End time": "2021-06-16 15:36:27.163",
            "Name": "--",
            "Succeeded": "95",
            "Total": "95",
            "Level": "Cluster",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-16 15:36:22.554",
            "% Completed": "100"
          }
        ],
        "Task Status": "completed",
        "LU Name": "Billing"
      },
      {
        "Fabric Batch ID": "d854ac07-6d00-4a89-9ae9-797f74c8b51b",
        "Task Statistics": [
          {
            "Status": "",
            "Ent./sec (avg.)": "30.28",
            "Ent./sec (pace)": "30.28",
            "Failed": "0",
            "Duration": "00:00:05",
            "End time": "2021-06-16 15:36:27.445",
            "Name": "2373be01-f751-47ee-926b-c6e9312aab6e",
            "Succeeded": "148",
            "Total": "--",
            "Level": "Node",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-16 15:36:22.557",
            "% Completed": "100"
          },
          {
            "Status": "",
            "Ent./sec (avg.)": "30.28",
            "Ent./sec (pace)": "30.28",
            "Failed": "0",
            "Duration": "00:00:05",
            "End time": "2021-06-16 15:36:27.445",
            "Name": "DC1",
            "Succeeded": "148",
            "Total": "--",
            "Level": "DC",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-16 15:36:22.557",
            "% Completed": "100"
          },
          {
            "Status": "DONE",
            "Ent./sec (avg.)": "30.28",
            "Ent./sec (pace)": "30.28",
            "Failed": "0",
            "Duration": "00:00:05",
            "End time": "2021-06-16 15:36:27.445",
            "Name": "--",
            "Succeeded": "148",
            "Total": "148",
            "Level": "Cluster",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-16 15:36:22.557",
            "% Completed": "100"
          }
        ],
        "Task Status": "completed",
        "LU Name": "Orders"
      }
    ],
    "Task Name": "createTaskByTester",
    "Task Execution ID": 488
  },
  "errorCode": "SUCCESS",
  "message": null
}
```



## Get the Summary Execution info of each LU and Post Execution process of a Given Task Execution ID 

### API URL

/task/{taskExeId}/history

### HTTP Method

GET

### API Category

TDM_Tasks

### API Description

Returns the list of all executed Logical Units and Post Execution Processes of the input task execution id.

### API Input

- taskExeId

### API Input Example

```
http://localhost:3213/api/task/488/history
```

### API Output Example

```json
{
  "result": [
    {
      "be_id": 10,
      "selected_version_task_name": null,
      "selected_ref_version_task_name": null,
      "num_of_copied_entities": 148,
      "fabric_execution_id": "d854ac07-6d00-4a89-9ae9-797f74c8b51b",
      "be_last_updated_date": "2021-06-08 08:20:27.804",
      "execution_plan_name": "epOrders",
      "product_id": 8,
      "load_entity": true,
      "selected_version_task_exe_id": null,
      "task_created_by": "tali",
      "product_description": null,
      "scheduling_end_date": null,
      "environment_point_of_contact_phone1": "(",
      "product_created_by": "admin",
      "environment_status": "Active",
      "be_status": "Active",
      "creation_date": "2021-06-16 15:36:00.055",
      "selected_version_datetime": null,
      "last_executed_lu": false,
      "task_last_updated_by": "admin",
      "selected_ref_version_task_exe_id": null,
      "task_execution_status": "Active",
      "product_versions": "1",
      "product_last_updated_by": "admin",
      "replace_sequences": false,
      "end_execution_time": "2021-06-16 15:36:27.000",
      "environment_point_of_contact_last_name": "hhhl.",
      "updated_by": null,
      "clean_redis": false,
      "environment_point_of_contact_phone2": null,
      "environment_last_updated_by": "admin",
      "product_status": "Active",
      "task_id": 293,
      "be_created_by": "admin",
      "environment_description": "ffff",
      "version_datetime": "2021-06-16 15:36:00.055",
      "selected_ref_version_datetime": null,
      "source_env_name": "SRC",
      "task_title": "createTaskByTester",
      "allow_write": true,
      "task_status": "Active",
      "environment_last_updated_date": "2021-06-15 10:44:44.934",
      "number_of_entities_to_copy": 50,
      "environment_point_of_contact_first_name": "tali",
      "product_creation_date": "2021-05-26 08:03:57.526",
      "task_type": "LOAD",
      "environment_creation_date": "2021-05-26 08:05:57.085",
      "task_executed_by": "admin",
      "task_last_updated_date": "2021-06-16 15:34:40.555",
      "num_of_failed_ref_tables": 0,
      "environment_id": 10,
      "selection_method": "R",
      "lu_parent_id": 20,
      "refresh_reference_data": false,
      "task_execution_id": 488,
      "lu_dc_name": null,
      "refcount": 0,
      "lu_is_ref": null,
      "lu_parent_name": "Customer",
      "process_name": null,
      "be_last_updated_by": "admin",
      "retention_period_type": "Days",
      "start_execution_time": "2021-06-16 15:36:22.000",
      "selection_param_value": null,
      "product_last_updated_date": "2021-05-26 08:11:03.218",
      "product_name": "ORDERING",
      "num_of_processed_ref_tables": 0,
      "execution_order": null,
      "sync_mode": null,
      "version_expiration_date": null,
      "entity_exclusion_list": null,
      "environment_point_of_contact_email": "",
      "lu_name": "Orders",
      "lu_id": 21,
      "be_description": "",
      "parameters": null,
      "environment_expiration_date": null,
      "process_id": 0,
      "product_vendor": null,
      "environment_created_by": "admin",
      "be_creation_date": "2021-05-26 08:02:46.710",
      "allow_read": true,
      "source_environment_id": 9,
      "num_of_failed_entities": 0,
      "scheduler": "immediate",
      "parent_lu_id": 20,
      "lu_description": null,
      "execution_status": "completed",
      "fabric_environment_name": null,
      "environment_name": "TAR",
      "delete_before_load": true,
      "product_version": "1",
      "retention_period_value": 5,
      "synced_to_fabric": true,
      "be_name": "CUSTOMER",
      "version_ind": false,
      "task_creation_date": "2021-06-08 12:38:40.523",
      "task_globals": false,
      "num_of_copied_ref_tables": 0,
      "data_center_name": null,
      "num_of_processed_entities": 148
    },
    {
      "be_id": 10,
      "selected_version_task_name": null,
      "selected_ref_version_task_name": null,
      "num_of_copied_entities": 95,
      "fabric_execution_id": "8182b3ab-0b1d-4a18-89b9-82913772dc76",
      "be_last_updated_date": "2021-06-08 08:20:27.804",
      "execution_plan_name": "epBilling",
      "product_id": 7,
      "load_entity": true,
      "selected_version_task_exe_id": null,
      "task_created_by": "tali",
      "product_description": null,
      "scheduling_end_date": null,
      "environment_point_of_contact_phone1": "(",
      "product_created_by": "admin",
      "environment_status": "Active",
      "be_status": "Active",
      "creation_date": "2021-06-16 15:36:00.055",
      "selected_version_datetime": null,
      "last_executed_lu": false,
      "task_last_updated_by": "admin",
      "selected_ref_version_task_exe_id": null,
      "task_execution_status": "Active",
      "product_versions": "1",
      "product_last_updated_by": "admin",
      "replace_sequences": false,
      "end_execution_time": "2021-06-16 15:36:27.000",
      "environment_point_of_contact_last_name": "hhhl.",
      "updated_by": null,
      "clean_redis": false,
      "environment_point_of_contact_phone2": null,
      "environment_last_updated_by": "admin",
      "product_status": "Active",
      "task_id": 293,
      "be_created_by": "admin",
      "environment_description": "ffff",
      "version_datetime": "2021-06-16 15:36:00.055",
      "selected_ref_version_datetime": null,
      "source_env_name": "SRC",
      "task_title": "createTaskByTester",
      "allow_write": true,
      "task_status": "Active",
      "environment_last_updated_date": "2021-06-15 10:44:44.934",
      "number_of_entities_to_copy": 50,
      "environment_point_of_contact_first_name": "tali",
      "product_creation_date": "2021-05-26 08:03:44.361",
      "task_type": "LOAD",
      "environment_creation_date": "2021-05-26 08:05:57.085",
      "task_executed_by": "admin",
      "task_last_updated_date": "2021-06-16 15:34:40.555",
      "num_of_failed_ref_tables": 0,
      "environment_id": 10,
      "selection_method": "R",
      "lu_parent_id": 20,
      "refresh_reference_data": false,
      "task_execution_id": 488,
      "lu_dc_name": null,
      "refcount": 0,
      "lu_is_ref": null,
      "lu_parent_name": "Customer",
      "process_name": null,
      "be_last_updated_by": "admin",
      "retention_period_type": "Days",
      "start_execution_time": "2021-06-16 15:36:22.000",
      "selection_param_value": null,
      "product_last_updated_date": "2021-05-26 08:04:58.187",
      "product_name": "BILLING",
      "num_of_processed_ref_tables": 0,
      "execution_order": null,
      "sync_mode": null,
      "version_expiration_date": null,
      "entity_exclusion_list": null,
      "environment_point_of_contact_email": "",
      "lu_name": "Billing",
      "lu_id": 22,
      "be_description": "",
      "parameters": null,
      "environment_expiration_date": null,
      "process_id": 0,
      "product_vendor": null,
      "environment_created_by": "admin",
      "be_creation_date": "2021-05-26 08:02:46.710",
      "allow_read": true,
      "source_environment_id": 9,
      "num_of_failed_entities": 0,
      "scheduler": "immediate",
      "parent_lu_id": 20,
      "lu_description": null,
      "execution_status": "completed",
      "fabric_environment_name": null,
      "environment_name": "TAR",
      "delete_before_load": true,
      "product_version": "1",
      "retention_period_value": 5,
      "synced_to_fabric": true,
      "be_name": "CUSTOMER",
      "version_ind": false,
      "task_creation_date": "2021-06-08 12:38:40.523",
      "task_globals": false,
      "num_of_copied_ref_tables": 0,
      "data_center_name": null,
      "num_of_processed_entities": 95
    },
    {
      "be_id": 10,
      "selected_version_task_name": null,
      "selected_ref_version_task_name": null,
      "num_of_copied_entities": 32,
      "fabric_execution_id": "5f5958d1-83e4-45cd-a64c-ce4a845ee183",
      "be_last_updated_date": "2021-06-08 08:20:27.804",
      "execution_plan_name": "epCollection",
      "product_id": 9,
      "load_entity": true,
      "selected_version_task_exe_id": null,
      "task_created_by": "tali",
      "product_description": null,
      "scheduling_end_date": null,
      "environment_point_of_contact_phone1": "(",
      "product_created_by": "admin",
      "environment_status": "Active",
      "be_status": "Active",
      "creation_date": "2021-06-16 15:36:00.055",
      "selected_version_datetime": null,
      "last_executed_lu": false,
      "task_last_updated_by": "admin",
      "selected_ref_version_task_exe_id": null,
      "task_execution_status": "Active",
      "product_versions": "1",
      "product_last_updated_by": "admin",
      "replace_sequences": false,
      "end_execution_time": "2021-06-16 15:36:24.000",
      "environment_point_of_contact_last_name": "hhhl.",
      "updated_by": null,
      "clean_redis": false,
      "environment_point_of_contact_phone2": null,
      "environment_last_updated_by": "admin",
      "product_status": "Active",
      "task_id": 293,
      "be_created_by": "admin",
      "environment_description": "ffff",
      "version_datetime": "2021-06-16 15:36:00.055",
      "selected_ref_version_datetime": null,
      "source_env_name": "SRC",
      "task_title": "createTaskByTester",
      "allow_write": true,
      "task_status": "Active",
      "environment_last_updated_date": "2021-06-15 10:44:44.934",
      "number_of_entities_to_copy": 50,
      "environment_point_of_contact_first_name": "tali",
      "product_creation_date": "2021-06-08 08:20:45.445",
      "task_type": "LOAD",
      "environment_creation_date": "2021-05-26 08:05:57.085",
      "task_executed_by": "admin",
      "task_last_updated_date": "2021-06-16 15:34:40.555",
      "num_of_failed_ref_tables": 0,
      "environment_id": 10,
      "selection_method": "R",
      "lu_parent_id": 20,
      "refresh_reference_data": false,
      "task_execution_id": 488,
      "lu_dc_name": null,
      "refcount": 0,
      "lu_is_ref": null,
      "lu_parent_name": "Customer",
      "process_name": null,
      "be_last_updated_by": "admin",
      "retention_period_type": "Days",
      "start_execution_time": "2021-06-16 15:36:22.000",
      "selection_param_value": null,
      "product_last_updated_date": "2021-06-08 08:20:55.973",
      "product_name": "COLLECTION",
      "num_of_processed_ref_tables": 0,
      "execution_order": null,
      "sync_mode": null,
      "version_expiration_date": null,
      "entity_exclusion_list": null,
      "environment_point_of_contact_email": "",
      "lu_name": "Collection",
      "lu_id": 23,
      "be_description": "",
      "parameters": null,
      "environment_expiration_date": null,
      "process_id": 0,
      "product_vendor": null,
      "environment_created_by": "admin",
      "be_creation_date": "2021-05-26 08:02:46.710",
      "allow_read": true,
      "source_environment_id": 9,
      "num_of_failed_entities": 0,
      "scheduler": "immediate",
      "parent_lu_id": 20,
      "lu_description": null,
      "execution_status": "completed",
      "fabric_environment_name": null,
      "environment_name": "TAR",
      "delete_before_load": true,
      "product_version": "1",
      "retention_period_value": 5,
      "synced_to_fabric": true,
      "be_name": "CUSTOMER",
      "version_ind": false,
      "task_creation_date": "2021-06-08 12:38:40.523",
      "task_globals": false,
      "num_of_copied_ref_tables": 0,
      "data_center_name": null,
      "num_of_processed_entities": 32
    },
    {
      "be_id": 10,
      "selected_version_task_name": null,
      "selected_ref_version_task_name": null,
      "num_of_copied_entities": 50,
      "fabric_execution_id": "07d813fa-1585-4380-ac19-1920d7074f54",
      "be_last_updated_date": "2021-06-08 08:20:27.804",
      "execution_plan_name": "epCustomer",
      "product_id": 6,
      "load_entity": true,
      "selected_version_task_exe_id": null,
      "task_created_by": "tali",
      "product_description": null,
      "scheduling_end_date": null,
      "environment_point_of_contact_phone1": "(",
      "product_created_by": "admin",
      "environment_status": "Active",
      "be_status": "Active",
      "creation_date": "2021-06-16 15:36:00.055",
      "selected_version_datetime": null,
      "last_executed_lu": false,
      "task_last_updated_by": "admin",
      "selected_ref_version_task_exe_id": null,
      "task_execution_status": "Active",
      "product_versions": "PROD,DEV",
      "product_last_updated_by": "admin",
      "replace_sequences": false,
      "end_execution_time": "2021-06-16 15:36:04.000",
      "environment_point_of_contact_last_name": "hhhl.",
      "updated_by": null,
      "clean_redis": false,
      "environment_point_of_contact_phone2": null,
      "environment_last_updated_by": "admin",
      "product_status": "Active",
      "task_id": 293,
      "be_created_by": "admin",
      "environment_description": "ffff",
      "version_datetime": "2021-06-16 15:36:00.055",
      "selected_ref_version_datetime": null,
      "source_env_name": "SRC",
      "task_title": "createTaskByTester",
      "allow_write": true,
      "task_status": "Active",
      "environment_last_updated_date": "2021-06-15 10:44:44.934",
      "number_of_entities_to_copy": 50,
      "environment_point_of_contact_first_name": "tali",
      "product_creation_date": "2021-05-26 08:03:28.264",
      "task_type": "LOAD",
      "environment_creation_date": "2021-05-26 08:05:57.085",
      "task_executed_by": "admin",
      "task_last_updated_date": "2021-06-16 15:34:40.555",
      "num_of_failed_ref_tables": 0,
      "environment_id": 10,
      "selection_method": "R",
      "lu_parent_id": null,
      "refresh_reference_data": false,
      "task_execution_id": 488,
      "lu_dc_name": null,
      "refcount": 0,
      "lu_is_ref": null,
      "lu_parent_name": null,
      "process_name": null,
      "be_last_updated_by": "admin",
      "retention_period_type": "Days",
      "start_execution_time": "2021-06-16 15:36:02.000",
      "selection_param_value": null,
      "product_last_updated_date": "2021-05-26 08:04:48.842",
      "product_name": "CRM",
      "num_of_processed_ref_tables": 0,
      "execution_order": null,
      "sync_mode": null,
      "version_expiration_date": null,
      "entity_exclusion_list": null,
      "environment_point_of_contact_email": "",
      "lu_name": "Customer",
      "lu_id": 20,
      "be_description": "",
      "parameters": null,
      "environment_expiration_date": null,
      "process_id": 0,
      "product_vendor": null,
      "environment_created_by": "admin",
      "be_creation_date": "2021-05-26 08:02:46.710",
      "allow_read": true,
      "source_environment_id": 9,
      "num_of_failed_entities": 0,
      "scheduler": "immediate",
      "parent_lu_id": null,
      "lu_description": null,
      "execution_status": "completed",
      "fabric_environment_name": null,
      "environment_name": "TAR",
      "delete_before_load": true,
      "product_version": "PROD",
      "retention_period_value": 5,
      "synced_to_fabric": true,
      "be_name": "CUSTOMER",
      "version_ind": false,
      "task_creation_date": "2021-06-08 12:38:40.523",
      "task_globals": false,
      "num_of_copied_ref_tables": 0,
      "data_center_name": null,
      "num_of_processed_entities": 50
    }
  ],
  "errorCode": "SUCCESS",
  "message": null
}
```



## Get the List of Copied and Failed Entities and Reference Tables of a Given Task Execution ID

### API URL

/taskStats

### HTTP Method

POST

### API Category

TDM_Tasks

### API Description

Gets the task execution details:

- List of copied and Failed Entities.
- Task's Logical Units hierarchy tree.
- List of copied and failed reference tables.

The information can be retrieved on the following levels based on the input BODY request parameters:

- Get the execution details of the root Logical Unit
- Get the execution details of a selected Logical Unit
- Get the execution details of a selected Logical Unit and entity ID

### API Input

- Request BODY:

  ```json
  {
    "targetId": 0,
    "parentTargetId": 0,
    "taskExecutionId": "string",
    "lu_name": "string",
    "entityId": 0,
    "type": "string"
  }
  ```

- The **taskExecutionId** parameter is mandatory. Other parameters are optional.

- The **type** is an optional attribute to filter the returned data when the **luName** attribute is populated, but the **targetId**, **parentTargetId**, and **entityId** attributes are not populated. The **type** can be populated by the following values:

  - Copied entities per execution
  - Failed entities per execution
  - Copied Reference per execution
  - Failed Reference per execution 
  
  

### API Request BODY Examples

#### Get the Information on the Task's Root LU 

```json
{
  "taskExecutionId": "490"
}
```

#### Get the Information on a Selected Task's LU

```json
{
  "taskExecutionId": "490",  
  "lu_name": "Billing"
}
```

#### Get the Information on a Selected Task's LU and Entity ID

```json
{
 "taskExecutionId": "490",
 "lu_name": "Billing",
 "targetId": "102"
}
```

#### Get the List of Children Entities Related to a Given Parent ID

```json
{
  "parentTargetId": "36",
  "taskExecutionId": "490",
  "lu_name": "Billing"
}
```

#### Get the List of Children Entities Related to a Given Root Entity ID

```json
{
  "entityID": "36",
  "taskExecutionId": "490",
  "lu_name": "Billing"
}
```

#### Get the Failed Entities of a Selected Task's LU

```json
{
  "type": "Failed entities per execution",
  "taskExecutionId": "448",
  "lu_name": "Collection"
}
```



### API Output Example

#### Get the Information on the Task's Root LU

```json
{
  "result": {
    "luTree": [
      {
        "isRoot": true,
        "test": true,
        "hasChildren": true,
        "collapsed": true,
        "lu_name": "Customer",
        "task_execution_id": 490,
        "count": 3,
        "lu_id": 20,
        "test1": true,
        "lu_status": "completed",
        "selected": true,
        "status": "completed"
      }
    ],
    "data": {
      "Copied entities per execution": {
        "entitiesList": [
          {
            "sourceId": "36",
            "parentLuName": "",
            "parentTargetId": "",
            "targetId": "36",
            "copyEntityStatus": "Copied",
            "luName": "Customer",
            "parentSourceId": "",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "36",
            "rootTargetId": "36"
          },
          {
            "sourceId": "532",
            "parentLuName": "",
            "parentTargetId": "",
            "targetId": "532",
            "copyEntityStatus": "Copied",
            "luName": "Customer",
            "parentSourceId": "",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "532",
            "rootTargetId": "532"
          },
          {
            "sourceId": "577",
            "parentLuName": "",
            "parentTargetId": "",
            "targetId": "577",
            "copyEntityStatus": "Copied",
            "luName": "Customer",
            "parentSourceId": "",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "577",
            "rootTargetId": "577"
          },
          {
            "sourceId": "627",
            "parentLuName": "",
            "parentTargetId": "",
            "targetId": "627",
            "copyEntityStatus": "Copied",
            "luName": "Customer",
            "parentSourceId": "",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "627",
            "rootTargetId": "627"
          },
          {
            "sourceId": "794",
            "parentLuName": "",
            "parentTargetId": "",
            "targetId": "794",
            "copyEntityStatus": "Copied",
            "luName": "Customer",
            "parentSourceId": "",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "794",
            "rootTargetId": "794"
          }
        ],
        "NoOfEntities": "5"
      },
      "Failed entities per execution": {
        "entitiesList": [],
        "NoOfEntities": "0"
      },
      "Copied Reference per execution": {
        "entitiesList": [],
        "NoOfEntities": 0
      },
      "Failed Reference per execution": {
        "entitiesList": [],
        "NoOfEntities": 0
      },
      "Roots Status": {
        "Customer": "completed"
      }
    }
  },
  "errorCode": "SUCCESS",
  "message": null
}
```

#### Get the Information on a Selected Task's LU

```json
{
  "result": {
    "data": {
      "Copied entities per execution": {
        "entitiesList": [
          {
            "sourceId": "102",
            "parentLuName": "Customer",
            "parentTargetId": "36",
            "targetId": "102",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "36",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "36",
            "rootTargetId": "36"
          },
          {
            "sourceId": "103",
            "parentLuName": "Customer",
            "parentTargetId": "36",
            "targetId": "103",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "36",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "36",
            "rootTargetId": "36"
          },
          {
            "sourceId": "104",
            "parentLuName": "Customer",
            "parentTargetId": "36",
            "targetId": "104",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "36",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "36",
            "rootTargetId": "36"
          },
          {
            "sourceId": "105",
            "parentLuName": "Customer",
            "parentTargetId": "36",
            "targetId": "105",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "36",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "36",
            "rootTargetId": "36"
          },
          {
            "sourceId": "106",
            "parentLuName": "Customer",
            "parentTargetId": "36",
            "targetId": "106",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "36",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "36",
            "rootTargetId": "36"
          },
          {
            "sourceId": "1324",
            "parentLuName": "Customer",
            "parentTargetId": "532",
            "targetId": "1324",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "532",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "532",
            "rootTargetId": "532"
          },
          {
            "sourceId": "1325",
            "parentLuName": "Customer",
            "parentTargetId": "532",
            "targetId": "1325",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "532",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "532",
            "rootTargetId": "532"
          },
          {
            "sourceId": "1326",
            "parentLuName": "Customer",
            "parentTargetId": "532",
            "targetId": "1326",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "532",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "532",
            "rootTargetId": "532"
          },
          {
            "sourceId": "1429",
            "parentLuName": "Customer",
            "parentTargetId": "577",
            "targetId": "1429",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "577",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "577",
            "rootTargetId": "577"
          },
          {
            "sourceId": "1430",
            "parentLuName": "Customer",
            "parentTargetId": "577",
            "targetId": "1430",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "577",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "577",
            "rootTargetId": "577"
          },
          {
            "sourceId": "1431",
            "parentLuName": "Customer",
            "parentTargetId": "577",
            "targetId": "1431",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "577",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "577",
            "rootTargetId": "577"
          },
          {
            "sourceId": "1432",
            "parentLuName": "Customer",
            "parentTargetId": "577",
            "targetId": "1432",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "577",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "577",
            "rootTargetId": "577"
          },
          {
            "sourceId": "1537",
            "parentLuName": "Customer",
            "parentTargetId": "627",
            "targetId": "1537",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "627",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "627",
            "rootTargetId": "627"
          },
          {
            "sourceId": "1538",
            "parentLuName": "Customer",
            "parentTargetId": "627",
            "targetId": "1538",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "627",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "627",
            "rootTargetId": "627"
          },
          {
            "sourceId": "1965",
            "parentLuName": "Customer",
            "parentTargetId": "794",
            "targetId": "1965",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "794",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "794",
            "rootTargetId": "794"
          },
          {
            "sourceId": "1966",
            "parentLuName": "Customer",
            "parentTargetId": "794",
            "targetId": "1966",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "794",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "794",
            "rootTargetId": "794"
          },
          {
            "sourceId": "1967",
            "parentLuName": "Customer",
            "parentTargetId": "794",
            "targetId": "1967",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "794",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "794",
            "rootTargetId": "794"
          }
        ],
        "NoOfEntities": "17"
      },
      "Failed entities per execution": {
        "entitiesList": [],
        "NoOfEntities": "0"
      },
      "Copied Reference per execution": {
        "entitiesList": [],
        "NoOfEntities": 0
      },
      "Failed Reference per execution": {
        "entitiesList": [],
        "NoOfEntities": 0
      },
      "Roots Status": {
        "Customer": "completed"
      }
    }
  },
  "errorCode": "SUCCESS",
  "message": null
}
```



```json
{
  "result": {
    "data": {
      "Copied entities per execution": {
        "entitiesList": [],
        "NoOfEntities": "0"
      },
      "Failed entities per execution": {
        "entitiesList": [
          {
            "sourceId": "142",
            "parentLuName": "Customer",
            "parentTargetId": "311",
            "targetId": "142",
            "copyEntityStatus": "Failed",
            "Full Error Path": [
              {
                "parentLuName": "Customer",
                "luStatus": "Failed",
                "luName": "Collection",
                "entityStatus": "Failed"
              }
            ],
            "luName": "Collection",
            "parentSourceId": "311",
            "copyHierarchyStatus": "Failed",
            "rootSourceId": "311",
            "rootTargetId": "311",
            "errorMsg": [
              "Flow: InitiateTDMLoad Level: 8 Stage: Get Instance From Fabric Actor: Get Entity  InnerFlowException: Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43",
              "Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43 from population [LU:Collection, TABLE:TAR_COLLECTION, POPULATION:populationTar_Collection.flow]\nGET ?.?"
            ]
          },
          {
            "sourceId": "194",
            "parentLuName": "Customer",
            "parentTargetId": "409",
            "targetId": "194",
            "copyEntityStatus": "Failed",
            "Full Error Path": [
              {
                "parentLuName": "Customer",
                "luStatus": "Failed",
                "luName": "Collection",
                "entityStatus": "Failed"
              }
            ],
            "luName": "Collection",
            "parentSourceId": "409",
            "copyHierarchyStatus": "Failed",
            "rootSourceId": "409",
            "rootTargetId": "409",
            "errorMsg": [
              "Flow: InitiateTDMLoad Level: 8 Stage: Get Instance From Fabric Actor: Get Entity  InnerFlowException: Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43",
              "Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43 from population [LU:Collection, TABLE:TAR_COLLECTION, POPULATION:populationTar_Collection.flow]\nGET ?.?"
            ]
          },
          {
            "sourceId": "261",
            "parentLuName": "Customer",
            "parentTargetId": "545",
            "targetId": "261",
            "copyEntityStatus": "Failed",
            "Full Error Path": [
              {
                "parentLuName": "Customer",
                "luStatus": "Failed",
                "luName": "Collection",
                "entityStatus": "Failed"
              }
            ],
            "luName": "Collection",
            "parentSourceId": "545",
            "copyHierarchyStatus": "Failed",
            "rootSourceId": "545",
            "rootTargetId": "545",
            "errorMsg": [
              "Flow: InitiateTDMLoad Level: 8 Stage: Get Instance From Fabric Actor: Get Entity  InnerFlowException: Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43",
              "Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43 from population [LU:Collection, TABLE:TAR_COLLECTION, POPULATION:populationTar_Collection.flow]\nGET ?.?"
            ]
          },
          {
            "sourceId": "291",
            "parentLuName": "Customer",
            "parentTargetId": "597",
            "targetId": "291",
            "copyEntityStatus": "Failed",
            "Full Error Path": [
              {
                "parentLuName": "Customer",
                "luStatus": "Failed",
                "luName": "Collection",
                "entityStatus": "Failed"
              }
            ],
            "luName": "Collection",
            "parentSourceId": "597",
            "copyHierarchyStatus": "Failed",
            "rootSourceId": "597",
            "rootTargetId": "597",
            "errorMsg": [
              "Flow: InitiateTDMLoad Level: 8 Stage: Get Instance From Fabric Actor: Get Entity  InnerFlowException: Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43",
              "Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43 from population [LU:Collection, TABLE:TAR_COLLECTION, POPULATION:populationTar_Collection.flow]\nGET ?.?"
            ]
          },
          {
            "sourceId": "385",
            "parentLuName": "Customer",
            "parentTargetId": "807",
            "targetId": "385",
            "copyEntityStatus": "Failed",
            "Full Error Path": [
              {
                "parentLuName": "Customer",
                "luStatus": "Failed",
                "luName": "Collection",
                "entityStatus": "Failed"
              }
            ],
            "luName": "Collection",
            "parentSourceId": "807",
            "copyHierarchyStatus": "Failed",
            "rootSourceId": "807",
            "rootTargetId": "807",
            "errorMsg": [
              "Flow: InitiateTDMLoad Level: 8 Stage: Get Instance From Fabric Actor: Get Entity  InnerFlowException: Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43",
              "Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43 from population [LU:Collection, TABLE:TAR_COLLECTION, POPULATION:populationTar_Collection.flow]\nGET ?.?"
            ]
          },
          {
            "sourceId": "394",
            "parentLuName": "Customer",
            "parentTargetId": "822",
            "targetId": "394",
            "copyEntityStatus": "Failed",
            "Full Error Path": [
              {
                "parentLuName": "Customer",
                "luStatus": "Failed",
                "luName": "Collection",
                "entityStatus": "Failed"
              }
            ],
            "luName": "Collection",
            "parentSourceId": "822",
            "copyHierarchyStatus": "Failed",
            "rootSourceId": "822",
            "rootTargetId": "822",
            "errorMsg": [
              "Flow: InitiateTDMLoad Level: 8 Stage: Get Instance From Fabric Actor: Get Entity  InnerFlowException: Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43",
              "Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43 from population [LU:Collection, TABLE:TAR_COLLECTION, POPULATION:populationTar_Collection.flow]\nGET ?.?"
            ]
          },
          {
            "sourceId": "62",
            "parentLuName": "Customer",
            "parentTargetId": "127",
            "targetId": "62",
            "copyEntityStatus": "Failed",
            "Full Error Path": [
              {
                "parentLuName": "Customer",
                "luStatus": "Failed",
                "luName": "Collection",
                "entityStatus": "Failed"
              }
            ],
            "luName": "Collection",
            "parentSourceId": "127",
            "copyHierarchyStatus": "Failed",
            "rootSourceId": "127",
            "rootTargetId": "127",
            "errorMsg": [
              "Flow: InitiateTDMLoad Level: 8 Stage: Get Instance From Fabric Actor: Get Entity  InnerFlowException: Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43",
              "Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43 from population [LU:Collection, TABLE:TAR_COLLECTION, POPULATION:populationTar_Collection.flow]\nGET ?.?"
            ]
          }
        ],
        "NoOfEntities": "7"
      },
      "Copied Reference per execution": {
        "entitiesList": [],
        "NoOfEntities": 0
      },
      "Failed Reference per execution": {
        "entitiesList": [],
        "NoOfEntities": 0
      },
      "Roots Status": {
        "Customer": "child failed"
      }
    }
  },
  "errorCode": "SUCCESS",
  "message": null
}
```



#### Get the Information on a Selected Task's LU and Entity ID

```json
{
  "result": {
    "data": {
      "Copied entities per execution": {
        "entitiesList": [
          {
            "sourceId": "102",
            "parentLuName": "Customer",
            "parentTargetId": "36",
            "targetId": "102",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "36",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "36",
            "rootTargetId": "36"
          }
        ],
        "NoOfEntities": "1"
      },
      "Failed entities per execution": {
        "entitiesList": [],
        "NoOfEntities": "0"
      },
      "Copied Reference per execution": {
        "entitiesList": [],
        "NoOfEntities": 0
      },
      "Failed Reference per execution": {
        "entitiesList": [],
        "NoOfEntities": 0
      },
      "Roots Status": {
        "Customer": "completed"
      }
    }
  },
  "errorCode": "SUCCESS",
  "message": null
}
```



#### Get the List of Children Entities Related to a Given Parent ID

```json
{
  "result": {
    "data": {
      "Copied entities per execution": {
        "entitiesList": [
          {
            "sourceId": "102",
            "parentLuName": "Customer",
            "parentTargetId": "36",
            "targetId": "102",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "36",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "36",
            "rootTargetId": "36"
          },
          {
            "sourceId": "103",
            "parentLuName": "Customer",
            "parentTargetId": "36",
            "targetId": "103",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "36",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "36",
            "rootTargetId": "36"
          },
          {
            "sourceId": "104",
            "parentLuName": "Customer",
            "parentTargetId": "36",
            "targetId": "104",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "36",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "36",
            "rootTargetId": "36"
          },
          {
            "sourceId": "105",
            "parentLuName": "Customer",
            "parentTargetId": "36",
            "targetId": "105",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "36",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "36",
            "rootTargetId": "36"
          },
          {
            "sourceId": "106",
            "parentLuName": "Customer",
            "parentTargetId": "36",
            "targetId": "106",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "36",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "36",
            "rootTargetId": "36"
          }
        ],
        "NoOfEntities": "5"
      },
      "Failed entities per execution": {
        "entitiesList": [],
        "NoOfEntities": "0"
      },
      "Copied Reference per execution": {
        "entitiesList": [],
        "NoOfEntities": 0
      },
      "Failed Reference per execution": {
        "entitiesList": [],
        "NoOfEntities": 0
      },
      "Roots Status": {
        "Customer": "completed"
      }
    }
  },
  "errorCode": "SUCCESS",
  "message": null
}
```



#### Get the List of Children Entities Related to a Given Root Entity ID

```json
{
  "result": {
    "data": {
      "Copied entities per execution": {
        "entitiesList": [
          {
            "sourceId": "102",
            "parentLuName": "Customer",
            "parentTargetId": "36",
            "targetId": "102",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "36",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "36",
            "rootTargetId": "36"
          },
          {
            "sourceId": "103",
            "parentLuName": "Customer",
            "parentTargetId": "36",
            "targetId": "103",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "36",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "36",
            "rootTargetId": "36"
          },
          {
            "sourceId": "104",
            "parentLuName": "Customer",
            "parentTargetId": "36",
            "targetId": "104",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "36",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "36",
            "rootTargetId": "36"
          },
          {
            "sourceId": "105",
            "parentLuName": "Customer",
            "parentTargetId": "36",
            "targetId": "105",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "36",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "36",
            "rootTargetId": "36"
          },
          {
            "sourceId": "106",
            "parentLuName": "Customer",
            "parentTargetId": "36",
            "targetId": "106",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "36",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "36",
            "rootTargetId": "36"
          },
          {
            "sourceId": "1324",
            "parentLuName": "Customer",
            "parentTargetId": "532",
            "targetId": "1324",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "532",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "532",
            "rootTargetId": "532"
          },
          {
            "sourceId": "1325",
            "parentLuName": "Customer",
            "parentTargetId": "532",
            "targetId": "1325",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "532",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "532",
            "rootTargetId": "532"
          },
          {
            "sourceId": "1326",
            "parentLuName": "Customer",
            "parentTargetId": "532",
            "targetId": "1326",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "532",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "532",
            "rootTargetId": "532"
          },
          {
            "sourceId": "1429",
            "parentLuName": "Customer",
            "parentTargetId": "577",
            "targetId": "1429",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "577",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "577",
            "rootTargetId": "577"
          },
          {
            "sourceId": "1430",
            "parentLuName": "Customer",
            "parentTargetId": "577",
            "targetId": "1430",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "577",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "577",
            "rootTargetId": "577"
          },
          {
            "sourceId": "1431",
            "parentLuName": "Customer",
            "parentTargetId": "577",
            "targetId": "1431",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "577",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "577",
            "rootTargetId": "577"
          },
          {
            "sourceId": "1432",
            "parentLuName": "Customer",
            "parentTargetId": "577",
            "targetId": "1432",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "577",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "577",
            "rootTargetId": "577"
          },
          {
            "sourceId": "1537",
            "parentLuName": "Customer",
            "parentTargetId": "627",
            "targetId": "1537",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "627",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "627",
            "rootTargetId": "627"
          },
          {
            "sourceId": "1538",
            "parentLuName": "Customer",
            "parentTargetId": "627",
            "targetId": "1538",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "627",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "627",
            "rootTargetId": "627"
          },
          {
            "sourceId": "1965",
            "parentLuName": "Customer",
            "parentTargetId": "794",
            "targetId": "1965",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "794",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "794",
            "rootTargetId": "794"
          },
          {
            "sourceId": "1966",
            "parentLuName": "Customer",
            "parentTargetId": "794",
            "targetId": "1966",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "794",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "794",
            "rootTargetId": "794"
          },
          {
            "sourceId": "1967",
            "parentLuName": "Customer",
            "parentTargetId": "794",
            "targetId": "1967",
            "copyEntityStatus": "Copied",
            "luName": "Billing",
            "parentSourceId": "794",
            "copyHierarchyStatus": "Copied",
            "rootSourceId": "794",
            "rootTargetId": "794"
          }
        ],
        "NoOfEntities": "17"
      },
      "Failed entities per execution": {
        "entitiesList": [],
        "NoOfEntities": "0"
      },
      "Copied Reference per execution": {
        "entitiesList": [],
        "NoOfEntities": 0
      },
      "Failed Reference per execution": {
        "entitiesList": [],
        "NoOfEntities": 0
      },
      "Roots Status": {
        "Customer": "completed"
      }
    }
  },
  "errorCode": "SUCCESS",
  "message": null
}
```



#### Get the Failed Entities of a Selected Task's LU

```json
{
  "result": {
    "data": {
      "Failed entities per execution": {
        "entitiesList": [
          {
            "sourceId": "142",
            "parentLuName": "Customer",
            "parentTargetId": "311",
            "targetId": "142",
            "copyEntityStatus": "Failed",
            "Full Error Path": [
              {
                "parentLuName": "Customer",
                "luStatus": "Failed",
                "luName": "Collection",
                "entityStatus": "Failed"
              }
            ],
            "luName": "Collection",
            "parentSourceId": "311",
            "copyHierarchyStatus": "Failed",
            "rootSourceId": "311",
            "rootTargetId": "311",
            "errorMsg": [
              "Flow: InitiateTDMLoad Level: 8 Stage: Get Instance From Fabric Actor: Get Entity  InnerFlowException: Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43",
              "Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43 from population [LU:Collection, TABLE:TAR_COLLECTION, POPULATION:populationTar_Collection.flow]\nGET ?.?"
            ]
          },
          {
            "sourceId": "194",
            "parentLuName": "Customer",
            "parentTargetId": "409",
            "targetId": "194",
            "copyEntityStatus": "Failed",
            "Full Error Path": [
              {
                "parentLuName": "Customer",
                "luStatus": "Failed",
                "luName": "Collection",
                "entityStatus": "Failed"
              }
            ],
            "luName": "Collection",
            "parentSourceId": "409",
            "copyHierarchyStatus": "Failed",
            "rootSourceId": "409",
            "rootTargetId": "409",
            "errorMsg": [
              "Flow: InitiateTDMLoad Level: 8 Stage: Get Instance From Fabric Actor: Get Entity  InnerFlowException: Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43",
              "Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43 from population [LU:Collection, TABLE:TAR_COLLECTION, POPULATION:populationTar_Collection.flow]\nGET ?.?"
            ]
          },
          {
            "sourceId": "261",
            "parentLuName": "Customer",
            "parentTargetId": "545",
            "targetId": "261",
            "copyEntityStatus": "Failed",
            "Full Error Path": [
              {
                "parentLuName": "Customer",
                "luStatus": "Failed",
                "luName": "Collection",
                "entityStatus": "Failed"
              }
            ],
            "luName": "Collection",
            "parentSourceId": "545",
            "copyHierarchyStatus": "Failed",
            "rootSourceId": "545",
            "rootTargetId": "545",
            "errorMsg": [
              "Flow: InitiateTDMLoad Level: 8 Stage: Get Instance From Fabric Actor: Get Entity  InnerFlowException: Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43",
              "Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43 from population [LU:Collection, TABLE:TAR_COLLECTION, POPULATION:populationTar_Collection.flow]\nGET ?.?"
            ]
          },
          {
            "sourceId": "291",
            "parentLuName": "Customer",
            "parentTargetId": "597",
            "targetId": "291",
            "copyEntityStatus": "Failed",
            "Full Error Path": [
              {
                "parentLuName": "Customer",
                "luStatus": "Failed",
                "luName": "Collection",
                "entityStatus": "Failed"
              }
            ],
            "luName": "Collection",
            "parentSourceId": "597",
            "copyHierarchyStatus": "Failed",
            "rootSourceId": "597",
            "rootTargetId": "597",
            "errorMsg": [
              "Flow: InitiateTDMLoad Level: 8 Stage: Get Instance From Fabric Actor: Get Entity  InnerFlowException: Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43",
              "Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43 from population [LU:Collection, TABLE:TAR_COLLECTION, POPULATION:populationTar_Collection.flow]\nGET ?.?"
            ]
          },
          {
            "sourceId": "385",
            "parentLuName": "Customer",
            "parentTargetId": "807",
            "targetId": "385",
            "copyEntityStatus": "Failed",
            "Full Error Path": [
              {
                "parentLuName": "Customer",
                "luStatus": "Failed",
                "luName": "Collection",
                "entityStatus": "Failed"
              }
            ],
            "luName": "Collection",
            "parentSourceId": "807",
            "copyHierarchyStatus": "Failed",
            "rootSourceId": "807",
            "rootTargetId": "807",
            "errorMsg": [
              "Flow: InitiateTDMLoad Level: 8 Stage: Get Instance From Fabric Actor: Get Entity  InnerFlowException: Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43",
              "Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43 from population [LU:Collection, TABLE:TAR_COLLECTION, POPULATION:populationTar_Collection.flow]\nGET ?.?"
            ]
          },
          {
            "sourceId": "394",
            "parentLuName": "Customer",
            "parentTargetId": "822",
            "targetId": "394",
            "copyEntityStatus": "Failed",
            "Full Error Path": [
              {
                "parentLuName": "Customer",
                "luStatus": "Failed",
                "luName": "Collection",
                "entityStatus": "Failed"
              }
            ],
            "luName": "Collection",
            "parentSourceId": "822",
            "copyHierarchyStatus": "Failed",
            "rootSourceId": "822",
            "rootTargetId": "822",
            "errorMsg": [
              "Flow: InitiateTDMLoad Level: 8 Stage: Get Instance From Fabric Actor: Get Entity  InnerFlowException: Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43",
              "Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43 from population [LU:Collection, TABLE:TAR_COLLECTION, POPULATION:populationTar_Collection.flow]\nGET ?.?"
            ]
          },
          {
            "sourceId": "62",
            "parentLuName": "Customer",
            "parentTargetId": "127",
            "targetId": "62",
            "copyEntityStatus": "Failed",
            "Full Error Path": [
              {
                "parentLuName": "Customer",
                "luStatus": "Failed",
                "luName": "Collection",
                "entityStatus": "Failed"
              }
            ],
            "luName": "Collection",
            "parentSourceId": "127",
            "copyHierarchyStatus": "Failed",
            "rootSourceId": "127",
            "rootTargetId": "127",
            "errorMsg": [
              "Flow: InitiateTDMLoad Level: 8 Stage: Get Instance From Fabric Actor: Get Entity  InnerFlowException: Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43",
              "Flow: TAR_COLLECTION.populationTar_Collection Level: 2 Stage: Source Actor: Query  class org.postgresql.util.PSQLException Cause: ERROR: relation \"public.collection\" does not exist\n  Position: 43 from population [LU:Collection, TABLE:TAR_COLLECTION, POPULATION:populationTar_Collection.flow]\nGET ?.?"
            ]
          }
        ],
        "NoOfEntities": "7"
      }
    }
  },
  "errorCode": "SUCCESS",
  "message": null
}
```

## Get the Hierarchy Tree of the Selected Entity ID

### API URL

wsGetTaskExeStatsForEntity

### HTTP Method

GET

### API Category

TDM_Tasks

### API Description

Gets the LU hierarchy of a given entity and LU name within the task execution.

### API Input

- taskExecutionId
- luName
- targetId

### API Input Example

```
http://localhost:3213/api/wsGetTaskExeStatsForEntity?taskExecutionId=490&luName=Billing&targetId=102
```

### API Output Example

```json
{
  "result": {
    "Customer": {
      "luName": "Customer",
      "targetId": "36",
      "sourceId": "36",
      "entityStatus": "completed",
      "parentLuName": "",
      "parentTargetId": "",
      "children": [
        {
          "luName": "Billing",
          "targetId": "102",
          "sourceId": "102",
          "entityStatus": "completed",
          "parentLuName": "Customer",
          "parentTargetId": "36",
          "luStatus": "completed"
        }
      ],
      "luStatus": "completed"
    }
  },
  "errorCode": "SUCCESS",
  "message": null
}
```



## Get Execution Details of Reference Tables  

### API URL

/extractrefstats

### HTTP Method

POST

### API Category

TDM_Tasks

### API Description

Gets a summary or detailed information of the reference tables' execution of a given task execution id.

The type is the run mode of the API and can have the following values:

- 'D': detailed execution. Returns detailed information of all reference tables their execution status.

- 'S': summary information of the execution.

### API Input

- Request BODY:

  ```json
  {
    "taskExecutionId": "string",
    "type": "string"
  }
  ```

- The **type** can be populated either with 'D' for detailed information or 'S' for summary information.

  

### API Request BODY Examples

#### Summary Information

```json
{taskExecutionId: 494, type: "S"}
```

#### Detailed Information

```json
{taskExecutionId: 494, type: "D"}
```

### API Output Examples

#### Summary Information

```json
{
  "result": {
    "Customer": {
      "minStartExecutionDate": "Thu Jun 17 06:31:15 UTC 2021",
      "maxEndExecutionDate": "Thu Jun 17 06:31:17 UTC 2021",
      "totNumOfTablesToProcess": 1,
      "numOfProcessedRefTables": 1,
      "numOfCopiedRefTables": 1,
      "numOfFailedRefTables": 0,
      "numOfProcessingRefTables": 0,
      "numberOfNotStartedRefTables": 0
    }
  },
  "errorCode": "SUCCESS",
  "message": null
}
```

#### Detailed Information

```json
{
  "result": [
    {
      "start_time": "2021-06-17 06:31:15.036",
      "ref_table_name": "DEVICESTABLE2017",
      "lu_name": "Customer",
      "execution_status": "completed",
      "end_time": "2021-06-17 06:31:17.090",
      "coalesce": 3752,
      "estimated_remaining_duration": "0",
      "number_of_records_to_process": 3752
    }
  ],
  "errorCode": "SUCCESS",
  "message": null
}
```

 [![Previous](/articles/images/Previous.png)](01_tdm_basic_task_execution_flow.md)
