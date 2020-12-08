# Override Data Catalog Tree

Occasionally there might be a need to modify the catalog structure prior to deploying it to the server. For example, if the table is supposed to be added to the Source DB interface but this change has not been performed yet by the 3rd party. Or if you need to reflect the future project changes.

Fabric Data Catalog enables performing manual updates of the catalog files, which includes adding new data entities or modifying the existing ones.

### How Do I Override Catalog Files?

1. Go to the **Resources** folder of the relevant project entity and create an **override.catalog** new resource file. 
2. Populate the nodes that describe the changes:
   * Add the new node description into the **override.catalog** file.
   * To update the existing node, copy the node from its respective **auto.catalog** file and modify it as needed.
3. Perform [Write Catalog]() - either from the Fabric Studio or from the server.

#### Override.catalog File Example

The following example adds a **Test2** Logical Unit with a **TestCustomer** table to the project's Data Catalog:

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



[![Previous](/articles/images/Previous.png)](04_catalog_command.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_data_catalog_setup.md) 
