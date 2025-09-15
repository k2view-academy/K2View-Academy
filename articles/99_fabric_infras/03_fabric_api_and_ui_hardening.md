# Fabric Hardening

## Table of Contents

- [Fabric API/WS Hardening](#fabric-apiws-hardening)
  - [Step 1 - Import or Generate TLS Certificate](#step-1---import-or-generate-tls-certificate)
    - [Import a TLS Certificate](#import-a-tls-certificate)
    - [Generate a Self-signed TLS Certificate](#generate-a-self-signed-tls-certificate)
  - [Step 2 - Copy the Key to All Fabric Nodes](#step-2---copy-the-key-to-all-fabric-nodes)
  - [Step 3 - Configure Fabric](#step-3---configure-fabric)
  - [Step 4 - Check the Access to Fabric Web UI via HTTPS](#step-4---check-the-access-to-fabric-web-ui-via-https)
- [Fabric JDBC Driver Hardening](#fabric-jdbc-driver-hardening)


## Fabric API/WS Hardening

### Step 1 - Import or Generate TLS Certificate

#### Import a TLS Certificate

**Option 1: Import your existing TLS certificate into the keystore (recommended).**

If you already have a TLS certificate, import it to the keystore:
~~~bash
${FABRIC_HOME}/fabric/scripts/certificates.sh addkey webserver ~/.keystore changeit
~~~

#### Generate a Self-signed TLS Certificate

**Option 2: Generate a self-signed certificate**

The <a href="/articles/99_fabric_infras/13_creating_a_self_signed_certificate.md">Generating a Self-Signed Certificate in Fabric</a> article explains how to create and use a self-signed certificate in Fabric using the `certificates.sh` helper script, and alternatively, how to use the `keytool` utility directly. It also covers advanced options for IP-based access and browser trust configuration.

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


[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/02_fabric_environments.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/13_creating_a_self_signed_certificate.md)
