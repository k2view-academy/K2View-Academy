# TDM - Sequence Implementation Based on Catalog

Fabric 8.2 has added the [Sequences tab](/articles/39_fabric_catalog/10_catalog_settings.md#sequences-tab) to the catalog. This tab allows to set up the sequences that can be generated in a project as part of a population or any other flow. 

## Catalog-Based Sequence TDM Implementation Steps

### I. Populating the Sequence Tab

Adding the Sequence classification the sequence tab and setting the data generator for each sequence. 

Click [here](/articles/39_fabric_catalog/10_catalog_settings.md#sequences-tab) for instructions.

### II. Adding the Sequence Classification to the Tables

Currently, the Catalog doesn’t automatically identify the sequence fields. Thus, after a list of sequences has been set in the **Sequences** tab, the relevant Catalog fields should be manually marked as sequences .

 Click [here](/articles/39_fabric_catalog/10_catalog_settings.md#sequences-tab) for instructions.

### III. Adding the Catalog Masking Actor to the Load and Rule-Based Data Generation Flow

 Run the [TDMLUInit](05_tdm_lu_implementation_general.md#ii-run-the-tdmluinit-flow) flow to add the **CatalogMaskingMapper** Actor to the load and data generation flows.



### Optional - Overriding the Catalog's Sequence Logic

Edit the flows to override the Catalog sequence logic for some of the ID fields: add [Data Generation Actors](/articles/TDM/tdm_implementation/articles/19_Broadway/actors/07_masking_and_sequence_actors.html) after the **CatalogMaskingMapper** Actor and link them to the relevant fields in the **DbLoad** Actor.

Fabric enables you to create your own function or Broadway flow in order to generate a new ID using either **MaskingLuFunction** Actor or **Masking** Actor. 

Follow these steps for setting custom logic for a given sequence:

- Set the **category** input parameter of the Masking or MaskingLuFunction to **enable_sequences** in order to use the Actor for sequence (ID) replacement.  