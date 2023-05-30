# Fabric System Requirements for POV Installations
## Introduction

* This document provides information regarding the hardware, software and the operating system requirements for Fabric and TDM proof-of-concept installations.
* TDM installation is based on Fabric with the additions of TDM library and PostGres installation.

## Execution Server Specifications 

The Fabric server installation is a Linux-based Solution.
We Provide installation guide for both native Linux and Docker Environment (based on Docker Compose).

## Minimum System Configuration for a Single-node Server

### Software Requirements:

### Operating system: 

* RedHat (version 7 or higher) based distribution (for example: Centos, Oracle, Linux).   
* Ubuntu Server, version 18.04 or higher. 

For docker installation, the latest **Docker Engine** and  **Docker Compose** installations are recommended.

### Hardware Requirements:

<table style="width: 900px; border-style: solid; float: left;" border="1">
<tbody>
<tr>
<td style="width: 224px;">
<p><strong>Processor</strong></p>
</td>
<td style="width: 446px;">
<p>For simple scenarios: Intel Xeon Octa-core or equivalent.</p>
<p>For high-load or complex scenarios: 16 cores are&nbsp; recommended.</p>
</td>
</tr>
<tr>
<td style="width: 224px;">
<p><strong>Physical Memory </strong><strong>(RAM)</strong></p>
</td>
<td style="width: 446px;">
<p>For simple scenarios: 32 GB RAM</p>
<p>For high-load or complex scenarios: 64 GB are recommended.</p>
</td>
</tr>
<tr>
<td style="width: 224px;">
<p><strong>Storage</strong></p>
</td>
<td style="width: 446px;">
<p>Direct attached SSD/NVME based storage is recommended for best performance.</p>
</td>
</tr>
<tr>
<td style="width: 224px;">
<p><strong>Disk Space</strong></p>
</td>
<td style="width: 446px;">
<p>For application data, at least 200 GB free disk space is recommended.</p>
<p>Depends on the project scope and retention requirements. Disk space should be increased.</p>
</td>
</tr>
<tr>
<td style="width: 224px;">
<p><strong>Network</strong></p>
</td>
<td style="width: 446px;">
<p>1 Gbps network speed between the Fabric node and the source DBs.</p>
</td>
</tr>
</tbody>
</table>




### Running on Cloud environment - Recommendations




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
