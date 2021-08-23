# Fabric Data Masking

Data Privacy laws compliancy requires the ability to mask data by hiding original values with modified content. 
Fabric provides a field-level API to protect data classified as Personal Identifiable Information, sensitive personal data, or commercially sensitive data.

To ensure that the data is still valid for you data lifecycle management, the data will look real and appear consistent.

This capability can be used to conduct tests, implement data transformations, or serve anonymized data to external third parties via web services or queue messages.


## Masking Algorithm

The Masking utility hashes the original value of the masked field using SHA-256 or SHA-512 algorithms depending on the encryption strength required. A Fabric master key dedicated to the hashing process can also be used for salting.

## Broadway Masking Actor

Broadway provides a number of masking actors that can be used to mask sensitive fields like SSN, credit card numbers, email addresses or sequences before they are loaded into a target Database or even into Fabric.

Moreover, sensitive data can be masked either by using the LU Table Population Broadway Flow (which masks the data before it is saved into Fabric), or by loading Broadway to mask the data before it is sent to the target.
Click [here](/articles/19_Broadway/actors/07_masking_and_sequence_actors.md) to read how to use fabric's masking capabilities.

[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/05_fabric_webservices_security.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/07_user_IAM_overview.md)

