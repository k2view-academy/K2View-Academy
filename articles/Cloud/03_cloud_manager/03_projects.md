# Projects

A **Project** is a parameter that is outlined in a Cloud Space. It is composed of some parts and it describes 2 important and diverse factors - the location of a Fabric Project on GIT and the Profiles connected to a Project. The Project’s definitions are the basis for Spaces creation and a Space contains at least 3 main fields that derive from the Project.

## A Project's Life Cycle
A Project is initiated when created by an implementor, who also develops it and then pushes it to GIT. The implementor then has to pass it onwards to QA, production and pre-production teams. The Project is being made stable by being added a GIT version and tag, and by being connected to a certain GIT branch. If someone else/a user wants to continue working and developing the same Project in GIT, but on another branch, he has to create a Space on the other branch of the Project. In such case, a Project Space Profile, known as Profile, is required. Every Project has its own Project Space Profile, which helps with the life cycle of the Project.
Additionally, each Customer’s user type – such as implementor, QA, production, etc. – belongs to a separate business operation unit and hence works on a different Site, which is based on different permissions. This Site separation prevents interferences between these various business operation units, but more importantly, the Site is a part of the Project Space Profile’s definition.
In case the user makes some changes to his Project, namely, implementation and enhancements as part of the Project’s life cycle, and in case the user wishes to upgrade Fabric per official releases, these acts are made possible via the Project Space Profile. The latter is being fed along with additional relevant data whilst creating a Project. After saving the Project, it can be selected when creating a new Space. This means that the Project is alive, and up for updates per the needs of the Project’s life cycle.

If a developer has now created a new branch in GIT as he wishes to continue the development, he would want the Project to be updated in the Projects page.
In case an old unused branch exists, it can simply be deleted in order to avoid being an extra load in the Project Space Profiles section. When deleted, the Project Space Profile, known as Profile, will be neither visible nor available for selection when creating a Space in the Spaces page. For this reason, when creating a Space, the selection order should first be a Project, followed by a Profile - provisioned in the Projects page - followed by a Site, which is a choice derived from the selected Project Space Profile, i.e., Profile. This relation can be regarded as dependency relation.

In summary, the aim of the Projects page is to provision a Project there and to accompany the Fabric Project throughout its Project’s life cycle, since it is alive and ongoing as changes constantly take place. 
A Customer/Organization/Client may have between 1 and 2 Projects, but many Project Space Profiles.  
A best practice would be to have one single GIT Project or one single Fabric Project that represents a Project in its logical sense at the Customer’s/Organization’s/Client’s end.

## Projects Page Layout
The Projects page is reached by accessing the k2cloud Orchestrator on https://cloud.k2view.com/, followed by a switch in the top bar from the Spaces to the Projects page.

INSERT IMAGE OF TOP BAR OF k2cloud Orchestrator AFTER IT’S SWITCHED TO PROJECTS PAGE

Upon entering the Projects page, you can see that *Show All Projects* is selected by default in its top bar; this brings about a display of all the Projects created by other users. The logic behind this is that the Project owner was most likely the one to define and create the Project. It is also possible to select *Show My Projects* for viewing Projects created by yourself. It is worth mentioning that **K2view Cloud Platform** is multi-tenant, which enables each User/Client to view his own Projects and Spaces only.
The Auto Refresh feature is turned off by default in the top bar, as it is less commonly used since Projects are not often created. If a user wishes to see a display of the most up-to-date Customer’s Projects list, he can simply click the Refresh button for an on-demand refresh.

INSERT IMAGE OF PROJECT PAGE’S TOP DEFAULT BAR

### Projects List
The Projects page presents the list of Projects in a line layout, where the user can carry out acts such as opening, closing, saving, etc. This repetitive visualization is kept throughout the k2cloud Orchestrator. 
Additionally, the Auto Refresh feature is turned on by default in the top bar as well, bringing about a display of the most up-to-date Spaces information. It can be turned on and off, and you can also click the Refresh button for an on-demand refresh.

Each **Project’s line** includes its name, source (a base source of GIT, GIT Repository), creation date, creator’s details, last modification date and an action button for disabling/enabling a Project. Disabling a Project is used when users should no longer be able to create more Spaces on this Project. This possibility is an alternative to deleting the Project; disabling a Project makes is invisible on the Spaces page and hence not selectable when creating a new Space. Additionally, there is a trash icon next to the action button, with which a user can delete a Project. However, if the trash icon is grayed-out, it means that the Project is in use and cannot be deleted, which is also what is stated in the toolkit when hovering over the grayed-out trash icon. When the user wishes to delete a Project, which can be deleted, a *Delete* pop-up message appears, asking whether "you are sure you want to delete the project".

In summary, a particular Project can be used by various users in their Spaces, which demonstrates yet again the dependency relation.

### Project’s Identity Section
**Clicking the Project name** in the Project’s line opens a display of a 2-sided section, left and right. 

* The **left side** presents 2 sub-sections:
  * ***Name*** of the Project
  * ***Space Profiles***, a table presenting the Project Space Profiles, known as **Profiles**. The table is scrollable when containing a long Profiles list. It is **divided into 3 columns**:
    * The ***Name*** of the Profile, as it would appear on the Space card in the Spaces page.
    * ***Profiles***, which provides a general and basic description of the Project Space Profile. Regarded as a **base Space Profile**, it provides explanations, such as on which Fabric image it will be present, whether Studio will be there or not, will the Profile be with Postgres or with a system DB of some sort. This column may contain 1 Profile, a few, or a list of Profiles. 
    * ***Site*** is the location on which a Space can be created. It depends on and derives from the base Space Profile. In this column, 1 or more Sites are available for selection. This is where the Project’s owner or a member of K2view's CloudOps - decides of and defines the Site options to be used when Space creators create their Spaces (in the Spaces Page). For instance, it could be that a certain Profile can run or be supported on a specific Site only. Based on this factor, K2view's CloudOps member shall decide, while observing each Client’s requirements, which would be the most suitable Site for the Client with the given base Space Profile, and will accordingly create and allocate it to the Client.

Additionally, a new Profile can be added to the Project through the Space Profiles sub-section by clicking the '*Add Profile* **+**' button (INSERT 'ADD PROFILE+' IMAGE), and a vertical ellipsis (INSERT 'VERTICAL ELLIPSIS' IMAGE) is shown at the end of each Project Space Profile line, which directs the user to an *Advanced Settings* pop-up window.
  
* The **right side** presents the **‘GIT world’**, that is, the location of the saved Fabric Project (upon its implementation) in GIT. It contains the following 5 fields: The fields there can/need to be populated once.
  * ***Source Type***, which is currently 'Git'.
  * ***Repository URL***, the address of the location on Git; appears also in the line of each Project in the Projects list.
  * ***Path***, an optional field.
  * ***Branch / Tag***, a Git branch indicates the location of all Git files, which is essential for a Space creation, a process that includes branch-cloning. This field is optional here, as it can also be populated in the *Advanced Settings* pop-up window (accessible via the vertical ellipsis), where it would then override this field. Note that such population is reflected in the parentheses following the Project name on the Space card in the Spaces page.
  * ***Token***, Authentication on Git is done via a token, also known as Personal Access Token (‘PAT’), which is a hidden password. It is being used here for the purpose of cloning only, and for security reasons, after this specific usage it becomes non-existent.

> Any field with an asterisk next to its name, is mandatory, and it wouldn’t be possible to save the Project without populating it.

The Projects page is the one that dictates factors to the Spaces page, not before ascription and cross-reference within the tenant is orchestrated by a K2view's CloudOps member.

## Create a Project

To create a new Project:

1. Click on either the 'Create Project +' button at the top bar of the Projects page, or the bottom gray line, labelled as ‘Create Project’.
2. Populate a Project Name.
3. Add a Profile/s to your Project. In order to facilitate the Project Space profiling process, all this Project’s existing Space Profiles are visible and selectable as they were ascribed by K2view’s CloudOps per the Project requirements. The tendency is to name the new Project Space Profile like the base Space Profile. The Site will be available for selecting according to the chosen base Space Profile.

If you wish to add **a brand new Profile**, click on ‘Add Profile+’ icon and an empty Profile line would appear at the bottom of the list. Populate its Name, choose a base Space Profile, select an available Site and click the vertical ellipsis for opening the Advanced Settings pop-up window. The first 4 fields there are rather important for defining the Profile:
* Git Branch/Tag - indicates to the Project Space Profile whether to run on a specified Git branch (which indicates the location of all Git files) or be tagged (referencing a specific commit within the project history). The system is able to identify both. When not populated here, parameters will be taken from *Branch / Tag* field in he ‘GIT world’ in the Project’s Identity Section.
* Environment - each Fabric project lives on a certain environment. The latter can be defined in this field, and accordingly be set at the backend. The environment can also be mentioned - for setting - as part of the name, in the newly created Project Space Profile.
* Spaces Limit - this number states how many Spaces can be created on this Project Space Profile. This parameter serves the project owner for resources control.
* config.ini - 

