<web>

# Productivity Tips



## Command Palette 

Access all available Fabric Web Studio functionality and commands from the top menu by selecting **View > Command Palette** or use `⇧⌘P` on a Mac or or `Ctrl+Shift+P` in Windows, to bring up the **Command Palette**.

- Each command appears with its associated shortcut (if one exists). If you forget a keyboard shortcut (also referred to as a "key binding"), use the **Command Palette** to help you out.

- The Command Palette shows first the recently used commands and then all other commands.
- If you do not remember the exact command, just start to type the keyword that might fit to filter relevant matches. The Command Palette will complete the command. 
- The Command Palette includes both standard IDE commands, as well as Fabric dedicated commands. Once the palette is opened type "Fabric" to filter and see them. 



## Keyboard Shortcuts

Following are useful keyboard shortcuts for Mac & Windows ( ⌘ refers to the CMD button on a Mac (CTRL in Windows), and  ⌥ stands for Alt on a Mac (ALT key in Windows)).

To open the full list of shortcuts you can either:

* Use top menu: **File** > **Preferences** > **Open Keyboard Shortcuts**
* Use left bottom **Preferences** <img src="images/web/settings.png" style="zoom:67%;" /> gear icon > **Open Keyboard Shortcuts**
  
### Navigation

<table>
    <tbody style="vertical-align: text-top; ">
        <tr>
            <th>Shortcut</th>
            <th>Description</th>
        </tr>
        <tr >
            <td>⇧⌘P / CTRL+shift+P</td>
            <td>Show Command Palette</td>    
        </tr>
        <tr >
            <td>⌘P / CTRL+P</td>
            <td>
                Quickly Open recent files, shown in the drop down list, or search by typing filename.<br>
Typing filename:line number will lead you to that line in file.</td>    
        </tr>
        <tr >
            <td>⌘J / CTRL+J</td>
            <td>Toggle (show/hide) Bottom Panel</td>    
        </tr>
        <tr >
            <td>⌘M / CTRL+M</td>
            <td>Toggle Maximize Editor's Window (used together with F11, to get a full screen editor)</td>    
        </tr>
        <tr >
            <td>⌘⌥D / CTRL+alt+D</td>
            <td>Switch to the next editor Tab</td>    
        </tr>
        <tr>
            <td>⌘⌥A / CTRL+alt+A</td>
            <td>Switch to the previous editor tab</td>
        </tr>
        <tr>
            <td>⇧⌘M / CTRL+Shift+M</td>
            <td>Open Problems Panel</td>
        </tr>
        <tr>
            <td>⇧⌘F / CTRL+Shift+F</td>
            <td>Open Search View to search for terms inside files</td>
        </tr>
        <tr>
            <td>⇧⌘D / CTRL+Shift+D</td>
            <td>Open Debug View</td>
        </tr>
        </tbody>
    </table>

### Editing & Debug

<table>
<tbody style="vertical-align: text-top; ">
<tr>
            <td>^G / CTRL+G</td>
            <td>Go to a line within a file</td>
        </tr>
        <tr>
            <td>⇧⌘K / Ctrl+Shift+K</td>
            <td>Delete a line</td>
        </tr>
 </tbody>
</table>


## Save / Auto Save

By default, Web Studio requires an explicit action to save your changes to disk, by using **Ctrl+S** or via top menu **File > Save**.

However, it's easy to turn on `Auto Save`, which will save your changes every preconfigured time (default is 4 seconds). With this option turned on, there is no need to explicitly save the file. To turn on `Auto Save` use the **File** > **Auto Save** toggle that turns on and off save. 



## Rename Entities

Rename an entity can be done from the project tree context menu. To do so: Right click on a file and choose the **Rename** option, or click **`F2`** key.

When renaming entities, Studio is making for you some of the tasks. For example:

* When renaming a Data Product/Logical unit, all relevant files under it, like java packages will be automatically updated.
* When renaming an interface file name, the name, which exists inside the file content will be changed, accordingly, .
* When renaming a table name, will update its related population flow name.



[![Previous](/articles/images/Previous.png)](/articles/04_fabric_studio/24_web_debug.md)
</web>
