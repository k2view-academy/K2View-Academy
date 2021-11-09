# Access Permissions Setup

### Overview

Fabric users who want to implement business intelligence capabilities can be divided into two groups: those who are allowed to setup the BI application and create the reports, and those who can only view and run the reports. 

To enforce this differentiation, Fabric divides the permission setup process into two steps:

1. Assign the BI ADMIN permissions to predefined Fabric roles only. 
2. Setup the access level for BI content. 

Note that only Fabric admin can do these activities. 

### BI ADMIN Permission Setup 

The **Admin** module of the **BI** application allows users to perform various system configurations as well as to set up the metadata for the reports. 
Only user roles with the **ALL** or **BI_ADMIN** permission can access the **Admin** module of **BI**.

Note that the ability to create new reports within the **Designer** module is also enabled only to users with the **ALL** or **BI_ADMIN** permission.

**How Do I Setup Access to the Admin Module?**

Setup the access to the **Admin** module either by:

* Running the [GRANT command](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#grant-command)

  Or 

* Via the Web Framework by opening **Admin** > **Security** > **Permissions**, and clicking **Grant Permissions**. The following window will appear:

<img src="images/permissions_setup_0.PNG" alt="image" />

Select a Role name under Roles and **BI_ADMIN** under Methods to assign this method to the selected Role. Place an asterisk in the Resources field, and click **Grant** .  

### Access Level Setup

**Fabric's BI** solution has a built-in access permissions mechanism that controls activities such as Edit reports, Rename reports or Delete the reports within the **Designer** module. 

The setup of the access level is performed by running the **SET_BI_ACCESS** Fabric command which is executed on the Fabric and populates access rules in the Storage Management DB. 

The following three access levels are defined:

* **Unrestricted** - the user can perform any activity within Designer, such as edit the report, rename it or delete it.
* **CreateContent** - the user can perform any activity except for delete.
* **ReadOnly** - the user can only view and copy the report.

**Note:**

- Only the PostgreSQL Storage Management type is supported by the command. 
- If the user that is logged into BI is not assigned any access rules, he will get a read-only access to the BI folders and reports.
- If the user is an owner of a folder or a report, he will have full access to it regardless of the access level assigned to his role on this folder/report.

**How Do I Set an Access Level?**

To define the access level to a Fabric role in the context of BI, run the Fabric command **set_bi_access** with the following input:

1. NAME, folder name or report name. Note that the command is executed on the reports/folders under the **project name** folder of the deployed project.
2. PARENTS, path to the report (optional) - the full path to the folder from the root folder. Applicable if the folder provided in NAME is not a root.
3. ROLE, Fabric role (mandatory).
4. ACCESS_LEVEL, the access level to reports within the Designer module. One of the following values must be sent: 
   * Unrestricted / CreateContent / ReadOnly

The command will either create the permissions in the Storage Management DB or update the existing permissions.

**Examples of Running a Command on a TDM Project **

* Give full access on **TDM** root folder to the **admin** user role:

```bash
SET_BI_ACCESS NAME="TDM" ROLE="admin" ACCESS_LEVEL="Unrestricted";
```

* Give create/edit access on **Reports** folder which is a **TDM**'s child folder to the **Implementer** user role: 

~~~bash
SET_BI_ACCESS NAME="Reports" PARENTS="TDM" ROLE="Implementer" ACCESS_LEVEL="CreateContent";
~~~

* Give read-only access on **TDM/Reports/Load** folder to the **Tester** user role: 

~~~bash
SET_BI_ACCESS NAME="Load" PARENTS="TDM/Reports" ROLE="Tester" ACCESS_LEVEL="ReadOnly";
~~~



[![Previous](/articles/images/Previous.png)](01_Installation.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_Metadata_Setup.md) 

