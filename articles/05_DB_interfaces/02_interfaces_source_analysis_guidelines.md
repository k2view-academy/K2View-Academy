# Source Analysis Guidelines

All Fabric projects include data sources which transfer data through interfaces. Therefore, when creating a Fabric project, the first step is to analyze the data sources needed for your project.
Do the following:
* Identify existing data sources and analyze the data they store.
* Define the interfaces for the data sources required for the specific implementation. 

### What Do I Need to Know Before Creating a New Interface?
1. Identify which source systems store [Digital Entities](/articles/01_fabric_overview/02_fabric_glossary.md#digital-entity) information in your project. 
2. Check the following for each identified legacy system:
    * System name: E.g. CRM.
    * Description: E.g. Customer Relationship Management.
    * Main business entities: E.g. Customer, Contract or Address.
    * Nature of data in each source: E.g. Customer Data or Financial Data. 
    * Data sources technology: E.g. a database like Oracle or SQL Server, or a non-database like files or a message queue.
3. Check the following for the database (DB) data source: 
    * DB type of the system: E.g. Oracle, MySql or PostgreSQL.
    * DB version of the system: E.g. Oracle 11g.
    * Check whether the DB has a built-in connector in Fabric. If it does not, check whether the DB has a JDBC driver.
    * Check the connection details to the DB.
4. Check the following for a non-database (DB) data source (depending on the data source’s technology): 
    * Server connection details.
    * Protocol: E.g. SFTP or HTTP.
    * Format: E.g. JSON.
    * For a file-based integration, check the location, size and frequency of the files.
5. Evaluate the requirements for the project’s hardware sizing based on the expected data volumes.
6. Identify the relationships in each system:
    * Which source tables are related to the Digital Entities in your project?
    * Check for ERD / data model documents for a better understanding of the relationships between the source tables.
    * Relationship of the source system to other systems. For example, check if the Billing system holds CRM Customer ID. 
    * Map duplicate information across several legacy systems and check which system is the data master.


[![Previous](/articles/images/Previous.png)](/articles/05_DB_interfaces/01_interfaces_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/05_DB_interfaces/03_DB_interfaces_overview.md) 




