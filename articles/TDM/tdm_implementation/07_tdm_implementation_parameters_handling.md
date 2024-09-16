# TDM - Handling Parameters 


## TDM Task - Selecting Entities Based on Parameters

A TDM task enables you to select a subset of entities based on a predefined list of parameters. For example, copy 10 business customers in Billing Cycle 1 that are located in NY. The parameters that are available for the task are attached to the LUs of the task's [Business Entity](/articles/TDM/tdm_overview/03_business_entity_overview.md). Parameters are defined at an LU level.

TDM 9.1 has added a new mode of parameters' handling: **parameters coupling**. The **regular parameters' mode** supports **isolated business parameters** in a parameters search. However, per the newly added **parameters coupling** capability, multiple parameters can be taken into account and joined, for an optimized param search that leads to **intersection finding**. This is done based on the LU schema structure and the relations between the LU tables. 

Examples of parameters coupling:

- Search for business customers that have a product X and that the product was purchased in 2024. The purchase date must be linked to the product type.
- Search for customers that have a VIP billing subscriber and that the total debt of the VIP subscriber does not exceed $100. 

The parameters' mode is set in the [TDM_GENERAL_PARAMETER](/articles/TDM/tdm_configuration/02_tdmdb_general_parameters.md) table in a parameter named **PARAMS_COUPLING** and impacts all the TDM Business Entities (BEs). By default, this parameter is false. Set this parameter to true in order to use the parameters coupling mode. 

## Optional - Update the Maximum Number of Values for Combo Parameters

- Combo parameters are parameters with a limited number of possible values. The TDM portal displays a drop-down list of all possible values, enabling the user to select one of the values in the task. The maximum number of possible values for combo parameters is set in a shared Global named **COMBO_MAX_COUNT**.
- Edit the **COMBO_MAX_COUNT** shared Global imported from the TDM Library, if needed. By default, the Global is populated with 100 and is checked when creating a TDM task using a parameters selection method. Suppose the number of possible values in the [TDM Parameters tables](#tdm-parameters-tables) is less than or equals to the COMBO_MAX_COUNT value. In that case, the parameter is handled as a **combo** parameter, and a list of all possible values for this parameter is displayed. If a value is not selected from the list, the parameter has more values than the threshold defined in COMBO_MAX_COUNT Global and you must enter the value in the parameter.

- Note that if the **COMBO_MAX_COUNT** Global is updated after executing Extract tasks, it is required to repopulate the [tdm_params_distinct_values](/articles/TDM/tdm_architecture/02_tdm_database.md#tdm_params_distinct_values) TDM DB table:

 - Verify that the **COMBO_MAX_COUNT** Global is defined as Final.
 - Redeploy the TDM LU to Fabric.
 - Run the **UpgradeDistinctValues** flow (imported from the TDM Library). This flow truncates and repopulates the tdm_params_distinct_values table based on the updated COMBO_MAX_COUNT value.

## TDM Parameters - Implementation Guidelines

Each mode requires its own implementation. Read the implementation guidelines for each mode:

[Parameters Handling -  Regular Mode](07a_param_implementation_regular_mode.md)

[Parameters Handling - Params Coupling Mode](07b_param_implementation_param_coupling.md)





[![Previous](/articles/images/Previous.png)](06_tdm_implementation_support_hierarchy.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](08_tdm_implement_delete_of_entities.md)
