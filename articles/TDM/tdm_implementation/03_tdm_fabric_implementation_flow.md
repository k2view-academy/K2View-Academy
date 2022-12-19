# TDM - Fabric Implementation Overview

 Implementation of the TDM  in Fabric involves several steps. The following illustration displays the main ones:

[<img src="images/tdm_fabric_imp_step_1.png" alt="drawing" width="200pxl"/>](04_fabric_tdm_library.md)[<img src="images/tdm_fabric_imp_step_2.png" alt="drawing" width="200pxl"/>](05_tdm_lu_implementation_general.md)[<img src="images/tdm_fabric_imp_step_3.png" alt="drawing" width="200pxl"/>](10_tdm_generic_broadway_flows.md)[<img src="images/tdm_fabric_imp_step_5.png" alt="drawing" width="200pxl"/>](tdm_fabric_implementation_environments_setup.md)

A Fabric TDM project has the following:

- TDM Utilities, TDM Web Services , [TDM LU](04_fabric_tdm_library.md#tdm-lu) and TDM_Reference LU.
- Logical Units, TDM entities and their related data are modeled into LUs like Customer, Billing, Ordering, etc.
- Broadway flows that are defined under each LU to delete or load entities from the target environment.
- Environment setup, defining the source and target environments of the TDM. Setting the connection details of interfaces and the Globals in each environment.

K2view offers a TDM library with TDM utilities as well as TDM Templates for Broadway flows. These utilities must be implemented by the TDM Fabric project. 



[![Previous](/articles/images/Previous.png)](02_tdm_implementation_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_fabric_tdm_library.md)



