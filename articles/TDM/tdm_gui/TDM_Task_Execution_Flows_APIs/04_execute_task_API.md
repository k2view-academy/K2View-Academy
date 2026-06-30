# Execute Task API

### API URL

/task/{taskId}/forced/{forced}/startTask

### HTTP Method

POST

### API Category

TDM_Tasks

### API Description

Starts a task execution and returns the execution's task_execution_id on success. The API can get an optional input of overridden parameters for the task execution.

### API Input

- **taskId**

- **forced** - this parameter indicates if the execution should ignore a failure of the task's environment connections validation. If the **forced** parameter is set to **true**, then the execution ignores the validation failure and executes the task. If the **forced** parameter is set to **false** and the environment validation fails, the execution is not initiated.

- An optional request body that specifies runtime overrides for task execution. All override values are nested under the **overrideParameters** object. You can provide all, some, or none of the supported override parameters. The following parameters are available.

  **Note:** Only parameters that are [open for editing in the task](/articles/TDM/tdm_gui/14_task_overview.md#attributes-available-for-runtime-override) can be overridden.


<table width="900pxl">
<tbody>
<tr>
<td style="width: 250px;">
<p><strong>Key</strong></p>
</td>
<td style="width: 650px;">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td>
<p><strong>BE_ID</strong></p>
</td>
<td>
<p>Business Entity ID of the task.</p>
</td>
</tr>
<tr>
<td>
<p><strong>ENTITY_LIST</strong></p>
</td>
<td>
<p>Comma-separated list of entity IDs to process. When the task uses an Entity Clone selection method, only a single entity ID is allowed.</p>
</td>
</tr>
<tr>
<td>
<p><strong>SOURCE_ENVIRONMENT_NAME</strong></p>
</td>
<td>
<p>Override the source environment name.</p>
</td>
</tr>
<tr>
<td>
<p><strong>TARGET_ENVIRONMENT_NAME</strong></p>
</td>
<td>
<p>Override the target environment name.</p>
</td>
</tr>
<tr>
<td>
<p><strong>TASK_GLOBALS</strong></p>
</td>
<td>
<p>Map of Fabric Global variable names to their override values. If a Global's value is a JSON string, escape internal quotes with a backslash.</p>
</td>
</tr>
<tr>
<td>
<p><strong>NO_OF_ENTITIES</strong></p>
</td>
<td>
<p>Override the number of entities processed by the task. Applicable only when ENTITY_LIST is not set and the task selection method is not Entity List.</p>
</td>
</tr>
<tr>
<td>
<p><strong>SELECTED_VERSION_TASK_EXE_ID</strong></p>
</td>
<td>
<p>Task execution ID of the selected data version. Applicable to Data Versioning load tasks only.</p>
</td>
</tr>
<tr>
<td>
<p><strong>DATAFLUX_RETENTION_PARAMS</strong></p>
</td>
<td>
<p>Retention period of the extracted data version. Object with two fields: <strong>units</strong> (Hours, Days, or Weeks) and <strong>value</strong> (integer).</p>
</td>
</tr>
<tr>
<td>
<p><strong>RESERVE_IND</strong></p>
</td>
<td>
<p>Boolean (true or false). Set to true to reserve the task entities on the target environment.</p>
</td>
</tr>
<tr>
<td>
<p><strong>RESERVE_RETENTION_PARAMS</strong></p>
</td>
<td>
<p>Reservation period for the task entities. Object with two fields: <strong>units</strong> (Hours, Days, or Weeks) and <strong>value</strong> (integer).</p>
</td>
</tr>
<tr>
<td>
<p><strong>EXECUTION_NOTE</strong></p>
</td>
<td>
<p>Free-text note attached to the execution.</p>
</td>
</tr>
<tr>
<td>
<p><strong>SELECTION_METHOD</strong></p>
</td>
<td>
<p>Override the task selection method. Supported values: 'L' (entity list — requires ENTITY_LIST), 'ALL' (predefined entity list), 'R' (random — requires NO_OF_ENTITIES), 'C' (custom logic — requires NO_OF_ENTITIES; CUSTOM_LOGIC_FLOW and CUSTOM_LOGIC_LU_NAME must be present in the override or in the task definition).</p>
</td>
</tr>
<tr>
<td>
<p><strong>PARAMETERS</strong></p>
</td>
<td>
<p>JSON string overriding the task's filter or input parameters. For Custom Logic ('C'): <code>{"inputs": [{"name": "...", "value": "..."}]}</code>. For Business Parameters ('P'/'PR'): the full parameter group JSON.</p>
</td>
</tr>
<tr>
<td>
<p><strong>BP_QUERY</strong></p>
</td>
<td>
<p>SQL WHERE clause string overriding the business parameter filter. Applicable only for selection methods 'P' (Parameters) or 'PR' (Parameters with Random selection).</p>
</td>
</tr>
<tr>
<td>
<p><strong>GENERATE_DATA_PARAMS</strong></p>
</td>
<td>
<p>Map of synthetic data generation parameter overrides. Each key is a parameter name; each value is an object with a "value" field. Applicable only when the selection method is 'GENERATE'.</p>
</td>
</tr>
<tr>
<td>
<p><strong>CUSTOM_LOGIC_FLOW</strong></p>
</td>
<td>
<p>Override the flow name used for a Custom Logic selection method.</p>
</td>
</tr>
<tr>
<td>
<p><strong>CUSTOM_LOGIC_LU_NAME</strong></p>
</td>
<td>
<p>Override the LU name used for a Custom Logic selection method.</p>
</td>
</tr>
<tr>
<td>
<p><strong>LOGICAL_UNITS</strong></p>
</td>
<td>
<p>List of per-LU override objects. Each entry must include <strong>lu_id</strong> and <strong>lu_name</strong>, and may optionally include <strong>max_no_of_workers</strong>, <strong>source_affinity</strong>, and <strong>target_affinity</strong>.</p>
</td>
</tr>
<tr>
<td>
<p><strong>TABLE_FILTERS</strong></p>
</td>
<td>
<p>List of reference table filter overrides. Each entry must include <strong>ref_table_name</strong> and a <strong>fields</strong> array. Each fields item must include <strong>field</strong> and may include <strong>condition</strong> and <strong>value</strong>.</p>
</td>
</tr>
<tr>
<td>
<p><strong>PRE_EXECUTION_PROCESSES_PARAMS</strong></p>
</td>
<td>
<p>List of pre-execution process parameter overrides. Each entry must include <strong>process_id</strong> and a <strong>parameter_overrides</strong> array of {name, value} objects.</p>
</td>
</tr>
<tr>
<td>
<p><strong>POST_EXECUTION_PROCESSES_PARAMS</strong></p>
</td>
<td>
<p>Same structure as PRE_EXECUTION_PROCESSES_PARAMS, applied to post-execution processes.</p>
</td>
</tr>
</tbody>
</table>

#### Request Body Structure


  ```json
  {
    "overrideParameters": {
      "BE_ID": "string",
      "ENTITY_LIST": "string",
      "SOURCE_ENVIRONMENT_NAME": "string",
      "TARGET_ENVIRONMENT_NAME": "string",
      "TASK_GLOBALS": {
        "GlobalName": "value"
      },
      "NO_OF_ENTITIES": 0,
      "SELECTED_VERSION_TASK_EXE_ID": 0,
      "DATAFLUX_RETENTION_PARAMS": {
        "units": "string",
        "value": "string"
      },
      "RESERVE_IND": true,
      "RESERVE_RETENTION_PARAMS": {
        "units": "string",
        "value": "string"
      },
      "EXECUTION_NOTE": "string",
      "SELECTION_METHOD": "string",
      "PARAMETERS": "string",
      "BP_QUERY": "string",
      "LOGICAL_UNITS": [
        {
          "lu_id": 0,
          "lu_name": "string",
          "max_no_of_workers": 0,
          "source_affinity": "string",
          "target_affinity": "string"
        }
      ],
      "TABLE_FILTERS": [
        {
          "ref_table_name": "string",
          "interface_name": "string",
          "schema_name": "string",
          "fields": [
            {
              "field": "string",
              "condition": "string",
              "value": "string"
            }
          ]
        }
      ],
      "PRE_EXECUTION_PROCESSES_PARAMS": [
        {
          "process_id": 0,
          "parameter_overrides": [
            {
              "name": "string",
              "value": "string"
            }
          ]
        }
      ],
      "POST_EXECUTION_PROCESSES_PARAMS": [
        {
          "process_id": 0,
          "parameter_overrides": [
            {
              "name": "string",
              "value": "string"
            }
          ]
        }
      ]
    },
    "draftExecutionId": 0
  }
  ```



### API Input Examples

#### API URL

```
http://localhost:3213/api/task/55/forced/true/startTask
```

#### Request Body Examples

##### Example 1

```json
{
  "overrideParameters": {
    "ENTITY_LIST": "1,2,4,9,8,11",
    "SOURCE_ENVIRONMENT_NAME": "SRC1",
    "TARGET_ENVIRONMENT_NAME": "TAR1",
    "TASK_GLOBALS": {
      "Customer.Global2": "value2",
      "Customer.CUST_DETAILS": "'{\"name\":\"John\", \"age\":30, \"car\":null}'"
    },
    "RESERVE_IND": true,
    "RESERVE_RETENTION_PARAMS": {
      "units": "Days",
      "value": "10"
    }
  }
}
```

   

##### Example 2

```json
{
  "overrideParameters": {
    "SOURCE_ENVIRONMENT_NAME": "SRC1",
    "TARGET_ENVIRONMENT_NAME": "TAR1",
    "TASK_GLOBALS": {
      "Customer.Global2": "value2"
    },
    "NO_OF_ENTITIES": 10
  }
}
```



##### Example 3 - Data Versioning Load Task

Override the selected version:

```json
{
  "overrideParameters": {
    "SOURCE_ENVIRONMENT_NAME": "TAR1",
    "TARGET_ENVIRONMENT_NAME": "TAR1",
    "TASK_GLOBALS": {
      "EMAIL": "john123@gmail.com"
    },
    "SELECTED_VERSION_TASK_EXE_ID": 10
  }
}
```

##### Example 4 - Data Versioning Extract Task

```json
{
  "overrideParameters": {
    "ENTITY_LIST": "1,2,4,9,8,11,33",
    "SOURCE_ENVIRONMENT_NAME": "TAR1",
    "TASK_GLOBALS": {
      "EMAIL": "john123@gmail.com"
    },
    "DATAFLUX_RETENTION_PARAMS": {
      "units": "Days",
      "value": "10"
    },
    "EXECUTION_NOTE": "Snapshot 1"
  }
}
```

##### Example 5 - Override Per-LU Settings

```json
{
  "overrideParameters": {
    "SOURCE_ENVIRONMENT_NAME": "SRC1",
    "TARGET_ENVIRONMENT_NAME": "TAR1",
    "NO_OF_ENTITIES": 5,
    "LOGICAL_UNITS": [
      {
        "lu_id": 1,
        "lu_name": "Customer",
        "max_no_of_workers": 5,
        "source_affinity": "DC1",
        "target_affinity": "DC2"
      }
    ]
  }
}
```


### API Output Examples

#### Validation Failure Examples

```json
{
    "result": {"Number of entity": "The number of entities exceeds the number of entities in the write permission", "selectionMethod": "The User has no permissions to run the task's selection method on the task's target environment"},
    "errorCode": "FAILED",
    "message": "validation failure"
}
```



```json
{ 
    "result": 
    {"reference": "The user has no permissions to run tasks on Reference tables on source environment", 
      "syncMode": "the user has no permissions to ask to always sync the data from the source."    }, 
    "errorCode": "FAILED",
    "message": "validation failure"
} 
```

#### Override Parameter Validation Error

```json
{
  "result": null,
  "errorCode": "FAILED",
  "message": "LOGICAL_UNITS[0] is missing required field 'lu_name'."
}
```



#### The Test Connection of the Task's Environment Fails

The test connection runs when the **forced** input parameter is set to **false**.

```json
{"errorCode": "FAILED", "message": "The test connection of [CRM_DB] failed. Please check the connection details of target environment TAR"}
```



#### Successful Execution Example

```json
{
  "result": {
    "taskExecutionId": 43
  },
  "errorCode": "SUCCESS",
  "message": null
}
```

### Backward Compatibility - TDM 9.5 Override Parameter Structure

TDM 10 changed the startTask request body: all override parameters are now wrapped in an **overrideParameters** object, and parameter names use uppercase keys (e.g., `ENTITY_LIST` instead of `entitieslist`).

If your integration code was built on TDM 9.5 and populates override parameters using the previous flat structure, you can invoke **V1 of the startTask API**. This version accepts the TDM 9.5 flat parameter structure and internally builds the new **overrideParameters** JSON required by TDM 10.

The TDM 9.5 flat parameter names and their TDM 10 equivalents are:

| TDM 9.5 Parameter | TDM 10 overrideParameters Key |
|---|---|
| entitieslist | ENTITY_LIST |
| sourceEnvironmentName | SOURCE_ENVIRONMENT_NAME |
| targetEnvironmentName | TARGET_ENVIRONMENT_NAME |
| taskGlobals | TASK_GLOBALS |
| numberOfEntities | NO_OF_ENTITIES |
| dataVersionExecId | SELECTED_VERSION_TASK_EXE_ID |
| dataVersionRetentionPeriod | DATAFLUX_RETENTION_PARAMS (note: key renamed from `unit` to `units`) |
| reserveInd | RESERVE_IND |
| reserveRetention | RESERVE_RETENTION_PARAMS (note: key renamed from `unit` to `units`) |
| executionNote | EXECUTION_NOTE |

 [![Previous](/articles/images/Previous.png)](01_tdm_basic_task_execution_flow.md)
