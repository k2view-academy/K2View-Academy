# Fabric Setup Guide

##  Fabric Pre-Installation Steps

1. Click [here](01_Fabric_7.xx_Installation_intro.md) to get the full list of activities to be performed prior to the installation of  Fabric Server. Note that this is mandatory and should be performed at least once per each server.
2. Click [here](Cassandra_New_Setup.md) for instructions on how to install Cassandra instance or cluster with our supplied package.
3. Click [here](Kafka_New_Setup.md) for instructions on how to install Kafka instance or cluster with our supplied package.

## Fabric Setup 

### Install the Package 

1. Log in with the User previously created for the fabric installation.

2. Download the package from the links that were provided to you.

2. Untar the package in the user home folder (/opt/apps/fabric):

   ~~~bash
   tar -zxf [package name].tar.gz -C /opt/apps/fabric
   ~~~

   
    
### Setup the Fabric nodes
The following script should be run on the first node seperatly, once the setup is done and the node status will be 'READY'
repeate the same command on th erest of the Fabric nodes.

**Mandatory required details:**
+ Cassandra seed node IPs
+ Cassandra username & password (if set different than the default in the Cassandra setup)
+ Kafka node IPs. If IP's are not supplied, the  PubSub will run 'in memeory'



1. Run the following command, replacing the parameters with your own environment:
~~~bash
/opt/apps/fabric/fabric/scripts/fabric-setup.sh --cassandra_user k2admin --cassandra_password changeit --cassandra_ips 10.0.0.1,10.0.0.2,10.0.0.3  --kafka_ips 10.0.0.4,10.0.0.5,10.0.0.6 
~~~

> For more information on memory, Cassandra replication factor and more, see /opt/apps/fabric/scripts/fabric-setup.sh --help


2. To start fabric run:
~~~bash
/opt/apps/fabric/fabric/bin/k2fabric start
~~~

After a short while, You will see: 
~~~bash
++++ Fabric is READY
~~~

3. Repeat the step 1 & 2  on the rest of the nodes


### Status, Shutdown and Starting Fabric server

* To stop the Fabric , run the following command on each node.

    ~~~bash
    /opt/apps/fabric/fabric/bin/k2fabric stop
    ~~~

* To start the Fabric, run the following command on each node.
    ~~~bash
    /opt/apps/fabric/fabric/bin/k2fabric start
    ~~~~

* To check node status, run the following command.

    ~~~bash
    /opt/apps/fabric/fabric/bin/k2fabric status
    ~~~

* To check cluster status, run the following command.

    ~~~bash
    /opt/apps/fabric/fabric/bin/k2fabric cluster-status
    ~~~



### For more information about advanced features see here:

+ Hardening procedure

+ nodeID

+ SAML

+ More
