# Data Generation Implementation

The TDM data generation creates synthetic entities. The synthetic data is populated into the LU tables: an LU table can be populated with either source data or generated synthetic data.  

## Implementation of LU Populations 

The LU population must be based on a Broadway flow (instead of a DB Query or a root function) to support a synthetic data generation: the **sourceDbQuery** Actor was enhanced by Fabric 7.1 to support either population modes: a DB Select query from a data source or a synthetic population. The population mode is set based on the **ROWS_GENERATOR** key (session variable). If it is set to **true**, the sourceDbQuery runs the data generation inner flow to generate the synthetic records. The number of synthetic records per each parent key is set based on the **rowsGeneratorDistribution** input Actor.

### LU Population Flows - Implementation Steps

1. Verify that the LU tables' populations are based on a Broadway flow to support the synthetic data generation.  Note that you need to use the **populationRootTable.pop.flow** for the main source LU table. For other LU tables, generate the default population flow.

   
   Note that the **rowsGeneratorDistribution** input argument of the **sourceDbQuery** Actor is automatically generated as an external parameter with the following naming convention: 

   ```
   [lu name]_[lu table name]_number_of_records
   ```

   For example: crm_activity_number_of_records. 

   This enables the user to edit the number of generated records per LU table in the TDM task. 
   

2. Edit the default number of generated synthetic records: edit the default values of the **rowsGeneratorDistribution** input argument of the **sourceDbQuery** Actor. By default, it generates 1 record for the main LU table and 1-3 records for the remaining LU tables.

## Implementation of Data Generation Flows 

The **sourceDbQuery** Actor (automatically added to the LU population flow) runs the inner data generation flow if the **ROWS_GENERATOR** key (session variable) is **true**. The data generation inner flow must have the following naming convention:

```
${population name}.population.generator
```

For example: activity.population.generator

### Data Generation Flows - Implementation Steps

#### 1. Sequence handling 

Populate the **tdmSeqList** and **TDMSeqSrc2TrgMapping** tables before generating the data generation flows. 

Click [here](/articles/TDM/tdm_implementation/11_tdm_implementation_using_generic_flows.md#step-2---create-sequences) for more information about the sequence implementation.

This step is needed in order to add a sequence generation in the data generation flow for fields that are set in the **TDMSeqSrc2TrgMapping** table. The generated flow creates a DB sequence in the TDM DB for the generated ID. The created DB sequence has the following naming convention:

```
[gen]_[the sequence name in TDMSeqSrc2TrgMapping]
```



**Example:**

Customer, contract and address CRM LU's tables have the following sequence mapping:      

![seq mapping](images/tdmSeqSrc2TrgMapping_example_2.png)



The data generation flows of these tables create the gen_customer_id_seq, gen_address_id_seq, and gen_contract_id_seq DB sequences in the TDM DB and populate the customer_id, address_id, and contract_id fields based on the generated sequences.



#### 2. Generate the data generation flows for the LU table
- Deploy both the **LU**, for which you need to generate the data generation flows, and the **TDM LU** to Fabric debug server.
- Open the **createGenerateDataTableFlows** flow imported from the TDM library.
- Populate the **LU_NAME** and **OVERRIDE_EXISTING_FLOWS** input parameters. 
- Run the flow to create the data generation flows for the LU's tables except for the tables populated in the [TDMFilterOutTargetTables](/articles/TDM/tdm_implementation/11_tdm_implementation_using_generic_flows.md#step-1---define-tables-to-filter-out) whose **generator_filterout** is checked (true). The data generation flows are automatically created in the **GeneratorFlows** subdirectory under the Broadway directory of the LU.
- Note that you can also run the [createAllFromTemplates flow](11_tdm_implementation_using_generic_flows.md#step-3---create-load-and-delete-flows) to generate the data generation flows together with the target LU tables and the load and delete flows for the LU.

The data generation flows are created with the following logic:

- Parent keys are populated into the LU tables based on the parent-child LU schema definition. For example: the address LU table is linked to the customer LU table by the customer_id field. The customer_id generated for the customer LU table is sent to the address' population in the parent row and is mapped to address.customer_id.

- Sequence Actors are set for IDs fields mapped in **TDMSeqSrc2TrgMapping**.

- Other fields are populated with default data generation Actors based on the fields' data type. Note the the default data generation Actors are set in **GenerateDataDefaultFieldTypeActors** constTable (imported from the TDM library) in the Shared Objects. This table can be edited to change the default Actors mapped to the LU table fields by the createGenerateDataTableFlows flow.

-  The data generation flow returns multiple results that serve as the row columns. This means that the [rowsGenerator Actor](/articles/19_Broadway/actors/07a_data_generators_actors.md#rowsgenerator) handles the loop on parent rows as well as the number of table's records that each parent will get.

  

#### 3. Edit the data generation flow

 The following manual updates may be required on the data generation flows:

##### Data Generators

- Replacement of the default data generation Actors with other [data generators](/articles/19_Broadway/actors/07a_data_generators_actors.md) or custom inner flows.

##### PII Fields

- In general, **it is recommended to populate the PII fields in the data generation flow** and to avoid overriding them with the Masking Actors in the LU population flow. Such population enables exposing PII fields as [external business parameters](#external-business-parameters) for the data generation tasks.
- Verify that the [Masking Sensitive Data](/articles/TDM/tdm_gui/08_environment_window_general_information.md#masking-sensitive-data) checkbox is cleared for the **Synthetic** environment in the TDM Portal, in order to avoid the masking of PII fields in the LU population flows for data generation tasks. 
- TDM 8.1 added a new Actor: **GenerateConsistent**. This Actor inherits the **Masking** Actor, but it has its own **category** value: **generate_consistent**. Using this Actor in the data generation flow enables the following:
  - The TDM execution process sets the **generate_consistent** to **true** and the **enable_masking** to **false** on data generation tasks. This ensures that the data generation flow generates synthetic values on PII fields and the Masking Actors in the LU population do not override the generated synthetic values of the PII fields.
  - The new Actor does not require having an input value since there is no original value for newly generated synthetic entities. 

- PII fields can vary in their incidence and their need for referential integrity (consistency). Each scenario requires a different implementation approach.

  Example: the First Name and Last Name are located in both LUs - CRM and Billing. Each LU represents a different system. It is needed to keep the same combination of the first and last names in both LUs for a given customer.

  The following table describes the implementation recommendations for each scenario: 

<table width="900pxl">
<tbody>
<tr>
<td width="250pxl">
<p><strong>PII field incidence</strong></p>
</td>
<td width="150pxl">
<p><strong>Referential integrity</strong></p>
</td>
<td width="500pxl">
<p><strong>Implementation Recommendation</strong></p>
</td>
</tr>
<tr>
<td width="250pxl">
<p>The PII field exists only in one LU table</p>
</td>
<td width="150pxl">
<p>N/A</p>
</td>
<td width="500pxl">
<p>Use a data generator Actor to generate a random synthetic value</p>
</td>
</tr>
<tr>
<td width="250pxl">
<p>The PII field exists in multiple tables in the LU</p>
</td>
<td width="150pxl">
<p>N</p>
</td>
<td width="500pxl">
<p>Use a data generator Actor to generate a random synthetic value</p>
</td>
</tr>
<tr>
<td width="250pxl">
<p>The PII field exists in multiple tables in the LU</p>
</td>
<td width="150pxl">
<p>Y</p>
</td>
<td width="500pxl">
<p>2 alternatives:</p>
<p>1. Use a data generator Actor for one of the field's instances and then select the generated value and populate it into the remaining instances.</p>
<p>2. Use the new TDM 8.1 Actor: <strong>GenerateConsistent&nbsp;</strong>for all the field's instances to keep the referential integrity of the instances.</p>
</td>
</tr>
<tr>
<td width="250pxl">
<p>The PII field exists in multiple LUs</p>
</td>
<td width="150pxl">
<p>N</p>
</td>
<td width="500pxl">
<p>Use a data generator Actor to generate a random synthetic value</p>
</td>
</tr>
<tr>
<td width="250pxl">
<p>The PII field exists in multiple LUs</p>
</td>
<td width="150pxl">
<p>Y</p>
</td>
<td width="500pxl">
<p>Use the new TDM 8.1 Actor: <strong>GenerateConsistent&nbsp;</strong>for all the field's instances to keep the referential integrity of the instances.</p>
</td>
</tr>
</tbody>
</table>



​	Click [here](/articles/19_Broadway/actors/07_masking_and_sequence_actors.md) for more information about the masking Actors.

##### External Business Parameters

- Add external business parameters to the data generation flow to enable the user to set the values for these parameters in the TDM task's parameters, such as City, State. The editor of the parameter depends on the parameter type. Spaces and special characters, except for an underscore, must not be included in the External Name setting. 

  Click [here](15_tdm_integrating_the_tdm_portal_with_broadway_editors.md) for more information about the integration of the TDM portal with the Broadway editors and the implementation guides for MTable and Distribution parameters.

##### Handle the Loop over the Number of Records

There are several optional modes for the data generation inner flow:

- **Row by row** - the inner flow can return a single row and let the RowsGenerator Actor handle parent rows and number of rows per parent. The flow can return either multiple results that will serve as the row columns or a single result named **result** of a map type. 
- **Rows per parent** - if the inner flow returns a single result named **result** with a **collection of maps**, the Actor will collect them and move to the next parent row.
- **Handle all parent rows** - a flow can traverse the parent_rows and return a **collection of maps**. The Actor will return these rows and will not call the inner flow again.

The data generation flow returns multiple results that will serve as the row columns and it is executed in the **row by row** mode. You can edit the data generation flow to be executed in either the **rows per parent** or the **handle all parent rows** modes as explained above.

For example, generating 2-5 open cases and 1-6 close cases per activity requires using the 'rows per parent' mode.

Click [here](/articles/19_Broadway/actors/07a_data_generators_actors.md#rowsgenerator) for more information about the RowsGenerator Actor.





[![Previous](/articles/images/Previous.png)](15_tdm_integrating_the_tdm_portal_with_broadway_editors.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](tdm_fabric_implementation_environments_setup.md)
