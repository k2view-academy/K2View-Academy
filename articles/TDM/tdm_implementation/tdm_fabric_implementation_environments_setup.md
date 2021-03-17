# TDM Fabric Implementation - Environments Setup

During the execution of a TDM task, data is extracted from a selected source environment and loaded into the selected target environment.
An environment can be defined as a source environment, target environment or both source and target environments. 

TDM environments must be defined in the following TDM components:

- TDM GUI, define the TDM environment types, related products (systems), roles and permissions and additional settings. These definitions are kept in the [TDM DB](/articles/TDM/tdm_architecture/02_tdm_database.md).
- Fabric, define the TDM environment source and target and connection details of each environment. 

### Defining TDM Environments in Fabric

Fabric environments are used to run TDM processes on various environments by switching between them according to the task's environments.  The connection details of each environment's data sources are taken from the Fabric environment's data.

**Example:**

Running a TDM task to copy selected entities from the **Production** environment to the **UAT** environment. Fabric must extract the source data from Production and load it to UAT. Each environment can have different connection details for its data sources. 

The TDM execution process sets the active environment as follows:

1. Set the active environment to Production before synchronizing the entity from the data source.
2. Set the active environment to UAT before loading the entity to the target.

To save a separate LUI for each source environment, the TDM concatenates the source environment to each LUI.

Click for more information about the [TDM LUI format](01_tdm_set_instance_per_env_and_version.md). 



The TDM implementation must include the **creation and deployment** of all the TDM environments with their connection details to enable the execution of the TDM tasks. 

Note that **every change of a Global or an Interface in the project requires a redeployment of the environments** to Fabric to be aligned with the updated project's Interfaces and Globals.

Click for more information about [Fabric environments](/articles/25_environments/02_create_new_environment.md).

[![Previous](/articles/images/Previous.png)](12_tdm_error_handling_and_statistics.md)
