# Execute Task API

### API URL

/task/{taskId}/forced/{forced}/startTask

### HTTP Method

POST

### API Category

TDM_Tasks

### API Description

Starts a task execution and return the execution's task_execution_id on success. The API can get an optional input of overriden parameters for the task execution.
The following parameters can be set:



<table width="900pxl">
<tbody>
<tr>
<td width="225pxl">
<p><strong>Parameter Name</strong></p>
</td>
<td width="225pxl">
<p><strong>Parameter Description</strong></p>
</td>
<td width="200pxl">
<p><strong>Task Types</strong></p>
</td>
<td width="150pxl">
<p><strong>Data Versioning </strong></p>
</td>
</tr>
<tr>
<td width="225pxl">
<p><strong>entitieslist</strong></p>
</td>
<td width="225pxl">
<p>Populated by a list of entities separated by a comma. Note that the entity list can only contain one entity ID when executing a task that clones an entity</p>
</td>
<td width="200pxl">
<p>Load task</p>
</td>
<td width="150pxl">
<p>True/False</p>
</td>
</tr>
<tr>
<td width="225pxl">
<p><strong>sourceEnvironmentName</strong></p>
</td>
<td width="225pxl">
<p>Source environment name</p>
</td>
<td width="200pxl">
<p>Load or Extract tasks</p>
</td>
<td width="150pxl">
<p>True/False</p>
</td>
</tr>
<tr>
<td width="225pxl">
<p><strong>targetEnvironmentName</strong></p>
</td>
<td width="225pxl">
<p>Target environment name</p>
</td>
<td width="200pxl">
<p>Load, Delete, or Reserve tasks</p>
</td>
<td width="123">
<p>True/False</p>
</td>
</tr>
<tr>
<td width="223">
<p><strong>taskGlobals</strong></p>
</td>
<td width="185">
<p>A list of Global variables (task variables) and their values</p>
</td>
<td width="99">
<p>All tasks</p>
</td>
<td width="123">
<p>True/False</p>
</td>
</tr>
<tr>
<td width="223">
<p><strong>numberOfEntities</strong></p>
</td>
<td width="185">
<p>Populated with a number to change the number of entities processed by the task. This parameter is only irrelevant for the entity list is not set in the overridden parameters.</p>
</td>
<td width="99">
<p>All tasks</p>
</td>
<td width="123">
<p>False</p>
</td>
</tr>
<tr>
<td width="223">
<p><strong>dataVersionExecId</strong></p>
</td>
<td width="185">
<p>Populated with the task execution id of the selected data version. The parameter can be set on Data Versioning load tasks.</p>
</td>
<td width="99">
<p>Load task</p>
</td>
<td width="123">
<p>True</p>
</td>
</tr>
<tr>
<td width="200pxl">
<p><strong>dataVersionRetentionPeriod</strong></p>
</td>
<td width="250pxl">
<p>Populated with the retention period of the extracted data version. This parameter contains the unit (Hours, Days, Weeks&hellip;) and the value.</p>
</td>
<td>Extract task</td>
<td>True</td>
</tr>
<tr>
<td width="223">
<p><strong>reserveInd</strong></p>
</td>
<td width="185">
<p>Populated with true or false. Set to true if the task execution needs to reserve the entities on the target environment.</p>
</td>
<td width="99">
<p>Load or Reserve tasks</p>
</td>
<td width="123">
<p>Load task: True/False</p>
<p>&nbsp;</p>
<p>Reserve task: N/A</p>
</td>
</tr>
<tr>
<td width="223">
<p><strong>reserveRetention</strong></p>
</td>
<td width="185">
<p>Populated with the reservation period of the task's entities. This parameter contains the unit (Hours, Days, Weeks.) and the value.</p>
</td>
<td width="99">
<p>Load or Reserve tasks</p>
</td>
<td width="123">
<p>Load task: True/False</p>
<p>&nbsp;</p>
<p>Reserve task: N/A</p>
</td>
</tr>
<tr>
<td width="223">
<p><strong>executionNote</strong></p>
</td>
<td width="185">
<p>Free text. Add a note to the execution.</p>
</td>
<td width="99">
<p>All tasks</p>
</td>
<td width="123">
<p>True/False</p>
</td>
</tr>
</tbody>
</table>



#### Validate the Task Execution Parameters

- Verify that the TDM task execution processes are up and running. If the TDM task execution processes are down, stop the task execution and return an error message.
- Test the connection details of the source and target environments of the task execution if the **forced** parameter is **false**.  
- Do not enable an execution if another execution with the same execution parameters is already running on the task.
- Validate the task's BE and LUs with the [TDM products](/articles/TDM/tdm_gui/11_environment_products_tab.md) of the task execution's source and target environment.
- Verify that the user is permitted to execute the task on the task execution's source and target environment. For example, the user cannot run a [Load task](/articles/TDM/tdm_gui/17_load_task_regular_mode.md) with a [sequence replacement](/articles/TDM/tdm_gui/10_environment_roles_tab.md#replace-sequences) on environment X if the user does not have permissions to run such a task on this environment.

##### Data Versioning Validations

- Data versioning extract tasks: validate the retention period to verify that it does not exceed the maximum days allowed for the tester.

##### Entity Reservation Validations

- Validate the number of reserved entities: if the task reserves the entities wheather the reservationInd is set to true in the task itself or in the overridden parameters, accumulate the number of entities in the task to the total number of reserved entities for the user on the target environment. If the total number of reserved entities exceeds the user's permissions on the environment, return an error. For example, if the user is allowed to reserved up to 70 entities in ST1 and there are 50 entities that are already reserved for the user in ST1, the user can reserve up to additional 20 entities in ST1.
- Validate the retention period to verify that the number of days does not exceed the the maximum number of days allowed for the tester.

If at least one of the validations fail, the API does not start the task and returns the validation errors.

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

- reverseRetentionPeriod

  

#### Start the Task Execution

If the validations pass successfully, start the task execution by populating the following TDM DB tables:

- [TASK_EXECUTION_LIST](/articles/TDM/tdm_architecture/02_tdm_database.md#task_execution_list) - populate the source and target environments by the task execution's environments:
  - If the overriden parameters include the task execution envionments, get the execution environments from the overriden parameters. Else, get the execution environments from the [task record](/articles/TDM/tdm_gui/25_task_tdmdb_tables.md#environments-columns).
- [TASK_EXECUTION_OVERRIDE_ATTRS](/articles/TDM/tdm_architecture/02_tdm_database.md#task_execution_override_attrs) -  populate the JSON of the overridden parameter by param name and param value pairs. For example- "SOURCE_ENVIRONMENT": "ST1"

### API Input

- **taskId**

- **forced** -  this parameter indicates if the execution should ignore a failure of the task's environment connections validation. If the **forced** parameter is set to **true**, then the execution ignores the validation failure and executes the task. If the **forced** parameter is set to **false** and the environment validation fails, the execution is not initiated.

- An optional request body with overriden parameters for task execution. It is possible to populate all, part , or none of the overriden parameters.

  ```json
  {
    "entitieslist": "string",
    "sourceEnvironmentName": "string",
    "targetEnvironmentName": "string",
    "taskGlobals": {
      "additionalProp1": "string",
      "additionalProp2": "string",
      "additionalProp3": "string"
    },
    "numberOfEntities": 0,
    "dataVersionExecId": 0,
    "dataVersionRetentionPeriod": {
      "additionalProp1": "string",
      "additionalProp2": "string",
      "additionalProp3": "string"
    },
    "reserveInd": true,
    "reserveRetention": {
      "additionalProp1": {}
    },
    "executionNote": "string"
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
	"entitieslist": "1,2,4,9,8,11",
	"sourceEnvironmentName": "SRC1",
	"targetEnvironmentName": "TAR1",
	"taskGlobals": {
		"MASKING_FLAG": "0",
		"Customer.Global2": "value2",
		"Customer.CUST_DETAILS": "'{\"name\":\"John\", \"age\":30, \"car\":null}'" 
	},
	"reserveInd": true,
  	"reserveRetention": {
    	"unit": "Days",
		"value": "10"
  }
	
}
```

   

##### Example 2

```json
{
	"sourceEnvironmentName": "SRC1",
	"targetEnvironmentName": "TAR1",
	"taskGlobals": {
		"MASKING_FLAG": "0",
		"Customer.Global2": "value2"
	},
 "numberOfEntities": 10
}
```



##### Example 3 - Data Versioning Load Task

Override the selected version

```json
{
	"sourceEnvironmentName": "TAR1",
	"targetEnvironmentName": "TAR1",
	"taskGlobals": {
		"MASKING_FLAG": "0"
	},
 "dataVersionExecId": 10
}
```

##### Example 4 - Data Versioning Extract Task
```json
{
	"entitieslist": "1,2,4,9,8,11,33",
	"sourceEnvironmentName": "TAR1",
	"taskGlobals": {
		"MASKING_FLAG": "0"
	},
 	"dataVersionRetentionPeriod": {
		"unit": "Days",
		"value": "10"
	},
    "executionNote": "Snapshot 1"
}
```


### API Output Examples

#### Validation Failure Examples

```json
{
    "result":[{"Number of entity":"The number of entities exceeds the number of entities in the write permission","selectionMethod":"The User has no permissions to run the task's selection method on the task's target environment"}],
    "errorCode":"FAIL",
    "message":"validation failure"
}
```



```json
{ 
    "result": 
    [{"reference": "The user has no permissions to run tasks on Reference tables on source environment", 
      "syncMode": "the user has no permissions to ask to always sync the data from the source."    } ], 
    "errorCode": "FAIL",
    "message": "validation failure"
} 
```



#### The Test Connection of the Task's Environment Fails

The test connection runs when the **forced** input parameter is set to **false**.

```json
{"errorCode":"FAIL","message":"The test connection of [CRM_DB] failed. Please check the connection details of target environment TAR"}
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
