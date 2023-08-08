<web>

# Catalog APIs

Fabric provides out-of-the-box Web Service APIs for querying a project's Catalog.

Any web service call passes authentication and authorization validations before being executed. For more information - see [here](/articles/26_fabric_security/05_fabric_webservices_security.md).

All APIs are accessed over HTTPS, from the Fabric URL endpoint `https://<Domain Name>:<PORT>`



## Get Catalog

<span style="border-radius: 1em; background-color: #0969da; padding: 0 10px; color:white">GET</span>   `/api/catalog`

The API retrieves a list of the Catalog's versions and their creations' timestamp.

**Example of an API call:**

```
https://localhost:3213/api/catalog
```



## Get Catalog Version Details

<span style="border-radius: 1em; background-color: #0969da; padding: 0 10px; color:white">GET</span>   `/api/catalog/{version}`

The API retrieves a list of data platforms that belong to a given Catalog version. Each data platform is retrieved with the CONTAINS relations to its respective schema nodes. 

The API has 2 modes - *view* and *compare* - that are alternatively invoked based on the **version** input parameter, as explained below:

<table style="width: 800px;">
<thead>
<tr>
<th style="text-align: left;" width="50pxl"><strong>Component</strong></th>
<th style="text-align: left;" width="50pxl"><strong>Mandatory</strong></th>
<th style="text-align: left;" width="700pxl"><strong>Description</strong></th>
</tr>
</thead>
<tbody>
<tr>
<td>version</td>
<td>Y</td>
<td>
<p>Populate either the version number or the word <strong>latest</strong> to get the version's data.</p>
<p>Populate <strong>{base version}...{compare version}</strong> to get the versions comparison. In the response, each node indicates whether it has been added, deleted, updated or unchanged.</p>
<p>In order to see the recent changes, set the <strong>{base version}</strong> to an older version number and the <strong>{compare version}</strong> to a more recent version number or to the word <strong>latest</strong>.</p>
</td>
</tr>
</tbody>
</table>


**Examples of an API call in *view* mode:**

```
https://localhost:3213/api/catalog/latest
```

```
https://localhost:3213/api/catalog/latest
```

**Examples of an API call in *compare* mode:**

```	
https://localhost:3213/api/catalog/2...5
```

```
https://localhost:3213/api/catalog/1...latest
```



## Get Catalog's Data Platform Details

<span style="border-radius: 1em; background-color: #0969da; padding: 0 10px; color:white">GET</span>   `/api/catalog/{version}/{dataPlatform}`

The API retrieves a list of schemas that belong to the specified catalog version and data platform. Each schema is retrieved with the CONTAINS relations to its respective dataset nodes. 

The API has 2 modes - *view* and *compare* - that are alternatively invoked based on the **version** input parameter, as explained below:

<table style="width: 800px;">
<thead>
<tr>
<th style="text-align: left;" width="50pxl"><strong>Component</strong></th>
<th style="text-align: left;" width="50pxl"><strong>Mandatory</strong></th>
<th style="text-align: left;" width="700pxl"><strong>Description</strong></th>
</tr>
</thead>
<tbody>
<tr>
<td>version</td>
<td>Y</td>
<td>
<p>Populate either the version number or the word <strong>latest</strong> to get the version's data.</p>
<p>Populate <strong>{base version}...{compare version}</strong> to get the versions comparison. In the response, each node indicates whether it has been added, deleted, updated or unchanged.</p>
<p>In order to see the recent changes, set the <strong>{base version}</strong> to an older version number and the <strong>{compare version}</strong> to a more recent version number or to the word <strong>latest</strong>.</p>
</td>
</tr>
<tr>
<td>dataPlatform</td>
<td>Y</td>
<td>
<p>The data platform name.</p>
</td>
</tr>
</tbody>
</table>
**Examples of an API call in the *view* mode:**

```
https://localhost:3213/api/catalog/latest/CRM_DB
```

```
https://localhost:3213/api/catalog/1/CRM_DB
```

**Examples of an API call in the *compare* mode:**

```
https://localhost:3213/api/catalog/1...5/CRM_DB
```

```
https://localhost:3213/api/catalog/1...latest/CRM_DB
```



## Get Catalog's Schema Details

<span style="border-radius: 1em; background-color: #0969da; padding: 0 10px; color:white">GET</span>   `/api/catalog/{version}/{dataPlatform}/schema`

The API retrieves all elements that belong to the specified catalog version, data platform and schema. The elements include the datasets, the REFERS_TO relations between the dataset nodes, fields and properties. 

The API has two modes - *view* and *compare* - which are alternatively invoked based on the **version** input parameter, as explained below:

<table style="width: 800px;">
<thead>
<tr>
<th style="text-align: left;" width="50pxl"><strong>Component</strong></th>
<th style="text-align: left;" width="50pxl"><strong>Mandatory</strong></th>
<th style="text-align: left;" width="700pxl"><strong>Description</strong></th>
</tr>
</thead>
<tbody>
<tr>
<td>version</td>
<td>Y</td>
<td>
<p>Populate either the version number or the word <strong>latest</strong> to get the version's data.</p>
<p>Populate <strong>{base version}...{compare version}</strong> to get the versions comparison. In the response, each node indicates whether it has been added, deleted, updated or unchanged.</p>
<p>In order to see the recent changes, set the <strong>{base version}</strong> to an older version number and the <strong>{compare version}</strong> to a more recent version number or the word <strong>latest</strong>.</p>
</td>
</tr>
<tr>
<td>dataPlatform</td>
<td>Y</td>
<td>
<p>The data platform name.</p>
</td>
</tr>
<tr>
<td>schema</td>
<td>Y</td>
<td>
<p>The schema name.</p>
</td>
</tr>
</tbody>
</table>
**Examples of an API call in the *view* mode:**

```
https://localhost:3213/api/catalog/latest/CRM_DB/main
```

```
https://localhost:3213/api/catalog/1/CRM_DB/main
```

**Examples of an API call in the *compare* mode:**

```
https://localhost:3213/api/catalog/1...5/CRM_DB/main
```

```
https://localhost:3213/api/catalog/1...latest/CRM_DB/main
```



## Search Catalog

<span style="border-radius: 12em; background-color: #46B583; padding: 0 10px; color:white">POST</span>   `/api/catalog/{version}/search-graph`

The API retrieves all elements that belong to the specified catalog version, based on the search criteria defined in the request body. 

The syntax of the request body definition is as follows:

* **input** is an array of keywords 
  * Keyword is a string included in the node (or relation) name

* **type** defines which object types will be searched
  * The valid values are: data_platform, schema, dataset, field, relation
  * Send an empty array in case of no limitation on object type
* **advanced** includes a list of additional search parameters, such as:
  * **pii** is a PII property with either **true** or false **value**
  * **classification** is a Classification property with one of its valid values
  * **score** is a maximum score of the searched object types (nodes or relations)

At least one of the search parameters must be provided in the request body. 

**Example of an API call:**

```
https://localhost:3213/api/catalog/4/search-graph
```

**Examples of the request body:**

Example 1: when searching for *dataset* and *field* nodes whose name include a *customer* keyword, the request body is:

~~~json
{
    "input": [
        "customer"
    ],
    "type": [
        "dataset",
        "field"
    ],
    "advanced": {
    }
}
~~~

Example 2: when searching for *any* nodes with *PII = true* and *Classification = EMAIL* properties, the request body is:

~~~json
{
    "input": [],
    "type": [],
    "advanced": {
        "pii": "true",
        "classification": "EMAIL"
    }
}
~~~



[![Previous](/articles/images/Previous.png)](09_build_artifacts.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](11_advanced_settings.md) 



</web>
