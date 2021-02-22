# Environment Products Tab

A [TDM Product](05_tdm_gui_product_window.md) represents a system or application installed in a source or target environment. Each Testing environment must have at least one product which can be added, edited or deleted from the environment by an Admin user or the [Environment Owner](08_environment_window_general_information.md#environment-owners).  

An environment's products are displayed in the Environment window's **Products tab**:

- To add a new product to an environment, click **Add Product**, populate the product's setting and then click **Add**.
- To open a selected product, click the **Name** of the product and then click **Save Changes**. 
- To delete a product, click the [![be_Example](https://github.com/k2view-academy/K2View-Academy/raw/Academy_6.4_TDM/articles/TDM/tdm_gui/images/delete_icon.png)](https://github.com/k2view-academy/K2View-Academy/blob/Academy_6.4_TDM/articles/TDM/tdm_gui/images/delete_icon.png) icon in the right corner of the Product window.

## Environment Product Window 

The Product Window holds the following settings:

- **Product Name**, select a product from the dropdown list.
- **Data Center Name**, the Data Center where the product is physically located in the environment. For example, ENV1 may have CRM in NY and Billing in TX. Select a data center from the dropdown list.

- **Product Version**, the version of the installed product in the environment. For example, the Production environment has CRM V1 and the Dev1 environment has CRM V1.5.  Select a version from the dropdown list.

  [Click for more information about supporting multiple product versions via TDM].

  

  Note that the connection details of the data sources (interfaces) of a product in an environment are populated and saved in Fabric.

  

   [![Previous](/articles/images/Previous.png)](10_environment_roles_tab.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](12_environment_globals_tab.md)
