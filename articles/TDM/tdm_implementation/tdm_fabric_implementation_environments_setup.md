# TDM Fabric Implementation - Environments Setup

During the execution of a TDM task, data is extracted from a selected source environment and loaded into the selected target environment.

TDM environments must be defined in Fabric. Make sure that you define the TDM environment source and target and connection details of each environment. 

Note: You must also define the TDM environments in the TDM Portal; [click here](/articles/TDM/tdm_gui/07_tdm_gui_environment_overview.md) for more information on how to do it.  


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

### Defining the Synthetic Environments for the Data Generation Tasks

TDM supports 2 data generation methods:

- **Rule-based** data generation (added in TDM 8.0)
- **AI-based** data generation (added in TDM 9.0).

Both methods use dummy source environments. Each method has its own dummy environment. The following Globals define the dummy environment names:

- **SYNTHETIC_ENVIRONMENT** - defines the rule-based environment name. Default value is **Synthetic**.
- **AI_ENVIRONMENT** - defines the AI-based environment name. Default value is **AI**. 

Both dummy environments must be created and deployed in Fabric with the TDM interfaces. In addition, the AI-based environment must be deployed with the following interfaces:

- **AI_DB** - PostgreSQL DB used as an interface layer between the TDM and the AI processes. 
- **AI_Execution** - HTTPS interface for the Kubernetes layer for the AI processes - training and data generation.

[Click here] for more information about the TDM - AI intergation.  

### Notes:

- **Every change of either a Global or an Interface in the project requires a redeployment of the environments** to Fabric to be aligned with the updated project's Interfaces and Globals.
- The create/update TDM DB scripts add the Synthetic environments to the TDM DB. Still, there is a need to add the Systems to the Synthetic environments in the TDM portal.

[Click here](/articles/25_environments/02_create_new_environment.md) for more information about Fabric environments.

[![Previous](/articles/images/Previous.png)](16_tdm_data_generation_implementation.md)
