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
- **entitieslist**: populated by a list of entities separated by a comma. Note that the entity list can only contain one entity ID when executing a task with a Synthetic selection method.
- **sourceEnvironmentName**: source environment name
- **targetEnvironmentName**: target environment name 
- **taskGlobals**: list of Global variables and their values. 
- **numberOfEntities**: populated with a number to change the number of entities processed by the task. This parameter is only relevant for Load tasks when the **entitylist** override parameter is not set.
- **dataVersionExecId**: populated with the task execution id of the selected data version. The parameter can be set on Data Versioning load tasks.
- **dataVersionRetentionPeriod**: populated with the the retention period of the extracted data version. This parameter contains the unit (Hours, Days, Weeks..) and the value.
-  **reservationInd**: true/false. Set to true if the task execution needs to reserve the entities on the target environment. The parameter is relavant for load or reserve TDM tasks.
-  **reservationPeriod**: populated with the the reservation period of the task's entities. This parameter is contains the unit (Hours, Days, Weeks..) and the value.

The task execution is validated whether the execution parameters are overridden or taken from the task itself.

#### Validate the Task Execution Parameters

- Verify that the TDM task execution processes are up and running. If the TDM task execution processes are down, stop the task execution and return an error message.
- Test the connection details of the source and target environments of the task execution if the **forced** parameter is **false**.  
- Do not enable an execution if another execution with the same execution parameters is already running on the task.
- Validate the task's BE and LUs with the [TDM products](/articles/TDM/tdm_gui/11_environment_products_tab.md) of the task execution's source and target environment.
- Verify that the user is permitted to execute the task on the task execution's source and target environment. For example, the user cannot run a [Load task](/articles/TDM/tdm_gui/17_load_task_regular_mode.md) with a [sequence replacement](/articles/TDM/tdm_gui/10_environment_roles_tab.md#replace-sequences) on environment X if the user does not have permissions to run such a task on this environment.
- Validate the number of reserved entities: if the task reserves the entities wheather the reservationInd is set to true in the task itself or in the overridden parameters, accumulate the number of entities in the task to the total number of reserved entities for the user on the target environment. If the total number of reserved entities exceeds the user's permissions on the environment, return an error. For example, if the user is allowed to reserved up to 70 entities in ST1 and there are 50 entities that are already reserved for the user in ST1, the user can reserve up to additional 20 entities in ST1.

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
    "additionalProp2": "string"
  },
  "reservationInd": true,
  "reservationPeriod": {
    "additionalProp1": "string",
    "additionalProp2": "string"
  }
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
		"Customer.Global2": "value2"
		"Customer.CUST_DETAILS": "'{\"name\":\"John\", \"age\":30, \"car\":null}'" 
	},
	"reservationInd": true,
  	"reservationPeriod": {
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
	}
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
