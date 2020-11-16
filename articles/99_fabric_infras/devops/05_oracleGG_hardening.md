# Oracle GG (Golden Gate) Big Data setup with Kafka SSL

## Step 1	- Copy Kafka_ssl content

Copy from Kafka node the content of $K2_HOME/.kafka_ssl to local directory on Oracle GG client


## Step 2 - Update Properties	

Update the custom_kafka_producer.properties file to contain the following details at the bottom of the file:

```
bootstrap.servers=(Kafka_server_IP_Address/_Hostname):9093
acks=1
reconnect.backoff.ms=1000
#
value.serializer = org.apache.kafka.common.serialization.ByteArraySerializer
key.serializer = org.apache.kafka.common.serialization.ByteArraySerializer
#
# 100KB per partition
batch.size = 102400
linger.ms = 10000
#
#TLS Security
security.protocol=SSL
ssl.truststore.location=/home/oracle/kafka.client.truststore.jks
ssl.truststore.password=Q1w2e3r4t5 
ssl.keystore.location=/home/oracle/kafka.client.keystore.jks
ssl.keystore.password=Q1w2e3r4t5
ssl.key.password=Q1w2e3r4t5
ssl.endpoint.identification.algorithm=
```

## Step 3 - Restart Service

- Open the Oracle GG Software Command Interface (ggcsi)

- Execute the command: 
```ALTER REPLICAT (replicat_name), BEGIN NOW```

- Update Kafka server.properties file (if not done previously):

```sed -i "65issl.endpoint.identification.algorithm=" $CONFLUENT_HOME/server.properties```

 - Restart Kafka service
 

[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/devops/04_kafka_hardening.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/devops/06_fabric_kafkaSSL_support.md)
