# Get Task Execution Reports APIs

## Get Task Execution Summary Report 

### API URL

/taskSummaryReport/{executionId}/luName/{luName}

### HTTP Method

POST

### API Category

TDM_Tasks

### API Description

Gets the task summary report.

### API Input

- executionId
- luName - populate this parameter by 'ALL' to get a summary execution report on the execution. This report includes all the related task's LUs.

#### API Input Example

```
http://localhost:3213/api/taskSummaryReport/490/luName/ALL
```

### API Output Example

```json
{
  "result": {
    "General Info": [
      {
        "task_name": "createTaskByTester",
        "task_id": 294,
        "task_execution_id": "490",
        "created_by": "tali",
        "executed_by": "admin",
        "start_execution": "2021-06-16 16:24:21.0",
        "end_execution": "2021-06-16 16:24:32.0",
        "execution_status": "completed",
        "source_env": "SRC",
        "target_env": "TAR",
        "be_name": "CUSTOMER",
        "task_type": "LOAD",
        "selection_method": "Randon Selection",
        "sync_mode": "ON",
        "operation_mode": "Delete and load entity",
        "replace_sequences": "false",
        "version_ind": "false",
        "selected_version_task_name": null,
        "selected_version_datetime": null,
        "selected_ref_version_task_name": null,
        "selected_ref_version_datetime": null,
        "scheduling_parameters": "immediate",
        "schedule_expiration_date": null
      }
    ],
    "Task Execution Summary": [
      {
        "lu_name": "Customer",
        "fabric_execution_id": "aced32c8-7d2c-43cd-a7ba-f363733f59e7",
        "parent_lu_name": "null",
        "data_center_name": "null",
        "start_execution_time": "2021-06-16 16:24:21.0",
        "end_execution_time": "2021-06-16 16:24:22.0",
        "num_of_processed_entities": 5,
        "num_of_copied_entities": 5,
        "num_of_failed_entities": 0,
        "num_of_processed_ref_tables": 0,
        "num_of_copied_ref_tables": 0,
        "num_of_failed_ref_tables": 0
      },
      {
        "lu_name": "Collection",
        "fabric_execution_id": "4f4e3609-1b9b-418b-b059-ceb38b4e93eb",
        "parent_lu_name": "Customer",
        "data_center_name": "null",
        "start_execution_time": "2021-06-16 16:24:31.0",
        "end_execution_time": "2021-06-16 16:24:32.0",
        "num_of_processed_entities": 3,
        "num_of_copied_entities": 3,
        "num_of_failed_entities": 0,
        "num_of_processed_ref_tables": 0,
        "num_of_copied_ref_tables": 0,
        "num_of_failed_ref_tables": 0
      },
      {
        "lu_name": "Billing",
        "fabric_execution_id": "8868047e-0aa4-4793-a2f2-d41f8a24a94b",
        "parent_lu_name": "Customer",
        "data_center_name": "null",
        "start_execution_time": "2021-06-16 16:24:31.0",
        "end_execution_time": "2021-06-16 16:24:32.0",
        "num_of_processed_entities": 17,
        "num_of_copied_entities": 17,
        "num_of_failed_entities": 0,
        "num_of_processed_ref_tables": 0,
        "num_of_copied_ref_tables": 0,
        "num_of_failed_ref_tables": 0
      },
      {
        "lu_name": "Orders",
        "fabric_execution_id": "d423dcfc-e175-4aed-b31f-0ca55e59f095",
        "parent_lu_name": "Customer",
        "data_center_name": "null",
        "start_execution_time": "2021-06-16 16:24:31.0",
        "end_execution_time": "2021-06-16 16:24:32.0",
        "num_of_processed_entities": 27,
        "num_of_copied_entities": 27,
        "num_of_failed_entities": 0,
        "num_of_processed_ref_tables": 0,
        "num_of_copied_ref_tables": 0,
        "num_of_failed_ref_tables": 0
      }
    ],
    "List of Root Entities": {
      "Number of Copied Entities": [
        {
          "number_of_copied_root_entities": 5
        }
      ],
      "List of Copied Entities": [
        {
          "source_id": "SRC_36",
          "target_id": "36"
        },
        {
          "source_id": "SRC_532",
          "target_id": "532"
        },
        {
          "source_id": "SRC_577",
          "target_id": "577"
        },
        {
          "source_id": "SRC_627",
          "target_id": "627"
        },
        {
          "source_id": "SRC_794",
          "target_id": "794"
        }
      ],
      "Number of Failed Entities": [
        {
          "number_of_failed_root_entities": 0
        }
      ],
      "List of Failed Entities": []
    },
    "List of Reference Tables": {
      "Number of Copied Reference Tables": [
        {
          "count": 0
        }
      ],
      "List of Copied Reference Tables": [],
      "Number of Failed Reference Tables": [
        {
          "count": 0
        }
      ],
      "List of Failed Reference Tables": []
    },
    "Error Summary": [],
    "Error Details": [],
    "Statistics Report": [
      {
        "lu_name": "Billing",
        "table_name": "BALANCE",
        "target_count": "172",
        "source_count": "172",
        "diff": "0",
        "results": "OK"
      },
      {
        "lu_name": "Billing",
        "table_name": "INVOICE",
        "target_count": "107",
        "source_count": "107",
        "diff": "0",
        "results": "OK"
      },
      {
        "lu_name": "Billing",
        "table_name": "OFFER",
        "target_count": "9",
        "source_count": "9",
        "diff": "0",
        "results": "OK"
      },
      {
        "lu_name": "Billing",
        "table_name": "SUBSCRIBER",
        "target_count": "17",
        "source_count": "17",
        "diff": "0",
        "results": "OK"
      },
      {
        "lu_name": "Collection",
        "table_name": "COLLECTION",
        "target_count": "3",
        "source_count": "3",
        "diff": "0",
        "results": "OK"
      },
      {
        "lu_name": "Customer",
        "table_name": "ACTIVITY",
        "target_count": "26",
        "source_count": "26",
        "diff": "0",
        "results": "OK"
      },
      {
        "lu_name": "Customer",
        "table_name": "ADDRESS",
        "target_count": "5",
        "source_count": "5",
        "diff": "0",
        "results": "OK"
      },
      {
        "lu_name": "Customer",
        "table_name": "CASE_NOTE",
        "target_count": "35",
        "source_count": "35",
        "diff": "0",
        "results": "OK"
      },
      {
        "lu_name": "Customer",
        "table_name": "CASES",
        "target_count": "16",
        "source_count": "16",
        "diff": "0",
        "results": "OK"
      },
      {
        "lu_name": "Customer",
        "table_name": "CONTRACT",
        "target_count": "17",
        "source_count": "17",
        "diff": "0",
        "results": "OK"
      },
      {
        "lu_name": "Customer",
        "table_name": "CUSTOMER",
        "target_count": "5",
        "source_count": "5",
        "diff": "0",
        "results": "OK"
      },
      {
        "lu_name": "Orders",
        "table_name": "ORDERS",
        "target_count": "27",
        "source_count": "27",
        "diff": "0",
        "results": "OK"
      }
    ],
    "Replace Sequence Summary Report": []
  },
  "errorCode": "SUCCESS",
  "message": ""
}
```



## Load Tasks - Get a Summary Execution Report on a Given LU

### API URL

/taskSummaryReport/{executionId}/luName/{luName}

### HTTP Method

POST

### API Category

TDM_Tasks

### API Description

Gets the task summary report on a given LU.

### API Input

- executionId
- luName - populate this parameter by the LU name.

#### API Input Example

```
http://localhost:3213/api/taskSummaryReport/490/luName/Billing
```

### API Output Example

```json
{
  "result": {
    "General Info": [
      {
        "task_name": "createTaskByTester",
        "task_id": 294,
        "task_execution_id": "490",
        "created_by": "tali",
        "executed_by": "admin",
        "start_execution": "2021-06-16 16:24:21.0",
        "end_execution": "2021-06-16 16:24:32.0",
        "execution_status": "completed",
        "source_env": "SRC",
        "target_env": "TAR",
        "be_name": "CUSTOMER",
        "task_type": "LOAD",
        "selection_method": "Randon Selection",
        "sync_mode": "ON",
        "operation_mode": "Delete and load entity",
        "replace_sequences": "false",
        "version_ind": "false",
        "selected_version_task_name": null,
        "selected_version_datetime": null,
        "selected_ref_version_task_name": null,
        "selected_ref_version_datetime": null,
        "scheduling_parameters": "immediate",
        "schedule_expiration_date": null
      }
    ],
    "Task Execution Summary": [
      {
        "lu_name": "Billing",
        "fabric_execution_id": "8868047e-0aa4-4793-a2f2-d41f8a24a94b",
        "parent_lu_name": "Customer",
        "data_center_name": "null",
        "start_execution_time": "2021-06-16 16:24:31.0",
        "end_execution_time": "2021-06-16 16:24:32.0",
        "num_of_processed_entities": 17,
        "num_of_copied_entities": 17,
        "num_of_failed_entities": 0,
        "num_of_processed_ref_tables": 0,
        "num_of_copied_ref_tables": 0,
        "num_of_failed_ref_tables": 0
      }
    ],
    "List of Root Entities": {
      "Number of Copied Entities": [
        {
          "number_of_copied_root_entities": 17
        }
      ],
      "List of Copied Entities": [
        {
          "source_id": "SRC_102",
          "target_id": "102"
        },
        {
          "source_id": "SRC_103",
          "target_id": "103"
        },
        {
          "source_id": "SRC_104",
          "target_id": "104"
        },
        {
          "source_id": "SRC_105",
          "target_id": "105"
        },
        {
          "source_id": "SRC_106",
          "target_id": "106"
        },
        {
          "source_id": "SRC_1324",
          "target_id": "1324"
        },
        {
          "source_id": "SRC_1325",
          "target_id": "1325"
        },
        {
          "source_id": "SRC_1326",
          "target_id": "1326"
        },
        {
          "source_id": "SRC_1429",
          "target_id": "1429"
        },
        {
          "source_id": "SRC_1430",
          "target_id": "1430"
        },
        {
          "source_id": "SRC_1431",
          "target_id": "1431"
        },
        {
          "source_id": "SRC_1432",
          "target_id": "1432"
        },
        {
          "source_id": "SRC_1537",
          "target_id": "1537"
        },
        {
          "source_id": "SRC_1538",
          "target_id": "1538"
        },
        {
          "source_id": "SRC_1965",
          "target_id": "1965"
        },
        {
          "source_id": "SRC_1966",
          "target_id": "1966"
        },
        {
          "source_id": "SRC_1967",
          "target_id": "1967"
        }
      ],
      "Number of Failed Entities": [
        {
          "number_of_failed_root_entities": 0
        }
      ],
      "List of Failed Entities": []
    },
    "List of Reference Tables": {
      "Number of Copied Reference Tables": [
        {
          "count": 0
        }
      ],
      "List of Copied Reference Tables": [],
      "Number of Failed Reference Tables": [
        {
          "count": 0
        }
      ],
      "List of Failed Reference Tables": []
    },
    "Error Summary": [],
    "Error Details": [],
    "Statistics Report": [
      {
        "lu_name": "Billing",
        "table_name": "BALANCE",
        "target_count": "172",
        "source_count": "172",
        "diff": "0",
        "results": "OK"
      },
      {
        "lu_name": "Billing",
        "table_name": "INVOICE",
        "target_count": "107",
        "source_count": "107",
        "diff": "0",
        "results": "OK"
      },
      {
        "lu_name": "Billing",
        "table_name": "OFFER",
        "target_count": "9",
        "source_count": "9",
        "diff": "0",
        "results": "OK"
      },
      {
        "lu_name": "Billing",
        "table_name": "SUBSCRIBER",
        "target_count": "17",
        "source_count": "17",
        "diff": "0",
        "results": "OK"
      }
    ],
    "Replace Sequence Summary Report": []
  },
  "message": ""
}
```



## Extract Tasks - Get Execution Reports on a Given LU

### API URL

/migrateStatusWs

### HTTP Method

POST

### API Category

TDM_Tasks

### API Description

Gets a summary of detailed information of the input batch IDs (LU execution). This API gets the run mode and a list of batch IDs or only one batch id as input values.
The API's run mode can have the following values:

- 'D': detailed execution. Returns detailed information of all entities and their execution status.

- 'S': summary information of the execution

- 'H': get the batch command.



### API Input

```
{
  "migrateIds": {},
  "runModes": [
    "string"
  ]
}
```

**Note:**

The **migrateIds** parameter is populated by the batch IDs related to the task execution. The batch ID is populated in the **fabric_execution_id** attribute of **/task/{taskExeId}/history** API's output.

#### API Input Examples

##### Get a Summary Report

```json
{"migrateIds": "3e8e8e44-9c97-4921-a622-f29161a8a112", "runModes": ["S", "H"]}
```



##### Get Summary and Detailed Reports with the Entity List and their Execution Status

```json
{"migrateIds": "3e8e8e44-9c97-4921-a622-f29161a8a112", "runModes": ["D", "H", "S"]}
```


 [![Previous](/articles/images/Previous.png)](01_tdm_basic_task_execution_flow.md)
