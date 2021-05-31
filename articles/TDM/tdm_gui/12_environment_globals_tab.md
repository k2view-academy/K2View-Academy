# Environment Globals Tab

The [Global variables](/articles/08_globals/01_globals_overview.md) defined in a Fabric TDM implementation can be overridden on an environment or [TDM task](23_task_globals_tab.md) level. 

**Examples:**

- A DB Schema Name Global is added and set on an environment level since each environment can have a different DB Schema name.
- An Email Global is added and populated by the tester's email on a TDM task level.

A Global can be added, edited or deleted from an environment by an Admin user or the [Environment Owner](08_environment_window_general_information.md#environment-owners).  

Environment Globals are displayed in the **Environment Globals tab** in the Environment window:

- To add a new Global to an environment, click **Add Global**, populate the Global's setting and then click **Add**.
- To open a selected Global, click the **Name** of the Global and then click **Save Changes**. 
- To delete a Global from an environment, click the [![be_Example](images/delete_icon.png)](/articles/TDM/tdm_gui/images/delete_icon.png) icon in the right corner of the Global window. The TDM execution process uses the default value of the Global.

## Environment Global Window 

The Global Window in the Environment Window holds the following settings:

- **Global Name**, select a Global from the dropdown list of Globals in the Fabric TDM implementation.
- **Global Value**, set the value of the Global. A Global variable can have a different value in each environment. 



 [![Previous](/articles/images/Previous.png)](11_environment_products_tab.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](13_environment_exclusion_lists.md)

