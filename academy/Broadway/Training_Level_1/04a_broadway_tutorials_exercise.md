# Broadway Tutorial Flow

###  ![](/academy/images/Exercise.png) **Exercise – Run and Debug Broadway Tutorial Flow**

#### Step 1 - Open the Broadway Tutorial Flow

1. Open the  **a-broadway-tutorial**  flow.

#### Step 2 - Run and Debug the Broadway Tutorial Flow
1. Click the Debug ON <img src="images/debug_on.png" alt="debug on" style="zoom:80%;" /> to set it to Debug OFF. Click <img src="images/run_flow_icon.png" alt="Run Flow" style="zoom:80%;" /> to run the flow.

  <ul>
  <pre><code>A. What is the result of the flow?</code></pre>
  </ul>



2. Add a  **Breakpoint** to **for each** Stage, click the Debug OFF <img src="images/debug_off.png" alt="debug off" style="zoom:80%;" /> to set it back to Debug ON <img src="images/debug_on.png" alt="debug on" style="zoom:80%;" />, and then click the <img src="images/run_flow_icon.png" alt="Run Flow" style="zoom:80%;" /> to execute the flow in a debug mode till the breakpoint.

3. Click the Step <img src="images/debug_step_icon.png" alt="Debug Step" style="zoom:80%;" /> or the Resume <img src="images/resume.PNG" alt="resume" style="zoom:80%;" /> to execute the next steps after the breakpoint step.

  <ul>
  <pre><code>
  A. How many iterations run on the <strong>StringBuilder</strong> Actor of the <strong>for each Stage</strong>?<br>
  B. Which input value is sent to the <strong>StringBuilder</strong> Actor on each iteration?<br>
  C. What is the output of the <strong>StringBuilder</strong> Actor?
  D. How many outputs are returned by the <strong>StringBuilder</strong> Actor? Please explain.
  E. Which Stage is executed after the <strong>Splitting the flow</strong> Stage? Why?
  </code></pre>
  </ul>

Click the green asterisk in **Splitting the flow** to read its remarks and check the value of the Actor in the **Paradox** Stage to help you answer this question.

  #### Step 3 - Edit the Flow to Test a Conditional Stage

1. Add **Now** Actor to **Stage 3**: click **Stage 3** in the flow and select the **Now** Actor in the popup window to add an Actor to **Stage 3**.
2. Click the Step <img src="images/debug_step_icon.png" alt="Debug Step" style="zoom:80%;" /> or the Resume <img src="images/resume.PNG" alt="resume" style="zoom:80%;" /> to execute the flow's steps in **Debug mode**. 

  <ul>
  <pre><code>A. Has the new <strong>Now</strong> Actor of <strong>Stage 3</strong> been executed? Why?</code></pre>
  </ul>

​		Read more about [Stage Condition](/articles/19_Broadway/02_broadway_high_level_components.md#stage-conditions) to help you answer this question.

3. Click <img src="images/stop_debug_icon.png" alt="Stop Debug" style="zoom:80%;" /> to stop the **Debug process**. 
4. Edit the **Const** Actor in the **Hello Broadway** Stage:  click the **Const** Actor in the **Hello Broadway** Stage. The [Actor's Properties window](/articles/19_Broadway/03_broadway_actor_window.md) is displayed.
5. Edit the value of the first input variable from **Hello Broadway** to **Broadway Training**.
6. Run the flow. 

  <ul><pre><code>A. What is the flow's result?</code></pre></ul> 

 #### Step 4 - Add the Flow to the Project Tree in the Fabric Studio

1. Close the Broadway Tutorial flow and check the list of Broadway flows under the <strong>project tree.</strong>

<ul><pre><code>A. How may flows do you have now?</code></pre></ul>

2. Reopen the **Broadway Tutorial flow** and click the **Const** Actor in the <strong>Hello Broadway</strong> Stage.

<ul><pre><code>A. Which value is set for the input parameter?</code></pre></ul> 



[![Previous](/articles/images/Previous.png)](04_broadway_tutorials.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04b_broadway_tutorials_solution.md)