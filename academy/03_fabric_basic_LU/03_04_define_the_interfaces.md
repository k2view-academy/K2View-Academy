#   Define the Interfaces

​                                                     ![](/academy/03_fabric_basic_LU/images/fabric_main_flow_03.png)

Now your Project has been defined and a preliminary analysis of the data requirements has been performed, it’s time to define the Project’s interfaces. 

 An interface can be based on Database or Non-database files and is defined in the Fabric Studio. 

 Some Database / Non-Database items might be new in the system and we will also show you how to define them and use them as Interfaces.

 

### Interfaces Overview

Let’s understand which Interfaces are availablein the Fabric Studio:

[Interface Overview](/articles/05_DB_interfaces/01_interfaces_overview.md)

To define the Interfaces and their respective parameters, you need to look into their sources using the following guidelines:

[Interfaces Source Analysis Guidelines](/articles/05_DB_interfaces/02_interfaces_source_analysis_guidelines.md)

Our Training environment has three main DBs of various types, which in essence,represent a typical Telco IT ecosystem:

-  **BILLING_DB** (type: MySQL) – Stores information requiredfor preparing or sending a bill or invoice, such as: Payments orBalance. 

- **CRM_DB** (type: Oracle) – Stores theinformation that manages a company's interaction with current and potentialcustomers, specifically focusing on customer retention and ultimately drivingsales growth. For example, Customer info, Contracts, Cases or Offers.

- **ORDERS_DB** (type: PostgreSQL) – Usually stores informationfor filling the Order functions required to complete a customer’s order createdin a CRM such as, Orders, Order items, Items or Pricing. **In the Fabric Trainingenvironment, Orders table. ******

  ​

### How to define an Interface

The properties that define an Interface havethe following settings:

[DB Interfaces Overview](/articles/05_DB_interfaces/03_DB_interfaces_overview.md)

Let’s look at the steps for creating the Interface:

 [Creating a new database interface](/articles/05_DB_interfaces/04_creating_a_new_database_interface.md)

 

![](/academy/03_fabric_basic_LU/images/information.png) To learn more about the options available using Interfaces, refer to:

​	[Adding a fabric and remote fabric_ nterface type](/articles/05_DB_interfaces/05_adding_a_fabric_and_remote_fabric_interface_type.md)

​	[Editing interface settings](/articles/05_DB_interfaces/06_editing_interface_settings.md)

​	[Deleting disabling an interface](/articles/05_DB_interfaces/07_deleting_disabling_an_interface.md)

​	[Clearing the database objects cache](/articles/05_DB_interfaces/08_clearing_the_database_objects_cache.md)



 Now let’s understand how to view the data using the Interface.

### Query Builder

Similar to other DB studios, Fabric Studio’s Query Builder allowsyou to view source DB structures, data and to invoke complex queries:

[Query Builder Overview]()

<!--Once loaded to Git: 11_1 Query Builder Overview-->

[Query_Builder_Window]()

<!--Once loaded to Git: 11_2 Query_Builder_Window-->

[Query_Builder_Build_SQL_Query]()

<!--Once loaded to Git: 11_3 Query_Builder_Build_SQL_Query-->

​       

### ![](/academy/03_fabric_basic_LU/images/example.png)Example-Define an Interface and Validate It

The main business requirementis to display a 360 Customer view which shows a customer’s activities and data.Todo so, define an interface that will access the customer’s data.

Considering the information above, whichinterface should be used ? **CRM DB**

 Let’s do this together!

1. Open your **Fabric Studio**, go to the **Project Tree**, right click **Interface** and select **New Interface.**
2. Select **Oracle** as the **Interface type**.
3. Name the **Interface: CRM_DB**
4. Add additional parameters based on the bolded items below. Pay attention that the **Password** equals the **user’s** **credentials**.
5. Test the **connection string**.
6. Click **Save**.

**Great!  your first interface is ready to be explored:**

Go to the **Project Tree**, right clickthe **CRM_DB interface** and select **Query Builder** or click ![](/academy/03_fabric_basic_LU/images/DBicon.png)  **DB**  in the Project’s main toolbar. 

The Query Builder is displayed where youcan see the list of tables and execute the following sample query.

How many customers do you have?

```
Select count (*) From CRM_DB.CUSTOMER

results:10,000
```

 

###  ![](/academy/03_fabric_basic_LU/images/Exercise.png)Exercise – Define Your Project’s Interfaces and Validate Them

 Your Customer’s 360view requires additional data such as Billing and Order.Using the training materials covered so far and the aboveexample, configure the following DB Interfaces:

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

   ​

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

 

### ![](/academy/03_fabric_basic_LU/images/Solution.png)Solution- Interface Exercise Solution

1. `Answer: BALANCE, CONTRACT_OFFER_MAPPING,INVOICE, OFFER, PAYMENT, SUBSCRIBER`
2. `Answer: Subscriber ID 82`
3. `Answer: 37351 orders`



[![Previous](/articles/images/Previous.png](/academy/03_fabric_basic_LU/02_create_a_fabric_project.md)[<imgalign="right" width="60" height="54" src="/articles/images/Next.png">](/articles/03_logical_units/03_LU_schema_window.md)
