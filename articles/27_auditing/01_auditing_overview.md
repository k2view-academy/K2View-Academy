# Auditing Overview

Fabric has a robust Auditing mechanism that logs activities running in Fabric like logins, Web Service calls and Fabric commands. 

Two major Auditing functionalities can be controlled:

-  Filtering strategies, for full flexibility over the type of activities that are introduced to the Auditing mechanism, like reporting only Web Service calls. This flexibility does not impact the performance of other activities and saves a lot of disk space.
-  Persistence strategies, defining the channel for reporting the activities logged by the Auditing mechanism, like Cassandra (default), Kafka, files, etc.

The above can be configured via the **[audit]** and **[audit_kafka_producer]** sections of the **config.ini**. By default, the persistence strategy is Cassandra and the data is written into the **k2_auditing** table of the [k2audit](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md) keyspace.

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
<td style="height: 36px; width: 323px;">Type of activity performed in Fabric</td>
<td style="height: 36px; width: 286px;">LOGIN, GetCommand, called Web-Service name, etc.</td>
</tr>
<tr style="height: 18px;">
<td style="height: 18px; width: 73px;">date</td>
<td style="height: 18px; width: 323px;">Activity date</td>
<td style="height: 18px; width: 286px;">2020-11-05</td>
</tr>
<tr style="height: 18px;">
<td style="height: 18px; width: 73px;">user</td>
<td style="height: 18px; width: 323px;">Fabric User ID</td>
<td style="height: 18px; width: 286px;">admin, etc...</td>
</tr>
<tr style="height: 18px;">
<td style="height: 18px; width: 73px;">written_at</td>
<td style="height: 18px; width: 323px;">Activity date and timestamp</td>
<td style="height: 18px; width: 286px;">2020-11-05 11:49:14.452000+0000</td>
</tr>
<tr style="height: 72px;">
<td style="height: 72px; width: 73px;">address</td>
<td style="height: 72px; width: 323px;">IP address of the node where the activity is performed. In http/https protocol this appears as a concatenation of the IP address:port</td>
<td style="height: 72px; width: 286px;">10.21.1.1 or 10.21.1.1:3213</td>
</tr>
<tr style="height: 36px;">
<td style="height: 36px; width: 73px;">params</td>
<td style="height: 36px; width: 323px;">Activity parameters</td>
<td style="height: 36px; width: 286px;">For a GetCommand [DC_NAME=null|LU_NAME=CRM|IID=1]</td>
</tr>
<tr style="height: 54px;">
<td style="height: 54px; width: 73px;">protocol</td>
<td style="height: 54px; width: 323px;">Contains the protocol used for the activity, valid values, HTTP/1.1, HTTPS/1.3 or DRIVER or JDBC driver</td>
<td style="height: 54px; width: 286px;">DRIVER</td>
</tr>
<tr style="height: 54px;">
<td style="height: 54px; width: 73px;">query</td>
<td style="height: 54px; width: 323px;">Activity details like a CQL query for a CQLCommand, a DESCRIBE SCHEMA CRM for a DescribeCommand, or the authentication provider for the LOGIN action. <br/>See <a href="/articles/26_fabric_security/16_user_IAM_auditing.md">here</a> for more details about Login Auditing.</br/></td>
<td style="height: 54px; width: 286px;">SELECT * FROM CRM.SUBSCRIBER</td>
</tr>
<tr style="height: 18px;">
<td style="height: 18px; width: 73px;">result</td>
<td style="height: 18px; width: 323px;">Number of affected rows</td>
<td style="height: 18px; width: 286px;">Rows Affected: 3</td>
</tr>
<tr style="height: 18px;">
<td style="height: 18px; width: 73px;">session_id</td>
<td style="height: 18px; width: 323px;">Session ID</td>
<td style="height: 18px; width: 286px;">73ae6592</td>
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

Failure logins are audited as well. Note that the actual login method is populated at Query. Thus if LDAP is the first authenticator and it failed and then the next one is local (Fabric) and it passed, only the last entry Query = FABRIC is captured.

Logout is not audited. 

### Turning Auditing On/Off

By default, Auditing is set to OFF. To enable Auditing in Fabric, set **AUDIT=ON** in the **config.ini** file and then restart Fabric.

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_filtering_strategy.md) 

