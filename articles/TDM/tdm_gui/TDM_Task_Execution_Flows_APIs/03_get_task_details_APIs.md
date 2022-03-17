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

#### Return One Task 

```json
{
  "result": [
    {
      "task_last_updated_date": "2022-03-15 12:02:15.84",
      "be_id": 1,
      "selected_version_task_name": null,
      "reserve_retention_period_type": "Days",
      "environment_id": 2,
      "selection_method": "C",
      "selected_ref_version_task_name": null,
      "refresh_reference_data": false,
      "tester": "taha",
      "be_last_updated_date": "2021-11-07 11:24:45.078",
      "owners": [
        {
          "owner": "tali",
          "owner_type": "ID"
        }
      ],
      "refcount": 0,
      "num_of_entities": 120,
      "tester_type": "ID",
      "load_entity": true,
      "selected_version_task_exe_id": 0,
      "task_created_by": "tali",
      "be_last_updated_by": "admin",
      "scheduling_end_date": null,
      "retention_period_type": null,
      "environment_point_of_contact_phone1": null,
      "processnames": null,
      "testers": [
        {
          "tester_type": "ID",
          "role_id": [
            "2"
          ],
          "tester": "taha"
        },
        {
          "tester_type": "GROUP",
          "role_id": [
            "2"
          ],
          "tester": "tdmTesters1"
        },
        {
          "tester_type": "GROUP",
          "role_id": [
            "6"
          ],
          "tester": "tdmTesters2"
        }
      ],
      "selection_param_value": "get5GCustomers",
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
      "reserve_retention_period_value": null,
      "parameters": "{\"group\":{\"rules\":[{\"condition\":\"=\",\"field\":\"BILLING.NO_OF_OPEN_INVOICES\",\"data\":\"2\",\"operator\":\"AND\",\"$$hashKey\":\"object:389\",\"type\":\"combo\",\"validValues\":[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\"],\"disableThird\":false}]}}",
      "environment_expiration_date": null,
      "environment_point_of_contact_phone2": null,
      "environment_created_by": "admin",
      "roles": [
        [
          {
            "role_id": 6,
            "allowed_test_conn_failure": true
          },
          {
            "role_id": 2,
            "allowed_test_conn_failure": true
          }
        ]
      ],
      "environment_last_updated_by": "admin",
      "be_creation_date": "2021-11-07 11:24:17.668",
      "task_id": 48,
      "be_created_by": "admin",
      "source_environment_id": 1,
      "role_id_orig": 2,
      "scheduler": "immediate",
      "environment_description": null,
      "selected_ref_version_datetime": null,
      "source_env_name": "SRC",
      "reserve_ind": true,
      "task_title": "testLoadAndReserve",
      "fabric_environment_name": null,
      "environment_name": "TAR",
      "delete_before_load": false,
      "allow_write": true,
      "owner": "tali",
      "task_status": "Active",
      "retention_period_value": null,
      "executioncount": 0,
      "environment_last_updated_date": "2022-03-09 15:00:45.845",
      "be_name": "Customer",
      "version_ind": false,
      "task_creation_date": "2022-03-15 09:35:02.208",
      "task_globals": false,
      "environment_point_of_contact_first_name": null,
      "task_type": "LOAD",
      "environment_creation_date": "2021-11-07 15:10:17.569",
      "owner_type": "ID",
      "creatorRoles": [
        "tdmOwner"
      ]
    }
  ],
  "errorCode": "SUCCESS",
  "message": null
}
```

#### Return Two Tasks

```json
{
  "result": [
    {
      "task_last_updated_date": "2022-03-15 12:02:15.84",
      "be_id": 1,
      "selected_version_task_name": null,
      "reserve_retention_period_type": "Days",
      "environment_id": 2,
      "selection_method": "C",
      "selected_ref_version_task_name": null,
      "refresh_reference_data": false,
      "tester": "tdmTesters2",
      "be_last_updated_date": "2021-11-07 11:24:45.078",
      "owners": [
        {
          "owner": "tali",
          "owner_type": "ID"
        }
      ],
      "refcount": 0,
      "num_of_entities": 120,
      "tester_type": "GROUP",
      "load_entity": true,
      "selected_version_task_exe_id": 0,
      "task_created_by": "tali",
      "be_last_updated_by": "admin",
      "scheduling_end_date": null,
      "retention_period_type": null,
      "environment_point_of_contact_phone1": null,
      "processnames": null,
      "testers": [
        {
          "tester_type": "GROUP",
          "role_id": [
            "6"
          ],
          "tester": "tdmTesters2"
        },
        {
          "tester_type": "ID",
          "role_id": [
            "2"
          ],
          "tester": "taha"
        },
        {
          "tester_type": "GROUP",
          "role_id": [
            "2"
          ],
          "tester": "tdmTesters1"
        }
      ],
      "selection_param_value": "get5GCustomers",
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
      "reserve_retention_period_value": null,
      "parameters": "{\"group\":{\"rules\":[{\"condition\":\"=\",\"field\":\"BILLING.NO_OF_OPEN_INVOICES\",\"data\":\"2\",\"operator\":\"AND\",\"$$hashKey\":\"object:389\",\"type\":\"combo\",\"validValues\":[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\"],\"disableThird\":false}]}}",
      "environment_expiration_date": null,
      "environment_point_of_contact_phone2": null,
      "environment_created_by": "admin",
      "roles": [
        [
          {
            "role_id": 6,
            "allowed_test_conn_failure": true
          },
          {
            "role_id": 2,
            "allowed_test_conn_failure": true
          }
        ]
      ],
      "environment_last_updated_by": "admin",
      "be_creation_date": "2021-11-07 11:24:17.668",
      "task_id": 48,
      "be_created_by": "admin",
      "source_environment_id": 1,
      "role_id_orig": 6,
      "scheduler": "immediate",
      "environment_description": null,
      "selected_ref_version_datetime": null,
      "source_env_name": "SRC",
      "reserve_ind": true,
      "task_title": "testLoadAndReserve",
      "fabric_environment_name": null,
      "environment_name": "TAR",
      "delete_before_load": false,
      "allow_write": true,
      "owner": "tali",
      "task_status": "Active",
      "retention_period_value": null,
      "executioncount": 0,
      "environment_last_updated_date": "2022-03-09 15:00:45.845",
      "be_name": "Customer",
      "version_ind": false,
      "task_creation_date": "2022-03-15 09:35:02.208",
      "task_globals": false,
      "environment_point_of_contact_first_name": null,
      "task_type": "LOAD",
      "environment_creation_date": "2021-11-07 15:10:17.569",
      "owner_type": "ID",
      "creatorRoles": [
        "tdmOwner"
      ]
    },
    {
      "task_last_updated_date": "2022-03-16 10:35:19.317",
      "be_id": 1,
      "selected_version_task_name": null,
      "reserve_retention_period_type": "Days",
      "environment_id": 2,
      "selection_method": "C",
      "selected_ref_version_task_name": null,
      "refresh_reference_data": false,
      "tester": "tdmTesters2",
      "be_last_updated_date": "2021-11-07 11:24:45.078",
      "owners": [
        {
          "owner": "tali",
          "owner_type": "ID"
        }
      ],
      "refcount": 0,
      "num_of_entities": 10,
      "tester_type": "GROUP",
      "load_entity": false,
      "selected_version_task_exe_id": 0,
      "task_created_by": "admin",
      "be_last_updated_by": "admin",
      "scheduling_end_date": null,
      "retention_period_type": null,
      "environment_point_of_contact_phone1": null,
      "processnames": null,
      "testers": [
        {
          "tester_type": "GROUP",
          "role_id": [
            "6"
          ],
          "tester": "tdmTesters2"
        },
        {
          "tester_type": "ID",
          "role_id": [
            "2"
          ],
          "tester": "taha"
        },
        {
          "tester_type": "GROUP",
          "role_id": [
            "2"
          ],
          "tester": "tdmTesters1"
        }
      ],
      "selection_param_value": "get5GCustomers",
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
      "reserve_retention_period_value": "5",
      "parameters": null,
      "environment_expiration_date": null,
      "environment_point_of_contact_phone2": null,
      "environment_created_by": "admin",
      "roles": [],
      "environment_last_updated_by": "admin",
      "be_creation_date": "2021-11-07 11:24:17.668",
      "task_id": 50,
      "be_created_by": "admin",
      "source_environment_id": 2,
      "role_id_orig": 6,
      "scheduler": null,
      "environment_description": null,
      "selected_ref_version_datetime": null,
      "source_env_name": "",
      "reserve_ind": true,
      "task_title": "ReserveEntities",
      "fabric_environment_name": null,
      "environment_name": "TAR",
      "delete_before_load": false,
      "allow_write": true,
      "owner": "tali",
      "task_status": "Active",
      "retention_period_value": null,
      "executioncount": 0,
      "environment_last_updated_date": "2022-03-09 15:00:45.845",
      "be_name": "Customer",
      "version_ind": false,
      "task_creation_date": "2022-03-16 10:35:19.317",
      "task_globals": false,
      "environment_point_of_contact_first_name": null,
      "task_type": "RESERVE",
      "environment_creation_date": "2021-11-07 15:10:17.569",
      "owner_type": "ID",
      "creatorRoles": [
        "admin"
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
