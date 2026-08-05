# Data Generation

### Overview

Two things happen at this step: 

* The data is generated based on the **user story** - built by the [Dataplease AI Agent](../dataplease_assistant/01_dataplease_assistant_overview.md) from the user's input, as described in [Selecting the Datasets](04_selecting_the_datasets.md).
* The generated data is saved into the Fabric DB, with its progress tracked via the monitor shown below:

<img src="../images/dataplease_generation_progress.jpg" style="zoom:75%;" />

The main area lists each table's status, duration and row count, while the side panel summarizes the overall job: execution status, start time, total datasets and rows, and duration.

### Reviewing the Results

Once generation completes, an execution summary is shown, including the success rate and any failed tables:

<img src="../images/dataplease_generation_completed.jpg" style="zoom:75%;" />

From here, the user can:

* **Retry failed** - re-run generation for tables that failed.
* **Preview data** - inspect the generated data before provisioning, as described in [Data Preview](06_data_preview.md).
* **Provision data** - skip the preview and provision the generated data directly, as described in [Provisioning](07_provisioning.md).
