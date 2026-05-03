# Create/Assign Pre/Post Execution Flows (Optional)

Custom Broadway flows can run before or after the core verification logic without modifying the engine itself.

1. **Create the Broadway flow** in your Cloud Studio.
2. **Tag the flow** in Flow Properties:
   - `verify_pre_execution` — appears in the Pre-Execution Flows selector.
   - `verify_post_execution` — appears in the Post-Execution Flows selector.
3. **Define External inputs** in the flow:

   **Available to both Pre and Post flows:**

   | Input | Description |
   |:---|:---|
   | `task_id` | ID of the Verify task |
   | `execution_id` | ID of the current execution |
   | `task_properties` | General task properties |
   | `table_properties` | Properties of tables included in the task |
   | `IsRetryMode` | Whether this is a retry execution |

   **Post-Execution flows only:**

   | Input | Description |
   |:---|:---|
   | `table_status_details` | List of executed tables and their execution status |

4. **Assign the flow** when creating or editing a task via the Pre/Post-Execution Flows dropdowns.

**Behavior:**
- ❌ A failing **Pre-Execution** flow prevents the task from starting.
- ✅ A failing **Post-Execution** flow does **not** prevent the report from being generated.