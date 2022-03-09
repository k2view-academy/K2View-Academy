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
  "result": [
    {
      "number_of_extracted_entities": 122,
      "version_datetime": "2022-01-31 14:01:09.289",
      "version_name": "createCustSnapshot",  
      "version_type": "ALL",
      "task_execution_id": 133,
      "version_number": 1,   
      "execution_note": "backup1",
      "lu_name": "Customer",
      "fabric_execution_id": "3534d569-ea35-4f38-b89d-e34b7cc11bc4",
      "task_id": 30,
      "task_last_updated_by": "admin",
      "root_indicator": "Y"
    },
    {
      "number_of_extracted_entities": 298,
      "version_datetime": "2022-01-31 14:01:09.289",
      "version_name": "createCustSnapshot",
      "version_type": "ALL",
      "task_execution_id": 133,
      "version_number": 1,   
      "execution_note": "backup1",  
      "lu_name": "Billing",
      "fabric_execution_id": "ee6c4878-89a0-4f6a-a79f-836349c78633",
      "task_id": 30,
      "task_last_updated_by": "admin",
      "root_indicator": "N"
    },
    {
      "number_of_extracted_entities": 122,
      "version_datetime": "2022-01-31 14:20:04.510",
      "version_name": "createCustSnapshot",
      "version_type": "ALL",
      "task_execution_id": 134,
      "version_number": 2,   
      "execution_note": "backup2",
      "lu_name": "Customer",
      "fabric_execution_id": "1c77242d-776b-4c09-bd2c-a06e966175ff",
      "task_id": 30,
      "task_last_updated_by": "admin",
      "root_indicator": "Y"
    },
    {
      "number_of_extracted_entities": 298,
      "version_datetime": "2022-01-31 14:20:04.510",
      "version_name": "createCustSnapshot",
      "version_type": "ALL",
      "task_execution_id": 134, 
      "version_number": 2,   
      "execution_note": "backup2",
      "lu_name": "Billing",
      "fabric_execution_id": "bdc15916-d82f-4ffc-9fa1-7e6cad4790d8",
      "task_id": 30,
      "task_last_updated_by": "admin",
      "root_indicator": "N"
    }
  ],
  "errorCode": "SUCCESS",
  "message": null
}
```





