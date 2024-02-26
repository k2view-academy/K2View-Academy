# Projects

A **Project** is a parameter that is outlined in a Cloud Space. It is composed of some parts and it describes 2 important and diverse factors - the location of a Fabric Project on GIT and the Profiles connected to a Project. The Project’s definitions are the basis for Spaces creation and a Space contains at least 3 main fields that derive from the Project.

## A Project's Life Cycle
A Project is started when created by an implementor, who also develops it and then pushes it to GIT. The implementor then has to pass it onwards to QA, production and pre-production teams. The Project has been made stable as it was added a GIT version and tag, and it is connected to a certain GIT branch. If someone else/a user wants to continue working and developing the same Project in GIT, but on another branch, he has to create a Space on the other branch of the Project. In such case, a Project Space Profile, known as Profile, is required. Every Project has its own Project Space Profile, which helps with the life cycle of the Project.
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

Upon entering the Projects page, you can see that Show All Projects is selected by default in its top bar; this brings about a display of all the Projects created by other users. The logic behind this is that the Project owner was most likely the one to define and create the Project. It is also possible to select Show My Projects for viewing Projects created by yourself. It is worth mentioning that the Cloud platform is multi-tenant, which enables each Customer to view his own Projects and Spaces only.
The Auto Refresh feature is turned off by default in the top bar, as it is less commonly used since Projects are not often created. If a user wishes to see a display of the most up-to-date Customer’s Projects list, he can simply click the Refresh button for an on-demand refresh.

INSERT IMAGE OF PROJECT PAGE’S TOP DEFAULT BAR

### Projects List
The Projects page presents the list of Projects in a line layout, where the user can open, close, save, etc. This repetitive visualization is kept throughout the k2cloud Orchestrator. 
Additionally, the Auto Refresh feature is turned on by default in the top bar as well, bringing about a display of the most up-to-date Spaces information. It can be turned on and off, and you can also click the Refresh button for an on-demand refresh.

Each Project’s line includes its name, source (a base source of GIT, GIT Repository), creation date, creator’s details, last modification date and an action button for disabling/enabling a Project. Disabling a Project is used when users should no longer be able to create more Spaces on this Project. This possibility is an alternative to deleting the Project; disabling a Project makes is invisible on the Spaces page and hence not selectable when creating a new Space. Additionally, there is a trash icon next to the action button, with which a user can delete a Project. However, if the trash icon is grayed-out, it means that the Project is in use and cannot be deleted, which is also what is stated in the toolkit when hovering over the grayed-out trash icon. When the user wishes to delete a Project, which can be deleted, a *Delete* pop-up message appears, asking whether "you are sure you want to delete the project".

In summary, various users can use a certain Project in their Space, which demonstrates yet again the dependency relation.

### Project’s Identity Section
Clicking the Project name in the Project’s line opens a display of a 2-sided section. The left side presents the name of the Project, and underneath it you shall see the ***Space Profiles*** section, i.e., the section that presents the Project Space Profiles, known as Profiles. When this section contains a list of Project Space Profiles, it can be scrolled down. Additionally, a new Profile can be added to the Project through this section (INSERT 'ADD PROFILE+' IMAGE). A vertical ellipsis (INSERT 'VERTICAL ELLIPSIS' IMAGE) is shown at the end of each Profile line, which is an option for further actions. The right side presents the ‘GIT world’, that is, the location of the saved Fabric Project (upon its implementation) in GIT. The fields there can/need to be populated once. 

> A field with an asterisk next to its name, is mandatory, and it wouldn’t be possible to save the Project without populating it.

The Profile is a general and basic description of the Project Space Profile. It provides descriptions, such as on which Fabric image it will be present, whether Studio will be there or not, will the Profile be with Postgres or with a system DB of some sort.

The Site is the location on which a Space can be created. In the Site column of the Space Profiles section, 1 or more Sites can be selected. This is where the Project’s owner decides of and defines the Site options to be used when Space creators create their Spaces (in the Spaces Page).

The Projects page is the one that dictates parameters for the Spaces page.

The ability to state where (i.e., choose the Site) depends on the Profile.
It could be that a certain Profile can run or be supported only on a specific Site, and this is decided by the k2view administrator, who observes each Client’s requirements and accordingly creates their Profile.
