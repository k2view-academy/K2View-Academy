# Broadway Overview

## What are Broadway incentives?
Broadway was introduced to Fabric for the first time on release 6.0. It aims to enrich Fabric with the following capabilities:

* ETL (Extract Transform Load) - replacing K2view ADI (Advanced Data Integration) tool for TDM (Test Data Management) and pure ETL projects (Data migration, data masking, data cleansing, data purging, data sequences management, etc..). Broadway resolves ADI technology obstacles, as it is written in Java and supports integration with any database provides a JDBC driver.
* BPM (Business Process Management) - manage the business processes, covering both design and execution, adding the ability to define the business flow, the order of the activities and the reaction to each flow stage results.
* Data Inspection - live data inspection capabilities with holistic and dynamic view on meta data (data structure) and actual data flow.

Broadway is the only tool in the world that capture business processes and data flows under one holistic view.

## Example

* Create or truncate a table in Oracle DB.
* Call graphit Web-Service that bring Customer data including invoices and payments, store the results into a field in the new table.
* Fetch one record from the new table.
* Parse the Json output structure.
* Return output string with the list of invoices for the given customer separated by comma.

In order to accomplish the example above the following steps should be implemented:

First, define the stages in order to cover the BPM needs (order is from left to right):

![image](/articles/99_Broadway/images/Broadway_stages.png)

Secondly, define the actors and the relations between the actors, in order to cover data flows needs:

![image](/articles/99_Broadway/images/Broadway_without_inspection.png)

Third, use the data inspection capability in order to view the complex data structure by building a dynamic schema representing the meta data

![image](/articles/99_Broadway/images/Broadway_full.png)

## What are Broadway principles?



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/03_logical_units/02_create_a_logical_unit_flow.md)
