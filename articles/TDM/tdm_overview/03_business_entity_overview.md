# Business Entity Overview

### What is a Business Entity? 

A Business Entity (BE) represents the main body of the selected data to be provisioned (copied) by the Test Data Management (TDM) platform. 
 
A Business Entity can have multiple Logical Units [LUs](( https://github.com/k2view-academy/K2View-Academy/blob/Academy_6.4_TDM_BCK/articles/TDM/tdm_overview/03_business_entity_overview.md)) with a flat or a hierarchical structure. Each LU can be attached to multiple BEs.

The ability to break a BE up into several LUs enables maximum flexibility and avoiding duplicate development. In addition, defining a hierarchical structure of LUs enables creating LUs based on the natural structure of each BE, instead of setting one unified structure which may not be appropriate for all BEs.

**Example**
- Customer's LU root entity = Customer ID.
- Ordering?s LU root entity = Order ID.
- Network Element?s LU root entity = Element ID.
- Device?s LU root entity = Device ID.
- Ordering, Network Element, and Device LUs are attached to two BEs:
  - Customer. 
  - Ordering.

![Multiple BEs for one LU](images/using_lu_in_multiple_BEs.png) 

Here, users can request to copy a list of selected Customer IDs. The TDM task also copies the related Ordering, Network Elements, and Device data of the selected Customers.
Alternatively, users can request to copy a list of selected Order IDs whereby the TDM task also copies the related Network Elements and Device data of the selected Orders.

BEs are defined using TDM and are saved in the [TDM Database](/articles/TDM/tdm_architecture/02_tdm_database.md).

### Building an LU Hierarchy in a BE

A parent-child hierarchy of LUs can also be defined under a BE. The relationship between the parent and child LUs is: **One - to - many**. Each parent LU can have many child LUs. 

If an LU in a BE has no parent LU, it is named **Root LU** whereby the root entity of this LU equals the root entity of the BE. 

There is only one definition of any given Logical Unit (LU), but multiple instances of the same logical unit can be used, and these are called (appropriately), Logical Unit Instances (LUIs). 

**Customer #1 Hierarchy Example**

 ![Customer example](images/customer_data_example.png)
                                

-  The Customer LU is the **Root LU** of the Customer BE.
-  Each LU has its own LUIs. 

The environment shown is called the ?PROD? environment, with each Logical Unit Instance (LUI) called a PROD instance, and indicated by a number (PROD_1, PROD_2, etc.)

When creating a TDM task to copy Customer 1 from this PROD environment, the TDM task must also copy the customer's related billing accounts and orders together with the related network elements and devices of the related orders. 

The following LUIs are extracted from the source and created in Fabric for Customer 1:


 <table width="900 pxl">
<tbody>
<tr>
<td valign="top" width="250 pxl">
<strong>LU Name</strong>
</td>
<td valign="top" width="250 pxl">
<strong>Parent LU Name</strong>
</td>
<td valign="top" width="400 pxl">
<strong>LU Instances</strong>
</td>
</tr>
<tr>
<td valign="top" width="250 pxl">
Customer
</td>
<td valign="top" width="250 pxl">
&nbsp;
</td>
<td valign="top" width="400 pxl">
PROD_1
</td>
</tr>
<tr>
<td valign="top" width="250 pxl">
Ordering
</td>
<td valign="top" width="250 pxl">
Customer
</td>
<td valign="top" width="400 pxl">
PROD_89, PROD_90, PROD_91, PROD_92
</td>
</tr>
<tr>
<td valign="top" width="250 pxl">
Billing
</td>
<td valign="top" width="250 pxl">
Customer
</td>
<td valign="top" width="400 pxl">
PROD_10, PROD_11, PROD_12
</td>
</tr>
<tr>
<td valign="top" width="250 pxl">
Network Elements
</td>
<td valign="top" width="250 pxl">
Ordering
</td>
<td valign="top" width="400 pxl">
PROD_600, PROD_601, PROD_602, PROD_507, PROD_889, PROD_563, PROD_432
</td>
</tr>
<tr>
<td valign="top" width="250 pxl">
Device
</td>
<td valign="top" width="250 pxl">Ordering</td>
<td valign="top" width="400 pxl">
PROD_1000, PROD_1001, PROD_1002, PROD_1003, PROD_1004, PROD_1005
</td>
</tr>
</tbody>
</table>



#### TDM Hierarchy With Several Root LUs 

A TDM BE may include several Root LUs with the same list of entities. For example, if the entity ID of both the Customer and Billing LUs is Customer ID, then the BE hierarchy may be:

![BE with several roots](images/be_hierarchy_with_several_root_lu.png) 

### TDM Task Execution of Hierarchical Business Entities

A TDM task can include a BE with a hierarchical structure of several LUs. The TDM task execution process must execute all the related LUs of the task from parent to child LUs as follows:

- Start with the execution of the root LUs on all entities of the task.

- After the execution of the parent LU ends, start the execution of the child LUs of the root LUs on all entities. Execute each child LU on the related entity IDs of the parent's entities that have been processed successfully by the task.

- Execute their child LUs etc..

  Note that if the parent LU execution fails, the child LU will not be executed and is marked as <i>failed</i>.

**Example:**

Goal: Create a TDM task to copy **Customers 1, 2 and 3** with their orders and related network elements.   

Customers' related entities:

- **Customer 1:** 
  - Order 4 : 
    - Network elements 90 and 91.
  - Order 5:
    - Network element 92.

- **Customer 2** :
  - Order 9: 
    - Network element 98.
- **Customer 3 :** 
  - Order 10:
    - Network element 99.
  - Order 11:
    - Network element 100.
  - Order 12:
    - This order has no related network element.

Task execution order:

- **Step 1:**
  - Run the Customer LU on all entities (related to Customers 1, 2 and 3). 
  - Entities of Customers 1 and 2 are processed successfully. Customer 3 fails.
- **Step 2:**
  - Run Order LU on entities related to Customers 1 and 2, i.e. order IDs 4, 5 and 9. The execution of Order 4 failed. The remaining Orders have been processed successfully. 
  - Note that the Order LU is not executed on the orders of Customer 3, since it failed.
- **Step 3:**
  - Run Network Element LU on the entities related to the successfully processed Orders, i.e. Network Element IDs 92 and 98.

  

 

 [![Previous](/articles/images/Previous.png)](02_tdm_glossary.md)
