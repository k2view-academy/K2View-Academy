# Get Task Details APIs

There are several APIs that return the full details of a given task ID.

## Get Task General Details

### API URL

/tasks 

### HTTP Method

GET

### API Category

TDM_Tasks

### API Description

This is the main API to get the task details. This API gets the list of all TDM tasks or a list of given task IDs if the input task_ids parameter is populated. 
The input **task_ids** is an optional parameter that can be populated to return the data of a given list of tasks. The ID(s) of the required task(s), will be supplied in this parameter separated by comma. For example, task_ids=4 or task_ids=3,2,6. 

If task_ids parameter is not populated, the data of all tasks will be returned by the API.

### API Input

- task_ids  -  This is an optional input. Can be populated by one task ID, or several task IDs separated by a comma.
- mode - Specifies the task status to retrieve. Options: 'Active' to get only active tasks, 'Inactive' for only inactive tasks, or 'Both' to retrieve all tasks regardless of status.

#### API Input Examples

```
https://k2rnd-3.cloud.k2view.com/tdm10demo-2-k2view/api/tasks?mode=Active
```

```
https://k2rnd-3.cloud.k2view.com/tdm10demo-2-k2view/api/tasks?task_ids=100%2C%20200&mode=Active
```

```
https://k2rnd-3.cloud.k2view.com/tdm10demo-2-k2view/api/tasks?task_ids=100&mode=Active
```

### API Output Example

```json
{
  "result": [
    {
      "task_last_updated_date": "2026-06-29 18:37:34.044",
      "task_description": null,
      "filterout_reserved": "OTHERS",
      "be_id": 1,
      "reserve_retention_period_type": "Days",
      "statistics_report_flag": "ALL",
      "notes": [
        {
          "note_id": 19,
          "note_title": "Instructions",
          "task_id": 100,
          "note_date": "2026-05-11 14:23:24.085",
          "note_description": "Select a state from the dropdown list."
        }
      ],
      "environment_id": 2,
      "selection_method": "C",
      "refresh_reference_data": false,
      "tester": null,
      "be_last_updated_date": "2026-06-29 09:18:20.567",
      "owners": [],
      "refcount": 0,
      "num_of_entities": -1,
      "selected_subset_task_exe_id": 0,
      "tester_type": null,
      "reserve_note": null,
      "enable_sequence_report": false,
      "permissions": [
        {
          "type": "ID",
          "value": "ALL"
        }
      ],
      "load_entity": true,
      "selected_version_task_exe_id": 0,
      "task_created_by": "tali.einhorn@k2view.com.k2v",
      "be_last_updated_by": "tali.einhorn@k2view.com.k2v",
      "scheduling_end_date": null,
      "retention_period_type": "Do Not Delete",
      "environment_point_of_contact_phone1": null,
      "processnames": "preTaskExePrintToLog,postTaskExePrintToLog",
      "testers": [],
      "selection_param_value": "getEntitiesByState",
      "environment_status": "Active",
      "be_status": "Active",
      "task_last_updated_by": "tali.einhorn@k2view.com.k2v##k2view_k2v_user",
      "selected_ref_version_task_exe_id": 0,
      "task_execution_status": "Active",
      "mask_sensitive_data": false,
      "sync_mode": "ON",
      "enable_execution": true,
      "task_groups": [
        {
          "task_group_id": 30,
          "task_group_name": "Customer provisioning tasks"
        }
      ],
      "execution_mode": "INHERITED",
      "replace_sequences": true,
      "environment_point_of_contact_last_name": null,
      "environment_point_of_contact_email": null,
      "be_description": "Normal Hierarchy",
      "reserve_retention_period_value": "5",
      "parameters": "{\"inputs\":[{\"name\":\"state\",\"type\":\"any\",\"value\":\"IL\",\"is_editable\":true,\"checked\":false}]}",
      "environment_expiration_date": null,
      "environment_point_of_contact_phone2": null,
      "environment_created_by": "admin",
      "clone_ind": false,
      "roles": [],
      "environment_last_updated_by": "admin",
      "be_creation_date": "2026-06-24 11:41:44.171884",
      "task_id": 100,
      "be_created_by": "admin",
      "custom_logic_lu_name": "Customer",
      "source_environment_id": 1,
      "role_id_orig": 0,
      "scheduler": "immediate",
      "environment_description": "This is the Target environment.",
      "source_env_name": "Production",
      "reserve_ind": false,
      "task_title": "Copy customers based on state",
      "environment_name": "UAT",
      "delete_before_load": false,
      "allow_write": true,
      "owner": null,
      "in_place_masking_ind": false,
      "task_status": "Active",
      "retention_period_value": "-1",
      "task_override_fields": "{\"business_entity\": {\"is_editable\": false, \"field_connector\": \"be_name\"}, \"retention_period\": {\"is_editable\": false, \"field_connector\": \"retention_period\"}, \"selection_method\": {\"random\": {\"is_editable\": true}, \"entity_list\": {\"is_editable\": true}, \"is_editable\": true, \"custom_logic\": {\"is_editable\": true, \"can_add_params\": {\"is_editable\": true}}, \"max_entities\": {\"is_editable\": true}, \"field_connector\": \"selection_method\", \"business_parameters\": {\"is_editable\": true}}, \"data_version_name\": {\"is_editable\": false, \"field_connector\": \"data_version_name\"}, \"reservation_period\": {\"is_editable\": false, \"field_connector\": \"reservation_period\"}, \"source_environment\": {\"is_editable\": true, \"field_connector\": \"source_env_name\"}, \"target_environment\": {\"is_editable\": true, \"field_connector\": \"environment_name\"}}",
      "executioncount": 0,
      "environment_last_updated_date": "2026-06-24 11:41:44.171884",
      "be_name": "Customer",
      "version_ind": false,
      "task_creation_date": "2026-05-11 11:10:32.432",
      "task_globals": false,
      "environment_point_of_contact_first_name": null,
      "task_type": "LOAD",
      "environment_creation_date": "2026-06-24 11:41:44.171884",
      "owner_type": null,
      "creatorRoles": [
        "k2view_k2v_user"
      ]
    }
  ],
  "errorCode": "SUCCESS",
  "message": null
}
```

## Get the Task's Logical Units List

### API URL

/task/{taskId}/logicalunits

### HTTP Method

GET

### API Category

TDM_Tasks

### API Description

This API gets the task's Logical Units. The Business Entity (BE) ID and name are returned by **/tasks** API in **be_id** and **be_name** output attributes.

### API Input

- taskId

#### API Input Example

```
http://localhost:3213/api/task/291/logicalunits
```

### API Output Example

```json
{
  "result": [
    {
      "lu_name": "Collection",
      "lu_id": 23,
      "task_id": 291
    },
    {
      "lu_name": "Customer",
      "lu_id": 20,
      "task_id": 291
    },
    {
      "lu_name": "Billing",
      "lu_id": 22,
      "task_id": 291
    },
    {
      "lu_name": "Orders",
      "lu_id": 21,
      "task_id": 291
    }
  ],
  "errorCode": "SUCCESS",
  "message": null
}
```



## Get the Task's Globals

### API URL

/task/{taskId}/globals

### HTTP Method

GET

### API Category

TDM_Tasks

### API Description

This API gets the task's Globals if they exist. The **task_globals** attribute of  **/tasks** API indicates if the task has globals. This attribute is populated by true if the task has Globals.

### API Input

- taskId

#### API Input Example

```
http://localhost:3213/api/task/292/globals
```

### API Output Example

```json
{
  "result": [
    {
      "global_name": "MASK_FLAG",
      "lu_name": "ALL",
      "task_id": 57,
      "global_value": "0"
    },
    {
      "global_name": "MAIL_ADDRESS",
      "lu_name": "Customer",
      "task_id": 57,
      "global_value": "tali@gmail.com"
    }
  ],
  "errorCode": "SUCCESS",
  "message": null
}
```



## Get the Task's Reference Tables

### API URL

/task/refsTable/{task_id}

### HTTP Method

GET

### API Category

TDM_Tasks

### API Description

Gets the list of Reference tables included in a given task. The **refcount** attribute of the  **/tasks** API is populated by the number of Reference tables included in the task. If the **refcount** attribute is populated by zero, the task does not have Reference tables.

### API Input

- task_id

#### API Input Example

```
http://localhost:3213/api/task/refsTable/55
```

### API Output Example

```json
{
  "result": [
    {
      "task_ref_table_id": 39,
      "ref_table_name": "MEDICATION_REFERENCE",
      "lu_name": "PATIENT_LU",
      "interface_name": "HIS_DB",
      "task_id": 55,
      "schema_name": "TDM_SOURCE",
      "update_date": "2021-06-16 12:48:10.208"
    },
    {
      "task_ref_table_id": 40,
      "ref_table_name": "PATIENT_REF",
      "lu_name": "PATIENT_LU",
      "interface_name": "HIS_DB",
      "task_id": 55,
      "schema_name": "TDM_SOURCE",
      "update_date": "2021-06-16 12:48:10.208"
    }
  ],
  "errorCode": "SUCCESS",
  "message": null
}
```

## Get the Task's Post Execution Processes

### API URL

/task/{taskId}/postexecutionprocess

### HTTP Method

GET

### API Category

TDM_Tasks

### API Description

Gets the task's Post Execution Processes. 

### API Input

- taskId

#### API Input Example

```
http://localhost:3213/api/task/54/postexecutionprocess
```

### API Output Example

```json
{
  "result": [
    {
      "process_id": 6,
      "process_name": "ShareLogFlow",
      "task_id": 54,
      "execution_order": 1
    },
    {
      "process_id": 7,
      "process_name": "PLUFlow",
      "task_id": 54,
      "execution_order": 2
    }
  ],
  "errorCode": "SUCCESS",
  "message": null
}
```

 [![Previous](/articles/images/Previous.png)](01_tdm_basic_task_execution_flow.md)
