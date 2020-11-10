# Cassandra Loader Architecture

<table style="width: 800px;">
<tbody>
<tr>
<td width="400pxl"><h4><strong>Default Architecture</strong></h4></td>
<td width="400pxl"><h4><strong>Custom Architecture</strong></h4></td>
</tr>
<tr>
<td>
    <p><img src="images/28_01_1.PNG" alt="Default" /></p>
</td>
<td>
    <p><img src="images/28_01_2.PNG" alt="Custom" /></p>
</td>
</tr>
</tbody>
</table>

The default loader configuration makes a distinction between the Fabric processes that need to perform WRITE operations to Cassandra DB and the Fabric internal processes like Deploy. The internal processes run on a separate session, to prevent the dependency on other heavy processes.









[![Previous](/articles/images/Previous.png)](01_cassandra_loader_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_loader_configuration.md) 

