# AI Fusion Integration with Vector Databases

The AI Fusion framework supports vector database integrations for semantic search, enabling RAG, by providing a set of actors and flows to build and use an in-house vector database.

In Fabric, PostgreSQL often serves as the system database. As a result, customers can leverage it for vector workloads without adding new infrastructure or learning new APIs. Small to medium datasets can be cross-referenced documents or procedures.

SQLite, which is used as the core of LUI storage, can be beneficial for few use cases, like:

* When LUI contains also unstructured data, like personal contracts and agreements. Vector tables can be then created as part of the business entity, in a way that such unstructured info can be processes and later semantic searches can done upon.
* Create a dedicated LU for holding vectors, for cross reference documents or procedures.

Accordingly, AI Fusion supports two vector DBs: SQLite and PostgreSQL.

> SQLite, aimed to be used inside Fabric storage, as LUIs tables, where each such table in the LUI is stands for vector index.
> You can create a dedicated LU for holding vectors or add such tables/indexes in any LU in your project. 



Setup information is available [here](11_setup_and_prerequisites.md). 



## Ingestion Flow Steps and Utilities

The ingestion flow — the process of inserting content into a vector store — consists of several steps. Below are utility actors and flows you may consider using. As always, when building your flow, you may prefer use alternatives, in each of the steps. 

> You can use the AI Fusion examples, as reference.
>
> Examples are located at SharedObjects/Broadway/aifusion/vectors/examples



* **Initialize** — prepare the table. For this, you can use the *VectorInitializer* flow (`PgVectorInitializer` or `SqliteVectorInitializer`). 

* **Grounding** — processing of the initial sources. It is recommended to transform resources, such as documents and web pages, into Markdown format. 
  During the agentic flow, these documents are provided to the AI to be part of its context. The Markdown format is very useful when working with AI models, enabling adding simple tags, hinting about titles, sections, list and so on.  

  > A very useful utility is ["markitdown"](https://github.com/microsoft/markitdown), which is a Python library that transforms various file formats into Markdown. Examples of file formats it can convert include PDF, PowerPoint, Word, Excel, images (PNG, JPG), HTML, and text-based formats such as CSV, JSON, and XML. 

* **Chunking** — splitting the processed document into smaller, semantically meaningful pieces, for more effective search.

  For this purpose, you can use the `chunker` actor.

* **Embedding** — converts text chunks into numerical vector embeddings using an embedding model.

  * Use the embedding interface that you defined in the project.
  * During this process, you should loop over the chunks and embed each one using the `embed` actor.

* **Indexing** — the load phase, during which vectors are stored and organized in the vector database.

  For this you can use the *VectorLoader* flow (`PgVectorLoader` or `SqliteVectorLoader`).

  

## Retrieval / Search

The search process consists of two steps:

- **Embed** — transforms the search term or sentence into a vector.
  Use the **embed** actor to perform this step.
- **Query** — searches for matching results in the Vector database. Note that because this search is done by semantic similarity, then usually more than a single search result is being used. In addition each result is retrieved with its rate.



## Examples

Example flows demonstrating the ingestion and retrieval processes can be found at `SharedObjects/Broadway/aifusion/vectors/examples/`: PgembedSearchExample and SqlitembedSearchExample.



## Guidelines and Best Practices

* Embedding vector dimension
  * The Vector DB table schema definition includes the vector dimension. This must match the chosen embedding model. 
  * Higher embedding dimensionality improves accuracy at increased computational cost. Adjust the dimension size according to your model choice and performance constraints.
* Search results return row identifiers along with a distance score. Typical meaningful distances are around 0.4–0.6; values below 0.6 are usually considered good matches.
* To enable updating the vector DB content, with controlled and tracked capabilities, use the pipeline for the ingestion process. For example:
  * Have a suite for each of the steps, where each can contain one or more cases. 
  * When several documents are indexed into the same table, each can be processed in a separate case (have a loop on a directory and have cases built dynamically) so that each can be tracked.
  * Use different pipelines or different suites for each table.
