# Requirements and Prerequisites for Self-hosted cloud Kubernetes Installation
This article describes the requirements and prerequisites for K2cloud *self-hosted* cloud deployment, which is based on Kubernetes (K8s) infrastructure, when deployed at your cloud. The supported cloud providers are: AWS, GCP and Azure.

K2cloud is also available as a *fully-managed* service (PaaS), where k2view is managing for you the platform, with all relevant deployments and installations, on a segregated arena in the cloud. 



Terraform sample for the creation and installation of the infrastructure, as well as Helm chart being used during the deployment can be found [here](https://github.com/k2view/blueprints/).

The namespaces creation and on-going lifecycle is handled by the K2cloud platform's Orchestrator. 



## Hardware Requirements

A Kubernetes worker node is expected to meet the following requirements:

<table>
<tbody>
<tr>
<td>
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
<td>
<p><strong>Storage</strong></p>
</td>
<td>
<p>300 GB SSD disk (minimum)</p>
</td>
</tr>
</tbody>
</table>


### How many nodes I need?

Determining the base number of the required worker nodes, as well as the maximum number of nodes for cluster horizontal auto-scaling, is depended on K8s cluster purpose, your project needs and project's type. According to that, different modules and PODs are required to be deployed, which affect on the nodes calculations.

Below are some use cases:

* **Studio** namespaces requires, as minimum, for Fabric POD: 4 cores and 16GB RAM. (On this POD several applications are running: Fabric runtime, Studio and Neo4J). 

  Additional PODs might be required, depends on the project's and solution types:

  * TDM solution needs a Postgres POD. Accordingly the minimum required for such namespace is:
    * Fabric: 4 cores, 16GB RAM
    * PG: 2 cores, 8 GB RAM
  * A Project using a real-time data streaming, a Cassandra POD is required (for the IIDFinder module). . Accordingly the minimum required for such namespace is:
    * Fabric: 4 cores, 16GB RAM (In this case also Kafka application is running on this POD)
    * Cassandra: 2 cores, 8GB RAM

* **Non Studio** namespaces like UAT, SIT, pre-production and production, requires scalable Fabric PODs (e.g. starting with 2 Fabric PODs). 

  On the other hand, PODs or resources which required for Studio namespace, might not be needed here: for non-studio case, it is recommended to use manages services (buckets / blob-storage for massive storage; managed DBs like managed Postgres or managed Cassandra; Managed Kafka rather than running it on Fabric POD).



> Note: You might consider having several clusters. For example: Dev cluster for Studio; QA and preproduction; Production. This can be useful for more security segregation and network policies (which clusters are allowed to access to what data platforms/DBs). In addition it can help for resources allocation, as scaling in and out might be different and you may wish to avoid the affect of Studio namespaces on production and vice-versa.
>
> In POT only single cluster is required for Studio namespaces. 



## K8s Cluster Preparations

While setting up a K8s cluster you shall follow these guidelines:

* The supported versions for a Kubernetes cluster are: 1.24 - 1.27
* The supported versions for Helm chart are: 3.X

- Verify that you have a client environment with the kubectl and Helm command-line tools, configured with a service account or a user that has an admin access to a namespace on the subject Kubernetes cluster.

- Ensure you use NGINX Ingress Controller (see [here](https://kubernetes.github.io/ingress-nginx/deploy/) the installation instruction).

- Ensure you use Calico CNI for the cluster's network policy (see [here](https://docs.tigera.io/calico/3.25/getting-started/kubernetes/helm#install-calico) the installation instruction).

- Prepare a domain name that will be used for this cluster and can be resolved by DNS. Provide it k2view team. 

  When creating a namespace its name is being associated as a subdomain to this domain in the ingress controller. For example, if domain is "k2dev.company.com" and a created namespace is "test", then URL of this namespace, that users shall access to, will be "test.k2dev.company.com".

- Ensure the following according to the cloud provider:

  - AWS
    - Amazon EFS CSI Driver shall be installed (see [here](https://docs.aws.amazon.com/eks/latest/userguide/efs-csi.html) and [here]([https://github.com/kubernetes-sigs/aws-efs-csi-driver/blob/master/docs/README.md#examples) for guidelines and examples).
    - Amazon EBS CSI Driver shall be installed. (see [here](https://docs.aws.amazon.com/eks/latest/userguide/ebs-csi.html) for guidelines).
    - Cluster auto-scaler is set (see [here](https://github.com/kubernetes/autoscaler/blob/master/cluster-autoscaler/cloudprovider/aws/README.md) for more information. It can be any cluster auto-scaler). for Dev Studio type cluster autoscaling is not required.
    - Have an ACM cert that attached on the NLB level.
  - GCP
    - Have GKE with 2 AZs (due to GCP limitation of regional-pd volumes, Refer [here]([https://cloud.google.com/kubernetes-engine/docs/how-to/persistent-volumes/regional-pd) for more information).
    - Provide k2view the cluster's TLS/HTTPS certificate.
  - Azure
    - Have AKS with single AZ (due to Azure limitation of persistent volume cross AZ)
    - Provide k2view the cluster's TLS/HTTPS certificate.



### Persistent volumes and Storage classes

The type of volume that shall be provisioned depends on the cloud provider:

- AWS: EFS storage class is being used for Studio namespaces. Please refer [here](https://raw.githubusercontent.com/kubernetes-sigs/aws-efs-csi-driver/master/examples/kubernetes/dynamic_provisioning/specs/storageclass.yaml) to EFS storage class sample.

  These are the default names and UIDs which used by k2cloud deployments. If different values have to be set - provide them to k2view. 

  The below list covers several storage classes, that not all of them are required for all projects. Please check with your team and with k2view about the project and solution type that you are using. For example, for TDM solution usually only fabric and PG are required. 

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
  - Currently Azure does not have an NFS/EFS equivalent solution and thus a local disk shall be used. 



### K2-agent

The K2-agent is a module, deployed in each cluster, as a POD inside a dedicated namespace. It is used for polling instructions, for deployment, from the k2cloud platform Mailbox. 

As part of cluster preparations, you shall deploy the k2-agent.

* Refer [here](https://github.com/k2view/blueprints/tree/main/helm/k2view-agent) for the k2-agent helm charts and with its configuration values.

* Cluster's dedicated Mailbox ID shall be obtained from k2view, to be applied at the agent values.



### Fabric containers registry 

For simplicity, k2view suggests using its shared Nexus for the Fabric and k2-agent images. To use and consume them, you shall open an outbound connection into the Nexus host. Refer to the Networking section. 

You can also use your registry. For this, you shall:

* Contact k2view team to get Nexus access credentials.
* Take the relevant images, scan them if required, and upload into your registry.
* Provide to k2view the URL of the registry.



The non Fabric images - Postgres, Casandra and Neo4j - are not provided by k2view. Rather, use the images as published at Docker Hub. If you prefer hosting them also in your registry, inform k2view team about, to be configured it at the K2cloud platform.



### Connectivity and Networking

The cluster interacts with external hosts, which you shall open the outbound network into them - all in port 443:

- https://cloud.k2view.com (used to get intrucstions via Mailbox from the K2cloud platform orchestrator)
- https://nexus.share.cloud.k2view.com (used to grab Fabric and k2-agent images)
- https://github.com (used to grab the deployments Helm charts)

> Notes: 
>
> * Container images as well as Helm charts can be hosted in your registry. If you consume them from there, please inform k2view team about, to be configured it at the K2cloud platform.
> * Cluster shall have access to managed services or to your data platforms/DBs, as required by your .

 

### Monitoring

The K2cloud fully managed solution includes monitoring mechanism, for collecting and showing Fabric's metrics and logs.

Assuming that you have your standards and regulation about monitoring, monitoring is out of self-host guidelines scope. You can contact k2view team, when required, for further explanations.

