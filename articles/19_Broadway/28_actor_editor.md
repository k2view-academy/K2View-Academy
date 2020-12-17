# Actor Editor

### Overview

The Broadway [Actors inheritance mechanism](06_export_actor.md) enables reusing the same business logic in different places in a flow or in several flows of the project. 

If an inherited Actor needs to be modified, open the Broadway flow with this Actor, edit it and save the updated Actor via the **Export Actor** menu with the **Override Current** option. 

To simplify the Actor modification process and enhance it with additional editing options, Broadway uses an **Actor Editor** which enables creating new Actors and editing inherited ones.  

The Actor Editor can be accessed by opening the ***.actor** file from the Project tree. It is displayed as a dummy flow with one Actor that can be setup / modified using the extended Actor's [Properties window](03_broadway_actor_window.md).

### How Do I Use an Actor Editor?

The Actor Editor can be used to create a new inherited Actor or modify an existing one via its ***.actor** file. The Actor must inherit either from another Actor or from an [Inner Flow](22_broadway_flow_inner_flows.md) that has been saved as an Actor. 

**To create** a new Actor, do the following:

1. Right click the **Broadway** folder in the Project tree and click **New Actor**.
2. Populate the new **Actor's name** and click **OK** to open the Actor Editor.
3. Select the **Parent Actor** to get the parent's input and output arguments. 
4. Debug the Actor by running it from the Main menu toolbar. If the Actor receives input parameters, they can be set using the [Debug / Run Arguments](25_broadway_flow_window_run_and_debug_flow.md).
5. Save the Actor.

**To modify** an Actor, do the following:

1. Go to the **Broadway** folder in the project tree and double click the ***.actor** file to open the Actor Editor.

2. Modify the required settings, for example set a value of an input argument.
3. Debug the Actor by running it from the Main menu toolbar. If the Actor receives input parameters, they can be set using the [Debug / Run Arguments](25_broadway_flow_window_run_and_debug_flow.md). 
4. Save the Actor. The change is implemented in every flow where this Actor is used.  

#### Example of New Actor Creation

Let's create a new **CheckMaxVal** Actor that inherits from a **JavaScript** Actor, receives two integers and returns their maximum value. Do the following:

1.  Right click the **Broadway** folder in the Project tree and click **New Actor**.

2. Populate the **CheckMaxVal** Actor's name and click **OK**.

3. Define the Actor as follows:

   * Set Badge to **Max**.

   * Click![dots](images/99_19_dots.PNG)in the **Parent** field to select the parent using the **Add Actors** window.

   * Populate the **script** input argument with the regex: **a > b ? a : b**.

   * Click **Add Input** to add two input arguments and update their names to **a** and **b**.

     ![image](images/99_10_editor_1.PNG)

4. Set the values of an Actor's input parameters using the [Debug / Run Arguments](25_broadway_flow_window_run_and_debug_flow.md) in the Main menu toolbar and debug the Actor.

5. Once the debug has finished, close the Actor Editor and add the Actor to the required flow.

[![Previous](/articles/images/Previous.png)](27_broadway_data_inspection.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](29_recovery_point.md)
