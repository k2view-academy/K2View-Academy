# TDM - Catalog-based Sequence Implementation

Fabric V8.2 has added the [Sequences tab](/articles/39_fabric_catalog/10_catalog_settings.md#sequences-tab) to the Catalog. This tab allows to set up the sequences that can be generated in a project as part of a population or any other flow. 

## Catalog-based Sequence Implementation Steps

### I. Catalog - Populating the Sequences Tab

This includes adding the Sequence classification to the Sequences tab and setting the data generator for each sequence. 

Click [here](/articles/39_fabric_catalog/10_catalog_settings.md#sequences-tab) for instructions.

### II. Catalog - Adding the Sequence Classification to the Tables

Currently, the Catalog does not automatically identify the sequence fields. Thus, after a list of sequences has been set in the **Sequences** tab, the relevant Catalog fields should be manually marked as sequences. Build the Catalog artifacts when completing the manual updates.

Click [here](/articles/39_fabric_catalog/10_catalog_settings.md#sequences-tab) for instructions.

Fabric 8.3 onwards supports a [bulk edit](/articles/39_fabric_catalog/catalog_app/14_1_bulk_creation.md) in the Catalog. This feature simplifies adding Sequence classification to the tables, since you can create a bulk for each Sequence and update the tables accordingly. A separate bulk needs to be created on each Sequence.

**Example: Adding the CUSTOMER_ID Sequence to all fields named 'CUSTOMER_ID' in the CRM DB**:

- Search for fields named CUSTOMER_ID:

  ![bulk example1](images/seq_bulk_edit_cust_id_example1.png)

- Select the relevant fields and add them to the bulk:

  ![bulk example2](images/seq_bulk_edit_cust_id_example2.png)

- Close the Search window (click the X on the Search box) and edit the bulk: add the CUSTOMER_ID Sequence to this bulk and click the *Submit & Clear bulk* button. 

  ![bulk](images/seq_bulk_edit_cust_id_example3.png)

- The CUSTOMER_ID Sequence is added to all the bulk's fields and the bulk is cleared. 

- Now you can repeat these steps for additional Sequences (CONRTACT_ID for example).

Click [here](/articles/39_fabric_catalog/catalog_app/14_2_bulk_edit.md) for additional instructions on how to edit a bulk in the Catalog.

### III. TDM Implementation Changes

- Run the [TDMLUInitBasedOfFabric  flow](05_tdm_lu_implementation_general.md#ii-adding-the-tdm-setup-to-the-lu) flow to regenerate the load and rule-based data generation flows.

- Add the **TDM_USING_CATALOG_SEQUENCES** Global on each LU for which the sequences should be populated by the Catalog. Set this Global to **true**. 

