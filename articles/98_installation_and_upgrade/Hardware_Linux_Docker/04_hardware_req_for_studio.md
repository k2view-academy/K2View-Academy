
# Fabric Studio - Web and .Net Studio - Installation System Requirements

## Table of Contents

  - [About Fabric Studio](#about-fabric-studio)
  - [Fabric Web Studio Requirements](#fabric-web-studio-requirements)
    - [Recommended Environment Sizinn](#recommended-environment-sizing)
    - [Installation Options](#installation-options)
  - [Fabric .Net Studio Requirements](#fabric-net-studio-requirements)
    - [Minimum System Configuration](#minimum-system-configuration)
    - [Windows Ports](#windows-ports)
    - [Windows Permissions](#windows-permissions)
    - [Recommended Software](#recommended-software)
    - [Linux Server Ports](#linux-server-ports)
    - [Connectivity](#connectivity)


## About Fabric Studio

K2view Fabric Studio is the development environment for building, managing, and testing K2view Fabric projects. It provides data engineers and developers with the tools to design and deploy Logical Units (LUs), business workflows, data pipelines, and other Fabric components with speed and precision.

### Fabric Web Studio – The Next Generation

Fabric Web Studio is the latest version of Studio, designed for today’s containerized and cloud-first enterprise environments. It runs seamlessly on Docker Compose, Podman, and Cloud deployments, offering:

  - Cross-platform flexibility – accessible from any modern browser, independent of local operating system constraints.
  - Isolated development Spaces – enabling secure, parallel workstreams for multiple teams.
  - Containerized agility – lightweight and scalable deployment with support for enterprise DevOps practices.
  - Cloud-ready architecture – built to integrate with hybrid and multi-cloud strategies.

With Web Studio, organizations gain a more modern, flexible, and collaborative development experience that accelerates project delivery.

### Fabric .Net Studio – The Previous Generation

Fabric .Net Studio is the earlier version of Studio, designed primarily for Windows environments. It provides a powerful IDE experience for Fabric developers, with system requirements detailed later in this topic. While it remains supported for customers with existing Windows-based workflows, new projects are encouraged to adopt Fabric Web Studio for its modern, containerized, and cloud-ready capabilities.


## Fabric Web Studio Requirements:

Fabric Web Studio is designed for flexible deployment across Windows, macOS, and Linux using Docker Compose or Podman, and is also available natively in K2cloud. To ensure a smooth experience, we recommend starting with the <a href="/articles/98_installation_and_upgrade/Hardware_Linux_Docker/00_hardware_requirements_for_POV.md">Proof of Value (POV) environment sizing</a>. This configuration is optimized for evaluation, development, and smaller-scale projects, while still providing the resources needed to explore Fabric’s full capabilities.

Fabric Web Studio supports the creation of multiple spaces, allowing multiple users to share a single environment for dedicated purposes. The number of concurrent running spaces will dictate the amount of RAM required on the host. You should allocate 4-8 MB of RAM per space, depending on your project needs. A K2view representative can help you with the sizing. 

### Recommended Environment Sizing

The following baseline resources are recommended for deploying Fabric Web Studio in a POV environment:

  - CPU: 8 cores
  - Memory: 32 GB RAM
  - Storage: 200 GB SSD or NVMe (high IOPS recommended)
  - Host OS: Windows, macOS, or Linux
  - Container Runtime: Docker Compose v2.x or Podman
  - Browser: Any modern browser (Chrome, Edge, or Firefox)

Tip: This sizing is intended for POV and initial development environments. For production-scale deployments, resource allocation should be adjusted based on workload size, number of Spaces, and concurrency requirements.

### Installation Options

  - Docker Compose / Podman: Fabric Web Studio can be installed on Windows, macOS, or Linux. For detailed setup instructions, refer to the <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/About.md">Installation Guide</a>.
  - K2cloud: When using Fabric Web Studio as part of K2cloud, installation and configuration are fully integrated into the K2cloud experience—no separate setup is required.



## Fabric .Net Studio Requirements:

Fabric .Net Studio is used for configuring, managing, and controlling the Fabric server operation, and its installation is Windows-based.

The application can be installed locally on either a workstation or a server with Terminal Services for RDP connection.

### Minimum System Configuration

<table>
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

Open the following ports on the Linux server and ensure they are accessible from Management stations.

Depending on your setup, not all ports are required:

<table>
<tbody>
<tr>
<td style="width: 161.109px;">
<p><strong>Port Numbers</strong></p>
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



