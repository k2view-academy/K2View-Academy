# Get Tasks List APIs

### API URL

/regularTasksByUser

### HTTP Method

GET

### API Category

custom_TDM_Tasks

### API Description

Gets the list of regular active tasks (version_ind is 'false', task_status and task_execution_status columns are 'Active') for a user based on the user's permission group (admin, owner, or tester) and based on the user's TDM environment roles:

- **Admin Users:**
  
  - Get all active tasks.
  
- **Owner Users:**
  - Get all active extract tasks if the user is the owner of at least one source environment.
  - Get all active load tasks if the user is the owner of at least one source environment and one target environment.
  - Get all active extract tasks that do not require special permissions if the user has at least one Read TDM Environment role.
  - Get all active extract tasks that require special permissions if the user has at least one Read TDM Environment role with these permissions.
  - Get all active load tasks that do not require special permissions if the user has at least one Read TDM Environment role and one Write TDM Environment role.
  - Get all active load tasks that require special permissions if the user has at least one Read TDM Environment role, and one Write TDM Environment role with these permissions.

- **Tester Users:**

  - Get all active extract tasks that do not require special permissions if the user has at least one Read TDM Environment role.
  - Get all active extract tasks that require special permissions if the user has at least one Read TDM Environment role with these permissions.
  - Get all active load tasks that do not require special permissions if the user has at least one Read TDM Environment role and one Write TDM Environment role.
  - Get all active load tasks that require special permissions if the user has at least one Read TDM environment role, and one Write TDM Environment role with these permissions.

  ### API Input

  None. The user and their groups are taken from the Fabric session.

  ### API Output Example

  ```json
  {
    "result": [
      {
        "task_title": "testTask",
        "task_id": 10
      },
      {
        "task_title": "testTask2",
        "task_id": 13
      },
      {
        "task_title": "testTask3",
        "task_id": 15
      }
    ],
    "errorCode": "SUCCESS",
    "message": null
  }
  ```
  
### API URL

/getTasksByParams

### HTTP Method

GET

### API Category

custom_TDM_Tasks

### API Description

Filters the tasks, returned by **/regularTasksByUser** API according the the input filtering parameters. The input is a dynamic JSON string. 
Currently it supports the following filtering parameters:
- **task_type** : LOAD/EXTRACT
- **version_ind** : true/false
- **load_entity** : true/false
- **delete_before_load** : true/false 
- **selection_method** : below is the list of the valid values:
  - 'L' (Entity list)
  - 'P' or 'PR' (Parameters), 
  - 'S' (Synthetic), 
  - 'R' (Random) 
- **sync_mode** : OFF/FORCE

The JSON filtering parameter is optional. If is it not populated, the API returns all user's tasks.

  ### API Input Examples:
   ```json
   {"task_type":"EXTRACT", "version_ind":true, "selection_method":"L", "sync_mode":"OFF"}
   ```
  
  ```json
  {"task_type":"EXTRACT", "version_ind":true, "load_entity":false, "delete_before_load":false, "selection_method":"L", "sync_mode":"FORCE"}
  ```
  
  ### API Output Example

  ```json
  {
    "result": [
      {
        "task_title": "testTask",
        "task_id": 10
      },
      {
        "task_title": "testTask2",
        "task_id": 13
      },
      {
        "task_title": "testTask3",
        "task_id": 15
      }
    ],
    "errorCode": "SUCCESS",
    "message": null
  }
  ```

  [![Previous](/articles/images/Previous.png)](01_tdm_basic_task_execution_flow.md)
