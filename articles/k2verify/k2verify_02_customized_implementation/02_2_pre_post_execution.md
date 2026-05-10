# Create/Assign Pre/Post Execution Flows (Optional)

Custom Broadway flows can run before or after the core verification logic without modifying the engine itself.

1. **Create the Broadway flow** in your Cloud Studio.
2. **Tag the flow** in Flow Properties:
   - `verify_pre_execution` — appears in the Pre-Execution Flows selector.
   - `verify_post_execution` — appears in the Post-Execution Flows selector.
3. **Define External inputs** in the flow:

   **Available to both Pre and Post flows:**

	<table>
	  <thead>
		<tr>
		  <th align="left">Input</th>
		  <th align="left">Description</th>
		</tr>
	  </thead>
	  <tbody>
		<tr>
		  <td valign="top"><code>task_id</code></td>
		  <td valign="top">ID of the Verify task</td>
		</tr>
		<tr>
		  <td valign="top"><code>execution_id</code></td>
		  <td valign="top">ID of the current execution</td>
		</tr>
		<tr>
		  <td valign="top"><code>task_properties</code></td>
		  <td valign="top">General task properties</td>
		</tr>
		<tr>
		  <td valign="top"><code>table_properties</code></td>
		  <td valign="top">Properties of tables included in the task</td>
		</tr>
		<tr>
		  <td valign="top"><code>IsRetryMode</code></td>
		  <td valign="top">Whether this is a retry execution</td>
		</tr>
	  </tbody>
	</table>

   **Post-Execution flows only:**

	<table>
	  <thead>
		<tr>
		  <th align="left">Input</th>
		  <th align="left">Description</th>
		</tr>
	  </thead>
	  <tbody>
		<tr>
		  <td valign="top"><code>table_status_details</code></td>
		  <td valign="top">
			List of executed tables and their execution status
		  </td>
		</tr>
	  </tbody>
	</table>

4. **Assign the flow** when creating or editing a task via the Pre/Post-Execution Flows dropdowns.

**Behavior:**
- ❌ A failing **Pre-Execution** flow prevents the task from starting.
- ✅ A failing **Post-Execution** flow does **not** prevent the report from being generated.