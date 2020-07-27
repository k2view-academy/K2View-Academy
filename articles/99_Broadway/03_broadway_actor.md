# Broadway Actor

## Actor Overview

A Broadway  **Actor** represents the action that must be executed on each [Stage](/articles/99_Broadway/19_broadway_flow_stages.md) of the [Broadway flow](/articles/99_Broadway/16_broadway_flow_overview.md) to get input parameters and return output parameters. For example, reading a file, creating a table, parsing an object or concatenating a string.

Broadway offers a number of [built-in Actors](/articles/99_Broadway/04_built_in_actor_types.md) which address a wide range of predefined activities and can be added to Broadway flows. When an Actor should execute a business logic that is not supported by any of the existing built-in Actors, one of the options is to use a **JavaScript** Actor, that executes the Javascript provided in the **script** [Input parameter](/articles/99_Broadway/03_broadway_actor_window.md#data-input-parameters). 

The options for working with Actors include:
- [Adding Actors to Stages](/articles/99_Broadway/03_broadway_actor.md#how-do-i-add-actor-to-stage) of Broadway flow.
- [Saving a flow as an Actor](/articles/99_Broadway/03_broadway_actor.md#how-do-i-save-flow-as-actor) to be used as inner flow of other Broadway flows.
- [Exporting Actors](/articles/99_Broadway/03_broadway_actor.md#how-do-i-export-an-actor) to create inherited Actors with extended business logic.  

### How Do I Add Actor to Stage?

To add an Actor to Stage of the Broadway flow, click anywhere in the **Stage** area to open the **Add Actors to [Stage Name]** window. The window displays the list of existing categories on the left side, and the existing built-in Actors per each categories on the right side. Click **SUBMIT** to finish the action.

![image](/articles/99_Broadway/images/99_04_01_add_actor.PNG)

### How Do I Save Flow as Actor?

In some cases you may prefer to package a flow and use it as an inner flow to avoid complex flows with many steps. This will enable a reuse of the business logic across different flows.

For example:
- Define an inner flow for error handling.

To save flow as Actor:
- Click **Actions** > Save as Actor in the Main menu of [Broadway flow window](/articles/99_Broadway/18_broadway_flow_window.md#main-menu).

The newly created Actor type can be added to any flow.

[Click for more information about inner flows.](add a link). 

### How Do I Export an Actor?

You can export an Actor object and create an Actor Type, based on this object, to reuse this Actor. The export activity creates a new Actor type which inherits the current Actor. The Actor is then added to the list of built-in Actors and can be used in other flows.

To export an Actor:
- Click **Export Actor** in [Actor's context menu](/articles/99_Broadway/18_broadway_flow_window.md#actors-context-menu).

[Click for more information about Export Actor and Actor Inheritance.](add a link) 

## Actor Description and Remark

Actor description is a read-only description which is coming from the Product. The remark is a user comment added during the flow implementation.
Note that when exporting an Actor, the Actor's remarks are copied to the description of the new Actor type.

Click ![image](/articles/99_Broadway/images/99_19_dots.PNG) in the right corner of the Actor to open the [Actor's context menu](/articles/99_Broadway/18_broadway_flow_window.md#actors-context-menu) and select **Description** to view the description of the Actor type. The Description window is displayed in a read-only mode. See the example below:

![image](/articles/99_Broadway/images/99_03_actor_desc.PNG)

To add your own remark to the Actor, select **Remark** from the [Actor's context menu](/articles/99_Broadway/18_broadway_flow_window.md#actors-context-menu).

![image](/articles/99_Broadway/images/99_03_actor_remark.PNG)


[![Previous](/articles/images/Previous.png)](/articles/99_Broadway/02_broadway_high_level_components.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_Broadway/03_broadway_actor_window.md)
