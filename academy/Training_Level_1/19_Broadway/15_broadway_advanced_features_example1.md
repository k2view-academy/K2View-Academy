# Examples of Broadway Advanced Features

### ![info](/academy/images/example.png)Actor Inheritance

1. Create a new Broadway flow.

2. Add a **Now** Actor to Stage 1 and a **DateFormat** Actor to Stage 2 and connect them as explained in [Linking Actors](/articles/19_Broadway/07_broadway_flow_linking_actors.md). 

3. Run the flow in the Debug mode to see the output date format. 

   ![1](images/15_ex1.PNG)

4. Change the **DateFormat** Actor's settings as follows:

   * Update the **format** input argument to **E dd-M-yy**.
   * Set the **tz** input argument to **Israel**. 

5. Now let's create an inherited Actor by clicking ![dots](images/three_dots_icon.png)> **Export Actor** in the Actor context menu and populated the fields in the pop-up window as follows:

   * Populate the **name** = myDate.
   * Set the **format** = **final** and **tz** = **hidden**.
   * Click **SUBMIT** to complete the action.

6. Run the flow again to verify the updated output and check the **Inputs** in the Actor's properties window:

   * The **format** input argument became read-only and the **tz** input argument doesn't appear in the inputs list.

     ![2](images/15_ex2.PNG)

7. Now the new **myDate** Actor is available in the list of all Actors and can be used in any other Broadway flow in order to set the same date format. 

   * To learn about selecting an Actor from the list of available Actors, refer to [How Do I Add Actor to Stage](articles/19_Broadway/03_broadway_actor.md#how-do-i-add-actor-to-stage).



[![Previous](/articles/images/Previous.png)](14_broadway_advanced_features.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](16_broadway_advanced_features_example2.md)

