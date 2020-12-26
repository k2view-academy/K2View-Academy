# The Basic Structure of a Web Service Function

Fabric Studio enables you to automatically generate Java code that holds the basic components of a Web Service function. 

### Web Service Function: Basic Structural Components 

<table width="900pxl">
<tbody>
<tr>
<td valign="top" width="300pxl">
<p><strong>Component</strong></p>
</td>
<td valign="top" width="600pxl">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">String Declaration</td>
<td valign="top" width="600pxl">
<p>String declaration of the SQL statement&rsquo;s structure which includes the Column Name, Table Name, Join with Other Tables, and other SQL syntax elements.</p>
<p>For example, to generate an SQL statement to retrieve the CUSTOMER ID, SSN and FIRST_NAME from the CUSTOMER table:</p>
<ul>
<li>String sql = "SELECT CUSTOMER_ID, SSN, FIRST_NAME FROM CUSTOMER";</li>
</ul>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<p>Fetch Statement</p>
</td>
<td width="600pxl" valign="top">
<p>Fabric <a href="/articles/02_fabric_architecture/04_fabric_commands.md#get-lui-commands">gets a specific instance</a> and fetches data from Fabric using the declared SQL statement.</p>
<p><strong>Examples:</strong></p>
<ul>
<li>Get the data for a Customer LUI from the Fabric using the <a href="/articles/05_DB_interfaces/09_fabric_API_for_DB_interfaces.md#connect-to-the-local-fabric-using-a-web-service&quot;">ludb() method</a>. The ID parameter, sent to ludb() function is received as a WS input parameter:
<ul>
<li>Db.Rows rows = ludb("Customer", ID).fetch(sql);&nbsp;</li>
<p><p>The SQL statement should be structured with binding parameter/s that are represented by a question mark. The parameter/s should be added to the Fetch method in the same order they are defined in the SQL statement.</p>
</ul>
</li>
<li>Fetch the CUSTOMER data from the CRM_DB based on the CUSTOMER_ID as an Input parameter:
<ul>
<li>String sql = "SELECT CUSTOMER_ID, CUSTOMER_TYPE, CREATION_DATE FROM CUSTOMER WHERE CUSTOMER_ID = ? &ldquo;; Db.Rows rows = db("CRM_DB").fetch(sql, custId);</li>
<p><p>Note that during runtime, the question mark is replaced by the Web Service&rsquo;s input parameter value as the&nbsp;<strong>custID</strong>.</p>
</ul>
</li>
</ul>
</td>
</tr>
<tr>
 <td width="300pxl" valign="top">
 <p>Return Statement</p>
 </td>
<td width="600pxl" valign="top">
  <p>Terminates the execution of the Web Service function. Close the statement or connections if needed and return rs;</p>
 </td>
</tr>
</tbody>
</table>



[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/02_web_services_properties.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/05_edit_web_service_code.md)
