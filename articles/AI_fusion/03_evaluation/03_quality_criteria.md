# Quality Criteria and Thresholds

Quality criteria are the dimensions used to evaluate your AI agent's responses.

Evaluation happens at two levels:

1. **Response level** – each agent response receives a score (1–5) for every criterion being measured.
2. **Conversation level** – the entire conversation is evaluated against the defined thresholds.

A test passes only if all configured thresholds are satisfied.

## Default Quality Criteria

Several standard criteria are provided out of the box. You can use them or define **custom** criteria for your business, as additional or alternative dimensions.

* **Accuracy** - measures whether the information is factually correct and complete. Inaccurate information damages customer trust and can cause problems, like wrong payments, missed deadlines, incorrect decisions.

  Example: "What's my checking account balance?"

  |          | Response                                                     | Score |
  | -------- | ------------------------------------------------------------ | ----- |
  | **High** | "Your checking account (ending in 1234) has a balance of $1,234.56 as of today." | 5     |
  | **Low**  | "Your checking account has about $1,200." (wrong amount, no details) | 2     |

  

* **Relevance** - Measures whether the response actually answers the customer's question.
  Example: "What's my checking account balance?"

  |          | Response                                                     | Score |
  | -------- | ------------------------------------------------------------ | ----- |
  | **High** | "Your checking account (ending in 1234) has a balance of $1,234.56 as of today." | 5     |
  | **Low**  | "We offer many types of accounts including checking, savings, and CDs. Checking accounts are great for everyday transactions..." | 1     |




* **Clarity** - measures whether the response is easy to understand and well-organized

  Example: "What are my account balances?"

  **High (score 5):**

  ```
  Your account balances:
  - Checking (1234): $1,234.56
  - Savings (5678): $5,678.90
  - Total: $6,913.46
  ```

  **Low (score 1):**

  ```
  Your checking which is account number 1234 has $1234.56 in it
  and then there's also your savings account that's number 5678
  with $5678.90 so altogether that's $6913.46 between both accounts.
  ```

  

* **Politeness** - measures whether the tone is professional, respectful, and appropriate. This criterion typically matters more for customer-facing conversations than for internal support interactions (like done, for example, by CRM representatives).

  Example: "What's my checking account balance?"

  |          | Response                                                     | Score |
  | -------- | ------------------------------------------------------------ | ----- |
  | **High** | "I'd be happy to help! Your checking account currently has $1,234.56. Is there anything else I can assist you with?" | 5     |
  | **Low**  | "Your balance is $1234.56. Next question."                   | 1     |




## Custom Quality Criteria

You can create custom dimensions specific to your business. Some examples:

- **Compliance** (Banking) - Does the response include required regulatory disclosures?
- **Security Awareness** (Banking) - Does the agent avoid exposing sensitive data like full account numbers or SSN?
- **HIPAA Compliance** (Healthcare) - Does the response protect patient privacy?
- **Problem Resolution** (Customer Service) - Does the agent actually solve the customer's issue?
- **Empathy** (Customer Service) - Does the agent acknowledge the customer's frustration?

When creating a custom criterion, provide:
- A clear name
- A description of what the criterion measures
- A threshold definition



## Scoring Scale Reference

| Score | Meaning     | Description                               |
|-------|-------------|-------------------------------------------|
| 5     | Excellent   | Exceptional quality, exceeds expectations |
| 4     | Good        | Solid quality, meets expectations         |
| 3     | Fair        | Below expectations, needs improvement     |
| 2     | Poor        | Significant issues                        |
| 1     | Failing     | Unacceptable quality                      |



## Understanding Thresholds

A threshold is the minimum acceptable score. A test passes only if all thresholds are met.

### Two Threshold Types

**MIN** — Every single response in the conversation must meet this score.
Use for non-negotiable requirements: accuracy, compliance, security.

> Example: Accuracy MIN 4.0 = every response must score at least 4.0

**AVERAGE** — The average score across all responses must meet this value.
Use for stylistic qualities where some variation is acceptable: clarity, politeness.

> Example: Clarity AVERAGE 4.0 = some responses can score 3.5 if others are 4.5, as long as the average is 4.0 or above

### Recommended Starting Points

| Criterion  | Recommended | Critical Systems |
| ---------- | ----------- | ---------------- |
| Accuracy   | MIN 4.0     | MIN 4.5          |
| Relevance  | MIN 4.0     | MIN 4.0          |
| Clarity    | AVG 3.5-4.0 | AVG 4.0          |
| Politeness | AVG 3.5     | AVG 3.5          |

These are defaults — adjust based on actual scores once you start running tests. If your agent consistently scores 3.8 on accuracy, either improve the agent or lower the threshold temporarily and raise it gradually.



## Putting It Together: A Scored Example

**Question:** "What are my account balances?"

**Agent Response:** "You have three accounts: Checking (1234) with $1,234.56, Savings (5678) with $5,678.90, and Credit Card (9012) with a balance of -$456.78 (amount owed). All balances as of today, December 3rd."

**Scores:**
- Accuracy: **5.0** — All balances correct, all accounts listed, current date included
- Relevance: **5.0** — Directly answers the question
- Clarity: **4.5** — Well-organized, easy to read
- Politeness: **4.0** — Professional, but no greeting or closing

**Threshold check:**

| Criterion  | Score | Threshold  | Result |
|------------|-------|------------|--------|
| Accuracy   | 5.0   | MIN 4.0    | PASS   |
| Relevance  | 5.0   | MIN 4.0    | PASS   |
| Clarity    | 4.5   | AVG 4.0    | PASS   |
| Politeness | 4.0   | AVG 3.5    | PASS   |

**Overall: PASS**
