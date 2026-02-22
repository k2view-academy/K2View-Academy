# In-place Masking Customization  — Implementation Guidelines

## In-place Masking Flow

- Starting with **TDM 9.5** onwards,  the TDM support in-place masking task that update the PII fields on the selected tables. The in-place masking flow  receives a list of input parameters from the TDM execution processes and update the table table: replaces the PII values with masking values. Duplicate the **UpdateTableByQuery**  flow (located in the TDM_TableLevel LU)  to implement the the customize the in-place masking flow.

## In-place Masking - MTables

### TableLevelDefinitions

The following field has been added for in-place masking:

- **inplace_masking_update_flow** —  populated with the in-place masking flow name.

Click [here](09a_table_level_customized_flows_implementation.md) for more information about the TableLeveDefinitions fields.

### TableLevelInPlaceMasking

The **MTable** defines which table fields are used as **key fields** during table update operations.

Each record in the MTable represents a single table field. When a **composite key** is required, multiple MTable records must be defined—one for each table field that participates in the composite key.

