### The Fabric 8.5.0 Official Release

We are excited to announce the release of Fabric 8.5.0. The release highlights are:

* **Large Catalog Navigation**: The datasets of large schemas are now displayed in a filterable list view instead of the tree view, reducing rendering time and preventing browser slowdowns when exploring schemas with thousands of datasets, fields, and relations.
* **File Cataloging — OpenAPI Support**: File Cataloging now supports discovery of OpenAPI interfaces (V3.0 & 3.1), via the File Cataloging framework and a new **OpenApiToMetadata** actor that transforms OpenAPI specifications into Catalog's standard metadata format for the full Discovery pipeline, including classification and PII detection.
* **Search Catalog Enhancement**: The Catalog Advanced search now includes a **Limit by hierarchy** criterion, allowing users to scope searches to a specific Data Platform or Data Platform and Schema.
* **Data Quality Metrics Plugin**: Enhanced with additional metrics and input parameters, plus a new safeguard that skips min/max value calculation for fields marked as PII.
* **Reference by Name Comparison Plugin**: A new *fieldNameCompare* rule uses the Jaro-Winkler similarity algorithm to create *refersTo* relations between fields based on name similarity score.
* **Reference by LLM Plugin**: A new plugin identifies possible foreign-key references between datasets by analyzing dataset pairs with an LLM and creating corresponding *refersTo* relations.
* **Catalog Settings Usability Enhancements**: Smoother filtering in the Classifier Regex, PII & Masking, and Sequences tabs, plus the ability to disable (instead of delete) classification rules in the Classifier Regex tab.

Refer to the [Release Notes](https://support.k2view.com/Academy/Release_Notes/V8.5/Fabric_Release_Notes_V8.5.0.pdf.html) for the full list of features and fixes.

<img src="images/img.png" alt="image" style="zoom: 70%;" />
