# Environment Globals Tab

The [Global variables](/articles/08_globals/01_globals_overview.md) defined in a Fabric TDM implementation can be overridden on an environment or [TDM task](23_task_globals_tab.md) level. 

**Examples:**

- A DB Schema Name Global variable is added and set on an environment level since each environment can have a different DB Schema name.
- An Email Global variable is added and populated by the tester's email on a TDM task level.

A Global variable value can be set on the TDM environment level by an Admin user or the [Environment Owner](08_environment_window_general_information.md#environment-owners).  The overridden Global variables are displayed in the **Environment Variables tab** in the Environment window:

- To set a variable value on the TDM environment, click **Set Environment Variable**, populate the variable's setting and then click **Add**.
- To open a selected variable, click the **Name** of the variable and then click **Save Changes**. 
- To delete a variable value from an environment, click the [![be_Example](images/delete_icon.png)](/articles/TDM/tdm_gui/images/delete_icon.png) icon in the right corner of the Environment Variable window. 

## Environment Variable Window 

The Environment Variable window in the Environment window holds the following settings:

- **Variable Name**, select a variable from the dropdown list. The dropdown list displays the list of Globals in the Fabric TDM implementation.
- **Logical Unit**, can be populated with 'ALL' or with a specific LU name.
- **Variable Value**,  a Global variable can have a different value in each environment. 



 [![Previous](/articles/images/Previous.png)](11_environment_products_tab.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](13_reserved_entities_window.md)

