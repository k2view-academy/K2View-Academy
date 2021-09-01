# Access Permissions Setup

### Overview

Fabric users who want to implement the Business Intelligence capabilities can be divided into two groups: those who can setup and create the BI reports and those who can only run them. In order to enforce this differentiation, Fabric introduces the permission setup processes divided into two steps, described further in this article:

1. [Fabric Role Permissions](02_Permissions_Setup.md#Fabric-Role-Permissions) definition via the Fabric Admin application to control the access to BI Admin application.
2. [Access Rules Setup](02_Permissions_Setup.md#Access-Rules-Setup) using the Exago BI built-in access permissions mechanism to control the activities such as Edit, Rename or Delete the reports within the BI Designer application. 

### Fabric Role Permissions 

**BI Admin** within the BI application in the Web Framework allows you to:

* Create data sources and define their metadata (objects and joins).
* Create parameters for applying a filter within the reports.
* Update various system configurations (advanced).

Since these activities are supposed to be performed only by specific kinds of users, Fabric provides the ability to control the user access to the above features. 

The **BI_ADMIN** permission is introduced in order to give access to the above features for specified user roles. By granting this permission to the Fabric role, the user with this role can perform the above activities.

Note that the ability to create new reports within the **BI Designer** is also enabled only to the user role with **BI_ADMIN** permission.

**How Do I Set Permissions in Fabric?**

Setup the **BI_ADMIN** permission in the Web Framework by clicking **Grant Permissions** in Admin > Security > Permissions:

<img src="images/permissions_setup_0.PNG" alt="image" />

### Access Rules Setup

Exago access rules are defined in the Storage Management DB, per each folder (and report - if it differs from the folder access).

When the Fabric project is deployed, the <project name> folder is created in BI Designer and the default read-only access rule is assigned to all user roles in this folder.

To define the specific access rules (e.g. Can View, Can Copy, Can Rename) per Fabric role, run the Fabric command **set_bi_access**.

**Note:**

- If the user that is logged into BI Designer is not assigned any access rules, he will get a read-only access to Exago folders and reports.
- If the logged user is an owner of a folder or a report, he will have full access to it even if no specific access rules were assigned to his role on the folder/report.

**How Do I Set Access Rules?**

Exago Storage Management is a DB that keeps the definition of all reports, templates, folders and themes. It also keeps the access rules to folders and reports, using the Content Permissions model based on 4 basic identity keys.

[Click for more information about Exago Identity Keys](https://support.exagoinc.com/hc/en-us/articles/360042587313#h_2ffb7d21-1f58-47bd-957d-55a4eace7ef0).

To define the access rules, run the Fabric command **set_bi_access** with the following input:

1. Folder name or report name (optional), if not provided the command is executed on the <project name> folder.
2. Fabric role (mandatory).
3. Array of access flags (mandatory) - provide only those that should be **true**: 
   * CanEdit
   * CanRename
   * CanShare
   * CanDelete
   * CanCopy
   * CanMove
   * CanSchedule (placeholder, will be supported in later versions)
   * CanCopy

The command will either create the permissions in the Storage Management DB or update the existing permissions.



[![Previous](/articles/images/Previous.png)](01_Installation.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_Metadata_Setup.md) 

