# Fabric V7.x.x  Setup Guide

Fabric V7.x.x requires JDK 17 (included in the Fabric installation package). However, the certified versions of Cassandra and Kafka require JDK 8.* package (the latest tested version is included with each supplied package).
Therefore, to prevent Issues, 

##  Fabric Pre-Installation Steps

1. Click [here](01_Fabric_7.xx_Installation_intro.md) to get the full list of activities to be performed prior to the installation of  Fabric Server. Note that this is mandatory and should be performed at least once per each server.
2. Click [here](Cassandra_New_Setup.md) for instruction how to install Cassandra instance or cluster with our supplied package.
3. Click [here](Kafka_New_Setup.md) for instruction how to install Kafka instance or cluster with our supplied package.

## Fabric Setup 

### Install the Package 

1. Log in with the User previously created for the fabric installation.

2. Download the package from the links that were provided to you.

2. Untar the package in the user home folder (/opt/apps/fabric):

   ~~~bash
   tar -zxf [package name].tar.gz -C /opt/apps/fabric
   ~~~

    > later in the script will add a sed command to add K2_HOME location to the .bash_profile

    
### Set up the Fabric nodes
The script should be run separately on each node, in the order of designated node numbers. It should not be run simultaneously as this may cause configuration issues.

**Mandatory required details:**
+ Cassandra seed node IPs
+ Cassandra username & password (if set different than the default in the Cassandra setup)
+ Kafka node IPs
+ Memory in GB for Fabric heap (or set automatic to ¼ of total ram).


Assuming you prepare to run the Fabric with 3 cassandra seed nodes, 3 kafka node and set the heap memory to  ¼ of total server RAM.

Run the following command: (start with the 1st node, then, one by one until the last node)
~~~bash
/opt/apps/fabric/fabric/scripts/fabric-setup.sh --cassandra_user k2admin --cassandra_password password --cassandra_ips 10.0.0.1,10.0.0.2,10.0.0.3  --kafka_ips 10.0.0.4,10.0.0.5,10.0.0.6 --memory auto
~~~

> for more information on memory, replication factor and more, see /opt/apps/fabric/scripts/fabric-setup.sh --help

to start fabric run:
~~~bash
/opt/apps/fabric/fabric/bin/k2fabric start
~~~

You should see: 
~~~bash
++++ Fabric IS Ready
~~~
