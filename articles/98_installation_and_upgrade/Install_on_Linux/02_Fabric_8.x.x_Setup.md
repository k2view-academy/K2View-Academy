# Fabric 8 Setup Guide

This guide describes the steps required to prepare your environment for the installation of Fabric and TDM. It covers both system-level and application-level preparation to ensure a successful deployment.

## Table of Contents

- [Overview](#overview)
- [Getting Started](#getting-started)
- [Fabric Pre-Installation Steps](#fabric-pre-installation-steps)
  - [Hardware Requirements](#hardware-requirements)
  - [3rd Party Components](#3rd-party-components)
    - [System Database](#system-database)
    - [Kafka](#kafka)
  - [Operating System Preparation](#operating-system-preparation)
    - [System Users](#system-users)
    - [OS Limits](#os-limits)
    - [Kernel Parameters](#kernel-parameters)
    - [Additional Steps for Cassandra](#additional-steps-for-cassandra)
    - [Additional Steps for Kafka](#additional-steps-for-kafka)
  - [Setting up TLS](#setting-up-tls)
- [Fabric Setup](#fabric-setup)
  - [Install the Package](#install-the-package)
  - [Set up the Fabric Nodes](#set-up-the-fabric-nodes)
    - [Set up your Fabric node](#1-set-up-your-fabric-node)
    - [Start Fabric](#2-start-fabric)
    - [Add additional nodes](#3-add-additional-nodes)
- [Fabric Server - Start, Shutdown and Status](#fabric-server---start-shutdown-and-status)
- [For More Information](#for-more-information)

## Overview

The setup process includes:

- Hardware and infrastructure preparation
- Linux system configuration and required system users
- Installation of third-party components (PostgreSQL, Cassandra, Kafka, SQLite)
- Operating system tuning (OS limits and kernel parameters)
- TLS preparation for secure deployments
- Fabric server installation and cluster setup
- Startup, shutdown, and operational management

Follow the instructions in this guide to fully prepare your environment for Fabric and TDM installation.


## Getting Started

Before proceeding with the Fabric installation, you may need to set up one or more backend services depending on your deployment architecture. Fabric supports PostgreSQL, Cassandra, and SQLite as SystemDB options, and Kafka for multi-node configurations. 

SQLite is supported for lightweight, single-node, or development environments. No additional installation is required — support is built into Fabric.

Please refer to the following setup guides for detailed installation instructions:

- [PostgreSQL Setup Guide](02.2_Fabric_8.x.x_PG_setup.md)  
  Use this guide to install a PostgreSQL instance or cluster using K2view’s supplied package. **PostgreSQL is required when installing TDM.**

- [Cassandra Setup Guide](Cassandra_Setup.md)  
  Use this guide to install a Cassandra instance or cluster using K2view’s supplied package.

- [Kafka Setup Guide](Kafka_Setup.md)  
  Use this guide to install a Kafka cluster. Kafka is only required for multi-node Fabric deployments.

> **Note:** For production and TDM deployments, PostgreSQL is the recommended SystemDB.


## Fabric Pre-Installation Steps

### Hardware Requirements

Before installing Fabric, verify that your hardware meets the recommended specifications for your target environment (Development, QA, or Production).

Detailed hardware requirements can be found in the following document:

[Hardware Requirements Guide](https://support.k2view.com/Academy/articles/98_maintenance_and_operational/Hardware/2_All_Environments/README.html)

> It is  recommended to review these guidelines to ensure proper resource allocation based on your workload and deployment model.


### 3rd Party Components

Fabric relies on several third-party components and system services. K2view provides optional pre-packaged installers for Cassandra and Kafka to simplify setup, though vendor-supplied installation packages may also be used if preferred.

#### System Database

A System Database is mandatory for all Fabric installations. Fabric supports the following SystemDB options:

- **PostgreSQL**  
  Required for TDM installations. It is strongly recommended to use PostgreSQL as the SystemDB when installing Fabric in conjunction with TDM.

- **SQLite**  
  Recommended only for small-scale or development environments. SQLite support is built into Fabric; no external installation is required.

- **Cassandra**  
  Supported for larger scale deployments. Cassandra can be installed manually following vendor documentation, or by using K2view’s pre-packaged installation scripts to simplify setup.

#### Kafka

Kafka is required **only** for multi-node Fabric deployments. K2view provides Kafka installation packages, or you may install Kafka directly from vendor distributions. Kafka is required when Fabric nodes must coordinate shared state or when data integration patterns (streaming, IID sync, CDC, or audit) require a durable pub/sub mechanism.


### Operating System Preparation

#### System Users

Create the required system users and base directories as follows:

On **all servers**, create the base installation directory:

~~~bash
sudo mkdir -p /opt/apps
sudo chmod 755 /opt/apps
~~~

On each server, create the necessary user accounts depending on the components you are installing.  
(*For single-host deployments, all user creation commands may be executed on the same server.*)

**Fabric Server User:**

~~~bash
sudo useradd -m -d /opt/apps/fabric -s /bin/bash fabric
~~~

#### OS Limits

Update the system limits to support high resource utilization required by Fabric:

Append the following entries to `/etc/security/limits.conf`:

~~~bash
echo "root soft    nproc     unlimited" >> /etc/security/limits.conf

echo "fabric - nofile 1000000" >> /etc/security/limits.conf
echo "fabric - nproc 500000" >> /etc/security/limits.conf
~~~

#### Kernel Parameters

Update kernel parameters by appending the following entries to `/etc/sysctl.conf`:

~~~bash
echo "## Added by K2view" >> /etc/sysctl.conf
echo "vm.max_map_count = 1048575" >> /etc/sysctl.conf
echo "fs.file-max = 1000000" >> /etc/sysctl.conf
echo "net.ipv4.tcp_keepalive_time = 60" >> /etc/sysctl.conf
echo "net.ipv4.tcp_keepalive_probes = 3" >> /etc/sysctl.conf
echo "net.ipv4.tcp_keepalive_intvl = 10" >> /etc/sysctl.conf
~~~

Apply the changes:

~~~bash
sudo sysctl -p
~~~

> **Note:** Some OS limit changes may require a reboot or user session reload to take full effect.


#### Additional Steps for Cassandra

If Cassandra is planned as the SystemDB, perform the following additional configuration steps:

**Create Cassandra System User:**

~~~bash
useradd -m -d /opt/apps/cassandra  -s /bin/bash cassandra
~~~

**Update OS Limits:**

Append the following entries to `/etc/security/limits.conf`:

~~~bash
echo "cassandra - nofile 1000000" >> /etc/security/limits.conf
echo "cassandra - nproc 500000" >> /etc/security/limits.conf
~~~


#### Additional steps if using Kafka
In Some cases, Kafka instance or cluster will be required.

~~~bash
useradd -m -d /opt/apps/kafka  -s /bin/bash kafka
~~~

Update the OS limits as follows:

~~~bash
echo "kafka hard nofile 1000000" >> /etc/security/limits.conf
echo "kafka soft nofile 1000000" >> /etc/security/limits.conf
echo "kafka - nproc 500000" >> /etc/security/limits.conf
echo "kafka soft nofile 1000000" >> /etc/security/limits.conf
echo "kafka - nproc 500000" >> /etc/security/limits.conf
~~~

### Setting up TLS

If the system is planned to operate over SSL/TLS and requires hardening, OpenSSL must be installed on the servers.

In most Linux distributions, OpenSSL is installed by default. If it is not present, install it using your distribution's package manager or follow the official Linux distribution documentation.

You can verify that OpenSSL is installed by running:

~~~bash
openssl version
~~~


## Fabric Setup

The Fabric Server installation package will be supplied to you directly by K2view.

### Install the Package

1. Log in as the previously created `fabric` user:

~~~bash
sudo su - fabric
~~~

2. Download the Fabric installation package using the provided link.

3. Extract the package into the Fabric home directory:

~~~bash
tar -zxf [package_name].tar.gz -C /opt/apps/fabric
source /opt/apps/fabric/.bash_profile
~~~

> **Note:** Replace `[package_name].tar.gz` with the actual package file name.

### Set up the Fabric Nodes

The following procedure should be performed on the first node of your Fabric cluster. Cluster creation is optional — Fabric can run as a single node or in a multi-node configuration.

Once the first node is installed and reaches the `READY` state, you can add additional nodes by repeating the same process on each additional server.

#### 1. Set up your Fabric node

Select the appropriate guide based on your chosen SystemDB:

- **PostgreSQL Setup:**  
  [Fabric Setup with PostgreSQL SystemDB](02.2_Fabric_8.x.x_PG_setup.md)

- **SQLite Setup:**  
  [Fabric Setup with SQLite SystemDB](02.3_Fabric_8.x.x_Sqlite_setup.md)

- **Cassandra Setup:**  
  [Fabric Setup with Cassandra SystemDB](02.1_Fabric_8.x.x_Cassandra_setup.md)

#### 2. Start Fabric

After system configuration, start the Fabric server:

~~~bash
/opt/apps/fabric/fabric/bin/k2fabric start
~~~

After a short while, you should see the following confirmation message:

~~~
++++ Fabric is READY
~~~

#### 3. Add additional nodes

To add additional nodes to your Fabric cluster, repeat steps **1** and **2** on each additional server.

> **Note:**  
> The default login credentials are:  
> **User:** `admin`  
> **Password:** `admin`  
>  
> It is strongly recommended to change the default credentials. For instructions, refer to:  
> [Replace the Fabric Default Admin Password](/articles/99_fabric_infras/devops/09_fabric_replace_admin_password.md)


## Fabric Server - Start, Shutdown and Status

Use the following commands to operate Fabric on each node:

- **Stop Fabric:**

~~~bash
/opt/apps/fabric/fabric/bin/k2fabric stop
~~~

- **Start Fabric:**

~~~bash
/opt/apps/fabric/fabric/bin/k2fabric start
~~~

- **Check Fabric node status:**

~~~bash
/opt/apps/fabric/fabric/bin/k2fabric status
~~~

## For more information 

<ul>
   <li><a href="/articles/99_fabric_infras/devops/09_fabric_replace_admin_password.md">Replace the Fabric Default Admin Password</a></li>
   <li><a href="/articles/99_fabric_infras/devops/04_cassandra_hardening.md">Cassandra Hardening Procedures</a></li>
   <li><a href="/articles/99_fabric_infras/devops/06_kafka_hardening.md">Kafka Hardening Procedures</a></li>
   <li><a href="/articles/99_fabric_infras/devops/03_fabric_api_and_ui_hardening.md">Fabric UI Hardening Procedures</a></li>
   <li><a href="/articles/26_fabric_security/01_fabric_security_overview.md">Fabric Security and Authentication Methods</a></li>
</ul>
