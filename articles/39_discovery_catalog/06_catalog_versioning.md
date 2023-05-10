<web>

# Catalog Versioning

The Fabric's Catalog supports versioning which allows to identify changes in the data source. Any kind of change is discoverable, for example, adding/removal of a field, change in the property's value and more.

A new version is created in the *neo4j* Graph DB when the Discovery process is executed and changes from the previous version are found.

By using the Catalog application, the user can:

* Switch between various Catalog's versions to get the full version view. 
  * By default, the latest version is displayed.

  * The coloring scheme of the standard view is: the nodes are blue and the links are orange.

* Compare two Catalog versions. In the compare mode, the Catalog displays all nodes and relations, indicating the delta of a later version comparing to an earlier versions using colors, as follows:
  * New elements are green.

  * Removed elements are red.

  * Updated elements are in blue.

  * When a property is updated, it is displayed twice –  new value is green, the removed one is red.

  * All unchanged entities and relations are grey.





[![Previous](/articles/images/Previous.png)](05_catalog_app.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](xxx.md) 

</web>
