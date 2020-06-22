# The Basic Structure of a Web Service Function

Fabric Studio enables you to automatically generate Java code that holds the basic components of a Web Service function. 

### Web Service Function: Basic Structural Components 

The following table describes the basic structural components of a Web Service function:
<table class="unchanged rich-diff-level-one">
<thead>
<tr>
<th>Component</th>
<th style="text-align: left;">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>String Declaration</td>
<td>
<p>String declaration of the SQL statement&rsquo;s structure which includes the Column Name, Table Name, Join with Other Tables and other SQL syntax elements.</p> <p>For example, to generate an SQL statement to retrieve the CUSTOMER ID, SSN and FIRST_NAME from the CUSTOMER table:</p>
<p style="padding-left: 30px;">String sql = "SELECT CUSTOMER_ID, SSN, FIRST_NAME FROM CUSTOMER";</p>
</td>
</tr>
<tr>
<td>Fetch Statement</td>
<td>
<p>Fabric Get a specific instance and fetches data from Fabric using the declared SQL statement.</p>
<p>For example, to get the data for a customer instance from the Fabric (LUDB):</p>
<p style="padding-left: 30px;">Db.Rows rows = ludb("Customer", ID).fetch(sql); &ldquo;Customer&rdquo; &ndash; Fabric LUT name ID - Instance id value, received as a Web Service input parameter DB Interfaces Get the required data from another DB interface.</p>
<p>The SQL statement should be structured with binding parameter/s that are represented by a question mark. The parameter/s should be added to the Fetch method in the same order they are defined in the SQL statement.</p>
<p>For example, to fetch the CUSTOMER data from the CRM_DB based on the CUSTOMER_ID as an Input parameter.</p>
<p style="padding-left: 30px;">String sql = "SELECT CUSTOMER_ID, CUSTOMER_TYPE, CREATION_DATE FROM CUSTOMER WHERE CUSTOMER_ID = ? &ldquo;; Db.Rows rows = db("CRM_DB").fetch(sql, custId);</p>
<p>Note that during runtime, the question mark is replaced by the Web Service&rsquo;s input parameter value as the&nbsp;<strong>custID</strong>.</p>
</td>
</tr>
<tr>
<td>Return Statement</td>
<td>Terminates the execution of the Web Service function. Close the statement or Connections if needed and return rs;</td>
</tr>
</tbody>
</table>



[![Previous](/articles/images/Previous.png)](/articles/15_web_services/03_create_a_web_service.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services/05_edit_web_service_code.md)

