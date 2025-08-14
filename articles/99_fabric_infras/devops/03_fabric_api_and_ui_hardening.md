# Fabric Hardening

## Fabric API/WS Hardening 

### Step 1 - Import or Generate TLS Certificate

**Option 1: Import existing certificate (recommended)**
If you already have a TLS certificate, import it to the keystore:
~~~bash
${FABRIC_HOME}/fabric/scripts/certificates.sh addkey webserver ~/.keystore changeit
~~~

**Option 2: Generate self-signed certificate**
If you need to create a self-signed certificate, run this on one of your Fabric nodes:
~~~bash
${FABRIC_HOME}/fabric/scripts/certificates.sh genkey webserver ~/.keystore changeit
~~~

>NOTE: For IP-based access, you may need to specify the SAN. Use this command instead:
>~~~bash
>keytool -genkey -keyalg RSA -keysize 4096 -sigalg SHA256WithRSA -alias webserver -keystore ~/.keystore -noprompt -storepass changeit -dname "CN=YOUR_IP, OU=K2View, O=K2View, L=City, ST=State, C=US" -ext "SAN=ip:YOUR_IP" -storetype PKCS12 -validity 760
>~~~

>NOTE: For self-signed certificates, you may need to export and import the certificate to your browser's truststore:
>~~~bash
>keytool -export -alias webserver -keystore ~/.keystore -storepass changeit -rfc -file webserver.pem
>~~~


### Step 2 - Copy the Key to All Fabric Nodes

In case of multiple Fabric nodes, if you want all nodes to have the same certificate, copy the `${user.home}/.keystore` that was created in the first step, to each of the nodes in the same path/location.

In case Fabric is running in a Docker container, the user may be different. In such case you need to change the file owner by running the following command:

~~~bash
chown fabric fabric ~/.keystore
~~~

### Step 3 - Configure Fabric 

Uncomment the following in fabric section of the config.ini file:

~~~
#WEB_SERVICE_SECURE_PORT=8443
#WEB_SERVICE_KEY_ALIAS=webserver
~~~


### Step 4 - Check the Access to Fabric Web UI via HTTPS

- Restart each one of the Fabric nodes.
- Use the following access points to check whether the **https** access has been properly granted: 
  - Admin Panel: ``` https://10.0.0.0:8443/app/admin ```

## Fabric JDBC Driver Hardening

In order to securely access Fabric Data via its JDBC driver, the TLS option is set to true by default in the **[jdbc-server]** section of the **config.ini** file:

```
## Turn on TLS for the Fabric driver protocol
#SECURE=true
```
To disable hardening, set the SECURE flag to false.



[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/devops/02_fabric_environments.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/devops/04_cassandra_hardening.md)
