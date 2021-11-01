# Broadway Actor's Properties Window

## Properties Overview

The **Actor's Properties** window is displayed when adding a new Actor to a flow or when clicking an Actor object in the Flow window. The Actor window holds the following sections:

<table>
<tbody>
<tr>
<td rowspan="4" valign="top" width="400pxl"><img src="images/99_03_actor_properties.PNG" alt="Properties window" /></td>
<td valign="top" width="600pxl">
<p><strong>Object Name</strong></p>
<p>Displays the Actor name in the following format:</p>
<ul>
<li>[Object Name] : [Actor Type]</li>
</ul>
<p>By default, when adding an Actor, Broadway concatenates a sequential number to the Actor Type name. For example: when adding <strong>DateAdd</strong> Actors to the flow, the object names of these Actors are DateAdd1, DateAdd2 etc.</p>
<p>It is recommended to give the Actors meaningful names according to their function in the flow.</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<p><strong>Parameters Filter</strong></p>
<p>Filters the input and output fields in the Actor window using the following options:</p>
<ul>
<li>All Fields (default), displays a list of all the Actor's fields and the fields of their ancestor.</li>
<li>Actor name, displays a list of input and output parameters of the current Actor object.</li>
<li>Ancestor Actor name, displays the list of input and output parameters of the Actor's ancestor.</li>
</ul>
<p>For example:</p>
<ul>
<li><strong>FixedColumnParser</strong> Actor inherits from <strong>LinesParser</strong> Actor.</li>
<li>Select <strong>All Fields</strong> to display a full list of input and output parameters of the <strong>FixedColumnParser</strong> Actor.</li>
<li>Select an Actor Type (FixedColumnParser or LinesParser) to filter the input and output parameters accordingly.</li>
</ul>
</td>
</tr>
<tr>
<td width="300pxl">
<p><strong>INPUTS</strong></p>
<p>Displays the input parameters in the following format:</p>
<ul>
<li>[Parameter Name] : [Parameter Type]</li>
</ul>
  <p>Click <img src="images/99_19_dots.PNG" alt=" " /> to update an input parameter. </p>
  <p>Click <strong>Add Input</strong> to add more parameters. The button is applicable for selected Actor types only.</p>
</td>
</tr>
<tr>
<td width="300pxl">
<p><strong>OUTPUTS</strong></p>
<p>Displays the output parameters in the following format:</p>
<ul>
<li>[Parameter Name] : [Parameter Type]</li>
</ul>
  <p>Click <img src="images/99_19_dots.PNG" alt=" " /> to update an output parameter. </p>
  <p> Click <strong>Add Output</strong> to add more parameters. The button applicable for selected Actor types only.</p>
</td>
</tr>
</tbody>
</table>




## Actor's Inputs and Outputs

### Input Parameter Properties

The Actor's input parameters are displayed in the Actor's Properties window in the following format:
- [Parameter Name] : [Parameter Type]

For example:
- interface : string

[Click for more information about Broadway data types](05_data_types.md#data-types-in-broadway).

The population of the input parameter depends on the **Population Type**. The following types are supported:
- **Link**, gets the input value as an input parameter from another Actor. When **Link** is selected, it connects the parameter to another Actor's output parameter.
- **Const**, sets the value of the parameter. When **Const** is selected, a text area opens under the parameter to enable populating the parameter's value.
- **External**, gets the input value as a parameter from the external process which executes the Broadway flow. When External is selected, the **External Name** field opens under the parameter.

<table>
<tbody>
<tr>
<td valign="top" ><img src="images/99_03_inputs_link.PNG" alt="Input Link" /></td>
<td valign="top" ><img src="images/99_03_inputs_const.PNG" alt="Input Const" /></td>
<td valign="top" ><img src="images/99_03_inputs_external.PNG" alt="Input External" /></td>
</tr>
</tbody>
</table>

The actions on the input parameters are:
- Default value, set a default value for an input parameter (for **Link** and **External** population types). Broadway enables populating a value that matches the parameters type. For example: when the parameter is Integer, Broadway only enables inserting numbers into this parameter.

- Edit Schema, click to open the popup window where you can update the parameter's type.

- Description, click to check the parameter's description. The description cannot be updated.

- Remark,  click to add a remark. A remark can be modified.

- Mandatory, click to set the input parameter mandatory (starting Fabric 6.5.3). This indication can be  used by external systems that are calling the **LIST BF** command in a context of a specific flow.

  **Syntax**: 
    ~~~~
	LIST BF LU_NAME=<'LU Name'> FLOW=<'BF FLOW'>;
    ~~~~
  
  **Example**:
    ~~~~
        fabric>list bf lu_name='Customer' flow='broadwayOverview';

        |param|name  |type|schema|mandatory|isDefault|
        +-----+------+----+------+---------+---------+
        |input|params|any |{}    |false    |false    |
    ~~~~

  ​

Note that when populating an input value for an **SQL** parameter, click **QB** to open the [Query Builder window](/articles/11_query_builder/02_query_builder_window.md) to validate the SQL query as displayed in the following **DbCommand** built-in Actor example:

![image](images/99_03_sql.PNG)

### Add Input Button

Some Actors such as JavaScript and DbCommand, enable adding input parameters using an **Add Input** button. A new input parameter has the following default settings:

- Parameter Type = Any.
- Population Type = Link.

If the population type is updated to a **Const**, select the parameter type from the dropdown list.

To delete or add more parameters, in the context menu click ![image](images/99_19_dots.PNG) > Delete or Insert.

Note that the input parameters of the ancestor Actor cannot be deleted.

### Output Parameters Properties

![Sql_input](images/99_03_outputs.PNG)

The Actor's output parameters are displayed in the Actor's Properties window in the following format:

- [Parameter Name] : [Parameter Type]

The actions in the output parameters are:
- Set External, click to expose the output parameter to the external process that executes the Broadway flow. When selected, the **External Name** field opens under the parameter.
- Edit Schema, click to open the popup window where you can update the parameter's type.
- Description, click to check the parameter's description. The description cannot be updated.
- Remark, click to add a remark. The remark can be modified.

### Add Output Button

Some Actors, such as InnerFlow, enable adding output parameters using an **Add Output** button. A new output parameter is added with the default Parameter Type = Any.

To delete or add more parameters, in the context menu click ![image](images/99_19_dots.PNG) > Delete or Insert.

Note that the output parameters of the ancestor Actor cannot be deleted.

[![Previous](/articles/images/Previous.png)](03_broadway_actor.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_built_in_actor_types.md)
