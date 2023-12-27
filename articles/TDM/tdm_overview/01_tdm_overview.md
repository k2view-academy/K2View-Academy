# What is the Test Data Management (TDM) Platform? 

<a href="https://www.k2view.com/products/test-data-management/" target="_blank">Test Data Management (TDM)</a> offers an automated solution for data provisioning. The provisioned data can either include tables or be organized according to primary business entities of a company (e.g. customers, orders, patients, products or households). The business entities' data can be either retrieved from various data sources or synthetically generated. This solution provides real, high-quality data for testing teams.

While enterprise IT continues to adopt DevOps to accelerate the delivery of solutions to both internal and external customers, one thing still holds them back - provisioning realistic data to test these solutions in a timely manner. With increasing complexity and system interdependencies, testing budgets now consume a large portion of all IT resources, and yet, the time required for testing brings the agility of DevOps to a halt.

One of the main challenges in providing real data for testing teams is that data is often split between different data sources. For example, a Customer may be broken up between Customer Care, Billing, Ordering, Ticketing and Collection systems. To run functional tests on a Customer in an integrative testing environment, the customer's data must be extracted from all relevant source systems.

The K2view patented [MicroDB](/articles/01_fabric_overview/02_fabric_glossary.md#mdb--microdb) - a data lake for each Data Product instance - ensures a smooth data provisioning, based on the company's business needs rather than running a separate copy on each data source.

### Key TDM Features

- Built-in self-service web application, where testers can request data to be provisioned on-demand and in real-time. 
- Central storage of provisioned entities in Fabric.
- Copying data into live testing environments.
- Request for a subset of entities for data provisioning:
  - Provisioning predefined lists of Business Entities from a selected source environment to a selected target environment. All data related to the selected entities is extracted and copied to the relevant data systems.
  - Copying a subset of entities based on predefined parameters. For example, selecting from a source some customers based in NY with small business packages and copying this selection to the testing environment.
- Rule-Based Synthetic Data Generation - generating synthetic entities on demand. The synthetic entities can be either generated and saved in Fabric in order to be loaded later into the testing environment, or generated and loaded into the target environment in one task. The user can set values in a selected list of data generation parameters for the data generation flow. For example, generate customers where 30% live in NY and 70% live in TX.
- Entity clone - cloning a given entity into the target environment. TDM replaces the sequences of each clone to avoid sequence duplication.
- Replacement of sequences of provisioned entities to avoid duplication in the target DB. 
- Entity reservation - enables the user to reserve entities in the testing environment to prevent other users from deleting or reloading these entities in the environment until the user completes their functional testing.
- Automatic data security and masking on an entity-by-entity basis.
- Support for up-to-date data of the selected entities.
- Cross-application integrity.
- Data Versioning features:
  - Users can save (extract) specific versions of either a selected list of entities or a selected list of metadata (reference) tables.
  - Users can load either a selected version of entities or metadata tables to the selected target environment.
-  Data provisioning - can be done either on-demand or automatically (based on scheduling parameters). For Example: automatic data provisioning every Monday, 08:00 AM.



### Getting Started

Learn about the TDM platform's main modules:

- [TDM Architecture](/articles/TDM/tdm_architecture/01_tdm_architecture.md).
- [TDM Implementation and Setup](/articles/TDM/tdm_implementation/02_tdm_implementation_flow.md).
- [TDM Portal (Self-Service) Activities](/articles/TDM/tdm_gui/01_tdm_gui_overview.md).



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_tdm_glossary.md)
