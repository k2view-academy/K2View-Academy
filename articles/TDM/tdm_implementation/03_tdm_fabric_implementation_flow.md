# TDM - Fabric Implementation Overview

The implementation of TDM in Fabric involves several steps. The following illustration displays the main ones. All the steps are implemented in Fabric Studio except the Fabric Catalog step: 

1. [TDM Library installation](/articles/98_installation_and_upgrade/Install_TDM/TDM_Installation_V9.4.md#tdm-development-environment-installation) - 

   Click [here](04_fabric_tdm_library.md) for more information about the TDM Library and its content.

2. Creating [interfaces](/articles/05_DB_interfaces/01_interfaces_overview.md) for data sources - 

   - Note - creating an interface for non-JDBC source may require an installation of the respective [K2exchange connector](/articles/04_fabric_studio/28_web_k2exchange.html)

3. Optional - [Fabric Catalog](/articles/39_fabric_catalog/01_catalog_overview.md) - 

   - [Running a Discovery on the project interfaces](/articles/39_fabric_catalog/04a_catalog_integration_with_fabric.md) - the Discovery process analyzes the requested interfaces, and among others, creates the schema with the identified relations between the datasets,  and classifies fields as PIIs. The Catalog can also create artifacts in a CSV-format file which enables to create Logical Units (LUs) based on the discovered and enriched data model. 
   - [PII settings](/articles/39_fabric_catalog/catalog_app/10_catalog_settings.md#pii--masking-tab)
   - [Sequence settings](/articles/39_fabric_catalog/catalog_app/10_catalog_settings.md#sequences-tab)

4. [Logical units (LUs) creation](05_tdm_lu_implementation_general.md)

5. Adding a TDM setup to the LUs:

   - [TDM tables and flows](05_tdm_lu_implementation_general.md)
   - Masking handling
   - Sequence handling
   - Business parameters implementation
   - Error and statistics handling

6. Optional - Synthetic data implementation:

   - Rule-based generation
   - AI-based generation

7. Environment setup

8. Optional setting: 

   - Table level tasks' implementation

   - Pre and post execution processes implementation

   - Custom logic flows implementation

     


A Fabric TDM project includes the following:

- TDM Utilities - TDM Web Services, [TDM LU](04_fabric_tdm_library.md#tdm-lu) and TDM_TableLevel LU.
- Logical Units - TDM entities and their related data are modeled into LUs like CRM, Billing, Ordering, etc.
- Broadway flows that are defined under each LU in order to delete or load entities from/to the target environment.
- An environment setup - a process in which the source and target environments of the TDM are defined, and the connection details of interfaces and the Globals in each environment are set.

K2view offers a TDM library with TDM utilities as well as TDM Templates for Broadway flows. These utilities must be included in the TDM Fabric project. 



[![Previous](/articles/images/Previous.png)](02_tdm_implementation_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_fabric_tdm_library.md)



