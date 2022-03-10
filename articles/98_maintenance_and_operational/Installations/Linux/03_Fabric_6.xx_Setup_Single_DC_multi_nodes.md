# Fabric 6.xx + TDM 7.xx Installation 1 DC  Multi Nodes

## Setup for Cassandra

### Load The Package 

1. Retrieve the latest Cassandra package (located at: [latest version](https://download.k2view.com/owncloud/index.php/s/kc3Zkc6RcaX48xE)).

2. Connect to the Linux execution server as "cassandra" user and copy the package to the home directory.

3. Untar the package (the package name varies based on the version) as follows:

   ~~~bash
   tar -zxvf k2v_cassandra-3.11.xxx.tar.gz
   ~~~

4. Updated the .bash_propile to use python 2.7.

   ~~~bash
   sed -i '11i\alias python='/usr/bin/python2.7'\' ~/.bash_profile
   source ./.bash_profile
   # verified the Python version 
   python --version
   ~~~


### Setup the first 3 Cassandra nodes

Run the commands as shown below for each node in turn. When doing so, update the parameters that are unique to your project. 

1.  Run the pre setup commands.

2.  Start Cassandra.

3.  Run the post setup commands.

**Pre setup run on the 3 node**, update the following as needed:

- `dc=`
- `cluster_name=`
- `seeds:` - should be the IP of the first Cassandra node that will be started 

~~~bash
sed -i "s@INSLATT_DIR=.*@INSLATT_DIR=$(pwd)@" .bash_profile
sed -i 's@dc=.*@dc=DC1@'  $INSLATT_DIR/cassandra/conf/cassandra-rackdc.properties
sed -i 's@cluster_name: .*@cluster_name: 'PreProd1'@'  $INSLATT_DIR/cassandra/conf/cassandra.yaml
sed -i s/seeds:.*/"seeds: \"172.31.47.245\""/g $INSLATT_DIR/cassandra/conf/cassandra.yaml
sed -i s/listen_address:.*/"listen_address: $(hostname -I |awk {'print $1'})"/g $INSLATT_DIR/cassandra/conf/cassandra.yaml
sed -i s/broadcast_rpc_address:.*/"broadcast_rpc_address: $(hostname -I |awk {'print $1'})"/g $INSLATT_DIR/cassandra/conf/cassandra.yaml
sed -i 's@endpoint_snitch:.*@endpoint_snitch: GossipingPropertyFileSnitch@'  $INSLATT_DIR/cassandra/conf/cassandra.yaml
sed -i 's@LOCAL_JMX=.*@LOCAL_JMX='no'@'  $INSLATT_DIR/cassandra/conf/cassandra-env.sh
sed -i "s@-Djava.rmi.server.hostname=.*@-Djava.rmi.server.hostname=$(hostname -I |awk {'print $1'})\"@"  $INSLATT_DIR/cassandra/conf/cassandra-env.sh
sed -i "s@-Dcom.sun.management.jmxremote.password.file=.*@-Dcom.sun.management.jmxremote.password.file=$INSLATT_DIR/cassandra/conf/.jmxremote.password\"@" $INSLATT_DIR/cassandra/conf/cassandra-env.sh
sed -i "s@num_tokens:.*@num_tokens: 16@" $INSLATT_DIR/cassandra/conf/cassandra.yaml

~~~

If the Cassandra node has 64G RAM, run the following:

~~~bash
sed -i 's@#-Xmx4G@-Xmx18G@' $INSLATT_DIR/cassandra/conf/jvm.options
sed -i 's@#-Xms4G@-Xms18G@' $INSLATT_DIR/cassandra/conf/jvm.options
~~~



#### Start Cassandra 

Start Cassandra on all 3 nodes:

~~~bash
cassandra
~~~

#### Post Setup Run on One Node

create new superuser for cassandra, and change the cassandra default user password, update the RF

Update the `DC1` to the DC name you used above:

~~~bash
echo "create user k2admin with password 'Q1w2e3r4t5' superuser;" |cqlsh -u cassandra -p cassandra
echo "ALTER KEYSPACE system_auth WITH REPLICATION = {'class' : 'NetworkTopologyStrategy', 'DC1': '3'};" | cqlsh -ucassandra -pcassandra
echo "ALTER KEYSPACE system_distributed WITH REPLICATION = {'class' : 'NetworkTopologyStrategy', 'DC1': '3'};" | cqlsh -ucassandra -pcassandra
echo "ALTER KEYSPACE system_traces WITH REPLICATION = {'class' : 'NetworkTopologyStrategy', 'DC1': '3'};" | cqlsh -ucassandra -pcassandra
echo "CREATE KEYSPACE keyspace_with_replication_factor_3 WITH replication = {'class': 'NetworkTopologyStrategy', 'DC1': 3} AND durable_writes = true;"|cqlsh -u cassandra -p cassandra

echo "ALTER user cassandra with PASSWORD 'ZBU3Ld35NvXU3qud' superuser;" |cqlsh -u k2admin -p 'Q1w2e3r4t5'
~~~

Run **nodetool repair** command on all the 3 nodes:

~~~bash
nodetool -u k2view -pw Q1w2e3r4t5 repair
~~~


**Note**: if you select to change the password from the example above, note that you will need to update it later in point that you preconfigure the Fabric. we refer the the following SED lines


~~~bash
sed -i "s@#PASSWORD=.*@PASSWORD=Q1w2e3r4t5@" $K2_HOME/config/config.ini
sed -i "s@#PASSWORD=.*@PASSWORD=Q1w2e3r4t5@" $K2_HOME/config/iifConfig.ini
~~~

### Setup Cassandra Nodes 4 and Above

**Pre setup run on the 3 node**, update the following as needed:

- `dc=`
- `cluster_name=`
- `seeds:` should be the IP of the first Cassandra node that will be started. 

~~~bash
sed -i 's@dc=.*@dc=DC1@'  $INSLATT_DIR/cassandra/conf/cassandra-rackdc.properties
sed -i 's@cluster_name: .*@cluster_name: 'PreProd1'@'  $INSLATT_DIR/cassandra/conf/cassandra.yaml
sed -i s/seeds:.*/"seeds: \"172.31.47.245\""/g $INSLATT_DIR/cassandra/conf/cassandra.yaml
sed -i s/listen_address:.*/"listen_address: $(hostname -I |awk {'print $1'})"/g $INSLATT_DIR/cassandra/conf/cassandra.yaml
sed -i s/broadcast_rpc_address:.*/"broadcast_rpc_address: $(hostname -I |awk {'print $1'})"/g $INSLATT_DIR/cassandra/conf/cassandra.yaml
sed -i 's@endpoint_snitch:.*@endpoint_snitch: GossipingPropertyFileSnitch@'  $INSLATT_DIR/cassandra/conf/cassandra.yaml
sed -i 's@LOCAL_JMX=.*@LOCAL_JMX='no'@'  $INSLATT_DIR/cassandra/conf/cassandra-env.sh
sed -i "s@-Djava.rmi.server.hostname=.*@-Djava.rmi.server.hostname=$(hostname -I |awk {'print $1'})\"@"  $INSLATT_DIR/cassandra/conf/cassandra-env.sh
sed -i "s@-Dcom.sun.management.jmxremote.password.file=.*@-Dcom.sun.management.jmxremote.password.file=$INSLATT_DIR/cassandra/conf/.jmxremote.password\"@" $INSLATT_DIR/cassandra/conf/cassandra-env.sh
sed -i "s@num_tokens:.*@num_tokens: 16@" $INSLATT_DIR/cassandra/conf/cassandra.yaml
sed -i "s@# allocate_tokens_for_keyspace:.*@allocate_tokens_for_keyspace: keyspace_with_replication_factor_3@" $INSLATT_DIR/cassandra/conf/cassandra.yaml
~~~

If the Cassandra node has 64G RAM, run the following:

~~~bash
sed -i 's@#-Xmx4G@-Xmx18G@' $INSLATT_DIR/cassandra/conf/jvm.options
sed -i 's@#-Xms4G@-Xms18G@' $INSLATT_DIR/cassandra/conf/jvm.options
~~~

#### Start Cassandra 

~~~bash
cassandra
~~~



## Setup Kafka Cluster

### Load the Package 

1. Download the package from: [latest version](https://download.k2view.com/owncloud/index.php/s/kc3Zkc6RcaX48xE).

2. Connect to Linux as "kafka" user and copy the package to the home directory.

3. Untar the package (the package name varies based on the version) as follows:

   ~~~bash
   tar -zxvf k2view_Confluent_5.xxx.tar.gz && bash -l
   ~~~

4. Pre setup:

   - updade the IP's of `kserver1` , `kserver2` , `kserver3` 

   ~~~bash
   export kserver1=172.31.11.198
   export kserver2=172.31.35.204
   export kserver3=172.31.31.69

   if [ "$(hostname -I |awk {'print $1'})" == "$kserver1" ]; then echo 1 > $K2_HOME/zk_data/myid; fi
   if [ "$(hostname -I |awk {'print $1'})" == "$kserver2" ]; then echo 2 > $K2_HOME/zk_data/myid; fi
   if [ "$(hostname -I |awk {'print $1'})" == "$kserver3" ]; then echo 3 > $K2_HOME/zk_data/myid; fi
   if [ "$(hostname -I |awk {'print $1'})" == "$kserver1" ]; then sed -i "s@broker.id=.@broker.id=1@" $CONFLUENT_HOME/server.properties ; fi
   if [ "$(hostname -I |awk {'print $1'})" == "$kserver2" ]; then sed -i "s@broker.id=.@broker.id=2@" $CONFLUENT_HOME/server.properties ; fi
   if [ "$(hostname -I |awk {'print $1'})" == "$kserver3" ]; then sed -i "s@broker.id=.@broker.id=3@" $CONFLUENT_HOME/server.properties ; fi

   sed -i "s@log.retention.minutes=.*@log.retention.hours=48@" $CONFLUENT_HOME/server.properties
   sed -i "s@advertised.listeners=.*@advertised.listeners=PLAINTEXT:\/\/$(hostname -I |awk {'print $1'}):9093@" $CONFLUENT_HOME/server.properties
   sed -i "s@advertised.host.name=.*@advertised.host.name=PLAINTEXT:\/\/$(hostname -I |awk {'print $1'}):9093@" $CONFLUENT_HOME/server.properties
   sed -i "s@listeners=PLAINTEXT:\/\/.*@listeners=PLAINTEXT:\/\/$(hostname -I |awk {'print $1'}):9093@" $CONFLUENT_HOME/server.properties
   sed -i "s@zookeeper.connect=.*.@zookeeper.connect=$kserver1:2181,$kserver2:2181,$kserver3:2181@" $CONFLUENT_HOME/server.properties
   echo "default.replication.factor=3" >> $CONFLUENT_HOME/server.properties
   sed -i "s@log.dirs=.*@log.dirs=$K2_HOME/zk_data@" $CONFLUENT_HOME/server.properties
   sed -i "s@dataDir=.*@dataDir=$K2_HOME/zk_data@" $CONFLUENT_HOME/zookeeper.properties
   sed -i "s@server.1=.*@#server.1=$(hostname -I |awk {'print $1'}):2888:3888@" $CONFLUENT_HOME/zookeeper.properties
   echo "server.1=$kserver1:2888:3888" >> $CONFLUENT_HOME/zookeeper.properties
   echo "server.2=$kserver2:2888:3888" >> $CONFLUENT_HOME/zookeeper.properties
   echo "server.3=$kserver3:2888:3888" >> $CONFLUENT_HOME/zookeeper.properties

   ~~~

5. Start Zookeeper on all 3 nodes:

   ~~~bash
   $K2_HOME/kafka/bin/zookeeper-server-start -daemon $K2_HOME/kafka/zookeeper.properties
   ~~~

6. Start Kafka on all 3 nodes:

   ~~~bash
   $K2_HOME/kafka/bin/kafka-server-start -daemon $K2_HOME/kafka/server.properties
   ~~~

7. Verify the Kafka and Zookeeper are running:

   ~~~bash
   $CONFLUENT_HOME/bin/zookeeper-shell localhost:2181 <<< "ls /brokers/ids"
   ~~~




## Setup for Fabric & TDM 7

### Load the Package 

1. Download the package from the links that were provided to you.

2. Untar the package:

   ~~~bash
   tar -zxvf [package name].tar.gz 
   ~~~

3. Pre setup:

   ~~~bash
   sed -i "s@K2_HOME=.*@K2_HOME=$(pwd)@" .bash_profile
   bash -l 
   ~~~

   - Update the IP's for: `cserver1` / `cserver2` / `cserver3` ....   and  `kserver1` / `kserver2` / `kserver3`  

   ~~~bash
   ## update the cassandra IP's
   export cserver1=192.168.168.212
   export cserver2=192.168.168.213
   export cserver3=192.168.168.214

   ## update the fafka IP's 
   export kserver1=172.31.11.198
   export kserver2=172.31.35.204
   export kserver3=172.31.31.69

   cp -r $K2_HOME/fabric/config.template $K2_HOME/config

   sed -i 's@-Xmx2G@-Xmx8G@' $INSLATT_DIR/config/jvm.options
   sed -i 's@-Xms2G@-Xms8G@' $INSLATT_DIR/config/jvm.options

   sed -i 's@#REPLICATION_OPTIONS=.*@REPLICATION_OPTIONS={ '"'"'class'"'"' : '"'"'NetworkTopologyStrategy'"'"', '"'"DC1"'"' : 3}@' $K2_HOME/config/config.ini
   sed -i "s@#HOSTS=.*@HOSTS=$cserver1,$cserver2,$cserver3@" $K2_HOME/config/config.ini
   sed -i "s@#USER=.*@USER=k2admin@" $K2_HOME/config/config.ini
   sed -i "s@#PASSWORD=.*@PASSWORD=Q1w2e3r4t5@" $K2_HOME/config/config.ini
   sed -i "s@#MESSAGES_BROKER_TYPE=.*@MESSAGES_BROKER_TYPE=KAFKA@" $K2_HOME/config/config.ini
   sed -i "s@#BOOTSTRAP_SERVERS=.*@BOOTSTRAP_SERVERS=$kserver1:9093,$kserver2:9093,$kserver3:9093@" $K2_HOME/config/config.ini
   sed -i "s@HOSTS=.*@HOSTS=$cserver1,$cserver2,$cserver3@" $K2_HOME/config/iifConfig.ini
   sed -i "s@#USER=.*@USER=k2admin@" $K2_HOME/config/iifConfig.ini
   sed -i "s@#PASSWORD=.*@PASSWORD=Q1w2e3r4t5@" $K2_HOME/config/iifConfig.ini
   sed -i "s@#KAFKA_BOOTSTRAP_SERVERS=.*@KAFKA_BOOTSTRAP_SERVERS=$kserver1:9093,$kserver2:9093,$kserver3:9093@" $K2_HOME/config/iifConfig.ini
   sed -i "s@#ZOOKEEPER_BOOTSTRAP_SERVERS=.*@ZOOKEEPER_BOOTSTRAP_SERVERS=$kserver1:2181,$kserver2:2181,$kserver3:2181@" $K2_HOME/config/iifConfig.ini
   sed -i 's@#IIF_REPLICATION_OPTIONS=.*@IIF_REPLICATION_OPTIONS={ '"'"'class'"'"' : '"'"'NetworkTopologyStrategy'"'"', '"'"DC1"'"' : 3}@' $K2_HOME/config/iifConfig.ini
   sed -i "s@#BOOTSTRAP_SERVERS=.*@BOOTSTRAP_SERVERS=$kserver1:9093,$kserver2:9093,$kserver3:9093@" $K2_HOME/config/iifConfig.ini
   ~~~

4. Start Fabric:

   - On the first node of Fabric run the following (only after it is ready you can start other Fabric nodes):

     ~~~bash
     cp config/adminInitialCredentials.template config/adminInitialCredentials
     sed -i 's@user.*@k2consoleadmin/KW4RVG98RR9xcrTv@' config/adminInitialCredentials
     k2fabric start && k2fabric status
     ~~~
   
   
      - Start the other Fabric nodes: 
   
        ~~~bash
        k2fabric start && k2fabric status
        ~~~
   


5. Connect to the Fabric console using the following command:

   ~~~bash
   fabric -u k2consoleadmin -p KW4RVG98RR9xcrTv
   ~~~

   - Same user and password should be used for login to the WEB UI.



## PGSQL 

TDM 7.xx is certified with pgsql 9.6 & 13. You can supply access to his PG if you have one.
TDM requires user & password with full create, delete and update privileges. 

the custumere can provide the **PGSQL**, or you can find installation instraction for **K2view** **PGSQL** installation:

<ul>      
<li>
<a href="/articles/98_maintenance_and_operational/Installations/Linux/PGSQL_setup.md">Setup PGSQL 13.3</a></li>







[![Previous](/articles/images/Previous.png)](02_Fabric_6.xx_Setup_Single_node.md) 
