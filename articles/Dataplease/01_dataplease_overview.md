# Dataplease Overview

### Overview

Dataplease is a K2view application, built on the Fabric platform, that generates realistic synthetic test data on demand. The app guides the user through the full process: select an interface, scan its structure, select the datasets, describe the necessary data, then provision the data. The **Dataplease AI Agent** interprets the user's requests and generates the data.

Test and development environments need data that looks and behaves like production data, without exposing real, sensitive information. Dataplease is designed to:

* Achieve **production-like synthetic data**, without exposing real sensitive information, with **zero implementation effort**.
* Enable a **single persona to complete the entire end-to-end flow**: run the Catalog → write prompts (natural-language requests) → evaluate the generated data → provision it to the target.

The result is an intuitive, self-service flow: a user points Dataplease at an interface and, with the agent's help, gets freshly generated, referentially consistent synthetic data for the selected datasets.

The Dataplease MVP version supports relational databases that have a JDBC driver, such as Oracle, PostgreSQL, IBM Db2 and Microsoft SQL Server.

### Data Privacy: What Is Sent to the LLM

Dataplease generates data from metadata. It does not copy the original data rows. Dataplease sends the following to the LLM:

* Schema structure: datasets, fields, data types, keys and relationships.
* Catalog classifications, including PII flags.
* Discovery enrichment properties, when the related discovery plugins are active.
* The user's chat prompts and the generated data story.

**No source table rows are sent to the LLM.**

For the end-to-end flow and the solution components, refer to [Dataplease Process](02_dataplease_process.md).
