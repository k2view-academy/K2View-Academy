# TDM LU Implementation — Hierarchy Support 

The [TDM task](/articles/TDM/tdm_gui/14_task_overview.md) can process Business Entities (BEs) and/or tables. A [Business Entity (BE)](/articles/TDM/tdm_overview/03_business_entity_overview.md) represents the main entity of the selected data to be provisioned by TDM. A BE can have multiple [LUs](https://github.com/k2view-academy/K2View-Academy/blob/Academy_8.3_TDM_9.4/articles/03_logical_units/01_LU_overview.md) with either a flat or a hierarchical structure. For example, a Customer BE can have CRM, Billing, Ordering and Usage LUs.

## Parent LU — Implementation Guidelines 

BEs are defined in the [TDM self-service application](articles/TDM/tdm_gui/04_tdm_gui_business_entity_window.md). In addition, the following guidelines must be implemented to support parent-child LU hierarchy:

- Populate the [ChildLink](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md#childlink) MTable object. Note that a parent LU can have several child LUs. Populate a separate record for each child LU with the SQL queries to select the source and the target child IDs.


## TDM Relationship Tables

TDM 9.3 onwards supports the hierarchy using the TASK_EXECUTION_ENTITIES TDM DB table. The task execution populates the parent LU name, parent entity, root LU name, and root entity for each executed entity. 



[![Previous](/articles/images/Previous.png)](05d_tdm_sequence.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](08_tdm_implement_delete_of_entities.md)

