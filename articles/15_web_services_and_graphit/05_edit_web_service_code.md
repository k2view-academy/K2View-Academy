# Editing Web Service Code

The code in an automatically generated Web Service function provides basic Select and Fetch templates for data retrieval. However, there is frequently a need to enhance a function’s code in order to perform a specific functionality. 

### How Do I Edit a Function's Code In a Web Service?

It is recommended to edit the code in a Web Service function using the IntelliJ Java Editor which offers a number of advantages like:

   * Smart code completion.

   * Inspection and quick fixes.

   * Functions.

   * Navigation and Search options.

Note that IntelliJ is not part of the Fabric Studio Installation Package and **must be** installed. Once installed, it is integrated into the Fabric Studio and can be invoked by right clicking the Web Service working area and selecting it or by pressing **CTRL+I** on your keyboard.

A Web Service can also be edited directly in its main working area.

### What Should be Edited?

The following items should be edited after they are automatically generated:

<table width="900pxl">
<tbody>
<tr>
<td  width="300pxl" valign="top">
<p><strong>Item</strong></p>
</td>
<td  width="600pxl" valign="top">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td  width="300pxl" valign="top">
<p><h4><strong>Fetch Data Statement</strong></h4></p>
</td>
<td  width="600pxl" valign="top">
<p>LUDB Fetch Statement.&nbsp;Replace this with the Web Service Input parameter defined as the Fabric LUI identifier. For example, in the wsCustomerInfo Web Service:</p>
<ul>
<li>Input parameter = &ldquo;ID&rdquo;&nbsp;</li>
<li>Db.Rows rows = ludb("Customer", ).fetch(sql, , , ...);</li>
<li>Db.Rows rows = ludb("Customer", ID).fetch(sql, , , ...); LUDB or DB Interface Fetch Statements</li>
</ul>
<p>Either populate the SQL query parameters in their placeholders (, ,&hellip;) or delete them if they are not required.</p>
<p>These steps are mandatory for a clean Web Service code compilation. For example:</p>
<ul>
<li>Db.Rows rows = ludb("CUSTOMER", ID).fetch(sql, &ldquo;XX&rdquo;, 123);</li>
<li>Db.Rows rows = ludb("CUSTOMER", ID).fetch(sql);</li>
</ul>
</td>
</tr>
<tr>
<td  width="300pxl" valign="top">
<p><h4><strong>SQL Statement Enhancement Options</strong></h4></p>
</td>
<td  width="600pxl" valign="top">
<ul>
<li>Add a WHERE clause.&nbsp;&nbsp;</li>
<li>JOIN additional tables to the query.</li>
<li>Add advanced features to the SELECT statement.</li>
</ul>
<p>For example, assigning identifiers to tables and specifying which table columns to retrieve.</p>
</td>
</tr>
<tr>
<td  width="300pxl" valign="top">
 <p><h4><strong>Java Code Enhancement</strong></h4></p>
</td>
<td  width="600pxl" valign="top">
<p>Web Service code can also apply transformation rules via the functions, translations or Globals defined in the project.</p>
<p>Note that any Fabric server runtime command can be used within the Web Service using the Execute function.</p>
<p>For example, the Get command on the Customer LUT for a specific instance:</p>
<p>Fabric().execute(&ldquo; get Customer.?&rdquo;,ID);</p>
</td>
</tr>
<tr>
<td  width="300pxl" valign="top">
 <p><h4><strong>Custom Payload Handling</strong></h4></p>
</td>
<td  width="600pxl" valign="top">
<p>When required, handle the service request body manually.<br/>
To do so, change the <a href=/articles/15_web_services_and_graphit/02_web_services_properties.md#web-service-properties>Web Service Properties</a> Custom Payroll value to True and change the code to handle the request body using WebServiceUserCode.request().getInputStream().<br/>
 
Note that custom payload supports POST and PUT request methods.
</p>
</td>
</tr>
</tbody>
</table>




[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/04_web_services_function_basic_structure.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/06_web_services_code_examples.md)


