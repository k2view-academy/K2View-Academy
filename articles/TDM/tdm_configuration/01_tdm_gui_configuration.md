# TDM GUI Configuration

A TDM configuration has the following:

- [TDM GUI Configuration](#tdm-gui-configuration)
- [TDM DB - General Parameters](#tdm-db---general-parameters)

## TDM GUI Configuration

The main configuration file of the TDM GUI is the **config.js** file which is located under the ~/TDM/k2vtdmbe directory of the TDM server.

### Config.js File

<table width="900pxl">
<tbody>
<tr>
<td valign="top" width="200pxl"><strong>Section Name</strong></td>
<td valign="top" width="200pxl"><strong>Attribute Name</strong></td>
<td valign="top" width="300pxl"><strong>Description</strong></td>
<td valign="top" width="200pxl"><strong>Default Value</strong></td>
</tr>
</tbody>
<tbody>
<tr>
<td valign="top" width="200pxl">N/A</td>
<td valign="top" width="200pxl">secret</td>
<td valign="top" width="300pxl">&nbsp;</td>
<td valign="top" width="200pxl">hello</td>
</tr>
<tr>
<td valign="top" width="200pxl">N/A</td>
<td valign="top" width="200pxl">sessionTimeout</td>
<td valign="top" width="300pxl">The number of seconds before an idle session expires.</td>
<td valign="top" width="200pxl">3600</td>
</tr>
<tr>
<td valign="top" width="200pxl">N/A</td>
<td valign="top" width="200pxl">https</td>
<td valign="top" width="300pxl">Configures the TDM GUI connection mode which can be either http or https. To connect the TDM GUI in https mode, set this parameter to True and set the <strong>url</strong> of the constants.js to https.</td>
<td valign="top" width="200pxl">false</td>
</tr>
<tr>
<td valign="top" width="200pxl">N/A</td>
<td valign="top" width="200pxl">fluxMode</td>
<td valign="top" width="300pxl">Set to false to disable the creation and execution of <a href="/articles/TDM/tdm_gui/16_extract_task.md">Extract tasks</a> and <a href="/articles/TDM/tdm_gui/15_data_flux_task.md">Load Data Flux tasks</a>.</td>
<td valign="top" width="200pxl">true</td>
</tr>
<tr>
<td valign="top" width="200pxl">adminUsers</td>
<td valign="top" width="200pxl">uid, displayName</td>
<td valign="top" width="300pxl">Defines the list of admin users in the TDM GUI. Note that each user including the admin user must also be defined in the LDAP system.</td>
<td valign="top" width="200pxl">&nbsp;</td>
</tr>
<tr>
<td valign="top" width="200pxl">postgres</td>
<td valign="top" width="200pxl">user</td>
<td valign="top" width="300pxl">The connection details of the <a href="/articles/TDM/tdm_architecture/02_tdm_database.md">TDM PostgreSQL DB</a>.</td>
<td valign="top" width="200pxl">tdm</td>
</tr>
<tr>
<td valign="top" width="200pxl">postgres</td>
<td valign="top" width="200pxl">pass</td>
<td valign="top" width="200pxl">The connection details of the <a href="/articles/TDM/tdm_architecture/02_tdm_database.md">TDM PostgreSQL DB</a>.</td>
<td valign="top" width="200pxl">tdm</td>
</tr>
<tr>
<td valign="top" width="200pxl">postgres</td>
<td valign="top" width="200pxl">host</td>
<td valign="top" width="200pxl">The connection details of the <a href="/articles/TDM/tdm_architecture/02_tdm_database.md">TDM PostgreSQL DB</a>. Edit the host and populate it using the IP address of the TDM DB.</td>
<td valign="top" width="200pxl">&nbsp;</td>
</tr>
<tr>
<td valign="top" width="200pxl">postgres</td>
<td valign="top" width="200pxl">port</td>
<td valign="top" width="200pxl">The connection details of the <a href="/articles/TDM/tdm_architecture/02_tdm_database.md">TDM PostgreSQL DB</a>.</td>
<td valign="top" width="200pxl">5432</td>
</tr>
<tr>
<td valign="top" width="200pxl">postgres</td>
<td valign="top" width="200pxl">database</td>
<td valign="top" width="200pxl">The connection details of the <a href="/articles/TDM/tdm_architecture/02_tdm_database.md">TDM PostgreSQL DB</a>.</td>
<td valign="top" width="200pxl">TDMDB</td>
</tr>
<tr>
<td valign="top" width="200pxl">postgres</td>
<td valign="top" width="200pxl">schema</td>
<td valign="top" width="200pxl">The connection details of the <a href="/articles/TDM/tdm_architecture/02_tdm_database.md">TDM PostgreSQL DB</a>.</td>
<td valign="top" width="200pxl">public</td>
</tr>
<tr>
<td valign="top" width="200pxl">postgres</td>
<td valign="top" width="200pxl">ssl</td>
<td valign="top" width="200pxl">Set to true to set an SSL connection to the TDM DB.</td>
<td valign="top" width="200pxl">false</td>
</tr>
<tr>
<td valign="top" width="200pxl">debug</td>
<td valign="top" width="200pxl">level</td>
<td valign="top" width="200pxl">The TDM GUI debug level can be set to <strong>error</strong>, <strong>warn</strong> or <strong>info</strong>.</td>
<td valign="top" width="200pxl">info</td>
</tr>
<tr>
<td valign="top" width="200pxl">debug</td>
<td valign="top" width="200pxl">outputType</td>
<td valign="top" width="200pxl">Set the debug output type to <strong>console</strong> or <strong>file</strong>.</td>
<td valign="top" width="200pxl">console</td>
</tr>
<tr>
<td valign="top" width="200pxl">debug</td>
<td valign="top" width="200pxl">status</td>
<td valign="top" width="200pxl">Set to <strong>off</strong> to disable debug messages.</td>
<td valign="top" width="200pxl">on</td>
</tr>
<tr>
<td valign="top" width="200pxl">ldap</td>
<td valign="top" width="200pxl">ssl</td>
<td valign="top" width="200pxl">Set to <strong>true</strong> to connect the LDAP in a secured mode (LDAPS).</td>
<td valign="top" width="200pxl">false</td>
</tr>
<tr>
<td valign="top" width="200pxl">ldap</td>
<td valign="top" width="200pxl">MC_AD</td>
<td valign="top" width="200pxl">Set to <strong>true</strong> to connect the Microsoft Active Directory as an LDAP system.</td>
<td valign="top" width="200pxl">false</td>
</tr>
<tr>
<td valign="top" width="200pxl">ldap</td>
<td valign="top" width="200pxl">urlString</td>
<td valign="top" width="200pxl">The LDAP's URL. Set the IP address and port of the LDAP system. Set the port to <strong>636</strong> to connect the LDAP in a secure mode (LDAPS). Note that the TDM server contains also an LDAP sever for TDM development and testing. Populate the URL with the TDM server IP address to invoke the development LDAP server.</td>
<td valign="top" width="200pxl">ldap://62.90.46.136:10389</td>
</tr>
<tr>
<td valign="top" width="200pxl">ldap</td>
<td valign="top" width="200pxl">adminDN</td>
<td valign="top" width="200pxl">&nbsp;</td>
<td valign="top" width="200pxl">uid=tdmldap,ou=users,ou=system</td>
</tr>
<tr>
<td valign="top" width="200pxl">ldap</td>
<td valign="top" width="200pxl">adminPassword</td>
<td valign="top" width="200pxl">The LDAP password. Use the default password to connect the development LDAP.</td>
<td valign="top" width="200pxl">Q1w2e3r4t5</td>
</tr>
<tr>
<td valign="top" width="200pxl">ldap</td>
<td valign="top" width="200pxl">ownersDN</td>
<td valign="top" width="200pxl">Use the default value to connect the development LDAP.</td>
<td valign="top" width="200pxl">ou=k2venvownerg,ou=system</td>
</tr>
<tr>
<td valign="top" width="200pxl">ldap</td>
<td valign="top" width="200pxl">testersDN</td>
<td valign="top" width="200pxl">Use the default value to connect the development LDAP.</td>
<td valign="top" width="200pxl">ou=k2vtestg,ou=system</td>
</tr>
<tr>
<td valign="top" width="200pxl">ldap</td>
<td valign="top" width="200pxl">ownersGroupName</td>
<td valign="top" width="200pxl">The group name of the environment owners users. Use the default value to connect the development LDAP.</td>
<td valign="top" width="200pxl">k2venvownerg</td>
</tr>
<tr>
<td valign="top" width="200pxl">ldap</td>
<td valign="top" width="200pxl">testersGroupName</td>
<td valign="top" width="200pxl">The group name of the testers users. Use the default value to connect the development LDAP.</td>
<td valign="top" width="200pxl">k2vtestg</td>
</tr>
<tr>
<td valign="top" width="200pxl">ldap</td>
<td valign="top" width="200pxl">baseCN</td>
<td valign="top" width="200pxl">To connect the Microsoft Active Directory, set the value as CN=Users,DC=k2vfabric,DC=local.</td>
<td valign="top" width="200pxl">DC=training,DC=k2view,DC=com</td>
</tr>
<tr>
<td valign="top" width="200pxl">fabricWsUrl</td>
<td valign="top" width="200pxl">url</td>
<td valign="top" width="200pxl">Edit the URL and set the Fabric IP address. Create the [tdm-WS token] in Fabric and grant it permissions for all Fabric Web Services.</td>
<td valign="top" width="200pxl">http://62.90.46.136:3213/ws?format=json&amp;token=tdm-WS</td>
</tr>
<tr>
<td valign="top" width="200pxl">fabricWsUrl</td>
<td valign="top" width="200pxl">List of Fabric APIs invoked by the TDM GUI</td>
<td valign="top" width="200pxl">&nbsp;</td>
<td valign="top" width="200pxl">&nbsp;</td>
</tr>
<tr>
<td valign="top" width="200pxl">fabricWsUrlFormatHTML</td>
<td valign="top" width="200pxl">url</td>
<td valign="top" width="200pxl">Edit the URL and set Fabric the IP address. Create the [tdm-WS token] in Fabric and grant it permissions for all Fabric Web Services.</td>
<td valign="top" width="200pxl">http://62.90.46.136:3213/ws?format=json&amp;token=tdm-WS</td>
</tr>
<tr>
<td valign="top" width="200pxl">getEntitiesList</td>
<td valign="top" width="200pxl">entitiesArrarySize</td>
<td valign="top" width="200pxl">Maximum number of sample entities to be displayed in the <a href="/articles/TDM/tdm_gui/27_task_execution_history.md#task-execution---detailed-statistics">copied and failed entity list of a task execution</a>.</td>
<td valign="top" width="200pxl">100</td>
</tr>  
<tr>
<td valign="top" width="200pxl">retentionPeriod</td>
<td valign="top" width="200pxl">maxRetentionPeriod</td>
<td valign="top" width="200pxl">Maximum <a href="/articles/TDM/tdm_gui/16_extract_task.md#retention-period">retention period</a> in days to define for Extract tasks.</td>
<td valign="top" width="200pxl">90</td>
</tr>
<tr>
<td valign="top" width="200pxl">retentionPeriod</td>
<td valign="top" width="200pxl">defaultPeriod</td>
<td valign="top" width="200pxl">The default retention period of an Extract task.</td>
<td valign="top" width="200pxl">"unit" : "Days", "value": 5</td>
</tr>
<tr>
<td valign="top" width="200pxl">availableOptions</td>
<td valign="top" width="200pxl">name, units</td>
<td valign="top" width="200pxl">The available options for the retention period of an Extract task.</td>
<td valign="top" width="200pxl">&nbsp;</td>
</tr>
</table>



### Constants.js File

The constants.js configuration file is used by the TDM GUI and is located under the ~/TDM//k2vtdmfe/app/js/constants directory.

Edit the **url** of the **BE_BASE_URL** as follows:

- Set the IP address to the TDM server IP address.

- Replace **http** with **https** to connect the TDM GUI in https mode.

  

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_tdmdb_general_parameters.md)

