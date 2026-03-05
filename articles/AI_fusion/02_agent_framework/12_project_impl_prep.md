# Project Implementation Preparation Guidelines

The following checklist guides you through building a new aifusion implementation from scratch. Use it to ensure that components are properly configured.

The implementation phase can be done on top of the [setup](11_setup_and_prerequisites.md) phase.

## Data Product Setup — AI-Data Ready

### Add Schema Description

Schema descriptions help the LLM understand your data model and generate accurate SQL queries. Verify that schema descriptions exist or add them.

**Option A: Catalog Discovery (Recommended)**

- [ ] Run Catalog discovery on your source database
- [ ] Use the LLM plugin to generate descriptions
- [ ] Build the LU from Catalog discovery results

**Option B: Manual in Fabric Studio**

- [ ] Open each table in the LU schema
- [ ] Add table-level descriptions
- [ ] Add column descriptions including:
  - Purpose of the column
  - Valid values (for enums/codes)
  - Relationships to other tables
  - Business context

For either option, also add the overall LU schema description using the Schema Properties pane.

> Note: Even if Option A is used, you can then accomplish, tune and change it in the LU itself, as described in Option B.

### Configure Domains Data

You can organize a data product into *domains*, where each domain represents a logical group of tables. 

This structure is especially useful for AI agents that dynamically generate SQL. When AI agents are intended to generate SQL dynamically, it is important to limit the scope to only the tables relevant to the user’s request. Domain-based separation enables this by allowing the agent to operate on a focused subset of the data product rather than the entire schema.

For example, a banking data product (*customer_bank*) might be divided into domains such as *DDA* (Demand Deposit Accounts), *LOAN* (Loans and Mortgages), and *CC* (Credit Card Management). If a user asks a question related to credit cards, only the tables in the *CC* domain are included in the LLM invocation context.

A good practice is to provision and maintain a domain list by creating an MTable in the data product LU, including the following recommended columns:

* Domain 
* Description
* Rules
* Tables
* Goal_Description

According to this list, during an agentic flow, you can use an MTable actor to look up a specific domain and its content, and thus augment the AI context. In case you are running an external agent, this information can be exposed as a tool via API or MCP.



Here is an example:

| Domain | Description            | Rules                                                        | Tables                                                       | Goal_Description                                             |
| ------ | ---------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| DDA    | Deposit Demand Account | If information about the customer's savings and current accounts is required | DDA_TRANSACTIONS, DDA_OVERDRAFTS, DDA_FEES, DDA_ACH_Transfers, DDA_ACCOUNTS, DDA_BRANCHES, DDA_Virtual_Accounts | gather data regarding the customer DDA accounts, including account types, balance, limits, overdraft transactions,  Fees, ACH transfers, virtual accounts, DDA transactions. Information about the DDA accounts is found in tables that their name start with DDA_. The main table is called DDA_Accounts and it includes all the accounts this customer has. All the accounts in this table belong to the customer that is chatting with you. |
| LOAN   | Loan and Mortgage      | If information about the customer's loans and mortgages is required | Loan_Escrow_Transactions, Loan_Interest_Transactions, Loan_Servicer_Changes, Loan_Covenant_Reviews, Loan_Payments, Loan_Interest_Rates, Loan_Accounts | gather data regarding the customer Loans including the loan terms, payments, interest transactions, escrow transaction, servicer changes and covenant reports. Note that a customer can have multiple loans, each with a different loan_account_id, but all of them related to the customer you are chatting with. |
| CC     | Credit Card Management | If information about the customer's credit card is required  | CCMS_Credit_Cards, CCMS_Credit_Card_Statements, CCMS_Credit_Card_Transactions, CCMS_Credit_Card_Payments | gather data regarding the customer credit cards, transactions, statements and payments. |

### Create Sample Questions and SQL Queries

Sample questions help the LLM generate accurate SQL for data retrieval.

In addition to the prompt context scoping sone by the domain list, it is important to provide, per domain, set of examples of user questions and SQL queries that can answer to them. 

- [ ] Create `questions.json` in `customer_bank/Java/resources/` 
  
  > * Use *UserCode loadResource* method for consuming it.
  > * As an alternative, you can have it is a const actor in a Broadway flow. 
- [ ] Add question-SQL pairs for each domain. For example:

	```json
	[
	  {
	    "domain": "POLICY",
	    "question": "What insurance policies do I have?",
	    "sql": "SELECT policy_number, policy_type, status, premium FROM 	Policies_Master"
	  },
	  {
	    "domain": "POLICY",
	    "question": "What is my coverage amount?",
	    "sql": "SELECT coverage_type, coverage_amount, deductible FROM Policy_Coverage"
	  },
	  {
	    "domain": "CLAIMS",
	    "question": "Show me my recent claims",
	    "sql": "SELECT claim_number, claim_date, status, amount FROM Claims_History ORDER BY claim_date DESC"
	  }
	]
	```



## Tools Configuration

### Create Core Tools

Create these standard tools for your implementation:

#### Domain Description Tool

- [ ] Create or update a `<describeDomain>.flow`.
- [ ] Input: Have the domain as input parameter
- [ ] Output: 
  - [ ] The specific domain's info from the domains MTable (name, description, rules, tables, goals)
  - [ ] Schema description (you can use *LLMSchema* actor)
  - [ ] Sample Questions and SQL Queries

#### Vector Database Tools

- [ ] When using vector database, create the tool for searching there. 

- [ ] In case self-hosted with Fabric (SQLite or Postgres) have a tool also to load the data.

  > Consider whether to run the load to vector also in QA and production deployment. You may prefer to prepare it during implementation, insert it into a CSV file already vectorized, and then use it in non-dev deployments (as SQLite vector). 
  > This approach is beneficial as it allows you to track changes in GIT and well as perform testing before using it. 

### Customized Tools

As part of implementation, you might create and expose tools related to data products. See [here](04_agents_and_tools.md#Tools) for more information about tool objectives and concepts.

#### Tool Building

When building and maintaining a tool, it is recommended to follow this checklist:

- [ ] Create the tool flow
- [ ] Configure flow properties:
  - [ ] Add relevant tags
  - [ ] Write clear description
- [ ] Add parameter remarks to all inputs/outputs
- [ ] Implement validation logic
- [ ] Add error handling
- [ ] Test the tool independently

#### Tool Calling

AI Fusion supports three types of tool calling, which you can choose from. When specifying their names, such as for a specialized sub-agent, ensure that you follow the naming conventions:

* `LU__<luName>__<toolName>` — calls a Broadway flow (`toolName`) located at an LU (`luName`) other than aifusion, usually tools which are built at data products, as recommended. Example: `LU__customer_bank__getTransactionsByCategory`

* `<toolname>`  — calls a Broadway flow located at aifusion LU.

* `MCP__<mcpInterfaceName>__<toolName>` — calls a tool via MCP protocol by an MCP connector. The interface (`mcpInterfaceName`) which is of MCP connector type, holds the MCP server information, and the tool (`toolName`) is the MCP tool name in that server.



## Worker Sub-Agents

As explained in other articles like [here](04_agents_and_tools.md) and [here](04a_agentic_flow_agents.md) it is very recommended to create sub agent specified on specific topics, usually according to data product domains.



## AI Applications 

An organization might have several AI based applications, aimed for different purposes and/or audiences . For example, apps for CRM, for technicians teams, and for end-customers. Some might share LUs, sub-agents and tools, while others are fully separated. 

AI Data Fusion platform provides the ability to define several apps, so that can be managed and tested along whole lifecycle, like Chat flows and Evaluation.

### App Flows

Per app, you shall create the following flows. Their objective and usage are described later on: 

- [ ] The main entry point flow (the chat flow)
- [ ] Customer Story Flow
- [ ] Synopsis Flow

### Register Apps in apps.csv

The apps are managed at `Implementation/LogicalUnits/aifusion/Mtable/apps.csv`, where you shall set the following, so that  AI Fusion platform apps - Chat playground, Evaluation, and Observation - can use and act upon: 

| Parameter           | Description                                                  | Example                 |
| ------------------- | ------------------------------------------------------------ | ----------------------- |
| app_id              | The app ID.                                                  | banking                 |
| app_name            | Display name for the application                             | Banking                 |
| chat_flow           | Name of the Broadway flow used for chat interactions         | Banking_Chat            |
| synopsis_flow       | Broadway flow for generating customer synopses/summaries     | Banking_Synopsis        |
| instances_flow      | Broadway flow for retrieving available instances             | customers_banking       |
| story_flow          | Broadway flow for generating customer profile which is displayed in the Chat playground UI | Banking_CustomerProfile |
| lu_name             | The base business entity LU (Data Product) associated with this app | customer_bank           |
| search_instance_api | API endpoint path for searching LUIs by IID. It is used when Search User option is turned on at the Chat playground app | /lu/customer_bank/      |
| description         | Human-readable description of the app                        | Banking App             |

#### Usage

This settings table is being used in several places:

* Chat - calling to the default chat API, this information shall be provided, so that right flows and tools will used. 

  > At the Chat playground 
  >
  > * client side knows to consume the app-id as defined at Apps JSON (see below) and to send it to the API. 
  > * The app-id value also affects on which chat playground customization files (CSS and texts) are loaded. Read [here](13_chat_playground_customize.md) for more information.

* Evaluation

  * On creating tests, the Evaluation Editor suggests user to create test of apps according to this list.
  * The evaluation analysis is executed for the specific app, as saved into the test case.
  * Auto test creation capability  is using the synopsis flow info to generate test case questions.

* Observation - information is collected with the app ID information, so that can separate dashboard widgets and views per app.  

### Configure Apps JSON

Configure the *apps.json*, which is used at your project, for example and as recommended: `Implementation/LogicalUnits/k2_ws/web/apps.json` to set

* The order of sub-apps - Chat playground, Observation and Evaluation
* The app and sub-apps names
* The active app-id of the Chat playground - change the *aifusionAppId* according  to the IDs at the apps csv

```json
{
        "name": "AI Data Fusion",
        "appId": "aifusion",
        "apps": [
            { "displayName": "Chat", "pathName": "chat", "aifusionAppId": "banking" },
            { "displayName": "Evaluation", "pathName": "evaluation" },
            { "displayName": "Observation", "pathName": "observation" }
        ]
    }
```



- [ ] Verify that you at least one app entry at the apps csv



## Best Practices

* **Cache utilization** - At LLMConst actor, locate the dynamic information of the system prompt, at the end. This will enable the usage of cache mechanism at the LLM providers, for saving input tokens. This mechanism reuses latest system prompts, where any dyanmic information breaks it 

