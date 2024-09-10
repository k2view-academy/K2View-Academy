# Catalog Application

### Overview

The Catalog application is accessible from the [K2view Web Framework](/articles/30_web_framework/01_web_framework_overview.md) by selecting the **Catalog** from the menu. Upon opening, the application displays the data retrieved from the *neo4j* Graph DB and it allows navigating from one level to another through a data model’s graph, expanding and/or collapsing various nodes, searching for nodes, viewing their properties, and more.

The Catalog has a top bar for navigation, which includes the following areas:

* The **Navigator** displays the Catalog tree. It allows to navigate through the Catalog and to perform various activities. The Navigator is described in this article, in the following sub-sections:

  * [Navigator's main area](05_catalog_app.md#navigators-main-area), which displays the Catalog tree.

  * [Menu bar](05_catalog_app.md#menu-bar), which allows to perform various activities. 

  * [Properties tab](05_catalog_app.md#properties-tab), which displays the properties of the selected node or relation.

  * Catalog's [deep linking](05_catalog_app.md#deep-linking) mechanism.

* The **Monitor** displays the Discovery job execution progress. It allows to either re-run the job for a selected data platform or stop the currently running job. [Click here to get more details about the Discovery job execution monitor](12_discovery_monitor.md). 

* The **Settings** allows to view and edit the pre-defined profiling and masking rules used by the Catalog. [Click here to get more details about the Settings tab](10_catalog_settings.md). 

### Navigator's Main Area

**General**

The Catalog Navigator's main area enables navigation between different hierarchy levels, by expanding and collapsing various nodes.

The initial view displays the Data Platform nodes (the data source interfaces defined in the Fabric project), for which the Discovery process is performed. In case the Discovery process hasn't been executed on any project interface, the main area would be empty. 

The Catalog Legend, which presents the 3 node types with their respective icons, allows to hide or unhide all the elements of the same type.

<img src="images/catalog_app.png" style="zoom:75%;" />

**Catalog's Version**

The Navigator displays the latest Catalog version by default. The version number is displayed in the upper-left corner of the main area. By clicking it, you can either: 

* **View** another version, by clicking its number in the drop-down list.
* **Compare** between 2 versions, by clicking the compare <img src="images/compare.png" style="zoom:75%;" /> icon.  

[Click here for more information about the Catalog Versioning](06_catalog_versioning.md).

**Data Platform and Schema's Context Menu**

A single click on any Data Platform or Schema node opens the context menu: 

<img src="images/dataplatform_collapsed_expanded.png" style="zoom: 67%;" />

The context menu allows performing the following actions:

* **Expand** <img src="images/expand.png" style="zoom:80%;" />or **Collapse** <img src="images/collapse.png" style="zoom:80%;" /> the next level elements. 
  * For example, clicking the <img src="images/expand.png" style="zoom:80%;" /> icon of the Data Platform expands all of its Schemas. 
  * A double-click on a selected node can either expand or collapse it.

* **Run Discovery** <img src="images/run_discovery.png" style="zoom:80%;" /> on the selected Data Platform; available in V7.2.1.

* **Hide** <img src="images/hide.png" style="zoom:80%;" /> an element from the window. A hidden element can be unhidden either from the Actions menu (as explained further in this article) or by reloading the Catalog.

* **Focus** <img src="images/focus.png" style="zoom:80%;" /> on the next level elements.
  * The Focus action differs from the Expand action in a way that it dives into the next hierarchy level, eliminating other nodes from the screen.
  * For example, when<img src="images/focus.png" style="zoom:80%;" />is clicked on a Schema node, the Catalog will only display the Dataset nodes of the selected Schema. 

  * In the Focus view, breadcrumbs are displayed in the upper-left corner of the main area, indicating your path within the Catalog tree. The breadcrumbs are clickable, and they allow to navigate up the tree.

<img src="images/breadcrumbs.png" style="zoom: 67%;" />

**Dataset Context Menu**

Clicking on any Dataset node opens its context menu, which includes the following actions:

* **Expand** <img src="images/expand.png" style="zoom:80%;" /> Dataset fields:

  * Click the <img src="images/expand.png" style="zoom:80%;" /> icon of the selected Dataset node to expand its fields. The Dataset node then changes its shape from a circle to a rectangle and displays field properties such as PK, FK and PII, where applicable.

    ​	<img src="images/dataset_collapsed_expanded.png" style="zoom: 67%;" />

  * To simultaneously expand all Datasets on the screen, click the <img src="images/expand-fields.png" style="zoom:75%;" /> icon on the Catalog's Legend:

    ​	<img src="images/legend.png" style="zoom: 67%;" />

  * To collapse an expanded Dataset, click the three dots in the corner of the node. To do so for all Dataset nodes together, click the <img src="images/eye.png" alt="." style="zoom:80%;" /> icon in the Catalog's Legend.

* **Hide** <img src="images/hide.png" style="zoom:80%;" /> an element from the window.

### Menu Bar

The menu bar is a toolbar located at the top of the window. It includes the following choices:

* An **Actions** menu that allows to:
  * **Edit Catalog**. [Click for more information about the Manual Overrides](07_manual_overrides.md).
  * View the list of **Hidden Nodes**. Clicking a node in this list, unhides it and the node returns to the Catalog tree.
  * **Build Artifacts**. [Click for more information about the Catalog artifacts](09_build_artifacts.md).
  * **Delete Catalog**. Clicking will delete all data from the neo4j GraphDB (once the user confirms his action). This feature is useful during the Development when a user wants to run Discovery on various data platforms and then re-build the Catalog from scratch on the same space. Available in V8.1.
* **Search** <img src="images/search.png" style="zoom:80%;" /> the Catalog. [Click for more information about the Catalog Search](08_search_catalog.md). 
* **Filter** <img src="images/filter.png" style="zoom:80%;" />the Catalog graph by property. When a filter is set, the icon changes its color to <img src="images/filter_selected.png" style="zoom:80%;" />. Available in V8.1. [Click for more information about the Catalog Filter](08a_filter_catalog.md). 
* **Hide** <img src="images/hide.png" style="zoom:80%;" />the node from the Catalog graph. Multiple element selection is supported.  
* **Show only connected** <img src="images/connected_only.png" style="zoom:80%;" />, for focusing only on a selected node with its predecessors and successors. When clicked, all other nodes are hidden, and the icon changes its color to <img src="images/connected_only_selected.png" style="zoom:80%;" />, indicating it is now in use. Clicking the icon again, unhides the other nodes and returns to the original view.
* **Run Discovery** <img src="images/run_discovery.png" style="zoom:75%;" />, for running the Discovery Job on the selected Data Platform.
* **Schema Insights** <img src="images/insights.png" style="zoom:75%;" />, for analyzing the Catalog's statistics in either a view version mode or a version comparison mode. The insights are currently available only on a Schema level.
* **Data Viewer** <img src="images/data_viewer.png" style="zoom:75%;" />, for connecting to the selected Fabric interface and querying the data directly from the data source.
* **Layout selection** <img src="images/layout.png" style="zoom:80%;" />, for selecting a Catalog layout. Centered (default), vertical or horizontal layouts are supported.
* **Zoom in / out**, for adjusting the zoom of the view.

### Properties Tab

The Properties Tab displays the selected element's name, the icon of the type and the element's properties.

​	<img src="images/properties.png" style="zoom: 67%;" />

Each element has different properties. For example, a Dataset field has properties such as **column size** and **source data type** - identified by the Crawler, and **PII** and **Classification** that are created by the plugins.

Clicking the property name or the <img src="images/info.png" style="zoom: 95%;" /> icon next to the element's name, opens the More Info pop-up window that provides more details about the property, e.g., the property's Origin, which could be either the Crawler, one of the plugins or manual.

When the selected element is a relation (a link) between 2 objects in the Catalog tree, the relation's properties are displayed. 

Note that in case there are more than one *refers to* relations between 2 Datasets, the Catalog tree displays only one link. However, the Properties Tab displays details of all relations. In the below example, there are 2 *refers to* relations between the **movie** and the **language** Datasets:

<img src="images/properties_two_links.png" style="zoom: 75%;" />

### Deep Linking

When navigating the Catalog tree and clicking a node, the node's path is added to the application URL using the following format:

~~~
/app/catalog/<version>/<data platform>/<schema>/<dataset>/<field>
~~~

Having a full node path allows sharing it as a direct link to a specific in-app location, thus saving the time and energy to try and locate a particular node.

The ```<version>``` should be either the word **latest** or **V** with the version number, for example:

~~~
/app/catalog/latest/CRM_DB/public/customer/customer_id
~~~

~~~
/app/catalog/V2/CRM_DB/public/customer/customer_id
~~~

<web>

The Catalog can also be accessed from the [DB Interface Explorer](/articles/04_fabric_studio/25_web_data_explorer.md) tab in the Web Studio, as explained [here](04a_catalog_integration_with_fabric.md#open-in-catalog).

</web>

[![Previous](/articles/images/Previous.png)](04a_catalog_integration_with_fabric.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_catalog_versioning.md) 

