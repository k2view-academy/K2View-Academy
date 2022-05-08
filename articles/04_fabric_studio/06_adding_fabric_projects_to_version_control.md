<studio>

# Adding Fabric Projects to Version Control

### Fabric Version Control Overview

The Fabric Studio has a built-in Version Control functionality based on GIT standards. The benefits of using Versioning Control are:

* Maintaining current and historical versions of files.
* Cooperative and simultaneous work of multiple users while ensuring the integrity of their work. 
* Reverting to an older version of a project’s files when errors are found in a current version.

Note that although there is an option for working with Apache Sub-version (SVN), it is recommended to work in a GIT framework

[Click for more information about Best Practices for Working with SVN and GIT.](/articles/04_fabric_studio/07_best_practices_for_working_with_GIT_and_SVN.md)

Click for more information about the K2Admin Studio. 

### How Do I Start Working with Version Control?

To start using Configuration Control, do either:

* Create a new project and add the project to version control.
* Check out an existing project based on the repository type: GIT or SVN.

Each user can work on a local copy of the project that is synchronized with the central repository of the project. Synchronization is performed manually.\
Note that if you choose to work with SVN as your preferred version control, the K2Admin Studio should be installed in order to define the repository, users, groups and permissions.

[Click for more information about Creating a New Project](/articles/04_fabric_studio/05_creating_a_new_project.md).

[Click for more information about the Fabric Project Structure.](/articles/04_fabric_studio/08_fabric_project_tree.md)

### Setting Up GIT

**GIT Server**

1.	Check that the **GIT Server** is available or use **GitHub**.
2.	Create a **new repository** for the new project.
3.	Copy the **Repo URL** and create the relevant **user permissions**. 

**GIT Client**

Download and install **TortoiseGit** or GitHub for Windows on the Fabric Studio Windows machine.

**GIT on Fabric Studio** 

1.	Go to the **Fabric Studio** and create a [**new project**](/articles/04_fabric_studio/05_creating_a_new_project.md) and add it to **GIT**.
2.	Add and commit the new project items to GIT: right click the **project**, click **TortoiseGit**, click **Add** and then click **Commit**. 

### Logging In to SVN

1.	Go to the **SVN Version Control** and click the **project** to display the **Connect to Login** dialog box of the last opened project.
2.	Enter the **Username** and **Password** and then click **OK** to open the Fabric Studio Main Window.

### Checking a Project Out of GIT

**How Do I Check a Project Out of GIT?**
1.	Go to the **File Browser** and click **Checkout Directory** and then **Project**.
2.	Add the **GitHub Repository URL** and click **OK** to open the **Authentication** dialog box.
3.	Enter your **Username** and **Password** and then click **OK**.


[Click for more information about Best Practices for Working with SVN and GIT.](/articles/04_fabric_studio/07_best_practices_for_working_with_GIT_and_SVN.md)

### Checking a Project Out of SVN

**How Do I Check a Project Out of SVN?**

1.	Go to the **File Explorer** and click **Checkout Directory and Project**. 
2.	In the **Repository URL** field type in the **name** of the **project** to check out.
3.	Verify the **Repository URL** is correct.
4.	In the  **Revision panel** select the preferred **SVN Revision** and click **OK**.
5.	Optional: If a previous revision is selected, click **Show Log** to display the log and select the **Revision**.

![image](/articles/04_fabric_studio/images/04_06_01%20revision.jpg)

Note that when opening a project that has been updated and committed to the repository by another user, a warning is displayed requesting confirmation that the revision on the local machine is up-to-date.

[Click for more information about Best Practices for Working with SVN and GIT.](/articles/04_fabric_studio/07_best_practices_for_working_with_GIT_and_SVN.md)

### Project Tree Version Control Indicators

When a project is registered in the Version Control system, the following indicators may be displayed next to the Project Tree components based on their status:

<table>
<tbody>
<tr>
<td width="150pxl">&nbsp;<img src="/articles/04_fabric_studio/images/04_06_02%20icon%201.jpg" alt="" width="20"/></td>
<td width="700pxl">
<p>Modified</p>
</td>
</tr>
<tr>
<td width="150pxl">
<p>  +</p>
</td>
<td width="554">
<p>New objects</p>
</td>
</tr>
<tr>
<td width="150pxl">&nbsp;<img src="/articles/04_fabric_studio/images/04_06_02%20icon%202.png" alt="" width="20"/></td>
<td width="554">
<p>Locked</p>
</td>
</tr>
<tr>
<td width="150pxl">&nbsp;<img src="/articles/04_fabric_studio/images/04_06_02%20icon%203.png" alt="" width="20"/></td>
<td width="554">
<p>Updated</p>
</td>
</tr>
<tr>
<td width="150pxl">&nbsp;<img src="/articles/04_fabric_studio/images/04_06_02%20icon%204.jpg" alt="" width="20"/></td>
<td width="554">
<p>Branch with modifications</p>
</td>
</tr>
<tr>
<td width="150pxl">&nbsp;<img src="/articles/04_fabric_studio/images/04_06_02%20icon%205.jpg" alt="" width="20"/></td>
<td width="554">
<p>File is versioned,&nbsp;but&nbsp;was&nbsp;inserted&nbsp;from&nbsp;a different&nbsp;location</p>
</td>
</tr>
<tr>
<td width="150pxl">&nbsp;<img src="/articles/04_fabric_studio/images/04_06_02%20icon%206.jpg" alt="" width="20"/></td>
<td width="554">
<p>File&nbsp;is&nbsp;versioned&nbsp;and&nbsp;locally&nbsp;locked,&nbsp;but&nbsp;not&nbsp;modified</p>
</td>
</tr>
<tr>
<td width="150pxl">&nbsp;<img src="/articles/04_fabric_studio/images/04_06_02%20icon%207.png" alt="" width="20"/></td>
<td width="554">
<p>File&nbsp;is&nbsp;versioned,&nbsp;locally&nbsp;locked&nbsp;and&nbsp;modified</p>
</td>
</tr>
<tr>
<td width="150pxl">&nbsp;<img src="/articles/04_fabric_studio/images/04_06_02%20icon%208.png" alt="" width="20"/></td>
<td width="554">
<p>File&nbsp;is&nbsp;in&nbsp;conflict;&nbsp;must&nbsp;be&nbsp;resolved&nbsp;before&nbsp;continuing</p>
</td>
</tr>
</tbody>
</table>

### What Are the Version Control Operations?

**TortioseGit Operations**

<table>
<tbody>
<tr>
<td width="200pxl">
<p><strong>Commit</strong></p>
</td>
<td width="650pxl">
<p>Record changes to the repository.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>Pull</strong></p>
</td>
<td width="406">
<p>Incorporate changes from a remote repository into the current branch.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>Fetch</strong></p>
</td>
<td width="406">
<p>Download objects and refs from another repository.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>Diff</strong></p>
</td>
<td width="406">
<p>Displays the following options:</p>
<p>&middot;&nbsp;&nbsp;&nbsp; Changes between the working tree and the index or a tree.</p>
<p>&middot;&nbsp;&nbsp;&nbsp; Changes between the index and a tree.</p>
<p>&middot;&nbsp;&nbsp;&nbsp; Changes between two trees.</p>
<p>&middot;&nbsp;&nbsp;&nbsp; Changes between two blob objects.</p>
<p>&middot;&nbsp;&nbsp;&nbsp; Changes between two files on disk.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>Show Log</strong></p>
</td>
<td width="406">
<p>Displays a log of all commits influenced by the selected file.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>Show Reflog</strong></p>
</td>
<td width="406">
<p>Manages the information recorded in reflogs.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>Browse References</strong></p>
</td>
<td width="406">
<p>Browse to view and work with all references.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>Repository Browser</strong></p>
</td>
<td width="406">
<p>Displays all contents / files / revisions of a repository, without having a working tree.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>Check for Modifications</strong></p>
</td>
<td width="406">
<p>Displays all modified files and un-versioned files in the working tree.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>Resolve</strong></p>
</td>
<td width="406">
<p>Displays a list of conflicted files in a folder. Select a file to mark it as Resolved.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>Revert</strong></p>
</td>
<td width="406">
<p>Displays a pop up listing the files that have been changed and that can&nbsp;be reverted. (Undo).</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>Cleanup</strong></p>
</td>
<td width="406">
<p>Enables removing untracked or ignored files from the working tree.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>Switch</strong></p>
</td>
<td width="406">
<p>Enables checking out a specific version to the working tree.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>Merge</strong></p>
</td>
<td width="406">
<p>Incorporates changes from another repository and can be used to manually merge changes from one branch into another.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>Create Branch</strong></p>
</td>
<td width="406">
<p>Create a new Git branch.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>Create Tag</strong></p>
</td>
<td width="406">
<p>Create a tag object.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>Export</strong></p>
</td>
<td width="406">
<p>Export a Git Working Tree.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>Add</strong></p>
</td>
<td width="406">
<p>Updates the index using the current content in the working tree to prepare the content staged for the next commit.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>Help</strong></p>
</td>
<td width="406">
<p>Display Help information about Git.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>About</strong></p>
</td>
<td width="406">
<p>Details on the Git version and additional components.</p>
</td>
</tr>
</tbody>
</table>



**Key SVN Operations**

The context menu displays two top-level SVN operations or TortoiseSVN operations directly from the Project Tree:

<table>
<tbody>
<tr>
<td width="150pxl">
<p><strong>SVN Update</strong></p>
</td>
<td width="700pxl">
<p>Performs manual synchronization at any time. Select to either update to the HEAD revision (latest) or to an earlier revision.</p>
</td>
</tr>
<tr>
<td width="151">
<p><strong>SVN Commit</strong></p>
</td>
<td width="459">
<p>Commit all changes to the central repository. The committed components are available to other users. You can commit individual components or all components.</p>
</td>
</tr>
<tr>
<td width="151">
<p><strong>Log</strong></p>
</td>
<td width="459">
<p>Enables you to review the changes of a specific component, level in the tree, or over a given period. Displays a Log window that lists the log entries associated with the object.</p>
</td>
</tr>
<tr>
<td width="151">
<p><strong>Repository Browser</strong></p>
</td>
<td width="459">
<p>Enables you to browse different versions of the project or a specific object. By default, the Head (latest) revision is displayed. Drill down to the level of a component and review the log.</p>
</td>
</tr>
<tr>
<td width="151">
<p><strong>Cleanup</strong></p>
</td>
<td width="459">
<p>Enables you to perform a sanity check on the SVN issues of the client. Upon completion, a message is displayed to report success or identify problems.</p>
</td>
</tr>
<tr>
<td width="151">
<p><strong>Revert</strong></p>
</td>
<td width="459">
<p>Enables you to roll back any changes that have not yet been committed and revert to the version in the SVN repository. By default, all changes under the tree component are rolled-back. There is also an option to choose only selected changes.</p>
</td>
</tr>
<tr>
<td width="151">
<p><strong>Resolve Conflict</strong></p>
</td>
<td width="459">
<p>Enables you to resolve a conflict between the local version and the version in the SVN. These conflicts may arise if a new project version, committed by another user, contains changes conflicting with the locally made changes. You can select to resolve the conflict based on the local version or the committed version.</p>
</td>
</tr>
<tr>
<td width="151">
<p><strong>SVN Export</strong></p>
</td>
<td width="459">
<p>Exports the SVN version of the entire project.</p>
</td>
</tr>
<tr>
<td width="151">
<p><strong>SVN Branch feature</strong></p>
</td>
<td width="459">
<p>Enables isolating changes into a branch. Branches are often used to test new features without disturbing the main line of development with compiler errors and bugs. When the new feature is stable, the development branch is merged back into the main branch (trunk), accessed from the root project tree SVN context menu.</p>
</td>
</tr>
</tbody>
</table>


[Click for more information about Best Practices for Working with SVN and GIT.](/articles/04_fabric_studio/07_best_practices_for_working_with_GIT_and_SVN.md)

[![Previous](/articles/images/Previous.png)](08_fabric_project_tree.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/04_fabric_studio/07_best_practices_for_working_with_GIT_and_SVN.md)



</studio>
