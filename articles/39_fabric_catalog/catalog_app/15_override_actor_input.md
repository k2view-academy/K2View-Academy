# Utilize Catalog Properties for Masking

### Overview

Fabric V8.3.1 includes the ability to override the Catalog generator parameters by the values of Catalog-calculated metrics. The purpose of this cross-system capability is to improve the quality of generated data by using the data snapshot values retrieved from the source system as a basis for generated data.

The capability is based on several of Fabric features and it requires to perform few steps in order to utilize them properly. 

The following article includes user stories that demonstrate how to utilize the override capability during the masking or synthetic data generation process. However, the functionality is generic and not limited to the specified user stories. 

### User story 1: Improve generation of random numeric values

When generation of a numeric field's value is required, the default **RandomNumber.actor** is assigned to the relevant classification in the PII & Masking tab. The actor receives two input parameters: ```minimum``` and ```maximum```, with pre-defined default values which are set per each relevant classification. 

To generate random values that will be significantly closer to the real column values, the user should create an alias map between the actor's input parameters and the field's calculated properties.

Below steps describe how to generate a random value based on the field's ```minimumValue``` and ```maximumValue``` range, calculated during the Discovery run. 

**Steps**:

1. Activate the  **Data Quality Metrics** plugin in the **Catalog Settings > Discovery Pipeline** screen and run discovery of the required interface. 

2. Perform **Build Artifacts** and validate which metrics were created for the catalog fields, e.g.  ```minimumValue``` , ```maximumValue```, ```average```. 

3. Verify the **classification** of the fields with the calculated metrics.

4. In the **Catalog Settings > PII & Masking** tab, for each relevant **classification** - click the **Advanced** link and set up the alias map between the generator's inputs and the Catalog's calculated properties: 

   <img src="../images/settings_masking_advanced.png" />


Once this alias map is set, the values of the Catalog field properties from the Catalog's artifact are used during **masking** or **synthetic data generation** process. 

### User story 2: Improve generation of values from distinct list

Sometimes a field includes a limited list of possible values. For example, a 'status' field usually includes a limited list of values (e.g. New, Open, Pending, In progress, Resolved, Closed) . When generating a value for such field, it is required to randomly select one of the existing values (from the data sample) , rather than generating a random string.

To randomly select a value from the list of possible values, the discovery first needs to identify and save these values. 

Below steps describe how to generate a random value based on the field's list of possible values.

**Steps**:

1. Activate the  **Option Set Analyzer** plugin in the **Catalog Settings > Discovery Pipeline** screen and run discovery on the interface. 
2. Perform **Build Artifacts** and validate which catalog fields include the property ```optionSet = true``` and ```classification = OPTION_SET``` - these fields have a limited list of possible values, based on the data sample. 
3. Validate that ```catalog_field_option_set___<data platform>_<schema>_main.csv``` file was created and it includes the field names and the distinct values, identified in the data sample.
4. In the **Catalog Settings > PII & Masking** tab, validate that the **OPTION_SET** classification exists and includes the **RandomOptionSet.actor** generator. Click the **Advanced** link to view the predefined alias map between the generator's inputs and the Catalog's calculated properties, as shown below.
   * If the alias map is not set, create it based on the below image:

<img src="../images/settings_masking_advanced_optionSet.png" />

Once this alias map is set, the values of the Option Set field are used during **masking** or **synthetic data generation** process. 