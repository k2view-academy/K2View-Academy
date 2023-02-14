# Fabric V7.x.x  Setup Guide

Fabric V7.x.x requires JDK 17 (included in the Fabric installation package). However, the certified versions of Cassandra and Kafka require JDK 8.* package (the latest tested version is included with each supplied package).
Therefore, to prevent Issues, 

##  Fabric Pre-Installation Steps

1. Click [here](01_Fabric_7.xx_Installation_intro.md) to get the full list of activities to be performed prior to the installation of  Fabric Server. Note that this is mandatory and should be performed at least once per each server.
2. Click [here](Cassandra_New_Setup.md) for instruction how to install Cassandra instance or cluster with our supplied package.
3. Click [here](Kafka_New_Setup.md) for instruction how to install Kafka instance or cluster with our supplied package.

## Fabric Setup 

### Load the Package 

1. Log in to the User previously created that was designated to the fabric installation.

2. Download the package from the links that were provided to you.

2. Untar the package in the user home folder (/opt/apps/fabric:

   ~~~bash
   tar -zxf [package name].tar.gz -C /opt/apps/fabric
   ~~~

    > later in the script will add a sed command to add K2_HOME location to the .bash_profile

    
### Set up the Fabric nodes
The script should be run separately on each node, in the order of designated node numbers. It should not be run simultaneously as this may cause configuration issues.

**Mandatory required details:**
+ Cassandra seed node IPs
+ Cassandra Replication factor
+ Cassandra username & password (if set different than the default in the Cassandra setup)
+ Kafka node IPs
+ Memory in GB for Fabric heap (or set automatic to ¼ of total ram).

**Optional required details:**
+ Cassandra DC name

Assuming you prepare to run the Fabric with 3 cassandra seed nodes, 3 kafka node and set the heap memory to  ¼ of total server RAM.

Run the following command: (start with the 1st node, then, one by one until the last node)
~~~bash
./fabric_setup.sh --cassandra_user k2admin ---cassandra_password password --cassandra_ips 10.0.0.1,10.0.0.2,10.0.0.3 --cassandra_replication_factor 3 --kafka_ips 10.0.0.4,10.0.0.5,10.0.0.6 --memory auto
~~~


Login to the Fabric with your web browser with your server IP at port 3123:

   http://10.0.0.7:3123
   
   user: admin

   password: admin
