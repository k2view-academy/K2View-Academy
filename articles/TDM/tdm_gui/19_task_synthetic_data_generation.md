# Synthetic Data Generation

K2view's TDM supports 2 methods of synthetic entities' generation:

- Rule-based generation
- AI-based generation

TDM enables, for each one of these methods, to either generate new entities or load pre-generated entities.

## Generating Rule-based Entities

I. Open the **Source** form and populate it as follows:

1. Select the **Rule based generation** option in the Source component.
2. Select the **Business Entity** for the data generation.
3. Select the **Generate new data** option.
4. Set the **Number of entities to generate**.
5. Optional - data generation parameters' setting.

   Click [here](14d_task_source_rule_based_generation.md) for more information on how to set the rule-based generation attributes in the Source component.

II. Optional - populate the [Target](17a_task_target_component_entities.md) form and select a testing environment to load the generated entities to the target environment. 

## Generating AI-based Entities

The AI-based data generation is based on pre-created **training models**. A creation and execution of at least one **AI training** task on a Business Entities (BE) is a **prerequisite** for a creation **AI-based data generation** for that BE.

### How to Create an AI Training Task? 

1. Open the **Source** form and select the [Entities & referential data](14b_task_source_component_entities.md) option. 

   Notes:

   - Click the **Advanced** setting to select only one LU for the task, since **AI-based training and generation tasks can run on one LU only**.
   - You can select each one of the **Policy for fetching data** options, that is, the task can either extract the entities from the source environment or get pre-extracted entities from the Test Data Store. 

2. Open the **Subset** form and define the [entity subset](15a_entity_subset.md) for the AI training process. For example, training the AI with customers based in NY.

3. Open the **Target** form and select the [AI training](17a_task_target_component_entities.md) option.

### How to Create an AI-based Generation Task 

I. Open the **Source** form and populate it as follows:

1. Select the **AI based generation** option in the Source component.
2. Select the **Business Entity** for the data generation. Click the **Advanced** setting to select only one LU for the task, since **AI-based training and generation tasks can run on one LU only**.
3. Select the **Generate new data** option.
4. Set the **Number of entities to generate** .
5. Select a **Data generator** (training model) for the AI-based generation.

   Click [here](14e_task_source_ai_based_generation.md) for more information on how to set the rule-based generation attributes in the Source component.

II. Optional - populate the [Target](17a_task_target_component_entities.md) form and select a testing environment in order to load the generated entities to the target environment. 



## Loading Pre-generated Entities

1. Open the **Source** form. Select either one of the following two options:

   - [Rule based generation](14d_task_source_rule_based_generation.md)
   - [AI-based generation](14e_task_source_ai_based_generation.md)

2. Select the **Use generated data in the Test data store** option in the **Source** form.

3. Define the [Subset](15a_entity_subset.md) of entities. The following options are available:

   - [Load all generated entities of a selected data generation execution](15a_entity_subset.md#synthetic-entities---load-all-generated-entities-of-a-selected-data-generation-execution)
   - Load a partial entity subset using either one of the following selection methods:
     - [Predefined custom logic](15a_entity_subset.md#predefined-custom-logic)
     - [Business parameters](15a_entity_subset.md#business-parameters)
     - [Random](15a_entity_subset.md#synthetic-entities---load-all-generated-entities-of-a-selected-data-generation-execution)

4. Populate the [Target](17a_task_target_component_entities.md) form and select a testing environment in order to load the generated entities to the target environment. 

   

   

    [![Previous](/articles/images/Previous.png)](18_task_provision_entities_from_source_env)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](20_task_provision_tables.md)

   

   

     
