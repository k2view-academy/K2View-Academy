# KNOWLEDGE_BASE_FABRIC_DEMO_PROJECT- Fabric 6.2.1

## Fabric 6.2 Project Setup
Fabric's Knowledge Base project includes a set of Logical Units, code examples and objects needed throughout the Knowledge Base articles or Learning Items:

Download KB_FABRIC_PROJECT.k2proj locally on your computer from this [location](https://github.com/k2view-academy/K2View-Academy/blob/Academy_6.2/articles/demo_project/KB_FABRIC_PROJECT.k2export).


## Fabric 6.2 DBs Setup

Fabric's Knowledge Base project includes three Databases that can be accessed locally from your computer without any DB Management Software.

For this purpose, all 3 databases **BILLING_DB**, **CRM_DB**, **ORDERS_DB** have been especially migrated to SQLite from their initial respective format mySQL, Oracle & PostGrSQL.

To configure these databases, execute the following steps:

1. Save the following *.db* files (BILLING_DB.db, CRM_DB.db, ORDERS_DB.db ) locally on your computer from the [following directory](https://github.com/k2view-academy/K2View-Academy/tree/Academy_6.2/articles/demo_project/SqliteDB).

2. Define SQLite type interfaces for each of these as explained in the [Interfaces Definition](https://github.com/k2view-academy/K2View-Academy/blob/Academy_6.2/academy/Training_Level_1/03_fabric_basic_LU/03_04_define_the_interfaces.md) article.

N.B. Add the full location path (to where you downloaded the 3 DBs) to each of the DBs in the Fabric Studio interfaces screens.



