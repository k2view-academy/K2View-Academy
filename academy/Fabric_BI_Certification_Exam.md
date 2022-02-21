# Fabric BI Certification Exam

Excellent! You have completed the Fabric BI Training. It's now time to take the following certification exam to see what you have learned. 

The exam consists of a number of multiple-choice questions, each providing a number of possible answers. Click the answer you think is correct. 



#### Question 1: Fabric BI Overview

Fabric BI is the Fabric module that:


A:  Can be used to design reports and dashboards using various data sources.


B:  Runs on a separate application server.

C:  Can be used to create a report on Fabric's LUI data.

D:  All the above.

Answer: D

#### Question 2: Fabric BI Architecture

The Fabric BI solution includes:

A: Admin and Designer applications embedded into the Fabric Web Framework.

B: A data warehouse to store the data for the generation of reports.

C: An ability to generate reports via the Fabric Jobs mechanism.

D: Answers A + C.

Answer: D

#### Question 3: Storage Management

The Storage Management is:

A:  An SQLite database schema that keeps each report as a MicroDB.

B: The Fabric BI application management DB,  based on Cassandra.

C: A database that keeps the report definition including the metadata, fonts and colors.

D: A database that keeps the report's source data.

Answer: C

#### Question 4: Configuration

To run the Fabric BI, you must install the BI server and:

A. Update the BI server host IP address in the Fabric's config.ini.

B. Update the Storage Management details in the Admin module of the Fabric BI.

C. Update the Fabric server host IP address in Fabric's config.ini.

D. Nothing further, since the BI server host IP has a default value in the Fabric's config.ini.

Answer: A

#### Question 5: Data Sources

The supported data sources in Fabric BI are:

A. Fabric, PostgreSQL and Cassandra.

B. Fabric, PostgreSQL and Oracle.

C. Fabric Common, but not the LU Instance data.

D. There is no need to define data sources in Fabric BI.

Answer: B

#### Question 6: Metadata Setup

Objects and joins are created in Fabric BI in the following manner:

A. Using a built-in metadata discovery mechanism.

B. Manually only, regardless of the data source type.

C. Via the Admin module of Fabric BI by authorized users only.

D. Answers A + C.

Answer: D

#### Question 7: Reports Creation

A report can be created using:

A. A Fabric LUI and Common tables if a join is created between a LUI and common tables.

B. A Fabric LUI and PostgreSQL tables if a join is created between a LUI and PostgreSQL tables.

C. A Fabric command using the Custom SQL Object.

D. All the above.

Answer: D

#### Question 8: Reports Creation

When creating a report that should retrieve data from several data objects, you can:

A. Select any object regardless of the joins between them.

B. Select the objects of the active data sources only.

C. Select the objects that have a join with the first selected object.

D. Select up to 5 objects per report.

Answer: C

#### Question 9: Reports Creation

When creating a report based on a Fabric LUI:

A. A LU root table must be selected even if its data is not required for the report.

B. A filter by Instance ID must be created using a parameter defined in the BI Admin module.

C. A join with Fabric Common DB must be created.

D. Answers A + B.

Answer: D

#### Question 10: Reports Generation

A report can be generated using the following method or methods:

A. Using a predefined URL that includes the Fabric host, port and the report name.

B. Via a fabric command that includes the export file format and location.

C. Manually via the Fabric Web Framework.

D. All the above.

Answer: D

#### Question 11: Reports Generation

A report's output can be:

A. Filtered only if a filter was set up during the report creation.

B. Filtered using either a pre-defined or a run-time filter or both combined.

C. Filtered using both a pre-defined and a run-time filter but cannot be sorted.

C. Cannot be filtered at run time.

Answer: B

#### Question 12: Moving Report Files

Once the reports are ready, the user can:

A. Move the files between different Storage Management DBs only if the Storage Management is PostgreSQL.

B. Move the reports structure and the source data between different Storage Management DBs.

C. Move the files between different Storage Management DBs or different table prefixes of the same Storage Management DB.

D. Move the files between different BI servers.

Answer: C