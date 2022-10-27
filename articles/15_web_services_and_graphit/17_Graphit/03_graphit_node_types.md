# Graphit Node Types

Node Type options define how content is structured and how a tag is presented in an output document. By default, nodes are not assigned a Type or Property when they are created.



The following table lists the node types. We suggest that you run each Graphit file in Debug mode and observe the response. 

<table>
<tbody>
<tr>
<td valign="top" width="150pxl">
<p><strong>Node Type</strong></p>
</td>
<td valign="top" width="500pxl">
<p><strong>Description</strong></p>
</td>
<td valign="top" width="150pxl">
<p><strong>Examples</strong></p>
</td>
</tr>
<tr>
<td valign="top" width="50pxl">Field</td>
<td valign="top" width="900pxl">Basic node type. Defines the node as a tag in XML / JSON format.</td>
<td valign="top" width="50pxl"> 
    <a href="/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md#grfieldgraphit">grField</a>
</td>
</tr>
<tr>
<td valign="top" width="50pxl">Function</td>
<td valign="top" width="900pxl">Runs the code to determine the value of the node. Note that the code must be written in JavaScript.&nbsp;</td>
<td valign="top" width="50pxl"><a href="/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md#grfunctiongraphit">grFunction</a></td>
</tr>
<tr>
<td valign="top" width="50pxl">SQL</td>
<td valign="top" width="900pxl">Defines an SQL statement that retrieves information from Fabric or other database interfaces.
    Enter the SQL statement manually or hover over and then click the SQL icon to open the Query Builder. 
    <br/><b>Note</b>: if the database is not a Fabric database, the Interface Name must be defined as described in the <a href="/articles/15_web_services_and_graphit/17_Graphit/04_graphit_node_properties.md">Node Properties</a> section. <br/>
<ul>
<li>If the <a href="/articles/11_query_builder/01_query_builder_overview.md">Query Builder</a> is selected, the Query Builder popup is opened and when closed the built query is copied into the Graphit node content.
</li>    
<li>Fields can be expanded automatically into nested nodes. When closing the Query Builder popup you are asked about. Expanding fields can be useful in case further manipulation is needed on the result fields or when shall be used on further nodes.
</li>    
</ul>
The SQL Type also enables looping results and executing nested codes on each returned row.<br/>
Note that it is recommended to set the SQL statement type to SQL to use a prepared statement and prepared binding.<br/>
To build an SQL statement for each call, set the query Type to Non-prepared SQL. For example, to build dynamic SQL, select X,Y from $table name.
</td>
<td valign="top" width="50pxl"><a href="/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md#grsqlgraphit">grSQL</a></td>
</tr>
<tr>
<td valign="top" width="50pxl">String</td>
<td valign="top" width="900pxl">simple string text or some combination with variables, such as input parameters or previoud field nodes .&nbsp;</td>
<td valign="top" width="50pxl"><a href="/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md#grstringgraphit">grString</a></td>
</tr>
<tr>
<td valign="top" width="50pxl">Get</td>
<td valign="top" width="900pxl">Define  the Fabric Get command, according to the LU and LU iid, which will be executed when invoking this Graphit file.<br/>
    Enter the Get command statement manually or hover over and then click the Helper icon to open the Command Builder.  TO EXPAND EXPLAIN
</td>
<td valign="top" width="50pxl"></a></td>
</tr>
<tr>
<td valign="top" width="50pxl">Broadway</td>
<td valign="top" width="900pxl">Define a call to a Broadway flow that will be activated.<br/>
    Enter the command statement manually or hover over and then click the Helper icon to open the Command Builder.  TO EXPAND EXPLAIN
</td>
<td valign="top" width="50pxl"></a></td>
</tr>
<tr>
<td valign="top" width="50pxl">Condition</td>
<td valign="top" width="900pxl">Builds IF-ELSE statements which should include a condition. The nested nodes are / not executed according to the result of the condition.&nbsp;</td>
<td valign="top" width="50pxl"><a href="/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md#grconditiongraphit">grCondition</a></td>
</tr>
<tr>
<td valign="top" width="50pxl">Group&nbsp;</td>
<td valign="top" width="900pxl">Groups several elements. Mainly used with Condition nodes.</td>
<td valign="top" width="50pxl"><a href="/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md#grgroupgraphit">grGroup</a></td>
</tr>
<tr>
<td valign="top" width="50pxl">Collect</td>
<td valign="top" width="900pxl">Iterates multiple data sets into one unified array.&nbsp;</td>
<td valign="top" width="50pxl"><a href="/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md#grcollectgraphit">grCollect</a></td>
</tr>
<tr>
<td valign="top" width="50pxl">Raw</td>
<td valign="top" width="900pxl">Presents data as output without manipulation. For example, a header for XML format.&nbsp;</td>
<td valign="top" width="50pxl"><a href="/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md#grrawgraphit">grRaw</a></td>
</tr>
</tbody>
</table>



[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/17_Graphit/02_graphit_basic_editing.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/17_Graphit/04_graphit_node_properties.md)

