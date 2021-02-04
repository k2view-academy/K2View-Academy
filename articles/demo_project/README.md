# FABRIC KNOWLEDGE BASE DEMO PROJECT 

The Fabric Knowledge Base project includes a set of LU, code examples and objects that are used in all Knowledge Base articles and Learning Items.

## Setup Steps

The Fabric Knowledge Base project uses four databases. For this purpose, the BILLING_DB, CRM_DB, COLLECTION_DB and ORDERS_DB databases have been created as SQLite *.db files.

To setup the project and connect the databases, do the following:

1. Open the Fabric Studio and create a new project.

2. Download the [KB_FABRIC_PROJECT.k2export](KB_FABRIC_PROJECT.k2export) file and import it to your project. Use the Import All option. 

3. Download the [BILLING_DB.db](SqliteDB/billing_db.db), [CRM_DB.db](SqliteDB/crm_db.db), [COLLECTION_DB.db](SqliteDB/collection_db.db) and [ORDERS_DB.db](SqliteDB/orders_db.db) files locally on your computer.

4. Edit the CRM_DB, BILLING_DB, COLLECTION_DB and ORDERS_DB interfaces, as follows:
    - Edit the **Database** field, set the location of the *.db file to the local directory.
    - Test the connection and save the changes.
    - Close the DB interface.
    
    [Click for more information about DB Connection Settings](/articles/05_DB_interfaces/03_DB_interfaces_overview.md).

Note, make sure to add the full path of the downloaded databases to each database in the Fabric Studio Interfaces screens.



