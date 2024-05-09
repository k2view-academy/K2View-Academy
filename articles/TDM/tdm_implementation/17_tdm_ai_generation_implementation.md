# AI Implementation

TDM 9.0 adds integration with AI-based entities' generation. K2view TDM supports 2 modes of synthetic entities' generation:

- Rule-based generation
- AI-based generation

The user who creates the task can select either one of these methods to generate synthetic entities by the task. 

The diagram below describes the TDM - AI integration:

![tdm-ai](images/tdm_ai_integration.png)

## Training Task

The training task creates the training models on the LU schema tables. This is a pre-requisite for AI-based data generation since the generation is based on a selected training model. 

The following diagram describes the execution of AI training task:

![ai training](images/ai_training_task_process.png)

## AI-based Generation Task

The AI-based data generation task generates synthetic entities based on a selected training models. The generated entities are imported to the Test data store (Fabric) and can be loaded to any target environment.

The following diagram describes the execution of AI training task:

![ai training](images/ai_generation_task_process.png)

## AI Implementation

### AI Globals

The following shared Globals have been added for the AI-based data generation:

- **AI_K2SYSTEM_INTERFACE**  - the name of the AI k2system interface. Default value is **AI_DB**. 
- **AI_ENTITIES_INTERFACE**  - the name of the AI LU schemas interface.  Default value is **AI_DB**. 
- **CREATE_AI_K2SYSTEM_DB** - this Global indicates if the TDM deploy flow needs to create the AI k2system  tables if not exist. The default value is **false**. Set this Global to **true** in order to implement the AI-based data generation.
- **AI_ENVIRONMENT**  -  this is the name of the AI dummy environment. The default value is  **AI**. 

###  AI Interfaces

- **AI_DB** - this interfaces must be active in order to enable the AI-based generation functionality. The TDM portal does not allow to create AI-based training or generation tasks if this interface is inactive. You can set same connection details as the TDM DB if you wish to include the AI schemas in the TDM DB.

### AI MTables 

The TDM 9 has 2 optional configuration tables for the AI-based training and generation processes:

#### TrainingMappingTables.csv

A list of LU tables and fields that are logically related to each other. For example:

- Contract.contract_ref_id and Contract.contract_description fields.

#### TrainingSpecialFields.csv

The data generation process identifies text fields with high cardinality as **special fields**. For these fields, the data generation generates values that do not come directly from the original data. **The generated values do not need to be real, just look realistic**. In some cases the **definition** of a field as a special param needs to be **overridden**. 

For example, do not define a city as a special param, since the data generation process needs to generate real values for a city:

![special params](images/ai_generation_special_params_example.png)



### Overriding Generated Values

- It is possible to define a post execution flow that gets the generated entities and updates them if needed.
