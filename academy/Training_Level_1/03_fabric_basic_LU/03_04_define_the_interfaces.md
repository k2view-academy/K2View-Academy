#   Define the Interfaces

![](/academy/Training_Level_1/03_fabric_basic_LU/images/fabric_main_flow_03.png)

Now your project has been defined and a preliminary analysis of the data requirements has been performed, it’s time to define the project’s interfaces. 

 An interface can be based on database or non-database files and is defined in the Fabric Studio. Some database / non-database items might be new in the system and we will also show you how to define them and use them as Interfaces.

 

### Interfaces Overview

Let’s understand which interfaces are available in the Fabric Studio. Please read [Interface Overview](/articles/05_DB_interfaces/01_interfaces_overview.md).

To define the interfaces and their respective parameters, you need to look into their sources using the following [Interfaces Source Analysis Guidelines](/articles/05_DB_interfaces/02_interfaces_source_analysis_guidelines.md).

Our Training environment has three main DBs of various types, which in essence, represent a typical Telco IT ecosystem:

-  **BILLING_DB** – Stores information required for preparing or sending a bill or invoice, such as: Payments or Balance. 

- **CRM_DB**  – Stores the information that manages a company's interaction with current and potential customers, specifically focusing on customer retention and ultimately driving sales growth. For example, Customer info, Contracts, Cases or Offers.

- **ORDERS_DB** – Usually stores information for filling the order functions required to complete a customer’s order created in a CRM such as, Orders, Order items, Items or Pricing. In the Fabric Training environment, Orders table. 

 Download the billing_db.db, crm_db.db, and orders_db.db SQLite files from the [Demo Project](/articles/demo_project/SqliteDB) and save them locally on your computer.

### How to define an Interface

Please read the [DB Interfaces Overview](/articles/05_DB_interfaces/03_DB_interfaces_overview.md) and [Creating a new database interface](/articles/05_DB_interfaces/04_creating_a_new_database_interface.md) articles to learn about the properties that define an interface. 


![](/academy/Training_Level_1/03_fabric_basic_LU/images/information.png) To learn more about the options available using Interfaces, refer to:

- [Adding a fabric and remote fabric interface type](/articles/05_DB_interfaces/05_adding_a_fabric_and_remote_fabric_interface_type.md)

- [Editing interface settings](/articles/05_DB_interfaces/06_editing_interface_settings.md)

- [Deleting disabling an interface](/articles/05_DB_interfaces/07_deleting_disabling_an_interface.md)

- [Clearing the database objects cache](/articles/05_DB_interfaces/08_clearing_the_database_objects_cache.md)


 Now let’s understand how to view the data using the interface.

### Query Builder

Similar to other DB studios, Fabric Studio’s Query Builder allows you to view source DB structures, data and to invoke complex queries:

- [Query Builder Overview](/articles/11_query_builder/01_query_builder_overview.md)

<!--Once loaded to Git: 11_1 Query Builder Overview-->

- [Query_Builder_Window]()

<!--Once loaded to Git: 11_2 Query_Builder_Window-->

- [Query_Builder_Build_SQL_Query]()

<!--Once loaded to Git: 11_3 Query_Builder_Build_SQL_Query-->

[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/03_fabric_basic_LU/02_create_a_fabric_project.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/03_fabric_basic_LU/05_define_the_interfaces_example_and_exercises.md)


