# Fabric 8.x Setup Guide

##  Fabric Pre-Installation Steps
For a proper operation, Fabric relies on a few system settings and 3rd party solutions.
K2view provide optional packages for Cassandra and Kafka to assist with installation, however the vendor installtion packages can be used.

* System DB (SQlite, PostgreSQL, Cassandra), is required for all installation types.
    + SQlite can be used with small or DEV enviroments, Support is built in within Fabric, so no intallation needed.
    + PostgreSQL is Requiered for TDM installation and therfore we recommend to use it also for Fabric SystemDB when Setting up TDM.
    + Cassandra or equivalent can also be used, and installed seperatly following the product manuals. However, to ease the installation, K2view provide a preconfigured package  that can be used.

* Kafka is required only for multi-nodes Fabric environment.

Follow the below steps, depending on the environment that you plan to use:

1. Click [here](01_Fabric_8.xx_Installation_intro.md) to get the full list of activities to be performed prior to the installation of the Fabric Environment. Note that this is mandatory and should be performed on each server in the environment.
2. Click [here](Cassandra_Setup.md) for instructions on how to install a Cassandra instance or a cluster with K2view's supplied package.
3. Click [here](Kafka_Setup.md) for instructions on how to install a Kafka instance or a cluster with K2view's supplied package.

## Fabric Setup 
Fabric Server Installation package will be supplied to you by K2view.

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

+ To setup Fabric with Cassandra as SystemDB
    Click [here](02.1_Fabric_8.x.x_Cassandra_setup.md) 
+ To setup Fabric with PostgreSQL as SystemDB
    Click [here](02.2_Fabric_8.x.x_PG_setup.md) 
+ To setup Fabric with SQlite as SystemDB
    Click [here](02.3_Fabric_8.x.x_Sqlite_setup.md) 


2. To start Fabric - run:
~~~bash
/opt/apps/fabric/fabric/bin/k2fabric start
~~~

After a short while, the following message will be displayed: 
~~~
++++ Fabric is READY
~~~

3. Repeat and implement steps 1 & 2 on the rest of the nodes.

> **Note**: Default login details are **User**: admin and **Password**: admin. To change from the default credentials, read [here](/articles/99_fabric_infras/devops/09_fabric_replace_admin_password.md) - *Replace the Fabric Default Admin Password*.

### Fabric server - Start, Shutdown and Status

* To stop Fabric, run the following command on each node:

    ~~~bash
    /opt/apps/fabric/fabric/bin/k2fabric stop
    ~~~

* To start Fabric, run the following command on each node:
    ~~~bash
    /opt/apps/fabric/fabric/bin/k2fabric start
    ~~~

* To check the node status, run the following command:

    ~~~bash
    /opt/apps/fabric/fabric/bin/k2fabric status
    ~~~




### For more information about an advanced setup, read below:

<ul>
   <li><a href="/articles/99_fabric_infras/devops/09_fabric_replace_admin_password.md">Replace the Fabric Default Admin Password</a></li>
   <li><a href="/articles/99_fabric_infras/devops/04_cassandra_hardening.md">Cassandra Hardening Procedures</a></li>
   <li><a href="/articles/99_fabric_infras/devops/06_kafka_hardening.md">Kafka Hardening Procedures</a></li>
   <li><a href="/articles/99_fabric_infras/devops/03_fabric_api_and_ui_hardening.md">Fabric UI Hardening Procedures</a></li>
   <li><a href="/articles/26_fabric_security/01_fabric_security_overview.md">Fabric Security and Authentication Methods</a></li>
   <li><a href="/articles/04_fabric_studio/README.md">Working with Fabric Studio</a></li>
</ul>
