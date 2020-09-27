# Example of Using Actors in Flows


### ![](/academy/images/example.png)Setting and Updating Links Between Actors, Remarks and Population Types

1. Create a new Broadway flow as explained in the [Building a Simple Broadway Flow](05a_create_broadway_flow_example.md#example---building-a-simple-broadway-flow) example. 

2. Add Actors **A1** and **B1** to two different Stages of the flow and draw a link between the Stages as explained in [Linking Actors](/articles/19_Broadway/07_broadway_flow_linking_actors.md). Verify that the connection's **Link Type** = **Value** (default). 

![image](images/08_link_type_1.PNG)

3. Click the connection line to set the Varargs to OFF and check the impact on the flow.

![image](images/08_link_type_vararg.PNG)

4. Change the Link Type to **Iterate** / **First** and check the impact on the flow. For more information about handling loops in Broadway, refer to [Broadway Iterations](/articles/19_Broadway/21_iterations.md). 
5. Delete the link's connection line.  
6. To add a connection, click ![image](images/three_dots_icon.png) in the source Actor's context menu > **Link**. For more information, refer to the [Actor Context Menu](/articles/19_Broadway/18_broadway_flow_window.md#actor-context-menu).
7. In a flow with many connection lines, click ![image](images/three_dots_icon.png) in the source Actor's context menu > **Show only connected** to display only the Actors linked to the selected Actor. For more information, refer to [Show Only Connected](/articles/19_Broadway/08_show_only_connected_actors.md).
8. Click ![image](images/three_dots_icon.png) in the Actor's context menu > **Remark** and add a remark.
9. Click ![image](images/green_asterisk.PNG) in the Actor's right corner to open the Remark pop-up, remove the text and close it to delete the remark.

10. Change the population type of **A1** Actor's input argument  from **Const** to **External** and then update the argument's external name. For more information about Actor's population types, refer to [Actor Inputs and Outputs](/articles/19_Broadway/03_broadway_actor_window.md#actors-inputs-and-outputs).

![image](images/08_link_type_external.PNG)

Let's continue to the next item and learn about frequently used built-in Actors.

[![Previous](/articles/images/Previous.png)](08_using_actors_in_boadway_flows.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](09_frequently_used_actor_types.md)

