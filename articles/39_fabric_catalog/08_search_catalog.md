# Catalog Search

### Overview

The Catalog application allows searching for Catalog objects (data platforms, schemas, datasets, fields and relations) within the currently displayed version. 

To start the search, click the ![](images/search.png) icon in the menu bar. It opens a text box, where you can enter one or more keywords - the names of the objects to be searched. To search by additional parameters, open the Advanced Search by clicking the ![](images/advanced.png) icon. To exit the search, click the ![](images/close.png) icon.

### Advanced Search

<img src="images/advanced_search.png" style="zoom:75%;" />

Advanced Search allows to narrow the search results by specifying one (or more) of the following parameters:

* Search the selected object type(s) only, for example - fields and datasets. 

* Search by PII and Classification properties. For example, when marking PII = true in the advanced search, the results list will include all nodes marked as PII.

* Search by score. For example, when the user enters 0.8,  the results list will return all Catalog objects with score 0.8 and below.

Note that once Advanced Search opens, each selection of the search criteria feeds the search text box at the top, using predefined syntax. For example, when searching by the keyword = phone, PII is true and object type is field, the search syntax is:

~~~
phone pii:true type:field
~~~

And vice versa, you can define your search criteria using syntax only in the text box, which will automatically feed back the search criteria fields. 

Click [here](10_catalog_APIs.md#search-catalog) for more details about the syntax of Catalog search.

### Search Results

<img src="images/search_results.png" style="zoom:75%;" />

The search results are presented in a list that can be exported into a CSV file. The result set returned to the client is limited to 750 nodes and 750 relations, meaning, one single search can yield a total of up to 1500 results.

To navigate from the search results to a node in the Catalog tree, click the ![](images/link.png) icon in the Name column. When navigating to a relation, the Catalog will focus on the FK column of the *refersTo* relation.





[![Previous](/articles/images/Previous.png)](07_manual_overrides.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](08a_filter_catalog.md) 

