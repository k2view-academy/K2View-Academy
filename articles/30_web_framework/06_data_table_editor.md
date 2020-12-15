# Data Table Editor

This screen enables viewing table's data as well as editing - add, update and delete data. It is built from two main areas - top **Action Bar** and main **Table Data** areas. 

<img src="images/30_dataeditor_04.png" alt="Table Data Editor" />

##### Actions Bar

The **action bar** provides the options to:

* Fetch data for the IID, even when the screen is of an inner table, i.e. not the LU's root table. To do so - select the sync-mode, type in the IID and click on Fetch
* View the sync information, using the info button, as described at the Scheme Tables screen.
* Switch to Edit mode, by clicking on the Edit button.
* Reset LUI - by clicking on the X at the IID chip 



##### Table Data

Table content is shown at this screen, enabling:

* Sort or filter data by table's field/column, by using table's heading actions.

* See and navigate to the linked tables - upper or lower in the scheme hierarchy.

  To do so - click on the ellipsis button appears at each table's entry. Once clicked - a pupup is opened that shows the linked tables, with a sign if it is upper or lower in tree. It also shows the population method - query or custom function name.  

  Selecting a lower table drills down to its Table Editor screen, with content related only to its predecessor.

  For example, if Customer LU has several subscribers associated to and for each of them invoices were generated, then at the Subscriber Table Editor screen select to drill down to Invoices. As can be shown in the screenshot below - only invoices for Subscriber #13 are shown.    

  <img src="images/30_dataeditor_05a.png" alt="Table Data Editor" />  

  Notice that this filtering is also reflected at the top action chips area, which also contains subscriber #13 chip. Clicking on the X at this chip leads to removing this filter and whole table data will be shown.

    

To edit the table's content - click on the **Edit** button at the top Actions Bar, switching to Edit Mode.

##### Table Edit Mode 

The table in this mode shown with:

* Delete button aside any of the table's entries. 
* An empty row at top, for adding new entry.

 Once entry is added/edited or signed for deletion, it will be colored as following:

* new entry - blue
* Edited entry - orange
* Signed for deletion - red 

Any of the actions will take effect **only** after clicking on the **Update** button at the top Actions Bar.

As long as Update was not done, those actions are reversible: Click on the revert button icon that appears at edited or signed to deletion rows, or click on the Delete icon for a new entry that shall be removed.

 