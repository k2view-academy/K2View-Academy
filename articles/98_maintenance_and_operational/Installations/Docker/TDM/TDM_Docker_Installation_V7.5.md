## Docker Install Windows\\MacBook\\Linux

These instructions requires the latest Docker & Docker Compose for Windows. For a MacBook, use Docker Desktop. 
We use the `c:/k2view/apps/`  as the path of the  TDM installation base. Use whatever location is appropriate for your environment. 

### Download Container Images 

Create a K2V_TDM directory and download the following into it: 

<ul>
    <li><a href="https://k2view.sharepoint.com/:w:/r/sites/KS/Releases/K2V%20Product%20Documents/TDM/v7.x/V7.5.0/TDM_7.5_download_links.docx?d=wc8bb0cd017484fa2b1d79939dcfaa807&csf=1&web=1&e=9SgjMb">Download links</a></li>
</ul>



### Load the Images 

1. Open a CLI and navigate to the K2V_TDM directory you created. 
2. Uncompress the files as shown (that is, load the images). 
3. Note that if the precise name of the specific image is different than shown, use the name that you downloaded. 

~~~bash
docker load -i D_K2V_PG13_TDM7.5.tar.gz
docker load -i D_k2view_cassandra_3.11.9.tar.gz
docker load -i D_Fabric_6.5.7_25_TDMGUI7.5_3.tar.gz
unzip compose_TDM_7.5_3.zip
~~~

You can see the images names by using the  **docker images**. command.

Note that the PostgreSQL image already contains the TDMDB.


### Run the Compose File 

1. Navigate to the compose folder as shown: 
~~~bash
    cd compose_TDM_7.5_3
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

