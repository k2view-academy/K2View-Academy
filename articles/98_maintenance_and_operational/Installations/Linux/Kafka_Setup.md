# Apache Kafka Cluster Setup

The provided Kafka package and setup scripts were designed for either a single node or a multi-node environment.

Follow the setup script configuration for the correct settings, depending on your environment.

The script should be run separately on each node, in the order of designated node numbers. It should not be run simultaneously as this may cause configuration issues.

The number of cluster nodes should be either greater than or equal to the replication factor number.


### Load the Package 

1. Log in to the previously created user that was designated to the Kafka installation.

2. Download the package from [here](https://download.k2view.com/index.php/s/tFnDRJEUyHiXPYL).

3. Log in to the Linux server as the 'Kafka' user and copy the package to the home directory.

4. Untar the package (the package name varies according to the version) as follows:

    ~~~bash
    tar -zxf k2view_Confluent_7.xxx.tar.gz && source .bash_profile
    ~~~

### Set up the Kafka Nodes

**Mandatory required details:**
+ Node IPs
+ Replication factor

If you setup multi node enviroment, run the following command on the first node: (when configuring a cluster, the first zookeeper need to be up ):
~~~bash
/opt/apps/kafka/kafka-setup.sh --ips 10.0.0.1,10.0.0.2,10.0.0.3  --replication_factor 3 --start_kafka_loop
~~~

On a single node configuration or from the 2nd node in a cluster, run the same command without the *--start_kafka_loop* switch
~~~bash
/opt/apps/kafka/kafka-setup.sh --ips 10.0.0.1,10.0.0.2,10.0.0.3  --replication_factor 3 
~~~

### Kafka cluster - Start, Shutdown and Monitor

* To shut down the Kafka server (Kafka and Zookeeper instances), run the following commands:

    ~~~bash
    /opt/apps/kafka/kafka/bin/kafka-server-stop
    /opt/apps/kafka/kafka/bin/zookeeper-server-stop
    ~~~

* To start the Kafka server (Kafka and Zookeeper instances), run the following commands:

    ~~~bash
    /opt/apps/kafka/kafka/bin/zookeeper-server-start -daemon /opt/apps/kafka/kafka/zookeeper.properties
    /opt/apps/kafka/kafka/bin/kafka-server-start -daemon /opt/apps/kafka/kafka/server.properties    
    ~~~

* To verify that the Kafka and Zookeeper are running properly on all nodes, run the below command:

    ~~~bash
    $CONFLUENT_HOME/bin/zookeeper-shell localhost:2181 <<< "ls /brokers/ids"
    ~~~
