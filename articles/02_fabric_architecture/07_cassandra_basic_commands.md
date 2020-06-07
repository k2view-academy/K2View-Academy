# Cassandra Basic Commands

Fabric uses Cassandra DB as a storage layer. You can run CQL commands to check Cassandra tables on [Fabric keyspaces](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md).

### Login Cassandra DB

Use the following command to connect Cassandra DB from Cassandra server:

<p><strong>cqlsh -u &lt;username&gt; -p &lt;password&gt; &lt;ip_address&gt;</strong>;</p>

Note that if you do not populate the ip_address, then the login command connects the local host of the Fabric server.

### Cassandra Useful Commands

The table below contains some useful **cqlsh** commands. 

For more information, see https://docs.datastax.com/en/cql-oss/3.x/cql/cql_reference/cqlshCommandsTOC.html

<table>
<tbody>
<tr>
<td width="200pxl">
<p><strong>Command Name</strong></p>
</td>
<td width="350pxl">
<p><strong>Command Description</strong></p>
</td>
<td width="350pxl">
<p><strong>Examples</strong></p>
</td>
</tr>
<tr>
<td width="200pxl">
<p>HELP</p>
</td>
<td width="350pxl">
<p>Get information about the list of Cassandra commands or a specific Cassandra command. </p>
</td>
<td width="350pxl">
<ul>
<li>Help describe;</li>
</ul>
</td>
</tr>
<tr>
<td width="200pxl">
<p>USE</p>
</td>
<td width="350pxl">
<p>Move into a specific&nbsp;keyspace</p>
</td>
<td width="350pxl">
<ul>
<li>Use k2view_customer;</li>
</ul>
</td>
</tr>
<tr>
<td width="200pxl">
<p>DESCRIBE (shorthand: DESC)</p>
</td>
<td width="350pxl">
<p>Describe Cassandra objects.</p>
<p>Note that when you run the Describe command inside a keyspace, it returns the information about the objects inside the keyspace.</p>
<p>&nbsp;For more information, see</p>
<p><a href="https://docs.datastax.com/en/cql-oss/3.x/cql/cql_reference/cqlshDescribe.html">https://docs.datastax.com/en/cql-oss/3.x/cql/cql_reference/cqlshDescribe.html</a></p>
</td>
<td width="350pxl">
<ul>
<li>Desc keyspaces- returns the list of the keyspaces in the Cassandra cluster.</li>
<li>Desc tables- returns the list of Cassandra keyspaces and the related table of each keyspace</li>
<li>Desc schema- returns the details for all non-system objects in the Cassandra cluster.</li>
<li>Desc keyspace &lt;keyspace name&gt; - returns the details for the specified keyspace and objects it contains.</li>
</ul>
</td>
</tr>
<tr>
<td width="200pxl">
<p>EXIT/QUIT</p>
</td>
<td width="350pxl">
<p>Exit Cassandra</p>
</td>
<td width="350pxl">
<p>&nbsp;</p>
</td>
</tr>
</tbody>
</table>

#### Run CQLSH on Local Cassandra 

Note that when you work on your local Fabric server, installed on your windows machine,  you must install Python 2.7.x on your windows machine (not part of Fabric Studio installation) to run **cqlsh** on your local Cassandra server.

Alternatively, you can do the following steps:

- Define a Cassandra DB interface on Fabric Studio and set the host to localhost. Set the user and password to **cassandra**.
- Open the [Query Builder](/articles/11_query_builder/01_query_builder_overview.md) to view the list of Cassandra keyspaces and run SQL queries on Cassandra tables. 







