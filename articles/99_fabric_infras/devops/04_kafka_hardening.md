# Kafka Hardening (SSL mode)

The following steps will ensure the hardening of Kafka server in a Fabric cluster.

## Step 1 - Shut down services	

Ensure the following services are switched off:
- Kafka
- ZooKeeper
- Fabric

## Step 2.	Keys Generation

- [Download](https://owncloud_bkp.s3.amazonaws.com/adminoc/Utils/Hardening/secure_kafka.sh) and run the following script (secure_kafka.sh) to generate self-signed keys and certificates.

- Run the following commands on a single Kafka node only:
```
cd $K2_HOME
chmod +x secure_kafka.sh
```
- Edit the secure_Kafka.sh or execute with password parameters: 

```
./secure_kafka.sh Q1w2e3r4t5
```

The following output will be generated:

```
Generating a 2048 bit RSA private key
......................+++
................................................................................+++
writing new private key to '/opt/apps/kafka/.kafka_ssl/ca-key.key'
-----
Subject Attribute S has no known NID, skipped

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore /opt/apps/kafka/.kafka_ssl/kafka.server.keystore.jks -destkeystore /opt/apps/kafka/.kafka_ssl/kafka.server.keystore.jks -deststoretype pkcs12".

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore /opt/apps/kafka/.kafka_ssl/kafka.server.keystore.jks -destkeystore /opt/apps/kafka/.kafka_ssl/kafka.server.keystore.jks -deststoretype pkcs12".
Signature ok
subject=/C=IL/ST=IL/L=Israel/O=K2VIEW/OU=K2VIEW/CN=kafka
Getting CA Private Key
Certificate was added to keystore

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore /opt/apps/kafka/.kafka_ssl/kafka.server.keystore.jks -destkeystore /opt/apps/kafka/.kafka_ssl/kafka.server.keystore.jks -deststoretype pkcs12".
Certificate reply was installed in keystore

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore /opt/apps/kafka/.kafka_ssl/kafka.server.keystore.jks -destkeystore /opt/apps/kafka/.kafka_ssl/kafka.server.keystore.jks -deststoretype pkcs12".
Certificate was added to keystore
 
Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore /opt/apps/kafka/.kafka_ssl/kafka.client.keystore.jks -destkeystore /opt/apps/kafka/.kafka_ssl/kafka.client.keystore.jks -deststoretype pkcs12".

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore /opt/apps/kafka/.kafka_ssl/kafka.client.keystore.jks -destkeystore /opt/apps/kafka/.kafka_ssl/kafka.client.keystore.jks -deststoretype pkcs12".
Signature ok
subject=/C=IL/ST=Il/L=Israel/O=K2VIEW/OU=K2VIEW/CN=kafka
Getting CA Private Key
Certificate was added to keystore

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore /opt/apps/kafka/.kafka_ssl/kafka.client.keystore.jks -destkeystore /opt/apps/kafka/.kafka_ssl/kafka.client.keystore.jks -deststoretype pkcs12".
Certificate reply was installed in keystore

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore /opt/apps/kafka/.kafka_ssl/kafka.client.keystore.jks -destkeystore /opt/apps/kafka/.kafka_ssl/kafka.client.keystore.jks -deststoretype pkcs12".
Certificate was added to keystore

```


## Step 3 - Replicate to all nodes

The 10 following files have been generated in the $K2_HOME/.kafka_ssl directory:

```
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
```

-	Tar & Copy them to all nodes in the cluster (Kafka and Fabric/IIDFinder nodes) as shown below:

``` 
tar -czvf Kafka_keyz.tar.gz -C $K2_HOME/.kafka_ssl
scp Kafka_keyz.tar.gz kafka@10.10.10.10:/opt/apps/kafka/
mkdir -p $K2_HOME/.kafka_ssl && tar -zxvf Kafka_keyz.tar.gz -C $K2_HOME/.kafka_ssl
## for Fabric (after file copied):
mkdir -p $K2_HOME/.kafka_ssl && tar -zxvf Kafka_keyz.tar.gz -C $K2_HOME/.kafka_ssl
```


# Zookeeper configuration

Notes: 
- Required to be applied over every node in cluster
- ZooKeeper does not support SSL authentication so MD5 authentication (username and password) is used

## Step 1 - SASL Authentication

```echo "authProvider.1=org.apache.zookeeper.server.auth.SASLAuthenticationProvider" >> $CONFLUENT_HOME/zookeeper.properties```


## Step 2 - zookeeper_jaas.conf

- Edit the newly created file zookeeper_jaas.conf

```vi $CONFLUENT_HOME/zookeeper_jaas.conf```


## Step 3 - Copy the following data into zookeeper_jaas.conf

```
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

## Step 4 - Start ZooKeeper service

- Upon starting ZooKeeper make sure the following command is being invoked: 
``` export KAFKA_OPTS="-Djava.security.auth.login.config=$CONFLUENT_HOME/zookeeper_jaas.conf" && ~/kafka/bin/zookeeper-server-start -daemon ~/kafka/zookeeper.properties ```

The ZooKeeper daemon will also have been started.


# Kafka Server Configuration
Note that the following steps must be applied over each node in cluster.

## Step 1 - SSL authentication
- Define the 2-way SSL authentication between Kafka server and clients:

```
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

- Edit new created file kafka_server_jaas.conf:

```
vi $CONFLUENT_HOME/kafka_server_jaas.conf
```

- Copy the following data into kafka_server_jaas.conf:

```
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
## Step 3 - Start Kafka server

- Upon starting Kafka make sure the following command is being invoked:
```export KAFKA_OPTS="-Djava.security.auth.login.config=$CONFLUENT_HOME/kafka_server_jaas.conf" && ~/kafka/bin/kafka-server-start -daemon ~/kafka/server.properties```

The Kafka daemon will also have been started.



[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/devops/03_fabric_and_cassandra_hardening.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/devops/05_oracleGG_hardening.md)

