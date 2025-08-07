# Set LU Instance per Source Environment and Version

## Overview

TDM allows users to create [tasks](/articles/TDM/tdm_overview/02_tdm_glossary.md#task) that extract entities from a selected source environment. In addition, it enables capturing and storing different versions of the same extracted entity’s data, allowing the maintenance and reuse of various snapshots when needed.

**Examples:**

- Customer 1 exists in both the Production and UAT environments, with different data stored in each. TDM must create separate instances of Customer 1 — one for Production and one for UAT.
- [Data Versioning task](/articles/TDM/tdm_overview/02_tdm_glossary.md#data-flux): A task is created to save a data version (snapshot) of a specific entity — e.g., Customer 1 — in a testing environment. The task can be scheduled to run periodically, such as every few hours, to back up the data. Each saved version of Customer 1 is stored as a separate Logical Unit Instance (LUI).

Additionally, TDM enables synthetic entity generation. Each generated entity must receive a unique LUI.

## TDM — LUI Format



### Default LUI

When an entity is extracted from the source environment, and the *Create data snapshot (version)* checkbox is checked — meaning a separate entity snapshot is not required for each task execution — the LUI format is as follows: 

```
<Source Env><separator><entity id>
```

 **Example:**

- Extract Customer 1 from the PROD source environment. The LUI will be PROD_1.



### Delete Only and Reserve Only Tasks

When a TDM task is set to perform either only a **Delete** (of an entity from the target environment) or only a **Reserve** (of an entity in the target environment) action, the **target environment is concatenated to the LUI**, since the source environment is not set in these task types.

 **Example:**

- Delete Customer 22 from UAT environment. The LUI will be UAT_22.

### Data Versioning Tasks

When the *Create data snapshot (version)* checkbox is checked in the task — intended to save a separate version of the entity with each task execution — the LUI format is as follows: 

```
<Source Env><separator><entity id><separator><task execution id>
```

**Example:**

Task execution IDs 11 and 15 extract and create a data snapshot of Customer 1 from the PROD source environment. The LUIs will be PROD_1_11 and PROD_1_15.  

### Synthetic Data Generation

#### Rule-based Generation

The LUI format of each generated entity is identical to the default format. The concatenated source environment is the dummy environment named Synthetic (the name is set in the SYNTHETIC_ENVIRONMENT Global), which is used for identifying rule-based generated entities. 

**Example:**

- Synthetic_12

#### AI-based Generation

The AI process generates entities with numeric IDs. The TDM process, which imports the generated entities into Fabric, creates a unique LUI for each generated entity using the following format:

```
<AI env name><separator><new seq value for the entity id><separator><task execution id>
```

**Example:**

- Task execution ID 102818 generates a new entity. The LUI will be AI_3_102818.

Notes:

- The dummy AI environment name is set in the AI_ENVIRONMENT Global.
- The mapping between the generated entity ID and the Fabric LUI is saved in the **tdm_ai_gen_iid_mapping** TDM DB table.

### TDM Separator

By default, **an underscore** is used as the separator between the Source Environment and the Entity ID (IID). A different separator can be configured in the [TDM_GENERAL_PARAMETERS TDM DB](/articles/TDM/tdm_architecture/02_tdm_database.md#tdm_general_parameters) table. Populate the **param_name** using the **iid_separator** and the **param_value** with the separator's value.   

Note that you must restart Fabric after adding the IID separator to the TDM_GENERAL_PARAMETERS table in order for the new separators to be stored in cache.



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_tdm_implementation_flow.md)
