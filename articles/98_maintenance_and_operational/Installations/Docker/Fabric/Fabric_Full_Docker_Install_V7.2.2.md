## FABRIC V7.2.2_239-HF14 DOCKER INSTALLATION

### Prerequisites

These prerequisites are for the purpose of development, training, a demo or a small Proof-of-Concept installation. You can use 1 of the following 2 environments:

- **Docker CE/EE** over **CentOs/RedHat 7.9, Ubuntu 18.04** (or higher) for a Linux environment
- **Docker Desktop** for Windows/Mac environments

More detailed requirements for each are described below:

#### Docker over CentOs/RedHat

1. CentOS/RedHat 7.9, Ubuntu 18.04 (or higher) Operating System with the latest patches, for each:

   - Modern Xeon Processor
   - 8 Physical Cores
   - 16 GB RAM
   - HDD, 200GB must be available

   **Note**: Minimum 10G RAM is required for running 1x Fabric, 1x Cassandra. Our recommendation if for a physical Docker host and not a virtual machine.
2. Install the latest Docker CE/EE version (https://docs.docker.com/engine/install/centos/ ).

   **Note**: The /var/lib/docker should have at least 200GB of free memory.
3. Docker Compose must also be installed (see Docker documentation online https://docs.docker.com/compose/install/ ).
4. Enable NTP/chronyc (see more details  [here](https://didyoubounceit.wordpress.com/2018/10/19/red-hat-centos-ntp-is-now-chrony/).)
5. Create the **k2view** user, and set the home directory on a drive with at least 50GB of space as follows:

   ```bash
   ## update the k2view user home directory and password!! 
   useradd -m -d /<update the path>/k2view/ k2view passwd k2view 
   ```
6. Grant the **k2view** user privileges to run Docker & Docker Compose via **SUDO**.

#### Minimum hardware requirements for Docker Desktop

1. Windows or Mac machine with an 8 core CPU.
2. Windows or Mac machine with a minimum of 16G RAM.
3. Windows or Mac machine with minimum 120G of hard disk space.

### How Do I Set Up and Run Docker Environment?

#### Load Container Images

1. Download the latest versions of Fabric, Cassandra and Docker Compose using 1 the below 4 links:
     > Kafka is no longer required for a single node setup, if needed, please download the relevant container and uncommnet the Kafka section in the docker-compose.yml file

  <table style="border-collapse: collapse; width: 100%;">

   <tbody>

   <tr>
   <td style="width: 50%; height: 18px;"><strong>kafka </strong></td>
   <td style="width: 50%; height: 18px;">https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/fabricint/kafka/7.2/D_k2view_Confluent_7.2_package_06.tar.gz
   <br>md5: 7f6d3cbe0761ba7dfa861a9d4fa4ef69</br></td>
   </tr>
   <tr>
   <td style="width: 50%; height: 18px;"><strong>cassandra </strong></td>
   <td style="width: 50%; height: 18px;">https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/fabricint/cassandra/4.1.3/D_k2view_cassandra_4.1.3.tar.gz 
   <br>md5: 5674945c399c58bc32b5aa49c381131fcd<br></td>
   </tr>
   <tr>
   <td style="width: 50%; height: 18px;"><strong>fabric </strong></td>
   <td style="width: 50%; height: 18px;">https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/fabricint/fabric_7.2/7.2.2/Server/fabric-7.2.2_239-HF14/D_k2view_fabric_7.2.2_239.tar.gz
   <br>md5: e3f0ac5a8c458f03c1975853f71a098e</br></td>
   </tr>
   <tr>
   <td style="width: 50%; height: 18px;"><strong>docker-compose </strong></td>
   <td style="width: 50%; height: 18px;">https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/fabricint/fabric_7.2/7.2.2/Server/fabric-7.2.2_239-HF14/compose_fabric_7.2.2_239.tar.gz
   <br>md5: f64808429ce1804fbb51cee38a1c54dc</br></td>
   </tr>
   </tbody>
   </table>
2. Copy all files to the k2view directory as follows: (skip Kafka if not needed)

```bash
   cd ~/ 
   wget https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/fabricint/kafka/7.2/D_k2view_Confluent_7.2_package_06.tar.gz
   wget https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/fabricint/cassandra/4.1.3/D_k2view_cassandra_4.1.3.tar.gz
   wget https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/fabricint/fabric_7.2/7.2.2/Server/fabric-7.2.2_239-HF14/D_k2view_fabric_7.2.2_239.tar.gz
   wget https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/fabricint/fabric_7.2/7.2.2/Server/fabric-7.2.2_239-HF14/compose_fabric_7.2.2_239.tar.gz

3. Load the Docker images as follows: (skip Kafka if not needed)

   ```bash
    docker load -i D_k2view_kafka_5.5.1_3.tar.gz
    docker load -i D_k2view_cassandra_4.1.3.tar.gz 
    docker load -i D_k2view_fabric_7.2.2_239.tar.gz 
   ```

   You can see the names of the images by using the command `docker images`
4. Extract the Config archives as follows:

   ```bash
   tar -zxvf compose_fabric_7.2.2_239.tar.gz 
   ```

#### Run Single Fabric, Cassandra and Kafka Instances

Run Docker Compose as follows:

```bash
cd compose_fabric_7.2.2_239
# run:  
docker-compose up -d 

# or  
sudo /usr/local/bin/docker-compose up -d  
```

Cassandra and Fabric will start automatically.

### How Do I Stop Services in Docker Environment?

1. To stop the Docker Compose, do the following:

   ```bash
   cd compose_fabric_7.2.2_239
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
