<web>

# Catalog Vocabulary

The Fabric Catalog introduces a vocabulary that describes the catalog entities and the relations between them. The relations indicate the connections between the data entities and determine their hierarchy.

The below vocabulary serves as a model for describing a Catalog and assists with processes standardization across different interface types. 

The data entities are represented by **nodes** and the referential links between the **nodes** are represented by **relations**. Nodes and relations have predefined properties that enrich the Catalog schema. 

Additionally, due to differences between the data sources, some nodes' properties are generic, while others are relevant only for specific interface types.

The following 2 tables describe the **node and relation types**, and how they are defined in each interface type:

### Node Types

<table style="width: 700px;">
<tbody>
<tr style="height: 35px;">
<td style="width: 200px;"><strong>Node Type</strong></td>
<td style="width: 500px;"><strong>RDBMS</strong></td>
</tr>
<tr>
<td style="width: 189.703px;"><strong>DATA_PLATFORM</strong></td>
<td style="width: 321.297px;">DB Interface</td>
</tr>
<tr>
<td style="width: 189.703px;"><strong>SCHEMA</strong></td>
<td style="width: 321.297px;">Schema of DB Interface</td>
</tr>
<tr>
<td style="width: 189.703px;"><strong>DATASET</strong></td>
<td style="width: 321.297px;">DB Table or View</td>
</tr>
<tr>
<td style="width: 189.703px;"><strong>CLASS</strong></td>
<td style="width: 321.297px;">DB Table or View (1:1 to DATASET)</td>
</tr>
<tr>
<td style="width: 189.703px;" rowspan="2"><strong>FIELD</strong></td>
<td style="width: 321.297px;">Columns of DB Table or View</td>
</tr>
<tr>
<td style="width: 321.297px;">
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
<p>DATA_PLATFORM contains SCHEMA</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 80%; height: 46px;">
<p>SCHEMA contains DATASET</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 80%; height: 46px;">
<p>DATASET contains CLASS</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 80%; height: 46px;">
<p>CLASS contains FIELD</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 20%; height: 142px;"><strong>REFERS TO</strong></td>
<td style="width: 80%; height: 96px;">
<p>DATASET refers to DATASET</p>
<p>Example: <em>INVOICE refers to CUSTOMER</em></p>
<p>The direction is Many To One. The PK / FK column names are included in the relation's properties.</p>
</td>
</tr>
</tbody>
</table>






[![Previous](/articles/images/Previous.png)](01_catalog_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_discovery_process.md) 

</web>
