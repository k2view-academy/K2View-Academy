

## LUI Encryption

### LUI Encryption Key
Fabric encrypts each instance (LUI) using the AES-256 in OFB mode encryption algorithm. Hence, the key length is 256 bytes. If an input key is shorter than the maximum key length, the key content is repeated as many times as necessary to complete the key.

The underlying key will be the hash (using SHA-256 algorithm) of the following parts:

- LU type name (logical unit name). For example: “CUSTOMER”
- LUI (instance id). For example: “123”
- Master key- input key generated as detailed in the previous section of this document.

As a result, Fabric creates a different key for each instance id, because each instance id has a different value. Fabric saves the key description for each instance id (in the ENTITY table in cassandra). This way, Fabric can decrypt the entity when necessary.
The encrypted master key that was used to encrypt the instance id, can be taken from the KEYS table by the key description.

### Encrypt LUI Using Fabric Studio

By default, when creating a logical unit, the *enable data encryption* property field is set to ‘False’.

If you wish to encrypt each instance (LUI), set the ‘Enable data encryption’ property of your LU schema to ‘True’. 

See the screenshot below:

<img src="/articles/26_fabric_security/images/03_fabric_LUencryption_studio.png">


### LUI Partial Encryption

If you wish to encrypt only selected fields on the LU instance, you need first to set the following parameter to false in the config.ini file ```FULL_ENTITY_ENCRYPTION=false```.

It is then possible to encrypt specific fields in your implementation using the following built-in functions:

<img src="/articles/26_fabric_security/images/04_fabric_LUencryption_LUEncrypt.PNG">


**luEncrypt()**

```public static String luEncrypt(String plainData) throws Exception```

The function encrypts data in text format using the latest master key and LUI key. This method can be used to encrypt individual fields in the LUI micro-database

Parameters:

- plainData - the text to encrypt


The function returns the following:

- Data encrypted with the LUI key described above.


**luDecrypt()**

```public static String luDecrypt(String encryptedData, String luName, String entityID) throws Exception```

The function decrypts a string that was previously encrypted using the ```luEncrypt``` method:

Parameters:

- encryptedData - the encrypted data
- luName - The LUType for the data to decrypt
- entityID - The instance id of the instance holding this data


The function returns the following:

- Decrypted text
- Exception in cases when decryption failed



**luRekey()** 

```public static String luRekey(String encryptedData) throws Exception```

The function decrypts data with it's key and encrypts it with the latest master key. For a system to support the rekey option, data needs to be re-keyed when re-syncing the LUI.

Parameters:

encryptedData - Encrypted data to be rekeyed


The function returns the following:

- Encrypted data, encrypted with the active key
- Exception in cases when the process has failed





[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/02_jobs_flow_and_status.md) 
