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

Starting from Fabric V8.4, version source (Crawler, Manual or Revert) and a list of impacted data platforms are also returned in the API's response per each version.

**Example of an API call:**

```
https://localhost:3213/api/catalog
```

## Get Details of Catalog Version 

<span style="border-radius: 1em; background-color: #0969da; padding: 0 10px; color:white">GET</span>   `/api/catalog/{version}`

The API retrieves a list of data platforms that belong to a given Catalog version. Each data platform is retrieved along with its *contains* relations to the respective schema nodes. 

The API has two modes — ***view*** and ***compare*** — that are interchangeably invoked based on the **version** input parameter, as explained below:

<table>
<thead>
<tr>
<th style="text-align: left;" width="100pxl"><strong>Component</strong></th>
<th style="text-align: left;" width="100pxl"><strong>Mandatory</strong></th>
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



## Get Details of Catalog Data Platform 

<span style="border-radius: 1em; background-color: #0969da; padding: 0 10px; color:white">GET</span>   `/api/catalog/{version}/{dataPlatform}`

The API retrieves a list of schemas that belong to the specified Catalog version and data platform. Each schema is retrieved along with its  *contains* relations to the respective dataset nodes. 

The API has two modes — ***view*** and ***compare*** — that are interchangeably invoked based on the **version** input parameter, as explained below:

<table>
<thead>
<tr>
<th style="text-align: left;" width="100pxl"><strong>Component</strong></th>
<th style="text-align: left;" width="100pxl"><strong>Mandatory</strong></th>
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
<p>When set to <strong>true</strong>, the API returns an array of shema names only, without the list of properties and links. Available from Fabric V8.3.</p>
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



## Get Details of Catalog Schema 

<span style="border-radius: 1em; background-color: #0969da; padding: 0 10px; color:white">GET</span>   `/api/catalog/{version}/{dataPlatform}/{schema}`

The API retrieves all metadata elements that belong to the specified Catalog version, data platform and schema, including datasets, fields with their properties, and the *refersTo* relations between dataset nodes. 

The API has two modes — ***view*** and ***compare*** — that are interchangeably invoked based on the **version** input parameter, as explained below:

<table>
<thead>
<tr>
<th style="text-align: left;" width="100pxl"><strong>Component</strong></th>
<th style="text-align: left;" width="100pxl"><strong>Mandatory</strong></th>
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

The API retrieves a list of datasets that belong to the **latest version** of the specified data platform and schema. Available starting from Fabric V8.3.

<span style="border-radius: 1em; background-color: #0969da; padding: 0 10px; color:white">GET</span>   `/api/catalog/{dataPlatform}/{schema}/{dataset}/fields`

The API retrieves a list of fields that belong to the **latest version** of the specified data platform, schema and dataset. Available starting from Fabric V8.3.

Starting from Fabric V8.3.2, an optional input parameter ```primitiveTypeOnly``` has been added:

* When set to ```false``` (default) - the API returns all fields of the specified dataset.
* When set to ```true``` - the API filters out fields with complex type and returns primitive-type fields only.


## Building Catalog Artifacts

<span style="border-radius: 1em; background-color: #0969da; padding: 0 10px; color:white">GET</span>   `/api/catalog/{version}/build-catalog-artifacts`

The API builds the Catalog artifacts for the specified Catalog version. The artifacts include details of the fields that belong to all Catalog's data platforms and their properties (such as *definedBy*, *classification* and *pii*). The artifact is created in a CSV format, saved in the ```Implementation/SharedObjects/Interfaces/Discovery/MTable``` folder of the Project tree, and uploaded to the Fabric memory as an [MTable](/articles/09_translations/06_mtables_overview.md). 

Starting from Fabric V8.3, the relations artifact can also be extracted by the API. The relations artifacts include the details of the Catalog's *refersTo* relations. It can be done when setting ```refersTo=true``` in the API input. The relations artifact is created in a CSV format, saved in the ```Implementation/SharedObjects/Interfaces/Discovery/MTable``` folder of the Project tree, and uploaded to the Fabric memory as an [MTable](/articles/09_translations/06_mtables_overview.md). 

Refer to the [Catalog Artifacts](/articles/39_fabric_catalog/catalog_app/09_build_artifacts.md) article for more details about the structure and naming format of the created artifacts. 

Starting from Fabric V8.4, building an artifact can be done for either a selected data platform or for the full catalog. 

<table>
<thead>
<tr>
<th style="text-align: left;" width="100pxl"><strong>Component</strong></th>
<th style="text-align: left;" width="100pxl"><strong>Mandatory</strong></th>
<th style="text-align: left;" width="700pxl"><strong>Description</strong></th>
</tr>
</thead>
<tbody>
<tr>
<td>version</td>
<td>Y</td>
<td>
<p>Populate either the version number or the word <strong>latest</strong> to build the version's artifacts.</p>
</td>
</tr>
<tr>
<td>refersTo</td>
<td>N</td>
<td>
<p>When set to <strong>true</strong>, the artifacts of the <em>refersTo</em> relations are created in addition to the artifacts of fields.</p>
</td>
</tr>
<tr>
<td>dataPlatform</td>
<td>N</td>
<td>
<p>The data platform name. When it is populated, the artifact is created for the specified data platform only.</p>
</td>
</tr>
</tbody>
</table>

**Example of an API call:**

```
https://localhost:3213/api/catalog/4/build-catalog-artifacts
```

```
https://localhost:3213/api/catalog/latest/build-catalog-artifacts?refersTo=true
```

~~~
https://localhost:3213/api/catalog/latest/build-catalog-artifacts?refersTo=true&dataPlatform=CRM_DB
~~~



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
  * **pii** is a PII property with a value of either **true** or **false**
  * **classification** is a Classification property with one of its valid values
  * **score** represents the maximum score among the searched object types (nodes or relations)

At least one of the search parameters must be provided in the request body. 

**Example of an API call:**

```
https://localhost:3213/api/catalog/4/search-graph
```

**Examples of the request body:**

Example 1: When searching for Data Platform and Schema nodes, whose names include the keyword *customer*, the request body is as follows:

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

Example 2: When searching for node types with *PII = true* and *Classification = EMAIL* properties, the request body is as follows:

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



## Revert Catalog Version

<span style="border-radius: 12em; background-color: #46B583; padding: 0 10px; color:white">POST</span>   `/api/catalog/{toVersion}/revert-catalog-version`

The API reverts from the last Catalog version to a specified version. The revert can be performed either to the entire Catalog or to a selected data platform. The selected data platform is sent in the request body. Available starting from Fabric 8.4.

<table>
<thead>
<tr>
<th style="text-align: left;" width="100pxl"><strong>Component</strong></th>
<th style="text-align: left;" width="100pxl"><strong>Mandatory</strong></th>
<th style="text-align: left;" width="700pxl"><strong>Description</strong></th>
</tr>
</thead>
<tbody>
<tr>
<td>toVersion</td>
<td>Y</td>
<td>
<p>Populate the version number to which the Catalog should be reverted. Must be lower than the latest version.</p>
</td>
</tr>
<tr>
<td>dataPlatform</td>
<td>N</td>
<td>
<p>The data platform name. When populated, only the specified data platform's metadata is reverted to the specified version.</p>
</td>
</tr>
</tbody>
</table>

**Example of an API call:**

```
https://localhost:3213/api/catalog/4/revert-catalog-version
```

**Example of the request body:**

~~~json
{
  "dataPlatform": "CRM_DB"
}
~~~



## Clean Graph

<span style="border-radius: 12em; background-color: #F93E3E; padding: 0 10px; color:white">DELETE</span>   `/api/catalog/clean-graph`

The API permanently deletes the Catalog's metadata from the Neo4j GraphDB, including all versions and data platforms with their associated information.

Starting from Fabric 8.4, cleaning of the graph can be applied either on the entire Catalog or on a selected data platform.

<table>
<thead>
<tr>
<th style="text-align: left;" width="100pxl"><strong>Component</strong></th>
<th style="text-align: left;" width="100pxl"><strong>Mandatory</strong></th>
<th style="text-align: left;" width="700pxl"><strong>Description</strong></th>
</tr>
</thead>
<tbody>
<tr>
<td>dataPlatform</td>
<td>N</td>
<td>
<p>The data platform name. When populated, only the specified data platform's metadata is deleted from the Neo4j GraphDB.</p>
</td>
</tr>
<tr>
<td>keepManualOverrides</td>
<td>N</td>
<td><p>When set to <strong>true</strong>, the manual overrides will not be deleted. During the next discovery run on the same data platform, the manual overrides are automatically reapplied.</p><p>When set to <strong>false</strong>, the manual overrides will be permanently deleted.</p>

</td>
</tr>
</tbody>
</table>

**Example of an API call:**

```
https://localhost:3213/api/catalog/clean-graph
```

~~~
https://localhost:3213/api/catalog/clean-graph?dataPlatform=BILLING_DB&keepManualOverrides=true
~~~

