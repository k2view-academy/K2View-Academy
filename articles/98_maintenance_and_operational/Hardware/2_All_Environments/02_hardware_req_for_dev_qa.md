## Fabric & TDM 7.xx Hardware Requirements for Development and QA Environments

### PostgreSQL

- **PostgreSQL is generally required for TDM projects only**. 
- K2view supports PostgreSQL version 9.6 & 13.
- PostgreSQL requires 100G storage.
- PostgreSQL can be deployed in one of these ways:
  - On Premise, as a virtual or physical machine with 4 Cpu cores and 16 GB Ram  (use latest CentOS/Redhat/Ubuntu OS with latest patches).
  - As a service. 

 **For cloud deployments:**

- If using **AWS**: EC2: a1.2xlarge, m5.xlarge
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

------

## Fabric/Cassandra/Kafka Linux Execution Server Specifications

#### Hardware Requirements (for each Linux server): 

- OS: latest CentOS/Redhat Operating System with latest patches
**Note**: Cassandra requiers python 2.7 
- CPU: 4 Cores/vCPUs (Modern Xeon Processor) 
- RAM: 12GB 
- Storage: The preferred storage is attached local SSD’s in non-RAID configuration.  
  When **SAN must be used,** it must be flash and in RAID-0. 
  **NAS are not certified** 

**The FS volumes required are as follows:** 

- Volume of 150G /opt/apps/fabric/ - will be used also as the home directory for fabric user 
- Volume of 200G /opt/apps/cassandra/ 
- Volume of 50G /opt/apps/kafka/  

 **For Cloud installation:** 

   - If using **AWS**: EC2: m5.2xlarge
   - If using **Azure**: Standard_DS14_v2
   - If using **GCP**: e2-standard-8

###  Linux Server Setup

Follow these steps to setup a Linux Server:

1. **NTP/chrony** installed and configured
2. Add a user ‘**fabric'** with group '**fabric'**
3. Add Admin permissions to the fabric user.
4. Provide read/write access to **/opt/apps/fabric**
5. Remote access option (SSH) enabled to user **fabric**.
6. Add a user ‘**cassandra'** with group ‘**fabric’**
7. Provide read/write access to **/opt/apps/cassandra**
8. Remote access option (SSH) enabled to user **cassandra**.
9. Add a user ‘**kafka** with group ‘**fabric’**
10. Provide read/write access to **/opt/apps/kafka**
11. Remote access option (SSH) enabled to user **kafka**.

**Run the following as ROOT:**

~~~bash
echo "root  soft   nproc   unlimited"  >> /etc/security/limits.conf
echo "cassandra - nofile 100000" >>  /etc/security/limits.conf
echo "cassandra - nproc 50000" >> /etc/security/limits.conf
echo "fabric - nofile  100000" >> /etc/security/limits.conf
echo "fabric - nproc  50000" >> /etc/security/limits.conf
echo "kafka hard  nofile 100000" >> /etc/security/limits.conf
echo "kafka soft  nofile 100000" >> /etc/security/limits.conf
echo "kafka - nproc  50000" >> /etc/security/limits.conf
echo "kafka - nproc  50000" >> /etc/security/limits.conf
## update /etc/sysctl.conf  ##
echo "## Added by  K2view - GabiO" >> /etc/sysctl.conf
echo  "vm.max_map_count = 1048575" >> /etc/sysctl.conf
echo "fs.file-max =  1000000" >> /etc/sysctl.conf

~~~

### Linux Ports 

Open the following ports on the LINUX server, and make sure they are accessible from outside the server: 

<table style="border-collapse: collapse; width: 100%;">
<tbody>
<tr>
<td style="width: 50%; height: 18px;">22</td>
<td style="width: 50%; height: 18px;">SSH, SCP</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">7000</td>
<td style="width: 50%; height: 18px;">cassandra</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">7001</td>
<td style="width: 50%; height: 18px;">cassandra</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">7199</td>
<td style="width: 50%; height: 18px;">cassandra</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">9042</td>
<td style="width: 50%; height: 18px;">cassandra</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">9160</td>
<td style="width: 50%; height: 18px;">cassandra</td>
</tr>
<tr>
<td style="width: 50%; height: 11px;">3213, 9443</td>
<td style="width: 50%; height: 11px;">K2View Fabric</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">6379</td>
<td style="width: 50%; height: 18px;">Redis</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">2181, 2888, 3888</td>
<td style="width: 50%; height: 18px;">Kafka Zookeeper</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">9093</td>
<td style="width: 50%; height: 18px;">Kafka</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">9081</td>
<td style="width: 50%; height: 18px;">Kafka JMX</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">5124</td>
<td style="width: 50%; height: 18px;">K2View Fabric</td>
</tr>
</tbody>
</table>





[![Previous](/articles/images/Previous.png)](01_hardware_requirements_introduction.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_hardware_req_for_prod.md)  

