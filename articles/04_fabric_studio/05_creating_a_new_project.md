# Creating a New Project

### What Is a Fabric Project?
A Fabric project is a consolidation of rules that transform data from one or more data sources into the K2View Fabric [MicroDB](/articles/01_fabric_overview/02_fabric_glossary.md#mdbmicrodb) structure using [Logical Unit](/articles/03_logical_units/01_LU_overview.md) modeling. To do so, the project must be defined in the Fabric Studio and can then be added to GIT <studio>or SVN</studio> version control. 

[Click for more information about Fabric Studio UI Components and Menus.](/articles/04_fabric_studio/01_UI_components_and_menus.md)

[Click for more information about the Fabric Project Structure.](/articles/04_fabric_studio/08_fabric_project_tree.md)

### How Do I Create a New Project?

<studio>

1. Go to the **Start Page** or click the **File** icon <img src="images/04_05_01%20New%20Project%20icon.jpg" alt="drawing" width="25"/> and then click **New Project** to display the **New Project** dialog box.
2. Enter the **name** of the **project** in the **Project Name** field. 
3. Verify that the **default directory** displayed in the **Location** field is correct, if not, **Browse** and select the correct one. This directory will be used to store the project’s files and definitions.
4. Optional: check the **Add Project to Version Control** checkbox to add the new project to SVN or Git. It is recommended to save and commit the new project as a baseline.
Note that to avoid errors, the SVN/Git repositories must be created in advance.  
5. Verify the **default URL** in the **Repository URL** field is correct, if not, **Browse** and select the correct one. The **PROJECT_NAME** is automatically added to the Repository URL once added to the configuration control. 
6. Click **OK**. The project’s **Main Window** opens and the **Project Tree** is displayed in the left pane. 


**Notes**
* Only one project can be deployed to a specific Fabric server / cluster. Therefore, when defining the project’s physical saved name(.K2proj), make sure that it is meaningful, for example, the project’s business purpose.  The project’s logical name can be edited in the Fabric Studio. 
* To find the deployed project’s name, execute the **Fabric set;** command from the Fabric console.

[Click for more information about User Preferences.](/articles/04_fabric_studio/04_user_preferences.md)

[Click for more information about Adding Fabric Projects to Version Controls.](/articles/04_fabric_studio/06_adding_fabric_projects_to_version_control.md)

[Click for more information about Best Practices for Using SVN and GIT.](/articles/04_fabric_studio/07_best_practices_for_working_with_GIT_and_SVN.md)

### How Do I Access a Fabric Project?

1. Either:
   * Go to the **K2view Fabric Studio Start Page** and click **Open Project**.
   * Click **File** in the top left corner and then click **Open Project** to open the default Fabric Project Directory.     
2. Go to the **Project Folder** (it has the name of the project) and double click the **.k2proj file**.
3. Do the following to check out a project:
   * Go to the **Start Page** or click **File** and then click **Checkout Project** to display the Checkout Project dialog box.
   * The **Repository Type** is based on the central version control defined for the project and is one of the following:

**GIT**

![image](images/04_05_02%20GIT.jpg)

**SVN** 

![image](images/04_05_03%20SVN.jpg)

4. Once checked out, the project is created locally.

[Click for more information about Adding Fabric Projects to Version Controls and Best Practices for Using SVN and GIT.](/articles/04_fabric_studio/06_adding_fabric_projects_to_version_control.md)

</studio>

<web>

Currently a space is created with a new Fabric project structure, ready for working on the project from scratch. You can use an existing project on which you already have worked.

[Click to get more information how to load an existing project to Web Studio.](11_fabric_studio_exporting_and_importing a_fabric_project.md)

</web>




[![Previous](/articles/images/Previous.png)](/articles/04_fabric_studio/04_user_preferences.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/04_fabric_studio/06_adding_fabric_projects_to_version_control.md)

