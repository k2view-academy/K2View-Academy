<web>

## Using Source Control in Web Studio


The Fabric Web Studio has an integrated source control management (SCM) system and it includes [Git](https://git-scm.com/) support in-the-box.

The Source Control <img src="images/web/scm.png" style="zoom:7%;" /> icon in the Activity Bar on the left-most menu indicates an **overview of how many changes** you currently have in your repository.



Clicking the icon will show the details of your current repository changes: **CHANGES**, **STAGED CHANGES** and **MERGE CHANGES**.

> Tip: To bring the Source Control View up, you can also use the keyboard shortcut `CTRL+SHIFT+G`.

Clicking each item will show you in detail **the textual changes within each file**. Note that for unstaged changes, the editor on the right still lets you edit the file.

You can also find indicators of the **status of your repository** at the bottom-left corner of the status bar: the **current branch**, **dirty indicators**, and the number of **incoming and outgoing commits** of the current branch. You can **checkout** any branch in your repository by clicking that status indicator and selecting the Git reference from the list.



## Commit

**Staging** (Git add) and **unstaging** (Git reset) can be done either via contextual actions in the files or by a drag-and-drop feature.

You can type a commit message above the changes and press `Ctrl+Enter` (macOS: `⌘+Enter`) to commit them. If there are any staged changes, only changes will be committed. Otherwise, you will get a prompt asking you to select what changes you would like to commit and get the option to change your commit settings.

More specific **Commit** actions can be found in the **Views and More Actions** `...`  (ellipsis) menu at the top of the Source Control view.



> **Tip:** If you commit your change to the wrong branch, undo your commit using the **Git: Undo Last Commit** command in the **Command Palette**.



## Git Status Bar Actions

There is a **Synchronize Changes** action icon in the Status Bar, next to the branch indicator; when clicked, it pulls remote changes down to your local repository and then pushes local commits to the upstream branch.



## Gutter Indicators

If you open a folder that is a Git repository and begin making changes, Web Studio will add useful annotations to the gutter and to the overview ruler. These annotations appear near the line numbers.

* A red triangle indicates where lines have been deleted
* A green bar indicates new added lines
* A blue bar indicates modified lines



## Merge Conflicts

Merge conflicts are recognized by Web Studio. Differences are highlighted and there are inline actions to accept either one or both changes. Once the conflicts are resolved, stage the conflicting file so you can commit those changes.

## Viewing Diffs

The Git tool supports viewing of diffs within Web Studio, showing original and modified file side by side.



>**Tip:** You can diff any two files by first right-clicking on a file in the Explorer or **OPEN EDITORS** list and selecting **Select for Compare** and then right-click on the second file to compare with and select **Compare with 'file_name_you_chose'**.   Alternatively from the keyboard hit `Ctrl+Shift+P` and select **File: Compare Active File With** and you will be presented with a list of recent files.



[![Previous](/articles/images/Previous.png)](/articles/04_fabric_studio/24_web_debug.md)
[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/04_fabric_studio/25_web_data_explorer.md)

</web>
