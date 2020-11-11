# Cassandra Loader Configuration

### Configuration Logic

The loader configuration is set in the [config.ini](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#configini) file and it includes the loader and session configuration sections. Each section includes the configuration parameters, such as queue size, number of threads, etc. which can be updated during the loader's fine-tuning optimization process.

The loader execution modes are:

<table>
<tbody>
<tr>
<td width=250px>SINGLE</td>
<td width=650px>Default. Every query submitted to the loader will be executed.</td>
</tr>
<tr>
<td>BATCH</td>
<td>The loader will execute statements in batches, the batch size is set by the BATCH_SIZE property from config.ini or iifConfig.ini.</td>
</tr>
<tr>
<td>TOKEN_AWARE_BATCH</td>
<td>The loader will execute statements in batches, the statements will be grouped to batches by token to improve performance, the batch size is set by the BATCH_SIZE property from config.ini or iifConfig.ini.</td>
</tr>
</tbody>
</table>

Note that for the testing purpose it is possible to disable the writing into the Cassandra DB. It is controlled by the IS_NOP loader's configuration parameter.

### Work by Priority

The loader configuration works by the following priority:

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

The session configuration works by the following priority:

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

