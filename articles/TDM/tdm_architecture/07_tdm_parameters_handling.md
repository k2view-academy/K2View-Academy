# TDM Parameters Handling

The TDM enables the user to select entities based on [predefined parameters](/articles/TDM/tdm_gui/17_load_task_regular_mode.md#parameters) when creating a load task. It is also possible to [configure the TDM](/articles/TDM/tdm_configuration/02_tdmdb_general_parameters.md#tdm-portal-general-parameters) to enable the Parameters selection method for a [reserve task](/articles/TDM/tdm_gui/20_reserve_only_task.md#parameters). 

The list of available parameters is displayed on the task's BE (Business Entity). The list of parameters for each LU and the parameters' information -  the valid values of [combo parameters](/articles/TDM/tdm_gui/17_load_task_regular_mode.md#how-do-i-populate-a-parameters-value), number of values, parameter type, and minimum and maximum values for numeric parameters - are kept in the **tdm_params_distinct_values** TDM DB table.

A BE can have either a flat or a hierarchical structure and each LU has its own parameters list and its own [LU parameters table](/articles/TDM/tdm_implementation/07_tdm_implementation_parameters_handling.md#tdm-parameter-tables) in the TDM DB. The LU parameters table is created and populated in the TDM DB by the LUI sync. The naming convention of the parameters tables is `<LU Name>_params`.

The entity selection on a TDM task selects a subset of **root entities**, but the parameters for selection can be based on the child LU's parameters. Therefore, it is important to have the **linkage between the root entity and the children entities** when selecting entities based on parameters. **Previous TDM versions** created a **MATERIALIZED VIEW** in the TDM DB on each combination of **BE and source environment** to have the linkage between the root entity and the children entities. From TDM 8.1 onwards, each LU parameters table contains the following fields for connecting the entity id to its root entity:

- root_lu_name
- root_iid

**Example:**

- Customer BE has four LUs:
  - Two **root LUs**: **Customer** and **Collection**.
  - **Customer** LU has two children LUs:
    - **Billing**
    - **Order**

- Customer #65 has the following children IDs in the Production environment:

  - **Billing LU**: #169, #170, #171, #172, and #173.
  - **Order LU**: #279, #280, #281, #282, #283, #284, and #285.

- Syncing Customer ID #1 inserts the following record into **CUSTOMER_PARAMS** TDM DB table:

  <table width="900pxl">
  <tbody>
  <tr>
  <td width="100pxl"><strong>root_lu_name</strong></td>
  <td width="100pxl"><strong>root_iid</strong></td>    
  <td width="100pxl"><strong>entity_id</strong></td>
  <td width="120pxl"><strong>source_environment</strong></td>
  <td width="120px"><strong>CUSTOMER.FIRST_NAME</strong></td>
  <td width="120px"><strong>CUSTOMER.LAST_NAME</strong></td>
  <td width="120px"><strong>CUSTOMER.LINE_NUMBER</strong></td>
  <td width="120px"><strong>CUSTOMER.NO_OF_OPEN_CASES</strong></td>
  <td width="120px"><strong>CUSTOMER.OPEN_CASE_DATE</strong></td>
  <td width="120px"><strong>CUSTOMER.NO_OF_SUBSCRIBERS</strong></td>
  </tr>
  <tr>
  <td width="100pxl">Customer</td>
  <td width="100pxl">65</td>    
  <td width="100pxl">65</td>
  <td width="120px">Production</td>
  <td width="120px">{"Maisie"}</td>
  <td width="120px">{"Berger"}</td>
  <td width="120px">{"719 764 1363","404 376 5891","(248) 143-7235","342-203-6253","+1 (929) 454-2178"}</td>
  <td width="120px">{"3"}</td>
  <td width="120px">{"2015-09-16 06:14:40","2016-01-13 04:27:36","2017-02-10 20:44:54"}</td>
  <td width="120px">{"5"}</td>
  </tr>
  </tbody>
  </table>
  
  
- Syncing Collection ID #1 inserts the following records into **COLLECTION_PARAMS** TDM DB table:

  <table width="900pxl">
  <tbody>
  <tr>
  <td width="100pxl"><strong>root_lu_name</strong></td>
  <td width="100pxl"><strong>root_iid</strong></td>      
  <td width="100pxl"><strong>entity_id</strong></td>
  <td width="150pxl"><strong>source_environment</strong></td>
  <td width="600pxl"><strong>COLLECTION.COLLECTION_STATUS</strong></td>
  </tr>
  <tr>
  <td width="100pxl">Collection</td>
  <td width="100pxl">65</td>    
  <td width="100pxl">65</td>
  <td width="150pxl">Production</td>
  <td width="600pxl">{"5","4","3","1","2"}</td>
  </tr>
  </tbody>
  </table>
  
  
- Syncing Billing IDs #169, #170, #171, #172, and #173 inserts the following records into **BILLING_PARAMS** TDM DB table:

  <table width="900pxl">
  <tbody>
  <tr>
  <td width="100pxl"><strong>root_lu_name</strong></td>
  <td width="100pxl"><strong>root_iid</strong></td>       
  <td width="100pxl"><strong>entity_id</strong></td>
  <td width="100pxl"><strong>source_environment</strong></td>
       <td width="125pxl"><strong>BILLING.NO_OF_OPEN_INVOICES</strong></td>
      <td width="125pxl"><strong>BILLING.VIP_STATUS</strong></td>
      <td width="125pxl"><strong>BILLING.TOTAL_PAYMENT_AMOUNT</strong></td>
      <td width="125pxl"><strong>BILLING.SUBSCRIBER_TYPE</strong></td>
  </tr>
  <tr>
  <td width="100pxl">Customer</td>
  <td width="100pxl">65</td>      
  <td width="100pxl">169</td>
  <td width="100pxl">Production</td>
   <td width="125pxl">{"2"}</td>
  <td width="125pxl">{"Gold"}</td>
  <td width="125pxl">{"3789"}</td>
  <td width="125pxl">{"2"}</td>
  </tr>
  <tr>
  <td width="100pxl">Customer</td>
  <td width="100pxl">65</td>      
  <td width="100pxl">170</td>
  <td width="100pxl">Production</td>
  <td width="125pxl">{"2"}</td>
  <td width="125pxl">{"Silver"}</td>
  <td width="125pxl">{"824"}</td>
  <td width="125pxl">{"1"}</td>
  </tr>
  <tr>
  <td width="100pxl">Customer</td>
  <td width="100pxl">65</td>      
  <td width="100pxl">171</td>
  <td width="100pxl">Production</td>
   <td width="125pxl">{"0"}</td>
  <td width="125pxl">{"Gold"}</td>
  <td width="125pxl">&nbsp;</td>
  <td width="125pxl">{"4"}</td>
  </tr>
  <tr>
  <td width="100pxl">Customer</td>
  <td width="100pxl">65</td>      
  <td width="100pxl">172</td>
  <td width="100pxl">Production</td>
  <td width="125pxl">{"0"}</td>
  <td width="125pxl">{"Gold"}</td>
  <td width="125pxl">&nbsp;</td>
  <td width="125pxl">{"3"}</td>
  </tr>
  <tr>
  <td width="100pxl">Customer</td>
  <td width="100pxl">65</td>      
  <td width="100pxl">173</td>
  <td width="100pxl">Production</td>
  <td width="125pxl">{"1"}</td>
  <td width="125pxl">{"Platinum"}</td>
  <td width="125pxl">{"1898"}</td>
  <td width="125pxl">{"4"}</td>
  </tr>
  </tbody>
  </table>

- Syncing Order IDs #279, #280, #281, #282, and #283. #284, and #285 inserts the following records into **ORDER_PARAMS** TDM DB table:

  <table width="900pxl">
  <tr>
  <td width="100pxl"><strong>root_lu_name</strong></td>
  <td width="100pxl"><strong>root_iid</strong></td>     
  <td width="100pxl"><strong>entity_id</strong></td>
  <td width="150pxl"><strong>source_environment</strong></td>
  <td width="150pxl"><strong>ORDERS.ORDER_TYPE</strong></td>
  <td width="150pxl"><strong>ORDERS.ORDER_STATUS</strong></td>
  </tr>
  <tr>
  <td width="100pxl">Customer</td>
  <td width="100pxl">65</td>    
  <td width="225pxl">279</td>
  <td width="225pxl">Production</td>
  <td width="225pxl">{"Device"}</td>
  <td width="225pxl">{"New"}</td>
  </tr>
  <tr>
  <td width="100pxl">Customer</td>
  <td width="100pxl">65</td>    
  <td width="225pxl">280</td>
  <td width="225pxl">Production</td>
  <td width="225pxl">{"Network"}</td>
  <td width="225pxl">{"Closed"}</td>
  </tr>
  <tr>
  <td width="100pxl">Customer</td>
  <td width="100pxl">65</td>    
  <td width="225pxl">281</td>
  <td width="225pxl">Production</td>
  <td width="225pxl">{"Billing"}</td>
  <td width="225pxl">{"Closed"}</td>
  </tr>
  <tr>
  <td width="100pxl">Customer</td>
  <td width="100pxl">65</td>    
  <td width="225pxl">282</td>
  <td width="225pxl">Production</td>
  <td width="225pxl">{"Billing"}</td>
  <td width="225pxl">{"New"}</td>
  </tr>
  <tr>
  <td width="100pxl">Customer</td>
  <td width="100pxl">65</td>    
  <td width="225pxl">283</td>
  <td width="225pxl">Production</td>
  <td width="225pxl">{"Network"}</td>
  <td width="225pxl">{"Closed"}</td>
  </tr>
  <tr>
  <td width="100pxl">Customer</td>
  <td width="100pxl">65</td>    
  <td width="225pxl">284</td>
  <td width="225pxl">Production</td>
  <td width="225pxl">{"Billing"}</td>
  <td width="225pxl">{"In Progress"}</td>
  </tr>
  <tr>
  <td width="100pxl">Customer</td>
  <td width="100pxl">65</td>    
  <td width="225pxl">285</td>
  <td width="225pxl">Production</td>
  <td width="225pxl">{"Billing"}</td>
  <td width="225pxl">{"Closed"}</td>
  </tr>
  </table>

- The tester selects entities based on the following parameters:

  - NO_OF_OPEN_CASES > 0  AND VIP_STATUS = "Gold" AND ORDER_TYPE = "New"

- The following SELECT statement runs on the TDM DB to get the available entities:

     `SELECT ROOT_IID FROM crm_params WHERE source_environment = 'Production' AND root_lu_name = ANY('Customer', 'Collection') AND 0 < ANY("CRM.NUM_OF_OPEN_CASES"::numeric[] )   
  INTERSECT` 
  	   `SELECT ROOT_IID FROM billing_params WHERE source_environment = 'Production' AND root_lu_name = ANY('Customer', 'Collection') AND 'Gold' = ANY("BILLING.VIP_STATUS")  
  INTERSECT` 
  	   `SELECT ROOT_IID FROM order_params WHERE source_environment = 'Production' AND root_lu_name = ANY('Customer', 'Collection') AND 'New' = ANY("ORDER.ORDER_TYPE") ;`
  
  
  
  
  

[![Previous](/articles/images/Previous.png)](06_tdmdb_cleanup_process.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](08_entity_reservation.md)

  



  

  
