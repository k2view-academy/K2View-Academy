
## FABRIC V7.0.X OVA INSTALLATION

### Prerequisites

* Virtual Environment/hypervisor that supports the import of an OFA/OVF file.
* The image was configured with the following specifications, verify the hypervisor contain enough resources:
> * Compute: 8 vCPU.
> * Memory: 32 GB RAM.
> * Storage: 500 GB disk.
* The VM is based on Ubuntu Server Version22.04.2 LTS (Jammy Jellyfish).
* The OS is preconfigured with management users.

    
| Role           | Username    | Password      | Notes 
| ----------     | ---------- | ---------- | ---------- | 
|  Management      | k2view    | Q1w2e3r4t5   | with sudo permissions and will be used to login the rest of the users |
|  fabric         | fabric     |--   | to manage the fabric instance |
|  cassandra      | cassandra  | --| to manage the cassandra instance |
|  kafka          | kafka      | --| not needed in a single node environment |

    

### Fabric Server initial configuration.

#### 1. Load the server image
* To load the server image to the hypervisor, contact your Local IT/Infrustructure team for further information.
* The system is configured to get the IP from the network DHCP server. The IP can be set manually, if needed (See More information at the bottom. 
* Connect to the VM (by console or SSH) and login with the user 'k2view'
* 

### 2. Set up Cassandra

a. log in to cassandra user
```
sudo su - cassandra
```

b. for hardened enviroment prepare a self signed certificate:
```bash
./secure_cassandra.sh changeit k2tls
```

c. configure the cassandra instance with the pre assigned IP (for example - 192.168.1.1): 
```
./cassandra-setup.sh -i 192.168.1.1 --ssl_default
``` 
      

	
	
d. wait for the script to finish and verify the cassandra us up and running:
```
nodetool -u cassandra -pw cassandra status
```
### 3. Setup Fabric
a. log in to fabric user
``` 
sudo su - fabric
```
b. Run the Fabric Configuration script/
```
/opt/apps/fabric/fabric/scripts/fabric-setup.sh -c 192.168.1.1 --cassandra_ssl -m 8
```
c. Start Fabric server
```
k2fabric start
```








#### More Information:
<ul>      
<li>
<a href="/articles/98_maintenance_and_operational/Installations/OVA/Set_Static_IP.md">Set Static IP</a></li>
