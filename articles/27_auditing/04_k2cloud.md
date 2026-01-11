# K2cloud Auditing

In a K2cloud SaaS deployment, the [persistence strategy](03_persistence_strategy.md) is set to be `com.k2view.fabric.auditing.persistence.AuditLog`. While written into logs, it is collected into a repository, for viewing it a K2cloud pages.

>  Note: Customers using self-hosted K2cloud environments are not eligible for this capability.



## Where to View Audit Logs

### Monitoring Page

When auditing is enabled, audit entries are integrated into the logs shown on the Monitoring page, under the Fabric Monitor Logs pane. These entries are interspersed with standard logs and can be filtered using the search feature.

To view only audit records, enter `AUDIT` in the case-sensitive search bar.

#### Downloading Audit Logs

Audit data can be downloaded in either **CSV** or **plain text** format using one of the following methods:

1. **Using the UI’s Vertical Three-Dot Menu (⋮):**  
   - Click the vertical three-dot menu (⋮) in the log pane.  
   - Navigate to: `Inspect > Data`  
   - Choose the desired format (CSV or text) for export.

2. **Using a Keyboard Shortcut:**  
   - Press the `i` key to access the same `Inspect > Data` option and initiate export.



### Space Details Page

When auditing is enabled, audit entries are integrated into the Audit Log pane on the Space Details page.

To access it:

1. Click the Space's three-dot (vertical) menu.
2. Choose 'View Space Details...'
