# Apache Kafka Setup

### Load the Package 

1. Download the package from: [latest version](https://download.k2view.com/index.php/s/PTswLot7a21eFET).

2. Connect to Linux as "kafka" user and copy the package to the home directory.

3. Untar the package (the package name varies based on the version) as follows:

   ~~~bash
   tar -zxvf k2view_Confluent_7.xxx.tar.gz && bash -l
   ~~~

4. Pre setup:

   ~~~bash
   export kserver1=$(hostname -I |awk {'print $1'})
   
   if [ "$(hostname -I |awk {'print $1'})" == "$kserver1" ]; then echo 1 > $K2_HOME/zk_data/myid; fi
   if [ "$(hostname -I |awk {'print $1'})" == "$kserver1" ]; then sed -i "s@broker.id=.@broker.id=1@" $CONFLUENT_HOME/server.properties ; fi
   
   sed -i "s@log.retention.minutes=.*@log.retention.hours=48@" $CONFLUENT_HOME/server.properties
   sed -i "s@advertised.listeners=.*@advertised.listeners=PLAINTEXT:\/\/$(hostname -I |awk {'print $1'}):9093@" $CONFLUENT_HOME/server.properties
   sed -i "s@advertised.host.name=.*@advertised.host.name=PLAINTEXT:\/\/$(hostname -I |awk {'print $1'}):9093@" $CONFLUENT_HOME/server.properties
   sed -i "s@listeners=PLAINTEXT:\/\/.*@listeners=PLAINTEXT:\/\/$(hostname -I |awk {'print $1'}):9093@" $CONFLUENT_HOME/server.properties
   sed -i "s@zookeeper.connect=.*.@zookeeper.connect=$kserver1:2181@" $CONFLUENT_HOME/server.properties
   echo "default.replication.factor=3" >> $CONFLUENT_HOME/server.properties
   sed -i "s@log.dirs=.*@log.dirs=$K2_HOME/zk_data@" $CONFLUENT_HOME/server.properties
   sed -i "s@dataDir=.*@dataDir=$K2_HOME/zk_data@" $CONFLUENT_HOME/zookeeper.properties
   sed -i "s@server.1=.*@#server.1=$(hostname -I |awk {'print $1'}):2888:3888@" $CONFLUENT_HOME/zookeeper.properties
   echo "server.1=$kserver1:2888:3888" >> $CONFLUENT_HOME/zookeeper.properties
   
   ~~~

5. Start Kafka and Zookeeper:

   ~~~bash
   $K2_HOME/kafka/bin/zookeeper-server-start -daemon $K2_HOME/kafka/zookeeper.properties
   sleep 10
   $K2_HOME/kafka/bin/kafka-server-start -daemon $K2_HOME/kafka/server.properties
   ~~~

6. Verify the Kafka and Zookeeper are running:

   ~~~bash
   $CONFLUENT_HOME/bin/zookeeper-shell localhost:2181 <<< "ls /brokers/ids"
   ~~~


## 