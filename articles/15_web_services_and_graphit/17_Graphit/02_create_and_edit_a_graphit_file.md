# Create and Edit a Graphit file

### How Do I Create a New Graphit File?

<studio>

1. Go to **Project Tree**, click **Web Services** > **Resources**.
2. Right click **New Resource Files** and select **New Graphit File**. 

    ![](/articles/15_web_services_and_graphit/17_Graphit/images/01_new_graphit_file.png)

3. Assign a **Name** to the new Graphit file and **Save** it. Note that the filename must have a **gr%** prefix and contain alpha-numeric characters. Once the file is saved, it is displayed under the project's Web Services folder under Resources.

    ![](/articles/15_web_services_and_graphit/17_Graphit/images/02_graphit_resource_file.png)
    
</studio>

<web>

1. Go to **Project Tree**, click **Implementation** > **Logical Units/ Data Products**.
2. Expand **Web Services** > **Java** > **resources**
3. Right click **resources** and select **New Graphit**. 

    ![web graphit figure where you enter the name of the new graphit file]

3. Assign a name to the new Graphit file and press Enter to save it. Note that the filename must contain only alpha-numeric characters. Once the file is saved, it is displayed under the project's Web Services folder under resources.

    ![web graphit figure showing the new graphit file in the tree]
    
</web>

### How Do I Edit a Graphit File?
Once a new Graphit file is created, you can edit it to create the required CSV / XML / JSON document structure. 

A Graphit file is structured as a hierarchical representation of nodes, where each node defines a tag or condition in the structure of the CSV, XML or JSON document. 

Nodes can have children nodes and each child node can have children nodes, whereby creating nested tags in the generated document. 

When creating a document, the Node Name, Type and Properties can be defined for  each node. 

[do a web figure similar to the studio one below. And update the studio figure as well]

![](/articles/15_web_services_and_graphit/17_Graphit/images/03_edit_graphit_file.png)

### What Are the Hierarchical Structure Options? 

- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/04_plus.png" width="20" height="20"></img> **Parent Node** to create a new parent node under the original node on the same level.
- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/05_arrow.png" width="20" height="20"></img> **Child Node** to create a new child node under the parent node.
- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/06_trash_bin.png" width="20" height="20"></img> **Delete** to delete a node on the node level.  
- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/06_copy.png" width="20" height="20"></img> **Copy** to delete a node on the node level. 
- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/06_cut.png" width="20" height="20"></img> **Cut** to delete a node on the node level. 
- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/07_hamburger.png" width="20" height="20"></img> and drag a node to another location in the hierarchy.  (update this fig for studio)

### How Do I Assign a Name To a Node?
To assign a **Tag Name** to a **node**, hover over the left side of the node to display its frame and then type in the **Name**.   
-  Only nodes with a tag name are displayed in the output document. 
-  Nodes without tag names can be used for internal purposes.

[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/17_Graphit/01_graphit_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/17_Graphit/03_graphit_node_types.md)

