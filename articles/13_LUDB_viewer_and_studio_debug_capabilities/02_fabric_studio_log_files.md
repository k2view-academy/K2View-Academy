# Fabric Studio Log Files

Fabric Studio enables you to develop, test and monitor projects within the Studio using Fabric’s test and log capabilities. 

To display a list of recent messages from the server, click
<img src="images/13_02_01%20Server%20icon.png" alt="image" style="zoom:75%;" /> Server / Activity Logs (left menu). By default, this window only displays warnings and error messages, however additional messages and notices can also be reviewed.

The following are the main log options:
* Server logs.
* Activity logs.
* Compilation errors.

[Click for more information about UI Components and Menus.](/articles/04_fabric_studio/01_UI_components_and_menus.md)

### How do I Review Server / Activity Logs and Compilation Errors?
**Server Logs**
1. Click <img src="images/13_02_02%20Server%20Logs%20icon.png" alt="image" style="zoom:75%;" />  **Server Logs** and then click either **Errors**, **Warnings** or **Messages** to display the relevant server logs list.

![image](images/13_02_03%20server%20logs%20list.jpg)

2. Right click in the **Log’s list** area to display the following context menu options:
    * **Select Open Selected Items Data in Notepad**, to review the entire error or notification message.
    * **Select Copy Selected Items Data**, to copy the entire text onto the clipboard.
    * **Clear List** to remove the listed logs. Clear list can also be accessed by clicking the **Clear List** icon (top pane).

**Activity Logs**  

3. Click <img src="images/13_02_04%20Activity%20Logs%20ICON.png" alt="image" style="zoom:75%;" /> **Activity Logs** to open the Activity Logs window.

![image](images/13_02_05%20Activity%20Logs%20window.jpg) 

4. Follow **Step 2** to select and review the log. 

**Compilation Errors**

5. Click <img src="images/13_02_06%20Compilation%20Errors%20ICON.png" alt="image" style="zoom:75%;" />  **Compilation Errors** to open the following window. 

![image](images/13_02_07%20window.jpg)

6. Follow **Step 2** to select and review the log. 

**Output**

7. Click <img src="images/13_02_08%20Output%20ICON.jpg" alt="image" style="zoom:67%;" /> **Output** to open the following window where you can review the K2Fabric log:

![image](images/13_02_09%20K2Fabric%20log.jpg)


### Debugging Logs and Messages

Logs and messages can also be used for debugging purposes. The **log.info ()** method is available to enable Fabric’s runtime Debug options. This method can be added to any Java code and its output can be viewed after deployment and during runtime. 

For example, see the **fnCreateInstID** function under Customer LU in the demo project:

~~~java
if (i_id!=null && !i_id.isEmpty()){
// Increase the input by 10 and return
   log.info("o_id: "(Integer.sum(Integer.valueOf(i_id),10)+""));
	return Integer.sum(Integer.valueOf(i_id),10)+"";
}
return "0";
~~~



The runtime log (k2fabric.log) can be reviewed in the Fabric Server’s Logs directory or in the Studio’s Output logs.

![image](images/13_02_10%20Debug.jpg)



[![Previous](/articles/images/Previous.png)](01_data_viewer.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_debug_table_population.md)
