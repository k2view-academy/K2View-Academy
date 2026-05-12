# Fabric-Managed Master Key

Fabric Master Key Management defines how the platform generates, protects, and controls the master encryption key used to secure data encryption keys in Fabric, either through Fabric’s internal key management or through integration with an external enterprise KMS.

Using the Fabric-Managed Master Key model, the Fabric platform manages the master encryption key internally. Fabric generates the master key and securely stores it within the platform environment, where it is used to protect the encryption keys that secure sensitive data stored by Fabric.

This approach is appropriate for deployments where an external enterprise Key Management Service (KMS) is not required. It provides strong encryption with minimal operational complexity, as Fabric handles the lifecycle of the master key within the platform while maintaining the encryption hierarchy used to protect data at rest.

For more information about Fabric master key management, please review this [topic](/articles/26_fabric_security/02_fabric_entities_design.md#kms).


### Generate Master Key Without KeyStore

Set the ``MASTERKEY_KEY_STORE_ENABLED`` parameter of the config.ini file to **false** (default) to generate a master key without a KeyStore. 


### Generate Master Key Using KeyStore
Set the ``MASTERKEY_KEY_STORE_ENABLED`` parameter of the config.ini file to **true**.

#### Run the keytool
Run the ```keytool``` command on the coordinator node:

~~~bash

keytool -genseckey -alias masterkey_key_name -keyalg aes -keysize 256 -storepass <password> -keystore  $FABRIC_HOME/config/.keystore -storetype PKCS12
~~~

- Copy the key to all other nodes:

~~~bash
scp $FABRIC_HOME/config/.keystore fabric@10.10.10.10:/$FABRIC_HOME/config/.keystore
~~~


#### Edit Config.ini
- Edit the **KEY_STORE_PASSWORD** parameter in the config.ini to the password used in the Keytool command, and enable the **KEY_STORE_LOCATION** parameter and set it to point to the correct path for all Fabric nodes:

```bash
$FABRIC_HOME/fabric/scripts/merge-config.sh -s encryption -k KEY_STORE_LOCATION -v $FABRIC_HOME/config/.keystore -f $FABRIC_HOME/config/config.ini
$FABRIC_HOME/fabric/scripts/merge-config.sh -s encryption -k KEY_STORE_PASSWORD -v <password> -f $FABRIC_HOME/config/config.ini
```

### Restart Fabric Nodes
- Restart all Fabric nodes.


[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security_mk/10a_fabric_master_key_integrated_with_kms.md)
