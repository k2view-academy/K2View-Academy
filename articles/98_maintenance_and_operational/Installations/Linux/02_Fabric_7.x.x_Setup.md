# Fabric 7.x Setup Guide

##  Fabric Pre-Installation Steps

1. Click [here](01_Fabric_7.xx_Installation_intro.md) to get the full list of activities to be performed prior to the installation of the Fabric server. Note that this is mandatory and should be performed at least once per each server.
2. Click [here](Cassandra_Setup.md) for instructions on how to install a Cassandra instance or a cluster with our supplied package.
3. Click [here](Kafka_Setup.md) for instructions on how to install a Kafka instance or a cluster with our supplied package.

## Fabric Setup 

### Install the Package 

1. Log in with the previously created user for the Fabric installation.

2. Download the package from the links that were provided to you.

2. Untar the package in the user home folder (/opt/apps/fabric):

   ~~~bash
   tar -zxf [package name].tar.gz -C /opt/apps/fabric && source .bash_profile
   ~~~

   
   
### Set up the Fabric Nodes
The following script should be run on the first node separately; once the setup is done and the Fabric node status is 'READY',
run the same command on the rest of the Fabric nodes.

**Mandatory required details:**
+ Cassandra seed node IPs
+ Cassandra username & password (if different from the default in the Cassandra setup)
+ Kafka node IPs. (If kafka_ips is not provided, the PubSub will run 'in memory')



1. Run the following command, replacing the parameters with your own environment:
~~~bash
/opt/apps/fabric/fabric/scripts/fabric-setup.sh --cassandra_user k2admin --cassandra_password changeit --cassandra_ips 10.0.0.1,10.0.0.2,10.0.0.3  --kafka_ips 10.0.0.4,10.0.0.5,10.0.0.6 
~~~
* If the Cassanda & Kafka are Hardened with SSL, add the switch --ssl to import the certificate to Fabric Truststore
~~~bash
/opt/apps/fabric/fabric/scripts/fabric-setup.sh --cassandra_user k2admin --cassandra_password changeit --cassandra_ips 10.0.0.1,10.0.0.2,10.0.0.3  --kafka_ips 10.0.0.4,10.0.0.5,10.0.0.6 --ssl
~~~
> For more information on memory, Cassandra replication factor and more, see /opt/apps/fabric/scripts/fabric-setup.sh --help


2. To start Fabric - run:
~~~bash
/opt/apps/fabric/fabric/bin/k2fabric start
~~~

After a short while, the following message will be displayed: 
~~~
++++ Fabric is READY
~~~

3. Repeat and implement steps 1 & 2 on the rest of the nodes.

>Note, Default login details are **User**: admin and **Password**: admin, to change from the default, see [here]("/articles/99_fabric_infras/devops/09_fabric_replace_admin_password.md") - *Replace the Fabric Default Admin Password*.

### Fabric server - Start, Shutdown and Status

* To stop Fabric, run the following command on each node:

    ~~~bash
    /opt/apps/fabric/fabric/bin/k2fabric stop
    ~~~

* To start Fabric, run the following command on each node:
    ~~~bash
    /opt/apps/fabric/fabric/bin/k2fabric start
    ~~~~

* To check the node status, run the following command:

    ~~~bash
    /opt/apps/fabric/fabric/bin/k2fabric status
    ~~~




### For more information about advanced setup, read below:

<ul>
   <li><a href="/articles/99_fabric_infras/devops/09_fabric_replace_admin_password.md">Replace the Fabric Default Admin Password</a></li>
   <li><a href="/articles/99_fabric_infras/devops/04_cassandra_hardening.md">Cassandra Hardening Procedures</a></li>
   <li><a href="/articles/99_fabric_infras/devops/06_kafka_hardening.md">Kafka Hardening Procedures</a></li>
   <li><a href="/articles/99_fabric_infras/devops/03_fabric_api_and_ui_hardening.md">Fabric UI Hardening Procedures</a></li>
   <li><a href="/articles/26_fabric_security/01_fabric_security_overview.md">Fabric Security and Authentication Methods (LDAP, SAML.. )</a></li>
   <li><a href="/articles/04_fabric_studio/README.md">Working with Fabric Studio</a></li>
</ul>
