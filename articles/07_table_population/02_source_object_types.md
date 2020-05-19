# Table Population - Source Object Types

There are two types of **Source Objects** in a Table Population object:
*	**DB Query**, (default) an SQL Select query with one or several tables that is executed on a predefined DB interface . 
*	**Root Function**, a Fabric Java function that can execute an SQL Select query and perform data manipulations and calculations.

Note that a Table Population can extract data from other LU Tables  in the same Logical Unit. It is recommended to always check the execution order  of a source table’s population objects to verify that the source LU Tables are populated before the target LU Tables. For example, the SUBSCRIBER LU Table must be populated before the OFFER LU Table to enable populating the OFFER LU Table based on data from the SUBSCRIBER LU Table.

When an LU Table has two populations, a second table population can extract data from the same LU Table. For example, the ADDRESS LU Table has two table populations:
*	Population 1 extracts data from the CRM DB.
*	Population 2 extracts data from the ADDRESS LU Table that has been inserted by Population 1.

Click for more information about Creating Table Population Objects .

### DB Query Vs. Root Function Use Cases

<table width="606">
<tbody>
<tr>
<td width="153">
<p><strong>DB Query</strong></p>
</td>
<td width="413">
<p>A <strong>DB query</strong> is used when the data is selected from one data source only in one or several tables. All required data can be retrieved by one SQL query.</p>
</td>
</tr>
<tr>
<td width="95">
<p><strong>Root Function</strong></p>
</td>
<td width="511">
<p>A <strong>Root Function</strong> is needed when a Table Population requires complex logic.</p>
<p>Most common use cases are:</p>
<ol>
<li>A population requires data from multiple DB interfaces, however, the DB query cannot run the Join between different DB interfaces. For example, to retrieve a customer&rsquo;s Address ID from the CRM DB and to then retrieve full address details from the Address Repository DB.</li>
<li>When the population has complex logic that requires execution of several DB queries.</li>
<li>A population requires data from another LU. For example, to retrieve Customer details from the CRM LU to populate part of its data into the Billing LU.</li>
<li>When source data is extracted from a file or any other non-DB interface.</li>
<li>When using the Fabric Remote DB, a Table Population must use a root function to get the instance before running the SQL query on the LUI.</li>
<li>When the population needs to run a Fabric command. For example, setting the source environment&nbsp; to UAT so that data for a selected table is not extracted from Production to avoid overloading the source system. The root function can set the environment on a session level and select the data from the required source environment.</li>
</ol>
</td>
</tr>
</tbody>
</table>

### DB Query Vs. Root Function Comparison Analysis

The comparison below analyzes the differences and the similarities between two different source object types and provides insight on when each type should be used.

<table style="width: 580px;">
<thead>
<tr>
<td style="width: 153px;">
<p><strong>Category</strong></p>
</td>
<td style="width: 413px;">
<p><strong>DB Query</strong></p>
</td>
</tr>
</thead>
<tbody>
<tr>
<td style="width: 153px;">
<p><strong>Structure</strong></p>
</td>
<td style="width: 413px;">
<p>SQL query. All fields in a Select statement become query output.</p>
</td>
</tr>
<tr>
<td style="width: 153px;">
<p><strong>Execution time</strong></p>
</td>
<td style="width: 413px;">
<p>Upon table population.</p>
</td>
</tr>
<tr>
<td style="width: 153px;">
<p><strong>Content</strong></p>
</td>
<td style="width: 413px;">
<p>Execute the SQL query on one or several tables of a predefined DB interface.</p>
</td>
</tr>
<tr>
<td style="width: 153px;">
<p><strong>Access to LU tables</strong></p>
</td>
<td style="width: 413px;">
<p>Can access LU Tables.</p>
</td>
</tr>
<tr>
<td style="width: 153px;">
<p><strong>Automatic relation to the parent ID</strong></p>
</td>
<td style="width: 413px;">
<p>The DB query automatically filters the selected records based on the link to the parent LU TableThe Join process to the parent table is invisible in the DB query.</p>
<p>For example:</p>
<ul>
<li>The parent table of ADDRESS LU is CUSTOMER and the relationship is via CUSTOMER_ID.</li>
<li>The ADDRESS DB query selects the ADDRESS records that belong to the CUSTOMER_ID of each LUI.</li>
</ul>
</td>
</tr>
<tr>
<td style="width: 153px;">
<p><strong>Automatic binding of parameters</strong></p>
</td>
<td style="width: 413px;">
<p>The DB query has an automatic background functionality for preparing and binding the SQL statement that is sent to external interfaces.</p>
</td>
</tr>
<tr>
<td style="width: 153px;">
<p><strong>LUDB population performance</strong></p>
</td>
<td style="width: 413px;">
<p>The DB query has a Grouping mechanism that runs a SELECT statement for each group of 1,000 parent IDs. The formula for the number of times the parent data is accessed is: <strong>Round(number of parents ID/1,000) + 1</strong></p>
<p>For example:</p>
<p>There are 1,500 subscribers for Instance ID = 1. Each subscriber has services.</p>
<p>To select the subscriber&rsquo;s services, the DB Query runs two SELECT statements from the source DB:</p>
<ul>
<li>Query 1 on a group of 1,000 subscribers.</li>
<li>Query 2 on the remaining 500 subscribers.</li>
</ul>
</td>
</tr>
</tbody>
</table>



