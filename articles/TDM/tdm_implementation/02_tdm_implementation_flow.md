# TDM Implementation Flow

A TDM implementation involves several steps. The following illustration displays the main ones:

[<img src="images/tdm_implementation_flow_step1.png" alt="drawing" width="200pxl"/>](03_tdm_fabric_implementation_flow.md)[<img src="images/tdm_implementation_flow_step2.png" alt="drawing" width="200pxl"/>](/articles/TDM/tdm_configuration/03_tdm_fabric_credentials.md)[<img src="images/tdm_implementation_flow_step4.png" alt="drawing" width="200pxl"/>](/articles/TDM/tdm_gui/01_tdm_gui_overview.md)



A TDM implementation begins with a [Fabric Implementation](03_tdm_fabric_implementation_flow.md) and is followed by Fabric setup, deploying the implementation to Fabric, and setting TDM definitions in the TDM GUI.

**Notes:** 

- Complete the implementation in Fabric and deploy the LUs to Fabric before setting TDM definitions via the TDM GUI, since the TDM GUI only retrieves the deployed LUs when adding LUs to the [BE](/articles/TDM/tdm_overview/03_business_entity_overview.md).
- It is recommended to migrate a large sub-set of entities of each LU into Fabric to populate the [TDM relation (tdm_lu_type_relation_eid)](/articles/TDM/tdm_architecture/02_tdm_database.md#tdm_lu_type_relation_eid) and the [LU Parameters](/articles/TDM/tdm_architecture/02_tdm_database.md#lu_name_params) TDM table before the creation and execution of TDM load tasks to enable the user to create load tasks based on [selected parameters](/articles/TDM/tdm_gui/18_load_task_requested_entities_regular_mode.md#parameters).  

[![Previous](/articles/images/Previous.png)](01_tdm_set_instance_per_env_and_version.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_tdm_fabric_implementation_flow.md)
