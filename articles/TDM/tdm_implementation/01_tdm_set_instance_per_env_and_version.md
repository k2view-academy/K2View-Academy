# Set LU Instance Per Source Environment and Version

## Overview

The TDM enables users to create a [TDM task](/articles/TDM/tdm_overview/02_tdm_glossary.md#task) and select the source environment from which the entity is extracted. Tasks can also be created to extract and save different versions of the data in the selected entities.

**Examples**

- Customer 1 exists in both the Production and UAT environments where different data is saved in each environment. The TDM must create separate instances of Customer 1, one for Production and another for UAT.

- [Data Flux task](/articles/TDM/tdm_overview/02_tdm_glossary.md#data-flux): A task is created to save a version of Customer 1 in a testing environment and to run the task every couple of hours to backup the data. Each version of Customer 1 must create a separate instance.

  

## TDM - LUI Format

To create different LUIs per environment and version, each LUI created by the TDM must have the following format: 

### Regular Tasks

When a version indicator is not set, and a separate version of the entity is not saved, the LUI format is as follows: 

```
<Source Env><separator><entity id>
```

 **Example**

Copy Customer 1 from the PROD source env. The LUI is PROD_1.

#### Delete Only Load Tasks

To delete an entity only from a selected target environment, the target environment is concatenated to the LUI instead of the source environment.

### DataFlux Tasks

When the version indicator is set that is, to save a separate version of the entity, the LUI format is as follows: 

```
<Source Env><separator><entity id><separator><version name><separator><version datetime>
```

**Example**

To extract a specific version of Customer 1 from the PROD source env, the LUI is PROD_1_copyCust_20201105090000. 

### TDM Separator

Note that by default, the separator between the Source Env and the Entity ID (IID) is underscore and can be set another separator in the [TDM_GENERAL_PARAMETERS TDM DB](/articles/TDM/tdm_architecture/02_tdm_database.md#tdm_general_parameters) table. Populate the **param_name** using the **iid_separator** and the **param_value** with the separator's value.   



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_tdm_implementation_flow.md)
