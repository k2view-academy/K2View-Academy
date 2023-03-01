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
	tar -zxvf k2view_Confluent_7.xxx.tar.gz
	~~~

### Set up the Kafka Nodes

**Mandatory required details:**
+ Node IPs
+ Replication factor

 Run the following command: (start with the 1st node, then run it one by one on the 2nd, etc.):
~~~bash
/opt/apps/kafka/kafka-setup.sh -ips 10.0.0.1,10.0.0.2,10.0.0.3  --replication_factor 3
~~~

### Kafka cluster - Start, Shutdown and Monitor

* To shut down the Kafka server (Kafka and Zookeeper instances), run the setup script on each node with the following flag:

	~~~bash
	/opt/apps/kafka/kafka-setup.sh --stop
	~~~

* To start the Kafka server (Kafka and Zookeeper instances), run the setup script on each node with the following flag:

	~~~bash
	/opt/apps/kafka/kafka-setup.sh --start
	~~~

* To verify that the Kafka and Zookeeper are running properly on all nodes, run the below command:

	~~~bash
	$CONFLUENT_HOME/bin/zookeeper-shell localhost:2181 <<< "ls /brokers/ids"
	~~~
