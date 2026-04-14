# Configuring Observation

Observation works out of the box once enabled, but several aspects can be configured to match your organization's monitoring needs: the application-level on/off switch, custom conversation tags, custom dashboard widgets, and the underlying database.



## Enabling Observation

Observation is enabled or disabled in the web menu configuration file - `apps.json`
1. look for "aifusion" appid entry
2. Add/remove the Observation entry in the apps array.
   like `{ "displayName": "Observation", "pathName": "observation" }`



## Conversation Tags

The auto-tagging system produces a standard set of tags for every conversation (Topic, Sentiment, Resolution Status, Risk Level, Follow-up Required). These are stored in the **INSIGHTS** table in the Observation database.

The signals extracted from each conversation are defined in the **`chat_signals.csv`** MTable. Each row defines one signal with its name, allowed values, and whether multiple values can be assigned (multi-select).

Default signals include: Topic, Sentiment, Resolution Status, Urgency Level, Follow-up, Complaint Severity, Upsell Opportunity, and others. Edit the MTable in Fabric Studio to add, remove, or modify signals for your domain.



## Custom Dashboard Widgets

Dashboard widgets are defined in the **`widgets` MTable** in Fabric Studio. Each row in this table defines one widget that appears in the Observation dashboard.

### Widget Configuration Fields

<table>
<tbody>
<tr>
<td><strong>Field</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><strong>id</strong></td>
<td>Unique widget identifier</td>
</tr>
<tr>
<td><strong>title</strong></td>
<td>Display title shown in the dashboard header</td>
</tr>
<tr>
<td><strong>type</strong></td>
<td>Widget type: <code>box</code> for a KPI card, or a chart type (e.g., <code>bar</code>, <code>pie</code>, <code>line</code>)</td>
</tr>
<tr>
<td><strong>xKey</strong></td>
<td>For chart widgets: the field used for the x-axis or category grouping</td>
</tr>
<tr>
<td><strong>series</strong></td>
<td>For chart widgets: the data series to plot (JSON array)</td>
</tr>
<tr>
<td><strong>action</strong></td>
<td>The SQL query that retrieves the widget's data from the Observation database</td>
</tr>
</tbody>
</table>

### Widget Types

**`box` (KPI card)**

Displays a single aggregated value. The `action` SQL query must return a single scalar (e.g., a count or percentage).

Example - count of unresolved conversations:
```sql
SELECT COUNT(*) FROM INSIGHTS
WHERE RESOLUTION_STATUS = 'Not Resolved'
AND CREATION_DATE >= :startDate
```

**Chart widgets**

Chart widgets (`bar`, `pie`, `line`) plot distributions or trends. The `action` query must return rows suitable for the chosen chart type, and `xKey` specifies which column to use as the category axis.



## Database Configuration

Observation data is stored in the **Metrics/Assurance database**. Two database types are supported:

<table>
<tbody>
<tr>
<td><strong>Database</strong></td>
<td><strong>Use case</strong></td>
</tr>
<tr>
<td><strong>SQLite</strong></td>
<td>Development and local testing (Studio mode); zero configuration, file-based</td>
</tr>
<tr>
<td><strong>PostgreSQL</strong></td>
<td>QA and production environments; supports concurrent writes and large data volumes</td>
</tr>
</tbody>
</table>

The database type is configured at the Fabric interface level. SQLite is used automatically when no PostgreSQL interface is configured. For production deployments, configure a PostgreSQL interface named `METRICS_DB` (or per your implementation's naming convention) in Fabric Studio.



## Running the Analysis

Conversation tagging and signal extraction do not run automatically. Analysis must be triggered by running the RunAnalyze.flow or by the related Pipeline.

```
broadway aifusion.RunAnalyze 'day'='7'
```

The `day` parameter controls how far back to look. Sessions already analyzed (status `CLOSED`) are skipped.

