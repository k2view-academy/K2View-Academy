# Execute Task API

### API URL

/task/{taskId}/forced/{forced}/startTask

### HTTP Method

POST

### API Category

TDM_Tasks

### API Description

Starts an execution of a given task and returns the execution's task_execution_id. 

### API Input

- taskId
- forced -  this parameter indicates if the execution should ignore a failure of the task's environment connections validation. If the 'forced' parameter is set to 'true', then the execution ignores the validation failure and executes the task. If the 'forced' parameter is set to 'false' and the environment validation fails, the execution is not initiated.

### API Input Example

```
http://localhost:3213/api/task/55/forced/true/startTask
```

### API Output Example

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
