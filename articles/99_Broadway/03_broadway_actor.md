# Broadway Actor

## Actor Overview

A Broadway  **Actor** represents the action that must be executed on each [Stage](/articles/99_Broadway/19_broadway_flow_stages.md) of the [Broadway flow](/articles/99_Broadway/16_broadway_flow_overview.md) to get input parameters and return output parameters. For example, reading a file, creating a table, parsing an object or concatenating a string.

Broadway offers a number of [built-in Actors](/articles/99_Broadway/04_built_in_actor_types.md) which address a wide range of activities and can be added to Broadway flows.

The options for working with Actors include:
- Adding Actors to Stages of Broadway flow.
- [Saving a flow as an Actor](/articles/99_Broadway/03_broadway_actor.md#how-do-i-save-flow-as-actor) to be used as inner flow of other Broadway flows.
- [Exporting Actors](/articles/99_Broadway/03_broadway_actor.md#how-do-i-export-an-actor) to create inherited Actors.  

#### How Do I Save Flow as Actor?

In some cases you may prefer to package a flow and use it as an inner flow to avoid complex flows with a huge number of step. This will enable a reuse of the business logic in different flows.

For example:
- Define an inner flow for error handling.

To save flow as Actor:
- Click **Actions** > Save as Actor in the Main menu of [Broadway flow window](/articles/99_Broadway/18_broadway_flow_window.md#main-menu).

The newly created Actor Type can be added to any flow.

[Click for more information about inner flows.](add a link). 

#### How Do I Export an Actor?

You can export an Actor object and create an Actor Type, based on this object, to reuse this Actor. The export activity creates a new Actor Type which inherits the current Actor. The Actor is then added to the list of built-in Actors and can be used in other flows.

To export an Actor:
- Click **Export Actor** in [Actor's context menu](/articles/99_Broadway/18_broadway_flow_window.md#actors-context-menu).

[Click for more information about Export Actor and Actor Inheritance.](add a link) 

## Actor Description and Remark

Click ![image](/articles/99_Broadway/images/99_19_dots.PNG) in the right corner of the Actor to open the [Actor's context menu](/articles/99_Broadway/18_broadway_flow_window.md#actors-context-menu) and select **Description** to view the description of the Actor Type. The Description window is displayed in a read-only mode. See the example below:

![image](/articles/99_Broadway/images/99_03_actor_desc.PNG)

To add your own remark to the Actor, select **Remark** from the [Actor's context menu](/articles/99_Broadway/18_broadway_flow_window.md#actors-context-menu).

![image](/articles/99_Broadway/images/99_03_actor_remark.PNG)

Note that if you export an Actor and create an Actor Type based on it, the Actor's remarks are copied to the description of the new Actor Type.

[![Previous](/articles/images/Previous.png)](articles/99_Broadway/02_broadway_high_level_components.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_Broadway/03_broadway_actor_window.md)
