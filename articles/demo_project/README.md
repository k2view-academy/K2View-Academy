# KNOWLEDGE_BASE_FABRIC_DEMO_PROJECT- Fabric 6.2.1

## Fabric 6.2 Project Setup
The Fabric Knowledge Base project includes a set of LU, code examples and objects that are used in all Knowledge Base articles and Learning Items.
In addition it includes a list of SQLite files for the DB interfaces of the demo project.

Please download the KB_FABRIC_PROJECT.export file and the SQLiteDB directory to your computer. 

## Fabric 6.2 DBs Setup

The Fabric Knowledge Base project includes three databases that can be accessed locally from your computer without DB Management Software. To enable this, the BILLING_DB, CRM_DB and ORDERS_DB databases have been migrated to SQLite from their initial mySQL, Oracle or PostGrSQL format.

To configure these databases, do the following:

1. Download the [Databases directory](https://github.com/k2view-academy/K2View-Academy/tree/Academy_6.2/articles/demo_project/SqliteDB) and save the BILLING_DB.db, CRM_DB.db, ORDERS_DB.db, and COLLECTION_DB.db files locally on your computer. 

2. Open the demo project in your Fabric Studio and update the **Database** property by the local path and file name of the related SQLite file on the following DB interfaces:
  - CRM_DB
  - BILLING_DB
  - ORDERS_DB
  - COLLECTION_DB 

  See additional explaination in the [Interfaces Definition](https://github.com/k2view-academy/K2View-Academy/blob/Academy_6.2/academy/Training_Level_1/03_fabric_basic_LU/03_04_define_the_interfaces.md) article.

Note, make sure to add the full path to the downloaded databases to each database in the Fabric Studio Interfaces screens.



