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

- task_ids  -  this is an optional input. Can be populated by one task ID, or several task IDs separated by a comma.

#### API Input Examples

```
http://localhost:3213/api/tasks?task_ids=3222
```

```
http://localhost:3213/api/tasks?task_ids=3222,454
```

```
http://localhost:3213/api/tasks
```

### API Output Example

```json
{
  "result": [
    {
      "task_last_updated_date": "2021-09-09 10:08:45.529",
      "be_id": 1,
      "selected_version_task_name": null,
      "environment_id": 6,
      "selection_method": "L",
      "selected_ref_version_task_name": null,
      "refresh_reference_data": false,
      "tester": "michal",
      "be_last_updated_date": "2021-09-30 06:33:58.083",
      "owners": [
        {
          "owner": "OwnerEnv1",
          "owner_type": "GROUP"
        },
        {
          "owner": "TestRole1",
          "owner_type": "ID"
        },
        {
          "owner": "OwnerMega",
          "owner_type": "ID"
        }
      ],
      "refcount": 0,
      "tester_type": "ID",
      "load_entity": false,
      "reservation_ind": false,  
      "selected_version_task_exe_id": 0,
      "task_created_by": "admin",
      "be_last_updated_by": "admin",
      "scheduling_end_date": null,
      "retention_period_type": "Days",
      "environment_point_of_contact_phone1": null,
      "processnames": null,
      "testers": [
        {
          "tester_type": "ID",
          "role_id": [
            "9"
          ],
          "tester": "michal"
        },
        {
          "tester_type": "ID",
          "role_id": [
            "9"
          ],
          "tester": "owner2"
        },
        {
          "tester_type": "GROUP",
          "role_id": [
            "9"
          ],
          "tester": "wsRole"
        },
        {
          "tester_type": "ID",
          "role_id": [
            "10"
          ],
          "tester": "testershai"
        }
      ],
      "selection_param_value": "1,2,3",
      "environment_status": "Active",
      "be_status": "Active",
      "selected_version_datetime": null,
      "task_last_updated_by": "admin",
      "selected_ref_version_task_exe_id": 0,
      "task_execution_status": "Active",
      "sync_mode": null,
      "replace_sequences": false,
      "entity_exclusion_list": null,
      "environment_point_of_contact_last_name": null,
      "environment_point_of_contact_email": null,
      "be_description": "",
      "parameters": null,
      "environment_expiration_date": null,
      "environment_point_of_contact_phone2": null,
      "environment_created_by": "admin",
      "roles": [
        [
          {
            "role_id": 8,
            "allowed_test_conn_failure": true
          },
          {
            "role_id": 9,
            "allowed_test_conn_failure": true
          },
          {
            "role_id": 11,
            "allowed_test_conn_failure": false
          },
          {
            "role_id": 10,
            "allowed_test_conn_failure": false
          }
        ]
      ],
      "environment_last_updated_by": "admin",
      "be_creation_date": "2021-08-08 13:31:04.17",
      "task_id": 131,
      "be_created_by": "admin",
      "source_environment_id": 6,
      "role_id_orig": 9,
      "scheduler": "immediate",
      "environment_description": "ENV1",
      "selected_ref_version_datetime": null,
      "source_env_name": null,
      "task_title": "r6",
      "fabric_environment_name": null,
      "environment_name": "ENV1",
      "delete_before_load": false,
      "allow_write": true,
      "owner": "OwnerEnv1",
      "task_status": "Active",
      "retention_period_value": "5",
      "executioncount": 0,
      "environment_last_updated_date": "2021-10-13 09:09:48.743",
      "be_name": "BE1",
      "version_ind": true,
      "number_of_entities_to_copy": 3,
      "task_creation_date": "2021-09-09 10:08:45.529",
      "task_globals": false,
      "environment_point_of_contact_first_name": "",
      "task_type": "EXTRACT",
      "environment_creation_date": "2021-09-09 08:12:28.523",
      "owner_type": "GROUP"
    },
    {
      "task_last_updated_date": "2021-09-09 12:45:49.442",
      "be_id": 1,
      "selected_version_task_name": null,
      "environment_id": 6,
      "selection_method": "R",
      "selected_ref_version_task_name": null,
      "refresh_reference_data": false,
      "tester": "michal",
      "be_last_updated_date": "2021-09-30 06:33:58.083",
      "owners": [
        {
          "owner": "OwnerEnv1",
          "owner_type": "GROUP"
        },
        {
          "owner": "TestRole1",
          "owner_type": "ID"
        },
        {
          "owner": "OwnerMega",
          "owner_type": "ID"
        }
      ],
      "refcount": 0,
      "tester_type": "ID",
      "load_entity": true,
      "reservation_ind": true,  
      "selected_version_task_exe_id": 0,
      "task_created_by": "admin",
      "be_last_updated_by": "admin",
      "scheduling_end_date": null,
      "retention_period_type": "Days",
      "environment_point_of_contact_phone1": null,
      "processnames": null,
      "testers": [
        {
          "tester_type": "ID",
          "role_id": [
            "9"
          ],
          "tester": "michal"
        },
        {
          "tester_type": "ID",
          "role_id": [
            "9"
          ],
          "tester": "owner2"
        },
        {
          "tester_type": "GROUP",
          "role_id": [
            "9"
          ],
          "tester": "wsRole"
        },
        {
          "tester_type": "ID",
          "role_id": [
            "10"
          ],
          "tester": "testershai"
        }
      ],
      "selection_param_value": null,
      "environment_status": "Active",
      "be_status": "Active",
      "selected_version_datetime": null,
      "task_last_updated_by": "admin",
      "selected_ref_version_task_exe_id": 0,
      "task_execution_status": "Active",
      "sync_mode": null,
      "replace_sequences": false,
      "entity_exclusion_list": null,
      "environment_point_of_contact_last_name": null,
      "environment_point_of_contact_email": null,
      "be_description": "",
      "parameters": null,
      "environment_expiration_date": null,
      "environment_point_of_contact_phone2": null,
      "environment_created_by": "admin",
      "roles": [],
      "environment_last_updated_by": "admin",
      "be_creation_date": "2021-08-08 13:31:04.17",
      "task_id": 135,
      "be_created_by": "admin",
      "source_environment_id": 9,
      "role_id_orig": 9,
      "scheduler": "immediate",
      "environment_description": "ENV1",
      "selected_ref_version_datetime": null,
      "source_env_name": null,
      "task_title": "load2",
      "fabric_environment_name": null,
      "environment_name": "ENV1",
      "delete_before_load": false,
      "allow_write": true,
      "owner": "OwnerEnv1",
      "task_status": "Active",
      "retention_period_value": "5",
      "executioncount": 0,
      "environment_last_updated_date": "2021-10-13 09:09:48.743",
      "be_name": "BE1",
      "version_ind": false,
      "number_of_entities_to_copy": 3,
      "task_creation_date": "2021-09-09 12:45:49.442",
      "task_globals": false,
      "environment_point_of_contact_first_name": "",
      "task_type": "LOAD",
      "environment_creation_date": "2021-09-09 08:12:28.523",
      "owner_type": "GROUP"
    },
    {
      "task_last_updated_date": "2021-08-08 14:29:46.16",
      "be_id": 1,
      "selected_version_task_name": "yughfhgf",
      "environment_id": 2,
      "selection_method": "L",
      "selected_ref_version_task_name": null,
      "refresh_reference_data": false,
      "tester": null,
      "be_last_updated_date": "2021-09-30 06:33:58.083",
      "owners": [
        {
          "owner": "owner1",
          "owner_type": "ID"
        }
      ],
      "refcount": 0,
      "tester_type": null,
      "load_entity": true,
      "reservation_ind":true,  
      "selected_version_task_exe_id": 2,
      "task_created_by": "owner1",
      "be_last_updated_by": "admin",
      "scheduling_end_date": null,
      "retention_period_type": "Days",
      "environment_point_of_contact_phone1": null,
      "processnames": null,
      "testers": [],
      "selection_param_value": "3",
      "environment_status": "Active",
      "be_status": "Active",
      "selected_version_datetime": "20210808140941",
      "task_last_updated_by": "owner1",
      "selected_ref_version_task_exe_id": 0,
      "task_execution_status": "Active",
      "sync_mode": null,
      "replace_sequences": false,
      "entity_exclusion_list": null,
      "environment_point_of_contact_last_name": null,
      "environment_point_of_contact_email": null,
      "be_description": "",
      "parameters": null,
      "environment_expiration_date": null,
      "environment_point_of_contact_phone2": null,
      "environment_created_by": "admin",
      "roles": [],
      "environment_last_updated_by": "owner1",
      "be_creation_date": "2021-08-08 13:31:04.17",
      "task_id": 3,
      "be_created_by": "admin",
      "source_environment_id": 1,
      "role_id_orig": 0,
      "scheduler": "immediate",
      "environment_description": "ENV2",
      "selected_ref_version_datetime": null,
      "source_env_name": "ENV1",
      "task_title": "cdcdvs",
      "fabric_environment_name": null,
      "environment_name": "ENV2",
      "delete_before_load": true,
      "allow_write": true,
      "owner": "owner1",
      "task_status": "Active",
      "retention_period_value": "5",
      "executioncount": 0,
      "environment_last_updated_date": "2021-08-17 10:05:34.328",
      "be_name": "BE1",
      "version_ind": true,
      "number_of_entities_to_copy": 1,
      "task_creation_date": "2021-08-08 14:29:46.16",
      "task_globals": false,
      "environment_point_of_contact_first_name": null,
      "task_type": "LOAD",
      "environment_creation_date": "2021-08-08 14:28:05.461",
      "owner_type": "ID"
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
      "global_name": "CLONE_CLEANUP_RETENTION_PERIOD_VALUE",
      "task_id": 292,
      "global_value": "0.5"
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

Gets the list of Reference tables included in a given task. The **refcount** attribute of  **/tasks** API is populated by the number of Reference tables included in the task. If the **refcount** attribute is populated by zero, the task does not have Reference tables.

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
