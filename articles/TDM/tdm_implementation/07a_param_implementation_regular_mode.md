# TDM Parameters — Regular Mode — Implementation Guidelines

Prerequisite: Verify that the LU_PARAMS table has been attached to the LU Schema.

## Optional — Adding Parameters to Logical Units

1. Add the LU parameters to the **LuParams** MTable (located under the *References* section of the Project tree).

   Note that starting from TDM V8.1, the previous translation object, **trnLuParams**, has been replaced with the **LuParams** MTable. Deploy all LUs to the debug server and run the **RunTDMDBUpgradeScripts** flow. This flow executes the **convertLuTranslations** flow to convert old TDM translations into the corresponding TDM MTables. Each execution of the convertLuTranslations flow deletes the related MTables and repopulates them.

2. The **LuParams** has the following fields:

   - lu_name
   - column_name — populated with the parameter name. Note that the parameter name may differ from the corresponding LU table field name. 
   - sql
   - description — this optional field allows adding a short description to the business parameters. The description is displayed in the task when hovering over a parameter, which helps users select business parameters for entity sub-setting. 

3. **LuParams** example:

   <table width="900pxl">
   <tbody>
   <tr>
   <td width="150pxl"><strong>lu_name</strong></td>
   <td width="150pxl"><strong>column_name</strong></td>
   <td width="600pxl"><strong>sql</strong></td>
   <td width="600pxl"><strong>description</strong></td>    
   </tr>
   <tr>
   <td width="150pxl">CRM</td>
   <td width="150pxl">first_name</td>
   <td width="600pxl">Select first_name<br />&nbsp;From customer</td>
   <td width="600pxl">Customer's first name.<br />&nbsp;From customer</td>    
   </tr>
   <tr>
   <td width="150pxl">CRM</td>
   <td width="150pxl">last_name</td>
   <td width="600pxl">Select last_name<br />&nbsp;From customer</td>
   <td width="600pxl">Customer's last name.<br />&nbsp;From customer</td>        
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
   <td width="600pxl">Subscriber's debt balance.</td>    
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
   <td width="600pxl">Subscriber's VIP status.</td>
   </tr>
   <tr>
   <td width="150pxl">Billing</td>
   <td width="150pxl">subscriber_type</td>
   <td width="600pxl">Select Distinct subscriber_type From Billing.subscriber</td>
   </tr>
   </tbody>
   </table>

   

4. The LU_PARAMS' population flow runs the **fnEnrichmentLuParams** function. This function executes the LU's SQL queries in the **LuParams**, creates the `<LU>_params` table in the TDM DB if needed, and populates the `<LU>_params` table in the TDM DB. Each parameter's column holds a JSON file that contains the values of the parameter. Each parameter can hold several values that are separated by a comma. For example:

   - Line number = {"(722) 404-4222","+1 (372) 682-2450,"+1 (799) 979-1233","883-486-7523","1394031132"}



## TDM Parameter Tables

When [synced](/articles/14_sync_LU_instance/01_sync_LUI_overview.md), the LUIs create and update the `<LU>_params` table in the TDM database. A separate `<LU>_params` table is created for each LU. The naming convention of the parameter table is `<LU Name>_params`, e.g., customer_params. 

Moreover, the TDM task execution populates the [tdm_params_distinct_values](/articles/TDM/tdm_architecture/02_tdm_database.md#tdm_params_distinct_values) table in the TDM DB with the list of parameters and the valid values for [combo parameters](/articles/TDM/tdm_implementation/07_tdm_implementation_parameters_handling.md#optional---update-the-maximum-number-of-values-for-combo-parameters).

Parameter tables are used for:

- Retrieving the list of available parameters for each task.
- Checking the number of matching entities for the selected parameters of the task.
- Creating the entity list when the task's selection method is based on parameters.
- Creating the entity list when entities are randomly selected from the `<LU>_params` table of the task's root LU.  

### AI-based Generation

The AI-based generated entities are not 'synced' from a data source. The AI process generates entities, and TDM imports the generated entities to Fabric. A post TDM process updates the parameter tables for the imported entities to enable a selection of these entities based on parameters.

Click [here](/articles/TDM/tdm_gui/14e_task_source_ai_based_generation.md) for more information about the AI-based generation.



**Notes:**

- The LU_PARAMS' population runs the SQL queries to retrieve the LU tables' data. Therefore, it has an execution order 999 to run after the remaining LU tables' population. 
- Do not include spaces or special characters in parameter names.
- Even if parameters do not need to be defined for an LU, the LU_PARAMS table must be added to the LU Schema to create the `<LU Name>_params` table in the TDM DB. The `<LU Name>_params` table is needed by both entities selection methods of a TDM task: [Business parameters](/articles/TDM/tdm_gui/15a_entity_subset.md#business-parameters) and [Random Selection](/articles/TDM/tdm_gui/15a_entity_subset.md#random).
- The PARAMS_JSON field of the LU_PARAMS table contains the list of LU parameters and their values to enable the debugging of a given entity.
- Click [here](/articles/TDM/tdm_architecture/07_tdm_parameters_handling.md) for more information about parameters handling.
