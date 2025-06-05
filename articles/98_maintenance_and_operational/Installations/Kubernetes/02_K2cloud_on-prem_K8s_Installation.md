# On-premises Kubernetes Cluster Installation

This article describes the guidelines and instructions for creating a K2cloud site - a K8s (Kubernetes) cluster - on premises.  

While K2cloud K8s cluster deployment on the Cloud as a <a href="/articles/98_maintenance_and_operational/Installations/Kubernetes/01_K2cloud_Self-hosted_K8s_Installation.md">Self-hosted Kubernetes Cluster Installation</a> is done using Terraform and Helm charts, based on cloud provider’s K8s infrastructure, the on-premises K8s cluster deployment is done by running a script that is responsible for preparing all required infrastructure components. 

## Table of Contents

1. [On-premises Kubernetes Cluster Installation](#on-premises-k2cloud-kubernetes-cluster-installation)  
2. [Options](#options)  
   - [1. Multi-Node K2cloud Kubernetes Cluster – `k8s-setup.sh`](#1-multi-node-k2cloud-kubernetes-cluster--k8s-setupsh)  
   - [2. Single-Node K2cloud Kubernetes Cluster – `single_nodesh`](#2-single-node-k2cloud-kubernetes-cluster--single_nodesh)  
   - [Summary Comparison - Multi-node Cluster / Single-node Cluster](#summary-comparison---multi-node-cluster--single-node-cluster)  
   - [Recommendation](#recommendation)  
3. [Hardware Requirements](#hardware-requirements)  
4. [Preparations and Provisioning](#preparations-and-provisioning)
5. [K2view Bare Metal Blueprint](#k2view-bare-metal-blueprint)
6. [Installing Fabric in a Multi-Node K2cloud Fabric Cluster](#installing-fabric-in-a-multi-node-k2cloud-fabric-cluster)  
   - [Overview of the K2View Baremetal Kubernetes Setup Script (`k8s-setup.sh`)](#overview-of-the-k2view-baremetal-kubernetes-setup-script-k8s-setupsh)  
   - [Key Features](#key-features)  
   - [Prerequisites](#prerequisites)  
   - [Internet Access Required](#internet-access-required)  
   - [Before you Start](#before-you-start)  
   - [How the `k8s-setup.sh` Script Works](#how-the-k8s-setupsh-script-works)  
   - [Installed Components](#installed-components)  
   - [Addons](#addons)  
   - [Private container registry](#private-container-registry)  
   - [Optional Components and Features](#optional-components-and-features)  
   - [Usage Instructions](#usage-instructions)  
   - [Post-Installation](#post-installation)  
   - [Troubleshooting Tips](#troubleshooting-tips)  
   - [References](#references)  
7. [Installing Fabric in a Single-Node Cluster, "Kubernetes-in-a-Box"](#installing-fabric-in-a-single-node-cluster-kubernetes-in-a-box)  
   - [Starting and Stopping the Cluster and Services](#starting-and-stopping-the-cluster-and-services)  


# Options
There are **two variants** of on-premises Kubernetes installations for **K2view Fabric and TDM**, each tailored to different deployment needs and environments. These variants are based on two setup scripts available in the K2view Blueprints repository:

---

## 1. **Multi-Node K2cloud Kubernetes Cluster – `k8s-setup.sh`**

This variant is designed for **production-like environments** and installs a **multi-node Kubernetes cluster** on bare metal servers.

* **Purpose:** Prepares both **control plane** and **worker nodes** using `kubeadm`.
* **Components Installed:**

  * Container runtime (e.g., Docker)
  * Kubernetes components: `kubeadm`, `kubectl`, `kubelet`
  * Pod networking (e.g., Calico or Flannel)
* **Features:**

  * Initializes the cluster (`kubeadm init`)
  * Generates and applies **join tokens** for connecting worker nodes
  * Ideal for distributed deployments simulating production clusters
* **Use Case:** When you need a **realistic, multi-node Kubernetes environment** for hosting Fabric and TDM services across multiple machines.

---

## 2. **Single-Node K2cloud Kubernetes Cluster – `single_node.sh`**

This variant targets **development, testing, or proof-of-concept scenarios** and installs a **self-contained Kubernetes cluster** on a single machine. It is also known as the **Kubenetes-in-a-box** installation. 

* **Purpose:** Sets up a combined **control plane and worker node** on one host.
* **Components Installed:**

  * Container runtime
  * Kubernetes components
* **Features:**

  * Automatically initializes the cluster (`kubeadm init`)
  * Removes node taints to allow pod scheduling on the control plane
  * Lightweight and easy to deploy
* **Use Case:** When you need a **quick, local setup** of Fabric and TDM on a **single bare-metal host** for testing or evaluation.

---

## Summary Comparison - Multi-node Cluster / Single-node Cluster

<table>
  <thead>
    <tr>
      <th>Feature</th>
      <th><code>k8s-setup.sh</code></th>
      <th><code>single_node.sh</code></th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Target</td>
      <td>Multi-node cluster</td>
      <td>Single-node cluster</td>
    </tr>
    <tr>
      <td>Control plane setup</td>
      <td>Yes</td>
      <td>Yes</td>
    </tr>
    <tr>
      <td>Worker node setup</td>
      <td>Yes (via join token)</td>
      <td>No (same node acts as worker)</td>
    </tr>
    <tr>
      <td>Taints removal (for pods)</td>
      <td>No</td>
      <td>Yes</td>
    </tr>
    <tr>
      <td>Pod networking setup</td>
      <td>Yes</td>
      <td>Yes</td>
    </tr>
    <tr>
      <td>Intended use</td>
      <td>Production-like deployments</td>
      <td>Local/test deployments</td>
    </tr>
  </tbody>
</table>

---

These options allow K2view customers to tailor their on-prem Kubernetes deployments of Fabric and TDM based on the scale and purpose of their environment.

## Recommendation

Customers looking to install [Fabric Web Studio](/articles/98_maintenance_and_operational/Installations/dcr_web_studio/README.md) should consider installing it on Docker Compose or Podman. This provides a simpler installation experience than the use of the Kubernetes-in-a-box option. 

---

# Hardware Requirements 
Please consult the [requirements and prerequisites section](/articles/98_maintenance_and_operational/Hardware/2_All_Environments/04_Requirements_and_Prerequisites_for_K2cloud_on-prem_K8s_Installation.md) for the K2cloud on-premises K8s cluster installation. 

These recommendations apply to both the multi-node and single-node cluster installations. 

---

# Preparations and Provisioning

To install a K2cloud site on-premises, you must prepare and provide the necessary steps in coordination with your K2view representative. The process begins with gathering key configuration details, including TLS certificate files, and ensuring outbound internet access to specific K2view endpoints. These are essential for secure communications, image retrieval, and configuration via the K2cloud Orchestrator.

You must also contact your K2view representative to request access credentials and provisioning information. This includes a Cloud Mailbox ID, a K2view Nexus Repository account for pulling required Docker images, and a list of container images to populate your private registry. 

https://github.com/k2view/blueprints/blob/main/baremetal/README.md
---

# K2view Bare Metal Blueprint

The Baremetal Blueprint article published to [K2view's blueprint GitHub repository](https://github.com/k2view/blueprints/blob/main/baremetal) provides comprehensive guidance for deploying a K2view Fabric Kubernetes cluster on bare-metal (on-premises) infrastructure. It outlines the two supported installation options — single-node and multi-node clusters — using purpose-built setup scripts, and details the prerequisites, tools, and execution steps required for each. 

You'll need to clone this repository as described later in this article. 

---

# Installing Fabric in a Multi-Node K2cloud Fabric Cluster

## Overview of the K2View Baremetal Kubernetes Setup Script (`k8s-setup.sh`)

The `k8s-setup.sh` script automates the deployment of a **multi-node Kubernetes cluster** on bare-metal infrastructure. It prepares the operating system, installs required Kubernetes components, configures networking, and provides the necessary tooling for control plane and worker node setup. The script also includes options for enabling various optional components and configurations, making it suitable for both production-grade and testing environments.

## Key Features

* Multi-node cluster setup with role-based configuration (control plane or worker node)
* Automated installation of Docker, kubeadm, kubelet, and kubectl
* Optional deployment of networking and DNS components
* Customizable Kubernetes version, network CIDR, and more

## Prerequisites

* Linux OS (e.g., Ubuntu 20.04+)
* Root or sudo access
* Static IP address configuration
* Internet access for downloading packages
* Time synchronization enabled (e.g., via `ntpd` or `chronyd`)

## Internet Access Required

If you're running the script behind a firewall or proxy, please make sure that **HTTPS access to these sources is allowed** and that **DNS resolution is working** for the corresponding domains. Optionally, you could mirror or host the required packages and manifests internally for air-gapped environments.

The `k8s-setup.sh` script from the K2view Blueprints repository installs various components required to bootstrap a Kubernetes cluster on bare metal. Here's a list of **internet packages** (downloaded via `apt`, `curl`, or similar tools) that the script installs or downloads from public sources:

### **APT Packages (from Ubuntu repositories)**

These are installed via `apt-get install`:

* `apt-transport-https`
* `ca-certificates`
* `curl`
* `gnupg`
* `lsb-release`
* `software-properties-common`
* `jq`
* `conntrack`
* `containerd` or `docker.io` *(depending on the configuration)*
* `kubelet`
* `kubeadm`
* `kubectl`

These packages come from:

* Ubuntu repositories
* Google Kubernetes apt repo (`https://packages.cloud.google.com/apt`)
* Docker apt repo (`https://download.docker.com/linux/ubuntu`)

### **Remote Files and Scripts Downloaded via `curl`/`wget`**

* **Kubernetes GPG Key**

  ```bash
  curl -fsSL https://packages.cloud.google.com/apt/doc/apt-key.gpg | gpg --dearmor -o /usr/share/keyrings/kubernetes-archive-keyring.gpg
  ```

* **Docker GPG Key**

  ```bash
  curl -fsSL https://download.docker.com/linux/ubuntu/gpg | gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
  ```

* **CNI Plugin (e.g., Calico or Flannel)**
  The script supports networking setup via:

  * **Calico** (`https://raw.githubusercontent.com/projectcalico/calico/...`)
  * **Flannel** (`https://raw.githubusercontent.com/coreos/flannel/...`)

  These are applied with:

  ```bash
  kubectl apply -f <CNI_URL>
  ```

* **Helm Install Script**

  ```bash
  curl https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 | bash
  ```

### Summary of Required Internet Repositories and URLs

<table>
  <thead>
    <tr>
      <th>Purpose</th>
      <th>Source URL/Repo</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Kubernetes APT packages</td>
      <td><code>https://packages.cloud.google.com/apt</code></td>
    </tr>
    <tr>
      <td>Docker APT packages</td>
      <td><code>https://download.docker.com/linux/ubuntu</code></td>
    </tr>
    <tr>
      <td>CNI Plugin YAMLs</td>
      <td><code>https://raw.githubusercontent.com/projectcalico/...</code> or Flannel</td>
    </tr>
    <tr>
      <td>Helm</td>
      <td><code>https://raw.githubusercontent.com/helm/helm/main/scripts/...</code></td>
    </tr>
    <tr>
      <td>Ubuntu base packages</td>
      <td>Ubuntu APT mirrors (e.g., <code>http://archive.ubuntu.com/ubuntu</code>)</td>
    </tr>
  </tbody>
</table>

---

## Before you Start
Before installing Kubernetes, the swap and the internal firewall must be disabled. (Important: those steps will NOT be configured automatically during the execution of our installation script!)

The official documentation for kubeadm installation can be found at this link: https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/install-kubeadm/ 

## How the `k8s-setup.sh` Script Works

The script orchestrates the following steps:

1. **System Preparation**

`k8s-setup.sh` will change the following settings:

  * Enable IP forwarding (as sysctl net.ipv4.ip_forward=1)
  * Disable SELinux (as setenforce 0)

2. **Container Runtime Installation**

Kubernetes uses a container runtime to run pods (a list of all supported runtimes can be found in the link above). If none is installed, k8s-setup.sh will install and configure containerd as a runtime. If containerd is installed and its service is running, no modifications to its settings will be made. If that's the case, please ensure all parameters required by kubeadm are set.

   * Installs and configures Docker to use the `systemd` cgroup driver

4. **Kubernetes Installation**

   * Installs Kubernetes packages (`kubeadm`, `kubectl`, `kubelet`)
   * Applies kubeadm repository configuration and GPG keys

5. **Control Plane Initialization (if selected)**

   * Runs `kubeadm init` with specified network CIDR and hostname
   * Sets up kubeconfig for `kubectl`
   * Deploys a CNI plugin (Calico) to enable pod networking

6. **Worker Node Setup (if selected)**

   * Executes `kubeadm join` using a token to connect to the control plane

7. **Cluster Verification**

   * Lists nodes and pods across namespaces using `kubectl`

## Installed Components

<table>
  <thead>
    <tr>
      <th>Component</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>Docker</strong></td>
      <td>Container runtime used by Kubernetes</td>
    </tr>
    <tr>
      <td><strong>kubeadm</strong></td>
      <td>CLI utility for bootstrapping Kubernetes clusters</td>
    </tr>
    <tr>
      <td><strong>kubelet</strong></td>
      <td>Node-level agent that runs and manages pods</td>
    </tr>
    <tr>
      <td><strong>kubectl</strong></td>
      <td>Command-line interface for interacting with the Kubernetes cluster</td>
    </tr>
    <tr>
      <td><strong>Calico</strong></td>
      <td>Default CNI plugin for pod networking (if deployed)</td>
    </tr>
    <tr>
      <td><strong>Sysctl Rules</strong></td>
      <td>Kernel parameter tuning for bridging and forwarding</td>
    </tr>
  </tbody>
</table>


## Addons
The following addons will be automatically installed in your Kubernetes cluster:

* [Tigera's Calico](https://www.tigera.io/project-calico/), a Container Network Interface
* [Rancher's Local Path Provisioner](https://github.com/rancher/local-path-provisioner), a Storage Class called local-path, used to create Persistent Volumes using the host's filesystem
* [Distribution's Registry](https://distribution.github.io/distribution/), a private Container Registry (requires containerd to be used as container runtime)
* [MetalLB](https://metallb.io/), a network Load Balancer for the cluster
* [K8s's Ingress-NGINX](https://kubernetes.github.io/ingress-nginx/), an Ingress Controller (plus additional components like error page handler / ingress test)
* [K2view-agent](https://github.com/k2view/blueprints/blob/main/helm/k2view-agent/README.md), an application that communicates with our Cloud Orchestrator
  
**Note**:
* local-path Storage Class uses the directory "/opt/local-path-provisioner" in the host to store the Persistent Volumes
* docker-registry Container Registry stores all its data in a local-path Persistent Volume
* Helm is used to deploy some of the addons and will be automatically installed by k8s-setup.sh

## Private container registry
A private container registry can be configured if containerd is used for container runtime.

To push images to this private container registry, pull or load (import) the desired image (Docker will not be installed by default, but you can use ctr as long as you can run sudo)

* Pull the desired image

```bash
sudo ctr image pull -u <USERNAME> docker.share.cloud.k2view.com/k2view/fabric-studio:8.0.0_123
```

* OR import desired image (note: the image cannot be compressed)

```bash
sudo ctr image import /path/to/fabric-studio-8.0.0_123.tar
```

* Retag image

```bash
sudo ctr image tag docker.share.cloud.k2view.com/k2view/fabric-studio:8.0.0_123 registry.localhost/k2view/fabric-studio:8.0.0_123
```

* push image to private container registry

```bash
sudo ctr image push --plain-http registry.localhost/k2view/fabric-studio:8.0.0_123
Now you can instruct the Cloud Orchestrator to use the image from your private container registry.
```

## Optional Components and Features

The `k8s-setup.sh` script offers the ability to optionally enable or configure the following components during installation:

<table>
  <thead>
    <tr>
      <th>Optional Component</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>CNI Plugin (Calico)</strong></td>
      <td>Deployed by default for pod networking; other plugins may be substituted manually</td>
    </tr>
    <tr>
      <td><strong>Custom Hostname</strong></td>
      <td>Prompts the user to define a hostname to register with the cluster</td>
    </tr>
    <tr>
      <td><strong>Kubernetes Version</strong></td>
      <td>You can specify a particular version to install (e.g., 1.28.x)</td>
    </tr>
    <tr>
      <td><strong>Pod Network CIDR</strong></td>
      <td>Allows customization of the internal pod network (default is <code>192.168.0.0/16</code>)</td>
    </tr>
    <tr>
      <td><strong>Cluster Join Command</strong></td>
      <td>Generated and displayed to allow worker nodes to join via <code>kubeadm</code></td>
    </tr>
    <tr>
      <td><strong>Firewall Rules (Manual)</strong></td>
      <td>The script may suggest configuring firewall rules but leaves enforcement to the user</td>
    </tr>
    <tr>
      <td><strong>CoreDNS Deployment</strong></td>
      <td>CoreDNS is set up as part of the control plane init via kubeadm</td>
    </tr>
    <tr>
      <td><strong>API Server Advertise Address</strong></td>
      <td>Can be provided as a script argument or prompted interactively</td>
    </tr>
  </tbody>
</table>


> While these components are not toggled via flags directly in the script today, their design allows future extensibility or manual modification to enable/disable them.

## Usage Instructions

### 1. Clone the Repo

```bash
git clone https://github.com/k2view/blueprints.git
cd blueprints/baremetal
```

### 2. Make Script Executable

```bash
chmod +x k8s-setup.sh
```

### 3. Run the Script

```bash
sudo ./k8s-setup.sh
```

You will be prompted to select the node type (control plane or worker), hostname, and provide any required settings such as the advertise IP address or join command.

---

## Post-Installation

* Validate the cluster status:

  ```bash
  kubectl get nodes
  kubectl get pods -A
  ```

* Run the generated `kubeadm join` command on additional worker nodes to add them to the cluster.

---

## Troubleshooting Tips

<table>
  <thead>
    <tr>
      <th>Issue</th>
      <th>Solution</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Swap not disabled</td>
      <td>Run <code>sudo swapoff -a</code> and remove swap entries from <code>/etc/fstab</code></td>
    </tr>
    <tr>
      <td>kubelet failing to start</td>
      <td>Check logs with <code>journalctl -u kubelet</code></td>
    </tr>
    <tr>
      <td>Pods not reaching each other</td>
      <td>Confirm CNI plugin is installed (<code>kubectl get pods -n kube-system</code>)</td>
    </tr>
    <tr>
      <td>Control plane unreachable</td>
      <td>Ensure port 6443 is open and accessible on the control plane node</td>
    </tr>
  </tbody>
</table>


---

## References

* [K2View Blueprints – Baremetal](https://github.com/k2view/blueprints/tree/main/baremetal)
* [Kubernetes Official Setup Guide](https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/install-kubeadm/)
* [Calico CNI Plugin](https://docs.tigera.io/calico/latest/introduction/)

---

# Installing Fabric in a Single-Node Cluster, "Kubernetes-in-a-box"

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
