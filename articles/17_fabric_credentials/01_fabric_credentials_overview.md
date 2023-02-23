# Fabric Credentials Overview

## RBAC in Fabric 
Fabric includes an authentication and authorization mechanism that enables managing user access control and restrictions, such as:

- Creating / dropping users, roles and API Keys.
- Assigning users to roles.
- Granting access on different levels, for example:
  - Access to LUIs can be defined on a user level.
  - Access to the methods that access LUIs can be defined on a role level.
- Assigning security profiles to roles.

Using roles makes managing permissions much easier. It avoids having to manually grant sets of privileges user by user. For example, several users might be assigned as “administrators”. 

User access control management can be performed using either:

- [Fabric commands for user access management](/articles/17_fabric_credentials/02_fabric_credentials_commands.md).

- [Web Admin UI](/articles/30_web_framework/03_web_admin_application.md)

##  List of Permissions 
Roles are also used to maintain consistency across Fabric and be assigned with many or all of the following permission and actions types:


<table>
<tbody>
<tr>
<td width="300pxl">
<p><strong>PERMISSION</strong></p>
</td>
<td width="600pxl">
<p><strong>DESCRIPTION</strong></p>
</td>
</tr>
<tr>
<td width="300pxl">
<p><strong>ACTIVATE_KEY</strong></p>
</td>
<td width="600pxl">
<p>This permission is needed to generate a new key when using Fabric's key generator capability</p>
</td>
</tr>
<tr>
<td width="300pxl">
<p><strong>ASSIGN_ROLE</strong></p>
</td>
<td width="600pxl">
<p>This permission is needed to assign a role to a specific user</p>
</td>
</tr>
<tr>
<td width="300pxl">
<p><strong>EDIT_ROLE</strong></p>
</td>
<td width="600pxl">
<p>Used to modify the scope of permissions for a specific role permission</p>
</td>
</tr>
<tr>
<td width="300pxl">
<p><strong>BATCH</strong></p>
</td>
<td width="600pxl">
<p>Used to enable user to run batch processes</p>
</td>
</tr>
<tr>
<td width="300pxl">
<p><strong>DELETE_INSTANCE</strong></p>
</td>
<td width="600pxl">
<p>Used to delete one or multiple instances from Fabric</p>
</td>
</tr>
<tr>
<td width="300pxl">
<p><strong>DEPLOY/DEPLOY_ENVIRONMENTS</strong></p>
</td>
<td width="600pxl">
<p>Ability to run the deploy command on a project or entire environment</p>
</td></tr>
<tr>
<td width="300pxl">
<p><strong>QUERY_WS / READ / WRITE</strong></p>
</td>
<td width="600pxl">
<p>The ability to invoke a web service and to read or write data from Fabric instances and/or CommonDB</p>
</td></tr>
<tr>
<td width="300pxl">
<p><strong>SET_ENVIRONMENT / SET_GLOBAL_ENVIRONMENT / SET_GLOBAL_GLOBAL</strong></p>
</td>
<td width="600pxl">
<p>To set the environment or Globals for the current session onto which role is defined/p>
</td></tr>
<tr>
<td width="300pxl">
<p><strong>ALL_WS</strong></p>
</td>
<td width="600pxl">
<p>Allows all web-services related permissions</p>
</td></tr>
<tr>
<td width="300pxl">
<p><strong>WS_* / gr<LUNAME></strong></p>
</td>
<td width="600pxl">
<p>Allows specific web services and graphit files to be invoked</p>
</td></tr>
<tr>
<td width="300pxl">
<p><strong>ALL</strong></p>
</td>
<td width="600pxl">
<p>Allows above-mentioned permissions</p>
</td></tr>
</table>


## K2Auth Tables

Fabric database credentials are saved in Cassandra under the [k2auth keyspace](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md) in the following four tables:  

<table>
<tbody>
<tr>
<td width="300pxl">
<p><strong>Table Name</strong></p>
</td>
<td width="600pxl">
<p><strong>Table Description</strong></p>
</td>
</tr>
<tr>
<td width="300pxl">
<p>User Credentials</p>
</td>
<td width="600pxl">
<p>Holds Fabric users and their roles. A user may have several roles.</p>
</td>
</tr>
<tr>
<td width="300pxl">
<p>Roles</p>
</td>
<td width="600pxl">
<p>List of role definitions.</p>
</td>
</tr>
<tr>
<td width="300pxl">
<p>Credentials</p>
</td>
<td width="600pxl">
<p>Holds the API Key definitions of each role. The API Key is encrypted.</p>
</td>
</tr>
<tr>
<td width="300pxl">
<p>Permissions</p>
</td>
<td width="600pxl">
<p>Holds the permissions of each role and method.</p>
</td>
</tr>
</tbody>
</table>


Fabric database credentials are validated each time a user attempts to access Fabric via the console, [Web Services](/articles/15_web_services_and_graphit/01_web_services_overview.md) or other interfaces. Permissions can be set on an [LU](/articles/01_fabric_overview/02_fabric_glossary.md#lu--lut) level or an [LUI](/articles/01_fabric_overview/02_fabric_glossary.md#lui) level.

Note that to avoid authentication of a user on an LUI level, set **DISABLE_LUI_AUTH** in the [config.ini](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#configini) file to **true**. By default, this parameter is **false**.

It is also possible to skip the sync process between Fabric user and Cassandra user by setting **SYNC_CASSANDRA_SYSTEM_AUTH** in the [config.ini](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#configini) file to **false**. By default, this parameter is **true**.
## Setting Credentials

Create the users and define their credentials, as follows: 

- [Create a new user](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#create-user) and a [new role](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#create-role).
- [Assign a security profile to the role](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#assign-security_profile-security_profile-to-role-role).
- [Assign a role to the user](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#assign-role-role-to-user-user).
- [Create API Key](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#create-token)  and [assign a role to the API Key](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#assign-role-role-to-token-token).
- [Grant permissions to the role](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#grant-command).

- Exit Fabric and log in with this user or use the token to invoke a Web Service.

## Admin User

By default, Fabric creates the **admin** user as the initial superuser when starting for the first time and defines their user and password as **"admin"**. Fabric can also be started for the first time with another initial superuser that is not defined as admin/admin.  

- Copy the **adminInitialCredentials.template** file from the [$K2_HOME/fabric/config.template](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md) directory to the [$K2_HOME/config](/articles/02_fabric_architecture/02_fabric_directories.md#k2_homeconfig) directory.
- Change the **File Name** to **adminInitialCredentials**.
- Edit the file and update the **User**/**Password** to the required values. Note that the username must only contain lowercase letters.
- When Fabric starts for the first time the new user is created and the **adminInitialCredentials** file is deleted.

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/17_fabric_credentials/02_fabric_credentials_commands.md)
