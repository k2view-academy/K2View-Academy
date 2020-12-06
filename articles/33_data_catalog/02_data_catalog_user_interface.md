# Data Catalog User Interface

The Data Catalog is a web application based on a distributed graph database (OLTP) – OrientDb (Apache 2). The catalog's UI enables the users to move through a data model’s graph, zoom in / out from one level to another and filter specific levels. The solution also has a Build Catalog option that creates a JSON file representing an entire project.

### How Do I Access the Data Catalog?

The Data Catalog is accessed from the [K2View Web Framework](/articles/30_web_framework/01_web_framework_overview.md). Select the **Data Catalog** from the context menu to display the circle representing your project. 

![image](images/33_02_proj.PNG)

### How Do I Navigate Through the Data Catalog?

The navigation through the Data Catalog is performed using the following tools:

* Search the required data element using the **Search** area in the top left corner of the screen. The search is performed using the **search-as-you-type** field type. The search results are presented in a table, enabling the user to either add an entity to the tree or to replace the tree focus to the selected entity.

  ![image](images/33_02_search.PNG)

* Change the tree view using one of the buttons in the top right corner of the screen to display it horizontally, vertically or using a centralized view. 

  ![image](images/33_02_view.PNG)

* Click on any of the data elements to display its properties, such as name, ID, type, etc. Click on the empty screen area to hide the Properties screen.

* Unselect or select data elements in the Legend (displayed in the down right corner of the screen) to remove or add various data types to the tree. For example, if COLUMN is unselected, the Column data elements will disappear from the tree.

[![Previous](/articles/images/Previous.png)](01_data_catalog_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](xxx.md) 