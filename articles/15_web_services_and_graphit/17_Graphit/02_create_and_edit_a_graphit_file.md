# Create and Edit a Graphit file

***As a powerful low-code utility, Graphit enables web service planning and generation in a visualized layout.***

### How Do I Create a New Graphit File?

<studio>

Go to **Project Tree**, click on **Web Services** > **Graphit** > right-click & choose **New Graphit File**. 

![images](images/new_graphit_file_studio.png)

A Graphit file opens, containing a newly created Graphit node, which should be populated.

1. Start with choosing, via a drop-down arrow, any of the below commands/functions (node types): 

    ![](images/commands_functions_node_types.png)

    Initiate the example by choosing a **get** command

    It is useful to place the **get** command - a function that retrieves the LUI (Logical Unit Instance) - in the beginning of the Graphit layout although it can be implemented on any of the nodes. 

2. From the open pop-up window, select an LU from the drop-down list.

    ![](images/get_command_builder_select_lu_bigger.png)

    Click **Add**

    ![](images/get_command_builder_add.png)



The 1st created node in the Graphit window is complete

![](images/first_graphit_node.png)

You can change the **get** command by clicking on the ![](images/get_icon.png) icon, triggering the *Get Command Builder* pop-up window again.

3. Click on the plus icon ![](images/create_sibling_child_node.png)in order to add a sibling node to the example layout.

4. Populate the left-hand-field in the second node with *CUSTOMER_DATA* and choose an **sql** command from the right-hand-field using the drop-down arrow. ![](images/populate_second_node.png)

   ​

Clicking on the query builder icon ![](images/query_builder_icon.png) on the 2nd node, generates a query in a separate window, a Query Builder window.

In order to execute the query, insert an incident number.

For more information about a Query Builder, visit <https://support.k2view.com/Academy/articles/11_query_builder/01_query_builder_overview.html>

Following the creation of the 2nd node and its population, a message pops up, asking whether you want to create table fields; choose **YES**.

 ![](images/create_table_fields_message.png)

The below image shows the **nested nodes layout result**, which is followed by a pop-up message that asks you whether you wish to **save** the Graphit file.

![](images/saving_graphit_nested_nodes_layout.png)

Choosing **Yes** is followed by another pop-up message, asking you to assign a **Name** and a **Category** for your Graphit file.

![](images/new_item_name_and_category.png)

Once **Ok**'ed, the newly created Graphit file name appears at the **top of its layout** ![](images/graphit_layout_name.png) 

as well as in the **Project tree**, under its category.

![](images/project_tree_incl_graphit_file_name.png)

In both cases, you can see that it has automatically received a **'.graphit' suffix**.

</studio>

<web>

1. Go to **Project Tree**, click **Implementation** > **Logical Units/ Data Products**.
2. Expand **Web Services** > **Java** > **resources**
3. Right click **resources** and select **New Graphit**. 

    ![](/articles/15_web_services_and_graphit/17_Graphit/images/01_new_graphit_file_web.png)

4. Assign a name to the new Graphit file and press Enter to save it. Note that the filename must contain only alpha-numeric characters. Once the file is saved, it is displayed under the project's Web Services folder under resources.

    ![](/articles/15_web_services_and_graphit/17_Graphit/images/02_graphit_resource_file_web.png)

</web>

### How Do I Edit a Graphit File?
Once a new Graphit file is created, you can edit it to create the required CSV / XML / JSON document structure. 

A Graphit file is structured as a hierarchical representation of nodes, where each node defines a tag or condition in the structure of the CSV, XML or JSON document. 

Nodes can have children nodes and each child node can have children nodes, whereby creating nested tags in the generated document. 

When creating a document, the Node Name, Type and Properties can be defined for  each node. 

<studio>

![](/articles/15_web_services_and_graphit/17_Graphit/images/03_edit_graphit_file.png)
​    
</studio>

<web>

![](/articles/15_web_services_and_graphit/17_Graphit/images/03_edit_graphit_web_file.png)
​    
</web>

### What Are the Hierarchical Structure Options? 

<studio>
​    
- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/04_plus.png" width="20" height="20"></img> **Parent Node** to create a new parent node under the original node on the same level.
- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/05_arrow.png" width="20" height="20"></img> **Child Node** to create a new child node under the parent node.
- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/06_trash_bin.png" width="20" height="20"></img> **Delete** to delete a node on the node level.  
- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/06_copy.png" width="20" height="20"></img> **Copy** to delete a node on the node level. 
- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/06_cut.png" width="20" height="20"></img> **Cut** to delete a node on the node level. 
- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/07_hamburger.png" width="20" height="20"></img> and drag a node to another location in the hierarchy.  

</studio>

<web>
​    
- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/04_plus_web.png" width="20" height="20"></img> **Parent Node** to create a new parent node under the original node on the same level.
- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/05_arrow_web.png" width="20" height="20"></img> **Child Node** to create a new child node under the parent node.
- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/06_trash_bin_web.png" width="20" height="20"></img> **Delete** to delete a node on the node level.  
- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/06_copy_web.png" width="20" height="20"></img> **Copy** to delete a node on the node level. 
- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/06_cut_web.png" width="20" height="20"></img> **Cut** to delete a node on the node level. 
- Click <img src="/articles/15_web_services_and_graphit/17_Graphit/images/07_hamburger_web.png" width="20" height="20"></img> and drag a node to another location in the hierarchy. 

</web> 


### How Do I Assign a Name To a Node?
To assign a **Tag Name** to a **node**, hover over the field in which the name of the node is found, then type in the new name.   
-  Only nodes with a tag name are displayed in the output document. 
-  Nodes without tag names can be used for internal purposes.

[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/17_Graphit/01_graphit_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/17_Graphit/03_graphit_node_types.md)

