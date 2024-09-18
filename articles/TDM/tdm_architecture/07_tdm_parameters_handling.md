# TDM Parameter Handling

The TDM enables the user to select entities based on [predefined parameters](/articles/TDM/tdm_gui/17_load_task_regular_mode.md#parameters) when creating a task. 

The list of available parameters is displayed on the task's BE (Business Entity). The list of parameters for each LU and the parameters' information - valid values of [combo parameters](/articles/TDM/tdm_gui/17_load_task_regular_mode.md#how-do-i-populate-a-parameters-value), number of values, parameter type, and minimum and maximum values for numeric parameters - are kept in the **tdm_params_distinct_values** TDM DB table.

A BE can have either a flat or a hierarchical structure, and each LU has its own parameters list and its own [LU parameters table](/articles/TDM/tdm_implementation/07_tdm_implementation_parameters_handling.md#tdm-parameter-tables) in the TDM DB. 

TDM 9.1 has added a new mode of parameter handling: **parameters coupling**. The **regular parameters' mode** supports **isolated business parameters** in a parameters search. However, per the newly added **parameters coupling** capability, multiple parameters can be taken into account and joined, for an optimized param search that leads to **intersection finding**. This is done based on the LU schema structure and the relations between the LU tables.

Examples of parameters coupling:

- Search for business customers that have a product X and the product was purchased in 2024. The purchase date must be linked to the product type.
- Search for customers that have a VIP billing subscriber and the total debt of the VIP subscriber does not exceed $100.

The parameters' mode is set in the [TDM_GENERAL_PARAMETER](https://github.com/k2view-academy/K2View-Academy/blob/Academy_8.1/articles/TDM/tdm_configuration/02_tdmdb_general_parameters.md) table in a parameter named **PARAMS_COUPLING**, and it impacts all the TDM Business Entities (BEs). The default setting of this parameter is false. Set this parameter to true in order to use the parameters coupling mode.

## TDM Parameter Handling - Regular Mode

The LU parameters table is created and populated in the TDM DB by the LUI sync. The naming convention of the parameters tables is `<LU Name>_params`.

The entity selection on a TDM task selects a subset of **root entities**, but the parameters for selection can be based on the child LU's parameters. Therefore, it is important to have the **linkage between the root entity and the children entities** when selecting entities based on parameters. **Previous TDM versions** created a **MATERIALIZED VIEW** in the TDM DB on each combination of **BE and source environment** to have the linkage between the root entity and the children entities. From TDM 8.1 onwards, each LU parameters table contains the following fields for connecting the entity id to its root entity:

- root_lu_name
- root_iid

**Example:**

- Customer BE has 4 LUs:
  - 2 **root LUs**: **Customer** and **Collection**.
  - **Customer** LU has 2 children LUs:
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

- Syncing Order IDs #279, #280, #281, #282, #283, #284, and #285 inserts the following records into **ORDER_PARAMS** TDM DB table:

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

- The following Select statement runs on the TDM DB to get the available entities:

     `SELECT ROOT_IID FROM crm_params WHERE source_environment = 'Production' AND root_lu_name = ANY('Customer', 'Collection') AND 0 < ANY("CRM.NUM_OF_OPEN_CASES"::numeric[] )   
  INTERSECT` 
  	   `SELECT ROOT_IID FROM billing_params WHERE source_environment = 'Production' AND root_lu_name = ANY('Customer', 'Collection') AND 'Gold' = ANY("BILLING.VIP_STATUS")  
  INTERSECT` 
  	   `SELECT ROOT_IID FROM order_params WHERE source_environment = 'Production' AND root_lu_name = ANY('Customer', 'Collection') AND 'New' = ANY("ORDER.ORDER_TYPE") ;`
  
  
  
  ## TDM Parameter Handling - Parameters Coupling Mode
  
  ### Export the LU tables into the TDM DB
  
  The parameters coupling mode builds the SQL query for each task based on the selected parameters and the links between the LU schema tables.
  
  The TDM extract task execution uses the MDB export Fabric command to export the LU tables to the TDM DB. **A dedicated schema is created for each LU**. The LU tables are created with PKs and FKs in the PosgtreSQL schema. The FKs are created based on parent-child links in the LU. The following LU tables are exported into the TDM DB:
  
  - FABRIC_TDM_ROOT - the entire table is exported.
  
  - TDM_BE_IIDS - the entire table is exported. This table contains the BE ID,  entity id (iid field) and the root entity id (root_iid field). It has been added for the parameters coupling mode and is need in order to connect different LU schema. The SQL query joins the TDM_BE_IIDS of the LU schemas based on the BE_ID and the ROOT_IID fields. This table has an accumulative population mode, i.e. if an LU exists in multiple BEs, it aggregates the current link of the root entity id to the previous syncs.
  
  - The LU tables in LuParamsMapping MTable - only the parameter fields and the linked fields to the parent or child LU tables are exported.
  
  - PK and FK fields are exported.
  
    - Example:
  
      - FABRIC_TDM_ROOT -> Customer -> Address
  
      - Customer is linked to FABRIC_TDM_ROOT via the customer_id.
  
      - Address is linked to Customer via the customer_id,
  
      - Address.state is mapped as a parameter.
  
      - All 3 tables are exported.
  
      - Customer table - the customer_id is exported.
  
      - Address table - the customer_id, address_id, and state fields are exported.
  
        
  
    ### Select the Entities Based on the Parameter
  
    The **analysisCount API** (called by the task window to calculate the number of matching entities) and the TDM execution process build the SQL query to run on the LU schemas in the TDM DB in order to select the task's entities.
  
    The generated SQL query for the parameters coupling is stored in the task_execution_list for tracking and analysis purpose. 
  
    #### Examples:
  
    - Select from Production environment customers that are based in TX with more than 1 contract (no_of_contract > 1). The following SQL is generated:
  
      ```sql
      SELECT entity_id FROM 
      (SELECT 'Production'||'_'||entity_id AS entity_id 
      	FROM (select distinct be1.root_iid as entity_id from Customer.tdm_be_iids be1 
      	INNER JOIN Customer.fabric_tdm_root root1 ON be1.be_id = '1' AND root1.__iid = be1.__iid 
                and root1.source_env = 'Production' 
      	INNER JOIN Customer.ADDRESS ON be1.__iid = Customer.ADDRESS.__iid 
      	INNER JOIN Customer.CUST_TOTALS ON be1.__iid = Customer.CUST_TOTALS.__iid 
      where ( Customer.address.state = 'TX' AND 1 < Customer.cust_totals.no_of_contracts )) AS ALIAS0 LIMIT 15) AS ALIAS1;
      ```
  
    - Select from Production environment customers with:
  
      -  Closed cases of Billing Issue type
      - The closed cases have a related activity with activity date > 2016-01-01
      - A VIP subscriber with more than one open invoice
  
      The following SQL is generated:   
  
      ```sql
        SELECT entity_id FROM (SELECT 'Production'||'_'||entity_id AS entity_id FROM (select distinct be1.root_iid as entity_id from Customer.tdm_be_iids be1 
        INNER JOIN Customer.fabric_tdm_root root1 ON be1.be_id = '1' AND root1.__iid = be1.__iid and root1.source_env = 'Production' 
        INNER JOIN Billing.tdm_be_iids be2 ON be1.be_id = be2.be_id and be1.root_iid = be2.root_iid 
        INNER JOIN Billing.fabric_tdm_root root2 ON root2.__iid = be2.__iid and root2.source_env = 'Production' 
        INNER JOIN Customer.ACTIVITY ON be1.__iid = Customer.ACTIVITY.__iid 
        INNER JOIN Customer.CASES ON be1.__iid = Customer.CASES.__iid 
        INNER JOIN Billing.SUB_TOTALS ON be2.__iid = Billing.SUB_TOTALS.__iid 
        INNER JOIN Billing.SUBSCRIBER ON be2.__iid = Billing.SUBSCRIBER.__iid 
        where Customer.ACTIVITY.activity_id=Customer.CASES.activity_id 
        AND Billing.SUBSCRIBER.subscriber_id=Billing.SUB_TOTALS.subscriber_id 
        AND ( Customer.cases.case_type = 'Billing Issue' 
        AND Customer.cases.status = 'Closed' 
        AND '2016-01-01' <= Customer.activity.activity_date 
        AND Billing.subscriber.vip_status IN ('Gold', 'Platinum') 
        AND 1 < Billing.sub_totals.no_of_open_inv )) AS ALIAS0 LIMIT 12) AS ALIAS1;
      ```
  
      
  
    

[![Previous](/articles/images/Previous.png)](06_tdmdb_cleanup_process.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](08_entity_reservation.md)

  



  

  
