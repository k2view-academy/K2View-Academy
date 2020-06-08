# Fabric Studio - Validating Java Code Within a Project


Fabric implementations are written in Java and are stored in standard libraries and packages that are part of the Fabric product. Similar to other development software platforms, the code generated in the Fabric environment is compiled, loaded and validated before it is ready for execution. 

### Validating Java Code When a Project is Opened
 
Fabric Studio compiles all Java code in the project when the project is opened. Compilation errors and warnings, if any, are displayed in the [Server/Activity Logs tab](/articles/13_LUDB_viewer_and_studio_debug_capabilities/02_fabric_studio_log_files.md).

### How Do I Publish Project Code?
 
After the Java code of the project object (like a Function or a Web Service) is complete it must be validated.
1. Do either:
    * Press **CTRL+S** to save the code.
    * Click **Save** on the left top screen to save the code.  
2. Go to the **Project Tree**, right click the **Project Name** and then click **Rebuild All Java Code**. 

### When Is Java Code Validated?
 
* Java code must be revalidated each time a function is modified or added. 
* All Java code in a project must be validated before deployment to the server side.

### How Do I Know If a Validation Is Successful?
  
There are three validation success indicators:
1. Success without a message, whereby no action is required.
2. Success with a warning which is displayed on the bottom of the screen. It is recommended to fix and review the warning.  

![image](/articles/04_fabric_studio/images/04_10_01%20fix%20and%20review.png)

3. Failure with an error, whereby the violation must be fixed. The error is displayed on the bottom of the screen at a Summary level. For more details, right click the error to open it in Notepad.

![image](/articles/04_fabric_studio/images/04_10_02%20Summary%20level.png)



[Click for more information about Fabric Studio Log Files.](/articles/13_LUDB_viewer_and_studio_debug_capabilities/02_fabric_studio_log_files.md)


[![Previous](/articles/images/Previous.png)](/articles/04_fabric_studio/09_logic_files_and_categories.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/04_fabric_studio/11_fabric_studio_exporting_and_importing%20a_fabric_project.md)

