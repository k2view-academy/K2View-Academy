# Verify Execution

### Create a New Verify Task

1. Click the hamburger menu (top-left) and select **Verify**.
2. Click **New Task**.

   ![New Task](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/newTask.png)

3. Fill in the task details:

	<table>
	  <thead>
		<tr>
		  <th align="left">Field</th>
		  <th align="left">Description</th>
		</tr>
	  </thead>
	  <tbody>
		<tr>
		  <td valign="top">Task Title</td>
		  <td valign="top">Descriptive name for the task</td>
		</tr>
		<tr>
		  <td valign="top">Task Description</td>
		  <td valign="top">Short description (optional)</td>
		</tr>
		<tr>
		  <td valign="top">Source Environment</td>
		  <td valign="top">
			Source environment — <code>_dev</code> selected by default
		  </td>
		</tr>
		<tr>
		  <td valign="top">Target Environment</td>
		  <td valign="top">
			Target environment — <code>_dev</code> selected by default
		  </td>
		</tr>
		<tr>
		  <td valign="top">Source Interface</td>
		  <td valign="top">
			Interfaces deployed in the selected source environment
		  </td>
		</tr>
		<tr>
		  <td valign="top">Target Interface</td>
		  <td valign="top">
			Interfaces deployed in the selected target environment
		  </td>
		</tr>
		<tr>
		  <td valign="top">Source &amp; Target Table Selection</td>
		  <td valign="top">
			Choose <strong>From Settings</strong> (predefined configs) or
			<strong>Add Tables at Runtime</strong> (JDBC or Catalog)
		  </td>
		</tr>
		<tr>
		  <td valign="top">Verify PII Fields Only</td>
		  <td valign="top">
			Runs verification only on PII fields, skipping regular columns
		  </td>
		</tr>
		<tr>
		  <td valign="top">Source Contains Sensitive Data</td>
		  <td valign="top">
			Indicates source has unmasked PII — affects comparison logic
		  </td>
		</tr>
		<tr>
		  <td valign="top">Target Contains Sensitive Data</td>
		  <td valign="top">
			Indicates target has unmasked PII — affects comparison logic
		  </td>
		</tr>
		<tr>
		  <td valign="top">Include Passed Verifications in Report</td>
		  <td valign="top">
			Adds passed records to the final report alongside failures
		  </td>
		</tr>
		<tr>
		  <td valign="top">Concurrency Threads per Node</td>
		  <td valign="top">
			Number of concurrent threads per table per node
		  </td>
		</tr>
		<tr>
		  <td valign="top">Pre-Execution Flows</td>
		  <td valign="top">
			Broadway flows (tagged <code>verify_pre_execution</code>)
			to run before verification
		  </td>
		</tr>
		<tr>
		  <td valign="top">Post-Execution Flows</td>
		  <td valign="top">
			Broadway flows (tagged <code>verify_post_execution</code>)
			to run after verification
		  </td>
		</tr>
	  </tbody>
	</table>

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