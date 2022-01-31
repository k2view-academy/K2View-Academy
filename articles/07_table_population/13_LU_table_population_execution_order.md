# LU Table Population - Execution Order

Fabric enables setting the execution order of [LU table](/articles/06_LU_tables/01_LU_tables_overview.md) population objects within an [LU schema](/articles/03_logical_units/03_LU_schema_window.md). The execution order is set by a sequence number (1, 2, 3…).

The default order of an LU table’s population is set when the LU schema is created. The default population execution order is based on the LU schema’s hierarchy. However, a different population execution order can be set above the automatically generated execution order. Meaning that a table higher in the LU schema’s hierarchy can be populated or modified after a table lower in the LU schema hierarchy has been modified or populated. Therefore, it is imperative to strategize and define the correct execution order of the [LU Table populations](/articles/07_table_population/01_table_population_overview.md).

**Parallel Sync Ability**

By default, the populations with the same execution order run sequentially **within the same execution order**.

To improve the sync time of an LUI, Fabric can support parallel syncs on several populations within the same execution order. This can be set in config.ini using the parameter **MAX_PARALLEL_SYNC_SAME_ORDER (default value = 1)**. When this parameter is greater than 1, it allows Fabric to run the LU Table Populations with the same execution order in parallel groups (according to the parameter’s value) in order to bring data from the source DB faster. The part of the sync that writes the data into Fabric remains sequential.

### How Do I Set the Population Order?

The LU schema's Root Table is always populated first. By default, its execution order is set to 1. Other tables in the LU schema are populated according to their hierarchy level in the LU schema in a parent plus one order.
By default, all LU table populations on the same hierarchy level in an LU schema have the same execution order. The order can be modified and have different values if needed. For example, when tables T2 and T3 table are added to a schema under table T1, the population execution order of tables T2 and T3 will be the same and will be higher than the execution order of table T1.
An LU table may have more than one Table Population. By default, all populations in the same LU table are set to the same population execution order, which can also be updated manually if needed. 

**Example**

The following diagram displays an LU schema with four tables: Customer, Subscriber, Activity and Address.


![image](images/07_13_01_screen.png)

*	**Customer** is a Root Table. It has one population. Its execution order = 1, indicating that the Customer table will always be populated first in the LU.
*	**Subscriber** and **Activity** tables are on the second level in the LU schema’s hierarchy: 
    *	**Subscriber** table has one population: popSub. 
    *	**Activity** table has two populations: query_CRM_DB_ACTIVITY and pop_Activity_UP. 
    *	The execution order of popSub and query_CRM_DB_ACTIVITY = 2 and the tables will be populated simultaneously.
    *	The execution order of pop_Activity_UP = 5. It is set to a higher value in order to be executed after all other LU tables are populated.
*	**Address** table is **also** on the second level in the LU schema’s hierarchy. It has two populations: popAddressCust and popAddressSub. 
    *	The execution order of the popAddressCust population = 2. It will be executed simultaneously with the popSub and query_CRM_DB_ACTIVITY
    *	Population popAddressSub has execution order = 3. It will be executed after popAddressCust.

Note that if **MAX_PARALLEL_SYNC_SAME_ORDER** is set to 1 in config.ini, the above three LU table populations whose execution order = 2 will be executed sequentially. However, if **MAX_PARALLEL_SYNC_SAME_ORDER** is set to 3 or higher, all three LU Table Populations will fetch the data from the source DB in parallel and will then populate the target DB sequentially.  
 
### How Do I Disable and Enable a Population? 
The population in an LU schema can be disabled / enabled according to requirements. For example, if a Table Population is not required temporarily it can be disabled and be later enabled instead of being deleted. 

**Disable a Population**

Go to the [**LU Schema window**](/articles/03_logical_units/03_LU_schema_window.md),  click the right corner of **Table Population** and unselect **Disable Population**. A red **No Entry** icon is displayed next to the execution order.

**Enable a Population**

Go to the [**LU Schema window**](/articles/03_logical_units/03_LU_schema_window.md), click the right corner of **Table Population** and unselect **Disable Population**. The red **No Entry** icon next to the execution order is not displayed.

![image](images/07_13_02_screen2.png)

**Note**: At least one population must be enabled in the root table.

[Click for more information about Disable / Enable Populations](/articles/03_logical_units/13_disable_enable_populations_in_schema.md).

[![Previous](/articles/images/Previous.png)](12_table_population_diagram_outline.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](14_table_population_based_Broadway.md)
