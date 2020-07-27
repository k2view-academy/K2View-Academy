# Sync Behavior - Summary Table

A Sync is influenced by the [Sync Method](/articles/14_sync_LU_instance/04_sync_methods.md), Properties  and [Sync Mode](/articles/14_sync_LU_instance/02_sync_modes.md)  (ON / OFF / FORCE), as described in the following table: 

<table width="106%">
<tbody>
<tr>
<td width="20%">
<p><strong>Sync Mode</strong></p>
</td>
<td colspan="5" width="35%">
<p><strong>On</strong></p>
</td>
<td colspan="5" width="44%">
<p><strong>Force</strong></p>
</td>
</tr>
<tr>
<td width="20%">
<p><strong>Sync Method</strong></p>
</td>
<td width="7%">
<p><strong>None</strong></p>
</td>
<td colspan="2" width="12%">
<p><strong>Decision</strong></p>
</td>
<td colspan="2" width="15%">
<p><strong>Time Interval</strong></p>
</td>
<td width="7%">
<p><strong>None</strong></p>
</td>
<td colspan="2" width="13%">
<p><strong>Decision</strong></p>
</td>
<td colspan="2" width="22%">
<p><strong>Time Interval</strong></p>
</td>
</tr>
<tr>
<td width="20%">
<p><strong>Result</strong></p>
</td>
<td width="7%">
<p><strong>&nbsp;</strong></p>
</td>
<td width="5%">
<p><strong>True</strong></p>
</td>
<td width="6%">
<p><strong>False</strong></p>
</td>
<td width="6%">
<p><strong>Pass</strong></p>
</td>
<td width="9%">
<p><strong>Not Pass</strong></p>
</td>
<td width="7%">
<p><strong>&nbsp;</strong></p>
</td>
<td width="6%">
<p><strong>True</strong></p>
</td>
<td width="7%">
<p><strong>False</strong></p>
</td>
<td width="8%">
<p><strong>Pass</strong></p>
</td>
<td width="13%">
<p><strong>Not Pass</strong></p>
</td>
</tr>
<tr>
<td width="20%">
<p>First Sync</p>
</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/X_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/X_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
</tr>
<tr>
<td width="20%">
<p>Schema Change</p>
</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/X_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/X_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
</tr>
<tr>
<td width="20%">
<p>Normal Operation</p>
</td>
<td align="center" width="60"><img src="/articles/images/X_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/X_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/X_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/X_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
</tr>
</tbody>
</table>

Note that when the Sync mode = OFF Fabric does not perform a sync. 

[![Previous](/articles/images/Previous.png)](/articles/14_sync_LU_instance/09_skip_sync.md)
