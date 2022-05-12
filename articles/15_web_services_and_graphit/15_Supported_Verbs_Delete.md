# Delete Verb

Use a DELETE API to delete resources that are identified by the Request-URI.

A successful response to DELETE requests SHOULD be:

- **HTTP 200 OK**, if the response includes an entity describing the status.  
- **HTTP 202 Accepted**, if the action has been queued.  
- **HTTP 204 No Content**, if the action has been performed but the response does not include an entity. 

DELETE operations are **idependent**. When a resouce is deleted, it is removed from the collection of resources and remains so, even when the DELETE API is called on the same resource repeatedly. The **404 NOT FOUND** response code is returned when the DELETE API is called on a deleted resource for the second time. Some may argue that this makes the DELETE method non-idempotent. It is a matter of discussion and personal opinion.

If the request passes through a cache and the Request-URI identifies one or more currently cached entities, these entries SHOULD be handled as stale. Responses to this method are **not cacheable**.

## DELETE LUI

<p><code>http://&lt;Domain name&gt;:&lt;PORT&gt;/api/[VERSION_NO]/&lt;LU Name&gt;/&lt;iid&gt;&amp;token=&lt;TOKEN NAME&gt;&amp;[format=json/xml]</p></code>

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
<td>LU Name</td>
<td>Logical unit name or COMMON for common tables</td>
<td>Y</td>
<td>PATIENT COMMON</td>
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
<td>Token</td>
<td>API key</td>
<td>Y</td>
<td>&nbsp;</td>
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

**Example:**

- `http://localhost:3213/api/v1.0/lu/PATIENT/1?token=ABC`

  Delete LUI 1 from PATIENT LU

  Request Body: null

  Response Body: null

  Response code: 200 if delete ended successfully



## DELETE Data From LU Table

<p><code>http://&lt;Domain name&gt;:&lt;PORT&gt;/api/[VERSION_NO]/lu/&lt;LU Name&gt;/&lt;iid&gt;/&lt;TABLE_NAME&gt;&amp;token=&lt;API Key&gt;&amp;[format=json/xml]</code></p>

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
<td>LU name</td>
<td>Logical unit name or COMMON for common tables</td>
<td>Y</td>
<td>PATIENT COMMON</td>
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
<td>Table name for data deletion</td>
<td>Y</td>
<td>PAYMENT</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>WHERE CLAUSE</td>
<td>Where clause statement</td>
<td>Y</td>
<td>INVOICE_ID=1</td>
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
</tbody>
</table>

**Example:**

- `http://localhost:3213/api/v1.0/lu/PATIENT/1/INVOICE?WHERE=CUSTOMER=1 or NAME=’LION’&token=ABC`

  Delete data from PATIENT LU instance id 1, INVOICE table by where clause

  Request Body: null

  Response Body: null

  Response code: 200 if delete ended successfully

##  Delete Data From Common Table

<p><code>http://&lt;Domain Name&gt;:&lt;PORT&gt;/api/[VERSION_NO]/COMMON/&lt;COMMON TABLE NAME&gt;?&lt;WHERE CLAUSE&gt;&amp;token=&lt;API Key&gt;&amp;[format=json/xml]</p></code>

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
<td>WHERE CLAUSE</td>
<td>Where clause statement</td>
<td>Y</td>
<td>CITY_ID=1</td>
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

- `http://localhost:3213/api/v1.0/COMMON?CITIES&WHERE CITY_ID=1&token=ABC`

  Delete data from CITIES common table where city_id = 1

  Request Body: null

  Response Body: null

  Response code: 200 if delete ended successfully


## Delete Custom Web Service 

Delete works like GET. All parameters should be populated on the URL or header.

[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/14_Supported_Verbs_Put.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/16_rest_api_additions.md)


