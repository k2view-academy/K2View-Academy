# Affinity and Worker Configuration for Task Execution

- Starting with **TDM 9.5**,  TDM now supports configuring **affinity** and/or **number of workers** at both the **TDM environment level** and the **task level**. These settings provide greater control over resource allocation and workload distribution.

- This feature applies to:

  - Entity-based tasks
  - Table-level tasks

  ## Affinity Configuration

- *Affinity* refers to Fabric assigning a job or a batch process to a specific handling node within a Fabric Cluster. This is particularly handy when specific nodes are reserved for specific tasks or need to be dedicated to time-consuming or heavy processing executions.

  Click [here](Task affinity can include either a DC name or a cluster Logical ID.md) for more information about the batch affinity.

- The task execution affinity can include either a DC name or a cluster Logical ID.

- An affinity can be defined either on the [Environment system](/articles/TDM/tdm_gui/11_environment_products_tab.md#affinity-and-maximum-number-of-workers) of on the Task level. 

  The following diagram describes the priority of how the task execution gets the affinity:   

  ![affinity priority](images/tdm_affinity_priority.png)

### Task Level Affinity Configuration  

An affinity can be on both task types - [entity-level](/articles/TDM/tdm_gui/14b_task_source_component_entities.md#system--logical-units-tab---affinity-and-max-number-of-workers) and [table-level](/articles/TDM/tdm_gui/14c_task_source_component_tables.md). The affinity can be set either on the Source or the Target component in the task. The table below describes which affinity is taken - source or target - for each task type and processed data:


</head>
<body>
  <table>
    <thead>
      <tr>
        <th>Processed Data</th>
        <th>Task Type</th>
        <th>Source Affinity</th>
        <th>Target Affinity</th>
        <th>Task Exec. Affinity</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>Entities, Tables, Entities &amp; Tables</td>
        <td>All types</td>
        <td>Undefined</td>
        <td>Undefined</td>
        <td>No affinity is set</td>
      </tr>
      <tr>
        <td>Entities, Tables, Entities &amp; Tables</td>
        <td>Extract</td>
        <td>Defined</td>
        <td>N/A</td>
        <td>Runs on source affinity</td>
      </tr>
      <tr>
        <td>Entities</td>
        <td>Load / Delete / Reserve only</td>
        <td>N/A</td>
        <td>Defined</td>
        <td>Runs on target affinity</td>
      </tr>
      <tr>
        <td>Entities</td>
        <td>Extract &amp; Load</td>
        <td>Defined</td>
        <td>Defined</td>
        <td>The batch process runs on the target env affinity.
The get LUI runs on the source env affinity (remote get)</td>
      </tr>
      <tr>
        <td>Entities</td>
        <td>Extract &amp; Load</td>
        <td>Undefined</td>
        <td>Defined</td>
        <td>No affinity is set on the batch process.
The get LUI runs on the source affinity (remote get)</td>
      </tr>
      <tr>
        <td>Entities</td>
        <td>Extract &amp; Load</td>
        <td>Undefined</td>
        <td>Undefined</td>
        <td>No affinity is set on the batch process or the get LUI</td>
      </tr>
      <tr>
        <td>Tables</td>
        <td>Extract &amp; Load</td>
        <td>N/A</td>
        <td>Defined</td>
        <td>The extract + load batch process runs on the target env affinity</td>
      </tr>
      <tr>
        <td>Tables</td>
        <td>Extract &amp; Load</td>
        <td>Undefined</td>
        <td>Undefined</td>
        <td>No affinity is set</td>
      </tr>
      <tr>
        <td>Tables</td>
        <td>Extract &amp; Load &amp; Delete</td>
        <td>N/A</td>
        <td>Defined</td>
        <td>The extract + load and the delete batch processes run on the Task target affinity</td>
      </tr>
      <tr>
        <td>Tables</td>
        <td>Extract &amp; Load / Extract &amp; Load &amp; Delete</td>
        <td>N/A</td>
        <td>Undefined</td>
        <td>No affinity is set</td>
      </tr>
    </tbody>
  </table>
</body>
</html>



## Workers Configuration

The maximum number of workers allocated per Fabric node for the batch process execution, is limited by [Fabric config.ini file](/articles/20_jobs_and_batch_services/12_batch_sync_commands.html) parameter (MAX_WORKERS_PER_NODE). This is also the default number of workers set when running a batch process.

Starting with TDM 9.5, it is possible to set a different default value of number of workers per batch process:

- TDM_GENERAL_PARAMETERS TDM DB table - a new parameter has been added: MAX_NO_OF_WORKERS_FOR_EXECUTION. If this parameter value is -1, then ignore this parameter. Else, if this parameter value >0, use it as a default number of workers. 

- [Environment](/articles/TDM/tdm_gui/11_environment_products_tab.md) - you can set maximum number of workers per system. 

- Task - you can set the maximum number of workers either on the Source component or on the Target component. The maximum number of workers can be set for [entity-level](/articles/TDM/tdm_gui/14b_task_source_component_entities.md#system--logical-units-tab---affinity-and-max-number-of-workers) and [table-level](/articles/TDM/tdm_gui/14c_task_source_component_tables.md) tasks.

- The following diagram describes the priority of how the task execution gets the maximum number of workers:   

  ![workers priority](images/tdm_max_workers_priority.png)

The table below describes which value is taken by the task execution for number of workers - source or target - for each task type and processed data:

</head>
<body>
  <div class="table-wrapper">
  <table>
    <thead>
      <tr>
        <th>Task Type</th>
        <th>Source No. of Workers</th>
        <th>Target No. of Workers</th>
        <th>Task Exec. No. of Workers</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>All types</td>
        <td>Undefined</td>
        <td>Undefined</td>
        <td>Default no. of workers</td>
      </tr>
      <tr>
        <td>Extract</td>
        <td>Undefined</td>
        <td>N/A</td>
        <td>Default no. of workers</td>
      </tr>
      <tr>
        <td>Extract</td>
        <td>Defined</td>
        <td>N/A</td>
        <td>Source no. of workers</td>
      </tr>
      <tr>
        <td rowspan="2">Load / Delete / Reserve only &amp; Extract &amp; Load</td>
        <td>N/A</td>
        <td>Undefined</td>
        <td>Default no. of workers</td>
      </tr>
      <tr>
        <td>N/A</td>
        <td>Defined</td>
        <td>Target no. of workers</td>
      </tr>
    </tbody>
  </table>
  </div>
</body>
</html>
