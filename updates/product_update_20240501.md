### The Fabric 8.0 Official Release

Fabric 8.0 has just been released and it includes the following new features/capabilities:

* Major upgrades: JDK 21, Tomcat 10, SQLite 3.45.2.
* Fabric can now use **PostgreSQL as an LUI storage layer**, where each business entity's instance is saved on a separate row in PostgreSQL.
* The Catalog can now parse text fields with a **complex structure** (JSON or XML), profile them and identify PII elements within the complex structure.
* Introducing **K2exchange** - a store-like platform for publishing and consuming Fabric’s modules that are not part of the product and that are hence considered as extensions. 
* New Broadway features: **Elseif** conditions, **Retry** mechanism for an actor in a flow and more.
* Fabric can now support a **hybrid cloud architecture**. For example: a Fabric cluster with 2 DCs: DC1 is on prem, DC2 is on cloud, and only one of the DCs has access to the source database.
* Schema reconciliation allows analyzing changes made to the tables upon which your project’s tables are based.
* Fabric’s dependencies are now isolated from the project’s dependencies by default. 

To get the full list of features and fixes, refer to the [Release Notes](https://support.k2view.com/Academy/Release_Notes_And_Upgrade/V8.0/Fabric_Release_Notes_V8.0.0.pdf.html) and [Upgrade Procedure to 8.0](https://support.k2view.com/Academy/Release_Notes_And_Upgrade/V8.0/Fabric_Upgrade_Procedure_To_V8.0.pdf.html).

<img src="images/img1.png" alt="image" style="zoom: 80%;" />
