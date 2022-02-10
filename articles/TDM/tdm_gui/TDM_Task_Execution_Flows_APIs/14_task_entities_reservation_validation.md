# Entity List Validations on Entity Reservation

The following validation need to be implemented on the task's entity list when the task reserves the entities:

- Verify that the entities are not reserved by another user on the environment.
- Verify that the total number of reserved entities on the environment does not exceeds the user's permissions on the environment. For example, if the user is allowed to reserved up to 70 entities in ST1 and there are 50 entities that are already reserved for the user in ST1, the user can reserve up to additional 20 entities in ST1. Therefore, the number of entities in the task execution cannot exceed 20 entities.

## Verify that the Entities are Available For Reservation

### API URL

/validateReservedEntitiesList

### HTTP Method

POST

### API Category

TDM_ReserveEntities

### API Description

Validate each input entity ID if it is reserved for another user on the input environment. If the entity is reserved for another user on the environment, return a failure on the entity. 

### API Input

The request body contains the following attributes:

- **beID** - populated with the unique identifier (be_id field) of the task's Business Entity.
- **envID** - populated with the task's environment_id (target environment).
- **listOfEntities** - populated with the task's entity list.  Note that if the **entitieslist** attribute is set in the overridden execution parameters, populate the listOfEntities with the entities of the **entitieslist**. Else, get the **selection_param_value** task's attribute if the task's **selection_method** is 'L' (entity list).  

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

The API returns the list of entities that are reserved on the environment by another user and therefore cannot be reserved by the user.

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

A tester user can reserve a limited number of entities per Business Entity and environment. The maximum number of reserved entities is set in the Environment permission set  attached to the user. Admin and environment owners can reserve unlimited number of entities on the environment.  The API performs the following validation if the user is not an admin user and is not the owner of the environment:

- Get the Write permission set attached to the user. If the user does not have a Write permission set on the environment, an exception is thrown by the API.

- Sum the input number of entities with the number of entities that are already reserved by the user on the environment (if exist). Validate the the total number of reserved entities does not exceed the user's permissions. 

  **Example:** 

  - The user can reserve up to 70 customers on ST1.

  - The user already has 40 customers reserved on ST1. 
  - The user asks to reserve 35 entities in the task:
    -  40+35 = 75. 
  - The API returns an error since the user cannot exceed the number of 30 customers in the task.

### API Input

- **noOfEntities** - populated by the number of entities, processed by the task.
- **envName** - populated by the target environment's name.
- **beName** - populated by the task's Business Entity name.



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

