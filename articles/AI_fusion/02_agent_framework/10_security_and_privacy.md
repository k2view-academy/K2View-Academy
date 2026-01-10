# Privacy & Security

GenAI agentic framework, as part of end-to-end Data Fabric 360 solution, is ...



- **Entity-level Access Control** — ensures LLMs access only user- and context-specific data. Each interaction is scoped to the data of a single Micro-Database, representing one customer, account, or employee. These built-in guardrails prevent responses from extending beyond the boundaries of that entity’s data. A customer using a chatbot cannot query details about another customer, and an employee using an HR assistant cannot access information about a colleague. By isolating each entity, the platform ensures GenAI responses remain private, compliant, and secure.
- **Dynamic Data Masking** — automatically anonymizes Personally Identifiable Information (PII) and sensitive data prior to exposure to LLMs.
- **Role-based Permissions** — restricts access to information based on roles, ensuring that users and applications can view only data permitted by their assigned privileges. 
- **Built-in Data Access Partitioning** — implements comprehensive data segregation at entity level, by using role-based security profiles.
- **Auditing** — 
