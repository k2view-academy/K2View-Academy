# TDM - Generic Broadway Flows


The Fabric TDM library has built-in generic Broadway flows that can be easily adapted for the TDM of each specific data model. This article describes the generic flows that are available in a project, following the [TDM Library](04_fabric_tdm_library.md) import. 

## Broadway Flows and Templates

The **TDM** folder in the Broadway Shared Objects folder includes generic flows. These flows are TDM flows that handle the task execution activities such as setting global variables and a sync mode, error handling, Reference loading, and population of task execution statistics. These flows run 'as is' and do not require a manual update by the implementor. 

The **Templates** folder holds the flows used for creating delete, load, and data generation flows. Since the flows receive the Logical Unit name as an input parameter, they can be run several times for each LU.

The following discusses the structure and functionality of various generic flows. 

### TDM Entity Orchestration Flows

The TDM orchestration flows manage the execution on each task's entity. The following orchestration flows are executed by the [TDM execution process](/articles/TDM/tdm_architecture/03_task_execution_processes.md#main-tdm-task-execution-process-tdmexecutetask-job) on each task's entity:

- [**TDMOrchestrator**](11_tdm_implementation_using_generic_flows.md#tdmorchestrator-flow) - this flow runs on every LU of a [load and/or delete task](/articles/TDM/tdm_gui/14_task_overview.md#task-types)  execution. It encapsulates all Broadway flows of the TDM task into a single flow. It includes the invocation of all steps such as initiation activities, running the delete and/or load flows, managing the TDM execution process as one transaction, error handling and statistics gathering.

- **TDMReserveOrchestrator** - this flow runs on [a Reserve only task](https://github.com/k2view-academy/K2View-Academy/blob/Academy_8.1/articles/TDM/tdm_gui/17a_task_target_component_entities.md#reserve) execution. Unlike the TDMOrchestrator flow that runs on each LU, this process is only executed once by each task execution, and it marks the root entities as a [Reserved Entity](/articles/TDM/tdm_architecture/08_entity_reservation.md) in the TDM DB.

  

### Initialization

TDM task initialization is performed using the **InitiateTDMLoad** flow, which includes several steps such as:

* Setting the values of global variables on a session level and setting a sync mode.
* Setting the source environment based on the task's source before getting the LUI.
* Getting the LUI from Fabric.
* Setting the target environment as a preparation step for Delete and Load.

The **InitiateTDMLoad.flow** is performed as the 1st step of the **TDMOrchestrator** task's flow.

### Tables

The TDM library includes a set of flows that handle tables.

[Click here to learn more about TDM Tables Implementation](09_tdm_reference_implementation.md).

### Load and Delete Generic Flows

The TDM library holds templates and generic flows that can be used to create a TDM implementation based on a project's data model. The templates are built using the Fabric [Templates](/articles/35_templates/01_templates_overview.md) functionality, which enables creating different project objects based on a predefined structure. 

[Click here to learn how to create a TDM Implementation using generic Broadway flows.](11_tdm_implementation_using_generic_flows.md)

### Error Handling and Statistics

The TDM library offers a generic error handling and statistics gathering mechanism based on Broadway capabilities that are tailored for TDM business requirements. 

[Click here to learn more about TDM error handling and statistics flows](12_tdm_error_handling_and_statistics.md).

### Data Generation

New templates, flows and Actors have been added in the TDM 8.0 to support a synthetic data generation of entities.

Click [here](16_tdm_data_generation_implementation.md) to learn more about TDM data generation implementation.

### 

[![Previous](/articles/images/Previous.png)](09_tdm_reference_implementation.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](11_tdm_implementation_using_generic_flows.md)

