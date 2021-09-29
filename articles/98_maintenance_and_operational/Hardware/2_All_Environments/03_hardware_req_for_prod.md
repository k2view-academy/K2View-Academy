## Fabric & TDM 7.xx Hardware Requirements for Production Environments

### PostgreSQL

- **PostgreSQL is generally required for TDM projects only**. 
- K2view supports PostgreSQL version 9.6 & 13.
- PostgreSQL requires 100G storage.
- PostgreSQL can be deployed in one of these ways:
  - On a VM (use latest CentOS/Redhat Operating System with latest patches), or 
  - As a service. 

 **For cloud deployments:**

- If using **AWS**: EC2: m5.xlarge
- If using **Azure** VM: A8v2 
- If using **GCP**: e2-standard-4

  For example, if you are implementing PostgreSQL as a PaaS, you can use the following:

  - For **AWS**: RDS PostgreSQL
  - For **GCP**: Cloud SQL PostgreSQL [see here for more about PostgreSQL](https://cloud.google.com/sql/docs/postgres/introduction) 

## Windows Server Specifications for Fabric Studio 

### Hardware Requirements 

**For Local installation:**

- Windows Version – Any one of the following: 10/11/2012/2016/2019 64bit Machine 
- CPU: 8 Cores/vCPUs.       
- RAM: 8GB 
- Storage: 100GB Available Disk Space       

**For Cloud installation:** 

- If using **AWS**: EC2: m5.xlarge
- If using **Azure** VM: A8v2 
- If using **GCP** e2-standard-4

#### **Windows Ports** 

Open the following ports on the Windows server: 

- 3389 – Used for RDP 

####  **Windows Permissions** 

Local administrator privileges are needed for the Fabric Studio installation.


## **Linux Execution Server Specifications** 

Use a dedicated server for Fabric, and a separate, dedicated server for Kafka

#### Hardware Requirements (for each Linux server): 

**For local installations:** 

- OS: latest CentOS/Redhat Operating System with latest patches
- CPU: 8 Cores/16vCPUs (Modern Xeon Processor)
- RAM: 64G 
  For Fabric, Redis, Kafka servers that runs Fabric, Redis - 64G RAM.   
- Network: Minimum 1G between the nodes and source DB’s 
- Storage: The preferred storage is attached local SSD’s in non-RAID configuration.  
  When **SAN must be used,** it must be flash and in RAID-0. 
  **NAS are not certified** 

**For Cloud installation:** 

Cloud instances for SOR:

   - If using **AWS**: EC2: m5.2xlarge
     - Use gp3, for K2view home and data storage
     - Use gp3 for the Kafka home directory 
   - If using **Azure**: Standard_DS14_v2
   - If using **GCP**: e2-standard-8

Cloud instances for non SOR:

   - If using **AWS**: EC2: i3.2xlarge – 
     - For the K2view home directory gp3, use the NVMe device
     - For the kafka home directory gp3 with provision of 25K IOPS 
   - If using **Azure**: Standard_DS14_v2, Standard_L8s_v2 
     - for the K2view storage use the NVMe device.  See also https://docs.microsoft.com/en-us/azure/architecture/best-practices/cassandra 
   - If using **GCP**: e2-standard-8
     - Make sure to attach to the VM “local SSD” type env NVMe. See also https://cloud.google.com/compute/docs/disks/performance for use of “Block storage”.

**For all installations, the FS configuration must be as follows:** 

- Volume of 50G /opt/apps/k2view/ - will be used also as the home directory for K2view user
- Volume of 100G* /opt/apps/k2view/storage  
- Volume of 100G* /opt/apps/kafka/   

**Note:** 
- the FS must provide IOPS of at least 30K read & 10K write on each node simultaneously, while all nodes are running the test at the same time.
- The above volume values should be modified in accordance with project scope and data retention requirements.

### Linux Server Setup

Follow the following steps to setup a Linux Server:

1. **NTP/chrony** installed and configured
2. Add a user ‘**fabric’** with group ‘**fabric’**
3. Add Admin permissions to the k2view user.
4. Provide read/write access to **/opt/apps/fabric**
5. Remote access option (SSH) enabled to user **k2view**.
6. Add a user ‘**cassandra** with group ‘**fabric’**
7. Provide read/write access to **/opt/apps/cassandra**
8. Remote access option (SSH) enabled to user **cassandra**.
9. Add a user ‘**kafka** with group ‘**fabric’**
10. Provide read/write access to **/opt/apps/kafka**
11. Remote access option (SSH) enabled to user **kafka**.

**Run the following as ROOT:**

~~~bash
echo "fabric - nofile  100000" >> /etc/security/limits.conf
echo "fabric - nproc  50000" >> /etc/security/limits.conf
echo "kafka hard  nofile 100000" >> /etc/security/limits.conf
echo "kafka soft  nofile 100000" >> /etc/security/limits.conf
echo "kafka - nproc  50000" >> /etc/security/limits.conf
## update /etc/sysctl.conf  ##
echo "## Added by  K2view - GabiO" >> /etc/sysctl.conf
echo  "vm.max_map_count = 1048575" >> /etc/sysctl.conf
echo "fs.file-max =  1000000" >> /etc/sysctl.conf 

~~~

### Fabric Linux Ports 

Open the following ports on the LINUX server, and make sure they are accessible outside the server: 

<table>
<tbody>
<tr>
<td style="width: 50%; height: 18px;">22</td>
<td style="width: 50%; height: 18px;">SSH, SCP</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">2181, 2888, 3888</td>
<td style="width: 50%; height: 18px;">Kafka Zookeeper</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">9093, 9091, 9081, 7270</td>
<td style="width: 50%; height: 18px;">Kafka</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">3213, 9443, 5124, 6379, 7170, 7270</td>
<td style="width: 50%; height: 18px;">K2View Fabric</td>
</tr>
</tbody>
</table>



### Cassandra Linux Execution Server Specifications 

Install Cassandra under **cassandra** user.  

#### Cassandra Servers   

For local installation: 

- **OS**: Latest Redhat/CentOS with latest patches (CentOS/ Redhat 8 are not certified)
Note: Cassandra requiers python 2.7 
- **CPU**: 16vCPUs (Modern Xeon Processor).
- **RAM**: Minimum 32GB RAM  
- **Network**: Minimum 1G between the nodes and source DB’s 
- **Storage**: The preferred storage is attached local SSD’s in non-RAID configuration.
  In cases where SAN must be used then it must be flash and in RAID-0.
  **NAS are not certified** 

**For Cloud installation:**

Cloud instances for SOR:

   - If using **AWS**: EC2: m5.4xlarge 
     - Use gp3 for Cassandra home and data storage
   - If using **Azure**: Standard_DS14_v2
   - If using **GCP**: c2-standard-16, see also https://cloud.google.com/compute/docs/disks/performance for use of “Block storage”

Cloud instances for non SOR:

   -  If using **AWS**: EC2: i3.2xlarge 
     -  For the Cassandra home directory gp3 storage, use the NVMe device for data storage
   -  If using **Azure**: Standard_DS14_v2, Standard_L8s_v2 
     - For the cassandra storage, use the NVMe device 
   -  If using **GCP**: c2-standard-16
     - Make sure to attach to the VM “local SSD” type env NVMe. See also https://cloud.google.com/compute/docs/disks/performance for use of “Block storage”

**For all installations, the FS configuration <u>for SOR</u> must be as follows:**

- Volume of 50G /opt/apps/cassandra/ 
- Volume of [25% of the data] /opt/apps/cassandra/storage/commitlog
- Volume of 2T* /opt/apps/cassandra/storage/data
- Volume of [10% of the data] /opt/apps/cassandra/storage/hints 

**Note:** 
- the FS must provide IOPS of at least 30K read & 10K write on each node simultaneously, while all nodes are running the test at the same time.
- The above volume values should be modified in accordance with project scope and data retention requirements.

**For all installations, the FS configuration for non SOR must be as follows:**   

- Volume of 50G /opt/apps/cassandra/
- Volume of 2T* /opt/apps/cassandra/storage/ mount on the NVMe LVM

The above volume values should be modified in accordance with project scope and data retention requirements. 

#### Cassandra Linux Server Setup

Follow these steps to setup the Linux Server:

1. **NTP/chrony** installed and configured
2. Add a user ‘**cassandra** with home directory **/opt/apps/cassandra**
3. Provide read/write access to **/opt/apps/cassandra**
4. Remote access option (SSH) enabled to user **cassandra**.
5. The SWAP should be disable for  **cassandra**.

Run the following as ROOT:

~~~bash
echo "root soft    nproc   unlimited" >>  /etc/security/limits.conf
echo "cassandra - nofile 100000" >>  /etc/security/limits.conf
echo "cassandra - nproc 50000" >>  /etc/security/limits.conf
## update /etc/sysctl.conf ##
echo "## Added by K2view - GabiO" >>  /etc/sysctl.conf
echo "vm.max_map_count = 1048575" >>  /etc/sysctl.conf
echo  "fs.file-max = 1000000" >> /etc/sysctl.conf 

~~~
### Linux Ports 

The following ports should be opened on the LINUX server and accessible outside the server: 

<table>
<tbody>
<tr>
<td style="width: 50%; height: 18px;"><strong>22</strong></td>
<td style="width: 50%; height: 18px;"><strong>SSH, SCP</strong></td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">9042, 9142, 7000, 7001, 7199, 7070</td>
<td style="width: 50%; height: 18px;">cassandra</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">8080</td>
<td style="width: 50%; height: 18px;">cassandra-reaper</td>
</tr>
</tbody>
</table>





[![Previous](/articles/images/Previous.png)](02_hardware_req_for_dev_qa.md) 
