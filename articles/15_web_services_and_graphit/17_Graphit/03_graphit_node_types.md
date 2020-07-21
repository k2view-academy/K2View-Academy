# Graphit Node Types

Node Type options define how content is structured and how a tag is presented in an output document. By default, nodes are not assigned a Type or Property when they are created.
### What Are the Node Type Options?
Please refer to the file *GraphitTagsExamples.graphit* located in the Project environment. 

<table>
<tbody>
<tr>
<td valign="top" width="300pxl">
<p><strong>Node Type</strong></p>
</td>
<td valign="top" width="600pxl">
<p><strong>Description</strong></p>
</td>
<td valign="top" width="400pxl">
<p><strong>Example</strong></p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">Field</td>
<td valign="top" width="600pxl">Basic node type. Defines the node as a tag in XML / JSON format.</td>
<td valign="top" width="400pxl"><img src="/articles/15_web_services_and_graphit/17_Graphit/images/01_graphit_examples_tags.PNG"></a></td>
</tr>
<tr>
<td valign="top" width="300pxl">Function</td>
<td valign="top" width="600pxl">Runs the code to determine the value of the node. Note that the code must be written in JavaScript.&nbsp;</td>
<td valign="top" width="400pxl"><img src="/articles/15_web_services_and_graphit/17_Graphit/images/02_graphit_examples_tags.PNG"></a></td>
</tr>
<tr>
<td valign="top" width="300pxl">SQL and Non-prepared SQL</td>
<td valign="top" width="600pxl">Defines how an SQL statement retrieves information from Fabric or other database interfaces.
    <br />Enter the SQL statement manually or hover over and then click either the SQL or Non-prepared SQL field. <No Type>  

Note that if the database is not a Fabric database, the Interface Name must be defined as a Node Property as described in the [Node Properties](/articles/15_web_services_and_graphit/17_Graphit/04_graphit_node_properties.md) section. 
  

-  If the <a href="/articles/11_query_builder/01_query_builder_overview.md">Query Builder</a> option is selected, the executed query is copied into the Graphit implementation.
-  Fields can be expanded automatically according to the SQL statement defined in the Query Builder. During runtime, the SQL query is executed and the results can be used in the nested nodes. 

The SQL Type also enables looping results and executing nested codes on each returned row.&nbsp;&nbsp;<br />

Note that it is recommended to set the SQL statement type to SQL to use a prepared statement and prepared binding.&nbsp;<br />

To build an SQL statement for each call, set the query Type to Non-prepared SQL. For example, to build dynamic SQL, select X,Y from $table name.</td>
<td valign="top" width="400pxl"><img src="/articles/15_web_services_and_graphit/17_Graphit/images/03_graphit_examples_tags.PNG"></a></td>
</tr>
<tr>
<td valign="top" width="300pxl">String</td>
<td valign="top" width="600pxl">Concatenates two or more values.&nbsp;</td>
<td valign="top" width="400pxl"><img src="/articles/15_web_services_and_graphit/17_Graphit/images/04_graphit_examples_tags.PNG"></a></td>
</tr>
<tr>
<td valign="top" width="300pxl">Condition</td>
<td valign="top" width="600pxl">Builds IF-ELSE statements which should include a condition. The nested nodes are / not executed according to the result of the condition.&nbsp;</td>
<td valign="top" width="400pxl"><img src="/articles/15_web_services_and_graphit/17_Graphit/images/05_graphit_examples_tags.PNG"></a></td>
</tr>
<tr>
<td valign="top" width="300pxl">Group&nbsp;</td>
<td valign="top" width="600pxl">Groups several elements. Mainly used with Condition nodes.</td>
<td valign="top" width="400pxl"><img src="/articles/15_web_services_and_graphit/17_Graphit/images/06_graphit_examples_tags.PNG"></a></td>
</tr>
<tr>
<td valign="top" width="300pxl">Collect</td>
<td valign="top" width="600pxl">Iterates multiple data sets into one unified array.&nbsp;</td>
<td valign="top" width="400pxl"><img src="/articles/15_web_services_and_graphit/17_Graphit/images/07_graphit_examples_tags.PNG"></a></td>
</tr>
<tr>
<td valign="top" width="300pxl">Raw</td>
<td valign="top" width="600pxl">Presents data as output without manipulation. For example, a header for XML format.&nbsp;</td>
<td valign="top" width="400pxl"><img src="/articles/15_web_services_and_graphit/17_Graphit/images/08_graphit_examples_tags.PNG"></a></td>

</tr>
</tbody>
</table>


[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/17_Graphit/02_create_and_edit_a_graphit_file.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/17_Graphit/04_graphit_node_properties.md)

