# K2cloud Features & Capabilities

This article explores some of the features and capabilities that are relevant to different roles and personas within your organization.

## Business Logic Teams: Improving Productivity

Along the Fabric's project lifetime, there is a need to have various Fabric environments, to support the lifecycle of the project's implementation and Fabric released versions. 

K2cloud simplifies the operations by streamlining them in a way that implementors, QA and DevOps teams can benefit from it: Implementors and QA teams can easily (without much effort) spin up any type of environment, without the need to wait for the DevOps team to prepare it; the DevOps team does not have to be involved in this preparation stage. 
Below are a few examples of a project lifecycle and environment variations:

#### Example 1: Project changes

1. **Dev1**: Fabric 7.2, single node, Postgres, with Studio. 

1. **QA**: Fabric 7.2, single node, Postgres, *without* Studio. 
   - Upon approval, Dev1 creates a project version tag 5.
2. **Dev2** works on the next project's features: Fabric 7.2, single node, Studio, Postgres, *project branch 123*.
3. **Staging**: Fabric *cluster 2 nodes*, Fabric 7.2, *managed Postgres*, without Studio, *project tag 5*.
4. **Production**: Fabric cluster 3 *initial* nodes with *scale* up to 6, *dedicated site*, *extended CPU*, Fabric 7.2, managed Postgres, without Studio, Tag 4, *Environment E3*.

#### Example 2: Product changes

1. **QA** regression on *new Fabric* *and TDM*: Fabric 8 + TDM 9, without project implementation changes, project tag 5.

2. **Dev3**: Align project implementation for new Fabric 8.0/TDM 9, Single node, Postgres, Studio, project branch 124.

   

## DevOps Teams: Simplify Operation

The k2cloud platform is beneficial for **DevOps** teams as it provides capabilities that aim to simplify Fabric environments' operation and maintenance. 

### Orchestrating End-to-End Processes

Any of the user can achieve the following tasks, by himself, within a click:

1. **Creation** (non-dev Fabric workloads example):
   - Auto-provision managed services.
   - Configure Fabric and non-managed services appropriately.
   - Apply privileged access control settings and certificates.
   - Clone, build, and deploy the project.
2. **Redeploy**:
   - Redeploy environments or logical units (LU/s), for non-dev Fabric workloads.
3. **Pause / Resume / Delete**:
   - Manage application states efficiently.

### Using Kubernetes (K8s) as an Infrastructure

- Kubernetes: The de facto standard for enterprise container orchestration.
  - Provides an infrastructure abstraction layer for handling compute, networking, storage, and app health monitoring.
  - Ensures resilience and scalability for containerized applications.

### Aligning with Delivery Standards

- GitOps Delivery Methods:
  - Adopt Helm charts methodology for deployment.
  - Maintain consistency and version control tracking.

### Monitoring

- **Operational Monitoring**:
  - Track Kubernetes clusters and namespaces.
  - Ensure smooth operation.
- **Functional Monitoring**:
  - Collect and display Fabric metrics dashboard and logs.



## Security Teams: Empowering Security

### Securing Users’ Access

The access to either k2cloud platform UI or the spaces, is done according to privileges.

* Front IdP – CyberArk, FedRAMP Identity and access management leader.

* Enables multi-factor authentication, using various authenticators, while applying different policies by profiles. 

* Enables SSO Integration.

* Each space - Fabric workload - is reparented by its own web-app at IdP, with associated permission roles. Users can access spaces according to matched roles.

* Access monitoring and reports.

### Separation

* SaaS - each organization has its own account (managed by K2view in the cloud provider), which, in turn, has its own tenant, where the organization’s Fabric workloads are fully separated from those belonging to other organizations.

* K2cloud is a multi-tenant application, with full separation between the tenants within it, and where each organization can view and access only its own projects and spaces; the tenant’s privacy is completely protected.

### Zero Trust

* **Outbound only connectivity**: K2cloud platform does not have any access to the clusters or spaces, or any data they manage. This is achieved by that the k2agent, deployed at each K8s cluster, in its own namespace, pulls its dedicated instructions. It plays like a proxy forwarder - it initiates REST calls into the k2cloud orchestrator, and move the response into the K8s cluster control plane, as YAML files.

* **No secrets**

  * K2cloud platform does not save secrets – all needed credentials, like access to managed services, are set at the k2-agent, When you own your cloud deployment, as self hosted tenant, you are responsible to set these values,without informing or exposing it to K2view.

  * All around usage of K8s secrets modules.

* **Secure access to data platforms**: Have a single cluster output point, using NAT GW, let you easily define whitelist to the spaces in that cluster, withotut doing it per Fabric workload environment. Moreover, following K2view recommendation of having 2-3 clusters - studio, QA & Staging, and production, you can define that only production cluster will have access to specific sensitive data platform.

* Avoid vulnerabilities - Fabric images can be scanned and uploaded to the customer’s OCI repository. K2cloud orchestrator providers a simple way - via UI - to provision the images' location, where accordingly spaces will be created from the scanned images.







​              
