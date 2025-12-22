# K2Cloud Auditing

On K2Cloud SaaS deployment, the [persistence strategy](03_persistence_strategy.md) is set to be `com.k2view.fabric.auditing.persistence.AuditLog`. While written into logs, it is collected into a repository, for viewing it a K2Cloud pages.

>  Note: Customers using self-hosted K2Cloud environments are not eligible for this capability.



## Where to View Audit Logs

### Monitoring page

When auditing is enabled, audit entries are integrated into the logs shown on the Monitoring page, under the Fabric Monitor Logs panel. These entries are interspersed with standard logs and can be filtered using the search feature.

To view only audit records, enter `AUDIT` into the search bar (**case-sensitive**).

#### Downloading Audit Logs

Audit data can be downloaded in either **CSV** or **plain text** formats using one of the following methods:

1. **Using the UI’s Vertical 3-dot Menu (⋮):**  
   - Click the vertical 3-dot menu (⋮) in the log panel.  
   - Navigate to: `Inspect > Data`  
   - Choose the desired format (CSV or text) for export.

2. **Using a Keyboard Shortcut:**  
   - Press the `i` key to access the same `Inspect > Data` option and initiate export.



### Space Details page

When auditing is enabled, audit entries are integrated into the Audit Log Panel at Space's Details Page.

To access it:

1. Click on the Space 3-dot menu
2. choose "View Space Details..."
