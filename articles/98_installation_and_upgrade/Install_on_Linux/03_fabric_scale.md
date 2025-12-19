# Fabric Cluster Scaling

A Fabric cluster may experience higher load during its lifecycle. Based on metrics such as CPU usage, available memory, or storage, you should consider scaling out the cluster. 

By design, Fabric is built to enable horizontal scaling out by adding more Fabric nodes. Each starting-up node knows how to add itself to the cluster autonomously; for example:

* Register itself to join the cluster's node list and start receiving the cluster's workload, such as job handling.
* Obtain the project deployment code.
* Obtain the master key for data encryption.

This article describes how to scale a Fabric cluster on-prem, within bare-metal or virtual machine environments. Read [here](/articles/98_installation_and_upgrade/Install_on_Kubernetes/04_fabric_scale_kubernetes.md) about the scaling methodology for Kubernetes deployment.

> Note: Scaling guidelines for Fabric's accompanying components, such as Cassandra, are not within the scope of this article. These components' scaling guidelines shall be applied according to their methodologies.


## Fabric Setup 

### Prerequisite

* Deploy the Fabric server on a virtual machine (VM) with network connectivity to the other servers within the cluster.
* The Fabric server Installation package is the same as the one used for other existing nodes.

### Install the Package 

The basic steps you will find in this topic are:

1. Log in with the previously created user for the Fabric installation.
2. Download the package from the provided links.
3. Untar the package in the user's home folder (/opt/apps/fabric)

Please follow the [installation instructions](/articles/98_installation_and_upgrade/Install_on_Linux/02_Fabric_8.x.x_Setup.md).


## Configuration
Configure the new Fabric node the same way you have configured the other nodes. Please refer to the Fabric server [installation instructions](/articles/98_installation_and_upgrade/Install_on_Linux/02_Fabric_8.x.x_Setup.md).

### Additional configuration

When setting up a node, you shall either configure it from scratch or duplicate the configuration files from another node. When copying the configuration files from an existing node, please consider the following:

* config.ini and iifConfig.ini: Ensure accurate passwords are inserted. Post the initial Fabric execution, passwords are encrypted, making them indecipherable to other nodes.
* jvm.options and jvm.iid_finder.options: Verify that both the keystore and truststore are present, and that their respective paths and passwords are accurate.
* node.id: If the node.id is configured, verify that the UUID is distinct or comment it out to prevent conflicts.


### Certificates
Ensure all necessary certificates are imported into the keystore and truststore as needed, according to your deployment, including:

* Cassandra SSL certificate
* Kafka SSL certificate
* SAML certificate
* Source/target data Interface certificates

## Project Files

As mentioned above, Fabric nodes obtain the project deployment from the system DB when they start up. Nevertheless, if you are using additional files in your project, such as JAR libraries, copy them to the node's Fabric home folder (e.g., $K2_HOME/ExternalJars).

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

When the Fabric cluster experiences a reduction in load, consider scaling it in by removing or stopping the working Fabric cluster nodes.

You can stop the relevant node, even though it is in the midst of processing jobs, because Fabric knows how to reconcile the tasks with other nodes that will process them. Since Fabric operates on a stateless architecture, all interactions, like web services, will function seamlessly.



For more information about an advanced setup, read below:

<ul>
   <li><a href="/articles/98_installation_and_upgrade/Install_on_Linux/02_Fabric_8.x.x_Setup.md">Fabric Installation</a></li>
   <li><a href="/articles/02_fabric_architecture/05_fabric_main_configuration_files.md">Fabric main configuration files</a></li>
   <li><a href="/articles/26_fabric_security_iam/13_user_IAM_configuration.md">SAML configuration</a></li>
   <li><a href="/articles/98_installation_and_upgrade/Hardware_Linux_Docker/README.md">Hardware requirements</a></li>
</ul>
