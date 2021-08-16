# Fabric 6.xx and TDM 7.xx Setup Single Node

## Setup Cassandra

### Load The Package 

1.  Retrieve the latest Cassandra package (located at: [latest version](https://download.k2view.com/owncloud/index.php/s/kc3Zkc6RcaX48xE)).

2.  Connect to the Linux execution server as "cassandra" user and copy the package to the home directory.

3. Untar the package (the package name varies based on the version) as follows:

   ~~~bash
   tar -zxvf k2v_cassandra-3.11.xxx.tar.gz
   ~~~

4. Cassandra 3.11.xx requires Python 2.7. If Python 2.7 is miising on your OS, install it as ROOT: 

   ~~~bash
   dnf -y install python2
   # verified
   whereis python
   ~~~

5. Updated the .bash_propile to use python 2.7:

   ~~~bash
   sed -i '11i\alias python='/usr/bin/python2.7'\' ~/.bash_profile
   ~~~

### Setup Single Node Cassandra

Run the commands as shown below.

1.  Run the pre setup commands,

2.  Start Cassandra.

3.  Run the post setup commands.

**Pre Setup Run**:

Update the following parameters if needed:

- `dc=`
- `cluster_name:` 

~~~bash
sed -i "s@INSLATT_DIR=.*@INSLATT_DIR=$(pwd)@" .bash_profile
rm -rf  cassandra/data && ln -s /opt/apps/cassandra/storage/  cassandra/data
sed -i 's@dc=.*@dc=DC1@' $INSLATT_DIR/cassandra/conf/cassandra-rackdc.properties
sed -i 's@cluster_name: .*@cluster_name: 'integration'@' $INSLATT_DIR/cassandra/conf/cassandra.yaml
sed -i s/seeds:.*/"seeds: $(hostname -I |awk {'print $1'})"/g $INSLATT_DIR/cassandra/conf/cassandra.yaml
sed -i s/listen_address:.*/"listen_address: $(hostname -I |awk {'print $1'})"/g $INSLATT_DIR/cassandra/conf/cassandra.yaml
sed -i s/broadcast_rpc_address:.*/"broadcast_rpc_address: $(hostname -I |awk {'print $1'})"/g $INSLATT_DIR/cassandra/conf/cassandra.yaml
sed -i 's@endpoint_snitch:.*@endpoint_snitch: GossipingPropertyFileSnitch@' $INSLATT_DIR/cassandra/conf/cassandra.yaml
sed -i 's@LOCAL_JMX=.*@LOCAL_JMX='no'@' $INSLATT_DIR/cassandra/conf/cassandra-env.sh
sed -i "s@-Djava.rmi.server.hostname=.*@-Djava.rmi.server.hostname=$(hostname -I |awk {'print $1'})\"@" $INSLATT_DIR/cassandra/conf/cassandra-env.sh
sed -i "s@-Dcom.sun.management.jmxremote.password.file=.*@-Dcom.sun.management.jmxremote.password.file=/opt/apps/cassandra/cassandra/conf/.jmxremote.password\"@" $INSLATT_DIR/cassandra/conf/cassandra-env.sh
echo "monitorRole QED" >> ~/cassandra/conf/.jmxremote.password
echo "controlRole R&D" >> ~/cassandra/conf/.jmxremote.password
echo "cassandra cassandra" >> ~/cassandra/conf/.jmxremote.password
echo "k2view Q1w2e3r4t5" >> ~/cassandra/conf/.jmxremote.password
chmod 400 ~/cassandra/conf/.jmxremote.password

~~~

### Start Cassandra

~~~bash
cassandra
~~~

### Post Setup Run on One Node

~~~bash
echo "create user k2admin with password 'Q1w2e3r4t5' superuser;" |cqlsh -u cassandra -p cassandra
echo "ALTER user cassandra with PASSWORD 'ZBU3Ld35NvXU3qud' superuser;" |cqlsh -u k2admin -p Q1w2e3r4t5
~~~



## Setup Kafka

### Load the Package 

1.  Download the package from: [latest version](https://download.k2view.com/owncloud/index.php/s/kc3Zkc6RcaX48xE).

2.  Connect to Linux as "kafka" user and copy the package to the home directory.

3. Untar the package (the package name varies based on the version) as follows:

   ~~~bash
   tar -zxvf k2view_Confluent_5.xxx.tar.gz && bash -l
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


## Setup for Fabric & TDM 7

### Load the Package 

1.  Download the package from the links that were provided to you.

2.  Untar the package:
   ~~~bash
   tar -zxvf [package name].tar.gz 
   ~~~

3. Pre setup:

   ~~~bash
   sed -i "s@K2_HOME=.*@K2_HOME=$(pwd)@" .bash_profile
   bash -l 
   ~~~

   - Assumption: Cassandra, Kafka and Fabric are running on the same node. If not, you have to update the IP's for: **cserver1** and  **kserver1**. 
     

   ~~~bash
   # Cassandra IP
   export cserver1=$(hostname -I |awk {'print $1'})
   # Kafka IP
   export kserver1=$(hostname -I |awk {'print $1'})
   cp -r $K2_HOME/fabric/config.template $K2_HOME/config

   # update heap memory if you have minimume of 32G
   ## sed -i 's@-Xmx2G@-Xmx8G@' $INSLATT_DIR/config/jvm.options
   ## sed -i 's@-Xms2G@-Xms8G@' $INSLATT_DIR/config/jvm.options

   cp config/adminInitialCredentials.template config/adminInitialCredentials
   sed -i 's@user.*@k2consoleadmin/KW4RVG98RR9xcrTv@' config/adminInitialCredentials

   sed -i 's@#REPLICATION_OPTIONS=.*@REPLICATION_OPTIONS={ '"'"'class'"'"' : '"'"'NetworkTopologyStrategy'"'"', '"'"DC1"'"' : 1}@' $K2_HOME/config/config.ini
   sed -i "s@#HOSTS=.*@HOSTS=$cserver1,$cserver2,$cserver3@" $K2_HOME/config/config.ini
   sed -i "s@#USER=.*@USER=k2admin@" $K2_HOME/config/config.ini
   sed -i "s@#PASSWORD=.*@PASSWORD=Q1w2e3r4t5@" $K2_HOME/config/config.ini
   sed -i "s@#MESSAGES_BROKER_TYPE=.*@MESSAGES_BROKER_TYPE=KAFKA@" $K2_HOME/config/config.ini
   sed -i "s@#BOOTSTRAP_SERVERS=.*@BOOTSTRAP_SERVERS=$kserver1:9093@" $K2_HOME/config/config.ini
   sed -i "s@HOSTS=.*@HOSTS=$cserver1@" $K2_HOME/config/iifConfig.ini
   sed -i "s@#USER=.*@USER=k2admin@" $K2_HOME/config/iifConfig.ini
   sed -i "s@#PASSWORD=.*@PASSWORD=Q1w2e3r4t5@" $K2_HOME/config/iifConfig.ini
   sed -i "s@#KAFKA_BOOTSTRAP_SERVERS=.*@KAFKA_BOOTSTRAP_SERVERS=$kserver1:9093@" $K2_HOME/config/iifConfig.ini
   sed -i "s@#ZOOKEEPER_BOOTSTRAP_SERVERS=.*@ZOOKEEPER_BOOTSTRAP_SERVERS=$kserver1:2181@" $K2_HOME/config/iifConfig.ini
   sed -i 's@#IIF_REPLICATION_OPTIONS=.*@IIF_REPLICATION_OPTIONS={ '"'"'class'"'"' : '"'"'NetworkTopologyStrategy'"'"', '"'"DC1"'"' : 1}@' $K2_HOME/config/iifConfig.ini
   sed -i "s@#BOOTSTRAP_SERVERS=.*@BOOTSTRAP_SERVERS=$kserver1:9093@" $K2_HOME/config/iifConfig.ini
   ~~~

4. Start Fabric

   ~~~bash
   k2fabric start && k2fabric status
   ~~~

5. Connect to the Fabric console with:

   ~~~bash
   fabric -u k2consoleadmin -p KW4RVG98RR9xcrTv
   ~~~

   - Same user and password should be use for login to the WEBUI

## Setup PG 

TDM 7.xx is certified with pgsql 9.6 & 13. You can supply access to his PG if you have one.
TDM requires user & password with full create, delete and update privileges. 

You also have the option install it from the K2View predefined **tarball** file.

### Prerequisite 

- RedHat/CentOs 8, AWS Linux 2
- 2 vCPU
- 8G RAM
- 100G free disk space, make sure it is assigned to `/opt/apps/pgsql` 
  
  Add the following users:

~~~bash
mkdir -p /opt/apps
chmod 755 /opt/apps
useradd -m -d /opt/apps/pqsql pgsql
~~~

### Setup  ###

- Connect as **pgsql**.

- Download or copy the [pg-13.2.tar.gz](https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/TDM/PG%20image/pg-13.2.tar.gz).

- Untar the `pg-13.2.tar.gz` 

  ~~~bash
  untar -zxvf pg-13.2.tar.gz && bash -l
  ~~~

- You can now start the **pgsql** with: 

  ~~~bash
  cd bin/
  ./bin/pg_ctl -D /opt/apps/pgsql/data -l logfile start
  ~~~

  - The configuration files are located at `/opt/apps/pgsql/data`
  - The user and password are **postgres**, port is the default (5432). 



[![Previous](/articles/images/Previous.png)](01_Fabric_6.xx_Installation_intro.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_Fabric_6.xx_Setup_Single_DC_multi_nodes.md)  

