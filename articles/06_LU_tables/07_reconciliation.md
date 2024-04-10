# Data product (LU) Tables Reconciliation

Tables Reconciliation allows you to analyze changes made to the connected source data platform tables upon which your project’s tables are based on. It provides a valuable solution for two primary scenarios, throughout project lifecycle:

1. **Data Product Business Entity Model Changes**: During the initial setup of project tables from source data platforms, implementers may decide to select  only those essential for the business entity model of the data product. Subsequent requirements may necessitate the incorporation of columns previously omitted. The Tables Reconciliation feature facilitates the identification of these columns for potential addition.
2. **Source Data Platforms changes**: Over the course of a project, changes may occur in the source data platform tables linked to it—columns could be added or removed. Tables Reconciliation can be used to spot these differences between the source and project tables, enabling informed decision-making within the Table Editor.

> Note: Tables Reconciliation process is relevant only for those tables which were built based on source data platforms, using the DB Explorer, since introducing this feature - Fabric 8.0. While an LU's table is built, the platform saves, as part of the table's metadata, based on which source data, schema and table it was created, so that the reconciliation process will be able to find mismatched, if exist. 



## Activating the Reconciliation Mismatches Finder

The first step for reconciliation is to find the mismatches. You can find mismatches according to either of 3 options: Find only added/removed tables columns which were added or removed at source, unused columns comparing to source, find all mismatches, includes added/removed/unused.

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



> Note: The mismatch finding process looks either directly at the source data platforms or at the Catalog's Discovery outcome , when it being used.
>



## Reconciliation Mismatches Finder Results

Upon mismatches finding process is concluded:  

- When mismatches are found, then
  - if you activated the finder within a schema, the relevant tables headers become purple colored. then you will be able to examine each table changes, by opening the table for editing, throughout the table's menu.
  - If you activated the finder within at a table you opened for editing from the project tree, you will the changes at the table, on the spot.
- If mismatches were not found a notification message appears at the bottom right side of the window. indicating it.



![](images/recon_schema_results.png)



> Note: When mismatch finding process is activated, the Reconciliation action icon becomes to be orange colored, hinting the user that Reconciliation is  turned on and "active".
>
> * When it turned on, you can click on the Reconciliation action icon to turn it off, hiding all the mismatches findings.
> * When it turned on, you can switch to one of the other mismatch finder options, by clicking on the down arrow and selecting it. You can do it as long as you did not take actions on the finder results. If you already took action, you shall save first your changes or discard them, before able to activate another option.



## Table Editor - Reconciliation Mode

Upon opening a table which was signed by the Reconciliation Mismatches Finder, the table editor is opened in *Reconciliation Mode*. 



![](images/recon_table_editor.png)

The green and red colored table's entries, are those which identified with changes at the data source. Gray colored entries are those which exists at the source data platform tables, but not at the corresponding LU table. 



> The above example, illustrates a case where there are various changes (added, removed, not-in-use columns). Usually, within a specific table, fewer changes will be done.   



## Reconciliation Actions

According to the Reconciliation Mismatches Finder results, as shown in the table editor screen, you can choose, for each one of the mismatched columns,  either to take action or to decide about it later, as following:

* **Added** at source:

  * *Do not Add* the column also to the project's table. 

    According to this decision, Studio will not alert you later that this column exists at the source. You still be able to find it when looking for unused columns.

  * *Add* the column also to the project's table.

  * *Decide Later* weather to add it or not. For example, you might not decide now because you wish to be advised by your team, if this column shall be added and used at project.

* **Removed** from source:

  * *Preserve* the column, although it was removed from the source. This probably useful for cases where this column is in use at implementation and need to be remained. Note that it is your responsibility from now on to populate its data, because the data will not be retrieved from source. 

    According to this decision, Studio will not alert you later that this column was removed at the source.

  * *Remove* from table and be aligned with the source table.

  * *Decide Later* weather to remove it or not. For example, For example, you might not decide now because you wish to be advised by your team, if this column shall be removed or maybe is still needed.

* **Not In Use** at the LU table, while existing at the source data table: 



Once you are done with handling a table, either if you made decision at the tables entries or not, you close it by:

* Click on OK: table considered as handled and your changes are preserved. In addition:
  * The table heading color becomes back to its "natural" bleu color
  * Opening the table over again, you will be able to see and examine the decision you made, as long as you did not save the change (saving the schema if you are in the schema editor, or saving the table if you are in table editing, opened from the project tree)
  * The Table Properties Source Origin (see below) indicates that you handled that table.
* Click Cancel: table considered as not handled and accordingly, the above accompanying implications do not hold.



Go next table



## 



## Table Properties Source Origin





## Highlighting Mismatched Tables

In case you deal with a big schema, containing with many tables, you can easily find those tables by using the Highlighting option:

1. At the top schema toolbar look for the ![](../03_logical_units/images/web/light-off.svg)Highlighting action icon.  

2. Click on the arrow aside and choose the *Mismatches vs Source* option.

   ![](images/schema_recon_bar_highlight.png)

3. As a result, the relevant tables become highlighted while other are grayed-out

> In case Reconciliation is not activated at the schema then this option is disabled
