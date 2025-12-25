# Vector Database Example Flow

This Broadway flow is used as example and a reference for building and querying a vector database within aifusion agentic framework.

It is located at Project's Shared-Objects/Broadway/aifusion/vectors/examples.



## High-Level Flow Structure

The flow is organized into several logical stages:

1. Convert file to markdown
2. Split into chunks and initialize vector table
3. Create embeddings for the file chunks and load them into the vector table
4. Accept and embed the user question
5. Vector search against stored chunks
6. Shape the results and serialize to JSON

---

## 1. Convert File to Markdown

**Stage:** `convert file to markidown`  

**Key actor:** `markitdown1` (InnerFlow → `markitdown`)

This stage delegates the work of reading an HTML file and converting it to markdown to a separate flow named `markitdown`.

- Input: directory and file name (from external inputs).
- Output: the markdown representation of the original document.

The markdown text becomes the basis for all subsequent chunking and embedding operations.

---

## 2. Split into Chunks & Initialize Vector Table

**Stage:** `splitting into chunks `  

**Key actors:**

- `chunker1` (custom `chunker` actor)
- `create new table in vectors` (InnerFlow → `SqliteVectorInitializer`)

### Chunking

`chunker1` receives:

- The markdown document from the previous stage.
- A fixed file path reference (for metadata purposes).

It splits the document into smaller, overlapping chunks that are easier to embed and search. Each chunk carries:

- `content` (text of the chunk),
- `file_name`,
- `index`.

### Vector Table Initialization

In parallel in the same stage, `create new table in vectors` calls the `SqliteVectorInitializer` flow. It:

- Ensures a vector table exists, typically in a **Vectors** schema and a `test` table.
- Sets the embedding dimension (1536) to match the embedding model.
- Defines `auxiliaryColumns` such as a `content` text column to store associated data per vector.

This prepares the underlying SQLite/Fabric structure that will store vectors and their related content.

---

## 3. Embed KB Chunks and Store Them

### 3.1 Create Embeddings for Chunks

**Stage:** `create embedding`  
**Key actor:** `Embed1` (actor type `Embed`)

This stage iterates over the chunks produced by `chunker1`. For each chunk:

- The chunk `content` is sent to the `Embed` actor using an embedding interface (e.g., OpenAI embeddings).
- The actor returns an embedding vector for that text.

The result is a sequence of embedding vectors, one per chunk.

### 3.2 Map Embedding + Content

**Stage:** `create map for input`  
**Key actor:** `MapCreate1` (actor type `MapCreate`)

This stage combines:

- Each embedding vector from `Embed1`,
- With its corresponding chunk `content` from `chunker1`.

For each chunk, it creates a map with two main fields:

- `embedding`: the numeric vector.
- `content`: the chunk’s textual (or structured) content.

This map is the unit that will be loaded into the vector table.

### 3.3 Load Embeddings into the Vector Table

**Stage:** `Store the returned vectors`  
**Key actor:** `SqliteVectorLoader1` (InnerFlow → `SqliteVectorLoader`)  
**Flags:** `transactional: true`, `last: 1` (transaction boundary / finalization in this branch)

This stage calls the `SqliteVectorLoader` flow to insert each `(embedding + content)` map into the previously initialized vector table (`Vectors.test`).

Effectively, after this stage completes, the KB document has been:

- Chunked,
- Embedded,
- Persisted as vectors in the SQLite/Fabric store, along with their `content`.

---

## 4. User Question Input and Embedding

### 4.1 User Question Source

**Stage:** ` user question `  
**Key actor:** `user question` (actor type `Const`)

This is the entry point for the user’s query:

- It exposes a single external parameter for the question text.
- At runtime, the caller passes the question into this actor.
- The stage outputs the question as a simple value.

### 4.2 Embed User Question

**Stage:** `convert to embed`  
**Key actor:** `Embed2` (actor type `Embed`)

The question text is sent to another embedding actor using the same embedding interface used for KB chunks. The result is:

- A single query embedding vector representing the user question.

---

## 5. Vector Search Against Stored Chunks

**Stage:** `compare it to stored chunks`  
**Key actor:** `SqliteVectorSelect1` (actor type `SqliteVectorSelect`)

This stage performs a similarity (K‑NN) search in the vector table:

- Uses the vector interface (`fabric`), schema (`Vectors`), and table (`test`).
- Takes the question embedding from `Embed2`.
- Uses defaults for:
  - The target embedding column (usually `embedding`),
  - The number of nearest neighbors (`limit`, default is 5),
  - Filters and other optional parameters.

The output is:

- A result array of rows, each containing:
  - A `distance` value (vector distance),
  - The stored `content`,
  - Other fields (e.g., row id, embedding).

Conceptually, this stage answers:  
**“Which stored chunks/plans are closest in meaning to the user’s question?”**

---

## 6. Result Shaping and JSON Output

### 6.1 Shape Each Result Row into a Plan

**Stage:** `Stage 9`  
**Key actor:** `MapCreate2` (actor type `MapCreate`)

This stage iterates over each row in the vector search result and constructs a unified plan structure. It expects the stored `content` to already be a structured object with:

- `objective`
- `tools` (each with `name`, `description`, `tag`)
- `action_steps`
- `required_information`
- `expected_outcomes`

For each row, the output map looks like:

```json
{
  "distance": <number>,
  "query": <string>,   // (declared in schema, not yet wired in this flow)
  "plan": {
    "objective": <string>,
    "tools": [
      {
        "name": <string>,
        "description": <string>,
        "tag": <string>
      }
    ],
    "action_steps": [<string>],
    "required_information": [<string>],
    "expected_outcomes": [<string>]
  }
}
