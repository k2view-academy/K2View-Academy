<web>

# Catalog Versioning

The Fabric's Catalog supports versioning which allows to identify changes in the data source. Any kind of change is discoverable, for example, addiion of a field or change in a field's property value.

A new version is created in the *neo4j* Graph DB when the Discovery process runs again and the changes  are found. Note that if the Discovery process hasn't identified any changes comparing to the previous catalog version, a new version is not created.

The Catalog application allows the user to view each catalog version as well as to analyze the differences between the versions, as follows:

* Select a version from the list to get the full version view. 
  * By default, the latest version is displayed.

  * The coloring scheme of the standard view is blue.

  * The user can switch between various catalog versions. 
* Compare two versions to analyze the changes. 
  * To do so, click the compare <img src="images/compare.png" style="zoom:75%;" /> icon in the version's drop-down list.
  * The earlie version is compared to the later version. 
  * The Catalog displays all nodes and relations, indicating the delta of a later version comparing to an earlier versions using the colors, as follows:
    * New elements are green, removed elements are red, updated elements are blue.
    * When a property is updated, it is displayed twice –  the new value is green, the removed one is red.
    * All unchanged entities and relations are grey.
  
  * To return to the regular view mode, select a version from the list.
  





[![Previous](/articles/images/Previous.png)](05_catalog_app.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](07_manual_overrides.md) 

</web>
