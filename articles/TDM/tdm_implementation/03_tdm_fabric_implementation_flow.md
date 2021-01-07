# TDM - Fabric Implementation Overview

The TDM implementation on Fabric involves several steps. The following illustration displays the main ones:

[<img src="images/tdm_fabric_imp_step_1.png" alt="drawing" width="200pxl"/>](04_fabric_tdm_library.md)[<img src="images/tdm_fabric_imp_step_2.png" alt="drawing" width="200pxl"/>]()[<img src="images/tdm_fabric_imp_step_3.png" alt="drawing" width="200pxl"/>]()[<img src="images/tdm_fabric_imp_step_4.png" alt="drawing" width="200pxl"/>]() [<img src="images/tdm_fabric_imp_step_5.png" alt="drawing" width="200pxl"/>]()

Fabric TDM projects consists on the following parts:

- TDM Utilities: TDM Web-Services and [TDM LU].
-  Logical Units: model the TDM entities and their related data to LUs such as Customer, Billing, Ordering, etc ..
- Broadway Flows: define the Broadway flows under each LU to delete or load the entities from the target environment.
- Handle special cases: reference handling,  post execution processes such as sending a mail in the end of the task execution, masking sensitive data...
- Environment setup: define the source and target environments of the TDM. Set the connection details of the interfaces, and the Globals of each environment.

K2view provides a TDM library with TDM utilities as well as TDM Templates for Broadway flows. These utilities need to be implemented by the TDM Fabric project. 



[![Previous](/articles/images/Previous.png)](02_tdm_implementation_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_fabric_tdm_library.md)



