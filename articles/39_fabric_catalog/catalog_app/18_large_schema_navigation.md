# Large Schema Navigation

### Overview

The Catalog Navigator's main area enables navigation between different hierarchy levels, by expanding and collapsing Catalog nodes. While the number of schemas under a data platform is typically not very high (usually around tens of schemas), there might be thousands of datasets and foreign key relations under each schema. Considering the fact that each dataset might include hundreds of fields and each field has multiple properties, the overall amount of data in each schema might be huge.

All the above makes the navigation of a large schema quite difficult, for the following reasons:

* Expanding the schema in the Catalog App requires retrieving huge amount of data from server to client and then rendering it in the client-side. This process might even get stuck and cause the browser to crash. 
* Even if the schema is eventually expands without crashing the browser, navigating a large schema is very difficult since the zoom level should become very small in order to see the whole picture.

To improve the usability and the user experience for working with large schemas, Catalog introduces the Schema Explorer view. Schema Explorer displays the datasets in a list (instead of the tree view), allowing users to filter by dataset name and drill into individual datasets on demand. The list view is displayed automatically upon expansion the schema which contains more than 100 datasets; schemas with 100 or fewer datasets continue to display the standard tree view.

This feature is available starting from Fabric V8.5. 

### Schema Explorer View

When the discovery process runs, the total number of datasets is calculated for each schema, along with other two properties - total fields and total relations. 

Upon expansion, if the schema contains more than 100 datasets, the Schema Explorer opens automatically, displaying the full list of dataset names in alphabetical order.

The Schema Explorer supports the following actions:

* **Filter datasets** - to limit the displayed dataset list:

  * **Click** ![](../images/filter.png)in the upper-right corner and start typing the dataset name. The list of datasets is then limited to those which contain the typed string.
  * Very useful when working with a large schema which might include thousand of datasets. It helps finding quickly the required dataset without scrolling a long list.


![](../images/schema_explorer_view_filter.png)

* **Resize the list view** by dragging the orange dot in the lower-right corner of the Schema Explorer. This is useful when some datasets have long names that don't fit within the default width of the viewer.

  ![](../images/schema_explorer_view.png)

* **View dataset properties and the *refersTo* relations** by clicking the dataset name in the Schema Explorer.

  ![](../images/schema_explorer_view_properties.png)

* **View dataset fields with their properties** by expanding the dataset in the Schema Explorer and clicking any of the fields in the list.

  * Complex fields can also be expanded within the Schema Explorer. 

  ![](../images/schema_explorer_view_fields.png)