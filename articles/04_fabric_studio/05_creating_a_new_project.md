# Creating a New Project

### What is a Fabric Project?
A Fabric Project is a consolidation of rules that transform data from one or more data sources into the K2View Fabric micro-database structure using [Logical Unit](https://github.com/k2view-academy/K2View-Academy/wiki/Logical-Units-Overview) modeling. To do so, the Project must be defined in the Fabric Studio which can then be added to either GIT or SVN Version Control. 

**Click for more information about Fabric Studio UI Components and Menus.**                      
**Click for more information about the Fabric Project Structure.**\
**Click for more information about Adding Fabric Projects to Version Controls.**

### How do I Create a New Project?

1. Go to the **Start Page** or click the **File** icon ![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_fabric_studio/images/04_05_01%20New%20Project%20icon.jpg =20X) and then click **New Project** to display the **New Project** dialog box.
2. Enter the **name** of the **project** in the **Project Name** field. 
3. Verify that the default directory displayed in the **Location** field is correct, if not, **Browse** and select the correct one. This directory will be used to store the Project’s files and definitions.
4. Optional: check the **Add Project to Version Control** checkbox to add the new Project to SVN or Git. It is recommended to save and commit the new Project as a baseline.
Note that to avoid errors, the SVN / GIT repositories must be created in advance.
5. Verify the default URL in the **Repository URL** field is correct, if not **Browse** and select the correct one. The **PROJECT_NAME** is automatically added to the Repository URL once added to the configuration control.
6. Click **OK**. The Project’s **Main Window** opens and the **Project Tree** is displayed in the left pane. 


**Notes**
* Only one Project can be deployed to a specific Fabric server / cluster. Therefore, when defining the Project’s physical saved name(*.K2proj), make sure that it is meaningful, for example, the Project’s business purpose.  The Project’s logical name can be edited in the Fabric Studio. 
* To find the deployed Project’s name, execute the [Fabric set; command] from the Fabric console.
 
**Click for more information about User Preferences.**\
**Click for more information about Adding Fabric Projects to Version Control and Best Practices for Using SVN and GIT.**

### How do I Access a Fabric Project?

1. Either:\
   a. Go to the K2view Fabric Studio Start Page and click Open Project.\
   b. Click File in the top left corner and then click Open Project to open the default Fabric Project Directory.     
2. Go to the Project Folder (it has the name of the Project) and double click the *. k2proj file.
3. Do the following to check out a Project:\
   a. Go to the Start Page or click File and then click Checkout Project to display the Checkout Project dialog box.\
   b. The Repository Type is based on the Central Version control defined for the Project and is one of the following:

**GIT**

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_fabric_studio/images/04_05_02%20GIT.jpg)


**SVN** 

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_fabric_studio/images/04_05_03%20SVN.jpg)

4. Once checked out, the Project is created locally.

**Click for more information about Adding Fabric Projects to Version Controls and Best Practices for Using SVN and GIT.**

 
[![Previous](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Previous.png)](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_general/04_user_preferences.md)[<img align="right" width="60" height="54" src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Next.png">](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_general/06_adding_fabric_projects_to_version_control.md)


