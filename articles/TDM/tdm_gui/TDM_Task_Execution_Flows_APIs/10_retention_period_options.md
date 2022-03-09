# Retention Period Options

## Get the Retention Period Options

This API can be used to get the retention period options of both of these activities:

- Creating a data versioning extract task.

- Reserving entities in the target environment.   

### API URL

/retentionperiodinfo

### HTTP Method

GET

### API Category

TDM_utils

### API Description

Get the retention period options as set in the [tdm_general_parameters](/articles/TDM/tdm_configuration/02_tdmdb_general_parameters.md) TDM DB table:

- The retention options and the maximum number of days for data version extract are populated in the **tdm_gui_params** parameter.
- The maximum number of days for the entity reservation is set in the **MAX_RESERVATION_DAYS_FOR_TESTER** parameter.

The retention period options contain the supported units and their default values. For example, "Days" and "5" represents 5 days. This is the default number for days.

The API also returns the maximum number of days that can be set for the following activities:

- Set a retention period of an extracted data version: get the **maxRetentionPeriodForExtract** output attribute.
- Set a reservation period of entities: get the **maxRetentionPeriodForReserve** output attribute.

### API Input

None.

### API Output Example

```json
{
  "result": {
    "maxRetentionPeriodForReserve": {
      "units": "Days",
      "value": "10"
    },
    "retentionPeriodTypes": [
      {
        "name": "Minutes",
        "units": 0.00069444444
      },
      {
        "name": "Hours",
        "units": 0.04166666666
      },
      {
        "name": "Days",
        "units": 1
      },
      {
        "name": "Weeks",
        "units": 7
      },
      {
        "name": "Years",
        "units": 365
      }
    ],
    "maxRetentionPeriodForExtract": {
      "units": "Days",
      "value": 90
    }
  },
  "errorCode": "SUCCESS",
  "message": ""
}
```

