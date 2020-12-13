# TDM Implementation Flow

The TDM implementation involves several steps. The following is an overview of the main steps :

[<img src="images/tdm_implementation_flow_step1.png" alt="drawing" width="170pxl"/>](03_tdm_fabric_implementation_flow.md)[<img src="images/tdm_implementation_flow_step2.png" alt="drawing" width="170pxl"/>](/articles/TDM/tdm_user_setup/01_tdm_fabric_credentials_setup.md)[<img src="images/tdm_implementation_flow_step3.png" alt="drawing" width="170pxl"/>](/05_tdm_deployment_and_migration.md)[<img src="images/tdm_implementation_flow_step4.png" alt="drawing" width="170pxl"/>](/articles/TDM/tdm_gui/01_tdm_gui_admin_activities.md)



The TDM implementation starts with [Fabric Implementation](03_tdm_fabric_implementation_flow.md). Then it is required to complete the Fabric setup, deploy the implementation to Fabric, and set the TDM definitions via the TDM GUI.

**Notes:** 

- It is required to deploy the LUs to Fabric before setting the TDM definitions via the TDM GUI, since the TDM GUI only retrieves the deployed LUs when adding LUs to the [BE](/articles/TDM/tdm_overview/03_business_entity_overview.md).
- It is recommended to migrate a large sub-set of entities of each LU into Fabric to get the [TDM relation](/articles/TDM/tdm_architecture/02_tdm_database.md#tdm_lu_type_relation_eid)  and the [LU Parameters](/articles/TDM/tdm_architecture/02_tdm_database.md#lu_name_params) TDM table populated before starting the TDM definitions via the TDM GUI. 

[![Previous](/articles/images/Previous.png)](01_tdm_set_instance_per_env_and_version.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_tdm_fabric_implementation_flow.md)