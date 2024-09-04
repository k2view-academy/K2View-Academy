# Pre-Integrated Applications Overview

The K2view Web Framework provides a list of pre-integrated applications that can be accessed from the Framework's toolbar. Since the Admin is the first application in the **apps.json** file, it is displayed by default when a user logs into the Framework. 

Click <img src="images/30_02_icon.PNG" alt="image" style="zoom:67%;" /> to open the list of applications:

- [Admin](03_web_admin_application.md) - a web application that manages various Fabric Server and Cassandra activities, such as monitoring batch processes, managing credentials or executing Fabric commands.
  As part of the [Admin](03_web_admin_application.md) application (a new tab called 'Configuration') we have launched a new tool, where its main objective is to expose an interface that enables a user with suitable permissions to the Admin page to change values in the config.ini file, for a specific node, and to save these changes (overrides) in the system DB.
- [Reports](/articles/38_reports/01_reports_overview.md) - a Reports Viewer application, which enables generating reports, created via the Designer component embedded into the Web Studio.
- [Statistics](/articles/34_JMX_statistics/01_JMX_overview.md) - JMX monitoring of the processes that run on the Fabric server, Cassandra, etc.
- [API](/articles/15_web_services_and_graphit/09_swagger.md) - a full framework implementation for invocating and testing Fabric Web Services via a visualization utility - Swagger UI.
- [Trace](/articles/29_tracing/05_trace_view.md) - a powerful Fabric mechanism for writing internal operations performed by Fabric into trace files. Tracing can be set on both global and session levels. The result of the tracing process is logged into a file that can be loaded for viewing in the Web Framework.
- [Data Explorer](/articles/36_data_editor/01_data_editor_overview.md) - a web utility for viewing and editing Fabric [LU](/articles/06_LU_tables/01_LU_tables_overview.md) and [reference](/articles/22_reference(commonDB)_tables/01_fabric_commonDB_overview.md) data. The Data Editor also shows the data structure and relationships between the tables within a Fabric LU schema.
- Documentation - links to frequently-used K2view documentation, such as the Knowledge Base, Fabric Java docs or Web Framework integration documentation.



[![Previous](/articles/images/Previous.png)](01_web_framework_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_web_admin_application.md) 
