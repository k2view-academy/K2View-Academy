<strong>Fabric Maintenance And Operational<strong>
        

<ul>
    <li><a href="/articles/98_maintenance_and_operational/">XXX</a></li>
</ul>

![](RackMultipart20210629-4-12ei9qm_html_81a4f7c90427e55.jpg)

![](RackMultipart20210629-4-12ei9qm_html_feaf1b7d680d7ce2.png)

# **Fabric &amp; TDM 7.xx Hardware Requirements**

## For Dev/QA/Prod

June, 2021

# Contents

[1INTRODUCTION 3](#_Toc75858192)

[2DIAGRAM 3](#_Toc75858193)

[3DEVELOPMENT/QA ENVIRONMENT 4](#_Toc75858194)

[3.1PostgreSQL 4](#_Toc75858195)

[3.2FABRIC STUDIO WINDOWS SERVER SPECIFICATIONS 4](#_Toc75858196)

[3.3FABRIC/Cassandra/Kafka LINUX EXECUTION SERVER SPECIFICATIONS 5](#_Toc75858197)

[4PRODUCTION ENVRIONMENT 7](#_Toc75858198)

[4.1FABRIC STUDIO WINDOWS SERVER SPECIFICATIONS 7](#_Toc75858199)

[4.2PostgreSQL 7](#_Toc75858200)

[4.3LINUX EXECUTION SERVER SPECIFICATIONS 8](#_Toc75858201)

[4.4CASSANDRA LINUX EXECUTION SERVER SPECIFICATIONS 11](#_Toc75858202)

[5LEGAL NOTICES 13](#_Toc75858203)

1.
## INTRODUCTION

This document provides information regarding hardware, software, and operating system requirements for Fabric and TDM installations.

Separate requirements are presented when working in a Development/Quality Assurance environment, and when working in a Production environment.

It is assumed that the reader is familiar with Fabric and TDM.

1.
## DIAGRAM

The diagram below depicts a generic system layout for Fabric and Test Data Management (TDM) projects. The number of Fabric, Cassandra and Kafka nodes can vary and should be based on a sizing assessment done for each project.

![](RackMultipart20210629-4-12ei9qm_html_d08c1913973d7230.png)

1.
## DEVELOPMENT/QA ENVIRONMENT

  1.
## PostgreSQL

PostgreSQL is generally required for TDM projects only. It can be deployed on a VM or alternatively use PostgreSQL as a service. K2view supports PostgreSQL version 9.6 &amp; 13.

In case it is deployed on a VM, use latestCentOS/Redhat Operating System with latest patches.

For cloud deployments:

- If using **AWS** : EC2: a1.2xlarge, m5.xlarge
- If using **Azure** VM: A8v2
- If using **GCP** : e2-standard-4

The PostgreSQL requires 100G storage

If you are implementing PostgreSQL as a PaaS you can use for example:

- For **AWS** : RDS PostgreSQL
- For **GCP** : Cloud SQL PostgreSQL [see here](https://cloud.google.com/sql/docs/postgres/introduction)

  1.
## FABRIC STUDIO WINDOWS SERVER SPECIFICATIONS

#### MINIMUM HW REQUIREMENTS

For Local installation:

- Windows Version – Any one of the following: 10/11/2012/2016/2019 64bit Machine
- 8 Cores/vCPUs.
- 8GB RAM
- 100GB Available Disk Space

For Cloud installation:

- AWS: EC2: a1.2xlarge, m5.xlarge
- Azure VM: A8v2
- GCP: e2-standard-4

#### WINDOWS PORTS

The following ports should be opened on the Windows server:

- 3389 – Used for RDP

#### WINDOWS PERMISSIONS

Local administrator privileges are needed for the Fabric Studio installation.

  1.
## FABRIC/Cassandra/Kafka LINUX EXECUTION SERVER SPECIFICATIONS

#### MINIMUM HW CONFIGURATION FOR EACH LINUX SERVER

1 x CentOS 7.9 Operating System or Redhat 7.9 with latest patches (CentOS/ Redhat 8 are not certified), and the following hardware:

- Modern Xeon Processor.
- 4 Cores/vCPUs.
- 12GB RAM.
- The preferred storage is attached local SSD&#39;s in non-RAID configuration. In cases where SAN must be used, it has to be flush one and in RAID-0.
  - **NAS are not certified**.

- The FS volumes required are as follows:

- Volume of 150G /opt/apps/k2view/ - will be used also as the home directory for K2view user
- Volume of 200G /opt/apps/cassandra/
- Volume of 50G /opt/apps/kafka/

For cloud installation:

- If using **AWS** : EC2: a1.2xlarge, m5.xlarge
- If using **Azure** VM: D8a v4, D8d v4
- If using **GCP** : e2-standard-4

#### Linux Server Setup

Follow these steps to setup a Linux Server:

1. **NTP/chrony** installed and configured
2. Add a user &#39; **k2view&#39;** with group &#39; **k2view&#39;**
3. Add Admin permissions to the k2view user.
4. Provide read/write access to **/opt/apps/k2view**
5. Remote access option (SSH) enabled to user **k2view**.
6. Add a user &#39; **cassandra** with group &#39; **k2view&#39;**
7. Provide read/write access to **/opt/apps/cassandra**
8. Remote access option (SSH) enabled to user **cassandra**.
9. Add a user &#39; **kafka** with group &#39; **k2view&#39;**
10. Provide read/write access to **/opt/apps/kafka**
11. Remote access option (SSH) enabled to user **kafka**.
12. Run the following as ROOT:

| echo &quot;root soft    nproc     unlimited&quot; \&gt;\&gt; /etc/security/limits.confecho &quot;cassandra - nofile 100000&quot; \&gt;\&gt; /etc/security/limits.confecho &quot;cassandra - nproc 50000&quot; \&gt;\&gt; /etc/security/limits.confecho &quot;k2view - nofile 100000&quot; \&gt;\&gt; /etc/security/limits.confecho &quot;k2view - nproc 50000&quot; \&gt;\&gt; /etc/security/limits.confecho &quot;kafka hard nofile 100000&quot; \&gt;\&gt; /etc/security/limits.confecho &quot;kafka soft nofile 100000&quot; \&gt;\&gt; /etc/security/limits.confecho &quot;kafka - nproc 50000&quot; \&gt;\&gt; /etc/security/limits.conf## update /etc/sysctl.conf ##echo &quot;## Added by K2view - GabiO&quot; \&gt;\&gt; /etc/sysctl.confecho &quot;vm.max\_map\_count = 1048575&quot; \&gt;\&gt; /etc/sysctl.confecho &quot;fs.file-max = 1000000&quot; \&gt;\&gt; /etc/sysctl.conf
 |
| --- |

####


####


####


####


####


####


#### LINUX PORTS

The following ports should be opened on the LINUX server and accessible from outside the machine:

| 22 | SSH, SCP |
| --- | --- |
| 7000 | cassandra |
| 7001 | cassandra |
| 7199 | cassandra |
| 9042 | cassandra |
| 9160 | Cassandra |
| 32139443 | K2view Fabric |
| 6379 | Redis |
| 2181 2888 3888 | Kafka Zookeeper |
| 9093 | Kafka |
| 9081 | Kafka JMX |
| 5124 | K2view Fabric |

1.
## PRODUCTION ENVRIONMENT

  1.
## FABRIC STUDIO WINDOWS SERVER SPECIFICATIONS

#### MINIMUM HW REQUIREMENTS FOR THE STUDIO

For Local installation:

- Windows Version – Any one of the following: 10/11/2008 R2/ 2012 / 2012 R2 / 2016 / 2019 Machine
- 8 Cores/vCPUs.
- 8GB RAM
- 100GB Available Disk Space

For Cloud installation:

- If using **AWS** : EC2: a1.2xlarge, m5.xlarge
- If using **Azure** : D8a v4, D8d v4
- If using **GCP** : e2-standard-4

#### WINDOWS PORTS

The following ports should be opened on the Windows server:

- 3389 – Used for RDP

#### WINDOWS PERMISSIONS

Local administrator privileges are needed for the Fabric Studio installation.

  1.
## PostgreSQL

**Note: PostgreSQL is generally required for TDM projects only**.
 It can be deployed on a VM or alternatively use PostgreSQL as a service. K2view supports PostgreSQL versions 9.6 &amp; 13.

When deployed as a VM, use latestCentOS/Redhat Operating System with latest patches.

For cloud installation:

- If using **AWS** : EC2: a1.2xlarge, m5.xlarge
- If using **Azure:** VM: A8v2
- If using **GCP** : e2-standard-4

PostgreSQL requires 100G storage

If you are using PostgreSQL as PaaS you can use for example:

- If using **AWS** : RDS PostgreSQL
- If using **GCP** : Cloud SQL PostgreSQL

  1.
## LINUX EXECUTION SERVER SPECIFICATIONS

On this server, we will install a Fabric server and a Kafka server under different users.

#### MINIMUM HW REQUIREMENTS AND CONFIGRATIONS FOR EACH LINUX SERVER:

FABRIC SERVER

For local installations:

- OS: CentOS 7.9 Operating System or Redhat 7.9 with latest patches (CentOS/ Redhat 8 are not certified)
- CPU: Modern Xeon Processor.
- 8 Cores/16vCPUs.
- RAM: 64G
 For Fabric, Redis, Kafka servers that runs Fabric, Redis - 64GB RAM.

    - Network: Minimum 1G between the nodes and source DB&#39;s
    - Storage: The preferred storage is attached local SSD&#39;s in non-RAID configuration.

When **SAN must be used,** it must be flash and in RAID-0.

**NAS are not certified**

For Cloud installation:

##### **Cloud instances for SOR**

- If using **AWS** :

    - EC2: m5.2xlarge –
      - Use gp3, for K2view home and data storage
      - Use gp3 for the Kafka home directory
- If using **Azure** : Standard\_DS14\_v2
- If using **GCP** : e2-standard-8

##### **Cloud instances for non SOR**

- If using **AWS** :
  - EC2: i3.2xlarge –

      - For the K2view home directory gp3, use the NVMe device
      - For the kafka home directory gp3 with provision of 25K IOPS
- If using **Azure** : Standard\_DS14\_v2, Standard\_L8s\_v2 - for the K2view storage use the NVMe device
 see https://docs.microsoft.com/en-us/azure/architecture/best-practices/cassandra
- If using **GCP** : e2-standard-8

      - Make sure to attach to the VM &quot;local SSD&quot; type env NVMe.
 see also [https://cloud.google.com/compute/docs/disks/performance](https://cloud.google.com/compute/docs/disks/performance) for use of &quot;Block storage&quot;.

For all installations, the FS configuration must be as follows:

- Volume of 50G /opt/apps/k2view/ - will be used also as the home directory for K2view user
- Volume of 100G\* /opt/apps/k2view/storage
- Volume of 100G\* /opt/apps/kafka/

**Note:** the FS must provide IOPS of at least 30K read &amp; 10K write on each node simultaneously,while all nodes are running the test at the same time.

The above volume values should be increased based on project scope and data retention requirements.

#### **Fabric Linux Server Setup**

Follow these steps to setup the Linux Server:

1. **NTP/chrony** installed and configured
2. Add a user &#39; **k2view&#39;** with group &#39; **k2view&#39;**
3. Add Admin permissions to the k2view user.
4. Provide read/write access to **/opt/apps/k2view**
5. Remote access option (SSH) enabled to user **k2view**.
6. Provide read/write access to **/opt/apps/kafka**
7. Remote access option (SSH) enabled to user **kafka**.
8. Run the following as ROOT:


| echo &quot;root soft    nproc     unlimited&quot; \&gt;\&gt; /etc/security/limits.confecho &quot;k2view - nofile 100000&quot; \&gt;\&gt; /etc/security/limits.confecho &quot;k2view - nproc 50000&quot; \&gt;\&gt; /etc/security/limits.confecho &quot;kafka hard nofile 100000&quot; \&gt;\&gt; /etc/security/limits.confecho &quot;kafka soft nofile 100000&quot; \&gt;\&gt; /etc/security/limits.confecho &quot;kafka - nproc 50000&quot; \&gt;\&gt; /etc/security/limits.conf## update /etc/sysctl.conf ##echo &quot;## Added by K2view - GabiO&quot; \&gt;\&gt; /etc/sysctl.confecho &quot;vm.max\_map\_count = 1048575&quot; \&gt;\&gt; /etc/sysctl.confecho &quot;fs.file-max = 1000000&quot; \&gt;\&gt; /etc/sysctl.conf
 |
| --- |

#### **Fabric LINUX PORTS**

The following ports should be opened on the LINUX server and accessible outside the server:

| 22 | SSH, SCP |
| --- | --- |
| 6379 | Redis |
| 2181 2888 3888 | Kafka Zookeeper |
| 9093 | Kafka |
| 9081 7270 | Kafka JMX |
| 3213 9443 | K2view Fabric |
| 5124 | K2view Fabric |
| 8199 7170 | K2view Fabric JMX |

  1.
## CASSANDRA LINUX EXECUTION SERVER SPECIFICATIONS

On this server we will Cassandra under different users.

#### CASSANDRA SERVERS

For local installation:

- **OS** : CentOS 7.9 Operating System or Redhat 7.9 with latest patches (CentOS/ Redhat 8 are not certified)
- **CPU** : 16vCPUs (Modern Xeon Processor).
- **RAM** : Minimum 32GB RAM
- **Network** : Minimum 1G between the nodes and source DB&#39;s
- **Storage** : The preferred storage is attached local SSD&#39;s in non-RAID configuration.

In caseswhere SAN must be used then it must be flash and in RAID-0.

NAS are not certified

For Cloud installation:

##### **Cloud instances for SOR**

- If using **AWS** :

  - EC2: m5.4xlarge –
  - Use gp3 for Cassandra home and data storage

- If using **Azure** : Standard\_DS14\_v2
- If using **GCP** : c2-standard-16
 see also [https://cloud.google.com/compute/docs/disks/performance](https://cloud.google.com/compute/docs/disks/performance) for use of &quot;Block storage&quot;

#####

##### **Cloud instances for non SOR**

- If using **AWS** :

    - EC2: i3.2xlarge – (for the Cassandra home directory gp3 storage, use the NVMe device)
- If using **Azure** : Standard\_DS14\_v2, Standard\_L8s\_v2 - for the cassandra storage we will use the NVMe device
- If using **GCP** : c2-standard-16

    - Make sure to attach to the VM &quot;local SSD&quot; type env NVMe
 see also [https://cloud.google.com/compute/docs/disks/performance](https://cloud.google.com/compute/docs/disks/performance) for use of &quot;Block storage&quot;


For all installations, the FS configuration for SOR must be as follows:

- Volume of 50G /opt/apps/cassandra/
- Volume of [25% of the data] /opt/apps/cassandra/storage/commitlog
- Volume of 2T\* /opt/apps/cassandra/storage/data
- Volume of [10% of the data] /opt/apps/cassandra/storage/hints

**Note:** the FS must provide IOPS of at least 30K read &amp; 10K write on each node simultaneously, while all nodes are running the test at the same time.

The above volume values should be increased based on project scope and data retention requirements.

All installations, the FS configuration for non SOR must be as follows:

- Volume of 50G /opt/apps/cassandra/
- Volume of 2T\* /opt/apps/cassandra/storage/ mount on the NVMe LVM

The above volume values should be increased based on project scope and data retention requirements.

#### **Cassandra Linux Server Setup**

Follow these steps to setup the Linux Server:

1. **NTP/chrony** installed and configured
2. Add a user &#39; **cassandra** with home directory **/opt/apps/cassandra**
3. Provide read/write access to **/opt/apps/cassandra**
4. Remote access option (SSH) enabled to user **cassandra**.
5. Run the following as ROOT:


| echo &quot;root soft    nproc     unlimited&quot; \&gt;\&gt; /etc/security/limits.confecho &quot;cassandra - nofile 100000&quot; \&gt;\&gt; /etc/security/limits.confecho &quot;cassandra - nproc 50000&quot; \&gt;\&gt; /etc/security/limits.conf## update /etc/sysctl.conf ##echo &quot;## Added by K2view - GabiO&quot; \&gt;\&gt; /etc/sysctl.confecho &quot;vm.max\_map\_count = 1048575&quot; \&gt;\&gt; /etc/sysctl.confecho &quot;fs.file-max = 1000000&quot; \&gt;\&gt; /etc/sysctl.conf
 |
| --- |

**LINUX PORTS**

The following ports should be opened on the LINUX server and accessible outside the server:

| 22 | SSH, SCP |
| --- | --- |
| 7000 | Cassandra |
| 7001 | cassandra |
| 7199 7070 | Cassandra JMX |
| 9042 | cassandra |
| 9160 | Cassandra |

1.
## LEGAL NOTICES

This Guide is delivered subject to the following conditions and restrictions:

- This Guide contains copyrighted work and proprietary information belonging to K2View.
- This Guide and information contained herein are delivered to you as is, and K2View makes no warranty whatsoever as to its accuracy, completeness, fitness for a particular purpose, or use. Any use of the documentation and/or the information contained herein, is at the user&#39;s risk, and K2View is not responsible for any direct, indirect, special, incidental, or consequential damages arising out of such use of the documentation. Technical or other inaccuracies, as well as typographical errors, may occur in this Guide.
- This Guide and the information contained herein and any part thereof are confidential and proprietary to K2View. All intellectual property rights (including, without limitation, copyrights, trade secrets, trademarks, etc.) evidenced by or embodied in and/or attached, connected, or related to this Guide, as well as any information contained herein, are and shall be owned solely by K2View. K2View does not convey to you an interest in or to this Guide, to information contained herein, or to its intellectual property rights, but only a personal, limited, fully revocable right to use the Guide solely for reviewing purposes. Unless explicitly set forth otherwise, you may not reproduce by any means any document and/or copyright contained herein.
- Information in this Guide is subject to change without notice. Corporate and individual names and data used in examples herein are fictitious unless otherwise noted.

Copyright © 2021 K2View Ltd. All rights reserved.

The following are trademark of K2View:

K2View logo, K2View&#39;s platform.

K2View reserves the right to update this list from time to time.

Other company and brand products and service names in this Guide are trademarks or registered trademarks of their respective holders.

Revision History:

| Revision | Date | Author | Changes |
| --- | --- | --- | --- |
| 0.1 | 10/15/2020 | Giora S,Yevgeni B | Initial Release |
| 0.2 | 10/17/2020 | Gabi Ochman | Review and fix, added AWS instances |
| 0.3 | 10/18/2020 | Gabi Ochman | Update layout, added linux setting per server type |
| 0.5 | 10/21/2020 | Gabi Ochman | Update instances for SOR |
| 0.6 | 6/8/2021 | Gabi Ochman | Update requirement for GCP and TDM 7.xx |

© 2021 K2VIEW. All Rights Reserved. Page **22** of **22**
