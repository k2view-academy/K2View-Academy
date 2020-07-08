# Broadway Data Inspector

**Data Inspector** is a Broadway capability to identify and display the complex data structures including their schema and the values which are passed between two Stages. It is applicable for **Object** data types only.

The data inspector can be open using a small **plus sign** next to the Actor's output argument. When pressing on it, the **yellow segment** is expanded and it displays the schema on its left side and the data values on its right side. Note that the data values are displayed only when the flow runs in [Debug mode](<!--Link to 26-Flow window- run + debug flow-->). 

![image](/articles/99_Broadway/images/99_27_01.PNG)

### How Do I Edit the Schema Using the Data Inspector?

If needed, the schema can be edited at the run time. Click on the yellow segment next to the schema element which you want to edit to open the context menu. 

<table style="width: 900px;">
<tbody>
<tr>
<td rowspan="7" width="420pxl">
<p><img src="/articles/99_Broadway/images/99_27_02.PNG" alt="Context menu" /></p>
</td>
<td width="60pxl">Add Child</td>
<td width="420pxl">
<p>Add a child to the selected element using the Link bar. The Link bar will become available in the main menu area, to define the name and the type of the new element.</p>
<p><img src="/articles/99_Broadway/images/99_27_03.PNG" alt="Add Child" /></p>
<p>Note that this menu item is available only when clicking on the parent node</p>
</td>
</tr>
<tr>
<td width="200">Add Sibling</td>
<td style="width: 465px;">
<p>Add a sibling to the selected element using the Link bar.</p>
</td>
</tr>
<tr>
<td width="200">Edit</td>
<td style="width: 465px;">Edit the element's name or type using the Link bar.</td>
</tr>
<tr>
<td width="200">Delete</td>
<td style="width: 465px;">Delete the element from the schema.</td>
</tr>
<tr>
<td width="200">Move Up</td>
<td style="width: 465px;">Move the element up the schema.</td>
</tr>
<tr>
<td width="200">Move Down</td>
<td style="width: 465px;">Move the element down the schema.</td>
</tr>
<tr>
<td width="200">Link</td>
<td style="width: 465px;">Link the element to a target Actor using the Link bar.</td>
</tr>
</tbody>
</table>

### How Do I Edit the Schema Using the Data Viewer?

Additional way to edit the schema is using the Data Viewer. Click **Edit Schema** in the output argument's properties to open **Edit Schema** pop-up window.
Then you can edit the schema manually and submit your change. The yellow segment will be refreshed and will display the updated schema.

![image](/articles/99_Broadway/images/99_27_04_data_viewer.PNG)

