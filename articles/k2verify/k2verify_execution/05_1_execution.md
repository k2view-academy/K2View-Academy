# Verify Execution

### Create a New Verify Task

1. Click the hamburger menu (top-left) and select **Verify**.
2. Click **New Task**.

   ![New Task](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/newTask.png)

3. Fill in the task details:

   | Field | Description |
   |:---|:---|
   | Task Title | Descriptive name for the task |
   | Task Description | Short description (optional) |
   | Source Environment | Source environment — `_dev` selected by default |
   | Target Environment | Target environment — `_dev` selected by default |
   | Source Interface | Interfaces deployed in the selected source environment |
   | Target Interface | Interfaces deployed in the selected target environment |
   | Source & Target Table Selection | Choose **From Settings** (predefined configs) or **Add Tables at Runtime** (JDBC or Catalog) |
   | Verify PII Fields Only | Runs verification only on PII fields, skipping regular columns |
   | Source Contains Sensitive Data | Indicates source has unmasked PII — affects comparison logic |
   | Target Contains Sensitive Data | Indicates target has unmasked PII — affects comparison logic |
   | Include Passed Verifications in Report | Adds passed records to the final report alongside failures |
   | Concurrency Threads per Node | Number of concurrent threads per table per node |
   | Pre-Execution Flows | Broadway flows (tagged `verify_pre_execution`) to run before verification |
   | Post-Execution Flows | Broadway flows (tagged `verify_post_execution`) to run after verification |

   ![New Task Screen](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/newTaskScreen.png)
   ![Pre/Post Processes](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/PrePostProcesses.png)

4. Click **Save**.

---

### Execute the Verify Task

1. Click the **Play** (▶) icon next to the task.

   ![Play Task](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/playTask.png)

2. A validation flow runs before execution starts:
   - Verifies source and target interfaces are accessible.
   - Confirms selected tables exist.
   - Validates key fields are present in both source and target.
   - Checks that task configuration is valid.
   
   If any validation fails, an error is displayed before execution begins.

   ![Validation Error](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/validationError.png)

> **Note:** Task execution uses the environments defined in the task, not the active Fabric session environment.