# On-prem K8s Cluster Installation

This article describes the guidelines and instructions for creating a K2cloud site - a K8s (Kubernetes) cluster that is ready for operation.

While K2cloud K8s cluster deployment on the cloud (fully managed or self-hosted) is done using Terraform, based on each cloud provider’s K8s infrastructure, the on-prem K8s cluster deployment is done by running a script that is responsible for preparing all required infrastructure components. This can be considered as Kubernetes in a box.

## Hardware Requirements 

## Preparations and Provisioning


## Installing Fabric in a Cluster



## Installing Fabric in a Single Node Cluster, "Kubernetes In a Box"

The installation script will automatically configure and install everything required to have K8s running and ready.

You should perform the following commands:

Clone the Git repository 'k2view/blueprints' in [GitHub](https://github.com/k2view/blueprints/tree/main/baremetal)

```bash
git clone https://github.com/k2view/blueprints.git
```

Navigate to the directory 'blueprints/baremetal' 

```bash
cd blueprints/baremetal
```

Run the script 'single_node.sh' and follow all the in-screen instructions. 

```bash
./single_node.sh
```

This script installs the following:

* [MicroK8s](https://microk8s.io/) 
* [cert-manager](https://cert-manager.io)
* [NGINX Ingress](https://docs.nginx.com)
* hostpath-storage
* [docker registry](https://microk8s.io/docs/registry-built-in)
* metrics-server

During the installation, the installer script will request you to provide the values prepared in the Prerequisites phase:

* Mailbox ID
* Cloud Manager URL
* DNS Record


Once the setup process is complete (it may take a few minutes) - and before you can create a new space - a few steps need to be taken:

* Load the downloaded docker images

```bash
docker load -i /path/to/file.tar.gz
```

* Tag the image to fit the local repository

```bash
docker tag <IMAGE_HASH> localhost:32000/image-name:tag
```

* Import the downloaded images to the Kubernetes local repository

```bash
docker push localhost:32000/image-name:tag
```

* Deploy it in the nginx namespace and restart the nginx controller

```bash
deploy_certificate.sh /path/to/fullchain.cer /path/to/private.key
```

## Starting and Stopping the Cluster and Services 

Use the following commands to stop and restart the cluster:

**Stopping the Cluster**

```bash
microk8s stop
```

**Starting the Cluster**

```bash
microk8s start
```

**Restarting the Cluster**

```bash
microk8s restart
```

**Uninstalling the Cluster**

Delete the spaces and other resources from the Cloud Manager, and then use the following commands to remove the cluster from your machine.

```bash
 microk8s uninstall
```
