# Apache Kafka Cluster Setup

The provided Kafka package and setup scripts are designed for either a single node environment or a multi-node environment.

Follow the setup script for configuring the setting correctly, depending on your environment.


### Load the Package 

1. Log in with the previously-created user name designated for the Kafka installation.

2. Download the latest Kafka package (located [here](https://owncloud-bkp2.s3.amazonaws.com/adminoc/fabricint/latest+version/K+7.6/k2view_kafka_7.6.1.tar.gz)).

3. Untar the package in the desired directory (see notes below) as follows:

    ~~~bash
    tar -zxf k2view_kafka_7.xxx.tar.gz -C /opt/apps/kafka && source /opt/apps/kafka/.setenv_kafka.sh
    ~~~

<blockquote>
The package name varies according to the version.

The parent directory of Kafka and its supporting apps are stored in the environment variable `K2_HOME`. If using a directory different than `/opt/apps/kafka`, you will need to open the file `.setenv_kafka.sh` with the text editor of your preference and change the value of the environment variable `K2_HOME`.
</blockquote>


### Setting up Kafka

We provide a setup script (located in `$K2_HOME/scripts/kafka-setup.sh`) that will configure your Kafka cluster.

In version 7.3 and below, Kafka requires ZooKeeper (which is also configured during the setup) for its metadata management. Starting from version 7.4 and above, Kafka runs in "KRaft" mode by default, which doesn't require ZooKeeper. Although not recommended, this behavior can be changed by passing the flag `--metadata-mgmt zookeeper`.

> For more information of all configurable parameters, run `$K2_HOME/scripts/kafka-setup.sh --help`.


#### <u>Single Node Setup:</u>


1. Run the following command:

    ~~~bash
    $K2_HOME/scripts/kafka-setup.sh --ip-list 10.0.0.1
    ~~~

    Once the script completes its execution, you should see a message `Kafka UP` (followed by `ZooKeeper UP` if running in ZooKeeper mode).


#### <u>Multi-Node Setup:</u>

The setup script should be run seperately on each node, in the order of the designated node numbers. It should not be run simultaneously as this might cause configuration issues.

The number of cluster nodes should be either greater than or equal to the replication factor number.

**Mandatory required details:**
* Node IPs
* Replication factor

1. Run the following command on the **first node only**:

    ~~~bash
    $K2_HOME/scripts/kafka-setup.sh --ip-list 10.0.0.1,10.0.0.2,10.0.0.3  --replication-factor 3 --start-kafka-loop
    ~~~

    > When you see the message "Starting Kafka  (start loop enabled at HH:MM:SS, will timeout in N seconds)", you can start configuring the next node (but keep the terminal open).


2. On the rest of the Kafka nodes, one by one, execute:

    ~~~bash
    $K2_HOME/scripts/kafka-setup.sh --ip-list 10.0.0.1,10.0.0.2,10.0.0.3  --replication-factor 3
    ~~~

    Once the script finished it's execution, you should see a message `Kafka UP` (followed by `ZooKeeper UP` if running in zookeeper mode)

3. To validate that Kafka is running properly in all nodes:

    *See item **"check the cluster's health"** in section **"Kafka cluster - Start, Shutdown and Monitor" below***

### Kafka cluster - Start, Shutdown and Monitor

* To stop Kafka (and ZooKeeper if running in zookeeper mode), run the following command:

    ~~~bash
    $K2_HOME/scripts/kafka-setup.sh stop
    ~~~

* To start Kafka (and ZooKeeper if running in zookeeper mode), run the following command:

    ~~~bash
    $K2_HOME/scripts/kafka-setup.sh start
    ~~~

* To verify that if Kafka (and ZooKeeper if running in zookeeper mode), is running on a specifc node:

    ~~~bash
    $K2_HOME/scripts/kafka-setup.sh status
    ~~~

* To check the cluster's health, run one of the following commands according to your configuration:

    in zookeeper mode:
    ~~~bash
    $CONFLUENT_HOME/bin/zookeeper-shell localhost:2181 <<< "ls /brokers/ids"
    ~~~

    The following message should be displayed:
    ~~~
    Connecting to localhost:2181
    Welcome to ZooKeeper!
    JLine support is disabled

    WATCHER::

    WatchedEvent state:SyncConnected type:None path:null
    [1, 2, 3]
    ~~~

    in kraft mode:
    ~~~bash
    $CONFLUENT_HOME/bin/kafka-metadata-quorum --bootstrap-server localhost:9093 describe --status
    ~~~

    The following message should be displayed:
    ~~~
    ClusterId:              K2ViewDocker-Cluster1A
    LeaderId:               1
    LeaderEpoch:            13
    HighWatermark:          2866
    MaxFollowerLag:         0
    MaxFollowerLagTimeMs:   0
    CurrentVoters:          [1,2,3]
    CurrentObservers:       []
    ~~~
