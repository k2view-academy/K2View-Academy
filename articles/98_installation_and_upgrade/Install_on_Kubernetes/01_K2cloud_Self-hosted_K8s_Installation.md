# K2cloud Self-Hosted Kubernetes Cluster Installation

## Table of Contents


1. [What is a K2cloud Self-Hosted Kubernetes Cluster](#what-is-a-k2cloud-self-hosted-kubernetes-cluster)
2. [High-level Deployment View](#high-level-deployment-view)
3. [Core Components](#core-components)
4. [Hardware Requirements](#hardware-requirements)
   - [Node Requirements](#node-requirements)
   - [K8s Cluster Preparations](#k8s-cluster-preparations)
   - [Persistent Volumes and Storage Classes](#persistent-volumes-and-storage-classes)
5. [Preparations and Provisioning](#preparations-and-provisioning)
   - [Planning and Installation Step Overview](#planning-and-installation-step-overview)
   - [Provisioning](#provisioning)
   - [Prerequisites](#prerequisites)
6. [Components](#components)
   - [K2-Agent](#k2-agent)
   - [Fabric Container Registry](#fabric-container-registry)
   - [NGINX Ingress Controller](#nginx-ingress-controller)
7. [Installation](#installation)
   - [TLS Certificate Requirements](#tls-certificate-requirements)
   - [Installation with Terraform](#installation-with-terraform)
   - [Installation with Helm](#installation-with-helm)
     - [Deploying NGINX Ingress Controller](#deploying-nginx-ingress-controller)
     - [TLS Certificate Installation for K2view Deployment](#tls-certificate-installation-for-k2view-deployment)
     - [Deploying Generic Database (PostgreSQL)](#deploying-generic-database-postgresql)
     - [Deploying K2view Agent](#deploying-k2view-agent)
     - [Deploying K2view Fabric](#deploying-k2view-fabric)
     - [Verify Deployments](#verify-deployments)
     - [Access Fabric Web Studio](#access-fabric-web-studio)
     - [Post-Installation Steps](#post-installation-steps)



## What is a K2cloud Self-Hosted Kubernetes Cluster

A **K2cloud Self-hosted Kubernetes cluster** for Fabric and TDM refers to a customer-managed Kubernetes environment (either on-premises or in their cloud account) that runs the full K2view Fabric and optional TDM services independently, while still integrating securely with the K2cloud Orchestrator for deployment management and control.

### Key Characteristics:

* **Self-Managed Infrastructure**: The Kubernetes cluster is provisioned and maintained by the customer in their preferred environment—on bare-metal, AWS (EKS), GCP (GKE), Azure (AKS), or any other compliant platform.

* **Cloud-Orchestrated, Locally Executed**: Although the environment is fully hosted and operated by the customer, it is connected to K2view’s centralized K2cloud Orchestrator via the secure K2-Agent. This enables remote deployment instructions, configuration management, and monitoring without exposing internal services to the public internet.

* **Fabric and TDM Services**: The deployment includes Fabric Server, Fabric Studio, and optionally TDM—each running as containers managed by Kubernetes. These services interact with local or managed databases, cloud-native storage, and customer-specific data sources.

* **Deployment Automation**: Terraform and Helm blueprints are provided to standardize and simplify the provisioning and installation process, enabling Infrastructure-as-Code and DevOps workflows.

* **Customer-Owned Data Plane**: All customer data, services, and integrations remain within the self-hosted environment. K2view does not access customer data; the K2-Agent only communicates control messages.

* **Compliance and Flexibility**: This deployment model is ideal for organizations with strict data residency, compliance, or connectivity requirements. It provides the flexibility to integrate with private networks, existing IAM systems, and custom CI/CD pipelines.

This model delivers the advantages of centralized orchestration and product updates from K2view while allowing enterprises complete control over the runtime environment and data boundaries.


## High-level Deployment View

The K2view Fabric deployment is designed for modularity, scalability, and security, leveraging modern cloud-native architecture principles. At its core, the solution operates within a Kubernetes cluster (K8s), where all services are deployed as containers orchestrated through the Kubernetes control plane.

Key aspects of the high-level deployment include:

* Cloud-Agnostic K8s Orchestration: Whether running on AWS (EKS), GCP (GKE), Azure (AKS), or on-premises, the architecture maintains a consistent deployment model using [Helm charts](https://github.com/k2view/blueprints/tree/main/helm) and [Terraform](https://github.com/k2view/blueprints#:~:text=yesterday-,Terraform,-Sync%20changes%20from) configurations.

* Separation of Control and Runtime: Fabric and Studio and control services are logically separated from runtime workloads, enabling fine-grained access control, easier scaling, and secure CI/CD pipelines.

* Ingress and Load Balancing: An NGINX Ingress Controller is typically deployed within the cluster, acting as the central entry point for all external requests. This allows mapping of incoming HTTPS traffic to internal services such as Fabric, TDM, and Studio.

* Secure Communications and Identity: All services are secured using TLS certificates and integrated with role-based access controls. The K2-Agent connects securely to the K2cloud Orchestrator using outbound HTTPS.

* Infrastructure as Code (IaC): Environments are provisioned using [Terraform](https://github.com/k2view/blueprints#:~:text=yesterday-,Terraform,-Sync%20changes%20from) for reproducibility, traceability, and ease of configuration. [Helm charts](https://github.com/k2view/blueprints/tree/main/helm) are used to deploy application components.

* Registry and Artifact Management: Docker images are pulled from K2view’s Nexus repository and pushed into customer-specific OCI-compliant registries (e.g., Azure or a native provider Container Registry). These are referenced during Helm-based deployment.

* DNS and URL Management: Depending on the version, Fabric Spaces can be accessed via subdomain-based URLs or context-based URLs under a single domain. This provides flexibility in how environments are exposed and simplifies certificate management.

* Scalability and HA: The deployment supports multi-AZ clusters, auto-scaling node pools, and shared persistent storage via Cloud provider-native solutions.

This architecture ensures that K2view Fabric deployments are secure, highly available, and adaptable to various enterprise deployment models.

## Core Components
K2cloud Fabric deployments on customer self-hosted Kubernetes clusters rely on several core components:

* **Fabric Server**: The core runtime engine that powers the execution of data products and orchestrates the entire runtime lifecycle of services, processes, data access, and transformation logic within the platform. It supports the concurrent execution of multiple data services and interfaces, handles real-time data flows, and enables integration with external systems through configurable adapters. Fabric Server is highly scalable, designed to run as containerized workloads within a Kubernetes cluster, and supports stateful and stateless services. In production environments, it leverages Kubernetes-native features such as auto-scaling, persistent volume claims, and node affinity rules to deliver performance, fault tolerance, and operational flexibility.
* **TDM** (Test Data Management): A powerful, optional extension to Fabric used to generate, mask, and provision test data across distributed environments. TDM enables the creation of realistic, privacy-compliant test data by extracting and transforming production data or generating fully synthetic datasets. It supports use cases such as test data provisioning, PII masking, subsetting, and maintaining referential integrity. TDM can integrate with multiple source systems and is optimized for performance and scalability when deployed in Kubernetes environments. It is particularly useful in regulated industries that require compliant data handling in non-production environments.
* **Fabric Web Studio**: A comprehensive web-based UI for managing, developing, and deploying data products within the K2view platform. It provides visual tools for configuring interfaces, managing business logic, testing data flows, monitoring service activity, and accessing documentation. Fabric Studio supports collaborative development and tightly integrates with version control systems like Git. In development environments, it includes an embedded Neo4j instance for visualizing data relationships. For production environments, it typically connects to managed backend services and excludes development-only tooling to optimize performance and security.
* **K2-Agent**: Communicates with the K2cloud Orchestrator to receive deployment instructions. The K2-Agent is a lightweight Kubernetes service that securely connects your on-premises or cloud-based K2view Fabric deployment to the K2cloud Orchestrator. It is crucial in enabling centralized management, monitoring, and deployment orchestration.
* **Ingress Controller**: Handles routing external traffic to services within the cluster. K2view typically deploys an NGINX Ingress Controller as the default solution, offering a well-supported and configurable entry point across cloud and on-premises environments. For cloud-native implementations, customers can use provider-specific ingress solutions, such as AWS ALB Ingress Controller, GCP Ingress, or Azure Application Gateway Ingress Controller, depending on their platform requirements, networking architecture, and load-balancing needs.
* **Container Registry**: Stores and serves Docker images used during deployment.
* **Persistent Storage**: Maintaining stateful data across service restarts and rescheduling events within the Kubernetes cluster. In production environments, persistent storage must support high availability and zone redundancy to ensure data durability and fault tolerance. K2view supports cloud-native options such as Azure Files with Zone Redundant Storage (ZRS), Amazon EFS or EBS with Multi-AZ replication, and Google Filestore with regional availability. In non-production or development setups, simpler storage configurations (e.g., LRS or single-zone disks) may be used because they provide lower resilience.
* **PostgreSQL Database**: As the metadata store and persistence layer for Fabric configurations, project settings, and runtime state. In development or single-node deployments, an embedded PostgreSQL database is automatically provisioned within the cluster. For production-grade and multi-node clusters, a managed external PostgreSQL service (e.g., Azure Database for PostgreSQL) is required for resilience and scalability.
* **Embedded Neo4j Graph Database**: Bundled with Fabric Web Studio for use in development and testing scenarios. It supports metadata relationship visualization and query capabilities. In production environments, this is optional and typically not externally exposed.

## Hardware Requirements 

For platform-specific sizing guidance, the [Requirements and Prerequisites for Cloud Self-hosted Kubernetes Installation](/articles/98_installation_and_upgrade/Hardware_K8s/04_k8s_req.md) topic outlines detailed hardware specifications across AWS, GCP, and Azure environments, ensuring compatibility with Fabric and TDM workloads. It covers the following additional topics:

* **Node Requirements** - The number of nodes required depends on the intended usage (development, SIT, or production). While the document provides a general guideline, actual node count should account for redundancy, workload isolation, and autoscaling policies.
* **K8s Cluster Preparations** - Successful cluster preparation involves ensuring tool readiness (kubectl, helm, terraform, etc.), verifying outbound internet connectivity, and aligning cloud provider configurations (e.g., role assignments, resource provider registrations). The referenced guide provides practical pre-installation steps for both administrators and DevOps engineers.
* **Persistent Volumes and Storage Classes** - Fabric services rely on persistent volumes for stateful workloads. The guide explains the recommended use of high-availability storage classes such as ZRS or multi-zone volumes, depending on your cloud provider. Developers and operators should review this section to ensure their storage classes align with the cluster topology and SLA expectations.

## Preparations and Provisioning

To install a K2cloud Self-hosted Kubernetes cluster for Fabric and TDM, you will need to prepare and perform the necessary steps in coordination with your K2view representative. The process begins with gathering key configuration details, including TLS certificate files, and ensuring outbound internet access to specific K2view endpoints. These are essential for secure communications, image retrieval, and configuration via the K2cloud Orchestrator. K2view will also need to perform provisioning actions requiring information you will provide it. 

Your K2view representative will provide you with access credentials and provisioning information. This includes a Cloud Mailbox ID, a K2view Nexus Repository account for pulling required Docker images, and a list of container images to populate your private registry.

K2view will share a planning guide to help you with this provisioning and coordinate activities.

### Planning and Installation Step Overview 

Steps include:
* Gather prerequisites and confirm network access
* Provision of environment and services
* Install K2view Fabric using Terraform and Helm
* Populate the container registry
* Provide container image paths and domain to K2view
* Create a K2view Project and Space

### Provisioning 

K2view provisions:
* Nexus repo access
* Mailbox ID
* Initial admin user
* Tenant environment

Customer provides:
* TLS cert + key
* DNS config
* Git repo + token
* Registry image locations


### Prerequisites 

Internet access to:
* https://cloud.k2view.com
* https://nexus.share.cloud.k2view.com
* https://github.com

Required tools:
* Terraform 1.9.x
* Helm 2.13.0
* Kubectl 1.14+
* Docker (latest)
* Your provider's CLI (e.g., Azure CLI)

## Componenents

### K2-Agent 

Please refer to: ([K8s Requirements](https://github.com/k2view-academy/K2View-Academy/blob/Academy_8.2/articles/98_maintenance_and_operational/Hardware/2_All_Environments/04_k8s_req.md))

The K2-Agent is a lightweight Kubernetes service that securely connects your on-premises or cloud-based K2view Fabric deployment to the K2cloud Orchestrator. It plays a crucial role in enabling centralized management, monitoring, and deployment orchestration.

#### Function
- The K2-Agent fetches configuration instructions, deployment updates, and execution metadata from K2cloud.
- It acts as a secure communication bridge, ensuring the environment receives orchestrated control updates without manual intervention.
- It establishes trust between the runtime environment and the central K2view control plane, including provisioning and runtime telemetry exchange.

#### Security
- **Outbound-only HTTPS**: The agent communicates using outbound HTTPS (port 443) and does not require any inbound access, reducing surface area and simplifying firewall configurations.
- **Mailbox Authentication**: The agent authenticates with K2cloud using a tenant-specific Mailbox ID, securely provisioned by K2view.
- **Isolated Execution**: Deployed as a dedicated pod in the cluster, it operates independently from runtime workloads, ensuring separation of control and data paths.
- **No access to data**: K2-Agent does not have access to customer data or services directly. It only facilitates control plane instructions and status updates.

This design makes the K2-Agent a secure, robust component for enterprise-grade hybrid deployments.

---

### Fabric Container Registry 

Please refer to [K8s Requirements](/articles/98_installation_and_upgrade/Hardware_K8s/04_k8s_req.md).

K2view Docker images for Fabric, Studio, and supporting services must be pulled from K2view’s Nexus repository and pushed into a customer-managed, OCI-compliant container registry. This enables secure, high-performance image retrieval during Helm-based Kubernetes deployments.

#### Registry Setup Guidelines

Customers can use any of the following cloud-native registries:

- **Azure**: Azure Container Registry (ACR)
- **AWS**: Amazon Elastic Container Registry (ECR)
- **GCP**: Google Artifact Registry (GAR)

You may also use a private registry hosted on your infrastructure as long as it supports OCI-compliant image handling and secure access.

#### Considerations:
- Ensure the registry is accessible from within the Kubernetes cluster (e.g., proper VPC/VNet routing, firewall rules).
- Use access credentials, tokens, or identity-based authentication mechanisms (e.g., IAM roles) as required by your cloud provider.
- Store and tag the pulled Docker images using the same names and versions provided by K2view to ensure compatibility.
- Ensure that you configure your Helm `values.yaml` files with the correct image repository path and tag.

Once the registry is populated, you must share the full image paths (e.g., `gcr.io/project-id/k2view/fabric:8.2.1_40`) with your K2view representative to complete environment setup.

- Populate from K2view Nexus:
  - `fabric`, `fabric-studio`, `k2-cloud-deployer`
- Push images to your OCI-compliant registry (e.g., ACR)
- Share image locations with K2view for project setup

### NGINX Ingress Controller

K2view uses NGINX as the default Ingress Controller for routing external traffic to services inside the Kubernetes cluster. This controller provides a flexible, production-grade entry point that can handle TLS termination, path-based routing, rate limiting, and custom annotations for fine-grained access control.

#### Deployment Considerations:
- Deployed using Helm and runs within its namespace (typically `ingress-nginx`).
- Configured to support HTTPS, with TLS certificates passed via Kubernetes secrets.
- Provides load balancing and reverse proxy functionality for accessing services such as Fabric, TDM, and Studio externally.
- Compatible with both subdomain-based and context-based URL routing schemes.
- Custom annotations may be required depending on the cloud platform (e.g., internal vs. public load balancer annotations in Azure or AWS).

#### Cloud Alternatives:
While NGINX is the default, some environments may choose native cloud ingress solutions for deeper integration:
- **AWS**: AWS ALB Ingress Controller
- **Azure**: Azure Application Gateway Ingress Controller
- **GCP**: Google Cloud Load Balancing with GKE Ingress

These alternatives may simplify integration with native services such as identity management or centralized certificate storage, but may offer less flexibility than NGINX.

## Installation 

K2view would like to help you through the installation to help you overcome various issues. Good planning goes a long way to ensuring that the installation goes smoothly and quickly. Being ready having your certificate will help a lot make this a reality. 

### TLS Certificate Requirements

Here's a summary of the certificate requirements:

* **Format**: Must be in PEM format.
* **Full Chain**: The certificate must include the full Certificate Authority (CA) chain (e.g., a fullchain.pem file).
* **Private Key**: An associated PEM-encoded private key must be provided.
* **Domain Match**: The certificate must match the Fully Qualified Domain Name (FQDN) configured for the Kubernetes Ingress Controller.
* **Base64 Encoding**: Both the certificate and private key must be base64-encoded if required by the automation tooling (e.g., Terraform or Helm charts).
* **Wildcard or Context-Based Support**: If you're using subdomain-based access (e.g., *.domain.com), a wildcard certificate is required. For context-based URL routing (available in Fabric 8.2+), a standard single-domain certificate is sufficient and recommended

### Installation with Terraform 

Please refer to [K2view Terraform Blueprints](https://github.com/k2view/blueprints/tree/main/Terraform).

K2view provides Terraform blueprints to automate the provisioning of cloud infrastructure required for hosting Fabric and TDM services. This infrastructure-as-code approach ensures a consistent, repeatable, and secure setup of services across environments.

#### Steps:

1. **Clone K2view Blueprints**
   - Clone the GitHub repository using:
     ```bash
     git clone https://github.com/k2view/blueprints.git
     ```
   - Navigate to the `Terraform` directory corresponding to your cloud provider (e.g., `azure-template`, `aws-template`, or `gcp-template`).

2. **Modify `terraform.tfvars`**
   - Edit the `terraform.tfvars` file to define key parameters such as:
     - Resource group or project ID
     - Region and availability zones
     - DNS zone name
     - Cluster name
     - Container registry settings
     - Mailbox ID and site name
     - Node pool sizing
   - Additional variables such as virtual network CIDR blocks and TLS paths may be required depending on your configuration.

3. **Initialize and Apply the Terraform Plan**
   - Initialize the working directory and install provider plugins:
     ```bash
     terraform init
     ```
   - Apply the configuration to provision infrastructure:
     ```bash
     terraform apply
     ```
   - Monitor the output for success messages and review any prompts to approve actions.

4. **Review Terraform Output**
   - Upon completion, Terraform will output values such as:
     - Container registry login URL
     - DNS zone and ingress FQDN
     - Kubernetes cluster credentials or context info
   - These outputs are used in subsequent Helm deployments and for sharing information with K2view (e.g., registry locations, domain settings).

5. **Next Steps**
   - After Terraform is complete, validate your environment setup (e.g., confirm cluster availability with `kubectl` and test the DNS resolution).
   - Proceed to populate your container registry and deploy Helm-based applications.

Using Terraform, organizations gain improved visibility, compliance, and manageability of their infrastructure lifecycle.


### Installation with Helm 

Please refer to [K2view Helm Blueprints](https://github.com/k2view/blueprints/tree/main/helm).

This guide outlines the step-by-step process to deploy K2view components using Helm charts. It assumes that you have a running Kubernetes cluster and have cloned the [K2view Helm blueprints repository](https://github.com/k2view/blueprints/tree/main/helm).

For detailed configurations and advanced deployment scenarios, refer to the individual README files in the [K2view Helm blueprints repository](https://github.com/k2view/blueprints/tree/main/helm).

#### Prerequisites

- **Kubernetes Cluster**: Ensure you can access a Kubernetes cluster.
- **Helm**: Install Helm version 3.x. [Installation Guide](https://helm.sh/docs/intro/install/)
- **Docker Registry Access**: Credentials to access K2view's Docker registry or your private registry.
- **TLS Certificates**: Valid TLS certificates for securing ingress traffic.
- **Mailbox ID**: Provided by K2view for agent configuration.


#### Deploying NGINX Ingress Controller

K2view provides a customized Helm chart for deploying the NGINX Ingress Controller.

```bash
helm install ingress-nginx ./ingress-nginx-k2v \
  --namespace ingress-nginx \
  --create-namespace \
  -f ingress-nginx-k2v/values.yaml
````

> **Note**: Customize `values.yaml` to suit your cloud provider's load balancer settings and TLS configurations.

#### TLS Certificate Installation for K2view Deployment

The TLS certificate used to secure external HTTPS access to K2view Fabric and Studio is installed on the **Ingress Controller**, typically the **NGINX Ingress Controller**.

##### Component and Location

- **Component**: `ingress-nginx` (deployed via the `ingress-nginx-k2v` Helm chart)
- **Kubernetes Resource**: TLS certificate is stored in a Kubernetes `Secret` of type `kubernetes.io/tls`
- **Namespace**: Typically the same as the ingress controller (e.g., `ingress-nginx`)
- **Referenced By**: The `Ingress` resource or Helm `values.yaml` under the `controller.extraArgs.default-ssl-certificate` setting

##### Step-by-Step Instructions

1. **Create a TLS Secret**

Replace the certificate and key paths with your own, and adjust the namespace if different:

```bash
kubectl create secret tls fabric-tls-cert \
  --cert=/path/to/fullchain.pem \
  --key=/path/to/privkey.pem \
  -n ingress-nginx
````

2. **Configure the Ingress Controller (`values.yaml`)**

Update the `values.yaml` file used with the `ingress-nginx-k2v` Helm chart to reference the TLS secret:

```yaml
controller:
  ingressClass: nginx
  service:
    annotations:
      service.beta.kubernetes.io/aws-load-balancer-type: "nlb"  # if applicable
  extraArgs:
    default-ssl-certificate: ingress-nginx/fabric-tls-cert
```

3. **Ensure the Ingress Resources Use HTTPS**

In your Fabric or Studio `Ingress` resources (usually templated through Helm), verify that TLS is enabled and the host matches the certificate:

```yaml
ingress:
  enabled: true
  hosts:
    - host: fabric.example.com
      paths: [/]
  tls:
    - secretName: fabric-tls-cert
      hosts:
        - fabric.example.com
```

With this setup, the ingress controller will terminate TLS traffic using the provided certificate and securely route requests to K2view services within the cluster.

#### Deploying Generic Database (PostgreSQL)

For development or testing environments, you can deploy a PostgreSQL database using the provided `generic-db` chart.

```bash
helm install generic-db ./generic-db \
  --namespace k2view \
  --create-namespace \
  -f generic-db/values.yaml
```

> **Important**: For production environments, it's recommended to use a managed PostgreSQL service provided by your cloud provider.



#### Deploying K2view Agent

The K2view Agent facilitates communication between your Kubernetes cluster and the K2cloud Orchestrator.

```bash
helm install k2view-agent ./k2view-agent \
  --namespace k2view-agent \
  --create-namespace \
  -f k2view-agent/values.yaml
```

Ensure that your `values.yaml` includes the correct `MAILBOX_ID` and Docker registry credentials if pulling images from a private registry.



#### Deploying K2view Fabric

Deploy the core Fabric services using the Fabric Helm chart.

```bash
helm install fabric ./fabric \
  --namespace k2view \
  --create-namespace \
  -f fabric/values.yaml
```

Customize the `fabric/values.yaml` file to configure:

* Docker image repository and tags.
* Ingress settings (hostnames, TLS secrets).
* Environment variables specific to your deployment.
* Resource requests and limits.



#### Verify Deployments

After deploying all components, verify that all pods are running as expected:

```bash
kubectl get pods -n ingress-nginx
kubectl get pods -n k2view
kubectl get pods -n k2view-agent
```

Check the services and ingress resources to ensure they're correctly configured:

```bash
kubectl get svc -n ingress-nginx
kubectl get svc -n k2view
kubectl get ingress -n k2view
```


#### Access Fabric Web Studio

Once all services are up and running, access the Fabric Web Studio using the configured ingress hostname. Ensure that your DNS records point to the ingress controller's external IP and that TLS certificates are correctly set up.


#### Post-Installation Steps

* **Create Projects and Spaces**: Use the K2cloud Orchestrator to create and manage projects and spaces.
* **Monitor Logs**: Monitor the logs of each component to ensure they're functioning correctly.
* **Backup Configurations**: Regularly back up your Helm `values.yaml` files and Kubernetes manifests.







