# TDM - Generic Broadway Flows


The Fabric TDM library has built-in generic Broadway flows that can be easily adapted for the TDM of each specific data model. This article describes the generic flows that are available in a project after the [TDM Library](04_fabric_tdm_library.md) is imported. 

## Broadway Flows and Templates

The **TDM** folder in the Broadway Shared Objects folder includes generic flows that can be used to execute TDM tasks in several Logical Units. These flows do not require manual updates and handle activities like setting global variables and sync mode, loading reference tables, handling errors, populating execution statistics.

The **Templates** folder holds the flows used for creating DELETE and LOAD flows. Since the flows receive the Logical Unit name as an input parameter, they can be run several times for each LU.

The following discusses the structure and functionality of various generic flows.

### TDM Orchestration Flows

The TDM orchestration flows manage the execution on each task's entity. The following orchestration flow are executed by the [TDM execution process](/articles/TDM/tdm_architecture/03_task_execution_processes.md#main-tdm-task-execution-process-tdmexecutetask-job) on each task's entity:

- [**TDMOrchestrator**](/articles/TDM/tdm_implementation/11_tdm_implementation_using_generic_flows.md#step-4---tdm-orchestration-flows) - this flow runs on every LU of a [load and/or delete task](/articles/TDM/tdm_gui/14_task_overview.md#task-types)  execution. It encapsulates all Broadway flows of the TDM task into a single flow. It includes the invocation of all steps such as initiation activities, running the delete and/or load flows, managing the TDM execution process as one transaction, error handling and statistics gathering.

  [Click to learn how to create the TDMOrchestrator](/articles/TDM/tdm_implementation/11_tdm_implementation_using_generic_flows.md#create-the-tdmorchestratorflow-from-the-template).

- **TDMReserveOrchestrator** - this flow runs on [a Reserve only task](/articles/TDM/tdm_gui/20_reserve_only_task.md) execution. Unlike the TDMOrchestrator flow that runs on each LU, this process is only executed once by each task execution, and marks the root entities as a [Reserved Entity](/articles/TDM/tdm_architecture/08_entity_reservation.md) in the TDM DB.

### Initialization

TDM task initialization is performed using the **InitiateTDMLoad** flow which includes several steps like:

* Setting the values of global variables on a session level and setting sync mode.
* Setting the source environment based on the task's source before getting the LUI.
* Getting the LUI from Fabric.
* Setting the target environment as a preparation step for Delete and Load.

The **InitiateTDMLoad.flow** is performed as the first step of the **TDMOrchestrator** task's flow.



### Reference

The TDM library includes a set of flows that handle reference data.

[Click to learn more about TDM Reference Implementation](09_tdm_reference_implementation.md).

### Load and Delete Generic Flows

The TDM library holds templates and generic flows that can used to create a TDM implementation based on a project's data model. The templates are built using the Fabric [Templates](/articles/35_templates/01_templates_overview.md) functionality which enables creating different project objects based on a predefined structure. 

[Click to learn how to create a TDM Implementation using generic Broadway flows.](11_tdm_implementation_using_generic_flows.md)

### Error Handling and Statistics

The TDM library offers a generic error handling and statistics gathering mechanism based on Broadway capabilities tailored for TDM business requirements. 

[Click to learn more about TDM error handling and statistics flows](12_tdm_error_handling_and_statistics.md).



[![Previous](/articles/images/Previous.png)](09_tdm_reference_implementation.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](11_tdm_implementation_using_generic_flows.md)

