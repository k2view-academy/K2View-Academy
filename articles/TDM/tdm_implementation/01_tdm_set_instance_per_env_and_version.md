# Set LU Instance Per Source Environment and Version

## Overview

TDM enables users to create a [TDM task](/articles/TDM/tdm_overview/02_tdm_glossary.md#task) and select the source environment from which the entity is extracted. Tasks can also be created to extract and save different versions of the data in selected entities.

**Examples**

- Customer 1 exists in both the Production and UAT environments where different data is saved in each environment. TDM must create separate instances of Customer 1, one for Production and another for UAT.
- [Data Versioning task](/articles/TDM/tdm_overview/02_tdm_glossary.md#data-flux): A task is created to save a data version (snapshot) of Customer 1 in a testing environment and to run the task every couple of hours for backing up the data. Each version of Customer 1 must create a separate LU instance (LUI).

In addition, the TDM enables a synthetic entity generation. Each generated entity must get a unique LUI.

## TDM - LUI Format



### Default LUI

When an entity is extracted from the source environment, and the Create data snapshot checkbox is checked, i.e. there is no need to create a separate entity snapshot upon each task execution, the LUI format is as follows: 

```
<Source Env><separator><entity id>
```

 **Example**:

- Extract customer 1 from the PROD source environment. The LUI is PROD_1.



### Delete Only and Reserve Only Tasks

When the TDM task either **delete only** an entity from the target environment, or **reserve only** an entity in the target environment, the **target environment is concatenated to the LUI**, since the source environment is not set in these tasks.

 **Example**:

- Delete customer 22 from UAT environment. The LUI is UAT_22.

### Data Versioning Tasks

When the Create data snapshot checkbox is set in the task, that is, to save a separate version of the entity upon each task execution, the LUI format is as follows: 

```
<Source Env><separator><entity id><separator><task execution id>
```

**Example**

Task execution IDs 11 and 15 extract and create a data snapshot of customer 1 from the PROD source environment. The LUIs will be PROD_1_11 and  PROD_1_15.  

### Synthetic Data Generation

#### Rule-based Generation

The LUI format of each generated entity is identical to the default format. The concatenated source environment is the dummy environment named Synthetic (the name is set in the SYNTHETIC_ENVIRONMENT Global) that is used to identify rule-based generated entities. 

**Example:**

- Synthetic_12.

#### AI-based Generation

The AI process generates entities with numeric IDs. The TDM process which imports the generated entities to Fabric creates a unique LUI on each generated entity with the following format:

```
<AI env name><separator><new seq value for the entity id><separator><task execution id>
```

**Example:**

- Task execution id 102818 generates a new entity. The LUI is AI_3_102818 .

Notes:

- The dummy AI environment name is set in the AI_ENVIRONMENT Global.
- The mapping between the generated entity ID and the Fabric LUI is saved in the **tdm_ai_gen_iid_mapping** TDM DB table.

### TDM Separator

By default, the separator between the Source Environment and the Entity ID (IID) is an underscore. This can be set to a different separator in the [TDM_GENERAL_PARAMETERS TDM DB](/articles/TDM/tdm_architecture/02_tdm_database.md#tdm_general_parameters) table. Populate the **param_name** using the **iid_separator** and the **param_value** with the separator's value.   



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_tdm_implementation_flow.md)
