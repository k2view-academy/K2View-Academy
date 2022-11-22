# Fabric 7.x Setup Single Node

Fabric 7.x.x requires JDK 17 (included in the Fabric installation package). However, the certified version of Cassandra and Kafka require JDK 8.* package (latest tested version included with each supplied package).

[Click here to get the Cassandra setup details.](Cassandra_Setup.md)

[Click here to get the Apache Kafka setup details.](Apache_Kafka_Setup.md)

## Pre-Installation Steps

Click [here](01_Fabric_7.xx_Installation_intro.md) to get the full list of activities that must be performed prior to either Fabric or TDM installation. Note this step is mandatory and must be performed at least once per each server.

## Setup for Fabric 7.x & TDM 7.x

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

   - Presumption: Cassandra, Kafka and Fabric are running on the same node. If not, you have to update the IP's for: **cserver1** and  **kserver1**. 


   ~~~bash
   # Cassandra IP
   export cserver1=$(hostname -I |awk {'print $1'})
   # Kafka IP
   export kserver1=$(hostname -I |awk {'print $1'})
   cp -r $K2_HOME/fabric/config.template $K2_HOME/config

   # update heap memory if you have minimume of 32G
   ## sed -i 's@-Xmx2G@-Xmx8G@' $INSTALL_DIR/config/jvm.options
   ## sed -i 's@-Xms2G@-Xms8G@' $INSTALL_DIR/config/jvm.options

   cp config/adminInitialCredentials.template config/adminInitialCredentials
   sed -i 's@user.*@k2consoleadmin/KW4RVG98RR9xcrTv@' config/adminInitialCredentials

   sed -i 's@#REPLICATION_OPTIONS=.*@REPLICATION_OPTIONS={ '"'"'class'"'"' : '"'"'NetworkTopologyStrategy'"'"', '"'"DC1"'"' : 1}@' $K2_HOME/config/config.ini
   sed -i "s@#HOSTS=.*@HOSTS=$cserver1@" $K2_HOME/config/config.ini
   sed -i "s@#USER=.*@USER=k2admin@" $K2_HOME/config/config.ini
   ~~~

   - In case of using Kafka + iidFinder, run also the following commands:

   ~~~bash
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

:notebook_with_decorative_cover: **Note** that the Fabric Admin user is defined now as **k2consoleadmin**.

4. Start Fabric:

   ~~~bash
   k2fabric start && k2fabric status
   ~~~

5. Connect to the Fabric console with:

   ~~~bash
   fabric -u k2consoleadmin -p KW4RVG98RR9xcrTv
   ~~~

   - Same user and password should be used for login to the Web Framework.

## PGSQL 

TDM 7.xx is certified with PGSQL 9.6 & 13. You can supply access to Existing PG if you have one.
TDM requires user & password with full **create**, **delete** and **update** privileges. 

The customer can provide the **PGSQL**, or find below the installation instructions for **K2view** **PGSQL**:

<ul>      
<li>
<a href="/articles/98_maintenance_and_operational/Installations/Linux/PGSQL_setup.md">Setup PGSQL 13.3</a></li>



[![Previous](/articles/images/Previous.png)](01_Fabric_7.xx_Installation_intro.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](003_Fabric_7.x.x_Setup_Single_DC_multi_nodes.md)  

