# Broaway Actor's Properties Window

The **Actor's Properties** window is displayed when adding a new Actor to the flow, or when clicking an Actor object in the flow window. The Actor window contains the following sections:

<table>
<tbody>
<tr>
<td rowspan="4" valign="top" width="400pxl"><img src="/articles/99_Broadway/images/99_03_actor_properties.PNG" alt="Properties window" /></td>
<td valign="top" width="600pxl">
<p><strong>Object Name</strong></p>
<p>Populated by the object name for each Actor, added to the flow. By default, when adding an Actor, Broadway concatenates a sequential number to the Actor Type name. For example: when adding <strong>DateAdd</strong> Actors to the flow, the object names of these Actors are DateAdd1, DateAdd2...</p>
<p>The Actor name is displayed in the following format:</p>
<ul>
<li>&lt;Object Name&gt;:&lt;Actor Type&gt;</li>
</ul>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<p><strong>Parameters Filter</strong></p>
<p>Filters the input and output fields, displayed in the Actor window. The list of input and output parameters can be fileted by the following options:</p>
<ul>
<li>All Fields (default) - display the list of all fields of the Actor type and its ancestor Actors.</li>
<li>Actor Type name &ndash; display the list of input and output parameters, added for the Actor Type of the current Actor object.</li>
<li>The list of ancestor Actor Types - display the list of input and output parameters, added for the selected ancestor Actor Type.</li>
</ul>
<p><strong>For example:</strong></p>
<p>FixedColumnParser Actor Type inherits from LinesParser Actor Type:</p>
<ul>
<li>Selecting All Fields option displays the full list of input and output parameters of both Actor Types - FixedColumnParser and LinesParser.</li>
<li>You can also select one of the Actor Types - FixedColumnParser or LinesParser to get its input and output parameters.</li>
</ul>
</td>
</tr>
<tr>
<td width="300pxl">
<p><a href="/articles/99_Broadway/03_broadway_actor_window.md#data-input-parameters "><strong>Input Parameters</strong></a></p>
</td>
</tr>
<tr>
<td width="300pxl">
<p><a href="/articles/99_Broadway/03_broadway_actor_window.md#data-output-parameters "><strong>Output Parameters</strong></a></p>
</td>
</tr>
</tbody>
</table>

### Data Input Parameters

Each Actor has data input and output parameters. Each input parameter has the following attributes:

- Parameter's Attributes
- Parameter's Value

#### Input Parameter: Attributes

The parameter's definition includes the following attributes:

<table>
<tbody>
<tr>
<td valign="top" width="300pxl">
<p><strong>Parameter Name</strong></p>
</td>
<td valign="top" width="600pxl">
<p>The name of the parameter</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<p><strong>Parameter Type</strong></p>
</td>
<td valign="top" width="600pxl">
<p>String, Integer, Real&hellip;</p>
<p>When you set the population type of the input parameter as <strong>Const,&nbsp;</strong>you must set the parameter type of the input parameter:</p>
<p><img src="/articles/99_Broadway/images/const_input_parameter_type.png" alt="Const_input_type" /></p>
<p>You can edit the parameter type for each parameter regardless of its population type:</p>
<ul>
<li>Click <img src="/articles/99_Broadway/images/99_19_dots.PNG" alt=" " /> next to the parameter name &gt; Edit Schema to edit the parameter type.&nbsp;</li>
</ul>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<p><strong>Population Type</strong></p>
</td>
<td valign="top" width="600pxl">
<p>The following types are supported:</p>
<ul>
<li><strong>Link</strong> - get the input value as an input parameter from another actor.</li>
<li><strong>Const</strong> - constant. You need to set the value of the parameter.</li>
<li><strong>External</strong> - get the input value as a parameter from the external process which executes the Broadway flow. Setting the parameter as External opens an additional setting - <strong>External Name</strong>.</li>
</ul>
<p>Click <img src="/articles/99_Broadway/images/99_19_dots.PNG" alt=" " /> next to the parameter name to view or edit the population type.</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<p><strong>Description</strong></p>
</td>
<td valign="top" width="600pxl">
<p>Parameter description. The parameter description is displayed in a read-only mode.</p>
<p>Click <img src="/articles/99_Broadway/images/99_19_dots.PNG" alt=" " /> next to the parameter name &gt; Description to open the Description window.</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<p><strong>Remark</strong></p>
</td>
<td valign="top" width="600pxl">
<p>You can add a remark for each parameter. The remark adds a green asterisk next to the parameter name.</p>
<p>Click <img src="/articles/99_Broadway/images/99_19_dots.PNG" alt=" " /> next to the parameter name &gt; Remark to open the Description window.</p>
</td>
</tr>
</tbody>
</table>

#### Parameter's Value

Setting the parameter's value is needed in the following scenarios:

-  The population type is set to **Const**. 

  Example: 

   **Const** Actor. The input parameter name is **value**, the type is **String**, the value is "Hello Broadway":

  ![Const1](/articles/99_Broadway/images/const_actor_example.png)

- The population type is **Link** or **External** and you define a default value.

  Example:

  **Const** Actor. The  input parameter name is **value**, the type is **Integer**, and the default value is 7:

  ![Const2](/articles/99_Broadway/images/const_actor_def_value_example.png)

Notes:

- Click ![image](/articles/99_Broadway/images/99_19_dots.PNG) next to the parameter name and select **Default value** to set a default value for an input parameter.

- Broadway enables populating a value that matches the parameters type. For example: Broadway only enables to insert  numbers for Integer parameters. 

- When populating an input value for **SQL** parameter, you can click the **QB** icon and open the [Query Builder window](/articles/11_query_builder/02_query_builder_window.md) to build the SQL query. See an example of **DbCommand** built-in Actor:

  ![Sql_input](/articles/99_Broadway/images/sql_input_value.png)
  
  
  
  ### Data Output Parameters
  
  <table>
  <tbody>
  <tr>
  <td valign="top" width="300pxl">
  <p><strong>Parameter Name</strong></p>
  </td>
  <td valign="top" width="600pxl">
  <p>The name of the parameter</p>
  </td>
  </tr>
  <tr>
  <td valign="top" width="300pxl">
  <p><strong>Parameter Type</strong></p>
  </td>
  <td valign="top" width="600pxl">
  <p>String, Integer, Real&hellip;</p>
  <p>Click <img src="/articles/99_Broadway/images/99_19_dots.PNG" alt=" " /> next to the parameter name &gt; Edit Schema to edit the parameter type.&nbsp;</p>
  </td>
  </tr>
  <tr>
  <td valign="top" width="300pxl">
  <p><strong>Output Type</strong></p>
  </td>
  <td valign="top" width="600pxl">
  <p>Click <img src="/articles/99_Broadway/images/99_19_dots.PNG" alt=" " /> next to the parameter name &gt; Set External to expose the output to the external process that executes the Broadway flow.</p>
  </td>
  </tr>
  <tr>
  <td valign="top" width="300pxl">
  <p><strong>Description</strong></p>
  </td>
  <td valign="top" width="600pxl">
  <p>Parameter description. The parameter description is displayed in a read-only mode.</p>
  <p>Click <img src="/articles/99_Broadway/images/99_19_dots.PNG" alt=" " /> next to the parameter name &gt; Description to open the Description window.</p>
  </td>
  </tr>
  <tr>
  <td valign="top" width="300pxl">
  <p><strong>Remark</strong></p>
  </td>
  <td valign="top" width="600pxl">
  <p>You can add a remark for each parameter. The remark adds a green asterisk next to the parameter name.</p>
  <p>Click <img src="/articles/99_Broadway/images/99_19_dots.PNG" alt=" " /> next to the parameter name &gt; Remark to open the Description window.</p>
  </td>
  </tr>
  </tbody>
  </table>
  
  

[![Previous](/articles/images/Previous.png)](articles/99_Broadway/03_broadway_actor.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_Broadway/04_built_in_actor_types.md)
