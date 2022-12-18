# Fabric Master Key Integrated with External KMS

Fabric master key management mechanism can be integrated with external KMS, since Fabric v6.5.9 release, as described [here](/articles/26_fabric_security/02_fabric_entities_design.md#kms).

To define Fabric to work with KMS, the information should first be acquired from KMS and then set in Fabric.
> By default, Fabric uses its internal master key mechanism. 



## Integration with AWS KMS

1. From KMS, get the specific customer master key information - region and customer master key ID

   - *Key ID* - can be seen in the KMS keys list, for example

     ![](images/10a_aws_KMS_key_list.jpg)

   - *Region* - the region name where the CMK is created. You can see the region (as well as the ID) also when drilling down into the key page, from the key list page (KMS > Customer Managed Keys):

     ![](images/10a_aws_KMS_CMK.jpg)

2. From AWS, get the user IAM access credentials: access key ID and its secret access key.

   - This user shall be granted with appropriate permissions to the specific KMS customer master key. Associated users can also be found in the key page > key policy section. 

3. In Fabric, set the values in config.ini under ``[encryption_aws_kms]`` section, according to the KMS information, as following: 

   ~~~
   [encryption_aws_kms]
   ACCESS_KEY_ID=
   SECRET_ACCESS_KEY=
   REGION=
   CUSTOMER_KEY_ID=
   ~~~
   >  Notes: 
   >
   >  * Relevant config.ini parameters are encrypted and are not saved in the file in their clear/plain form.
   >  * Changes in the config.ini file shall be done on all Fabric nodes.
   >  * In case a Fabric node already has a trust with AWS (with AWS's user who shall connect to KMS), then ACCESS_KEY_ID and SECRET_ACCESS_KEY can be omitted.

4. In Fabric, run ``activatekey name='<name>' generatorType='AWS_KMS' storeType='AWS_KMS'``.

### Multi Region Support

While Fabric might be deployed across several regions, it can use same KMS key which defined at a specific AWS region. it is still may be required to work with [AWS multi region keys](https://docs.aws.amazon.com/kms/latest/developerguide/multi-region-keys-overview.html). In this article AWS recommends to consider this option carefully. This article also explains the process of multi-region keys creation. In such case, config.ini shall be set differently among the Fabric nodes, i.e. with relevant region definitions (key-id is the same).



## Integration with GCP KMS

1. From KMS, get the specific master key information - product/project ID, location, master key ID, keyring ID

   ![](images/10a_gcp_KMS_MK.jpg)

2. From GCP, get the user's access credentials , as a JSON file, which can be achieved when creating the user. 

   - This user shall be granted with appropriate permissions to the specific KMS master key. At least "*Cloud KMS CryptoKey Encrypter/Decrypter*" role shall be assigned to this user.

3. In Fabric:

   - Locate the credential file in Fabric machine and populate its full path location in the ``CREDENTIAL_FILE`` parameter. Alternatively, credentials file can be set as an environment variable called *GOOGLE_APPLICATION_CREDENTIALS*.
   - Set the values in config.ini under ``[encryption_gcp_kms]`` section, according to the KMS information, as following:

      ~~~
      [encryption_gcp_kms]
      PROJECT_ID=
      LOCATION_ID=
      KEY_ID=
      KEY_RING_ID=
      CREDENTIAL_FILE=
      ~~~
      >  Note: 
      >
      >  * Relevant parameters are encrypted and are not saved in the file in their clear/plain form. In addition, the credential file is encrypted and is not saved in its plain/clear form. On runtime, when calling the GCP, Fabric knows to provides it properly, in its plain form. 
      >  * Changes at the config.ini file shall be done on all Fabric nodes.
      >  * In case a Fabric node already has a trust with GCP (with GCP's user or role who shall connect to KMS), then CREDENTIAL_FILE can be omitted.

4. In Fabric, run ``activatekey name='<name>' generatorType='Java_AES' storeType='GCP_KMS'``.



## Integration with KMIP KMS

1. From KMS, get the specific master key information - partition ID, master key ID, user, password, KMS server URL

2. In Fabric, set the values in config.ini under ``[encryption_kmip_kms]`` section, according to the KMS information, as following: 

   ~~~
   [encryption_kmip_kms] 
   USER=
   PASSWORD=
   PARTITION=
   KEY_ID=
   BASE_URL_TEMPLATE=
   ~~~

   >  Notes: 
   >
   >  * The BASE_URL_TEMPLATE, the server URL, shall include the key_id inside the URL with {} wrapping brackets, for example: https://<KMS-host>/api/keys/{key_id}
   >  * Relevant config.ini parameters, like user and password, are encrypted and are not saved in the file in their clear/plain form.
   >  * Changes at the config.ini file shall be done on all Fabric nodes.

3. In Fabric, run ``activatekey name='<name>' generatorType='Java_AES' storeType='KMIP_KMS'``.



## Symmetric and Asymmetric Master Key Encryption Types

While KMS providers enable working with either symmetric and asymmetric encryption types, Fabric supports the symmetric type. This type should be selected in KMS when creating the master key. 



[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/devops/10_fabric_definde_master_key.md)

