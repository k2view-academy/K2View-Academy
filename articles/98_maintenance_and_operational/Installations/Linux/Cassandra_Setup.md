# Cassandra Setup

### Load The Package 

1. Retrieve the latest Cassandra package (located at: [latest version](https://download.k2view.com/index.php/s/JCaRQITovgh6mcz)).

2. Connect to the Linux execution server as "cassandra" user and copy the package to the home directory.

3. Untar the package (the package name varies based on the version) as follows:

   ~~~bash
   tar -zxvf k2v_cassandra-3.11.xxx.tar.gz
   ~~~

4. Updated the .bash_propile to use python 2.7:

   ~~~bash
   sed -i '11i\alias python='/usr/bin/python2.7'\' ~/.bash_profile
   source ./.bash_profile
   # verified the Python version 
   python --version
   ~~~

### Setup Single Node Cassandra

Run the commands as shown below.

1.  Run the pre setup commands.

2.  Start Cassandra.

3.  Run the post setup commands.

**Pre Setup Configuration**:

Update the following parameters if needed:

- `dc=`
- `cluster_name:` 

~~~bash
sed -i "s@INSTALL_DIR=.*@INSTALL_DIR=$(pwd)@" .bash_profile
bash -l
rm -rf  cassandra/data && ln -s /opt/apps/cassandra/storage/  cassandra/data
sed -i 's@dc=.*@dc=DC1@' $INSTALL_DIR/cassandra/conf/cassandra-rackdc.properties
sed -i 's@cluster_name: .*@cluster_name: 'integration'@' $INSTALL_DIR/cassandra/conf/cassandra.yaml
sed -i s/seeds:.*/"seeds: $(hostname -I |awk {'print $1'})"/g $INSTALL_DIR/cassandra/conf/cassandra.yaml
sed -i s/listen_address:.*/"listen_address: $(hostname -I |awk {'print $1'})"/g $INSTALL_DIR/cassandra/conf/cassandra.yaml
sed -i s/broadcast_rpc_address:.*/"broadcast_rpc_address: $(hostname -I |awk {'print $1'})"/g $INSTALL_DIR/cassandra/conf/cassandra.yaml
sed -i 's@endpoint_snitch:.*@endpoint_snitch: GossipingPropertyFileSnitch@' $INSTALL_DIR/cassandra/conf/cassandra.yaml
sed -i 's@LOCAL_JMX=.*@LOCAL_JMX='no'@' $INSTALL_DIR/cassandra/conf/cassandra-env.sh
sed -i "s@-Djava.rmi.server.hostname=.*@-Djava.rmi.server.hostname=$(hostname -I |awk {'print $1'})\"@" $INSTALL_DIR/cassandra/conf/cassandra-env.sh
sed -i "s@-Dcom.sun.management.jmxremote.password.file=.*@-Dcom.sun.management.jmxremote.password.file=/opt/apps/cassandra/cassandra/conf/.jmxremote.password\"@" $INSTALL_DIR/cassandra/conf/cassandra-env.sh
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

### Post Setup on One Node

Create new superuser for Cassandra, and change the **cassandra** default user's password:

~~~bash
echo "create user k2admin with password 'Q1w2e3r4t5' superuser;" |cqlsh -u cassandra -p cassandra
echo "ALTER user cassandra with PASSWORD 'ZBU3Ld35NvXU3qud' superuser;" |cqlsh -u k2admin -p Q1w2e3r4t5
~~~

**Note**: if you select to change the password from the example above, note that you will need to update it later in point that you preconfigure the Fabric. We refer the the following SED lines:


~~~bash
sed -i "s@#PASSWORD=.*@PASSWORD=Q1w2e3r4t5@" $K2_HOME/config/config.ini
sed -i "s@#PASSWORD=.*@PASSWORD=Q1w2e3r4t5@" $K2_HOME/config/iifConfig.ini
~~~

## 