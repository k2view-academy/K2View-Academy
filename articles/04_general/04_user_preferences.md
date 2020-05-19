# User Preferences

### What Are User Preferences Used For?
User Preferences are used to define key operational functionalities for development, debugging, deployment and runtime execution activities.\
To open the User Preferences options, click the [Tools](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_general/01_UI_components_and_menus.md#fabric-studio-toolbar-tabs) icon in the Fabric Studio to display the default General Preferences folder and its configuration items.
 
![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_general/images/04_04_01%20default%20General%20Preferences.png)

User Preferences are saved in: <Fabric Studio Installation Dir >\Preferences. 

### What Is The purpose of the General Preferences Tab?
The General Preferences tab is used to define the system’s Projects Directory folder which is by default saved in:

C:\users\<username>\documents\K2View Fabric Studio\Projects.

To update the location of the folder or to edit it, click **Browse**.

### What is the Purpose pf the Server Configuration Tab?

The Server Configuration tab is used to define the Deployment URLs of Fabric Servers. Each Fabric Server can be accessed to deploy an implementation, [debug](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/05_DB_interfaces/05_adding_a_fabric_and_remote_fabric_interface_type.md) and to validate [Web Services]. 

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_general/images/04_04_02%20Web%20Services.png)


The following is a list of server configuration items: 

<table>
<tbody>
<tr>
<td width="133">
<p><strong>Title</strong></p>
</td>
<td width="467">
<p>K2View Fabric logical name of the server.</p>
</td>
</tr>
<tr>
<td width="133">
<p><strong>URL</strong></p>
</td>
<td width="467">
<p>Displays a generic URL for the deployment in the following format: http://&lt;host&gt;:3213/deploy.</p>
<p>Edit the Hostname or Host IP Address in the &lt;host&gt; field to deploy your Project or its components such as a <a href="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/01_LU_overview.md">Logical Unit<\a> or Web Services to the configured server.</p>
</td>
</tr>
<tr>
<td width="133">
<p><strong>Username</strong></p>
</td>
<td width="467">
<p>Username for database access authentication.</p>
</td>
</tr>
<tr>
<td width="133">
<p><strong>Password</strong></p>
</td>
<td width="467">
<p>Password for database access authentication.</p>
</td>
</tr>
<tr>
<td width="133">
<p><strong>Force Upgrade Post Deploy</strong></p>
</td>
<td width="467">
<p>&middot;&nbsp;&nbsp;&nbsp; <strong>Checked</strong>: performs a forced <a href="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/14_sync_LU_instance/01_sync_LUI_overview.md">Sync</a> after deployment whereby the LU is always synchronized regardless of the Sync Method defined for the LU. <br /> Note that when a Decision Function is defined for a Sync Method, the function takes precedence.</p>
<p>&middot;&nbsp;&nbsp;&nbsp; <strong>Unchecked</strong>: performs a Sync on Schema changes only.</p>
<p>Note that each LU undergoes one forced Sync the first time it is retrieved, regardless whether this option is checked / unchecked.</p>
</td>
</tr>
<tr>
<td width="133">
<p><strong>Web service invoke path template</strong></p>
</td>
<td width="467">
<p>By default, Web Services are invoked using Swagger. For example: static/swaggerUI/dist/index.html#/&lt;CATEGORY&gt;/&lt;WS_VERB&gt;_&lt;WS_PATH&gt;</p>
</td>
</tr>
<tr>
<td width="133">
<p><strong>Add WS Parameters to URL Query</strong></p>
</td>
<td width="467">
<p>When checked, enables adding parameters to the Web Service (WS) URL body.</p>
<p>For example: ws?format=html&amp;methodName=&lt;WS_NAME&gt;&amp;token=&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
</td>
</tr>
<tr>
<td width="133">
<p><strong>Test</strong></p>
</td>
<td width="467">
<p>Test the connection to the server to validate the URL.</p>
</td>
</tr>
</tbody>
</table>


**Click for more information about Deployment from the Fabric Studio.**\
[**Click for more information about the Sync Method.**\](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/14_sync_LU_instance/04_sync_methods.md)
**Click for more information about Invoking a Web Service from the Studio.**

Note that once defined, both General Preferences and Server Configurations will continue to apply after the Fabric Studio is upgraded.

### How Do I Add Application Shortcuts?
Application shortcuts enable you to define shortcuts to other applications directly from the Fabric Studio without exiting the application. Once defined, the new shortcut is displayed in the upper left corner of the window.

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_general/images/04_04_03%20new%20shortcut.png)

1. Go to **Application Shortcuts** and click Add **New Shortcut**.  
2. Enter the **Application Name** in the **Title** field.
3. Enter the **Path** to the application file in the **Target Path** field.
4. Enter any **Settings** for the application in the **Parameters** field.
5. Click **OK** and then click **Save** or press **CTRL+S** to save the updated shortcuts. 


### How do I Configure GIT Preferences?

GIT preferences can be used to enable the following:
* Creating new Projects in GIT.
* Checking Projects out of GIT: 
  * **Auto Update Gif Status**, automatically refreshes the Git’s status in the Project Tree, for example, to show if a file is modified. By default, this option is checked. If this is not checked, the Project Tree will not fetch the status of each file.
  * **Auto Add Files to Git**, when checked adds the Project’s new files to Git which can then be committed. If this is not checked, you can add the files manually using the Git **add** command and then commit them. By default, this option is not set.

Note that TortoiseGit enables adding un-versioned files when performing the Commit process. These files do not need to be added beforehand. 

**Click for more information about SVN and GIT Best Practices.**

### How do I Configure SVN Preferences?
The following SVN preferences can be configured:








