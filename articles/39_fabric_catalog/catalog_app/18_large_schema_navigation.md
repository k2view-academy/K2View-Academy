# Large Schema Navigation

## Overview

The Catalog Navigator's main area enables navigation between different hierarchy levels by expanding and collapsing Catalog nodes. While the number of schemas under a data platform is typically low (usually in the tens), there might be thousands of datasets and foreign key relations under each schema. Given that each dataset may include hundreds of fields and each field has multiple properties, the total volume of data per schema can be significant.

All of the above makes navigating a large schema quite challenging, for the following reasons:

* Expanding the schema in the Catalog App requires retrieving a large amount of data from the server to the client and then rendering it on the client side. This process may become unresponsive and cause the browser to crash.
* Even if the schema eventually expands without crashing the browser, navigating a large schema is very difficult since the zoom level must be reduced significantly to see the full schema.

To improve the user experience when working with large schemas, Catalog introduces the Schema Explorer view. Schema Explorer displays the datasets in a list (instead of the tree view), allowing users to filter by dataset name and drill into individual datasets on demand. The list view is displayed automatically upon expanding a schema that contains more than 100 datasets; schemas with 100 or fewer datasets continue to display the standard tree view.

This feature is available starting from Fabric V8.5. 

## Schema Explorer View

When the discovery process runs, the total number of datasets is calculated for each schema, along with two other properties - total fields and total relations. 

Upon expansion, if the schema contains more than 100 datasets, the Schema Explorer opens automatically, displaying the full list of dataset names in alphabetical order.

The Schema Explorer supports the following actions:

* **Filter datasets** - to limit the displayed dataset list:

  * **Click** ![](../images/filter.png) in the upper-right corner and start typing the dataset name. The list of datasets is then limited to those that contain the typed string. Click **X** to clear the filter.
  * This is especially useful when working with a large schema that might include thousands of datasets. It helps users quickly find the required dataset without scrolling through a long list.


  ![](../images/schema_explorer_view_filter.png)

* **Resize the list view** by dragging the orange dot in the lower-right corner of the Schema Explorer. This is useful when some datasets have long names that don't fit within the default width of the Schema Explorer.

  ![](../images/schema_explorer_view.png)

* **View dataset properties and the *refersTo* relations** by clicking the dataset name in the Schema Explorer.

  ![](../images/schema_explorer_view_properties.png)

* **View dataset fields with their properties** by expanding the dataset in the Schema Explorer and clicking any of the fields in the list.

  ![](../images/schema_explorer_view_fields.png)

  * Complex fields can also be expanded within the Schema Explorer:

  ![](../images/schema_explorer_view_complex_fields.png)