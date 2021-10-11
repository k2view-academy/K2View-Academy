# Data Centers Window

The Data Centers (DC) window is view only. It displays a list of the Data Centers in a Fabric cluster together with their nodes and status. 

A Data Center can be attached to the following TDM objects:

- [Business Entity](04_tdm_gui_business_entity_window.md) (BE), a DC can be attached to each LU in a BE if the LU instances are saved under a specific DC in Fabric. When creating an extract task for the BE and LU, the [batch process](/articles/20_jobs_and_batch_services/11_batch_process_overview.md) which migrates the LU instances into Fabric runs on the specified DC.

- Environment, set the DC on each [product](11_environment_products_tab.md) (system) attached to the environment. The TDM task's execution process runs the batch process on Load tasks on the DC specified in the target environment's product. The LUI sync is executed on the DC specified in the source environment's product.

**Example:**

The CRM product is in NY while the Billing product is in TX. The batch process related to the CRM LU runs on the NY DC while the batch process related to the Billing LU runs on the TX DC.

  

  [![Previous](/articles/images/Previous.png)](02_tdm_gui_user_types.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_tdm_gui_business_entity_window.md)



