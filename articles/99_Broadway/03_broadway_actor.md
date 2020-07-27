# Broadway Actors

## Actor Overview

A Broadway  **Actor** represents the action that must be executed on each [Stage](/articles/99_Broadway/19_broadway_flow_stages.md) of the [Broadway flow](/articles/99_Broadway/16_broadway_flow_overview.md) to get input parameters and return output parameters. For example, reading a file, creating a table, parsing an object or concatenating a string.

An **Actor** is a JAVA class which implements the **Actor Interface** and overrides the **action** method. 

Broadway offers a number of built-in Actors which can be extended by customization. 
-  [Built-in Actors](/articles/99_Broadway/04_built_in_actor_types.md) address a wide range of activities and can be added to Broadway flows for example to handle files, JSON files, DB activities or for string manipulations.
-  A customized Actor is a customized JAVA class which implements the **Actor Interface** and overrides the **action** method.

Other options for working with Actors include:
-  Creating new Actors. **(Add link)**.
-  Adding or deleting Actors from flows. **(Add link)**
-  Saving Actors as inner flows of other Broadway flows. **(Add link)**
-  Exporting Actors to create inherited Actors. **(Add link)**  



*************************************************************************************************************
**New article**

#### How Do I Save an Actor?
An Actor can be saved and be added as an inner flow of another flow, for example for handling errors. 
Actors can also be reused in other flows, for example to avoid complex flows with multiple steps. 

To save an Actor, click **Actions** in the toolbar and select **Save as Actor**. The new Actor can be added to any flow.

[Click for more information about inner flows.](add a link). 

*************************************************************************************************************
**New article**
#### How Do I Export an Actor?
An Actor can be exported to create a new Actor Type that inherits its parameters.

To export an Actor, do the following:
1.  Go to the **Actor's context menu** and click in the right corner of the **Actor**.
2.  Click **Export Action** and **save** the Actor. The Actor is added to the list of <a href="/articles/99_Broadway/04_built_in_actor_types.md">built-in Actors</a> and can be used in other flows.

[Click for more information about Export Actor and Actor Inheritance.]() 

*************************************************************************************************************
**New article**
### What is an Actor Object?

An Actor Type is a template of an Actor object. When an Actor is added to a Broadway flow, a new instance (object) of the selected Actor type is created.



**************************************************************************************************************************
**New article**

### How Do I Add/Delete an Actor To/From A Stage?
1.  Click the **Stage** working area to display the **Add Actor** dialog box.

![image](/articles/99_Broadway/images/99_04_01_add_actor.PNG)

The **Add Actor** window has the following columns:
  
   -  Left column, displaying the Actor categories.
    
   -  Right column, displaying a list of the Actor Types in each category.
 
   When searching for Actors, filter the search according to Actor categories or types.

2.  To add an Actor to a Stage, select the **Actor Type** and click **SUBMIT**.

3.  To delete an Actor from a Stage, select the **Actor** and click **Delete** on your keyboard.

Note that if you export an Actor and create an Actor Type based on it, the Actor's Remarks are copied to the Description of the new Actor Type. 
 
***********************************************************************************************************************
**New article**
### How Do I View an Actor's Description or Remarks?

1.  Click **...** in the **Actor > Description** to display a description of the Actor Type.  

![Description](/articles/99_Broadway/images/actor_description.png)

The Description window is displayed in a read-only mode.

2.  Click **...** in the **Actor > Remark** to open the Remark window and add remarks on the Actor object.

Note that if you export an Actor and create an Actor Type based on it, the Actor's Remarks are copied to the Description of the new Actor Type. 

**********************************************************************************************************************

## Actor Window

The Actor Window is displayed when adding a new Actor to the flow, or when clicking an Actor object in the flow's window. The Actor window contains the following sections:

<table>
<tbody>
<tr>
<td width="300pxl" valign="top">
<p><strong>Object Name</strong></p>
</td>
<td width="600pxl" valign="top">
<p>Populated by the object name of each Actor added to the flow. By default, when an Actor is added, Broadway concatenates a sequential number to the Actor Type name. For example: when adding <strong>DateAdd</strong> Actors to the flow, the object names of these Actors are DateAdd1, DateAdd2...</p>
<p>The Actor Name is displayed in the following format:</p>
<li>&lt;Object Name&gt;:&lt;Actor Type&gt;</li>
</ul>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<p><strong>Parameters Filter</strong></p>
</td>
<td width="600pxl" valign="top">
<p>Filters the input and output fields displayed in the Actor window. The input and output parameters list can be filtered as follows:</p>
<ul>
<li>All Fields, (default) displays the list of all fields of the Actor type and its ancestor Actors.</li>
<li>Actor Type name, displays the list of input and output parameters added for the Actor Type of the current Actor object.</li>
<li>Ancestor Actor Types, displays the list of input and output parameters added for the selected ancestor Actor Type.</li>
</ul>
<p><strong>For example:</strong></p>
<p>The FixedColumnParser Actor Type inherits from the LinesParser Actor Type:</p>
<ul>
<li>Selecting All Fields displays the full list of input and output parameters of both the FixedColumnParser and LinesParser Actor Types.</li>
<li>Selecting either the FixedColumnParser or LinesParser Actor Type displays its input and output parameters.</li>
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

### Data Input Parameters

Each Actor has data input and out parameters and each input parameter has the following attributes:

- Parameter Attributes.
- Parameter Value.

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
<p>When setting the Input of the <strong>Population Type</strong> to <strong>Const</strong>, always set the Input parameter type:</p>
<p><img src="/articles/99_Broadway/images/const_input_parameter_type.png" alt="Const_input_type" /></p>
<p>To edit a parameter's Parameter Type, regardless of its population type, click <strong>&hellip;</strong> next to the <strong>Parameter Name</strong> and select <strong>Edit Schema</strong>.</li></p>
<ul>
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
<li><strong>Link</strong>, get the input value as an input parameter from another actor.</li>
<li><strong>Const</strong>, constant, set the value of the parameter.</li>
<li><strong>External</strong>, get the input value as a parameter from the external process that executes the Broadway flow. Set the parameter as <strong>External</strong> to open an additional <strong>External Name</strong> setting.</li>
</ul>
<p>Click <strong>&hellip;</strong> adjacent to the <strong>parameter name</strong> to view or edit the population type.</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<p><strong>Description</strong></p>
</td>
<td valign="top" width="600pxl">
<p>Parameter description (read only)</p>
<p>Click <strong>&hellip;</strong> adjacent to the <strong>parameter name</strong> and select <strong>Description/<strong> to open the Description window.</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<p><strong>Remark</strong></p>
</td>
<td valign="top" width="600pxl">
<p>Remarks can be added to each parameter. When added a   ICON??????   is displayed adjacent to the parameter name.</p>
<p>Click <strong>&hellip;</strong> adjacent to the <strong>parameter name</strong> and select <strong>Remark</strong> to open the Description window.</p>
</td>
</tr>
</tbody>
</table>

#### How Do I Set a Parameter's Value?

A parameter's value can be set if the **population type** is set to **Const**. 

 For Example: 

   **Const** Actor. The input parameter name is **value**, the type is **String**, the value is "Hello Broadway":

  ![Const1](/articles/99_Broadway/images/const_actor_example.png)

- The population type is **Link** or **External** and you define a default value.

  For Example:

  **Const** Actor. The  input parameter name is **value**, the type is **Integer**, and the default value is 7:

  ![Const2](/articles/99_Broadway/images/const_actor_def_value_example.png)

Notes:

- To set a default value for an input parameter, click **…** adjacent to the **parameter name** and select **Default value**. 

- Values that match the **parameter type** can be populated, for example: to only insert  numbers for Integer parameters. 

- When populating an input value for an **SQL** parameter, click **QB** icon to open the [Query Builder window](/articles/11_query_builder/02_query_builder_window.md) and build the SQL query. For example, **DbCommand** built-in Actor:

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
  <p>Click <strong>&hellip;</strong> adjacent to the parameter name &gt; Edit Schema to edit the parameter type.&nbsp;</p>
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
  <p>Click <strong>&hellip;</strong> adjacent to the parameter name &gt; Description to open the Description window.</p>
  </td>
  </tr>
  <tr>
  <td valign="top" width="300pxl">
  <p><strong>Remark</strong></p>
  </td>
  <td valign="top" width="600pxl">
  <p>Remarks can be added to each parameter. The remark adds a green asterisk next to the parameter name.</p>
  <p>Click <strong>&hellip;</strong> adjacent to the parameter name and select Remark to open the Description window.</p>
  </td>
  </tr>
  </tbody>
  </table>
  
    

[![Previous](/articles/images/Previous.png)](articles/99_Broadway/02_broadway_high_level_components.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_Broadway/04_built_in_actor_types.md)

