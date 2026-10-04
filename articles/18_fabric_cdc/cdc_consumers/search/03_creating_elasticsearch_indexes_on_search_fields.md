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


## Customizing Index Settings

Starting from Fabric V8.5.2, you can customize the settings of the indexes that Fabric creates in the Search provider, such as the number of shards or replicas, using JSON settings files. This lets you deploy a different index configuration to each environment, for example, by mounting the files as ConfigMaps in Kubernetes.

### Settings File Locations

Settings files are saved under the **$FABRIC_HOME/config/cdc-tags** directory:

- **Root settings file** - a JSON file saved directly under **cdc-tags**. Its settings apply to all environments.
- **Environment settings file** - a JSON file with the **same file name**, saved under **cdc-tags/[environment name]**. Its settings apply only to that environment.

You can define a separate settings file for each index.

For example:

~~~
$FABRIC_HOME/config/cdc-tags/
    <settings file>.json              <- root settings
    DEV/<settings file>.json          <- DEV environment settings
    PROD/<settings file>.json         <- PROD environment settings
~~~

### Merging the Settings

When Fabric sends an index creation or update message, for example, following a [CDC_REPUBLISH_SCHEMA](/articles/18_fabric_cdc/04_cdc_publication_flow.md#cdc_republish_schema) command, the settings files are merged into the message as follows:

- Attributes that exist in only one of the files (the root file or the environment file) are added to the merged settings.
- Attributes that exist in both files are taken from the environment file.
- The merged settings override the system default settings.

The precedence order is: **environment settings file** > **root settings file** > **system defaults**.

A missing settings file is ignored. If neither file exists, Fabric uses the system default settings.

Example:

- Root settings file:

  ~~~json
  {
    "number_of_shards": 3,
    "number_of_replicas": 1
  }
  ~~~

- PROD environment settings file:

  ~~~json
  {
    "number_of_replicas": 2,
    "refresh_interval": "30s"
  }
  ~~~

- Merged settings in the PROD environment:

  ~~~json
  {
    "number_of_shards": 3,
    "number_of_replicas": 2,
    "refresh_interval": "30s"
  }
  ~~~

### Notes

- Use the same settings file name in all environments. The operator is responsible for maintaining the content of each environment's file.
- The operator is responsible for verifying that the settings are compatible with an existing index. Some settings, such as the number of replicas, can be updated on an existing index. Other settings, such as the number of shards, cannot be changed after the index is created. To change them, reshard the index in the Search provider first, and then update the settings file.


[![Previous](/articles/images/Previous.png)](02_search_implementation.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_search_templates.md)
