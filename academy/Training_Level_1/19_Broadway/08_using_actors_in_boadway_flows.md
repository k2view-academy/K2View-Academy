# Using Actors in Broadway Flows

Now you have seen how to create and run a Broadway flow, let's learn more about the Actors and how they can be used in various scenarios.

### What Will You Experience In This Learning Item?

By the end of this learning item you will:

- Know which Actor's data types exist.
- Learn how to edit the input and output arguments and add the remarks.
- Learn about different link types between the Actors.

### Actor Overview

A Broadway **Actor** represents an action that is executed on a [Stage](/articles/19_Broadway/19_broadway_flow_stages.md) of the [Broadway flow](/articles/19_Broadway/02a_broadway_flow_overview.md) to get input parameters and return output parameters. To learn more about Broadway Actors, their properties, and how to link them in a Broadway flow, please refer to:

* [Actor Overview](/articles/19_Broadway/03_broadway_actor.md)
* [Actor's Properties WIndow](/articles/19_Broadway/03_broadway_actor_window.md)
* [Data Types in Broadway](/articles/19_Broadway/05_data_types.md)


### ![](/academy/images/example.png)Example - Set and Update the Links Between Actors, Remarks and Population Type

Use a flow you created in a previous exercise to try various ways of connecting two Actors in the flow. 

1. Add two Actors **A1** and **B1** to two different Stages of the flow.

   ![info](images/information.png)To learn more refer to [How Do I Edit Links in the Flow](/articles/19_Broadway/07_broadway_flow_linking_actors.md#how-do-i-edit-links-in-the-flow).

2. Draw a link between them. Verify that the connection's **Link Type** = **Value** by default.

   ![info](images/information.png) To learn more about Link Types refer to [Linking Actors](/articles/19_Broadway/07_broadway_flow_linking_actors.md).

![image](images/08_link_type_1.PNG)

3. Click the connection line to set Varargs to OFF and verify the impact on the flow.

![image](images/08_link_type_vararg.PNG)

4. Then change its Link Type to **Iterate** / **First** and verify the impact on the flow.

   ![](images/information.png) To learn more about iteration in a Broadway flows refer to [Broadway Iterations](/articles/19_Broadway/21_iterations.md). 

5. Delete the link's connection line.

6. Add a connection using the source Actor's context menu > **Link** option. 

   ![](images/information.png)To learn more refer to [Actor's Context Menu](/articles/19_Broadway/18_broadway_flow_window.md#actor-context-menu).

7. In a flow with many connection lines, use Actor's context menu > **Show only connected** option to display only Actors linked to the selected Actor.

   ![info](images/information.png) To learn more refer to [Show Only Connected](/articles/19_Broadway/08_show_only_connected_actors.md).

8. Add a remark to the Actor via Actor's context menu > **Remark** option.

9. Change the population type of **A1** Actor's input argument from **Const** to **External**, then update the argument's external name.

   ![info](images/information.png) To learn more refer to [Actor's Inputs and Outputs](/articles/19_Broadway/03_broadway_actor_window.md#actors-inputs-and-outputs).

![image](images/08_link_type_external.PNG)

Let's continue to the next item to learn more about the built-in Actors and some frequently used ones.

[![Previous](/articles/images/Previous.png)](07_broadway_flow_add_condition_execise.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](09_frequently_used_actor_types.md)

