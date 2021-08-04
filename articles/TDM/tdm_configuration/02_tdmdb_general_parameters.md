# TDM DB - General Parameters

The TDM DB  [tdm_general_parameters](/articles/TDM/tdm_architecture/02_tdm_database.md#tdm_general_parameters) table holds the name of each parameter (param_name) and its value (param_value). The following categories of parameters are populated in this table:

- [TDM Clean-up](/articles/TDM/tdm_architecture/06_tdmdb_cleanup_process.md) parameters: These are automatically created in **tdm_general_parameters** by TDM DB creation scripts.

- [LUI separator](/articles/TDM/tdm_implementation/01_tdm_set_instance_per_env_and_version.md#tdm-separator) parameters: By default, the LUI separator is set to underscore. However, if the source entity ID contains an underscore, another separator must be set. For example, to enable the TDM process to parse the LUI correctly and to get the correct Customer ID, if the source Customer ID is 123_4, the LUI separator must not be an underscore.
  - The **param_name** of the LUI separator is **iid_separator**.  
  - The **param_value** must be populated by the String set as a separator.  

  Note that the iid_separator setting impacts all LUs in a project.

  **Example**:

  Insert the following record to tdm_general_parameters to set the separator to @ : 

  ```sql
  insert into tdm_general_parameters (param_name, param_value) values ('iid_separator', '@');
  ```
  
  The LUI of Customer 123_4 and environment ENV1 is **ENV1@123_4**.

- [TDM GUI](/articles/TDM/tdm_gui/01_tdm_gui_overview.md) parameters: The param_name is **tdm_gui_params**. The value of this parameter includes a list of  the following parameters:
  - **maxRetentionPeriod**: maximum number days when setting a [retention period](/articles/TDM/tdm_gui/16_extract_task.md#retention-period) on extract tasks. Default value is 90 days.
  - **defaultPeriod**: default retention period on extract tasks. Detault value is 5 days ("unit":"Days","value":5).
  - **permissionGroups**: list of the [TDM permission groups](/articles/TDM/tdm_gui/02a_permission_group_mapping_window.md). The following options are currently supported: **admin**,**owner**, and **tester**.   

[![Previous](/articles/images/Previous.png)](01_tdm_installation.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_tdm_fabric_credentials.md)
