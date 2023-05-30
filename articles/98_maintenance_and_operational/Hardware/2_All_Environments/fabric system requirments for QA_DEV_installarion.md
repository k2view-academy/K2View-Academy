# Fabric System Requirements for Development and QA Installations
## Introduction

* This document provides information regarding the hardware, software and the operating system requirements for Fabric installations.
* TDM installation is based on Fabric with the additions of TDM library and PostgreSQL installation.

## Fabric Execution Servers - Node Specifications 

The Fabric server installation is a Linux-based Solution.
Use dedicated servers for Fabric/Cassandra/Kafka instances. Following a sizing process, the exact number of nodes for each environment needs to be determined . 


## Minimum System Configuration for each Fabric Node Server

### Software Requirements:

### Operating system: 

* RedHat (version 7 or higher) based distribution (for example: Centos, Oracle, Linux).   
* Ubuntu Server, version 18.04 or higher. 


### Hardware Requirements:

<table style="width: 900px; border-style: solid; float: left;" border="1">
<tbody>
<tr>
<td style="width: 224px;">
<p><strong>Processor</strong></p>
</td>
<td style="width: 446px;">
<p>For simple scenarios: Intel Xeon Quad-core or equivalent.</p>
<p>For high-load or complex scenarios: Octa-cores are&nbsp; recommended.</p>
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
<p>Direct attached SSD/NVME based storage is recommended for best performance.</p>
<p><strong>SAN</strong> supported with flash based disks. RAID-0 is preferred.</p>
<p><strong>NAS is not certified</strong>
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
<p>1 Gbps network speed between the Fabric nodes,Cassandra Nodes, Kafka nodes and source and destination Dbs.</p>
</td>
</tr>
</tbody>
</table>




### Example of Cloud Supported Instance Types:

<table style="width: 900px; border-style: solid;" border="1">
<tbody>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Cloud provider</strong></p>
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
<p>m5.4xlarge</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Google - GCP</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p>D16d v4</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Microsoft - Azure</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p>e2-standard-8</p>
</td>
</tr>
</tbody>
</table>


### For all installations, the FS configuration must be as follows:

* Volume of 150G /opt/apps/fabric/ - will be used also as the home directory for a Fabric user.


## Kafka Server Node Specifications 

The Kafka server installation is a Linux based Solution.
Use dedicated servers for Fabric/Cassandra/Kafka instances. Following a sizing process, the exact number of nodes for each environment needs to be determined. 

### Minimum System Configuration for each Fabric node Server

### Software Requirements:
### Operating system: 

* RedHat (version 7 or higher) based distribution with latest patches (for example: Centos, Oracle Linux).   
* Ubuntu Server, version 18.04 or higher, with latest patches.

### Hardware Requirements:
<table style="width: 900px; border-style: solid; float: left;" border="1">
<tbody>
<tr>
<td style="width: 224px;">
<p><strong>Processor</strong></p>
</td>
<td style="width: 446px;">
<p>For simple scenarios: Intel Xeon Quad-core or equivalent.</p>
<p>For high-load or complex scenarios: Octa-cores are&nbsp; recommended.</p>
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
<p>Direct attached SSD/NVME based storage is recommended for best performance.</p>
<p><strong>SAN</strong> supported with flash based disks. RAID-0 is preferred.</p>
<p><strong>NAS is not certified</strong>
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

### Example of Cloud Supported Instance Types:

<table style="width: 900px; border-style: solid;" border="1">
<tbody>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Cloud provider</strong></p>
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
<p>D16d v2</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Microsoft - Azure</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p>e2-standard-8</p>
</td>
</tr>
</tbody>
</table>

### For all installations, the FS configuration must be as follows:
Volume of 50G /opt/apps/kafka/ - will be used also as the home directory for a Kafka user.


## Cassandra Server Node Specifications 

The Cassandraserver installation is a Linux based Solution.
Use dedicated servers for Fabric/Cassandra/Kafka instances. Following a sizing process, the exact number of nodes for each environment needs to be determined. 

### Minimum System Configuration for each Cassandra node Server

### Software Requirements:

### Operating system: 

* RedHat (version 7 or higher) based distribution with latest patches (for example: Centos, Oracle Linux).   
* Ubuntu Server, version 18.04 or higher, with latest patches.
> **Note** Until Cassandra version 4.0.x - Python version 2.7 is required; newer versions of Cassandra are not yet certified.

### Hardware Requirements:
<table style="width: 900px; border-style: solid; float: left;" border="1">
<tbody>
<tr>
<td style="width: 224px;">
<p><strong>Processor</strong></p>
</td>
<td style="width: 446px;">
<p>For most scenarios: Intel Xeon Octa-core CPU or equivalent .</p>
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
<p>Direct attached SSD/NVME based storage is recommended for best performance.</p>
<p><strong>SAN</strong> supported with flash based disks. RAID-0 is preferred.</p>
<p><strong>NAS is not certified</strong>
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

### Example of Cloud Supported Instance Types:

<table style="width: 900px; border-style: solid;" border="1">
<tbody>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Cloud provider</strong></p>
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
<p>D16d v2</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Microsoft - Azure</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p>e2-standard-8</p>
</td>
</tr>
</tbody>
</table>


### For all installations, the FS configuration should be as follows:
* Volume of 200G /opt/apps/cassandra/


## PostgreSQL Server Node Specifications 

* PostgreSQL is generally required for TDM projects only.
* K2view supports PostgreSQL version 9.6 & 13.x .
* PostgreSQL can be deployed in one of these ways:
    * On premise as a virtual or physical machine. 
    * As a cloud service (SAAS)

### Suggested System Configuration for PostgreSQL server

### Software Requirements:
### Operating system: 

* RedHat (version 7 or higher) based distribution with latest patches (for example: Centos, Oracle Linux).   
* Ubuntu Server, version 18.04 or higher, with latest patches.

### Hardware Requirements:
<table style="width: 900px; border-style: solid; float: left;" border="1">
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
<p>Direct attached SSD/NVME based storage is recommended for best performance.</p>
<p><strong>SAN</strong> supported with flash based disks. RAID-0 is preferred.</p>
<p><strong>NAS is not certified</strong>
</td>
</tr>
<tr>
<td style="width: 224px;">
<p><strong>Disk Space</strong></p>
</td>
<td style="width: 446px;">
<p>For application data, at least 100 GB free disk space is required.</p>
<p>Depends on the project scope and retention requirements. Disk space canbe increased.</p>
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

### Example of Cloud Supported Instance Types:

<table style="width: 900px; border-style: solid;" border="1">
<tbody>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Cloud provider</strong></p>
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
<p>m5.xlarge, a1.2xlarge</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Google - GCP</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p> e2-standard-4</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="width: 240.469px; height: 46px;">
<p><strong>Microsoft - Azure</strong></p>
</td>
<td style="width: 447.516px; height: 46px;">
<p>e2-standard-4</p>
</td>
</tr>
</tbody>
</table>

### Example of Cloud Supported SAAS 
* AWS RDS PostgreSQL - See more [Here](https://aws.amazon.com/free/database/?trk=492c57d3-8cdc-4660-b6ac-d2008bd51b40&sc_channel=ps&ef_id=Cj0KCQjwmtGjBhDhARIsAEqfDEcolBtzTdv_q0Ob8_Xda3524RZfaxKgGOzqoi1IVjieHyRiSYgUEr0aAk12EALw_wcB:G:s&s_kwcid=AL!4422!3!645125273471!e!!g!!aws%20postgre%20sql!19574556899!145779849512)
* GCP Cloud SQL PostgreSQL - See more [Here](https://cloud.google.com/sql/docs/postgres/introduction)
* Azure Database for PostgreSQL - See more [Here](https://azure.microsoft.com/en-us/products/postgresql/?ef_id=_k_Cj0KCQjwmtGjBhDhARIsAEqfDEdFvRBFcGSocBebegdYAH-KKrEjh3YxAuG0vKhGbQ0djHuzAPbdhMsaAolmEALw_wcB_k_&OCID=AIDcmm81syc84i_SEM__k_Cj0KCQjwmtGjBhDhARIsAEqfDEdFvRBFcGSocBebegdYAH-KKrEjh3YxAuG0vKhGbQ0djHuzAPbdhMsaAolmEALw_wcB_k_&gclid=Cj0KCQjwmtGjBhDhARIsAEqfDEdFvRBFcGSocBebegdYAH-KKrEjh3YxAuG0vKhGbQ0djHuzAPbdhMsaAolmEALw_wcB)


## Fabric Studio Requirements:

Fabric Studio is used to configure, manage and control the Fabric server operation and its installation is Windows-based.
The application can be installed locally on either a workstation or a server with Terminal Services for RDP connection.

### Minimum System Configuration

<table style="width: 900px; border-style: solid;">
<tbody>
<tr>
<td style="width: 255.078px;">
<p><strong>Operation System</strong></p>
</td>
<td style="width: 628.922px;">
<p>Workstation: Windows 10 Professional 64 Bit or higher.</p>
<p>Server: Windows server standard 2012 64 bit or higher.</p>
</td>
</tr>
<tr>
<td style="width: 255.078px;">
<p><strong>Processors </strong></p>
</td>
<td style="width: 628.922px;">
<p>Intel I3 quad-core 5<sup>th</sup> Gen or equivalent.</p>
</td>
</tr>
<tr>
<td style="width: 255.078px;">
<p><strong>Physical Memory </strong><strong>(RAM)</strong></p>
</td>
<td style="width: 628.922px;">
<p>8 GB RAM</p>
</td>
</tr>
<tr>
<td style="width: 255.078px;">
<p><strong>Disk Space</strong></p>
</td>
<td style="width: 628.922px;">
<p>100 GB available disk space.</p>
<p>&nbsp;</p>
</td>
</tr>
</tbody>
</table>

### Windows Ports

The following ports should be open on the Windows server:
* 3389 - used for RDP

### Windows Permissions

Local administrator privileges are needed for the Fabric Studio installation.

### Recommended Software

<table style="width: 900px; border-style: solid;">
<tbody>
<tr>
<td style="width: 179.391px;">
<p><strong>Text/Code editor</strong></p>
</td>
<td style="width: 237.688px;">
<p>Visual Studio Code / notepad++</p>
</td>
<td style="width: 460.922px;">
<p><a href="https://code.visualstudio.com/">https://code.visualstudio.com/</a></p>
<p><a href="https://notepad-plus-plus.org/downloads/">https://notepad-plus-plus.org/downloads/</a></p>
</td>
</tr>
<tr>
<td style="width: 179.391px;">
<p><strong>file archiver</strong></p>
</td>
<td style="width: 237.688px;">
<p>7zip</p>
</td>
<td style="width: 460.922px;">
<p><a href="https://7-zip.org/">https://7-zip.org/</a></p>
</td>
</tr>
<tr>
<td style="width: 179.391px;">
<p><strong>FTP/SCP client</strong></p>
</td>
<td style="width: 237.688px;">
<p>winSCP</p>
</td>
<td style="width: 460.922px;">
<p><a href="https://winscp.net/eng/download.php">https://winscp.net/eng/download.php</a></p>
</td>
</tr>
<tr>
<td style="width: 179.391px;">
<p><strong>SSH client</strong></p>
</td>
<td style="width: 237.688px;">
<p>Putty</p>
</td>
<td style="width: 460.922px;">
<p><a href="https://www.putty.org/">https://www.putty.org/</a></p>
</td>
</tr>
<tr>
<td style="width: 179.391px;">
<p><strong>Git Shell</strong></p>
</td>
<td style="width: 237.688px;">
<p>TortoiseGit</p>
</td>
<td style="width: 460.922px;">
<p>https://tortoisegit.org/</p>
</td>
</tr>
<tr>
<td style="width: 179.391px;">
<p><strong>Java IDE</strong></p>
</td>
<td style="width: 237.688px;">
<p>Intellij IDE (community edition):</p>
</td>
<td style="width: 460.922px;">
<p><a href="https://www.jetbrains.com/idea/download/#section=windows">https://www.jetbrains.com/idea/download/#section=windows</a></p>
</td>
</tr>
</tbody>
</table>

### Linux Servers Ports

Open the following ports on the Linux server and make sure they are accessible from Management stations.

Depending on your setup, not all ports are required:

<table style="width: 427px; border-style: solid;">
<tbody>
<tr>
<td style="width: 161.109px;">
<p><strong>Ports Number</strong></p>
</td>
<td style="width: 249.891px;">
<p><strong>Role</strong></p>
</td>
</tr>
<tr>
<td style="width: 161.109px;">
<p>22</p>
</td>
<td style="width: 249.891px;">
<p>SSH, SCP</p>
</td>
</tr>
<tr>
<td style="width: 161.109px;">
<p>3213, 9443, 5124, 6379</p>
</td>
<td style="width: 249.891px;">
<p>Fabric</p>
</td>
</tr>
<tr>
<td style="width: 161.109px;">
<p>9042, 9142</p>
</td>
<td style="width: 249.891px;">
<p>Cassandra</p>
</td>
</tr>
<tr>
<td style="width: 161.109px;">
<p>9093, 7270</p>
</td>
<td style="width: 249.891px;">
<p>Kafka</p>
</td>
</tr>
<tr>
<td style="width: 161.109px;">
<p>3000, 9090</p>
</td>
<td style="width: 249.891px;">
<p>Grafana stack</p>
</td>
</tr>
<tr>
<td style="width: 161.109px;">
<p>5432</p>
</td>
<td style="width: 249.891px;">
<p>PostgreSQL</p>
</td>
</tr>
</tbody>
</table>



### Connectivity

Fabric server and Fabric Studio should have access (permissions) and connectivity (network) to all source & target systems.
Cassandra and Kafka servers should be accessible from Fabric servers’ nodes.
