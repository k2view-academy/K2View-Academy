### The Fabric 8.5.0 Official Release

We are excited to announce the release of Fabric 8.5.0. The release highlights are:

* **Catalog** has the following major enhancements:
  * **Large Catalog Navigation**: The datasets of large schemas are now displayed in a filterable list view instead of the tree view, reducing rendering time and preventing browser slowdowns.
  * **OpenAPI** interfaces (V3.0 & 3.1) are now supported using a new **OpenApiToMetadata** actor in the File Cataloging flows. 
  * **Creation of Logical Relations** is now enhanced by: a **Reference by Name Comparison** plugin new rule that scores field name similarity, and a new **Reference by LLM** plugin that analyzes dataset pairs with an LLM to identify possible FKs. Both rules can create the relations even for datasets with no PK.
* **Affinity Management**: Fabric now supports centralized management of node affinities across the cluster — defined once per DC or for the entire cluster via the Admin UI or the new `set_global affinity_rules` command, with a dedicated Affinity Management Job distributing and maintaining the settings on all nodes.
* **API Key-based authentication** is strengthened with three explicit key types — a new high-entropy **Access** key (now the default), the existing **Legacy** name-as-key option, and a new **Signing JWT** type where the client signs its own short-lived JWT using a Fabric-issued signing key.
* **Authenticate API Mechanism**: The `/authenticate` endpoint now distinguishes end-user logins, which continue to receive a secure-cookie JWT, from server-to-server API calls, which explicitly request the JWT in the response body — with audit logs attributing each login to the resolved user or API Key.
* **SET INSTANCE_TTL** command has been enhanced to support Time To Live (TTL) management for MicroDBs stored in Google Cloud Storage (GCS), Azure Blob Storage, and Amazon S3.

Refer to the [Release Notes](https://support.k2view.com/Academy/Release_Notes/V8.5/Fabric_Release_Notes_V8.5.0.pdf.html) for the full list of features and fixes.

<img src="images/img.png" alt="image" style="zoom: 70%;" />
