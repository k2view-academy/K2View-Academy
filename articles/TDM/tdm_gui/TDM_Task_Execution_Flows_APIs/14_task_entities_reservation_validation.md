# Entity List Validations on Entity Reservation

The following validations must be implemented on the task's entity list when the task reserves the entities:

- Verify that the entities are not reserved by another user in the environment.
- Verify that the total number of reserved entities in the environment does not exceeds the user's permissions in the environment. For example, if the user is allowed to reserved up to 70 entities in ST1 and there are 50 entities that are already reserved for the user in ST1, the user can reserve up to an additional 20 entities in ST1. Therefore, the number of entities in the task execution cannot exceed 20 entities.

## Verify that the Entities are Available For Reservation

### API URL

/validateReservedEntitiesList

### HTTP Method

POST

### API Category

TDM_ReserveEntities

### API Description

Validate each input entity ID if it is reserved for another user in the input environment. If the entity is reserved for another user in the environment, return a failure on the entity. 

### API Input

The request body contains the following attributes:

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
<p><strong>beID</strong></p>
</td>
<td style="width: 167.409px;" valign="top">
<p>Yes</p>
</td>
<td style="width: 516.284px;" valign="top">
<p>Populated with the unique identifier (be_id field) of the task's Business Entity</p>
</td>
</tr>
<tr>
<td style="width: 196.489px;" valign="top">
<p><strong>envID</strong></p>
</td>
<td style="width: 167.409px;" valign="top">
<p>Yes</p>
</td>
<td style="width: 516.284px;">
<p>Populated with the task's environment_id (target environment).</p>
</td>
</tr>
<tr>
<td style="width: 196.489px;"><strong>listOfEntities</strong></td>
<td style="width: 167.409px;">
<p>Yes</p>
</td>
<td style="width: 516.284px;">
<p>Populated with the task's entity list. Note that if the <strong>entitieslist</strong>&nbsp;attribute is set in the overridden execution parameters, populate the listOfEntities with the entities of the&nbsp;<strong>entitieslist</strong>. Else, get the&nbsp;<strong>selection_param_value</strong>&nbsp;task's attribute if the task's&nbsp;<strong>selection_method</strong>&nbsp;is 'L' (entity list).</p>
</td>
</tr>
</tbody>
</table>


### API Input Example

```json
{
  "beID": "1",
  "envID": "2",
  "listOfEntities": [
    {
      "target_entity_id": "1"
    },
    {
      "target_entity_id": "3"
    },
    {
      "target_entity_id": "4"
    }
  ]
}
```



### API Output

The API returns the list of entities that are reserved in the environment by another user and therefore cannot be reserved by the user.

### API Output Example

```json
{
  "result": {
    "listOfEntities": [
      {
        "start_datetime": "2022-01-23 01:00:00.000",
        "end_datetime": "2022-02-10 00:00:00.000",
        "entity_id": "3",
        "reserve_owner": "admin"
      },
      {
        "start_datetime": "2022-02-06 01:00:00.000",
        "end_datetime": "2022-02-16 01:00:00.000",
        "entity_id": "4",
        "reserve_owner": "taha"
      }
    ]
  },
  "errorCode": "SUCCESS",
  "message": null
}
```



## Validate that the Total Number of Entities Reserved by the User

### API URL

/validatenoofreserved

### HTTP Method

GET

### API Category

TDM_ReserveEntities

### API Description

A tester user can reserve a limited number of entities per Business Entity and environment. The maximum number of reserved entities is set in the Environment permission set  attached to the user. Admin and environment owners can reserve an unlimited number of entities in the environment.  The API performs the following validation if the user is not an admin user and is not the owner of the environment:

- Get the Write permission set attached to the user. If the user does not have a Write permission set in the environment, an exception is thrown by the API.

- Sum the input number of entities with the number of entities that are already reserved by the user in the environment (if they exist). Validate that the the total number of reserved entities does not exceed the user's permissions. 

  **Example:** 

  - The user can reserve up to 70 customers on ST1.

  - The user already has 40 customers reserved on ST1. 
  - The user asks to reserve 35 entities in the task:
    -  40+35 = 75. 
  - The API returns an error since the user cannot exceed a total of 70 customers (40 + 30) in the task.

### API Input

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
<p><strong>noOfEntities</strong></p>
</td>
<td style="width: 167.409px;" valign="top">
<p>Yes</p>
</td>
<td style="width: 516.284px;" valign="top">
<p>Populated by the number of entities, processed by the task.</p>
</td>
</tr>
<tr>
<td style="width: 196.489px;" valign="top">
<p><strong>envName</strong></p>
</td>
<td style="width: 167.409px;" valign="top">
<p>Yes</p>
</td>
<td style="width: 516.284px;">
<p>Populated by the target environment's name.</p>
</td>
</tr>
<tr>
<td style="width: 196.489px;"><strong>beName</strong></td>
<td style="width: 167.409px;">
<p>Yes</p>
</td>
<td style="width: 516.284px;">
<p>Populated by the task's Business Entity name. For example, "Customer".</p>
</td>
</tr>
</tbody>




### API Input Example
  ```
  http://localhost:3213/api/validatenoofreserved?noOfEntities=10&envName=ENV1&beName=Customer
  ```

### API Output Examples

```json
{
  "result": {
   },
  "errorCode": "SUCCESS",
  "message": null
}
```



```json
{
  "result": {
   },
  "errorCode": "FAILED",
  "message": "The number of entities to be reserved exceeds the number of entities allowed for the user"
}
```

