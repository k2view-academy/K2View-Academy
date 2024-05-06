# TDM Data Snapshot (Version) Tasks

TDM enables saving backup snapshots (versions) of data during the functional testing and to then reload the latest saved snapshot (version) to the target environment, if needed. Once created, the snapshot creation task can be executed multiple times to create different data versions where each version is saved in Fabric.   

This functionality is useful when running a complex testing calendar in a testing environment. Backing up data every X steps or every X times enables testers to reload the latest version to their environment and repair data without returning to the original state and losing their updates. 

Note that the testing environment is often used as a source and target environment for Data Versioning tasks. Therefore, the [Environment Type](/articles/TDM/tdm_gui/08_environment_window_general_information.md#environment-type) must be set to **Both** to enable Data Versioning in an environment.



## How do I Create a Data Snapshot Task?

Check the [Create Data Snapshot Checkbox](16_task_test_data_store_component.md#create-data-snapshot-checkbox)  checkbox in the [Test data store](16_task_test_data_store_component.md) task component.

Note that when the task process tables, the each table is saved as a separate version on the Test data store.

## Who Can Create a Data Snapshot Task?

The following users can create a data snapshot task:

1. Admin users.
2. Environment owner users.
3. Testers who can create a TDM task for environments with **Data Versioning** permissions that are attached to their [TDM Environment permission set](10_environment_roles_tab.md).  


