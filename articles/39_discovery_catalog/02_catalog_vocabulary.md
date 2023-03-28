<web>

# Catalog Vocabulary

The Data Discovery solution introduces a vocabulary that describes the catalog entities and the relationships between them. The relationships indicate the connections between the data entities and determine their hierarchy.

The below Data Catalog vocabulary serves as a model for describing a catalog and assists with processes standardization across different interface types. 

The data entities are represented by **nodes** and the links between the **nodes** are represented by **relations**. Nodes and relations have predefined properties that allow to enrich the discovery results. 

Additionally, due to differences between the data sources, some nodes' properties are generic, while other are relevant for specific interface types only.

The following 2 tables describe the **node and relation types**, and how they are defined in each interface type:

### Node Types

<table style="width: 900px;">
<tbody>
<tr style="height: 35px;">
<td width="200pxl"><strong>Node Type</strong></td>
<td width="350pxl"><strong>RDBMS</strong></td>
<td width="350pxl"><strong>OpenAPI</strong></td>
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
<td rowspan="2">FIELD</td>
<td>Columns of DB Table or View</td>
<td>Property of OpenAPI Object</td>
</tr>
<tr>
<td colspan="2">
<p>The FIELD data type can be:</p>
<ul>
<li>PRIMITIVE - string, integer, blob, date, number, Boolean or any.</li>
<li>COLLECTION - an array of primitive values.</li>
</ul>
</td>
</tr>
</tbody>
</table>



### Relation Types

<table style="width: 700px;">
<tbody> 
<tr style="height: 35px;">
<td width="200pxl"><strong>Relation Type</strong></td>
<td width="500pxl"><strong>Description</strong></td>
</tr>
<tr style="height: 46px;">
<td style="width: 20%; height: 184px;" rowspan="4"><strong>CONTAINS</strong></td>
<td style="width: 80%; height: 46px;">
<p>SCHEMA contains DATA_PLATFORM</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 80%; height: 46px;">
<p>DATASET contains SCHEMA</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 80%; height: 46px;">
<p>CLASS contains DATASET</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 80%; height: 46px;">
<p>FIELD contains CLASS</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 20%; height: 142px;"><strong>REFERS TO</strong></td>
<td style="width: 80%; height: 96px;">
<p>CLASS refers to CLASS</p>
<p>Example: <em>INVOICE refers to CUSTOMER</em></p>
<p>The direction is Many To One. </p>
<p>The PK / FK column names are included in the relation's properties.</p>
</td>
</tr>
</tbody>
</table>




[![Previous](/articles/images/Previous.png)](01_discovery_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_discovery_process.md) 

</web>
