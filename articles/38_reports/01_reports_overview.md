# Reports Overview

<web>

Starting with V7.1, Fabric includes a **Reports** application, which enables building interactive pixel-perfect reports. This application supports data binding to a wide range of JSON data sources, by providing a flexible connection configuration to a REST API endpoint.

Fabric's **Reports** is a lightweight reporting solution, implemented using the 3rd party application - *ActiveReportsJS* - which is embedded into the [K2View Web Framework](/articles/30_web_framework/01_web_framework_overview.md) and Fabric Web Studio. The *ActiveReportsJS* license is included within Fabric license. No separate installation or configuration is required. 

Fabric's **Reports** application includes a Designer and a Viewer components:

* **Designer** - enables creating a report template, including the following: 
  - Definition of data sources, data sets and parameters.
  - Binding the report's elements to the data set's fields.
  - Designing the report's layout (fonts, colors, borders, etc). 
* **Viewer** - enables generating a report, including the following:
  - Generating the report's preview.
  - Searching for data within the preview file.
  - Downloading the extract files. The supported formats are: PDF, HTML and CSV.

Access to the Designer component is available via Fabric Web Studio only. Access to the Viewer component is available via the Web Framework application's list.

Follow this user guide to learn more about each step of the reports setup, implementation and generation.

[Click for more information about *ActiveReportsJS*.](https://www.grapecity.com/activereportsjs/docs/GettingStarted/Introduction)



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_create_new_report.md) 

</web>

<studio>

The reports creation is possible only via the Fabric Web Studio.

</studio>
