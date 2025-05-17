# Cloud Self-Hosted Kubernetes Cluster Installation

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

* **Fabric Server**: The core runtime engine that powers the execution of data products and orchestrates the entire runtime lifecycle of services, processes, data access, and transformation logic within the platform. It supports concurrently executing multiple data services and interfaces, handles real-time data flows, and enables integration with external systems through configurable adapters. Fabric Server is highly scalable, designed to run as containerized workloads within a Kubernetes cluster, and supports stateful and stateless services. In production environments, it leverages Kubernetes-native features such as auto-scaling, persistent volume claims, and node affinity rules to deliver performance, fault tolerance, and operational flexibility.
* **TDM** (Test Data Management): A powerful, optional extension to Fabric used to generate, mask, and provision test data across distributed environments. TDM enables creating realistic, privacy-compliant test data by extracting and transforming production data or generating fully synthetic datasets. It supports use cases such as test data provisioning, PII masking, subsetting, and referential integrity maintenance. TDM can integrate with multiple source systems and is optimized for performance and scalability when deployed in Kubernetes environments. It is particularly useful in regulated industries that require compliant data handling in non-production environments.
* **Fabric Web Studio**: A comprehensive web-based UI for managing, developing, and deploying data products within the K2view platform. It provides visual tools for configuring interfaces, managing business logic, testing data flows, monitoring service activity, and accessing documentation. Fabric Studio supports collaborative development and tightly integrates with version control systems like Git. In development environments, it includes an embedded Neo4j instance for data relationship visualization. For production environments, it typically connects to managed backend services and excludes development-only tooling to optimize performance and security.
* **K2-Agent**: Communicates with the K2cloud Orchestrator to receive deployment instructions. The K2-Agent is a lightweight Kubernetes service that securely connects your on-premises or cloud-based K2view Fabric deployment to the K2cloud Orchestrator. It is crucial in enabling centralized management, monitoring, and deployment orchestration.
* **Ingress Controller**: Handles routing external traffic to services within the cluster. K2view typically deploys an NGINX Ingress Controller as the default solution, offering a well-supported and configurable entry point across cloud and on-premises environments. For cloud-native implementations, customers may use provider-specific ingress solutions such as AWS ALB Ingress Controller, GCP Ingress, or Azure Application Gateway Ingress Controller, depending on their platform requirements, networking architecture, and load balancing needs.
* **Container Registry**: Stores and serves Docker images used during deployment.
* **Persistent Storage**: Maintaining stateful data across service restarts and rescheduling events within the Kubernetes cluster. In production environments, persistent storage must support high availability and zone redundancy to ensure data durability and fault tolerance. K2view supports cloud-native options such as Azure Files with Zone Redundant Storage (ZRS), Amazon EFS or EBS with Multi-AZ replication, and Google Filestore with regional availability. In non-production or development setups, simpler storage configurations (e.g., LRS or single-zone disks) may be used because they provide lower resilience.
* **PostgreSQL Database**: As the metadata store and persistence layer for Fabric configurations, project settings, and runtime state. In development or single-node deployments, an embedded PostgreSQL database is automatically provisioned within the cluster. For production-grade and multi-node clusters, a managed external PostgreSQL service (e.g., Azure Database for PostgreSQL) is required for resilience and scalability.
* **Embedded Neo4j Graph Database**: Bundled with Fabric Web Studio for use in development and testing scenarios. It supports metadata relationship visualization and query capabilities. In production environments, this is optional and typically not externally exposed.

## Hardware Requirements 

For platform-specific sizing guidance, the [Requirements and Prerequisites for Cloud Self-hosted Kubernetes Installation](/articles/98_maintenance_and_operational/Hardware/2_All_Environments/04_k8s_req.md) topic outlines detailed hardware specifications across AWS, GCP, and Azure environments, ensuring compatibility with Fabric and TDM workloads. It covers the following additional topics:

* **Node Requirements** - The number of nodes required depends on the intended usage (development, SIT, or production). While the document provides a general guideline, actual node count should account for redundancy, workload isolation, and autoscaling policies.
* **K8s Cluster Preparations** - Successful cluster preparation involves ensuring tool readiness (kubectl, helm, terraform, etc.), verifying outbound internet connectivity, and aligning cloud provider configurations (e.g., role assignments, resource provider registrations). The referenced guide provides practical pre-installation steps for both administrators and DevOps engineers.
* **Persistent Volumes and Storage Classes** - Fabric services rely on persistent volumes for stateful workloads. The guide explains the recommended use of high-availability storage classes such as ZRS or multi-zone volumes, depending on your cloud provider. Developers and operators should review this section to align their storage classes with cluster topology and SLA expectations.

## Preparations and Provisioning

To install a K2cloud Self-hosted Kubernetes cluster for Fabric and TDM, you will need to prepare and perform the necessary steps in coordination with your K2view representative. The process begins with gathering key configuration details, including TLS certificate files, and ensuring outbound internet access to specific K2view endpoints. These are essential for secure communications, image retrieval, and configuration via the K2cloud Orchestrator. K2view will also need to perform provisioning actions requiring information you will provide it. 

Your K2view representative provide you with access credentials and provisioning information. This includes a Cloud Mailbox ID, a K2view Nexus Repository account for pulling required Docker images, and a list of container images to populate your private registry.

K2view will share a planning guide to help you with this provisioning and coordinate activities.

### Planning and Installation Step Overview 

Steps include:
* Gather prerequisites and confirm network access
* Provision of environment and services
* Install K2view Fabric using Terraform and Helm
* Populate the container registry
* Provide container image paths and domain to K2view
* Create K2view Project and Space

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

Please refer to:([K2view Terraform Blueprints](https://github.com/k2view/blueprints/tree/main/Terraform))

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
   - Proceed to populate your container registry and perform Helm-based application deployment.

Using Terraform, organizations gain improved visibility, compliance, and manageability of their infrastructure lifecycle.


### Installation with Helm 

Please refer to: ([K2view Helm Blueprints](https://github.com/k2view/blueprints/tree/main/helm))

Once the infrastructure is provisioned and Docker images are pushed to your container registry, Helm is used to deploy the K2view Fabric and TDM services into your Kubernetes cluster.

#### Steps:

1. **Access the Helm Blueprints**
   - Navigate to the cloned `blueprints/helm` directory.
   - The folder contains individual charts for components like `fabric`, `fabric-studio`, and `k2view-agent`.

2. **Configure Helm Values**
   - Each chart includes a `values.yaml` file that must be edited to reflect your environment. Key settings include:
     - Docker image registry paths and versions
     - Ingress domain and TLS certificate configuration
     - Kubernetes namespace and service parameters
     - Persistent volume claim names or storage class overrides
     - Environment variables such as Mailbox ID, Fabric license, or Git branch/tag

3. **Install with Helm**
   - Run Helm installation commands for each component:
     ```bash
     helm install fabric ./fabric -n k2view --create-namespace -f fabric-values.yaml
     helm install studio ./fabric-studio -n k2view -f studio-values.yaml
     helm install agent ./k2view-agent -n k2view -f agent-values.yaml
     ```
   - Use `--dry-run` for preview and `--debug` for verbose output during troubleshooting.

4. **Validate Deployment**
   - Check pod and service status using:
     ```bash
     kubectl get pods -n k2view
     kubectl get svc -n k2view
     ```
   - Confirm the Ingress is correctly routing to services and TLS termination is functional.

5. **Update and Upgrade**
   - Use `helm upgrade` for redeploying changes:
     ```bash
     helm upgrade fabric ./fabric -n k2view -f fabric-values.yaml
     ```
   - Maintain version control over your `values.yaml` files to track changes across environments.

6. **Next Steps**
   - After successful deployment, log into Fabric Studio via the ingress URL.
   - Use K2cloud to create and manage Projects and Spaces as described in earlier sections.

Helm simplifies lifecycle management and enables seamless updates, rollback capabilities, and consistency across development, test, and production environments.


