# Entity List Validation on Entity Reservation

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

