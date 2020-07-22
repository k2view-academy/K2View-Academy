# Graphit Node Types

Node Type options define how content is structured and how a tag is presented in an output document. By default, nodes are not assigned a Type or Property when they are created.
### What Are the Node Type Options?
Please refer to the files referred to in the Example column and located in the Project environment under Project Tree > Web Services > Resources.
You can run each file in debug mode and observe the response for each of the types that are described below:

<table>
<tbody>
<tr>
<td valign="top" width="50pxl">
<p><strong>Node Type</strong></p>
</td>
<td valign="top" width="700pxl">
<p><strong>Description</strong></p>
</td>
<td valign="top" width="300pxl">
<p><strong>Examples</strong></p>
</td>
</tr>
<tr>
<td valign="top" width="50pxl">Field</td>
<td valign="top" width="700pxl">Basic node type. Defines the node as a tag in XML / JSON format.</td>
<td valign="top" width="300pxl"> 
<No Type>*[grFormat.graphit](/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md)*
</td>
</tr>
<tr>
<td valign="top" width="50pxl">Function</td>
<td valign="top" width="700pxl">Runs the code to determine the value of the node. Note that the code must be written in JavaScript.&nbsp;</td>
<td valign="top" width="300pxl">[grFunction.graphit file](/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md)</br></a></td>
</tr>
<tr>
<td valign="top" width="50pxl">SQL and Non-prepared SQL</td>
<td valign="top" width="700pxl">Defines how an SQL statement retrieves information from Fabric or other database interfaces.
    Enter the SQL statement manually or hover over and then click either the SQL or Non-prepared SQL field. <No Type>  

Note that if the database is not a Fabric database, the Interface Name must be defined as a Node Property as described in the [Node Properties](/articles/15_web_services_and_graphit/17_Graphit/04_graphit_node_properties.md) section. 
  

-  If the <a href="/articles/11_query_builder/01_query_builder_overview.md">Query Builder</a> option is selected, the executed query is copied into the Graphit implementation.
-  Fields can be expanded automatically according to the SQL statement defined in the Query Builder. During runtime, the SQL query is executed and the results can be used in the nested nodes. 

The SQL Type also enables looping results and executing nested codes on each returned row.&nbsp;&nbsp;<br />

Note that it is recommended to set the SQL statement type to SQL to use a prepared statement and prepared binding.&nbsp;<br />

To build an SQL statement for each call, set the query Type to Non-prepared SQL. For example, to build dynamic SQL, select X,Y from $table name.</td>
<td valign="top" width="300pxl">[grSQL.graphit](/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md)</br></a></td>
</tr>
<tr>
<td valign="top" width="50pxl">String</td>
<td valign="top" width="700pxl">Concatenates two or more values.&nbsp;</td>
<td valign="top" width="300pxl">[grString.graphit](/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md)</br></a></td>
</tr>
<tr>
<td valign="top" width="50pxl">Condition</td>
<td valign="top" width="700pxl">Builds IF-ELSE statements which should include a condition. The nested nodes are / not executed according to the result of the condition.&nbsp;</td>
<td valign="top" width="300pxl">[grCondition.graphit](/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md)</a></td>
</tr>
<tr>
<td valign="top" width="50pxl">Group&nbsp;</td>
<td valign="top" width="700pxl">Groups several elements. Mainly used with Condition nodes.</td>
<td valign="top" width="300pxl">[grGroup.graphit](/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md)</br></a></td>
</tr>
<tr>
<td valign="top" width="50pxl">Collect</td>
<td valign="top" width="700pxl">Iterates multiple data sets into one unified array.&nbsp;</td>
<td valign="top" width="300pxl">[grCollect.graphit](/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md)</br></a></td>
</tr>
<tr>
<td valign="top" width="50pxl">Raw</td>
<td valign="top" width="700pxl">Presents data as output without manipulation. For example, a header for XML format.&nbsp;</td>
<td valign="top" width="300pxl">[grRaw.graphit](/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md)</br></a></td>

</tr>
</tbody>
</table>


[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/17_Graphit/02_create_and_edit_a_graphit_file.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/17_Graphit/04_graphit_node_properties.md)

