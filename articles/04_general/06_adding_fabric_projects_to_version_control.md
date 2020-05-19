# Adding Fabric Projects to Version Control

### Fabric Version Control Overview

The Fabric Studio has a built-in Version Control functionality based on GIT standards. The benefits of using Versioning Control are:

* Maintaining current and historical versions of files.
* Cooperative and simultaneous work of multiple users while ensuring the integrity of their work. 
* Reverting to an older version of a project’s files when errors are found in a current version.

Note that although there is an option for working with Apache Sub-version (SVN), it is recommended to work in a GIT framework

**Click for more information about Best Practices for Working with SVN and GIT.**\
**Click for more information about the K2Admin Studio.** 

### How do I Start Working with Version Control?
 
To start using Configuration Control, do either:

* Create a new project and add the project to version control.
* Check out an existing project based on the repository type: GIT or SVN.

Each user can work on a local copy of the project that is synchronized with the central repository of the project. Synchronization is performed manually.\
Note that if you choose to work with SVN as your preferred version control, the K2Admin Studio should be installed in order to define the repository, users, groups and permissions.

**Click for more information about Creating a New Project.**\  
**Click for more information about the Fabric Project Structure.**

### Setting Up GIT

**GIT Server**

1.	Check that the **GIT Server** is available or use **GitHub**.
2.	Create a **new repository** for the new project.
3.	Copy the **Repo URL** and create the relevant **user permissions**. 

**GIT Client**

Download and install **TortoiseGit** or GitHub for windows on the Fabric Studio Windows machine.

**GIT on Fabric Studio** 

1.	Go to the **Fabric Studio** and create a [**new project**](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_general/05_creating_a_new_project.md) and add it to **GIT**.
2.	Add and commit the new project items to GIT: right click the **project**, click **TortoiseGit**, click **Add** and then click **Commit**. 

### Logging in to SVN

1.	Go to the **SVN Version Control** and click the **project** to display the **Connect to Login** dialog box of the last opened project.
2.	Enter the **Username** and **Password** and then click **OK** to open the Fabric Studio Main Window.

### Checking a Project Out of GIT
 
**How do I check a project out of GIT?**
1.	Go to the **File Browser** and click **Checkout Directory** and then **Project**.
2.	Add the **GitHub Repository URL** and click **OK** to open the **Authentication** dialog box.
3.	Enter your **Username** and **Password** and then click **OK**.


**Click for more information about Best Practices for Working with SVN and GIT.**

### Checking a Project Out of SVN
 
**How do I check a project out of SVN?**

1.	Go to the **File Explorer** and click **Checkout Directory and Project**. 
2.	In the **Repository URL** field type in the **name** of the **project** to check out.
3.	Verify the **Repository URL** is correct.
4.	In the  **Revision panel** select the preferred **SVN Revision** and click **OK**.
5.	Optional: If a previous revision is selected, click **Show Log** to display the log and select the **Revision**.

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_general/images/04_06_01%20revision.jpg)

Note that when opening a project that has been updated and committed to the repository by another user, a warning is displayed requesting confirmation that the revision on the local machine is up-to-date.

**Click for more information about Best Practices for Working with SVN and GIT.**

### Project Tree Version Control Indicators

When a project is registered in the Version Control system, the following indicators may be displayed next to the Project Tree components based on their status:

<<table width="576">
<tbody>
<tr>
<td width="22">&nbsp;<img src="&lt;td width=&quot;22&quot;&gt;" alt="" width="20" height="20" /><img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_general/images/04_06_02%20icon%201.jpg" alt="" width="30" height="30" /></td>
<td width="554">
<p>Modified</p>
</td>
</tr>
<tr>
<td width="22">
<p>+</p>
</td>
<td width="554">
<p>New objects</p>
</td>
</tr>
<tr>
<td width="22">&nbsp;</td>
<td width="554">
<p>Locked</p>
</td>
</tr>
<tr>
<td width="22">&nbsp;</td>
<td width="554">
<p>Updated</p>
</td>
</tr>
<tr>
<td width="22">&nbsp;</td>
<td width="554">
<p>Branch with modifications</p>
</td>
</tr>
<tr>
<td width="22">&nbsp;</td>
<td width="554">
<p>File is versioned,&nbsp;but&nbsp;was&nbsp;inserted&nbsp;from&nbsp;a different&nbsp;location</p>
</td>
</tr>
<tr>
<td width="22">&nbsp;</td>
<td width="554">
<p>File&nbsp;is&nbsp;versioned&nbsp;and&nbsp;locally&nbsp;locked,&nbsp;but&nbsp;not&nbsp;modified</p>
</td>
</tr>
<tr>
<td width="22">&nbsp;</td>
<td width="554">
<p>File&nbsp;is&nbsp;versioned,&nbsp;locally&nbsp;locked&nbsp;and&nbsp;modified</p>
</td>
</tr>
<tr>
<td width="22">&nbsp;</td>
<td width="554">
<p>File&nbsp;is&nbsp;in&nbsp;conflict;&nbsp;must&nbsp;be&nbsp;resolved&nbsp;before&nbsp;continuing</p>
</td>
</tr>
</tbody>
</table>

### What are the Version Control Operations?
  
**TortioseGit Operations **


**Key SVN Operations**

The context menu displays two top-level SVN operations or TortoiseSVN operations directly from the project tree:

**Click for more information about Best Practices for Working with SVN and GIT.**


