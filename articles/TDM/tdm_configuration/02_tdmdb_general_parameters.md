# TDM DB - General Parameters

The TDM DB  [tdm_general_parameters](/articles/TDM/tdm_architecture/02_tdm_database.md#tdm_general_parameters) table holds the name of each parameter (param_name) and its value (param_value). The following categories of parameters are populated in this table:

### [TDM Clean-Up Parameters](/articles/TDM/tdm_architecture/06_tdmdb_cleanup_process.md) 

The clean-up parameters are automatically created in **tdm_general_parameters** by TDM DB creation scripts:

- The **cleanup_retention_period** defines the number of months of the retention period of Inactive records.  The clean-up process deletes inactive records that are older than the retention period.

### [LUI Separator Parameters](/articles/TDM/tdm_implementation/01_tdm_set_instance_per_env_and_version.md) 

- To create different LUIs per environment and data version, the TDM concatenates additional identifiers to each IID: environment name (for all tasks), and version identifier for Data Versioning tasks.  By default, the LUI's parts are separated by an underscore. For example: ENV1_45773.  However, if the source entity ID contains an underscore, another separator must be set on the LUI. 
  
  - For example, to enable the TDM process to parse the LUI correctly and to get the correct Customer ID, if the source Customer ID is 123_4, the LUI separator must not be an underscore.

- The **param_name** of the LUI separator is **iid_separator**.  

- The **param_value** must be populated by the String set as a separator.  

  Note that the iid_separator setting impacts all LUs in the project.

  **Example**:

  Insert the following record to tdm_general_parameters to set the separator to @ : 

  ```sql
  insert into tdm_general_parameters (param_name, param_value) values ('iid_separator', '@');
  ```

  The LUI of Customer 123_4 and environment ENV1 is **ENV1@123_4**.

  

### [Reservation Period Parameter](/articles/TDM/tdm_architecture/08_entity_reservation.md) 

An admin user of the environment's owner can reserve entities for an unlimited period. However, a tester user is limited and can only set a retention period that does not exceed the maximum retention period, defined in the TDM DB. 

The maximum number of days for the entity reservation is set in the **MAX_RESERVATION_DAYS_FOR_TESTER** parameter. 



### TDM Portal General Parameters

- The [TDM Portal](/articles/TDM/tdm_gui/01_tdm_gui_overview.md) param_name is **tdm_gui_params**. The value of this parameter includes a list of  the following parameters:
  
  - **maxRetentionPeriod**: maximum number days when setting a [retention period](/articles/TDM/tdm_gui/16_extract_task.md#retention-period) on extract tasks. Default value is 90 days.
  - **defaultPeriod**: default retention period on extract tasks. Default value is 5 days ("unit":"Days","value":5).
  - **permissionGroups**: list of the [TDM permission groups](/articles/TDM/tdm_gui/02a_permission_group_mapping_window.md). The following options are currently supported: **admin**,**owner**, and **tester**.   
  - **TDM Version**
  
  

[![Previous](/articles/images/Previous.png)](01_tdm_installation.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_tdm_fabric_credentials.md)
