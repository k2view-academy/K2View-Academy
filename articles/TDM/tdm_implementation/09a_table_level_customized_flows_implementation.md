# Table-Level Implementation - Customized Execution Flows

A customized Broadway flow can be added to a table's extract, load, delete processes or in-place masking processes. The implementor can apply it to all activities, or only to specific ones. Using this feature, you can access the following capabilities:

- Custom masking for selected fields (not Catalog-based).
- Extracting or loading large volumes of data that require using third-party tools, such as DB2move.
- Impacting the table execution order or table count.
- Custom logic for in-place masking.

## Customized Table Flows — Implementation Guidelines

The customized flows must be added under the Shared Objects in the Project tree.

### Extract Flow

- The extract flow receives a list of input parameters from the TDM execution processes and returns the number of records in the table and an object's array for the result. Duplicate the **GetSourceDataByQuery** flow (located in the TDM_TableLevel LU) in order to implement the extract template and customize the extract logic. 

#### Customized Masking Logic

The Catalog Masking Actor is invoked **after** the extract flow execution. 

Setting customized masking logic on tables:

- If you need to set customized logic on specific fields, edit the Catalog and remove the PII property from these fields in the Catalog as a way to prevent double-masking them.
- Sometimes, the customized masking logic is based on the Catalog masking output, e.g., building the masked email address based on the masked first and last names. If you need to call the Catalog Masking Actor in the extract flow, proceed as follows: 
  - Add the **CatalogMaskingMapper** Actor to the extract flow. 
  - Add customized Masking Actors to the extract flow to be invoked after the CatalogMaskingMapper Actor.
  - Set the **enable_masking** parameter to **false** at the end of the extract flow as a way to prevent double-masking of the table's record by the TDM execution processes.

#### Customized Extract Flow — Example

The image below depicts an example, which executes the following actions:

- Selecting records from the address table.
- Opening a loop on the extracted records.
- On each record - 
  - Masking the street, city, and zip code fields.
  - Merging the masked fields into the address record. 
  - Accumulating the merged record with the masked fields into an array. The accumulated array is the external **result** field of the flow.


![table extract](C:\Users\TaliEinhorn\OneDrive - K2View\Documents\K2View-Academy\articles\TDM\tdm_implementation\images\table_leve_custom_ext_flow.png)

See the loop on the selected address records:

![table extract](C:\Users\TaliEinhorn\OneDrive - K2View\Documents\K2View-Academy\articles\TDM\tdm_implementation\images\table_leve_custom_ext_flow2.png)

### Load Flow

- The load flow receives a list of input parameters from the TDM execution processes and returns the number of loaded records. Duplicate the **LoadTableByQuery** flow (located in the TDM_TableLevel LU) in order to implement the load template and customize the load logic.
- Note that if you use **Fabric V8.1.6 and above**, you must manually add the **__active_environment input parameter** to the DbCommand and/or DbLoad Actors; set this parameter as *Const* and populate it with any value (e.g., *target*). Adding the **__active_environment** input parameter enables refreshing the environment, updating it to be the target environment in the load flow, and running the load in the target environment. This parameter is already included in the duplicated **LoadTableByQuery** flow (DbLoad Actor). 

### Delete Flow

- The delete flow receives a list of input parameters from the TDM execution processes and deletes the table before the load. Duplicate the **DeleteTableByDBCommand** flow (located in the TDM_TableLevel LU)  to implement the delete template and customize the delete logic.

### In-place Masking Flow

- Starting with **TDM 9.5** onwards,  the TDM support in-place masking task that update the PII fields on the selected tables. The in-place masking flow  receives a list of input parameters from the TDM execution processes and update the table table: replaces the PII values with masking values. Duplicate the **UpdateTableByQuery**  flow (located in the TDM_TableLevel LU)  to implement the the customize the in-place masking flow.

### Customized Flows  — TableLevelDefinitions Fields

The following **TableLevelDefinitions** fields should be populated for each record:

- **interface_name** — the interface name defined in the TDM project implementation. 
- **schema_name** — the DB schema. Can be populated with either of the following options:
  - Schema name
  - From TDM V9.4 onwards, the schema name can also be populated with the Global name. Add a `@` sign before and after the Global name to indicate that the schema name should be taken from the Global's value. For example: `@CUSTOMER_SCHEMA_NAME@`. Using a Global to populate the schema is useful when different environments have different schema names. 

- **table_name** — populated with the table name. If this setting is empty, the customized flows will run on all the tables in the interface and schema.
- **count_indicator**— by **default** the count indicator is **true**, enabling counting the number of records in the source or target, as a way to monitor task execution. Set this indicator to **false**, if required, to disable counting records in the target.
- **record_count_flow** — populated with the name of a customized flow that counts the table records. 
- **table_order** — a numeric value or a flow name that defines the table’s execution priority. The table order specified in the TableLevelDefinitions MTable has the highest priority and can override the PK/FK relationships between task tables. If any table in a task has a table order defined in this MTable, then all tables in the task must have a table order defined as well. The TDM execution follows PK/FK relationships only when none of the task’s tables has a table order defined.
- **extract_flow** — populated with the customized extract flow name.
- **delete_flow** — populated with the customized delete flow name. 
- **load_flow** — populated with the load flow name.
- **inplace_masking_update_flow** —  populated with the in-place masking flow name.