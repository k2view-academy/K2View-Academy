# Get the Available Data Versions for a Load Task

### API URL

/tasks/versionsForLoad

### HTTP Method

POST

### API Category

TDM_Tasks

### API Description

Get the list of the available data versions that match the input parameters. The user can select one of the data versions for the [Data Versioning](/articles/TDM/tdm_gui/15_data_flux_task.md) load task and reload the entities in the selected versions to the target environment. The API filters our expired data versions. For example, a data version that is created with a retention period of 5 days, will no longer be able to be retrieved after 6 days have passed.

### API Input

The request body contains the following filtering parameters:

<table width="900pxl">
<tbody>
<tr>
<td style="width: 197.912px;"><strong>Param Name</strong></td>
<td style="width: 195.668px;">
<p><strong>Mandatory</strong></p>
</td>
<td style="width: 485.227px;">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td style="width: 197.912px;" valign="top">
<p><strong>entitiesList</strong></p>
</td>
<td style="width: 195.668px;" valign="top">
<p>No</p>
</td>
<td style="width: 485.227px;" valign="top">
<p>Populated with a list of entities separated by a comma. When populated, the API only brings data versions that contain the entities in the list.</p>
</td>
</tr>
<tr>
<td style="width: 197.912px;" valign="top">
<p><strong>be_id</strong></p>
</td>
<td style="width: 195.668px;" valign="top">
<p>Yes</p>
</td>
<td style="width: 485.227px;">
<p>Populated by the unique identifier (be_id) of the task's Business Entity (BE).</p>
</td>
</tr>
<tr>
<td style="width: 197.912px;"><strong>source_env_name</strong></td>
<td style="width: 195.668px;">
<p>Yes</p>
</td>
<td style="width: 485.227px;">
<p>Populated by the source environment from which the data version has been extracted.</p>
</td>
</tr>
<tr>
<td style="width: 197.912px;"><strong>fromDate</strong></td>
<td style="width: 195.668px;">
<p>No</p>
</td>
<td style="width: 485.227px;">
<p>Populated to return a list of data versions within a given time interval. The date format of these parameters is "MM.DD.YYYY" or "MM-DD-YYYYY".</p>
</td>
</tr>
<tr>
<td style="width: 197.912px;"><strong>toDate</strong></td>
<td style="width: 195.668px;">
<p>No</p>
</td>
<td style="width: 485.227px;">
<p>Populated to return a list of data versions within a given time interval. The date format of these parameters is "MM.DD.YYYY" or "MM-DD-YYYYY".</p>
</td>
</tr>
<tr>
<td style="width: 197.912px;"><strong>lu_list</strong></td>
<td style="width: 195.668px;">
<p>Yes</p>
</td>
<td style="width: 485.227px;">
<p>List of LU names. For example, when populated with <strong>Customer</strong>&nbsp;and&nbsp;<strong>Billing</strong>&nbsp;, the API only returns data versions that contain both LUs.</p>
</td>
</tr>
</tbody>
</table>

### API Input Examples

```json
{

	"be_id": 1,
	"source_env_name": "TAR",
	"fromDate": "01.01.2022",
	"toDate": "01.31.2022",
	"lu_list": [
		{"lu_name":"Customer"},
		{"lu_name":"Billing"}
	]
}
```

```json
{

    "entitiesList": "1, 22, 33" ,	
    "be_id": 1,
    "source_env_name": "TAR",
    "fromDate": "01.01.2022",
    "toDate": "01.31.2022",
    "lu_list": [
    	{"lu_name":"Customer"},
		{"lu_name":"Billing"}
    ]

}
```



### API Output Example

Note that the API returns the full list of LUs on each returned data version.

```json
{
  "result": {
    "EntityReservationValidations": {},
    "ListOfVersions": [
      {
        "version_name": "createCustSnapshot",
        "task_id": 59,
        "task_execution_id": 208,
        "task_last_updated_by": "harry",
        "version_type": "Selected Entities",
        "number_of_extracted_entities": 104,
        "version_datetime": "2022-03-20 11:25:31.229",
        "lu_name": "Collection",
        "fabric_execution_id": "41079b2d-55ab-4ed7-9f74-e18941d5296e",
        "root_indicator": "N",
        "num_of_succeeded_entities": 104,
        "num_of_failed_entities": 0,
        "execution_note": null,
        "version_no": 1
      },
      {
        "version_name": "createCustSnapshot",
        "task_id": 59,
        "task_execution_id": 208,
        "task_last_updated_by": "harry",
        "version_type": "Selected Entities",
        "number_of_extracted_entities": 221,
        "version_datetime": "2022-03-20 11:25:31.229",
        "lu_name": "Customer",
        "fabric_execution_id": "eb4f3157-3c79-4138-a719-97840f8e3d0e",
        "root_indicator": "Y",
        "num_of_succeeded_entities": 221,
        "num_of_failed_entities": 279,
        "execution_note": null,
        "version_no": 1
      },
      {
        "version_name": "createCustSnapshot",
        "task_id": 59,
        "task_execution_id": 208,
        "task_last_updated_by": "harry",
        "version_type": "Selected Entities",
        "number_of_extracted_entities": 748,
        "version_datetime": "2022-03-20 11:25:31.229",
        "lu_name": "Billing",
        "fabric_execution_id": "30622d98-d983-40eb-884b-c496642aa7c2",
        "root_indicator": "N",
        "num_of_succeeded_entities": 748,
        "num_of_failed_entities": 27,
        "execution_note": null,
        "version_no": 1
      },
      {
        "version_name": "createCustSnapshot",
        "task_id": 59,
        "task_execution_id": 208,
        "task_last_updated_by": "harry",
        "version_type": "Selected Entities",
        "number_of_extracted_entities": 1048,
        "version_datetime": "2022-03-20 11:25:31.229",
        "lu_name": "Orders",
        "fabric_execution_id": "2da473d7-c795-443b-9b90-610c958a19b2",
        "root_indicator": "N",
        "num_of_succeeded_entities": 1048,
        "num_of_failed_entities": 0,
        "execution_note": null,
        "version_no": 1
      },
      {
        "version_name": "createCustSnapshot",
        "task_id": 60,
        "task_execution_id": 209,
        "task_last_updated_by": "admin",
        "version_type": "Selected Entities",
        "number_of_extracted_entities": 112,
        "version_datetime": "2022-03-20 13:25:31.230",
        "lu_name": "Collection",
        "fabric_execution_id": "8b6aef81-f632-48c7-8db6-2423cfb1a23f",
        "root_indicator": "N",
        "num_of_succeeded_entities": 112,
        "num_of_failed_entities": 0,
        "execution_note": null,
        "version_no": 2
      },
      {
        "version_name": "createCustSnapshot",
        "task_id": 60,
        "task_execution_id": 209,
        "task_last_updated_by": "admin",
        "version_type": "Selected Entities",
        "number_of_extracted_entities": 253,
        "version_datetime": "2022-03-20 13:25:31.230",
        "lu_name": "Customer",
        "fabric_execution_id": "ef84e092-9683-4f5a-b494-9d06b8aee711",
        "root_indicator": "Y",
        "num_of_succeeded_entities": 253,
        "num_of_failed_entities": 0,
        "execution_note": null,
        "version_no": 2
      },
      {
        "version_name": "createCustSnapshot",
        "task_id": 60,
        "task_execution_id": 209,
        "task_last_updated_by": "admin",
        "version_type": "Selected Entities",
        "number_of_extracted_entities": 861,
        "version_datetime": "2022-03-20 13:25:31.230",
        "lu_name": "Billing",
        "fabric_execution_id": "665d6de7-caca-4d52-9e6a-aca57ef28994",
        "root_indicator": "N",
        "num_of_succeeded_entities": 861,
        "num_of_failed_entities": 27,
        "execution_note": null,
        "version_no": 2
      },
      {
        "version_name": "createCustSnapshot",
        "task_id": 60,
        "task_execution_id": 209,
        "task_last_updated_by": "admin",
        "version_type": "Selected Entities",
        "number_of_extracted_entities": 1109,
        "version_datetime": "2022-03-20 13:25:31.230",
        "lu_name": "Orders",
        "fabric_execution_id": "4790ccfa-37d5-48f8-8b15-e1fc7a041ed6",
        "root_indicator": "N",
        "num_of_succeeded_entities": 1109,
        "num_of_failed_entities": 0,
        "execution_note": null,
        "version_no": 2
      },
      {
        "version_name": "createCustSnapshot",
        "task_id": 60,
        "task_execution_id": 210,
        "task_last_updated_by": "admin",
        "version_type": "Selected Entities",
        "number_of_extracted_entities": 104,
        "version_datetime": "2022-03-20 13:34:05.077",
        "lu_name": "Collection",
        "fabric_execution_id": "0f0c893c-182c-4bb8-938d-0529064b21c2",
        "root_indicator": "N",
        "num_of_succeeded_entities": 104,
        "num_of_failed_entities": 0,
        "execution_note": null,
        "version_no": 3
      },
      {
        "version_name": "createCustSnapshot",
        "task_id": 60,
        "task_execution_id": 210,
        "task_last_updated_by": "admin",
        "version_type": "Selected Entities",
        "number_of_extracted_entities": 221,
        "version_datetime": "2022-03-20 13:34:05.077",
        "lu_name": "Customer",
        "fabric_execution_id": "63644180-f674-4d21-a0b1-05677dc39fb5",
        "root_indicator": "Y",
        "num_of_succeeded_entities": 221,
        "num_of_failed_entities": 279,
        "execution_note": null,
        "version_no": 3
      },
      {
        "version_name": "createCustSnapshot",
        "task_id": 60,
        "task_execution_id": 210,
        "task_last_updated_by": "admin",
        "version_type": "Selected Entities",
        "number_of_extracted_entities": 748,
        "version_datetime": "2022-03-20 13:34:05.077",
        "lu_name": "Billing",
        "fabric_execution_id": "e18117d0-c599-4e11-ab60-2cc153c2cbd6",
        "root_indicator": "N",
        "num_of_succeeded_entities": 748,
        "num_of_failed_entities": 27,
        "execution_note": null,
        "version_no": 3
      },
      {
        "version_name": "createCustSnapshot",
        "task_id": 60,
        "task_execution_id": 210,
        "task_last_updated_by": "admin",
        "version_type": "Selected Entities",
        "number_of_extracted_entities": 1048,
        "version_datetime": "2022-03-20 13:34:05.077",
        "lu_name": "Orders",
        "fabric_execution_id": "8c27aeb7-ccd8-4b7a-bb2a-d2cfddcc90a3",
        "root_indicator": "N",
        "num_of_succeeded_entities": 1048,
        "num_of_failed_entities": 0,
        "execution_note": null,
        "version_no": 3
      }
    ]
  },
  "errorCode": "SUCCESS",
  "message": null
}
```





