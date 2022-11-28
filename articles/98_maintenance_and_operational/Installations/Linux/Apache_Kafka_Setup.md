# Apache Kafka Setup
The supplied Cassandra package is preconfigured to run as a single node with fabric on the same host. In order to achive this, the following settings are predefined:

* advertised.listeners = 127.0.0.1

* advertised.host.name = 127.0.0.1

* replication.factor = 1

### Load the Package 

1. Download the package from: [here](https://download.k2view.com/index.php/s/tFnDRJEUyHiXPYL).

2. Log in to the Linux server as the user "Kafka" and copy the package to the home directory.

3. Untar the package (the package name varies according to the version) as follows:

   ~~~bash
   tar -zxvf k2view_Confluent_7.xxx.tar.gz && bash -l
   ~~~


4. Start Kafka and Zookeeper:

   ~~~bash
   $K2_HOME/kafka/bin/zookeeper-server-start -daemon $K2_HOME/kafka/zookeeper.properties
   sleep 10
   $K2_HOME/kafka/bin/kafka-server-start -daemon $K2_HOME/kafka/server.properties
   ~~~

5. Verify that the Kafka and Zookeeper are running:

   ~~~bash
   $CONFLUENT_HOME/bin/zookeeper-shell localhost:2181 <<< "ls /brokers/ids"
   ~~~


##
