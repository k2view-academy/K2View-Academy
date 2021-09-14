# Cassandra Hardening

The following steps ensure that the keys that secure Fabric and Cassandra are properly generated and configured.

- The example password ```Q1w2e3r4t5!``` is used for TLS keys and can be replaced in all the following sections by a new password.
- Do not forget to replace all `$K2_HOME/` & `$INSTALL_DIR`  values with the full and correct path location for both Fabric and Cassandra.


## Step 1 - Keys Generation

1. Run the keys script that can be downloaded from [location](https://owncloud-bkp2.s3.amazonaws.com/adminoc/Utils/Hardening/secure_cassandra.sh). 
2. Stop Fabric and Cassandra services before running the script.


```bash
cd $K2_HOME/
rm -rf .cassandra .cassandra_ssl export .oracle_jre_usage .ssl

chmod +x secure_cassandra.sh

!! run on single Fabric node only !!
* To change the password or the cluster name, edit the secure_cassandra.sh or execute using the password and cluster name parameters
e.g.: ./secure_cassandra.sh {Password} {Cluster_Name}

./secure_cassandra.sh Q1w2e3r4t5 k2tls

Warning:
The JKS Keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore /opt/apps/k2view/.cassandra_ssl/cassandra.keystore -destkeystore /opt/apps/k2view/.cassandra_ssl/cassandra.keystore -deststoretype pkcs12".
Certificate stored in file </opt/apps/k2view/.cassandra_ssl/CLUSTER_k2tls_PUBLIC.cer>

Warning:
The JKS Keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore /opt/apps/k2view/.cassandra_ssl/cassandra.keystore -destkeystore /opt/apps/k2view/.cassandra_ssl/cassandra.keystore -deststoretype pkcs12".
Certificate was added to keystore
[Storing /opt/apps/k2view/.cassandra_ssl/cassandra.truststore]

Warning:
The JKS Keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore /opt/apps/k2view/.cassandra_ssl/cassandra.keystore -destkeystore /opt/apps/k2view/.cassandra_ssl/cassandra.keystore -deststoretype pkcs12".
Certificate stored in file </opt/apps/k2view/.cassandra_ssl/CLIENT_k2tls_PUBLIC.cer>

Warning:
The JKS Keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore /opt/apps/k2view/.cassandra_ssl/cassandra.keystore -destkeystore /opt/apps/k2view/.cassandra_ssl/cassandra.keystore -deststoretype pkcs12".
Certificate was added to keystore
[Storing /opt/apps/k2view/.cassandra_ssl/cassandra.truststore]
Importing keystore /opt/apps/k2view/.cassandra_ssl/cassandra.keystore to /opt/apps/k2view/.cassandra_ssl/cassandra.pks12.keystore...
Entry for alias k2tls_client successfully imported.
Entry for alias k2tls_cluster successfully imported.
Import command completed:  2 entries successfully imported, 0 entries failed or cancelled
MAC verified OK
MAC verified OK 
```

The following 7 files will appear under the ```$K2_HOME/.cassandra_ssl``` directory:
- k2tls_CLIENT.key.pem
- k2tls_CLIENT.cer.pem
- cassandra.keystore
- cassandra.pks12.keystore
- cassandra.truststore
- CLIENT_k2tls_PUBLIC.cer
- CLUSTER_k2tls_PUBLIC.cer

## Step 2 - Cassandra YAML

1. Edit the cassandra.yaml file with the appropriate passwords and certification files.
2. Execute this as a Cassandra user on all the Cassandra nodes. 

```bash
sed -i "s@internode_encryption: none@internode_encryption: all@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@key_password: cassandra@key_password: Q1w2e3r4t5@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@keystore: conf/.keystore*@keystore: $INSLATT_DIR/.cassandra_ssl/cassandra.keystore@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@# truststore:@truststore:@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@truststore: conf/.truststore*@truststore: $INSLATT_DIR/.cassandra_ssl/cassandra.truststore@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@# protocol: TLS*@protocol: TLS@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@keystore_password: cassandra@keystore_password: Q1w2e3r4t5@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@# truststore: conf/.truststore*@truststore: $INSLATT_DIR/.cassandra_ssl/cassandra.truststore@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@# truststore_password: cassandra*@truststore_password: Q1w2e3r4t5@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@truststore_password: cassandra*@truststore_password: Q1w2e3r4t5@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@# protocol: TLS*@protocol: TLS@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@# require_client_auth: .*@require_client_auth: true@g" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@# store_type: JKS@store_type: JKS@g" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i '0,/enabled: false/! {0,/enabled: false/ s/enabled: false/enabled: true/}' $CASSANDRA_HOME/conf/cassandra.yaml
sed -i -e 's/# \(.*cipher_suites.*\)/\1/g' $CASSANDRA_HOME/conf/cassandra.yaml
sed -i -e 's/# \(.*native_transport_port_ssl:.*\)/\1/g' $CASSANDRA_HOME/conf/cassandra.yaml
sed -i '1,/cdc_enabled: false/ {0,/cdc_enabled: false/ s/cdc_enabled: false/cdc_enabled: true/}' $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@back_pressure_enabled: .*@back_pressure_enabled: true@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@native_transport_port: .*@native_transport_port: 9142@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i -e 's/# \(.*native_transport_port_ssl:.*\)/\1/g' $CASSANDRA_HOME/conf/cassandra.yaml
```

3. Restart the Cassandra service on each node: ```cassandra```


## Step 3 - Cassandra CQLSHRC
1. Edit the .cassandra/cqlshrc file using the appropriate passwords and certification files.
2. Execute this as a Cassandra user on all Cassandra nodes. 
```bash
cp $INSTALL_DIR/cassandra/conf/cqlshrc.sample $INSTALL_DIR/.cassandra/cqlshrc

sed -i "s@\;\[ssl\]@\[ssl\]@" $INSLATT_DIR/.cassandra/cqlshrc
sed -i '/^\[csv]/i factory = cqlshlib.ssl.ssl_transport_factory' $INSLATT_DIR/.cassandra/cqlshrc
sed -i "s@; certfile = .*@certfile = $INSLATT_DIR/.cassandra_ssl/k2tls_CLIENT.cer.pem@" $INSLATT_DIR/.cassandra/cqlshrc
sed -i "s@;validate = true@validate = true@" $INSLATT_DIR/.cassandra/cqlshrc
sed -i "s@;userkey = .*@userkey = $INSLATT_DIR/.cassandra_ssl/k2tls_CLIENT.key.pem@" $INSLATT_DIR/.cassandra/cqlshrc
sed -i "s@;usercert = .*@usercert = $INSLATT_DIR/.cassandra_ssl/k2tls_CLIENT.cer.pem@" $INSLATT_DIR/.cassandra/cqlshrc
sed -i "s@port = .*@port = 9142@" $INSLATT_DIR/.cassandra/cqlshrc
sed -i "s@hostname = .*@hostname = $(hostname -I |awk {'print $1'})@" $INSLATT_DIR/.cassandra/cqlshrc
```



## Step 4 - Transfer Keys and Certificates to All Cassandra and Fabric Nodes

Tar and copy them to all Cassandra and Fabric nodes in the cluster.  

See the example below: 

``` bash
tar -czvf keys.tar.gz -C $INSLATT_DIR/.cassandra_ssl .
scp keys.tar.gz cassandra@10.10.10.10:/opt/apps/cassandra/
mkdir -p $INSLATT_DIR/.cassandra_ssl && tar -zxvf ckeys.tar.gz -C $INSLATT_DIR/.cassandra_ssl
```



## Step 5 - Disable the default cassandra superuser

Cassandra default **superuser** is `cassandra` and it must be disabled before going to production. Before doing so, you need to create new **superusers**, one for SYSDBA, and one for Fabric connection use

1. connect to one of the Cassandra nodes console, and create 2 new **superuser's**

   ~~~bash
   echo "create user k2admin with password 'Q1w2e3r4t5' superuser;" |cqlsh -u cassandra -p cassandra
   echo "create user k2sysdba with password '3ptBF9eMSsyLrXr3' superuser;" |cqlsh -u cassandra -p cassandra
   echo "drop role cassandra;" |cqlsh -u k2sysdba -p 3ptBF9eMSsyLrXr3
   ~~~

2. drop the `cassandra` user

   ~~~bash
   echo "drop role cassandra;" |cqlsh -u k2sysdba -p 3ptBF9eMSsyLrXr3
   ~~~

   

[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/devops/03_fabric_api_and_ui_hardening.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/devops/05_connect_fabric_to_cassandra_with_tls.md)

