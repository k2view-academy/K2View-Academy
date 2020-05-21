# Logic Files and Categories

There are two types of Java files in Fabric Studio, one dedicated to the development of Java functions and the other for the creation of [Globals](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/08_globals/01_globals_overview.md) variables. In Fabric Studio the term **Logic Category Files** refers to a Java package that stored the Logic.java file. Logic.java file contains all the Java functions defined under the Logic Category, although the functions are presented on the project tree as separate files, it is basically stored under one file called Logic.java under the selected category.  

### Shared Java Files
Java files residing on a shared level can be inherited by any [project](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_fabric_studio/08_fabric_project_tree.md) components, for example,  [Web Services], References or [Logical Units](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/02_create_a_logical_unit_flow.md) and can be shared throughout a project.\
It is highly recommended to avoid duplicating names of Shared Objects at lower levels of a project, for example; within a Logical Unit component. However, if there are duplicated object file names, the file that resides under the specific lower level component has priority when executed.
 
A Java file residing at a [Shared Objects](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_fabric_studio/12_shared_objects.md) level has two out-of-the-box Java template files:
* SharedLogic.java, a placeholder for all shared functions. 
* SharedGlobals.java, a placeholder for all shared Globals. 

[Click for more information about Shared Objects in a Project Tree.](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_fabric_studio/12_shared_objects.md)

### Designated Java Files
 
Designated Logic.java files are specific to their level of definition, which can be References, Web Services or Logical Units.\
The Globals.java file is automatically created under either the References or each Logical Unit, which are both empty.

### How do I Associate a Function to a Category?
 	
When creating a new Java function, it must be associated to a category like a built-in or Product. Each category has multiple sub-categories like Date or Math that hold the most common types of functions for that sub-category.
 
* If the category does not exist, a new Logic.java file is created and the function is associated to it. 
* When the category exists, the new function is associated to the existing Logic.java file.
* If a new category name is entered into the Category setting, a new **category** is created and the function is associated to the new Logic.java file.
 
Each category creates a separate Logic.java file that has a specific path to differentiate it from other files in other categories.

[Click for more information about Project Functions.](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/08_project_functions.md)

**Notes** 
* [Export / Import](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_fabric_studio/11_fabric_studio_exporting_and_importing%20a_fabric_project.md) can be implemented on a Java file level. To copy only one function from one category to another category or project, copy and paste the function’s code. 
* Version control is managed on a Logic.java file level and not on a function level. 
* Functions can be edited from IntelliJ by pressing Ctrl+I in a function in the Fabric Studio to activate IntelliJ. Fabric Studio enables you to open the source file.

### How do I Create a Category?
 
Go to the **Project Tree**, right click **Java** and click **New SharedLogic Category File / New Logic Category File**.\
Note that when creating a new function, enter a new category name to automatically create a new category folder.

[![Previous](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Previous.png)](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_fabric_studio/08_fabric_project_tree.md)[<img align="right" width="60" height="54" src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Next.png">](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_fabric_studio/10_fabric_studio_validating_java_code_within_a_project.md)
