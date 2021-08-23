# Fabric Credentials Commands

The following tables discuss how user access control is managed using Fabric commands.

## CREATE Command

**CREATE** commands are used to create a user, role or token in the [k2auth keyspace](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md) in Cassandra:

<table width="900pxl">
<tbody>
<tr>
<td width="200pxl">
<p><h4>CREATE USER</p>
</td>
<td width="700pxl">
<p><strong>Description</strong>: Create a Fabric user. The user is saved in the system_auth.roles Cassandra table.</p>
<p><strong>Usage</strong>:&nbsp;</p>
<p>CREATE USER &lt;'username'&gt; [ WITH PASSWORD &lt;'password'&gt; ] [SUPERUSER | NOSUPERUSER | (empty) ]</p>
<p><strong>Parameters:</strong></p>
<ul>
<li>&lt;'username'&gt; &ndash; mandatory, user name.</li>
<li>[WITH PASSWORD &lt;'password'&gt;] &ndash; optional, password.</li>
<li>[SUPERUSER | NOSUPERUSER | (empty) ] &ndash; optional, indicates whether the user is a superuser (admin) or not. If empty, the user is not a superuser.</li>
</ul>
<p><strong>Examples:</strong></p>
<ul>
<li>Create a regular user named&nbsp;<strong>test_user</strong>&nbsp;without a password:&nbsp;
<ul>
<li>create user test_user;</li>
</ul>
</li>
<li>Create a superuser named&nbsp;<strong>sup_user </strong>without a password:
<ul>
<li>create a <strong>sup_user</strong> user without a password and as a superuser.</li>
</ul>
</li>
<li>Create a regular user named <strong>psw_user</strong> with password '12345':
<ul>
<li>create user psw_user with password &lsquo;12345&rsquo;;</li>
</ul>
</li>
</ul>
</td>
</tr>
<tr>
<td width="200pxl">
<p><h4>CREATE ROLE</p>
</td>
<td width="700pxl">
<p><strong>Description</strong>: Create a new role with a description.</p>
<p><strong>Usage</strong>:&nbsp;</p>
<p>CREATE ROLE &lt;'role_name'&gt; [description &lt;'role description'&gt;]</p>
<p><strong>Parameters:</strong></p>
<ul>
<li>&lt;'role_name'&gt; &ndash; mandatory, role name.</li>
<li>[description &lt;'role description'&gt;] &ndash; optional, role description.</li>
</ul>
<p><strong>Examples:</strong></p>
<ul>
<li>Create a role named&nbsp;<strong>test_role</strong>&nbsp;without a description:
<ul>
<li>create role 'test_role';</li>
</ul>
</li>
<li>Create a role named&nbsp;<strong>test_role</strong> role with a description:
<ul>
<li>create role 'test_role' description 'test the desc';</li>
</ul>
</li>
</ul>
</td>
</tr>
<tr>
<td width="200pxl">
<p><h4>CREATE TOKEN</p>
</td>
<td width="700pxl">
<p><strong>Description</strong>: Create a new API Key.</p>
<p><strong>Usage</strong>:</p>
<p>CREATE TOKEN &lt;'token_name'&gt; [SECURED]</p>
<p><strong>Parameters:</strong></p>
<ul>
<li>&lt;'token_name'&gt; &ndash; mandatory, API Key name.</li>
<li>SECURED &ndash; optional in case of a secured API Key.</li>    
</ul>
<p><strong>Examples:</strong></p>
<ul>
<li>Create a <strong>test_token</strong> token. Do not assign it to any user:
<ul>
<li>create token 'test_token';</li>
</ul>
</li>
</ul>
</td>
</tr>
</tbody>
</table>





## ASSIGN Command

**ASSIGN** commands are used to assign a role either for a user or for a token:

<table width="900pxl">
<tbody>
<tr>
<td width="400pxl">
<p><h4>ASSIGN ROLE &lt;ROLE&gt; to user &lt;USER&gt;</p>
</td>
<td width="500pxl">
<p><strong>Description</strong>: Assign a role to a user.</p>
<p><strong>Usage</strong>:</p>
<p>ASSIGN ROLE &lt;role&gt; to USER &lt;user&gt;</p>
<p><strong>Example: </strong></p>
<p>assign role role1 to user test_user;</p>
</td>
</tr>
<tr>
<td width="400pxl">
<p><h4>ASSIGN ROLE &lt;ROLE&gt; to token &lt;API Key&gt;</p>
</td>
<td width="500pxl">
<p><strong>Description</strong>: Assign a role to a token.</p>
<p><strong>Usage</strong>: ASSIGN ROLE &lt;role&gt; to TOKEN &lt;token&gt;</p>
<p><strong>Example: </strong></p>
<p>assign role role1 to token test_token;</p>
</td>
</tr>
</tbody>
</table>



## GRANT Command

**GRANT** commands are used to enable specific roles to access Fabric. These commands insert records into the **Permissions table** in the [k2auth keyspace](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md) in Cassandra. Several **GRANT** commands can be run for the same LU. The permissions granted by the **GRANT** command will be appended. 

The following **GRANT** commands are supported:

<p><h4>
<span class="md-plain md-expand">GRANT </span><span class="md-tag md-raw-inline" spellcheck="false">&lt;OPERATION&gt;</span><span class="md-plain"> ON </span><span class="md-tag md-raw-inline" spellcheck="false">&lt;RESOURCE&gt;</span><span class="md-plain"> TO </span><span class="md-tag md-raw-inline" spellcheck="false">&lt;ROLE&gt;</span></h4></p>
Below is a list of GRANT OPERATION command parameters:

<table>
<tbody>
<tr>
<td width="130pxl">
<p><strong>Parameter Name</strong></p>
</td>
<td width="130pxl">
<p><strong>Mandatory</strong></p>
</td>
<td width="640pxl">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td width="150pxl">
<p>Operation</p>
</td>
<td width="150pxl">
<p>Yes</p>
</td>
<td width="600pxl">
<p>Fabric operations included in the permission.</p>
<p><strong>Examples:</strong></p>
<ul>
<li>ALL - give permission on all Fabric activities.</li>
<li>ALL_WS - give permission on all Fabric Web Services.</li>
<li>Web Service name</li>
<li>Role activities: REVOKE_ROLE, ASSIGN_ROLE, EDIT_ROLE</li>
<li>Other Fabric activities: READ, DROP_LUTYPE, DEPLOY, MIGRATE&hellip;</li>
</ul>
<p>Run a <a href="/articles/02_fabric_architecture/04_fabric_commands.md#fabric-help">help grant; </a> command to view the full list of operations.</p>
</td>
</tr>
<tr>
<td width="150pxl">
<p>Resource</p>
</td>
<td width="150pxl">
<p>Yes</p>
</td>
<td width="600pxl">
<p>Fabric resources included in the permission:</p>
<ul>
<li>* - grant permissions on all Fabric resources.</li>
<li>&lt;LUT&gt; - LU name. Grant permissions on a given LU.</li>
<li>&lt;LUT&gt;.&lt;List of LUIs&gt; - grant permissions for a list of instance IDs in a given LU.</li>
</ul>
</td>
</tr>
<tr>
<td width="150pxl">
<p>Role</p>
</td>
<td width="150pxl">
<p>Yes</p>
</td>
<td width="600pxl">
<p>The permissions are granted to a given role.</p>
</td>
</tr>
</tbody>
</table>



#### GRANT OPERATION Command - Examples

<table>
<tbody>
<tr>
<td width="400pxl" valign="top">
<p><strong>Description</strong></p>
</td>
<td width="500pxl" valign="top">
<p><strong>Example</strong></p>
</td>
</tr>
<tr>
<td width="450pxl" valign="top">
<p>Allow the role to access all instances of all LUs.</p>
</td>
<td width="450pxl" valign="top">
<p>grant all on * to role1;</p>
</td>
</tr>
<tr>
<td width="450pxl" valign="top">
<p>Allow the role to access all instances of a specific LU.</p>
<p>&nbsp;</p>
</td>
<td width="450pxl" valign="top">
<p>Grant all CRM LU permissions to the role1 role:</p>
<p>grant all on CRM to role1;</p>
</td>
</tr>
<tr>
<td width="450pxl" valign="top">
<p>Allow the role to access specific instances of a specific LU.</p>
<p>&nbsp;</p>
</td>
<td width="450pxl" valign="top">
<p>Grant all permissions for Instance IDs 41 and 42 of CRM LU to the role1 role:</p>
<p>&nbsp;grant all on CRM.41, CRM.42 to role1;</p>
</td>
</tr>
<tr>
<td width="450pxl" valign="top">
<p>Allow the role to deploy CRM LU on Fabric.</p>
</td>
<td width="450pxl" valign="top">
<p>grant deploy on CRM to role1;</p>
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td width="450pxl" valign="top">
<p>Allow the role of migrating Instance IDs on Customer LU.</p>
</td>
<td width="450pxl" valign="top">
<p>grant migrate on Customer to role1;</p>
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td width="450pxl" valign="top">
<p>Allow the role to access Instance IDs 1, 2, 4, and 6 of CRM LU.</p>
</td>
<td width="450pxl" valign="top">
<p>grant all on CRM.1, CRM.2, CRM.4,CRM.6 to role1;</p>
</td>
</tr>
<tr>
<td width="450pxl" valign="top">
<p>Allow the role to access Instance IDs 1 and 2 of CRM LU, and Instance ID 57 of Customer LU.</p>
</td>
<td width="450pxl" valign="top">
<p>grant all on CRM.1, CRM.2, Customer.57 to role1;</p>
</td>
</tr>
<tr>
<td width="450pxl" valign="top">
<p>Allow the role to invoke wsGetCustomerDetails Fabric Web Service to access CRM LU</p>
</td>
<td width="450pxl" valign="top">
<p>grant wsGetCustomerDetails on CRM to role1;</p>
</td>
</tr>
</tbody>
</table>



<p><h4><span class="md-tag md-raw-inline" spellcheck="false">GRANT &lt;WS_NAME&gt; TO &lt;ROLE&gt; </span></h4></p>

Enables users to give generic access to a given Web Service to access the Fabric database.

Notes:

- Use the **GRANT OPERATION** command to limit the access of the [Web Service](/articles/15_web_services_and_graphit/01_web_services_overview.md) to a given [LU](/articles/03_logical_units/01_LU_overview.md) or LUI.
- Use the **GRANT OPERATION** command to grant an access to all Web Services: populate the **Operation** parameter by **ALL_WS**.

Below is a list of GRANT WS_NAME command parameters:

<table>
<tbody>
<tr>
<td width="200pxl">
<p><strong>Parameter Name</strong></p>
</td>
<td width="150pxl">
<p><strong>Mandatory</strong></p>
</td>
<td width="550">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td width="162">
<p>WS_Name</p>
</td>
<td width="144">
<p>Yes</p>
</td>
<td width="318">
<p>Fabric Web Service name.</p>
</td>
</tr>
<tr>
<td width="162">
<p>Role</p>
</td>
<td width="144">
<p>Yes</p>
</td>
<td width="318">
<p>The permissions are granted to a given role.</p>
</td>
</tr>
</tbody>
</table>


#### GRANT WS_NAME Command - Examples

<table>
<tbody>
<tr>
<td width="450pxl">
<p><strong>Description</strong></p>
</td>
<td width="450pxl">
<p><strong>Example</strong></p>
</td>
</tr>
<tr>
<td width="450pxl">
<p>Allow the role to invoke wsGetCustomerDetails&nbsp;</p>
</td>
<td width="450pxl">
<p>grant wsGetCustomerDetails to role1;</p>
</td>
</tr>
</tbody>
</table>

#### Web Services Authorization

[Web Service](/articles/15_web_services_and_graphit/01_web_services_overview.md) authorization is performed using the **API Key**:

  - Project Web Services: give permission to the **role** in the Web Service or all Web Services and assign the API Key to the role.
  - Product Web Services: assign the API key to the user. The permissions for Product Web Services are defined by combining the API Key assigned to the user and the permissions of the roles assigned to the user. 

**Example**
<pre><code>
    create user test_read with password '1234'; 
    create role readonly;  
    assign role readonly to user test_read; 
    create token test_read_token user 'this is token: test_read_token'; 
    assign role readonly to token test_read_token; 
    grant READ on * to readonly;

</code></pre>

When invoking the DELETE WS: /lu/{luName}/{iid} using the 'test_read_token' token, Fabric throws the following error:
  "Com.k2view.cdbms.exceptions.UnauthorizedException: ```test_read``` is not allowed to perform [DELETE_INSTANCE]"


## Additional Commands

<table width="900pxl">
<tbody>
<tr>
<td width="200pxl">
<p><h4>DROP USER</p>
</td>
<td width="700pxl">
<p><strong>Description</strong>: Drop the user from Fabric.</p>
<p><strong>Usage</strong>: DROP USER &lt;user&gt;</p>
<p><strong>Example: </strong>drop user test_user;</p>
</td>
</tr>
<tr>
<td width="200pxl">
<p><h4>DROP ROLE</p>
</td>
<td width="700pxl">
<p><strong>Description</strong>: Drop a role from Fabric.</p>
<p><strong>Usage</strong>: DROP ROLE &lt;role name&gt;</p>
<p><strong>Example: </strong>drop role role1;</p>
</td>
</tr>
<tr>
<td width="200pxl">
<p><h4>DROP TOKEN</p>
</td>
<td width="700pxl">
<p><strong>Description</strong>: Drop a token from Fabric.</p>
<p><strong>Usage</strong>: DROP TOKEN &lt;API Key&gt;</p>
<p><strong>Example: </strong>drop token test_token;</p>
</td>
</tr>
<tr>
<td width="200pxl">
<p><h4>REVOKE&nbsp;</p>
</td>
<td width="700pxl">
<p><strong>Description</strong>: Remove the granted permissions from a role.</p>
<p><strong>Usage</strong>: Revoke syntax is the same as Grant commands with the exception that &ldquo;Revoke&rdquo; replaces &ldquo;Grant&rdquo; and &ldquo;From&rdquo; replaces &ldquo;To&rdquo;.</p>
<p class="CellBodyLeft"><strong>Examples:</strong></p>
<ul>
<li>revoke all on CRM from role1;</li>
<li>revoke migrate on Customer from role1;</li>
<li>revoke wsGetCustomerDetails on Customer from role1;</li>
</ul>
</td>
</tr>
<tr>
<td width="200pxl">
<p><h4>REVOKE ROLE</p>
</td>
<td width="700pxl">
<p><strong>Description:</strong> Unassign a role from a user or a token.</p>
<p><strong>Usage:</strong></p>
<ul>
<li class="CellBodyLeft">REVOKE ROLE &lt;ROLE&gt; FROM USER &lt;user&gt;</li>
<li class="CellBodyLeft">REVOKE ROLE &lt;ROLE&gt; FROM TOKEN &lt;token&gt;</li>
</ul>
<p><strong>Examples:</strong></p>
<ul>
<li>revoke test_role from test_user;</li>
</ul>
</td>
</tr>
<tr>
<td width="200pxl">
<p><h4>CHECK PERMISSION</p>
</td>
<td width="700pxl">
<p><strong>Description</strong>: Check the permission for the user on the given method (operation).</p>
<p><strong>Usage</strong>: CHECK_PERMISSION FOR &lt;user&gt; ON &lt;operation&gt;</p>
<p><strong>Example: </strong>check_permission for test_user on deploy;</p>
</td>
</tr>
</tbody>
</table>



[![Previous](/articles/images/Previous.png)](/articles/17_fabric_credentials/01_fabric_credentials_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/17_fabric_credentials/02a_fabric_credentials_list_commands.md)
