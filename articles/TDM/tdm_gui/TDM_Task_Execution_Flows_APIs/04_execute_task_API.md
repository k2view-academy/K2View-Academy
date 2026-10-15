# Execute Task API

### API URL

/task/{taskId}/forced/{forced}/startTask

### HTTP Method

POST

### API Category

TDM_Tasks

### API Description

Starts a task execution and returns the execution's task_execution_id on success. The API can get an optional input of overridden parameters for the task execution. Starting from TDM 10.1, the API can also get an optional ID of a previous execution of the task, in order to rerun the task with the override parameters of that execution.

#### Validate the Task Execution Parameters

- Verify that the TDM task execution processes are up and running. If the TDM task execution processes are down, stop the task execution and return an error message.
- Test the connection details of the source and target environments of the task execution if the **forced** parameter is **false**.  
- Do not enable an execution if another execution with the same execution parameters is already running on the task.
- Validate the task's BE and LUs with the [TDM Systems](/articles/TDM/tdm_gui/11_environment_products_tab.md) of the task execution's source and target environment.
- Verify that the user is permitted to execute the task on the task execution's source and target environment. For example, the user cannot run a [Load task](/articles/TDM/tdm_gui/17_load_task_regular_mode.md) with a [sequence replacement](/articles/TDM/tdm_gui/10_environment_roles_tab.md#replace-sequences) on environment X if the user does not have permissions to run such a task on this environment.

##### Data Versioning Validations

- Data versioning extract tasks: validate the retention period to verify that it does not exceed the maximum days allowed for the tester.

##### Entity Reservation Validations

- Validate the number of reserved entities: if the task reserves the entities whether the RESERVE_IND is set to **true** in the task itself or in the overridden parameters, accumulate the number of entities in the task to the total number of reserved entities for the user on the target environment. If the total number of reserved entities exceeds the user's permissions on the environment, return an error. For example, if the user is allowed to reserved up to 70 entities in ST1 and there are 50 entities that are already reserved for the user in ST1, the user can reserve up to 20 additional entities in ST1.
- Validate the retention period to verify that the number of days does not exceed the maximum number of days allowed for the tester.

If at least one of the validations fail, the API does not start the task. Instead, it returns a FAILED status and the validation errors.

Below is the list of the validation codes, returned by the API:

- BEandLUs

- Reference

- selectionMethod

- Versioning

- ReplaceSequence

- DeleteBeforeLoad

- syncMode

- totalNumberOfReservedEntities

- versioningRetentionPeriod

- reserveRetentionPeriod

  

#### Start the Task Execution

If the validations pass successfully, start the task execution by populating the following TDM DB tables:

- [TASK_EXECUTION_LIST](/articles/TDM/tdm_architecture/02_tdm_database.md#task_execution_list) - populate the source and target environments by the task execution's environments:
  - If the overridden parameters include the task execution environments, get the execution environments from the overridden parameters. Else, get the execution environments from the [task record](/articles/TDM/tdm_gui/25_task_tdmdb_tables.md#environments-columns).
- [TASK_EXECUTION_OVERRIDE_ATTRS](/articles/TDM/tdm_architecture/02_tdm_database.md#task_execution_override_attrs) -  populate the JSON of the overridden parameters by param name and param value pairs. For example- "SOURCE_ENVIRONMENT_NAME": "ST1"

### API Input

- **taskId**

- **forced** -  this parameter indicates if the execution should ignore a failure of the task's environment connections validation. If the **forced** parameter is set to **true**, then the execution ignores the validation failure and executes the task. If the **forced** parameter is set to **false** and the environment validation fails, the execution is not initiated.

- An optional request body with the following attributes:

  - **overrideParameters** - an optional object with the overridden parameters of the task execution. It is possible to populate all, part, or none of the overridden parameters. The parameter names are set in uppercase, for example: ENTITY_LIST, SOURCE_ENVIRONMENT_NAME.

  - **taskExecutionId** - *new in TDM 10.1*. An optional ID of an existing execution of the task. The override parameters of this execution (stored in the TASK_EXECUTION_OVERRIDE_ATTRS TDM DB table) are used as a baseline for the new execution:
    - Override parameters that are also populated in **overrideParameters** take precedence over the baseline.
    - Parameters that are populated neither in the baseline nor in **overrideParameters** are taken from the task itself.
    - A new task execution is always created. The input task execution ID is checked as a baseline.

The following override parameters can be set in the **overrideParameters** object:

<table width="900pxl">
<tbody>
<tr>
<td width="250pxl"><strong>Parameter Name</strong></td>
<td width="450pxl"><strong>Parameter Description</strong></td>
<td width="200pxl"><strong>Relevant For</strong></td>
</tr>
<tr>
<td valign="top"><strong>BE_ID</strong></td>
<td valign="top">The Business Entity ID of the task.</td>
<td valign="top">All tasks</td>
</tr>
<tr>
<td valign="top"><strong>ENTITY_LIST</strong></td>
<td valign="top">A list of entities separated by a comma. Note that the entity list can only contain one entity ID when executing a task with an Entity Clone selection method.</td>
<td valign="top">Load or Extract tasks, including Data Versioning tasks</td>
</tr>
<tr>
<td valign="top"><strong>SOURCE_ENVIRONMENT_NAME</strong></td>
<td valign="top">Source environment name.</td>
<td valign="top">Load or Extract tasks, including Data Versioning tasks</td>
</tr>
<tr>
<td valign="top"><strong>TARGET_ENVIRONMENT_NAME</strong></td>
<td valign="top">Target environment name.</td>
<td valign="top">Load, Delete, or Reserve tasks, including Data Versioning tasks</td>
</tr>
<tr>
<td valign="top"><strong>TASK_GLOBALS</strong></td>
<td valign="top">A map of Global variable (task variable) names and their override values. If a Global's value is a JSON, add a backslash to the values (see Example 1).</td>
<td valign="top">All tasks</td>
</tr>
<tr>
<td valign="top"><strong>NO_OF_ENTITIES</strong></td>
<td valign="top">A number to change the number of entities processed by the task. This parameter is only relevant when the ENTITY_LIST override parameter is not set and the task's selection method is not Entity List.</td>
<td valign="top">All tasks, excluding Data Versioning tasks</td>
</tr>
<tr>
<td valign="top"><strong>SELECTED_VERSION_TASK_EXE_ID</strong></td>
<td valign="top">The task execution ID of the selected data version.</td>
<td valign="top">Data Versioning Load tasks</td>
</tr>
<tr>
<td valign="top"><strong>SELECTED_SUBSET_TASK_EXE_ID</strong></td>
<td valign="top"><i>New in TDM 10.1.</i> The task execution ID of a previously generated (AI-based or rule-based) subset to reload.</td>
<td valign="top">Generate Subset Load tasks</td>
</tr>
<tr>
<td valign="top"><strong>DATAFLUX_RETENTION_PARAMS</strong></td>
<td valign="top">The retention period of the extracted data version. This parameter contains the units (Hours, Days, Weeks…) and the value.</td>
<td valign="top">Data Versioning Extract tasks</td>
</tr>
<tr>
<td valign="top"><strong>RESERVE_IND</strong></td>
<td valign="top"><strong>true</strong> or <strong>false</strong>. Set to <strong>true</strong> if the task execution needs to reserve the entities on the target environment.</td>
<td valign="top">Load tasks (including Data Versioning) or Reserve tasks</td>
</tr>
<tr>
<td valign="top"><strong>RESERVE_RETENTION_PARAMS</strong></td>
<td valign="top">The reservation period of the task's entities. This parameter contains the units (Hours, Days, Weeks…) and the value.</td>
<td valign="top">Load tasks (including Data Versioning) or Reserve tasks</td>
</tr>
<tr>
<td valign="top"><strong>EXECUTION_NOTE</strong></td>
<td valign="top">Free text. Add a note to the execution.</td>
<td valign="top">All tasks</td>
</tr>
<tr>
<td valign="top"><strong>SELECTION_METHOD</strong></td>
<td valign="top"><i>New in TDM 10.1.</i> Overrides the task's selection method. Only the following values are supported:<ul><li>'L' - Entity list. Requires ENTITY_LIST.</li><li>'ALL' - Predefined entity list.</li><li>'R' - Random selection. Requires NO_OF_ENTITIES.</li><li>'C' - Custom logic. Requires NO_OF_ENTITIES. CUSTOM_LOGIC_FLOW and CUSTOM_LOGIC_LU_NAME must be set either in the override parameters or in the task definition.</li></ul></td>
<td valign="top">Entity-based tasks</td>
</tr>
<tr>
<td valign="top"><strong>PARAMETERS</strong></td>
<td valign="top"><i>New in TDM 10.1.</i> A JSON string that overrides the task's filter or input parameters. The structure depends on the selection method:<ul><li>Custom Logic ('C'): <code>{"inputs": [{"name": "...", "value": "..."}]}</code></li><li>Business Parameters ('P' or 'PR'): the full parameter group JSON.</li></ul></td>
<td valign="top">Custom Logic or Business Parameters selection methods</td>
</tr>
<tr>
<td valign="top"><strong>BP_QUERY</strong></td>
<td valign="top"><i>New in TDM 10.1.</i> A SQL WHERE clause that overrides the business parameters filter of the task.</td>
<td valign="top">Business Parameters selection methods: 'P' (Parameters) or 'PR' (Parameters with Random selection)</td>
</tr>
<tr>
<td valign="top"><strong>GENERATE_DATA_PARAMS</strong></td>
<td valign="top"><i>New in TDM 10.1.</i> A map of synthetic data generation parameter overrides. Each key is a parameter name, and each value is an object with a <strong>value</strong> field.</td>
<td valign="top">'GENERATE' selection method</td>
</tr>
<tr>
<td valign="top"><strong>CUSTOM_LOGIC_FLOW</strong></td>
<td valign="top"><i>New in TDM 10.1.</i> Overrides the flow name of the Custom Logic selection method.</td>
<td valign="top">Custom Logic selection method</td>
</tr>
<tr>
<td valign="top"><strong>CUSTOM_LOGIC_LU_NAME</strong></td>
<td valign="top"><i>New in TDM 10.1.</i> Overrides the LU name of the Custom Logic selection method.</td>
<td valign="top">Custom Logic selection method</td>
</tr>
<tr>
<td valign="top"><strong>LOGICAL_UNITS</strong></td>
<td valign="top"><i>New in TDM 10.1.</i> A list of objects, one per LU, that override the per-LU settings. Each entry must include <strong>lu_id</strong> and <strong>lu_name</strong>, and may include <strong>max_no_of_workers</strong>, <strong>source_affinity</strong>, and <strong>target_affinity</strong>.</td>
<td valign="top">Entity-based tasks</td>
</tr>
<tr>
<td valign="top"><strong>TABLE_FILTERS</strong></td>
<td valign="top"><i>New in TDM 10.1.</i> A list of objects that override the filter values of the tables included in the task. Each entry must include <strong>ref_table_name</strong> and a <strong>fields</strong> array, and may include <strong>interface_name</strong> and <strong>schema_name</strong>. Each <strong>fields</strong> item must include <strong>field</strong>, and may include <strong>condition</strong> and <strong>value</strong>.</td>
<td valign="top">Tasks that include tables</td>
</tr>
<tr>
<td valign="top"><strong>TABLE_SAMPLING</strong></td>
<td valign="top"><i>New in TDM 10.1.</i> A list of objects that override the record sampling of the tables included in the task, for this execution only. Each entry must include <strong>ref_table_name</strong>, and may include <strong>interface_name</strong>, <strong>schema_name</strong>, <strong>records_limit</strong> (number of records per partition), and <strong>partitions_limit</strong> (number of partitions).<ul><li>A limit must be a positive integer. Set it to null or -1 to remove the limit. An omitted limit keeps the task's value.</li><li><strong>partitions_limit</strong> cannot exceed the table's number of partitions in the source environment. This is not checked when loading from a data version.</li><li>A limit is rejected on the parent side of a foreign key relation between the task's tables.</li></ul></td>
<td valign="top">Tasks that include tables</td>
</tr>
<tr>
<td valign="top"><strong>PRE_EXECUTION_PROCESSES_PARAMS</strong></td>
<td valign="top"><i>New in TDM 10.1.</i> A list of objects that override the input parameters of the task's pre-execution processes. Each entry must include <strong>process_id</strong> and a <strong>parameter_overrides</strong> array. Each <strong>parameter_overrides</strong> item must include <strong>name</strong> and <strong>value</strong>.</td>
<td valign="top">Tasks with pre-execution processes</td>
</tr>
<tr>
<td valign="top"><strong>POST_EXECUTION_PROCESSES_PARAMS</strong></td>
<td valign="top"><i>New in TDM 10.1.</i> The same structure as PRE_EXECUTION_PROCESSES_PARAMS, applied to the task's post-execution processes.</td>
<td valign="top">Tasks with post-execution processes</td>
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
      "additionalProp1": "string"
    },
    "NO_OF_ENTITIES": 0,
    "SELECTED_VERSION_TASK_EXE_ID": 0,
    "SELECTED_SUBSET_TASK_EXE_ID": 0,
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
    "GENERATE_DATA_PARAMS": {
      "additionalProp1": {"value": "string"}
    },
    "CUSTOM_LOGIC_FLOW": "string",
    "CUSTOM_LOGIC_LU_NAME": "string",
    "LOGICAL_UNITS": [
      {"lu_id": 0, "lu_name": "string", "max_no_of_workers": 0, "source_affinity": "string", "target_affinity": "string"}
    ],
    "TABLE_FILTERS": [
      {"ref_table_name": "string", "interface_name": "string", "schema_name": "string",
       "fields": [{"field": "string", "condition": "string", "value": "string"}]}
    ],
    "TABLE_SAMPLING": [
      {"ref_table_name": "string", "interface_name": "string", "schema_name": "string", "records_limit": 0, "partitions_limit": 0}
    ],
    "PRE_EXECUTION_PROCESSES_PARAMS": [
      {"process_id": 0, "parameter_overrides": [{"name": "string", "value": "string"}]}
    ],
    "POST_EXECUTION_PROCESSES_PARAMS": [
      {"process_id": 0, "parameter_overrides": [{"name": "string", "value": "string"}]}
    ]
  },
  "taskExecutionId": 0
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

##### Example 5 - Override the LU Settings, Table Filters and Sampling, and Pre/Post Execution Process Parameters

```json
{
  "overrideParameters": {
    "LOGICAL_UNITS": [
      {
        "lu_id": 1,
        "lu_name": "Customer",
        "max_no_of_workers": 5,
        "source_affinity": "DC1",
        "target_affinity": "DC2"
      }
    ],
    "TABLE_FILTERS": [
      {
        "ref_table_name": "RefT",
        "interface_name": "RefInterface",
        "schema_name": "RefSchema",
        "fields": [
          {"field": "col1", "condition": "=", "value": "val1"}
        ]
      }
    ],
    "TABLE_SAMPLING": [
      {
        "ref_table_name": "RefT",
        "interface_name": "RefInterface",
        "schema_name": "RefSchema",
        "records_limit": 100,
        "partitions_limit": 2
      }
    ],
    "PRE_EXECUTION_PROCESSES_PARAMS": [
      {
        "process_id": 1,
        "parameter_overrides": [
          {"name": "param1", "value": "v1"}
        ]
      }
    ],
    "POST_EXECUTION_PROCESSES_PARAMS": [
      {
        "process_id": 2,
        "parameter_overrides": [
          {"name": "param1", "value": "v1"}
        ]
      }
    ]
  }
}
```

##### Example 6 - Rerun a Previous Execution

Rerun the task with the override parameters of execution 120, and override its number of entities:

```json
{
  "taskExecutionId": 120,
  "overrideParameters": {
    "NO_OF_ENTITIES": 20
  }
}
```

### API Output Examples

#### Validation Failure Examples

Entity or environment permission errors - the validation errors are returned in the **result**:

```json
{
  "result": {
    "Number of entity": "Exceeds write permission"
  },
  "errorCode": "FAILED",
  "message": "validation failure"
}
```

Invalid override parameters or permission errors - the error is returned in the **message**:

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
{"errorCode":"FAILED","message":"The test connection of [CRM_DB] failed. Please check the connection details of target environment TAR"}
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

ease check the connection details of target environment TAR"}
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

 [![Previous](/articles/images/Previous.png)](01_tdm_basic_task_execution_flow.md)
