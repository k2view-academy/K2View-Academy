# Connect Fabric to Kafka in TLS mode

Execute the following command to stop Fabric:

```k2fabric stop```

### Step 1: Connect with SSL

Configure Fabric connections to Kafka with SSL support.

~~~bash
sed -i s/#SSL_ENABLED=.*/SSL_ENABLED=true/g $K2_HOME/config/config.ini
sed -i s/#SSL_ENABLED=.*/SSL_ENABLED=true/g $K2_HOME/config/config.ini
sed -i s/#SECURITY_PROTOCOL=.*/SECURITY_PROTOCOL=SSL/g $K2_HOME/config/config.ini
sed -i "s@#TRUSTSTORE_LOCATION=.*@TRUSTSTORE_LOCATION=$K2_HOME/.kafka_ssl/kafka.client.truststore.jks@" $K2_HOME/config/config.ini
sed -i s/#TRUSTSTORE_PASSWORD=.*/TRUSTSTORE_PASSWORD=Q1w2e3r4t5/g $K2_HOME/config/config.ini
sed -i "s@#KEYSTORE_LOCATION=.*@KEYSTORE_LOCATION=$K2_HOME/.kafka_ssl/kafka.client.keystore.jks@" $K2_HOME/config/config.ini
sed -i s/#KEYSTORE_PASSWORD=.*/KEYSTORE_PASSWORD=Q1w2e3r4t5/g $K2_HOME/config/config.ini
sed -i s/#KEY_PASSWORD=.*/KEY_PASSWORD=Q1w2e3r4t5/g $K2_HOME/config/config.ini
sed -i s@#ENDPOINT_IDENTIFICATION_ALGORITHM=@ENDPOINT_IDENTIFICATION_ALGORITHM=@g $K2_HOME/config/config.ini
~~~

### Step 2: Start Fabric

`k2fabric start`


## Setup the IIDFinder to Support Kafka SSL Connections

Make sure the IIDFinder has stopped.

`$K2_HOME/fabric/scripts/iid_finder_stop.sh`


### Step 1 - On Fabric 6.x and Higher Versions 

Configure the IIDFinder to connect to Kafka with SSL support per node. The assumption is that the IIDFinder is already configured with basic connections to Kafka in non SSL / TLS modes:

~~~bash
sed -i s@#SSL_ENABLED=.*@SSL_ENABLED=true@g $K2_HOME/config/iifConfig.ini
sed -i s@#SECURITY_PROTOCOL=.*@SECURITY_PROTOCOL=SSL@g $K2_HOME/config/iifConfig.ini
sed -i s@#TRUSTSTORE_LOCATION=.*@TRUSTSTORE_LOCATION=$K2_HOME/.kafka_ssl/kafka.client.truststore.jks@g $K2_HOME/config/iifConfig.ini
sed -i s@#TRUSTSTORE_PASSWORD=.*@TRUSTSTORE_PASSWORD=Q1w2e3r4t5@g $K2_HOME/config/iifConfig.ini
sed -i s@#KEYSTORE_LOCATION=.*@KEYSTORE_LOCATION=$K2_HOME/.kafka_ssl/kafka.client.keystore.jks@g $K2_HOME/config/iifConfig.ini
sed -i s@#KEYSTORE_PASSWORD=.*@KEYSTORE_PASSWORD=Q1w2e3r4t5@g $K2_HOME/config/iifConfig.ini
sed -i s@#KEY_PASSWORD=.*@KEY_PASSWORD=Q1w2e3r4t5@g $K2_HOME/config/iifConfig.ini
sed -i s@#ENDPOINT_IDENTIFICATION_ALGORITHM=@ENDPOINT_IDENTIFICATION_ALGORITHM=@g $K2_HOME/config/iifConfig.ini
~~~

### Step 2 - Start the IIDFinder

Run the following command:
`$K2_HOME/fabric/scripts/iid_finder.sh`


## Testing Fabric and the IIDFinder SSL / TLS with Kafka

Assumption 1: Usage of Oracle Golden Gate for Big Data (OGG - BD). 

Assumption 2: Know how to simulate message creation in Kafka in a format similar to a message pushed by the OGG BD replicate component

1. Check the Fabric, IIDFinder, Kafka and OGG BD systems are up and running in SSL/TLS mode.
2. Create an update in OGG to push a new message to Kafka.
3. The IDFinder authenticates with Kafka and pulls the new message.
4. The message is written to a cache instance under the keyspace k2staging tables.
5. In the Fabric console, run the ```GET``` command on the instance to validate the data has been received and written as expected.


**Note:**
If the IIDFinder has not been configured with the SSL/TLS setup as required, the data in the k2staging cache is not be written as expected.




[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/devops/06_kafka_hardening.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/devops/11_kafka_plain_sasl_hardening.md)
