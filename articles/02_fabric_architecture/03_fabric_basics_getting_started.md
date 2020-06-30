# Fabric Basics - Getting Started

## Start and Stop Fabric Commands

Run the following commands through the Fabric server command line:

<table>
<tbody>
<tr>
<td width="300pxl" valign="top">
<p><h4><strong>Command Name</strong></p>
</td>
<td width="600pxl" valign="top">
<p><h4><strong>Command Description</strong></p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<p><h4><strong>k2fabric start</strong></p>
</td>
<td width="600pxl" valign="top">
<p>Start the Fabric node. Fabric displays notifications when local files are in conflict with the installed release (private files). 
Always start the seed nodes before other nodes in the Fabric cluster.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<p><h4><strong>k2fabric stop</strong></p>
</td>
<td width="600pxl" valign="top">
<p>Stop the Fabric node.</p> 
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<p><h4><strong>k2fabric restart</strong></p>
</td>
<td width="600pxl" valign="top">
<p>Restart (stop and start) the Fabric node.</p> 
</td>
</tr>
</tbody>
</table>


## Get Fabric Version

Run the **k2fabric -version** script through the Fabric server command line to get the version of Fabric installed on your server. Note that you can check the Fabric version of your server in Fabric using the [**VERSION INFO** Fabric command](/articles/02_fabric_architecture/04_fabric_commands.md#fabric-view).  

## Login Fabric 

### Enter Fabric From the Fabric Server

Type **fabric** in the Fabric server command line. 

### Enter the Local Fabric Server

Open the **Fabric Console** via the **Windows Search** option. When opening a project, the Fabric Studio logs into the local Fabric server. 

Note that if there are no open projects in the Fabric Studio, you will not be able to open the local Fabric server.

## Reset Fabric

The Fabric **reset.sh** script cleans Fabric and deletes (drops) all data from Fabric and Cassandra. The **reset.sh** script is located under [$K2_HOME/fabric/scripts](/articles/02_fabric_architecture/02_fabric_directories.md#k2_homefabricscripts) and is used mainly:

- In a **Test environment** to delete the current data and to restart the testing process from scratch.

- In a **Production environment**. Note that the [DROP LUTYPE](/articles/02_fabric_architecture/04_fabric_commands.md#drop-lu-command) command and the **reset.sh** script are rarely used in a Production environment. A possible scenario for using these processes is to clean an environment after a soft launch prior to starting an actual Production run.

Unlike the **drop LU** ([DROP LUTYPE](/articles/02_fabric_architecture/04_fabric_commands.md#drop-lu-command)) command which drops a specific LU, the **reset.sh script** performs a full Fabric initialization, including deleting users, tokens, metadata, data and also deletes the data from Cassandra.

The Drop process must be followed by the re-creation of Fabric credentials and redeployment of the project implementation into the Fabric server and an initial load of LUI into the re-deployed LUs.

Note that the Windows version of the **$K2_HOME/fabric/scripts** Reset script is **reset.bat**.

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
    <p><strong>Mode</strong></p>
</td>
<td width="120pxl">
<p>Yes</p>
</td>
<td width="580pxl">
    <p>Reset mode. The following modes are supported:</p>
<ul>
    <li><strong>drop</strong>, removes Fabric storage from the local Fabric node, Kafka topics, and all <a href="/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md">Cassandra Fabric-related keyspaces</a> except for keyspaces set in the black-list parameter if set.</li>
    <li><strong>drop_all</strong>, removes Fabric storage on the local Fabric node, Kafka topics, and all <a href="/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md">Cassandra Fabric-related keyspaces</a> except the keyspaces set in the black-list parameter and system keyspaces.</li>
    <li><strong>drop_local</strong>, removes Fabric storage on the local Fabric node only. For example, remove /dev/shm/ directory on the local node.</li>
</ul>
</td>
</tr>
<tr>
<td width="200pxl">
    <p><strong>Black-list</strong></p>
</td>
<td width="120pxl">
<p>No</p>
</td>
<td width="580pxl">
<p>List of <a href="/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md">Cassandra Fabric-related keyspaces</a> or Kafka topic names that must be excluded from the drop.</p>
<p>The names have double quotes and are separated by a space.</p>
</td>
</tr>
<tr>
<td width="200pxl">
    <p><strong>Path</strong></p>
</td>
<td width="120pxl">
<p>No</p>
</td>
<td width="580pxl">
<p><a href="/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#configini">Config.ini file</a> path.</p>
</td>
</tr>
</tbody>
</table>
<p>&nbsp;</p>

### Run Reset.sh On a Fabric Cluster

When a Fabric cluster clean-up is required, it is recommended to execute the **reset.sh** script in the following order:

- Run on one node, ./reset.sh drop_all; - cleans Fabric storage on the local Fabric node and removes Cassandra keyspaces and Kafka topics. Removing Cassandra keyspaces and Kafka topics impacts  the entire Fabric cluster.

- Run on all other nodes, ./reset.sh drop_local; - cleans Fabric storage on the local Fabric node. To reset the Fabric cluster correctly, execute the **reset.sh** script on all fabric nodes, and only then [start](/articles/02_fabric_architecture/03_fabric_basics_getting_started.md#k2fabric-start) each Fabric node individually.

### Reset Fabric - Remove Fabric Directories

The **reset.sh** script gets the list of the Fabric directories to be removed from the **config.ini** configuration file. The following parameters are checked to get the list of removed Fabric directories:

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
<p>Common (reference) tables storage directory.</p>
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
<p>Fabric storage directory.</p> 
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
<p>Directory holding cached database files of LU Instances.</p>
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
<p>Home directory of the Fabric Web Admin. Can also contain manipulations (rewrites) on the URL when invoking Fabric Web Services.</p>
</td>
<td width="300pxl">
<p>${FABRIC_HOME}/webserver</p>
</td>
</tr>
</tbody>
</table>

[![Previous](/articles/images/Previous.png)](/articles/02_fabric_architecture/02_fabric_directories.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/02_fabric_architecture/04_fabric_commands.md)



