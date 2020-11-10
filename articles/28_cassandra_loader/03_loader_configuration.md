# Cassandra Loader Configuration

### Execution Modes

<table>
<tbody>
<tr>
<td width=250px>SINGLE</td>
<td width=650px>Every query submitted to the loader will be executed.</td>
</tr>
<tr>
<td>BATCH</td>
<td>The loader will execute statements in batches, the batch size is set by the BATCH_SIZE property from config.ini or iifConfig.ini.</td>
</tr>
<tr>
<td>TOKEN_AWARE_BATCH</td>
<td>The loader will execute statements in batches, the statements will be grouped to batches by token to improve performance, the batch size is set by the BATCH_SIZE property from config.ini or iifConfig.ini.</td>
</tr>
<tr>
<td>NOP</td>
<td>The loader doesn’t execute the statements and discards them, should be used only for testing.</td>
</tr>
</tbody>
</table>



### Configuration Properties

<table>
<tbody>
<tr>
<td><strong>Property Name</strong></td>
<td><strong>Description</strong></td>
<td><strong>Valid Values</strong></td>
<td><strong>Default Value</strong></td>
</tr>
<tr>
<td>&nbsp;PARSER_WRITING_TYPE</td>
<td>Writing method into Cassandra using the Cassandra loader or JDBC driver for parsers.</td>
<td>JDBC, LOADER</td>
<td>JDBC</td>
</tr>
<tr>
<td>&nbsp;EXECUTION_MODE</td>
<td>Loader execution mode.</td>
<td>
<p>SINGLE, BATCH, NOP, TOKEN_AWARE_BATCH</p>
</td>
<td>SINGLE</td>
</tr>
<tr>
<td>&nbsp;ASYNC</td>
<td>Statement execution mode - synchronous or asynchronous.</td>
<td>true, false</td>
<td>true</td>
</tr>
<tr>
<td>&nbsp;ENABLE_FAILED_ QUERIES_LOGGING</td>
<td>Enables query error logging when running the parser.</td>
<td>true, false</td>
<td>false</td>
</tr>
<tr>
<td>&nbsp;BATCH_SIZE</td>
<td>
<p>The number of statements in a batch. Applicable for execution modes BATCH and TOKEN_AWARE_BATCH.</p>
</td>
<td>Recommended 100</td>
<td>100</td>
</tr>
<tr>
<td>&nbsp;BATCH_THREADS_NUM</td>
<td>The number of threads for committing batch statements.</td>
<td>Recommended 5</td>
<td>5</td>
</tr>
</tbody>
</table>







[![Previous](/articles/images/Previous.png)](02_loader_architecture.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](xxx.md) 

