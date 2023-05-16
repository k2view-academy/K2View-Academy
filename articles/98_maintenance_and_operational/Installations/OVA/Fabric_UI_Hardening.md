## Fabric API/WS Hardening

To Enable access to Fabric over SSL follow these instruction

for extended guide see [here](https://support.k2view.com/Academy/articles/99_fabric_infras/devops/03_fabric_api_and_ui_hardening.html) 

1. Log in to the Fabric user and stop the service:
	```bash
	sudo su - fabric
	k2fabric stop
	```
2. Generate key

	Run the Web server self-signed script on one of the Fabric nodes. The script’s purpose is to create a key in the key store.
	ALIAS - a name for the generated key
	CNAME - common name - hostname or host IP
	PASSWORD - the certificate passphrase
	
	```
	certifcates.sh genkey <ALIAS> [CNAME] [PASSWORD]
	```
3. Configure Fabric

	Edit the config.ini file
	```
	nano /opt/apps/fabric/config/config.ini
	```
	Uncomment the following in the config.ini file:
	```
	#WEB_SERVICE_SECURE_PORT=8443
	```
	Note that the password of the certification file should be defined here:
	```
	#WEB_SERVICE_CERT_PASSPHRASE=
	```
4. Start and check access to Fabric Web UI via HTTPS

	* Start the Fabric Instance
	```
	k2fabric start	
	```
	* Use the following access points to check that the https access has been properly granted for example 192.168.1.1:
		https://192.168.1.1:8443
