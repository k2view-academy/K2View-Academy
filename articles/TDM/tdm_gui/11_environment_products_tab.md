# Environment Systems Tab

A [TDM System](05_tdm_gui_product_window.md) (product) represents a system or application installed in a source or target environment. Each Testing environment must have at least one system which can be added, edited or deleted from the environment by an Admin user or the [Environment Owner](08_environment_window_general_information.md#environment-owners).  

An environment's systems are displayed in the Environment window's **Systems tab**:

- To add a system to an environment, click **Add System**, populate the system's setting and then click **Add**.
- To open a selected system, click the **Name** of the system and then click **Save Changes**. 
- To delete a system, click the [![be_Example](images/delete_icon.png)](/articles/TDM/tdm_gui/images/delete_icon.png) icon in the right corner of the System window.

## Environment System Window 

The System Window holds the following settings:

- **System Name**, select a system from the dropdown list.
- **Data Center Name**, the Data Center where the system is physically located in the environment. For example, ENV1 may have CRM in NY and Billing in TX. Select a data center from the dropdown list.

- **System Version**, the version of the installed system in the environment. For example, the Production environment has CRM V1 and the Dev1 environment has CRM V1.5.  Select a version from the dropdown list.

  Click for more information about [supporting multiple system versions via TDM](/articles/TDM/tdm_implementation/13_tdm_implementation_supporting_different_product_versions.md).

  

  Note that the connection details of the data sources (interfaces) of a system in an environment are populated and saved in Fabric.

  

   [![Previous](/articles/images/Previous.png)](10_environment_roles_tab.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](12_environment_globals_tab.md)
