# Actor Editor

### Overview

Broadway's [Actors inheritance mechanism](06_export_actor.md) provides an ability to reuse the same business logic in different places in the flow or across several flows of the project. 

If modification of an inherited Actor is needed, you can either manually edit the JSON of the ***.actor** file, or open the Broadway flow that includes this Actor, edit it and save it using ![dots](images/99_19_dots.PNG)> **Export Actor** and selecting the **Override Current** option. 

To simplify the Actor modification process and enhance it with additional options, Broadway introduces the **Actor Editor** which enables editing the inherited Actors as well as creating new Actors.  

The Actor Editor can be accessed from the Project tree and it is presented as a dummy flow with only one Actor and various editing options using the Actor's [Properties window](03_broadway_actor_window.md).

### How Do I Use an Actor Editor?

The Actor Editor can be used to modify the ***.actor** file of either an inherited Actor or an [Inner Flow](22_broadway_flow_inner_flows.md) saved as Actor. Once the Actor is modified, the change becomes effective in every flow where this Actor is used.  

**To create** a new Actor, do the following:

1. Right click on the  **Broadway** folder in the Project tree and click **New Actor**.
2. Populate the new Actor's name and click OK to open the Actor Editor.
3. Select the Parent Actor to get the parent's input and output arguments. 
4. Debug the Actor by running it from the Main menu toolbar. If the Actor receives input parameters, they can be set using the [Debug / Run Arguments](25_broadway_flow_window_run_and_debug_flow.md).
5. Save the Actor.

**To modify** an Actor, do the following:

1. Go to the **Broadway** folder in the project tree and double click on the ***.actor** file to open the Actor Editor.

2. Modify the required settings, for example set a value of an input argument.
3. Debug the Actor by running it from the Main menu toolbar. If the Actor receives input parameters, they can be set using the [Debug / Run Arguments](25_broadway_flow_window_run_and_debug_flow.md). 
4. Save the Actor.

#### Example of New Actor Creation

Let's create a new **CheckMaxVal** Actor that inherits from **JavaScript** Actor, receives two input arguments and returns the maximum value.

1.  Right click on the **Broadway** folder in the Project tree and click **New Actor**.

2. Populate the **CheckMaxVal** Actor's name and click OK.

3. Define the Actor as follows:

   * Set **Max** as Badge.

   * Click![dots](images/99_19_dots.PNG)to select  the **Parent** from the Actor's list.

   * Populate the **script** input argument with the regex: **a > b ? a : b**.

   * Add two input arguments and update their names to **a** and **b**.

     ![image](images/99_10_editor_1.PNG)

4. Set the Actor's input parameters using the [Debug / Run Arguments](25_broadway_flow_window_run_and_debug_flow.md) the Main menu toolbar and debug the Actor.

[![Previous](/articles/images/Previous.png)](27_broadway_data_inspection.md)