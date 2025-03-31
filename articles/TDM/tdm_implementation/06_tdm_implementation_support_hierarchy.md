# TDM LU Implementation - Hierarchy Support 

## TDM Relationship Tables

TDM 9.3 onwards supports the hierarchy using the TASK_EXECUTION_ENTITIES TDM DB table. The task execution populates the parent LU name, parent entity, root LU name, and root entity for each executed entity. 

## Parent LU - Implementation Guidelines 

Although Business Entities are defined in the TDM Portal, the following guidelines must be implemented to support parent-child LU hierarchy:

- Populate the [ChildLink](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md#childlink) MTable object. Note that a parent LU can have several child LUs. Populate a separate record for each child LU with the SQL queries to select the source and the target child IDs.

  

[![Previous](/articles/images/Previous.png)](05_tdm_lu_implementation_general.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](07_tdm_implementation_parameters_handling.md)

