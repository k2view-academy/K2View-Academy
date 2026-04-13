# Observation Dashboard

The Observation dashboard is the primary interface for monitoring AI agent activity in production. It provides a consolidated view of conversation quality, volume, and risk across a configurable time window, combining high-level KPI widgets with a filterable conversation table that links directly to individual session reviews.

<img src="images/observation_overview.png" alt="Observation dashboard" style="zoom:80%;" />



## Accessing the Dashboard

Navigate to:

```
AI Data Fusion > Observation
```

The dashboard loads with the **Last 7 Days** time window selected by default.



## Dashboard Layout

The dashboard is organized in four areas, top to bottom:

1. **Header bar** - page title, inline summary stats for the selected period, and the duration filter
2. **Info cards** - four KPI cards showing averages for the selected period
3. **Chart widgets** - charts covering topics, sentiment, volume, satisfaction, resolution, and cost. This is the default widgets, which can be changed, replaced, reorganized, as well as extended, per needs
4. **Conversation table** - a filterable list of individual sessions with a summary per row



## Header Bar

The header bar displays the title **Conversation Analytics** alongside a set of inline summary indicators that give an immediate at-a-glance view of the selected period:

- **Total** - total number of conversations
- **Not Resolved %** - percentage of conversations tagged as not resolved
- **Follow-ups** - count of conversations requiring follow-up
- **At Risk %** - percentage of conversations flagged as at risk
- **Resolved %** - percentage of conversations tagged as resolved

The **duration filter** is on the right side of the header bar.



## Duration Filter

The duration filter controls the time window for all widgets and the conversation table simultaneously.

<table>
<tbody>
<tr>
<td><strong>Option</strong></td>
<td><strong>Use case</strong></td>
</tr>
<tr>
<td><strong>Last 24 Hours</strong></td>
<td>Monitoring activity since the last deployment or investigating a recent spike</td>
</tr>
<tr>
<td><strong>Last 7 Days</strong></td>
<td>Default weekly review; identifies emerging patterns</td>
</tr>
<tr>
<td><strong>Last 30 Days</strong></td>
<td>Monthly performance assessment and trend tracking</td>
</tr>
<tr>
<td><strong>Last 90 Days</strong></td>
<td>Quarterly analysis and long-term quality trends</td>
</tr>
</tbody>
</table>

Changing the duration refreshes all widgets and the conversation table without reloading the page.



## Info Card Widgets

Four info cards are displayed side by side, each showing an average for the selected period:

- **Avg. Resolution Rate** - percentage of conversations that reached a resolved state
- **Avg. Tokens / Conversation** - mean total tokens per session, with an input/output breakdown shown below the main figure
- **Avg. Steps / Conversation** - mean number of agent execution steps per session; indicates conversation complexity
- **Avg. Response Time** - mean end-to-end response time per turn, in seconds

Each card's query is configurable. See [Configuring Observation](03_configuring_observation.md) for how to add or modify widgets.



## Chart Widgets

Chart widgets are displayed in a 2 or 3 grid (depending on width and screen resolution), covering the key dimensions of conversation quality and cost.

Below are the **default** widgets:

**Top Conversation Topics** - Bar chart ranking topics by session count. Reveals which subjects drive the most volume (e.g., Billing Inquiry, Account Update, Technical Support).

**Customer Sentiment** - Pie chart showing the split between Positive, Neutral, and Negative conversations. Gives an immediate read on overall customer experience.

**Conversation Volume Over Time** - Line chart of daily session count. Highlights traffic patterns, spikes after deployments, and quiet periods.

**Satisfaction Trend (%)** - Line chart of satisfaction percentage over time. Use this to track whether quality is improving or declining following agent changes.

**Resolution Pathway** - Bar chart showing how far conversations progress through the resolution stages (Initial Contact → Issue Identified → Solution Provided → Customer Engagement → Resolution Achieved). Identifies where conversations tend to drop off.

**Token Consumption** - Bar chart of input, cache and output token usage over time. Use for cost tracking and detecting unusual consumption spikes.

All widgets are configurable. See [Configuring Observation](03_configuring_observation.md) for how to add, modify, or replace widgets.



## Conversation Table

The **Conversation Details** table lists individual sessions for the selected period. A filter dropdown above the table narrows the list by conversation type.

### Columns

<table>
<tbody>
<tr>
<td><strong>Column</strong></td>
<td><strong>Data field</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><strong>Date</strong></td>
<td>CREATION_DATE</td>
<td>Session timestamp, formatted as YYYY-MM-DD HH:MM</td>
</tr>
<tr>
<td><strong>Customer</strong></td>
<td>CUSTOMER_ID</td>
<td>Customer identifier (IID), displayed with a # prefix</td>
</tr>
<tr>
<td><strong>Topics</strong></td>
<td>TOPICS</td>
<td>Auto-tagged topic labels; up to two topics shown, with hover text for the full list</td>
</tr>
<tr>
<td><strong>Resolution</strong></td>
<td>RESOLUTION_STATUS</td>
<td>Colored badge: <strong>Resolved</strong> (green), <strong>Pending</strong> (yellow), <strong>Escalated</strong> (red)</td>
</tr>
<tr>
<td><strong>Risk</strong></td>
<td>RISK_LEVEL</td>
<td>Colored badge: <strong>Low</strong> (green), <strong>Medium</strong> (orange), <strong>High</strong> (red)</td>
</tr>
<tr>
<td><strong>Avg Response</strong></td>
<td>AVG_RESPONSE_TIME</td>
<td>Mean response time per agent turn in this session, shown in seconds (e.g., 5.2s)</td>
</tr>
<tr>
<td><strong>Submitted By</strong></td>
<td>SUBMITTED_BY</td>
<td>User or system identity that initiated the session</td>
</tr>
<tr>
<td><strong>App ID</strong></td>
<td>APP_ID</td>
<td>The AI Fusion application that handled the session</td>
</tr>
<tr>
<td><strong>Summary</strong></td>
<td>CHAT_SUMMARY</td>
<td>LLM-generated summary snippet; hover for full text</td>
</tr>
</tbody>
</table>

### Filter Modes

<table>
<tbody>
<tr>
<td><strong>Filter</strong></td>
<td><strong>What it shows</strong></td>
</tr>
<tr>
<td><strong>All</strong></td>
<td>All conversations in the selected period (default)</td>
</tr>
<tr>
<td><strong>High Risk Conversations</strong></td>
<td>Sessions with HIGH risk level</td>
</tr>
<tr>
<td><strong>Unresolved Conversations</strong></td>
<td>Sessions tagged as Pending or Escalated</td>
</tr>
<tr>
<td><strong>Customer ID</strong></td>
<td>All sessions for a specific customer IID. Selecting this option reveals a text input for the customer ID</td>
</tr>
<tr>
<td><strong>Submitted By</strong></td>
<td>All sessions initiated by a specific user, for example a CRM representative. Selecting this option reveals a text input</td>
</tr>
<tr>
<td><strong>App ID</strong></td>
<td>All sessions handled by a specific application. Selecting this option reveals a text input</td>
</tr>
</tbody>
</table>



## Exporting Data

Click the **Export List** button above the conversation table to download the current view (respecting the active duration and filter) as a file. Use this for reporting, sharing with stakeholders, or loading into external analytics tools.



## Navigating to Conversation Review

Click any row in the conversation table to open the [Conversation Review](02_conversation_review.md) page for that session. The review page shows the full message history, auto-tags, user feedback, and execution trace.



**Next article:** [Conversation Review](02_conversation_review.md)
