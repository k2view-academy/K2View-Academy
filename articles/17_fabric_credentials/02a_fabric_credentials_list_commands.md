# Fabric Credentials List Commands

The following table describes the methods to see the access control information using Fabric `LIST` commands.

<table width="900pxl">
<tbody>
<tr>
<td width="200pxl">
<h4>LIST ROLES</h4>
</td>
<td width="700pxl">
<p><strong>Description</strong>: Retrives the list of roles, as stored at Fabric.&nbsp;</p>
<p>To read more about creating roles see&nbsp;<a href="/articles/17_fabric_credentials/02_fabric_credentials_commands.md#create-role">here</a>.&nbsp;</p>
<p>To read more about deleting roles see <a href="/articles/17_fabric_credentials/02_fabric_credentials_commands.md#revoke">here</a>.&nbsp;</p>
</td>
</tr>
<tr>
<td width="200pxl">
<h4>LIST SECURITY_PROFILES</h4>
</td>
<td width="700pxl">
<p><strong>Description</strong>: Retrives the list of securify profiles, as stored at Fabric.&nbsp;</p>
<p>To read more about creating security profiles see&nbsp;<a href="05_security_profiles.md">here</a>.&nbsp;</p>
</td>
</tr>
<tr>
<td width="200pxl">
<h4>LIST USERS</h4>
</td>
<td width="700pxl">
<p><strong>Description</strong>:&nbsp;List existing users along with their associated roles</p>
<p><strong>Usage</strong>:&nbsp;</p>
<p>LIST USERS&nbsp;[user_filter='&lt;filter&gt;'] [role_filter='&lt;filter&gt;']</p>
<p><strong>Parameters:</strong></p>
<ul>
<li>user_filter='&lt;filter&gt;' &ndash; optional, when specified the result will show only users which match to the filter applied on the user name..</li>
<li>[description &lt;'role description'&gt;] &ndash; optional, when&nbsp;specified the result will show only users which match to the filter applied on the role name</li>
</ul>
<p>The command filtering supports exact match as well as similarity filtering by using the '%' to sign wildcards.</p>
<p><strong>Examples:</strong></p>
<ul>
<li>'%th' - retries names that ends with 'th'
<ul>
<li>When defined on user filter - retrieves users that their name ends with 'th'</li>
<li>When defined on role filter - retierves users that are assoicated to roles which then name ends with 'th'</li>
</ul>
</li>
<li>'jo%' = starts with 'jo' (by filter type as explained above)</li>
<li>'%smi%' = contains 'smi'&nbsp;(by filter type as explained above)</li>
<li>'john smith' = exact match of 'john smith'&nbsp;(by filter type as explained above)</li>
<li>'%%' or without filters = all users</li>
</ul>
<p>To read more about disassociate user from roles see <a href="/articles/17_fabric_credentials/02_fabric_credentials_commands.md#revoke-role">here</a>.</p>
<p><strong>NOTE</strong>: Users as well as their associations to troles are not stored at Fabric when it is set to work with external identificationa and authentication providers, such as SAML or LDAP. Thus, this list will be empty (unless an admin was defined during system setup or when using fallbacks). For more information see <a href="/articles/26_fabric_security/07_user_IAM_overview.md">here</a>.</p>
</td>
</tr>
<tr>
<td width="200pxl">
<h4>LIST ROLE_PERMISSIONS</h4>
</td>
<td width="700pxl">
<p><strong>Description</strong>:&nbsp;List of permissions per role and per resource.</p>
<p>The retrieved list is in a table form where each entry contains the <em>role-name</em>, <em>method</em>&nbsp;(the operation) and resource -name. Each permission is on a seprated entry.&nbsp;</p>
<p><strong>Example:</strong></p>
<blockquote>
<pre>|role   |method|permissions|<br />+-------+------+-----------+<br />|admin  |ALL   |*          |<br />|admin  |ALL_WS|*          |<br />|manager|DEPLOY|CRM        |</pre>
</blockquote>
<p>&nbsp;</p>
<p>To read more about permission granting capabilities see <a href="/articles/17_fabric_credentials/02_fabric_credentials_commands.md#grant-command">here</a>.</p>
<p>To read more about revoking permissions see <a href="/articles/17_fabric_credentials/02_fabric_credentials_commands.md#revoke">here</a>.</p>
</td>
</tr>
<tr>
<td width="200pxl">
<h4>LIST TOKENS</h4>
</td>
<td width="700pxl">
<p><strong>Description:&nbsp;</strong>List of tokens and their association to roles.</p>
<p>The retrieved list is in a table form where each entry contains the token-name, indication if it is secured, and associated role/s</p>
<p><strong>Example:</strong></p>
<blockquote>
<p>|token|secured|roles&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |<br />+-----+--------+-----------------+<br />|ABC&nbsp; |false&nbsp; &nbsp; &nbsp;|admin&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |<br />|CBA&nbsp; |true&nbsp; &nbsp; &nbsp; |admin,manager|</p>
</blockquote>
<p>&nbsp;</p>
<p>To read more about create tokens see <a href="/articles/17_fabric_credentials/02_fabric_credentials_commands.md#create-token">here</a>.</p>
<p>To read more about associate token to role see <a href="/articles/17_fabric_credentials/02_fabric_credentials_commands.md#assign-role-role-to-token-api-key">here</a>.</p>
<p>To read more about disassociate roles from tokens see <a href="/articles/17_fabric_credentials/02_fabric_credentials_commands.md#revoke-role">here</a>.</p>
</td>
</tr>
</tbody>
</table>




[![Previous](/articles/images/Previous.png)](/articles/02_fabric_credentials_commands.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/17_fabric_credentials/03_fabric_credentials_backup.md)
