# Broadway Flow - Stages

**A Broadway flow** acts as a graph (or tree) which is built out of several **Stages** - steps in the flow. 
- Each Stage can have one or more [Actors](/articles/99_Broadway/03_broadway_actor.md). 
- The Stages can be [added or deleted](/articles/99_Broadway/19_broadway_flow_stages.md#how-do-i-add-or-delete-a-stage) from the flow.
- The Stage can be split which creates [more than one Stage on the same level](/articles/99_Broadway/19_broadway_flow_stages.md#how-do-i-split-or-merge-the-stages). 
- [Stage conditions](/articles/99_Broadway/19_broadway_flow_stages.md#what-is-a-stage-condition) can be added to the flow, to create various schenarios. It creates different branches of the same flow. 

The Stages in the flow are executed one by one from left to right. In case of several Stages on the same level, the execution order is top down. The Actors within each Stage are also executed top down. 

### How Do I Add or Delete a Stage?

A Stage can be added anyway in the flow.

- To add a Stage to the end of the flow, click on big plus sign in the empty Stage area.
- To add a Stage in the middle of the flow, open the [Stage context menu](/articles/99_Broadway/18_broadway_flow_window.md#stage-context-menu) by clicking the three dots in the right corner of the Stage and select either **Insert After** or **Insert Before**. 
- To delete a Stage, select **Delete** from the [Stage context menu](/articles/99_Broadway/18_broadway_flow_window.md#stage-context-menu). If the Stage has a dependent branch, it will be deleted as well.

### How Do I Split or Merge the Stages?

Each Stage can be split into two (and so on), so there can be several stages on the same dependency level. 

To split a Stage, open the [Stage context menu](/articles/99_Broadway/18_broadway_flow_window.md#stage-context-menu) by clicking the three dots in the right corner of the Stage and select  **Split**. The Split logic depends on the position of the Stage in the flow and it includes the following rules:
- The first Stage in the flow (root) can't be split. 
- If the Stage before the current Stage is not split, the current Stage will be split into two.
- If the Stage before the current Stage is split (for example, there are 4 Stages on the same level), the current Stage will be split to the same number of Stages.

The Stages can be merged under the following conditions:
- The Stage belongs to the group of Stages with the same parent Stage.
- The parent Stage is not a root.

**Valid Merge Example**

The Stages 1, 4 and 5 can be merged.

![image](/articles/99_Broadway/images/99_19_merge_example_1.PNG)

**Invalid Merge Example**

The Stages 1 and 6 cannot be merged since they don't have a common parent.

![image](/articles/99_Broadway/images/99_19_merge_example_2.PNG)

### What is a Stage Condition?

If a condition is required in the flow, the flow can be split and a **Stage condition** Actor can be added to one or more Stages that were created as a result of the split. The condition Actor can have any number of output parameters, however the true/false check is always performed on the first output parameter. Note that this parameter doesn't have to be Boolean, since Broadway can do the casting. For example, an empty String will be considered as false while not an empty String will be considered as true.

The **else** condition can be set to only one of the Stages on the same level and it is only executed if all the conditions were false. The stages which neither have a condition nor **else** will be always executed and they no impact on the **else** Stage. 

The Stage conditions impacts all the following Stages in the same branch. Thus, if some Stage condition is false, the execution of its branch stops. 
A merged Stage will run after all its parent Stages have been executed. If none of the parent Stages have been executed (all conditions are false), the merged Stage will not be executed.

### How Do I Define a Stage Condition?

- To add a Stage condition to the flow, open the [Stage context menu](/articles/99_Broadway/18_broadway_flow_window.md#stage-context-menu) by clicking the three dots in the right corner of the Stage and select **Stage Condition**. 
- To mark a Stage as **else**, open the [Stage context menu](/articles/99_Broadway/18_broadway_flow_window.md#stage-context-menu) by clicking the three dots in the right corner of the Stage and select **Else**.

Note that even though any Actor can be used as a condition, a **JavaScript** Actor is a powerful actor to express complex conditions. The script returns the value of the last line and it does not expect the 'return' keyword.

**Stage Conditions Example**

The below example shows a flow with 4 Stages on the second flow level: two Stages with conditions, one Stage marked as **else** and a Stage without any condition. Both conditions are checked, and then each related branche is executed only if the condition is true. The **else** branch is executed when both conditions are false. The Stage without any condition and its branch are always executed.

Depending on the flow input, the execution order of the Actors in this flow will be as follows:

- If both **Cond1 and Cond2 are true**: A1 -> Cond1 (true) -> B1-Const -> B3 -> Cond2 (true) -> B4-Split -> C1 -> Logger2 -> Count1
- If only **Cond1 is true**: A1 -> Cond1 (true) -> B1-Const -> B3 -> Cond2 (false) -> C1 -> Logger2
- If only **Cond2 is true**: A1 -> Cond1 (false) -> B3 -> Cond2 (true) -> B4-Split -> Logger2 -> Count1
- If both **Cond1 and Cond2 are false**: A1 -> Cond1 (false) -> Cond2 (false) -> B2 -> B3 -> Logger1 -> Logger2

![image](/articles/99_Broadway/images/99_19_cond_example_1.PNG)

[![Previous](/articles/images/Previous.png)](/articles/99_Broadway/18_broadway_flow_window.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_Broadway/20_broadway_flow_linking_actors.md)
