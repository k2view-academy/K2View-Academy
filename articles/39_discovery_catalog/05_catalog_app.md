<web>

# Catalog Application

### Overview

The Catalog is accessed from the [K2View Web Framework](/articles/30_web_framework/01_web_framework_overview.md) by selecting the **Catalog** application from the context menu. Upon opening, the application displays the data from *neo4j* Graph DB and allows navigation from one level to another through a data model’s graph, expanding and collapsing various elements (nodes), viewing node's properties, searching the node, and more.

The Catalog application includes the following parts, described in this article:

* [Main area](05_catalog_app.md#main-area), which displays the Catalog tree with its elements.
* [Menu bar](05_catalog_app.md#menu-bar), which allows to perform various activities. 
* [Properties tab](05_catalog_app.md#properties-tab), which displays the properties of the selected element.

### Main Area

The Catalog's main area enables navigation between the hierarchy levels, by expanding and collapsing various elements.

The initial view displays the Data Platform elements (data source interfaces defined in the Fabric project), for which the Discovery process has been performed.

In case the Discovery process hasn't been executed on any project interface, the main area would be empty. 

The following activities can be performed using the entity's context menu:

* **Expand**<img src="images/expand.png" style="zoom:80%;" />or **collapse**<img src="images/collapse.png" style="zoom:80%;" /> the next level elements. 
  * For example, clicking the <img src="images/expand.png" style="zoom:80%;" /> icon of the Schema element expands all Data Sets under this Schema. 
  * A double-click on the selected entity can also expand or collapse it.
* **Hide** <img src="images/hide.png" style="zoom:80%;" />the element from the window.
* **Focus**<img src="images/focus.png" style="zoom:80%;" /> is available on the Data Platform and the Schema nodes. 
  * Clicking Focus opens a new view, which includes all the elements of the level below. 
  * For example, when Focus is clicked on the Schema element, the view will display all Class elements under this Schema.

### Menu Bar

The menu bar is a toolbar located at the top of the window. It includes the following choices:

* **Expand**, **collapse** and **hide**, triggering the same activities as in the element's context menu. Multiple element selection is supported.  
* **Layout selection** <img src="images/layout.png" style="zoom:80%;" />, enabling the Catalog layout. Horizontal (default), vertical or centered layout are supported.
* **Hidden nodes**, a drop-down list displaying the nodes that have been temporary hidden from the Catalog main area. Selecting a node from this list returns it back to the view.
* **Zoom in / out**, adjusting the zoom of the view.
* **Search**, providing a node search capability.

### Properties Tab

The Properties Tab displays the properties of the selected element. Some of the properties are created by the Crawler and some are created by plugins. Clicking the property name or the <img src="images/info.png" style="zoom:85%;" />icon next to the element name opens the More Info popup window, providing more details about the clicked property. 

Clicking on a property opens a Property Details pop-up that provides more details on the selected property.







[![Previous](/articles/images/Previous.png)](04_plugin_framework.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_catalog_versioning.md) 

</web>
