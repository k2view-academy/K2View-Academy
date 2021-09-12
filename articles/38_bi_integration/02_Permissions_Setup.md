# Access Permissions Setup

### Overview

Fabric users who want to implement the Business Intelligence capabilities can be divided into two groups: those who can setup the BI application and create the reports and those who can only view and run the reports. 

In order to enforce this differentiation, Fabric introduces the permission setup process divided into two steps, described further in this article:

1. Assign the BI Admin permissions to predefined Fabric roles only. 
2. Setup the access level to BI content. This step is only available to the roles with BI Admin permission.


### BI ADMIN Permission Setup 

The **BI Admin** module of the **BI** application allows the users to perform various system configurations as well as to setup the metadata for the reports. 

Since these activities are supposed to be performed by limited number of users, Fabric enables controlling the user access to the **BI Admin** module. Only the user roles with the **BI_ADMIN** permission can access the **BI Admin**.

Note that the ability to create new reports within the **BI Designer** is also enabled only to the user role with **BI_ADMIN** permission.

**How Do I Setup Permissions in Fabric?**

Setup the **BI_ADMIN** permission either by:

* Running the [GRANT command](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#grant-command).
* Or, via the Web Framework **Admin** > **Security** > **Permissions** by clicking **Grant Permissions**.

<img src="images/permissions_setup_0.PNG" alt="image" />

### Access Level Setup

Exago BI has a built-in access permissions mechanism, to control the activities such as Edit, Rename or Delete the reports within the **BI Designer** application. The access rules are stored in the Storage Management DB.

The setup of the access level is performed by running the **SET_BI_ACCESS** Fabric command.

Three access levels are defined:

* **Unrestricted** - the user can perform any activity within BI Designer, such as edit the report, rename it or delete it.
* **CreateContent** - the user can perform any activity except for delete.
* **ReadOnly** - the user can only view and copy the report.

**Note:**

- If the user that is logged into BI Designer is not assigned any access rules, he will get a read-only access to Exago folders and reports.
- If the user is an owner of a folder or a report, he will have full access to it regardless of the access level assigned to his role on this folder/report.

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

* Give create/edit access on **Reports** folder which is a **TDM**'s child folder to the **Implementer** user role: 

~~~
SET_BI_ACCESS NAME="Reports" PARENTS="TDM" ROLE="Implementer" ACCESS_LEVEL="CreateContent";
~~~

* Give read-only access on **TDM/Reports/Load** folder to the **Tester** user role: 

~~~
SET_BI_ACCESS NAME="Load" PARENTS="TDM/Reports" ROLE="Tester" ACCESS_LEVEL="ReadOnly";
~~~



[![Previous](/articles/images/Previous.png)](01_Installation.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_Metadata_Setup.md) 

