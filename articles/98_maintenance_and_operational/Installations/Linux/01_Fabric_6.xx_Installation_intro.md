# Fabric 6.xx Installation

## Introduction 

Follow the instructions here to install Fabric in a production environment.

## Prerequisites 

Minimum hardware for each Linux execution server is as follows:

-   Modern Xeon Processor

-   16 Physical Cores

-   RAM:

    -   For servers that run all components (Fabric, Cassandra & Kafka) - 64GB RAM.

    -   For servers that run one component (preferred method) - 32GB RAM.

-   Network: Minimum 1G per sec between the nodes and source databases.

-   Storage: The preferred storage is attached local SSD's in a non-RAID configuration.

> **Note:** If you must use a SAN, it must be flash and in RAID-0.
> **NAS are not certified.**

Operating System: Redhat/CentOS/"Amazon Linux" latest Operating System and above, with latest patches.

The following File Server volumes must be made available:

-   Volume of 50G /opt/apps/fabric/ will also be used as the home directory for a **fabric** user (Owned by fabric user)

-   Volume of 100G\* **/opt/apps/fabric/storage** (Owned by fabric user)

-   Volume of 50G **/opt/apps/cassandra/** (Owned by cassandra user)

-   Volume of 2T\* **/opt/apps/cassandra/storage/data** (Owned by cassandra user)

-   Volume of 10% of data volume - **/opt/apps/cassandra/storage/hints** (Owned by cassandra user)

-   Volume of 25% of data volume - **/opt/apps/cassandra/storage/commitlog** (Owned by cassandra user)

-   Volume of 100G\* **/opt/apps/kafka/** (Owned by kafka user)

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
echo "root soft    nproc     unlimited" >> /etc/security/limits.conf
echo "cassandra - nofile 100000" >> /etc/security/limits.conf
echo "cassandra - nproc 50000" >> /etc/security/limits.conf
echo "fabric - nofile 100000" >> /etc/security/limits.conf
echo "fabric - nproc 50000" >> /etc/security/limits.conf
echo "kafka hard nofile 100000" >> /etc/security/limits.conf
echo "kafka soft nofile 100000" >> /etc/security/limits.conf
echo "kafka - nproc 50000" >> /etc/security/limits.conf
echo "kafka soft nofile 100000" >> /etc/security/limits.conf
echo "kafka - nproc 50000" >> /etc/security/limits.conf
~~~

#### Update /etc/sysctl.conf ####

~~~bash
echo "## Added by K2view - GabiO" >> /etc/sysctl.conf
echo "vm.max_map_count = 1048575" >> /etc/sysctl.conf
echo "fs.file-max = 1000000" >> /etc/sysctl.conf
echo "net.ipv4.tcp_keepalive_time = 60" >> /etc/sysctl.conf
echo "net.ipv4.tcp_keepalive_probes = 3" >> /etc/sysctl.conf
echo "net.ipv4.tcp_keepalive_intvl = 10" >> /etc/sysctl.conf
sysctl -p
~~~



#### Add the following packages for RHEL/CentOs 8

~~~bash
dnf install -y compat-openssl10 readline* python2 glibc-locale-source glibc-langpack-en
ln -s /usr/lib64/libreadline.so /usr/lib64/libreadline.so.6

~~~

> **Note:** To verify whether what is your current  RHEL/Centos version, run one of the followinng commands:
~~~bash
rpm -E %{rhel}
hostnamectl
cat /etc/os-release
~~~

### Linux Ports 

Open the following ports on the LINUX server, and make sure they are accessible from outside the server: 

<table style="border-collapse: collapse; width: 100%;">
<tbody>
<tr>
<td style="width: 50%; height: 18px;">22</td>
<td style="width: 50%; height: 18px;">SSH, SCP</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">7000</td>
<td style="width: 50%; height: 18px;">Cassandra</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">7001</td>
<td style="width: 50%; height: 18px;">Cassandra</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">7199</td>
<td style="width: 50%; height: 18px;">Cassandra</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">9042</td>
<td style="width: 50%; height: 18px;">Cassandra</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">9160</td>
<td style="width: 50%; height: 18px;">Cassandra</td>
</tr>
<tr>
<td style="width: 50%; height: 11px;">3213, 9443</td>
<td style="width: 50%; height: 11px;">K2View Fabric</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">6379</td>
<td style="width: 50%; height: 18px;">Redis</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">2181, 2888, 3888</td>
<td style="width: 50%; height: 18px;">Kafka Zookeeper</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">9093, 9091 </td>
<td style="width: 50%; height: 18px;">Kafka</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">9081</td>
<td style="width: 50%; height: 18px;">Kafka JMX</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">5124</td>
<td style="width: 50%; height: 18px;">K2View Fabric</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">5432</td>
<td style="width: 50%; height: 18px;">pdsql</td>
</tr>
</tbody>
</table>



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_Fabric_6.xx_Setup_Single_node.md)  
