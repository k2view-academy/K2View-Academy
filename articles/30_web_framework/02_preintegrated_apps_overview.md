# Pre-Integrated Applications Overview

The K2View Web Framework provides a list of pre-integrated applications that can be accessed from the Framework's toolbar. Since the Admin is the first application in the **apps.json** file, it is displayed by default when a user logs into the Framework. 

<img src="images/30_02_toolbar.PNG" alt="image" style="zoom:80%;" />

Click <img src="images/30_02_icon.PNG" alt="image" style="zoom:67%;" /> to open the list of applications:

- [Admin](03_web_admin_application.md) - a web application that manages various Fabric Server and Cassandra activities, such as monitoring batch processes, managing credentials or executing Fabric commands.
- [Graphit](/articles/15_web_services_and_graphit/17_Graphit/01_graphit_overview.md) - a UI utility for designing Fabric [Web Services](/articles/15_web_services_and_graphit/01_web_services_overview.md) responses. Graphit provides a very user-friendly interface and requires minimal coding while enabling powerful debugging capabilities. The content of a response is defined during its execution, either according to specific parameters relevant to the specific Web Service call, or by retrieving dynamic information from other databases or interfaces.
- Statistics - JMX monitoring of the processes running on the Fabric server, Cassandra, etc.
- [Swagger](/articles/15_web_services_and_graphit/09_swagger.md) - a full framework implementation for invocating and testing Fabric Web Services.
- [Trace](/articles/29_tracing/05_trace_view.md) - a powerful Fabric mechanism for writing internal operations performed by Fabric into trace files. Tracing can be set on both global and session levels. The result of the tracing process is logged into a file that can be loaded for viewing in the Web Framework.
- Documentation - links to frequently-used K2View documentation, such as the Knowledge Base and Fabric Java docs.
<!-- - 6.4 Data Catalog - TBD-->
<!-- - 6.4 Data Editor - TBD-->



[![Previous](/articles/images/Previous.png)](01_web_framework_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_web_admin_application.md) 
