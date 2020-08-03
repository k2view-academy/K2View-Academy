# Inner Flows

### Inner Flow Actor Types

Broadway enables an execution of one Broadway flow within another one. This function can be used when the same logic needs to be executed in several flows. There are three [built-in Actor types](04_built_in_actor_types.md)  that can execute an inner flow:

* **InnerFlow** Actor, executes a Broadway flow. Input and output arguments reflect the inputs and outputs of external arguments to the inner flow.
* **InnerFlowAsync** Actor, executes a Broadway flow asynchronously in a thread pool. When called, the Actor will return immediately once a working thread becomes available. When the flow completes, it will wait for all threads to complete. 
* **InnerFlowJoin** Actor, is used to wait for all pending tasks of an **InnerFlowAsync** Actor to be completed. You need to connect the **remaining** output argument (the number of flow still remaining to be completed) of **InnerFlowAsync** Actor to the **remaining** input argument of **InnerFlowJoin** Actor. The actor will complete once the remaining tasks is 0.

### Save As Actor

Additional way to create an inner flow in Broadway is by using the **Save as Actor** action in the [Main menu](18_broadway_flow_window.md#main-menu) of the Broadway flow window. It saves the current flow as a new Actor, so that its logic can be reused in another Broadway flow. When a new Actor is created, it inherits from **InnerFlow** Actor. 

##### Example of Saving an Actor and Using it in Another Flow

1. Create a flow which incapsulates a specific business logic, for example: given two input numbers, divide a bigger number by a smaller number. 

   ![image](images/99_22_01.PNG)

2. Save the flow as a new Actor. For example, if the flow name is **myFlow**, the new Actor's name will be **myFlow_Actor**.

4. Add a new Actor to another flow.

   ![image](images/99_22_02.PNG)

[![Previous](/articles/images/Previous.png)](21_iterations.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](23_transactions.md)