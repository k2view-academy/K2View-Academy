# TDM Data Snapshot (Version) Tasks

K2view's TDM enables saving backup snapshots (versions) of data during the functional testing and then reloading the latest saved snapshot (version) to the target environment, if needed. Once created, the snapshot creation task can be executed multiple times to create different data versions where each version is saved in Fabric.   

This functionality is useful when running a complex testing calendar in a testing environment. Backing up data every X steps or every X times enables testers to reload the latest version to their environment and repair data without returning to the original state and losing their updates. 

Note that the testing environment is often used as a source as well as a target environment for Data Versioning tasks. Therefore, the [Environment Type](/articles/TDM/tdm_gui/08_environment_window_general_information.md#environment-type) must be set to **Both** to enable Data Versioning in an environment.



## How do I Create a Data Snapshot Task?

Check the [Create Data Snapshot Checkbox](16_task_test_data_store_component.md#create-data-snapshot-checkbox) checkbox in the [Test Data Store](16_task_test_data_store_component.md) task component.

Note that when the task processes tables, each table is saved as a separate version in the Test Data Store.

## Who Can Create a Data Snapshot Task?

The following users can create a data snapshot task:

1. Admin users.
2. Environment owner users.
3. Testers who can create a TDM task for environments with **Data Versioning** permissions that are attached to their [TDM Environment permission set](10_environment_roles_tab.md).

## How do I Load a Data Snapshot?
- Open the [Source](14b_task_source_component_entities.md#policy-for-fetching-data) form and select the **Selected snapshot (version)** option for the **Policy for fetching data** field.
- Open the [Subset](15a_entity_subset.md) form and select either one of the following options:
  - Load all entities in the select a data version (snapshot)
  - Load an entity list from the selected data version (snapshot) 


