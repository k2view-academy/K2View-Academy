# Setup and Prerequisites

In addition to implementation setup activities such as extension installations, there are other setup activities that should be completed and verified for every deployment — Dev, QA, and Production.

This article provides a checklist and guidance on the required components and their setup, for both infrastructure (DevOps) and project (implementation) perspectives, to ensure all components are properly configured.



## Infrastructure

### AI Model Readiness

- [ ] Verify that network connectivity to the AI language model is enabled. This is relevant when working with either foundation model providers or local models. 
- [ ] If non-managed vector database is used, verify that network access to the embedding model is enabled.

* Cloud-based / SaaS model providers:  
  - [ ] Ensure that credentials and access permissions are properly configured, either as an API key or as native cloud permissions (e.g., Amazon ARN).  
  - [ ] Select the region according to regulations and the availability of the model versions. Consider choosing cross-region model for better availability.

### Pipeline DB

Pipeline is a Fabric module, installed via extension, that enables evaluation regression tests.
In Studio, it may be defined as an SQLite database, whereas in non-Studio deployments (QA, Pre-Prod, Prod), it resides in PostgreSQL.

- [ ] On the Postgres server, define a schema on which the Pipeline table will be located, e.g., `pipeline`.
- [ ] Provide the project implementation team with credentials to allow Fabric to access this database and schema.
- [ ] Verify that the network is open between Fabric and PostgreSQL. 

### Metrics/Assurance DB

Evaluation and Observation results are collected and stored in the Metrics/Assurance database.

In Studio, it may be defined as an SQLite database, whereas in non-Studio deployments (QA, Pre-Prod, Prod), it resides in PostgreSQL.

The steps and checklist are similar to those outlined in the Pipeline DB section. 

### LUI Snaps Storage

As part of the Evaluation and Observation processes, the platform enables capturing LUI snapshots. These snapshots are samples captured during conversations for later evaluation.

It is expected to have file storage where these snapshots can be stored and later read.

### Vector DB

When using an internal, non-managed or as-a-service vector database, the AI Fusion platform can integrate and work with two vector databases that are part of a typical Fabric setup — SQLite and PostgreSQL.

#### PostgreSQL

* In **Docker** deployment, use this pgvector image: https://hub.docker.com/r/pgvector/pgvector; it is a pre-built container that already includes PostgreSQL 16 with the pgvector extension installed and ready for usage. 

​	Read [here](https://github.com/pgvector/pgvector) for more information about PG vector installation and usage.

#### SQLite

To enable vector usage in SQLite, you should set the SQLITE_EXTENSIONS in the Fabric configuration.

1. Update via Admin Pages → Configuration 
   - Choose to show Hidden as well as Show All
   - Filter attributes by `fabricdb` section 
   - For the `SQLITE_EXTENSIONS` attribute key, set the value to be `vec0`
2. Alternatively, configure it in the `config.ini` file:
   - Set the following:   `SQLITE_EXTENSIONS = vec0`

3. **Restart Fabric**.

> Note: The required support library is part of the aifusion extension and is located in the *lib/SQLite_Vector* folder in the Project tree.



## Project Setup and Preparations

### AI Models

- [ ] **LLM**: Choose and install the GenAI language model connector extension from the K2exchange.
    * Create an interface upon the installed connector and set its values. Read [here](05_llm_interfaces.md) for more information. 

      > While organizations usually have an agreement with a specific LLM service provider, or install locally a specific model with a single inference engine, AI Data Fusion is flexible in that you can use several LLMs, usually for different purposes.
      >
      > In this case, you should install more than one connector extension and configure them. As explained [here](05_llm_interfaces.md), you should tag them in a way that allows you to decide, during implementation, which model will be used for the LLM invocation.
    
- [ ] **Embedding** model: If an internal vector database is used, you should also define an Interface for the embedding model, covering both ingestion and retrieval phases. 

### Pipeline DB

- [ ] **Pipeline Interface settings**: The Pipeline DB definitions should be set according to Dev/Studio to non-Dev/Studio (like QA and Prod): in case your Studio System DB is SQLite, then accordingly you will have two pipeline interfaces, per DB type for example: *pipeline* and *pipeline_pg*.
- [ ] **Environment Settings** in the [Studio's Environments Editor](/articles/25_environments/01_environments_overview.md)
  - [ ] Set pipeline interface properties values per deployment (for the non-dev you should set the Postgres interface, if you have two, as described above).
  - [ ] Set the Global values: 
    - [ ] PIPELINE_INTERFACE — the actual pipeline interface name to be used. For example, if you have two interfaces — *pipeline* and *pipeline_pg* — type the relevant name for this deployment environment (*pipeline_pg* for QA and Prod).
    - [ ] PIPELINE_SCHEMA — the name of the schema that was prepared in the database (please refer to the *Infrastructure* section).
    - [ ] PIPELINE_DIALECT — the actual type that is going to be used in this environment — `postgres`  or `sqlite`. 

### Metrics/Assurance DB

- [ ] Set the Metrics/Assurance DB, used by Evaluation and Observation - interfaces and Environments Globals, with relevant values, similar to those mentioned for the Pipeline DB. The Environment Globals that should be set are: ASSURANCE_INTERFACE_TYPE, ASSURANCE_INTERFACE, ASSURANCE_SCHEMA

### LUI snaps Storage

- [ ] Set the LUI snaps file type interface with relevant properties values.
- [ ] Set the Environment Globals
  - [ ] STORAGE_INTERFACE — name of the interface
  - [ ] STORAGE_PATH — the path (folder) to be used in the defined interface file system.

### Vector DB — Markdown

When using a non managed service vector DB, the ingestion process is done at AI Fusion. One of the recommended steps is to align the content into a grounded format, like markdown, which is very common and useful when working with GenAI models.

A very useful utility is ["markitdown"](https://github.com/microsoft/markitdown) - a Python library, which transforms many formats to markdown. Examples of file types formats it converts: PDF, PowerPoint, Word, Excel, Images (png, jpg), HTML, Text-based formats (CSV, JSON, XML).

If you wish using it, check and verify where you can install this library. Please refer to the *install_py_dep* flow and other related flows located at SharedObjects/Broadway/aifusion/markitdown project folder.

