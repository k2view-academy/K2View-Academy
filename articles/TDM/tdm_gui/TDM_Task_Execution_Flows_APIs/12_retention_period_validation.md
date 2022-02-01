# Retention Period Validation

A TDM user can set a retention period either on the extracted data version or on the entity reservation (set a reservation period).

In both cases the retention period is a combination of **unit** (Hours, Days, Weeks ...) and **value**. For example, populate the unit with 'Days' and the value with 2 to set a retention period of 2 days.

An admin user of the environment's owner can set unlimited retention period by populating the value with zero.

However, a tester user is limited and can set a retention period that does not exceed the [maximum retention period](10_retention_period_options.md) defined on Data Versioning extract task and entity's reservation period.

Therefore the retention period, either set on the task itself of the overridden execution parameters, **needs to be validated for a tester user** to verify that it does not exceed the maximum period.

## Which Task Requires a Retention Period Validation?

### Data Versioning Extract Task

Validate the retention period for the user whether it is set in the overridden parameters or taken from the [task itself](03_get_task_details_APIs.md).

### Load or Reserve Tasks

Validate the retention period (reservation period) if the task reserves entities on the target environment: the task's **reserve_ind** attribute is **true** or the user set the **reservationInd**  to **true** in the overridden execution parameter.

Validate the retention period for the user whether it is set in the overridden parameters or taken from the [task itself](03_get_task_details_APIs.md).



## How Do I Identify a User as a Tester?

### Data Versioning Extract Task

#### 1. The Task's Source Environment is Overridden by the User

The [getEnvironmentsForTaskAttr](08_get_evailable_envs_for_task_execution.md) API returns  the **role_id** attribute on each environment. If the role_id of the selected environment is populated by a numeric sequence value, the user is added to the environment as a tester.

#### 2. The  Task's Source Environment is not Overridden by the User

Get the task's environment id from the [tasks](03_get_task_details_APIs.md) API (environment_id attribute). Then get the role_id of this environment from the [getEnvironmentsForTaskAttr](08_get_evailable_envs_for_task_execution.md) output. If the role_id of the task's environment is populated by a numeric sequence value, the user is added to the environment as a tester.

### Reserve Entities on the Target Environment

#### 1. The Task's Target Environment is Overridden by the User

The [getEnvironmentsForTaskAttr](08_get_evailable_envs_for_task_execution.md) API returns  the **role_id** attribute on each environment. If the role_id of the selected **target environment** is populated by a numeric sequence value, the user is added to the environment as a tester.

#### 2. The  Task's Target Environment is not Overridden by the User

Get the task's environment id from the [tasks](03_get_task_details_APIs.md) API (environment_id attribute). Then get the role_id of this environment from the [getEnvironmentsForTaskAttr](08_get_evailable_envs_for_task_execution.md) output. If the role_id of the task's environment is populated by a numeric sequence value, the user is added to the environment as a tester.



## How to Validate the Tasks' Retention Period?

Get the maximum number of days from the [retentionperiodinfo](10_retention_period_options.md) API and compare it with the task's retention period:

- **maxRetentionPeriodForReserve** attribute returns the maximum number of days to be set in the retention period for **Entity Reservation**. 

  Example: 

  ```json
  "maxRetentionPeriodForReserve": {
        "units": "Days",
        "value": "10"
      }
  ```

- **maxRetentionPeriodForExtract** attribute returns the maximum number of days to be set in the retention period for **Data Versioning extract tasks**. 

  Example: 

  ```json
    "maxRetentionPeriodForExtract": {
        "units": "Days",
        "value": 90
      }
  ```



Note that if the task's retention period is set with a different unit (the unit is not set to 'Days'), it is needed to covert the task's retention period to days to validate it.