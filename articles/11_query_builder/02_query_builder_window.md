# Query Builder Window


The Query Builder window has 2 parts: 

* Query Builder -  where you can build and run an SQL query on selected DB Tables, Views or Synonyms. 
* Results - displays the results of the executed SQL query. 

<studio>

## Query Tab

The Query tab enables you to build and run an SQL query on selected DB Tables and is divided into the following working areas:
1. DB Tree (top left) - enables selecting a Project’s [DB Interface](/articles/05_DB_interfaces/04_creating_a_new_database_interface.md) from the DB Interfaces drop-down list, and it displays a tree of DB objects (Tables, Views, and Synonyms) and their columns of the selected DB interface.
2. Main Window (top right) - displays a graphical map of the selected tables and columns for the SQL query. 
3. Selected Columns Table (bottom right) - displays the same information as the Main Window in a tabular format and enables editing the SQL query. 
4. Query Settings toolbar.
5. Query display (bottom of the window) - displays the generated SQL statement, which can be edited manually.

![image](images/12_2_3_query_builder_window.PNG)

## Result Tab
Displays the data received from the executed query. Click on **Export to Excel** to export the data into an Excel file.   

<img src="images/12_2_2%20Excel%20file..png" width="700pxl">

</studio>

<web>

When opened via the [DB Interface Explorer](/articles/04_fabric_studio/25_web_data_explorer.md), the Query Builder opens in the main panel, on the right.

![image](images/web/01_QB2.png)



When opened from either the Schema Editor, Graphit or Broadway - the Query Builder opens as a popup window:

<img src="../03_logical_units/images/web/01_QB_WEB_popup3.png" style="zoom:67%;">



> You can control the sizes of the Query Editor and the Results windows: Hover your mouse cursor over the horizontal separating line, which appears between these parts, and you will see the ellipsis indication; your cursor type will turn to be a cursor-resize ![cursor-resize](images/web/cursor-resize.png). Use it to resize the windows' heights.



## Query Editor Panel

You can edit the SQL commands in the Query Editor panel - the upper part of the Query Builder window.  

It contains the editing area and top action bar, where you can run your query by clicking on the **Execute** button or clear the editing area by clicking on the **Clear** button.

> Note: You can open Query Builder where Fabric is the data source. In such case, several fields will be shown in the top bar of the Query Editor panel: Sync mode, Select LU, Instance ID. Set these fields before executing the query.
>
> ![fabric top](images/web/01_fabric_interface_top_bar.png)





## Results Panel

When executing the query, the results are presented in a table view in the Results window (bottom part).

* The Results window shows up to 1000 entries.

* The Results window's top bar is divided into 2 parts: information (on the right side) and actions (on the left side):

  * Information part contains: 
    * Number of displayed rows (as previously explained, up to 1000 rows).
    * Query status - success/failure. In case of a failure, a failure reason will be shown in the results area, instead of the results table.
    * Duration taken to execute the query. Note that the duration is not the time until the results table is rendered and displayed as a table in the results window.
    * How many rows were affected during the query execution. In case of a select statement, no rows are affected, so it would show zero.
  * Actions part contains:
    * Columns to show - allows to adjust the Results table display. When clicked, a pop-up opens where you can set up the Result tables columns that will be shown.
    * CSV - allows to download the results.

* The Results table enables some manipulations that let you adjust the display according to your needs, in addition to the *Columns to show* option:

  * Sort Order - click on the column's header sort icon ![sort](C:\Users\EyalOrbach\OneDrive - K2View\K2View-Academy-7.2\articles\11_query_builder\images\web\order_icon.png) to reorder the table (Ascending/Descending).

  * Filter - click on the column's header filter icon ![sort](C:\Users\EyalOrbach\OneDrive - K2View\K2View-Academy-7.2\articles\11_query_builder\images\web\filter_icon.png)to filter table's display accordingly.

  * Group - click on the column's header left arrow to group the table rows by this column. 

    * Grouping by a column will show it as the left most column, change its color to gray and flip the arrow direction.
    * Click on a table row arrow to expand and collapse its grouped rows.
    * You can group by several columns.
    * To ungroup, click again on the column header arrow.

    ![grouping](C:\Users\EyalOrbach\OneDrive - K2View\K2View-Academy-7.2\articles\11_query_builder\images\web\01_results_grouping.png)



</web>



[![Previous](/articles/images/Previous.png)](/articles/11_query_builder/01_query_builder_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/11_query_builder/03_building_and_running_an_sql_query.md)
