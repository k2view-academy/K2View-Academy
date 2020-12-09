# Globals Overrides Priorities

### Overview

Global variables are defined within **scopes**:

* [Logical Unit](/articles/03_logical_units/01_LU_overview.md), whereby the Global is available within the specific Logical Unit where it is defined.
* [References Tables](/articles/22_reference(commonDB)_tables/01_fabric_commonDB_overview.md)
* [Shared Objects](/articles/04_fabric_studio/12_shared_objects.md), whereby a Global variable is available and permeated to all objects in a project under all Logical Units, Reference Tables and [Web Services](/articles/15_web_services/01_web_services_overview.md).

The base definition, also called "Fabric Implementation" can be overridden over several **levels** - environment, global cluster and session.

This article explains these levels and scopes overrides priorities, along with examples.

### Override Priority and Hierarchy

**The Priority Rule** among all levels and scopes is - the upper, when specified, as visualized below, is stronger.

Override priority **levels** is illustrated in the following diagram:

##### <img src="images/08_05_globals_override_priority_perspct.png" alt="image" style="zoom:30%;" />



* **Fabric Implementation**, as populated at Studio Globals tables, as reflection of the Globbals.java files.
* **Environment**, as populated at Environment Settings Globals table  
* **Cluster**, uses set_global commands, within a Fabric runtime, which persist at DB and thus affect on all cluster nodes and sessions.
* **Session**, uses set commands, within a Fabric runtime, which affect only on the session where it have been executed.



In addition to levels, priority is managed by **scope**: if the Global is defined at both Shared Objects and Logical Unit scopes, the Logical Unit definition is used within that Logical Unit scope. Other Logical Units use the Shared Objects definition.

Following is a *by-scope* prioritization illustration.  

##### <img src="images/08_05_globals_override_priority_lu.png" alt="image" style="zoom:80%;" />

* Once a Global variable is set in a lower level, per scope/LU, it will be reflected and inherited by upper levels (unless overridden). For example - in case Implementation holds a value for LU "A", then when creating an environment it will be shown also there as Global variable associated to that LU.
* Yet, in any upper level, another split can be applied



### Apply Overrides

In order to apply overrides, set commands shall be used. 

* Click [here](/articles/08_globals/03_set_globals.md) for more information about Global's override using SET and SET_GLOBAL commands.

* Click [here](/articles/25_environments/05_set_and_list_commands.md) for more information about Environment switching SET commands.

Overrides can be **canceled/reset**, as explained at the above links, and in that cases Globals get back to their original values. 



### Examples - Level's Priorities

The below examples demonstrate some use cases of the priorities that explained above. Note that we show example of close levels - one level above another, but the priority is also between other levels, for example - *session* is stronger than *environment*.

##### Environment VS. Implementation 

In this example we will refer to the SOURCE_ENV_NAME which is defined at the CRM LU. The base value - the Fabric implementation is set to UAT.

Assume that we created several UAT environments UAT1, UAT2, UAT3.

We wish to switch the entire environment - the cluster - to work with UAT2, which there CRM.SOURCE_ENV_NAME="UAT2_ALPHA".

When running the switch environment command `set_global environment='UAT2';` the "UAT2_ALPHA" value for CRM.SOURCE_ENV_NAME Global will be set among all sessions in all cluster nodes.

```
set;
|key                              |value                               |
+---------------------------------+------------------------------------+
|...                              |                                    |
|ENVIRONMENT                      |UAT2                                |
|...                              |                                    |
|Global.CRM.SOURCE_ENV_NAME       |UAT2_ALPHA                          |
```

No set global command had to be  executed, since values are defined at the Environment. 

For more information about create and edit Globals per environment click [here](/articles/25_environments/02_create_new_environment.md).

For more information about switching between environments click [here](/articles/25_environments/05_set_and_list_commands.md).

##### Cluster VS. Environment

In this example we assume that even though we set specific values for our UAT environment, we shall change it temporary for today.

The required change is for the Shared Object Global variable CASES_THERSHOLD, that shall be reduced from 30,000 to 10,000.

```
set;
|key                              |value                               |
+---------------------------------+------------------------------------+
|...                              |                                    |
|ENVIRONMENT                      |UAT2                                |	
|...                              |                                    |
|Global.Customer.CASES_THRESHOLD  |30000                               |
|Global.CRM.CASES_THRESHOLD       |30000                               |
|...                              |                                    |
```

On running `set_global global '*.CASES_THRESHOLD=10000';` the outcome will be:

```
set;
|key                              |value                               |
+---------------------------------+------------------------------------+
|...                              |                                    |
|ENVIRONMENT                      |UAT2                                |	
|...                              |                                    |
|Customer.CASES_THRESHOLD         |10000                               |
|Global.CRM.CASES_THRESHOLD       |10000                               |
|...                              |                                    |
```

In order to reset it (e.g. the day after) back to the original environment values, this command shall be executed:

`set_global global '*.CASES_THRESHOLD=';`

##### Session VS. Cluster

Assume that in the previous example the threshold was set to 10000 but we still wish that one session will be used  as probe, examining another value.

For this we can run the `set CASES_THRESHOLD='20000';` command. 

In order to reset it back to its original value - the non-session level - this command shall be executed, for our example - `set CASES_THRESHOLD='';`.\ Note that if at cluster level this value was not overwritten then it will be get back to the environment Global variable value, and if also was not overwritten  for the environment, it will be set back to the implementation value.

### Examples - Scope's Priorities

Scope priorities are applied using SET commands. Click here for the command syntax and various available options. 

##### Session LU VS. Session

Sometimes while the system is working, we wish to make some testing toward an UAT environment. We might use one of the sessions for this task. Yet,  one of the LUs shall refer to another UAT environment to take its data.

For this we can call to `set SOURCE_ENV_NAME='UAT1'` for all LUs in this session, and for the CRM LU to run `set SOURCE_ENV_NAME='UAT2'` (at session level it is not matter which command you run first. The LU command will be stronger either it called before or after the general).

Once tests are done - it can be reset back to the original cluster/environment/implementation sestinas, but running `set SOURCE_ENV_NAME=''`.



### Example - Combined Priorities

Following is an illustration example demonstrating a combined settings priority rules. Colored objects are those which hold Global settings, as described below

##### <img src="images/08_05_priority_combined_example.png" alt="image" style="zoom:90%;" />

 In this example

* At Fabric Implementation there is no override per LU, meaning taking the *Shared Objects* values for *all* objects
* At Environment level - an override is made on the Shared Object, i.e. - applied on *all* objects and LUs. No change per LU on that level.
* At Cluster level - an override is done for one of LUs, while for other - not, meaning that inherit the previous level.
* At Session level - an override is made for two LUs, and in addition a change was done on the Shared Objects, which applied on other LUs and objects (Ref. & WS).



Taking the previous cases examples, let's refer to the SOURCE_ENV_NAME Global variable, following the example illustration, and examine what will be the actual values (as will be retrieved within the `set;` command).

The defined values for the SOURCE_ENV_NAME :

* Fabric Implementation - Shared Objects - "UAT" 
* Environment - Shared Objects (empty Logical Unit) - "UAT2_ALPHA" 
* Cluster - LU "A" - "UAT2_ALPHA_123" 
* Session - Shared Objects -  "UAT2_ALPHA_EXPORT_5" ; LU "A" - "UAT12"; LU "B" - "UAT17"

The actual values:

| Variable key                  | variable Value      |
| ----------------------------- | ------------------- |
| Global.A.SOURCE_ENV_NAME      | UAT12               |
| Global.B.SOURCE_ENV_NAME      | UAT17               |
| Global.C.SOURCE_ENV_NAME      | UAT2_ALPHA_EXPORT_5 |
| Global.k2_ws.SOURCE_ENV_NAME  | UAT2_ALPHA_EXPORT_5 |
| Global.k2_ref.SOURCE_ENV_NAME | UAT2_ALPHA_EXPORT_5 |
| SOURCE_ENV_NAME               | UAT2_ALPHA_EXPORT_5 |

On reset the session values, the actual values will be:

| Variable key                  | variable Value |
| ----------------------------- | -------------- |
| Global.A.SOURCE_ENV_NAME      | UAT2_ALPHA_123 |
| Global.B.SOURCE_ENV_NAME      | UAT2_ALPHA     |
| Global.C.SOURCE_ENV_NAME      | UAT2_ALPHA     |
| Global.k2_ws.SOURCE_ENV_NAME  | UAT2_ALPHA     |
| Global.k2_ref.SOURCE_ENV_NAME | UAT2_ALPHA     |





[![Previous](/articles/images/Previous.png)](/articles/08_globals/04_globals_code_examples.md)