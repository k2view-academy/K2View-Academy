# Fabric Credentials Overview

 Fabric includes an authentication mechanism that enables managing user access control and restrictions, such as:

- Creating / dropping users, roles and tokens.
- Assigning users to roles.
- Granting access on different levels, for example:
  - Access to LUIs can be defined on a user level.
  - Access to the methods that access LUIs can be defined on a role level.

User access control management can be performed using either:

- [Fabric commands for user access management](/articles/17_fabric_credentials/02_fabric_credentials_commands.md).

- Web Admin UI

  <!-- Add a link- drop 4- Fabric Web Admin -->

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
<p>Holds the token definitions of each role. The token is encryted.</p>
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

Fabric database credentials are validated each time a user attempts to access Fabric via the console, [Web Services](/articles/15_web_services/01_web_services_overview.md) or other interfaces. Permissions can be set on an [LU](/articles/01_fabric_overview/02_fabric_glossary.md#lu--lut) level or an [LUI](/articles/01_fabric_overview/02_fabric_glossary.md#lui) level.

Note that to avoid authentication of a user on an LUI level, set **DISABLE_LUI_AUTH** in the [config.ini](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#configini) file to **true**. By default, this parameter is **false**.

## Setting Credentials

Create the users and define their credentials, as follows: 

- [Create a new user](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#create-user) and a [new role](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#create-role).
- [Assign the role to the user](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#assign-role-role-to-user-user).
- [Create a token](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#create-token)  and [assign a role to the token](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#assign-role-role-to-token-token).
- [Grant the permissions to the role](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#grant-command).

- Exit Fabric and log in with this user or use the token to invoke a [Web Service]().

## Admin User

By default, Fabric creates the **admin** user as the initial superuser when starting for the first time and defines their user and password as **"admin"**. Fabric can also be started for the first time with another initial superuser that is not defined as admin/admin.  

- Copy the **adminInitialCredentials.template** file from the [$K2_HOME/fabric/config.template](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md) directory to the [$K2_HOME/config](/articles/02_fabric_architecture/02_fabric_directories.md#k2_homeconfig) directory.
- Change the **File Name** to **adminInitialCredentials**.
- Edit the file and update the **User**/**Password** to the required values. Note that the username must only contain lowercase letters.
- When Fabric starts for the first time the new user is created and the **adminInitialCredentials** file is deleted.

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/17_fabric_credentials/02_fabric_credentials_commands.md)
