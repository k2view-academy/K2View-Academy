# Translations Overview

### What is a Translation?
A Translation is a Fabric Studio object that transforms data from one set of valid values to another in order to enable the execution of various transformation rules. Translation objects can be used as decision tables in Fabric and can be defined either on a [Shared Objects](https://github.com/k2view-academy/K2View-Academy/blob/3ecdb4c58cf1eac320138b514c8399aa895d02ee/articles/04_general/12_shared_objects.md) level or on a [Logical Unit](https://github.com/k2view-academy/K2View-Academy/blob/3ecdb4c58cf1eac320138b514c8399aa895d02ee/articles/03_logical_units/01_LU_overview.md) level or both. 
*	Translations defined on a Shared Objects level can be used in all objects in a Project. 
*	When a Translation is used for a [Web Service] it must be defined on a Shared Objects level.
There are several options for populating data in a Translation in the Fabric Studio. 
*	Manual population via the Translation window. 
*	Retrieving the Translation’s data from a file. The data is loaded from the file only once.
*	Retrieving the Translation’s data from a database to create a Dynamic Translation. The data is loaded from the database during each Synch process.

[Click for more information about Data Population in Translations.]  


### How Can I Use a Translation in Fabric?
A Translation can be used when a [Table Population](https://github.com/k2view-academy/K2View-Academy/blob/3ecdb4c58cf1eac320138b514c8399aa895d02ee/articles/07_table_population/01_table_population_overview.md)  or [Project function](https://github.com/k2view-academy/K2View-Academy/blob/3ecdb4c58cf1eac320138b514c8399aa895d02ee/articles/07_table_population/08_project_functions.md) needs to apply transformation rules.
For example:
*	When there are several source systems, each having a list of valid values for the Customer Type attribute. The Translation defines one unified set of valid values for the target (Fabric) and transformation rules from the Input value (source) to the Output value (target).
*	When there are several DB interfaces , the Translation can map between the system name and its [DB interface](https://github.com/k2view-academy/K2View-Academy/blob/3ecdb4c58cf1eac320138b514c8399aa895d02ee/articles/05_DB_interfaces/01_interfaces_overview.md) name to be used in the code.
*	When there is a list of attributes or business rules in the LU, the Translation can define an SQL query for each attribute or business rule.

### Translation Window
Translations are defined via the Translation window which has two tabs:
*	[Translation Schema] tab, defines the Translation Schema properties. Each column in a Translation Schema must be either Input or Output and can have different data types.
*	[Translation Data] tab, which is populated by the Translation’s values in the Input and Output fields. The data can be populated in a Translation in several ways.

*	[Click for more information about Data Population in Translations.]

*Translation Schema Tab Icons*
The following icons are displayed at the top of the Translation Schema tab:
<p>&nbsp;</p>
<table>
<tbody>
<tr>
<td width="56"><img src=
<p><strong>Icon</strong></p>
</td>
<td width="446">
<p><strong>Action</strong></p>
</td>
</tr>
<tr>
<td width="56">&nbsp;</td>
<td width="446">
<p>Print translation definition.</p>
</td>
</tr>
<tr>
<td width="56">&nbsp;</td>
<td width="446">
<p>Export translation definition as an Excel file.</p>
</td>
</tr>
<tr>
<td width="56">&nbsp;</td>
<td width="446">
<p>Filter definition by one or more columns.</p>
</td>
</tr>
<tr>
<td width="56">&nbsp;</td>
<td width="446">
<p>Toggle summaries.</p>
</td>
</tr>
<tr>
<td width="56">&nbsp;</td>
<td width="446">
<p>Toggle groupings.</p>
</td>
</tr>
</tbody>
</table>


*Translation Options*
The following options define system behavior when a matching Translation is not found. Select an option from the dropdown list on the bottom of the Translation Schema tab. The Use Default option is always defined when a new Translation is created and can be modified when needed.
<table>
<tbody>
<tr>
<td width="156">
<p><strong>Option</strong></p>
</td>
<td width="420">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td width="156">
<p>Use Default</p>
</td>
<td width="420">
<p>Use the value defined in the <strong>Default Value</strong> column in the Translation Schema tab.</p>
</td>
</tr>
<tr>
<td width="156">
<p>Reject Record</p>
</td>
<td width="420">
<p>Rejects a single record.</p>
</td>
</tr>
<tr>
<td width="156">
<p>Reject Instance</p>
</td>
<td width="420">
<p>Rejects the entire instance.</p>
</td>
</tr>
<tr>
<td width="156">
<p>Report and Use Default</p>
</td>
<td width="420">
<p>Reports the missing value and uses the default value.</p>
</td>
</tr>
</tbody>
</table>
<p><strong>&nbsp;</strong></p>

### Translation Schema
The structure of a Translation is defined in the Translation Schema tab and is comprised of Input and Output fields: 
*	There can be one or more than one Input field. 
*	A Translation’s Primary Key is a combination of all Translation Input fields. The Primary Key defines which Translation entries are used to apply the data transformation rules and therefore it must be unique.   
*	An Output value does not need to be unique and can be repeated. 
A Translation Schema has the following structure: 
<table width="614">
<tbody>
<tr>
<td width="141">
<p><strong>Parameter Property</strong></p>
</td>
<td width="472">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td width="141">
<p><strong>Title</strong></p>
</td>
<td width="472">
<p>Name of the parameter.</p>
</td>
</tr>
<tr>
<td width="141">
<p><strong>Direction</strong></p>
</td>
<td width="472">
<p>Indicates whether a parameter is <strong>Input</strong> or <strong>Output</strong>.</p>
</td>
</tr>
<tr>
<td width="141">
<p><strong>Type</strong></p>
</td>
<td width="472">
<p>Value type. The following types are supported:</p>
<ul>
<li>Integer.</li>
<li>Real.</li>
<li>Text.</li>
<li>Blob (applicable only for a DB that supports Blob).</li>
<li>Auto increment. Creates an automatic sequence starting from 1 and incremented by 1. The value cannot be updated by the user.</li>
<li>Project Function. Fabric provides a list of all functions available in the project. The value must be selected from the dropdown list.</li>
<li>LUDB Function, applicable for Translations on LU levels only. Fabric provides a list of LUDB functions available in the LU. The value must be selected from the dropdown list.</li>
<li>LUDB Table, applicable for Translations on LU levels only. Fabric provides a list of LU Tables available in the LU. The value must be selected from the dropdown list.</li>
<li>DB Interface. Fabric provides a list of Interfaces existing in the project. The value must be selected from the dropdown list.</li>
<li>SQL. Fabric enables testing the SQL query by opening a Query Builder from the Translation Schema window.</li>
<li>LU Name, applicable for Translations on a Shared Objects level only. Fabric provides a list of all LU available in the Project. The value must be selected from the dropdown list.</li>
</ul>
</td>
</tr>
<tr>
<td width="141">
<p><strong>Length</strong></p>
</td>
<td width="472">
<p>Field length, max number of characters. Populated automatically for several types, for example Project function. Ignored for Blob and Auto increment types.</p>
</td>
</tr>
<tr>
<td width="141">
<p><strong>Match</strong></p>
</td>
<td width="472">
<p>Optional: A regular expression that indicates a restricted format for each value and is applied for manual Translation population only. Ignored for Blob and Auto increment types.</p>
</td>
</tr>
<tr>
<td width="141">
<p><strong>Default Value</strong></p>
</td>
<td width="472">
<p>Default value used when no entry is found (relevant for an Output setting).</p>
</td>
</tr>
<tr>
<td width="141">
<p><strong>Allow NULL</strong></p>
</td>
<td width="472">
<p>True / False indicates whether the setting can be NULL. Default is True.</p>
</td>
</tr>
<tr>
<td width="141">
<p><strong>Comment</strong></p>
</td>
<td width="472">
<p>Optional: Additional information.</p>
</td>
</tr>
</tbody>
</table>

*Note* that all Translation Schema constraints are only applied in the Fabric Studio. The Fabric Server uses the data only and not the Schema’s information.
The following icons are displayed next to each Translation setting:
<table>
<tbody>
<tr>
<td width="85">
<p><strong>Icon</strong></p>
</td>
<td width="417">
<p><strong>Action</strong></p>
</td>
</tr>
<tr>
<td width="85">&nbsp;</td>
<td width="417">
<p>Deletes the Translation row.</p>
</td>
</tr>
<tr>
<td width="85">&nbsp;</td>
<td width="417">
<p>Moves the Translation row up.</p>
</td>
</tr>
<tr>
<td width="85">&nbsp;</td>
<td width="417">
<p>Moves the Translation row down.</p>
</td>
</tr>
</tbody>
</table>
<p>&nbsp;</p>


