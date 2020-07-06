# Broadway Flow - Stages

**A Broadway flow** acts as a graph (or tree) which is built out of several [Stages]().  There can be [one or more Stages on the same level](/articles/99_Broadway/16_broadway_flow_overview.md#flow-with-2-stages-on-the-same-level) and in each Stage - one or more [Actors](/articles/99_Broadway/03_broadway_actor.md). The Stages are executed one by one from left to right. In case of several Stages on the same level, they are executed top down. The Actors within each Stage are also executed top down. 

### How Do I Add or Delete a Stage?

A Stage can be added anyway in the flow.

To add a Stage at the end of the flow, click on big plus sign in the empty Stage area.

To add a Stage in the middle of the flow, open the Stage context menu by clicking the three dots in the right corner of the Stage and select either **Insert After** or **Insert Before**. 

To delete a Stage, select **Delete** from the Stage context menu. If the Stage has a dependent branch, it will be deleted as well.

### How Do I Split or Merge the Stages?

Each Stage can be split into two (and so on), so there can be several stages on the same dependency level. 

To split a Stage, open the Stage context menu by clicking the three dots in the right corner of the Stage and select  **Split**. The Split logic depends on the position of the Stage in the flow and it includes the following rules:

- The first Stage in the flow (root) can't be split. 
- If the Stage before the current Stage is not split, the current Stage will be split into two.
- If the Stage before the current Stage is split (for example, there are 4 Stages on the same level), the current Stage will be split to the same number of Stages.

It is possible to merge several Stages under the following conditions:

- The Stage belong to the group of Stages with the same parent Stage.
- The parent Stage is not root.

**Valid Merge Example**

Merge can be done on any of the Stages 1, 4 or 5. 

![image](/articles/99_Broadway/images/99_19_merge_example_1.PNG)

**Invalid Merge Example**

Merge cannot be done on the Stages 1 or 6 since they don't have a common parent.

![image](/articles/99_Broadway/images/99_19_merge_example_2.PNG)

### What is a Stage Condition?

If a condition is required in the flow, the flow can be split and a stage condition can be added to one or more stages that were created as a result of the split. Any Actor can be used as a condition. The **else** condition can be set to only one of the stages on the same level and it is only executed if all the conditions were false. The stages which neither have a condition nor **else** will be always executed and they no impact on the **else** Stage. 

The Stage conditions impacts all the following Stages in the same branch. Thus, if some Stage condition is false, the execution of its branch stops. 

A merged Stage will run after all its parent Stages have been executed. If none of the parent Stages have executed (all conditions are false) the merged stage will not be executed.

Note that a **JavaScript** Actor is a powerful actor to express complex conditions.

**Stage Conditions Example**

![image](/articles/99_Broadway/images/99_19_cond_example_1.PNG)

The execution order of the Actors in the above flow will be as follows, depending on the flow input:

- If both **Cond1 and Cond2 are true**, the Actors execution order will be:

  **A1 -> Cond1 (true) -> B1-Const -> B3 -> Cond2 (true) -> B4-Split -> C1 -> Logger2 -> Count1**

- If **only Cond1 is true**, the Actors execution order will be:

  **A1 -> Cond1 (true) -> B1-Const -> B3 -> Cond2 (false) -> C1 -> Logger 2**

- If **only Cond2 is true**, the Actors execution order will be:

  **A1 -> Cond1 (false) -> B3 -> Cond2 (true) -> B4-Split -> Logger2 -> Count1**

- If both **Cond1 and Cond2 are false**, the Actors execution order will be:

  **A1 -> Cond1 (false) -> Cond2 (false) -> B2 -> B3 -> Logger1 -> Logger2**
