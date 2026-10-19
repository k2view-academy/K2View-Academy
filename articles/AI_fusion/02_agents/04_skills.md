# Skills

A **skill** is a Markdown document with instructions or knowledge that an agent loads when it needs it. Skills keep the system prompt short: instead of describing every table and every rule up front, the prompt lists the available skills with a one-line description each, and the agent loads the relevant ones with the `load_skill` tool.

Typical skills:

- **Schema skills** — describe a business domain's tables, with SQL guidelines and example queries, so the agent writes correct SQL for the `query` tool.
- **How-to skills** — instructions for a type of request ("how to explain a fee", "how to handle a dispute").
- **Shared query skills** — general guidance, such as how to query the micro-database.



## Skill File Format

A skill is a `.md` file with YAML frontmatter:

```markdown
---
description: Deposit account (checking/savings) schemas - accounts, transactions, overdrafts, fees, ACH transfers - plus SQL guidelines and example queries
tables: DDA_Accounts, DDA_Transactions, DDA_Overdrafts, DDA_Fees, DDA_ACH_Transfers
---
The `### Schema` section below is generated at load time from the live data model.

## SQL Guidelines
- The schema represents a single customer — no need to filter by customer_id.
- Only reference fields that exist in the schema below.

## Example Queries
- "What is my total savings balance?" →
  `SELECT COALESCE(SUM(balance), 0) FROM DDA_Accounts WHERE account_type = 'Savings';`
```

<table>
<tbody>
<tr>
<td width="160pxl"><strong>Frontmatter key</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>description</code></td>
<td>Required. Shown in the agent's skills catalog — the model decides whether to load the skill from this line alone, so state precisely what it covers.</td>
</tr>
<tr>
<td><code>tables</code></td>
<td>Optional. A comma-separated list of LU tables. When the skill is loaded, the live schema of these tables — as DDL, with table and column documentation from the LU's documentation MTables (<code>*_doc</code>) — is appended to the body. The skill therefore always matches the deployed schema.</td>
</tr>
<tr>
<td><code>title</code></td>
<td>Optional, informational.</td>
</tr>
</tbody>
</table>

If schema generation fails, or a table is not part of the LU schema, the static body is used as is — a misconfigured skill never breaks the agent. Document reference-only tables in the body text.



## Where Skills Live

<table>
<tbody>
<tr>
<td width="260pxl"><strong>Location</strong></td>
<td width="160pxl"><strong>Scope</strong></td>
<td><strong>How an agent gets it</strong></td>
</tr>
<tr>
<td><code>&lt;LU&gt;/AI/Agents/&lt;agent&gt;/Skills/</code></td>
<td>Agent-private</td>
<td>Discovered automatically.</td>
</tr>
<tr>
<td><code>&lt;LU&gt;/AI/Skills/</code></td>
<td>LU-wide</td>
<td>Must be listed under the agent's <code>skills:</code>.</td>
</tr>
<tr>
<td><code>SharedObjects/AI/Skills/</code></td>
<td>Shared across Data Products</td>
<td>Must be listed under the agent's <code>skills:</code>.</td>
</tr>
</tbody>
</table>

LU-wide skills are the natural home for schema skills: one skill per business domain, reused by the main agent and by the sub-agent that specializes in that domain.

```yaml
skills:
  - dda-schema                         # LU-wide skill, loaded on demand
  - credit-card-schema:
      auto_load: true                  # injected into the prompt from the start
  - microdb-query                      # shared skill
```



## Loading: On Demand or Auto-Load

- **On demand** (default) — the skill appears in the `## Available Skills` catalog of the system prompt. The agent calls `load_skill` when the request needs it. The catalog is also the allow-list: the agent cannot load a skill that is not in it.
- **Auto-load** — `auto_load: true` injects the skill body under `## Loaded Skills` in the system prompt. Use it for a specialist sub-agent whose single domain schema is always needed.

When the `query` tool is enabled and the agent has skills, the prompt tells the agent to load every skill whose description covers the data it is about to query, and to answer a cross-domain question with a single JOIN rather than one query per domain.



## Skills and Sub-Agents

A sub-agent sees only the skills it declares itself. Its skill names are resolved in its parent agent's `Skills/` folder first, then among the LU-wide skills. Declare in each sub-agent exactly the skills of its domain.



## Writing Good Skills

- **Make the description specific.** "Credit card schema — cards, statements, payments, transactions, MCC categories" routes better than "Credit card data".
- **One domain per schema skill.** Smaller skills load faster and cost fewer tokens.
- **Include example questions with their SQL.** Examples teach the model your naming, joins and date handling better than rules.
- **State the scope of the data.** For entity micro-databases, say that the data belongs to one customer, so the model does not filter by customer ID.
- **Use `tables:` instead of pasting DDL.** The schema stays correct when the LU changes.
- **Remember to deploy.** Skills are read from the deployed Data Product.



**Next article:** [References and Planning](/articles/AI_fusion/02_agents/05_references_and_planning.md)
