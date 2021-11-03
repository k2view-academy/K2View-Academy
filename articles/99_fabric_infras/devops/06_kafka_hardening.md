# Kafka Hardening (TLS mode)

The following steps are used to harden the Kafka server in a Fabric cluster.

## Step 1 - Shut Down Services	

Check that the following services are switched off:
- Kafka
- ZooKeeper
- Fabric

## Step 2. Keys Generation

1. [Download](https://owncloud-bkp2.s3.amazonaws.com/adminoc/Utils/Hardening/secure_kafka.sh) and run the secure_kafka.sh script to generate self-signed keys and certificates.

2. Run the following commands on a single Kafka node only:
```bash
cd $K2_HOME
chmod +x secure_kafka.sh
```
3. Edit the secure_Kafka.sh script or execute it using password parameters: 

```
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

creat tar file
``` bash
tar -czvf Kafka_keyz.tar.gz -C $K2_HOME/.kafka_ssl
```

copy to fabric example
``` bash
scp Kafka_keyz.tar.gz kafka@10.10.10.10:/opt/apps/kafka/
```

on the fabric use the following to extract 
```bash
mkdir -p $K2_HOME/.kafka_ssl && tar -zxvf Kafka_keyz.tar.gz -C $K2_HOME/.kafka_ssl
```

# Zookeeper Configuration

Notes: 
- These configuration must be applied to every node in the cluster
- Since ZooKeeper does not support SSL authentication, MD5 authentication (username and password) is used.

## Step 1 - SASL Authentication

```bash
echo "authProvider.1=org.apache.zookeeper.server.auth.SASLAuthenticationProvider" >> $CONFLUENT_HOME/zookeeper.properties
```


## Step 2 - zookeeper_jaas.conf

Edit the newly created file zookeeper_jaas.conf

```bash
vi $CONFLUENT_HOME/zookeeper_jaas.conf
```


## Step 3 - Copy the Following Data into zookeeper_jaas.conf

```yaml
Server {
org.apache.zookeeper.server.auth.DigestLoginModule required
user_super="kafka"
user_kafka="kafka";
};

Client {
	org.apache.zookeeper.server.auth.DigestLoginModule required
	username="kafka"
	password="kafka";
};
```

## Step 4 - Start the ZooKeeper Service

When starting ZooKeeper make sure the following command is invoked: 

```bash
export KAFKA_OPTS="-Djava.security.auth.login.config=$CONFLUENT_HOME/zookeeper_jaas.conf" && ~/kafka/bin/zookeeper-server-start -daemon ~/kafka/zookeeper.properties
```

The ZooKeeper daemon starts.


# Kafka Server Configuration
Note that the following steps must be applied for each node in cluster.

## Step 1 - SSL Authentication
Define the 2-way SSL authentication between the Kafka server and clients:

```bash
sed -i "s@listeners=.*@listeners=SSL://$(hostname -I |awk {'print $1'}):9093@"  $CONFLUENT_HOME/server.properties 
sed -i "s@advertised.listeners=.*@advertised.listeners=PLAINTEXT:\/\/$(hostname -I |awk {'print $1'}):9093@" $CONFLUENT_HOME/server.properties
sed -i "32isecurity.inter.broker.protocol=SSL" $CONFLUENT_HOME/server.properties
sed -i "33issl.client.auth=required" $CONFLUENT_HOME/server.properties
sed -i 's/^advertised.listeners/#&/' $CONFLUENT_HOME/server.properties
sed -i 's/^advertised.host.name/#&/' $CONFLUENT_HOME/server.properties
sed -i "60issl.truststore.location=$K2_HOME/.kafka_ssl/kafka.server.truststore.jks" $CONFLUENT_HOME/server.properties
sed -i "61issl.truststore.password=Q1w2e3r4t5" $CONFLUENT_HOME/server.properties
sed -i "62i ssl.keystore.location=$K2_HOME/.kafka_ssl/kafka.server.keystore.jks" $CONFLUENT_HOME/server.properties
sed -i "63issl.keystore.password=Q1w2e3r4t5" $CONFLUENT_HOME/server.properties
sed -i "64issl.key.password=Q1w2e3r4t5" $CONFLUENT_HOME/server.properties
sed -i "65issl.endpoint.identification.algorithm=" $CONFLUENT_HOME/server.properties
```


## Step 2 - kafka_server_jaas.conf

1. Edit the new kafka_server_jaas.conf file:

```bash
vi $CONFLUENT_HOME/kafka_server_jaas.conf
```

2. Copy the following data into the kafka_server_jaas.conf:

```json
KafkaServer {
	org.apache.kafka.common.security.plain.PlainLoginModule required
	username="kafka"
	password="kafka"
	user_kafkabroker="kafka"
	user_client1="kafka";
};

Client {
	org.apache.zookeeper.server.auth.DigestLoginModule required
	username="kafka"
	password="kafka";
};

```
## Step 3 - Start the Kafka Server

When starting Kafka make sure the following command is invoked:

```bash
export KAFKA_OPTS="-Djava.security.auth.login.config=$CONFLUENT_HOME/kafka_server_jaas.conf" && ~/kafka/bin/kafka-server-start -daemon ~/kafka/server.properties
```

The Kafka daemon starts.



[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/devops/05_connect_fabric_to_cassandra_with_tls.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/devops/07_fabric_kafkaSSL_support.md)

