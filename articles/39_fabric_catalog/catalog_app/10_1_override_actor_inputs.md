# Utilize Catalog for Improved Data Generation

### Overview

Fabric V8.3.1 includes the ability to use the values of Catalog field properties for masking or synthetic data generation process. The purpose of this cross-system capability is to improve the generated data quality by using the data snapshot values of the source system as a basis for generated data.

The capability is based on a set of core features and requires to perform few steps in order to utilize it properly. The following article includes the description of user stories that demonstrate how to utilize the override capability during the masking or synthetic data generation process.

### User story 1: Improve generation of random values

**Example**: when generating a masked value of a field, use the values of the field's ```minimumValue``` and ```maximumValue``` properties calculated during the Discovery. 

**Steps**:

1. Run discovery on the interfacing, setting **Data Quality Metrics** plugin to active prior to the run. 

2. Perform **Build Artifacts** and validate which metrics were created for the catalog fields (e.g.  ```minimumValue``` and ```maximumValue```) and what is the classification of these fields.

3. ​

   ​

   ​

   ​

establish the alias between the Catalog field properties and the actor's inputs, based on the field’s classification. Once this alias map is set, the values of the relevant Catalog field properties can be used during masking or synthetic data generation process. 