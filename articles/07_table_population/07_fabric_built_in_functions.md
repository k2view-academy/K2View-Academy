# Fabric Built-in Functions

Fabric offers several **Built-in functions** which can be used for different types of data transformation. Built-in functions can be either connected to other Fabric objects in a map (Table Population or Parser) or invoked from another function by Java coding.  
Built-in functions are grouped into different categories.  The following table displays a list of categories and examples for each category.

Click to display the full Fabric API list: http://[Fabric IP address]:3213/static/doc/user-api/index.html

<table style="width: 528px;">
<tbody>
<tr>
<td style="width: 97px;">
<p><strong>Date</strong></p>
</td>
<td style="width: 418px;">
<ul>
<li>k2_breakDate &ndash; break the given date into year, month, day, etc.</li>
<li>k2_currentDate &ndash; return the current date in "yyyy/MM/dd" format.</li>
</ul>
</td>
</tr>
<tr>
<td style="width: 97px;">
<p><strong>FileSystem</strong></p>
</td>
<td style="width: 418px;">
<ul>
<li>k2_find_files &ndash; return the list of all files matching a regular expression in a specific path.</li>
</ul>
</td>
</tr>
<tr>
<td style="width: 97px;">
<p><strong>Math</strong></p>
</td>
<td style="width: 418px;">
<p>Functions that perform various mathematical manipulations. For example:</p>
<ul>
<li>k2_multiply &ndash; multiply two numbers and returns the result.</li>
<li>k2_round &ndash; round an input value to a specified precision.</li>
</ul>
</td>
</tr>
<tr>
<td style="width: 97px;">
<p><strong>String</strong></p>
</td>
<td style="width: 418px;">
<p>Functions that perform manipulations on strings. For example:</p>
<ul>
<li>k2_concat5 &ndash; concatenate up to 5 strings into 1 string using the provided delimiter.</li>
<li>k2_ltrim &ndash; strip whitespace (or other characters) from the beginning of a string.</li>
</ul>
</td>
</tr>
<tr>
<td style="width: 97px;">
<p><strong>Utilities</strong></p>
</td>
<td style="width: 418px;">
<ul>
<li>k2_IF &ndash; check a condition and set a value when True or False.</li>
</ul>
</td>
</tr>
</tbody>
</table>


[![Previous](/articles/images/Previous.png)](/articles/07_table_population/06_table_population_transformation_rules.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/07_table_population/08_project_functions.md)
