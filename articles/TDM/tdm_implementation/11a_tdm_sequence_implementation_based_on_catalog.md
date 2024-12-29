# TDM - Sequence Implementation Based on Catalog

Fabric V8.2 has added the [Sequences tab](/articles/39_fabric_catalog/10_catalog_settings.md#sequences-tab) to the Catalog. This tab allows to set up the sequences that can be generated in a project as part of a population or any other flow. 

## Catalog-based Sequence TDM Implementation Steps

### I. Populating the Sequence Tab

Adding the Sequence classification to sequence tab and setting the data generator for each sequence. 

Click [here](/articles/39_fabric_catalog/10_catalog_settings.md#sequences-tab) for instructions.

### II. Adding the Sequence Classification to the Tables

Currently, the Catalog does not automatically identify the sequence fields. Thus, after a list of sequences has been set in the **Sequences** tab, the relevant Catalog fields should be manually marked as sequences.

Click [here](/articles/39_fabric_catalog/10_catalog_settings.md#sequences-tab) for instructions.

### III. Adding the Catalog Masking Actor to the Load and Rule-based Data Generation Flow

Run the [TDMLUInit](05_tdm_lu_implementation_general.md#ii-run-the-tdmluinit-flow) flow to add the **CatalogMaskingMapper** Actor to the load and data generation flows.



### Optional - Overriding the Catalog's Sequence Logic

Edit the flows to override the Catalog sequence logic for some of the ID fields: Add [Data Generation Actors](/articles/19_Broadway/actors/07_masking_and_sequence_actors.md) after the **CatalogMaskingMapper** Actor and link them to the relevant fields in the **DbLoad** Actor.

Fabric enables you to create your own function or Broadway flow in order to generate a new ID using either **MaskingLuFunction** Actor or **Masking** Actor. 

Follow these steps for setting custom logic for a given sequence:

- Open the generated sequence flow and replace the MaskingSequence Actor with MaskingLuFunction Actor or Masking Actor.
- Set the **category** input parameter of the Masking or MaskingLuFunction to **enable_sequences** as a way to use the Actor for sequence (ID) replacement.  
