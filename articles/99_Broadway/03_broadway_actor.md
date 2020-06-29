# Broadway Actors

## Actor Overview

Broadway **Actor** object executes a predefined activity in the **Flow**. The Actors are added to each Flow's **Stage**.

The **Actor** is a JAVA class which implements the **Actor Interface** and overrides the **action** method.

### Actor Types

#### Built-In Actors

Broadway contains [built-in Actors](Add a link) which address a vast range of activities and can be added to Broadway Flows: file handling, JSON handling, DB activities, String manipulations, etc..

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

#### Data Input and Output Parameters

Each Actor has input and output parameters.

Each parameter has the following attributes:

- Parameter Definition
- Parameter Value

<!-- Add explanation about parameters + actor remark (click ... on the Actor in the flow) -->

