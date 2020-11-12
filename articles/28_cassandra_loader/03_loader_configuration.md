# Cassandra Loader Configuration 

### Configuration Logic

The loader's configuration is set in the [config.ini](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#configini) file which includes the loader and session configuration sections. Each section includes configuration parameters, such as queue size, number of threads, etc. which can be updated during the loader's fine-tuning optimization process.

The loader execution modes are:

<table>
<tbody>
<tr>
<td width=250px>SINGLE</td>
<td width=650px>Default. Every query submitted to the loader is executed.</td>
</tr>
<tr>
<td>BATCH</td>
<td>The loader executes statements in batches, the batch size is set by the BATCH_SIZE property in the config.ini or iifConfig.ini.</td>
</tr>
<tr>
<td>TOKEN_AWARE_BATCH</td>
<td>The loader executes statements in batches. To improve performance, the statements are grouped into batches by a token. The batch size is set by the BATCH_SIZE property in the config.ini or iifConfig.ini.</td>
</tr>
</tbody>
</table>

Note that writing into the Cassandra DB can be disabled for testing purposes. This is controlled by the IS_NOP loader's configuration parameters.

### Work by Priority

Configuration of the loader is according to the following priority:

- Batch_process
  - Look for **[LU type]_batch_process_loader** section.
  - If not exist, user **batch_process_loader** section.
  - if not exist, use **default_loader** section.
- Parser
  - Look for **[LU type]_[parser name]_loader** section.
  - if not exist, use **parser_loader** section.
  - If not exist, user **default_loader** section.
- iidFinder
  - Look for **iid_finder_loader** section.
  - If not exist, use **default_loader** section.

The session's configuration is according to the following priority:

- Fabric internal
  - Use the **default_session** section.
- Batch_process
  - Look for **[LU type]_batch_process_session** section.
  - If not exist, use **batch_process_session** section.
  - If not exist, use **loader_session** section.
  - If not exist, use **default_session** section.
- Parser
  - Look for **[LU type]_[parser name]_session** section.
  - If not exist, use **parser_session** section.
  - If not exist, use **loader_session** section.
  - If not exist, use **default_session** section.
- iidFinder
  - Look for **iid_finder_session** section.
  - If not exist, use **loader_session** section.
  - If not exist, use **default_session** section.
    



[![Previous](/articles/images/Previous.png)](02_loader_architecture.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_loader_invocation.md) 

