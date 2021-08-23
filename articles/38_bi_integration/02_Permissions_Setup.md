# Access Permissions Setup

### Overview

Fabric has [roles and permissions](/articles/17_fabric_credentials/01_fabric_credentials_overview.md) for managing authentication and authorization mechanism. Exago BI has a built-in mechanism to control the access and ownership of content by different identity keys. The described below process explains how to setup different levels of access in BI Designer, based on the combination of Fabric credentials and Exago access rules.

The permissions setup is divided into 2 steps:

1. Assign BI permission to Fabric roles in Fabric Admin.
2. Setup the access rules in the BI Storage Management DB.

### Fabric Role Permissions 

Grant BI_ADMIN permission to the user roles that need to have the following:

* Access the BI Admin within the Web Framework.
* Ability to create new reports.

**How Do I Set Permissions in Fabric?**

Setup the BI related permissions in Fabric by clicking **Grant Permissions** in the Web Framework Admin > Security > Permissions. 

<img src="images/permissions_setup_0.PNG" alt="image" />

### Access Rules within BI

When the Fabric project is deployed, the <project name> folder is created in BI Designer and the default read-only permissions are assigned to all user roles to this folder.

To define the access rules (e.g. Can View, Can Copy, Can Rename) per Fabric role, run the Fabric command **set_bi_access**.

Note:

- If the user that is logged into BI Designer is not assigned any access rules, he will get a read-only access to Exago folders and reports.
- If the logged user is an owner of a folder or a report, he will have full access to it even if no specific access rules were assigned to his role on the folder/report.

**How Do I Set Access Rules?**

Exago Storage Management is a DB that keeps the definition of all reports, templates, folders and themes. It also keeps the access permissions to folders and reports, using the Content Permissions model based on 4 basic identity keys.

[Click for more information about Exago Identity Keys](https://support.exagoinc.com/hc/en-us/articles/360042587313#h_2ffb7d21-1f58-47bd-957d-55a4eace7ef0).

To define the access rules, run the Fabric command **set_bi_access** with the following input:

1. Folder name (or report name).
2. Fabric role.
3. Array of access flags (CanView, CanCopy, etc.)

The command will either create the permissions in the Storage Management DB or update the existing.



[![Previous](/articles/images/Previous.png)](01_Installation.md)

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_Metadata_Setup.md) 

