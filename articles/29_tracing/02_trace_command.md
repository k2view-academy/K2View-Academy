# Trace Commands

### Trace ops/operations

Get a list of available operations for the Trace command in the related category and default tracing level. 

For example:

trace ops;

| Operation              | Category  | base_level |
| ---------------------- | --------- | ---------- |
| sqlCommand             | database  | info       |
| saveLuToCassandra      | database  | info       |
| batchInsert            | database  | info       |
| fabricPrepareCommand   | commands  | info       |
| fabricCommand          | commands  | info       |
| deploy                 | commands  | info       |
| createLuType           | commands  | info       |
| jobExecuter            | jobs      | info       |
| folderPoller           | jobs      | debug      |
| sftpReader             | jobs      | debug      |
| jobsReconcile          | jobs      | debug      |
| jobsScheduler          | jobs      | debug      |
| jobsPoolExecutorGet    | jobs      | debug      |
| jobsPoolExecutorSubmit | jobs      | debug      |
| webservice             | solutions | info       |
| sync                   | solutions | info       |
| parser                 | solutions | info       |
| graphit                | solutions | info       |
| custom                 | solutions | info       |
| parallelYield          | yield     | debug      |
| scopeCommand           | commands  | info       |
| broadway               | broadway  | info       |
| broadwayLoops          | broadway  | info       |
| broadwayData           | broadway  | info       |

### Create a New Trace 

trace [session_scope/global_scope] <TRACE_NAME> '[TRACE_PARAM=[=TRACE_VALUES]];...';

| parameter                  |            | Description                                                  |
| -------------------------- | ---------- | ------------------------------------------------------------ |
| session_scope/global_scope |            | Optional: Set if the trace is on the current session only or is global. Default is global_scope.  |
| TRACE NAME                 |            | An alpha numeric value (cannot use a reserved sql keyword). |
| TRACE PARAM=TRACE VALUES   |            | All TRACE _PARAM are optional and are one of the following:   |
|                            | operations | Operations to monitor. Default is none.                 |
|                            | resources  | Resources to filter. Default is no filter, all resources under an operation. |
|                            | level      | Level to use: debug, verbose, info, warning or error. Default is info. |
|                            | categories | Shortcut that turns on all operations under a category.          |
|                            | syncWrite  | Perform synchronous writes. Default is async write to files.  |
|                            | maxCols    | The number of columns to write using the traced data. Default is 10. |
|                            | maxColLen  | The number of bytes to write for each column. Default is 256.   |

example: trace session_scope Test 'level=debug;categories=database;resources=billing,crm;operations=connection;syncWrite;maxCols=3;maxColLen=128;';

### Get Trace Status

To get all global traces and current session traces, run:

trace status;

To get a specific trace name status, run:

trace status <Trace_name>;

### Stop a Trace

To stop all global traces and the current session's traces, run:

trace off;

To stop a specific trace name, run:

trace off <Trace_name>;

### Delete a trace

To stop the trace if it is on and delete the trace files, run.

trace remove <Trace_name>;



[![Previous](/articles/images/Previous.png)](01_tracing_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_trace_examples.md) 



