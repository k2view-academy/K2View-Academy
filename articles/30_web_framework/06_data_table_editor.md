# Data Editor Table

Information in the Data Editor is displayed in a table and can be edited, added, updated and deleted using the functions displayed in the top Actions bar.  

<img src="images/30_dataeditor_04.png" alt="Table Data Editor" />

### Actions Bar

<ul class="unchanged rich-diff-level-one">
<li class="unchanged">Fetch data for the IID, also if the screen is an inner table that is not the LU's root table. To do so, select the&nbsp;<strong>sync-mode</strong>, type in the&nbsp;<strong>IID</strong>&nbsp;and click&nbsp;<strong>Fetch</strong>.</li>
<li class="unchanged">Click Info to display the synced information. Refer to the Schema Tables screen.</li>
<li class="unchanged">Click Edit to switch to Edit mode.</li>
<li class="unchanged">Click X on the IID chip to reset the LUI.</li>
</ul>
<table style="border-collapse: collapse; width: 100%; height: 68px;" border="1">
<tbody>
<tr style="height: 17px;">
<td style="width: 12.4762%; height: 17px;">Fetch</td>
<td style="width: 87.5238%; height: 17px;">Click to fetch data for the IID. This option is also available for inner screens that are not the LU's root table.</td>
</tr>
<tr style="height: 17px;">
<td style="width: 12.4762%; height: 17px;">Info</td>
<td style="width: 87.5238%; height: 17px;">Click to display the synced data.&nbsp;</td>
</tr>
<tr style="height: 17px;">
<td style="width: 12.4762%; height: 17px;">Edit</td>
<td style="width: 87.5238%; height: 17px;">Click to switch to Edit mode.</td>
</tr>
<tr style="height: 17px;">
<td style="width: 12.4762%; height: 17px;">IID X</td>
<td style="width: 87.5238%; height: 17px;">Click to reset the LUI.</td>
</tr>
</tbody>  
</table> 


To fetch data, select the **sync-mode**, enter the **IID** and click **Fetch**.

For information about synced information, click Schema Tables.



### Table Data

Information in the data table can be filtered, sorted and navigated by clicking the XXX icons at the top of each column:  

-  To filter data, click XXX to open the XXX and enter the filtering criteria.
-  To go to a linked table and edit its hierarchy in the schema, click XXX, click the linked table and the click xxxx xxxx. 

The data table also displays the   To do so - click on the ellipsis button appears at each table's entry. Once clicked - a pupup is opened that shows the linked tables, with a sign if it is upper or lower in tree. It also shows the population method - query or custom function name, ass can be seen in the below example.  

  Selecting a lower table drills down to its Table Editor screen, with content related only to its predecessor.

  

In the following example, a customer LUI has several subscribers each having several invoices.  In the example the Invoice table is shown filtered by subscriber #13, that is - only invoices which are associated to subscriber #13 are shown.   

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
