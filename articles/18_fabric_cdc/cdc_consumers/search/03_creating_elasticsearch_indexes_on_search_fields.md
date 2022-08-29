# Creating Indexes on Search Fields

Fabric creates a separate index in the Search provider (Elasticsearch or OpenSearch) on each LU table that has Search fields.

Search indexes are created after a [CDC Schema](/articles/18_fabric_cdc/03_cdc_messages.md#cdc-schema) message or a [CDC Schema Update](/articles/18_fabric_cdc/03_cdc_messages.md#cdc-schema-update) message when a Search field is added on a new LU table. 

The following displays mapping of Fabric LU Search fields and Search indexes:

<table width="900pxl">
<tbody>
<tr>
<td width="450pxl" valign="top">
<p><strong>Fabric</strong></p>
</td>
<td width="450pxl" valign="top">
<p><strong>Elasticsearch</strong></p>
</td>
</tr>
<tr>
    <td width="450pxl" valign="top">
        <p>LU table with at least one Search field</p>
    </td>
    <td width="450pxl" valign="top">
        <p>Index. The index name is [LU Name]_[Cluster_id]_[LU Table].</p>
    </td>
    </tr>
    <tr>
        <td width="450pxl" valign="top">
            <p>Search field (LU table column)</p>
        </td>
        <td width="450pxl" valign="top">
            <p>Column of the Index</p>
        </td>
    </tr>
    <tr>
        <td width="450pxl" valign="top">
         <p>LU table record (LU table data)</p>
        </td>
          <td width="450pxl" valign="top">
              <p>Document</p>
        </td>
    </tr>
    </tbody>
</table>
### Example:

- Define Search fields on the ADDRESS LU table of the Customer LU. Define the following table columns as Search fields:
  - STREET
  - CITY
  - STATE
  - COUNTRY
  - ZIP_CODE

- Deploy the Customer LU. 
- Fabric creates an search index for the ADDRESS LU table. The fields below are set as the **Columns** of the index.
- Sync Customer 123 into Fabric. This customer has 3 ADDRESS records.
- The [CDC Table Change Info](/articles/18_fabric_cdc/03_cdc_messages.md#cdc-table-change-info) message initiates an update of the search index:
  - Save the data of the Search fields on each ADDRESS record of Customer 1. Each ADDRESS record creates a separate document in the search index.



[![Previous](/articles/images/Previous.png)](02_search_implementation.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_search_templates.md)
