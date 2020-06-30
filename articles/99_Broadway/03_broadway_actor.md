# Broadway Actors

## Actor Overview

Broadway **Actor** object executes a predefined activity in the **Flow**. The Actors are added to each Flow's **Stage**.

The **Actor** is a JAVA class which implements the **Actor Interface** and overrides the **action** method.

### Actor Types

#### Built-In Actors

Broadway contains [built-in Actors](/articles/99_Broadway/04_built_in_actor_types.md) which address a vast range of activities and can be added to Broadway Flows: file handling, JSON handling, DB activities, String manipulations, etc..

#### Customized Actors

A customized JAVA class which implements the **Actor Interface** and overrides the **action** method.

#### Save Flow as Actor

In some cases you may prefer to package a Flow object and use it as an inner flow to avoid complex flows with a huge number of step or to enable a reuse of an inner flow in different flows.

For example:

- Define an inner flow for error handling.

To enable a reuse of a Flow object you need to save it as Actor:

- Click **Actions** in the tool bar > Save as Actor.

The newly created Actor Type can be added to any flow.

[Click for more information about inner flows.](add a link). 

#### Export Actor

You can export an Actor object and create an Actor Type, based on this object, to reuse this Actor.

The export activity creates a new Actor Type.

[Click for more information about Export Actor and Actor Inheritance.]() 

### Actor Objects

An Actor Type is a template of an Actor object. Adding an Actor to a Flow create a new instance of the selected Actor Type.

### Actor  Description and Remark

Click the **...** icon on the Actor > Description to view the description of the Actor Type.  See the example below:

![Description](/articles/99_Broadway/images/actor_description.png)

The Description window is displayed in a read-only mode.

Click the **...** icon on the Actor > Remark to open the Remark window and add your own remark on the Actor object.

Notes that if you export the Actor and creates an Actor Type based on the exported Actor, the Remark of the Actor is copied to the Description of the newly created Actor Type. 

### Actor Window

The Actor Window is displayed when adding a new Actor to the flow, or when clicking an Actor object in the flow window. The Actor window contains the following sections:

<table>
<tbody>
<tr>
<td width="300pxl" valign="top">
<p><strong>Object Name</strong></p>
</td>
<td width="600pxl" valign="top">
<p>Populated by the object name for each Actor, added to the flow. By default, when adding an Actor, Broadway concatenates a sequential number to the Actor Type name. For example: when adding <strong>DateAdd</strong> Actors to the flow, the object names of these Actors are DateAdd1, DateAdd2...</p>
<p>The Actor Name is displayed in the following format:</p>
<ul>
<li>&lt;Object Name&gt;:&lt;Actor Type&gt;</li>
</ul>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<p><strong>Parameters Filter</strong></p>
</td>
<td width="600pxl" valign="top">
<p>Filters the input and output fields, displayed in the Actor window. The list of input and output parameters can be fileted by the following options:</p>
<ul>
<li>All Fields- default option- display the list of all fields of the Actor type and its ancestor Actors.</li>
<li>Actor Type name &ndash; display the list of input and output parameters, added for the Actor Type of the current Actor object.</li>
<li>The list of ancestor Actor Types- display the list of input and output parameters, added for the selected ancestor Actor Type.</li>
</ul>
<p><strong>For example:</strong></p>
<p>FixedColumnParser Actor Type inherits from LinesParser Actor Type:</p>
<ul>
<li>Selecting All Fields option displays the full list of input and output parameters of both Actor Types- FixedColumnParser and LinesParser.</li>
<li>You can also select one of the Actor Types- FixedColumnParser or LinesParser to get its input and output parameters.</li>
</ul>
</td>
</tr>
<tr>
<td width="300pxl">
<p><strong>Data Input</strong></p>
</td>
<td width="600pxl">
<p>Input Parameters</p>
</td>
</tr>
<tr>
<td width="300pxl">
<p><strong>Data Output</strong></p>
</td>
<td width="600pxl">
<p>Output Parameters</p>
</td>
</tr>
</tbody>
</table>

#### Data Input Parameters

Each Actor has data input and out parameters.

Each input parameter has the following attributes:

- Parameter's Attributes
- Parameter's Value

##### Input Parameter: Attributes

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
<p>Click the <strong>&hellip;</strong> icon next to the parameter name &gt; Edit Schema to edit the parameter type.&nbsp;</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<p><strong>Input Type</strong></p>
</td>
<td valign="top" width="600pxl">
<p>The following input types are supported:</p>
<ul>
<li><strong>Link</strong>- get the input type as an input parameter from another actor.</li>
<li><strong>Const</strong>- constant. You need to set the value of the parameter.</li>
<li><strong>External</strong>- get the input value as a parameter from the external process which executes the Broadway flow. Setting the parameter as External opens an additional setting- <strong>External Name</strong>.</li>
</ul>
<p>Click the <strong>&hellip;</strong> icon next to the parameter name to view or edit the input type.</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<p><strong>Description</strong></p>
</td>
<td valign="top" width="600pxl">
<p>Parameter description. The parameter description is displayed in a read-only mode.</p>
<p>Click the <strong>&hellip;</strong> icon next to the parameter name &gt; Description to open the Description window.</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<p><strong>Remark</strong></p>
</td>
<td valign="top" width="600pxl">
<p>You can add a remark for each parameter. The remark adds a green asterisk next to the parameter name.</p>
<p>Click the <strong>&hellip;</strong> icon next to the parameter name &gt; Remark to open the Description window.</p>
</td>
</tr>
</tbody>
</table>




##### Parameter's Value

Setting the parameter's value is needed in the following scenarios:

-  The input type is set to **Const**. 

  Example: 

   **Const** Actor. The input parameter name is **value**, the type is **String**, the value is "Hello Broadway":

  ![Const1](/articles/99_Broadway/images/const_actor_example.png)

- The input type is **Link** or **External** and you define a default value.

  Example:

  **Const** Actor. The  input parameter name is **value**, the type is **Integer**, and the default value is 7:

  ![Const2](/articles/99_Broadway/images/const_actor_def_value_example.png)

Notes:

- Click the … icon next to the parameter name > Default value to set a default value for an input parameter.

- Broadway enables populating a value that matches the parameters type. For example: Broadway only enables to insert  numbers for Integer parameters. 

- When populating an input value for **SQL** parameter, you can click the **QB** icon and open the [Query Builder window](/articles/11_query_builder/02_query_builder_window.md) to build the SQL query. See an example of **DbCommand** built-in Actor:

  ![Sql_input](/articles/99_Broadway/images/sql_input_value.png)
  
  
  
  #### Data Output Parameters
  
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
  <p>Click the <strong>&hellip;</strong> icon next to the parameter name &gt; Edit Schema to edit the parameter type.&nbsp;</p>
  </td>
  </tr>
  <tr>
  <td valign="top" width="300pxl">
  <p><strong>Output Type</strong></p>
  </td>
  <td valign="top" width="600pxl">
  <p>Click the &hellip; icon next to the parameter name &gt; Set External to expose the output to the external process that executes the Broadway flow.</p>
  </td>
  </tr>
  <tr>
  <td valign="top" width="300pxl">
  <p><strong>Description</strong></p>
  </td>
  <td valign="top" width="600pxl">
  <p>Parameter description. The parameter description is displayed in a read-only mode.</p>
  <p>Click the <strong>&hellip;</strong> icon next to the parameter name &gt; Description to open the Description window.</p>
  </td>
  </tr>
  <tr>
  <td valign="top" width="300pxl">
  <p><strong>Remark</strong></p>
  </td>
  <td valign="top" width="600pxl">
  <p>You can add a remark for each parameter. The remark adds a green asterisk next to the parameter name.</p>
  <p>Click the <strong>&hellip;</strong> icon next to the parameter name &gt; Remark to open the Description window.</p>
  </td>
  </tr>
  </tbody>
  </table>
  
  
  
  <!-- to check with Yuval- empty values -->



