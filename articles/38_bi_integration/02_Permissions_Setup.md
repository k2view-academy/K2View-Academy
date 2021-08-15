# Access Permissions Setup

### Overview

Fabric has [roles and permissions](/articles/17_fabric_credentials/01_fabric_credentials_overview.md) for managing authentication and authorization mechanism. Exago BI has a built-in mechanism to control the access and ownership of content by different identity keys. The described below process explains how to setup different levels of access in Exago BI, based on the combination of Fabric credentials and Exago access rules.

The permissions setup is divided into 2 steps:

1. Setup the access rules in the Storage Management.
2. Assign BI permission to Fabric roles in Fabric Admin.

3 dedicated permissions are defined in Fabric to control different levels of access within BI applications: BI_ADMIN, BI_DESIGNER and BI_TESTER. The exact definition of each permission level can be given by each implementation, for example:

- **BI_ADMIN** has full unrestricted access to folders and reports within BI application.
- **BI_DESIGNER** has almost full access to Exago folders and reports except for ability to Delete.
- **BI_TESTER** provides read-only access to Exago folders and reports.

### Access Rule in Exago

* Define the Access flags (e.g. Can View, Can Copy, Can Rename) in the Storage Management DB for all Exago folders/reports - per Fabric role, permission and project by either:

  * The Fabric command **set_bi_access**.

  * Or, The Storage Management Utility. 

  * The Party ID on each access definition should be defined as follows: 

    ~~~
    <Fabric role>_<BI Permission>_<Project Name>
    ~~~

    For example: admin_BI_ADMIN_TDM.

* The activities of Report Creation and Report Download have a different restriction mechanism within Exago, thus require a separate setting. If you need to change the default setting, it can be done via the config.ini. The default setting is:
  - **Create Report** is allowed to BI_ADMIN and BI_DESIGNER for all Fabric roles.
  - **Download Report** is allowed to BI_ADMIN, BI_DESIGNER and BI_TESTER for all Fabric roles.

Note:

* The logged in user that is not assigned any of the above permissions will get a read-only access to Exago folders and reports.
* The Exago folders and reports that don't have Access flags definition in the Storage Management DB will be available to any logged in user.
* If the logged in user is an owner of a folder or a report, he will have full access to it even if he doesn't have permissions assigned.

### Permissions in Fabric

- Grant one of new permissions BI_ADMIN, BI_DESIGNER and BI_TESTER to the relevant user roles. The default definition of the permissions are:

### 

### How Do I Set Permissions in Fabric?

Setup the BI related permissions in Fabric by clicking **Grant Permissions** in the Web Framework Admin > Security > Permissions. 

<img src="images/permissions_setup_0.PNG" alt="image" />

### How Do I Set Access Rules in Exago?

Exago Storage Management is a DB that keeps the definition of all reports, templates, folders and themes. It also keeps the access permissions to folders and reports, using the Content Permissions model based on 4 basic identity keys and the priority between them:

<img src="images/permissions_setup_1.PNG" alt="image" style="zoom:67%;" />



[Click for more information about Exago Identity Keys](https://support.exagoinc.com/hc/en-us/articles/360042587313#h_2ffb7d21-1f58-47bd-957d-55a4eace7ef0).

TBD