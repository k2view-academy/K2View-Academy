# Fabric 7.x Setup Single Node

Fabric 7.x.x requires JDK 17 (included in the Fabric installation package). However, the certified version of Cassandra and Kafka require JDK 8.* package (latest tested version included with each supplied package).

[Click here to get the Cassandra setup details.](Cassandra_Setup.md)

[Click here to get the Apache Kafka setup details.](Apache_Kafka_Setup.md)

## Pre-Installation Steps

Click [here](01_Fabric_7.xx_Installation_intro.md) to get the full list of activities that should be performed prior to either Fabric or TDM installation. Note that this is mandatory and should be performed at least once per each server.

## Setup for Fabric 7.x & TDM 7.x

The Fabric package supplied is preconfigured to run as a single node with cassandra on the same host to achieve that, some setting are set as default:

Cassandra seed IP - 127.0.0.1 (localhost)
DC = DC1


### Load the Package 

1.  Download the package from the links that were provided to you.

2.  Untar the package:
   ~~~bash
   tar -zxvf [package name].tar.gz && bash -l
   ~~~

3. Pre setup:

   ~~~bash
   sed -i "s@K2_HOME=.*@K2_HOME=$(pwd)@" .bash_profile
   bash -l 
   ~~~

   **Note** that the Fabric Admin user is defined now as **k2consoleadmin**.

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

