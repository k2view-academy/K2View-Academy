# Fabric Hardening

# Table of Contents

- [Fabric API/WS Hardening](#fabric-apiws-hardening)
  - [Step 1 - Import or Generate TLS Certificate](#step-1---import-or-generate-tls-certificate)
  - [Step 2 - Copy the Key to All Fabric Nodes](#step-2---copy-the-key-to-all-fabric-nodes)
  - [Step 3 - Configure Fabric](#step-3---configure-fabric)
  - [Step 4 - Check the Access to Fabric Web UI via HTTPS](#step-4---check-the-access-to-fabric-web-ui-via-https)
- [Fabric JDBC Driver Hardening](#fabric-jdbc-driver-hardening)


## Fabric API/WS Hardening

### Step 1 - Import or Generate TLS Certificate

**Option 1: Import your existing TLS certificate into the keystore (recommended).**
If you already have a TLS certificate, import it to the keystore:
~~~bash
${FABRIC_HOME}/fabric/scripts/certificates.sh addkey webserver ~/.keystore changeit
~~~

**Option 2: Generate a self-signed certificate**
To create one, run the following command on one of your Fabric nodes:
~~~bash
${FABRIC_HOME}/fabric/scripts/certificates.sh genkey webserver ~/.keystore changeit
~~~

>Note: When using IP-based access, a Subject Alternative Name (SAN) may be required. Use the following command instead:
>~~~bash
>keytool -genkey -keyalg RSA -keysize 4096 -sigalg SHA256WithRSA -alias webserver -keystore ~/.keystore -noprompt -storepass changeit -dname "CN=YOUR_IP, OU=K2View, O=K2View, L=City, ST=State, C=US" -ext "SAN=ip:YOUR_IP" -storetype PKCS12 -validity 760
>~~~

>Note: When using self-signed certificates, it may be necessary to export the certificate and add it to your browser’s truststore. Use the following command to export the certificate from the keystore and save it as a PEM file, which can then be imported into a browser truststore:
>~~~bash
>keytool -export -alias webserver -keystore ~/.keystore -storepass changeit -rfc -file webserver.pem
>~~~


### Step 2 - Copy the Key to All Fabric Nodes

If you have multiple Fabric nodes and want all nodes to use the same certificate, copy the ${user.home}/.keystore created in the first step to the same path on each node.

If Fabric is running in a Docker container, the user may differ. In that case, change the file owner by running the following command:

~~~bash
chown fabric fabric ~/.keystore
~~~

### Step 3 - Configure Fabric 

Uncomment the following lines in the fabric section of the config.ini file:

~~~
#WEB_SERVICE_SECURE_PORT=8443
#WEB_SERVICE_KEY_ALIAS=webserver
~~~


### Step 4 - Check the Access to Fabric Web UI via HTTPS

- Restart each one of the Fabric nodes.
- Use the following access points to check whether the **https** access has been properly granted: 
  - Admin Panel: ``` https://10.0.0.0:8443/app/admin ```

## Fabric JDBC Driver Hardening

To securely access Fabric data via its JDBC driver, the TLS option is enabled (true) by default in the [jdbc-server] section of the config.ini file:

```
## Turn on TLS for the Fabric driver protocol
#SECURE=true
```
To disable hardening, set the SECURE flag to false.


[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/devops/02_fabric_environments.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/devops/04_cassandra_hardening.md)
