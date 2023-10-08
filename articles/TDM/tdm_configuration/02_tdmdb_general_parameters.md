# TDM DB - General Parameters

The TDM DB **tdm_general_parameters** table holds the name of each parameter (param_name) and its value (param_value). The following categories of parameters are populated in this table:

## TDM VERSION

The **TDM_VERSION** parameter is populated with the TDM version that is displayed in the TDM portal.

## [TDM Clean-Up Parameters](/articles/TDM/tdm_architecture/06_tdmdb_cleanup_process.md) 

The clean-up parameters are automatically created in **tdm_general_parameters** by TDM DB creation scripts:

- The **cleanup_retention_period** defines the number of months of the retention period for inactive records. The clean-up process deletes inactive records that are older than the retention period; the default period is set to 1 week (0.25 months).

## [LUI Separator Parameters](/articles/TDM/tdm_implementation/01_tdm_set_instance_per_env_and_version.md)  

- To create different LUIs per environment and per data version, the TDM concatenates additional identifiers to each IID: environment name (for all tasks) and version identifier for Data Versioning tasks. By default, the LUI parts are separated by an underscore, e.g., ENV1_45773.
- However, if the source entity ID already contains an underscore, another separator must be set on the LUI. Insert a **new record** to the tdm_general_parameters table in order to set an LUI separator that is different from the defaultive (underscore) value.
      
- The **param_name** of the LUI separator is **iid_separator**.  

- The **param_value** must be populated by a String set as a separator.  

  Note that the iid_separator setting impacts all LUs in the project.

  **Example**:

  Insert the following record to the tdm_general_parameters table to set the separator to @ : 

  ```sql
  insert into tdm_general_parameters (param_name, param_value) values ('iid_separator', '@');
  ```

  If the source Customer ID is 123_4 and the environment is ENV1, the LUI is set as **ENV1@123_4**. This way, the TDM process can parse the LUI correctly and get the correct Customer ID.
  

## Maximum [Reservation Period](/articles/TDM/tdm_architecture/08_entity_reservation.md) for Testers

An admin user of the environment owner can reserve entities for an unlimited period of time. However, a tester user is limited and can only set a retention period that does not exceed the maximum retention period defined in the TDM DB. 

The maximum number of days for the entity reservation is set in the **MAX_RESERVATION_DAYS_FOR_TESTER** parameter; the default value is 90 (90 days).

## Maximum [Retention Period](/articles/TDM/tdm_gui/16_extract_task.md#retention-period) for Testers

The maximum number of days that a tester can set to a retention period in the task is defined in the **MAX_RETENTION_DAYS_FOR_TESTER** parameter; the default value is 90 (90 days). Note that the validation is not set if the user sets the retention period to **Do not Delete**.

## Data Generation Parameters

- The **TABLE_DEFAULT_DISTRIBUTION_MIN** and **TABLE_DEFAULT_DISTRIBUTION_MAX** parameters set the default range for the number of generated records, populated in the LU table for each parent ID by a [data generation task](/articles/TDM/tdm_gui/16a_generate_task.md). By default, these parameters are populated with 1 and 3, i.e., the data generation task generates 1-3 records for the LU tables except for the main source LU table that is created with only one record by the data generation task.
- Edit these parameters, if needed, in order to set a different number range of records for synthetically generated entities.

  Click [here](/articles/TDM/tdm_implementation/16_tdm_data_generation_implementation.md) for more information about the data generation implementation.

## TDM Portal General Parameters

- The [TDM portal](/articles/TDM/tdm_gui/01_tdm_gui_overview.md) param_name is **tdm_gui_params**. The value of this parameter includes a list of the following parameters:
  
  - **retentionDefaultPeriod** - default retention period of the LUIs on *extract* or *extract and load* tasks; the default value is **Do Not Delete**.
  
  - **reservationDefaultPeriod** - default reservation period for an entity reservation.
  
  - **versioningRetentionPeriod** and **versioningRetentionPeriodForTesters** - default retention period on *extract with data versioning* tasks; the default value is 5 days ("unit":"Days","value":5).
  
  - **permissionGroups** - list of the [TDM permission groups](/articles/TDM/tdm_gui/02a_permission_group_mapping_window.md). The following options are currently supported: **admin**, **owner** and **tester**.
  
  - **enable_reserve_by_params** - this parameter indicates whether the TDM portal enables the Parameters selection method on [Reserve only tasks](/articles/TDM/tdm_gui/20_reserve_only_task.md). When set to **true**, it is enabled; the **default is false**.
  
  - **retentionPeriodTypes** and **reservationPeriodTypes** - these are the available options for the retention and reservation periods.
  
    ### Update the TDM_GENERAL_PARAMETERS to Support Fabric Storage without a TTL
  
    TDM enables setting a [retention period](/articles/TDM/tdm_gui/16_extract_task.md#retention-period) (TTL) on the TDM tasks in order to save the task's entities in Fabric only for a limited period of time. However, if the [Fabric storage](/articles/32_LU_storage/01_LU_storage_overview.md) does not support a TTL for the LUIs (such as PG DB), the TDM needs to limit the TDM task’s retention period options to either **Do not Delete** or **Do not Retain**.
    
    Run the following UPDATE statements in the TDM DB to update the **retentionPeriodTypes**, **versioningRetentionPeriod**, and **versioningRetentionPeriodForTesters** attributes. This is needed to enable setting only **Do not Delete** or **Do not Retain** values in the TDM task's retention period.
    
    ```sql
    UPDATE 
       tdm_general_parameters
    SET 
       param_value = REPLACE(param_value,'"retentionPeriodTypes":[{"name":"Minutes","units":0.00069444444},{"name":"Hours","units":0.04166666666},{"name":"Days","units":1},{"name":"Weeks","units":7},{"name":"Years","units":365}]', '"retentionPeriodTypes":[]')  
    where param_name = 'tdm_gui_params'; 
    
    UPDATE 
       tdm_general_parameters
    SET 
       param_value = REPLACE(param_value, '"versioningRetentionPeriod":{"units":"Days","value":5,"allow_doNotDelete":True}',  '"versioningRetentionPeriod":{"units":"Do Not Delete","value":-1,"allow_doNotDelete":True}')
      where param_name = 'tdm_gui_params'; 
    
     UPDATE 
       tdm_general_parameters
    SET 
       param_value = REPLACE(param_value, '"versioningRetentionPeriodForTesters":"versioningRetentionPeriodForTesters":{"units":"Days","value":5,"allow_doNotDelete":False}',  '"versioningRetentionPeriodForTesters":{"units":"Do Not Delete","value":-1,"allow_doNotDelete":True}')
      where param_name = 'tdm_gui_params'; 
    ```
    
    
    
    ### Update the TDM_GENERAL_PARAMETERS to Support a Reserve Task with Parameters 
    
    Run the following UPDATE statement on the TDM DB in order to enable the Parameters selection value for Reserve tasks: 
    
    ```sql
    UPDATE 
       tdm_general_parameters
    SET 
       param_value = REPLACE(param_value,'"enable_reserve_by_params":False','"enable_reserve_by_params":true') 
    where param_name = 'tdm_gui_params'; 
    ```
    
    Note that it is required to run an Extract task on a large subset of entities and to populate the Extract from Environment with the Reserve task's testing environment. The Extract task needs to run prior to the Reserve task creation in order to populate the Parameters tables in the TDM DB for the testing environment.
    
    Click [here](/articles/TDM/tdm_architecture/07_tdm_parameters_handling.md) for more information about the Parameters' TDM DB tables. 
  
  
  
  

[![Previous](/articles/images/Previous.png)](01_tdm_installation.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_tdm_fabric_credentials.md)
