# Built-In Common DB Tables APIs

Fabric provides out of the box web services for querying project's Common data and meta data resources.

An appropriate HTTP status codes is used to indicate the status of the executed operation, following standard status codes which are defined by [[RFC7231](https://spec.openapis.org/oas/v3.1.0#bib-RFC7231)] and listed in the [IANA Status Code Registry](https://www.iana.org/assignments/http-status-codes/http-status-codes.xhtml).

Any web-service call passes authentication and authorization validations before executed. For more information see [here]().

All API access is over HTTPS, and accessed from Fabric URL endpoint `https://<Domain Name>:<PORT>`

## Get Common DB Schema (Metadata)

Retrieves the Common  DB schema, describing its tables structure.

**GET** `/api/common[?format=json|xml]`

<table width="900pxl">
<tbody>
<tr>
<td width="150pxl" valign="top">
<p><strong>Component</strong></p>
</td>
<td width="250pxl" valign="top">
<p><strong>Description</strong></p>
</td>
<td width="100pxl" valign="top">
<p><strong>Mandatory</strong></p>
</td>
<td width="200pxl" valign="top">
<p><strong>Example</strong></p>
</td>
<td width="200pxl" valign="top">
<p><strong>Default</strong></p>
</td>
</tr>
</thead>
<tbody>
<tr>
<td width="150pxl" valign="top">
<p>Format</p>
</td>
<td width="250pxl" valign="top">
<p>Response format</p>
</td>
<td width="100pxl" valign="top">
<p>N</p>
</td>
<td width="200pxl" valign="top">
<p>JSON/XML</p>
</td>
<td width="200pxl" valign="top">
<p>JSON</p>
</td>
</tr>
</tbody>
</table>


**Example:**

- `http://localhost:3213/api/common`

  Bring metadata for all COMMON tables



## Get Common Table Data

**GET** `/api/common/<Common Table Name>[?fields=<list of fields separated by comma>[&where=<WHERE STATEMENT>][&format=json|xml]`

<table width="900pxl">
<thead>
<tr>
<td valign="top" width="150pxl">
<p><strong>Component</strong></p>
</td>
<td valign="top" width="250pxl">
<p><strong>Description</strong></p>
</td>
<td valign="top" width="100pxl">
<p><strong>Mandatory</strong></p>
</td>
<td valign="top" width="200pxl">
<p><strong>Example</strong></p>
</td>
<td valign="top" width="200pxl">
<p><strong>Default</strong></p>
</td>
</tr>
</thead>
<tbody>
<tr>
<td valign="top" width="150pxl">
<p>Common Table Name</p>
</td>
<td valign="top" width="250pxl">
<p>Common table name</p>
</td>
<td valign="top" width="100pxl">
<p>Y</p>
</td>
<td valign="top" width="200pxl">
<p>ADDRESSES</p>
</td>
<td valign="top" width="200pxl">&nbsp;</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>where</p>
</td>
<td valign="top" width="250pxl">
<p>Where statement for the common table</p>
</td>
<td valign="top" width="100pxl">
<p>N</p>
</td>
<td valign="top" width="200pxl">
<p>WHERE={CITY=&rsquo;TEL AVIV&rsquo;}</p>
</td>
<td valign="top" width="200pxl">
<p>Can be populated if the COMMON TABLE NAME is populated</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>fields</p>
</td>
<td valign="top" width="250pxl">
<p>List of fields to be returned as output</p>
</td>
<td valign="top" width="100pxl">
<p>N</p>
</td>
<td valign="top" width="200pxl">
<p>fields=CITY,ADDRESS</p>
</td>
<td valign="top" width="200pxl">&nbsp;</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>Format</p>
</td>
<td valign="top" width="250pxl">
<p>Response format</p>
</td>
<td valign="top" width="100pxl">
<p>N</p>
</td>
<td valign="top" width="200pxl">
<p>JSON or XML</p>
</td>
<td valign="top" width="200pxl">
<p>JSON</p>
</td>
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
<p>&nbsp;</p>

 **Examples:**

- `https://localhost:3213/api/common/CITIES?schema=demo`

  Bring all data from CITIES common table related to demo schema

- `https://localhost:3213/api/common/ADDRESSES?fields=CTIY_NAME&where=CITY=’TEL AVIV'`

  Bring city_name from ADDRESSES common table where city is ’TEL AVIV’




##  Create Common Table Data

**POST** `/api/common/<Common Table Name>[?format=json|xml]`



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
<td>Common Table Name</td>
<td>Common Table Name</td>
<td>Y</td>
<td>ADDRESSES</td>
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

- `https://localhost:3213/api/common/REF_NAMES`

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


## Update Common Table Data

**PUT** `/api/common/<Common Table Name>[?format=json|xml]`

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
<td>Common Table Name</td>
<td>Common table name</td>
<td>Y</td>
<td>ADDRESSES</td>
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

The request body shall contain the row data to be updated along with optional "where" element, as the where condition statement.

**Example:**

- `https://localhost:3213/api/common/ADDRESSES`

  Update data in common ADDRESSES table

  Request Body

```json
 {
	"row" : {"ADDRESS_NAME":"YOQNEAM"} ,
	"where":"ADDRESS_ID=3"
}
```



##  Delete Data From a Common Table

**DELETE** `/api/common/<COMMON TABLE NAME>?where=<WHERE CLAUSE>[&format=json|xml]`

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
<td>COMMON TABLE NAME</td>
<td>Common table name</td>
<td>Y</td>
<td>CITIES</td>
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
<td>format</td>
<td>Response format</td>
<td>N</td>
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
<p></p>
</td>
</tr>
</tbody>
</table>

**Example:**

- `https://localhost:3213/api/common/CITIES&where=CITY_ID=1`

  Delete data from CITIES common table where city_id = 1

  



[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/02_built_in_lu_ws.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/04_built_in_fabric_ws.md)

