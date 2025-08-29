# Proof of Value Environment Installation System Requirements

## Table of Contents

  - [Introduction](#introduction)
  - [Execution Server Specifications](#execution-server-specifications)
  - [Minimum System Configuration for a Single-node Server](#minimum-system-configuration-for-a-single-node-server)
    - [Software Requirements](#software-requirements)
      - [Operating system](#operating-system)
    - [Hardware Requirements](#hardware-requirements)
    - [Running on Cloud Environment - Recommendations](#running-on-cloud-environment---recommendations)


## Introduction
 
This document provides information regarding the hardware, software, and operating system requirements for Fabric and TDM proof-of-concept installations.

TDM installation is based on Fabric with the additions of the TDM library and PostgreSQL installation.

## Execution Server Specifications 

The Fabric server installation is a Linux-based solution. 

## Recommended System Configuration for a Single-node Server

For proof-of-concept installations, we recommend single-node configurations with physical memory requirements varying based on the anticipated use of the node - either for Fabric or Fabric Studio. For system requirements for Fabric Studio, please refer to <a href="/articles/98_installation_and_upgrade/Hardware_Linux_Docker/04_hardware_req_for_studio.md">Fabric Studio - Web and .Net Studio</a>. 

### Operating system: 

* RedHat (version 7 or higher) based distribution (for example: Centos, Oracle, Linux).   
* Ubuntu Server, version 18.04 or higher. 

### Hardware Requirements:

<table>
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




### Running on Cloud Environment - Recommendations


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
