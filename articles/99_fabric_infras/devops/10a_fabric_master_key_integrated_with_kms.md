# Fabric Master Key Integrated with KMS

Fabric master key mechanism can be integrated with KMS, as described [here](/articles/26_fabric_security/02_fabric_entities_design.md#kms).

To define Fabric to work with KMS, the information shall be acquired first from KMS and then shall be set at Fabric.

## Integration with AWS KMS

1. Get from KMS the specific customer master key information - region and customer master key ID

   - *Key ID*, can be seen in the KMS keys list, for example

     ![](images/10a_aws_KMS_key_list.jpg)

   - *Region*, the region name where the CMK is created. Note that keys are created per region. For more information about multi region support see [here](). You can see the region (as well as the ID) also when drilling down into the key page, from the key list page (KMS > Customer Managed Keys):

     ![](images/10a_aws_KMS_CMK.jpg)

2. Get from AWS the user IAM access credentials: access key ID and its secret access key.

   - This user shall be granted with appropriate permissions to the specific KMS customer master key. The associated users can be also found at the key page > key policy section. 

3. At Fabric, Set the values at config.ini under ``[encryption_aws_kms]`` section, according to the KMS information. 

   > Note: This shall be done at all Fabric nodes. 

   ~~~
   [encryption_aws_kms]
   ACCESS_KEY_ID=
   SECRET_ACCESS_KEY=
   REGION=
   CUSTOMER_KEY_ID=
   ~~~


4. At Fabric, run ``activatekey name='masterkey_key_name' generatorType='AWS_KMS' storeType='AWS_KMS'``.

### Multi Region Support

According to the specific Fabric deployment and to customer definitions, config.ini shall be set. 

For example, at AWS, when using multi region keys, the replication keys might need to be used at different Fabric nodes, according to their region.

## Integration with GCP KMS

1. Get from KMS the specific master key information - product/project ID, location, master key ID, keyring ID

   ![](images/10a_gcp_KMS_MK.jpg)

2. Get from GCP the user access credentials, as JSON file, which can be achieved when creating the user. 

   - This user shall be granted with appropriate permissions to the specific KMS master key. At least "*Cloud KMS CryptoKey Encrypter/Decrypter*" role shall be assigned to that user.

3. At Fabric:

   - Set the values at config.ini under ``[encryption_gcp_kms]`` section, according to the KMS information.

   - Locate the credential file at Fabric machine.

   >  Note: These actions shall be done at all Fabric nodes. 
   ~~~
   [encryption_gcp_kms]
   PRODUCT_ID=
   LOCATION_ID=
   KEY_ID=
   KEY_RING_ID=
   CREDENTIAL_FILE=
   ~~~


## Symmetric and Asymmetric Master Key Encryption Types

While KMS providers enable working with either symmetric and asymmetric encryption types, Fabric supports the symmetric type. This type shall be selected at KMS when creating the master key. 



[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/devops/10_fabric_definde_master_key.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/devops/11_kafka_plain_sasl_hardening.md)

