#   Defining Interfaces

![](/academy/Training_Level_1/03_fabric_basic_LU/images/fabric_main_flow_03.png)

Now that your project has been defined and a preliminary analysis of the data requirements has been performed, it’s time to define the project’s interfaces. 

 An interface can be based on database or non-database files and is defined in the Fabric Studio. Some database / non-database items might be new in the system and we will also show you how to define them and use them as interfaces.

 

### Interfaces Overview

Let’s understand which interfaces are available in the Fabric Studio. Please read [Interface Overview](/articles/05_DB_interfaces/01_interfaces_overview.md).

To define the interfaces and their respective parameters, read about their sources in the [Interfaces Source Analysis Guidelines](/articles/05_DB_interfaces/02_interfaces_source_analysis_guidelines.md).

Our Training environment has three main and different DBs, which in essence, represent a typical Telco IT ecosystem:

-  **BILLING_DB**, stores information required for preparing or sending a bill or invoice. For example, Payments or Balance. 

- **CRM_DB**, stores the information that manages a company's interaction with current and potential customers, specifically focusing on customer retention and ultimately driving sales growth. For example, Customer info, Contracts, Cases or Offers.

- **ORDERS_DB**, usually stores information for filling an order's functions required to complete a customer’s order created in a CRM. For example, Orders, Order items, Items or Pricing and in the Fabric Training environment, an Orders table. 

 Download the billing_db.db, crm_db.db, and orders_db.db SQLite files from the [Demo Project](/articles/demo_project/SqliteDB) and save them locally on your computer.

### Instructions for Defining an Interface 

Please read the [DB Interfaces Overview](/articles/05_DB_interfaces/03_DB_interfaces_overview.md) and [Creating a New Database Interface](/articles/05_DB_interfaces/04_creating_a_new_database_interface.md) articles to learn about the properties that define an interface. 


![](/academy/Training_Level_1/03_fabric_basic_LU/images/information.png) To learn more about the options available using interfaces, refer to:

- [Adding a Fabric and Remote Fabric Interface Type](/articles/05_DB_interfaces/05_adding_a_fabric_and_remote_fabric_interface_type.md).
- [Editing Interface Settings](/articles/05_DB_interfaces/06_editing_interface_settings.md).
- [Deleting / Disabling an Interface](/articles/05_DB_interfaces/07_deleting_disabling_an_interface.md).
- [Clearing the Database Objects Cache](/articles/05_DB_interfaces/08_clearing_the_database_objects_cache.md).


 Now let’s understand how to view the data using the interface and the Query Builder.

### Query Builder

Similar to other DB studios, Fabric Studio’s Query Builder allows you to view source DB structures, data and to invoke complex queries. Please read:

- [Query Builder Overview](/articles/11_query_builder/01_query_builder_overview.md).
- [Query_Builder_Window](/articles/11_query_builder/02_query_builder_window.md).
- [Query_Builder_Build_SQL_Query](/articles/11_query_builder/03_building_and_running_an_sql_query.md#main-window---editing-an-sql-query).


