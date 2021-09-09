# Access Permissions Setup

### Overview

Fabric users who want to implement the Business Intelligence capabilities can be divided into two groups: those who can setup and create the BI reports and those who can only view and run the reports. 

In order to enforce this differentiation, Fabric introduces the permission setup process divided into two steps, described further in this article:

1. [Fabric Role Permissions Setup](02_Permissions_Setup.md#Fabric-Role-Permissions-Setup) via the Fabric Admin application, to control who can access the BI Admin application.
2. [Access Level Setup](02_Permissions_Setup.md#Access-Level-Setup) using the Exago BI built-in access permissions mechanism, to control the activities such as Edit, Rename or Delete the reports within the BI Designer application. 

### Fabric Role Permissions Setup 

**BI Admin** within the BI application in the Web Framework allows you to:

* Create data sources and define their metadata (objects and joins).
* Create parameters for applying a filter within the reports.
* Update various system configurations (advanced).

Since these activities are supposed to be performed by limited number of users, Fabric provides the ability to control the user access to the above features. 

The **BI_ADMIN** permission is introduced in order to give access to the above features for specified user roles. By granting this permission to the Fabric role, the user with this role can perform the above activities.

Note that the ability to create new reports within the **BI Designer** is also enabled only to the user role with **BI_ADMIN** permission.

**How Do I Set Permissions in Fabric?**

Setup the **BI_ADMIN** permission in the Web Framework by clicking **Grant Permissions** in **Admin** > **Security** > **Permissions**:

<img src="images/permissions_setup_0.PNG" alt="image" />

### Access Level Setup

Exago access rules are defined in the Storage Management DB, per each folder (and report - if it differs from the folder access).

When the Fabric project is deployed, the <project name> folder is created in BI Designer and the default read-only access rule is assigned to all user roles.

To define the specific access level per Fabric role, run the Fabric command **set_bi_access**. Three access levels are defined:

* **Unrestricted** - the user can perform any activity within BI Designer, such as edit the report, rename it or delete it.
* **CreateContent** - the user can perform any activity except the delete.
* **ReadOnly** - the user can only view and copy the report.

**Note:**

- If the user that is logged into BI Designer is not assigned any access rules, he will get a read-only access to Exago folders and reports.
- If the logged user is an owner of a folder or a report, he will have full access to it even if no specific access rules were assigned to his role on the folder/report.

**How Do I Set Access Level?**

To define the access level to a Fabric role, run the Fabric command **set_bi_access** with the following input:

1. Folder name or report name (optional), if not provided the command is executed on the <project name> folder.
2. Path (optional), the full path to the folder from the root folder. Applicable if the folder is not a root.
3. Fabric role (mandatory).
4. Access level, one of the three values must be sent: 
   * Unrestricted / CreateContent / ReadOnly

The command will either create the permissions in the Storage Management DB or update the existing permissions.

**Examples**

* Give full access on **TDM** root folder to the **admin** user role:

```
SET_BI_ACCESS NAME="TDM" ROLE="admin" ACCESS_LEVEL="Unrestricted";
```

* Give limited access on **TDM/Reports/Load** folder to the **tester** user role: 

~~~
SET_BI_ACCESS NAME="Load" PARENTS="TDM/Reports" ROLE="tester" ACCESS_LEVEL="CreateContent";
~~~



[![Previous](/articles/images/Previous.png)](01_Installation.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_Metadata_Setup.md) 

