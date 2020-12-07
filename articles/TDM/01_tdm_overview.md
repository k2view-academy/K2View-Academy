# What is TDM? 

K2view Test Data Management (TDM) product offers an automated solution to provision (copy) a subset of Business Entities (Digital Entities) like Customer, Order, Patient, Product or Household from the source systems into selected testing environments and provide a real, high quality data to the testing teams.

While enterprise IT continues to adopt DevOps to accelerate delivery of solutions to both internal and external customers, one thing still holds them back: provisioning realistic data to test those solutions in a timely manner. With increasing complexity and system interdependencies, testing budgets now consume a large portion of all IT resources, and yet the time required for testing brings the agility of DevOps to a halt.

One of the main challenges to provide a real data to the testing teams is that data is often split between different data sources.  For example, a Customer may be divided between Customer Care, Billing, Ordering, Ticketing, and Collection systems. To run functional tests on a Customer in an integrative testing environment, it is necessary to have its data, extracted from all the relevant source systems.

K2view patented [Digital Entity](/articles/01_fabric_overview/02_fabric_glossary.md#digital-entity) based [MicroDB](/articles/01_fabric_overview/02_fabric_glossary.md#mdb--microdb), a data lake for each Digital Entity instance, ensures a smooth data copy, based on the company's business needs rather than running a separate copy on each data source.

### Key TDM Features

- A built-in Self-Service web application, a place where testers can request data to be provisioned on demand and in real-time. 
- Store data centrally in Fabric. The copied (provisioned) entities are stored in Fabric.
- Support for partial data requests, re-deployments, and data appending: 
  - Copy (provision) a predefined list of Business Entities from a selected source environment to a selected target environment.  The full data, related to the selected entities, is extracted and copied to the relevant data systems.
  - Copy a sub setting of entities based on pre-defined parameters. For example, copy 10 Customers, located in NY, and use a Small Business packages.
- Synthetic data generation:
  - Clone a given entity into the target environment. The TDM replaces the sequences of each clone to avoid a sequence duplication.
- Support a replacement of sequences of the provisioned entities to avoid duplication in the target DB. 
- Automatic data security and masking on an entity-by-entity basis.
- Support for fresh and entity-specific data.
- Cross-application integrity.
- Data Flux features:
  - Enable the user to save (extract) specific versions on a selected list of entities or selected list of metadata (reference) tables.
  - Enable the user to load a selected version of entities or metadata tables to the selected target environment.
-  Support a provisioning of data on demand or an automatic provisioning based on scheduling parameters. For example, provision the data automatically every week.



### Getting Started

**Learn about Main TDM Platform Modules:**

- TDM Architecture
- TDM Implementation and Set-up.
- TDM Self-Service Activities.



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/101_test_data_management//02_tdm_glossary.md)
