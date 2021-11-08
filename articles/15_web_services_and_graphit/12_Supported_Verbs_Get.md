# Get Verb

Use a GET request without modifying it to retrieve **resource representation** or **information**. 

Since GET requests do not change the status of the resource, they are considered to be a **safe method**. Additionally, GET APIs should be **idempotent**, meaning that multiple identical requests must produce the same result every time until another POST or PUT API changes the status of the resource on the server.

- If the Request-URI refers to a data-producing process, the produced data is returned as the entity in the response and not the source text of the process, unless that text is the output of the process.
- If the resource of a given HTTP GET API is found on the server, it returns the HTTP 200 OK response code together with the response body, which is either XML or JSON content (due to their platform independent nature). 
- If the resource is NOT found on server, it returns the HTTP 404 NOT FOUND response code. 
- If the GET request is incorrectly written, the server returns the HTTP 400 BAD REQUEST response code.

The new REST API is fully integrated with the open API (Swagger) whereby the structure of the body of the response is known before the API is called.

# Get LU Data

<p><code>http://&lt;Domain name&gt;:&lt;PORT&gt;/api/[VERSION_NO]/lu/&lt;LU Name&gt;/&lt;iid&gt;/[[TABLE_NAME]?fields=VALUE1,VALUE2&amp;where=WHERE STATEMENT]] query=QUERY STATMENT]&amp;token=[API Key]&amp;[format=json/xml]&amp;SET={mode,value}</code></p>

 <table width="900pxl">
<tbody>
<tr>
<td width="150pxl" valign="top">
<p><strong>Component</strong></p>
</td>
<td width="250pxl" valign="top" >
<p><strong>Description</strong></p>
</td>
<td width="100pxl" valign="top" >
<p><strong>Mandatory</strong></p>
</td>
<td width="200pxl" valign="top" >
<p><strong>Example</strong></p>
</td>
<td width="200pxl" valign="top" >
<p><strong>Default</strong></p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top" >
<p>Domain name</p>
</td>
<td width="250pxl" valign="top" >
<p>Domain name</p>
</td>
<td width="100pxl" valign="top" >
<p>Y</p>
</td>
<td width="200pxl" valign="top" >
<p>localhost</p>
</td>
<td width="200pxl" valign="top" >&nbsp;</td>
</tr>
<tr>
<td width="150pxl" valign="top" >
<p>PORT</p>
</td>
<td width="250pxl" valign="top" >
<p>PORT</p>
</td>
<td width="100pxl" valign="top" >
<p>Y</p>
</td>
<td width="200pxl" valign="top" >
<p>3213</p>
</td>
<td width="200pxl" valign="top" >&nbsp;</td>
</tr>
<tr>
<td width="150pxl" valign="top" >
<p>api</p>
</td>
<td width="250pxl" valign="top" >
<p>API</p>
</td>
<td width="100pxl" valign="top" >
<p>Y</p>
</td>
<td width="200pxl" valign="top" >
<p>api</p>
</td>
<td width="200pxl" valign="top" >&nbsp;</td>
</tr>
<tr>
<td width="150pxl" valign="top" >
<p>VERSION_NO</p>
</td>
<td width="250pxl" valign="top" >
<p>Version number</p>
</td>
<td width="100pxl" valign="top" >
<p>N</p>
</td>
<td width="200pxl" valign="top" >
<p>V1.4</p>
</td>
<td width="200pxl" valign="top" >
<p>Latest version</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top" >
<p>lu</p>
</td>
<td width="250pxl" valign="top" >
<p>Lu</p>
</td>
<td width="100pxl" valign="top" >
<p>Y</p>
</td>
<td width="200pxl" valign="top" >
<p>lu</p>
</td>
<td width="200pxl" valign="top" >&nbsp;</td>
</tr>
<tr>
<td width="150pxl" valign="top" >
<p>LU Name</p>
</td>
<td width="250pxl" valign="top" >
<p>Logical unit name</p>
</td>
<td width="100pxl" valign="top" >
<p>Y</p>
</td>
<td width="200pxl" valign="top" >
<p>CUSTOMER</p>
</td>
<td width="200pxl" valign="top" >&nbsp;</td>
</tr>
<tr>
<td width="150pxl" valign="top" >
<p>Iid</p>
</td>
<td width="250pxl" valign="top" >
<p>Instance ID</p>
</td>
<td width="100pxl" valign="top" >
<p>Y</p>
</td>
<td width="200pxl" valign="top" >
<p>1</p>
</td>
<td width="200pxl" valign="top" >&nbsp;</td>
</tr>
<tr>
<td width="150pxl" valign="top" >
<p>TABLE_NAME</p>
</td>
<td width="250pxl" valign="top" >
<p>Table name</p>
</td>
<td width="100pxl" valign="top" >
<p>N</p>
</td>
<td width="200pxl" valign="top" >
<p>PAYMENT</p>
</td>
<td width="200pxl" valign="top" >
<p>All tables</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top" >
<p>Fields</p>
</td>
<td width="250pxl" valign="top" >
<p>Field name</p>
</td>
<td width="100pxl" valign="top" >
<p>N</p>
</td>
<td width="200pxl" valign="top" >
<p>fields=CUSTOMER_ID, INVOICE_ID</p>
</td>
<td width="200pxl" valign="top" >
<p>Multiple fields supported</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top" >
<p>where</p>
</td>
<td width="250pxl" valign="top" >
<p>Where statement for the selected table</p>
</td>
<td width="100pxl" valign="top" >
<p>N</p>
</td>
<td width="200pxl" valign="top" >
<p>where=NAME=&rsquo;MOSHE&rsquo; OR ADDRESS=&rsquo;TEL AVIV&rsquo;</p>
</td>
<td width="200pxl" valign="top" >
<p>Can be populated if FIELDS are populated</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top" >
<p>QUERY</p>
</td>
<td width="250pxl" valign="top" >
<p>Where statement for cross LU tables on the same LU</p>
</td>
<td width="100pxl" valign="top" >
<p>N</p>
</td>
<td width="200pxl" valign="top" >
<p>QUERY=SELECT NAME from ADDRESS_DATA A,ADDRESS_NAME_LINK B WHERE A.ADDRESS_ID =B.ADDRESS_ID</p>
</td>
<td width="200pxl" valign="top" >&nbsp;</td>
</tr>
<tr>
<td width="150pxl" valign="top" >
<p>token</p>
</td>
<td width="250pxl" valign="top" >
<p>API Key</p>
</td>
<td width="100pxl" valign="top" >
<p>Y</p>
</td>
<td width="200pxl" valign="top" >&nbsp;</td>
<td width="200pxl" valign="top" >&nbsp;</td>
</tr>
<tr>
<td width="150pxl" valign="top" >
<p>format</p>
</td>
<td width="250pxl" valign="top" >
<p>Response format</p>
</td>
<td width="100pxl" valign="top" >
<p>Y</p>
</td>
<td width="200pxl" valign="top" >
<p>JSON/XML</p>
</td>
<td width="200pxl" valign="top" >
<p>JSON</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top" >
<p>Set</p>
</td>
<td width="250pxl" valign="top" >
<p>Sync mode</p>
</td>
<td width="100pxl" valign="top" >
<p>N</p>
</td>
<td width="200pxl" valign="top" >
<p>SET=sync,off SET=sync,on SET=sync,force</p>
</td>
<td width="200pxl" valign="top" >
<p>Sync policy on the session</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top" >&nbsp;Set</td>
<td width="250pxl" valign="top" >
<p>Environment</p>
</td>
<td width="100pxl" valign="top" >
<p>N</p>
</td>
<td width="200pxl" valign="top" >
<p>SET=ENVIRONMENT,&rsquo;ENV1&rsquo;</p>
</td>
<td width="200pxl" valign="top" >
<p>_dev</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top" >&nbsp;Set</td>
<td width="250pxl" valign="top" >
<p>Sync_timeout</p>
</td>
<td width="100pxl" valign="top" >
<p>N</p>
</td>
<td width="200pxl" valign="top" >
<p>SET=SYNC_TIMEOUT,10</p>
</td>
<td width="200pxl" valign="top" >
<p>Set on config.ini</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top" >&nbsp;Set</td>
<td width="250pxl" valign="top" >
<p>instance_ttl</p>
</td>
<td width="100pxl" valign="top" >
<p>N</p>
</td>
<td width="200pxl" valign="top" >
<p>SET=instance_ttl,10</p>
</td>
<td width="200pxl" valign="top" >&nbsp;</td>
</tr>
<tr>
<td width="150pxl" valign="top" >&nbsp;Set</td>
<td width="250pxl" valign="top" >
<p>Environment variable</p>
</td>
<td width="100pxl" valign="top" >
<p>N</p>
</td>
<td width="200pxl" valign="top" >
<p>SET=A,4</p>
</td>
<td width="200pxl" valign="top" >&nbsp;</td>
</tr>
</tbody>
</table>
<p>&nbsp;</p>

**Examples:**

- `http://localhost:3213/api/v1.0/lu/CUSTOMER/1?token=ABC`

  Bring all data for CUSTOMER LU Instance ID 1

  Response Body: response body supports streaming solution


- `http://localhost:3213/api/v1.0/lu/CUSOMTER/1/ALLERGIES?token=ABC`

  Bring data for CUSTOMER LU Instance ID 1, table ALLERGIES  


- `http://localhost:3213/api/v1.0/lu/CUSTOMER/1/PAYMENT?fields=PAYMENT_ID,PAYMENT_DATE&where=PAYMENT_STATUS!=’CLOSED’&token=ABC`

  Bring data for CUSTOMER LU Instance ID 1, table PAYMENT, fields PAYMENT_ID, PAYMENT_DATE where payments are not closed.


- `http://localhost:3213/api/v1.0/lu/CUSTOMER/1?query=SELECT FIRST_NAME FROM ADDRESS_DATA A, ADDRESS_NAME_LINK B WHERE A.ADDRESS_ID =B.ADDRESS_ID AND  B.ADDRESS_TYPE=’P’&token=ABC`
  
  Bring data for CUSTOMER LU Instance ID 1, table ADDRESS_DATA field FIRST_NAME where name type is private.

- `http://localhost:3213/api/v1.0/lu/CUSTOMER/1?query=SELECT FIRST_NAME FROM ADDRESS_DATA A,ADDRESS_NAME_LINK B WHERE A.ADDRESS_ID =B.ADDRESS_ID AND B.ADDRESS_TYPE=’P’&token=ABC&set=sync,force&SET=ENVIRONMENT,ENV1&set=GLOBAL_LION,10`
  
  Bring data for CUSTOMER LU Instance ID 1, table ADDRESS_DATA field FIRST_NAME where name type is private. Make sure sync mode is force and  run it on ENV1 and set GLOBAL_LION to 10

# Get LU Schema (Metadata)

<p><code>http://&lt;Domain name&gt;:&lt;PORT&gt;/api/[VERSION_NO]/lu/&lt;LU Name&gt;?token=&lt;API Key&gt;&amp;[format=json/xml/yaml]</code></p>

<table width="900pxl">
<tbody>
<tr>
<td width="150pxl" valign="top">
<p><strong>Component</strong></p>
</td>
<td width="250pxl" valign="top" >
<p><strong>Description</strong></p>
</td>
<td width="100pxl" valign="top" >
<p><strong>Mandatory</strong></p>
</td>
<td width="200pxl" valign="top" >
<p><strong>Example</strong></p>
</td>
<td width="200pxl" valign="top" >
<p><strong>Default</strong></p>
</td>
</tr>
</thead>
<tbody>
<tr>
<td width="150pxl" valign="top" >
<p>Domain name</p>
</td>
<td width="250pxl" valign="top" >
<p>Domain name</p>
</td>
<td width="100pxl" valign="top" >
<p>Y</p>
</td>
<td width="200pxl" valign="top" >
<p>localhost</p>
</td>
<td width="200pxl" valign="top" >&nbsp;</td>
</tr>
<tr>
<td width="150pxl" valign="top" >
<p>PORT</p>
</td>
<td width="250pxl" valign="top" >
<p>PORT</p>
</td>
<td width="100pxl" valign="top" >
<p>Y</p>
</td>
<td width="200pxl" valign="top" >
<p>3213</p>
</td>
<td width="200pxl" valign="top" >&nbsp;</td>
</tr>
<tr>
<td width="150pxl" valign="top" >
<p>Api</p>
</td>
<td width="250pxl" valign="top" >
<p>API</p>
</td>
<td width="100pxl" valign="top" >
<p>Y</p>
</td>
<td width="200pxl" valign="top" >
<p>api</p>
</td>
<td width="200pxl" valign="top" >&nbsp;</td>
</tr>
<tr>
<td width="150pxl" valign="top" >
<p>VERSION_NO</p>
</td>
<td width="250pxl" valign="top" >
<p>Version number</p>
</td>
<td width="100pxl" valign="top" >
<p>N</p>
</td>
<td width="200pxl" valign="top" >
<p>V1.4</p>
</td>
<td width="200pxl" valign="top" >
<p>Latest version</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top" >
<p>Lu</p>
</td>
<td width="250pxl" valign="top" >
<p>lu</p>
</td>
<td width="100pxl" valign="top" >
<p>Y</p>
</td>
<td width="200pxl" valign="top" >
<p>lu</p>
</td>
<td width="200pxl" valign="top" >&nbsp;</td>
</tr>
<tr>
<td width="150pxl" valign="top" >
<p>LU Name</p>
</td>
<td width="250pxl" valign="top" >
<p>Logical unit name</p>
</td>
<td width="100pxl" valign="top" >
<p>Y</p>
</td>
<td width="200pxl" valign="top" >
<p>CUSTOMER</p>
</td>
<td width="200pxl" valign="top" >&nbsp;</td>
</tr>
<tr>
<td width="150pxl" valign="top" >
<p>Token</p>
</td>
<td width="250pxl" valign="top" >
<p>API Key</p>
</td>
<td width="100pxl" valign="top" >
<p>Y</p>
</td>
<td width="200pxl" valign="top" >&nbsp;</td>
<td width="200pxl" valign="top" >&nbsp;</td>
</tr>
<tr>
<td width="150pxl" valign="top" >
<p>Format</p>
</td>
<td width="250pxl" valign="top" >
<p>Response format</p>
</td>
<td width="100pxl" valign="top" >
<p>Y</p>
</td>
<td width="200pxl" valign="top" >
<p>JSON/XML</p>
</td>
<td width="200pxl" valign="top" >
<p>JSON</p>
</td>
</tr>
</tbody>
</table>
<p>&nbsp;</p>

**Examples:**

- `http://localhost:3213/api/v1.0/lu/CUSTOMER?token=ABC`

  Bring metadata for CUSTOMER LU

# Get Common Schema (Metadata)

`http://[Domain name]:[PORT]/api/[VERSION_NO]/COMMON/[table name]?token=[API Key]&[format=json/xml/yaml]`

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
<p>Domain name</p>
</td>
<td width="250pxl" valign="top">
<p>Domain name</p>
</td>
<td width="100pxl" valign="top">
<p>Y</p>
</td>
<td width="200pxl" valign="top">
<p>localhost</p>
</td>
<td width="200pxl" valign="top">&nbsp;</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>PORT</p>
</td>
<td width="250pxl" valign="top">
<p>PORT</p>
</td>
<td width="100pxl" valign="top">
<p>Y</p>
</td>
<td width="200pxl" valign="top">
<p>3213</p>
</td>
<td width="200pxl" valign="top">&nbsp;</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>Api</p>
</td>
<td width="250pxl" valign="top">
<p>API</p>
</td>
<td width="100pxl" valign="top">
<p>Y</p>
</td>
<td width="200pxl" valign="top">
<p>api</p>
</td>
<td width="200pxl" valign="top">&nbsp;</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>VERSION_NO</p>
</td>
<td width="250pxl" valign="top">
<p>Version number</p>
</td>
<td width="100pxl" valign="top">
<p>N</p>
</td>
<td width="200pxl" valign="top">
<p>V1.4</p>
</td>
<td width="200pxl" valign="top">
<p>Latest version</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>COMMON</p>
</td>
<td width="250pxl" valign="top">
<p>Common tables</p>
</td>
<td width="100pxl" valign="top">
<p>Y</p>
</td>
<td width="200pxl" valign="top">
<p>COMMON</p>
</td>
<td width="200pxl" valign="top">&nbsp;</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>Token</p>
</td>
<td width="250pxl" valign="top">
<p>API Key</p>
</td>
<td width="100pxl" valign="top">
<p>Y</p>
</td>
<td width="200pxl" valign="top">&nbsp;</td>
<td width="200pxl" valign="top">&nbsp;</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>Format</p>
</td>
<td width="250pxl" valign="top">
<p>Response format</p>
</td>
<td width="100pxl" valign="top">
<p>Y</p>
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
<p>&nbsp;</p>

**Example:**

- `http://localhost:3213/api/v1.0/COMMON?token=ABC`

  Bring metadata for all COMMON tables

# Get Common Table

<p><code>http://&lt;Domain name&gt;:&lt;PORT&gt;/api/[VERSION_NO]/common/[COMMON TABLE NAME?fields=list of fields separated by comma&amp;where=WHERE STATEMENT]&amp;token=&lt;API Key&gt;&amp;[format=json/xml]</code></p>

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
<p>Domain name</p>
</td>
<td valign="top" width="250pxl">
<p>Domain name</p>
</td>
<td valign="top" width="100pxl">
<p>Y</p>
</td>
<td valign="top" width="200pxl">
<p>localhost</p>
</td>
<td valign="top" width="200pxl">&nbsp;</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>PORT</p>
</td>
<td valign="top" width="250pxl">
<p>PORT</p>
</td>
<td valign="top" width="100pxl">
<p>Y</p>
</td>
<td valign="top" width="200pxl">
<p>3213</p>
</td>
<td valign="top" width="200pxl">&nbsp;</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>Api</p>
</td>
<td valign="top" width="250pxl">
<p>API</p>
</td>
<td valign="top" width="100pxl">
<p>Y</p>
</td>
<td valign="top" width="200pxl">
<p>api</p>
</td>
<td valign="top" width="200pxl">&nbsp;</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>COMMON</p>
</td>
<td valign="top" width="250pxl">
<p>Specify that scope is common</p>
</td>
<td valign="top" width="100pxl">
<p>Y</p>
</td>
<td valign="top" width="200pxl">
<p>COMMON</p>
</td>
<td valign="top" width="200pxl">&nbsp;</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>COMMON TABLE NAME</p>
</td>
<td valign="top" width="250pxl">
<p>Common table name</p>
</td>
<td valign="top" width="100pxl">
<p>N</p>
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
<p>Token</p>
</td>
<td valign="top" width="250pxl">
<p>API Key</p>
</td>
<td valign="top" width="100pxl">
<p>Y</p>
</td>
<td valign="top" width="200pxl">&nbsp;</td>
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
<p>Y</p>
</td>
<td valign="top" width="200pxl">
<p>JSON/XML</p>
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

- `http://localhost:3213/api/v1.0/COMMON/CITIES?token=ABC&schema=demo`

  Bring all data from CITIES common table related to demo schema

- `[http://localhost:3213/api/v1.0/COMMON/ADDRESSES?fields=CTIY_NAME&where=CITY=’TEL](http://localhost:3213/api/v1.0/COMMON/ADDRESSES?fields=CTIY_NAME&where=CITY='TEL) AVIV’&token=ABC`

  Bring city_name from ADDRESSES common table where city is ’TEL AVIV’

# Get Based On Graphit File

<p><code>http://&lt;Domain Name&gt;:&lt;PORT&gt;/api/[VERSION_NO]/&lt;Graphit file name&gt;/ PARAM1 VALUE1/PARAM2 VALUE2?token=&lt;API Key&gt;&amp;[format=json/xml/yaml]</code></p>

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
<p>Domain name</p>
</td>
<td valign="top" width="250pxl">
<p>Domain name</p>
</td>
<td valign="top" width="100pxl">
<p>Y</p>
</td>
<td valign="top" width="200pxl">
<p>localhost</p>
</td>
<td valign="top" width="200pxl">&nbsp;</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>PORT</p>
</td>
<td valign="top" width="250pxl">
<p>PORT</p>
</td>
<td valign="top" width="100pxl">
<p>Y</p>
</td>
<td valign="top" width="200pxl">
<p>3213</p>
</td>
<td valign="top" width="200pxl">&nbsp;</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>Api</p>
</td>
<td valign="top" width="250pxl">
<p>API</p>
</td>
<td valign="top" width="100pxl">
<p>Y</p>
</td>
<td valign="top" width="200pxl">
<p>api</p>
</td>
<td valign="top" width="200pxl">&nbsp;</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>VERSION_NO</p>
</td>
<td valign="top" width="250pxl">
<p>Version number</p>
</td>
<td valign="top" width="100pxl">
<p>N</p>
</td>
<td valign="top" width="200pxl">
<p>V1.4</p>
</td>
<td valign="top" width="200pxl">
<p>1.0</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>Token</p>
</td>
<td valign="top" width="250pxl">
<p>API Key</p>
</td>
<td valign="top" width="100pxl">
<p>Y</p>
</td>
<td valign="top" width="200pxl">&nbsp;</td>
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
<p>Y</p>
</td>
<td valign="top" width="200pxl">
<p>JSON/XML</p>
</td>
<td valign="top" width="200pxl">
<p>JSON</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>Graphit file name</p>
</td>
<td valign="top" width="250pxl">
<p>Name of graphit file</p>
</td>
<td valign="top" width="100pxl">
<p>Y</p>
</td>
<td valign="top" width="200pxl">
<p>file name should include the version number customer_query.1.4.graphit</p>
</td>
<td valign="top" width="200pxl">&nbsp;</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>PARAM1&hellip;N</p>
</td>
<td valign="top" width="250pxl">
<p>Input parameter 1..N name and value to the graphit file</p>
</td>
<td valign="top" width="100pxl">
<p>N</p>
</td>
<td valign="top" width="200pxl">
<p>iid=1&amp;name=moshe</p>
</td>
<td valign="top" width="200pxl">
<p>Supported only for Post verb, should be part of the request body {parameter name:parameter value}</p>
</td>
</tr>
</tbody>
</table>
<p>&nbsp;</p>

**Example**

- `http://localhost:3213/api/v1.3/graphit/customer_query/1?token=ABC`

  Run the Web Service according to the customer_query.1.3 Graphit file, send 2 parameters as input (id =1 and name = moshe) and response body should input JSON structure be defined on the Graphit file.

   Should call Graphit: customer_query.1.3.graphit file.

# Get Custom Web Service

<p><code>http://&lt;Domain name&gt;:&lt;PORT&gt;/api/[VERSION_NO]/{customized Web-Service name}/PARAM1 VALUE1/PARAM2 VALUE2?token=&lt;API Key&gt;&amp;[format=json/xml]</code></p>

<table width="900pxl">
<thead>
<tr>
<td valign="top" width="150pxl">
<p><strong>Component</strong></p>
</td>
<td valign="top" width="200pxl">
<p><strong>Description</strong></p>
</td>
<td valign="top" width="100pxl">
<p><strong>Mandatory</strong></p>
</td>
<td valign="top" width="300pxl">
<p><strong>Example</strong></p>
</td>
<td valign="top" width="150pxl">
<p><strong>Default</strong></p>
</td>
</tr>
</thead>
<tbody>
<tr>
<td valign="top" width="150pxl">
<p>Domain name</p>
</td>
<td valign="top" width="200pxl">
<p>Domain name</p>
</td>
<td valign="top" width="100pxl">
<p>Y</p>
</td>
<td valign="top" width="300pxl">
<p>localhost</p>
</td>
<td valign="top" width="150pxl">&nbsp;</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>PORT</p>
</td>
<td valign="top" width="200pxl">
<p>PORT</p>
</td>
<td valign="top" width="100pxl">
<p>Y</p>
</td>
<td valign="top" width="300pxl">
<p>3213</p>
</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>
<p>Api</p>
</td>
<td>
<p>API</p>
</td>
<td>
<p>Y</p>
</td>
<td>
<p>api</p>
</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>
<p>VERSION_NO</p>
</td>
<td>
<p>Version number</p>
</td>
<td>
<p>N</p>
</td>
<td>
<p>V1.4</p>
</td>
<td>
<p>Latest version</p>
</td>
</tr>
<tr>
<td>
<p>Customized Web Service name</p>
</td>
<td>
<p>Name of the Web Service to be executed</p>
</td>
<td>
<p>Y</p>
</td>
<td>
<p>Orders</p>
</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>
<p>PARAM1&hellip;N</p>
</td>
<td>
<p>Web Service input parameters</p>
</td>
<td>
<p>N</p>
</td>
<td valign="top" width="300pxl">
<p>/1/3 Assuming two parameters as input i_order_id and order_status it will pass 1 to i_order_id and 3 to i_order_status</p>
</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>
<p>Format</p>
</td>
<td>
<p>Response format</p>
</td>
<td>
<p>Y</p>
</td>
<td>
<p>JSON/XML</p>
</td>
<td>
<p>JSON</p>
</td>
</tr>
</tbody>
</table>
<p>&nbsp;</p>

**Example:**

- `http://localhost:3213/api/v1.0/Orders/1/Open?token=ABC&format=json`

  Call **Orders** Web Service and bring the output structure in JSON format according to input parameters i_order_id = 1 and i_order_status=Open

# isAlive

Indicates whether the Fabric is up and running on the given domain and port. 

<p><code>http://&lt;Domain name&gt;:&lt;PORT&gt;/api/isAlive</code></p>

<table width="900pxl">
<thead>
<tr>
<td valign="top" width="150pxl">
<p><strong>Component</strong></p>
</td>
<td valign="top" width="200pxl">
<p><strong>Description</strong></p>
</td>
<td valign="top" width="100pxl">
<p><strong>Mandatory</strong></p>
</td>
<td valign="top" width="300pxl">
<p><strong>Example</strong></p>
</td>
<td valign="top" width="150pxl">
<p><strong>Default</strong></p>
</td>
</tr>
</thead>
<tbody>
<tr>
<td valign="top" width="150pxl">
<p>Domain name</p>
</td>
<td valign="top" width="200pxl">
<p>Domain name</p>
</td>
<td valign="top" width="100pxl">
<p>Y</p>
</td>
<td valign="top" width="300pxl">
<p>localhost</p>
</td>
<td valign="top" width="150pxl">&nbsp;</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>PORT</p>
</td>
<td valign="top" width="200pxl">
<p>PORT</p>
</td>
<td valign="top" width="100pxl">
<p>Y</p>
</td>
<td valign="top" width="300pxl">
<p>3213</p>
</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>
<p>Api</p>
</td>
<td>
<p>API</p>
</td>
<td>
<p>Y</p>
</td>
<td>
<p>api</p>
</td>
<td>&nbsp;</td>
</tr>
</tbody>
</table>

**Example:**

- `http://localhost:3213/api/isAlive`

  

# Request Header

<table width="900pxl">
<thead>
<tr>
<td valign="top" width="300pxl">
<p><strong>Parameter</strong></p>
</td>
<td valign="top" width="100pxl">
<p><strong>Mandatory</strong></p>
</td>
<td valign="top" width="500pxl">
<p><strong>Value</strong></p>
</td>
</tr>
</thead>
<tbody>
<tr>
<td valign="top" width="300pxl">
<p>Token</p>
</td>
<td valign="top" width="100pxl">
<p>Y</p>
</td>
<td valign="top" width="500pxl">
<p>API Key</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<p>Accept</p>
</td>
<td valign="top" width="100pxl">
<p>Y</p>
</td>
<td valign="top" width="500pxl">
<p>Json/XML/RAW/CSV</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<p>Any additional parameters</p>
</td>
<td valign="top" width="100pxl">
<p>N</p>
</td>
<td valign="top" width="500pxl">
<p>Parameter=value, can be provided on both the URL and header</p>
</td>
</tr>
</tbody>
</table>
<p>&nbsp;</p>


# Response Body During A Failure

During a failure, the error description structure is returned according to RFC 7807 guidelines with the required details.

[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/11_response_codes.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/13_Supported_Verbs_Post.md)


