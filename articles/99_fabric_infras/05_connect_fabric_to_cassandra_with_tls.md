# Connect Fabric to Cassandra in TLS mode

The following steps connect Fabric to a TLS-hardened Cassandra cluster (see [Cassandra Hardening](/articles/99_fabric_infras/04_cassandra_hardening.md)).

Rather than pointing Fabric's JVM at the Cassandra keystore, the Cassandra **client key/certificate** is imported into Fabric's existing keystore, and the Cassandra **server (cluster) public certificate** is imported into Fabric's truststore.

**Assumptions:**
- Cassandra was hardened per the [Cassandra Hardening](/articles/99_fabric_infras/04_cassandra_hardening.md) guide. Its keys are under `/opt/apps/cassandra/.cassandra_ssl`, with the default cluster name `Cassandra` and password `changeit` (adjust the file names / password below if you changed them).
- `${FABRIC_HOME}` is set on each Fabric node.
- The example password `changeit` is used for the Fabric keystore and truststore — replace it if your stores use a different one.

## Step 1 - Copy the Cassandra keys archive to all Fabric nodes

From one of the Cassandra nodes, copy the `cassandra_keys.tar.gz` archive (created during hardening) to every Fabric node.

~~~bash
# 10.1.0.1 represents the IP address of each Fabric node
scp /opt/apps/cassandra/.cassandra_ssl/cassandra_keys.tar.gz fabric@10.1.0.1:/opt/apps/fabric/
~~~

In case of a Docker installation, copy between the running containers instead:

~~~bash
docker cp cassandra:/opt/apps/cassandra/.cassandra_ssl/cassandra_keys.tar.gz ./
docker cp cassandra_keys.tar.gz fabric:/opt/apps/fabric/
~~~

## Step 2 - Import the key and certificate into the Fabric keystore and truststore

Run the following as the **fabric** user **on each Fabric node**. The archive is unpacked first, then the client key/certificate and the cluster public certificate are imported using Fabric's `certificates.sh` helper script (which wraps `keytool`).

The `FABRIC_KEYSTORE_PATH` / `FABRIC_TRUSTSTORE_PATH` environment variables tell `certificates.sh` which stores to write to. They are set here to the default Fabric stores so that Step 4 applies unchanged.

~~~bash
export FABRIC_KEYSTORE_PATH=${FABRIC_HOME}/config/.keystore
export FABRIC_TRUSTSTORE_PATH=${FABRIC_HOME}/config/.truststore

# Unpack the archive
mkdir -p /opt/apps/fabric/.cassandra_ssl
tar -zxvf /opt/apps/fabric/cassandra_keys.tar.gz -C /opt/apps/fabric/.cassandra_ssl
cd /opt/apps/fabric/.cassandra_ssl

# 1. Package the Cassandra client key + certificate as PKCS12
#    (certificates.sh addkey imports a private key, which keytool can only read from a PKCS12 bundle)
openssl pkcs12 -export -name cassandra_client \
  -in Cassandra_CLIENT.cer.pem -inkey Cassandra_CLIENT.key.pem \
  -out cassandra_client.p12 -passout pass:changeit

# 2. Import the client key/certificate into the Fabric keystore
${FABRIC_HOME}/fabric/scripts/certificates.sh addkey cassandra_client cassandra_client.p12 changeit

# 3. Import the Cassandra server (cluster) public certificate into the Fabric truststore
${FABRIC_HOME}/fabric/scripts/certificates.sh addtrust cassandra_cluster CLUSTER_Cassandra_PUBLIC.cer changeit
~~~

## Step 3 - Enable the TLS connection in config.ini

Update the `default_session` (Cassandra SystemDB) section of `${FABRIC_HOME}/config/config.ini` to enable SSL, set the SSL port, and the credentials of the superuser created during hardening. Fabric's `merge-config.sh` helper sets each key in the named section (creating it if missing), which is safer than a global `sed` because the same key names can appear in other sections.

~~~bash
${FABRIC_HOME}/fabric/scripts/merge-config.sh -i ${FABRIC_HOME}/config/config.ini -s default_session -k SSL -v true
${FABRIC_HOME}/fabric/scripts/merge-config.sh -i ${FABRIC_HOME}/config/config.ini -s default_session -k PORT -v 9142
${FABRIC_HOME}/fabric/scripts/merge-config.sh -i ${FABRIC_HOME}/config/config.ini -s default_session -k USER -v k2admin
${FABRIC_HOME}/fabric/scripts/merge-config.sh -i ${FABRIC_HOME}/config/config.ini -s default_session -k PASSWORD -v changeit
~~~

## Step 4 - (Optional) Set the keystore/truststore paths in jvm.options

By default Fabric uses `${FABRIC_HOME}/config/.keystore` and `${FABRIC_HOME}/config/.truststore` (the stores written to in Step 2), so **no change is required** when the defaults are in use.

Run the following **only if** these options are not already set in `${FABRIC_HOME}/config/jvm.options`, to point them explicitly at the default stores:

~~~bash
sed -i "s@#-Djavax.net.ssl.keyStore=.*@-Djavax.net.ssl.keyStore=${FABRIC_HOME}/config/.keystore@g" ${FABRIC_HOME}/config/jvm.options
sed -i "s@#-Djavax.net.ssl.keyStorePassword=.*@-Djavax.net.ssl.keyStorePassword=changeit@g" ${FABRIC_HOME}/config/jvm.options
sed -i "s@#-Djavax.net.ssl.trustStore=.*@-Djavax.net.ssl.trustStore=${FABRIC_HOME}/config/.truststore@g" ${FABRIC_HOME}/config/jvm.options
sed -i "s@#-Djavax.net.ssl.trustStorePassword=.*@-Djavax.net.ssl.trustStorePassword=changeit@g" ${FABRIC_HOME}/config/jvm.options
~~~

If the `keyStore` / `trustStore` options have instead been customized to a different path, import the key and certificate into those configured stores (re-run Step 2 with `FABRIC_KEYSTORE_PATH` / `FABRIC_TRUSTSTORE_PATH` pointing at those paths).

## Step 5 - Restart Fabric

Restart the Fabric service on each node:

~~~bash
k2fabric stop
k2fabric start
~~~

[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/04_cassandra_hardening.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/06_kafka_hardening.md)
