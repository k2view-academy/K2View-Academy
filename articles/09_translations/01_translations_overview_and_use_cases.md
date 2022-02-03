# Translations Overview

<web>

A Translation is a Fabric Studio object used for transforming data in the Fabric .NET Studio. They don't exist in the Web Studio.

In the Web Studio, the data transformation rules are defined using various Actors that can be added to a Broadway population flow.

[Click for more information about the built-in actors.](/articles/19_Broadway/04_built_in_actor_types.md)

</web>



<studio>

### What Is a Translation?

A Translation is a Fabric Studio object that transforms data from one set of valid values to another in order to enable the execution of various transformation rules. Translation objects can be used as decision tables in Fabric and can be defined either on a [Shared Objects](/articles/04_fabric_studio/12_shared_objects.md) level or on a [Logical Unit](/articles/03_logical_units/01_LU_overview.md) level or both. 
*	Translations defined on a Shared Objects level can be used in all objects in a project. 
*	When a Translation is used for a [Web Services](/articles/15_web_services_and_graphit/01_web_services_overview.md) it must be defined on a Shared Objects level.  

There are several options for populating data in a Translation in the Fabric Studio: 
*	Manual population via the Translation window. 
*	Retrieving the Translation’s data from a file. The data is loaded from the file only once.
*	Retrieving the Translation’s data from a database to create a Dynamic Translation. The data is loaded from the database during each [Sync](/articles/01_fabric_overview/02_fabric_glossary.md#sync) process.

[Click for more information about Data Population in Translations.](/articles/09_translations/03_data_population_in_a_translation.md#data-population-in-a-translation)


### How Can I Use a Translation in Fabric?
A Translation can be used when a [Table Population](/articles/07_table_population/01_table_population_overview.md) or [Project function](/articles/07_table_population/08_project_functions.md) needs to apply transformation rules.
For example:
*	When there are several source systems, each having a list of valid values for the Customer Type attribute. The Translation defines one unified set of valid values for the target (Fabric) and transformation rules from the Input value (source) to the Output value (target).
*	When there are several DB interfaces, the Translation can map between the system name and its [DB interface](/articles/05_DB_interfaces/03_DB_interfaces_overview.md) name to be used in the code. 
*	When there is a list of attributes or business rules in the LU, the Translation can define an SQL query for each attribute or business rule.

### Translation Window
Translations are defined via the Translation window which has two tabs:
*	[Translation Schema tab](/articles/09_translations/01_translations_overview_and_use_cases.md#translation-schema), defines the Translation Schema properties. Each column in a Translation Schema must be either Input or Output and can have different data types.
*	[Translation Data tab](/articles/09_translations/03_data_population_in_a_translation.md#data-population-in-a-translation), which is populated by the Translation’s values in the Input and Output fields. The data can be populated in a Translation in several ways.

### Translation Schema Tab Icons
The following icons are displayed at the top of the **Translation Schema tab**:
<table>
<tbody>
<tr>
<td width="200pxl">
<p><strong>Icon</strong></p>
</td>
<td width="700pxl">
<p><strong>Action</strong></p>
</td>
</tr>
<tr>
<td width="56">&nbsp;&nbsp;<img src="/articles/09_translations/images/table_1.png"></td>
<td width="446">
<p>Print translation definition.</p>
</td>
</tr>
<tr>
<td width="56">&nbsp;&nbsp;<img src="/articles/09_translations/images/table_2.png"></td>
<td width="446">
<p>Export translation definition as an Excel file.</p>
</td>
</tr>
<tr>
<td width="56">&nbsp;&nbsp;<img src="/articles/09_translations/images/table_3.png"></td>
<td width="446">
<p>Filter definition by one or more columns.</p>
</td>
</tr>
<tr>
<td width="56">&nbsp;&nbsp;<img src="/articles/09_translations/images/table_4.png"></td>
<td width="446">
<p>Toggle summaries.</p>
</td>
</tr>
<tr>
<td width="56">&nbsp;&nbsp;<img src="/articles/09_translations/images/table_5.png"></td>
<td width="446">
<p>Toggle groupings.</p>
</td>
</tr>
</tbody>
</table>


### Translation Options
The following options define system behavior when a matching Translation is not found. Select an option from the dropdown list on the bottom of the **Translation Schema tab**. The Use Default option is always defined when a new Translation is created and can be modified when needed.
<table>
<tbody>
<tr>
<td width="200pxl">
<p><strong>Option</strong></p>
</td>
<td width="700pxl">
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

### Translation Schema
The structure of a Translation is defined in the Translation Schema tab and is comprised of Input and Output fields: 
*	There can be one or more than one Input field. 
*	A Translation’s Primary Key is a combination of all Translation Input fields. The Primary Key defines which Translation entries are used to apply the data transformation rules and therefore must be unique.   
*	An Output value does not need to be unique and can be repeated. 

A Translation Schema has the following structure: 
<table width="614">
<tbody>
<tr>
<td width="200pxl">
<p><strong>Parameter Property</strong></p>
</td>
<td width="700pxl">
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
<p>Parameter type. The following types are supported:</p>
<ul>
<li>Integer.</li>
<li>Real.</li>
<li>Text.</li>
<li>Blob (applicable only for a DB that supports Blob).</li>
<li>Auto increment. Creates an automatic sequence starting from 1 and incremented by 1. The value cannot be updated by the user.</li>
<li><a href="/articles/07_table_population/08_project_functions.md"> Project function</a>. Fabric provides a list of all functions available in the project. The value must be selected from the dropdown list.</li>
<li><a href="/articles/07_table_population/08_project_functions.md#ludb-function"> LUDB function</a>, applicable for Translations on LU levels only. Fabric provides a list of LUDB functions available in the LU. The value must be selected from the dropdown list.</li>
<li><a href="/articles/06_LU_tables/01_LU_tables_overview.md"> LUDB table</a>, applicable for Translations on LU levels only. Fabric provides a list of LU tables available in the LU. The value must be selected from the dropdown list.</li>
<li><a href="/articles/05_DB_interfaces/03_DB_interfaces_overview.md"> DB Interface</a>. Fabric provides a list of the Interfaces in the project. The value must be selected from the dropdown list.</li>
<li>SQL. Fabric enables testing the SQL query by opening a <a href="/articles/11_query_builder/01_query_builder_overview.md"> Query Builder</a> from the Table Schema window.</li>
<li><a href="/articles/03_logical_units/01_LU_overview.md "> LU Name</a>, applicable for Translations on a Shared Objects level only. Fabric provides a list of all LU available in the project. The value must be selected from the dropdown list.</li>
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
<p>Optional: A Regular expression that indicates a restricted format for each value and is applied for manual Translation population only. Ignored for Blob and Auto increment types.</p>
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

Note that all Translation Schema constraints are only applied in the Fabric Studio. The Fabric Server uses the data only and not the Schema’s information.
The following icons are displayed next to each Translation setting:
<table>
<tbody>
<tr>
<td width="200pxl">
<p><strong>Icon</strong></p>
</td>
<td width="700pxl">
<p><strong>Action</strong></p>
</td>
</tr>
<tr>
<td width="56">&nbsp;&nbsp;<img src="/articles/09_translations/images/table2_01.png"></td>
<td width="417">
<p>Deletes the Translation row.</p>
</td>
</tr>
<tr>
<td width="56">&nbsp;&nbsp;<img src="/articles/09_translations/images/table2_02.png"></td>
<td width="417">
<p>Moves the Translation row up.</p>
</td>
</tr>
<tr>
<td width="56">&nbsp;&nbsp;<img src="/articles/09_translations/images/table2_03.png"></td>
<td width="417">
<p>Moves the Translation row down.</p>
</td>
</tr>
</tbody>
</table>
<p>&nbsp;</p>


[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/09_translations/02_creating_a_new_translation_in_fabric.md)

</studio>

