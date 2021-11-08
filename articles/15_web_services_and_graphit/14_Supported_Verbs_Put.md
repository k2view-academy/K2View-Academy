# Put Verb

Use PUT APIs to update an existing resource:  
- If the resource does not exist, the API may decide whether to create a new resource or not. 
- If a new resource has been created by the PUT API, the origin server MUST inform the user agent via the **HTTP 201 Created** response code.
- If an existing resource is modified, either the **200 OK** or **204 No Content** response codes should be sent to indicate the request has been successfully completed.
- If the request passes through a cache and the Request-URI identifies one or more currently cached entities, the entries SHOULD be handled as stale. Responses to this method are **not cacheable**.

The difference between the POST and PUT APIs can be observed in request URIs:
- POST requests are made on resource collections.
- PUT requests are made on an individual resource.

##  Put Data Into LU Table

<p><code>http://&lt;Domain name&gt;:&lt;PORT&gt;/api/[VERSION_NO]/lu/&lt;LU Name&gt;/&lt;iid&gt;/&lt;TABLE_NAME&gt;&amp;token=&lt;API Key&gt;&amp;[format=json/xml]</code></p>

<table>
<thead>
<tr>
<th><strong>Component</strong></th>
<th><strong>Description</strong></th>
<th><strong>Mandatory</strong></th>
<th><strong>Example</strong></th>
<th><strong>Default</strong></th>
</tr>
</thead>
<tbody>
<tr>
<td>Domain name</td>
<td>Domain name</td>
<td>Y</td>
<td>localhost</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>PORT</td>
<td>PORT</td>
<td>Y</td>
<td>3213</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>api</td>
<td>API</td>
<td>Y</td>
<td>api</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>VERSION_NO</td>
<td>Version number</td>
<td>N</td>
<td>V1.4</td>
<td>Latest version</td>
</tr>
<tr>
<td>LU Name</td>
<td>Logical unit name or COMMON for common tables</td>
<td>Y</td>
<td>CUSTOMER COMMON</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>Iid</td>
<td>Instance Id</td>
<td>Y</td>
<td>1</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>TABLE_NAME</td>
<td>Table name for data creation</td>
<td>Y</td>
<td>PAYMENT</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>token</td>
<td>Token name</td>
<td>Y</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>format</td>
<td>Response format</td>
<td>Y</td>
<td>JSON/XML/CSV</td>
<td>JSON</td>
</tr>
</tbody>
</table>

**Example:**

- `http://localhost:3213/api/v1.0/lu/CUSTOMER/1/INVOICE?token=ABC`

  Update data on CUSTOMER LU instance id 1, CUSTOMER table

  Request Body
```
 {
	"row" : {"LAST_NAME":"TEST1"},
	"where":"CUSTOMER=1 or ADDRESS='Betsey'"
}                    
```


##  Put Data Into Common Table

<p><code>http://&lt;Domain name&gt;:&lt;PORT&gt;/api/[VERSION_NO]/COMMON/&lt;COMMON TABLE NAME&gt;&amp;token=&lt;API Key&gt;&amp;[format=json/xml]</code></p>

<table class="unchanged rich-diff-level-one">
<thead>
<tr>
<th><strong>Component</strong></th>
<th><strong>Description</strong></th>
<th><strong>Mandatory</strong></th>
<th><strong>Example</strong></th>
<th><strong>Default</strong></th>
</tr>
</thead>
<tbody>
<tr>
<td>Domain name</td>
<td>Domain name</td>
<td>Y</td>
<td>localhost</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>PORT</td>
<td>PORT</td>
<td>Y</td>
<td>3213</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>api</td>
<td>API</td>
<td>Y</td>
<td>api</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>COMMON</td>
<td>Specify that scope is common</td>
<td>Y</td>
<td>COMMON</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>COMMON TABLE NAME</td>
<td>Common table name</td>
<td>N</td>
<td>ADDRESSES</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>token</td>
<td>API key</td>
<td>Y</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>format</td>
<td>Response format</td>
<td>Y</td>
<td>JSON/XML</td>
<td>JSON</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>schema</p>
</td>
<td valign="top" width="250pxl">
<p>Reference table schema name</p>
</td>
<td valign="top" width="100pxl">
<p>N</p>
</td>
<td valign="top" width="200pxl">
<p>A valid schema name</p>
</td>
<td valign="top" width="200pxl">
<p>common if empty</p>
</td>
</tr>
</tbody>
</table>

**Example:**

- `http://localhost:3213/api/v1.0/COMMON?CITIES&token=ABC`

  Update data in common ADDRESSES table
  
  Request Body

```
 {
	"row" : {"ADDRESS_NAME":"YOQNEAM"} ,
	"where":"ADDRESS_ID=3"
}
```


##  Put Custom Web Service 

<p><code>http://&lt;Domain name&gt;:&lt;PORT&gt;/api/[VERSION_NO]/{&lt;Customized Web-Service name&gt;?token=&lt;API Key&gt;&amp;[format=json/xml]</code></p>

Parameters should be populated in the body in the following structure:
```
{
“parameter name 1”:”value”,
“parameter name 2”:”value”
}
```
<table class="unchanged rich-diff-level-one">
<thead>
<tr>
<th><strong>Component</strong></th>
<th><strong>Description</strong></th>
<th><strong>Mandatory</strong></th>
<th><strong>Example</strong></th>
<th><strong>Default</strong></th>
</tr>
</thead>
<tbody>
<tr>
<td>Domain name</td>
<td>Domain name</td>
<td>Y</td>
<td>localhost</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>PORT</td>
<td>PORT</td>
<td>Y</td>
<td>3213</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>Api</td>
<td>API</td>
<td>Y</td>
<td>api</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>VERSION_NO</td>
<td>Version number</td>
<td>N</td>
<td>V1.4</td>
<td>Latest version</td>
</tr>
<tr>
<td>Customized Web-Service name</td>
<td>Name of the Web Service to be executed</td>
<td>Y</td>
<td>Orders</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>Format</td>
<td>Response format</td>
<td>Y</td>
<td>JSON/XML</td>
<td>JSON</td>
</tr>
</tbody>
</table>


## Request Header

<table class="unchanged rich-diff-level-one">
<thead>
<tr>
<th><strong>Parameter</strong></th>
<th><strong>Mandatory</strong></th>
<th><strong>Value</strong></th>
</tr>
</thead>
<tbody>
<tr>
<td>Token</td>
<td>Y</td>
<td>API key</td>
</tr>
<tr>
<td>Accept</td>
<td>Y</td>
<td>Json/XML/CSV</td>
</tr>
<tr>
<td>Any additional parameters</td>
<td>N</td>
<td>Parameter=value&amp; Can be provided on both URL and header</td>
</tr>
</tbody>
</table>

**Example:**

- `http://localhost:3213/api/v1.0/Orders/1/Open?token=ABC&format=json`

  In the body request put:

  ```
  {
   "i_order_id": "1",
   "i_order_status": "Open"
  }
  ```

  Call Web Service Orders and bring the output structure in JSON format according to input parameters i_order_id = 1 and i_order_status=Open

[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/13_Supported_Verbs_Post.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/15_Supported_Verbs_Delete.md)


