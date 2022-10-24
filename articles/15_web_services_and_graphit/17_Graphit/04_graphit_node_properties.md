# Graphit Node Properties

### What Are Node Properties?

Node Properties are additional instructions that can be given to a node. For example, how to format a number, which database to query, or if the node is active or disabled. 

To assign a property to a node, click the **(+)** adjacent to the node and select the  property from the dropdown menu. 

Note that there is a short description of each property in the dropdown menu.

<img src="../images/graphit/19_node_properties_menu.png" width="500" height="350"></img>


### What Are the Node Properties Options?
Please refer to the files in the following table's Example column. We suggest that you run each file in Debug mode and observe the response. The files are saved in the Project environment under Project Tree > Web Services > Resources. 
<table>
<tbody>
<tr>
<td valign="top" >
<p><strong>Node Property</strong></p>
</td>
<td valign="top" >
<p><strong>Description</strong></p>
</td>
<td valign="top" >
<p><strong>Examples</strong></p>
</td>
</tr>
<tr>
<td valign="top" >Session Provider</td>
<td valign="top" >Defines which interface should be used for a query. This property should be defined each time a node is defined as SQL or Non-prepared SQL and the queried database is not Fabric.<br />Note that this property affects the node and its children nodes.</td>
<td valign="top" ><a href="/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md#grshowformatgraphit">grShowFormat</a></td> 
</tr>
<tr>
<td valign="top" >Enabled</td>
<td valign="top" >If enabled is set to False, the node and its children are disregarded.</td>
<td valign="top" ><a href="/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md#grshowenabledgraphit">grShowEnabled</a></td>
</tr>
<tr>
<td valign="top" >Nice</td>
<td valign="top" >Defines the layout of the output format. True defines that each tag is printed in a new line and is indented.&nbsp;<br />Note that this property affects the node and its child nodes.</td> 
<td valign="top" ><a href="/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md#grshowenabledgraphit">grShowEnabled</a></td>  
</tr>
<tr>
<td valign="top" >One</td>
<td valign="top" >Defines whether the node is handled as an array or a single value. True defines that the result is always a single entry also if the query it is based on returns multiple rows.</td>
<td valign="top" ><a href="/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md#gronegraphit">grOne</a></td>
</tr>
<tr>
<td valign="top" >Entry Tag</td>
<td valign="top" >Defines the tag surrounding XML array entries. When not used or set as None, the [entry] value is used.</td>
<td valign="top" ><a href="/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md#grentrygraphit">grEntry</a></td>
</tr>
<tr>
<td valign="top" >Attribute</td>
<td valign="top" >Defines in XML whether a value is set as an attribute or child node (default).&nbsp;</td>
<td valign="top" ><a href="/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md#grattributegraphit...">grAttribute</a></td>
</tr>
<tr>
<td valign="top" >Format</td>
<td valign="top" >When defined, the node is evaluated and added when the output format matches the format's JSON, XML or CSV value. Note that this property only affects the node where it is defined.&nbsp;</td>
<td valign="top" ><a href="/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md#grformatgraphit">grFormat</a></td>
</tr>
<tr>
<td valign="top" >Show Empty</td>
<td valign="top" >Defines whether empty nodes are displayed in the output. Default = True.<br />Note that this property affects the node and its children nodes.
<td valign="top" ><a href="/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md#grshowemptygraphit">grShowEmpty</a></td>
</td>
</tr>
<tr>
<td valign="top" >Show Null</td>
<td valign="top" >Defines whether null entries are displayed in the output. Default = True.<br />Note that this property affects the node and its child nodes.&nbsp;</td>
<td valign="top" ><a href="/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md#grshownullgraphit">grShowNull</a></td>
</tr>
<tr>
<td valign="top" >Number Format</td>
<td valign="top" >Controls how numbers are formatted in the output. Either use a built-in format or use the following syntax to create your own:
```
0  Digit
#  Digit, zero shows as absent
.  Decimal separator or monetary decimal separator
-  Minus sign
,  Grouping separator
E  Separates mantissa and exponent in scientific notation.
;  Separates positive and negative subpatterns
%  Multiply by 100 and show as percentage
```
Note that this property affects the node and its children nodes.&nbsp;</td>
<td valign="top" ><a href="/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md#grnumberformatgraphit">grFormatNumber</a></td>
</tr>
<tr>
<td valign="top" >Keys</td>
<td valign="top" >Advanced mechanism that replaces nested queries by joining the data on the root query and grouping it with a key when query keys are used to select a subset of rows to group each invocation of the node. When keys are specified in children nodes, each node groups its parent's node according to the key.</td>
<td valign="top" ><a href="/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md#grkeysgraphit">grKeys</a></td>
</tr>
<tr>
<td valign="top" >csvHeader, csvRow, csvCol, csvEnclose</td>
<td valign="top" >The following node properties control CSV format:
<ul>
<li>csvHeader, disables a header row</li>
<li>csvRow, defines the delimiter between rows in&nbsp; CSV format. The default value is set to the CR sign (\n).</li>
<li>csvCol, defines the delimiter between columns in CSV format. The default value is set to the comma character.</li>
<li>csvEnclose, defines the character used to enclose a value in CSV format. This is only used if the value holds a special character (csvEnclose, csvRow, csvCol).</li>
</ul>
</td>
<td valign="top" ><a href="/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md#grcsvgraphit">grCSV</a></td>
</tr>
</tbody>
</table>





[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/17_Graphit/03_graphit_node_types.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/17_Graphit/05_graphit_debugging.md)

