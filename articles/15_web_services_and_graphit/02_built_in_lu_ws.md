# Built-In Logical Units APIs

Fabric provides out-of-the-box web services APIs for querying a project's LU data and meta data resources.

An appropriate HTTP status code is used to indicate the status of the executed operation, following standardized status codes that are defined by [[RFC7231](https://spec.openapis.org/oas/v3.1.0#bib-RFC7231)] and listed in the [IANA Status Code Registry](https://www.iana.org/assignments/http-status-codes/http-status-codes.xhtml).

Any web-service call passes authentication and authorization validations before being executed. For more information - see [here]().

All API accesses are over HTTPS, and are accessed from the Fabric URL endpoint `https://<Domain Name>:<PORT>`



## Get LU Schema (Metadata)

<span style="border-radius: 2em; background-color: #0969da; padding: 0 7px; color:white">GET</span>   `/api/lu/<LU Name>[?format=json|xml]`

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
<p>Format</p>
</td>
<td width="250pxl" valign="top" >
<p>Response format</p>
</td>
<td width="100pxl" valign="top" >
<p>N</p>
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

- `https://localhost:3213/api/lu/CUSTOMER`

  Brings metadata for CUSTOMER LU



## Get LU Data

Retrieves either the whole LUI data or a specific LUI table

<span style="border-radius: 2em; background-color: #0969da; padding: 0 7px; color:white">GET</span>   `/api/lu/<LU Name>/<iid>/[<TABLE NAME>][?fields=<VALUE1,VALUE2>][&where=<WHERE STATEMENT>][&query=<QUERY STATMENT>][&limit=<Number of rows to get>][&set={mode,value}][&format=json|xml]`

<table>
<tbody>
<tr>
<td  valign="top">
<p><strong>Component</strong></p>
</td>
<td  valign="top" >
<p><strong>Description</strong></p>
</td>
<td  valign="top" >
<p><strong>Mandatory</strong></p>
</td>
<td  valign="top" >
<p><strong>Example</strong></p>
</td>
<td  valign="top" >
<p><strong>Default</strong></p>
</td>
</tr>
<tr>
<td  valign="top" >
<p>LU Name</p>
</td>
<td  valign="top" >
<p>Logical unit name</p>
</td>
<td  valign="top" >
<p>Y</p>
</td>
<td  valign="top" >
<p>CUSTOMER</p>
</td>
<td  valign="top" >&nbsp;</td>
</tr>
<tr>
<td  valign="top" >
<p>iid</p>
</td>
<td  valign="top" >
<p>Instance ID</p>
</td>
<td  valign="top" >
<p>Y</p>
</td>
<td  valign="top" >
<p>1</p>
</td>
<td  valign="top" >&nbsp;</td>
</tr>
<tr>
<td  valign="top" >
<p>TABLE NAME</p>
</td>
<td  valign="top" >
<p>The LU table name</p>
</td>
<td  valign="top" >
<p>N</p>
</td>
<td  valign="top" >
<p>PAYMENT</p>
</td>
<td  valign="top" >
<p>When omitted, retrieves all tables data</p>
</td>
</tr>
<tr>
<td  valign="top" >
<p>fields</p>
</td>
<td  valign="top" >
<p>Specify specific fields to be retrieved</p>
</td>
<td  valign="top" >
<p>N</p>
</td>
<td  valign="top" >
<p>fields=CUSTOMER_ID, INVOICE_ID</p>
</td>
<td  valign="top" >
<p>When omitted, all fields are retrieved</p>
</td>
</tr>
<tr>
<td  valign="top" >
<p>where</p>
</td>
<td  valign="top" >
<p>Where statement for the selected table</p>
</td>
<td  valign="top" >
<p>N</p>
</td>
<td  valign="top" >
<p>where=NAME=&rsquo;MOSHE&rsquo; OR ADDRESS=&rsquo;TEL AVIV&rsquo;</p>
</td>
<td  valign="top" >
<p>Can be populated if FIELDS are populated</p>
</td>
</tr>
<tr>
<td  valign="top" >
<p>QUERY</p>
</td>
<td  valign="top" >
<p>Where statement for cross LU tables on the same LU</p>
</td>
<td  valign="top" >
<p>N</p>
</td>
<td  valign="top" >
<p>QUERY=SELECT NAME from ADDRESS_DATA A,ADDRESS_NAME_LINK B WHERE A.ADDRESS_ID =B.ADDRESS_ID</p>
</td>
<td  valign="top" >&nbsp;</td>
</tr>
<tr>
<td  valign="top" >
<p>set</p>
</td>
<td  valign="top" >
<p>Sync mode</p>
</td>
<td  valign="top" >
<p>N</p>
</td>
<td  valign="top" >
<p>set=sync,off SET=sync,on SET=sync,force</p>
</td>
<td  valign="top" >
<p>Sync policy on the session</p>
</td>
</tr>
<tr>
<td  valign="top" >&nbsp;set</td>
<td  valign="top" >
<p>Environment</p>
</td>
<td  valign="top" >
<p>N</p>
</td>
<td  valign="top" >
<p>set=ENVIRONMENT,&rsquo;ENV1&rsquo;</p>
</td>
<td  valign="top" >
<p>_dev</p>
</td>
</tr>
<tr>
<td valign="top" >&nbsp;set</td>
<td  valign="top" >
<p>Sync_timeout</p>
</td>
<td  valign="top" >
<p>N</p>
</td>
<td  valign="top" >
<p>set=SYNC_TIMEOUT,10</p>
</td>
<td  valign="top" >
<p>Set on config.ini</p>
</td>
</tr>
<tr>
<td  valign="top" >&nbsp;set</td>
<td  valign="top" >
<p>instance_ttl</p>
</td>
<td  valign="top" >
<p>N</p>
</td>
<td  valign="top" >
<p>set=instance_ttl,10</p>
</td>
<td  valign="top" >&nbsp;</td>
</tr>
<tr>
<td  valign="top" >set</td>
<td  valign="top" >
Environment variable
</td>
<td  valign="top" >
<p>N</p>
</td>
<td  valign="top" >
<p>set=A,4</p>
</td>
<td  valign="top" >&nbsp;</td>
</tr>
<tr>
<td  valign="top" >
<p>format</p>
</td>
<td  valign="top" >
<p>Response format</p>
</td>
<td  valign="top" >
<p>N</p>
</td>
<td  valign="top" >
<p>JSON or XML</p>
</td>
<td  valign="top" >
<p>JSON</p>
</td>
</tr>
<tr>
<td  valign="top" >
<p>limit</p>
</td>
<td  valign="top" >
<p>Number of rows to get per retrieved table</p>
</td>
<td  valign="top" >
<p>N</p>
</td>
<td  valign="top" >
<p>2</p>
</td>
<td  valign="top" >
<p>JSON</p>
</td>
</tr>    
</tbody>
</table>
<p>&nbsp;</p>

**Examples:**

- `https://localhost:3213/api/lu/CUSTOMER/1`

  Brings all data for CUSTOMER LU Instance ID 1

  Response Body: response body supports streaming solution


- `https://localhost:3213/api/lu/CUSOMTER/1/ALLERGIES`

  Brings data for CUSTOMER LU Instance ID 1, table ALLERGIES  


- `https://localhost:3213/api/lu/CUSTOMER/1/PAYMENT?fields=PAYMENT_ID,PAYMENT_DATE&where=PAYMENT_STATUS!=’CLOSED’`

  Brings data for CUSTOMER LU Instance ID 1, table PAYMENT, fields PAYMENT_ID, PAYMENT_DATE where payments are not closed.


- `https://localhost:3213/api/lu/CUSTOMER/1?query=SELECT FIRST_NAME FROM ADDRESS_DATA A, ADDRESS_NAME_LINK B WHERE A.ADDRESS_ID =B.ADDRESS_ID AND  B.ADDRESS_TYPE=’P’`

  Brings data for CUSTOMER LU Instance ID 1, table ADDRESS_DATA field FIRST_NAME where name type is private.

- `https://localhost:3213/api/lu/CUSTOMER/1?query=SELECT FIRST_NAME FROM ADDRESS_DATA A,ADDRESS_NAME_LINK B WHERE A.ADDRESS_ID =B.ADDRESS_ID AND B.ADDRESS_TYPE=’P’&set=sync,force&set=ENVIRONMENT,ENV1&set=GLOBAL_LION,10`

  Brings data for CUSTOMER LU Instance ID 1, table ADDRESS_DATA field FIRST_NAME where name type is private. Make sure sync mode is force and run it on ENV1 and set GLOBAL_LION to 10.



## Create LU Table Data

<span style="border-radius: 2em; background-color: #0969da; padding: 0 7px; color:white"> POST</span>   `/api/lu/<LU Name>/<iid>/[[<TABLE-NAME>][?format=json/xml]`

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
<td>LU Name</td>
<td>Logical unit name</td>
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
<td>TABLE NAME</td>
<td>Table names to post data into</td>
<td>N</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>format</td>
<td>Response format</td>
<td>N</td>
<td>JSON/XML</td>
<td>JSON</td>
</tr>
</tbody>
</table>



 **Example:**

1. Inserts data into CUSTOMER LU instance id 1, LION table, where table name is specified at the post body
    `https://10.21.1.69:3213/api/v1.0/lu/CUSTOMER/1`
    Request Body:

    ```json
    {"rows":{"LION":[{"ID":11, "NAME":"lion11"},{"ID":12,"NAME":"lion12"},{"ID":13,"NAME":"lion13"}]}}
    ```

2. Inserts data into CUSTOMER LU instance id 1, LION table, where table name is specified at URL

   `https://10.21.1.69:3213/api/v1.0/lu/CUSTOMER/1/LION`
   Request Body:

   ```json
   {"rows":[{"ID":11, "NAME":"lion11"},{"ID":12,"NAME":"lion12"},{"ID":13,"NAME":"lion13"}]}
   ```



##  Update LU Table Data

<span style="border-radius: 2em; background-color: #0969da; padding: 0 7px; color:white">PUT</span>   `/api/lu/<LU Name>/<iid>/[TABLE NAME][?format=json|xml]`

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
<td>LU Name</td>
<td>Logical unit name or COMMON for common tables</td>
<td>Y</td>
<td>CUSTOMER</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>iid</td>
<td>Instance Id</td>
<td>Y</td>
<td>1</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>TABLE  NAME</td>
<td>Table name for data creation</td>
<td>Y</td>
<td>PAYMENT</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>format</td>
<td>Response format</td>
<td>N</td>
<td>JSON/XML/CSV</td>
<td>JSON</td>
</tr>
</tbody>
</table>

The request body shall contain the row data to be updated along with an optional 'where' element, as the where condition statement.

**Example:**

- `https://localhost:3213/api/v1.0/lu/CUSTOMER/1/INVOICE`

  Updates data on CUSTOMER LU instance id 1, CUSTOMER table

  Request Body

```json
 {
	"row" : {"LAST_NAME":"TEST1"},
	"where":"CUSTOMER=1 or ADDRESS='Betsey'"
}                    
```



## Delete LUI

<span style="border-radius: 2em; background-color: #0969da; padding: 0 7px; color:white">DELETE</span> `/api/lu/<LU Name>/iid[?format=json/xml]`

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
<td>LU Name</td>
<td>Logical unit name or COMMON for common tables</td>
<td>Y</td>
<td>PATIENT COMMON</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>iid</td>
<td>Instance Id</td>
<td>Y</td>
<td>1</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>Format</td>
<td>Response format</td>
<td>N</td>
<td>JSON/XML</td>
<td>JSON</td>
</tr>
</tbody>
</table>


**Example:**

- `https://localhost:3213/api/lu/PATIENT`

  Deletes LUI 1 from PATIENT LU



## Delete Data From LU Table

<span style="border-radius: 2em; background-color: #0969da; padding: 0 7px; color:white">DELETE</span>   `/api/lu/<LU Name>/iid/<TABLE_NAME>?where=<WHERE STATEMENT>][&format=json|xml]`

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
<td>LU name</td>
<td>Logical unit name</td>
<td>Y</td>
<td>PATIENT</td>
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
<td>TABLE NAME</td>
<td>Table name for data deletion</td>
<td>Y</td>
<td>PAYMENT</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>WHERE STATEMENT</td>
<td>Where clause statement</td>
<td>Y</td>
<td>INVOICE_ID=1</td>
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

- `https://localhost:3213/api/lu/PATIENT/1/INVOICE?WHERE=CUSTOMER=1 or NAME=’LION’`

  Deletes data from PATIENT LU instance id 1, INVOICE table by using a 'where' clause


[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/01_web_services_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/03_built_in_common_ws.md)

