# Query Builder Window

The Query Builder is an embedded visual query building component that allows you to build complex SQL queries on a selected [DB Interface](/articles/05_DB_interfaces/03_DB_interfaces_overview.md) using an intuitive interface. 
The Query Builder window has two tabs: 
* [Query](/articles/11_query_builder/02_query_builder_window.md#query-tab), where you can build and run an SQL query on selected DB Tables, Views or Synonyms. 
* [Results](/articles/11_query_builder/02_query_builder_window.md#result-tab), which displays the results of the executed SQL query. 

Note: 
The [DB Interface](/articles/05_DB_interfaces/03_DB_interfaces_overview.md) has a [Schema Filter](/articles/05_DB_interfaces/03_DB_interfaces_overview.md#schema-filter) setting which enables filtering the DB Schema’s list that is used by the Query Builder and the [DB Queries](/articles/07_table_population/01_table_population_overview.md) in the DB Interface.

### Query Tab
 The Query tab enables you to build and run an SQL query on selected DB Tables and is divided into the following working areas:
1. DB Tree (top left), enables selecting a Project’s [DB interface](/articles/05_DB_interfaces/04_creating_a_new_database_interface.md) from the DB Interfaces dropdown list and displays a tree of DB objects (Tables, Views, and Synonyms) and their columns of the selected DB interface.
2.	Main Window (top right), displays a graphical map of the selected tables and columns for the SQL query. 
3.	Selected Columns Table (bottom right), displays the same information as the Main Window in a tabular format and enables editing the SQL query. 
4.	Query Settings toolbar.
5.	Query display (bottom of the window), displays the generated SQL statement which can be edited manually.

![image](/articles/11_query_builder/images/12_2_3_query_builder_window.PNG)

### Result Tab
Displays the data that is received from the executed query. Click **Export to Excel** to export the data into an Excel file.   

<img src="/articles/11_query_builder/images/12_2_2%20Excel%20file..png" width="700pxl">

[![Previous](/articles/images/Previous.png)](/articles/11_query_builder/01_query_builder_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/11_query_builder/03_building_and_running_an_sql_query.md)
