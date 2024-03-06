# Prerequisites and Installation Instructions for K2cloud On-prem K8s Cluster  

This article describes the guidelines and instructions for creating a K2cloud's site - a K8s (kubernetes) cluster, ready for operation.

While K2cloud K8s cluster deployment at cloud (fully-managed or self-hosted) is done using Teraform, based on each cloud provider K8s infrastructure, the on-prem K8s clsuter deployment is done by runing a script that is responsible for the preperations of all reuired infrasturcture componnents. This cab be considered as a kuberentes in-a-box.



## Hardware Requirements 

**Supported OS**: The K2cloud K8s cluster can be installed on both Debian based (Debian, Ubuntu) and RHEL based (Redhat, Centos, Fedora, Amazon Linux 2) Linux distributions.



A K8s worker node is expected to meet the following requirements, and accordingly shall be prepared:

<table>
<tbody>
<tr>
<td valign="top">
<p><strong>CPU</strong></p>
</td>
<td>
<p>4 cores (minimum) or 8 cores (recommended)</p>
<p>64-bit CPU architecture</p>
</td>
</tr>
<tr>
<td>
<p><strong>RAM</strong></p>
</td>
<td>
<p>16 GB RAM (minimum) or 64 GB RAM (recommended)</p>
</td>
</tr>
<tr>
<td valign="top">
<p><strong>Storage</strong></p>
</td>
<td>
<p>Serving Studio namespaces: 150 GB SSD disk (minimum)</p>
<p>Serving Fabric cluster namespaces: Storage shall be calculated according to the project's estimated needs.</p>
</td>
</tr>
</tbody>
</table>



## Preparations and Provisioning

* Prepare a record on public domain, pointing the the server that will host the K8s node (can point either private or public IP). 

* Have an asterisks certificate (with private key) for the selected domain.

  > For a POT Environment both domain and certificates can be provided by K2view.

* Have a user with sudo privilege to run the script.

* Have a Docker engine (latest version), or an OCI compatible tool. This will be used to push the images to the k8s local repository.

* Verify that host has an outbound access to GitHub.com and K2view Cloud manager at port 443 (https).

* Provide to K2view:

  * The prepared domain name.
  * The Fabric Git project details that is going to be used.

* Get from K2view:

  * K2view Kubernetes Docker images, depended on the required projects.

  * Mailbox ID and Cloud manager URL. 



## Installation 

The installation script will automatically configure and install everything that is required to have K8s running and ready to be access K2view Cloud Manager.

You should perform the following commands:

Clone Git repository 'k2view/blueprints' in [GitHub](https://github.com/k2view/blueprints/tree/main/baremetal)

```bash
git clone https://github.com/k2view/blueprints.git
```

Navigate to directory 'blueprints/baremetal' 

```bash
cd blueprints/baremetal
```

Run script 'single_node.sh'  and follow all the in-screen instructions 

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

During the installation, the installer script will request to provide the values prepared at the prerequisites phase:

* Mailbox ID
* Cloud manager URL
* DNS Record


Once the setup finishes (it can take several minutes), and before you can create new space, few steps need to be taken:

* Load the downloaded docker images

```bash
docker load -i /path/to/file.tar.gz
```

* Tag the image to fit the local repository

```bash
docker tag <IMAGE_HASH> localhost:32000/image-name:tag
```

* Import the downloaded images to the Kubernetes local repository.

```bash
docker push localhost:32000/image-name:tag
```

* Deploy it in the nginx namespace and restart nginx controller

```bash
deploy_certificate.sh /path/to/fullchain.cer /path/to/private.key
```

## Starting and Stopping the Cluster and Services 

Use the following commands to stop and restart the cluster:

**Stoping The Cluster**

```bash
microk8s stop
```

**Starting The Cluster**

```bash
microk8s start
```

**Restarting The Cluster**

```bash
microk8s restart
```

**Uninstalling The Cluster**

Delete the spaces and other resources from the Cloud Manager and then use the following commands to remove the cluster from your machine.

```bash
 microk8s uninstall
```


