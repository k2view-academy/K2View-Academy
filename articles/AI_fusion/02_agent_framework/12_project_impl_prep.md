# Project Implementation Preparation Guidelines





## Data Product - AI-Data Ready

### Schema Description

Schema descriptions help the LLM understand your data model and generate accurate SQL queries. You shall verify that schema descriptions exist or add them.

**Option A: Catalog Discovery (Recommended)**

- [ ] Run catalog discovery on your source database
- [ ] Use the LLM plugin to generate descriptions
- [ ] Build the LU from catalog discovery results

**Option B: Manual in Fabric Studio**

- [ ] Open each table in the LU schema
- [ ] Add table-level descriptions
- [ ] Add column descriptions including:
  - Purpose of the column
  - Valid values (for enums/codes)
  - Relationships to other tables
  - Business context

For either of options, add also the overall LU schema description, via the Schema Properties pane.



> Note: Even if option A is used, you can then accomplish, tune and change it in the LU itself, as mentioned at option B

### Domains

According to the data product scope and content, you may decide to separate it logically into domains. This can be useful when building specialized domain specific sub agents, where each gets the context of its domain. 

For example, a bank's data product might be devided  

### Sample Questions and SQL Queries



### Tools



## Applications Configuration

An organization might have several AI based applications, aimed for different purposes and/or audiences . For example, apps for CRM, for technicians teams, and for end-customers. Some might share LUs, sub-agents and tools, while others are fully separated. 

AI Data Fusion platform provides the ability to define several apps, so that can be managed and tested along whole lifecycle, like Chat flows and Evaluation.

### Apps CSV

The apps are managed at `Implementation/LogicalUnits/aifusion/Mtable/apps.csv`, where you shall set the following, so that  AI Fusion platform apps - Chat playground, Evaluation, and Observation - can use and act upon: 

| Parameter           | Description                                                  | Example                 |
| ------------------- | ------------------------------------------------------------ | ----------------------- |
| app_id              | The app ID                                                   | banking                 |
| app_name            | Display name for the application                             | Banking                 |
| chat_flow           | Name of the Broadway flow used for chat interactions         | Banking_Chat            |
| synopsis_flow       | Broadway flow for generating customer synopses/summaries     | Banking_Synopsis        |
| instances_flow      | Broadway flow for retrieving available instances             | customers_banking       |
| story_flow          | Broadway flow for generating customer profile which is displayed in the Chat playground UI | Banking_CustomerProfile |
| lu_name             | The base business entity LU (Data Product) associated with this app | customer_bank           |
| search_instance_api | API endpoint path for searching LUIs by IID. It is used when Search User option is turned on at the Chat playground app | /lu/customer_bank/      |
| description         | Human-readable description of the app                        | Banking App             |



| Unique identifier for the application (e.g., `telco`, `banking`) |                                                              |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| `app_name`                                                   | Display name for the application (e.g., "Telco", "Banking")  |
| `chat_flow`                                                  | Name of the Broadway flow used for chat interactions (e.g., `Telco_Chat`) |
| `synopsis_flow`                                              | Broadway flow for generating customer synopses/summaries     |
| `instances_flow`                                             | Broadway flow for retrieving available instances/customers   |
| `story_flow`                                                 | Broadway flow for generating customer profile stories        |
| `lu_name`                                                    | The Logical Unit (Data Product) associated with this app     |
| `search_instance_api`                                        | API endpoint path for searching instances                    |
| `description`                                                | Human-readable description of the agent                      |



#### Set the active chat Application ID

Configure the chat `aifusionAppId` in the *apps.json*, which is used at your project, for example and as recommended: `Implementation/LogicalUnits/k2_ws/web/apps.json`:

```json
{
    "name": "aifusion",
    "appId": "aifusion",
    "apps": [
        { "displayName": "Chat", "pathName": "chat", "aifusionAppId": "banking" },
        ...
    ]
}
```

The `aifusionAppId` value (e.g., `banking`, `telco`, `healthcare`) determines which customization files are loaded.
