# Cassandra Setup

The provided Cassandra package and setup scripts were designed for either a single node or a multi-node environment.

Follow the setup script configuration for the correct settings, depending on your environment.

The script should be run separately on each node, in the order of designated node numbers. It should not be run simultaneously as this may cause configuration and operation issues.


## Pre-Installation Steps

1. Make sure all Cassandra related activities were preformed; click [here](01_Fabric_7.xx_Installation_intro.md) to get the full list of activities.

2. Verify that Python version 2.7 is being used

    ~~~bash
    python --version
    ~~~


### Load the Package on all Nodes

1. Log in to the previously created user that was designated to the Cassandra installation.

2. Download the latest Cassandra package (located [here](https://download.k2view.com/index.php/s/dMH2PWuIErPFszK)).

2. Log in to the Linux server as the 'Cassandra' user and copy the package to the home directory.

3. Untar the package (the package name varies according to the version) as follows:

    ~~~bash
    tar -zxvf k2v_cassandra-x.xx.xxx.tar.gz -C /opt/apps/cassandra && source /opt/apps/cassandra/.bash_profile
    ~~~
> Note: The setup script will use the folder it runs from.

### Set up the Cassandra Nodes

The number of seed nodes should match the chosen replication factor number.

**Mandatory required details:**
* Seed node IPs
* Cassandra admin user
* Cassandra admin password


**Optional details:**
* DataCenter name – unless defined otherwise, default will be set as DC1
* Cluster name – unless defined otherwise, default will be set as Cassandra
* Replication factor number
* Hardening and SSl configuration, TBD 


#### Multi-Node Setup
1. Run the following command on the seed nodes only (start with the 1st node, then run it one by one on the 2nd and 3rd):

    ~~~bash
    /opt/apps/cassandra/cassandra-setup.sh --cassandra_seeds 10.0.0.1,10.0.0.2,10.0.0.3 --cassandra_user k2admin --cassandra_password changeit --cassandra_replication_factor 3
    ~~~

2. Once all seed nodes are up and running, run the same command on all the rest of the Cassandra nodes (one by one):
    > In case you have no additional nodes, run the command on one of the existing seeds node to finalize configuration.

    ~~~bash
    /opt/apps/cassandra/cassandra-setup.sh --cassandra_seeds 10.0.0.1,10.0.0.2,10.0.0.3 --cassandra_user k2admin --cassandra_password changeit --  cassandra_replication_factor 3
    ~~~

#### Single Node Setup

1. Run the following command:

    ~~~bash
    /opt/apps/cassandra/cassandra-setup.sh --listeners --cassandra_user k2admin --cassandra_password changeit
    ~~~

### Cassandra cluster - Start, Shutdown and Status 

* To stop the Cassandra cluster, run the following command on each node (seed nodes should be shut down last):

    ~~~bash
    /opt/apps/cassandra/cassandra/bin/stop-server
    ~~~

* To start the Cassandra cluster, run the following command on each node, one by one, (seed nodes should be started first):
    ~~~bash
    /opt/apps/cassandra/cassandra/bin/cassandra
    ~~~~

* To check all nodes statuses, run the nodetool command:

    ~~~bash
    /opt/apps/cassandra/cassandra/bin/nodetool -u k2admin -pw changeit status
    ~~~
