<web>

# Report Layout Design

### Overview

Fabric's **Reports** application provides various controls to enable visualizing your data in many different ways. The examples of controls are:

* **Tables** - a flexible control that allows grouping, headers and footers, aggregates, sorting, etc. Individual cells can host other controls such as image and more. This report displays tabular data in rows and columns.
* **Lists** - a free-form layout for repeating data record.
* Various types of **charts**, **pivot tables** and many more tools. 

The reports can be created using numerous types of layouts:

* **Tabular report** is the most straightforward way to visualize your data, in a multicolumn, multirow fashion. A tabular report can group, sort and filter data, based on pre-defined conditions or user input.
* **Dashboard** is a dashboard-like report that allows combining several different controls, e.g., a chart and a summary table. The page orientation of such report can be defined as **Landscape**.
* **Master-detail report** is a report that can visualize two related data sets.
* And more... [Click for the list of demos describing various ActiveReportsJS layout features.](https://www.grapecity.com/activereportsjs/demos/)

### Tabular Reports

There are two ways to creating a data binding in a tabular report's layout:

1. Selecting the data set fields and drag-and-drop them into a report page - like this:

   ![](images/05_create_table_1.gif)

2. Dragging the Table control <img src="images/table_control.png"  /> from the report's toolbox. In this case, you still need to connect the table with respective data set fields - like this:

   ![](images/05_create_table_2.gif)

The next step should be the table formatting, which may include the formatting of the cells font size, color, alignment, for both the detail and the header rows.

All the above activities are done on report items using the Properties panel located on the right side of the report designer. Clicking on each report element (cell, column, detail row, header column, table) displays its properties. 

You can also create grouping, summarize the data on a group or header/footer level, apply conditional formatting, interactive sorting, create bookmarks and jump to them, and more. 

Once the table is added to the report page and data binding is in place, you need to click Preview to see the report layout with the data.

![](images/05_preview.png)

### Example

Below is an example of a tabular report that demonstrates several features:

![](images/05_tabular_report_example.gif)

[Click for the full user guide of Tabular report creation.](https://www.grapecity.com/activereportsjs/docs/ReportAuthorGuide/QuickStart/get-started-with-/Tutorial-1-Tabular-Report)



 [![Previous](/articles/images/Previous.png)](05_quick_data_binding_with_Fabric.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](07_report_viewer.md)

</web>
