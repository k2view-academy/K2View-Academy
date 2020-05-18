# User Preferences

### What Are User Preferences Used For?
User Preferences are used to define key operational functionalities for development, debugging, deployment and runtime execution activities.\
To open the User Preferences options, click the [Tools] icon in the Fabric Studio to display the default General Preferences folder and its configuration items.
 
![image]

User Preferences are saved in: <Fabric Studio Installation Dir >\Preferences. 

### What Is The purpose of the General Preferences Tab?
The General Preferences tab is used to define the system’s Projects Directory folder which is by default saved in:

C:\users\<username>\documents\K2View Fabric Studio\Projects.

To update the location of the folder or to edit it, click **Browse**.

### What is the Purpose pf the Server Configuration Tab?

The Server Configuration tab is used to define the Deployment URLs of Fabric Servers. Each Fabric Server can be accessed to deploy an implementation, [debug] and to validate [Web Services]. 

![image]


The following is a list of server configuration items: 


**Click for more information about Deployment from the Fabric Studio.**\
**Click for more information about the Sync Method.**\
**Click for more information about Invoking a Web Service from the Studio.**

Note that once defined, both General Preferences and Server Configurations will continue to apply after the Fabric Studio is upgraded.

### How Do I Add Application Shortcuts?
Application shortcuts enable you to define shortcuts to other applications directly from the Fabric Studio without exiting the application. Once defined, the new shortcut is displayed in the upper left corner of the window.

![image]

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








