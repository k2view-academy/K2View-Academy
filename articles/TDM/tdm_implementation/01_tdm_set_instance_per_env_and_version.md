# Set LU Instance Per Source Environment and Version

## Overview

The TDM product enables the user to create a [TDM task](/articles/TDM/tdm_overview/02_tdm_glossary.md#task) and select the required source environment to indicate from where the entity needs to be extracted. The user can also create a task to extract and save different version on the selected entities.

**Examples:**

- Customer 1 exists in both environments: Production and UAT. This customer has a different data in each environment. Therefore, the TDM must create separate instances of Customer 1: one instance of the Production customer and another instance of the UAT customer.

- [Data Flux task](/articles/TDM/tdm_overview/02_tdm_glossary.md#data-flux): The user creates a task to save  a version of Customer 1 on his testing environment and runs the task every couple of hours to backup his data. Each version of Customer 1 must create a separate instance.

  

## TDM - LUI Format

Each LUI created by the TDM must have the following format to support creating different LUIs per environment and version: 

### Regular Tasks

 When the version indicator is not set, i.e. the user does not require to save a separate version of the entity, the LUI format is as follows: 

```
<Source Env><separator><entity id>
```

 **Example:**

Copy Customer 1 from PROD source env. The LUI is PROD_1.

#### Delete Only Load Tasks

When the user only asks to delete an entity from a selected target environment, the target environment is concatenated to the LUI instead of the source environment.

### DataFlux Tasks

  When the version indicator set, i.e. the user requires to save a separate version of the entity, the LUI format is as follows: 

```
<Source Env><separator><entity id><separator><version name><separator><version datetime>
```

**Example:**

If the user asks to extact a specific version of Customer 1 from PROD source env, then the LUI is PROD_1_copyCust_20201105090000. 

### TDM Separator

 Note that by default, the separator between the Source Env and the entity id (IID) is underscore. However, it is possible to set a different separator in [TDM_GENERAL_PARAMETERS TDM DB](/articles/TDM/tdm_architecture/02_tdm_database.md#tdm_general_parameters) table:  populate the **param_name** by **iid_separator** and the **param_value** by the separator's value.   



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_business_entity_overview.md)