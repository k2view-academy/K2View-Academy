# Cassandra Loader Configuration

### Execution Modes

The Cassandra loader execution modes are:

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









[![Previous](/articles/images/Previous.png)](02_loader_architecture.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](xxx.md) 

