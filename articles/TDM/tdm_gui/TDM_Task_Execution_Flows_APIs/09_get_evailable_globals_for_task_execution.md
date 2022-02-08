# Get the Available Globals (Variables) for Task

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

### API Output
The API output returns a list of available Global variables. The following attributes are populated on each Global variable:
- **globalName** - Global variable name
- **globalValue** - the current value of the Global variable
- **luList** - the luList value depends on the Global variable definition:
    - When the Global variable is only defined in the **Shared Objects**, the luList is populated with **ALL**.
    - When the Global variable is only defined in the **LUs**, the luList is populated with the **LU names**.
    - When the Global variable is defined in **both**, the **Shared Objects** and the **LUs**,  the luList is populated with **ALL** and the **LU names**.

### API Output Examples

#### Get the Globals Billing LU: return all the shared and the Billing LU's Globals.

```json
{
    "result": [
        {
            "globalName": "MASK_FLAG",
            "globalValue": "0",
            "luList": ["ALL"]
        },              
        {
            "globalName": "PRODUCTION_PRODUCT_VERSION",
            "globalValue": "PROD",
            "luList": ["ALL"]
        },      
        {
            "globalName": "PAYMENT_METHOD",
            "globalValue": "CC",
            "luList": ["Billing"]
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
            "luList": ["ALL"]
        },              
        {
            "globalName": "PRODUCTION_PRODUCT_VERSION",
            "globalValue": "PROD",
            "luList": ["ALL"]
        },
        {
            "globalName": "PAYMENT_METHOD",
            "globalValue": "CC",
            "luList": ["Billing"]
        },
        {
            "globalName": "CUSTOMER_TYPE",
            "globalValue": "Business",
            "luList": ["Customer", "Order"]
        }
    ],
    "errorCode": "SUCCESS",
    "message": null
}
```



 [![Previous](/articles/images/Previous.png)](01a_tdm_task_execution_overriding_params_flow.md)
