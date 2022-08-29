# Searching a Fabric Project

Fabric’s Search tool is a useful option for finding or replacing strings in any module within a Fabric project. A Search can be invoked anywhere and at any time to provide you with a quick reference to either the entire project, [Shared Objects](/articles/04_fabric_studio/12_shared_objects.md), References, Web Services or the [project’s LUs](/articles/03_logical_units/01_LU_overview.md).\
Searches can be made for the following Fabric components. 
* All modules
* Database types
* [Databases](/articles/05_DB_interfaces/04_creating_a_new_database_interface.md)
* Environments
* Generic [Interfaces](/articles/05_DB_interfaces/01_interfaces_overview.md)
* IID finder
* Instance groups
* [Java ](/articles/07_table_population/08_project_functions.md)
* Jobs
* Parsers
* Resources
* [Schema](/articles/03_logical_units/03_LU_schema_window.md)
* [Table population](/articles/07_table_population/01_table_population_overview.md)
* [Tables](/articles/06_LU_tables/01_LU_tables_overview.md)
* [Translations](/articles/09_translations/01_translations_overview_and_use_cases.md)
* Broadway

[Click here for more information about Project Structure.](/articles/04_fabric_studio/08_fabric_project_tree.md)

## How Do I Search for an Item?

<studio>

Fabric Studio offers different types of Search options, these include:
* Find a string.
* Search and replace.
* Additional search options.

### Searching for a String 

1. Do either of the following:
    a. Press **Ctrl+Shift+F**.
    b. Go to the **Tools tab** and click **Find in Files**.
    
    ![image](/articles/12_LU_navigation/images/10_02_01%20Tools%20tab.png)
    
2. Enter the **string** in the **Find What** field.
3.	Click the **Look in** dropdown list and select either the **Entire Project** or other selection options such as: a specific **Logical Unit** or **Web Services**.
4.	Click the **Search in Modules** dropdown list and select the **modules**.
5.	In the **Search Options** section, check the relevant match criteria, for example; **Match Case** or **Use Regular Expressions**. The **Show Only Matching Files** and **Include File Name in Search/Replace** options are enabled by default. 
6.	Click **Search**. A list of where the string is found is displayed in the **Search Results** area.
7.	Double click the search result’s item to open the **object**. 

### Search and Replace

Act with caution when using with the Replace option, be careful not to damage your project. 
1. Do either:\
    a. Press **Ctrl+Shift+F**.\
    b. Go to the **Tools tab** and click **Find in Files**.
    
    ![image](/articles/12_LU_navigation/images/10_02_02%20Find%20in%20Files.png)

2.	Enter the **string** in the **Find What** field.
3.	Click the **Look in** dropdown list and select either the **Entire Project** or other selection options like **Specific Logical Unit** or Web **Services**.
4.	Click the **Search In Modules** dropdown list and select the **modules**.
5.	In the **Search Options** section, check the relevant match criteria, for example; **Match Case** or **Use Regular Expressions**. The **Show Only Matching Files** and **Include File Name In Search/Replace options** are enabled by default. 
6.	In the **Replace with** field, enter the **Replacement String**, click **Replace** and click **OK** to confirm. All modules are closed, and the strings are replaced.

### Additional Search Options
* To search for a specific string in the code, press **Ctrl+F**. 
* To search for a specific string in the Project Tree, type the item name or part of it in the Project Tree **Search** bar (bottom left corner of the Studio) and press **enter**. The results are displayed in the Project Tree under the respective objects.

</studio>

<web>
    
Fabric Studio offers the following Search options:
    
* Search from the explorer tree.
* Search from within code
* Search from within a Logical Unit/Data Product schema.

### Search from the Explorer Tree 

Do either of the following:\
* Press **Ctrl+Shift+F**.\
  or
* Go to the **Main Menu** and select **View > Search**.
    
The Search box will open. Enter the search item here. 
    
![image](/articles/12_LU_navigation/images/10_03_09_SearchBox.png)

Note that there are options you can choose by clicking on one or more of the icons on the right of the box (Match Case, Match Whole Word, Use Regular Expression, and Include Ignored Files). 
    
You can also indicate which files to include or exclude, which can help to make your search more efficient. 
    
The search results will be displayed in the lower part of the left pane immediately (you do not have to press ENTER or any other similar action). 
    
### Search from Within Code

From a JAVA code sample that has been opened in the right side of the window, do **CTRL-F**. A Find box will appear.
    
![image](/articles/12_LU_navigation/images/10_03_08_FindBox.png)

You have similar options here to the search from the explorer tree, specifically: Match Case, Match Whole Word, and Use Regular Expression. 
    
The box will show you how many results have been found (eg. 3 out of 12). If there are no results, a "no results" message will appear. 
    
You can also narrow the search by selecting part of the code, then choosing the small hamburger option in the right side of the Find box ("Find in selection"). 
    
### Search from Within a Schema
    
From an open Schema's tool bar, click on the **Search table or group** drop-down menu, and enter the name of the table for which you are searching. 
    
![image](/articles/12_LU_navigation/images/10_03_03_ToolBar.png)
    
</web>    
    
    
    
[![Previous](/articles/images/Previous.png)](/articles/12_LU_navigation/01_Navigating_an_LU_schema.md)




