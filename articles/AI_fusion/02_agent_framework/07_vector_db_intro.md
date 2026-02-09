# Vector Database and RAG — Introduction

While business-entity-oriented data is retrieved from Fabric, a Retrieval-Augmented Generation (RAG) mechanism is applied for non-personal data such as organizational unstructured content (for example, agreements, procedures, and knowledge bases). 

At its core, RAG is built upon two critical pillars: **Indexing** and **Retrieval**. While **Indexing** prepares the organization's documents for machine understanding by transforming raw documents into searchable vectors, **Retrieval** ensures the agent can pinpoint the exact context needed in milliseconds during an active workflow.



## The Three Models of Implementation

When deciding how and where to store the data and execute these pillars, organizations typically choose between three levels of service, ranging from full control to full automation.

1. **RAG-as-a-Service** (fully managed)
   - **The approach**: The service provider - one of the major cloud providers - encapsulates the entire process, automating the synchronization between the data source and the vector store to remove the need for manual pipeline maintenance. You provide the files; the service provides the answers. 
   - **Why choose this**: This is the fastest route to production, designed for teams that want a fully automated "hands-off" pipeline where the service handles the parsing, chunking, and embedding logic automatically. 
     It fits for large-scale datasets and high query volumes.
   - **Examples**: AWS Bedrock Knowledge Bases, Vertex AI Search.
2. **Purpose-built dedicated vector databases** (managed storage)
   - **The approach**: The database infrastructure is managed, but the organization remains responsible for the Indexing process (the pipeline that reads, chunks, and embeds the files).
   - **Why choose this**: Organizations choose this model because it provides specialized high-performance indexing and hybrid search that can handle billions of vectors with sub-second latency, offering full architectural control without the burden of server maintenance.
   - **Examples**: Pinecone, Weaviate, Milvus, Qdrant.
3. **Traditional databases with vector extensions** (non-managed)
   In this model, vector search is implemented as an extension to an existing relational database. Vectors are stored alongside structured data and accessed using standard database drivers and SQL-based queries.
   - **The approach:** Vector search is implemented as an extension to an existing relational database. Vectors are stored alongside structured data and accessed via standard SQL.
   - **Why choose This**: This is ideal for operational simplicity, data locality, and keeping sensitive organizational data within your own controlled infrastructure.
   - **Examples**: SQLite + `vec0`, PostgreSQL + `pgvector`.



## When to use which approach

There is no single “best” option for all scenarios.

- For large collections, high query volumes, or strict performance requirements, managed vector databases or fully managed vector services are often a better fit.
- For smaller to medium datasets, and environments where DBs with vector extension or with native support are already central, using them with vector enabling extension, can be a natural and efficient choice.
