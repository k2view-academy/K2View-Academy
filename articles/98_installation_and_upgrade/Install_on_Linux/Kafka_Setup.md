# Apache Kafka Cluster Setup

This guide describes how to install and configure **Confluent Platform 8.x (Community components)**, which provides Apache Kafka in **KRaft** mode, for either single-node or multi-node environments.

Apply the configuration on each node separately, in the order of the designated node numbers (start with node 1, then continue one by one). Do not configure the nodes simultaneously, as this might cause configuration issues.

**Official reference:** https://docs.confluent.io/platform/current/installation/installing_cp/zip-tar.html

## Version Considerations

This guide targets **Confluent Platform 8.x** (the examples use `8.4.0`).

- **Java 17 or 21** must be on the `PATH` (verify with `java -version`). Java 21 is recommended. Confluent Platform 8.x does not support Java 11.
- Kafka runs in ***KRaft*** mode only. Starting from Confluent Platform 8.0 (Apache Kafka 4.0), ZooKeeper is no longer supported. KRaft does its own metadata management, so ZooKeeper is neither installed nor configured.
- The **Community** package (`confluent-community-*`) runs the open-source Apache Kafka broker and does not need a Confluent license. The full `confluent-*` package runs Confluent Server, which needs a license for multi-broker clusters after its trial period. Use it only if you have one.

## Pre-Installation Steps

1. Make sure all Kafka-related activities were performed; click [here](01_Fabric_8.xx_Installation_intro.md) to get the full list of activities.

2. It is recommended to run Kafka under a dedicated `kafka` user (in the `k2view` group) with its home/installation directory at `/opt/apps/kafka/`:

    ~~~bash
    mkdir -p /opt/apps
    groupadd -f k2view
    useradd -m -d /opt/apps/kafka -g k2view -s /bin/bash kafka
    chown -R kafka:k2view /opt/apps/kafka
    ~~~

3. Install the latest version of Java 21 (or 17), either Oracle Java SE or an OpenJDK build (see the [Confluent system requirements](https://docs.confluent.io/platform/current/installation/system-requirements.html)). Download from:

    - Oracle JDK 21: https://www.oracle.com/java/technologies/downloads/#java21
    - OpenJDK 21 (Eclipse Temurin): https://adoptium.net/temurin/releases/?version=21

    To verify that you have the correct version of Java installed, type `java -version`:

    ~~~bash
    java -version
    ~~~

    > *Recommended:* Set `JAVA_HOME` and add its `bin` to the `PATH` in the `kafka` user's `.bash_profile` so Kafka can always locate the JVM (point the path at your JDK 21 install location):
    >
    > ~~~bash
    > echo "export JAVA_HOME=/opt/apps/kafka/jdk-21" >> ~/.bash_profile
    > echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.bash_profile
    > source ~/.bash_profile
    > ~~~

### Download and Install on all Nodes

1. Log in as the user designated for the Kafka installation (`kafka`).

2. Download the **Confluent Platform 8.x Community** tarball from the official site (https://packages.confluent.io/archive/). For example:

    ~~~bash
    cd /opt/apps/kafka
    curl -OL https://packages.confluent.io/archive/8.4/confluent-community-8.4.0.tar.gz
    ~~~

3. Extract the package, create a version-independent `confluent` symlink, and set `CONFLUENT_HOME` to point at it:

    ~~~bash
    tar -zxvf confluent-community-8.4.0.tar.gz -C /opt/apps/kafka
    ln -sfn /opt/apps/kafka/confluent-8.4.0 /opt/apps/kafka/confluent
    export CONFLUENT_HOME=/opt/apps/kafka/confluent
    ~~~

    > Using the `confluent` symlink as `CONFLUENT_HOME` keeps all paths version-independent. To upgrade later, extract the new release and repoint the link (`ln -sfn`). Because the configuration and data directories below live outside the release directory, they carry over to the new release unchanged.

    > *Tip:* Persist `CONFLUENT_HOME` and add it to the `PATH` in the `kafka` user's `.bash_profile` so the `kafka-*` commands are always available:
    >
    > ~~~bash
    > echo "export CONFLUENT_HOME=/opt/apps/kafka/confluent" >> ~/.bash_profile
    > echo 'export PATH=$CONFLUENT_HOME/bin:$PATH' >> ~/.bash_profile
    > source ~/.bash_profile
    > ~~~

> **Note:** The commands above use `8.4.0` as an example. Substitute the Confluent Platform 8.x release you are deploying in the download URL and paths. Always download from the official Confluent archive and follow the [Confluent Platform documentation](https://docs.confluent.io/platform/current/overview.html) for version-specific details.

### Configure the Kafka Nodes

Each node runs in **combined mode**: it is both a broker (serves Fabric clients) and a KRaft controller (manages cluster metadata). All nodes are controllers and together form the controller quorum.

> Confluent recommends separate broker and controller nodes (at least three of each) for large production deployments. If you use separate nodes, configure them from `broker.properties` and `controller.properties` as described in the [official reference](https://docs.confluent.io/platform/current/installation/installing_cp/zip-tar.html#configure-cp).

**Required details:**
* Node IPs
* A unique node ID for each node (`1`, `2`, `3`, ...)
* Replication factor. The number of cluster nodes must be greater than or equal to the replication factor.

**Ports:**
* `9093`: client and inter-broker traffic (Fabric connects to this port)
* `9094`: KRaft controller traffic between nodes

1. Copy the shipped sample configuration to a directory outside the release, and create the data directory:

    ~~~bash
    mkdir -p /opt/apps/kafka/config /opt/apps/kafka/data
    cp $CONFLUENT_HOME/etc/kafka/server.properties /opt/apps/kafka/config/server.properties
    ~~~

2. Set the variables that the `sed` commands below reference. Edit the values for this node. The example is for node 1 of a 3-node cluster:

    ~~~bash
    export KAFKA_CONF=/opt/apps/kafka/config/server.properties

    export NODE_IP=10.0.0.1     # this node's IP
    export NODE_ID=1            # unique per node: 1, 2, 3, ...
    export VOTERS="1@10.0.0.1:9094,2@10.0.0.2:9094,3@10.0.0.3:9094"   # all nodes as <node_id>@<ip>:9094; identical on every node
    export RF=3                 # replication factor
    export MIN_ISR=2            # minimum in-sync replicas: 2 when RF is 3, 1 when RF is 1
    ~~~

    For a **single node**, use `NODE_ID=1`, `VOTERS="1@<node_ip>:9094"`, `RF=1` and `MIN_ISR=1`.

#### Step 1 — Node Identity and Quorum (`server.properties`)

~~~properties
process.roles=broker,controller
# Unique per node; must match this node's entry in controller.quorum.voters
node.id=1
# Static quorum: every controller, as <node.id>@<ip>:<controller port>
controller.quorum.voters=1@10.0.0.1:9094,2@10.0.0.2:9094,3@10.0.0.3:9094
~~~

~~~bash
sed -i "s/^node.id=.*/node.id=${NODE_ID}/" "$KAFKA_CONF"
sed -i "s/^controller.quorum.bootstrap.servers=.*/controller.quorum.voters=${VOTERS}/" "$KAFKA_CONF"
~~~

> The sample file uses a *dynamic* quorum (`controller.quorum.bootstrap.servers`). This guide replaces it with a *static* quorum (`controller.quorum.voters`), which is simpler for a cluster with a fixed set of nodes. To add or replace controllers without reconfiguring the cluster, see [KRaft dynamic quorums](https://docs.confluent.io/platform/current/kafka-metadata/config-kraft.html).

#### Step 2 — Network (`server.properties`)

~~~properties
# Client listener on all interfaces; controller listener on the node's IP
listeners=PLAINTEXT://0.0.0.0:9093,CONTROLLER://10.0.0.1:9094

# Advertised address for clients. Must be reachable from the Fabric nodes (never 0.0.0.0)
advertised.listeners=PLAINTEXT://10.0.0.1:9093
~~~

~~~bash
sed -i "s|^listeners=.*|listeners=PLAINTEXT://0.0.0.0:9093,CONTROLLER://${NODE_IP}:9094|" "$KAFKA_CONF"
sed -i "s|^advertised.listeners=.*|advertised.listeners=PLAINTEXT://${NODE_IP}:9093|" "$KAFKA_CONF"
~~~

> Setting the `PLAINTEXT` listener to `0.0.0.0` makes Kafka listen on all interfaces. In that case `advertised.listeners` **must** hold the node's reachable IP, because that is the address returned to clients. `inter.broker.listener.name=PLAINTEXT` and `controller.listener.names=CONTROLLER` keep their sample values.

#### Step 3 — Data Directory (`server.properties`)

~~~properties
log.dirs=/opt/apps/kafka/data
~~~

~~~bash
sed -i "s|^log.dirs=.*|log.dirs=/opt/apps/kafka/data|" "$KAFKA_CONF"
~~~

> The sample value `/tmp/kraft-combined-logs` is not suitable for real use: `/tmp` may be cleaned on reboot, which would wipe all topic data and cluster metadata.

#### Step 4 — Replication (`server.properties`)

~~~properties
default.replication.factor=3
min.insync.replicas=2
offsets.topic.replication.factor=3
transaction.state.log.replication.factor=3
transaction.state.log.min.isr=2
share.coordinator.state.topic.replication.factor=3
share.coordinator.state.topic.min.isr=2
~~~

~~~bash
sed -i "s/^offsets.topic.replication.factor=.*/offsets.topic.replication.factor=${RF}/" "$KAFKA_CONF"
sed -i "s/^transaction.state.log.replication.factor=.*/transaction.state.log.replication.factor=${RF}/" "$KAFKA_CONF"
sed -i "s/^transaction.state.log.min.isr=.*/transaction.state.log.min.isr=${MIN_ISR}/" "$KAFKA_CONF"
sed -i "s/^share.coordinator.state.topic.replication.factor=.*/share.coordinator.state.topic.replication.factor=${RF}/" "$KAFKA_CONF"
sed -i "s/^share.coordinator.state.topic.min.isr=.*/share.coordinator.state.topic.min.isr=${MIN_ISR}/" "$KAFKA_CONF"
grep -q "^default.replication.factor=" "$KAFKA_CONF" || echo "default.replication.factor=${RF}" >> "$KAFKA_CONF"
grep -q "^min.insync.replicas=" "$KAFKA_CONF" || echo "min.insync.replicas=${MIN_ISR}" >> "$KAFKA_CONF"
~~~

> `default.replication.factor` applies to topics that are created automatically (for example, by Fabric). With a replication factor of 3 and `min.insync.replicas=2`, the cluster keeps accepting writes while one node is down.

#### Step 5 — Heap Size (optional)

By default, `kafka-server-start` starts the JVM with a 1 GB heap. To change it, set `KAFKA_HEAP_OPTS` in the `kafka` user's `.bash_profile` (adjust the values to the server's memory):

~~~bash
echo 'export KAFKA_HEAP_OPTS="-Xms4g -Xmx4g"' >> ~/.bash_profile
source ~/.bash_profile
~~~

### Format Storage and First Boot

Kafka does not format blank storage directories or generate a cluster ID on its own. Each node's storage must be formatted once, with the **same cluster ID**, before Kafka first starts.

1. On the **first node only**, generate the cluster ID:

    ~~~bash
    $CONFLUENT_HOME/bin/kafka-storage random-uuid
    ~~~

    Copy the output (for example, `q1Sh-9_ISia_zwGINzRvyQ`). You will use it on every node.

2. On **each node**, format the storage with that cluster ID:

    ~~~bash
    $CONFLUENT_HOME/bin/kafka-storage format -t <cluster_id> -c $KAFKA_CONF
    ~~~

    > Format a node only once. Running `format` again on a node that already holds data fails. Do not remove the data directory to work around this, as doing so erases the node's data.

3. Start Kafka on each node, one by one, in the order of the node numbers:

    ~~~bash
    $CONFLUENT_HOME/bin/kafka-server-start -daemon $KAFKA_CONF
    ~~~

    > **Note (multi-node):** The controllers elect a leader only after a majority of nodes are up (2 out of 3). Until then, the first node keeps retrying and logs connection warnings in `$CONFLUENT_HOME/logs/server.log`. This is expected. For a single node, the node is its own quorum and starts right away.

4. To validate that Kafka is running properly on all nodes:

    *See item **"check the cluster's health"** in section **"Kafka Cluster - Start, Shutdown and Monitor"** below*.

### Kafka Cluster - Start, Shutdown and Monitor

* To **stop** Kafka, run the following command on each node:

    ~~~bash
    $CONFLUENT_HOME/bin/kafka-server-stop
    ~~~

* To **start** Kafka, run the following command on each node, one by one:

    ~~~bash
    $CONFLUENT_HOME/bin/kafka-server-start -daemon $KAFKA_CONF
    ~~~

* To verify that Kafka is running on a specific node:

    ~~~bash
    ps -ef | grep [k]afka.Kafka
    $CONFLUENT_HOME/bin/kafka-broker-api-versions --bootstrap-server localhost:9093 | head -1
    ~~~

* To **check the cluster's health**, run the following command:

    ~~~bash
    $CONFLUENT_HOME/bin/kafka-metadata-quorum --bootstrap-server localhost:9093 describe --status
    ~~~

    Output similar to the following should be displayed:
    ~~~
    ClusterId:              q1Sh-9_ISia_zwGINzRvyQ
    LeaderId:               1
    LeaderEpoch:            13
    HighWatermark:          2866
    MaxFollowerLag:         0
    MaxFollowerLagTimeMs:   0
    CurrentVoters:          [{"id": 1, ...}, {"id": 2, ...}, {"id": 3, ...}]
    CurrentObservers:       []
    ~~~

    All nodes should be listed under `CurrentVoters`, and `MaxFollowerLag` should be at or near `0`.

## SSL / TLS Hardening (Optional, Recommended for Production)

SSL/TLS configuration is covered in the dedicated hardening guides:

* [Kafka Hardening](/articles/99_fabric_infras/06_kafka_hardening.md)
* [Kafka PLAIN SASL Hardening](/articles/99_fabric_infras/11_kafka_plain_sasl_hardening.md)

## Summary of All Changes from the Sample Configuration

All changes are made in `/opt/apps/kafka/config/server.properties`, a copy of `$CONFLUENT_HOME/etc/kafka/server.properties`.

| Setting | Sample default | Recommended value |
|---------|----------------|-------------------|
| `node.id` | `1` | unique per node (`1`, `2`, `3`, ...) |
| `controller.quorum.bootstrap.servers` | `localhost:9093` | *(replaced by `controller.quorum.voters`)* |
| `controller.quorum.voters` | *(not present)* | `<id>@<ip>:9094` for every node |
| `listeners` | `PLAINTEXT://:9092,CONTROLLER://:9093` | `PLAINTEXT://0.0.0.0:9093,CONTROLLER://<node_ip>:9094` |
| `advertised.listeners` | `PLAINTEXT://localhost:9092,CONTROLLER://localhost:9093` | `PLAINTEXT://<node_ip>:9093` |
| `log.dirs` | `/tmp/kraft-combined-logs` | `/opt/apps/kafka/data` |
| `offsets.topic.replication.factor` | `1` | replication factor |
| `transaction.state.log.replication.factor` | `1` | replication factor |
| `transaction.state.log.min.isr` | `1` | `2` if RF ≥ 3, else `1` |
| `share.coordinator.state.topic.replication.factor` | `1` | replication factor |
| `share.coordinator.state.topic.min.isr` | `1` | `2` if RF ≥ 3, else `1` |
| `default.replication.factor` | *(not present, `1`)* | replication factor |
| `min.insync.replicas` | *(not present, `1`)* | `2` if RF ≥ 3, else `1` |

**Before first boot (always):** generate one cluster ID and run `kafka-storage format` with it on every node.

**SSL/TLS:** see the [Kafka hardening guides](#ssl--tls-hardening-optional-recommended-for-production).
