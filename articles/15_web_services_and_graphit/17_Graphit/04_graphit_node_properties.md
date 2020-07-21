# Graphit Node Properties

### What Are Node Properties?

Node Properties are additional instructions that can be given to a node. For example, how to format a number, which database to query, or if the node is active or disabled. 

To assign a property to a node, click the **(+)** adjacent to the node and select the  property from the dropdown menu. 

Note that there is a short description of each property in the dropdown menu.

<img src="/articles/15_web_services_and_graphit/17_Graphit/images/19_node_properties_menu.png" width="500" height="350"></img>


### What Are the Node Properties Options?
Please refer to the files referred to in the Example column and located in the Project environment under Project Tree > Web Services > Resources.
You can run each file in debug mode and observe the response for each of the types that are described below:
<table>
<tbody>
<tr>
<td valign="top" width="50pxl">
<p><strong>Node Property</strong></p>
</td>
<td valign="top" width="700pxl">
<p><strong>Description</strong></p>
</td>
<td valign="top" width="300pxl">
<p><strong>Examples</strong></p>
</td>
</tr>
<tr>
<td valign="top" width="50pxl">Session Provider</td>
<td valign="top" width="700pxl">Defines which interface should be used for a query. This property should be defined each time a node is defined as SQL or Non-prepared SQL and the queried database is not Fabric.<br />Note that this property affects the node and its children nodes.</td>
<td valign="top" width="300pxl">grShowFormat.graphit:<br> The sessionProvider flag is set to CRM_DB thus enabling direct references to CRM_DB tables and fields</br></td> 
</tr>
<tr>
<td valign="top" width="50pxl">Enabled</td>
<td valign="top" width="700pxl">If enabled is set to false, the node and its children are disregarded.</td>
<td valign="top" width="300pxl">grShowEnabled.graphit:<br>The response comes back empty since the entire CRM_DB node and its children nodes are affected by the "enabled" flag </br></td>
</tr>
<tr>
<td valign="top" width="50pxl">Nice</td>
<td valign="top" width="700pxl">Defines the layout of the output format. True defines that each tag is printed in a new line and is indented.&nbsp;<br />Note that this property affects the node and its child nodes.</td> 
<td valign="top" width="300pxl">grShowEnabled.graphit:<br>The "nice" flag is set to TRUE and has been activated at the root node level. The response comes back with each tag appearing in a new line and with the indentation corresponding to the location of the tag in the documents' hierarchy</br></td>  
</tr>
<tr>
<td valign="top" width="50pxl">One</td>
<td valign="top" width="700pxl">Defines whether the node is handled as an array or a single value. True defines that the result is always a single entry also if the query it is based on returns multiple rows.</td>
<td valign="top" width="300pxl">grOne.graphit:<br>The "ONE" flag is set to TRUE and has been applied to the Billing_DB2 node. The response only brings the first value for {"BILLING_DB2":{"SUBSCRIBER_ID":2}}, instead of the 10 values expected for this tag (if the "ONE" flag had not been activated)</br></td>
</tr>
<tr>
<td valign="top" width="50pxl">Entry Tag</td>
<td valign="top" width="700pxl">Defines the tag surrounding XML array entries. When not used or set as None, the [entry] value is used.</td>
<td valign="top" width="300pxl">grEntry.graphit:<br>The Entry flag has been set to the subscribers node. The XML response therefore shows <subscribers> and </subscribers> tags around each value of subscriber_id</br></td>
</tr>
<tr>
<td valign="top" width="50pxl">Attribute</td>
<td valign="top" width="700pxl">Defines in XML whether a value is set as an attribute or child node (default).&nbsp;</td>
<td valign="top" width="300pxl">grAttribute.graphit:<br>The Attribute flag has been activated to all children nodes of the CRM_DB node.</br></td>
</tr>
<tr>
<td valign="top" width="50pxl">Format</td>
<td valign="top" width="700pxl">When defined, the node is evaluated and added when the output format matches the format's JSON, XML or CSV value. Note that this property only affects the node where it is defined.&nbsp;</td>
<td valign="top" width="300pxl">grFormat.graphit:<br>The Format flag has been set to XML in the root node. The response format will only be displayed if the Output value (on the right side of the run button) is set to XML. Running the file with other output selections such as JSON or CSV will return an error</br></td>
</tr>
<tr>
<td valign="top" width="50pxl">Show Empty</td>
<td valign="top" width="700pxl">Defines whether empty nodes are displayed in the output. Default = True.<br />Note that this property affects the node and its child nodes.
<td valign="top" width="300pxl">grShowEmpty.graphit:<br>The ShowEmpty flag has been set to False and applied to the CRM_DB node. Empty nodes are not shown in the response.</br></td>

</td>
</tr>
<tr>
<td valign="top" width="50pxl">Show Null</td>
<td valign="top" width="700pxl">Defines whether null entries are displayed in the output. Default = True.<br />Note that this property affects the node and its child nodes.&nbsp;</td>
<td valign="top" width="300pxl">grShowNull.graphit:<br>The ShowNull flag has been set to False and applied to the CRM_DB node. Null values will therefore be ignored and not shown in the part of the response that deals with the CRM_DB. Since the flag has not been applied to the Billing_DB node, null values will be shown.</br></td>

</tr>
<tr>
<td valign="top" width="50pxl">Number Format</td>
<td valign="top" width="700pxl">Controls how numbers are formatted in the output. Either use a built-in format or use the following syntax to create your own:

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

Note that this property affects the node and its child nodes.&nbsp;</td>
<td valign="top" width="300pxl">grNumberFormat.graphit:<br>The flag has been set to 000.00 and applied to the NumberFormat node. All responses displays "Number Format" with 3 digits before the floating point and another 2 after.</br></td>
</tr>
<tr>
<td valign="top" width="50pxl">Keys</td>
<td valign="top" width="700pxl">Advanced mechanism that replaces nested queries by joining the data on the root query and grouping it with a key when query keys are used to select a subset of rows to group each invocation of the node. When keys are specified in child nodes, each node groups its parent's node according to the key.</td>
<td valign="top" width="300pxl">grKeys.graphit:<br>The response has been re-organized using the subscriber_id as a key.</br></td>

</tr>
<tr>
<td valign="top" width="50pxl">csvHeader, csvRow, csvCol, csvEnclose</td>
<td valign="top" width="700pxl">The following node properties control CSV format:
<td valign="top" width="300pxl">grCSV.graphit:<br>The csvRow has been set to the SUBSCRIBER_ID node and csvHeader has been set to False in the Subscriber_info node. The header is removed from the CSV output, while a new line is created for wach new subscriber_id entry</br></td>
<ul>
<li>csvHeader, disables a header row</li>
<li>csvRow, defines the delimiter between rows in&nbsp; CSV format. The default value is set to the CR sign (\n).</li>
<li>csvCol, defines the delimiter between columns in CSV format. The default value is set to the comma sign.</li>
<li>csvEnclose, defines the character used to enclose a value in CSV format. This is only used if the value holds a special character (csvEnclose, csvRow, csvCol).</li>
</ul>
</td>
</tr>
</tbody>
</table>





[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/17_Graphit/03_graphit_node_types.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/17_Graphit/05_graphit_debugging.md)

