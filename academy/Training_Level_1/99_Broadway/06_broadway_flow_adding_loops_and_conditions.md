# Broadway Flow- Adding Loops and Conditions

### Adding Loops

A Broadway flow is built out of Stages. The Stages are executed from left to right. Each of the Stages can contain one or more Actors to implement Logic and Data Transformation. 

Often there is a need to iterate the output of an Actor and handle each Iteration. 

For example: the  [Simple Broadway Flow](/academy/Training_Level_1/99_Broadway/05_create_broadway_flow.md#example---building-a-simple-broadway-flow) that you've built in the previous lesson selects the list of customers from the database and then builds a Json object for each customer, and write it into an output file.

You can add loops either by:

- Add loops on Stages: setting the [Link Type](/articles/99_Broadway/20_broadway_flow_linking_actors.md#link-object-properties)  to **Iterate** when linking one Actor to another opens a loop. The loop runs on the next flow Stages and is closed by the first Stage which is marked as **Iterate Close**.

  Read [Broadway Flow Window](/articles/99_Broadway/18_broadway_flow_window.md) to learn more about the Stage Context Menu.

- Add a loop into the Actor- the Actor itself iterates the data and handles each iteration.

  Read [Broadway Flows Loops] to learn more about adding loops to Broadway flows.

  ### Adding Conditions

  Broadway flow can be split into different execution paths based on conditions. More than one stage can be executed in each fork in the path. If a condition is required in the flow, the flow can be split and a **Stage condition** Actor can be added to one or more Stages that have been created as a result of the split. 

  For example-

  If (A>3) => Do something.

  Else => Do something else.

  Read [Broadway Flow - Stages](/articles/99_Broadway/19_broadway_flow_stages.md) to learn about split or merge of Stages in a flow and about adding conditions to the Stage.  

  Please continue to the next exercise to enhance your first Broadway flow and add a condition to this flow. 

  [![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/99_Broadway/05_create_broadway_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/99_Broadway/06_broadway_flow_add_condition_execise.md)