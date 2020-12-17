# Data Table Editor

This screen displays table's data which can be edited, added, updated and deleted. The screen has two main areas:
- Top Action Bar.
- Main Table Data areas. 

<img src="images/30_dataeditor_04.png" alt="Table Data Editor" />

### Actions Bar

The **action bar** has the folllowing options:

* Fetch data for the IID, also if the screen is an inner table that is not the LU's root table. To do so, select the **sync-mode**, type in the **IID** and click **Fetch**.
* Click Info to display the synced information. Refer to the Schema Tables screen.
* Click Edit to switch to Edit mode.
* Click X on the IID chip to reset the LUI. 



### Table Data

The table data has the following options:

* Sort or filter data by table's field/column, by using table's heading actions.

* View and navigate to the linked tables - upper or lower in the scheme hierarchy.

  To do so - click on the ellipsis button appears at each table's entry. Once clicked - a pupup is opened that shows the linked tables, with a sign if it is upper or lower in tree. It also shows the population method - query or custom function name, ass can be seen in the below example.  

  Selecting a lower table drills down to its Table Editor screen, with content related only to its predecessor.

  

  In the below example, a customer LUI has several subscribers associated to and for each of them several invoices were generated. In the example the Invoice table is shown filtered by subscriber #13, that is - only invoices which are associated to subscriber #13 are shown.   

  <img src="images/30_dataeditor_05a.png" alt="Table Data Editor" />  

  The filtering is reflected at the top action chips area, which also contains subscriber #13 chip. Clicking on the X at this chip leads to removing this filter and whole table data will be shown.
  
  
  
  Note that only first 1000 entries are presented per table.
  
    

#### Table Edit Mode 

To edit the table's content - click on the **Edit** button at the top Actions Bar, switching to Edit Mode. Editing is available according to the permission that granted to the user. 

The table in this mode shown with:

* Delete button aside any of the table's entries. 
* An empty row at top, for adding a new entry. One or more entries can be added.

 Once an entry is added, edited or signed for deletion, it will be colored as following:

* new entry - blue
* Edited entry - orange
* Signed for deletion - red 

Any of the actions will take effect **only** after clicking on the **Update** button at the top Actions Bar.



As long as Update was not done, those actions are reversible: Click on the revert button icon that appears at edited or signed to deletion rows, or click on the Delete icon for a new entry that shall be removed.

 <img src="images/30_dataeditor_06.png" alt="actions colors" />





[![Previous](/articles/images/Previous.png)](05_data_editor_schema_viewer.md)
