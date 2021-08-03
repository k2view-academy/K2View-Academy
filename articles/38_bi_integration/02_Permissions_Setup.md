# Permissions Setup

Permissions setting is divided into 2 steps:

1. Permissions in Fabric:
   * Grant one of new permissions BI_ADMIN, BI_DESIGNER and BI_TESTER to the relevant user roles. The default definition of the permissions are:
     * BI_ADMIN provides full unrestricted access to Exago folders and reports.
     * BI_DESIGNER provides almost full access to Exago folders and reports except for ability to Delete.
       * *TBD - to update*
     * BI_TESTER provides read-only access to Exago folders and reports.
2. Access Rule in Exago:
   * Define the Access flags (e.g. Can View, Can Copy, Can Rename) in the Storage Management DB for all Exago folders/reports - per Fabric role, permission and project.
   * (Optional) Change  the default setting of the following application areas: Create Report and Download Report. The default setting is:
     - Create Report is allowed to BI_ADMIN and BI_DESIGNER.
     - Download Report is allowed to BI_ADMIN, BI_DESIGNER and BI_TESTER.

Note:

* The logged in user that is not assigned any of the above permissions will get a read-only access to Exago folders and reports.
* The Exago folders and reports that don't have Access flags definition in the Storage Management DB will be available to any logged in user.
* If the logged in user is an owner of a folder or a report, he will have full access to it even if he doesn't have permissions assigned.

### How Do I Set Permissions in Fabric?

Setup the BI related permissions in Fabric by clicking **Grant Permissions** in the Web Framework Admin > Security > Permissions. 

<img src="images/permissions_setup_0.PNG" alt="image" />

### How Do I Set Access Rules in Exago?

Exago Storage Management is a DB that keeps the definition of all reports, templates, folders and themes. It also keeps the access permissions to folders and reports, using the Content Permissions model based on 4 basic identity keys and the priority between them:

<img src="images/permissions_setup_1.PNG" alt="image" style="zoom:67%;" />



[Click for more information about Exago Identity Keys](https://support.exagoinc.com/hc/en-us/articles/360042587313#h_2ffb7d21-1f58-47bd-957d-55a4eace7ef0).

TBD