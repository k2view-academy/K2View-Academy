# Proof of Value Environment System Requirements

## Table of Contents

  - [Introduction](#introduction)
  - [Server Specifications](#server-specifications)
  - [Recommended System Configuration for a Single-node Server](#recommended-system-configuration-for-a-single-node-server)
    - [Software Requirements](#software-requirements)
      - [Operating system](#operating-system)
    - [Hardware Requirements](#hardware-requirements)
    - [Running on a Cloud Provider Environment](#running-on-a-cloud-provider-environment)


## Introduction
 
Welcome to the Proof‑of‑Value (POV) Environment System Requirements, designed to support both Fabric and TDM — with TDM builds extending Fabric’s capabilities through the addition of the TDM library and PostgreSQL.

## Server Specifications 

The Fabric server is intended to run on Linux-based environments, leveraging enterprise-grade operating systems. This ensures compatibility with standard IT practices while providing the performance, stability, and security required for Proof-of-Value (POV) installations of <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/README.md">Fabric Web Studio</a>, <a href="/articles/98_installation_and_upgrade/Install_on_Linux/README.md">Fabric</a>, or <a href="/articles/98_installation_and_upgrade/Install_TDM/README.md">TDM</a> in a single-node environment. 

## Recommended System Configuration for a Single-node Server

This section outlines the baseline specifications for setting up a single-node Linux server tailored for POV installations. Whether you’re deploying Fabric alone or alongside Fabric Studio, this section defines the prerequisites across software and hardware dimensions, calibrated to deliver dependable performance and ease of setup.

For system requirements for Fabric Studio, please refer to <a href="/articles/98_installation_and_upgrade/Hardware_Linux_Docker/04_hardware_req_for_studio.md">Fabric Studio System Requirements</a>. 

### Software Requirements 

#### Operating System: 

* RedHat (version 7 or higher) based distribution (for example: Centos, Oracle, Linux).   
* Ubuntu Server, version 18.04 or higher. 

### Hardware Requirements

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
<p>A minimum of 200 GB of free disk space is recommended for application data.</p>
<p>Additional storage should be allocated based on project scope and retention requirements.</p>
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


### Running on a Cloud Environment

To accommodate the flexibility and scalability of cloud deployments, this section outlines the recommended virtual machine configurations across major cloud platforms—AWS, GCP, and Azure—for setting up a Fabric POV installation. These recommendations are designed to mirror the characteristics of on‑premise hardware by aligning the key cloud instance types with the system requirements for Fabric deployments.

Here are preferred instance families and sizes tailored to meet the recommended hardware criteria for running Fabric in a cloud ecosystem, including CPU, memory, networking, and storage characteristics that align with the single-node, high-performance Proof-of-Value setups. 

These instance types provide a baseline for POV use cases. Larger or specialized instance families may be required if your evaluation involves high-volume data processing, GPU acceleration, or multi-node topologies.

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
