# Fabric Auditing

<web>

## Table of Contents

1. [K2Cloud Auditing Capability](#k2cloud-auditing-capability)  
2. [When to Enable Auditing in Production](#when-to-enable-auditing-in-production)  
3. [When Auditing Should Not Be Enabled](#when-auditing-should-not-be-enabled)  
4. [Where to View Audit Logs](#where-to-view-audit-logs)  
5. [Downloading Audit Logs](#downloading-audit-logs)  
6. [Turning Auditing On/Off](#turning-auditing-onoff)  
7. [What Gets Audited](#what-gets-audited)  
8. [Auditing Reporting Structure](#auditing-reporting-structure)  
9. [Auditing Reporting Examples](#auditing-reporting-examples)  


## K2Cloud Auditing Capability

K2View’s auditing capability is now available to K2Cloud SaaS customers. This feature offers detailed visibility into user and system activity across Fabric.

 > Requires: Fabric 8.3 or later to enable this feature.

Note: Customers using self-hosted K2Cloud environments are not eligible for this capability.

## When to Enable Auditing in Production

Auditing is particularly useful in production environments, where system integrity, accountability, and regulatory compliance are essential. Enabling auditing ensures that user actions and system events are accurately recorded for analysis, compliance, and troubleshooting purposes.

Here’s a list of common activities audited in production:

<table border="1">
  <thead>
    <tr>
      <th>Category</th>
      <th>Activity</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Authentication</td>
      <td>- User logins to Web Studio / Fabric CLI / Web Framework</td>
    </tr>
    <tr>
      <td></td>
      <td>- Failed login attempts (authentication errors)</td>
    </tr>
    <tr>
      <td>User Management</td>
      <td>- User creation, deletion, or modification</td>
    </tr>
    <tr>
      <td></td>
      <td>- Role assignment and revocation</td>
    </tr>
    <tr>
      <td></td>
      <td>- Changes to permissions or tokens</td>
    </tr>
    <tr>
      <td>Configuration Changes</td>
      <td>- Updates to environment configurations</td>
    </tr>
    <tr>
      <td></td>
      <td>- Changes to Fabric settings via config.ini or commands</td>
    </tr>
    <tr>
      <td></td>
      <td>- Feature enablement/disabling (e.g., turning audit ON/OFF)</td>
    </tr>
    <tr>
      <td>Deployment Actions</td>
      <td>- Deployment of Logical Units (LUs), flows, or configurations</td>
    </tr>
    <tr>
      <td></td>
      <td>- Script or service updates pushed to the Fabric runtime</td>
    </tr>
    <tr>
      <td>Command Execution</td>
      <td>- Executed Fabric commands (e.g., GET, LIST, ALTER, GRANT)</td>
    </tr>
    <tr>
      <td></td>
      <td>- Batch commands and automation jobs</td>
    </tr>
    <tr>
      <td>Data Access</td>
      <td>- Executed queries (read/write) on LU Tables or external DBs</td>
    </tr>
    <tr>
      <td></td>
      <td>- Use of SEARCH or CQL commands</td>
    </tr>
    <tr>
      <td>Web Service Usage</td>
      <td>- Web service calls via Fabric endpoints</td>
    </tr>
    <tr>
      <td></td>
      <td>- API access patterns and integration behaviors</td>
    </tr>
    <tr>
      <td>Job Execution</td>
      <td>- Scheduled job execution and lifecycle (start/stop/update)</td>
    </tr>
    <tr>
      <td></td>
      <td>- Execution of Broadway or background processes</td>
    </tr>
    <tr>
      <td>System Operations</td>
      <td>- System restart events</td>
    </tr>
    <tr>
      <td></td>
      <td>- Startup/shutdown logs and audit state transitions</td>
    </tr>
  </tbody>
</table>


## When Auditing Should Not Be Enabled

Auditing is not recommended in development environments, particularly when using Fabric Web Studio.

**Why?**

In development environments, Fabric Web Studio often executes actions on behalf of the user. If auditing is enabled:

- Both user-driven actions and system-initiated background tasks by Web Studio will be logged.
- This results in misleading or noisy audit records that do not accurately reflect user behavior.
- There is no operational benefit, as this environment is typically used for prototyping or testing—not production governance or compliance.

**Recommendation:**  
Do not enable auditing in development environments where Fabric Web Studio is employed. It provides no added value and may introduce confusion in interpreting activity logs.

## Where to View Audit Logs

When auditing is enabled, audit entries are integrated into the logs shown on the Monitoring page, under the Fabric Monitor Logs panel. These entries are interspersed with standard logs and can be filtered using the search feature.

To view only audit records, enter `AUDIT` into the search bar (**case-sensitive**).

## Downloading Audit Logs

Audit data can be downloaded in either **CSV** or **plain text** formats using one of the following methods:

1. **Using the UI’s Vertical 3-dot Menu (⋮):**  
   - Click the vertical 3-dot menu (⋮) in the log panel.  
   - Navigate to: `Inspect > Data`  
   - Choose the desired format (CSV or text) for export.

2. **Using a Keyboard Shortcut:**  
   - Press the `i` key to access the same `Inspect > Data` option and initiate export.
  
## Turning Auditing On/Off

By default, Auditing is set to OFF. To enable Auditing in Fabric, set **AUDIT=ON** using K2admin's Configuration panel and set a configuration override for AUDIT. You then need to restart the K2cloud space.

~~~
AUDIT=ON
~~~


## What Gets Audited

Fabric auditing tracks the following activity types:

- System logins  
- All executed Fabric commands  
- Web-Service calls  
- All executed data queries (read and write)

## Auditing Reporting Structure

When an activity is captured by the Fabric Auditing mechanism, it is logged with the following fields:

<table border="1">
  <thead>
    <tr>
      <th>Name</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Action</td>
      <td>Type of activity performed in Fabric.</td>
    </tr>
    <tr>
      <td>Date</td>
      <td>Activity date.</td>
    </tr>
    <tr>
      <td>User</td>
      <td>Fabric User ID.</td>
    </tr>
    <tr>
      <td>Written at</td>
      <td>Full date and timestamp of the activity.</td>
    </tr>
    <tr>
      <td>Address</td>
      <td>IP address of the node where the activity occurred. May include port.</td>
    </tr>
    <tr>
      <td>Params</td>
      <td>Parameters passed to Fabric commands.</td>
    </tr>
    <tr>
      <td>Protocol</td>
      <td>Protocol used for the activity.</td>
    </tr>
    <tr>
      <td>Query</td>
      <td>Activity details such as query, schema info, or auth provider.</td>
    </tr>
    <tr>
      <td>Result</td>
      <td>Outcome of the action (rows affected or status).</td>
    </tr>
    <tr>
      <td>Session ID</td>
      <td>Correlation ID for related actions within a session or request chain.</td>
    </tr>
  </tbody>
</table>


## Auditing Reporting Examples

<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th>Action</th>
      <th>Query</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>LOGIN</td>
      <td>['null']</td>
    </tr>
    <tr>
      <td>LOGOUT</td>
      <td>['null']</td>
    </tr>
    <tr>
      <td>Create Role Command</td>
      <td>["create role 'audit'"]</td>
    </tr>
    <tr>
      <td>Create Token Command</td>
      <td>['create token audit_token']</td>
    </tr>
    <tr>
      <td>Create User Command</td>
      <td>['CREATE USER ksmith*** with PASSWORD ****']</td>
    </tr>
    <tr>
      <td>Alter User Command</td>
      <td>['ALTER USER ksmith*** with password ****']</td>
    </tr>
    <tr>
      <td>Assign Role Command</td>
      <td>['assign role audit to token audit_token', 'assign role audit to user ksmith***']</td>
    </tr>
    <tr>
      <td>Deploy Command</td>
      <td>["DEPLOY CustomerAudit WITH JAR '/tmp/fabric_upload_tmp_nura_nondev83_k2view_qa_0/ludb.jar' ZIP_FILE '/tmp/fabric_upload_tmp_nura_nondev83_k2view_qa_0/ludbXMLs.zip' WS_METHODS ''  SOFT_DEPLOY false"]</td>
    </tr>
    <tr>
      <td>Deploy Environments Command</td>
      <td>["DEPLOY ENVIRONMENTS FROM FILE '/tmp/fabric_upload_tmp_nura_nondev83_k2view_qa_1/Environments.k2fabEnv.xml'"]</td>
    </tr>
    <tr>
      <td>Grant Command</td>
      <td>['grant ALL on * to audit', 'grant ALL_WS on * to audit']</td>
    </tr>
    <tr>
      <td>Batch In Process Command</td>
      <td>["batch_in_process filter='(?i)sync_instance'"]</td>
    </tr>
    <tr>
      <td>Batch Process Details Command</td>
      <td>["BATCH_DETAILS '${batch_id}'", "BATCH_DETAILS ''", "BATCH_DETAILS '51905b7e-6c8d-4914-a3db-243252c07c80'"]</td>
    </tr>
    <tr>
      <td>Batch Process List Command</td>
      <td>['batch_list']</td>
    </tr>
    <tr>
      <td>Batch Process Retry Command</td>
      <td>["batch_retry  '07e05c7a-33ea-4da1-a0e7-27b6c13e5237' allow_cancelled=true"]</td>
    </tr>
    <tr>
      <td>Batch Process Summary Command</td>
      <td>["batch_summary  '51905b7e-6c8d-4914-a3db-243252c07c80'"]</td>
    </tr>
    <tr>
      <td>Broadway Command</td>
      <td>['broadway k2_ws.fabricRestart']</td>
    </tr>
    <tr>
      <td>Cancel Command</td>
      <td>["cancel batch'13b8d066-c523-4a2d-9be5-79c22e75c786'"]</td>
    </tr>
    <tr>
      <td>Drop Command</td>
      <td>['drop token audit_token', 'drop role audit', 'drop lutype CustomerAudit']</td>
    </tr>
    <tr>
      <td>Get Command</td>
      <td>['get OracleLu.2', 'use OracleLu.3', 'get LU3.1']</td>
    </tr>
    <tr>
      <td>GetF Command</td>
      <td>['getf CustomerAudit.insertRowToUsers(999999)']</td>
    </tr>
    <tr>
      <td>Job Status Command</td>
      <td>['jobstatus']</td>
    </tr>
    <tr>
      <td>List Command</td>
      <td>['list config_overrides', 'list config', 'list lut']</td>
    </tr>
    <tr>
      <td>Migrate Command</td>
      <td>['migrate CustomerAudit.(1;2;3;4;5) with async=true', "migrate CustomerAudit from fabric using ('select user_id from common.Users where user_id<=10000') with async=true", 'migrate CustomerAudit with async=true']</td>
    </tr>
    <tr>
      <td>Migrate Details Command</td>
      <td>["migrate_details '${batch_id}'", "migrate_details ''", "migrate_details '51905b7e-6c8d-4914-a3db-243252c07c80'"]</td>
    </tr>
    <tr>
      <td>Migrate In Process Command</td>
      <td>['migrate_in_process']</td>
    </tr>
    <tr>
      <td>Migrate List Command</td>
      <td>['migrate_list']</td>
    </tr>
    <tr>
      <td>Migrate Resume Command</td>
      <td>["migrate_resume '07e05c7a-33ea-4da1-a0e7-27b6c13e5237' allow_cancelled=true"]</td>
    </tr>
    <tr>
      <td>Migrate Summary Command</td>
      <td>["migrate_summary '51905b7e-6c8d-4914-a3db-243252c07c80'"]</td>
    </tr>
    <tr>
      <td>PS Command</td>
      <td>['ps all']</td>
    </tr>
    <tr>
      <td>QUERY</td>
      <td>['select * from table1 where COL1=123']</td>
    </tr>
    <tr>
      <td>QUERY_DATA_CHANGE</td>
      <td>['begin', 'INSERT into common.USERS (...)', 'delete from table1 where COL1=123']</td>
    </tr>
    <tr>
      <td>Release Command</td>
      <td>['release', 'release CustomerAudit', 'release CustomerAudit; LU3']</td>
    </tr>
    <tr>
      <td>Revoke Command</td>
      <td>["revoke ALL on * from 'audit'"]</td>
    </tr>
    <tr>
      <td>Set Command</td>
      <td>['set username', 'set default', "set environment='_dev'"]</td>
    </tr>
    <tr>
      <td>Set Global Command</td>
      <td>['set_global config_overrides_add=?;', "set_global global 'CustomerAudit.audit_test=10'"]</td>
    </tr>
    <tr>
      <td>Test Connection Command</td>
      <td>['test_connection interface=anthropic timeout=30', 'test_connection interface=bedrock timeout=30', 'test_connection interface=cassandraLoader timeout=30']</td>
    </tr>
    <tr>
      <td>Delete Instance Command</td>
      <td>['delete instance CustomerAudit.999999']</td>
    </tr>
    <tr>
      <td>Time Command</td>
      <td>['time']</td>
    </tr>
    <tr>
      <td>Version Info Command</td>
      <td>['version basic']</td>
    </tr>
    <tr>
      <td>Help Command</td>
      <td>['help', 'help set', 'help deploy']</td>
    </tr>
  </tbody>
</table>


</web>

<studio>

Fabric has a robust Auditing mechanism that logs various activities running on Fabric. These can be logins, Web Service calls, and various Fabric commands. 

Two major Auditing features can be controlled:

-  **Filtering strategies:** provides full flexibility over the type of activities that are introduced to the Auditing mechanism. For instance, you may audit the Web Service calls only, without impacting the performance of other activities but with saving a lot of disk space.
-  **Persistence strategies:** defines the reporting channel of the Auditing mechanism. Examples for such channels are Cassandra (default), Kafka, files, etc.

The Auditing mechanism can be configured via the **[audit]** and **[audit_kafka_producer]** sections of the **config.ini**. By default, the persistence strategy is Cassandra, and the data is written into the **k2_auditing** table of the [k2audit](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md) keyspace.

### Auditing Reporting Structure

When an activity is logged by the Fabric Auditing mechanism, it has the following structure:
<table style="width: 900px;">
<thead>
<tr style="height: 18px;">
<th style="height: 18px; width: 73px;">Name</th>
<th style="height: 18px; width: 323px;">Description</th>
<th style="height: 18px; width: 286px;">Example</th>
</tr>
</thead>
<tbody>
<tr style="height: 36px;">
<td style="height: 36px; width: 73px;">action</td>
<td style="height: 36px; width: 323px;">Type of activity performed in Fabric.</td>
<td style="height: 36px; width: 286px;">LOGIN, GetCommand, called Web-Service name, etc.</td>
</tr>
<tr style="height: 18px;">
<td style="height: 18px; width: 73px;">date</td>
<td style="height: 18px; width: 323px;">Activity date.</td>
<td style="height: 18px; width: 286px;">2020-11-05</td>
</tr>
<tr style="height: 18px;">
<td style="height: 18px; width: 73px;">user</td>
<td style="height: 18px; width: 323px;">Fabric User ID.</td>
<td style="height: 18px; width: 286px;">admin, etc...</td>
</tr>
<tr style="height: 18px;">
<td style="height: 18px; width: 73px;">written_at</td>
<td style="height: 18px; width: 323px;">Activity date and timestamp.</td>
<td style="height: 18px; width: 286px;">2020-11-05 11:49:14.452000+0000</td>
</tr>
<tr style="height: 72px;">
<td style="height: 72px; width: 73px;">address</td>
<td style="height: 72px; width: 323px;">IP address of the node where the activity is performed. In HTTP/HTTPS protocol address is a concatenation of the IP address:port.</td>
<td style="height: 72px; width: 286px;">10.21.1.1 or 10.21.1.1:3213</td>
</tr>
<tr style="height: 36px;">
<td style="height: 36px; width: 73px;">params</td>
<td style="height: 36px; width: 323px;">Activity parameters, applicable for Fabric commands only.</td>
<td style="height: 36px; width: 286px;">For example, for GetCommand: [DC_NAME=null|LU_NAME=CRM|IID=1]</td>
</tr>
<tr style="height: 54px;">
<td style="height: 54px; width: 73px;">protocol</td>
<td style="height: 54px; width: 323px;">Contains the protocol used for the activity. The valid values are: HTTP/1.1, HTTPS/1.3 or DRIVER or JDBC driver.</td>
<td style="height: 54px; width: 286px;">DRIVER</td>
</tr>
<tr style="height: 54px;">
<td style="height: 54px; width: 73px;">query</td>
<td style="height: 54px; width: 323px;">Activity details, for example CQL query for a CQLCommand, a DESCRIBE SCHEMA CRM for a DescribeCommand, or the authentication provider for the LOGIN action.</td>
<td style="height: 54px; width: 286px;">SELECT * FROM CRM.SUBSCRIBER</td>
</tr>
<tr style="height: 18px;">
<td style="height: 18px; width: 73px;">result</td>
<td style="height: 18px; width: 323px;">Number of affected rows or  activity status.</td>
<td style="height: 18px; width: 286px;">Rows Affected: 3</td>
</tr>
<tr style="height: 18px;">
<td style="height: 18px; width: 73px;">session_id</td>
<td style="height: 18px; width: 323px;">Session ID. When few actions are executed as a result of entry point (e.g. Web Service), session ID is the same for all the related entries.</td>
<td style="height: 18px; width: 286px;">07a40433-17a3-4054-9aaf-59d19378c555</td>
</tr>
</tbody>
</table>



For example, when the user performs login and authentication to the Web Framework, the activity is audited as follows:

- Action = LOGIN
- Protocol = HTTP/1.1
- Query = LDAP/SAML/FABRIC

When the user performs login to the Fabric console, it is audited as follows:

* Action = LOGIN
* Protocol = DRIVER
* Query = LDAP/FABRIC

Logouts are not audited.  

The following activities in Fabric can be captured by the auditing mechanism:

* System Login
* Any executed Fabric commands
* Web-Service calls
* Any executed queries on the data, covering both read and write.


[Click for more information about the User Identification and Access Management Auditing](/articles/26_fabric_security_iam/16_user_IAM_auditing.md).

### Turning Auditing On/Off

By default, Auditing is set to OFF. To enable Auditing in Fabric, set **AUDIT=ON** in the **config.ini** file and then restart Fabric.

~~~
AUDIT=ON
~~~



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_filtering_strategy.md) 



</studio>

