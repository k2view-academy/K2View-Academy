### The Fabric 8.5.0 Official Release

We are excited to announce the release of Fabric 8.5.0. The release highlights are:

* **Studio AI** — an embedded, extensible AI assistant that builds, deploys, tests, and troubleshoots Fabric artifacts using specialized skills, K2View's AskMe knowledge base, and configurable LLM providers. Enabled through the *Studio AI Core Artifacts* extension.
* **Catalog** has several major enhancements, including:
  * **Large Catalog Navigation** — large schemas are displayed in a filterable dataset list, improving navigation and browser performance.
  * **OpenAPI Support** — OpenAPI 3.0 and 3.1 interfaces can now be discovered and cataloged using the new **OpenApiToMetadata** actor.
  * **Logical Relations** — new **Reference by Name Comparison** and **Reference by LLM** plugins identify potential relationships between datasets, including those without primary keys.
* **Affinity Management** — centrally define and maintain node affinities across the cluster through the Admin UI or `set_global affinity_rules` command.
* **API Key Authentication** — introduces three explicit key types: high-entropy **Access** keys, **Legacy** keys, and **Signing JWT** keys for client-signed, short-lived JWTs.
* **Authenticate API Mechanism** — `/authenticate` now distinguishes end-user sessions from server-to-server API calls and improves audit attribution for users and API Keys.

Refer to the [Release Notes](https://support.k2view.com/Academy/Release_Notes/V8.5/Fabric_Release_Notes_V8.5.0.pdf.html) for the full list of features and fixes.

<img src="images/img.png" alt="image" style="zoom: 70%;" />
