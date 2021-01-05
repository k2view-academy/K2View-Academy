# Support Parallel Execution

Broadway enables running several Actors of the same Stage in parallel.

To define the parallel execution, click ![image](images/99_19_dots.PNG) in the right corner of the Stage to open the [Stage context menu](18_broadway_flow_window.md#stage-context-menu) and select **Parallel** to populate the number of parallel threads. Once submitted, the orange marking is displayed above the Stage name:

<img src="images/99_19_parallel_01.PNG" alt="Parallel" />

To remove the parallel execution, click ![image](images/99_19_dots.PNG) > **Parallel** and set the number to zero. 

### Parallel Execution Examples

##### Example 1 - Read from LUI and Write into Target DB

The following example displays a flow with two Inner Flows on Stage 2 that are executed in parallel. Each of the inner flows on Stage 2 is reading the data from the LUI and writing it into the target DB.

<img src="images/99_19_parallel_02.PNG" alt="Parallel" />

##### Example 2 - Read and Update LUI

The following example displays a flow which synchronizes an LUI into Fabric, then reads from two different LU tables in parallel and writes into two different LU tables in parallel. 

<img src="images/99_19_parallel_03.PNG" alt="Parallel" />

##### Example 3 - Invalid Use of Parallel Execution

The following example of performing two GET Instance commands in parallel is invalid. The attempt to execute the flow causes an exception. 

<img src="images/99_19_parallel_04.PNG" alt="Parallel" />

[![Previous](/articles/images/Previous.png)](29_recovery_point.md)
