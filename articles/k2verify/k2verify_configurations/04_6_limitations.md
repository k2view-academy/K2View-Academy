## Limitations

1. **Table-to-table comparison requires matching database types.** When comparing database tables, both the source and target must use the same database engine. Cross-database comparisons — for example, verifying an Oracle source against a PostgreSQL target — are not supported in this version.

2. **Only CSV files are supported for file-based comparison.** Other file formats are not supported at this time.

3. **Each CSV file must include a header row.** Verify uses the column names in the header to match fields between source and target. Files without a header row are not supported.

4. **CSV file names must match between source and target.** Verify expects each source file to have a counterpart with the same name in the target. If your target files use different names, you must provide a pre-execution Broadway flow to rename or remap them before Verify runs. See `Pre/Post execution flows` for how to set this up.