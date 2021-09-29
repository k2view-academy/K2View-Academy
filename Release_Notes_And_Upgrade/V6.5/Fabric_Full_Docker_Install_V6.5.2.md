## FABRIC v6.5.2_73 DOCKER INSTALLATION 

### PREREQUISITES 

The following is for Dev/training/Demo/small POV 

the minimum requirement is: 

- **Docker CE/EE** over **CentOs/RedHat 7.7** or higher 
- Or **docker-desktop** for Windows or Mac 

### Minimum requirement for Docker over CentOs/RedHat

1. CentOS/RedHat 7.3 (or higher) Operating System with latest patches, for each: 

   - Modern Xeon Processor. 
   - 8 Physical Cores. 
   - RAM 16. 
   - HDD, free 200G for the Docker 

   **Note**:
   - Minimum 10G RAM for running 1x fabric, 1x Cassandra 
   - it is RECOMMENDED that Docker host will be physical server and not a  VM  


2.	Docker CE/EE & docker-compose (see Docker documentation online) must be installed, and assigned privileges to k2view user  

3.	NTP/chronyc should be enabled (see https://access.redhat.com/documentation/enus/red_hat_enterprise_linux/7/html/system_administrators_guide/sect-using_chrony ) 

4.	Installed the latest Docker CE/EE version (https://docs.docker.com/install/linux/dockerce/centos/ ) 

      **NOTE**: the /var/lib/docker should have at list 200G of free  


5.	Add k2view user, and assigned the k2view user 50G of space 
      ~~~bash
      ## update the k2view user home directory and password!! 
      useradd -m -d /<update the path>/k2view/ k2view passwd k2view 
      ~~~
6. Give the k2view user privileges to run docker & docker-compose via **SUDO** 

   <img src="../images/sudo_config.jpg" style="zoom:%;" />



###  Minimum requirement for docker-desktop 

1. Windows or Mac machine with 8 cores of CPU 
2. Windows or Mac machine with minimum 16G of RAM 
3. Windows or Mac machine with minimum 120G of free space  



### USING THE CONTAINER

#### Load the container image 

1. Grab the latest version of Fabric, Cassandra. Kafka & Configuration files at: 

   <table style="border-collapse: collapse; width: 100%;">
   <tbody>
   <tr>
   <td style="width: 50%; height: 18px;"><strong>cassandra </strong></td>
   <td style="width: 50%; height: 18px;">https://owncloud-
   bkp2.s3.amazonaws.com/adminoc/fabricint/cassandra/3.11.6/openjdk/D_k2v_cassandra_3.11.6_2.tar.gz 
   </td>
   </tr>
   <tr>
   <td style="width: 50%; height: 18px;"><strong>kafka </strong></td>
   <td style="width: 50%; height: 18px;">https://owncloud-bkp2.s3.amazonaws.com/adminoc/fabricint/kafka/5.5.1/D_k2view_kafka_5.5.1_2.tar.gz</td>
   </tr>
   <tr>
   <td style="width: 50%; height: 18px;"><strong>fabric </strong></td>
   <td style="width: 50%; height: 18px;">https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/fabricint/fabric_6.5/6.5.2/Server/fabric-6.5.2_73/D_k2view_fabric_6.5.2_73.tar.gz</td>
   </tr>
   <tr>
   <td style="width: 50%; height: 18px;"><strong>docker-compose </strong></td>
   <td style="width: 50%; height: 18px;">https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/fabricint/fabric_6.5/6.5.2/Server/fabric-6.5.2_73/compose_fabric_6.5.2_73.zip</td>
   </tr>
   </tbody>
   </table>
   
2. Copy all to the k2view directory 

   ~~~bash
   cd ~/ 
   wget https://owncloud-bkp2.s3.amazonaws.com/adminoc/fabricint/cassandra/3.11.6/openjdk/D_k2v_cassandra_3.11.6_2.tar.gz  
   wget https://owncloud-bkp2.s3.amazonaws.com/adminoc/fabricint/kafka/5.5.1/D_k2view_kafka_5.5.1_2.tar.gz  
   wget https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/fabricint/fabric_6.5/6.5.2/Server/fabric-6.5.2_73/D_k2view_fabric_6.5.2_73.tar.gz
   wget https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/fabricint/fabric_6.5/6.5.2/Server/fabric-6.5.2_73/compose_fabric_6.5.2_73.zip
   ~~~

   

3. Load Docker images:
   ~~~bash
    docker load -i D_k2v_cassandra_3.11.6_2.tar.gz 
    docker load -i D_k2view_kafka_5.5.1_2.tar.gz 
    docker load -i D_k2view_fabric_6.5.2_73.tar.gz 
   ~~~
   
   You can see the images names by using the command `docker image`  
   
4. UNZIP Config archives: 

   ```bash
   unzip compose_fabric_6.5.2_73.zip 
   ```
   Note: open the “.env” file and update the names of the images as needed 


### RUN SINGLE FABRIC, CASSANDRA INSTANCES:

1. Run docker-compose 

   ~~~bash
   cd compose_fabric_6.5.2_73
   # run:  
   docker-compose up -d 
   
   # or  
   sudo /usr/local/bin/docker-compose up -d  
   ~~~

2.	Start Cassandra
   ~~~bash
   docker exec -u=cassandra -it cassandra1 bash -l
   # run:
   cassandra
   ~~~

3.	Start Kafka:  
   ~~~bash
   docker exec -it -u=kafka kafka1 bash -l -c '$K2_HOME/kafka/bin/zookeeper-server-start -daemon 
   $K2_HOME/kafka/zookeeper.properties' 
   sleep 3 
   docker exec -it -u=kafka kafka1 bash -l -c '$K2_HOME/kafka/bin/   kafka-server-start -daemon 
   $K2_HOME/kafka/server.properties' 
   sleep 3 
   docker exec -it -u=kafka kafka1 bash -l -c '~/kafka/bin/zookeeper-shell    localhost:2181 <<< "ls /brokers/ids"'

   ~~~

4.	Start Fabric:  
   ~~~bash
   #run: docker exec -it fabric1 bash -l 
   cp -r fabric/config.template config && \ 
   sed -i 's@#HOSTS=.*@HOSTS=172.29.0.116@'  $K2_HOME/config/config.ini &&    \ 
   sed -i 's@#USER=cassandra@USER=cassandra@'  $K2_HOME/config/config.ini   && \ 
   sed -i 's@#BOOTSTRAP_SERVERS=localhost:9093@BOOTSTRAP_SERVERS=172.29.0. 115:9093@'  $K2_HOME/config/config.ini && \ sed -i  's@#BOOTSTRAP_SERVERS=localhost:9093@BOOTSTRAP_SERVERS=172.29.0.   115:9093@'  $K2_HOME/config/iifConfig.ini && \ 
   k2fabric start && k2fabric cluster-status && exit
   ~~~


## APPENDIX A

1.	stop docker-compose 

      ~~~bash
      cd compose_fabric_6.5.2_73 
      #run: 
      docker-compose stop
      ~~~
 
2.	Stop Cassandra service 
 
      ~~~bah
      docker exec -u=root -it cassandra bash su - cassandra 

      ## stop cassandra  
      nodetool -u k2view -pw Q1w2e3r4t5 flush && kill -9 $(ps aux | grep -i $INSLATT_DIR | grep -v "pts/7" |awk {'print $2'})  
      
      ## restart cassandra  
      nodetool -u k2view -pw Q1w2e3r4t5 flush && kill -9 $(ps aux | grep -i $INSLATT_DIR | grep -v "pts/7" |awk {'print $2'}) && $INSLATT_DIR/cassandra/bin/cassandra 

      ~~~
 
 
3.	Stop / Restart Fabric:  
 
      ~~~bash
      docker exec -it fabric1 bash -l  
      k2fabric stop / restart  
      ~~~
 
 
