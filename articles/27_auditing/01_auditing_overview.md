# Auditing Overview

<web>

Currently the Web Studio does not support the Auditing mechanism.

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

[Click for more information about the User Identification and Access Management Auditing](/articles/26_fabric_security/16_user_IAM_auditing.md).

### Turning Auditing On/Off

By default, Auditing is set to OFF. To enable Auditing in Fabric, set **AUDIT=ON** in the **config.ini** file and then restart Fabric.

~~~
AUDIT=ON
~~~



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_filtering_strategy.md) 



</studio>

