# Requirements and Prerequisites for On-premises Kubernetes Cluster Installation

This article describes the guidelines and instructions for creating a K2cloud site - a Kubernetes (K8s) cluster ready for operation.

While K2cloud K8s cluster deployment on the cloud (fully managed or self-hosted) is done using Terraform, based on each cloud provider’s K8s infrastructure, the on-prem K8s cluster deployment is performed by running a script that is responsible for preparing all required infrastructure components. 

Please refer to the [On-premises K2ckoud Kubernetes Cluster Installation](/articles/98_installation_and_upgrade/Installations/02_K2cloud_on-prem_K8s_Installation.md) for instructions how this is acheived. 


## Hardware Requirements 

**Supported OS**: The K2cloud K8s cluster can be installed on Debian-based (Debian, Ubuntu) and RHEL-based (Redhat, Centos, Fedora, Amazon Linux 2) Linux distributions.

A K8s worker node is expected to meet the following requirements and shall be prepared accordingly:

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

* Create a DNS record pointing to the server hosting the K8s node (it can point to a private or a public IP). The DNS record for the server hosting the K8s node does not need to be registered in DNS. The only requirement is that users can resolve the DNS name internally to the customer's environment.

* A certificate (with its corresponding private key) is needed for the selected domain.

  > For a Proof of Technology (POT) Environment, both domain and certificates can be provided by K2view.

* A user with sudo privileges is needed in order to run the installation script.

* The requirement is for either a Docker engine to be installed (latest version) or for an OCI-compatible tool. This will be used to push the images to the local Kubernetes (K8s) repository.

* Verify that the host has outbound access to both GitHub.com and the K2view Cloud Manager on port 443 (https).

* K2view should be provided with the domain name of your environment.

* Contact K2view and obtain:

  * K2view Kubernetes Docker images, depending on the required projects.

  * Mailbox ID and the Cloud Manager URL.
 

## Installation Options for On-Premises Kubernetes
K2view supports two variants of on-premises Kubernetes installations for Fabric and TDM, allowing customers to tailor deployments based on their environment and use case. These options are provided through two setup scripts in the K2view Blueprints repository:

* `k8s-setup.sh` is intended for multi-node production-like deployments, installing a Kubernetes cluster across multiple bare-metal machines. It sets up both control plane and worker nodes, configures networking (e.g., Calico or Flannel), and handles node joining via kubeadm.

* `single_node.sh` offers a lightweight, single-node cluster suitable for development or testing. It installs all required components on a single host and configures the node to run both the control plane and workloads.
  
  * **Recommendation** - Customers looking to install [Fabric Web Studio](/articles/98_installation_and_upgrade/Installations/Fabric_Web_Studio/README.md) should consider installing it on Docker Compose or Podman. This provides a simpler installation experience than the use of the Kubernetes-in-a-box option. 

These installation methods provide flexibility for deploying Fabric and TDM in either realistic, distributed environments or local, self-contained setups.

Please refer to the [On-premises K2ckoud Kubernetes Cluster Installation](/articles/98_installation_and_upgrade/Installations/02_K2cloud_on-prem_K8s_Installation.md) topic for instructions. 


