# Fabric scale
During Fabric cluster lifecycle it might experience higher load and based on metrics like CPU usage, available memory or storage you shall consider to scale the cluster. 

Fabric is built by design to enable scale out, horizontally, by adding more fabric nodes. Each node that is starting up knows autonomy to add itself to the cluster; for example:

* Register itself to the node list, for start getting load and jobs.
* Obtain the project deployment code.
* Obtain the master key for data encryption.

This article describes how to scale Fabric cluster on-prem, within bare-metal or virtual machine environments. Read [here]() about scaling methodology for Kubernetes deployment.

> Note: Scaling guidelines for Fabric's accompanying components, like Cassandra, are not included in this article scope and shall be applied according to their methodologies.



## Fabric Setup 

### Prerequisite

* Deploy the Fabric server on a VM with a network connectivity to the other servers within the cluster.
* Fabric server Installation package is the same as the one used for other existing nodes.

### Install the Package 

The following steps are similar to the standard Fabric server [installation instructions](/articles/98_maintenance_and_operational/Installations/Linux/02_Fabric_7.x.x_Setup.md).

1. Log in with the previously created user for the Fabric installation.
2. Download the package from the provided links.
3. Untar the package in the user's home folder (/opt/apps/fabric):

    ~~~bash
    tar -zxf [package name].tar.gz -C /opt/apps/fabric && source .bash_profile
    ~~~

## Configuration
Configure the new fabric node the same way you have configurated the other nodes.

### Basic configurations
Run the following command to set up the base configuration. Replace the parameters with your own environment, where IPs must be the same as used for other existing nodes. 

~~~bash
/opt/apps/fabric/fabric/scripts/fabric-setup.sh --cassandra_user k2admin --cassandra_password changeit --cassandra_ips 10.0.0.1,10.0.0.2,10.0.0.3  --kafka_ips 10.0.0.4,10.0.0.5,10.0.0.6 
~~~

> If the Cassanda & Kafka are Hardened with SSL, add  `--ssl` to the command.

### Additional configuration

When setting up a node, you shall either configure it from scratch or duplicate the configuration files from another node. When copying the configuration files from an existing node, please take note of the following considerations:
* config.ini and iifConfig.ini: Ensure accurate passwords are inserted. Post the initial Fabric execution, passwords are encrypted, making them indecipherable to other nodes.
* jvm.options and jvm.iid_finder.options: Validate that both the keystore and truststore are present, and their respective paths and passwords are accurate.
* node.id: If the node.id is configured, verify that the UUID is distinct or comment it out to avoid conflicts.


### Certificates
Ensure all necessary certificates are imported into the key and trust store as needed, according to your deployment, including:
* Cassandra SSL certificate.
* Kafka SSL certificate.
* SAML certificate.
* Source/target data Interface certificates.

## Project Files

As mentioned above, Fabric nodes obtain the project deployment from the system DB when they starting up. Nevertheless, if you are using additional files in your project, for example JAR libraries, copy them to the node's Fabric Home folder (e.g. $K2_HOME/ExternalJars).

## Start Fabric

To start Fabric - run:
~~~bash
/opt/apps/fabric/fabric/bin/k2fabric start
~~~

After a short while, the following message will be displayed: 
~~~
++++ Fabric is READY
~~~



## Scale In

When Fabric cluster experiences reduction in load, you might consider to scaled it in, by remove or stop working Fabric cluster nodes.

You can just stop the relevant node, even while it processes jobs, as Fabric knows to reconcile, where other nodes will process that jobs.



For more information about an advanced setup, read below:

<ul>
   <li><a href="/articles/98_maintenance_and_operational/Installations/Linux/02_Fabric_7.x.x_Setup.md">Fabric Installation</a></li>
   <li><a href="/articles/02_fabric_architecture/05_fabric_main_configuration_files.md">Fabric main configuration files</a></li>
   <li><a href="/articles/26_fabric_security/13_user_IAM_configiration.md">SAML configiration</a></li>
   <li><a href="/articles/98_maintenance_and_operational/Hardware/2_All_Environments/03_hardware_req_for_prod.md">Hardware requirements</a></li>
</ul>
