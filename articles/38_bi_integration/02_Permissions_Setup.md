# Access Permissions Setup

### Overview

Fabric users who want to implement business intelligence capabilities can be divided into two groups: those who are allowed to setup the Fabric BI application and create the reports, and those who can only view and run the reports. 

To enforce this differentiation, Fabric divides the permission setup process into two steps:

1. Assign the BI Admin permissions to predefined Fabric roles only. 
2. Setup the access level for Fabric BI content. This step is only available to the roles with BI ADMIN permission.

### BI ADMIN Permission Setup 

The **BI Admin** module of the **Fabric BI** application allows users to perform various system configurations as well as to setup the metadata for the reports. 
Only user roles with the **BI_ADMIN** (or **ALL**) permission can access the **BI Admin** module.

Note that the ability to create new reports within the **BI Designer** is also enabled only to users with the **BI_ADMIN** (or **ALL**) permission.

**How Do I Setup Access to BI Admin Module?**

Setup the access to **BI Admin** either by:

* Running the [GRANT command](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#grant-command)

  Or 

* Via the Web Framework by opening **Admin** > **Security** > **Permissions**, and clicking **Grant Permissions**. The following window will appear:

<img src="images/permissions_setup_0.PNG" alt="image" />

Select a Role name under Roles and **BI_ADMIN** under Methods to assign this method to the selected Role. Place an asterisk in the Resources field, and click **Grant** .  

### Access Level Setup

Fabric BI has a built-in access permissions mechanism that controls activities such as Edit reports, Rename reports or Delete the reports within the **BI Designer** module. The access rules are stored in the Storage Management DB.

The setup of the access level is performed by running the **SET_BI_ACCESS** Fabric command.

The following three access levels are defined:

* **Unrestricted** - the user can perform any activity within BI Designer, such as edit the report, rename it or delete it.
* **CreateContent** - the user can perform any activity except for delete.
* **ReadOnly** - the user can only view and copy the report.

**Note:**

- Since Fabric BI is based on ExagoBI, the names of the folders and reports are also based on ExagoBI.
- If the user that is logged into BI Designer is not assigned any access rules, he will get a read-only access to the Fabric BI (that is, ExagoBI) folders and reports.
- If the user is an owner of a folder or a report, he will have full access to it regardless of the access level assigned to his role on this folder/report.

**How Do I Set an Access Level?**

To define the access level to a Fabric role, run the Fabric command **set_bi_access** with the following input:

1. Folder name or report name (optional). If the folder or report name is not provided, the command is executed on the <project name> folder.
2. Path (optional) - the full path to the folder from the root folder. Applicable if the folder is not a root.
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

