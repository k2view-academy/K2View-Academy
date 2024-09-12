# TDM Parameters - Regular Mode - Implementation Guidelines

Prerequisite: verify that the LU_PARAMS is attached to the LU Schema.

## Optional - Adding Parameters for Logical Unit

1. Add the LU's parameters to the **LuParams** MTable (located under the References in the Project tree).

   Note that from TDM 8.1 onwards, the previous translation object - trnLuParams - is replaced with the **LuParams** MTable. Deploy all LUs to the debug server and run the **RunTDMDBUpgradeScripts** flow. This flow runs the **convertLuTranslations** flow to convert old TDM translations to the equivalent TDM MTables. Each execution of the convertLuTranslations flow deletes and re-populates the related MTables.

2. The LuParams has the following fields:

   - lu_name
   - column_name
   - sql

3. LuParams example:

   <table width="900pxl">
   <tbody>
   <tr>
   <td width="150pxl"><strong>lu_name</strong></td>
   <td width="150pxl"><strong>column_name</strong></td>
   <td width="600pxl"><strong>sql</strong></td>
   </tr>
   <tr>
   <td width="150pxl">CRM</td>
   <td width="150pxl">first_name</td>
   <td width="600pxl">Select first_name<br />&nbsp;From customer</td>
   </tr>
   <tr>
   <td width="150pxl">CRM</td>
   <td width="150pxl">last_name</td>
   <td width="600pxl">Select last_name<br />&nbsp;From customer</td>
   </tr>
   <tr>
   <td width="150pxl">CRM</td>
   <td width="150pxl">line_number</td>
   <td width="600pxl">Select contract.associated_line As line_number From contract</td>
   </tr>
   <tr>
   <td width="150pxl">CRM</td>
   <td width="150pxl">num_of_open_cases</td>
   <td width="600pxl">Select Count(*) As num_of_open_cases<br />From cases<br />Where Upper(cases.status) != 'CLOSED'</td>
   </tr>
   <tr>
   <td width="150pxl">CRM</td>
   <td width="150pxl">open_case_date</td>
   <td width="600pxl">Select case_date As open_case_date<br />From cases<br />Where Upper(cases.status) != 'CLOSED'</td>
   </tr>
   <tr>
   <td width="150pxl">CRM</td>
   <td width="150pxl">num_of_subscribers</td>
   <td width="600pxl">Select Count(*) As num_of_subscribers From contract</td>
   </tr>
   <tr>
   <td width="150pxl">CRM</td>
   <td width="150pxl">state</td>
   <td width="600pxl">Select state From address</td>
   </tr>
   <tr>
   <td width="150pxl">Billing</td>
   <td width="150pxl">total_balance_amount</td>
   <td width="600pxl">Select Sum(ifNull(Billing.balance.available_amount, 0)) As total_balance_amount<br />From Billing.balance</td>
   </tr>
   <tr>
   <td width="150pxl">Billing</td>
   <td width="150pxl">num_of_open_invoices</td>
   <td width="600pxl">Select Count(*) As num_of_open_invoices<br />From Billing.invoice<br />Where Upper(Billing.invoice.status) = 'OPEN'</td>
   </tr>
   <tr>
   <td width="150pxl">Billing</td>
   <td width="150pxl">total_payment_amount</td>
   <td width="600pxl">Select Sum(ifNull(Billing.payment.amount, 0)) As total_payment_amount<br />From Billing.payment</td>
   </tr>
   <tr>
   <td width="150pxl">Billing</td>
   <td width="150pxl">vip_status</td>
   <td width="600pxl">Select Distinct vip_status <br />From Billing.subscriber</td>
   </tr>
   <tr>
   <td width="150pxl">Billing</td>
   <td width="150pxl">subscriber_type</td>
   <td width="600pxl">Select Distinct subscriber_type From Billing.subscriber</td>
   </tr>
   <tr>
   <td width="150pxl">Asset</td>
   <td width="150pxl">transaction_duartion</td>
   <td width="600pxl">Select Distinct duration From Asset.asset_transaction</td>
   </tr>
   <tr>
   <td width="150pxl">Asset</td>
   <td width="150pxl">transaction_city</td>
   <td width="600pxl">Select Distinct transactioncity From Asset.asset_transaction</td>
   </tr>
   </tbody>
   </table>

   

4. The LU_PARAMS' population flow runs the **fnEnrichmentLuParams** function. This function runs the LU's SQL queries in the **LuParams**, creates the LU parameters table in the TDM DB if needed, and populates the LU parameters table in the TDM DB. Each parameter's column holds a JSON file that contains the values of the parameter. Each parameter can hold several values that are separated by a comma. For example:

   - Line number = {"(722) 404-4222","+1 (372) 682-2450,"+1 (799) 979-1233","883-486-7523","1394031132"}



## TDM Parameter Tables

When [synced](/articles/14_sync_LU_instance/01_sync_LUI_overview.md), the LUIs create and update the Parameters table in the TDM database. A separate parameters table is created for each LU. The naming convention of the parameters tables is `<LU Name>_params`. 

In addition, the TDM task execution process populates the [tdm_params_distinct_values](/articles/TDM/tdm_architecture/02_tdm_database.md#tdm_params_distinct_values) TDM DB table with the parameters list and the valid values for [combo parameters].

Parameter tables are used for the following:

- Getting the list of available parameters per task.
- Getting the number of matching entities for the selected parameters of the task.
- Creating the entity list for the task if the task's selection method is based on parameters.
- Creating the entity list for the task if a random selection of entities is used whereby the entities are randomly selected from the parameters table in the task's root LU.  

### AI-based Generation

The AI-based generated entities are not 'synced' from a data source. The AI process generates entities, and the TDM imports the generated entities to Fabric. A post TDM process updates the parameters tables for the imported entities to enable a selection of these entities based on parameters.

Click [here](/articles/TDM/tdm_gui/14e_task_source_ai_based_generation.md) for more information about the AI based generation.



**Notes:**

- The LU_PARAMS' population runs the SQL queries to retrieve the LU tables' data. Therefore, it has an execution order 999 to run after the remaining LU tables' population. 
- Do not include spaces or special characters in parameter names.
- Even if parameters do not need to be defined for an LU, the LU_PARAMS table must be added to the LU Schema to create the `<LU Name>_params` table in the TDM DB. The `<LU Name>_params` table is needed by both entities selection methods of a TDM task: [Parameters](/articles/TDM/tdm_gui/17_load_task_regular_mode.md#parameters) and [Random Selection](/articles/TDM/tdm_gui/17_load_task_regular_mode.md#random-selection).
- The PARAMS_JSON field of the LU_PARAMS table contains the list of LU parameters and their values to enable the debugging of a given entity.
- Click [here](/articles/TDM/tdm_architecture/07_tdm_parameters_handling.md) for more information about parameters handling.