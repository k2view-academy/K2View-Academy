#   Define the Interfaces

​                                                     ![](/academy/Training_Level_1/03_fabric_basic_LU/images/fabric_main_flow_03.png)

Now your project has been defined and a preliminary analysis of the data requirements has been performed, it’s time to define the project’s interfaces. 

 An interface can be based on database or non-database files and is defined in the Fabric Studio. Some database / non-database items might be new in the system and we will also show you how to define them and use them as Interfaces.

 

### Interfaces Overview

Let’s understand which interfaces are available in the Fabric Studio. Please read [Interface Overview](/articles/05_DB_interfaces/01_interfaces_overview.md).

To define the interfaces and their respective parameters, you need to look into their sources using the following [Interfaces Source Analysis Guidelines](/articles/05_DB_interfaces/02_interfaces_source_analysis_guidelines.md).

Our Training environment has three main DBs of various types, which in essence, represent a typical Telco IT ecosystem:

-  **BILLING_DB** (type: MySQL) – Stores information required for preparing or sending a bill or invoice, such as: Payments or Balance. 

- **CRM_DB** (type: Oracle) – Stores the information that manages a company's interaction with current and potential customers, specifically focusing on customer retention and ultimately driving sales growth. For example, Customer info, Contracts, Cases or Offers.

- **ORDERS_DB** (type: PostgreSQL) – Usually stores information for filling the order functions required to complete a customer’s order created in a CRM such as, Orders, Order items, Items or Pricing. In the Fabric Training environment, Orders table. 

  

### How to define an Interface

Please read the [DB Interfaces Overview](/articles/05_DB_interfaces/03_DB_interfaces_overview.md) article to learn about the properties that define an interface. 

Let’s look at the steps for creating the interface. Please read [Creating a new database interface](/articles/05_DB_interfaces/04_creating_a_new_database_interface.md).

 

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

- [Q- uery_Builder_Window]()

<!--Once loaded to Git: 11_2 Query_Builder_Window-->

- [Query_Builder_Build_SQL_Query]()

<!--Once loaded to Git: 11_3 Query_Builder_Build_SQL_Query-->

​       

### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/example.png)Example - Define an Interface and Validate It

The main business requirement is to display a 360 Customer view which shows a customer’s activities and data. To do so, define an interface that will access the customer’s data.

Considering the information above, which interface should be used ? **CRM DB**

 Let’s do this together!

1. Open your **Fabric Studio**, go to the **Project Tree**, right click **Interface** and select **New Interface.**
2. Select **Oracle** as the **Interface type**.
3. Name the **Interface: CRM_DB**.
4. Add additional parameters based on the bolded items below. Pay attention that the **Password** equals the **user’s** **credentials**.
5. Test the **connection string**.
6. Click **Save**.

**Great!  your first interface is ready to be explored:**

Go to the **Project Tree**, right click the **CRM_DB interface** and select **Query Builder** or click ![](/academy/Training_Level_1/03_fabric_basic_LU/images/DBicon.png)  **DB**  in the Project’s main toolbar. 

The Query Builder is displayed where you can see the list of tables and execute the following sample query.

How many customers do you have?

```
Select count (*) From CRM_DB.CUSTOMER

results:10,000
```

 

###  ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png) **Exercise – Define Your Project’s Interfaces and Validate Them**

 Your Customer’s 360 view requires additional data such as Billing and Order. Using the training materials covered so far and the above example, configure the following DB interfaces:

 **BILLING_DB Details**

```
DB type: MySQL

Server: 

Port: 3306

Database: BILLING_DB

User: root

Password: 
```

1. `Question: List the tables that are part of the Schema.`

2. `Question: Which Subscriber has the largest BALANCE?`

   

 **ORDERS_DB details**

```
DB type: PostgreSQL

Server: 

Port: 5436

Database: ORDERS_DB

User: ORDERS_USER

Password: 
```



3. `Question: How many orders are in the database?` 

 

### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png)Solution- Interface Exercise Solution

1. `Answer: BALANCE, CONTRACT_OFFER_MAPPING,INVOICE, OFFER, PAYMENT, SUBSCRIBER`
2. `Answer: Subscriber ID 13261`
3. `Answer: 37351 orders`

[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/03_fabric_basic_LU/02_create_a_fabric_project.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/03_fabric_basic_LU/05_LU_flow.md)


