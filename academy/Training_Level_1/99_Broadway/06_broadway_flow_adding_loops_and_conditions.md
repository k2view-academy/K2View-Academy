# Broadway Flow - Adding Loops and Conditions

### Adding Loops

A Broadway flow is built in Stages which are executed from left to right. Each Stage can contain one or more Actors that implement logic and data transformation. 

Occasionally there may be a need to iterate the output of an Actor and to handle each iteration. 

For example, the  [Simple Broadway Flow](/academy/Training_Level_1/99_Broadway/05_create_broadway_flow.md#example---building-a-simple-broadway-flow) that you built in the previous lesson selects a list of customers from the database and then builds a JSON object for each customer and writes it into an output file.

You can add loops either by:

- Adding loops on Stages: setting the [Link Type](/articles/99_Broadway/20_broadway_flow_linking_actors.md#link-object-properties)  to **Iterate** when linking an Actor to another Actor opens a loop. The loop runs on the next Stages in the flow and is closed by the first Stage which is marked as **Iterate Close**.

  Read [Broadway Flow Window](/articles/19_Broadway/18_broadway_flow_window.md) to learn more about the Stage context menu.

- Adding a loop to the Actor to iterate the data and handle each iteration.

  Read [Broadway Iterations](/articles/19_Broadway/21_iterations.md) to learn more about adding loops to Broadway flows.

### Adding Conditions

A Broadway flow can be split into different execution paths based on conditions. More than one Stage can be executed in each fork in the path. If a condition is required in the flow, the flow can be split and a **Stage condition** Actor can be added to one or more Stages that have been created as a result of the split. 

  For example:

  - If (A>3) => Do something.

  - Else => Do something else.

  Read [Broadway Flow - Stages](/articles/19_Broadway/19_broadway_flow_stages.md) to learn about spliting or merging the Stages of a flow and about adding conditions to a Stage.  

  Please continue to the next exercise to enhance your first Broadway flow and add a condition to it. 

  [![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/99_Broadway/05_create_broadway_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/99_Broadway/07_broadway_flow_add_condition_execise.md)
