**Fabric & TDM 7.xx Hardware Requirements**

For Dev/QA/Prod

June, 2021


# INTRODUCTION
This document provides information regarding hardware, software, and operating system requirements for Fabric and TDM installations. 

Separate requirements are presented when working in a Development/Quality Assurance environment, and when working in a Production environment.  

It is assumed that the reader is familiar with Fabric and TDM.  

# **DIAGRAM** 
The diagram below depicts a generic system layout for Fabric and Test Data Management (TDM) projects. The number of Fabric, Cassandra and Kafka nodes can vary and should be based on a sizing assessment done for each project.

add PIC


------------------------------------------------------------------------------------------------------------------------------------------------------------------

# **DEVELOPMENT/QA ENVIRONMENT**
## **PostgreSQL** 
**PostgreSQL is generally required for TDM projects only**. 
It can be deployed on a VM or alternatively use PostgreSQL as a service. K2view supports PostgreSQL version 9.6 & 13.

In case it is deployed on a VM, use latest CentOS/Redhat Operating System with latest patches.

### For cloud deployments: 

- If using **AWS**: EC2: a1.2xlarge, m5.xlarge
- If using **Azure** VM: A8v2 
- If using **GCP**: e2-standard-4

The PostgreSQL requires 100G storage. 

If you are implementing PostgreSQL as a PaaS you can use for example:
- For **AWS**: RDS PostgreSQL 
- For **GCP**: Cloud SQL PostgreSQL [see here](https://cloud.google.com/sql/docs/postgres/introduction) 

## **FABRIC STUDIO WINDOWS SERVER SPECIFICATIONS** 
### MINIMUM HW REQUIREMENTS 

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

------------------------------------------------------------------------------------------------------------------------------------------------------------------

## **FABRIC/Cassandra/Kafka LINUX EXECUTION SERVER SPECIFICATIONS** 

### MINIMUM HW CONFIGURATION FOR EACH LINUX SERVER 
1 x CentOS 7.9 Operating System or Redhat 7.9 with latest patches (CentOS/ Redhat 8 are not certified), and the following hardware: 

- **CPU** 4 Cores/vCPUs (Modern Xeon Processor).  
- **RAM** 12GB. 
- **Storage** The preferred storage is attached local SSD’s in non-RAID configuration. In cases where SAN must be used, it has to be flush one and in RAID-0. 
  **NAS are not certified**. 
 
  The FS volumes required are as follows: 
  >* Volume of 150G /opt/apps/k2view/ - will be used also as the home directory for K2view user 
  >* Volume of 200G /opt/apps/cassandra/ 
  >* Volume of 50G /opt/apps/kafka/ 

For cloud installation: 
-
- If using **AWS**: EC2: a1.2xlarge, m5.xlarge
- If using **Azure** VM:  D8a v4, D8d v4
- If using **GCP**: e2-standard-4


#### Linux Server Setup
Follow these steps to setup a Linux Server:
-
- 1. **NTP/chrony** installed and configured
- 1. Add a user ‘**k2view’** with group ‘**k2view’**
- 1. Add Admin permissions to the k2view user.
- 1. Provide read/write access to **/opt/apps/k2view**
- 1. Remote access option (SSH) enabled to user **k2view**.
- 1. Add a user ‘**cassandra** with group ‘**k2view’**
- 1. Provide read/write access to **/opt/apps/cassandra**
- 1. Remote access option (SSH) enabled to user **cassandra**.
- 1. Add a user ‘**kafka** with group ‘**k2view’**
- 1. Provide read/write access to **/opt/apps/kafka**
- 1. Remote access option (SSH) enabled to user **kafka**.
- 1. Run the following as ROOT:

|<p>echo "root soft    nproc     unlimited" >> /etc/security/limits.conf</p><p>echo "cassandra - nofile 100000" >> /etc/security/limits.conf</p><p>echo "cassandra - nproc 50000" >> /etc/security/limits.conf</p><p>echo "k2view - nofile 100000" >> /etc/security/limits.conf</p><p>echo "k2view - nproc 50000" >> /etc/security/limits.conf</p><p>echo "kafka hard nofile 100000" >> /etc/security/limits.conf</p><p>echo "kafka soft nofile 100000" >> /etc/security/limits.conf</p><p>echo "kafka - nproc 50000" >> /etc/security/limits.conf</p><p>## update /etc/sysctl.conf ##</p><p>echo "## Added by K2view - GabiO" >> /etc/sysctl.conf</p><p>echo "vm.max\_map\_count = 1048575" >> /etc/sysctl.conf</p><p>echo "fs.file-max = 1000000" >> /etc/sysctl.conf</p><p></p>|
| :- |





####
####
####
####
####
####
#### LINUX PORTS 
The following ports should be opened on the LINUX server and accessible from outside the machine: 



|22 |SSH, SCP |
| :- | :- |
|7000 |cassandra |
|7001 |cassandra |
|7199 |cassandra |
|9042 |cassandra |
|9160 |Cassandra |
|<p>3213</p><p>9443 </p>|K2view Fabric |
|6379 |Redis |
|<p>2181 </p><p>2888 </p><p>3888 </p>|<p> </p><p>Kafka Zookeeper </p>|
|9093 |Kafka |
|9081 |Kafka JMX |
|5124 |K2view Fabric |

# **PRODUCTION ENVRIONMENT**
## **FABRIC STUDIO WINDOWS SERVER SPECIFICATIONS** 
#### MINIMUM HW REQUIREMENTS FOR THE STUDIO 
For Local installation: 
-
- Windows Version – Any one of the following: 10/11/2008 R2/ 2012 / 2012 R2 / 2016 / 2019 Machine 
- 8 Cores/vCPUs. 	 
- 8GB RAM 
- 100GB Available Disk Space 

For Cloud installation: 
-
- If using **AWS**: EC2: a1.2xlarge, m5.xlarge
- If using **Azure**:  D8a v4, D8d v4
- If using **GCP**: e2-standard-4

#### WINDOWS PORTS 
The following ports should be opened on the Windows server: 
-
- 3389 –  Used for RDP 

#### WINDOWS PERMISSIONS 
Local administrator privileges are needed for the Fabric Studio installation.

## **PostgreSQL** 
**Note: PostgreSQL is generally required for TDM projects only**. 
It can be deployed on a VM or alternatively use PostgreSQL as a service. K2view supports PostgreSQL versions 9.6 & 13.

When deployed as a VM, use latest CentOS/Redhat Operating System with latest patches. 

For cloud installation:
-
- If using **AWS**: EC2: a1.2xlarge, m5.xlarge
- If using **Azure:** VM: A8v2 
- If using **GCP**: e2-standard-4

PostgreSQL requires 100G storage

If you are using PostgreSQL as PaaS you can use for example:
-
- If using **AWS**: RDS PostgreSQL 
- If using **GCP**: Cloud SQL PostgreSQL

-
## **LINUX EXECUTION SERVER SPECIFICATIONS** 
On this server, we will install a Fabric server and a Kafka server under different users.

#### MINIMUM HW REQUIREMENTS AND CONFIGRATIONS FOR EACH LINUX SERVER: 
FABRIC SERVER 

For local installations: 
-
- OS: CentOS 7.9 Operating System or Redhat 7.9 with latest patches (CentOS/ Redhat 8 are not certified)  
- CPU: Modern Xeon Processor.	
- 8 Cores/16vCPUs.	
- RAM: 64G 
- For Fabric, Redis, Kafka servers that runs Fabric, Redis - 64GB RAM.	
  - - Network:  Minimum 1G between the nodes and source DB’s 
  - - Storage: The preferred storage is attached local SSD’s in non-RAID configuration.	

When **SAN must be used,** it must be flash and in RAID-0. 

**NAS are not certified** 

For Cloud installation: 
##### `   `**Cloud instances for SOR**
- If using **AWS**: 
  - - EC2: m5.2xlarge – 
  - - Use  gp3, for K2view home and data storage
  - - Use  gp3 for the Kafka home directory 
- If using **Azure**: Standard\_DS14\_v2
- If using **GCP**: e2-standard-8
##### `   `**Cloud instances for non SOR**
- If using **AWS**: 
- - EC2: i3.2xlarge – 
    - - For the K2view home directory gp3, use the NVMe device
    - - For the kafka home directory gp3 with provision of 25K IOPS 
- If using **Azure**: Standard\_DS14\_v2, Standard\_L8s\_v2 - for the K2view storage use the NVMe device 
- see https://docs.microsoft.com/en-us/azure/architecture/best-practices/cassandra  
- If using **GCP**: e2-standard-8
  - - Make sure to attach to the VM “local SSD” type env NVMe.
  -   see also <https://cloud.google.com/compute/docs/disks/performance> for use of “Block storage”.







For all installations, the FS configuration must be as follows: 

- Volume of 50G /opt/apps/k2view/ - will be used also as the home directory for K2view user
- Volume of 100G\* /opt/apps/k2view/storage 	
- Volume of 100G\* /opt/apps/kafka/ 	

**Note:** the FS must provide IOPS of at least 30K read & 10K write on each node simultaneously, while all nodes are running the test at the same time.	

The above volume values should be increased based on project scope and data retention requirements.




#### **Fabric Linux Server Setup**
Follow these steps to setup the Linux Server:
-
- 1. **NTP/chrony** installed and configured
- 1. Add a user ‘**k2view’** with group ‘**k2view’**
- 1. Add Admin permissions to the k2view user.
- 1. Provide read/write access to **/opt/apps/k2view**
- 1. Remote access option (SSH) enabled to user **k2view**.
- 1. Provide read/write access to **/opt/apps/kafka**
- 1. Remote access option (SSH) enabled to user **kafka**.
- 1. Run the following as ROOT:

|<p>echo "root soft    nproc     unlimited" >> /etc/security/limits.conf</p><p>echo "k2view - nofile 100000" >> /etc/security/limits.conf</p><p>echo "k2view - nproc 50000" >> /etc/security/limits.conf</p><p>echo "kafka hard nofile 100000" >> /etc/security/limits.conf</p><p>echo "kafka soft nofile 100000" >> /etc/security/limits.conf</p><p>echo "kafka - nproc 50000" >> /etc/security/limits.conf</p><p>## update /etc/sysctl.conf ##</p><p>echo "## Added by K2view - GabiO" >> /etc/sysctl.conf</p><p>echo "vm.max\_map\_count = 1048575" >> /etc/sysctl.conf</p><p>echo "fs.file-max = 1000000" >> /etc/sysctl.conf</p><p></p>|
| :- |

#### **Fabric LINUX PORTS** 
The following ports should be opened on the LINUX server and accessible outside the server: 



|22 |SSH, SCP |
| :- | :- |
|6379 |Redis |
|<p>2181 </p><p>2888 </p><p>3888 </p>|<p> </p><p>Kafka Zookeeper </p>|
|9093 |Kafka |
|<p>9081 </p><p>7270</p>|Kafka JMX |
|<p>3213 </p><p>9443</p>|K2view Fabric |
|5124 |K2view Fabric |
|<p>8199 </p><p>7170</p>|K2view Fabric JMX |


## **CASSANDRA LINUX EXECUTION SERVER SPECIFICATIONS** 
On this server we will Cassandra under different users. 

#### CASSANDRA SERVERS 
For local installation: 
-
- **OS**: CentOS 7.9 Operating System or Redhat 7.9 with latest patches (CentOS/ Redhat 8 are not certified)  
- **CPU**: 16vCPUs (Modern Xeon Processor).
- **RAM**: Minimum 32GB RAM	
- **Network**:  Minimum 1G between the nodes and source DB’s 
- **Storage**: The preferred storage is attached local SSD’s in non-RAID configuration.	

In cases** where SAN must be used then it must be flash and in RAID-0.

NAS are not certified 

For Cloud installation:
##### `   `**Cloud instances for SOR**
- If using **AWS**: 
- - EC2: m5.4xlarge – 
- - Use  gp3 for Cassandra home and data storage
- If using **Azure**: Standard\_DS14\_v2
- If using **GCP**: c2-standard-16
- see also <https://cloud.google.com/compute/docs/disks/performance> for use of “Block storage”
#####
##### `   `**Cloud instances for non SOR**
- If using **AWS**: 
  - - EC2: i3.2xlarge – (for the Cassandra home directory gp3 storage, use the NVMe device)
- If using **Azure**: Standard\_DS14\_v2, Standard\_L8s\_v2 - for the cassandra storage we will use the NVMe device 
- If using **GCP**: c2-standard-16
  - - Make sure to attach to the VM “local SSD” type env NVMe
  -   see also <https://cloud.google.com/compute/docs/disks/performance> for use of “Block storage”

For all installations, the FS configuration for SOR must be as follows: 

- Volume of 50G /opt/apps/cassandra/	
- Volume of [25% of the data] /opt/apps/cassandra/storage/commitlog
- Volume of 2T\* /opt/apps/cassandra/storage/data
- Volume of [10% of the data] /opt/apps/cassandra/storage/hints

**Note:** the FS must provide IOPS of at least 30K read & 10K write on each node simultaneously, while all nodes are running the test at the same time. 	

The above volume values should be increased based on project scope and data retention requirements.

All installations, the FS configuration for non SOR must be as follows: 
-
- Volume of 50G /opt/apps/cassandra/	
- Volume of 2T\* /opt/apps/cassandra/storage/ mount on the NVMe LVM	

The above volume values should be increased based on project scope and data retention requirements.

#### **Cassandra Linux Server Setup**
Follow these steps to setup the Linux Server:
-
- 1. **NTP/chrony** installed and configured
- 1. Add a user ‘**cassandra** with home directory **/opt/apps/cassandra**
- 1. Provide read/write access to **/opt/apps/cassandra**
- 1. Remote access option (SSH) enabled to user **cassandra**.
- 1. Run the following as ROOT:

|<p>echo "root soft    nproc     unlimited" >> /etc/security/limits.conf</p><p>echo "cassandra - nofile 100000" >> /etc/security/limits.conf</p><p>echo "cassandra - nproc 50000" >> /etc/security/limits.conf</p><p>## update /etc/sysctl.conf ##</p><p>echo "## Added by K2view - GabiO" >> /etc/sysctl.conf</p><p>echo "vm.max\_map\_count = 1048575" >> /etc/sysctl.conf</p><p>echo "fs.file-max = 1000000" >> /etc/sysctl.conf</p><p></p>|
| :- |
**LINUX PORTS** 

The following ports should be opened on the LINUX server and accessible outside the server: 



|22 |SSH, SCP |
| :- | :- |
|7000 |Cassandra |
|7001 |cassandra |
|<p>7199 </p><p>7070</p>|Cassandra JMX |
|9042 |cassandra |
|9160 |Cassandra |

