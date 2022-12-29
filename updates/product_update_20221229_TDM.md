### The TDM 7.6 Official Release

TDM 7.6  has just been released and it includes many exciting features:

* **TDM 7.6 is based on Fabric 7**.
* **Installation Improvements**: the addition of the TDM GUI code to the TDM library. The TDM installation no longer requires a separate package of Fabric with the TDM GUI code. The deployment of the TDM LU adds the TDM GUI web application to Fabric.
* **Reference Fixes and Enhancements**: Reference tables are now stored in a dedicated Fabric LU instead of Cassandra.
* Adding **TDM Custom JMX Statistics** for a better monitoring of the TDM processes.
* **TDM GUI - Task Window** - adding a new checkbox to define whether the task needs to filter out entities that are reserved for other users from the task's entity list for a better control on the task.  Note that this checkbox impacts the creation of the task's entities list and not the execution itself. In other words, even if the checkbox is cleared, reserved entities may be included in the task's entity list, but their execution fails, since they are reserved for other users. 
* **Masking improvements** -  split the enable masking Global variable to 2 variables, for extract and load.
* **Other Bug Fixes**.

See the [Release Notes](https://support.k2view.com/Academy/Release_Notes_And_Upgrade/TDM-V7.6/TDM_Release_Notes_V7.6.pdf.html) and [Upgrade Procedure to TDM 7.6](https://support.k2view.com/Academy/Release_Notes_And_Upgrade/TDM-V7.6/TDM_Upgrade_Procedure_to_V7.6.pdf.html) for the full list.

<img src="images/img10.png" alt="image" style="zoom: 67%;" />

