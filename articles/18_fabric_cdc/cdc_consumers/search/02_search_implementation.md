# Search Implementation Steps

## Creating a Search Engine Interface 

The Search Engine is used to connect Fabric to the Elasticsearch engine when running Search commands. During a search, the Search Engine's interface is populated with the Elasticsearch engine's connection details.  


To  create a new Search Engine interface:

1. Go to **Project Tree** > **Shared Objects**, right click **Interfaces** and select **New Interface**.
2. Select **Search Engine** from the dropdown menu.
3. Populate the connection settings and click **Save**. 
   * Note that the interface name must be **search** (in small letters).

### Search Engine Interface Settings

<table>
<tbody>
<tr>
<td valign="top" width="200pxl">
<p><strong>Parameter </strong></p>
</td>
<td valign="top" width="700pxl">
<p><strong>Description </strong></p>
</td>
</tr>
<tr>
<td valign="top" width="200pxl">
<p>Host(s)</p>
</td>
<td valign="top" width="700pxl">
<p>Comma-delimited list of Elasticsearch server hosts and ports.</p>
</td>
</tr>
<tr>
<td valign="top" width="200pxl">
<p>User</p>
</td>
<td valign="top" width="700pxl">
<p>User Id</p>
</td>
</tr>
<tr>
<td valign="top" width="200pxl">
<p>Password</p>
</td>
<td valign="top" width="700pxl">
<p>User Password</p>
</td>
</tr>
<tr>
<td valign="top" width="200pxl">
<p>SSL</p>
</td>
<td valign="top" width="700pxl">
<p>By default, set to False.</p>
</td>
</tr>
</tbody>
</table>

## Fabric Studio - Defining Search Fields

Search fields must be defined on the selected LU table's columns to enable a cross-instance search based on these columns. For example, to enable a search of all customers named "John Doe", define the FIRST_NAME and LAST_NAME columns of the customer schema as search indexes.

To create a search index on a column, do either: 

- Right click the selected column > **Create Search Index From Selected Columns** > select the index type.
- Open the **Search** tab and populate the **Column Name** and **Type**. 

Note that Fabric Studio does not enable defining more than 63 columns in the same LU table as CDC fields, assuming that all columns are positioned according to 1 to 63 in the LU table. This limitation is not valid from release 6.5.4 and on.

### Search Field Types

The default built-in types of Search fields are:

- **keyword**, enables a search by this column. In the background Fabric creates two search indexes for the keyword field, a keyword index and an additional index based on the type of field. The keyword index enables searching for an exact match (case sensitive) of the searched value. 
- **date**, enables a search on a date column.  Date fields must be populated by a date format identified by Elasticsearch. Click to see the list of the date formats supported by Elasticsearch:
  * [https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping-date-format.html#built-in-date-formats](https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping-date-format.html#built-in-date-formats)
- **data**, can be returned by the search, a search cannot be initiated by this column.
- [search templates](04_search_templates.md), Fabric supports adding templates for Search fields to support specific cases.

**Example:** 

- Set the search indexes of the Customer LU as follows:
  - First Name and Last Name are keyword fields.
  - Customer ID is a data field.
- Run a search to get the list of all customer IDs called “John Doe”. 



[![Previous](/articles/images/Previous.png)](01_search_overview_and_use_cases.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_creating_elasticsearch_indexes_on_search_fields.md)
