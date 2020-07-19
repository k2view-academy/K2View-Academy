# ­­Broadway Tutorials Flows

You are now familiar with Broadway concept the the main components. Broadway has various of examples for Broadway flows. 

### What Will You Experience In This Learning Item?

By the end of the Broadway Tutorials Flows learning item you will know:

- How to create a Broadway flow.
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

 Please read [Run and Debug Broadway Flow](/articles/99_Broadway/25_broadway_flow_window_run_and_debug_flow.md) and debug the Broadway Tutorial flow step by step.

###  ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png) **Exercise – Run and Debug Broadway Tutorial Flow**

1. Download the [demo project] (/articles/demo_project) and open this project in your **Fabric Studio**.
2. Go to the **Project Tree**, right click the **Broadway** and select **New Flow**.
2. Populate the **Name** in the **Flow name** popup window by **test**.
3. In the Flow Window, Click **Actions** and then select **Examples**.
4. Select **a-broadway-tutorial** to open the Broadway Tutoria flow.
5. Click the **Run Flow** icon to run the flow.

- What is the result of the flow? 

6. Add a Breakpoint to the **for each** Stage and click the **Debug Play** icon on the Flow window. Then click the **Debug Step** icon to execute the next steps of the flow. 

- How many iterations run on the **StringBuilder** Actor of the **for stage** Stage? 
- Which input value is sent to the  **StringBuilder** Actor on each iteration? 
- What is the output of the **StringBuilder** Actor? 
- How many outoputs does the **StringBuilder** return? Please explain.
- What is the next Stage, executed after the **splitting the flow** Stage? Why? \
  Click the green asterisk icon in the **splitting the flow** to read its remark, and check the value of the Actor in the **paradox** Stage to help you answer this question.
  
 7. Click **Stage 3** of the flow, and select **Now** Actor from the popup window to add an Actor to **Stage 3**.
 8. Click the the **Debug Step** icon to execute the flow on a debug mode, step-by-step.
  - Was the new **Now** Actor, added to to **Stage 3** exectued? Why
  
9. Click the **Stop Debug** icon tp stop the debugging.
10. Click the **Const** Actor of the **Hello Broadway** Stage. The [Actor window](/articles/99_Broadway/03_broadway_actor.md#actor-window) is displayed.
11. Edit the value of the first input variable from "Hello Broadway" to "Broadway Training".
12. Run the flow.
- What is the flow result? 

13. Close the Broadway Turorial flow and check the list of Broadway Flows under your **Project Tree**.
- How may flows do you habbe now?

14. Re-open the Broadway Tutorials flow and click the **Const** Actor of the **Hello Broadway** Stage.
- Which value is set for the input parameter? 

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/03_fabric_basic_LU/02_create_a_fabric_project.md)
