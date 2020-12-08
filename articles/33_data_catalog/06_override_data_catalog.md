# Override Data Catalog 

Occasionally there might be a need to modify the catalog structure prior to deploying it to the server. For example, if the table is supposed to be added to the Source DB interface but this change has not been performed yet by the 3rd party. Or if you need to reflect the future project changes.

Fabric Data Catalog enables performing manual updates of the catalog files, which includes adding new data entities or modifying the properties of the existing entities.

### How Do I Override Catalog Files?

1. Go to the **Resources** folder of the relevant project entity and create an **override.catalog** new resource file. 
2. To add a new nodes to the catalog:
   * Add the new node description into the **override.catalog** file under:
   
     ~~~
     {
     	"nodes": [
     	
     	]
     }
     ~~~
   
3. Perform [Write Catalog]().

#### Example of override.catalog

The following override.catalog file adds a **Test2** Logical Unit with a **TestCustomer** table to the **testCatalogDB** project's Data Catalog:

~~~
{
	"nodes": [
		{
			"type": "SCHEMA",
			"id": "PROJECT:testCatalogDB.SCHEMA:Test2",
			"name": "Test2",
			"project": "testCatalogDB",
			"links": [
				{
					"type": "CONTAINED",
					"other": "PROJECT:testCatalogDB"
				}
			]
		},
		{
			"type": "TABLE",
			"id": "PROJECT:testCatalogDB.SCHEMA:Test2.TABLE:TestCustomer",
			"name": "TestCustomer",
			"project": "testCatalogDB",
			"properties": {
				"schema": "Test2",
				"fullTextSearch": false,
				"isCdc": false,
				"shouldTruncateTable": true,
				"rootObjectInLUDB": false,
				"numberOfColumnsInObject": 5
			},
			"links": [
				{
					"type": "CONTAINED",
					"other": "PROJECT:testCatalogDB.SCHEMA:Test2"
				}
			]
		}
	]
}
~~~



[![Previous](/articles/images/Previous.png)](05_data_catalog_navigation.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](07_OrientDB_setup.md) 
