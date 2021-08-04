# Get the Available Environments for Task Execution

### API URL

/getEnvironmentsForTaskAttr

### HTTP Method

GET

### API Category

TDM_Tasks

### API Description

Get the list of TDM environments that are available for the user based on the input filtering parameters:

-  If the task type is [Extract](/articles/TDM/tdm_gui/16_extract_task.md) , then validate and return the list of available source environments.
-  If the task type is [Load](/articles/TDM/tdm_gui/17_load_task_regular_mode.md), then validate and return both - source and target - available environments.

### API Input



<table width="900pxl">
<tbody>
<tr>
<td valign="top" width="150pxl">
<p><strong>Param Name</strong></p>
</td>
<td valign="top" width="150pxl">
<p><strong>Type</strong></p>
</td>
<td valign="top" width="150pxl">
<p><strong>Mandatory</strong></p>
</td>
<td valign="top" width="150pxl">
<p><strong>Default Value</strong></p>
</td>
<td width="300pxl" valign="top">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
  <p>be</p>
</td>
<td valign="top" width="150pxl">
<p>String</p>
</td>
<td valign="top" width="150pxl">
<p>Yes</p>
</td>
<td valign="top" width="150pxl">
<p>N/A</p>
</td>
<td width="300pxl" valign="top">
<p>Populated by the task's be_id (Business Entity ID). For example: 1.</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>lus</p>
</td>
<td valign="top" width="150pxl">
<p>String</p>
</td>
<td valign="top" width="150pxl">
<p>Yes</p>
</td>
<td valign="top" width="150pxl">
<p>N/A</p>
</td>
<td width="300pxl" valign="top">
<p>Populated by the task's Logical Units (LU IDs), separated by a comma. For example: 1, 2, 3. The task's LUs are returned by <a href="03_get_task_details_APIs.md#get-the-tasks-logical-units-list">/task/{taskId}/logicalunits API</a>.</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>refcount</p>
</td>
<td valign="top" width="150pxl">
<p>Integer</p>
</td>
<td valign="top" width="150pxl">
<p>No</p>
</td>
<td valign="top" width="150pxl">
<p>0</p>
</td>
<td width="300pxl" valign="top">
<p>Populated by the number of reference tables included in the TDM task. The refcount parameter is returned by <a href="03_get_task_details_APIs.md">/tasks API</a></p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>selection_method</p>
</td>
<td valign="top" width="150pxl">
<p>String</p>
</td>
<td valign="top" width="150pxl">
<p>No</p>
</td>
<td valign="top" width="150pxl">
<p>L</p>
</td>
<td width="300pxl" valign="top">
<p>Can be populated by the following values: "L" (entity list), "R" (random selection), "S" (entity cloning), "PR" (parameters with a random selection), "P" (parameters), "ALL" (all entities), or "REF" (reference only task).</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>sync_mode</p>
</td>
<td valign="top" width="150pxl">
<p>String</p>
</td>
<td valign="top" width="150pxl">
<p>No</p>
</td>
<td valign="top" width="150pxl">
<p>Empty</p>
</td>
<td width="300pxl" valign="top">
<p>Can be populated by &ldquo;OFF&rdquo;, &ldquo;FORCE&rdquo;, or can be empty</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>version_ind</p>
</td>
<td valign="top" width="150pxl">
<p>Boolean</p>
</td>
<td valign="top" width="150pxl">
<p>No</p>
</td>
<td valign="top" width="150pxl">
<p>False</p>
</td>
<td width="300pxl" valign="top">
<p>True/False</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>replace_sequences</p>
</td>
<td valign="top" width="150pxl">
<p>Boolean</p>
</td>
<td valign="top" width="150pxl">
<p>No</p>
</td>
<td valign="top" width="150pxl">
<p>False</p>
</td>
<td width="300pxl" valign="top">
<p>True/False</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>delete_before_load</p>
</td>
<td valign="top" width="150pxl">
<p>Boolean</p>
</td>
<td valign="top" width="150pxl">
<p>No</p>
</td>
<td valign="top" width="150pxl">
<p>False</p>
</td>
<td width="300pxl" valign="top">
<p>True/False</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>task_type</p>
</td>
<td valign="top" width="150pxl">
<p>String</p>
</td>
<td valign="top" width="150pxl">
<p>Yes</p>
</td>
<td valign="top" width="150pxl">
<p>N/A</p>
</td>
<td width="300pxl" valign="top">
<p>Populated by "extract" or "load"</p>
</td>
</tr>
</tbody>
</table>

#### API Input Examples

```
http://localhost:3213/api/getEnvironmentsForTaskAttr?be=1&lus=1,2,3&refcount=1&selection_method=L&sync_mode=FORCE&version_ind=false&replace_sequences=true&delete_before_load=true&task_type=load
```

```
http://localhost:3213/api/getEnvironmentsForTaskAttr?be=1&lus=1,2,3&refcount=1&delete_before_load=true&task_type=load
```

### API Output Example

#### Get the Environments for an Admin User

```json
{
  "result": [
    {
      "source environments": [
        {
          "environment_id": "4",
          "role_id": "admin",
          "environment_name": "ENV6"
        },
        {
          "environment_id": "1",
          "role_id": "admin",
          "environment_name": "ENV1"
        },
        {
          "environment_id": "6",
          "role_id": "admin",
          "environment_name": "ENV3"
        },
        {
          "environment_id": "3",
          "role_id": "admin",
          "environment_name": "ENV4"
        },
        {
          "environment_id": "9",
          "role_id": "admin",
          "environment_name": "Env9"
        }
      ]
    },
    {
      "target environments": [
        {
          "environment_id": "2",
          "role_id": "admin",
          "environment_name": "ENV2"
        },
        {
          "environment_id": "5",
          "role_id": "admin",
          "environment_name": "ENV5"
        },
        {
          "environment_id": "6",
          "role_id": "admin",
          "environment_name": "ENV3"
        },
        {
          "environment_id": "9",
          "role_id": "admin",
          "environment_name": "Env9"
        }
      ]
    }
  ],
  "errorCode": "SUCCESS",
  "message": null
}
```

#### Get the Environments for an Owner User

Note: an owner user can be the owner of some TDM environments and can still be attached as a tester to other TDM environments. If the user is the owner of the TDM environment, the **role_id** is populated by **owner**. If the user is attached as a tester to the TDM environment, the **role_id** is populated by the **ID** of the [TDM environment role](/articles/TDM/tdm_gui/10_environment_roles_tab.md).

```
{
  "result": [
    {
      "source environments": [
        {
          "environment_id": "4",
          "role_id": "owner",
          "environment_name": "ENV6"
        },
        {
          "environment_id": "1",
          "role_id": "2",
          "environment_name": "ENV1"
        }
      ]
    },
    {
      "target environments": [
        {
          "environment_id": "4",
          "role_id": "owner",
          "environment_name": "ENV6"
        },
        {
          "environment_id": "5",
          "role_id": "3",
          "environment_name": "ENV5"
        }
      ]
    }
  ],
  "errorCode": "SUCCESS",
  "message": null
}
```



#### Get the Environments for a Tester User

```json
{
  "result": [
    {
      "source environments": [
        {
          "environment_id": "4",
          "role_id": "1",
          "environment_name": "ENV6"
        },
        {
          "environment_id": "1",
          "role_id": "2",
          "environment_name": "ENV1"
        }
      ]
    },
    {
      "target environments": [
        {
          "environment_id": "4",
          "role_id": "1",
          "environment_name": "ENV6"
        },
        {
          "environment_id": "5",
          "role_id": "3",
          "environment_name": "ENV5"
        }
      ]
    }
  ],
  "errorCode": "SUCCESS",
  "message": null
}


```

 [![Previous](/articles/images/Previous.png)](01a_tdm_task_execution_overriding_params_flow.md)
