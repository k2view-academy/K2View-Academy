# Get the Available Globals for Task

### API URL

/environment/getAllGlobals 

### HTTP Method

GET

### API Category

TDM_Environments

### API Description

Gets the list of all Global variables defined in the Fabric project except the TDM product Globals. If the optional input **lus** parameter is populated, return only shared Globals or Globals defined in the input LUs.



### API Input

- **lus**  -  this is an optional input. Can be populated by one or several LU names, separated by a comma. 

Note that the list of the task's LUs is returned by [/task/{taskId}/logicalunits](/articles/TDM/tdm_gui/TDM_Task_Execution_Flows_APIs/03_get_task_details_APIs.md#get-the-tasks-logical-units-list) API.

#### API Input Examples

```
http://localhost:3213/api/environment/getAllGlobals?lus=Customer, Billing
```

```
http://localhost:3213/api/environment/getAllGlobals
```

### API Output Examples

#### Get the Globals for Customer and Billing LUs

```json
{
    "result": [
        {
            "globalName": "CLONE_CLEANUP_RETENTION_PERIOD_VALUE",
            "globalValue": "1"
        },
        {
            "globalName": "MASK_FLAG",
            "globalValue": "0"
        },
        {
            "globalName": "CLONE_CLEANUP_RETENTION_PERIOD_TYPE",
            "globalValue": "Days"
        },
        {
            "globalName": "DEVELOPMENT_PRODUCT_VERSION",
            "globalValue": "DEV"
        },
        {
            "globalName": "PRODUCTION_PRODUCT_VERSION",
            "globalValue": "PROD"
        },
        {
            "globalName": "TDM_DEL_TABLE_PREFIX",
            "globalValue": "TAR"
        },
        {
            "globalName": "Customer.CLONE_CLEANUP_RETENTION_PERIOD_VALUE",
            "globalValue": "1"
        },
        {
            "globalName": "Customer.MASK_FLAG",
            "globalValue": "0"
        },
        {
            "globalName": "Customer.CLONE_CLEANUP_RETENTION_PERIOD_TYPE",
            "globalValue": "Days"
        },
        {
            "globalName": "Customer.DEVELOPMENT_PRODUCT_VERSION",
            "globalValue": "DEV"
        },
        {
            "globalName": "Customer.PRODUCTION_PRODUCT_VERSION",
            "globalValue": "PROD"
        },
        {
            "globalName": "Customer.TDM_DEL_TABLE_PREFIX",
            "globalValue": "TAR"
        },
        {
            "globalName": "Billing.CLONE_CLEANUP_RETENTION_PERIOD_VALUE",
            "globalValue": "1"
        },
        {
            "globalName": "Billing.MASK_FLAG",
            "globalValue": "0"
        },
        {
            "globalName": "Billing.CLONE_CLEANUP_RETENTION_PERIOD_TYPE",
            "globalValue": "Days"
        },
        {
            "globalName": "Billing.TEST_GLOBAL",
            "globalValue": "1"
        },
        {
            "globalName": "Billing.DEVELOPMENT_PRODUCT_VERSION",
            "globalValue": "DEV"
        },
        {
            "globalName": "Billing.PRODUCTION_PRODUCT_VERSION",
            "globalValue": "PROD"
        },
        {
            "globalName": "Billing.TDM_DEL_TABLE_PREFIX",
            "globalValue": "TAR"
        }
    ],
    "errorCode": "SUCCESS",
    "message": null
}
```



#### Get All Globals

```json
{
    "result": [
        {
            "globalName": "CLONE_CLEANUP_RETENTION_PERIOD_VALUE",
            "globalValue": "1"
        },
        {
            "globalName": "MASK_FLAG",
            "globalValue": "0"
        },
        {
            "globalName": "CLONE_CLEANUP_RETENTION_PERIOD_TYPE",
            "globalValue": "Days"
        },
        {
            "globalName": "DEVELOPMENT_PRODUCT_VERSION",
            "globalValue": "DEV"
        },
        {
            "globalName": "PRODUCTION_PRODUCT_VERSION",
            "globalValue": "PROD"
        },
        {
            "globalName": "TDM_DEL_TABLE_PREFIX",
            "globalValue": "TAR"
        },
        {
            "globalName": "Orders.CLONE_CLEANUP_RETENTION_PERIOD_VALUE",
            "globalValue": "1"
        },
        {
            "globalName": "Orders.MASK_FLAG",
            "globalValue": "0"
        },
        {
            "globalName": "Orders.CLONE_CLEANUP_RETENTION_PERIOD_TYPE",
            "globalValue": "Days"
        },
        {
            "globalName": "Orders.DEVELOPMENT_PRODUCT_VERSION",
            "globalValue": "DEV"
        },
        {
            "globalName": "Orders.PRODUCTION_PRODUCT_VERSION",
            "globalValue": "PROD"
        },
        {
            "globalName": "Orders.TDM_DEL_TABLE_PREFIX",
            "globalValue": "TAR"
        },
        {
            "globalName": "Customer.CLONE_CLEANUP_RETENTION_PERIOD_VALUE",
            "globalValue": "1"
        },
        {
            "globalName": "Customer.MASK_FLAG",
            "globalValue": "0"
        },
        {
            "globalName": "Customer.CLONE_CLEANUP_RETENTION_PERIOD_TYPE",
            "globalValue": "Days"
        },
        {
            "globalName": "Customer.DEVELOPMENT_PRODUCT_VERSION",
            "globalValue": "DEV"
        },
        {
            "globalName": "Customer.PRODUCTION_PRODUCT_VERSION",
            "globalValue": "PROD"
        },
        {
            "globalName": "Customer.TDM_DEL_TABLE_PREFIX",
            "globalValue": "TAR"
        },
        {
            "globalName": "Billing.CLONE_CLEANUP_RETENTION_PERIOD_VALUE",
            "globalValue": "1"
        },
        {
            "globalName": "Billing.MASK_FLAG",
            "globalValue": "0"
        },
        {
            "globalName": "Billing.CLONE_CLEANUP_RETENTION_PERIOD_TYPE",
            "globalValue": "Days"
        },
        {
            "globalName": "Billing.TEST_GLOBAL",
            "globalValue": "1"
        },
        {
            "globalName": "Billing.DEVELOPMENT_PRODUCT_VERSION",
            "globalValue": "DEV"
        },
        {
            "globalName": "Billing.PRODUCTION_PRODUCT_VERSION",
            "globalValue": "PROD"
        },
        {
            "globalName": "Billing.TDM_DEL_TABLE_PREFIX",
            "globalValue": "TAR"
        },
        {
            "globalName": "Collection.CLONE_CLEANUP_RETENTION_PERIOD_VALUE",
            "globalValue": "1"
        },
        {
            "globalName": "Collection.MASK_FLAG",
            "globalValue": "0"
        },
        {
            "globalName": "Collection.CLONE_CLEANUP_RETENTION_PERIOD_TYPE",
            "globalValue": "Days"
        },
        {
            "globalName": "Collection.DEVELOPMENT_PRODUCT_VERSION",
            "globalValue": "DEV"
        },
        {
            "globalName": "Collection.PRODUCTION_PRODUCT_VERSION",
            "globalValue": "PROD"
        },
        {
            "globalName": "Collection.TDM_DEL_TABLE_PREFIX",
            "globalValue": "TAR"
        }
    ],
    "errorCode": "SUCCESS",
    "message": null
}
```



 [![Previous](/articles/images/Previous.png)](01a_tdm_task_execution_overriding_params_flow.md)
