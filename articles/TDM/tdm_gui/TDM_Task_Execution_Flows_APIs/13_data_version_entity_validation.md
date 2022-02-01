# Data Version - Entity Validation

### API URL

/tasks/versionsForLoad

### HTTP Method

POST

### API Category

TDM_Tasks

### API Description

Checks if the list of requested entities are copied successfully in the selected data version across all the input LU names in the task's Business Entity hierarchy. For example, if the customer's order is not extracted succesfully to Fabric, the validation on the related customer returns it as a failed entity.

Note that  this API is needed since  the [versionsForLoad](11_get_available_data_versions.md) API only checks the status of root LUI.

### API Input

The request body contains the following filtering parameters:

- **entitiesList**: populated by list of entities separated by a comma. This is an optional input. When populated, the API only brings data versions that contain the entities in the list.
- **taskExecId**: populated by the unique identifier (task_execution_id) of the extract task's execution that created the selected data version. This value is returned in the **task_execution_id** output attribute of the [versionsForLoad](11_get_available_data_versions.md) API.
- **lu_list**: list of the load task's LU names. For example, when populated with **Customer** and **Billing** , the API only validates the LUIs' execution status of  these LUs.

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

 