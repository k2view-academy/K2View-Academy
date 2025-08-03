# TDM - Fabric Implementation Overview

A Fabric TDM project includes the following:

- [TDM Library](04_fabric_tdm_library.md). The TDM Library must be included in the TDM Fabric project. 
- Logical Units -  business driven entities and their related data are modeled into LUs like CRM, Billing, Ordering, etc. Each LU contains Broadway flows to delete or load entities from/to the target environment. In addition and LU contains data generation generation Broadway flows to generate rule-based synthetic entities
- An environment setup - a process in which the source and target environments of the TDM are defined, and the connection details of interfaces and the Globals in each environment are set.

The implementation of TDM in Fabric involves several steps. The following illustration displays the main ones. All the steps are implemented in Fabric Studio except the Fabric Catalog step: 

## 1. [TDM Library installation](/articles/98_installation_and_upgrade/Install_TDM/TDM_Installation_V9.4.md#tdm-development-environment-installation)  

Click [here](04_fabric_tdm_library.md) for more information about the TDM Library and its content.

## 2. [Creating interfaces for data sources](/articles/05_DB_interfaces/01_interfaces_overview.md) 

- Note - creating an interface for non-JDBC source may require an installation of the respective [K2exchange connector](/articles/04_fabric_studio/28_web_k2exchange.html)

## 3. Optional - [Fabric Catalog](/articles/39_fabric_catalog/01_catalog_overview.md)  

- [Running a Discovery on the project interfaces](/articles/39_fabric_catalog/04a_catalog_integration_with_fabric.md) - the Discovery process analyzes the requested interfaces, and among others, creates the schema with the identified relations between the datasets,  and classifies fields as PIIs. The Catalog can also create artifacts in a CSV-format file which enables to create Logical Units (LUs) based on the discovered and enriched data model. 
- [PII settings](/articles/39_fabric_catalog/catalog_app/10_catalog_settings.md#pii--masking-tab)
- [Sequence settings](/articles/39_fabric_catalog/catalog_app/10_catalog_settings.md#sequences-tab)

## 4. [Logical units (LUs) creation and TDM Setup](05_tdm_lu_implementation_general.md)

## 5. LUs - Special Handling

- Masking handling
- Sequence handling
- LU hierarchy implementation
- Business parameters implementation
- Error and statistics handling

## 6. Optional - Synthetic data implementation

- Rule-based generation
- AI-based generation

## 7. Environment setup

## 8. Additional Optional setting 

- Table level tasks' implementation

- Pre and post execution processes implementation

- Custom logic flows implementation

  

[![Previous](/articles/images/Previous.png)](02_tdm_implementation_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_fabric_tdm_library.md)



