# Data Version - Entity Validation

### API URL

/tasks/validateVersionForLoad

### HTTP Method

POST

### API Category

TDM_Tasks

### API Description

Checks if the list of requested entities are copied successfully in the selected data version across all the input LU names in the task's Business Entity hierarchy. For example, if the customer's order is not extracted succesfully to Fabric, the validation on the related customer returns it as a failed entity.

Note that  this API is needed since  the [versionsForLoad](11_get_available_data_versions.md) API only checks the status of the root LUI.

### API Input

The request body contains the following filtering parameters:

<table width="900pxl">
<tbody>
<tr>
<td width="200pxl"><strong>Param Name</strong></td>
<td width="200pxl">
<p><strong>Mandatory</strong></p>
</td>
<td width="500pxl">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td style="width: 196.489px;" valign="top">
<p><strong>entitiesList</strong></p>
</td>
<td style="width: 167.409px;" valign="top">
<p>Yes</p>
</td>
<td style="width: 516.284px;" valign="top">
<p>Populated by a list of entities separated by a comma.&nbsp;</p>
</td>
</tr>
<tr>
<td style="width: 196.489px;" valign="top">
<p><strong>taskExecId</strong></p>
</td>
<td style="width: 167.409px;" valign="top">
<p>Yes</p>
</td>
<td style="width: 516.284px;">
<p>Populated by the unique identifier (task_execution_id) of the extract task's execution that created the selected data version. This value is returned in the task_execution_id output attribute of the <a href="11_get_available_data_versions.md">versionsForLoad</a> API.</p>
</td>
</tr>
<tr>
<td style="width: 196.489px;"><strong>lu_list</strong></td>
<td style="width: 167.409px;">
<p>Yes</p>
</td>
<td style="width: 516.284px;">
<p>List of the load task's LU names. For example, when populated with Customer and Billing, the API only validates the LUIs' execution status of these LUs.</p>
</td>
</tr>
</tbody>
</table>


### API Input Example

```json
{
  "entitiesList": "3762,3771,3790,3791",
  "taskExecId": "123",
  "lu_list": [
      {"lu_name":"Customer"},
      {"lu_name":"Billing"}
    ]
}
```



### API Output Examples

#### Example 1 - Validation Failure (on all or part of the entities)

```json
{
  "result": [
    {
      "root_entity_id": "3762",
      "lu_entity_id": "11991",
      "lu_name": "Billing"
    },
    {
      "root_entity_id": "3762",
      "lu_entity_id": "11993",
      "lu_name": "Billing"
    },
    {
      "root_entity_id": "3762",
      "lu_entity_id": "11997",
      "lu_name": "Billing"
    },
    {
      "root_entity_id": "3762",
      "lu_entity_id": "11999",
      "lu_name": "Billing"
    },
    {
      "root_entity_id": "3762",
      "lu_entity_id": "12001",
      "lu_name": "Billing"
    },
    {
      "root_entity_id": "3771",
      "lu_entity_id": "12011",
      "lu_name": "Billing"
    },
    {
      "root_entity_id": "3771",
      "lu_entity_id": "12012",
      "lu_name": "Billing"
    },
    {
      "root_entity_id": "3771",
      "lu_entity_id": "12013",
      "lu_name": "Billing"
    },
    {
      "root_entity_id": "3771",
      "lu_entity_id": "12014",
      "lu_name": "Billing"
    },
    {
      "root_entity_id": "3771",
      "lu_entity_id": "12015",
      "lu_name": "Billing"
    },
    {
      "root_entity_id": "3790",
      "lu_entity_id": "12078",
      "lu_name": "Billing"
    },
    {
      "root_entity_id": "3790",
      "lu_entity_id": "12079",
      "lu_name": "Billing"
    },
    {
      "root_entity_id": "3790",
      "lu_entity_id": "12080",
      "lu_name": "Billing"
    },
    {
      "root_entity_id": "3790",
      "lu_entity_id": "12081",
      "lu_name": "Billing"
    },
    {
      "root_entity_id": "3790",
      "lu_entity_id": "12082",
      "lu_name": "Billing"
    },
    {
      "root_entity_id": "3791",
      "lu_entity_id": "12083",
      "lu_name": "Billing"
    }
  ],
  "errorCode": "FAIL",
  "message": "The following Entities failed to be extracted by the requested version: 3762, 3771, 3790, 3791"
}
```



#### Example 2 - Successful Validation on All Entities

```json
{
  "result": [],
  "errorCode": "SUCCESS",
  "message": null
}
```

 
