# Cassandra Setup

### Load The Package 

1. Retrieve the latest Cassandra package (located [here](https://download.k2view.com/index.php/s/dMH2PWuIErPFszK)).

2. Log in to the Linux execution server as the user "Cassandra" and copy the package to the home directory.

3. Untar the package (the package name varies according to the version) as follows:

   ~~~bash
   tar -zxvf k2v_cassandra-3.11.xxx.tar.gz && bash -l
   ~~~


### Setup Single Node Cassandraa

the Cassandra package supplied is preconfigured to run as single node with fabric on the same host
to achive that, some setting are set as default:

DC=DC1
cluster_name:integration
Listen_address: 127.0.0.1 (localhost)

To start working, run the commands as shown below.

1.  Start Cassandra.

2.  Run the post setup commands.

### Start Cassandra

~~~bash
cassandra
~~~

### Post Setup on One Node

Create new superuser for Cassandra, and change the **cassandra** default user's password:

~~~bash
echo "create user k2admin with password 'Q1w2e3r4t5' superuser;" |cqlsh -u cassandra -p cassandra
~~~

**Note**: if you select to change the password from the example above, note that you will need to update it later in point that you preconfigure the Fabric. We refer the the following SED lines:


~~~bash
sed -i "s@#PASSWORD=.*@PASSWORD=Q1w2e3r4t5@" $K2_HOME/config/config.ini
sed -i "s@#PASSWORD=.*@PASSWORD=Q1w2e3r4t5@" $K2_HOME/config/iifConfig.ini
~~~

## 
