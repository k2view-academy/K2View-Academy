# Broadway Actor's Properties Window

### Properties Overview

The **Actor's Properties** window is displayed when adding a new Actor to the flow, or when clicking an Actor object in the flow window. The Actor window contains the following sections:

<table>
<tbody>
<tr>
<td rowspan="4" valign="top" width="400pxl"><img src="/articles/99_Broadway/images/99_03_actor_properties.PNG" alt="Properties window" /></td>
<td valign="top" width="600pxl">
<p><strong>Object Name</strong></p>
<p>Displays the Actor name in the following format:</p>
<ul>
<li>&lt;Object Name&gt;:&lt;Actor Type&gt;</li>
</ul>
<p>By default, when adding an Actor, Broadway concatenates a sequential number to the Actor Type name. For example: when adding <strong>DateAdd</strong> Actors to the flow, the object names of these Actors are DateAdd1, DateAdd2...</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<p><strong>Parameters Filter</strong></p>
<p>Filters the input and output fields in the Actor window by the following options:</p>
<ul>
<li>All Fields (default) - display the list of all fields of the Actor and its ancestor Actors.</li>
<li>Actor name &ndash; display the list of input and output parameters of the current Actor object.</li>
<li>Ancestor Actor name - display the list of input and output parameters of the Actor's ancestor.</li>
</ul>
<p>For example:</p>
<ul>
<li><strong>FixedColumnParser</strong> Actor inherits from <strong>LinesParser</strong> Actor.</li>
<li>Selecting <strong>All Fields</strong> option displays the full list of input and output parameters of <strong>FixedColumnParser</strong> Actor.</li>
<li>Selecting one of the Actor Types (FixedColumnParser or LinesParser) will filter the input and output parameters accordingly.</li>
</ul>
</td>
</tr>
<tr>
<td width="300pxl">
<p><strong>INPUTS</strong></p>
<p>Displays the Input parameters in the following format:</p>
<ul>
<li>&lt;Parameter Name&gt;:&lt;Parameter Type&gt;</li>
</ul>
<p>You can perform changes on the input parameters - by pressing <img src="/articles/99_Broadway/images/99_19_dots.PNG" alt=" " />.</p>
<p><strong>Add Input</strong> button allows to add more input parameters.</p>
</td>
</tr>
<tr>
<td width="300pxl">
<p><strong>OUTPUTS</strong></p>
<p>Displays the Output parameters in the following format:</p>
<ul>
<li>&lt;Parameter Name&gt;:&lt;Parameter Type&gt;</li>
</ul>
<p>You can perform changes on the output parameters - by pressing <img src="/articles/99_Broadway/images/99_19_dots.PNG" alt=" " />.</p>
</td>
</tr>
</tbody>
</table>



### Actor's Inputs and Outputs

#### Input Parameters Properties

![image](/articles/99_Broadway/images/99_03_inputs_const.PNG)

![image](/articles/99_Broadway/images/99_03_inputs_external.PNG)

![image](/articles/99_Broadway/images/99_03_inputs_link.PNG)

The Actors input parameters are displayed in the Actor's Properties window in the following format:

- <Parameter Name>:<Parameter Type>

The population of the input parameter depends on the **Population Type**. The following types are supported:

- Link - get the input value as an input parameter from another Actor. When **Link** is selected, need to connect the parameter with another Actor's output parameter.
- Const - set the value of the parameter. When **Const** is selected, a text area will appear below the parameter to allow inserting the text.
- External - get the input value as a parameter from the external process which executes the Broadway flow. When External is selected, additional field **External Name** will appear below the parameter.

The actions on the input parameters are:

* Default value - set a default value for an input parameter. 

  * Default value is applicable for population types **Link** and **External**.

  * Broadway enables populating a value that matches the parameters type. For example: when the parameter is Integer, Broadway only enables to insert numbers into this parameter.

  * When populating an input value for **SQL** parameter, you can click the **QB** icon and open the [Query Builder window](/articles/11_query_builder/02_query_builder_window.md) to build the SQL query. See an example of **DbCommand** built-in Actor:

    ![Sql_input](/articles/99_Broadway/images/99_03_sql.PNG)

* Edit Schema - click to open the popup window that enables updating the parameter's type.

* Description - click to check the parameter's description. The description cannot be changed.

* Remark - click to add a remark. The remark can be modified later.

#### Add Input Button

Some Actor types allow to add more input parameters by clicking **Add Input** button. 

When clicked, a new input parameter will be added with default Parameter Type = any and default Population Type = Link. If the population type is changed to Const, a drop-down list will appear to select the required parameter type. 

You can delete or add more parameters via the context menu by clicking ![image](/articles/99_Broadway/images/99_19_dots.PNG) > Delete or Insert.

Note that the input parameters of the ancestor Actor cannot be deleted. 

#### Output Parameters Properties

The Actors output parameters are displayed in the Actor's Properties window in the following format:

- <Parameter Name>:<Parameter Type>

The actions on the input parameters are:

* Set External - click to expose the output parameter to the external process that executes the Broadway flow. When selected, additional field **External Name** will appear below the parameter.
* Edit Schema - click to open the popup window that enables updating the parameter's type.
* Description - click to check the parameter's description. The description cannot be changed.
* Remark - click to add a remark. The remark can be modified later.

[![Previous](/articles/images/Previous.png)](/articles/99_Broadway/03_broadway_actor.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_Broadway/04_built_in_actor_types.md)