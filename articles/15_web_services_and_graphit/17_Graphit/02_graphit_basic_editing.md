# Graphit Basic Editing

Once your created a Graphit file you can start edit it. To learn how to create a Graphit as web service read [here]("/articles/15_web_services_and_graphit/06_custom_ws_create_graphit_ws.md" ). Similarly you can create Graphit under any other logical unit.



## Graphit Terminology

* **Node** - an entry element, represented visually as a vertical strip, and which stands for an output document element and/or as a logical function.
* **Node Type** - define how node content is generated and structured. For node types which have a representation in the output document it also might define how a node is presented. Some of the node types, such as SQL, Broadway and Get, provide additional helpers to ease the node content building.  
* **Node Property** - an additional instruction that can be given to a node. For example, how to format a number, which database to query, or if the node is active or disabled.



## Basic Editing Concepts

* The Graphit editor is built from a main editing area, a top toolbar, and a right panel which is opened when an action in the top toolbar is selected.

<img src="images\graphit_editor.png"  />

* The editor manages Graphit files which are built from nodes that can be added as a sibling or as a child of any other existing node, in the main editing area.
* Nodes can be moved or copied within a GraphIt file using Drag&Drop or Copy&Paste actions. Node can be copied and pasted between files.
* On node creation, you shall first select its node type and accordingly accomplish its logic and/or output form. Node's type can be changed later during editing. To learn more about *node types* read [here](TBD). Note:
  * Not all nodes have an output representation, as they define a logical functionality.
  * Any node that has an output representation shall be named.
* You might use node properties to define some of the logic or the output form of  a node. To learn more about *node properties* read [here](TBD).

  

## Top Toolbar Actions

*  <img src="images/refraesh-icon.png"></img> Refreshing the  opened testing results panel. It is enabled only when the result panel is opened.
*  <img src="images/url-icon.png"></img> Opening the URL Parameters and Properties panel. This panel shows different items by context.
*  <img src="images/show-output-json-icon.png"></img> Opening the JSON result panel 
*  <img src="images/show-output-xml-icon.png"></img> Opening the XML result panel 
*  <img src="images/show-output-csv-icon.png"></img> Opening the CSV result panel 
*  <img src="images/profiler-icon.png"></img> Opening the Graphit Profile panel 



## Nodes Editing Actions

Use the following actions for managing nodes such as create, copy, paste, move, delete:

- Click <img src="images/add_sibling.png"></img> at left of a node, to create a new sibling node placed under the original node, on the same level. Appears on hoovering the node.
- Click <img src="images/add-child.png"></img> at left of a node, to create a new child node under the parent node. Appears on hoovering the node.
- Click <img src="images/drag-icon.png" ></img> to drag a node to another location in the hierarchy. 
- Click  <img src="images/drag-open-icon.png" > to expand a collapsed node.
- Click <img src="images/delete_node.png" ></img> to delete a node on the node level.  Appears on hoovering the node.
- Click <img src="images/copy.png" ></img> to copy a node so that can be paste into another location at this Graphit or to another Graphit file. Appears on hoovering the node.
- Click <img src="images/cut.png"></img> to copy a node so that can be moved into another location at this Graphit or to another Graphit file. Moving nodes within a Graphit file can be done also via drag and drop. Appears on hoovering the node.
- Click <img src="images/paste_child.png" > to paste a previously copied/cut node as a child node. Appears on hoovering the node.
- Click <img src="images/paste_sibling.png" > to paste a previously copied/cut node as a sibling node. Appears on hoovering the node.



Use the following actions for node editing:

- Click <img src="images/plus-icon.png" > to add property to the node
- Click <img src="images/db-icon.png" > to open Query builder for a *sql* node type
- Click <img src="images/selection.png" > to open Helper for a *get* and *Broadway* node types.



## Testing Graphit

You can easily execute and test your Graphit file by using the top tollbar actions.

Before testing you shall populate the input parameters via the **Input Parameters & Properties** panel. Otherwise, on executing, the result panel will inform you that they are missing.

On executing, for example clicking on the <img src="images/show-output-json-icon.png"></img> icon, a JSON **Result** panel will be opened, where you can see the outcome of the Graphit execution. similarly and easily you can examine the outcome of the file in other format - XML or CSV, by clicking their action icons at the top toolbar.

You can leave this panel opened and continue editing the file. Then you can click on refresh <img src="images/refraesh-icon.png"></img> icon at the top tollbar to re-execute and refresh the outcome. This is very useful for debugging purposes.



You can also examine the Graphit's performance at the **Profiler** panel which is opened when choosing the <img src="images/profiler-icon.png"></img> icon at the top toolbar.







[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/17_Graphit/01_graphit_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/17_Graphit/03_graphit_node_types.md)

