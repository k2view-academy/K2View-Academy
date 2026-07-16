# Cassandra Setup

This guide describes how to install and configure **vanilla Apache Cassandra 4.1.x** as a Fabric System Database, for either single-node or multi-node environments.

Apply the configuration on each node separately, in the order of the designated node numbers (start with seed node 1, then continue one by one). Do not configure or start the nodes simultaneously, as this might cause configurational and operational issues.

**Official reference:** https://cassandra.apache.org/doc/4.1/

## Version Considerations

This guide targets **Apache Cassandra 4.1.x**.

- **Java 8 or 11** must be on the `PATH` (verify with `java -version`). Cassandra 4.1.x does not support Java 17+.
- **Python 3.6 or higher** is required for utilities such as `cqlsh` (verify with `python3 --version`).

> *Note:* On systems such as RHEL 7 that default to Python 2.7, be sure to explicitly install and use a Python 3 interpreter (e.g., `python3`) when invoking Cassandra utilities.

> **Cassandra 4.x Configuration**  
> After configuration, review your `cassandra.yaml` for any deprecated options and align the Java/TLS settings with Cassandra 4.1.x performance and security guidelines.

## Pre-Installation Steps

1. Make sure all Cassandra-related activities were performed; click [here](01_Fabric_8.xx_Installation_intro.md) to get the full list of activities.

2. It is recommended to run Cassandra under a dedicated `cassandra` user (in the `k2view` group) with its home/installation directory at `/opt/apps/cassandra/`:

    ~~~bash
    mkdir -p /opt/apps
    groupadd -f k2view
    useradd -m -d /opt/apps/cassandra -g k2view -s /bin/bash cassandra
    chown -R cassandra:k2view /opt/apps/cassandra
    ~~~

3. Install the latest version of Java 11, either the Oracle Java Standard Edition 11 (Long Term Support) or OpenJDK 11 (see the [Apache prerequisites](https://cassandra.apache.org/doc/4.1/cassandra/getting_started/installing.html#prerequisites)). Download from:

    - Oracle JDK 11: https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html
    - OpenJDK 11: https://jdk.java.net/archive/ (direct Linux x64 tarball: https://download.java.net/java/ga/jdk11/openjdk-11_linux-x64_bin.tar.gz)

    To verify that you have the correct version of Java installed, type `java -version`:

    ~~~bash
    java -version
    ~~~

    > *Recommended:* Set `JAVA_HOME` and add its `bin` to the `PATH` in the `cassandra` user's `.bash_profile` so Cassandra can always locate the JVM (point the path at your JDK 11 install location):
    >
    > ~~~bash
    > echo "export JAVA_HOME=/opt/apps/cassandra/jdk-11" >> ~/.bash_profile
    > echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.bash_profile
    > source ~/.bash_profile
    > ~~~

4. Verify that a supported Python 3 interpreter is available (required for `cqlsh`):

    ~~~bash
    python3 --version
    ~~~

### Download and Install on all Nodes

1. Log in to the previously created user that was designated to the Cassandra installation (`cassandra`).

2. Download the latest **Apache Cassandra 4.1.x** binary tarball from the official site (https://cassandra.apache.org/download/). For example:

    ~~~bash
    cd /opt/apps/cassandra
    curl -OL https://dlcdn.apache.org/cassandra/4.1.11/apache-cassandra-4.1.11-bin.tar.gz
    ~~~

3. Extract the package, create a version-independent `cassandra` symlink, and set `CASSANDRA_HOME` to point at it:

    ~~~bash
    tar -zxvf apache-cassandra-4.1.11-bin.tar.gz -C /opt/apps/cassandra
    ln -sfn /opt/apps/cassandra/apache-cassandra-4.1.11 /opt/apps/cassandra/cassandra
    export CASSANDRA_HOME=/opt/apps/cassandra/cassandra
    ~~~

    > Using the `cassandra` symlink as `CASSANDRA_HOME` keeps all paths version-independent — to upgrade later, extract the new release and repoint the link (`ln -sfn`) without touching any other configuration.

    > *Tip:* Persist `CASSANDRA_HOME` and add it to the `PATH` in the `cassandra` user's `.bash_profile` so the `cassandra`, `nodetool`, and `cqlsh` commands are always available:
    >
    > ~~~bash
    > echo "export CASSANDRA_HOME=/opt/apps/cassandra/cassandra" >> ~/.bash_profile
    > echo 'export PATH=$CASSANDRA_HOME/bin:$PATH' >> ~/.bash_profile
    > source ~/.bash_profile
    > ~~~

> **Note:** The commands above use `4.1.11` as an example. Any **4.1.x** release is supported — substitute the latest 4.1.x patch version in the download URL and paths. Always download from the official Apache mirror and follow the [Apache Cassandra 4.1 documentation](https://cassandra.apache.org/doc/4.1/) for version-specific details.

### Configure the Cassandra Nodes

Apply the following changes under `$CASSANDRA_HOME/conf/` **on every node** before the first start. The values for `listen_address`, `rpc_address`, and `broadcast_rpc_address` are written into the data directory on first boot — Cassandra will refuse to start if they change afterwards, so get them right the first time.

**Required details:**
* Seed node IPs (the number of seed nodes should match the chosen replication factor)
* Cassandra admin user and password
* DataCenter name (default: `DC1`)
* Cluster name (default: `Cassandra`)
* Replication factor

The `sed` commands in each step below reference the following variables. Set the config-file paths, then **optionally** override any value — if you leave one unset, the `sed` command falls back to a sensible default (this node's first IP, `DC1`, etc.):

~~~bash
export CONF=$CASSANDRA_HOME/conf/cassandra.yaml
export RACKDC=$CASSANDRA_HOME/conf/cassandra-rackdc.properties
export ENV_SH=$CASSANDRA_HOME/conf/cassandra-env.sh

# Optional overrides — uncomment/edit only what you need:
# NODE_IP=10.0.0.1                          # defaults to this host's first IP
# SEEDS="10.0.0.1,10.0.0.2,10.0.0.3"        # defaults to this node's IP (single-node)
# CLUSTER_NAME=Cassandra
# DC=DC1
# RACK=RAC1
~~~

#### Step 1 — Network (`cassandra.yaml`)

~~~yaml
# Bind address for inter-node communication (never 0.0.0.0)
listen_address: <node_private_ip>

# CQL client listen address — 0.0.0.0 accepts from all interfaces
rpc_address: 0.0.0.0

# Advertised address for clients — must be reachable from the Fabric nodes
broadcast_rpc_address: <node_private_ip>
~~~

~~~bash
sed -i "s/^listen_address:.*/listen_address: ${NODE_IP:-$(hostname -I | awk '{print $1}')}/" "$CONF"
sed -i "s/^rpc_address:.*/rpc_address: 0.0.0.0/" "$CONF"
sed -i "s/^# broadcast_rpc_address:.*/broadcast_rpc_address: ${NODE_IP:-$(hostname -I | awk '{print $1}')}/" "$CONF"
~~~

> Setting `rpc_address` to `0.0.0.0` makes Cassandra listen on all interfaces. When you do this you **must** also set `broadcast_rpc_address` to the node's reachable IP, otherwise Cassandra will refuse to start (it cannot gossip `0.0.0.0` to the rest of the cluster).

#### Step 2 — Cluster Identity (`cassandra.yaml` + `cassandra-rackdc.properties`)

~~~yaml
# cassandra.yaml
cluster_name: 'Cassandra'

# At least one seed per DC; for a single node use its own IP
seeds: "10.0.0.1,10.0.0.2,10.0.0.3"
~~~

~~~properties
# cassandra-rackdc.properties
dc=DC1
rack=RAC1
~~~

~~~bash
sed -i "s/^cluster_name:.*/cluster_name: '${CLUSTER_NAME:-Cassandra}'/" "$CONF"
sed -i "s/.*- seeds:.*/          - seeds: \"${SEEDS:-$(hostname -I | awk '{print $1}')}\"/" "$CONF"
sed -i "s/^dc=.*/dc=${DC:-DC1}/" "$RACKDC"
sed -i "s/^rack=.*/rack=${RACK:-RAC1}/" "$RACKDC"
~~~

#### Step 3 — Snitch (`cassandra.yaml`)

~~~yaml
endpoint_snitch: GossipingPropertyFileSnitch
~~~

~~~bash
sed -i "s/^endpoint_snitch:.*/endpoint_snitch: GossipingPropertyFileSnitch/" "$CONF"
~~~

> `GossipingPropertyFileSnitch` reads `dc=` / `rack=` from each node's `cassandra-rackdc.properties` and gossips it to the ring. It is the standard choice for any production cluster — even single-DC — because it enables future DC expansion without a full restart.

#### Step 4 — Authentication (`cassandra.yaml`)

~~~yaml
authenticator: PasswordAuthenticator
authorizer: AllowAllAuthorizer      # switch to CassandraAuthorizer for per-keyspace GRANT control
role_manager: CassandraRoleManager
~~~

~~~bash
sed -i "s/^authenticator:.*/authenticator: PasswordAuthenticator/" "$CONF"
~~~

#### Step 5 — JMX (`cassandra-env.sh`)

By default `LOCAL_JMX=yes` restricts JMX to `127.0.0.1`, which is fine for most setups. If remote JMX access is required (monitoring, `nodetool` from another host):

~~~bash
# conf/cassandra-env.sh
LOCAL_JMX=yes    # keep 'yes' unless you need remote JMX (set to 'no' when enabling SSL — see hardening guide)

# Uncomment and set to the node's IP so remote nodetool can reach it
JVM_OPTS="$JVM_OPTS -Djava.rmi.server.hostname=<node_private_ip>"
~~~

Only needed for remote JMX — uncomment and set the RMI hostname to this node's IP:

~~~bash
sed -i "s|^#*JVM_OPTS=\"\$JVM_OPTS -Djava.rmi.server.hostname=.*|JVM_OPTS=\"\$JVM_OPTS -Djava.rmi.server.hostname=${NODE_IP:-$(hostname -I | awk '{print $1}')}\"|" "$ENV_SH"
~~~

Create the JMX password file (referenced when JMX authentication is enabled):

~~~bash
echo "k2admin changeit" > $CASSANDRA_HOME/conf/.jmxremote.password
chmod 400 $CASSANDRA_HOME/conf/.jmxremote.password
~~~

#### Step 6 — Graceful Stop (`bin/stop-server`)

Vanilla 4.1.x ships `bin/stop-server` as an empty stub. Replace it with a proper graceful drain so nodes shut down cleanly:

~~~bash
#!/bin/bash
NODETOOL="$CASSANDRA_HOME/bin/nodetool -u k2admin -pw changeit"
$NODETOOL status          # check node status
$NODETOOL disablegossip   # stop accepting new writes from other nodes
$NODETOOL flush           # flush memtables to disk
$NODETOOL drain           # flush + stop listening for client requests
$NODETOOL stopdaemon      # shut down the process
~~~

### First Boot and Post-Boot Configuration

#### Start Cassandra
Start Cassandra:
~~~bash
$CASSANDRA_HOME/bin/cassandra
~~~

> **Note (multi-node):** Start the **seed nodes first**, one by one (node 1, then node 2, then node 3). Once all seed nodes report `UN` (Up/Normal) in `nodetool status`, start the remaining nodes one by one. For a single node, just start Cassandra (its `seeds` is set to its own IP).

#### Create the Admin User (run once, after first boot)

Connect with the default credentials and create your own superuser:

~~~bash
cqlsh -u cassandra -p cassandra <node_ip>
~~~

~~~sql
-- Create the admin user
CREATE ROLE k2admin WITH PASSWORD = 'changeit' AND LOGIN = true AND SUPERUSER = true;
~~~

> **Important:** Verify that `k2admin` can log in (open a second session) **before** dropping the default `cassandra` role. Once dropped it cannot be recovered without restarting with `AllowAllAuthenticator`.

~~~sql
-- After confirming the new superuser works:
DROP ROLE cassandra;
~~~

#### System Keyspace Replication (multi-node only)

After all nodes are `UN`, update the system keyspaces from the default `SimpleStrategy` to `NetworkTopologyStrategy` (set `<RF>` to your replication factor — RF should equal the number of nodes, up to 3):

~~~sql
ALTER KEYSPACE system_auth        WITH REPLICATION = {'class': 'NetworkTopologyStrategy', 'DC1': <RF>};
ALTER KEYSPACE system_distributed WITH REPLICATION = {'class': 'NetworkTopologyStrategy', 'DC1': <RF>};
ALTER KEYSPACE system_traces      WITH REPLICATION = {'class': 'NetworkTopologyStrategy', 'DC1': <RF>};
~~~

Then run a repair to distribute the data:

~~~bash
$CASSANDRA_HOME/bin/nodetool -u k2admin -pw changeit repair -full
~~~

### Cassandra cluster - Start, Shutdown, and Status

* To **stop** the Cassandra cluster, run the graceful stop on each node (seed nodes should be shut down last):

    ~~~bash
    $CASSANDRA_HOME/bin/stop-server
    ~~~

* To **start** the Cassandra cluster, run the following command on each node, one by one (seed nodes should be started first):

    ~~~bash
    $CASSANDRA_HOME/bin/cassandra
    ~~~

* To check all node statuses, run the `nodetool` command:

    ~~~bash
    $CASSANDRA_HOME/bin/nodetool -u k2admin -pw changeit status
    ~~~

## SSL / TLS Hardening (Optional, Recommended for Production)

SSL/TLS configuration — certificate generation, inter-node and client encryption, `cqlshrc`, and JMX over SSL — is covered in the dedicated hardening guide:

[Cassandra Hardening Procedures](/articles/99_fabric_infras/04_cassandra_hardening.md)

## Summary of All Changes from Vanilla Defaults

| File | Setting | Vanilla default | Recommended value |
|------|---------|----------------|-------------------|
| `cassandra.yaml` | `listen_address` | `localhost` | node private IP |
| `cassandra.yaml` | `rpc_address` | `localhost` | `0.0.0.0` |
| `cassandra.yaml` | `broadcast_rpc_address` | *(commented)* | node private IP |
| `cassandra.yaml` | `seeds` | `127.0.0.1` | real seed IPs |
| `cassandra.yaml` | `cluster_name` | `Test Cluster` | custom name |
| `cassandra.yaml` | `endpoint_snitch` | `SimpleSnitch` | `GossipingPropertyFileSnitch` |
| `cassandra.yaml` | `authenticator` | `AllowAllAuthenticator` | `PasswordAuthenticator` |
| `cassandra-rackdc.properties` | `dc=` | `dc1` | `DC1` (or custom) |
| `cassandra-env.sh` | `LOCAL_JMX` | `yes` | `yes` / `no` (SSL) |
| `cassandra-env.sh` | `java.rmi.server.hostname` | *(commented)* | node private IP |
| `conf/.jmxremote.password` | *(not present)* | — | created with superuser credentials |
| `bin/stop-server` | *(stub)* | — | drain → stopdaemon |

**Post-boot CQL (always):** create the `k2admin` superuser and drop the default `cassandra` role.

**Multi-node:** update `system_auth`, `system_distributed`, and `system_traces` to `NetworkTopologyStrategy`, then run `nodetool repair -full`.

**SSL/TLS:** see the [Cassandra Hardening Procedures](/articles/99_fabric_infras/04_cassandra_hardening.md).
