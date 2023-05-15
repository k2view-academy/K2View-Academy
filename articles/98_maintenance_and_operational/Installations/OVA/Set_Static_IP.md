## Fabric OVA Switch Network IP configuration

* The VM is pre-set to start with DHCP configuration 

* The configuration templates for each mode are located under the folder /etc/netplan.

  * For DHCP configureation, the file name is: 00-installer-config.yaml.dhcp

  * For static configuration, the file name is: 00-installer-config.yaml.static

To switch configuration to a static IP address, follow the below instructions:

1. Copy the template file.

	~~~bash
	cp /etc/netplan/00-installer-config.yaml.static /etc/netplan/00-installer-config.yaml
	~~~

2. Edit the copied file with the requested configuration:

	~~~bash
	nano /etc/netplan/00-installer-config.yaml
	~~~

	(update the IP address and subnet, Default Gateway and DNS IP's)

	~~~
	network:
  	  version: 2
	  renderer: networkd
	  ethernets:
	    enp0s3:
	     dhcp4: no
	     addresses: [192.168.1.222/24]
	     gateway4: 192.168.1.1
	     nameservers:
	       addresses: [8.8.8.8,8.8.4.4]
	~~~

3. Apply Configuration:
	
	~~~bash
	netplan apply
	~~~

4. Reconnect to the server with the assigned IP address and continue with the setup process.
