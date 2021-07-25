# Fabric & TDM 7.xx Infrastructure Requirements for POC Installations

### Introduction 

This document provides information regarding hardware, software, and operating system requirements for Fabric and TDM proof-of-concept installations.

The installation is based on Docker containers.

### Fabric Studio Windows Server  

#### Minimum Hardware Requirements 

##### For Local Installation:

-   Windows Version -- Any one of the following: 10/11/2012/2016/2019 64bit Machine
-   8 Cores/vCPUs.
-   8GB RAM
-   100GB Available Disk Space

##### For Cloud Installation:

-   AWS: EC2: a1.2xlarge, m5.xlarge
-   Azure VM: A8v2
-   GCP: e2-standard-4

#### Windows Ports 

Open the following ports on the Windows server:

-   3389 -- Used for RDP

#### Windows Permissions 

Local administrator privileges are needed for the Fabric Studio installation.

#### Minimum Software Requirements 

-   Notepad++
-   [7zip](http://www.7-zip.org/download.html)
-   [WinSCP](https://winscp.net/eng/download.php)
-   Putty
-   TortoiseGit: <https://tortoisegit.org/download/>
-   IntelliJ IDE (community edition): <https://www.jetbrains.com/idea/download/download-thanks.html?platform=mac&code=IIC>

### POC Linux Execution Server Specifications

On this server we will install run number of containers:

-   Fabric server
-   Cassandra
-   Kafka
-   And any other app container that will be needed 

#### Minimum Hardware Configuration for Each Linux Server 

Use the latest RedHat/CentOS Operating System, with **latest** **Docker Engine**, and **latest** **docker-compose** installed

Hardware requirements:

-   Modern Xeon Processor.
-   CPU 8/16 Cores/vCPUs.
-   RAM: 64GB RAM.
-   Network: Minimum 1G between the nodes and source DB's
-   Storage: The preferred storage is attached local SSD's devices, that IOS provisioning can be set.
    The **/var/lib/docker** directory require volume of at list 200G
    \* Should be increased based on project scope and data retention requirements

##### For Cloud Installations:

-   If using **AWS**: EC2: m54.xlarge
-   If using **Azure** VM: D16d v4
-   If using **GCP**: e2-standard-8

#### Linux Ports 

Open the following ports on the LINUX server and make sure they are accessible from outside the machine:

<table style="border-collapse: collapse; width: 100%; height: 131px;" border="1">
<tbody>
<tr style="height: 18px;">
<td style="width: 50%; height: 18px;"><strong>22</strong></td>
<td style="width: 50%; height: 18px;"><strong>SSH, SCP</strong></td>
</tr>
<tr style="height: 18px;">
<td style="width: 50%; height: 18px;">3213, 9443, 5124, 6379</td>
<td style="width: 50%; height: 18px;">Fabric</td>
</tr>
<tr style="height: 18px;">
<td style="width: 50%; height: 18px;">9042, 9142</td>
<td style="width: 50%; height: 18px;">Cassandra</td>
</tr>
<tr style="height: 18px;">
<td style="width: 50%; height: 18px;">9093, 7270</td>
<td style="width: 50%; height: 18px;">Kafka</td>
</tr>
<tr style="height: 18px;">
<td style="width: 50%; height: 18px;">3000, 9090</td>
<td style="width: 50%; height: 18px;">Grafana stack</td>
</tr>
</tbody>
</table>



#### Connectivity 

All servers should have access (permissions) and connectivity (network) to all legacy & target systems.


