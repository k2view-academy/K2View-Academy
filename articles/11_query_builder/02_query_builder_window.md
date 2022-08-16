# Query Builder Window


The Query Builder window has two parts: 

* [Query](/articles/11_query_builder/02_query_builder_window.md#query-tab) -  where you can build and run an SQL query on selected DB Tables, Views or Synonyms. 
* [Results](/articles/11_query_builder/02_query_builder_window.md#result-tab) - displays the results of the executed SQL query. 

<studio>

### Query Tab

The Query tab enables you to build and run an SQL query on selected DB Tables and is divided into the following working areas:
1. DB Tree (top left) - enables selecting a Project’s [DB Interface](/articles/05_DB_interfaces/04_creating_a_new_database_interface.md) from the DB Interfaces dropdown list and displays a tree of DB objects (Tables, Views, and Synonyms) and their columns of the selected DB interface.
2. Main Window (top right) - displays a graphical map of the selected tables and columns for the SQL query. 
3. Selected Columns Table (bottom right) - displays the same information as the Main Window in a tabular format and enables editing the SQL query. 
4. Query Settings toolbar.
5. Query display (bottom of the window) - displays the generated SQL statement, which can be edited manually.

![image](images/12_2_3_query_builder_window.PNG)

### Result Tab
Displays the data received from the executed query. Click on **Export to Excel** to export the data into an Excel file.   

<img src="images/12_2_2%20Excel%20file..png" width="700pxl">

</studio>

<web>

When opened via the [DB Interface Explorer](/articles/04_fabric_studio/25_web_data_explorer.md), the Query Builder opens in the main panel on the right

![image](images/web/01_QB1.png)



When Opened from Schema Editor, Graphit or Broadway, the Query Builder opens as a popup window

![QB popup](/articles/03_logical_units/images/web/01_QB_WEB_popup3.png)



You can open Query Builder where Fabric is the data source. In this case, several fields will be shown at the Query Editor window top bar: Sync mode, Select LU, Instance ID. Fill in and set their values and accordingly the query will be executed.

![fabric top](images/web/01_fabric_interface_top_bar.png)



> You can control the sizes of the Query Editor and the Results windows: Hover your mouse cursor over the horizontal separating line, which appears between these parts, and you will see the ellipsis indication; your cursor type will turn to be a cursor-resize ![cursor-resize](images/web/cursor-resize.png). Use it to resize the windows heights.



</web>



[![Previous](/articles/images/Previous.png)](/articles/11_query_builder/01_query_builder_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/11_query_builder/03_building_and_running_an_sql_query.md)
