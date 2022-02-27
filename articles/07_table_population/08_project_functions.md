# Project Functions

Fabric Project functions are user-defined Java functions that are added to the project implementation to perform complex data manipulations or to execute queries on a specific [Instance ID](/articles/01_fabric_overview/02_fabric_glossary.md#instance-id). Project functions can be either connected in a map (population flow), to other Fabric objects or invoked from another function using Java code.
Functions can be created in [Logical Units](/articles/03_logical_units/01_LU_overview.md), References, [Web Services](/articles/15_web_services_and_graphit/01_web_services_overview.md), [Shared Objects](/articles/04_fabric_studio/12_shared_objects.md) or from existing [Table Populations](/articles/07_table_population/01_table_population_overview.md). A function can be defined as a Shared Object and can be used in any object in a project. If a function is defined in an LU, Reference or Web Service, it is accessible only within that specific object.

### Project Function Types

#### **Regular Function**
*	A function that executes the business logic required within a project and can be invoked from any Fabric object like a [Table Population](/articles/07_table_population/01_table_population_overview.md), [Web Services](/articles/15_web_services_and_graphit/01_web_services_overview.md) or from another function. 
*	A Regular function has a specific category known as an [Enrichment function](/articles/10_enrichment_function/01_enrichment_function_overview.md) which is executed once all LU tables have been populated to extract data from any LU table and use it as needed. An Enrichment function must not have Input parameters and must not return any value.

[Click for Code Examples of an Enrichment  Function.](/articles/10_enrichment_function/04_enrichment_function_code_examples.md)

<studio>

#### **Root Function**  

*	A function that is used as a [Table Population Source Object](/articles/07_table_population/02_source_object_types.md).
*	The output of a Root function serves as input for the Table Population. This function cannot be used for data-mapping logic.
*	A Root function must have at least one Input parameter and at least one Output parameter. The Root function of a Root Table must have only one parameter.

[Click for Code Examples of a Root Function.](11_2_root_functions_code_examples.md)

</studio>

#### **LUDB Function** 
*	A function that is invoked from an SQL query to perform more complex logic operations on LU data than those performed using standard SQL statements.
*	An LUDB function is invoked from an SQL statement.
*	An LUDB function must have at least one Output value.

[Click for Code Examples of an LUDB Function.](11_3_creating_an_LUDB_function.md#example-of-an-ludb-function)

#### **Decision Function**  
*	A function that assesses whether a [sync](/articles/01_fabric_overview/02_fabric_glossary.md#sync) is performed on an LUI. 
*	A Decision function can be defined on [LU schema](/articles/03_logical_units/03_LU_schema_window.md), [LU table](/articles/06_LU_tables/01_LU_tables_overview.md) or [Table Population](/articles/07_table_population/01_table_population_overview.md) levels. 
*	A Decision function must have no Input parameters and must return a Boolean (True or False) parameter. An LUI sync is performed or not performed based on the returned result. 

[Click for Code Examples of a Decision Function.](/articles/14_sync_LU_instance/05_sync_decision_functions.md#decision-functions-for-lui-sync--example-use-cases)

#### **Trigger Function**

* A function that is triggered when an LU table's data is modified. 
* A trigger function is invoked from the **On Change** [LU table property](/articles/06_LU_tables/04_table_properties.md) that contains a list of Trigger functions on a Shared and LU level.

[Click for Code Examples of a Trigger Function.](11_4_creating_a_trigger_function.md#example-of-a-trigger-function)

#### Event Function

- A function that is triggered on one of the following events:
  - Post Sync success, post Sync failure, post Delete Instance success.
- An Event function is invoked from the **Events** [LU Schema property](/articles/03_logical_units/04_LU_properties.md) that contains a list of Event functions on a Shared and LU level.

[Click for Code Examples of an Event Function](11_5_creating_an_event_function.md).

<studio>

#### **Switch Function**

*	A specific Fabric Project function used by Parsers to split records to populate more than one record type in the Parser map.
*	A Switch function enables users to define different types of records for the same parser. 

#### **User Job**

* A specific Fabric function used by the Job’s mechanism. [Fabric Jobs](/articles/20_jobs_and_batch_services/01_fabric_jobs_overview.md) can only invoke functions of this type.

</studio>  



[![Previous](/articles/images/Previous.png)](07_fabric_built_in_functions.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](10_creating_a_project_function.md)
