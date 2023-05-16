# Catalog APIs

Fabric provides out-of-the-box Web Service APIs for querying a project's catalog.

Any web service call passes authentication and authorization validations before being executed. For more information - see [here](/articles/26_fabric_security/05_fabric_webservices_security.md).

All API are accessed over HTTPS, from the Fabric URL endpoint `https://<Domain Name>:<PORT>`



## All Catalog Versions

<span style="border-radius: 2em; background-color: #0969da; padding: 0 8px; color:white">GET</span>   `/api/catalog/all-versions`

The API retrieves a list of catalog's versions and their creation timestamp.



## Get Catalog's Version

<span style="border-radius: 2em; background-color: #0969da; padding: 0 8px; color:white">GET</span>   `/api/catalog/{version}`

The API retrieves a list of data platforms that belong to the given catalog version. Each data platform is retrieved with the CONTAINS relations to its respective schema nodes. 

The API has two modes - *view* and *compare* - which are alternatively invoked based on the **version** input parameter, as explained below:

<table style="width: 800px;">
<thead>
<tr>
<th style="text-align: left;" width="100pxl"><strong>Component</strong></th>
<th style="text-align: left;" width="50pxl"><strong>Mandatory</strong></th>
<th style="text-align: left;" width="650pxl"><strong>Description</strong></th>
</tr>
</thead>
<tbody>
<tr>
<td style="width: 141.047px;" rowspan="2">version</td>
<td style="width: 83.7969px;" rowspan="2">Y</td>
<td style="width: 400.156px;">
    <p>Populate either the version number or the word <strong>latest</strong> to get the version's data.</p>
</td>
</tr>
<tr>
<td style="width: 400.156px;">
    <p>Populate <strong>{base version}...{compare version}</strong> to get the versions comparison. In the response, each node indicates whether it has been added, deleted, updated or unchanged.</p>
    <p>In order to see the recent changes, set the <strong>{base version}</strong> to an older version number and the <strong>{compare version}</strong> to a more recent version number or the word <strong>latest</strong>. 
    </p></td>
</tr>
</tbody>
</table>
**Examples of an API call in *view* mode:**

https://localhost:3213/api/catalog/latest

https://localhost:3213/api/catalog/1

**Examples of an API call in *compare* mode:**

https://localhost:3213/api/catalog/2...5

https://localhost:3213/api/catalog/1...latest



## Get Catalog's Data Platforms

<span style="border-radius: 2em; background-color: #0969da; padding: 0 8px; color:white">GET</span>   `/api/catalog/{version}/{dataPlatform}`

The API retrieves a list of schemas that belong to the given catalog version and data platform. Each schema is retrieved with the CONTAINS relations to its respective dataset nodes. 

The API has two modes - *view* and *compare* - which are alternatively invoked based on the **version** input parameter, as explained below:

<table style="width: 800px;">
<thead>
<tr>
<th style="text-align: left;" width="100pxl"><strong>Component</strong></th>
<th style="text-align: left;" width="50pxl"><strong>Mandatory</strong></th>
<th style="text-align: left;" width="650pxl"><strong>Description</strong></th>
</tr>
</thead>
<tbody>
<tr>
<td style="width: 141.047px;" rowspan="2">version</td>
<td style="width: 83.7969px;" rowspan="2">Y</td>
<td style="width: 400.156px;">
<p>Populate either the version number or the word <strong>latest</strong> to get the version's data.</p>
</td>
</tr>
<tr>
<td style="width: 400.156px;">
<p>Populate <strong>{base version}...{compare version}</strong> to get the versions comparison. In the response, each node indicates whether it has been added, deleted, updated or unchanged.</p>
<p>In order to see the recent changes, set the <strong>{base version}</strong> to an older version number and the <strong>{compare version}</strong> to a more recent version number or the word <strong>latest</strong>.</p>
</td>
</tr>
<tr>
<td style="width: 141.047px;">dataPlatform</td>
<td style="width: 83.7969px;">Y</td>
<td style="width: 400.156px;">
<p>The data platform name.</p>
</td>
</tr>
</tbody>
</table>
**Examples of an API call in *view* mode:**

https://localhost:3213/api/catalog/latest/CRM_DB

https://localhost:3213/api/catalog/1/CRM_DB

**Examples of an API call in *compare* mode:**

https://localhost:3213/api/catalog/1...5/CRM_DB

https://localhost:3213/api/catalog/1...latest/CRM_DB



## Get Catalog's Schemas

<span style="border-radius: 2em; background-color: #0969da; padding: 0 8px; color:white">GET</span>   `/api/catalog/{version}/{dataPlatform}/schema`

The API retrieves all elements that belong to the given catalog version, data platform and schema. The elements include the datasets, the REFERS_TO relations between the dataset nodes, fields and properties. 

The API has two modes - *view* and *compare* - which are alternatively invoked based on the **version** input parameter, as explained below:

<table style="width: 800px;">
<thead>
<tr>
<th style="text-align: left;" width="100pxl"><strong>Component</strong></th>
<th style="text-align: left;" width="50pxl"><strong>Mandatory</strong></th>
<th style="text-align: left;" width="650pxl"><strong>Description</strong></th>
</tr>
</thead>
<tbody>
<tr>
<td style="width: 141.047px;" rowspan="2">version</td>
<td style="width: 83.7969px;" rowspan="2">Y</td>
<td style="width: 400.156px;">
<p>Populate either the version number or the word <strong>latest</strong> to get the version's data.</p>
</td>
</tr>
<tr>
<td style="width: 400.156px;">
<p>Populate <strong>{base version}...{compare version}</strong> to get the versions comparison. In the response, each node indicates whether it has been added, deleted, updated or unchanged.</p>
<p>In order to see the recent changes, set the <strong>{base version}</strong> to an older version number and the <strong>{compare version}</strong> to a more recent version number or the word <strong>latest</strong>.</p>
</td>
</tr>
<tr>
<td style="width: 141.047px;">dataPlatform</td>
<td style="width: 83.7969px;">Y</td>
<td style="width: 400.156px;">
<p>The data platform name.</p>
</td>
</tr>
<tr>
<td style="width: 141.047px;">schema</td>
<td style="width: 83.7969px;">Y</td>
<td style="width: 400.156px;">
<p>The schema name.</p>
</td>
</tr>
</tbody>
</table>
**Examples of an API call in *view* mode:**

https://localhost:3213/api/catalog/latest/CRM_DB/main

https://localhost:3213/api/catalog/1/CRM_DB/main

**Examples of an API call in *compare* mode:**

https://localhost:3213/api/catalog/1...5/CRM_DB/main

https://localhost:3213/api/catalog/1...latest/CRM_DB/main