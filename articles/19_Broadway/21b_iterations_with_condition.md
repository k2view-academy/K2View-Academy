# Iterations With Conditions

This article describes various use cases when a combination of an iteration and conditions is used in a flow. It provides recommendations of how to handle such use cases correctly.

### Split Due to Stage Condition in Iteration

When a flow is split into branches due to conditions and includes an additional split inside the iteration, the split inside the loop is only applicable for the duration of the loop.

The following flow displays an example of a flow of iterations with conditions. If Stage 2 is true, its branch will start and execute Stage 5. Then, either Stage 8 or 11 will be executed starting an additional split. 

Note that after the loop is completed, both Stage 17 and 18 will run since they depend on Stage 2 and not on the conditions inside the loop.

![image](images/iterate_over_branches.PNG)

### Using Actors Outputs of Split Iteration

When a flow has an iteration and is split into branches due to conditions within the iteration, you need to carefully define which output you need to get. 

The following flow displays an example of searching for a match in the input list and returning the iteration index once the match is found. The best practice for this kind of flows is not to define the external output inside the iteration but rather outside. Note that **Result** Actor at the **Return Result** Stage is linked to both IF and ELSE branches. The returned value belongs to the Actor that runs the last, **IF-LoopIndex** in this case.

![image](images/iterate_with_condition1.PNG)

If however you need to get the last index of both IF and ELSE branches (when each of them was true), the flow should be as below. 

![image](images/iterate_with_condition2.PNG)





[![Previous](/articles/images/Previous.png)](21a_complex_iteration_flows.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](22_broadway_flow_inner_flows.md)