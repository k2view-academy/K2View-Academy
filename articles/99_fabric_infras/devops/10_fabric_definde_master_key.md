# Master Key Protection with KeyStore


### Generate Master Key Without KeyStore

Set the ``MASTERKEY_KEY_STORE_ENABLED`` parameter of the config.ini file to **false** (default) to generate a master key without a KeyStore. 


### Generate Master Key Using KeyStore
Set the ``MASTERKEY_KEY_STORE_ENABLED`` parameter of the config.ini file to **true**.

#### Create KeyStore Directory 
After adding the encryption module to the fabric-server-start.sh module, create the Keystore folder under the k2view home directory for all nodes:
```bash
cd $K2_HOME
mkdir .keystore
```

#### Run the keytool
Run the ```keytool``` command on the coordinator node:

~~~bash
keytool -genseckey -alias masterkey_key_name -keyalg aes -keysize 256 -storepass <password> -keystore  $K2_HOME/.keystore/fabric.keystore -storetype JCEKS
~~~

- Copy the key to all other nodes:

~~~bash
scp $K2_HOME/.keystore/fabric.keystore fabric@10.10.10.10:/$K2_HOME/.keystore/
~~~



#### Edit Config.ini Script
- Edit the **KEY_STORE_PASSWORD** parameter in the config.ini to the password used in the Keytool command, and enable the **KEY_STORE_LOCATION** parameter and set it to point to the correct path for all Fabric nodes:

```bash
sed -i "s@#KEY_STORE_LOCATION=.*@KEY_STORE_LOCATION=$K2_HOME/.keystore/fabric.keystore@" $K2_HOME/config/config.ini
sed -i 's@#KEY_STORE_PASSWORD=.*@KEY_STORE_PASSWORD= <password>@' $K2_HOME/config/config.ini
```

### Restart Fabric Nodes
- Restart all Fabric nodes.



[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/devops/09_fabric_replace_admin_password.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/devops/10a_fabric_master_key_integrated_with_kms.md)
