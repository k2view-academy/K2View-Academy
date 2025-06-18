# Fabric Data Masking

Data Privacy laws require the ability to mask data by hiding the original values with a modified content. 
Fabric provides a field-level API to protect data that is classified as Personal Identifiable Information, sensitive personal data, or commercially sensitive data.

To ensure that the data is still valid for your data lifecycle management, the masked data will look real and appear consistent.

This capability can be used for conducting tests, implementing data transformations, or providing anonymized data to external 3rd parties via web services or queue messages.

Click [here](02_data_masking_flow.md) for detailed explanation about the masking flow.

### K2view Masking Advantages

- The support of **cross instances consistency**, based on the hashed values.
- The original value is not used as an input for creating the random masked value, other than for formatting purposes.
- The MicroDB is created with the masked values.
- The usage of K2view's Masking mechanism (using **SHA-512/256** algorithm).
- **Multiple masking options** enable maximal flexibility when masking the data.

### De-Anonymization (Pseudonymization)

In some cases, there is a business need to retrieve the original value of the masked LUI. For example, a retrieval of a mailing address in order to contact the customer. 

There are 2 recommended approaches to support de-anonymization and retrieve the original value of the masked field: 

- Keep the source Instance ID in Fabric and use it to retrieve the original data from the source system.

- Keep the encrypted version (each instance is encrypted separately) of the original values in *Fabric only*, in addition to the anonymized values. Limit the access to the anonymized data. Only permitted users can access the original values.

Click [here](/articles/26_fabric_security/03_fabric_LUI_encryption.md) for more information about the LUI encryption.
