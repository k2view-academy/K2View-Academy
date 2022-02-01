<web>

# Fabric Cloud Platform

In Fabric 7.0 we are introducing the Cloud Platform **cloud.k2view.com**, where you can create your spaces and see a list of the created spaces. A **space** is a fully working Fabric cluster environment providing all the tools and dependencies needed to run Fabric solutions. 

The Cloud Platform is multi-tenant, i.e. it handles multi organizations – K2view’s customers - each in its own separated area. Once a space is created it is accessible directly - there is no need to access first to the Cloud Platform.

The table below lists the features of the Cloud Platform that are currently available:

<table width="700pxl">
<tbody>
<tr>
<td style="width: 193.675px;">
<p><strong>Feature</strong></p>
</td>
<td style="width: 491.525px;">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td style="width: 193.675px;">
<p><strong>Authentication with IDP / SSO</strong></p>
</td>
<td style="width: 491.525px;">
<p>Login with K2View standard organization login (if you passed authentication at your browser then you will not need to make login).</p>
</td>
</tr>
<tr>
<td style="width: 193.675px;">
<p><strong>View space list</strong></p>
</td>
<td style="width: 491.525px;">
<p>All users can see and act on all spaces. Roles and permissions are not within the scope of this release.</p>
</td>
</tr>
<tr>
<td style="width: 193.675px;">
<p><strong>Open / Access a space</strong></p>
</td>
<td style="width: 491.525px;">
<p>Opening a space can be done by:</p>
<ul>
<li>Click on the space&rsquo;s name at the space list (when it is in status Running)</li>
<li>Use the ellipsis menu which resides for each space entry.</li>
<li>Direct access to the space by typing its URL address.</li>
</ul>
<p>Note that more than one user can access to a space on the same time.</p>
</td>
</tr>
<tr>
<td style="width: 193.675px;">
<p><strong>Create a space</strong></p>
</td>
<td style="width: 491.525px;">
<p>To create a space:</p>
<ul>
<li>Click on the Create button and top right side of the screen.</li>
<li>At the opened form, give the space a name, following the allowed pattern (a-z, 0-9; up to 16 characters) and rules (cannot be an existing name).</li>
<li>The space will be added to the list of spaces where you can see their creation progress and status. The lines of the new space are highlighted with bold text.</li>
</ul>
<p>Notes:</p>
<ul>
<li>A created space is an environment with Fabric and Cassandra.</li>
<li>A space is created with an empty Fabric project.There are no limitations as to how many spaces you can create.</li>
</ul>
</td>
</tr>
<tr>
<td style="width: 193.675px;">
<p><strong>Delete a space</strong></p>
</td>
<td style="width: 491.525px;">
<p>To delete a space:</p>
<ul>
<li>Use the ellipsis menu which resides for each space entry. Click there on Delete and assert the action at the popup dialog.</li>
<li>The deletion process will be then shown at the spaces list. Once process is done the space will not be shown at the spaces list.</li>
</ul>
<p>Notes:</p>
<ul>
<li>There is no restriction who can delete a space or which space can be deleted.</li>
<li>Deleting a space affects the storage. Delete must be done carefully and if there are changes that must be preserved, download the space first.</li>
</ul>
</td>
</tr>
</tbody>
</table>



</web>



