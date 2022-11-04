# Graphit Basic Editing

Once you've created a Graphit file, you can start editing it. To learn how to create a Graphit as web service - read [here]("/articles/15_web_services_and_graphit/06_custom_ws_create_graphit_ws.md" ). Similarly, you can create Graphit under any other Logical Unit.



## Graphit Terminology

* **Node** - an entry element, represented visually as a vertical strip, and which stands for an output document element and/or as a logical function.
* **Node Type** - defines how node content is generated and structured. For node types that have a representation in the output document, there may also be a definition relating to the node presentation. Some node types, such as SQL, Broadway and Get, provide additional helpers to ease the node content building.  
* **Node Property** - an additional instruction that can be given to a node. For example, how to format a number, which database to query, or whether the node is active/disabled.



## Basic Editing Concepts

* The Graphit editor is built from a main editing area, a top toolbar, and a right panel that opens when an action in the top toolbar is selected.

<img src="images\graphit_editor.png"  />

* The editor manages Graphit files that are built from nodes that can be added as either a sibling or a child of any other existing node, in the main editing area.
* Nodes can be moved or copied within a GraphIt file using Drag&Drop or Copy&Paste actions. A node can be copied and pasted between files.
* Upon a node creation, you shall first select its node type and accordingly accomplish its logic and/or output form. The node type can be changed later during editing. To learn more about *node types* - read [here](TBD). Note:
  * Not all nodes have an output representation, as they define a logical functionality.
  * Any node that has an output representation shall be named.
* You may use node properties to define some of the logic or the output form of a node. To learn more about *node properties* - read [here](TBD).


## Top Toolbar Actions

*  <img src="images/refraesh-icon.png"></img> Refreshing the opened testing results panel. It is enabled only when the result panel is opened.
*  <img src="images/url-icon.png"></img> Opening the URL Parameters and Properties panel. This panel shows different items by context.
*  <img src="images/show-output-json-icon.png"></img> Opening the JSON result panel 
*  <img src="images/show-output-xml-icon.png"></img> Opening the XML result panel 
*  <img src="images/show-output-csv-icon.png"></img> Opening the CSV result panel 
*  <img src="images/profiler-icon.png"></img> Opening the Graphit Profile panel 



## Nodes Editing Actions

Use the following actions for managing nodes such as create, copy, paste, move, delete:

- Click <img src="images/add_sibling.png"></img> on the left of a node, to create a new sibling node placed under the original node, on the same level. The icon appears when hovering over the node.
- Click <img src="images/add-child.png"></img> on the left of a node, to create a new child node under the parent node. The icon appears when hovering over the node.
- Click <img src="images/drag-icon.png" ></img> to drag a node to another location in the hierarchy. 
- Click  <img src="images/drag-open-icon.png" > to expand a collapsed node.
- Click <img src="images/delete_node.png" ></img> to delete a node on the node level. The icon appears when hovering over the node.
- Click <img src="images/copy.png" ></img> to copy a node so that it can be pasted into another location in this Graphit or to another Graphit file. The icon appears when hovering over the node.
- Click <img src="images/cut.png"></img> to copy a node so that it can be moved into another location in this Graphit or to another Graphit file. Moving nodes within a Graphit file can be also done via drag and drop. The icon appears when hovering over the node.
- Click <img src="images/paste_child.png" > to paste a previously copied/cut node as a child node. The icon appears when hovering over the node.
- Click <img src="images/paste_sibling.png" > to paste a previously copied/cut node as a sibling node. The icon appears when hovering over the node.



Use the following actions for node editing:

- Click <img src="images/plus-icon.png" > to add a property to the node
- Click <img src="images/db-icon.png" > to open Query builder for a *sql* node type
- Click <img src="images/selection.png" > to open Helper for a *get* and *Broadway* node types.



## Testing Graphit

You can easily execute and test your Graphit file by using the top tollbar actions.

Before testing, you shall populate the input parameters via the **Input Parameters & Properties** panel. Otherwise, upon executing, the result panel will inform you that they are missing.

Upon executing, for example clicking on the <img src="images/show-output-json-icon.png"></img> icon, a JSON **Result** panel would open, where you can see the outcome of the Graphit execution. Similarly and easily, you can examine the outcome of the file in other formats - XML or CSV, by clicking their action icons at the top toolbar.

You can leave this panel open and continue editing the file. Then, you can click on refresh <img src="images/refraesh-icon.png"></img> icon at the top tollbar to re-execute and refresh the outcome. This is very useful for debugging purposes.



You can also examine the Graphit's performance at the **Profiler** panel, which opens when selecting the <img src="images/profiler-icon.png"></img> icon in the top toolbar.







[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/17_Graphit/01_graphit_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/17_Graphit/03_graphit_node_types.md)

