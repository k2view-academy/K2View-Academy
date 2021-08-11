## Docker Install Windows\\MacBook\\Linux

These instructions requires the latest Docker & Docker Compose for Windows. For a MacBook, use Docker Desktop. 
We use the `c:/k2view/apps/`  as the path of the  TDM installation base. Use whatever location is appropriate for your environment. 

### Download Container Images 

Create a K2V_TDM directory and download the following into it: 

<ul>
    <li><a href="https://download.k2view.com/owncloud/index.php/s/k1woeRdi1eBQ0tF">Fabric Studio</a></li>
    <li><a href="https://download.k2view.com/owncloud/index.php/s/RXkIyZ3xF41fLZG">Fabric 6.5.0_84 + TDM 7.1.0_6</a></li>
    <li><a href="https://owncloud-bkp2.s3.amazonaws.com/adminoc/fabricint/cassandra/3.11.6/openjdk/D_k2v_cassandra_3.11.6_2.tar.gz">Cassandra</a></li>
    <li><a href="https://owncloud-bkp2.s3.amazonaws.com/adminoc/fabricint/kafka/5.5.1/D_k2view_kafka_5.5.1_2.tar.gz">Kafka</a></li>
    <li><a href="https://owncloud-bkp2.s3.amazonaws.com/adminoc/etl%20%28ADI%29/LDAP/D_k2view_LDAP_02.tar.gz">LDAP</a></li>
    <li><a href="https://owncloud-bkp2.s3.amazonaws.com/adminoc/TDM/PG%20image/D_k2view_pg_9.6.9.tar.gz">PG 9.6</a></li>
    <li><a href="https://download.k2view.com/owncloud/index.php/s/l1wBwFO7rW9cHY1">Compose_TDM_7.1.0_6</a></li>
</ul>

### Load the Images 

1. Open a CLI and navigate to the K2V_TDM directory you created. 
2. Uncompress the files as shown (that is, load the images). 
3. Note that if the precise name of the specific image is different than shown, use the name that you downloaded. 

~~~bash
docker load -i D_k2view_pg_9.6.9.tar.gz
docker load -i D_k2view_LDAP_02.tar.gz
docker load -i D_k2v_cassandra_3.11.6_2.tar.gz
docker load -i D_k2view_kafka_5.5.1_2.tar.gz
docker load -i D_Fabric_6.5.0_84-TDM7.1.0_6.tar.gz
unzip compose_TDM_7.1.0_6.zip
~~~

You can see the images names by using the command **docker images**. The result will be similar to the following: 

   <img src="images/docker_images_example.png" alt="Customer BE" style="zoom:67%;" />


### Run the Compose File 

1. Navigate to the compose folder as shown: 
~~~bash
    cd compose_TDM_7.1.0_6
~~~
2. Run the compose file using docker as shown: 
~~~bash
    docker-compose up -d
~~~
3. Wait approximately 3 minutes, then check that all the containers are up using this command
~~~bash
    docker ps
~~~
- You can now access TDM/Fabric from your browser using the containers host IP 
http://localhost:3213

