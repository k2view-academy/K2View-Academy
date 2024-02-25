# Projects

A **Project** is one of three parameters outlined in a Cloud Space. It is composed of some parts and it describes 2 important and diverse factors - the location of a Fabric Project on GIT and the Profiles connected to a Project. The Project’s definitions are the basis for Spaces creation and a Space contains at least 3 main fields that derive from the Project.

## A Project's Life Cycle
A Project is started when created by an implementor, who also develops it and then pushes it to GIT. The implementor then has to pass it onwards to QA, production and pre-production teams. The Project has been made stable as it was added a GIT version and tag, and it is connected to a certain GIT branch. If someone else/a user wants to continue working and developing the same Project in GIT, but on another branch, he has to create a Space on the other branch of the Project. In such case, a Project Space Profile, known as Profile, is required. Every Project has its own Project Space Profile, which helps with the life cycle of the Project.
Additionally, each Customer’s/Organization’s/Client’s user type – such as implementor, QA, production, etc. – belongs to a separate business operation unit, works on a different Site, which is based on different permissions. This Site separation prevents interferences between the various Organization’s business operation units, but more importantly, the Site is a part of the Project Space Profile’s definition.
In case the Customer/Organization (Client) makes some changes to his Project, namely, implementation and enhancements, as part of the Project’s life cycle and in case the user wishes to upgrade Fabric per official releases, these acts are made possible via the Project Space Profile. The latter is being fed along with additional relevant data whilst creating a Project. After saving the Project, it can be selected when creating a new Space. This means that the Project is alive, and up for updates per the needs of the Project’s life cycle.

If a developer has now created a new branch in GIT as he wishes to continue the development, he would want the Project to be updated in the Projects page.
In case an old unused branch exists, it can simply be deleted in order to avoid being an extra load in the Project Space Profiles section. When deleted, the Project Space Profile, known as Profile, will be neither visible nor available for selection when creating a Space in the Spaces page. For this reason, when creating a Space, the selection order should first be a Project, followed by a Profile - provisioned in the Projects page - followed by a Site, which is a choice derived from the selected Project Space Profile, i.e., Profile. This relation can be regarded as dependency relation.
In summary, the aim of the Projects page is to provision a Project there and to accompany the Fabric Project throughout its Project’s life cycle, since it is alive and ongoing as changes constantly take place. 
A Customer/Organization/Client may have between 1 and 2 Projects, but many Project Space Profiles.  
A best practice would be to have one single GIT Project or one single Fabric Project that represents a Project in its logical sense at the Customer’s/Organization’s/Client’s end.

## Projects Page Layout
The Projects page is reached by accessing the k2cloud Orchestrator on https://cloud.k2view.com/, followed by a switch in the top bar from the Spaces to the Projects page.
INSERT IMAGE OF TOP BAR OF k2cloud Orchestrator AFTER IT’S SWITCHED TO PROJECTS PAGE
