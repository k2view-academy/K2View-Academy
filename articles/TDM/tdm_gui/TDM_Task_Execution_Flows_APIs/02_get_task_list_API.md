# Get Tasks List APIs

## Get Regular Tasks

### API URL

/regularTasksByUser 

### HTTP Method

GET

### API Category

custom_TDM_Tasks

### API Description

Gets the list of regular active tasks (version_ind is 'false', task_status and task_execution_status columns are 'Active') for a user based on the user's permission group (admin, owner, or tester) and based on the user's TDM environment permissions:

- **Admin Users:**
  
  - Get all active regular tasks.
- **Tester Users:**
  - **Extract Tasks**:
    - Get all active tasks that **do not require special permissions** (that is, tasks that do not include reference tables, do not require up-to-date data, or that do not run on all entities) if the user or their group has a Read TDM Environment permission set on at least one TDM environment with the task's Business Entity (BE) and LUs.
    - Get all active tasks that include reference tables or that require up-to-date data if the user or their group has a Read TDM Environment permission set with these permissions on at least one TDM environment with the task's Business Entity (BE) and LUs.
    
  - **Load Tasks:**
    - Get all active tasks that **do not require special permissions** (that is, tasks that do not include reference tables, do not include Synthetic or Random selection methods, tasks that do not have Sequence replacement, or tasks that do not include a delete of entities from the target system) if the user or their group has a Read TDM Environment permission set  on at least one TDM environment with the task's Business Entity (BE) and LUs, and a Write TDM Environment permission set on at least one TDM environment with the task's Business Entity (BE) and LUs.
    - Get all active tasks that **require special permissions** if the user or their group has at least one Read TDM Environment permission set, and one Write TDM Environment permission set with these permissions and the source and target environments have the task's Business Entity (BE) and LUs.
    
  - **Delete Tasks**:
  
    - Get all active delete tasks if the user has at least one Write TDM Environment permission set with a permission to [delete entities](/articles/TDM/tdm_gui/10_environment_roles_tab.md#delete-entity-from-target) and the environment has the task's Business Entity (BE) and LUs.
  
  - **Reserve Tasks**:
  
    - Get all active reserve tasks if the the user has at least one Write TDM Environment permission set and the [Max Number of Reserved Entities on Env](/articles/TDM/tdm_gui/10_environment_roles_tab.md#max-number-of-reserved-entities-on-env) setting is bigger than zero and the environment has the task's Business Entity (BE) and LUs.
  
      
- **Owner Users:**
  
  - **Extract Tasks**:
    - Get all active tasks if the user or their group is the owner of at least one source environment with the task's Business Entity (BE) and LUs.
    - Get active tasks based on the tester's selection logic, since an owner can also be attached to the TDM environment as a tester.
    
  - **Load Tasks**:
    - Get all active tasks if the user or their group is the owner of at least one source environment and one target environment with the task's Business Entity (BE) and LUs.
    - Get active tasks based on the tester's selection logic, since an owner can also be attached to the TDM environment as a tester.
    
  - **Delete Tasks**:
  
    - Get all active tasks if the user or their group is the owner of at least one target environment with the task's Business Entity (BE) and LUs.
    - Get all active delete tasks if the user has at least one Write TDM Environment permission set with a permission to [delete entities](/articles/TDM/tdm_gui/10_environment_roles_tab.md#delete-entity-from-target) and the environment has the task's Business Entity (BE) and LUs.

  - **Reserve Tasks**:
  
    - Get all active tasks if the user or their group is the owner of at least one target environment with the task's Business Entity (BE) and LUs.
  
    - Get all active reserve tasks if the the user has at least one Write TDM Environment permission set and the [Max Number of Reserved Entities on Env](/articles/TDM/tdm_gui/10_environment_roles_tab.md#max-number-of-reserved-entities-on-env) setting is bigger than zero.
  
      
  
  Click for more information about the [TDM Environment role's permissions](/articles/TDM/tdm_gui/10_environment_roles_tab.md#role-permissions).
  
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



## Get Data Versioning (Data Flux) Tasks

### API URL

/VersionTasksByUser

### HTTP Method

GET

### API Category

custom_TDM_Tasks

### API Description

Gets data versioning (Data Flux) tasks with version_ind set to true. Only active tasks (task_status and task_execution_status columns are 'Active') are taken. The task list is returned for the user based on the user's permission group (admin, owner, or tester) and based on the user's TDM environment permissions: 

**Admin Users:**

- Get all active tasks.

**Owner Users:**

- Get all active extract tasks if the user is the owner of at least one source environment.
- Get all active load tasks if the user is the owner of at least one source environment and one target environment.
- Get all active extract tasks if the user has at least one Read TDM Environment permission set that enables a data versioning.
- Get all active load tasks if the user has at least one Read TDM Environment permission set and one Write TDM Environment permission set. Both must enable a data versioning.

**Tester Users:**

- Get all active extract tasks if the user has at least one Read TDM Environment permission set that enables a data versioning.
- Get all active load tasks if the user has at least one Read TDM Environment permission set and one Write TDM Environment permission set. Both must enable a data versioning.

- ### API Input

  None. The user and their groups are taken from the Fabric session.

  ### API Output Example

  ```json
  {
    "result": [
      {
        "task_title": "loadSnapShot",
        "task_id": "19"
      },
      {
        "task_title": "loadVersion",
        "task_id": "31"
      }
    ],
    "errorCode": "SUCCESS",
    "message": null
  }
  ```



## Get Tasks by Filtering Parameters

### API URL

/getTasksByParams

### HTTP Method

GET

### API Category

custom_TDM_Tasks

### API Description

The API invokes either  [regularTasksByUser](#get-regular-tasks) API to bring regular tasks or [VersionTasksByUser](#get-data-versioning-data-flux-tasks) API to bring data versioning (Data Flux) tasks. The selection of the API that is used to get the tasks for the user is based on the value of the **version_ind** input value: 

- If the version_ind is **true** (get data versioning tasks): call the **/VersionTasksByUser** API.
- If the version_ind is **false** or **empty**: call the  **/regularTasksByUser** API.

The returned tasks are filtered based on the additional input filtering parameters (if set). The input is a **dynamic JSON string**. 

The JSON filtering parameter is optional. If is it not populated, the API returns all of the user's regular tasks.

### API Input

- **filteringParams** - this is an optional String parameter that can be populated by a JSON with a list of filtering parameters. You can populate part or all of the parameters.
It supports the following filtering parameters:

<table width="900pxl">
<tbody>
<tr>
<td width = "150pxl">
<p><strong>Param Name</strong></p>
</td>
<td  width="400pxl">
<p><strong>Valid Values</strong></p>
</td>
<td width="350pxl">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td>
<p><strong>task_type</strong></p>
</td>
<td width="200">
<ul>
<li>LOAD</li>
<li>EXTRACT</li>
<li>RESERVE</li>
</ul>
</td>
<td width="251">
<p>Populate the task type to filter the returned tasks based on their type.</p>
<p>Notes:</p>
<ul>
<li>To get a reserve-only task, populate the task type with RESERVE.</li>
<li>To get a delete-only task, populate the task type with LOAD, load_entity&nbsp;with <strong>false</strong>, and delete_before_load&nbsp;with <strong>true.</strong></li>
</ul>
</td>
</tr>
<tr>
<td>
  <p><strong>version_ind</strong></p>
</td>
<td width="200">
<ul>
<li>true</li>
<li>false</li>
</ul>
</td>
<td width="251">
<ul>
  <li>Populate with <strong>false</strong> to get regular tasks.</li>
  <li>Populate with <strong>true</strong> to get data versioning tasks.</li>
</ul>
</td>
</tr>
<tr>
<td>
<p><strong>load_entity</strong></p>
</td>
<td width="200">
<ul>
<li>true</li>
<li>false</li>
</ul>
</td>
<td width="251">
<ul>
  <li>Populate with <strong>true</strong> to get tasks that provisions data to the target environment.</li>
<li>For other tasks (extract, reserve only, and delete only), populate this field with <strong>false</strong>.</li>
</ul>
</td>
</tr>
<tr>
<td>
<p><strong>delete_before_load</strong>&nbsp;</p>
</td>
<td width="200">
<ul>
<li>true</li>
<li>false</li>
</ul>
</td>
<td width="251">
<ul>
  <li>Populate with <strong>true</strong> to get delete and load or delete only tasks.</li>
  <li>Else, populate this parameter with <strong>false</strong>.</li>
</ul>
</td>
</tr>
<tr>
<td>
<p><strong>selection_method</strong></p>
</td>
<td width="200">
<ul>
<li>'L' (Entity list)</li>
<li>'P' or 'PR' (Parameters)</li>
<li>'S' (Entity Clone)</li>
<li>'R' (Random)</li>
<li>'C' (Custom Logic)</li>
<li>'ALL' (Extract tasks: select a predefined entity list. Load Data Versioning tasks: select all entities of the selected version)</li>
<li>'REF' (Reference Only)</li>
</ul>
</td>
<td width="251">
<p>The entity&rsquo;s selection method.</p>
</td>
</tr>
<tr>
<td>
<p><strong>sync_mode</strong>&nbsp;</p>
</td>
<td width="200">
<ul>
<li>OFF</li>
<li>FORCE</li>
</ul>
</td>
<td width="251">
<p>Populate this parameter to get tasks that override the default sync mode (sync ON which syncs new data based on the LU&rsquo;s implementation sync policy).</p>
</td>
</tr>
</tbody>
</table>

### API Input Examples:
   ```json
   {"task_type":"EXTRACT", "version_ind":false, "selection_method":"L", "sync_mode":"FORCE"}
   ```



  ```json
  {"task_type":"LOAD", "version_ind":false, "load_entity":false, "delete_before_load":true, "selection_method":"L"}
  ```


Get all data versioning (Data Flux) load tasks for the user:

```json
{"task_type":"LOAD", "version_ind":true}
```



### API Request URL Examples:

```
http://localhost:3213/api/getTasksByParams
```

```
http://localhost:3213/api/getTasksByParams?filteringParams=%7B%22task_type%22%3A%22LOAD%22%2C%20%22load_entity%22%3Afalse%2C%20%22delete_before_load%22%3Atrue%2C%20%22selection_method%22%3A%22L%22%7D

```

```
http://localhost:3213/api/getTasksByParams?filteringParams=%7B%22task_type%22%3A%22LOAD%22%2C%20%22version_ind%22%3Atrue%7D
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
