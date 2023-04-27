# Trace Commands

### Trace ops/operations

Get a list of available operations for the Trace command in the related category and default tracing level. 

For example:

```
trace ops;
```



<table class="unchanged rich-diff-level-one">
<thead>
<tr>
<th>Operation</th>
<th>Category</th>
<th>base_level</th>
</tr>
</thead>
<tbody>
<tr>
<td>sqlCommand</td>
<td>database</td>
<td>info</td>
</tr>
<tr>
<td>saveLuToCassandra</td>
<td>database</td>
<td>info</td>
</tr>
<tr>
<td>batchInsert</td>
<td>database</td>
<td>info</td>
</tr>
<tr>
<td>fabricPrepareCommand</td>
<td>commands</td>
<td>info</td>
</tr>
<tr>
<td>fabricCommand</td>
<td>commands</td>
<td>info</td>
</tr>
<tr>
<td>deploy</td>
<td>commands</td>
<td>info</td>
</tr>
<tr>
<td>createLuType</td>
<td>commands</td>
<td>info</td>
</tr>
<tr>
<td>jobExecuter</td>
<td>jobs</td>
<td>info</td>
</tr>
<tr>
<td>folderPoller</td>
<td>jobs</td>
<td>debug</td>
</tr>
<tr>
<td>sftpReader</td>
<td>jobs</td>
<td>debug</td>
</tr>
<tr>
<td>jobsReconcile</td>
<td>jobs</td>
<td>debug</td>
</tr>
<tr>
<td>jobsScheduler</td>
<td>jobs</td>
<td>debug</td>
</tr>
<tr>
<td>jobsPoolExecutorGet</td>
<td>jobs</td>
<td>debug</td>
</tr>
<tr>
<td>jobsPoolExecutorSubmit</td>
<td>jobs</td>
<td>debug</td>
</tr>
<tr>
<td>webservice</td>
<td>solutions</td>
<td>info</td>
</tr>
<tr>
<td>sync</td>
<td>solutions</td>
<td>info</td>
</tr>
<tr>
<td>parser</td>
<td>solutions</td>
<td>info</td>
</tr>
<tr>
<td>graphit</td>
<td>solutions</td>
<td>info</td>
</tr>
<tr>
<td>custom</td>
<td>solutions</td>
<td>info</td>
</tr>
<tr>
<td>parallelYield</td>
<td>yield</td>
<td>debug</td>
</tr>
<tr>
<td>scopeCommand</td>
<td>commands</td>
<td>info</td>
</tr>
<tr>
<td>broadway</td>
<td>broadway</td>
<td>info</td>
</tr>
<tr>
<td>broadwayLoops</td>
<td>broadway</td>
<td>info</td>
</tr>
<tr>
<td>broadwayData</td>
<td>broadway</td>
<td>info</td>
</tr>
</tbody>
</table>

### Create a New Trace 

**Syntax:**

```
trace [session_scope/global_scope] <TRACE_NAME> '[TRACE_PARAM=[=TRACE_VALUES]];...';
```



<table class="unchanged rich-diff-level-one">
<thead>
<tr>
<th>Parameter</th>
<th>&nbsp;</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>session_scope/global_scope</td>
<td>&nbsp;</td>
<td>Optional: Set if the trace is on the current session only or is global. Default is global_scope.</td>
</tr>
<tr>
<td>TRACE NAME</td>
<td>&nbsp;</td>
<td>An alpha numeric value (cannot use a reserved sql keyword).</td>
</tr>
<tr>
<td>TRACE PARAM=TRACE VALUES</td>
<td>&nbsp;</td>
<td>All TRACE _PARAM are optional and are one of the following:</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>operations</td>
<td>Operations to monitor. Default is none.</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>resources</td>
<td>Resources to filter. Default is no filter, all resources under an operation.</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>level</td>
<td>Level to use: debug, verbose, info, warning or error. Default is info.</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>categories</td>
<td>Shortcut that turns on all operations under a category.</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>syncWrite</td>
<td>Perform synchronous writes. Default is async write to files.</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>maxCols</td>
<td>The number of columns to write using the traced data. Default is 10.</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>maxColLen</td>
<td>The number of bytes to write for each column. Default is 256.</td>
</tr>
</tbody>
</table>
**Example**

~~~
trace session_scope Test 'level=debug;categories=database;resources=billing,crm;operations=connection;syncWrite;maxCols=3;maxColLen=128;';
~~~

### Get Trace Status

To get all global traces and current session traces, run:

~~~
trace status;
~~~

To get a specific trace name status, run:

~~~
trace status <Trace_name>;
~~~

### Stop a Trace

To stop all global traces and the current session's traces, run:

~~~
trace off;
~~~

To stop a specific trace name, run:

~~~
trace off <Trace_name>;
~~~

### Delete a Trace

To stop the trace if it is on and delete the trace files, run.

~~~
trace remove <Trace_name>;
~~~



[![Previous](/articles/images/Previous.png)](01_tracing_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_trace_examples.md) 



