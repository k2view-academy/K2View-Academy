# Vector Database

The aifusion framework supports vector databases for semantic search, enabling Retrieval-Augmented Generation (RAG). It provides set of actors and examples to achieve this.

## Vector Ingestion

The vector ingestion - inserting data into vector DB for later semantic search, is built from several steps:

* Parse - Document processing, preparing and grounding. This is done by transform the document into markdown format.
* Chunk - slicing the processed document into pieces, for better search
* Embed - transform the chunked text into vector, using an Embedding utility. 
* Store - insert into the vector DB table
  Note that there is a setup step of creating the target table with relevant and matched columns.

## Search

The search process is built from 2 steps:

* Embed - transform the search term or sentence into vector
* Query - search for matched results at the Vector DB. Note that because this search is done by semantic similarity, then usually more than a single search result is being used. In addition each result is retrieved with its rate.



## Supported DB

AI Fusion supports 2 vector DBs

* SQLite, using `vec0` extension. 
* Postgres, using its `pgvector` flavor.



## Vectors LU

The Vectors LU maintains SQLite-based vector stores with separate tables for each information type. Tables can be created by using the utility actors and are not shown 

## Get Started

### Preparations and Setup

* markitdown
* DB
* Embedding interface

### Create

IT is recommended to make 

Please refer to aifusion examples at Share Objects vectors folder



## Guidelines and Best Practices

* Embedding vector dimension
  * The Vector DB table schema definition includes the vector dimension. This must match the chosen embedding model. 
  * Higher embedding dimensionality improves accuracy at increased computational cost. Tune dimension size based on model choice and performance constraints.

* Search results return row identifiers and a distance score. Typical meaningful distances are around 0.4 to 0.6; values below 0.6 are often good matches.
* To enable updating the vector DB content, with a controlled and tracked capabilities, use the Pipeline for the ingestion process. For example:
  * Have a suite for each of the steps, where each can contain one or more cases. 
  * When several documents are indexed into the same table, processing each can be handled by a separated case (have a loop on a directory and have cases built dynamically) so that each can be tracked.
  * Use different pipelines or different suites for each table.

* For large datasets consider managed vector providers or dedicated vector DBs. The SQLite extension is useful for small/medium proof of concepts but is experimental and may not scale.

