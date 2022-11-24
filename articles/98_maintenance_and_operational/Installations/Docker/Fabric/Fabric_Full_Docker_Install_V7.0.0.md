## FABRIC V7.0.0_226 DOCKER INSTALLATION

### Prerequisites

These prerequisites are for a development, training, demo, or a small Proof-of-Concept installation. You can use one of the following environments:

- **Docker CE/EE** over **CentOs/RedHat 7.9, Ubuntu 18.04** or higher.
  or
- **Docker-Desktop** for Windows or Mac.

More detailed requirements for each are described below:

#### Docker over CentOs/RedHat

1. CentOS/RedHat 7.9, Ubuntu 18.04 (or higher) Operating System with the latest patches, for each:

   - Modern Xeon Processor
   - 8 Physical Cores
   - 16 GB RAM
   - HDD, 200GB must be available

   **Note**: Minimum 10G RAM is required for running 1x Fabric, 1x Cassandra. Our recommendation if for a physical Docker host and not a virtual machine.
2. Install the latest Docker CE/EE version (https://docs.docker.com/engine/install/centos/).

   **Note**: The /var/lib/docker should have at list 200GB of free memory.
3. Docker-compose must also be installed (see Docker documentation online https://docs.docker.com/compose/install/).
4. Enable NTP/chronyc (see https://access.redhat.com/documentation/enus/red_hat_enterprise_linux/7/html/system_administrators_guide/sect-using_chrony).
5. Create the **k2view** user, and set the home directory on a drive with at least 50GB of space as follows:

   ```bash
   ## update the k2view user home directory and password!! 
   useradd -m -d /<update the path>/k2view/ k2view passwd k2view 
   ```
6. Give the **k2view** user privileges to run docker & docker-compose via **SUDO**.

#### Minimum hardware requirements for Docker-Desktop

1. Windows or Mac machine with an 8 core CPU.
2. Windows or Mac machine with a minimum of 16G RAM.
3. Windows or Mac machine with minimum 120G of hard disk space.

### How Do I Setup and Run Docker Environment?

#### Load Container Images

1. Download the latest versions of Fabric, Cassandra, Kafka and docker-compose using on the below links:

   <table style="border-collapse: collapse; width: 100%;">
   <tbody>
   <tr>
   <td style="width: 50%; height: 18px;"><strong>cassandra </strong></td>
   <td style="width: 50%; height: 18px;">https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/fabricint/cassandra/3.11.12/D_k2view_cassandra_3.11.12.tar.gz 
   <br>md5: 5674945c399c58bc32b5aa49c381131fcd<br></td>
   </tr>
   <tr>
   <td style="width: 50%; height: 18px;"><strong>kafka </strong></td>
   <td style="width: 50%; height: 18px;">https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/fabricint/kafka/5.5.1/D_k2view_kafka_5.5.1_3.tar.gz
   <br>md5: 5d038a563723838d891ea6d09935e9dd</br></td>
   </tr>
   <tr>
   <td style="width: 50%; height: 18px;"><strong>fabric </strong></td>
   <td style="width: 50%; height: 18px;">https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/fabricint/fabric_7.0/Server/fabric-7.0.0_226/D_k2view_fabric_7.0.0_226.tar.gz
   <br>md5: c4b16f563d2ab913f4d9b3c499b2cbaf</br></td>
   </tr>
   <tr>
   <td style="width: 50%; height: 18px;"><strong>docker-compose </strong></td>
   <td style="width: 50%; height: 18px;">https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/fabricint/fabric_7.0/Server/fabric-7.0.0_226/compose_fabric_7.0.0_226.tar.gz
   <br>md5: 8091e752e16ca59ebb7fffa8edf1a9ef</br></td>
   </tr>
   </tbody>
   </table>

3. Copy all files to the k2view directory as follows:

   ```bash
   cd ~/ 
   wget https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/fabricint/cassandra/3.11.12/D_k2view_cassandra_3.11.12.tar.gz
   wget https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/fabricint/kafka/5.5.1/D_k2view_kafka_5.5.1_3.tar.gz 
   wget https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/fabricint/fabric_7.0/Server/fabric-7.0.0_226/D_k2view_fabric_7.0.0_226.tar.gz
   wget https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/fabricint/fabric_7.0/Server/fabric-7.0.0_226/compose_fabric_7.0.0_226.tar.gz
   ```
   
4. Load the Docker images as follows:

   ```bash
    docker load -i D_k2view_cassandra_3.11.12.tar.gz 
    docker load -i D_k2view_kafka_5.5.1_3.tar.gz
    docker load -i D_k2view_fabric_7.0.0_226.tar.gz 
   ```

   You can see the names of the images by using the command `docker images`
   
5. Extract the Config archives as follows:

   ```bash
   tar -zxvf compose_fabric_7.0.0_226.tar.gz 
   ```


#### Run Single Fabric, Cassandra and Kafka Instances

Run docker-compose as follows:

```bash
cd compose_fabric_7.0.0_226-HF2
# run:  
docker-compose up -d 

# or  
sudo /usr/local/bin/docker-compose up -d  
```

Cassandra and Fabric will start automatically.

### How Do I Stop Services in Docker Environment?

1. To stop the docker-compose, do the following:

   ```bash
   cd compose_fabric_7.0.0_226
   #run: 
   docker-compose stop
   ```
2. To stop the Cassandra service, do the following:

   ```bah
   docker exec -u=cassandra -it cassandra bash su - cassandra 
   
   ## stop cassandra  
   stop-server
   
   ## restart cassandra  
   stop-server && cassandra
   ```
3. To stop or restart Fabric, do the following:

   ```bash
   docker exec -it fabric bash -l  
   k2fabric stop / restart  
   ```
