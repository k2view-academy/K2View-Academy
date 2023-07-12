# Catalog Application

### Overview

The Catalog is accessed from the [K2View Web Framework](/articles/30_web_framework/01_web_framework_overview.md) by selecting the **Catalog** application from the context menu. Upon opening, the application displays the data from *neo4j* Graph DB and allows navigation from one level to another through a data model’s graph, expanding and collapsing various elements (nodes), viewing node's properties, searching the node, and more.

The Catalog application includes the following parts, described in this article:

* [Main area](05_catalog_app.md#main-area), which displays the Catalog tree.
* [Menu bar](05_catalog_app.md#menu-bar), which allows to perform various activities. 
* [Properties tab](05_catalog_app.md#properties-tab), which displays the properties of the selected node or relation.

### Main Area

**General Navigation**

The Catalog's main area enables navigation between the hierarchy levels, by expanding and collapsing various elements.

The initial view displays the Data Platform nodes (the data source interfaces defined in the Fabric project), for which the Discovery process has been performed. In case the Discovery process hasn't been executed on any project interface, the main area would be empty. 

The Legend presents three types of nodes with their respective icon. 

<img src="images/catalog_app.png" style="zoom:75%;" />

**Catalog's Version**

When the catalog application opens, the latest **catalog version** is displayed by default. The version number is displayed in the upper left corner of the main area. By clicking it, you can either: 

* Select another version from the list in order to view it.
* Click the compare <img src="images/compare.png" style="zoom:75%;" /> icon to perform the versions comparison analysis.  

Click [here](06_catalog_versioning.md) for more details regarding the Catalog Versioning.

**Data Platform and Schema's Context Menu**

Clicking on any Data Platform or Schema node opens the context menu which allows to perform the following:

* **Expand** <img src="images/expand.png" style="zoom:80%;" />or **collapse** <img src="images/collapse.png" style="zoom:80%;" /> the next level elements. 
  
  * For example, clicking the <img src="images/expand.png" style="zoom:80%;" /> icon of the Data Platform expands all of its Schemas. 
  * A double-click on the selected node can also expand or collapse it.
  
* **Hide** <img src="images/hide.png" style="zoom:80%;" /> the element from the window. The hidden element can be unhidden either from the Actions menu (as explained further in this article) or by reloading the catalog.

* **Focus** <img src="images/focus.png" style="zoom:80%;" /> opens a view, which includes only the elements of the level below the selected node.
  
  * For example, when<img src="images/focus.png" style="zoom:80%;" />is clicked on a Schema node, the view will display all Dataset nodes under this Schema.
  
  * In the Focus view, the breadcrumbs are displayed in the left upper corner of the main area to indicate your path within the Catalog tree. The breadcrumbs are clickable and allow you navigating up the tree.  
  
    <img src="images/breadcrumbs.png" style="zoom: 67%;" />

**Dataset Context Menu**

Clicking on any Dataset node opens the context menu which allows to perform the following:

* **Expand** <img src="images/expand.png" style="zoom:80%;" />the Dataset fields:

  * Click the <img src="images/expand.png" style="zoom:80%;" /> icon of the selected Dataset node to expand its fields, so the Dataset changes:

    <img src="images/dataset_collapsed_expanded.png" style="zoom:75%;" />

  * To expand all Datasets on the screen at once, click the <img src="images/expand-fields.png" style="zoom:75%;" /> icon on the Catalog's legend:

    <img src="images/legend.png" style="zoom:75%;" />

* **Hide** <img src="images/hide.png" style="zoom:80%;" /> the element from the window.

### Menu Bar

The menu bar is a toolbar located at the top of the window. It includes the following choices:

* The **Actions** menu allows to:
  * **Edit** the catalog manually. Click [here](07_manual_overrides.md) for more details regarding the Manual Overrides.
  * Open the **Classifier Configuration** screen, to update the profiling rules. This screen is described [further in this article](05_catalog_app.md#classifier-configuration-window). 
  * View and unhide the **hidden nodes**. Selecting a node from this list returns it back to the Catalog tree.
* **Search** <img src="images/search.png" style="zoom:80%;" />the catalog. Click [here](08_search_catalog.md) for more details regarding the Catalog Search. 
* **Expand**, **collapse** and **hide**, triggering the same activities as using the node's context menu. Multiple element selection is supported.  
* **Layout selection** <img src="images/layout.png" style="zoom:80%;" />, enabling the Catalog layout. Horizontal (default), vertical or centered layout are supported.
* **Zoom in / out**, adjusting the zoom of the view.

### Properties Tab

The Properties Tab displays the selected element's name, the icon of the type and the element's  properties.

<img src="images/properties.png" style="zoom: 67%;" />

Clicking the property name or the <img src="images/info.png" style="zoom:80%;"/> icon next to the element name opens the More Info popup window, providing more details about the property. For example, the property Origin, which can be the crawler, one of the plugins or manual.

### Classifier Configuration Window

The Classifier Configuration window allows to view and update the profiling rules invoked by the Catalog built-in plugins which are described [here](04_plugin_framework.md#built-in-plugins).

<img src="images/classifier.png" style="zoom: 67%;" />

The following restrictions should be considered when updating the data via this window:

* The combination of Classification & Type must be unique.
  * For example, you can define Classification = EMAIL twice: with Type = Data and with Type = Metadata, but you cannot define it twice with the same type.
* The PII must be aligned for two entries with the same Classification.
  * For example, when defining two entries for Classification = EMAIL, the PII indicator of both of them should be either true or false.





[![Previous](/articles/images/Previous.png)](04a_catalog_integration_with_fabric.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_catalog_versioning.md) 

