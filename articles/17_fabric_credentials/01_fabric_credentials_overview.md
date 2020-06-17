# Fabric Credentials Overview

 Fabric includes an authentication mechanism that enables managing user access control and restrictions, such as:

- Creating / dropping users, roles and tokens.
- Assigning users to roles.
- Granting access on different levels, for example:
  - Access to LUIs can be defined on a user level.
  - Access to the methods that access LUIs can be defined on a role level.

User access control management can be performed using either:

- [Fabric commands for user access management]( <!— add link to #2-->).

- Web Admin UI

  <!-- Add a link- drop 4- Fabric Web Admin -->).

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

Fabric database credentials are validated each time a user attempts to access Fabric via the console, Web Service or other interfaces. Permissions can be set on an [LUT](/articles/01_fabric_overview/02_fabric_glossary.md#lu--lut) level or an [LUI](/articles/01_fabric_overview/02_fabric_glossary.md#lui) level.

Note that to avoid authentication of a user on an instance level, set the DISABLE_LUI_AUTH parameter in the config.ini file to **true.** By default, this parameter is **false.**

## Setting Credentials

Before using Fabric, create the users and define their credentials, as follows: 

- [Create a new user](<!— add link to #2 – sub-section Create-->) and a [new role](<!— add link to #2 – sub-section Create-->).
- [Assign the role to the user](<!— add link to #2 – sub-section Assign-->).
- [Create a token](<!— add link to #2 – sub-section Create-->)  and [assign it to the role](<!— add link to #2 – sub-section Assign-->).
- [Grant the permissions to the role](<!— add link to #2 – sub-section Grant-->).

- Exit Fabric and log in with this user or use the token to invoke a [Web Service]().

## Admin User

By default, Fabric creates the **admin** user as an initial superuser when starting for the first time: the user and password are set to "admin". 

It is possible to start Fabric for the first time with a different initial superuser than admin/admin:

- Copy **adminInitialCredentials.template** file from [$K2_HOME/fabric/config.template](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md) directory to [$K2_HOME/config](/articles/02_fabric_architecture/02_fabric_directories.md#k2_homeconfig) directory and change the file name to **adminInitialCredentials**.
- Edit the file and update the user/password to the required user and password value. Note that user name must only  contain lowercase letters.
- When Fabric starts for the first time the new user is created and After fabric starts the **adminInitialCredentials** file is deleted.