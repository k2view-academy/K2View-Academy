# What is TDM? 

K2View’s <b> Test Data Management</b> (TDM) is a platform that offers an automated solution for provisioning (copying) a subset of business or digital entities from a source system into selected testing environments. This allows TDM to provide real, high quality data to testing teams. 

### Background 

With the increasing complexity of systems, testing budgets are consuming a larger portion of IT resources, yet the time required for testing impinges upon the efficiency of DevOps.

One difficulty of providing real data to testing teams is that data sources can be different, for example: a Customer may be divided between Customer Care, Billing, Ordering, Ticketing, and Collection. In this case, to run functional tests on one given Customer, it is necessary to extract their data from all the relevant sources.

While enterprise IT continues to adapt DevOps to deliver solutions to internal and external customers, the lack of being able to provision realistic data to test those solutions in a timely manner is still a major stumbling block.
 
The K2View patented [digital entity](/articles/01_fabric_overview/02_fabric_glossary.md#digital-entity) based [microDB](/articles/01_fabric_overview/02_fabric_glossary.md#mdb--microdb) – the TDM - is a type of “data lake” for each digital entity instance. Rather than running a separate copy on each data source, one single source can be dealt with. As such, TDM ensures smooth data provisioning based on the company's business needs.

### Key TDM Features

- A built-in self-service web application where testers can request data to be provisioned on-demand and in real-time. 
- Stores data (including the provisioned entities) centrally in Fabric. 
- Supports copying data into live testing environments. 
- Supports partial data requests, re-deployments of data, and data appending. Specifically:  
  - Provisioning a predefined list of business entities from a selected source environment to a selected target environment.  All data related to the selected entities is extracted and copied to the relevant data systems.
  - Provisioning a sub-set of entities based on predefined parameters.
- Has synthetic data generation, cloning a given entity into the target environment without sequence duplication. 
- Supports the replacement of sequences of the provisioned entities to avoid duplication in the target database. 
- Provides automatic data security and masking on an entity-by-entity basis.
- Supports current data of the selected entities.
- Has cross-application integrity.
- Data flux features enables users to :
  - Save (extract) specific versions of data on a selected list of entities
  - Save a selected list of metadata tables.
  - Load a selected version of entities or metadata tables to the selected target environment.
-  Supports the provisioning of data on-demand or automatic provisioning based on scheduling parameters. 

### Learn more about TDM

- [TDM Architecture](/articles/TDM/tdm_overview/01_tdm_overview.md).
- [TDM Implementation and Setup](/articles/TDM/tdm_implementation/02_tdm_implementation_flow.md).
- [TDM GUI (Self-Service) Activities](/articles/TDM/tdm_gui/01_tdm_gui_overview.md).

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_tdm_glossary.md)
