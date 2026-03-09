### The Fabric 8.4.0 Official Release

We are excited to announce the release of Fabric 8.4.0! This version introduces significant enhancements to Fabric, Catalog and Web Studio, highlighted by built-in support for MCP (Model Context Protocol).  Here are some of the highlights of this release:

* **MCP Server Support**: Fabric now automatically exposes each Logical Unit (LU) and Data Product as an MCP server, providing built-in resources and tools for AI integration.
* The **Catalog** enhancements include among others the following main features:
  * **Advanced Catalog Management**: The Catalog now supports reverting to previous versions and deleting specific data platforms to clean up metadata. 
  * **Granular Artifact Building**: You can now build artifacts for a single data platform or the entire Catalog, featuring a real-time progress bar with estimated completion time. 
  * **Improved Discovery Monitoring**: The discovery monitor has been redesigned with vertical step details and automated expansion of error and warning sections for better visibility. 
* **NoSQL Query Builder** for NoSQL interfaces is now included in the Web Studio, allowing for seamless querying of non-relational data sources. 
* **Akeyless Secrets Management**: Security has been expanded to include support for Akeyless Secrets Management. 
* Fabric now supports parallel processing for Entity transfers to and from **Amazon S3** and **Microsoft Azure**, which significantly reduces the large entities’ transfer times.
* **High-Performance PostgreSQL Loading**: A new Broadway actor, **PgCopy**, enables high-speed bulk data inserts into PostgreSQL databases using the native COPY command. 

Refer to the [Release Notes](https://support.k2view.com/Academy/Release_Notes/V8.4/Fabric_Release_Notes_V8.4.0.pdf.html) for the full list of features and fixes. 

<img src="images/img.png" alt="image" style="zoom: 70%;" />
