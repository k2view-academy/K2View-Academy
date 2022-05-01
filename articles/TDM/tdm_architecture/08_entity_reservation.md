# Entity Reservation

The entity reservation feature has been added in TDM 7.4. This feature is made to enable the user better control on the tested entities in their environments and **enables a user to reserve (lock) entities on the testing environment and avoid other users from re-provisioning these entities into the testing environment** till the user completes the functional tests and can release these entities.

However, the **user can still load a replica of the reserved entity** using the [replace sequence](/articles/TDM/tdm_gui/17_load_task_regular_mode.md#replace-sequence) option in the TDM task.

The reserved entities are **marked as reserved in the TDM DB**. Note that the reserved entities are **not physically locked** in the target environment. Other users can still update the reserved entities in the target environment.

## **How Can I Reserve Entities?** 

A [TDM Task](/articles/TDM/tdm_gui/14_task_overview.md) can reserve entities in one of two ways:

1. [Load tasks](/articles/TDM/tdm_gui/18_load_task_data_versioning_mode.md), reserve the copied entities on target.
2. [Reserve tasks](/articles/TDM/tdm_gui/20_reserve_only_task.md), reserve selected entities on the target environment. 

In both cases, the **task execution** marks the **root target entity IDs** as reserved. On load and reserve tasks, the task execution reserves each root entity ID before loading it to the target environment. If the reserve fails due to any reason, the entity is marked as failed and it is not loaded to the target environment.

If the load of the root entity fails, the [TDM orchestration process](/articles/TDM/tdm_implementation/11_tdm_implementation_using_generic_flows.md#step-4---create-the-tdmorchestratorflow-from-the-template) deletes the failed entity from the TDM DB **tdm_reserved_entities**  table.

Note that entities can only be reserved by load or reserve tasks. A “reservation” of entities by extract task is implemented by the Data Versioning functionality.

## **Who Can Reserve Entities?** 

- Admin
- Environment Owner
- Tester: 
  - The maximum number of reserved entities by a tester is set per a TDM environment on the TDM environment [permission set]. 
  - A tester must set a retention period on the reserved entity. The maximum number of days of a reservation period is set in the **tdm_general_parameters** TDB DB table in the **MAX_RESERVATION_DAYS_FOR_TESTER** parameter.

## Task Execution - Entity Reservation Validations

- **Validate the number of reserved entities:**  Accumulate the number of reserved entities that are allowed for the user in the target environment. If the total number of reserved entities exceeds the user's permissions on the environment, return an error. For example, if the user is allowed to reserved up to 70 entities in ST1 and there are 50 entities that are already reserved for the user in ST1, the user can reserve up to additional 20 entities in ST1.

- **Validate the retention period** to verify that the number of days does not exceed the maximum number of days allowed for the tester.

- **Validate the task's entities**:

  - Filter out entities that are reserved by another user when running a task with **Custom Logic**, **Random**, or **Parameters** section methods.
  - If the task is executed on an entity list, verify that the **entities are not reserved for other users**. If an entity is reserved by another user, the entity fails.

  Click here for more information about the [available task's selection methods](03a_task_execution_building_entity_list_on_tasks_LUs.md#root-lus).



## Reserved Entity's Attributes

The reserved entities are kept in a dedicated TDM DB table: **tdm_reserved_entities**. Each entity has its own record.

The key of each reserve entity consists of the combination of the following fields:

- **entity_id**, populated with the **target entity ID**.
- **be_id**, populated with the unique identifier of the task's Business Entity (BE).
- **env_id**, populated with the unique identifier of the task's target environment.

The table contains the following information:

- **Key fields**: entity_id, be_id, and env_id.

- **Task identifiers**: task_id and task_execution_id. Identify the task execution that reserved the entities.

- **Reservation period**: start_datetime and end_datetime. Note that admin and environment owner users can reserve entities for an unlimited period. In this case, the end_datetime will be empty.

- **reservation_owner**: the user ID which reserved the entities. 

- **reserve_notes**: a free text concerning the reserved entity.

  

## **Reserved Entities Management**

The [Reserve Entities window](/articles/TDM/tdm_gui/13_reserved_entities_window.md) enables the TDM users to run the following activities on the reserved entities:

- **View**: each user can view the list of entities, reserved on their environment, to avoid running functional tests on entities reserved by other users.

- **Release** reserved entities:
  - Release your entities
  - Environment owner can release all entities in their environment
  - Admin user can release all entities

- **Update** reserved entities:
  - Update the reservation end date
  - Add a note for the reserved entities

## Releasing Reserved Entities

**Explicit release**: release entities via the **new Reserved Entities window**. The entities are deleted from the reserve entities TDM table.

**Implicit release**: a reserved entity can be released by either of the following scenarios:
- The reservation period ends. The entity is automatically released and is no longer reserved. Note that in this case- the record is not deleted automatically from the TDM DB. However, when the entity is reserved again, the record is updated with the new owner and the new reservation period dates.

- A [delete only task](/articles/TDM/tdm_gui/19_delete_only_task.md) releases the deleted entities and deletes them from the reserve entities TDM table.

  

[![Previous](/articles/images/Previous.png)](07_tdm_parameters_handling.md)
