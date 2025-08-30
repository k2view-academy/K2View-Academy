# Dev and QA Environment Installation System Requirements

## Table of Contents

  - [Introduction](#introduction)
  - [Fabric Execution Servers - Node Specifications](#fabric-execution-servers---node-specifications)
  - [Minimum System Configuration for Each Fabric Node Server](#minimum-system-configuration-for-each-fabric-node-server)
    - [Software Requirements](#software-requirements)
      - [Operating system](#operating-system)
    - [Hardware Requirements](#hardware-requirements)
    - [Examples of Cloud-supported Instance Types](#examples-of-cloud-supported-instance-types)
    - [For all installations, the FS configuration must be as follows](#for-all-installations-the-fs-configuration-must-be-as-follows)
  - [PostgreSQL Server Node Specifications](#postgresql-server-node-specifications)
    - [Suggested System Configuration for PostgreSQL Server](#suggested-system-configuration-for-postgresql-server)
      - [Software Requirements](#software-requirements-3)
        - [Operating system](#operating-system-3)
      - [Hardware Requirements](#hardware-requirements-3)
      - [Examples of Cloud-supported Instance Types](#examples-of-cloud-supported-instance-types-3)
      - [Examples of Cloud-supported SaaS](#examples-of-cloud-supported-saas)
  - [Kafka Server Node Specifications](#kafka-server-node-specifications)
    - [Minimum System Configuration for Each Kafka Node Server](#minimum-system-configuration-for-each-kafka-node-server)
      - [Software Requirements](#software-requirements-1)
        - [Operating system](#operating-system-1)
      - [Hardware Requirements](#hardware-requirements-1)
      - [Examples of Cloud-supported Instance Types](#examples-of-cloud-supported-instance-types-1)
      - [For all installations, the FS configuration must be as follows](#for-all-installations-the-fs-configuration-must-be-as-follows-1)
  - [Cassandra Server Node Specifications](#cassandra-server-node-specifications)
    - [Minimum System Configuration for Each Cassandra Node Server](#minimum-system-configuration-for-each-cassandra-node-server)
      - [Software Requirements](#software-requirements-2)
        - [Operating system](#operating-system-2)
      - [Hardware Requirements](#hardware-requirements-2)
      - [Examples of Cloud-supported Instance Types](#examples-of-cloud-supported-instance-types-2)
      - [For all installations, the FS configuration should be as follows](#for-all-installations-the-fs-configuration-should-be-as-follows)



## Introduction

* This document provides information regarding the hardware, software, and operating system requirements for Fabric installations.
* TDM installation is based on Fabric with the additions of the TDM library and PostgreSQL installation.

## Fabric Execution Servers - Node Specifications 

The Fabric server installation is a Linux-based solution.
A dedicated server is preferred for each Fabric instance. Following a sizing process, the exact number of nodes for each environment needs to be determined. 

## Minimum System Configuration for Each Fabric Node Server

### Software Requirements:

#### Operating system: 

* RedHat (version 7 or higher) based distribution (for example: CentOS, Oracle Linux).   
* Ubuntu Server, version 18.04 or higher. 


#### Hardware Requirements:

<table>
<tbody>
<tr>
<td style="width: 224px;">
<p><strong>Processor</strong></p>
</td>
<td style="width: 446px;">
<p>For simple scenarios: Intel Xeon Quad-core or equivalent.</p>
<p>For high-load or complex scenarios: Octa-cores are&nbsp;recommended.</p>
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
<p>For application data, at least 150 GB free disk space is recommended.</p>
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


### For all installations, the FS configuration must be as follows:

* Volume of 150G /opt/apps/fabric/ - will also be used as the home directory for a Fabric user.


### Connectivity

Fabric server should have access (permissions) and connectivity (network) to all sources and destinations.
Cassandra and Kafka servers should be accessible from Fabric servers’ nodes.



## PostgreSQL Server Node Specifications 

* PostgreSQL is required for TDM projects only.
* K2view supports PostgreSQL version 9.6 & 13.x.
* PostgreSQL can be deployed in one of the following two ways:
    * On-premises as a virtual or a physical machine. 
    * As a cloud service (SaaS)

### Suggested System Configuration for PostgreSQL Server

### Software Requirements:
### Operating system: 

* RedHat (version 7 or higher) based distribution with the latest patches (for example: CentOS, Oracle Linux).   
* Ubuntu Server, version 18.04 or higher, with the latest patches.

### Hardware Requirements:
<table>
<tbody>
<tr>
<td style="width: 224px;">
<p><strong>Processor</strong></p>
</td>
<td style="width: 446px;">
<p>Intel Xeon quad-core or equivalent.</p>
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
<p>Disk space can be increased according to the project scope and the retention requirements.</p>
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
A dedicated server is preferred for each Kafka instance. Following a sizing process, the exact number of nodes for each environment needs to be determined. 

### Minimum System Configuration for Each Kafka Node Server

### Software Requirements:
#### Operating system: 

* RedHat (version 7 or higher) based distribution with the latest patches (for example: CentOS, Oracle Linux).   
* Ubuntu Server, version 18.04 or higher, with the latest patches.

#### Hardware Requirements:
<table>
<tbody>
<tr>
<td style="width: 224px;">
<p><strong>Processor</strong></p>
</td>
<td style="width: 446px;">
<p>For simple scenarios: Intel Xeon Quad-core or equivalent.</p>
<p>For high-load or complex scenarios: Octa-cores are&nbsp;recommended.</p>
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
<p>For application data, at least 50 GB free disk space is recommended.</p>
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

### For all installations, the FS configuration must be as follows:
Volume of 50G /opt/apps/kafka/ - will also be used as the home directory for a Kafka user.


## Cassandra Server Node Specifications 

The Cassandra server installation is a Linux-based solution.
A dedicated server for each Cassandra instance is preferred. Following a sizing process, the exact number of nodes for each environment needs to be determined. 

### Minimum System Configuration for Each Cassandra Node Server

### Software Requirements:

#### Operating system: 

* RedHat (version 7 or higher) based distribution with the latest patches (for example: CentOS, Oracle Linux).   
* Ubuntu Server, version 18.04 or higher, with the latest patches.


#### Cassandra Versions Supported

The matrix of Cassandra versions supported, along with their associated TDM and Fabric versions, is available at https://support.k2view.com/Academy/articles/Product_Versions/TDM_versions.html.

#### Python version requirement
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
<p>For most scenarios: Intel Xeon Octa-core CPU or equivalent.</p>
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
<p>For application data, at least 200 GB free disk space is recommended.</p>
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


### For all installations, the FS configuration should be as follows:
* Volume of 200G /opt/apps/cassandra/

