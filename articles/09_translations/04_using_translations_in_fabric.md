<studio>

# Using Translations in Fabric

Translations can be used in the [Table Population](/articles/07_table_population/01_table_population_overview.md) of an [LU table](/articles/06_LU_tables/01_LU_tables_overview.md), a Reference table or a Parser Map when a specific set of Data Transformation rules is required. Translations can also be used in any [Fabric Project functions](/articles/07_table_population/08_project_functions.md) or [Web Services](/articles/15_web_services_and_graphit/01_web_services_overview.md).

[Click for more information about Translations – Code Examples.](/articles/09_translations/05_translations_code_examples.md)

### How Do I Add a Translation to a Table Population?

1.	Go to **Project Tree** > **Logical Units** > **[LU Name]**. 
2.	Click **Tables** > **[Table Name]** > **[Table Population]**
3.	Click the **Objects** tab in the right panel of the **Table Population** working area.
4.	Click **Translations** and then click the **Translation** in the list. The list includes the Translation objects defined under the selected LU and under the Shared Objects.
5.	Drag the **Translation** to the working area.
6.	Connect the **Translation’s Input** and **Output** fields to the **Table Columns**, as follows:
       * Translation **Input** fields can be either connected to the **Source Object** or other Fabric objects, attached to the **Table Population** like functions or Lookups.
       * Translation **Output** fields can be connected either to the **Target Object** or other Fabric objects, attached to the **Table Population** like functions or Lookups. 


[Click for more information about Creating a New Translation.](/articles/09_translations/02_creating_a_new_translation_in_fabric.md)

[Click for more information about Table Population Transformation Objects.](/articles/07_table_population/06_table_population_transformation_rules.md)


## How Do I Add a Translation to a Parser Map?

1.	Go to **Project Tree** > **Logical Units** > **[LU Name]**. 
2.	Click **Parsers** > **[Parser Map]**.
3.	Click the **Objects** tab in the right panel of the **Parser Map** working area.
4.	Click **Translations** and then click the **Translation** in the list.
5.	Drag the **Translation** into the working area.
6.	Connect the Translation’s **Input** and the **Output** fields.
       * Translation **Input** fields can be either connected to the **Source Object**, other Fabric objects or attached to the **map** like functions or lookups.
       * Translation **Output** fields can be connected either to the **Target Object,** other Fabric objects or attached to the **map** like functions or lookups. 


## How Do I Use a Translation in a Java File in Fabric?

Translations can be used in any [Project functions](/articles/07_table_population/08_project_functions.md) defined in Fabric. 
* Translations used in an LU function can be defined either on an [Logical Unit](/articles/03_logical_units/01_LU_overview.md) or on a [Shared Objects](/articles/04_fabric_studio/12_shared_objects.md) level.
* Translations used in [Web Services](/articles/15_web_services_and_graphit/01_web_services_overview.md) must be defined on a Shared Objects level.
When editing a Java file, two built-in functions are available and displayed in the **Objects** tab.

<table>
<tbody>
<tr>
<td width="150pxl">
<p><strong>Built-in Function</strong></p>
</td>
<td colspan="2" width="750pxl">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td rowspan="4" width="150pxl">
<p><strong>getTranslationValues</strong></p>
</td>
<td width="150pxl">
<p><strong>Format</strong></p>
</td>
<td width="600pxl">
<p>Map &lt;String&gt;&lt;String&gt;(String translationName, Object[] inputs)</p>
</td>
</tr>
<tr>
<td width="84">
<p><strong>Description</strong></p>
</td>
<td width="377">
<p>Returns the Translation&rsquo;s Output parameters based on the Input parameter/s.</p>
</td>
</tr>
<tr>
<td width="84">
<p><strong>Input</strong></p>
</td>
<td width="377">
<p>Translation input fields.</p>
</td>
</tr>
<tr>
<td width="84">
<p><strong>Output</strong></p>
</td>
<td width="377">
<p>Translation output fields.</p>
</td>
</tr>
<tr>
<td rowspan="4" width="143">
<p><strong>getTranslationsData</strong></p>
</td>
<td width="84">
<p><strong>Format</strong></p>
</td>
<td width="377">
<p>Map&lt;String,Map&lt;String,String&gt;&gt;(String translationName)</p>
</td>
</tr>
<tr>
<td width="84">
<p><strong>Description</strong></p>
</td>
<td width="377">
<p>Returns full Translation data based on the Translation name.</p>
</td>
</tr>
<tr>
<td width="84">
<p><strong>Input</strong></p>
</td>
<td width="377">
<p>Translation name.</p>
</td>
</tr>
<tr>
<td width="84">
<p><strong>Output</strong></p>
</td>
<td width="377">
<p>Translation data.</p>
</td>
</tr>
</tbody>
</table>

[Click for more information about Translations – Code Examples.](/articles/09_translations/05_translations_code_examples.md)

[![Previous](/articles/images/Previous.png)](/articles/09_translations/03_data_population_in_a_translation.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/09_translations/05_translations_code_examples.md)

</studio>
