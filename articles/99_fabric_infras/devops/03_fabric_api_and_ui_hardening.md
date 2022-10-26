# Fabric Hardening

## Fabric API/WS Hardening 

### Step 1 - Keys Generation

Run the Web server self-signed script on one of the Fabric nodes. The script’s purpose is to create a key in the key store. 

* Usage: ```certifcates.sh genkey <ALIAS> [CNAME] [PASSWORD]```

### Step 2 - Copy the key to all Fabric nodes

In case of multiple Fabric nodes, if you want that all the nodes will have the same certificate - copy the `${user.home}/.keystore` on each node into the same location.

In case that Fabric is running in Docker container, the user may be different. Then need to change the file owner by running the following command:

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


### Step 4 - Check access to Fabric Web UI via HTTPS

- Restart each one of the Fabric nodes.
- Use the following access points to check that the **https** access has been properly granted: 
  - Admin Panel: ``` https://10.10.10.10:8443/ ```
  - Fabric Web service will be available at: ``` https://10.10.10.10:8443/deploy ```

## Fabric JDBC Driver Hardening

In order to securely access Fabric Data via its JDBC driver, the TLS option can be switched on by adding the following lines to the **[jdbc-server]** section of the **config.ini** file as shown below:

```
## Turn on TLS for the Fabric driver protocol
SECURE=true
```
To disable hardening, simply set the *SECURE* flag to false.



[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/devops/02_fabric_environments.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/devops/04_cassandra_hardening.md)
