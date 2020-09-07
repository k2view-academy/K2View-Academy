# KNOWLEDGE_BASE_FABRIC_DEMO_PROJECT- Fabric 6.2.1

## Fabric 6.2 Project Setup
The Fabric Knowledge Base project includes a set of LU, code examples and objects that are used in all Knowledge Base articles and Learning Items.

To download the KB_FABRIC_PROJECT.k2proj locally to your computer, click [here](https://github.com/k2view-academy/K2View-Academy/blob/Academy_6.2/articles/demo_project/KB_FABRIC_PROJECT.k2export).


## Fabric 6.2 DBs Setup

The Fabric Knowledge Base project includes three databases that can be accessed locally from your computer without DB Management Software.

To enable this all three **BILLING_DB**, **CRM_DB**, **ORDERS_DB** databases have been migrated to SQLite from their initial mySQL, Oracle or PostGrSQL format.

To configure these databases, do the following:

1. Click [Databases directory](https://github.com/k2view-academy/K2View-Academy/tree/Academy_6.2/articles/demo_project/SqliteDB) and save the BILLING_DB.db, CRM_DB.db, ORDERS_DB.db *.db* files locally on your computer. 

2. Define SQLite type interfaces for each database as explained in the [Interfaces Definition](https://github.com/k2view-academy/K2View-Academy/blob/Academy_6.2/academy/Training_Level_1/03_fabric_basic_LU/03_04_define_the_interfaces.md) article.

Note, make sure to add the full path to the downloaded databases to each database in the Fabric Studio Interfaces screens.



