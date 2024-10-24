# Task - Subset Component

The Subset component is displayed by the following icon ![subset](images/task_subset_icon.png) and contains either of the following:

- [Entity subset](15a_entity_subset.md) -  defines the entity subset for the task when the task extracts entities from the source environment or gets the pre-extracted/pre-generated entities from the Test Data Store. 
- [Table subset](15b_table_subset.md) - an optional setting to be used when the task runs on [tables only](14c_task_source_component_tables.md). It enables the user to filter the extracted records for the task's tables. 

The entity subset is **mandatory** either when the task extracts **entities** from the source environment or when it gets the pre-extracted/pre-generated entities from the Test Data Store.  

The table subset is **optional** for tasks that are created for **tables only**. Note that TDM does not support table subsetting when selecting [Entities & referential data](14b_task_source_component_entities.md) in the task's Source component.

In general, the Subset component is located between the [Source](14a_task_source_component.md) and the [Test Data Store](16_task_test_data_store_component.md) components to reflect the data subset extracted from the source or the data subset that is synthetically generated:

![subset example](images/task_widget_subset_example2.png)

However, when the data is not extracted from the source, the subset moves and is located between the [Test Data Store]((16_task_test_data_store_component.md)) and the [Target](17_task_target_component.md) components:  

- Getting pre-extracted or pre-generated data from the TDM Test Data Store:

  ![subset example](images/task_widget_subset_example1.png)



- [Delete only](17a_task_target_component_entities.md#delete) and [Reserve only](17a_task_target_component_entities.md#reserve) tasks - the Source component is disabled:

  ![subset example](images/task_widget_subset_example3.png)


 [![Previous](/articles/images/Previous.png)](14a_task_source_component.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](16_task_test_data_store_component.md)
