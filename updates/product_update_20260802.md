### The Fabric 8.5.0 Official Release

We are excited to announce the release of Fabric 8.5.0. The release highlights are:

* **Large Catalog Navigation**: The datasets of large schemas are now displayed in a filterable list view instead of the tree view, reducing rendering time and preventing browser slowdowns when exploring schemas with thousands of datasets, fields, and relations.
* **File Cataloging — OpenAPI Support**: File Cataloging now supports discovery of OpenAPI interfaces (V3.0 & 3.1), via the File Cataloging framework and a new **OpenApiToMetadata** actor that transforms OpenAPI specifications into Catalog's standard metadata format for the full Discovery pipeline, including classification and PII detection.
* **Search Catalog Enhancement**: The Catalog Advanced search now includes a **Limit by hierarchy** criterion, allowing users to scope searches to a specific Data Platform or Data Platform and Schema.
* **Creation of Logical Relations**: Two new plugins expand the *refersTo* relation creation — a **Reference by Name Comparison** plugin that scores field name similarity using the Jaro-Winkler algorithm, and a **Reference by LLM** plugin that analyzes dataset pairs with an LLM to identify possible foreign-key references. Both rules can create the relations even for datasets with no PK.
* **Affinity Management**: Fabric now supports centralized management of node affinities across the cluster — rules can be defined once per DC or for the entire cluster via the Admin UI or the new `set_global affinity_rules` command, with a dedicated Affinity Management Job distributing and maintaining the settings on all nodes.
* **API Key Security**: API Key-based authentication is strengthened with three explicit key types — a new high-entropy **Access** key (now the default), the existing **Legacy** name-as-key option, and a new **Signing JWT** type where the client signs its own short-lived JWT using a Fabric-issued signing key.
* **Authenticate API Mechanism**: The `/authenticate` endpoint now distinguishes end-user logins, which continue to receive a secure-cookie JWT, from server-to-server API calls, which explicitly request the JWT in the response body — with audit logs attributing each login to the resolved user or API Key.
* **SET INSTANCE_TTL** command has been enhanced to support Time To Live (TTL) management for MicroDBs stored in Google Cloud Storage (GCS), Azure Blob Storage, and Amazon S3.

Refer to the [Release Notes](https://support.k2view.com/Academy/Release_Notes/V8.5/Fabric_Release_Notes_V8.5.0.pdf.html) for the full list of features and fixes.

<img src="images/img.png" alt="image" style="zoom: 70%;" />
