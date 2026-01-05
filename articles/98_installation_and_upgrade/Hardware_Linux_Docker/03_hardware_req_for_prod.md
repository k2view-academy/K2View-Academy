# Production Environment Installation System Requirements

## Table of Contents

  - [Introduction](#introduction)
  - [Fabric Execution Servers - Node Specifications](#fabric-execution-servers---node-specifications)
  - [Minimum System Configuration for Each Fabric Node Server](#minimum-system-configuration-for-each-fabric-node-server)
    - [Operating system](#operating-system)
    - [Hardware Requirements](#hardware-requirements)
    - [Examples of Cloud-supported Instance Types](#examples-of-cloud-supported-instance-types)
    - [For all installations, the file system configuration must be as follows](#for-all-installations-the-file-system-configuration-must-be-as-follows)
  - [PostgreSQL Server Node Specifications](#postgresql-server-node-specifications)
    - [Suggested System Configuration for a PostgreSQL Server](#suggested-system-configuration-for-a-postgresql-server)
      - [Operating system](#operating-system-3)
      - [Hardware Requirements](#hardware-requirements-3)
      - [Examples of Cloud-supported Instance Types](#examples-of-cloud-supported-instance-types-3)
      - [Examples of Cloud-supported SaaS](#examples-of-cloud-supported-saas)
  - [Kafka Server Node Specifications](#kafka-server-node-specifications)
    - [Minimum System Configuration for Each Kafka Node Server](#minimum-system-configuration-for-each-kafka-node-server)
      - [Operating system](#operating-system-1)
      - [Hardware Requirements](#hardware-requirements-1)
      - [Examples of Cloud-supported Instance Types](#examples-of-cloud-supported-instance-types-1)
      - [For all installations, the file system configuration must be as follows](#for-all-installations-the-file-system-configuration-must-be-as-follows-1)
  - [Cassandra Server Node Specifications](#cassandra-server-node-specifications)
    - [Minimum System Configuration for Each Cassandra Node Server](#minimum-system-configuration-for-each-cassandra-node-server)
      - [Operating system](#operating-system-2)
      - [Hardware Requirements](#hardware-requirements-2)
      - [Examples of Cloud-supported Instance Types](#examples-of-cloud-supported-instance-types-2)
      - [For all installations, the file system configuration should be as follows](#for-all-installations-the-file-system-configuration-should-be-as-follows)




## Introduction

This document provides the hardware, software, and operating system requirements for deploying Fabric in a production environment. Unlike Proof-of-Value or Dev/QA installations, production systems must be sized for sustained workloads, high availability, and fault tolerance.

TDM installations build on these requirements by adding the TDM library and PostgreSQL. Each component—Fabric, PostgreSQL, Kafka, and Cassandra — has its own node specifications, which can be deployed on-premises or mapped to equivalent cloud instance types.

## Fabric Execution Servers - Node Specifications 

The Fabric server installation is a Linux-based solution.
Use dedicated servers for Fabric instances. Following a sizing process, the exact number of nodes for each environment needs to be determined. 


## Minimum System Configuration for Each Fabric Node Server

### Software Requirements:

### Operating system: 

* RedHat (version 8 or higher) based distribution (for example: Centos, Oracle, Linux).   
* Ubuntu Server, version 22 or higher. 


### Hardware Requirements:

<table>
<tbody>
<tr>
<td style="width: 224px;">
<p><strong>Processor</strong></p>
</td>
<td style="width: 446px;">
<p>For simple scenarios: Intel Xeon 8 core or equivalent.</p>
<p>For high-load or complex scenarios: 16 cores are recommended.</p>
</td>
</tr>
<tr>
<td style="width: 224px;">
<p><strong>Physical Memory </strong><strong>(RAM)</strong></p>
</td>
<td style="width: 446px;">
<p> 32 GB RAM</p>
</td>
</tr>
<tr>
<td style="width: 224px;">
<p><strong>Storage</strong></p>
</td>
<td style="width: 446px;">
<p>Direct-attached SSD/NVMe-based storage is recommended for best performance.</p>
<p><strong>SAN (Storage Area Network)</strong> is supported only with flash-based disks, and RAID-0 is the preferred disk chain method.</p>
<p><strong>NAS (Network Attached Storage) is not certified.</strong>
</td>
</tr>
<tr>
<td style="width: 224px;">
<p><strong>Disk Space</strong></p>
</td>
<td style="width: 446px;">
<p>For application data, at least 150 GB free disk space is recommended.</p>
<p>Disk space should be increased according to the project scope and retention requirements.</p>
</td>
</tr>
<tr>
<td style="width: 224px;">
<p><strong>Network</strong></p>
</td>
<td style="width: 446px;">
<p>1 Gbps network speed between the Fabric nodes, Cassandra Nodes, Kafka nodes, and source and destination DBs.</p>
</td>
</tr>
</tbody>
</table>




### Examples of Cloud-supported Instance Types:

The following specifications define the on-premises baseline hardware requirements. Equivalent instance types across AWS, GCP, and Azure are provided for reference to simplify cloud sizing.

<table>
<tbody>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Cloud Provider</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p><strong>Instance Type</strong></p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Amazon - AWS</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p>m5.2xlarge</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Google - GCP</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p>e2-standard-8</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Microsoft - Azure</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p>D8d v5</p>
</td>
</tr>
</tbody>
</table>


### For all installations, the file system configuration must be as follows:

* Volume of 50GB/opt/apps/fabric/ - will also be used as the home directory for a Fabric user.
* Volume of 100GB /opt/apps/fabric/storage

### Note:
* The file system must provide IOPS of at least 30K read and 10K write on each node. 
* The above volume values should be modified in accordance with the project scope and data retention requirements.


### Connectivity

Fabric server should have access (permissions) and connectivity (network) to all sources and destinations.
Cassandra and Kafka servers should be accessible from Fabric servers’ nodes.


## PostgreSQL Server Node Specifications 

* PostgreSQL is required for TDM projects only.
* K2view supports the PostgreSQL version certified for a specific Fabric version. Refer to the <a href="/articles/Product_Versions
/Fabric_versions.md">Fabric Versions</a> page.
* PostgreSQL can be deployed in one of the following two ways:
	* On-premises as a virtual or a physical machine. 
	* As a cloud service (SaaS)

### Suggested System Configuration for a PostgreSQL Server

### Operating system: 

* RedHat (version 8 or higher) based distribution (for example: Centos, Oracle, Linux).   
* Ubuntu Server, version 22 or higher. 

### Hardware Requirements:
<table>
<tbody>
<tr>
<td style="width: 224px;">
<p><strong>Processor</strong></p>
</td>
<td style="width: 446px;">
<p>Intel Xeon 4 core or equivalent.</p>
</td>
</tr>
<tr>
<td style="width: 224px;">
<p><strong>Physical Memory </strong><strong>(RAM)</strong></p>
</td>
<td style="width: 446px;">
<p> 16 GB RAM</p>
</td>
</tr>
<tr>
<td style="width: 224px;">
<p><strong>Storage</strong></p>
</td>
<td style="width: 446px;">
<p>Direct-attached SSD/NVMe-based storage is recommended for best performance.</p>
<p><strong>SAN (Storage Area Network)</strong> is supported only with flash-based disks, and RAID-0 is the preferred disk chain method.</p>
<p><strong>NAS (Network Attached Storage) is not certified.</strong>
</td>
</tr>
<tr>
<td style="width: 224px;">
<p><strong>Disk Space</strong></p>
</td>
<td style="width: 446px;">
<p>For application data, at least 100 GB free disk space is required.</p>
<p>Disk space can be increased according to the project scope and retention requirements.</p>
</td>
</tr>
<tr>
<td style="width: 224px;">
<p><strong>Network</strong></p>
</td>
<td style="width: 446px;">
<p>1 Gbps network speed between the PostgreSQL and Fabric nodes.</p>
</td>
</tr>
</tbody>
</table>

### Examples of Cloud-supported Instance Types:

The following specifications define the on-premises baseline hardware requirements. Equivalent instance types across AWS, GCP, and Azure are provided for reference to simplify cloud sizing.

<table>
<tbody>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Cloud Provider</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p><strong>Instance Type</strong></p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Amazon - AWS</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p>m5.xlarge</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Google - GCP</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p>e2-standard-4</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Microsoft - Azure</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p>D4d v5</p>
</td>
</tr>
</tbody>
</table>

### Examples of Cloud-supported SaaS 
* AWS RDS PostgreSQL - read more [here](https://aws.amazon.com/free/database/?trk=492c57d3-8cdc-4660-b6ac-d2008bd51b40&sc_channel=ps&ef_id=Cj0KCQjwmtGjBhDhARIsAEqfDEcolBtzTdv_q0Ob8_Xda3524RZfaxKgGOzqoi1IVjieHyRiSYgUEr0aAk12EALw_wcB:G:s&s_kwcid=AL!4422!3!645125273471!e!!g!!aws%20postgre%20sql!19574556899!145779849512)
* GCP Cloud SQL PostgreSQL - read more [here](https://cloud.google.com/sql/docs/postgres/introduction)
* Azure Database for PostgreSQL - read more [here](https://azure.microsoft.com/en-us/products/postgresql/?ef_id=_k_Cj0KCQjwmtGjBhDhARIsAEqfDEdFvRBFcGSocBebegdYAH-KKrEjh3YxAuG0vKhGbQ0djHuzAPbdhMsaAolmEALw_wcB_k_&OCID=AIDcmm81syc84i_SEM__k_Cj0KCQjwmtGjBhDhARIsAEqfDEdFvRBFcGSocBebegdYAH-KKrEjh3YxAuG0vKhGbQ0djHuzAPbdhMsaAolmEALw_wcB_k_&gclid=Cj0KCQjwmtGjBhDhARIsAEqfDEdFvRBFcGSocBebegdYAH-KKrEjh3YxAuG0vKhGbQ0djHuzAPbdhMsaAolmEALw_wcB)


## Kafka Server Node Specifications 

The Kafka server installation is a Linux-based solution.
Use dedicated servers for Kafka instances. Following a sizing process, the exact number of nodes for each environment needs to be determined. 

### Minimum System Configuration for Each Kafka Node Server

### Operating system: 

* RedHat (version 8 or higher) based distribution (for example: Centos, Oracle, Linux).   
* Ubuntu Server, version 22 or higher. 

#### Kafka Version: 
K2view supports the Kafka version certified for a specific Fabric version. Refer to the <a href="/articles/Product_Versions
/Fabric_versions.md">Fabric Versions</a> page for links to the associated release notes where version info is provided.

### Hardware Requirements:
<table>
<tbody>
<tr>
<td style="width: 224px;">
<p><strong>Processor</strong></p>
</td>
<td style="width: 446px;">
<p>For simple scenarios: Intel Xeon 8 core or equivalent.</p>
<p>For high-load or complex scenarios: 16 cores are&nbsp; recommended.</p>
</td>
</tr>
<tr>
<td style="width: 224px;">
<p><strong>Physical Memory </strong><strong>(RAM)</strong></p>
</td>
<td style="width: 446px;">
<p> 32 GB RAM</p>
</td>
</tr>
<tr>
<td style="width: 224px;">
<p><strong>Storage</strong></p>
</td>
<td style="width: 446px;">
<p>Direct-attached SSD/NVMe-based storage is recommended for best performance.</p>
<p><strong>SAN (Storage Area Network)</strong> is supported only with flash-based disks, and RAID-0 is the preferred disk chain method.</p>
<p><strong>NAS (Network Attached Storage) is not certified.</strong>
</td>
</tr>
<tr>
<td style="width: 224px;">
<p><strong>Disk Space</strong></p>
</td>
<td style="width: 446px;">
<p>For application data, at least 150 GB free disk space is recommended.</p>
<p>Disk space should be increased according to the project scope and retention requirements.</p>
</td>
</tr>
<tr>
<td style="width: 224px;">
<p><strong>Network</strong></p>
</td>
<td style="width: 446px;">
<p>1 Gbps network speed between the Kafka nodes and Fabric nodes.</p>
</td>
</tr>
</tbody>
</table>

### Examples of Cloud-supported Instance Types:

The following specifications define the on-premises baseline hardware requirements. Equivalent instance types across AWS, GCP, and Azure are provided for reference to simplify cloud sizing.

<table>
<tbody>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Cloud Provider</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p><strong>Instance Type</strong></p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Amazon - AWS</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p>m5.2xlarge</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Google - GCP</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p>e2-standard-8</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Microsoft - Azure</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p>D8d v5</p>
</td>
</tr>
</tbody>
</table>

### For all installations, the file system configuration must be as follows:
Volume of 150GB /opt/apps/kafka/ - will be used also as the home directory for a Kafka user.

### Note:
* The file system must provide IOPS of at least 30K read and 10K write on each node.
* The above volume values should be modified in accordance with the project scope and data retention requirements.


## Cassandra Server Node Specifications 

The Cassandra server installation is a Linux-based solution.
Use dedicated servers for Cassandra instances. Following a sizing process, the exact number of nodes for each environment needs to be determined. 

### Minimum System Configuration for Each Cassandra Node Server

### Operating system: 

* RedHat (version 8 or higher) based distribution (for example: Centos, Oracle, Linux).   
* Ubuntu Server, version 22 or higher. 


### Cassandra Versions Supported

K2view supports the Cassandra version certified for a specific Fabric version. Refer to the <a href="/articles/Product_Versions
/Fabric_versions.md">Fabric Versions</a> page.

### Python version requirement
Please ensure the matching Python version is available on all nodes where cqlsh or Cassandra tools are used.

- Cassandra 3.11.14 requires Python 2.7.
- Cassandra 4.0.3 and 4.1.3 require Python 3.6 or higher.

### Hardware Requirements:
<table>
<tbody>
<tr>
<td style="width: 224px;">
<p><strong>Processor</strong></p>
</td>
<td style="width: 446px;">
<p>For simple scenarios: Intel Xeon CPU or equivalent with 16 cores.</p>
</td>
</tr>
<tr>
<td style="width: 224px;">
<p><strong>Physical Memory </strong><strong>(RAM)</strong></p>
</td>
<td style="width: 446px;">
<p> 32 GB RAM</p>
</td>
</tr>
<tr>
<td style="width: 224px;">
<p><strong>Storage</strong></p>
</td>
<td style="width: 446px;">
<p>Direct-attached SSD/NVMe-based storage is recommended for best performance.</p>
<p><strong>SAN (Storage Area Network)</strong> is supported only with flash-based disks, and RAID-0 is the preferred disk chain method.</p>
<p><strong>NAS (Network Attached Storage) is not certified.</strong>
</td>
</tr>
<tr>
<td style="width: 224px;">
<p><strong>Disk Space</strong></p>
</td>
<td style="width: 446px;">
<p>For application data, at least 500 GB free disk space is recommended.</p>
<p>Disk space should be increased according to the project scope and retention requirements.</p>
</td>
</tr>
<tr>
<td style="width: 224px;">
<p><strong>Network</strong></p>
</td>
<td style="width: 446px;">
<p>1 Gbps network speed between the Kafka nodes and Fabric nodes.</p>
</td>
</tr>
</tbody>
</table>

### Examples of Cloud-supported Instance Types:

The following specifications define the on-premises baseline hardware requirements. Equivalent instance types across AWS, GCP, and Azure are provided for reference to simplify cloud sizing.

<table>
<tbody>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Cloud Provider</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p><strong>Instance Type</strong></p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Amazon - AWS</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p>m5.2xlarge</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Google - GCP</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p>e2-standard-8</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Microsoft - Azure</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p>D8d v5</p>
</td>
</tr>
</tbody>
</table>


For Cloud-based VM, NVMe storage type is preferred for a high performance.

Further details are found below:

[AWS](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/nvme-ebs-volumes.html)

[Azure](https://learn.microsoft.com/en-us/azure/architecture/best-practices/cassandra)

[GCP](https://cloud.google.com/compute/docs/disks/performance)


### For all installations, the file system configuration should be as follows:
* Volume of 50GB /opt/apps/cassandra/
* Volume of 450GB /opt/apps/cassandra/storage


### Note:
* The file system must provide IOPS of at least 30K read and 10K write on each node.
* The above volume values should be modified in accordance with the project scope and data retention requirements.

