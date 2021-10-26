# Fabric API/WS Hardening 

## Step 1 - Keys Generation

1. Run the following script on one of the Fabric nodes to generate the following key: ```k2vws.key```

~~~bash
mkdir $K2_HOME/.ssl

keytool -genkey -noprompt \
        -keyalg RSA \
        -alias selfsigned \
        -alias alias1 \
        -dname "CN=k2view.com, OU=SU, O=k2view, L=k2v, S=k2v, C=TLV" \
        -keystore $K2_HOME/.ssl/k2vws.key \
        -storepass Q1w2e3r4t5 \
        -validity 760 \
        -keysize 4096 \
        -keypass Q1w2e3r4t5
~~~

## Step 2 - Copy the *k2vws.key* key to all Fabric nodes

1. Copy the `$K2_HOME/.ssl/k2vws.key` on each Fabric node into the same location ```$K2_HOME/.ssl```

``` bash
tar -czvf k2vmws.tar.gz -C $K2_HOME/.ssl .
scp k2vmws.tar.gz fabric@10.10.10.10:/opt/apps/fabric/
mkdir -p $K2_HOME/.ssl && tar -zxvf k2vmws.tar.gz -C $K2_HOME/.ssl
```

## Step 3 - Configure Fabric to use TLS for API or WebUI

1. Run the snippet below on each fabric node, and restart Fabric.


```bash
sed -i "s@WEB_SERVICE_CERT=@#WEB_SERVICE_CERT=@" $K2_HOME/config/config.ini
sed -i "s@WEB_SERVICE_PORT=@#WEB_SERVICE_PORT=@" $K2_HOME/config/config.ini
sed -i "s@#WEB_SERVICE_SECURE_PORT=443@WEB_SERVICE_SECURE_PORT=9443@" $K2_HOME/config/config.ini
sed -i "s@WEB_SERVICE_CERT=@#WEB_SERVICE_CERT=@" $K2_HOME/config/config.ini
sed -i "s@WEB_SERVICE_CERT_PASSPHRASE=@#WEB_SERVICE_CERT_PASSPHRASE=@" $K2_HOME/config/config.ini
sed -i "s@WEB_SERVICE_CERT=.*@WEB_SERVICE_CERT=$K2_HOME/.ssl/k2vws.key@" $K2_HOME/config/config.ini
sed -i '93s/.*/WEB_SERVICE_CERT_PASSPHRASE=Q1w2e3r4t5/g' $K2_HOME/config/config.ini
echo "ENABLE_INTER_NODES_SSL=true" >> $K2_HOME/config/config.ini
chown fabric.fabric $K2_HOME/.ssl/k2vws.key
```


## Step 4 - Check access to Fabric Web UI via HTTPS

- Restart each one of the Fabric nodes.
- Use the following Access points to check that the https access has been properly granted: 
  - Admin Panel: ``` https://10.10.10.10:9443/ ```
  - Fabric web service will be available at: ``` https://10.10.10.10:9443/deploy ```

[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/devops/02_fabric_environments.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/devops/04_cassandra_hardening.md)

