# Verify Execution

#### Open the Verify in the Web Framework
In your Cloud Studio, Click menu at the top left of the screen (hamburger icon) and select **Verify** to open the page.

#### Create a new Verify task
Follow the steps below to create a new verification task using the Verify web interface:
1. Click on **New Task**
2. Provide the task details:
    * Task Title – A descriptive title for the task.
    * Task Description – A short description of the task (optional).
    * Source Interface – Select a source interface from the dropdown list.
        * The list of available source interfaces is populated from the predefined configuration MTable.
    * Target Interface – Select a target interface from the dropdown list.
        * The available target interfaces correspond to the selected source interface.
    * Source Schema – Select a source schema from the dropdown list.
        * The available schemas correspond to the selected source and target interfaces.
    * Target Schema – Select a target schema from the dropdown list.
        * The available schemas correspond to the selections made in the previous steps.
    * Source & Target Table Selection – Select one or more source–target table pairs to be included in this task.
        * To verify multiple tables in a single execution, select the relevant tables from the list.
        * The available source & target tables pair correspond to the selections made in the previous steps.
    * Verify PII Fields Only – Enable this option to execute verification only on PII fields, excluding regular (non-PII) columns.
    * Include Passed Verifications in Report – Enable this option to include both `passed` and `failed` verifications in the final report output.
    * Concurrency threads per node - Defines the number of concurrent threads executed per table on each node. This setting can be tuned to improve performance through parallel processing or to limit resource usage when needed.
3. Click on **Save**.

![Extensions](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/newTask.png)


![Extensions](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/newTaskScreen.png)

#### Execute the Verify task

Follow the steps below to execute the verification task using the Verify web interface.
**Steps**:

1. Click on the **Play** icon.
![Extensions](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/playTask.png)

2. Before the task initiation, a validation flow is executed to validate the inputs provided in the task:
    * Verifies that source and target interfaces are accessible.
    * Confirms that the selected tables exist.
    * Validates that key fields are present in both source and target tables.
    * Checks that the task configuration is valid.
    * In case of error you will be prompted with the relevant error on the screen.
    ![Extensions](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/validationError.png)
