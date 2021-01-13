# Data Centers Window

The Data Centers window display the list of the Data Centers defined in the Fabric cluster. 

On each DC, the Data Center window displays the node, and their status. 

The Data Center window is a view only window and displays the status of the Fabric cluster.

A Data Center (DC) can be attached to the following TDM objects:

- [Business Entity](04_tdm_gui_business_entity_window.md), it is possible to attach a DC to each one of the LU of the Business Entities if the LU instances are saved under a specific DC in Fabric.  This way, whenever creating an Extract task for this Business Entity and LU, the [batch process](/articles/20_jobs_and_batch_services/11_batch_process_overview.md) which migrates the LU instances info Fabric, runs on the specified DC.

- Environment, set the DC on each [Product] (system) attached to the environment. The TDM task execution process runs the batch process on Load tasks on the DC, specified on the environment's product. 

  **Example:**

  CRM product is located in NY while the Billing product is located in TX. The batch process related to the CRM LU runs on NY DC while the batch process related to the Billing LU runs on TX DC.

  

  [![Previous](/articles/images/Previous.png)](02_tdm_gui_user_types.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_tdm_gui_business_entity_window.md)



