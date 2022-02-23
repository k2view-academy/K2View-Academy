# What is the Test Data Management (TDM) Platform? 

<a href="https://www.k2view.com/products/test-data-management/" target="_blank">Test Data Management (TDM)</a> offers an automated solution for provisioning subsets of Business Entities (Digital Entities) like Customer, Order, Patient, Product or Household from source systems into selected testing environments to provide real, high-quality data to testing teams.

While enterprise IT continues to adopt DevOps to accelerate delivery of solutions to both internal and external customers, one thing still holds them back - provisioning realistic data to test these solutions in a timely manner. With increasing complexity and system interdependencies, testing budgets now consume a large portion of all IT resources, and yet the time required for testing brings the agility of DevOps to a halt.

One of the main challenges of providing real data to testing teams is that data is often split between different data sources. For example, a Customer may be broken up between Customer Care, Billing, Ordering, Ticketing, and Collection systems. To run functional tests on a Customer in an integrative testing environment, their data must be extracted from all relevant source systems.

The K2View patented [Digital Entity](/articles/01_fabric_overview/02_fabric_glossary.md#digital-entity) based [MicroDB](/articles/01_fabric_overview/02_fabric_glossary.md#mdb--microdb), a data lake for each Digital Entity instance, ensures smooth data provisioning, based on the company's business needs rather than running a separate copy on each data source.

### Key TDM Features

- Built-in self-service web application where testers can request data to be provisioned on-demand and in real-time. 
- Central storage of provisioned entities in Fabric.
- Copying data into live testing environments. 
- Partial data requests, re-deployment of data and data appending: 
  - Provisioning predefined lists of Business Entities from a selected source environment to a selected target environment. All data related to the selected entities is extracted and copied to the relevant data systems.
  - Copying a sub-set of entities based on predefined parameters. For example, copying 10 customers in NY and using small business packages.
- Synthetic data generation, cloning a given entity into the target environment. TDM replaces the sequences of each clone to avoid sequence duplication.
- Replacement of sequences of provisioned entities to avoid duplication in the target DB. 
- Automatic data security and masking on an entity-by-entity basis.
- Support for up-to-date data of the selected entities.
- Cross-application integrity.
- Data Versioning features:
  - Users can save (extract) specific versions of a selected list of entities or selected list of metadata (reference) tables.
  - Users can load a selected version of entities or metadata tables to the selected target environment.
-  Provisioning of data on-demand or automatic provisioning based on scheduling parameters. For example, provisioning the data automatically every week.



### Getting Started

Learn about the TDM platform's main modules:

- [TDM Architecture](/articles/TDM/tdm_architecture/01_tdm_architecture.md).
- [TDM Implementation and Setup](/articles/TDM/tdm_implementation/02_tdm_implementation_flow.md).
- [TDM GUI (Self-Service) Activities](/articles/TDM/tdm_gui/01_tdm_gui_overview.md).



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_tdm_glossary.md)
