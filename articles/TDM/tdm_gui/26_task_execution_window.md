# Task Execution Window

TDM 10 introduces a simplified way to execute tasks that requires no technical knowledge of TDM configuration. Rather than building a task from scratch, task runners work from a pre-defined task created by a qualified engineer. The task serves as a reusable **template** — the task runner adjusts only what is relevant for the current run, and the task itself is never modified.

## Tasks as Templates

When a task creator defines a task, they decide — attribute by attribute — which parameters are fixed and which can be adjusted at execution time. Fixed parameters are **locked**: the task runner sees them but cannot change them. Parameters the creator has explicitly unlocked can be edited by the task runner before each execution.

This means task runners get a clean, guided experience with sensible defaults already in place, and only the parameters that are meaningful for their run are exposed. No deep TDM knowledge is required.

> **Important:** Changes made in the execution window apply only to the current execution. They never modify the saved task. The task template remains intact for every future run.

## Opening the Execution Window

The Execute Task window can be opened in two ways:

- **From the Task Management window** — click a task card to open the execution window for that task.
- **From the task window** — click the **Save & open Execution** button to save the task and immediately open its execution window.
- **From the execution history** — select a previous execution to reopen the task execution window pre-populated with the parameters used in that run.

![Execute Task window](images/task_execution_window.png)

## Task Execution Prompt

The task execution prompt is a predefined text that describes the task action. Each task type has its own predefined prompt text. The prompt includes the task's attributes — for example:

> *Copy data by **Customer** from **Production** to **UAT**. Subset by **Entity list** using: **100, 200**. Reserve the entities for **5 Days**.*

Attributes the creator has **unlocked** appear as **clickable blue links**. Clicking a link opens an inline editor where the task runner can provide or change the value for this execution. Attributes that are **locked** appear as plain bold text and cannot be changed.

The **refresh icon** in the top-right corner of the prompt box resets all editable attributes back to the task's default values.

### Editing an Unlocked Attribute

Clicking an unlocked attribute opens a small editing popup. After making changes, click **Save** to apply or **Cancel** to discard. The task execution prompt updates immediately to reflect the new value.

![Editing an entity list parameter](images/task_execution_edit_entity_list.png)

For environment parameters, the editor shows a drop-down list of the available options:

![Editing the target environment](images/task_execution_edit_environment.png)

### Business Parameters

For tasks that filter entities using business parameters, the task summary includes a link to the filter. Clicking it expands the filter inline. The task runner can change the values of individual conditions the creator has unlocked — for example, changing a STATE value — but cannot modify the overall filter structure.

![Business parameters filter](images/task_execution_business_params.png)

### Custom Logic Parameters

For tasks that use a custom logic subset, the task summary includes a link to the custom logic function name. Clicking it expands a **Custom logic parameters** panel inline. The panel shows the parameters defined in the custom logic function — the task runner can select which parameters to include and set their values.

Parameters marked as required (indicated by an asterisk) are always included and cannot be deselected. Optional parameters can be checked or unchecked by the task runner. Each parameter has a reset icon to restore its default value.

![Custom logic parameters](images/task_execution_custom_logic.png)

### Data Generation Parameters

For generation tasks, the task summary describes the synthetic data to be generated. A parameters panel below the summary shows the generation parameters the creator has unlocked, allowing the task runner to adjust values such as city or weighted distributions before running the task.

![Data generation parameters](images/task_execution_generation_params.png)

## Notes

Clicking the **clipboard icon** next to the task description opens the **Notes** panel. Notes are written by the task creator to guide the task runner — for example, explaining which parameters to update for a typical execution or what values are appropriate.

![Notes panel](images/task_execution_notes.png)

## Execution Notes

The **Execution notes** field is a free-text area where the task runner can record context about the current run. Execution notes are saved with the execution record and visible in the execution history.

## Advanced Settings

The **Advanced** button opens additional execution options across four tabs:

- **System settings** — LU-level affinity and worker settings, for any values the creator has unlocked.
- **Pre execution process** — lists pre-execution processes. If the creator has unlocked input parameters, the task runner can expand each process and provide values before running.
- **Post execution process** — same as above for post-execution processes.
- **Task variables** — variables defined in the task. Unlocked variables can be set or overridden for the current execution.

![Advanced Settings](images/task_execution_advanced_settings.png)

![Pre execution process inputs](images/task_execution_pre_process_inputs.png)

## Previous Executions

Clicking **Previous Executions** opens the execution history for this task. Task runners can review past runs and select any previous execution to reopen the task execution window pre-populated with the parameter values used in that run. This makes it easy to repeat or adjust a previous execution without starting from scratch.

## Executing the Task

Once the task runner has reviewed and set any editable parameters, click **Execute Task** to run. The execution uses the current parameter values for this run only — the saved task is not affected.

 [![Previous](/articles/images/Previous.png)](21_task_advanced_settings.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](26_task_execution.md)
