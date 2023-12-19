# Apache Kafka Cluster Setup

The provided Kafka package and setup scripts were designed for either a single node environment or a multi-node environment.

Follow the setup script for configuring the setting correctly, depending on your environment.


### Load the Package 

1. Log in with the previously-created user name that was designated for the Kafka installation.

2. Download the latest Kafka package (located [here](https://download.k2view.com/index.php/s/SomMMD6PDpUAfCp)).

3. Untar the package in the user home folder (the package name varies according to the version) as follows:

    ~~~bash
    tar -zxf k2view_Confluent_7.xxx.tar.gz -C /opt/apps/kafka && source /opt/apps/kafka/.bash_profile
    ~~~


### Set up the Kafka Nodes

The script should be run seperately on each node, in the order of designated node numbers. It should not be run simultaneously as this may cause configuration issues.

The number of cluster nodes should be either greater than or equal to the replication factor number.

**Mandatory required details:**
* Node IPs
* Replication factor

#### <u>Multi Node Setup:</u>


1. Run the following command on the **first node only**:

    ~~~bash
    /opt/apps/kafka/kafka-setup.sh --ips 10.0.0.1,10.0.0.2,10.0.0.3  --replication_factor 3 --start_kafka_loop
    ~~~

> When setting up multi-node environment, the first zookeeper need to be up.


2. On the rest of the Kafka nodes, one by one, execute:

    ~~~bash
    /opt/apps/kafka/kafka-setup.sh --ips 10.0.0.1,10.0.0.2,10.0.0.3  --replication_factor 3
    ~~~

3. Validate that all the Zookeepers are running properly:

    ~~~bash
    $CONFLUENT_HOME/bin/zookeeper-shell localhost:2181 <<< "ls /brokers/ids"
    ~~~

After a short while, the following message should be displayed: 
~~~
Connecting to localhost:2181
Welcome to ZooKeeper!
JLine support is disabled

WATCHER::

WatchedEvent state:SyncConnected type:None path:null
[1, 2, 3]
~~~


#### <u>Single Node Setup:</u>


1. Run the following command:

    ~~~bash
    /opt/apps/kafka/kafka-setup.sh --ips 10.0.0.1
    ~~~

2. Validate that the Kafka Broker and Zookeeper are up:

    ~~~bash
    jps
    ~~~

After a short while, list of processes should be displayed: 
~~~
<proc_id> QuorumPeerMain
<proc_id> Kafka
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

* To verify that the Kafka and Zookeeper are running on a specifc node:

    ~~~bash
    jps
    ~~~

* To verify that the Zookeepers are running properly on all nodes, run the below command:

    ~~~bash
    /opt/apps/kafka/kafka/bin/zookeeper-shell localhost:2181 <<< "ls /brokers/ids"
    ~~~
