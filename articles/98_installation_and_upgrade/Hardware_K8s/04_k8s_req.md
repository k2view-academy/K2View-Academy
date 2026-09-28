# K2cloud Self-hosted Kubernetes Installation System Requirements

This article describes the requirements and prerequisites for the K2cloud *self-hosted* cloud deployment, which is based on the Kubernetes (K8s) infrastructure, when deployed at your cloud. Supported cloud providers include AWS, GCP, and Azure.

K2cloud is also available as a *fully-managed* service (PaaS), where K2view manages the platform for you, with all relevant deployments and installations, on a segregated arena in the cloud.



A Terraform sample for creating and installing the infrastructure, along with the Helm chart used for deployment, is available [here](https://github.com/k2view/blueprints/).

The K2cloud platform's Orchestrator handles namespace creation and ongoing lifecycle management.

## Table of Contents

  - [Hardware Requirements](#hardware-requirements)
    - [How Many Nodes Do I Need?](#how-many-nodes-do-i-need)
  - [K8s Cluster Preparations](#k8s-cluster-preparations)
    - [Persistent Volumes and Storage Classes](#persistent-volumes-and-storage-classes)
    - [K2-agent](#k2-agent)
    - [Fabric Containers Registry](#fabric-containers-registry)
    - [Connectivity and Networking](#connectivity-and-networking)
    - [Managed Service Credentials](#managed-service-credentials)


## Hardware Requirements

A Kubernetes worker node is expected to meet the following requirements:

<table>
<tbody>
<tr>
<td valign="top">
<p><strong>CPU</strong></p>
</td>
<td>
<p>8 cores (minimum) or 16 cores (recommended)</p>
<p>64-bit CPU architecture</p>
</td>
</tr>
<tr>
<td>
<p><strong>RAM</strong></p>
</td>
<td>
<p>32 GB RAM (minimum) or 64 GB RAM (recommended)</p>
</td>
</tr>
<tr>
<td valign="top">
<p><strong>Storage</strong></p>
</td>
<td>
<p>Serving Studio namespaces: 300 GB SSD disk (minimum)</p>
<p>Serving Fabric cluster namespaces: Storage is calculated according to the project's estimated needs.</p>
</td>
</tr>
</tbody>
</table>

The CPU-to-memory ratio is useful for memory-optimized machines' profile.

### How Many Nodes Do I Need?

Determining the base number of the required worker nodes, as well as the maximum number of nodes for a cluster's horizontal auto-scaling, depends on the K8s cluster's purpose, your project's needs, and the project's type. According to these, different modules and PODs are required to be deployed, which affect the nodes' calculations.

Below are some use cases:

* The recommended resources for **Studio** namespaces for the Fabric POD are: 4 cores and 16GB RAM. (Several applications are running on this POD: Fabric runtime, Studio, and Neo4J). 

  Additional PODs may be required, depending on the project and solution types:

  * The TDM solution needs a Postgres POD. Accordingly, the minimum requirement for such a namespace is:
        * Fabric: 4 cores, 16GB RAM
        * PG: 2 cores, 8 GB RAM
  * A Project using real-time data streaming requires a Cassandra POD (for the IIDFinder module).  Accordingly, the minimum requirement for such a namespace is:
        * Fabric: 4 cores, 16GB RAM (note that in this case, the Kafka application is also running on this POD).
        * Cassandra: 2 cores, 8GB RAM

* **Non-Studio** namespaces, such as UAT, SIT, pre-production, and production, require a cluster of several Fabric PODs, using K8S auto-scaling capabilities.

  On the other hand, PODs and resources that are required for the Studio namespace might not be needed here: for a non-studio case, it is recommended to use managed services (buckets / blob-storage for massive storage; managed DBs like managed Postgres or managed Cassandra; managed Kafka rather than running it on Fabric PODs).
  Accordingly, a namespace might contain only Fabric Pods, which require 2 cores and 8GB RAM. Just so you know, different resources will be necessary, according to your project's needs.   



> Note: You may consider having several clusters. For example: Dev cluster for Studio, QA, preproduction, and Production. This separation leads to stronger enforcement of security and privacy policies (i.e., which clusters can access which data platforms/DBs). Additionally, it can help with resource allocation, as scaling in and out may differ, and you may want to avoid Studio namespaces affecting production and vice versa.
>
> In POT - for Studio namespaces, a single 3-node K8S cluster is required. 



## K8s Cluster Preparations

While setting up a K8s cluster, you shall follow these guidelines:

* The supported versions for a Kubernetes cluster are: 1.28 - 1.32
* The supported versions for the Helm chart are: 3.X

- Verify that you have a client environment with the kubectl and Helm command-line tools, configured with a service account or a user that has admin access to a namespace on the subject Kubernetes cluster.

- Prepare a domain name that will be used for this cluster and that can be resolved by DNS. The domain should point to the load balancer that points to the NGINX Ingress controller. 

  Provide the domain name to the K2view team.

- Ensure the following, according to the cloud provider:

  - AWS
    - Amazon EFS CSI Driver is installed (see [here](https://docs.aws.amazon.com/eks/latest/userguide/efs-csi.html) and [here](https://github.com/kubernetes-sigs/aws-efs-csi-driver/blob/master/docs/README.md#examples) for guidelines and examples).
    - Amazon EBS CSI Driver shall be installed. (see [here](https://docs.aws.amazon.com/eks/latest/userguide/ebs-csi.html) for guidelines).
    - Cluster auto-scaler is set (see [here](https://github.com/kubernetes/autoscaler/blob/master/cluster-autoscaler/cloudprovider/aws/README.md) for more information. It can be any cluster auto-scaler). Auto-scaling is not required for Dev Studio type clusters.
    - Have a certificate attached at the LB level.
  - GCP
    - Have GKE with 2 AZs (due to GCP limitation of regional-pd volumes. Refer [here]([https://cloud.google.com/kubernetes-engine/docs/how-to/persistent-volumes/regional-pd) for more information).
    - Provide K2view with the cluster's TLS/HTTPS certificate.
  - Azure
    - Provide K2view with the cluster's TLS/HTTPS certificate.
    - Recommended: Have AKS on a single AZ (Azure does not support having persistent volumes across AZs, which can affect the user experience when K8S revives or moves its namespace).

> The proposed sample Terraform defines several modules that are part of the cluster preparations. If, according to your organization's needs, you need to change some parts of it or run your Terraform, ensure the following:
> * You use NGINX Ingress controller (see [here](https://kubernetes.github.io/ingress-nginx/deploy/) the installation instructions).
> * You have a CNI for the cluster's network policy (see [here](https://docs.tigera.io/calico/3.25/getting-started/kubernetes/helm#install-calico) the installation instructions for Calico CNI. K2cloud deployments use basic network policy; accordingly, most CNIs fit.


### Persistent Volumes and Storage Classes

The type of volume that shall be provisioned depends on the cloud provider:

- AWS: EFS storage class is being used for Studio namespaces. Please refer to [here](https://raw.githubusercontent.com/kubernetes-sigs/aws-efs-csi-driver/master/examples/kubernetes/dynamic_provisioning/specs/storageclass.yaml) for the EFS storage class sample.

  These are the default names and UIDs that are used by K2cloud deployments. If you need different values, provide them to K2view. 

  The list below covers several storage classes, but not all are required for every project. Please check with your team and with K2view about the project and the solution that you are using. For example, for the TDM solution, you usually need only Fabric and PG. 

  - name: efs-fabric
    uid: "1000"
  - name: efs-cassandra
    uid: "0"
  - name: efs-kafka
    uid: "1000"
  - name: efs-pg
    uid: "999"

- GCP
  - Use regional pd 
- Azure
  - Currently, Azure does not have an NFS/EFS equivalent solution; therefore, a local disk shall be used. 



### K2-agent

The K2-agent is a module, deployed in each cluster, as a POD inside a dedicated namespace. It polls deployment instructions from the K2cloud platform Mailbox. This workflow eliminates the need for connectivity from the K2cloud orchestrator into the cluster, so only outbound traffic from the agent to the K2cloud Orchestrator is required.

The k2-agent source code can be found [here](https://github.com/k2view/k2-agent).

As part of cluster preparations, you shall deploy the K2-agent. Deploy it in a dedicated namespace (default name: "k2view-agent").

* Refer [here](https://github.com/k2view/blueprints/tree/main/helm/k2view-agent) for the k2-agent helm charts and with its configuration values.

* The cluster's dedicated Mailbox ID shall be obtained from K2view and applied to the agent's configuration values.

* The kubeInterface should be accessible by the k2-agent.




### Fabric Containers Registry 

For simplicity, K2view suggests using its OCI shared container registry for the Fabric and k2-agent images. To use and consume them, you shall open an outbound connection to K2view's container registry at docker.share.cloud.k2view.com. Refer to the Networking section. 

You can also use your OCI-based registry. For this, you shall:

* Contact the K2view team to get container registry access credentials.
* Take the relevant images, scan them if required, and upload them to your registry.
* Provide K2view with the registry URL.


The non-Fabric images - Postgres, Cassandra, and Neo4j - are not provided by K2view. Instead, use the images published on Docker Hub. If you prefer to host them in your registry, inform the K2view team so they can configure them in the K2cloud platform orchestrator.



### Connectivity and Networking

The cluster interacts with external hosts, to which you shall open outbound network access, all on port 443:

- https://cloud.k2view.com (used to get instructions via the Mailbox REST service from the K2cloud platform orchestrator)
- https://docker.share.cloud.k2view.com (used for fetching Fabric and k2-agent images)
- https://github.com (used for fetching the deployments' Helm charts)
- Cluster shall have access to your data platforms/DBs, as the project requires.

> Note: As mentioned, container images can be hosted in your OCI registry. Helm charts can also be copied into your GIT repository and maintained there (your team is responsible for synchronizing with the official repository to ensure smooth operation). If you consume them from your repositories, inform the K2view team so they can configure them in the K2cloud platform orchestrator.

 

### Managed service Credentials 

For a Fabric cluster namespace, like production, where massive data is handled, we recommend using managed services (like managed Postgres or bucket/blob storage). K2cloud creates relevant managed resources on the fly during namespace creation. For this purpose, the k2-agent namespace needs credentials. This can be achieved by using K8s cloud native credentials: 

* AWS: using an IAM role ARN, attached to the k2view-agent namespace service account. Set this in the K2-agent configuration.
* GCP: using a service account. Set the GCP service account name and project ID in the k2-agent configuration.

 

