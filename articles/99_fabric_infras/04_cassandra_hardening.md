# Cassandra Hardening

## Table of Contents

- [Step 1 - Keys Generation](#step-1---keys-generation)
- [Step 2 - Transfer Keys and Certificates to All Cassandra and Fabric Nodes](#step-2---transfer-keys-and-certificates-to-all-cassandra-and-fabric-nodes)
- [Step 3 - Cassandra YAML](#step-3---cassandra-yaml)
- [Step 4 - Cassandra CQLSHRC](#step-4---cassandra-cqlshrc)
- [Step 5 - Disable the default cassandra superuser](#step-5---disable-the-default-cassandra-superuser)


The following steps ensure that the keys that secure Fabric and Cassandra are correctly generated and configured on a **vanilla Apache Cassandra 4.1.x** installation (see the [Cassandra Setup guide](/articles/98_installation_and_upgrade/Install_on_Linux/Cassandra_Setup.md)).

- The example password ```changeit``` is used for the TLS keys, and the cluster name defaults to ```Cassandra```. Both can be replaced in all of the following sections (export `CLUSTER_NAME` before running the commands to use a different name).
- These steps assume `$CASSANDRA_HOME` is set to the `cassandra` symlink (`/opt/apps/cassandra/cassandra`) created in the Cassandra Setup guide. All keys are generated under `/opt/apps/cassandra/.cassandra_ssl`; the keystore and truststore are then deployed to `$CASSANDRA_HOME/conf/.keystore` and `$CASSANDRA_HOME/conf/.truststore` (the paths the stock `cassandra.yaml` already references).
- `keytool` (bundled with the JDK) and `openssl` must be available on the `PATH`.


## Step 1 - Keys Generation

Run the following **on a single Cassandra node only**, connected as the **cassandra** user. The commands generate a cluster keypair (for inter-node encryption) and a client keypair (for client-to-node encryption), build the keystore and truststore, and extract the client key/certificate in PEM format for `cqlsh` and Fabric.

> To use a different password or cluster name, change `changeit` and export `CLUSTER_NAME` (e.g. `export CLUSTER_NAME=Cassandra`) before running the commands.

~~~bash
export SSL_DIR=/opt/apps/cassandra/.cassandra_ssl
export CLUSTER_NAME=${CLUSTER_NAME:-Cassandra}
export KEYSTORE_PASSWORD=changeit
mkdir -p $SSL_DIR && cd $SSL_DIR

# 1. Generate the cluster (node) keypair
keytool -genkeypair -keyalg RSA -keysize 2048 -alias ${CLUSTER_NAME}_cluster \
  -keystore cassandra.keystore -storepass ${KEYSTORE_PASSWORD} -keypass ${KEYSTORE_PASSWORD} \
  -validity 3650 -dname "CN=${CLUSTER_NAME}, OU=Cassandra, O=K2view, C=US"

# 2. Generate the client keypair (used by cqlsh / Fabric)
keytool -genkeypair -keyalg RSA -keysize 2048 -alias ${CLUSTER_NAME}_client \
  -keystore cassandra.keystore -storepass ${KEYSTORE_PASSWORD} -keypass ${KEYSTORE_PASSWORD} \
  -validity 3650 -dname "CN=${CLUSTER_NAME}_client, OU=Cassandra, O=K2view, C=US"

# 3. Export the public certificates
keytool -exportcert -alias ${CLUSTER_NAME}_cluster -keystore cassandra.keystore \
  -storepass ${KEYSTORE_PASSWORD} -file CLUSTER_${CLUSTER_NAME}_PUBLIC.cer
keytool -exportcert -alias ${CLUSTER_NAME}_client -keystore cassandra.keystore \
  -storepass ${KEYSTORE_PASSWORD} -file CLIENT_${CLUSTER_NAME}_PUBLIC.cer

# 4. Build the truststore from the public certificates
keytool -importcert -noprompt -alias ${CLUSTER_NAME}_cluster -file CLUSTER_${CLUSTER_NAME}_PUBLIC.cer \
  -keystore cassandra.truststore -storepass ${KEYSTORE_PASSWORD}
keytool -importcert -noprompt -alias ${CLUSTER_NAME}_client -file CLIENT_${CLUSTER_NAME}_PUBLIC.cer \
  -keystore cassandra.truststore -storepass ${KEYSTORE_PASSWORD}

# 5. Extract the client key and certificate in PEM format (for cqlsh / Fabric)
keytool -importkeystore -srckeystore cassandra.keystore -srcstorepass ${KEYSTORE_PASSWORD} \
  -srcalias ${CLUSTER_NAME}_client -destkeystore cassandra.pks12.keystore \
  -deststoretype PKCS12 -deststorepass ${KEYSTORE_PASSWORD}
openssl pkcs12 -legacy -in cassandra.pks12.keystore -passin pass:${KEYSTORE_PASSWORD} -nocerts -nodes -out ${CLUSTER_NAME}_CLIENT.key.pem
openssl pkcs12 -legacy -in cassandra.pks12.keystore -passin pass:${KEYSTORE_PASSWORD} -clcerts -nokeys -out ${CLUSTER_NAME}_CLIENT.cer.pem

# 6. Bundle the keys for transfer to the other nodes
tar -zcvf cassandra_keys.tar.gz cassandra.keystore cassandra.truststore cassandra.pks12.keystore \
  ${CLUSTER_NAME}_CLIENT.key.pem ${CLUSTER_NAME}_CLIENT.cer.pem CLIENT_${CLUSTER_NAME}_PUBLIC.cer CLUSTER_${CLUSTER_NAME}_PUBLIC.cer
~~~

The following 7 files are created under `/opt/apps/cassandra/.cassandra_ssl` (file names contain your cluster name — `Cassandra` by default):
- &lt;CLUSTER_NAME&gt;_CLIENT.key.pem
- &lt;CLUSTER_NAME&gt;_CLIENT.cer.pem
- cassandra.keystore
- cassandra.pks12.keystore
- cassandra.truststore
- CLIENT_&lt;CLUSTER_NAME&gt;_PUBLIC.cer
- CLUSTER_&lt;CLUSTER_NAME&gt;_PUBLIC.cer

The `cassandra_keys.tar.gz` archive bundles all of the above so it can be transferred to the other nodes.

## Step 2 - Transfer Keys and Certificates to All Cassandra and Fabric Nodes

Copy the previously created file  *cassandra_keys.tar.gz* to all Cassandra nodes in the cluster.

See the example below: 

``` bash
# copy the archive to the other nodes in case more than one node is used
# 10.10.10.10 represents the IP address of another node
scp /opt/apps/cassandra/.cassandra_ssl/cassandra_keys.tar.gz cassandra@10.10.10.10:/opt/apps/cassandra/.cassandra_ssl/

# on each OTHER node, unpack the archive into the same directory
mkdir -p /opt/apps/cassandra/.cassandra_ssl && tar -zxvf cassandra_keys.tar.gz -C /opt/apps/cassandra/.cassandra_ssl

# on ALL nodes (including the one that generated the keys), deploy the
# keystore and truststore to the Cassandra conf directory
cp /opt/apps/cassandra/.cassandra_ssl/cassandra.keystore   $CASSANDRA_HOME/conf/.keystore
cp /opt/apps/cassandra/.cassandra_ssl/cassandra.truststore $CASSANDRA_HOME/conf/.truststore
```

## Step 3 - Cassandra YAML

1. Edit the `cassandra.yaml` file with the appropriate passwords and certification files.
2. Execute the following commands as a Cassandra user on all the Cassandra nodes.
   > Replace the password in the following command with the one you have set earlier. 

```bash
sed -i "s@internode_encryption: none@internode_encryption: all@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@key_password: cassandra@key_password: changeit@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@keystore: conf/.keystore*@keystore: $CASSANDRA_HOME/conf/.keystore@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@# truststore:@truststore:@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@truststore: conf/.truststore*@truststore: $CASSANDRA_HOME/conf/.truststore@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@# protocol: TLS*@protocol: TLS@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@keystore_password: cassandra@keystore_password: changeit@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@# truststore: conf/.truststore*@truststore: $CASSANDRA_HOME/conf/.truststore@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@# truststore_password: cassandra*@truststore_password: changeit@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@truststore_password: cassandra*@truststore_password: changeit@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@# protocol: TLS*@protocol: TLS@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@# require_client_auth: .*@require_client_auth: false@g" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@# store_type: JKS@store_type: JKS@g" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i '0,/enabled: false/! {0,/enabled: false/ s/enabled: false/enabled: true/}' $CASSANDRA_HOME/conf/cassandra.yaml
sed -i -e 's/# \(.*native_transport_port_ssl:.*\)/\1/g' $CASSANDRA_HOME/conf/cassandra.yaml
sed -i '1,/cdc_enabled: false/ {0,/cdc_enabled: false/ s/cdc_enabled: false/cdc_enabled: true/}' $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@back_pressure_enabled: .*@back_pressure_enabled: true@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i "s@native_transport_port: .*@native_transport_port: 9142@" $CASSANDRA_HOME/conf/cassandra.yaml
sed -i -e 's/# \(.*native_transport_port_ssl:.*\)/\1/g' $CASSANDRA_HOME/conf/cassandra.yaml
```

3. Restart the Cassandra service on each node: ```cassandra```


## Step 4 - Cassandra CQLSHRC
1. Edit the `.cassandra/cqlshrc` file using the appropriate passwords and certification files created earlier.
2. Execute this as a Cassandra user on all Cassandra nodes. 
> Replace the key and certificate file names with the files created earlier.

   ```bash
   export SSL_DIR=/opt/apps/cassandra/.cassandra_ssl
   export CLUSTER_NAME=${CLUSTER_NAME:-Cassandra}
   mkdir -p ~/.cassandra
   cp $CASSANDRA_HOME/conf/cqlshrc.sample ~/.cassandra/cqlshrc

   # cqlsh verifies the node's (server) certificate against 'certfile'. The node presents the
   # CLUSTER certificate, so convert it from DER (keytool output) to PEM for cqlsh to trust it.
   openssl x509 -inform der -in $SSL_DIR/CLUSTER_${CLUSTER_NAME}_PUBLIC.cer -out $SSL_DIR/${CLUSTER_NAME}_CLUSTER.cer.pem

   sed -i "s@\;\[ssl\]@\[ssl\]@" ~/.cassandra/cqlshrc
   sed -i '/^\[csv]/i factory = cqlshlib.ssl.ssl_transport_factory' ~/.cassandra/cqlshrc
   sed -i "s@; certfile = .*@certfile = $SSL_DIR/${CLUSTER_NAME}_CLUSTER.cer.pem@" ~/.cassandra/cqlshrc
   sed -i "s@;validate = true@validate = true@" ~/.cassandra/cqlshrc
   sed -i "105iversion = SSLv23" ~/.cassandra/cqlshrc
   sed -i "s@;userkey = .*@userkey = $SSL_DIR/${CLUSTER_NAME}_CLIENT.key.pem@" ~/.cassandra/cqlshrc
   sed -i "s@;usercert = .*@usercert = $SSL_DIR/${CLUSTER_NAME}_CLIENT.cer.pem@" ~/.cassandra/cqlshrc
   sed -i "s@port = .*@port = 9142@" ~/.cassandra/cqlshrc
   sed -i "s@hostname = .*@hostname = $(hostname -I |awk {'print $1'})@" ~/.cassandra/cqlshrc
   ```
3. Check your TLS configuration by connecting to Cassandra. At this point the only account is the bootstrap superuser `cassandra` (the `k2admin` user is created in Step 5), so connect with:
   ```bash
   cqlsh -u cassandra -p cassandra --ssl
   ```
   After completing Step 5, connect with the new superuser instead: `cqlsh -u k2admin -p changeit --ssl`

## Step 5 - Disable the default `cassandra` superuser

Cassandra's default superuser is `cassandra`, and it must be disabled before deployment to production. Before doing so, you need to create new **superusers**, one for SYSDBA, and one that will be used for connecting Fabric to Cassandra.

1. Connect to one of the Cassandra nodes' consoles, and create two new **superusers**

   ~~~bash
   echo "create user k2admin with password 'changeit' superuser;" | cqlsh -u cassandra -p cassandra $(hostname -i) 9142 --ssl
   echo "create user k2sysdba with password '3ptBF9eMSsyLrXr3' superuser;" | cqlsh -u cassandra -p cassandra $(hostname -i) 9142 --ssl
   ~~~

2. Drop the `cassandra` user

   ~~~bash
   echo "drop role cassandra;" | cqlsh -u k2sysdba -p 3ptBF9eMSsyLrXr3 $(hostname -i) 9142 --ssl
   ~~~

   

[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/13_creating_a_self_signed_certificate.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/05_connect_fabric_to_cassandra_with_tls.md)
