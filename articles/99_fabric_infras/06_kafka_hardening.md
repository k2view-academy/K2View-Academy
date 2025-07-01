# Kafka Custom Hardening

In this section, you configure Kafka with hardening, incorporating your inputs.

Please follow the guidelines to set the correct settings according to your needs.

> **NOTE:** 
If this is your first Kafka setup with customized hardening, proceed to step 3. 
> In case you've had Kafka in the past, without hardening, and now you wish to add a hardening configuration, follow the instructions below:


1. Stop Kafka
~~~bash
$CONFLUENT_HOME/bin/kafka-server-stop -daemon ~/kafka/server.properties
~~~
2. Stop Zookeeper
~~~bash
$CONFLUENT_HOME/bin/zookeeper-server-stop -daemon ~/kafka/zookeeper.properties
~~~
3. Create Kafka certificates

run the following command **on a single Kafka node only**, set the password per your choice:
~~~bash
/opt/apps/kafka/secure_kafka.sh <PASSWORD>
~~~
4. Execute the Kafka setup with hardening flags
~~~bash
/opt/apps/kafka/kafka-setup.sh --ips 10.0.0.1,10.0.0.2,10.0.0.3  --replication_factor 3 [KAFKA_SSL_FLAGS]
~~~


Below are your **KAFKA_SSL_FLAGS** options:

## Choose Keystore password only
~~~bash
--ssl --keystore_password <keystorePWD>
~~~
 > In this case, both the Truststore password and key password inside the keystore will be defined as the keystore password value.

## Choose the Keystore and Truststore values
~~~bash
--ssl --keystore_path </path/to/keystore> --keystore_password <keystorePWD> --truststore_path </path/to/truststore> --truststore_password <truststorePWD>
~~~
 > In this case, the Keystore and Truststore paths are defined differently, where each has a different password.


## Choose all SSL flags 
~~~bash
--ssl --keystore_path </path/to/keystore> --keystore_password <keystorePWD> --truststore_path </path/to/truststore> --truststore_password <truststorePWD> --keypass <keyPWD>
~~~
 > In this case, Keystore and Truststore paths are defined differently, where each has a different password. Additionally, the Key password inside the keystore differs from the keystore password.



***In case you have a cluster, copy the certificate tar.gz file to the whole cluster and repeat the above commands on every node.***


[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/05_connect_fabric_to_cassandra_with_tls.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/07_fabric_kafkaSSL_support.md)
