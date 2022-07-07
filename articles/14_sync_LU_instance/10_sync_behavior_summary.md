# Sync Behavior - Summary Table

A Sync is influenced by the [Sync Method](/articles/14_sync_LU_instance/04_sync_methods.md), Properties  and [Sync Mode](/articles/14_sync_LU_instance/02_sync_modes.md)  (ON / OFF / FORCE), as described in the following table: 

### Sync Mode = ON

<table>
<tbody>
<tr>
<td style="width: 200px;">
<p><strong>Sync Method</strong></p>
</td>
<td style="width: 200px; text-align: center;">
<p><strong>None</strong></p>
</td>
<td style="width: 200px; text-align: center;" colspan="2">
<p><strong>Decision</strong></p>
</td>
<td style="width: 200px; text-align: center;" colspan="2">
<p><strong>Time Interval</strong></p>
</td>
</tr>
<tr>
<td >
<p><strong>Result</strong></p>
</td>
<td style="text-align: center;">
<p><strong>&nbsp;</strong></p>
</td>
<td style="text-align: center;">
<p><strong>True</strong></p>
</td>
<td style="text-align: center;">
<p><strong>False</strong></p>
</td>
<td style="text-align: center;">
<p><strong>Pass</strong></p>
</td>
<td style="text-align: center;">
<p><strong>Not Pass</strong></p>
</td>
</tr>
<tr>
<td >
<p>First Sync</p>
</td>
<td style="width: 64px;" align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
<td style="width: 71px;" align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
<td style="width: 64px;" align="center" width="60"><img src="/articles/images/X_icon.png" alt="" width="25" height="26" /></td>
<td style="width: 67px;" align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
<td style="width: 64px;" align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
</tr>
<tr>
<td >
<p>Schema Change</p>
</td>
<td style="width: 64px;" align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
<td style="width: 71px;" align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
<td style="width: 64px;" align="center" width="60"><img src="/articles/images/X_icon.png" alt="" width="25" height="26" /></td>
<td style="width: 67px;" align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
<td style="width: 64px;" align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
</tr>
<tr>
<td style="width: 150px;">
<p>Normal Operation</p>
</td>
<td style="width: 64px;" align="center" width="60"><img src="/articles/images/X_icon.png" alt="" width="25" height="26" /></td>
<td style="width: 71px;" align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
<td style="width: 64px;" align="center" width="60"><img src="/articles/images/X_icon.png" alt="" width="25" height="26" /></td>
<td style="width: 67px;" align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
<td style="width: 64px;" align="center" width="60"><img src="/articles/images/X_icon.png" alt="" width="25" height="26" /></td>
</tr>
</tbody>
</table>



### Sync Mode = FORCE

<table>
<tbody>
<tr>
<td style="width: 200px;">
<p><strong>Sync Method</strong></p>
</td>
<td style="width: 200px; text-align: center;">
<p><strong>None</strong></p>
</td>
<td style="width: 200px; text-align: center;" colspan="2">
<p><strong>Decision</strong></p>
</td>
<td style="width: 200px; text-align: center;"  colspan="2">
<p><strong>Time Interval</strong></p>
</td>
</tr>
<tr>
<td>
<p><strong>Result</strong></p>
</td>
<td style="text-align: center;">
<p><strong>&nbsp;</strong></p>
</td>
<td style="text-align: center;">
<p><strong>True</strong></p>
</td>
<td style="text-align: center;">
<p><strong>False</strong></p>
</td>
<td style="text-align: center;">
<p><strong>Pass</strong></p>
</td>
<td style="text-align: center;">
<p><strong>Not Pass</strong></p>
</td>
</tr>
<tr>
<td>
<p>First Sync</p>
</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
<td align="center" width="60"><img src="/articles/images/X_icon.png" alt="" width="25" height="26" /></td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
</tr>
<tr>
<td>
<p>Schema Change</p>
</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
<td align="center" width="60"><img src="/articles/images/X_icon.png" alt="" width="25" height="26" /></td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
</tr>
<tr>
<td>
<p>Normal Operation</p>
</td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
<td align="center" width="60"><img src="/articles/images/X_icon.png" alt="" width="25" height="26" /></td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
<td align="center" width="60"><img src="/articles/images/V_icon.png" alt="" width="25" height="26" /></td>
</tr>
</tbody>
</table>



When the Sync mode = OFF, Fabric does not perform a sync. 



[![Previous](/articles/images/Previous.png)](/articles/14_sync_LU_instance/09_skip_sync.md)
