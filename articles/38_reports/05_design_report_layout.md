# Designing Report Layout

### Overview

Fabric's **Reports** application provides various controls to enable visualizing your data in many different ways. The examples of controls are:

* **Tables** - a flexible control that allows grouping, headers and footers, aggregates, sorting, etc. Individual cells can host other controls such as image and more. This report displays tabular data in rows and columns.
* **Lists** - a free-form layout for repeating data record.
* Input fields, various types of charts, pivot tables, and many more. 

[Click for more details about various ActiveReportsJS layout features.](https://www.grapecity.com/activereportsjs/demos/)

### Tabular Reports

A tabular report is the most straightforward way to visualize your data, in a multicolumn, multirow fashion. A tabular report can group, sort, and filter the data based on pre-defined conditions or user input.

Tabular report starts by either:

* Dragging the Table control <img src="images/table_control.png"  /> from the report's toolbox.

* Or, selecting the data set fields and drag-and-drop them into a report page - like [here](images/05_create_table_1.gif):

  ![](images/05_create_table_1.gif)

When the first method is used, you still need to connect the table with respective data set fields. This can be accomplished by either dragging individual data set fields to the cells 