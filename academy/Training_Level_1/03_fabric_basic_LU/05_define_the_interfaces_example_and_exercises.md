 
![](/academy/Training_Level_1/03_fabric_basic_LU/images/example.png)

### Example - Define an Interface and Validate It

The main business requirement is to display a 360 Customer view which shows a customer’s activities and data. To do so, define an interface that will access the customer’s data.

Considering the information above, which interface should be used ? **CRM DB**

 Let’s do this together!

1. Open your **Fabric Studio**, go to the **Project Tree**, right click **Interface** and select **New Interface.**
2. Select **SQLite** as the **Interface Type**.
3. Name the **Interface: CRM_DB**.
4. Populate the **Database** property using the full path of **cdm_db.db** in your computer. For example: C:\Training\SqliteDB\crm_db.db
5. Test the **connection string**.
6. Click **Save**.

**Your first interface is ready to be explored:**

Go to the **Project Tree**, right click the **CRM_DB interface** and select **Query Builder** or click ![](/academy/Training_Level_1/03_fabric_basic_LU/images/DBicon.png)  **DB**  in the Project’s main toolbar. 

The Query Builder is displayed where you can see the list of tables. Which SQL query do you need to execute to show how many customers there are in the Customer table?

```sql
Select count (*) From CUSTOMER

```

 

![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png) 

### **Exercise – Define Your Project’s Interfaces and Validate Them**

Your Customer’s 360 view requires additional data such as Billing and Order. Using the Training materials covered so far and the above example, configure the following DB interfaces:

 **BILLING_DB Details**

```
Interface type: SQLite

Database: [full path of the billing_db.db SQLite file. For example: C:\Training\SqliteDB\billing_db.db]

```

1. `Question: List the tables that are part of the Schema.`

2. `Question: Which command would you execute to show which subscriber has the largest BALANCE?`

   

 **ORDERS_DB details**

```
Interface type: SQLite

Database: [full path of the orders_db.db SQLite file. For example: C:\Training\SqliteDB\orders_db.db]
 
```


3. `Question: Which command would you execute to show the number of orders are there in the database?` 


[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/03_fabric_basic_LU/03_04_define_the_interfaces.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/03_fabric_basic_LU/06_define_the_interfaces_solutions.md)

