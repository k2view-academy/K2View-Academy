# Post Verb

Use POST APIs to create **new subordinate resources**. For example, a file that is subordinate to the directory containing it, or a row  that is subordinate to a database table. 

Strictly in terms of REST, POST methods can be used to create a new resource in a collection of resources.

Ideally, if a resource has been created on the origin server, the response code SHOULD be **HTTP 201 Created** and contain:
- An entity describing the request's status.
- A reference to the new resource.
- A [Location](https://en.wikipedia.org/wiki/HTTP_location) header.

Frequently the action performed by the POST method might not result in a resource that can be identified by a URI. In this case, either **HTTP 200 OK** or **204 No Content** are an appropriate response status. Responses to this method are **not cacheable,** unless the response includes the appropriate [Cache-Control](https://en.wikipedia.org/wiki/Web_cache#Cache_control) or [Expires](https://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html) header fields.

Note that POST is **neither safe nor idempotent** and invoking two identical POST requests results in two different resources containing the same information (except resource IDs).

## Post Data Into LU Table

<p><code>http://&lt;Domain name&gt;:&lt;PORT&gt;/api/[VERSION_NO]/COMMON/&lt;common table name&gt;?token=&lt;API Key&gt;&amp;[format=json/xml]</code></p>

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
<td>10.21.1.69</td>
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
<td>Instance ID</td>
<td>Y</td>
<td>1</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>token</td>
<td>API Key</td>
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

- `http://10.21.1.69:3213/api/v1.0/lu/CUSTOMER/1?token=ABC`

  Insert data into CUSTOMER LU instance id 1, LION table

  Request Body

  ```                     
  {"rows" : {"LION" : [{"ID":11, "NAME":"lion11"},{"ID":12, "NAME":"lion12"},{"ID":13, "NAME":"lion13"}]}}
  ```


##  Post Data Into Common Table

<p><code>http://&lt;Domain name&gt;:&lt;PORT&gt;/api/[VERSION_NO]/&lt;customized Web-Service name&gt;?token=&lt;API Key&gt;&amp;[format=json/xml]</code></p>

<table class="unchanged rich-diff-level-one">
<tbody>
<tr>
<th><strong>Component</strong></th>
<th><strong>Description</strong></th>
<th><strong>Mandatory</strong></th>
<th><strong>Example</strong></th>
<th><strong>Default</strong></th>
</tr>
<tr>
<td>Domain name</td>
<td>Domain name</td>
<td>Y</td>
<td>10.21.1.69</td>
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

- `http://10.21.1.69:3213/api/v1.0/COMMON?REF_NAMES&token=ABC&format=json`

  Insert data into common table REF_NAMES

  Request Body
```
{
  "rows": [
    {
      "FIRST_NAME": "XXX",
      "LAST_NAME": "YYY"
    }
  ]
}
```
##  Post Authenticate

* <p><code>http://&lt;Domain name&gt;:&lt;PORT&gt;/api/[VERSION_NO]/authenticate

  Generate a JWT digital signed cookie for Web-Services calls on the same session. Next Fabric Web-services calls will not require a token as a parameter. Located under fabric category.
  
<table class="unchanged">
<tbody>
<tr>
<th><strong>Component</strong></th>
<th><strong>Description</strong></th>
<th><strong>Mandatory</strong></th>
<th><strong>Example</strong></th>
<th><strong>Default</strong></th>
</tr>
<tr>
<td>Domain name</td>
<td>Domain name</td>
<td>Y</td>
<td>10.21.1.69</td>
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
</tbody>
</table>  


**Example:**

`http://10.21.1.69:3213/api/v1.0/authenticate

- Authenticate by apikey (token)

  Request Body

```
{
  "apikey": "string"
}
```

- Authenticate by user/password

  Request Body

```
{
  "username": "string",
  "password": "string",
}
```

##  Post Fabric Commands

* <p><code>http://&lt;Domain name&gt;:&lt;PORT&gt;/api/[VERSION_NO]/fabric-commands

  Run fabric command or a set of fabric commands. Located under fabric category.

<table class="unchanged">
<tbody>
<tr>
<th><strong>Component</strong></th>
<th><strong>Description</strong></th>
<th><strong>Mandatory</strong></th>
<th><strong>Example</strong></th>
<th><strong>Default</strong></th>
</tr>
<tr>
<td>Domain name</td>
<td>Domain name</td>
<td>Y</td>
<td>10.21.1.69</td>
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
</tbody>
</table>

**Example:**

`http://10.21.1.69:3213/api/v1.0/fabric-commands

Request Body

```
{
    "commands": [
        {
            "command":"list ?",
            "params":['lut']

        }
    ]
 }
```

Response Body

```
{
  "results": [
    {
      "resultSet": {
        "columns": [
          "LU_NAME"
        ],
        "rows": [
          [
            "Customer"
          ],
          [
            "CRM"
          ]
        ]
      }
    }
  ]
}
```

##  

##  Post Custom Web Service 

<p><code>http://&lt;Domain name&gt;:&lt;PORT&gt;/api/[VERSION_NO]/&lt;customized Web-Service name&gt;?token=&lt;API Key&gt;&amp;[format=json/xml]</code></p>

Parameters should be populated in the body in the following structure:

```
{
“parameter name 1”:”value”,
“parameter name 2”:”value”
}
```
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
<td>10.21.1.69</td>
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
<td>Customized Web Service name</td>
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

##  Request Header

<table>
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
<td>API Key</td>
</tr>
<tr>
<td>Accept</td>
<td>Y</td>
<td>Json/XML/RAW</td>
</tr>
<tr>
<td>Any additional parameters</td>
<td>N</td>
<td>Parameter=value&amp; Can be provided on both URL and header</td>
</tr>
</tbody>
</table>

**Example:**

- `http://10.21.1.69:3213/api/v1.0/Orders/1/Open?token=ABC&format=json`

  In the body request put:
```
{
 "i_order_id": "1",
 "i_order_status": "Open"
}
```
- Call Web Service Orders and bring output structure in JSON format according to input parameters **i_order_id=1** and **i_order_status=Open**.


[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/12_Supported_Verbs_Get.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/14_Supported_Verbs_Put.md)


