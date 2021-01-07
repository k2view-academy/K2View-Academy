# FABRIC KNOWLEDGE BASE DEMO PROJECT 

## Project Setup
The Fabric Knowledge Base project includes a set of LU, code examples and objects that are used in all Knowledge Base articles and Learning Items.

To download the KB_FABRIC_PROJECT.k2proj locally to your computer, click [here](https://github.com/k2view-academy/K2View-Academy/blob/Academy_6.2/articles/demo_project/KB_FABRIC_PROJECT.k2export).


## DBs Setup

The Fabric Knowledge Base project includes four databases that can be accessed locally from your computer. To enable this, the BILLING_DB, CRM_DB, COLLECTION_DB and ORDERS_DB databases have been created as SQLite *.db files.

To configure these databases, do the following:

1. Click [here](https://github.com/k2view-academy/K2View-Academy/tree/Academy_6.2/articles/demo_project/SqliteDB) and save the BILLING_DB.db, CRM_DB.db, COLLECTION_DB.db and ORDERS_DB.db files locally on your computer.

2. Open the Fabric Studio, create a new project and then import the [KB_FABRIC_PROJECT.export](https://github.com/k2view-academy/K2View-Academy/blob/Academy_6.2/articles/demo_project/KB_FABRIC_PROJECT.k2export) file to your project.

3. Edit the CRM_DB, BILLING_DB, COLLECTION_DB and ORDERS_DB interfaces, as follows:
    - Edit the **Database** field, set the location of the *.db file to the local directory.
    - Test the connection and save the changes.
    - Close the DB interface.
    
    [Click for more information about DB Connection Settings](/articles/05_DB_interfaces/03_DB_interfaces_overview.md).

Note, make sure to add the full path of the downloaded databases to each database in the Fabric Studio Interfaces screens.



