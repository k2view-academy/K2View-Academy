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
  - Get all active extract tasks that do not require special permissions (that is, tasks that do not include reference tables, do not require up-to-date data, or that do not run on all entities) if the user has a Read TDM Environment role on at least one TDM environment.
  - Get all active extract tasks that include refernece tables or that require up-to-date data if the user has a Read TDM Environment role with these permissions on at least one TDM environment.
  - Get all active load tasks that do not require special permissions (that is, tasks that do not include reference tables, do not include Synthetic or Random selection methods, tasks that do not have Sequence replacement, or tasks that do not include a delete of entities from the target system) if the user has a Read TDM Environment role on at least one TDM environment and a Write TDM Environment role on at least one TDM environment.
  - Get all active load tasks that require special permissions if the user has at least one Read TDM Environment role, and one Write TDM Environment role with these permissions.

- **Tester Users:**

  - Get all active extract tasks that do not require special permissions (that is, tasks that do not include reference tables, do not require up-to-date data, or that do not run on all entities) if the user has a Read TDM Environment role on at least one TDM environment.
  - Get all active extract tasks that include refernece tables or require an up-to-date data if the user has a Read TDM Environment role with these permissions on at least one TDM environment.
  - Get all active load tasks that do not require special permissions (that is, tasks that do not include reference tables, do not include Synthetic or Random selection methods, tasks that do not have Sequence replacement, or tasks that do not include a delete of entities from the target system) if the user has a Read TDM Environment role on at least one TDM environment and a Write TDM Environment role on at least one TDM environment.
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
  -  'REF' (Reference Only)
- **sync_mode** : OFF/FORCE

The JSON filtering parameter is optional. If is it not populated, the API returns all user's tasks.

### API Input
  
- filteringParams - this is an optional String parameter that can be populated by a JSON with a list of filtering parameters.

### API Input Examples:
   ```json
   {"task_type":"EXTRACT", "version_ind":true, "selection_method":"L", "sync_mode":"FORCE"}
   ```
  
  ```json
  {"task_type":"EXTRACT", "version_ind":true, "load_entity":false, "delete_before_load":false, "selection_method":"L", "sync_mode":"FORCE"}
  ```
### API Request URL Examples:

```
http://10.21.3.4:3213/api/getTasksByParams
```

```
http://10.21.3.4:3213/api/getTasksByParams?filteringParams=%7B%22task_type%22%3A%22LOAD%22%2C%20%22load_entity%22%3Afalse%2C%20%22delete_before_load%22%3Atrue%2C%20%22selection_method%22%3A%22L%22%7D

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
