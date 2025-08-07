# TDM — Project Implementation Overview

A TDM Fabric project includes the following:

- [TDM Library](04_fabric_tdm_library.md). The TDM Library must be included in the TDM Fabric project. 
- Logical Units — business-driven entities and their related data are modeled into LUs such as CRM, Billing, Ordering, etc. Each LU contains Broadway flows to delete or load entities from/to the target environment. In addition, an LU contains data generation Broadway flows to generate rule-based synthetic entities.
- An environment setup — a process in which the source and target environments of TDM are defined, and the connection details of interfaces and Globals in each environment are configured.

Implementing a TDM project in Fabric Studio involves several steps. Note that all steps are implemented in Fabric Studio except for the Fabric Catalog step, which is handled separately. The following highlights the main steps: 

## 1. [TDM Library Installation](/articles/98_installation_and_upgrade/Install_TDM/TDM_Installation_V9.4.md#tdm-development-environment-installation)  

Click [here](04_fabric_tdm_library.md) for more information about the TDM Library and its content.

## 2. [Creating Interfaces for Data Sources](/articles/05_DB_interfaces/01_interfaces_overview.md) 

- Note: Creating an interface for a non-JDBC source may require installing the corresponding [K2exchange connector](/articles/04_fabric_studio/28_web_k2exchange.md).

## 3. Optional — [Fabric Catalog](/articles/39_fabric_catalog/01_catalog_overview.md)  

- [Running Discovery on project interfaces](/articles/39_fabric_catalog/04a_catalog_integration_with_fabric.md) — the Discovery process analyzes the requested interfaces and, among others activities, creates the schema with  identified relations between datasets, and classifies fields as PIIs. The Catalog can also create artifacts in a CSV-format file, enabling the creation of Logical Units (LUs) based on the discovered and enriched data model. 
- [PII settings](/articles/39_fabric_catalog/catalog_app/10_catalog_settings.md#pii--masking-tab)
- [Sequence settings](/articles/39_fabric_catalog/catalog_app/10_catalog_settings.md#sequences-tab)

## 4. [Creation of Logical Units (LUs) and TDM Setup](05_tdm_lu_implementation_general.md)

## 5. LUs — Special Handling

- [Masking implementation](05c_tdm_masking.md)
- [Sequence implementation](05d_tdm_sequence.md)
- [LU hierarchy implementation](06_tdm_implementation_support_hierarchy.md)
- [Error and statistics handling](12_tdm_error_handling_and_statistics.md)

## 6. Optional — Implementation for Entity Subset Setting

- [Business parameters implementation](07_tdm_implementation_parameters_handling.md)
- [Custom logic flows implementation](11d_custom_logic.md)
- [Predefined entity list implementation](11c_predefined_entity_list.md)

## 7. Optional — Synthetic Data Implementation

- [Rule-based generation](16_tdm_data_generation_implementation.md)
- [AI-based generation](17_tdm_ai_generation_implementation.md)

## 7. [Environment Setup](tdm_fabric_implementation_environments_setup.md)

## 8. Additional Optional Setting 

- [Implementation of table-level tasks](09_tdm_reference_implementation.md)

- Implementation of pre and post execution processes

  

  

[![Previous](/articles/images/Previous.png)](02_tdm_implementation_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_fabric_tdm_library.md)



