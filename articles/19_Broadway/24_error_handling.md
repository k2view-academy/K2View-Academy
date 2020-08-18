#  Error Handling
 
In Broadway, the Error Handling mechanism can be used to assign an **Error Handler** to any [Stage](19_broadway_flow_stages.md) of a flow. 

Any [Actor](03_broadway_actor.md) can act as an error handler whereby the Actor's logic is validated by the error handler. If the selected error handler is a [**JavaScript** Actor](actors/01_javascript_actor.md), custom logic can be included in the **script's** input parameter.

- When an error handler returns true, the flow continues.

- When an error handler returns false, the flow stops.

Errors can also be handled using an [Inner flow](22_broadway_flow_inner_flows.md) functionality. This is recommended when the same error handler validation is required in several flows.

### How Do I Add an Error Handler to a Stage?

Click ![image](images/99_19_dots.PNG) in the right corner of the Stage to open the [Stage context menu](18_broadway_flow_window.md#stage-context-menu) and select **Error Handler** to [add an Actor to the Stage](03_broadway_actor.md#how-do-i-add-actor-to-stage). The added Actor has a red background to indicate that it is an error handler.

![image](images/99_24_01.PNG)

**Example of an Error Handler in a Flow** 

1. Create a flow with the required business logic. For example: given two input numbers, divide a bigger number by a smaller number. 

2. Before the division in Stage 4, check that the smaller number does not equal zero using an error handler named **Validation** in Stage 3 of the flow. 

   ![image](images/99_24_02.PNG)

3. If the smaller number equals zero, throw an error using the **ErrorMsg** **JavaScript** Actor. Stage 4 is not executed since the error handler stops the flow's execution. 

   ![image](images/99_24_03.PNG)



**Example of Handling an Error Using an Inner Flow**

1. Create a simple flow that performs a validation and throws an error. For example, check that the input number is not zero and if it is - throw an exception. 

   - The validation is implemented using a **JavaScript** Actor set as **Error Handler** which includes the validation check: *a != 0*.
   - The exception is implemented using a **JavaScript** Actor which throws an exception if the Error Handler returns false: *throw "Can't divide by zero!"*.

   ![image](images/99_24_04.PNG)

2. Save the flow and then [save the flow as an Actor](22_broadway_flow_inner_flows.md#save-as-actor). The name of the flow is **CheckZeroDiv** and the name of the new Actor is **CheckZeroDiv_Actor**.
 
3. Create another flow that requires validation of a zero division and name it **myFlow** and then add a new **CheckZeroDiv_Actor** to it as an inner flow. If during the flow's execution the error occurs (min number = 0), the exception is thrown and the flow stops.

   ![image](images/99_24_05.PNG)

   The exception displays the message that helps to identify the flow and the Stage where the error occurred. If the error occurred in the inner flow, it also displays the name and the Stage of the inner flow.

   In the example below, the following information is displayed in the error message:

   **Flow**: myFlow **Level**: 3 Stage: **Stage 3 Actor**: CheckZeroDiv_Actor1  

   **Cause**:  InnerFlowException: **Flow**: CheckZeroDiv **Level**: 1 Stage: **Stage 1 Actor**: ErrorMsg  class jdk.nashorn.internal.runtime.ECMAException **Cause**: Can't divide by zero!

   <img src="images/99_24_06.png"/>

4. Several validations can be performed using different inner flows. For example, add a validation that input numbers are not negative and if yes - throw an exception and stop the flow. This check is also implemented by creating another flow, saving it as an Actor and adding it to the current flow.

   <img src="images/99_24_07.PNG"/>

   

[![Previous](/articles/images/Previous.png)](23_transactions.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](25_broadway_flow_window_run_and_debug_flow.md)

