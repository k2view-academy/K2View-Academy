# Report Creation And Data Binding

### Overview

The report creation starts from adding a data source, data set(s) and the parameter(s). Then  you can define the data binding between the report elements and the data set's fields. Last but not the least, set the report's layout design (fonts, colors, etc.).

This article provides a step-by-step guide of how to create a report and perform data binding. The next article explains how to define the report's properties.

### New Report

To initiate a new report creation, go to the Logical Unit in the project tree, right click on **Reports > New Report** and populate the report name. Click Enter to create and open an empty report canvas. 

![](images/02_create_new_rep_01.png)

### Data Source Configuration

Data binding establishes a connection between a report and the displayed data and it starts with adding a data source.

To create a data source, click the ![](images/add_icon.png) icon in the **Data Sources** area of the report's properties. It provides a choice of two templates:

- The **fabric_api** data source is defined as the **/api/** Fabric endpoint which runs under your Fabric's host. This is a default data source to be used for any Fabric API.

- The **custom data source** is an empty template that allows defining any required endpoint, by setting the following properties:

  - Endpoint - either a full or base URL of your REST API. Then the endpoint paths are appended by using data sets (they are described further).

  - HTTP Headers - the collection of HTTP Header fields that are passed with the request. 

  - Query Parameters - the collection of (name, value) pairs that define the Query String of the URL.

### Data Set Configuration

Each data source may contain one or more data sets. 

To create a data set, click the ![](images/plus_icon.png) icon near the data source name in the **Data Sources** area of the report's properties. It provides a choice of the following templates:

* The Fabric core endpoints:
  * **luTable**, defined by lu/{luName}/{@iid}/{table} Fabric endpoint.
  * **luQuery**, defined by lu/Customer/{@iid} Fabric endpoint.
  * **commonTable**, defined by common/{table} Fabric endpoint.
  * **command_get**, defined by fabric-command Fabric endpoint.
* The customer data set is an empty template that allows defining any required endpoint.







[Click for more details about data binding options in ActiveReportsJS.](https://www.grapecity.com/activereportsjs/docs/ReportAuthorGuide/Databinding)