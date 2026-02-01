# Catalog Vocabulary

The Fabric Catalog introduces a vocabulary that describes the Catalog entities and the relations between them. The relations indicate the connections between the data source entities and determine their hierarchy.

The below vocabulary serves as a model for describing a Catalog and assists with processes standardization across different interface types. 

The data entities are represented by **nodes** and the referential links between the **nodes** are represented by **relations**. Nodes and relations have predefined **properties** that enrich the Catalog schema. 

Additionally, due to differences between the data sources, some nodes' properties are generic, while others are relevant only for specific interface types.


### Node Types

<table style="width: 900px;">
<tbody>
<tr>
<td style="width: 250px;"><strong>Node Type</strong></td>
<td style="width: 650px;"><strong>Description</strong></td>
</tr>
<tr>
<td ><strong>dataPlatform</strong></td>
<td>Represents a Fabric interface in the Catalog data model</td>
</tr>
<tr>
<td ><strong>schema</strong></td>
<td >Represents an interface schema</td>
</tr>
<tr>
<td ><strong>dataset</strong></td>
<td>Represents a dataset (e.g. table) of an interface schema</td>
</tr>
<tr>
<td><strong>class</strong></td>
<td>
<p>Represents one of the following:</p>
<ul>
<li>A dataset (e.g. table) - when the class name is identical to a dataset name</li>
<li>A complex structure  - when the class name is different from a dataset name (available starting from V8.0)</li>
</ul>
</td>
</tr>
<tr>
<td ><strong>field</strong></td>
<td >
<p>Represents a dataset field. The field data type can be:</p>
<ul>
<li>Primitive - string, integer, blob, date, number, boolean or any.</li>
<li>Collection - an array of primitive values.</li>
</ul>
</td>
</tr>
</tbody>
</table>




### Relation Types

<table style="width: 900px;">
<tbody>
<tr>
<td style="width: 250px;"><strong>Relation Type</strong></td>
<td style="width: 650px;"><strong>Description</strong></td>
</tr>
<tr>
<td><strong>contains</strong></td>
<td>
<p>The relation between the objects that belong to two hierarchy levels:</p>
<ul>
<li>dataPlatform <strong>contains</strong> schema</li>
<li>schema <strong>contains</strong> dataset</li>
<li>dataset <strong>contains</strong> class</li>
<li>class <strong>contains</strong> field</li>
</ul>
<p>Example: <em>CRM_DB contains public</em></p>
<p>The direction is One To Many.</p>
</td>
</tr>
<tr>
<td ><strong>refersTo</strong></td>
<td >
<p>The relation between a parent (dataset1) and a child (dataset2). The direction is Many To One (from child to parent).</p>
<ul>
<li>dataset2 <strong>refersTo</strong> dataset1 (foreign keys)</li>
</ul>
<p>Example: <em>INVOICE refersTo CUSTOMER (customer_id)</em></p>
<p>The relation key columns are included in the relation's properties.</p>
</td>
</tr>
<tr>
<td ><strong>definedBy</strong></td>
<td>
<p>The relation between a field and its respective Class node (available starting from V8.0):</p>
<ul>
<li>field <strong>definedBy</strong> Class</li>
</ul>
<p>Example: <em>ACTIVITY_JSON definedBy Activity_jsonClass</em></p>
</td>
</tr>
</tbody>
</table>



### Node Properties 

Each Catalog node has properties that provide additional information about the node. A **Dataset field** might have a variety of properties, whereas some of them are created by the Crawler and others are created by the plugins during the Discovery job.

The **Defined By** property is a mandatory property for every Catalog field. It specifies the field's Catalog Type and can include one of the following:

<table style="width: 900px;">
<tbody>
<tr>
<td style="width: 150px;"><strong>Field Type</strong></td>
<td style="width: 200px;"><strong>Property Definition</strong></td>
<td style="width: 550px;"><strong>Description</strong></td>
</tr>
<tr>
<td><strong>Primitive</strong></td>
<td>
<p>One of the following:</p>
<ul>
<li>STRING</li>
<li>INTEGER</li>
<li>REAL</li>
<li>DATA</li>
<li>TIME</li>
<li>DATETIME</li>
<li>BYTES</li>
<li>BOOLEAN</li>
<li>UNKNOWN</li>
</ul>
</td>
<td>
<p>These values of the <strong>definedBy</strong> property establish standartization of various primitive data types across different platforms and data sources.&nbsp;</p>
<p>For example, a string value can be defined as VARCHAR, CHAR, string or other in different data sources. In Catalog, all of them are interpretend as a STRING.</p>
</td>
</tr>
<tr>
<td><strong>Object</strong></td>
<td>&lt;name&gt;</td>
<td>
<p>When a field includes a complex structure (e.g. XML), it is defined in Catalog by a class.&nbsp;</p>
</td>
</tr>
<tr>
<td><strong>Array</strong></td>
<td>
<p>One of the following:</p>
<ul>
<li>Collection (primitive type)</li>
<li>Collection(&lt;name&gt;)</li>
</ul>
</td>
<td>
<p>When a field includes an array, the values of the array can be either primitives or obejcts.&nbsp;</p>
<p>Array of arrays is supported as well.</p>
</td>
</tr>
</tbody>
</table>








[![Previous](/articles/images/Previous.png)](01_catalog_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_discovery_process.md) 

