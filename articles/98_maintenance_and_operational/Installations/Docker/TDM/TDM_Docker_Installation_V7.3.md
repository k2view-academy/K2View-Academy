## Docker Install Windows\\MacBook\\Linux

These instructions requires the latest Docker & Docker Compose for Windows. For a MacBook, use Docker Desktop. 
We use the `c:/k2view/apps/`  as the path of the  TDM installation base. Use whatever location is appropriate for your environment. 

### Download Container Images 

Create a K2V_TDM directory and download the following into it: 

<ul>
    <li><a href="https://k2view.sharepoint.com/:w:/r/sites/KS/Releases/K2V%20Product%20Documents/TDM/v7.x/V7.3/TDM_7.3.0_download_links.docx?d=wfed14278f7f240b3a97a077879a3dfe7&csf=1&web=1&e=Fry5OY">Download links</a></li>
</ul>


### Load the Images 

1. Open a CLI and navigate to the K2V_TDM directory you created. 
2. Uncompress the files as shown (that is, load the images). 
3. Note that if the precise name of the specific image is different than shown, use the name that you downloaded. 

~~~bash
docker load -i D_k2view_pg_9.6.9.tar.gz
docker load -i D_k2v_cassandra_3.11.6_2.tar.gz
docker load -i D_k2view_kafka_5.5.1_2.tar.gz
docker load -i D_Fabric_6.5.2_73_TDMGUI7.3_6.tar.gz
unzip compose_TDM_7.3_6.zip
~~~

You can see the images names by using the command **docker images**. The result will be similar to the following: 

   <img src="images/docker_images_example.png" alt="Customer BE" style="zoom:45%;" />


### Run the Compose File 

1. Navigate to the compose folder as shown: 
~~~bash
    cd compose_TDM_7.3_6
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

