# TDM Fabric Implementation - Environments Setup

In general, the TDM task execution extracts the required data from the selected source environment and loads it to the selected target environments.

An environment can be defined as source environment, target environment, or can be used as both - source and target environments. 

The TDM environments must be defined in the following TDM layers:

- TDM GUI - define the TDM environments, their types, related products (systems), roles and permissions, and additional settings. These definitions are kept in the [TDM DB](/articles/TDM/tdm_architecture/02_tdm_database.md).
- Fabric - define the TDM environments (source and target) and the connection details of each environment. The following article describes the definition of the TDM environments in Fabric.

### Define the TDM Environments in Fabric

Fabric Environments are used to run the TDM processes on various environments  by switching between them according to specific needs.  The connection details of each environment's data sources are taken from Fabric environment's data.

**Example:**

Running a TDM task to copy the selected entities from **Production** environment to **UAT** environment. Fabric needs to extract the source data from Production and load it to UAT.  Of course each environment can have different connection details it its data sources. The TDM execution process sets the active environment as follows:

- Set the active environment to Production before synchronizing the entity from the data source.
- Then set the active environment tp UAT before loading the entity to the target.

Moreover, to save a separate LUI on each source environment, the [TDM concatenates the source environment to each LUI](01_tdm_set_instance_per_env_and_version.md).

Hence, the TDM implementation must include a creation and deployment of all the TDM environments including their connection details to enable the proper execution of the TDM tasks. 

[Click for more information about Fabric environments](/articles/25_environments/02_create_new_environment.md).