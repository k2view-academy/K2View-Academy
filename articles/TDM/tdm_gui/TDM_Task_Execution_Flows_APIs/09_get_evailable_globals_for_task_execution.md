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

#### Get the Globals Billing LU: return all the shared and the Billing LU's Globals.

```json
{
    "result": [
        {
            "globalName": "MASK_FLAG",
            "globalValue": "0",
            "description": "Indicates if the sensitive data must be masked by the task execution. 1 is set as true and 0 is set as false.",
            "lu_name": "All"
        },              
        {
            "globalName": "PRODUCTION_PRODUCT_VERSION",
            "globalValue": "PROD",
            "description": "Indicates the applicative version of the product (system) in the environment",
            "lu_name": "All"
        },      
        {
            "globalName": "PAYMENT_METHOD",
            "globalValue": "CC",
            "description": "Payment method name",
            "lu_name": "Billing"
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
            "globalName": "MASK_FLAG",
            "globalValue": "0",
            "description": "Indicates if the sensitive data must be masked by the task execution. 1 is set as true and 0 is set as false.",
            "lu_name": "All"
        },              
        {
            "globalName": "PRODUCTION_PRODUCT_VERSION",
            "globalValue": "PROD",
            "description": "Indicates the applicative version of the product (system) in the environment",
            "lu_name": "All"
        },
        {
            "globalName": "PAYMENT_METHOD",
            "globalValue": "CC",
            "description": "Payment method name",
            "lu_name": "Billing"
        }
        {
            "globalName": "CUSTOMER_TYPE",
            "globalValue": "Business",
            "description": "Customer type. Valid values are Private, Business, and Government",
            "lu_name": "Customer"
        }
    ],
    "errorCode": "SUCCESS",
    "message": null
}
```



 [![Previous](/articles/images/Previous.png)](01a_tdm_task_execution_overriding_params_flow.md)
