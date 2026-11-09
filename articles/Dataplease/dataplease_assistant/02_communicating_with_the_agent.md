# Communicating with the Agent

### Overview

After the [Dataplease AI Agent](/articles/Dataplease/dataplease_assistant/01_dataplease_assistant_overview.md) starts, the user can write requests in natural language. The agent handles two types of requests:

* **Catalog questions** – Questions about the Catalog metadata of the selected datasets, for example about fields, data types, PII, classifications and relationships.
* **Data generation** – Requests to generate synthetic data for the selected datasets.

The Dataplease Assistant also shows quick-pick options, for example **Summarize the selected datasets** and **Suggest a data generation plan**. For the full list of options, refer to [Selecting the Datasets](/articles/Dataplease/dataplease_app/04_selecting_the_datasets.md).

During the generation, the panel shows the user request and the response of the agent. The response includes the reasoning steps of the agent and a summary of the data story. Refer to [Data Generation](/articles/Dataplease/dataplease_app/05_data_generation.md).

### The Data Story

The data story describes the business scenario that the generated data represents. The agent creates the data story from the user request and the Catalog metadata of the selected datasets. The user request is the base of the data story. The agent uses the Catalog metadata to add the details that are missing from the request.

The generation starts only after the user sends a request. To send a request, type it in the **Type a request** box and press **Enter**. Describe the necessary data, for example the business scenario, the number of records, the languages or the ID ranges.

The agent creates the data story in the **Building story** stage of the generation. Refer to [Data Generation](/articles/Dataplease/dataplease_app/05_data_generation.md).

In one session, the agent uses the same data story again while the dataset selection and the Catalog metadata do not change. Thus, the data of different requests stays consistent.

By default, each generation request adds new rows to the existing rows of the selected datasets. To delete the existing rows before the generation, ask the agent to replace the data. Refer to [Data Preview](/articles/Dataplease/dataplease_app/06_data_preview.md).
