### The TDM 9.2 Official Release

TDM 9.2 has just been released and it includes the following new features/capabilities:

* **Vertical execution mode**: Introducing an additional execution mode for TDM tasks, where instead of executing system by system (where all entities are processed in one system before moving on to the next system in the hierarchy), the vertical execution processes the **complete LU hierarchy for each root entity** before moving on to the next root entity. This execution mode is particularly useful when running TDM tasks on **a large scale of entities** as it ensures **better cross-systems data consistency** and **data alignment**.

* **Filter out reserved entities enhancements**: Enabling the user to filter all reserved entities from the task's entities, including the ones reserved by the task executor.

* **Display Business Names for Task’s Parameters**: Omitting the Logical Unit (LU) name from the business parameter names that appear in the list of business parameters in the sub-setting of TDM task. The display of the previous parameter's name along with the LU name - is supported as well. 

* **AI-based Synthetic Entities improvements**: Improving the identification of logical relationships between distant tables, namely, tables that are not linked with Primary Key/Foreign Key relation.

* Bug fixes.

  

To get the full list of features and fixes, refer to the [Release Notes](https://support.k2view.com/Academy/Release_Notes_And_Upgrade/TDM-V9.2/TDM_Release_Notes_V9.2.pdf.html) and [Upgrade Procedure to 9.2](https://support.k2view.com/Academy/Release_Notes_And_Upgrade/TDM-V9.2/TDM_Upgrade_Procedure_to_V9.2.pdf.html).

<img src="images/img1.png" alt="image" style="zoom: 80%;" />
