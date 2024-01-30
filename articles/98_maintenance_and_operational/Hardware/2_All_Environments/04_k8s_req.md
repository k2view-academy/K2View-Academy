# Requirements and Prerequisites for Self-hosted Cloud Kubernetes Installation
This article describes the requirements and prerequisites for the K2cloud *self-hosted* cloud deployment, which is based on the Kubernetes (K8s) infrastructure, when deployed at your cloud. The supported cloud providers are: AWS, GCP and Azure.

K2cloud is also available as a *fully-managed* service (PaaS), where K2view manages the platform for you, with all relevant deployments and installations, on a segregated arena in the cloud.



Terraform sample for the creation and installation of the infrastructure, as well as Helm chart being used during the deployment can be found [here](https://github.com/k2view/blueprints/).

The namespaces creation and on-going lifecycle is handled by the K2cloud platform's Orchestrator. 



## Hardware Requirements

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
<p>Serving Studio namespaces: 300 GB SSD disk (minimum)</p>
<p>Serving Fabric cluster namespaces: Storage shall be calclulated according to the project's estimated needs.</p>
</td>
</tr>
</tbody>
</table>



### How Many Nodes Do I Need?

Determining the base number of the required worker nodes, as well as the maximum number of nodes for cluster horizontal auto-scaling, depends on the K8s cluster purpose, your project needs and the project's type. According to these, different modules and PODs are required to be deployed, which affect the nodes calculations.

Below are some use cases:

* **Studio** namespaces requires, as minimum, for Fabric POD: 4 cores and 16GB RAM. (On this POD several applications are running: Fabric runtime, Studio and Neo4J). 

  Additional PODs may be required, depending on the project's and solution types:

  * The TDM solution needs a Postgres POD. Accordingly, the minimum required for such namespace is:
    * Fabric: 4 cores, 16GB RAM
    * PG: 2 cores, 8 GB RAM
  * A Project using a real-time data streaming, a Cassandra POD is required (for the IIDFinder module).  Accordingly, the minimum required for such namespace is:
    * Fabric: 4 cores, 16GB RAM (in this case, Kafka application is also running on this POD).
    * Cassandra: 2 cores, 8GB RAM

* **Non-Studio** namespaces, such as UAT, SIT, pre-production and production, require a cluster of several Fabric PODs, using K8S auto-scale capabilities.

  On the other hand, PODs or resources which required for Studio namespace, might not be needed here: for non-studio case, it is recommended to use manages services (buckets / blob-storage for massive storage; managed DBs like managed Postgres or managed Cassandra; Managed Kafka rather than running it on Fabric POD).



> Note: You may consider having several clusters. For example: Dev cluster for Studio; QA and preproduction; Production. This can be useful for more security segregation (that is: which clusters are allowed to access what data platforms/DBs). Additionally, it can help for resources allocation, as scaling in and out may be different, and you may wish to avoid the effect of Studio namespaces on production and vice versa.
>
> In POT only single cluster is required for Studio namespaces. 



## K8s Cluster Preparations

While setting up a K8s cluster, you shall follow these guidelines:

* The supported versions for a Kubernetes cluster are: 1.24 - 1.27
* The supported versions for Helm chart are: 3.X

- Verify that you have a client environment with the kubectl and Helm command-line tools, configured with a service account or a user that has an admin access to a namespace on the subject Kubernetes cluster.

- Ensure you use NGINX Ingress Controller (see [here](https://kubernetes.github.io/ingress-nginx/deploy/) the installation instructions).

  - Ensure you have a CNI for the cluster's network policy (see [here](https://docs.tigera.io/calico/3.25/getting-started/kubernetes/helm#install-calico) the installation instructions for Calico CNI. K2cloud deployments use basic network policy and accordingly most of the CNI’s fit).

- Prepare a domain name that will be used for this cluster and that can be resolved by DNS. The domain should point to the load balancer that points to the Nginx ingress controller with domain wildcard. 

  Provide it k2view team. 

  When creating a namespace, its name is associated as a subdomain to this domain in the ingress controller. For example, if the domain is "k2dev.company.com" and a created namespace is "test", then the URL of this namespace, that users shall access, will be "test.k2dev.company.com".

- Ensure the following according to the cloud provider:

  - AWS
    - Amazon EFS CSI Driver shall be installed (see [here](https://docs.aws.amazon.com/eks/latest/userguide/efs-csi.html) and [here]([https://github.com/kubernetes-sigs/aws-efs-csi-driver/blob/master/docs/README.md#examples) for guidelines and examples).
    - Amazon EBS CSI Driver shall be installed. (see [here](https://docs.aws.amazon.com/eks/latest/userguide/ebs-csi.html) for guidelines).
    - Cluster auto-scaler is set (see [here](https://github.com/kubernetes/autoscaler/blob/master/cluster-autoscaler/cloudprovider/aws/README.md) for more information. It can be any cluster auto-scaler). For Dev Studio type cluster, auto-scaling is not required.
    - Have an ACM cert that attached on the LB level.
  - GCP
    - Have GKE with 2 AZs (due to GCP limitation of regional-pd volumes. Refer [here]([https://cloud.google.com/kubernetes-engine/docs/how-to/persistent-volumes/regional-pd) for more information).
    - Provide k2view the cluster's TLS/HTTPS certificate.
  - Azure
    - Provide k2view the cluster's TLS/HTTPS certificate.
    - Recommended: Have AKS on a single AZ (Azure does not support having persistent volumes cross AZ, which can affect the user experience when K8s revives or moves his namespace).

### Persistent Volumes and Storage Classes

The type of volume that shall be provisioned, depends on the cloud provider:

- AWS: EFS storage class is being used for Studio namespaces. Please refer [here](https://raw.githubusercontent.com/kubernetes-sigs/aws-efs-csi-driver/master/examples/kubernetes/dynamic_provisioning/specs/storageclass.yaml) to EFS storage class sample.

  These are the default names and UIDs that are used by k2cloud deployments. If different values have to be set - provide them to K2view. 

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

The K2-agent is a module, deployed in each cluster, as a POD inside a dedicated namespace. It is used for polling instructions, for deployment, from the k2cloud platform Mailbox. Adopting this workflow eliminates the need of connectivity from the K2cloud orchestrator into the cluster, so that only outbound traffic from the agent to cloud K2cloud orchestrator is required.

The k2-agent source-code can be found [here](https://github.com/k2view/k2-agent).

As part of cluster preparations, you shall deploy the k2-agent. It is deployed at a dedicated namespace (by default at "k2view-agent" namespace).

* Refer [here](https://github.com/k2view/blueprints/tree/main/helm/k2view-agent) for the k2-agent helm charts and with its configuration values.

* Cluster's dedicated Mailbox ID shall be obtained from k2view, to be applied at the agent values.

* The kubeInterface should be accessible by the k2-agent.




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
- Cluster shall have access to your data platforms/DBs, as required by the project.

> Note:  Container images as well as Helm charts can be hosted in your registry. If you consume them from there, please inform k2view team about, to be configured it at the K2cloud platform.

 

### Managed service Credentials 

For Fabric cluster namespace, like production, where massive data is handled, it is recommended to use managed services (like managed Postgres or bucket / blob storage). K2cloud is creating on-the-fly relevant managed resources, during such namespaces that need them. For this purpose, k2-agent namespace need to have credentials to make it. This can be achieved by using K8s cloud native credentials: 

* AWS: using IAM role ARN, attached to k2view-agent namespace service account. This shall be set at the k2-agent configuration
* GCP: using service account. The GCP service account name and project ID shall be set at the k2-agent configuration.

 

### Monitoring

The K2cloud fully managed solution includes monitoring mechanism, for collecting and showing Fabric's metrics and logs.

Assuming that you have your standards and regulation about monitoring, monitoring is out of self-host guidelines scope. Contact k2view team, when required, for further explanations. Read [here](https://support.k2view.com/Academy/articles/21_Fabric_troubleshooting/03_monitoring.html) and [here](https://support.k2view.com/Academy/articles/21_Fabric_troubleshooting/05_monitoring_dashboard_example_setup.html) for more information of Fabric (non-cloud) monitoring setup examples.

