# Dev and QA Environment System Requirements

## Table of Contents

  - [Introduction](#introduction)
  - [Fabric Servers - Node Specifications](#fabric-servers---node-specifications)
  - [Minimum System Configuration for Each Fabric Node Server](#minimum-system-configuration-for-each-fabric-node-server)
    - [Software Requirements](#software-requirements)
      - [Operating system](#operating-system)
    - [Hardware Requirements](#hardware-requirements)
    - [Examples of Cloud-supported Instance Types](#examples-of-cloud-supported-instance-types)
    - [For all installations, the file system configuration must be as follows](#for-all-installations-the-file-system-configuration-must-be-as-follows)
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
      - [For all installations, the file system configuration must be as follows](#for-all-installations-the-file-system-configuration-must-be-as-follows-1)
  - [Cassandra Server Node Specifications](#cassandra-server-node-specifications)
    - [Minimum System Configuration for Each Cassandra Node Server](#minimum-system-configuration-for-each-cassandra-node-server)
      - [Software Requirements](#software-requirements-2)
        - [Operating system](#operating-system-2)
      - [Hardware Requirements](#hardware-requirements-2)
      - [Examples of Cloud-supported Instance Types](#examples-of-cloud-supported-instance-types-2)
      - [For all installations, the file system configuration should be as follows](#for-all-installations-the-file-system-configuration-should-be-as-follows)



## Introduction

The Dev and QA environments are intended for development, functional testing, and validation before production rollout. These environments typically require less capacity than production systems but must still provide stability and representative performance to ensure reliable testing. This topic outlines the baseline requirements for each component (Fabric, PostgreSQL, Kafka, Cassandra) to help size and configure your Dev/QA clusters consistently across on-premises and cloud deployments.

## Fabric Servers - Node Specifications 

The Fabric server installation is a Linux-based solution.
A dedicated server is preferred for each Fabric instance. Following a sizing process, the exact number of nodes for each environment needs to be determined. 

## Minimum System Configuration for Each Fabric Node Server

### Software Requirements:

#### Operating system: 

* RedHat (version 7 or higher) based distribution (for example: CentOS, Oracle Linux).   
* Ubuntu Server, version 18.04 or higher. 


#### Hardware Requirements:

The following specifications outline the baseline hardware and software requirements for on-premises installations of a Fabric node in a development or quality assurance (Dev/QA) environment. These requirements ensure stable operation and representative performance during development and testing. 

For organizations deploying in the cloud, the listed cloud-supported instance types (AWS, GCP, Azure) map to equivalent resource profiles and are provided as reference points to simplify environment sizing.

<table>
<tbody>
<tr>
<td style="width: 224px;">
<p><strong>Processor</strong></p>
</td>
<td style="width: 446px;">
<p>For simple scenarios: Intel Xeon Quad-core or equivalent.</p>
<p>For high-load or complex scenarios: 8 cores are recommended.</p>
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

Here are equivalent resource profiles for cloud-supported instance types (AWS, GCP, Azure).

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


### For all installations, the file system configuration must be as follows:

* Volume of 150G /opt/apps/fabric/ - will also be used as the home directory for a Fabric user.


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

### Suggested System Configuration for PostgreSQL Server

### Software Requirements:
### Operating system: 

* RedHat (version 7 or higher) based distribution with the latest patches (for example: CentOS, Oracle Linux).   
* Ubuntu Server, version 18.04 or higher, with the latest patches.

### Hardware Requirements:

The following specifications outline the baseline hardware and software requirements for PostgreSQL when used in TDM projects, deployed on-premises as dedicated or virtual servers. These requirements provide stable performance for metadata and TDM workloads. 

For cloud deployments, the cloud-supported instance types and SaaS offerings (AWS RDS, GCP Cloud SQL, Azure Database for PostgreSQL) reflect equivalent resource profiles and can be used as sizing references.

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

### Examples of Cloud-supported Instance Types

Here are equivalent resource profiles for cloud-supported instance types (AWS, GCP, Azure).

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

#### Kafka Version: 
K2view supports the Kafka version certified for a specific Fabric version. Refer to the <a href="/articles/Product_Versions
/Fabric_versions.md">Fabric Versions</a> page for links to the associated release notes where version info is provided.

#### Hardware Requirements:

The following specifications outline the baseline hardware and software requirements for on-premises Kafka nodes in development and quality assurance (Dev/QA) environments. These requirements ensure reliable messaging throughput and integration with Fabric and Cassandra. 

For cloud deployments, the cloud-supported instance types (AWS, GCP, Azure) map to comparable resources and are provided as reference options.

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

Here are equivalent resource profiles for cloud-supported instance types (AWS, GCP, Azure).

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

### For all installations, the file system configuration must be as follows:
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

K2view supports the Cassandra version certified for a specific Fabric version. Refer to the <a href="/articles/Product_Versions
/Fabric_versions.md">Fabric Versions</a> page.

#### Python version requirement

Please ensure the matching Python version is available on all nodes where cqlsh or Cassandra tools are used.

- Cassandra 3.11.14 requires Python 2.7.
- Cassandra 4.0.3 and 4.1.3 require Python 3.6 or higher.


### Hardware Requirements:

The following specifications outline the baseline hardware and software requirements for on-premises Cassandra nodes in a development and quality assurance (Dev/QA) environment. These requirements support stable performance for distributed storage and query workloads. 

For cloud deployments, the cloud-supported instance types (AWS, GCP, Azure) offer equivalent resource sizing to align with these recommendations.

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

Here are equivalent resource profiles for cloud-supported instance types (AWS, GCP, Azure).

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


### For all installations, the file system configuration should be as follows:
* Volume of 200G /opt/apps/cassandra/

