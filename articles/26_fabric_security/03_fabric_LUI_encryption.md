# LUI Encryption

## LUI Encryption Key
Fabric encrypts each LUI using the AES-256 mode encryption algorithm. Hence, the key length is 256 bytes. 

The underlying key, using the SHA-256 algorithm, is a hash that consists of the following parts:

- LU type name (Logical Unit name). For example: “CUSTOMER”
- LUI (instance ID). For example: “123”
- Master key, [input key](/articles/26_fabric_security/02_fabric_entities_design.md#fabric-master-key) generated. 

Since each Instance ID has a different value, Fabric creates a different key for each Instance ID. Fabric saves the key description of each Instance ID in the ENTITY table in Cassandra. This way, Fabric can decrypt the entity when necessary.
The encrypted master key used to encrypt the Instance ID can be taken from the KEYS table according to the key's description.

## Encrypting an LUI Using the Fabric Studio

By default, when creating a Logical Unit, the **Enable data encryption** field is set to **False**.

To encrypt each instance (LUI), set the **Enable data encryption** property of the LU schema to **True**. 

See the screenshot below:

<img src="/articles/26_fabric_security/images/03_fabric_LUencryption_studio.png">

There is a parameter in config.ini called ENTITY_ENCRYPTION_MODE that controls the two LUI encryption modes supported by Fabric. The modes are ON_SAVE and MDB, and each are described below:

> **ON_SAVE** (default value): Encrypt the LUI when saving it to Cassandra. 
The encryption is done after the compression. The advantage of using this encryption mode is twofold compared to the non-encrypted mode: 
The data is stored encrypted in Cassandra, and at the same time the data has minimal performance and storage impact. 
It is important to mention that data in the cache is not encrypted, therefore it is the user’s responsibility to ensure that the data 
in the cache is secured on the operating system level.

> **MDB**: Encrypt on the MicroDB before the compression. The result here is that compression has a minimal impact on the MicroDB size
but it could affect performance when reading/writing data, as the MicroDB is much bigger compared to its size in non-encryption mode. On the other hand,
the microDB is stored encrypted in the cache as well.

Although ENTITY_ENCRYPTION_MODE is set to ON_SAVE by default, the user must set the **Enable data encryption** field 
in LU schema properties to **True** , otherwise the LU will not be encrypted.

## Partial LUI Encryption

To encrypt only selected fields on the LU Instance, use the following built-in functions:

<img src="/articles/26_fabric_security/images/04_fabric_LUencryption_LUEncrypt.PNG">


### Partial LUI Encryption Functions

#### **luEncrypt()**

```public static String luEncrypt(String plainData) throws Exception```

This function encrypts data in text format using the latest master key and LUI key and can be used to encrypt individual fields in the LUI micro-database

Parameters:

- plainData, the text to encrypt


The function returns the following:

- Data encrypted with the LUI key described above.


#### **luDecrypt()**

```public static String luDecrypt(String encryptedData, String luName, String entityID) throws Exception```

This function decrypts a string previously encrypted using the ```luEncrypt``` method:

Parameters:

- encryptedData, the encrypted data
- luName, LUType for the data to decrypt
- entityID, Instance ID of the instance holding this data

This function returns the following:

- Decrypted text
- Exceptions of failed decryptions 



#### **luRekey()** 

```public static String luRekey(String encryptedData) throws Exception```

This function decrypts data using its key and encrypts it using the latest master key. For a system to support the luRekey option, data must be rekeyed when resyncing the LUI.

Parameters:

encryptedData, encrypted data to be rekeyed


This function returns the following:

- Encrypted data, encrypted with the active key
- Exceptions when the process fails



[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/02_fabric_entities_design.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/04_fabric_interfaces_security.md)
