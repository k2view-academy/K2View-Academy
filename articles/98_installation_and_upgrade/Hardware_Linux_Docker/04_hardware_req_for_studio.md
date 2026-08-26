# Fabric Studio Installation System Requirements

## Table of Contents

  - [About Fabric Studio](#about-fabric-studio)
  - [Fabric Web Studio Requirements](#fabric-web-studio-requirements)
    - [Recommended Environment Sizing](#recommended-environment-sizing)
    - [Installation Options](#installation-options)
  - [Fabric .Net Studio Requirements](#fabric-net-studio-requirements)
    - [Minimum System Configuration](#minimum-system-configuration)
    - [Windows Ports](#windows-ports)
    - [Windows Permissions](#windows-permissions)
    - [Recommended Software](#recommended-software)
    - [Linux Server Ports](#linux-server-ports)
  - [Related Topics](#related-topics)


## About Fabric Studio

K2view Fabric Studio is the development environment for building, managing, and testing K2view Fabric projects. It provides data engineers and developers with the tools to design and deploy Logical Units (LUs), business workflows, data pipelines, and other Fabric components with speed and precision.

There are two generations of Fabric Studio: *Fabric Web Studio* and *Fabric .NET Studio*.

### Fabric Web Studio – The Next Generation

**Fabric Web Studio** is the latest version of Studio, designed for today’s containerized and cloud-first enterprise environments. It runs seamlessly on Docker Compose, Podman, and Cloud deployments, offering:

  - Cross-platform flexibility – accessible from any modern browser, independent of local operating system constraints.
  - Isolated development Spaces – enabling secure, parallel workstreams for multiple teams.
  - Containerized agility – lightweight and scalable deployment with support for enterprise DevOps practices.
  - Cloud-ready architecture – built to integrate with hybrid and multi-cloud strategies.

With Fabric Web Studio, organizations gain a more modern, flexible, and collaborative development experience that accelerates project delivery.

### Fabric .NET Studio – The Previous Generation

**Fabric .NET Studio** is the earlier version of Studio, designed primarily for Windows environments. It provides a powerful IDE experience for Fabric developers, with system requirements detailed later in this topic. While it remains supported for customers with existing Windows-based workflows, new projects are encouraged to adopt Fabric Web Studio for its modern, containerized, and cloud-ready capabilities.


## Fabric Web Studio Requirements

Fabric Web Studio is designed for flexible deployment across Windows, macOS, and Linux using Docker Compose or Podman, and is also available natively in K2cloud. To ensure a smooth experience, we recommend starting with the <a href="/articles/98_installation_and_upgrade/Hardware_Linux_Docker/00_hardware_requirements_for_POV.md">Proof of Value (POV) environment sizing</a>. This configuration is optimized for evaluation, development, and smaller-scale projects, while still providing the resources needed to explore Fabric’s full capabilities.
 
Fabric Web Studio supports the creation of multiple spaces, allowing multiple users to share a single environment for dedicated purposes. The number of concurrent running spaces will dictate the amount of RAM required on the host. You should allocate 4-8 GB of RAM per space, depending on your project needs. A K2view representative can help you figure out the correct size. 

### Recommended Environment Sizing

The following baseline resources are recommended for deploying Fabric Web Studio in a POV environment:

<table>
  <tr>
    <td>CPU</td>
    <td>8 cores</td>
  </tr>
  <tr>
    <td>Memory</td>
    <td>32 GB RAM</td>
  </tr>
  <tr>
    <td>Storage</td>
    <td>200 GB SSD or NVMe (high IOPS recommended)</td>
  </tr>
  <tr>
    <td>Host OS</td>
    <td>Windows, macOS, or Linux</td>
  </tr>
  <tr>
    <td>Container Runtime</td>
    <td>Docker Compose or Podman</td>
  </tr>
  <tr>
    <td>Browser</td>
    <td>Any modern browser (Chrome, Edge, or Firefox)</td>
  </tr>
</table>

Tip: This sizing is intended for POV and initial development environments. For production-scale deployments, resource allocation should be adjusted based on workload size, number of Spaces, and concurrency requirements.

### Installation Options

  - **Docker Compose / Podman**: Fabric Web Studio can be installed on Windows, macOS, or Linux. For detailed setup instructions, refer to the <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/About.md">Installation Guide</a>.
  - **K2cloud**: When using Fabric Web Studio as part of K2cloud, installation and configuration are fully integrated into the K2cloud experience—no separate setup is required.



## Fabric .Net Studio Requirements

Fabric .NET Studio is used for configuring, managing, and controlling the Fabric server operation, and its installation is Windows-based.
 
The application can be installed locally on either a workstation or a server with Terminal Services for RDP connection.

### Minimum System Configuration

<table>
<tbody>
<tr>
<td style="width: 255.078px;">
<p><strong>Operation System</strong></p>
</td>
<td style="width: 628.922px;">
<p>Workstation: Windows 10 Professional (64-Bit) or higher.</p>
<p>Server: Windows Server Standard 2012 (64-bit) or higher.</p>
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

<table>
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

### Linux Server Ports

Open the following ports on the Linux server and ensure they are accessible from the Management station.
 
Depending on your setup, not all ports are required:

<table>
<tbody>
<tr>
<td style="width: 120px;">
<p><strong>Section</strong></p>
</td>
<td style="width: 80px;">
<p><strong>Port</strong></p>
</td>
<td style="width: 400px;">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td style="width: 120px;">
<p><strong>Linux</strong></p>
</td>
<td style="width: 80px;">
<p>22</p>
</td>
<td style="width: 400px;">
<p>SSH and SCP - shell access and file transfer for installation and maintenance.</p>
</td>
</tr>
<tr>
<td rowspan="7" style="width: 120px;">
<p><strong>Fabric</strong></p>
</td>
<td style="width: 80px;">
<p>3213</p>
</td>
<td style="width: 400px;">
<p>Fabric web server - REST APIs and the server status pages.</p>
</td>
</tr>
<tr>
<td style="width: 80px;">
<p>9443</p>
</td>
<td style="width: 400px;">
<p>Fabric Admin UI and deployment endpoint (HTTPS).</p>
</td>
</tr>
<tr>
<td style="width: 80px;">
<p>5124</p>
</td>
<td style="width: 400px;">
<p>Fabric JDBC listener - used by Studio, deployment tools, and JDBC clients.</p>
</td>
</tr>
<tr>
<td style="width: 80px;">
<p>7170</p>
</td>
<td style="width: 400px;">
<p>JMX Exporter - Fabric JVM and application metrics, in Prometheus format.</p>
</td>
</tr>
<tr>
<td style="width: 80px;">
<p>7270</p>
</td>
<td style="width: 400px;">
<p>JMX Exporter for the iid_finder process, when running.</p>
</td>
</tr>
<tr>
<td style="width: 80px;">
<p>9100</p>
</td>
<td style="width: 400px;">
<p>Node Exporter - host CPU, memory, disk, and network metrics. Optional.</p>
</td>
</tr>
<tr>
<td style="width: 80px;">
<p>5009</p>
</td>
<td style="width: 400px;">
<p>JVM remote debug port for the Fabric process. Optional, for development only.</p>
</td>
</tr>
<tr>
<td rowspan="5" style="width: 120px;">
<p><strong>Cassandra</strong></p>
</td>
<td style="width: 80px;">
<p>9042</p>
</td>
<td style="width: 400px;">
<p>CQL native transport - default Cassandra client port.</p>
</td>
</tr>
<tr>
<td style="width: 80px;">
<p>9142</p>
</td>
<td style="width: 400px;">
<p>CQL native transport over TLS, used in hardened Cassandra setups.</p>
</td>
</tr>
<tr>
<td style="width: 80px;">
<p>7199</p>
</td>
<td style="width: 400px;">
<p>JMX - used by nodetool and monitoring. Optional.</p>
</td>
</tr>
<tr>
<td style="width: 80px;">
<p>7000</p>
</td>
<td style="width: 400px;">
<p>Inter-node communication between Cassandra nodes. Not needed on a single node.</p>
</td>
</tr>
<tr>
<td style="width: 80px;">
<p>7001</p>
</td>
<td style="width: 400px;">
<p>Inter-node communication over TLS. Not needed on a single node.</p>
</td>
</tr>
<tr>
<td rowspan="3" style="width: 120px;">
<p><strong>Kafka</strong></p>
</td>
<td style="width: 80px;">
<p>9093</p>
</td>
<td style="width: 400px;">
<p>Kafka broker listener - SASL_SSL in hardened setups.</p>
</td>
</tr>
<tr>
<td style="width: 80px;">
<p>9092</p>
</td>
<td style="width: 400px;">
<p>Kafka broker listener - default plaintext port, used by local installations.</p>
</td>
</tr>
<tr>
<td style="width: 80px;">
<p>2181</p>
</td>
<td style="width: 400px;">
<p>ZooKeeper client port. Not needed when Kafka runs in KRaft mode.</p>
</td>
</tr>
<tr>
<td style="width: 120px;">
<p><strong>PostgreSQL</strong></p>
</td>
<td style="width: 80px;">
<p>5432</p>
</td>
<td style="width: 400px;">
<p>PostgreSQL client connections - Fabric metadata and CommonDB.</p>
</td>
</tr>
</tbody>
</table>


## Related Topics

  - <a href="/articles/99_fabric_infras/03_fabric_api_and_ui_hardening.md">Fabric API and UI Hardening</a>
  - <a href="/articles/99_fabric_infras/04_cassandra_hardening.md">Cassandra Hardening</a>
  - <a href="/articles/99_fabric_infras/06_kafka_hardening.md">Kafka Hardening</a>
  - <a href="/articles/98_installation_and_upgrade/Install_on_Linux/01_Fabric_8.xx_Installation_intro.md">Linux Environment Preparation for Fabric Installation</a>
