# Utilize Catalog for Data Generation

### Overview

Fabric V8.3.1 includes the ability to use the values of Catalog-calculated metrics for masking or synthetic data generation process. The purpose of this cross-system capability is to improve the generated data quality by using the data snapshot values of the source system as a basis for generated data.

The capability is based on a set of core features and requires to perform few steps in order to utilize it properly. The following article includes the description of user stories that demonstrate how to utilize the override capability during the masking or synthetic data generation process.

### User story 1: Improve generation of random numeric values

When generating of a numeric field's value is required, the default **RandomNumber.actor** is assigned to the relevant classification in the PII & Masking tab. The actor receives two input parameters: ```minimum``` and ```maximum```, with pre-defined default values which are set per each relevant classification. 

To generate random values that will be significantly closer to the real column values, the user should first create an alias map between the actor's input parameters and the field's calculated properties.

Below steps describe how to generate a random value based on the field's ```minimumValue``` and ```maximumValue``` range, calculated during the Discovery run. 

**Steps**:

1. Run discovery on the interface with **Data Quality Metrics** plugin activated. 

2. Perform **Build Artifacts** and validate which metrics were created for the catalog fields, e.g.  ```minimumValue``` , ```maximumValue```, ```average```. 

3. Verify the classification of the fields with the calculated metrics.

4. In the **Catalog Settings > PII & Masking** tab, click the **Advanced** link for each relevant classification and set up the alias map between the generator's inputs and the Catalog's calculated properties: 

   <img src="../images/settings_masking_advanced.png" />


Once this alias map is set, the values of the Catalog field properties from the Catalog's artifact are used during **masking** or **synthetic data generation** process. 

### User story 2: Improve generation of distinct values

Sometimes a field includes a limited list of possible values. For examples, fields that include an entity type or status usually include limited values. When generating a value for such field, we can randomly select one of the existing values (from the data sample) , rather than generation a random string.

To generate a random value from the list of possible values, the user should first run discovery with **Option Set Analyzer** plugin to identify and save these values. 

Below steps describe how to generate a random value based on the field's list of possible values.

**Steps**:

1. Run discovery on the interface with **Option Set Analyzer** plugin activated. 
2. Perform **Build Artifacts** and validate which catalog fields include the property ```optionSet = true``` and ```classification = OPTION_SET``` - these fields have a limited list of possible values, based on the data sample. 
3. Validate that ```catalog_field_option_set___<data platform>_<schema>_main.csv``` file was created and it includes the Option Set fields with their values, identified in the data sample.
4. In the **Catalog Settings > PII & Masking** tab, validate that the **OPTION_SET** classification exists and includes the **RandomOptionSet.actor** generator. Click the **Advanced** link to view the predefined alias map between the generator's inputs and the Catalog's calculated properties, as shown below.
   * If the alias map is not set, create it based on the below image:

<img src="../images/settings_masking_advanced_optionSet.png" />

Once this alias map is set, the values of the Option Set field are used during **masking** or **synthetic data generation** process. 