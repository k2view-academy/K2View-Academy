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
<td style="width: 100px;"><strong>Node Type</strong></td>
<td style="width: 600px;"><strong>Fabric Interface Type</strong></td>
</tr>
<tr>
<td style="width: 189.703px;"><strong>dataPlatform</strong></td>
<td style="width: 321.297px;">Represents a Fabric interface in the Catalog data model</td>
</tr>
<tr>
<td style="width: 189.703px;"><strong>schema</strong></td>
<td style="width: 321.297px;">Represents a schema of the Fabric interface</td>
</tr>
<tr>
<td style="width: 189.703px;"><strong>dataset</strong></td>
<td style="width: 321.297px;">Represents a dataset (e.g. table) of the Fabric interface’s schema</td>
</tr>
<tr>
<td style="width: 189.703px;"><strong>class</strong></td>
<td style="width: 321.297px;">Represents a dataset (e.g. table) of the Fabric interface’s schema (1:1 to dataset)</td>
</tr>
<tr>
<td style="width: 189.703px;" rowspan="2"><strong>field</strong></td>
<td style="width: 321.297px;">Represents a dataset field</td>
</tr>
<tr>
<td style="width: 321.297px;">
<p>The field data type can be:</p>
<ul>
<li>primitive - string, integer, blob, date, number, boolean or any.</li>
<li>collection - an array of primitive values.</li>
</ul>
</td>
</tr>
</tbody>
</table>





### Relation Types

<table style="width: 700px;">
<tbody>
<tr style="height: 35px;">
<td width="100pxl"><strong>Relation Type</strong></td>
<td width="600pxl"><strong>Description</strong></td>
</tr>
<tr>
<td style="width: 20%; height: 184px;"><strong>contains</strong></td>
<td style="width: 80%; height: 46px;">
<p>dataPlatform <strong>contains</strong> schema</p>
<p>schema <strong>contains</strong> dataset</p>
<p>dataset <strong>contains</strong> class</p>
<p>class <strong>contains</strong> field</p>
<p>The direction is One To Many.</p>
</td>
</tr>
<tr>
<td style="width: 20%; height: 142px;"><strong>refersTo</strong></td>
<td style="width: 80%; height: 96px;">
<p>dataset <strong>refers to</strong> dataset</p>
<p>Example: <em>INVOICE refers to CUSTOMER</em></p>
<p>The direction is Many To One. The PK / FK columns are included in the relation's properties.</p>
</td>
</tr>
</tbody>
</table>








[![Previous](/articles/images/Previous.png)](01_catalog_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_discovery_process.md) 

</web>
