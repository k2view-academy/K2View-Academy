# TDM Fabric Implementation - Environments Setup

During the execution of a TDM task, data are extracted from a selected source environment and loaded into the selected target environment.

TDM environments must be defined in Fabric. Make sure that you define the TDM environment source and target and connection details of each environment. 

Note: You must also define the TDM environments in the TDM Portal. [Click here](/articles/TDM/tdm_gui/07_tdm_gui_environment_overview.md) for more information on doing this.  


## Defining TDM Environments in Fabric

Fabric environments are used to run TDM processes on various environments by switching between them according to the task's environments.  The connection details of each environment's data sources are taken from the Fabric environment's data.

**Example:** Running a TDM task to copy selected entities from the **Production** environment to the **UAT** environment. Fabric must extract the source data from Production and load it to UAT. Each environment can have different connection details for its data sources. 

The TDM execution process sets the active environment as follows:

   1. Set the active environment to Production before synchronizing the entity from the data source.
   2. Set the active environment to UAT before loading the entity to the target.

 To save a separate Logical Unit Instance (LUI) for each source environment, the TDM concatenates the source environment to each LUI.

Note that **you must not include an underscore ('_') in the environment name when running the TDM using the default LUI separator**, since the default LUI separator is an underscore. This way, the TDM execution process cannot parse the LUI properly.

 [Click here](01_tdm_set_instance_per_env_and_version.md) for more information about the TDM LUI format. 

The TDM implementation must include the **creation and deployment** of all the TDM environments with their connection details to enable the execution of the TDM tasks. 

### Defining the Synthetic Environment for the Generate Tasks

TDM 8.0 added a support of a new task action (type): Generate - for generating synthetic entities.

The [Generate task] is based on a dummy source environment named **Synthetic**. The **Synthetic** environment must be created and deployed in Fabric with the TDM and DB_CASSANDRA interfaces (these interfaces must be enabled), and must also be created in the TDM DB.



### Notes:

- **Every change of either a Global or an Interface in the project requires a redeployment of the environments** to Fabric to be aligned with the updated project's Interfaces and Globals.
- It is important to **set the TDM_APIDOC_JSON local file system interface as disabled in the Environments** to avoid an error when running the test connection on the task's environment (Fabric server has a different IP address than the local windows machine and cannot connect window's directory).
- The create/update TDM DB scripts add the Synthetic environment to the TDM DB. Still, there is a need to add the Systems to the Synthetic environment in the TDM portal.

[Click here](/articles/25_environments/02_create_new_environment.md) for more information about Fabric environments.

[![Previous](/articles/images/Previous.png)](12_tdm_error_handling_and_statistics.md)
