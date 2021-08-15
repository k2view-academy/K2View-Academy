Fabric 6.xx Installation
  -----------------------------------------------------------------------
## Introduction 

Follow the instructions in this document to install Fabric in a production environment.

## Prerequisites 

Minimum hardware for each Linux execution server is as follows:

-   Modern Xeon Processor

-   16 Physical Cores

-   RAM:

    -   For servers that run all components (Fabric, Cassandra & Kafka) - 64GB RAM.

    -   For servers that run single component (preferred method) - 32GB RAM.

-   Network: Minimum 1G per sec between the nodes and source databases.

-   Storage: The preferred storage is attached local SSD's in a non-RAID configuration.

> **Note:** If you must use SAN, it must be flash and in RAID-0.\
> **NAS are not certified.**

Operating System: Redhat/CentOS latest Operating System and above, with latest patches.

The following File Server volumes must be made available:

-   Volume of 50G /opt/apps/fabric/ will be use also as the home directory for k2view user

-   Volume of 100G\* **/opt/apps/fabric/storage**

-   Volume of 50G **/opt/apps/cassandra/**

-   Volume of 2T\* **/opt/apps/cassandra/storage/data**

-   Volume of 10% of data volume - **/opt/apps/cassandra/storage/hints**

-   Volume of 25% of data volume - **/opt/apps/cassandra/storage/commitlog**

-   Volume of 100G\* **/opt/apps/kafka/**

> **Note:** The file server must provide IOPS of at least 30K read & 10K write.
> The number of servers should be increased based on project scope and data retention requirements.

Add the following users:

~~~bash
mkdir -p /opt/apps
chmod 755 /opt/apps
useradd -m -d /opt/apps/fabric fabric
useradd -m -d /opt/apps/cassandra cassandra
useradd -m -d /opt/apps/kafka kafka

## Update the OS limits as follows:
echo \"root soft    nproc     unlimited\" \>\> /etc/security/limits.conf
echo \"cassandra - nofile 100000\" \>\> /etc/security/limits.conf
echo \"cassandra - nproc 50000\" \>\> /etc/security/limits.conf
echo \"fabric - nofile 100000\" \>\> /etc/security/limits.conf
echo \"fabric - nproc 50000\" \>\> /etc/security/limits.conf
echo \"kafka hard nofile 100000\" \>\> /etc/security/limits.conf
echo \"kafka soft nofile 100000\" \>\> /etc/security/limits.conf
echo \"kafka - nproc 50000\" \>\> /etc/security/limits.conf
~~~

#### Update /etc/sysctl.conf ####

~~~bash
echo \"## Added by K2view - GabiO\" \>\> /etc/sysctl.conf
echo \"vm.max_map_count = 1048575\" \>\> /etc/sysctl.conf
echo \"fs.file-max = 500000\" \>\> /etc/sysctl.conf
~~~

