# Search Solution - Limitations

- Each search can include only one LU.  
- A search can include several LU tables only if they have the same structure and the same list of Search fields. Therefore, to run a search on fields related to different LU tables, it is recommended to build an additional LU table holding all Search fields to be included into one Search command.
- The column included in the search must be defined in Fabric  in capital letters.
- The implementation must define the Search field as TEXT in the LU table to enable a fuzzy search, ignore spaces and case insensitive searches.
- A Date field must be populated in Fabric with a format supported by Elasticsearch. For example, ‘YYYY-MM-DD’.
- Non-English languages are currently not supported.
- The Reset drop command does not clean Elasticsearch. Run the [DROP LU](/articles/02_fabric_architecture/04_fabric_commands.md#drop-lu-command) command to clean each LU from  Elasticsearch.

Note that the Fabric Studio does not enable defining more than 63 columns as a CDC field in the same LU table, assuming that all columns are positioned according to 1 to 63 in the LU table. This limitation is not valid from release 6.5.4 and on.



[![Previous](/articles/images/Previous.png)](05_search_command.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](07_search_configuration.md)
