# AI Fusion Integration with Vector Databases

The aifusion framework supports vector databases integrations for semantic search, enabling RAG, by providing set of actors and flows to build and use in-house vector database.

In Fabric, PostgreSQL often serves as the system database. As a result, customers can leverage it for vector workloads without adding new infrastructure or learning new APIs. small or medium datasets can be cross reference documents or proceduresץ

SQLite, which is used as the core of LUI storage, can be beneficial for few use cases, like:

* When LUI contains also unstructured data, like personal contract and agreements. Vector tables can be then created as part of the business entity, so that such unstructured info can be processes and later semantic searches can done upon.
* Create a dedicated LU for holding vectors, for cross reference documents or procedures.

Accordingly, AI Fusion supports these two vector DBs - SQLite and PostgresSQL.

> SQLite, aimed to be used inside Fabric storage, as LUIs tables, where each such table in the LUI is stands for vector index.
> You can create a dedicated LU for holding vectors or add such tables/indexes in any LU in your project. 



The Vector DB integrations prerequisite and building blocks can be found at the GenAI [preparations and setup](). 



## The ingestion flow steps and utilities

The ingestion flow is built from several steps. Below are utility actors and flows that you may consider using it. As usual, while building your flow, you may prefer use alternatives, in each of the steps. 

> You can use the AI Fusion examples, as reference.
>
> Examples are located at SharedObjects/Broadway/aifusion/vectors/examples



* **Initialize** - prepare the table. For this you can use the VectorInitializer flow (PgVectorInitializer or SqliteVectorInitializer) 

* **Grounding** - the initial sources processing. It is recommended to transform the resources, like documents and web-pages,  into a markdown format. 
  During the agentic flow, those documents are provided to the AI to be part of its context. Markdown  format is a very useful, enabling adding simple tags, hinting about titles, sections, list and so on.  

  > A very useful utility is ["markitdown"](https://github.com/microsoft/markitdown) - a Python library, which transforms many formats to markdown. Examples of file types formats it converts: PDF, PowerPoint, Word, Excel, Images (png, jpg), HTML, Text-based formats (CSV, JSON, XML) 

* **Chunking** - slicing and splitting the processed document into smaller, semantically meaningful pieces , for better search.

  For this purpose you can use the **chunker** actor.

* **Embedding** - Converts the text chunks into numerical vector embeddings using an embedding model.

  * Use the embedding interface that you define at the project.
  * During this process you shall loop on chunks and embed each chink using **embed** actor.

* **Indexing:** The load phase, where those vectors are stored and organized in the vector database.

  For this you can use the VectorLoader flow (PgVectorLoader or SqliteVectorLoader)

  

## Retrieval / Search

The search process is built from 2 steps:

- **Embed** - transform the search term or sentence into vector.
  Use the **embed** actor for doing it.
- **Query** - search for matched results at the Vector DB. Note that because this search is done by semantic similarity, then usually more than a single search result is being used. In addition each result is retrieved with its rate.



## Guidelines and Best Practices

* Embedding vector dimension
  * The Vector DB table schema definition includes the vector dimension. This must match the chosen embedding model. 
  * Higher embedding dimensionality improves accuracy at increased computational cost. Tune dimension size based on model choice and performance constraints.
* Search results return row identifiers and a distance score. Typical meaningful distances are around 0.4 to 0.6; values below 0.6 are often good matches.
* To enable updating the vector DB content, with a controlled and tracked capabilities, use the Pipeline for the ingestion process. For example:
  * Have a suite for each of the steps, where each can contain one or more cases. 
  * When several documents are indexed into the same table, processing each can be handled by a separated case (have a loop on a directory and have cases built dynamically) so that each can be tracked.
  * Use different pipelines or different suites for each table.
