# Iterations and Conditions in a Flow

### Using Iterations

A Broadway flow is built in Stages which are executed from left to right. Each Stage can contain one or more Actors that implement logic and data transformation. 

Occasionally there may be a need to iterate the output of an Actor and to handle each iteration. 

For example, the  [Simple Broadway Flow](/05_create_broadway_flow.md#example---building-a-simple-broadway-flow) that you built in the previous lesson selects a list of customers from the database and then builds a CSV string for each customer and writes it into an output file.

You can add iterations to the flow either by:

- Adding Iterations on Stages: setting the [Link Type](/articles/19_Broadway/07_broadway_flow_linking_actors.md#link-object-properties)  to **Iterate** when linking an Actor to another Actor opens a loop. The loop runs on the next Stages in the flow and is closed by the first Stage which is marked as **Iterate Close**.

  Read [Broadway Iterations](/articles/19_Broadway/21_iterations.md) to learn more about the iteration creation and handling in a flow.

- Adding a [ForLoop Actor](/articles/19_Broadway/21_iterations.md#forloop-actor) to the flow.

- Iterating a data by the code in a [JavaScript Actor](/articles/19_Broadway/actors/01_javascript_actor.md).

To learn about advanced iteration options, read [Complex Iteration Flows](/articles/19_Broadway/21a_complex_iteration_flows.md).

### Adding Conditions

A Broadway flow can be split into different execution paths based on conditions. More than one Stage can be executed in each fork in the path. If a condition is required in the flow, the flow can be split and a **Stage condition** Actor can be added to one or more Stages that have been created as a result of the split. 

For example:

  - If (A>3) => Do something.
  - If (B<1) => Do something.
  - Else => Do something else.

To learn about splitting or merging the Stages of a flow and adding Stage conditions, read [Broadway Flow - Stages](/articles/19_Broadway/19_broadway_flow_stages.md).

Continue to the exercise to enhance your first Broadway flow and add a condition to it. 

[![Previous](/articles/images/Previous.png)](05a_create_broadway_flow_example.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](07_broadway_flow_add_condition_exercise.md)