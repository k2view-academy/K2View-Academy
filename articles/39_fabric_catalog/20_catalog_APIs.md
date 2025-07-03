# Catalog APIs

Fabric provides out-of-the-box Web Service APIs for querying a project's Catalog.

Any web service call passes authentication and authorization processes prior to being executed. For more information, read [here](/articles/26_fabric_security/05_fabric_webservices_security.md).

All APIs are accessed over HTTPS, from the Fabric URL endpoint `https://<Domain Name>:<PORT>`



## Start Crawler Job

<span style="border-radius: 1em; background-color: #0969da; padding: 0 10px; color:white">GET</span>   `/api/catalog/start-crawler-job`

The API invokes the DISCOVERY_CRAWLER job for a given interface.

**Example of an API call:**

~~~
https://localhost:3213/api/catalog/start-crawler-job?dataPlatform=CRM_DB
~~~



## Get Catalog

<span style="border-radius: 1em; background-color: #0969da; padding: 0 10px; color:white">GET</span>   `/api/catalog`

The API retrieves a list of Catalog versions along with their creation timestamps.

**Example of an API call:**

```
https://localhost:3213/api/catalog
```



## Get Details of Catalog Version 

<span style="border-radius: 1em; background-color: #0969da; padding: 0 10px; color:white">GET</span>   `/api/catalog/{version}`

The API retrieves a list of data platforms that belong to a given Catalog version. Each data platform is retrieved along with its *contains* relations to the respective schema nodes. 

The API has two modes — ***view*** and ***compare*** — that are interchangeably invoked based on the **version** input parameter, as explained below:

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
<p>Populate either the version number or the word <strong>latest</strong> to retrieve data for that version.</p>
<p>Populate <strong>{base version}...{compare version}</strong> to retrieve a comparison between the versions. In the response, each node indicates whether it has been added, deleted, updated or unchanged.</p>
<p>To view recent changes, set <strong>{base version}</strong> to an older version number, and <strong>{compare version}</strong> to a more recent version number or to the word <strong>latest</strong>.</p>
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

The API retrieves a list of schemas that belong to the specified Catalog version and data platform. Each schema is retrieved with the *contains* relations to its respective dataset nodes. 

The API has two modes — ***view*** and ***compare*** — that are interchangeably invoked based on the **version** input parameter, as explained below:

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
<p>Populate either the version number or the word <strong>latest</strong> to retrieve data for that version.</p>
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
<tr>
<td>shortMode</td>
<td>N</td>
<td>
<p>When set to <strong>true</strong>, the EP returns an array of shema names only, without the list of properties and links. Available from V8.3.</p>
</td>
</tr>
</tbody>
</table>

**Examples of an API call in *view* mode:**

```
https://localhost:3213/api/catalog/latest/CRM_DB
```

```
https://localhost:3213/api/catalog/latest/CRM_DB?shortMode=true
```

```
https://localhost:3213/api/catalog/1/CRM_DB
```

**Examples of an API call in *compare* mode:**

```
https://localhost:3213/api/catalog/1...5/CRM_DB
```

```
https://localhost:3213/api/catalog/1...latest/CRM_DB
```



## Get Catalog's Schema Details

<span style="border-radius: 1em; background-color: #0969da; padding: 0 10px; color:white">GET</span>   `/api/catalog/{version}/{dataPlatform}/{schema}`

The API retrieves all elements that belong to the specified Catalog version, data platform and schema. The elements include datasets, fields and their properties, and the *refersTo* relations between dataset nodes. 

The API has two modes — ***view*** and ***compare*** — that are interchangeably invoked based on the **version** input parameter, as explained below:

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
<p>Populate either the version number or the word <strong>latest</strong> to retrieve data for that version.</p>
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
<tr>
<td>schema</td>
<td>Y</td>
<td>
<p>The schema name.</p>
</td>
</tr>
<tr>
<td>propertiesToInclude</td>
<td>N</td>
<td>
<p>Coma-separated list of properties to be included in the output. When empty, all properties are included. </p><p>E.g.: propertiesToInclude=pii,pk</p>
</td>
</tr>
</tbody>
</table>


**Examples of an API call in *view* mode:**

```
https://localhost:3213/api/catalog/latest/CRM_DB/main
```

```
https://localhost:3213/api/catalog/1/CRM_DB/main
```

**Examples of an API call in *compare* mode:**

```
https://localhost:3213/api/catalog/1...5/CRM_DB/main
```

```
https://localhost:3213/api/catalog/1...latest/CRM_DB/main
```

<span style="border-radius: 1em; background-color: #0969da; padding: 0 10px; color:white">GET</span>   `/api/catalog/{dataPlatform}/{schema}/datasets`

The API retrieves a list of datasets that belong to the **latest version** of the specified data platform and schema. This API and it is available starting from V8.3.

<span style="border-radius: 1em; background-color: #0969da; padding: 0 10px; color:white">GET</span>   `/api/catalog/{dataPlatform}/{schema}/{dataset}/fields`

The API retrieves a list of fields that belong to the **latest version** of the specified data platform, schema and dataset. This API and it is available starting from V8.3.

## Building Catalog Artifacts

<span style="border-radius: 1em; background-color: #0969da; padding: 0 10px; color:white">GET</span>   `/api/catalog/{version}/build-catalog-artifacts`

The API builds the Catalog artifacts based on a given version. The artifacts include details of all Catalog fields and their properties, such as Classification and PII. The artifact is created in a CSV format, saved into the ```Implementation/SharedObjects/Interfaces/Discovery/MTable``` folder of the Project tree and is uploaded to the Fabric memory as an [MTable](/articles/09_translations/06_mtables_overview.md). 

Starting from V8.3, the artifacts of relations might also be extracted by the API. It can be done when setting ```refersTo=true``` in the API input. The relations artifact is created in a CSV format, saved into the ```Implementation/SharedObjects/Interfaces/Discovery/MTable``` folder of the Project tree, and uploaded to the Fabric memory as an [MTable](/articles/09_translations/06_mtables_overview.md). 

Refer to the [Catalog Artifacts article](/articles/39_fabric_catalog/catalog_app/09_build_artifacts.md) for more details about the structure and naming convention of the relations extract files. 

**Example of an API call:**

```
https://localhost:3213/api/catalog/4/build-catalog-artifacts
```

```
https://localhost:3213/api/catalog/latest/build-catalog-artifacts?refersTo=true
```



## Catalog Search 

<span style="border-radius: 12em; background-color: #46B583; padding: 0 10px; color:white">POST</span>   `/api/catalog/{version}/search-graph`

The API retrieves all elements that belong to the specified Catalog version, based on the search criteria defined in the request body. 

The syntax of the request body definition is as follows:

* **input** is an array of keywords 
  * Keyword is a string included in the node (or relation) name

* **type** specifies which object types will be searched
  * The valid values are: dataPlatform, schema, dataset, field, relation
  * Send an empty array in case of no limitation on object type
* **advanced** includes a list of additional search parameters, such as:
  * **pii** is a PII property with either **true** or false **value**
  * **classification** is a Classification property with one of its valid values
  * **score** represents the maximum score among the searched object types (nodes or relations)

At least one of the search parameters must be provided in the request body. 

**Example of an API call:**

```
https://localhost:3213/api/catalog/4/search-graph
```

**Examples of the request body:**

Example 1: When searching for Data Platform and Schema nodes, whose name includes a *customer* keyword, the request body is:

~~~json
{
    "input": [
        "customer"
    ],
    "type": [
        "dataPlatform",
        "schema"
    ],
    "advanced": {
    }
}
~~~

Example 2: When searching for any node types with *PII = true* and *Classification = EMAIL* properties, the request body is:

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



