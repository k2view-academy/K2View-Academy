# ­­Broadway Tutorials Flows

You are now familiar with Broadway concept the the main components. Broadway has various of examples for Broadway flows. 

### What Will You Experience In This Learning Item?

By the end of the Broadway Tutorials Flows learning item you will know:

- Open and use Broadway Flow examples.
- View, run, and debug Broadway Tutorials flow to have a better understanding of Broadway.

To learn more about our Broadway flow, refer to [Broadway Flow Overview](/articles/99_Broadway/16_broadway_flow_overview.md) .

Now, let's have a better understanding about [Broadway Flow Examples](/articles/99_Broadway/17_tutorial_and_flow_examples.md).

### How Do I Open the Broadway Tutorials Flow?

To open the Broadway Tutorials flow, you need first  to open the Broadway flow window:

- Open a Fabric project in the Fabric Studio. You can get the K2view Academy [demo project](/articles/demo_project) or open your own Fabric Project.
- Go to the project tree, right click **Broadway > New Flow**. Set a name in the Flow Name and open the Broadway flow window.
- Go to the Broadway flow window, select **Actions > Examples**.
- Select the **a-broadway-tutorial** option to open the Broadway Tutorial flow.

### Debugging the Broadway Tutorials Flow

The Broadway Tutorial flow runs some manipulations on "Hello Broadway" String, and returns the "Hello Broadway" results.

Run the flow on a debug mode to view the input and the output of each [State](/articles/99_Broadway/19_broadway_flow_stages.md) and each [Actor](/articles/99_Broadway/03_broadway_actor.md) in the flow.

 Please read [Run and Debug Broadway Flow](/articles/99_Broadway/25_broadway_flow_window_run_and_debug_flow.md) and debug the Broadway Tutorial flow steps.

###  ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png) **Exercise – Run and Debug Broadway Tutorial Flow**

1. Download the [demo project] (/articles/demo_project) and open this project in your **Fabric Studio**.
2. Go to the **Project Tree**, right click the **Broadway** and select **New Flow**.
3. Populate the **Name** in the **Flow name** popup window by **test**.
4. In the Flow Window, Click **Actions** and then select **Examples**.
5. Select **a-broadway-tutorial** to open the Broadway Tutorial flow.
6. Click the **Run Flow** icon to run the flow.

  <ul>
 <pre><code>6.1 What is the result of the flow?</code></pre>
  </ul>

7. Add a Breakpoint to the **for each** Stage and click the **Debug Play** icon on the Flow window. Then click the **Debug Step** icon to execute the next steps of the flow.

<ul>
<pre><code>
7.1 How many iterations run on the <strong>StringBuilder</strong> Actor of the <strong>"for each"</strong> Stage?<br>
7.2 Which input value is sent to the <strong>StringBuilder</strong> Actor on each iteration?<br>
7.3 What is the output of the <strong>StringBuilder</strong> Actor?
7.4 How many outputs are returned by the <strong>StringBuilder</strong> Actor? Please explain.
7.5 What is the next Stage, executed after the <strong>"splitting the flow"</strong> Stage? Why?
</code></pre>
</ul>

  ![info](/academy/Training_Level_1/03_fabric_basic_LU/images/information.png) Click the green asterisk icon in the **"splitting the flow"** to read its remark, and check the value of the Actor in the **"paradox"** Stage to help you answer this question.

 8. Click **Stage 3** of the flow, and select **Now** Actor from the popup window to add an Actor to **Stage 3**.
 9. Click the the **Debug Step** icon to execute the flow on a debug mode, step-by-step.

  <ul>
<pre><code>9.1 Was the new <strong>Now</strong> Actor, added to to <strong>"Stage 3"</strong> exectued? Why?</code></pre>
</ul>

10. Click the **Stop Debug** icon tp stop the debugging.
11. Click the **Const** Actor of the **Hello Broadway** Stage. The [Actor window](/articles/99_Broadway/03_broadway_actor.md#actor-window) is displayed.
12. Edit the value of the first input variable from "Hello Broadway" to "Broadway Training".
13. Run the flow.
<ul><pre><code>13.1 What is the flow result?</code></pre></ul> 

14. Close the Broadway Turorial flow and check the list of Broadway Flows under your **Project Tree**.
<ul><pre><code>14.1 How may flows do you have now?</code></pre></ul>

15. Re-open the Broadway Tutorials flow and click the **Const** Actor of the **Hello Broadway** Stage.
<ul><pre><code>15.1 Which value is set for the input parameter?</code></pre></ul> 


### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png)Solution- Run and Debug Broadway Tutorial Flow Exercise 

 <ul>
 <pre><code> 
 6.1 The flow result is "Hellow Broadway"</code></pre>
 </ul>

<ul>
<pre><code>
7.1  + 7.2- The flow executes two iterations run on the <strong>StringBuilder</strong> Actor- the first iteration runs on "Hello" and the second iteration runs on "Broadway".
7.3 The <strong>StringBuilder</strong> returns the following output: "Hello Broadway".
7.4 The <strong>StringBuilder</strong> Actor returns one output. It gets its inputs by a loop, and builds the String. The "for each" State is marked by "Iterate Close". As a result- the StringBuilder in this Stage closes the loop and only then returns its output.
7.5 The next Stage, executed after the <strong>"splitting the flow"</strong> Stage, is the "Dynamic Logic Actors", since this Stage is on the same level as the "else" of the condition, and the condition returns "false". See <a href="https://github.com/k2view-academy/K2View-Academy/blob/KB_DROP2_99_BROADWAY_Nataly/articles/99_Broadway/16_broadway_flow_overview.md#flow-with-condition">Flow with Conditions</a>
</code></pre>
</ul>

 <ul>
<pre><code>9.1 The new <strong>Now</strong> Actor, added to to <strong>"Stage 3"</strong> was not exectued, since this Stage runs only if the "paradox" condition is fulfilled.</code></pre>
</ul>

<ul>
<pre><code>13.1 The flow result is "Broadway Training".</code></pre>
</ul> 

<ul><pre><code>14.1 There are two flow. A local copy of the tutorial flow was saved in the Fabric project by its execution.</code></pre></ul>

<ul><pre><code>15.1 The value of the input parameter is "Broadway Training". You can edit the flow and save the changes, or execute the flow. A local copy pf the flow is saved in the Fabric project. </code></pre></ul> 

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/03_fabric_basic_LU/02_create_a_fabric_project.md)
