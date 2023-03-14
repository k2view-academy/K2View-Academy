<web>

# Catalog Vocabulary

The Data Discovery solution introduces a vocabulary that describe the catalog entities and the relationships between them. The relationships indicate the connections between the data entities and determine their hierarchy.

The below Data Catalog vocabulary serves as a model for describing a catalog and helps to do a standardization across different interface types. 

The data entities are represented by **nodes** and the links between the **nodes** are represented by **relations**. Nodes and relations have predefined set of properties which allow to normalize the discovery results. 

In addition, due to the differences between the data source, some nodes' properties are generic, while other are relevant for specific interface types only.

The following tables describe **node and relation types**, and how they are defined in each interface type:

#### Node Types

<table style="border-collapse: collapse; width: 100%;">
<tbody>
<tr>
<td style="width: 20%;"><strong>Node Type</strong></td>
<td style="width: 40%;"><strong>RDBMS</strong></td>
<td style="width: 40%;"><strong>OpenAPI</strong></td>
</tr>
<tr>
<td>DATA_PLATFORM</td>
<td>DB Interface</td>
<td>JSON or YAML of HTTP Interface.</td>
</tr>
<tr>
<td>SCHEMA</td>
<td>Schema of DB Interface</td>
<td>Tags of JSON or YAML</td>
</tr>
<tr>
<td>DATASET</td>
<td>DB Table or View</td>
<td>End point (path)</td>
</tr>
<tr>
<td>CLASS</td>
<td>DB Table or View (1:1 to DATASET)</td>
<td>OpenAPI Object</td>
</tr>
<tr>
<td>FIELD</td>
<td>Columns of DB Table or View</td>
<td>Property of OpenAPI Object</td>
</tr>
</tbody>
</table>

The FIELD data type can be:

* PRIMITIVE - string, integer, blob, date, number, Boolean or any.
* COLLECTION - an array of primitive values.

#### Relation Types

The relation direction is many-to-one.

<table style="border-collapse: collapse; width: 100%;">
<tbody>
<tr>
<td style="width: 20%;"><strong>Relation Type</strong></td>
<td style="width: 80%;"><strong>Description</strong></td>
</tr>
<tr>
<td style="width: 20%;">CONTAINED IN</td>
<td style="width: 80%;">
<p>SCHEMA contained in DATA_PLATFORM</p>
<p>DATASET contained in SCHEMA</p>
<p>CLASS contained in DATASET</p>
<p>FIELD contained in CLASS</p>
</td>
</tr>
<tr>
<td style="width: 20%;">REFERS TO</td>
<td style="width: 80%;">
<p>(FIELD in) CLASS refers to (FIELD in) CLASS</p>
<p>For example: <em>INVOICE refers to CUSTOMER</em></p>
<p>The PK / FK columns names are included in the relation's properties</p>
</td>
</tr>
</tbody>
</table>





[![Previous](/articles/images/Previous.png)](01_discovery_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_discovery_process.md) 

</web>
