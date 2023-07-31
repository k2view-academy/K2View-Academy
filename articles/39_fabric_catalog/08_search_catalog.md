<web>

# Catalog Search

### Overview

The Catalog application provides a search of the catalog objects (data platforms, schemas, datasets, fields and relations) within the currently displayed version. 

To start the search, click the![](images/search.png) icon in the menu bar. It opens the text area where you can type a name of the object that you want to search. To search by additional parameters, open the advanced search by clicking the![](images/advanced.png) icon. To exit the search, click the![](images/close.png)icon.

### Advanced Search

<img src="images/advanced_search.png" style="zoom:75%;" />

The advanced search allows to narrow the search results by specifying one (or more) of the following parameters:

* Search the selected object type(s) only, for example - fields and datasets. 

* Search by PII and Classification properties. For example, when marking PII = true in the advanced search, the results list will include all nodes marked as PII.

* Search by score. For example, when the user enters 0.8,  the results list will return all catalog objects with score 0.8 and below.

### Search Results

<img src="images/search_results.png" style="zoom:75%;" />

The search results are presented in a list which can be exported into a CSV file. The returned result set is limited to 1,000 rows. 

To navigate from the Search Results to a node in the Catalog tree, click the![](images/link.png)icon in the Name column. When navigating to a relation, the Catalog will focus on the FK column of the *refers_to* relation.





[![Previous](/articles/images/Previous.png)](07_manual_overrides.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](09_build_artifacts.md) 

</web>
