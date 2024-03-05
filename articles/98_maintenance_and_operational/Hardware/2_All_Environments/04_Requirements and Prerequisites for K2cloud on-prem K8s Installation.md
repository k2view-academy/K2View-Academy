# Prerequisites and Installation Instructions for K2cloud On-prem K8s Clsuter  

This article describes the guidelines and instructions for creating a K2cloud's site - a K8s (kuberentes) clsuter, ready for operation.

While K2cloud K8s clsuter at cloud (fully-managed or self-hosted) deployment is done using Teraform, based on each cloud provider K8s infrastructure, the on-prem clsuter, is done by runing a script that is responsible for preperations of all reuired infrasturcture componnents.

## Supported OS
The K2cloud K8s clsuter can be installed on both Debian Based (Debian, Ubuntu) and RHEL Based (Redhat, Centos, Fedora, Amazon Linux 2) Linux Distributions.

## Hardware requirements 
A Kubernetes worker node is expected to meet the following requirements:

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

## Prerequisites 
* A record on public domain, pointing the the server that will host the kubernetes node (can point either private or public IP).
* An asterisks certificate (with private key) for the selected domain.

  > For a POT Environment domain and certificates can be provided by K2view.

* A user with sudo privilege to run the script.
* Docker engine (latest version), or an OCI compatible tool. This will be used to push the images to the k8s local repository.
* K2view Kubernetes Docker images. Will be provided by K2view, Depend on the required projects.
* Mailbox ID and Cloud manager URL. Will be provided be K2view.This will be used by the K2view-agent (more explanation below).
* Firewall outbound access to GitHub.com and K2view Cloud manager at port 443 (https).


## Kubernetes components and the K2view-agent 

The installation script will automatically configure and install everything that is required to have K8s running and ready to be access K2view Cloud Manager. It is installed using MicroK8s (https://microk8s.io/) along with some additional controllers. 

In addition to the K8s, k2view-agent Pod is also being deployed during the installation process. The k2view-agent is a module used to bridge the communication from K2view Cloud Manager to the K8s cluster. It pulls its dedicated instructions from K2view Cloud Manager according to cluster's mailbox (the Mailbox ID is provided by K2view). With that, there is only outbound traffic to K2view Cloud Manager URL and no inbound connection is required.  
 

## Installation 

Clone Git repository 'k2view/blueprints' in GitHub 

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
This script will install the following tools:
* cert-manager - more info at <a href="https://cert-manager.io/" target="_blank">https://cert-manager.io/</a>
* NGINX Ingress - more info at <a href="https://docs.nginx.com/" target="_blank">https://docs.nginx.com/</a>
* hostpath-storage
* docker registry - more info at <a href="https://microk8s.io/docs/registry-built-in" target="_blank">https://microk8s.io/docs/registry-built-in/</a>
* metrics-server

During the installation installer will request to provide the prerequisites:
* Mailbox ID
* Cloud manager URL
* DNS Record

Once the setup finishes (it can take several minutes), and before we can create new space, few steps need to be taken:
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

open the K2view Cloud Manager portal using the internet browser of your preference and create a space. After this space starts, you can begin using the application.


