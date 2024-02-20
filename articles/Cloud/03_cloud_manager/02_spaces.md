# Spaces

A **Space** is a Fabric cluster - a full working environment with all required modules and services. Through the k2cloud - also known as **k2cloud Orchestrator** - which constitutes of editable UI screens, the user is able to easily create a Space. This is done through the Spaces page, where you just need to choose *what and where* - the project, the profile and the location.

The Spaces page is the starting point to your daily basis work. It is reached directly by default when accessing the **k2cloud Orchestrator** on https://cloud.k2view.com/. The Spaces page displays your organization's spaces, each via its own **Space card** that provides information about the Space, showing operational mini-dashboards, and lets you take actions through it. 

## Spaces List

The Spaces page presents the **list of Spaces**, arranged by their creation date and time (newer to older). It is through this page that you can also create a new Space, see Spaces' status and info, as well as take actions relating to each Space.

Upon entering the **k2cloud Orchestrator**, you can see that *Show My Spaces* is selected by default in the top bar of the Spaces page; this brings about a display of only the Spaces that you have created. However, it is also possible to select *Show All Spaces*  for viewing all the Spaces created by other users.

Additionally, the Auto Refresh feature is turned on by default in the top bar as well, bringing about a display of the most up-to-date Spaces information. It can be turned on and off, and you can also click the *Refresh* button for an on-demand refresh.

![](images/spaces-top-bar.png)  

## Space Card's Anatomy

Each **Space card** comprises of a top bar, the main informative part and 2 mini dashboards.

### **Top Bar** 

* **Space name** - when clicked, you will leave the k2cloud and be directed to the Space itself. Clicking on the external link icon (![](images/ext-link.png)) next to the **Space name**, will lead you to the Space in another browser tab.

* **Status** - is indicated by the bar's color:

  * Blue = <span style="background-color: #0969da; padding: 0 7px; color:white">live</span> and active. In this case metrics graphs are active too, and some actions are available.
  * Gray = <span style="background-color: #999999; padding: 0 7px; color:white">inactive</span> - when the Space is either paused or in a transition process - creating/pausing/resuming/deleting. In a transition process actions are not available.
  * Red = <span style="background-color: #CC0000; padding: 0 7px; color:white">error</span>, where a clickable information alert icon (![](images/info-alert.png)) appears in the Space's top bar. Clicking on it opens a tooltip with added error description, which can be sent to the Cloud Ops team for handling.

* **Actions** - available via the vertical ellipsis (3 dots) menu on the right side in the Space's top bar. Various actions are available, depending on the current Space's status and profile. Actions are:

  * **Redeploy Environments** - uploads a fresh Environments file into the Space, from the project in GIT, and deploys it.

    Upon clicking - a pop-up window appears, offering you also to change and set the global environment.

    ![](images/redeploy-env.png)

    Clicking on *Redeploy* will close the pop-up window and you will see the status-change dialog box, indicating of the deployment progress.

    > Available only for non-Studio Spaces. A Studio Space shall be redeployed via Studio.

  * **Redeploy Project** - updates the Space with the updated project in GIT and deploys either the whole project or specified LU/s.

    Upon clicking - a pop-up window appears, letting you also to deploy either a whole project or specific LUs.

    When *Deploy LUs* is chosen, you can type the LUs, separated by a comma.

    ![](images/redeploy-proj.png)

    Clicking on *Redeploy* will close the pop-up window and you will see the status-change dialog box, indicating of the deployment progress.

    > Available only for non-Studio Spaces. A Studio Space shall be redeployed via Studio.

  * **Settings**

  * **Refresh Status** - triggers a refreshing of the Space's status, regardless of whether the Auto Refresh is turned on, in the top bar of the Spaces page, or not.

  * **Restart** - restart the Space, e.g., when a configuration that requires a Fabric restart is needed.

   
  * **Pause/Resume** - either one of these actions appears in accordance with the Space's current status (*Pause* when the Space is running and vice versa). Note that pausing your Space would free-up resources and cut cost.

       > * When pausing your Space, you do **not** loose the changes that you may have made in your project, although you have not pushed it to GIT. Upon resuming the Space, it will include those changes.

  * **Delete** - deletes the Space, after getting your approval in the open dialog box. 

   
  * **Support**

### Space Card Information

The **main part of the Space card** is divided into 3 sections: 

INSERT SPACE CARD IMAGE HERE
* Its **upper section** describes basic information outlining the project, its profile and the site.
  * ***Project*** provides the Fabric-related project name, followed by parentheses that include the name of the branch/tag that the project is being worked on. This overall name provides a brief overview and hints which project this Space is based on.
  * The ***Profile*** name, just like the Project name, is as self-explanatory as possible, aiming to be clear and concise, and to represent this Space.
  * The ***Site*** outlines where does this Space operate. It represents a Kubernetes cluster. K2view supports the 3 major Cloud Service Providers (CSPs) - GCP, AWS and Azure - and each organization usually uses only 1 CSP.
* Its **middle section** shows what modules are deployed and are running on this Space, such as Fabric, Studio, Cassandra, PostgreSQL and Neo4j. 
* Its **third section** contains the Space creator’s name, the creation date and a status change date. The latter is the date on which the Space’s status (e.g., paused or resumed) was changed.

### Mini Dashboards

There are 2 clickable mini dashboards in the Space card - *mDB Reads & Writes* and *API Calls*. They are located on the right side of the Space card and provide a quick view of the Space's performance and operation. Each mini dashboard contains 2 graphs that display data of the last 5 minutes. Wider and detailed information can be reached for inspection by clicking on each mini dashboard, as well as through accessing the Monitor app. Both these actions lead to the same informative page that displays the data via Metrics and Logs tabs.  

* **Reads & Writes** - to the Fabric storage. This information is important for both performance (traffic/usage) and financial aspects. 

  The graph is divided into 2 y-axes, one for read and the other for writes.

*  **API Calls** - which shows information about the calls that are done into Fabric by your platforms. The y-axis on the left shows the overall count number of calls, whereas the y-axis on the right shows the count of API calls' responses that are returned as failures. This gives you an at-a-glance indication of whether the operation and functioning are as expected.

## Create a Space

To create a new Space: 

1. Click on either the 'Create Space **+**' button at the top bar of the Spaces page, or the last empty card, labelled as ‘Create Space’.
2. Define *what and where*: The name of the Space, the Project, the Profile and where it shall be created - the Site. According to the setup and your project needs, the Profile and Site select lists will contain one or more options.
3. Click on the 'Create' button.

The newly created Space card then appears at the top left of the Spaces list, as the list is arranged by creation date and time (newer to older). Additionally, as long as you do not leave the Spaces page, the newly created Space card is visually emphasized with a gray border.

The Space creation process has 3 stages - Create, Start and Ready/Error. A progress bar indicates which stage the card is currently at. Note that even if you leave the Spaces page and come back to it, you will still see the stage on the progress bar, for any action you may make.

> The Space creation process may vary between project and profiles. For non-Studio Spaces, the Space creation contains also the step of build and deploy execution, which may take time in case the deployment also responsible to make some further automatic processes execution, defined in [deploy.flow](/articles/19_Broadway/09a_automatic_flows_execution_upon_deploy.md) or as user jobs. 
