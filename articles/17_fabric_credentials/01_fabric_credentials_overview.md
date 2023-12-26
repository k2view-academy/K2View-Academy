# Fabric Credentials Overview

## RBAC in Fabric 
Fabric includes an authentication and authorization mechanism that enables managing user access control and restrictions, such as:

- Creating / dropping users, roles and API Keys.
- Assigning users to roles.
- Granting access on different levels, for example:
  - Access to LUIs can be defined on a user level.
  - Access to the methods that access LUIs can be defined on a role level.
- Assigning security profiles to roles.

The use of roles makes permissions management much easier. It avoids having to manually grant sets of privileges, user by user. For example, several users may be assigned as 'administrators'.

User access control management can be performed by using either one of these two methods:

- [Fabric commands for user access management](/articles/17_fabric_credentials/02_fabric_credentials_commands.md)

- [Web Admin UI](/articles/30_web_framework/03_web_admin_application.md)

##  List of Permissions 
Roles are assigned with many or all of the following permissions:


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
<p>This permission is needed for generating a new key when using Fabric's key generator capability</p>
</td>
</tr>
<tr>
<td width="300pxl">
<p><strong>ASSIGN_ROLE</strong></p>
</td>
<td width="600pxl">
<p>This permission is needed for assigning a role to a specific user</p>
</td>
</tr>
<tr>
<td width="300pxl">
<p><strong>EDIT_ROLE</strong></p>
</td>
<td width="600pxl">
<p>Used for modifying the scope of permissions for a specific role permission</p>
</td>
</tr>
<tr>
<td width="300pxl">
<p><strong>BATCH and BATCH_READ</strong></p>
</td>
<td width="600pxl">
<p>BATCH permission enables the user to run and view batch processes.</p>
<p>BATCH_READ permission enables the user to view batch processes.</p>  
</td>
</tr>
<tr>
<td width="300pxl">
<p><strong>DELETE_INSTANCE</strong></p>
</td>
<td width="600pxl">
<p>Used for the deletion of one or multiple instances from Fabric</p>
</td>
</tr>
<tr>
<td width="300pxl">
<p><strong>DEPLOY/DEPLOY_ENVIRONMENTS</strong></p>
</td>
<td width="600pxl">
<p>Ability to run the deploy command on either a project or an entire environment</p>
</td></tr>
<tr>
<td width="300pxl">
<p><strong>QUERY_WS / READ / WRITE</strong></p>
</td>
<td width="600pxl">
<p>The ability to invoke a web service and to read/write data from Fabric instances and/or CommonDB</p>
</td></tr>
<tr>
<td width="300pxl">
<p><strong>SET_ENVIRONMENT / SET_GLOBAL_ENVIRONMENT / SET_GLOBAL_GLOBAL</strong></p>
</td>
<td width="600pxl">
<p>Used for setting the environment or Globals for the current session onto which a role is defined/p>
</td></tr>
<tr>
<td width="300pxl">
<p><strong>ALL_WS</strong></p>
</td>
<td width="600pxl">
<p>Allows all web services-related permissions</p>
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
<p>Allows the above-mentioned permissions</p>
</td></tr>
</table>


## K2Auth Tables

Fabric database credentials are saved in Cassandra under the [k2auth keyspace](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md) in the following 4 tables:  

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


Fabric database credentials are validated each time a user attempts to access Fabric via the console, [Web Services](/articles/15_web_services_and_graphit/01_web_services_overview.md) or other interfaces. Permissions can be set on either an [LU](/articles/01_fabric_overview/02_fabric_glossary.md#lu--lut) or an [LUI](/articles/01_fabric_overview/02_fabric_glossary.md#lui) level.

Note that to avoid authentication of a user on an LUI level, set **DISABLE_LUI_AUTH** in the [config.ini](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#configini) file to **true**. By default, this parameter is set to **false**.

It is also possible to skip the sync process between Fabric user and System DB (e.g. Cassandra) user by setting **READ_ONLY_AUTHENTICATORS** in the [config.ini](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#configini) file to **true**. By default, this parameter is set to **false**.

## Setting Credentials
Define credentials by either Admin UI (Security tab) or Fabric commands, as follows: 
- Create a new role ([command](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#create-role)).
- Assign a security profile to a role ([command](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#assign-security_profile-security_profile-to-role-role)).
- Create an API Key ([command](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#create-token)).
- Assign a role to an API Key ([command](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#assign-role-role-to-token-token)).
- Grant permissions to a role ([command](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#grant-command)).

### Bootstrap Credentials
Fabric can also be started for the first time with predefines apikeys, roles and permissions.
- Copy the **rolesPrivileges.template** file from the [$K2_HOME/fabric/config.template](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md) directory to the [$K2_HOME/config](/articles/02_fabric_architecture/02_fabric_directories.md#k2_homeconfig) directory.
- Change the file name to **rolesPrivileges.json**.
- Edit the file with the required values.
   - Define roles and associated operations, at `"roles"` array object.
   - Define apikeys and their association to roles, at `"apikeys"` array object.
- When Fabric starts for the first time, it looks for this file and if exists apply its definitions. 

Note: You can define only the operations and roles because resourses do not exist yet, as project was not deployed yet.

Example:
```
{
    "roles": {
        "deploy": ["DEPLOY", "DEPLOY_ENVIRONMENTS", "SET_GLOBAL_ENVIRONMENT"]
    },
    "apikeys": {
        "t1234": ["deploy"]
    }
}
```
 

## Users Credentials
Users might be defined at Fabric system DB and accoridngly shall be associated to roles, as follows. 
- Assign a role to the user by either Admin UI (Security tab) or ([by command](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#assign-role-role-to-user-user)).

Note: When Fabric integrates with an external authenitcator, Fabric does not manage and store users information and users permissions are applied by roles. For more infomration about User Identification and Access Management read [here](/articles/26_fabric_security/07_user_IAM_overview.md).

### Admin User

By default, Fabric creates the **admin** user as the initial superuser when starting for the first time and defines their user and password as **"admin"**. Fabric can also be started for the first time with another initial superuser that is not defined as admin/admin.  

- Copy the **adminInitialCredentials.template** file from the [$K2_HOME/fabric/config.template](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md) directory to the [$K2_HOME/config](/articles/02_fabric_architecture/02_fabric_directories.md#k2_homeconfig) directory.
- Change the **File Name** to **adminInitialCredentials**.
- Edit the file and update the **User**/**Password** to the required values. Note that the username must only contain lowercase letters.
- When Fabric starts for the first time, the new user is created and the **adminInitialCredentials** file is deleted.
- There is no need to provide a password on **adminInitialCredentials** file when the users are maintained outside of Fabric (when the **READ_ONLY_AUTHENTICATORS** parameter in the config.ini is set to true).

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/17_fabric_credentials/02_fabric_credentials_commands.md)
