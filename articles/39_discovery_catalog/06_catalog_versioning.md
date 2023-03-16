<web>

# Catalog Versioning

The purpose of the Catalog versioning is to provide the tools to identify changes in the data source. Any kind of change is discoverable, for example, field adding/removal or a change in the property's value. 

Each time the Data Discovery process is executed, a new Catalog version is created in the *neo4j* Graph DB, only in case there are changes comparing to the previous version. Then, by using the Data Catalog application, the user can select a version to compare with the latest version. 

The differences between the versions are indicated using colors:

* New elements or relations are colored in green.
* Removed elements or relations are colored in red.
* When a property's value is updated, it is displayed twice – the removed value in red and the new value in green.
* In the versioning view, all the unchanged relations are colored in black (unlike the regular view where the links are colors in orange).



[![Previous](/articles/images/Previous.png)](05_catalog_app.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](xxx.md) 

</web>
