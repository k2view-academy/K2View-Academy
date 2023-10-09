# Retention Period Options

## Get the Retention Period Options

This API can be used to get the period options for the following 2 activities:

- Retaining a period for the extracted LUIs.

- Reserving entities in the target environment.   

### API URL

/retentionperiodinfo

### HTTP Method

GET

### API Category

TDM_utils

### API Description

Get the retention period options as set in the [tdm_general_parameters](/articles/TDM/tdm_configuration/02_tdmdb_general_parameters.md) TDM DB table:

- The retention period and reservation period options are populated in the **tdm_gui_params** parameter.
- The maximum number of days for an entity reservation is set in the **MAX_RESERVATION_DAYS_FOR_TESTER** parameter.
- The maximum number of days for a retention period is set in the **MAX_RETENTION_DAYS_FOR_TESTER** parameter.

The retention period and reservation period options contain the supported units and their default values. For example, "Days" and "5" represent 5 days. This is the default number of days.

The API also returns the maximum number of days that can be set for the following activities:

- **maxRetentionPeriodForTesters** - the maximum retention period of the extracted LUIs in case a tester does not select the 'Do not Delete' option.
- **maxReservationPeriodForTesters** - the maximum reservation period for which a tester can reserve entities on the testing environment.

### API Input

None.

### API Output Example

```json
{
    "result": {
       "reservationDefaultPeriod": {
            "unit": "Days",
            "value": 5
        },
       "retentionDefaultPeriod": {
            "unit": "Do Not Delete",
            "value": -1
        },
        "retentionPeriodTypes": [
            {
                "name": "Minutes",
                "units": 6.9444444E-4
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
      "reservationPeriodTypes": [
            {
                "name": "Minutes",
                "units": 6.9444444E-4
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

      "versioningRetentionPeriod": {
            "unit": "Days",
            "value": 5,
           "allow_doNotDelete": true
        },
       "versioningRetentionPeriodForTesters": {
            "unit": "Days",
            "value": 5,
           "allow_doNotDelete": false
        },

      "maxRetentionPeriodForTesters": {
            "units": "Days",
            "value": 90
        },

      "maxReservationPeriodForTesters": {
            "units": "Days",
            "value": 10
        }
    },
    "errorCode": "SUCCESS",
    "message": ""
}
```

