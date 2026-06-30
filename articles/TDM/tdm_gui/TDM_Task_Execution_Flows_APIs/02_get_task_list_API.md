# Get Tasks List APIs

TDM 10 introduces two new recommended APIs for retrieving available tasks:

- **getTasksPerTaskGroup** – returns all available tasks for a user within a specified task group.
- **search** – retrieves all tasks available to a user based on filtering parameters in the request body.

The legacy TDM 9.5 APIs (`regularTasksByUser` and `versionTasksByUser`) continue to be supported for backward compatibility. However, we recommend migrating to the TDM 10 APIs, as the legacy APIs do not include the enhanced task access logic introduced in TDM 10, such as validation based on the task's access control settings (user/Fabric role assignments) and editable task attributes.

---

## TDM 10 - New APIs 

### Get Task Groups

### API URL

/taskgroup

### HTTP Method

GET

### API Category

TDM_TaskGroups

### API Description

Returns all available task groups for the user.

### API Input

N/A

### API Output Example

```json
{
  "result": {
    "allTaskGroups": [
      {
        "task_group_id": 30,
        "task_group_name": "Customer provisioning tasks",
        "task_group_desc": null,
        "created_by": "tali.einhorn@k2view.com.k2v",
        "isPermittedUser": true,
        "favorite": true,
        "has_task_created_by_the_user": true
      },
      {
        "task_group_id": 61,
        "task_group_name": "Demo",
        "task_group_desc": "TDM 10 demo",
        "created_by": "tali.einhorn@k2view.com.k2v",
        "isPermittedUser": true,
        "favorite": true,
        "has_task_created_by_the_user": true
      },
      {
        "task_group_id": 1,
        "task_group_name": "General",
        "task_group_desc": "General",
        "created_by": "system",
        "isPermittedUser": false,
        "favorite": false,
        "has_task_created_by_the_user": false
      },
      {
        "task_group_id": 2,
        "task_group_name": "Product predefined tasks",
        "task_group_desc": "Product predefined tasks",
        "created_by": "system",
        "isPermittedUser": false,
        "favorite": false,
        "has_task_created_by_the_user": false
      },
      {
        "task_group_id": 40,
        "task_group_name": "Subscriber related tasks",
        "task_group_desc": null,
        "created_by": "tali.einhorn@k2view.com.k2v",
        "isPermittedUser": true,
        "favorite": false,
        "has_task_created_by_the_user": true
      },
      {
        "task_group_id": 50,
        "task_group_name": "Synthetic customers",
        "task_group_desc": null,
        "created_by": "tali.einhorn@k2view.com.k2v",
        "isPermittedUser": true,
        "favorite": false,
        "has_task_created_by_the_user": true
      },
      {
        "task_group_id": 60,
        "task_group_name": "Table related tasks",
        "task_group_desc": null,
        "created_by": "tali.einhorn@k2view.com.k2v",
        "isPermittedUser": true,
        "favorite": false,
        "has_task_created_by_the_user": true
      }
    ],
    "myTaskGroups": [
      {
        "task_group_id": 30,
        "task_group_name": "Customer provisioning tasks",
        "task_group_desc": null,
        "created_by": "tali.einhorn@k2view.com.k2v",
        "isPermittedUser": true,
        "favorite": true,
        "has_task_created_by_the_user": true
      },
      {
        "task_group_id": 61,
        "task_group_name": "Demo",
        "task_group_desc": "TDM 10 demo",
        "created_by": "tali.einhorn@k2view.com.k2v",
        "isPermittedUser": true,
        "favorite": true,
        "has_task_created_by_the_user": true
      },
      {
        "task_group_id": 40,
        "task_group_name": "Subscriber related tasks",
        "task_group_desc": null,
        "created_by": "tali.einhorn@k2view.com.k2v",
        "isPermittedUser": true,
        "favorite": false,
        "has_task_created_by_the_user": true
      },
      {
        "task_group_id": 50,
        "task_group_name": "Synthetic customers",
        "task_group_desc": null,
        "created_by": "tali.einhorn@k2view.com.k2v",
        "isPermittedUser": true,
        "favorite": false,
        "has_task_created_by_the_user": true
      },
      {
        "task_group_id": 60,
        "task_group_name": "Table related tasks",
        "task_group_desc": null,
        "created_by": "tali.einhorn@k2view.com.k2v",
        "isPermittedUser": true,
        "favorite": false,
        "has_task_created_by_the_user": true
      }
    ],
    "favoritesTaskGroups": [
      {
        "task_group_id": 30,
        "task_group_name": "Customer provisioning tasks",
        "task_group_desc": null,
        "created_by": "tali.einhorn@k2view.com.k2v",
        "isPermittedUser": true,
        "favorite": true,
        "has_task_created_by_the_user": true
      },
      {
        "task_group_id": 61,
        "task_group_name": "Demo",
        "task_group_desc": "TDM 10 demo",
        "created_by": "tali.einhorn@k2view.com.k2v",
        "isPermittedUser": true,
        "favorite": true,
        "has_task_created_by_the_user": true
      }
    ]
  },
  "errorCode": "SUCCESS",
  "message": null
}
```

### Get Tasks per Task Group

### API URL

/getTasksPerTaskGroup

### HTTP Method

GET

### API Category

TDM_Tasks

### API Description

Returns the list of available tasks for a user within a specified task group, based on the user's permission group (admin, owner, or tester) and their TDM environment permissions. Results include task metadata such as the display type, editable status, and whether the task is marked as a favourite.

### API Input

<table width="900pxl">
<tbody>
<tr>
<td style="width: 200px;"><strong>Param Name</strong></td>
<td style="width: 150px;"><strong>Mandatory</strong></td>
<td style="width: 200px;"><strong>Type</strong></td>
<td style="width: 350px;"><strong>Description</strong></td>
</tr>
<tr>
<td><strong>task_group_id</strong></td>
<td>Yes</td>
<td>Number (query parameter)</td>
<td>The unique identifier of the task group.</td>
</tr>
</tbody>
</table>

### API Output Example

```json
{
  "result": [
    {
      "task_id": 1,
      "task_title": "Extract entities",
      "display_task_type": "Extract",
      "task_last_updated_date": "2026-04-21 07:08:17.780096",
      "can_edit_task": true,
      "can_create_task": true,
      "hold_task": false,
      "favorite": false
    }
  ],
  "errorCode": "SUCCESS"
}
```

---

## Search Tasks

### API URL

/search

### HTTP Method

POST

### API Category

TDM_TasksManagment

### API Description

Retrieves all tasks available to a user based on the supplied request body. Only tasks the calling user is permitted to see (based on their permission group: admin, owner, or tester) are returned. Results are grouped by task group name; within each group, tasks are sorted by favourite first, then by last updated date descending.

All request body parameters are optional. If the body is empty or omitted, the API returns all tasks available to the user across all task groups.

### API Input

The request body is a JSON object with the following optional filtering parameters:

<table width="900pxl">
<tbody>
<tr>
<td style="width: 200px;"><strong>Param Name</strong></td>
<td style="width: 200px;"><strong>Valid Values</strong></td>
<td style="width: 500px;"><strong>Description</strong></td>
</tr>
<tr>
<td><strong>text</strong></td>
<td>Free text string</td>
<td>Free-text search. Matches against task title, task description, task group name, and task group description (case-insensitive).</td>
</tr>
<tr>
<td><strong>taskTypes</strong></td>
<td>
<ul>
<li>IN_PLACE_MASKING</li>
<li>EXTRACT</li>
<li>EXTRACT_AND_LOAD</li>
<li>LOAD_AND_RESERVE</li>
<li>DELETE_AND_LOAD_AND_RESERVE</li>
<li>DELETE_AND_LOAD</li>
<li>LOAD</li>
<li>DELETE</li>
<li>RESERVE</li>
<li>AI_TRAINING</li>
<li>RULE_BASED_SDG</li>
<li>AI_BASED_SDG</li>
<li>RULE_BASED_GENERATE_AND_LOAD</li>
<li>AI_BASED_GENERATE_AND_LOAD</li>
<li>LOAD_RULE_BASED_GENERATED_ENTITIES</li>
<li>LOAD_AI_BASED_GENERATED_ENTITIES</li>
</ul>
</td>
<td>Filter by task type. Multiple values are treated as a logical OR (any match is included).</td>
</tr>
<tr>
<td><strong>sourceEnvironmentIds</strong></td>
<td>Array of numbers</td>
<td>Filter by source environment ID. Multiple values are treated as a logical OR.</td>
</tr>
<tr>
<td><strong>targetEnvironmentIds</strong></td>
<td>Array of numbers</td>
<td>Filter by target environment ID. Multiple values are treated as a logical OR.</td>
</tr>
<tr>
<td><strong>beIds</strong></td>
<td>Array of numbers</td>
<td>Filter by Business Entity ID. Multiple values are treated as a logical OR.</td>
</tr>
<tr>
<td><strong>selectionMethods</strong></td>
<td>
<ul>
<li>PREDEFINED_ENTITY_LIST</li>
<li>CUSTOM_LOGIC</li>
<li>ENTITY_LIST</li>
<li>BUSINESS_PARAMETERS</li>
<li>LOAD_PRE_GENERATED_SUBSET</li>
<li>RANDOM_LIST</li>
<li>SYNTHETIC_GENERATION</li>
</ul>
</td>
<td>Filter by entity selection method. Ignored when <strong>dataType</strong> is set to <strong>tables</strong>. Multiple values are treated as a logical OR.</td>
</tr>
<tr>
<td><strong>creator</strong></td>
<td>String</td>
<td>Filter by the username of the task creator (partial match, case-insensitive).</td>
</tr>
<tr>
<td><strong>isScheduled</strong></td>
<td>true / false / null</td>
<td>Set to <strong>true</strong> to return only scheduled tasks, <strong>false</strong> for immediate tasks only, or omit to return both.</td>
</tr>
<tr>
<td><strong>dataType</strong></td>
<td>
<ul>
<li>entities</li>
<li>tables</li>
<li>both</li>
</ul>
</td>
<td>Filter by the type of data the task processes: entity-based tasks, table-level tasks only, or entity tasks that also include reference tables.</td>
</tr>
</tbody>
</table>

### API Input Examples

```json
{
  "text": "GENERAL",
  "taskTypes": ["EXTRACT", "LOAD"],
  "sourceEnvironmentIds": [1],
  "targetEnvironmentIds": [2],
  "beIds": [1],
  "selectionMethods": ["ENTITY_LIST", "RANDOM_LIST"],
  "creator": "admin",
  "isScheduled": false,
  "dataType": "entities"
}
```



```json
{
    "taskTypes":["EXTRACT_AND_LOAD","LOAD"],
    "beIds":[1],
    "selectionMethods":["CUSTOM_LOGIC"],
    "dataType":"entities"
}
```



### API Output Example

Results are grouped by task group name:

```json
{
  "result": {
    "General": [
      {
        "task_id": 9,
        "task_title": "extract only",
        "display_task_type": "Extract",
        "task_last_updated_date": "2026-04-26 10:23:27.461",
        "can_edit_task": true,
        "can_create_task": true,
        "hold_task": false,
        "favorite": false
      }
    ],
    "Product predefined tasks": [
      {
        "task_id": 1,
        "task_title": "Extract entities",
        "display_task_type": "Extract",
        "task_last_updated_date": "2026-04-21 07:08:17.780096",
        "can_edit_task": true,
        "can_create_task": true,
        "hold_task": false,
        "favorite": false
      }
    ]
  },
  "errorCode": "SUCCESS"
}
```

---

## TDM 9.X APIs

The following APIs are supported for backward compatibility with TDM 9.X. Note that they do not include the enhanced task access logic introduced in TDM 10 (access control validation based on user/Fabric role assignments and editable task attributes). We recommend migrating to the TDM 10 APIs above.

### Get Regular Tasks

#### API URL

/regularTasksByUser

#### HTTP Method

GET

#### API Category

custom_TDM_Tasks

#### API Description

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

#### API Input

None. The user and their groups are taken from the Fabric session.

#### API Output Example

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

---

### Get Data Versioning (Data Flux) Tasks

#### API URL

/VersionTasksByUser

#### HTTP Method

GET

#### API Category

custom_TDM_Tasks

#### API Description

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

#### API Input

None. The user and their groups are taken from the Fabric session.

#### API Output Example

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

---

### Get Tasks by Filtering Parameters

#### API URL

/getTasksByParams

#### HTTP Method

GET

#### API Category

custom_TDM_Tasks

#### API Description

The API invokes either [regularTasksByUser](#get-regular-tasks) to bring regular tasks or [VersionTasksByUser](#get-data-versioning-data-flux-tasks) to bring data versioning (Data Flux) tasks. The selection is based on the value of the **version_ind** input:

- If the version_ind is **true** (get data versioning tasks): call the **/VersionTasksByUser** API.
- If the version_ind is **false** or **empty**: call the **/regularTasksByUser** API.

The returned tasks are filtered based on the additional input filtering parameters (if set). The input is a **dynamic JSON string**.

The JSON filtering parameter is optional. If it is not populated, the API returns all of the user's regular tasks.

#### API Input

- **filteringParams** - an optional String parameter that can be populated with a JSON containing the following filtering parameters:

<table width="900pxl">
<tbody>
<tr>
<td width="150pxl">
<p><strong>Param Name</strong></p>
</td>
<td width="400pxl">
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
<li>To get a delete-only task, populate the task type with LOAD, load_entity with <strong>false</strong>, and delete_before_load with <strong>true.</strong></li>
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
  <li>Populate with <strong>true</strong> to get tasks that provision data to the target environment.</li>
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
<p>The entity's selection method.</p>
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
<p>Populate this parameter to get tasks that override the default sync mode (sync ON which syncs new data based on the LU's implementation sync policy).</p>
</td>
</tr>
</tbody>
</table>

#### API Input Examples

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

#### API Request URL Examples

```
http://localhost:3213/api/getTasksByParams
```

```
http://localhost:3213/api/getTasksByParams?filteringParams=%7B%22task_type%22%3A%22LOAD%22%2C%20%22load_entity%22%3Afalse%2C%20%22delete_before_load%22%3Atrue%2C%20%22selection_method%22%3A%22L%22%7D
```

```
http://localhost:3213/api/getTasksByParams?filteringParams=%7B%22task_type%22%3A%22LOAD%22%2C%20%22version_ind%22%3Atrue%7D
```

#### API Output Example

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
