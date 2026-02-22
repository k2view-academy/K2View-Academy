# Setup and Prerequisites

In addition to implementation setup activities such as extension installations, there are other setup activities that should be done and verified on any deployment — Dev, QA, and Production.

This article provides a checklist and guidance on the required components and their setup, for both infrastructure (DevOps) and project (implementation) perspectives, to ensure all components are properly configured.



## Infrastructure

### AI Model Readiness

- [ ] Verify AI language model network accessibility is enabled. This is relevant when working with either foundation model providers or local models. 
- [ ] If non-managed vector database is used, verify embedding model network accessibility is enabled.

* Cloud-based / SaaS model providers:  
  - [ ] Ensure credentials and access permissions, either as APIKEY or native cloud permissions (Like Amazon ARN)  
  - [ ] Select the region according to regulations and the availability of the model versions. Consider choosing cross-region model for better availability.

### Pipeline DB

Pipeline is a Fabric module, installed via extension, that enables evaluation regression tests.
In Studio, it may be defined as an SQLite database, whereas in non-Studio / non-dev (QA, Pre-Prod, Prod) it is in PostgreSQL.

- [ ] On the Postgres server, define a schema on which the Pipeline table will be located, e.g., `pipeline`.
- [ ] Provide the project implementation team the credentials, so that Fabric will be able to access this DB and schema.
- [ ] Verify networking is opened between Fabric and this PostgreSQL. 

### Metrics/Assurance DB

Evaluation and Observation results are collected and kept into the Metrics/Assurance DB.

In Studio it might be defined as SQLite DB, while in non-Studio / non-dev (QA, Pre-Prod, Prod) it is in PostgreSQL.

The steps and checklist are similar to those mentioned for Pipeline DB. 

### LUI Snaps Storage

As part of Evaluation and Observation processes, AI Data platform enable taking LUI snaps. These are samples that can be taken during conversations, so that later on can be evaluated.

It is expected to have a file storage where these snaps can be stored and later on read.

### Vector DB

When using an internal, non managed or as-a-service vector DB, AI Fusion can integrate and work with two vector DBs, which is part of a usual Fabric setup - SQLite and PostgresSQL.

#### PostgresSQL

* **Docker** use this pgvector image, a pre-built container that already includes PostgreSQL 16 with the pgvector extension installed and ready to use: https://hub.docker.com/r/pgvector/pgvector

​	Read [here](https://github.com/pgvector/pgvector) for more information about PG vector installation and usage.

#### SQLite

To enable vector usage within SQLite you shall set the SQLITE_EXTENSIONS at Fabric configuration.

1. Update via Admin Pages → Configuration 
   - Choose to show Hidden as well as Show All
   - Filter attributes by `fabricdb` section 
   - For the `SQLITE_EXTENSIONS` attribute key set the value to be `vec0`
2. Alternatively, do it at the `config.ini` file:
   - Set the following:   `SQLITE_EXTENSIONS = vec0`

3. **Restart Fabric**.

> Note: The relevant supportive library is part of the aifusion extension, located at *lib/SQLite_Vector* folder in the project tree



## Project Setup and Preparations

### AI Models

- [ ] **LLM**: Choose and install the GenAI language model connector extension from the K2Exchange.
    * Create an interface upon the installed connector and set its values. read [here](05_llm_interfaces.md) for more information. 

      > While usually organizations have agreement with specific LLM service provider, or install locally a specific model with a single inference engine, AI Data Fusion is flexible in that you can use several LLMs, usually for different purposes.
      >
      > In this case, you shall install more than a single connector extension and configure them. As explained [here](05_llm_interfaces.md) you shall tag them, so that later, at implementation, you will be able to decide which model shall be used in the LLM invocation.
    
- [ ] **Embedding** model: If internal vector DB is being used, you shall define also an Interface for the embedding model, for both ingestion and retrieval phases. 

### Pipeline DB

- [ ] **Pipeline Interface settings**: The Pipeline DB definitions shall be set according to Dev/Studio to non-Dev/Studio (like QA and Prod): in case your Studio System DB is SQLite, then accordingly you shall have 2 pipeline interfaces, per DB type for example: *pipeline* and *pipeline_pg*.
- [ ] **Environments Settings** at the [Studio's Environments Editor](/articles/25_environments/01_environments_overview.md)
  - [ ] Set pipeline interface properties values per deployment (for the non-dev you shall set the Postgres interface, if you have two, as described above).
  - [ ] Set the Globals values: 
    - [ ] PIPELINE_INTERFACE - the actual pipeline interface name to be used. For example, if you have 2 interfaces - *pipeline* and *pipeline_pg* type the relevant name for this deployment environment (*pipeline_pg* for QA and Prod)
    - [ ] PIPELINE_SCHEMA - the name of the schema that was prepared at the dataabse (please refer to the *Infrastructure* section)
    - [ ] PIPELINE_DIALECT - the actual type that is going to be used in this environment - `postgres`  or `sqlite` 

### Metrics/Assurance DB

- [ ] Set the Metrics/Assurance DB, used by Evaluation and Observation - interfaces and Environments Globals, with relevant values, similar to those mentioned for the Pipeline DB. The Environments Globals that shall be set are: ASSURANCE_INTERFACE_TYPE , ASSURANCE_INTERFACE, ASSURANCE_SCHEMA

### LUI snaps Storage

- [ ] Set the LUI snaps file type interface with relevant properties values.
- [ ] Set the Environments Globals
  - [ ] STORAGE_INTERFACE - name of the interface
  - [ ] STORAGE_PATH - the path (folder) to be used at the defined interface file system.

### Vector DB - Markdown

When using a non managed service vector DB, the ingestion process is done at AI Fusion. One of the recommended steps is to align the content into a grounded format, like markdown, which is very common and useful when working with GenAI models.

A very useful utility is ["markitdown"](https://github.com/microsoft/markitdown) - a Python library, which transforms many formats to markdown. Examples of file types formats it converts: PDF, PowerPoint, Word, Excel, Images (png, jpg), HTML, Text-based formats (CSV, JSON, XML).

If you wish using it, check and verify where you can install this library. Please refer to the *install_py_dep* flow and other related flows located at SharedObjects/Broadway/aifusion/markitdown project folder.

