# Broadway Flow Overview

**Flow** is a main Broadway object which represents a business process. A flow is built out of several [Stages](<!--Link to 18-Flow Stages-->) and each Stage includes one or more [Actor](/articles/99_Broadway/04_built_in_actor_types.md). The Stages are executed one by one from left to right, while the Actors within each Stage of the flow are executed top down. 

Below is a simple flow with 3 stages and 4 actors. The execution order of the Actors within the flow is: **A1 -> B1 -> B2 -> C1**.

![image](/articles/99_Broadway/images/99_16_01_flow1.PNG)

Each Stage can be split into two stages (and so on), so there can be several stages on the same dependency level in the flow and their execution order is also top down. In the below example, the execution order is still the same and it is: **A1 -> B1 -> B2 -> C1**.

![image](/articles/99_Broadway/images/99_16_01_flow2.PNG)

When it's needed to introduce a condition, such as IF-ELSE, the flow can be split and a stage condition can be added to one of the split parts, while the other part can be set as ELSE. The execution order of the Actors in such flow will be: **A1 -> if B1 is true, then B1, otherwise B2 -> C1**. Note that Stage condition is a grey object, while a regular Actor is yellow.

![image](/articles/99_Broadway/images/99_16_01_flow3.PNG)

If there are additional Stages on the same dependency level as IF-ELSE condition, its Actors will be executed as well, in the following execution order: **A1 -> if B1 is true, then B1, otherwise B2 -> Now1 -> C1**.

![image](/articles/99_Broadway/images/99_16_01_flow4.PNG)



