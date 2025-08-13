# Masking Sensitive Data

TDM systems often handle sensitive data. To comply with data privacy laws and regulations, Fabric can [mask sensitive fields](/articles/41_masking/01_fabric_data_masking.md) — such as SSN, credit card numbers, and email addresses — before they are loaded either to Fabric or into the target database.

The TDM infrastructure controls whether masking is enabled or disabled based on the settings of global variables.

 <web>

## Integrating Catalog Masking 

- [Fabric's Discovery and Catalog solution](/articles/39_fabric_catalog/01_catalog_overview.md) provides an insight into the Fabric interfaces. The Fabric Catalog supports a [Catalog-based masking](/articles/39_fabric_catalog/catalog_app/11_catalog_masking.md) of PII fields. 

- From TDM V8.1 onwards, the TDM templates add the **CatalogMaskingMapper** Actor to LU population flows in order to integrate with Catalog masking. This Actor applies Catalog-based masking to detected PII fields before loading them into the LU table. 

</web> 

## TDM Processes that Mask Sensitive Data

* **LU population flows** — the TDM templates add the **CatalogMaskingMapper** Actor to LU population flows to apply Catalog-based masking on detected PII fields before loading them into the LU table. It is possible to edit the population flows to override the Catalog’s masking for certain PII fields. To do this, add [masking Actors](/articles/19_Broadway/actors/07_masking_and_sequence_actors.md) after the **CatalogMaskingMapper** Actor and link them to the relevant fields in the **DbLoad** Actor.
  * If the masked field is used as an [input argument](/articles/03_logical_units/12_LU_hierarchy_and_linking_table_population.md) linked to another LU table, add the masking population — which masks the fields in all LU tables — to the last executed LU table. This ensures the original value is available when populating the LU tables. 

* **Load flows** — the TDM templates adds the **HandleMaskAndSeqFields** flow to the load flows. The  **HandleMaskAndSeqFields** flow is invoked for each record to mask its PII fields and replace its sequences if needed. The masking is done using the **CatalogMaskingRecord** Actor.
* **Table-level flows** — the TDM table-level extract flow uses the **CatalogMaskingMapper** Actor to mask the sensitive data.

## Overriding the Catalog Masking 

- Overriding the Catalog masking may be required when there is a dependency between the PII fields. The Catalog masking handles each field separately.

- If you need to have a dependency between PII fields, you can override the Catalog masking for these fields. 

- Examples:

  - Address — the masked  city, street name, and zip code values must be related.

  - Email — the masked email contains the masked first and last name values. 

- **LU populations**: add the [Masking Actors](/articles/19_Broadway/actors/07_masking_and_sequence_actors.md) **after** the **CatalogMaskingMapper** Actor and link them to the relevant fields in the **DbLoad** Actor in order to override the Catalog masking.
- **Load flows**: add the [Masking Actors](/articles/19_Broadway/actors/07_masking_and_sequence_actors.md) **after** the **HandleMaskAndSeqFields** flow and link them to the relevant fields in the **DbLoad** Actor in order to override the Catalog masking.
- If you need to send the original (source) values for the Masking Actors in the LU population or load flows, move the Query result to an **ArrayBuilder** Actor and connect the **ArrayBuilder** output to the **CatalogMaskingMapper** Actor (for LU population flow), or to the **HandleMaskAndSeqFields** flow (for load flow), instead of connecting the Query result to it. This is needed in order to invoke the Query output twice – sending it to the CatalogMaskingMapper/HandleMaskAndSeqFields and to the Masking Actor.
- If your flows mask a PII field using the Masking actors (overriding the Catalog masking),  it is recommended to remove the PII classification in the Catalog for this field to prevent unnecessary double masking of this field. 

## TDM - Masking Categories

One of the masking Actors' input parameters is named **category**. This parameter indicates *when* the masking Actor needs to generate a new value, e.g., when masking sensitive data or replacing the ID (sequence). The following values can be set in the category:

- **enable_sequences**, which generates a new ID value
- **enable_masking**, which masks sensitive data
- Any custom string value

A new custom value has been added by TDM 8.1:  **enable_masking_uniqueness**. This category is set to true if the **enable_sequences** or the **enable_masking** categories are set to true by the TDM task execution process.

By default, the category is set to **enable_masking** on all masking Actors except for the **MaskingSequence** Actor, in which case the default category is set to **enable_sequences**. The main use of the  **enable_masking_uniqueness** category is for PII fields that must have unique values, such as SSN. For these fields, it is recommended to set the **category** of the masking Actor to **enable_masking_uniqueness**.

### Setting the Mask Categories by the TDM Task Execution Processes

The TDM execution processes sets the masking categories to true/false based on the TDM task execution settings:



<table width="900pxl">
<tbody>
<tr>
<td width="250pxl">
<p><strong>Category</strong></p>
</td>
<td width="300pxl">
<p><strong>LU Population (extract part)</strong></p>
</td>
<td width="350pxl">
<p><strong>Load Process</strong></p>
</td>
</tr>
<tr>
<td width="250pxl">
<p>enable_masking</p>
</td>
<td width="300pxl">
 <p>This attribute is set based on the  <a ref="/articles/TDM/tdm_gui/08_environment_window_general_information.md#mask-sensitive-data">source environment's setting</a>. 
</td>
<td width="350pxl">
<p>Will be set to true for the following tasks:</p>    
<ul>
<li>The task's selection method = Entity clone</li>
</ul>
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td width="250pxl">
<p>enable_sequences</p>
</td>
<td width="300pxl">
<p>N/A</p>
</td>
<td width="350pxl">
<p>Will be set to true for the following tasks:</p>    
<ul>
<li>The task's selection method = Entity clone.</li>
<li>The task replaces the entities' sequences (IDs).</li>
<li>Load synthetically generated entities (the source environment is Synthetic).</li>
</ul>
</td>
</tr>
<tr>
<td width="250pxl">
<p>enable_masking_uniqueness</p>
</td>
<td width="300pxl">
   Set to true if the enable_masking or enable_sequences are true.</p>
</td>
<td width="350pxl">
<p>Will be set to true for the following tasks:</p>    
<ul>
<li>The task's selection method = Entity clone.</li>
<li>The task replaces the entities' sequences (IDs).</li>
<li>Load synthetically generated entities (the source environment is Synthetic).</li>
</ul>
</td>
</tr>
</tbody>
</table>



* Notes:

  * From TDM 7.3 onwards, the task that clones an entity creates only **one LUI instance for all clones**. Therefore, you must add masking on both processes (LUI Sync and load flows) in order to get different data in the masked fields on each clone. The clone_id is included in the [masking caching key](/articles/26_fabric_security/06_data_masking.md#masking-flow).

  * TDM 8.0 added the **root_iid** to the caching key in order to maintain the **referential integrity on PII fields across different LUs of the task’s BE**.

    For example, CRM and Billing LUs keep the Customer's data. The customer name needs to be identical in both LUs for a given customer. Setting the root_iid with the customer ID enables keeping the referential integrity between the CRM and Billing LUs. It is recommended to set the **useInstanceId** input argument of the masking Actors to **true** for keeping the PII fields' referential integrity within the Business Entity LUs.

[Click here to learn how to use masking Actors](/articles/19_Broadway/actors/07_masking_and_sequence_actors.md).

[Click here to learn how the TDM task execution process builds the entity list](/articles/TDM/tdm_architecture/03a_task_execution_building_entity_list_on_tasks_LUs.md).





[![Previous](/articles/images/Previous.png)](05_tdm_lu_implementation_general.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05d_tdm_sequence.md)
