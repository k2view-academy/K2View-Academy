# Create and Edit a Graphit file

### How Do I Create a New Graphit File?

1. Go to **Project Tree**, click **Web Services** > **Resources**.
2. Right click **New Resource Files** and select **New Graphit File**. 

    ![](/articles/15_web_services_and_graphit/17_Graphit/images/01_new_graphit_file.png)

3. Assign a **Name** to the new Graphit file and **Save** it. Note that the filename must have a **gr%** prefix and contain alpha-numeric characters. Once the file is saved, it is displayed under the project's Web Services folder under Resources.

    ![](/articles/15_web_services_and_graphit/17_Graphit/images/02_graphit_resource_file.png)


### How Do I Edit a Graphit File?
Once a new Graphit file is created, you can edit it to create the required CSV / XML / JSON document structure. 

A Graphit file is structured as a hierarchical representation of nodes, where each node defines a tag or condition in the structure of the CSV, XML or JSON document. 

Nodes can have children nodes and each child node can have children nodes, whereby creating nested tags in the generated document. 

When creating a document, the Node Name, Type and Properties can be defined for  each node. 

![](/articles/15_web_services_and_graphit/17_Graphit/images/03_edit_graphit_file.png)

### What Are the Hierarchical Structure Options? 

- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/04_plus.png" width="20" height="20"></img> **Parent Node** to create a new parent node under the original node on the same level.
- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/05_arrow.png" width="20" height="20"></img> **Child Node** to create a new child node under the parent node.
- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/06_trash_bin.png" width="20" height="20"></img> **Delete** to delete a node on the node level.  
- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/07_hamburger.png" width="20" height="20"></img> and drag a node to another location in the hierarchy.

### How Do I Assign a Name To a Node?
To assign a **Tag Name** to a **node**, hover over the left side of the node to display its frame and then type in the **Name**.   
-  Only nodes with a tag name are displayed in the output document. 
-  Nodes without tag names can be used for internal purposes.

[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/17_Graphit/01_graphit_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/17_Graphit/03_graphit_node_types.md)

