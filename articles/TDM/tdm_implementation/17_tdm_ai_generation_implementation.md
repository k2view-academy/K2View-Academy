# AI-based Generation Implementation

TDM 9.0 adds integration with AI-based entities' generation (currently limited to a non-hierarchical BE). K2view's TDM supports 2 modes of synthetic entities' generation:

- Rule-based generation
- AI-based generation

The user, who creates the task, can select either one of these methods to generate synthetic entities by the task. The AI-based data generation supports only one LU (one schema).

The diagram below describes the TDM and AI integration:

![tdm-ai](images/tdm_ai_integration.png)

## Training Task

The training task creates the training models on the LU schema tables. This is a prerequisite for AI-based data generation since the generation is based on a selected training model. 

The following diagram describes the execution of the AI training task:

![ai training](images/ai_training_task_process.png)

## AI-based Generation Task

The AI-based data generation task generates synthetic entities based on a selected training model. The generated entities are imported to the Test Data Store (Fabric) and can be loaded to any target environment.

The following diagram describes the execution of the AI training task:

![ai training](images/ai_generation_task_process.png)

## Implementation Steps

### AI Globals

The following shared Globals have been added for the AI-based data generation:

- **AI_DB_INTERFACE** - the name of the AI DB interface. The default value is **AI_DB**. 
- **CREATE_AI_K2SYSTEM_DB** - this Global indicates whether the TDM deploy flow needs to create the AI k2system tables in case they do not exist. The default value is **false**. Set this Global to **true** in order to implement the AI-based data generation.
- **AI_ENVIRONMENT** - this is the name of the AI dummy environment. The default value is **AI**. 

###  AI Interfaces

- **AI_DB** - this Postgres interface must be active in order to enable the AI-based generation functionality. The TDM portal does not allow creating AI-based training or generation tasks if this interface is inactive. You can set the same connection details as the TDM DB if you wish to include the AI schemas in the TDM DB.
- **AI_Execution** - this interface must be active in order to enable the AI-based generation functionality. The TDM portal does not allow to create AI-based training or generation tasks if this interface is inactive. 
- **AI_DB_External** - this custom interface must be active in order to enable the AI-based generation functionality. This custom interface is utilized in order to securely allow Fabric to interact with the Kubernetes server [(K8s server)](/articles/98_maintenance_and_operational/Installations/TDM/TDM_AI_Installation_V9.0.md#admin-token). The AI_DB_EXTERNAL custom interface should have the same credentials as the AI_DB interface, and the Data field should be populated with your database name.
  ![ai training](images/External_DB.png)

Note that by default, the AI interfaces are disabled (inactive). 

  Click [here](/articles/24_non_DB_interfaces/07_custom_interface.md) for more information about Custom Interface.

  Click [here](/articles/98_maintenance_and_operational/Installations/TDM/TDM_AI_Installation_V9.0.md) for more information about the TDM with AI installation. 

### AI Environment
Add the AI environment to:
- [Fabric environments](tdm_fabric_implementation_environments_setup.md)
- [TDM portal](/articles/TDM/tdm_gui/10_environment_roles_tab.md#ai-environment---permission-set). Add the related [Systems](/articles/TDM/tdm_gui/11_environment_products_tab.md) to the AI environment in the TDM portal.

### AI MTables 

#### AISpecialAndCategoricalFields

- This is an optional table that enables to **override** the default classification of the **special parameters** or **categorical** fields in the AI training process:

  - **Special parameters** are **text** fields with **high cardinality** (above the default threshold set in training execution params). For these fields, the data generation generates values that do not come directly from the original data. **The generated values do not have to be real, just look realistic**. 

  - **Categorical** data is a type of data that is used for grouping information for values with a low cardinality. The synthetic data keeps the source values for these fields. An example for categorical data is **gender**.

    

- The **Special** and **Categorical** indicates for which field type you wish to override the default behavior: special parameters or categorical field. One of these fields must be **true** for each record.

- The **Indicator** field indicates how to override the default behavior: 

​	**Examples**:

- Do not define a city as a special param as the data generation process has to generate real values for a city.

- Force the AI to treat the case_note field as a special param and generate dummy "realistic like" value for this field. 

- The MTable will be populated as follows:

  ![special params](images/ai_generation_special_params_example.png)

##### Note:
  - **Primary and foreign keys** columns, as well as columns that are not string type, **cannot be overridden and populated** in this table.



#### AITableFieldsInclusion

- This is an optional table that enables including or excluding tables or fields from being exported into the PG DB and as a result, including or excluding them from the AI training process. See example:

  ![special params](images/ai_tables_inclusion_example.png)



### K2system Tables 

- Creation of the K2system tables:
     - This shall be done by the TDM deploy flow if the CREATE_AI_K2SYSTEM_DB global is set to true.
     - These created tables are populated by the TDM AI Task and the AI Job:
       
              - Task_executions: This table holds all the task executions for all the task types.
              - Task_execution_stats: A table that should be updated during the job execution. Will be holding any informative statistics/metrics that may be useful for a later analysis.
              - Entity_list: A table with all the entities relevant to an existing training/generation job.
     

![k2system_tables](images/K2system_Tables.png)
### Overriding Generated Values

- In some cases, it may be required to fix or override some of the AI-based generated values. It can be implemented either by defining a post-execution flow that gets the generated entities and updates them, or by adding an override logic to the load flows to update the values before they are loaded to the target environment.

### LU Implementation
Verify that all LU source tables have a PK. The PK is required for the AI-based training and generation tasks.

#### LU Schema Update
If the LU schema is updated, the next training task execution will drop and recreate the schema tables for the updated LU.

### Cleanup Process 
The cleanup process of both the AI execution server and the AI DB is manual, and it runs a dedicated flow. Click [here](/articles/98_maintenance_and_operational/Installations/Docker/TDM/TDM_AI_Installation_V9.0.md#manual-cleanup-flow) for more information about the AI cleanup process.

[![Previous](/articles/images/Previous.png)](16_tdm_data_generation_implementation.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](tdm_fabric_implementation_environments_setup.md)
