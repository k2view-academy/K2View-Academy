# Spaces

A space is a Fabric cluster - a full working environment with all required modules and services. K2view cloud lets you to easily spin up a space, thru  the Spaces page, where you just need to choose what and where - the project, the profile and the location.

The Spaces page is the starting point to your daily basis work in K2view Cloud. It lets you to see your organization's spaces, each reparented as a "card" providing information about the space, showing operational mini-dashboards and also let you to take actions about each space. 

## Space List

Spaces page shows the space list, ordered by their creation time, newer to older, lets you to create new space, to see spaces' status and info, as well take action upon each space.

By default, only spaces that you created are shown. To see all spaces, created by your teammates, choose the "Show All Space" radio button at the top bar.

In addition, by default the auto-refresh is turned on so that you can see the most updated information about the spaces. You can turn it on and off at the top bar, as well click to activate refresh, on demand.

![](images/spaces-top-bar.png)  

## Space Card

Each space card built from top bar, mini dashboard metrics and in a glance information area.

### **Top Bar** 

* **Name**, When clicked you will leave the cloud manager and move to the space itself.  Clicking on the external link icon (![](images/ext-link.png)) will lead you to the space in  another browser tab.

* **Status**, Identified by the bar's color

  * Blue = <span style="background-color: #0969da; padding: 0 7px; color:white">live</span> and active. In this case metrics graphs are active too, and some actions are available.
  * Gray = <span style="background-color: #999999; padding: 0 7px; color:white">not active</span>  - when space is paused or when it is in a transition process - creating/pausing/resuming/deleting. In a transition process actions are not available.
  * Red = <span style="background-color: #CC0000; padding: 0 7px; color:white">error</span> where an information alert icon ( ![](images/info-alert.png)) appears on the status bar. Clicking on it will open a tooltip with more information about the error, which can be sent to the Cloud Ops team. 

* **Actions**, available via the ellipsis (3-dots) menu at bar's right side. Various actions are available, depended by current space's status and space profile. Note that actions are not available for a space with an error status. Actions are:

  * **Redeploy Environments**, uploading into the space a fresh Environments file, from the project at GIT, and deploy it.

    On click - a pop will appear, offers you also to change and set the global environment

    ![](images/redeploy-env.png)

    Clicking on Redeploy will close the popup and you will see the status-change dialog box, indicating you on the deployment progress.

    > Available only for non Studio spaces. For a Studio space you shall redeploy it via Studio, to avoid misunderstanding.

  * **Redeploy Project**, update the space with the updated project in GIT and deploy either the whole project or specified LU/s.

    On click - a pop will appear, lets you also to deploy either whole project or specific LUs.

    When "Deploy LUs" is chosen, you can type the LUs with comma separated.

    ![](images/redeploy-proj.png)

    Clicking on Redeploy will close the popup and you will see the status-change dialog box, indicating you on the deployment progress.

    > Available only for non Studio spaces. For a Studio space you shall redeploy it via Studio, to avoid misunderstanding.

  * Settings

  * Refresh Status, trigger a refresh status action manually, either the auto-refresh is turned on, at spaces page top bar, or not.

  * Restart, restart the space, for example when a configuration which requires Fabric restart is needed.

    > Available only for Studio spaces, to avoid  

  * Pause/Resume, either of these appear according the current status (Pause when space is running and vice versa). By pausing your space you can save budget as its resource are going down. 

    > * Available only for Studio spaces, to avoid
    > * When pausing your space you do **not** loose the changes that you might made in your project, even if not pushed to GIT. On resuming the space you will have it.

  * Delete, delete the space, after getting your approval in the opened dialog box. 

    > Available only for Studio spaces, to avoid

  * Support

### Mini Dashboard



### Card Information



## Create a Space

