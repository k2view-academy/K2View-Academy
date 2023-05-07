<web>

# Catalog Versioning

The Fabric's Catalog supports versioning which serves as a the tools to identify changes in the data source. Any kind of change is discoverable, for example, field adding/removal or a change in the property's value. 

A new version is created in the *neo4j* Graph DB when the Discovery process is executed and changes from the previous version are found.

By using the Catalog application, the user can:

* Switch between various Catalog's versions to get the full version view. By default, the latest version is displayed.

* Compare two Catalog versions. In the compare mode, the differences between the versions are indicated using colors, as follows:

  * New elements or relations are colored in green.

  * Removed elements or relations are colored in red.

  * A update in a Data Set (e.g. new field) is colored in blue.

  * An updated property is displayed twice –  the new property is green, the removed one is red.

  * All unchanged entities and relations are colored in dark grey color (unlike the regular view where nodes are blue and links are orange).




[![Previous](/articles/images/Previous.png)](05_catalog_app.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](xxx.md) 

</web>
