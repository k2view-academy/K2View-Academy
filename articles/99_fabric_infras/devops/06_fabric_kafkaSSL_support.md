# Fabric and Fabric Tools Configuration  

## Setup Fabric with Kafka connection SSL support

- Make sure Fabric is stopped by executing the following command:

```k2fabric stop```

### Step 1: Connect with SSL

- Configure Fabric for connection to Kafka with SSL support

```
sed -i s/#SSL_ENABLED=.*/SSL_ENABLED=true/g $K2_HOME/config/config.ini
sed -i s/#SSL_ENABLED=.*/SSL_ENABLED=true/g $K2_HOME/config/config.ini
sed -i s/#SECURITY_PROTOCOL=.*/SECURITY_PROTOCOL=SSL/g $K2_HOME/config/config.ini
sed -i "s@#TRUSTSTORE_LOCATION=.*@TRUSTSTORE_LOCATION=$K2_HOME/.kafka_ssl/kafka.client.truststore.jks@" $K2_HOME/config/config.ini
sed -i s/#TRUSTSTORE_PASSWORD=.*/TRUSTSTORE_PASSWORD=Q1w2e3r4t5/g $K2_HOME/config/config.ini
sed -i "s@#KEYSTORE_LOCATION=.*@KEYSTORE_LOCATION=$K2_HOME/.kafka_ssl/kafka.client.keystore.jks@" $K2_HOME/config/config.ini
sed -i s/#KEYSTORE_PASSWORD=.*/KEYSTORE_PASSWORD=Q1w2e3r4t5/g $K2_HOME/config/config.ini
sed -i s/#KEY_PASSWORD=.*/KEY_PASSWORD=Q1w2e3r4t5/g $K2_HOME/config/config.ini
sed -i s@#ENDPOINT_IDENTIFICATION_ALGORITHM=@ENDPOINT_IDENTIFICATION_ALGORITHM=@g $K2_HOME/config/config.ini
```

### Step 2: Start Fabric

```k2fabric start```

 
## Setup IIDFinder with Kafka connection SSL support

- Make sure IIDFinder is stopped

```$K2_HOME/fabric/scripts/iid_finder_stop.sh```


### Step 1 - On Fabric 6.x and higher versions:

- Configure IIDFinder for connection to Kafka with SSL support per node (assumption is that IIDFinder is already configured with basic setup to get connected to Kafka in non SSL/TLS Mode):

```
sed -i s@#SSL_ENABLED=.*@SSL_ENABLED=true@g $K2_HOME/config/iifConfig.ini
sed -i s@#SECURITY_PROTOCOL=.*@SECURITY_PROTOCOL=SSL@g $K2_HOME/config/iifConfig.ini
sed -i s@#TRUSTSTORE_LOCATION=.*@TRUSTSTORE_LOCATION=$K2_HOME/.kafka_ssl/kafka.client.truststore.jks@g $K2_HOME/config/iifConfig.ini
sed -i s@#TRUSTSTORE_PASSWORD=.*@TRUSTSTORE_PASSWORD=Q1w2e3r4t5@g $K2_HOME/config/iifConfig.ini
sed -i s@#KEYSTORE_LOCATION=.*@KEYSTORE_LOCATION=$K2_HOME/.kafka_ssl/kafka.client.keystore.jks@g $K2_HOME/config/iifConfig.ini
sed -i s@#KEYSTORE_PASSWORD=.*@KEYSTORE_PASSWORD=Q1w2e3r4t5@g $K2_HOME/config/iifConfig.ini
sed -i s@#KEY_PASSWORD=.*@KEY_PASSWORD=Q1w2e3r4t5@g $K2_HOME/config/iifConfig.ini
sed -i s@#ENDPOINT_IDENTIFICATION_ALGORITHM=@ENDPOINT_IDENTIFICATION_ALGORITHM=@g $K2_HOME/config/iifConfig.ini
```

### Step 2 - Start IIDFinder

- Run the following command:
```$K2_HOME/fabric/scripts/iid_finder.sh```


## Testing Fabric and IIDFinder SSL/TLS with Kafka

- Assumption 1: Usage of Oracle Golden Gate for Big Data (OGG - BD) 
- Assumption 2: Knowledge to simulate message creation in Kafka with format similat to message pushed by OGG BD replicat component

### Step 1 - 	Fabric, IIDFinder, Kafka and OGG BD systems are up and running in SSL/TLS mode

### Step 2 - 	Create update in OGG so new message will be pushed to Kafka

### Step 3 - 	IDFinder will authenticate against Kafka and will pull the new message generated

### Step 4 - 	Message will be written to cache instance {under keyspace k2staging tables}

### Step 5 - 	From Fabric console run the ```GET``` command on to the instance to validate the data was received and written as expected

Note:
In case IIDFinder was not set with SSL/TLS setup as required, the data in cache (k2staging) will not be written as expected
