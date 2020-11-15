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

ca-crt.crt
ca-key.key
kafka.client.csr
kafka.client.keystore.jks
kafka-client-signed.crt
kafka.client.truststore.jks
kafka.server.csr
kafka.server.keystore.jks
kafka-server-signed.crt
kafka.server.truststore.jks

-	Tar & Copy them to all nodes in the cluster (Kafka and Fabric/IIDFinder nodes) as shown below:

``` 
tar -czvf Kafka_keyz.tar.gz -C $K2_HOME/.kafka_ssl
scp Kafka_keyz.tar.gz kafka@10.10.10.10:/opt/apps/kafka/
mkdir -p $K2_HOME/.kafka_ssl && tar -zxvf Kafka_keyz.tar.gz -C $K2_HOME/.kafka_ssl
## for Fabric (after file copied):
mkdir -p $K2_HOME/.kafka_ssl && tar -zxvf Kafka_keyz.tar.gz -C $K2_HOME/.kafka_ssl
```



