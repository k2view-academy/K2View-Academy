# Retention Period Validation

A TDM user can set a retention period either on the extracted data version or on the entity reservation (set a reservation period).

In both cases the retention period is a combination of **unit** (Hours, Days, Weeks ...) and **value**. For example, populate the unit with 'Days' and the value with 2 to set a retention period of 2 days.

An admin user of the environment's owner can set unlimited retention period by populating the value with zero.

However, a tester user is limited and can set a retention period that does not exceed the [maximum retention period](10_retention_period_options.md) defined on extract task and entity's reservation period.

Therefore the retention period, either set on the task itself of the overridden execution parameters, **needs to be validated for a tester user** to verify that it does not exceed the maximum period.

## Which Task Requires a Retention Period Validation?

### Extract Task

Validate the retention period for the user whether it is set in the overridden parameters or taken from the [task itself](03_get_task_details_APIs.md).

### Load or Reserve Tasks

Validate the retention period (reservation period) if the task reserves entities on the target environment: the task's **reserve_ind** attribute is **true** or the user set the **reservationInd**  to **true** in the overridden execution parameter.

Validate the retention period for the user whether it is set in the overridden parameters or taken from the [task itself](03_get_task_details_APIs.md).



## How Do I Identify a User as a Tester?

### Extract Task

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



### Validate the Retention Period on Extract Data Versioning Task

Get the maximum number of days from the [retentionperiodinfo](10_retention_period_options.md) API and compare it with the task's retention period:

- **maxRetentionPeriodForExtract** attribute returns the maximum number of days to be set in the retention period for **extract tasks**. 

  Example: 

  ```json
    "maxRetentionPeriodForExtract": {
        "units": "Days",
        "value": 90
      }
  ```

Note that if the task's retention period is set with a different unit (the unit is not set to 'Days'), it is needed to covert the task's retention period to days to validate it.



### Validate the Reservation Period

### Option I

Get the maximum number of days from the [retentionperiodinfo](10_retention_period_options.md) API and compare it with the task's retention period:

- **maxRetentionPeriodForReserve** attribute returns the maximum number of days to be set in the retention period for **Entity Reservation**. 

  Example: 

  ```json
  "maxRetentionPeriodForReserve": {
        "units": "Days",
        "value": "10"
      }
  ```


Note that if the task's retention period is set with a different unit (the unit is not set to 'Days'), it is needed to covert the task's retention period to days to validate it.

### Option II

Call the **validatereserveretentionperiod** API to validate the reservation period of the task's entities.

#### API URL

/validatereserveretentionperiod

#### HTTP Method

POST

#### API Category

TDM_ReserveEntities

#### API Description

Validate if the given retention period on the input entity list and environment does not exceed the max retention period allowed for user. An admin user of the environment's owner can set unlimited retention period.

If the entities are already reserved by the user, check the new expiration date against the existing start reservation date. Else, check the retention period against the current Datetime.

#### API Input

The validated retention period can be populated either by the combination of **retentionUnit** and **retentionValue** input parameters or by populating the **newEndDateTime** parameter. If the **retention unit and values** are populated, validate the reservation period based on these parameter. Else,  validate the reservation period based on the input **newEndDateTime**.


<table width="900pxl">
<tbody>
<tr>
<td width="150pxl"><strong>Param Name</strong></td>
<td width="150pxl">
<p><strong>Mandatory</strong></p>
</td>
<td width="200pxl">
<p><strong>Valid Values</strong></p>
</td>
<td width="400pxl">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td valign="top">
<p><strong>retentionUnit</strong></p>
</td>
<td valign="top">No</td>
<td valign="top">
<ul>
<li>"Minutes"</li>
<li>"Hours"</li>
<li>"Days"</li>
<li>"Weeks"</li>
<li>"Years"</li>
</ul>
</td>
<td valign="top">Populated with the unit type of the reservation period.</td>
</tr>
<tr>
<td valign="top">
<p><strong>retentionValue</strong></p>
</td>
<td valign="top" width="200">
<p>No</p>
</td>
<td width="251">
<p>Number</p>
</td>
<td valign="top">The reservation period value. For example, if the <strong>retentionUnit </strong>parameter&nbsp;is "Days" and the <strong>retentionValue</strong> parameter is 5, the reservation period is 5 days.</td>
</tr>
<tr>
<td valign="top">
<p><strong>newEndDateTime</strong></p>
</td>
<td valign="top">
<p>No</p>
</td>
<td valign="top">
<p>'YYYYMMDDhh24MISS' format</p>
</td>
<td valign="top">New end date of the reservation period. This parameter <strong>can be set instead of the retention period unit and value</strong> parameters. If the retention unit and values parameters are populated, validate the reservation period based on these parameters. Else, validate the reservation period based on the input <strong>newEndDateTime</strong> parameter.</td>
</tr>
<tr>
<td><strong>listOfEntities</strong><strong>&nbsp;</strong></td>
<td valign="top">
<p>Yes</p>
</td>
<td>
<p>&nbsp;</p>
</td>
<td>
<p>This parameter contains the following attributes of each task's entity:</p>
<ul>
<li>environment_name</li>
<li>be_name</li>
<li>target_entity_id.</li>
</ul>

<p>Note that if the <strong>entitieslist</strong> attribute is set in the overridden execution parameters, populate the <strong>listOfEntities</strong> with the entities of the <strong>entitieslist</strong>. Else, get the selection_param_value task's attribute if the task's <strong>selection_method</strong> is 'L' (entity list). </p>

</td>
</tr>
</tbody>
</table>

#### API Input Examples

```json
{
	"retentionUnit": "Days",
	"retentionValue": 8,
	"listOfEntities": [
	{
		"environment_name": "TAR",
		"be_name": "Customer",
		"target_entity_id": "1"
	},
	{
		"environment_name": "TAR",
		"be_name": "Customer",
		"target_entity_id": "2"
	}
	]
}
```



```json
{
	"newEndDateTime": "20220218000000",
	"listOfEntities": [
	{
		"environment_name": "TAR",
	"be_name": "Customer",
	"target_entity_id": "1"
	},
	{
	"environment_name": "TAR",
	"be_name": "Customer",
	"target_entity_id": "2"
	}
	]
}
```



#### API Output Example

```json
{
  "result": [
    {
      "id": "1"
    },
    {
      "id": "3"
    },
    {
      "id": "4"
    }
  ],
  "errorCode": "FAILED",
  "message": "The validation had failed for the following entities: 1, 2. The new expiration date calculated exceeds the retention period allowed for a tester."    
}
```
