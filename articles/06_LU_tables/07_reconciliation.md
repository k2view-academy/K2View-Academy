# Data product (LU) Tables Reconciliation

Tables Reconciliation allows you to analyze changes made to the connected source data platform tables upon which your project’s tables are based on. It provides a valuable solution for two primary scenarios:

1. **Building Project Tables**: Implementors may initially exclude certain columns while creating project tables from source data platforms, selecting only those essential for the business entity model of the data product. However, there may later arise a need to add these previously skipped columns to the project’s table. Tables Reconciliation can be used to reveal these columns.
2. **Project Lifecycle Updates**: As the project progresses, the source data platform tables connected to it might be modified—columns could be added or removed. Tables Reconciliation can be used to spot these differences between the source and project tables, enabling informed decision-making within the Table Editor.

> Note: Tables Reconciliation process is relevant only for those tables which were built based on source data platforms, using the DB Explorer, since introducing this feature - Fabric 8.0.





·     Tables Reconciliation provides the capability for analyzing changes made to the connected source data platform tables upon which your project’s tables are based on. It provides a valuable solution for two primary scenarios:

o  Construction of Project Tables: During the initial setup of project tables from source data platforms, implementers may decide to exclude certain columns which are less relevant to the business entity model of the data product. Subsequent requirements may necessitate the incorporation of columns previously omitted. The Tables Reconciliation feature facilitates the identification of these columns for potential addition.

o  Modifications Throughout Project Lifecycle: Over the course of a project, changes may occur in the source data platform tables linked to it, such as the insertion or deletion of columns. Tables Reconciliation can be used to spot these differences between the source and project tables, enabling informed decision-making within the Table Editor.







## Activating the Reconciliation Mismatches Finder

You can find mismatches according to either of 3 options: Find only added/removed tables columns which were added or removed at source, unused columns comparing to source, find all mismatches, includes added/removed/unused.

You can trigger the mismatches finder either in schema level or per table:

#### Schema

Activating the mismatches finder at the schema - the main tables design surface - gives you an overview of mismatches of the LU (aka Data Product) schema's tables.

1. At the top schema toolbar look for the ![](../03_logical_units/images/web/reconciliation.svg) Reconciliation action icon. 
2. Click on the icon to activate the mismatches finder, or click on the arrow aside it and choose one of the 3 options.
3. As a result, mismatches finding process is starting.

![](images/schema_recon_bar_select.png)

#### Table

You can activate the mismatches finder on a specific table, rather than on LU schema:

* At the Schema, choose a table and click to open its Properties panel.

* At the project tree, choose the table and open it. Then click to open its Properties panel.

  

1. Switch to the *Data Source Origin* tab.
2. look for the ![](../03_logical_units/images/web/reconciliation.svg) Reconciliation action icon. You can click on the icon to activate the mismatches finder, or click on the arrow aside it and choose one of the 3 options.
3. As a result, mismatches finding process is starting.



![](images/recon_table_activate.png)



> Notes:
>
> * The mismatch finding process looks either directly at the source data platforms or at the Catalog's Discovery outcome , when it being used.
> * When mismatch finding process is activated, the Reconciliation action icon becomes to be orange colored, hinting the user that Reconciliation is  turned on and "active".
>   * When it turned on, you can click on the Reconciliation action icon to turn it off, hiding all the mismatches findings.
>   * When it turned on, you can switch to one of the other mismatch finder options, by clicking on the down arrow and selecting it. You can do it as long as you did not take actions on the finder results. If you already took action, you shall save first your changes or discard them, before able to activate another option.



## Reconciliation Mismatches Finder Results

Upon mismatches finding process is concluded:  

- When mismatches are found, then
  - if you activated the finder within a schema, the relevant tables headers become purple colored. then you will be able to examine each table changes, as explained below.
  - If you activated the finder within at table opened from the project tree, you will the changes at the table.
- If mismatches were not found a notification message appears at the bottom right side of the window. indicating it.



![](images/recon_schema_results.png)



## Table Editor - Reconciliation Mode

Examining a table changes, as found by the Reconciliation Mismatches Finder, you shall open the table for editing. The table editor is opened in *Reconciliation Mode*. 



![](images/recon_table_editor.png)





purple title

Click OK

click Cancel

Go next table



## Reconciliation Actions

For each one of the mismatched columns, you can choose either to take action or to decide about it later, as following:

* **Added** column at source:

  * *Do not Add* the column also to the project's table. 

    According to this decision, Studio will not alert you later that this column exists at the source. You still be able to find it when looking for unused columns.

  * *Add* the column also to the project's table.

  * *Decide Later* weather to add it or not. For example, you might not decide now because you wish to be advised by your team, if this column shall be added and used at project.

* **Removed** column from source:

  * *Preserve* the column, although it was removed from the source. This probably useful for cases where this column is in use at implementation and need to be remained. Note that it is your responsibility from now on to populate its data, because the data will not be retrieved from source. 

    According to this decision, Studio will not alert you later that this column was removed at the source.

  * *Remove* from table and be aligned with the source table.

  * *Decide Later* weather to remove it or not. For example, For example, you might not decide now because you wish to be advised by your team, if this column shall be removed or maybe is still needed.



## Table Properties Source Origin





## Highlighting Mismatched Tables

In case you deal with a big schema, containing with many tables, you can easily find those tables by using the Highlighting option:

1. At the top schema toolbar look for the ![](../03_logical_units/images/web/light-off.svg)Highlighting action icon.  

2. Click on the arrow aside and choose the *Mismatches vs Source* option.

   ![](images/schema_recon_bar_highlight.png)

3. As a result, the relevant tables become highlighted while other are grayed-out

> In case Reconciliation is not activated at the schema then this option is disabled
