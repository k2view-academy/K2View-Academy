<!--rollocat:KeepLineBreaks-->
<!--rol	locat:KeepLineBreaks-->
## FABRIC V7.0.X OVA INSTALLATION

### Prerequisites

* Virtual Enviroment / hypervisor  supporting import OFA/OVF file.
* the image was configured with the following specifications, verify the hypervisor contain enough resources:
> * Compute: 8 vCPU.
> * Memory: 32 GB RAM.
> * Storage: 500 GB disk.
* The VM is base on Ubuntu Server Version22.04.2 LTS (Jammy Jellyfish).
* The OS is preconfigured with managment users.

    
| Role           | Username    | Password      | Notes 
| ----------     | ---------- | ---------- | ---------- | 
|  Manament      | k2view    | Q1w2e3r4t5   | with sudo permissions and will be used to login the rest of the users |
|  fabric         | fabric     |--   | to manage the fabric instance |
|  cassandra      | cassandra  | --| to managed the cassandra instance |
|  kafka          | kafka      | --| not needed in single node enviroment |

    

### Fabric Server initial configuration.

#### 1. Load the server image.
* To load the server image to the hypervisor, contact your Locsl IT / Infrustructure team for more information.
* the system is configured to get the IP from the network DHCP server, IP can be set manually if needed (See More information at the bottom. 
* Connect to the VM ( by console or SSH ) and login with the user 'k2view'
* 

### 2. Setup Cassandra

a. log in to cassandra user

	``` bash
	sudo su - cassandra
	```

b. for hardened enviroment prepare a self signed certificate:

	```bash
	./secure_cassandra.sh Q1w2e3r4t5 k2tls
	```

c. configure the cassandra instance with the pre assigned IP (for example - 192.168.1.1): 

	```bash
	./cassandra-setup.sh -i 192.168.1.1 --ssl_default
	``` 
      

	
	
d. wait for the script to finish and verify the cassandra us up and running:

	```bash
	nodetool -u cassandra -pw cassandra status
	```
### 3. Setup Fabric

a. log in to fabric user

	``` bash
	sudo su - fabric
	```
b. Run the Fabric Configuration script/

	```bash
	/opt/apps/fabric/fabric/scripts/fabric-setup.sh -c 10.21.0.153 --cassandra_ssl -m 8

c. Start Fabric server
	
	```bash
	k2fabric start
	```








More Information:
<ul>      
<li>
<a href="/articles/98_maintenance_and_operational/Installations/OVA/Set_Static_IP.md">Set Static IP</a></li>
