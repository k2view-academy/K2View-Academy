# Inner Flows

### Inner Flow Actor Types
A Broadway flow can be executed as part of another Broadway flow. This function can be used when the same logic needs to be executed in several flows or in several Stages of the same flow. 

An inner flow can be run using of the following [built-in Actor types](04_built_in_actor_types.md):

* **InnerFlow** Actor, executes a Broadway flow. Input and output arguments reflect the inputs and outputs of external arguments to and from the inner flow.
* **InnerFlowDynamic** Actor, extends an **InnerFlow** Actor. The Actor can receive a flow name and a map of input arguments with their values **at run-time**. The output is a map of an inner flow's output arguments and their respective values.
* **InnerFlowAsync** Actor, executes a Broadway flow asynchronously in a thread pool. When called, the **InnerFlowAsync** Actor returns immediately once a working thread becomes available. When the execution of the attached flow is completed, the **InnerFlowAsync** Actor waits for all threads to be completed. It then returns non-empty results obtained from completed flows. Empty results are not saved.
* **InnerFlowJoin** Actor, waits for all pending tasks of an **InnerFlowAsync** Actor to be completed. The **remaining** output argument (number of flows remaining to be completed) of the **InnerFlowAsync** Actor must be linked to the **remaining** input argument of the **InnerFlowJoin** Actor. The execution is completed once the number of remaining tasks is 0.

When running a flow with inner flows, they can also be debugged. You can debug the inner flow either by supplying the debug arguments or by running the outer flow while opening an inner flow in a separate tab and setting its break points. The flow’s execution stops when it reaches the inner flow's break points.

[Click here for more information about debugging Broadway flows](25_broadway_flow_window_run_and_debug_flow.md).

### Save As Actor

An inner Broadway flow can also be created using the **Save as Actor** action in the [Main menu](18_broadway_flow_window.md#main-menu) of the Broadway flow window. This method saves the current flow as a new Actor whereby its logic can be reused in another Broadway flow. When a new Actor is created, it inherits from the **InnerFlow** Actor. 

**Example of Saving an Actor and Using it in Another Flow**

1. Create a flow that encapsulates a specific business logic. For example, given two input numbers, divide a bigger number by a smaller number. 

   <img src="images/99_22_01.PNG" alt="image"  />

2. Save the flow as a new Actor, providing a new Actor's name. For example, **CheckMaxAndDivide**.

4. Add the new Actor to another flow.

   <img src="images/99_22_02.PNG" alt="image"  />

Full example of a Broadway flow with inner flow Actors can be found in the Demo project.

### Attaching an Inner Flow

When you need to execute an inner flow, add an **InnerFlow** Actor to your flow and either write the flow name (with or without the **.flow** extension) or click to open the selection popup:

<img src="images/99_22_04.PNG" alt="image" style="zoom:80%;" />

<img src="images/99_22_05.PNG" alt="image" style="zoom:80%;" />

Once the flow is selected, its inputs and outputs are automatically added to the Actor.

### Attaching an Actor as Inner Flow

Broadway supports an ability to invoke an Actor as an inner flow. This ability may be required in a use case such as masking, whereas a different Masking Actor should be invoked, based on the flow conditions. 

To attach an Actor, add an **InnerFlow** Actor to your flow and either write the actor name (including the **.actor** extension) or click to open the selection popup. Note that you will need to choose Actor radio button to get the Actor's list.

### Opening an Inner Flow

When a flow has inner flows, they can each be opened from the main flow:

1. Click the **InnerFlow** Actor to display the **Open** button in the Actor properties menu next to the inner flow's name.
2. Click **Open** to open the inner flow.

![image](images/99_22_03.PNG)





[![Previous](/articles/images/Previous.png)](21b_iterations_with_condition.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](23_transactions.md)
