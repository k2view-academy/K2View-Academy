# Fabric API, UI, and JDBC Hardening

## Fabric API/WS Hardening 

### Step 1 - Keys Generation

Run the Web Server self-signed script on one of the Fabric nodes. The script’s purpose is to create a key in the key store. 

* Usage: ```certificates.sh genkey <ALIAS> [CNAME] [PASSWORD]```

### Step 2 - Copy the Key to All Fabric Nodes

In the case of multiple Fabric nodes, if you want all nodes to have the same certificate, copy the `${user.home}/.keystore` file that was created in the first step to each node in the same path and location.

In case Fabric is running in a Docker container, the user may be different. In such a case, you need to change the file owner by running the following command:

~~~bash
chown root.root ~/.keystore
~~~

### Step 3 - Configure Fabric 

Uncomment the following in the config.ini file:

~~~
#WEB_SERVICE_SECURE_PORT=8443
~~~

Note that the password of the certification file should be defined here:

~~~
#WEB_SERVICE_CERT_PASSPHRASE=
~~~


### Step 4 - Check the Access to Fabric Web UI via HTTPS

- Restart each one of the Fabric nodes.
- Use the following access points to check whether the **https** access has been granted appropriately: 
  - Admin Panel: ``` https://10.10.10.10:8443/ ```
  - Fabric Web Service will be available in: ``` https://10.10.10.10:8443/deploy ```

## Fabric JDBC Driver Hardening

In order to securely access Fabric Data via its JDBC driver, the TLS option is set to true by default in the **[jdbc-server]** section of the **config.ini** file:

```
## Turn on TLS for the Fabric driver protocol
#SECURE=true
```
To disable hardening, set the SECURE flag to false.


[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/02_fabric_environments.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/04_cassandra_hardening.md)
