
## FABRIC V7.0.X OVA INSTALLATION

### Prerequisites

* Virtual Environment/hypervisor that supports the import of an OFA/OVF file.
* The image was configured with the following specifications; please verify that the hypervisor contains enough resources:
> * Compute: 8 vCPU
> * Memory: 32 GB RAM
> * Storage: 500 GB disk
* The VM is based on Ubuntu Server Version22.04.2 LTS (Jammy Jellyfish).
* The OS is preconfigured with management users.

    
| Role           | Username    | Password      | Notes 
| ----------     | ---------- | ---------- | ---------- | 
|  Management      | k2view    | Q1w2e3r4t5   | with sudo permissions and will be used to log in the rest of the users |
|  fabric         | fabric     |--   | to manage the fabric instance |
|  cassandra      | cassandra  | --| to manage the cassandra instance |
|  kafka          | kafka      | --| not needed in a single node environment |

    

### Fabric Server initial configuration

### 1. Load the server image
* To load the server image to the hypervisor, contact your Local IT/Infrustructure team for further information.
* The system is configured to get the IP address from the network DHCP server. The IP address can be set manually, if needed. (more information is found at the bottom of this doc.) 
* Connect to the VM (by console or SSH) and log in with the user 'k2view'.


### 2. Set up Cassandra

a. Log in to cassandra user
```
sudo su - cassandra
```

b. For a hardened environment, prepare a self-signed certificate:
```bash
./secure_cassandra.sh Q1w2e3r4t5 k2tls
```

c. Configure the cassandra instance with the pre-assigned IP address (for example - 192.168.1.1): 
```
./cassandra-setup.sh -i 192.168.1.1 --ssl_default --ssl
``` 
      

	
	
d. Wait for the script to finish and verify that the cassandra is up and running:
```
nodetool -u cassandra -pw cassandra status
```
e. Log out from the Cassandra User.
```
exit
```
### 3. Set up Fabric
a. Log in to the fabric user
``` 
sudo su - fabric
```
b. Run the Fabric Configuration script/
```
/opt/apps/fabric/fabric/scripts/fabric-setup.sh -c 192.168.1.1 -cp 9142 --ssl -m 8
```
c. Start Fabric server
```
k2fabric start
```








#### Additional Information:
<ul>      
<li>
<a href="/articles/98_maintenance_and_operational/Installations/OVA/Set_Static_IP.md">Set Static IP</a></li>
