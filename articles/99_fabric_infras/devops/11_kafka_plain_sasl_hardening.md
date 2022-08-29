# Kafka Hardening PLAIN_SASL

The following steps are used to harden the Kafka server in a Fabric cluster.

## Step 1 - Shut Down Services

Check that the following services are switched off:

- Kafka
- ZooKeeper
- Fabric

```shell
# stop kafka
~/kafka/bin/kafka-server-stop -daemon ~/kafka/server.properties
# stop zookeeper
~/kafka/bin/zookeeper-server-stop -daemon ~/kafka/zookeeper.properties
```

## Step 2. Keys Generation

1. [Download](https://owncloud-bkp2.s3.amazonaws.com/adminoc/Utils/Hardening/secure_kafka.sh) and run the secure_kafka.sh script to generate self-signed keys and certificates.

2. Run the following commands on a single Kafka node only:

   ```bash
   cd $K2_HOME
   chmod +x secure_kafka.sh
   # if openssl is not installed - login with root
   yum install openssl
   ```

3. Edit the secure_Kafka.sh script or execute it using password parameters:

   ```bash
   ./secure_kafka.sh Q1w2e3r4t5
   ```

The following output is generated:

   ```bash
   Generating a 2048 bit RSA private key
   ......................+++
   ................................................................................+++
   writing new private key to '/opt/apps/kafka/.kafka_ssl/ca-key.key'
   -----
   Subject Attribute S has no known NID, skipped

   Warning:
   The JKS Keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore /opt/apps/kafka/.kafka_ssl/kafka.server.keystore.jks -destkeystore /opt/apps/kafka/.kafka_ssl/kafka.server.keystore.jks -deststoretype pkcs12".

   Warning:
   The JKS Keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore /opt/apps/kafka/.kafka_ssl/kafka.server.keystore.jks -destkeystore /opt/apps/kafka/.kafka_ssl/kafka.server.keystore.jks -deststoretype pkcs12".
   Signature ok
   subject=/C=IL/ST=IL/L=Israel/O=K2VIEW/OU=K2VIEW/CN=kafka
   Getting CA Private Key
   Certificate was added to keystore

   Warning:
   The JKS Keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore /opt/apps/kafka/.kafka_ssl/kafka.server.keystore.jks -destkeystore /opt/apps/kafka/.kafka_ssl/kafka.server.keystore.jks -deststoretype pkcs12".
   Certificate reply was installed in keystore

   Warning:
   The JKS Keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore /opt/apps/kafka/.kafka_ssl/kafka.server.keystore.jks -destkeystore /opt/apps/kafka/.kafka_ssl/kafka.server.keystore.jks -deststoretype pkcs12".
   Certificate was added to keystore
   
   Warning:
   The JKS Keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore /opt/apps/kafka/.kafka_ssl/kafka.client.keystore.jks -destkeystore /opt/apps/kafka/.kafka_ssl/kafka.client.keystore.jks -deststoretype pkcs12".

   Warning:
   The JKS Keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore /opt/apps/kafka/.kafka_ssl/kafka.client.keystore.jks -destkeystore /opt/apps/kafka/.kafka_ssl/kafka.client.keystore.jks -deststoretype pkcs12".
   Signature ok
   subject=/C=IL/ST=Il/L=Israel/O=K2VIEW/OU=K2VIEW/CN=kafka
   Getting CA Private Key
   Certificate was added to keystore

   Warning:
   The JKS Keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore /opt/apps/kafka/.kafka_ssl/kafka.client.keystore.jks -destkeystore /opt/apps/kafka/.kafka_ssl/kafka.client.keystore.jks -deststoretype pkcs12".
   Certificate reply was installed in keystore

   Warning:
   The JKS Keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore /opt/apps/kafka/.kafka_ssl/kafka.client.keystore.jks -destkeystore /opt/apps/kafka/.kafka_ssl/kafka.client.keystore.jks -deststoretype pkcs12".
   Certificate was added to keystore

   ```

## Step 3 - Replicate to All Nodes

The following 10 files are generated in the $K2_HOME/.kafka_ssl directory:

- ca-crt.crt
- ca-key.key
- kafka.client.csr
- kafka.client.keystore.jks
- kafka-client-signed.crt
- kafka.client.truststore.jks
- kafka.server.csr
- kafka.server.keystore.jks
- kafka-server-signed.crt
- kafka.server.truststore.jks

Tar and copy them to all Kafka and Fabric / IIDFinder nodes in the cluster as shown below:

Create a tarball file:

``` bash
tar -czvf Kafka_keyz.tar.gz -C $K2_HOME/.kafka_ssl .
```

Copy to other Kafka nodes and Fabric:

``` bash
scp Kafka_keyz.tar.gz kafka@10.10.10.10:/opt/apps/kafka/
```

If you are working with a Docker installation, use the following commands to copy between running containers:

```bash
docker cp kafka:/opt/apps/kafka/Kafka_keyz.tar.gz ./
docker cp Kafka_keyz.tar.gz fabric:/usr/local/k2view/
```

On the Fabric and other Kafka nodes, use the following to extract:

```bash
mkdir -p $K2_HOME/.kafka_ssl && tar -zxvf Kafka_keyz.tar.gz -C $K2_HOME/.kafka_ssl
```

# Zookeeper Configuration

Notes:

- This configuration must be applied to every node in the cluster.
- Since ZooKeeper does not support SSL authentication, MD5 authentication (username and password) is used.

### Step 1 - SASL Authentication

```bash
echo "authProvider.1=org.apache.zookeeper.server.auth.SASLAuthenticationProvider" >> $CONFLUENT_HOME/zookeeper.properties
```

### Step 2 - Create zookeeper_jaas.conf

```bash
echo \
'Server {
        org.apache.zookeeper.server.auth.DigestLoginModule required
        user_super="admin-secret"
        user_kafka="kafka-secret";
 };

Client {
    org.apache.zookeeper.server.auth.DigestLoginModule required
    username="kafka"
    password="kafka-secret";
 };' > $CONFLUENT_HOME/zookeeper_jaas.conf
```

### Step 3 - Start the ZooKeeper Service

When starting ZooKeeper, make sure the following command is invoked:

```bash
export KAFKA_OPTS="-Djava.security.auth.login.config=$CONFLUENT_HOME/zookeeper_jaas.conf" && ~/kafka/bin/zookeeper-server-start -daemon ~/kafka/zookeeper.properties
```

The ZooKeeper daemon starts.

# Kafka Server Configuration

:clipboard: Note that the following steps must be applied for each node in the cluster.

### Step 1 - SSL Authentication

Define the 2-way SSL authentication between the Kafka server and clients:

```bash
sed -i "s@listeners=.*@listeners=SASL_SSL://$(hostname -I |awk {'print $1'}):9093@"  $CONFLUENT_HOME/server.properties 
sed -i "s@zookeeper.connect=.*@zookeeper.connect=$(hostname -I |awk {'print $1'}):2181@"  $CONFLUENT_HOME/server.properties
sed -i "s@advertised.listeners=.*@advertised.listeners=SASL_SSL:\/\/$(hostname -I |awk {'print $1'}):9093@" $CONFLUENT_HOME/server.properties
sed -i "32i security.inter.broker.protocol=SASL_SSL" $CONFLUENT_HOME/server.properties
sed -i "33i ssl.client.auth=required" $CONFLUENT_HOME/server.properties
sed -i "34i sasl.enabled.mechanisms=PLAIN" $CONFLUENT_HOME/server.properties
sed -i "35i sasl.mechanism.inter.broker.protocol=PLAIN" $CONFLUENT_HOME/server.properties
sed -i 's/^advertised.listeners/#&/' $CONFLUENT_HOME/server.properties
sed -i 's/^advertised.host.name/#&/' $CONFLUENT_HOME/server.properties
sed -i "60i ssl.truststore.location=$K2_HOME/.kafka_ssl/kafka.server.truststore.jks" $CONFLUENT_HOME/server.properties
sed -i "61i ssl.truststore.password=Q1w2e3r4t5" $CONFLUENT_HOME/server.properties
sed -i "62i ssl.keystore.location=$K2_HOME/.kafka_ssl/kafka.server.keystore.jks" $CONFLUENT_HOME/server.properties
sed -i "63i ssl.keystore.password=Q1w2e3r4t5" $CONFLUENT_HOME/server.properties
sed -i "64i ssl.key.password=Q1w2e3r4t5" $CONFLUENT_HOME/server.properties
sed -i "65i ssl.endpoint.identification.algorithm=" $CONFLUENT_HOME/server.properties
```

### Step 2 - kafka_server_jaas.conf

1. Create the kafka_server_jaas.conf file:

   ```bash
   echo \
   'KafkaServer {
       org.apache.kafka.common.security.plain.PlainLoginModule required
       username="admin"
       password="admin-secret"
       user_admin="admin-secret";
    };
   
   Client {
       org.apache.zookeeper.server.auth.DigestLoginModule required
       username="kafka"
       password="kafka-secret";
    };' > $CONFLUENT_HOME/kafka_server_jaas.conf
   ```

### Step 3 - producer.properties

1. Create the producer.properties file:

```bash
echo \
'sasl.mechanism=PLAIN
security.protocol=SASL_SSL
sasl.jaas.config=org.apache.kafka.common.security.plain.PlainLoginModule required \
   username="admin" \
   password="admin-secret";
ssl.truststore.location=/home/kafka/.kafka_ssl/kafka.client.truststore.jks
ssl.truststore.password=Q1w2e3r4t5' > $CONFLUENT_HOME/client_security.properties
```

### Step 4 - Start the Kafka Server

When starting Kafka, make sure the following command is invoked:

```bash
export KAFKA_OPTS="-Djava.security.auth.login.config=$CONFLUENT_HOME/kafka_server_jaas.conf" && ~/kafka/bin/kafka-server-start -daemon ~/kafka/server.properties
```

The Kafka daemon starts.

### Step 5 - Create Topic

1. For creating topics, use the following command: (in the following example, rf=1)

 ```bash
 $CONFLUENT_HOME/bin/kafka-topics --create  --zookeeper localhost:2181   --replication-factor 1  --partitions 1  --topic <topic-name>
 ```

### Step 6 - Create Kafka Producer Client

1. For creating the Kafka producer client, use the following command:

```bash
$CONFLUENT_HOME/bin/kafka-console-producer --broker-list <broker-ip>:9093 --topic <topic-name> --producer.config ~/kafka/producer.properties
```

### Step 7 - Create Kafka Consumer Clienr

1. For creating the Kafka consumer client, use the following command:

```bash
$CONFLUENT_HOME/bin/kafka-console-consumer --bootstrap-server <bootstrap-ip>:9093 --topic <topic-name> --consumer.config ~/producer.properties
```

[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/devops/07_fabric_kafkaSSL_support.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/devops/08_oracleGG_hardening.md)
