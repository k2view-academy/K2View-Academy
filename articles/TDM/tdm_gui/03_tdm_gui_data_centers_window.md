# Data Centers Window

The Data Centers (DC) window is view only. It displays a list of the Data Centers in a Fabric cluster together with their nodes and status. 

A Data Center can be attached to the following TDM objects:

- **Environment**
  -  A DC and/or Logical ID can be attached on each Environment's [system](11_environment_products_tab.md). The TDM task's execution process runs the batch processes on the environment's affinity if no other affinity value is set on the task.

- **Task**
  - Each **LU** (entity-level task) or **interface** (table-level task) can have a **DC** and/or **Logical ID** assigned.
  - During execution, the TDM task runs batch processes based on **these task-specific affinity values**, overriding the environment’s default if set..


**Example:**

- The CRM system is in NY.
- The Billing system is in TX.
- The batch process related to the CRM LU runs on the NY DC while the batch process related to the Billing LU runs on the TX DC.

  

  [![Previous](/articles/images/Previous.png)](02_tdm_gui_user_types.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_tdm_gui_business_entity_window.md)



