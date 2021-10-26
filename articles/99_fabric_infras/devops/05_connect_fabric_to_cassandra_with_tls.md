# Connect Fabric to Cassandra in TLS mode

The following steps for connecting Fabric to Cassandra in TLS mode

## Step 1 - Transfer The Cassandra Keys and Certificates to All Fabric Nodes

Tar and copy them to all  Fabric nodes in the cluster.  

1. prepare tar file with all the certificates on one of the cassandra nodes as in the example bellow

   ~~~bash
   tar -czvf ckeys.tar.gz -C $INSLATT_DIR/.cassandra_ssl .
   ~~~

2. copy to the fabric nodes

   ~~~bash
   scp ckeys.tar.gz fabric@10.10.10.10:/opt/apps/fabric/
   ~~~

## Step 2 - Set Fabric to connect Cassandra

1. extract the  `keys.tar.gz` file

   ~~~bash
   mkdir -p $K2_HOME/.cassandra_ssl && tar -zxvf ckeys.tar.gz -C $K2_HOME/.cassandra_ssl
   ~~~

2. Edit the $K2_HOME/config/jvm.options file using the appropriate passwords and certification files:

```bash
sed -i "s@#SSL=false@SSL=true@" $K2_HOME/config/config.ini
sed -i "s@#PORT=$.*@PORT=9142@" $K2_HOME/config/config.ini
sed -i 's@#-Djavax.net.ssl.keyStore=.*@-Djavax.net.ssl.keyStore=$K2_HOME/.cassandra_ssl/cassandra.keystore@g' $K2_HOME/config/jvm.options
sed -i 's@#-Djavax.net.ssl.keyStorePassword=.*@-Djavax.net.ssl.keyStorePassword=Q1w2e3r4t5@g' $K2_HOME/config/jvm.options
sed -i 's@#-Djavax.net.ssl.trustStore=.*@-Djavax.net.ssl.trustStore=$K2_HOME/.cassandra_ssl/cassandra.truststore@g' $K2_HOME/config/jvm.options
sed -i 's@#-Djavax.net.ssl.trustStorePassword=.*@-Djavax.net.ssl.trustStorePassword=Q1w2e3r4t5@g' $K2_HOME/config/jvm.options
```

- Start the Fabric service on each node: ```K2fabric start```

<u>**Note:** make sure not to use the default `cassandra` user for connection between Fabric and Cassandra</u>



[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/devops/04_cassandra_hardening.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/devops/06_kafka_hardening.md)

