# References and Planning

Many questions cannot be answered from the customer's data alone: product terms, fee policies, eligibility rules, or the steps of a business procedure. AI Fusion 3 handles this knowledge with **reference packs** — folders of Markdown documents stored with the agent — and two built-in capabilities that use them:

- **Reference search** answers knowledge questions with citations.
- **Planning** turns a multi-step request into a plan that follows your written procedures.

Reference packs replace the knowledge-base and procedure vector pipelines of earlier versions. No vector database or embedding model is required.



## Reference Packs

A reference pack is a sub-folder of the agent's `References/` folder. Each document is a Markdown file with frontmatter:

```text
AI/Agents/banking/References/
├── KB/
│   ├── credit-card-offers.md
│   ├── heloc.md
│   └── retail-account-types-and-fees.md
└── Procedures/
    ├── credit-card-apr-reduction.md
    └── credit-card-limit-change.md
```

```markdown
---
title: Credit Card APR Reduction
description: Procedure for processing customer-requested credit card APR reductions — checks payment history, compares to the standard rate for the customer's credit-score tier, and identifies lower-APR products.
---

**1. Purpose**
This document outlines the standard procedure for ...
```

- `title` and `description` are required; documents without them are skipped. The description is used to find the document, so summarize its content and the questions it answers.
- Documents are read from the deployed Data Product — deploy the LU after changing them.
- In Studio, create documents with **New Reference...** in the AI Explorer, which asks for the pack.

The pack names are free, with one exception: the **`Procedures`** pack is also read by the planner.



## Reference Search

Enable reference search in `agent.yaml`:

```yaml
search_reference: true
```

The agent gets a `search_references(query)` tool. When it calls the tool, a dedicated reference-search sub-agent — a "librarian" with its own small tool set — lists the documents of all packs, finds the relevant ones, reads them, and returns an answer with the references it used. The main agent then answers the user in its own words.

- Retrieval is keyword-based over the documents' titles, descriptions and content.
- The librarian's instructions can be replaced with `search_reference: {prompt: "..."}`.
- Tell the agent in its system prompt when to search ("For product and policy questions, call `search_references`").



## Planning (Plan-Then-Execute)

For requests that involve several steps — "lower my APR", "raise my credit limit" — an agent can first build a plan and then execute it step by step. Planning is available to top-level agents:

```yaml
planning: true                 # plan, ask the user to approve it, then execute
# or
planning:
  approve: false               # plan and execute without asking
  prompt: |                    # optional: replace the planner's instructions
    ...
```

How it works:

1. The agent decides that the request needs a plan and calls `enter_plan_mode` with a refined statement of the request.
2. A **planner sub-agent** searches the `References/Procedures` pack, loads the matching procedure, and writes a plan whose steps use the agent's tools and sub-agents. The planner knows the agent's sub-agents, skills and tools.
3. If approval is required, the planner shows the plan to the user (as an `ask_user` question) and revises it until the user approves.
4. The plan becomes a **to-do list**. The agent executes the items in order — typically calling a specialist sub-agent with `task` for each step — and updates their status with `todo_write`.
5. The client shows the to-do list live (pending, in progress, done, failed).

### Planning Rules Added Automatically

When planning is enabled, the runtime adds a `## Planning` section to the agent's prompt with the rules for when to plan and how to execute a plan, so you do not need to write them in `system_prompt`:

- Plan when the request needs more than one sub-agent (`task` call) or more than two tool calls; answer simple lookups directly.
- Let the planner design the plan — following a written procedure when one covers the request — and do not re-plan once it is handed back.
- Execute the to-dos in order without adding steps, marking each one in progress and done.
- When a to-do asks the user something, ask and stop the turn. When the user answers, continue from the first pending to-do instead of restarting the plan.
- When a procedure check fails and the procedure says to stop, tell the user why and mark the remaining to-dos as skipped.

Planning works without procedures too: if the `Procedures` pack is missing or nothing matches, the planner plans from the request and the available tools. It does not require `search_reference` to be enabled.



## Writing Effective Reference Documents

- **One topic per document.** Short, focused documents are found and cited more accurately.
- **Write precise descriptions.** They drive retrieval.
- **Number procedure steps and name the systems involved** ("check the payment history in CCMS"), so the planner can map steps to tools and sub-agents.
- **Include eligibility rules and limits explicitly**, and back hard limits with validation in the tool flows.
- **Keep customer-specific data out of references.** References are shared knowledge; customer data comes from the Data Product.



**Next article:** [Sub-Agents and Delegation](/articles/AI_fusion/02_agents/06_subagents_and_delegation.md)
