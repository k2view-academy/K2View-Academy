# Fabric and TDM Services - Deployment Overview

## Synopsis

The **K2view Fabric Service Deployment Options** document provides an overview of the different ways K2view Fabric and TDM (Test Data Management) services can be deployed to meet varying customer infrastructure, security, and management needs. It describes multiple deployment models, the components involved, environment types, responsibilities, and configurations.

Some of the main points:

* **Deployment Models**

  * **SaaS-Managed (on K2cloud)**: K2view fully hosts, manages, secures, and upgrades the environment. Data and environment are segregated per customer.
  * **Self-Managed**: Customers host and manage Fabric/TDM services on cloud providers or on-premises infrastructure. This can be using Kubernetes, VMs, or serverless technologies.
  * **Hybrid**: A mix of on-premises and cloud deployment, letting customers distribute services according to data locality, compliance, or performance, while some orchestration is managed by K2cloud.

* **Components & Tooling**

  * **K2cloud Orchestration Platform** handles provisioning, monitoring, creation of “spaces”, identity federation / SSO, upgrades, and lifecycle operations.
  * **Kubernetes (K8s) Clusters** are used in many models (both cloud and on-prem).
  * **Git Repositories** for source control, deployment, and versioning of services and configurations.

* **Environments**

  * Typical segregation: Development (Dev), SIT / Staging, Production.
  * Also “Studio” environments for developers (which may use Docker Compose, Podman, or Kubernetes depending on deployment).

* **Security & Connectivity**

  * TLS / HTTPS certificates.
  * For SaaS-managed models: VPC peering, VPN, or IP whitelisting as needed.
  * Data source/target integration is always required, and responsibility may differ depending on the deployment model.

* **Responsibilities / What’s Managed by Whom**

  * In SaaS-managed scenarios: K2view handles provisioning, upgrades, monitoring, orchestration, identity federation, etc.
  * In self-managed or customer-hosted setups: customer handles installation, environment provisioning, upgrades, SSO config, securing connectivity, and integrating data sources/targets.

* **Uniformity Across Models**

  * Regardless of model (SaaS, self-managed, hybrid), the behavior of services, lifecycle, monitoring, and infrastructure expectations aim to be consistent.


## Links

<ul>
  <li><a href="/articles/44_administration_and_operations/Installation/K2view_Fabric_Service_Deployment_Options.pdf">K2view Fabric Service Deployment Options</a>
  </li>
</ul>

## Keywords

Deployment Options, K2view Fabric, TDM Services, SaaS Managed, Self-Managed, Hybrid Deployment, Kubernetes, K2cloud Orchestration, On-Premises, Cloud Environments, Git Repository, Identity Federation, Single Sign-On, TLS HTTPS, Dev SIT Staging Production, Digital Studio, Monitoring, Security, Connectivity, Environment Isolation

