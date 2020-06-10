# Fabric Basics-Getting Started

## Start and Stop Fabric Commands

Run the following commands through the command line of Fabric server:

<table>
<tbody>
<tr>
<td width="300pxl">
<p><h4><strong>Command Name</strong></p>
</td>
<td width="600pxl">
<p><h4><strong>Command Description</strong></p>
</td>
</tr>
<tr>
<td width="300">
<p><h4><strong>k2fabric start</strong></p>
</td>
<td width="600">
<p>Start the Fabric node. When starting Fabric node, you will receive notifications if you have some local files that are in conflict with the installed release (private files). Note that you need to start the seed nodes before starting other Fabric nodes of the Fabric cluster.</p>
</td>
</tr>
<tr>
<td width="300">
<p><h4><strong>k2fabric stop</strong></p>
</td>
<td width="600">
<p>Stop the Fabric node</p>
</td>
</tr>
<tr>
<td width="300">
<p><h4><strong>k2fabric restart</strong></p>
</td>
<td width="600">
<p>Restart (stop and start) the fabric node</p>
</td>
</tr>
</tbody>
</table>

## Get Fabric Version

Run **k2fabric -version** command through the command line of Fabric server to get the installed fabric on your server. Note that you can check the Fabric version of your server inside Fabric using [**version info** Fabric command](/articles/02_fabric_architecture/04_fabric_commands.md#fabric-view).  

## Login Fabric 

#### Enter Fabric from Fabric Server

To enter Fabric server, type **fabric** through the command line of Fabric server

#### Enter Fabric Local Server

Open **Fabric Console** on your windows search. Opening a project in Fabric Studio logins the local Fabric server. Note that if you do not have any open project in Fabric Studio, you will not be able to open the local Fabric server. 

## Reset Fabric

Fabric provides the following script in order to clean Fabric and delete (drop) all data from Fabric and Cassandra. This script is located under $K2_HOME/fabric/scripts:

- **reset.sh**

Note that $K2_HOME/fabric/scripts also has a Windows version of the reset script: **reset.bat**

The **reset.sh** script is used mainly:

- In a **TEST environment** to delete the current data and to restart the testing process from scratch.
- In **Production environment**. Note that the [Drop LUTYPE](/articles/02_fabric_architecture/04_Fabric_Commands.md) command and the **reset.sh** script are very rarely used in the Production environment. A possible scenario for using these processes is to clean an environment after a soft launch prior to starting an actual Production run. 

Unlike the drop LU ([drop LUTYPE](/articles/02_fabric_architecture/04_Fabric_Commands.md)) command which drops a specific LU, the reset.sh script performs a full Fabric initialization, including deleting users, tokens, metadata, data and also deletes the data from Cassandra.

The Drop process must be followed by re-creation of Fabric credentials and redeploying of the project implementation into Fabric server and an initial load of LU instances into the re-deployed LUs. 

### Reset.sh Usage

Run the script from $K2_HOME/fabric/scripts directory:
   
<ul><li>./reset.sh &lt;mode&gt;&lt;black_list&gt;&lt;path of config.ini file&gt;</li></ul>

### Reset.sh Parameters

<table>
<tbody>
<tr>
<td width="200pxl">
<p><h4><strong>Parameter Name</strong></p>
</td>
<td width="120pxl">
<p><h4><strong>Mandatory</strong></p>
</td>
<td width="580pxl">
<p><h4><strong>Description</strong></p>
</td>
</tr>
<tr>
<td width="200pxl">
    <p><strong>mode</strong></p>
</td>
<td width="120pxl">
<p>Yes</p>
</td>
<td width="580pxl">
    <p><strong>Reset mode</strong>. The following modes are supported:</p>
<ul>
    <li><strong>drop</strong>- removing Fabric storage on the local Fabric node, Kafka topics, and all Cassandra Fabric-related keyspaces except the keyspaces, set in the black-list parameter if exist.</li>
    <li><strong>drop_all</strong>- removing Fabric storage on the local Fabric node, Kafka topics, and all Cassandra Fabric-related keyspaces except the keyspaces, set in the black-list parameter if exist, and except the system keyspaces.</li>
    <li><strong>drop_local</strong>- removing only the Fabric storage on the local Fabric node. For example: remove /dev/shm/ directory on the local node.</li>
</ul>
</td>
</tr>
<tr>
<td width="200pxl">
    <p><strong>black-list</strong></p>
</td>
<td width="120pxl">
<p>No</p>
</td>
<td width="580pxl">
<p>List of Cassandra keyspaces or Kafka topic names that must be excluded from the drop.</p>
<p>The names are quoted by double quotes and separated by space.</p>
</td>
</tr>
<tr>
<td width="200pxl">
    <p><strong>path</strong></p>
</td>
<td width="120pxl">
<p>No</p>
</td>
<td width="580pxl">
<p>The path of the config.ini file</p>
</td>
</tr>
</tbody>
</table>
<p>&nbsp;</p>

### Run Reset.sh on Fabric Cluster

When a clean-up of a Fabric cluster is required, it is recommended to execute the **reset.sh** script in the following order: 

- Run on one node - ./reset.sh drop_all; -  cleaning the Fabric storage of the local Fabric node and removing the Cassandra keyspaces  and Kafka topics. The removal of the Cassandra keyspaces and the Kafka topics impacts the entire Fabric cluster.

- Run on all other nodes - ./reset.sh drop_local;- cleaning the Fabric storage on the local Fabric node.  To reset the Fabric cluster properly, you must execute the reset.sh script on on all fabric nodes, and only then [start](/articles/02_fabric_architecture/03_fabric_basics_getting_started.md#k2fabric-start) all Fabric nodes one by one.

  

### Reset Fabric- Remove Fabric Directories

The **reset.sh** script gets the list of Fabric directories that need to be removed from the **config.ini** configuration file. The following parameters are checked to get the list of removed Fabric directories: 

<table width="900pxl">
<tbody>
<tr>
<td width="250pxl">
<p><strong>Parameter Name</strong></p>
</td>
<td width="350pxl">
<p><strong>Parameter Description</strong></p>
</td>
<td width="300pxl">
<p><strong>Default Value</strong></p>
</td>
</tr>
<tr>
<td width="250pxl">
<p>MDB_COMMONS_PATH</p>
</td>
<td width="350pxl">
<p>The storage directory of Common (reference) tables:</p>
</td>
<td width="300pxl">
<p>${FABRIC_HOME}/storage/common</p>
</td>
</tr>
<tr>
<td width="250pxl">
<p>STORAGE_DIR</p>
</td>
<td width="350pxl">
<p>Fabric storage directory</p>
</td>
<td width="300pxl">
<p>${FABRIC_HOME}/storage</p>
</td>
</tr>
<tr>
<td width="250pxl">
<p>MDB_DEFAULT_CACHE_PATH</p>
</td>
<td width="350pxl">
<p>This directory holds the cached database files for the LU instances</p>
</td>
<td width="300pxl">
<p>/dev/shm/fdb_cache</p>
</td>
</tr>
<tr>
<td width="250pxl;">
<p>WEBSERVER_DIR</p>
</td>
<td width="350pxl;">
<p>The home directory of Fabric web admin. This directory can also contain manipulations (rewrite) on the URL when invoking Fabric web-services.</p>
</td>
<td width="300pxl">
<p>${FABRIC_HOME}/webserver</p>
</td>
</tr>
</tbody>
</table>

[![Previous](/articles/images/Previous.png)](/articles/02_fabric_architecture/02_fabric_directories.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/02_fabric_architecture/04_fabric_commands.md)



