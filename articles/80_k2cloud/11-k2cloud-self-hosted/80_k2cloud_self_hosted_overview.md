# K2cloud Self-Hosted Overview

## Overview

K2cloud Self-Hosted allows customers to run K2view Fabric Spaces within customer-managed Kubernetes infrastructure while continuing to use the K2cloud Orchestrator as the centralized control plane for managing those Spaces.

In this model:

- **K2view operates the K2cloud Orchestrator as a SaaS service.**
- **The customer operates the Kubernetes infrastructure where Spaces run.**
- **K2cloud manages the deployment and lifecycle of K2view Fabric environments within that infrastructure.**

This provides customers with control over their runtime infrastructure while retaining the application-aware orchestration and lifecycle capabilities provided by K2cloud.

## K2cloud Self-Hosted Architecture

A Self-Hosted deployment separates the K2cloud control plane from the runtime infrastructure.

    K2cloud Orchestrator
    K2view-managed SaaS control plane
              │
              │
              ▼
       K2cloud Agent / Site
              │
              ▼
    Customer-managed Kubernetes
              │
              ▼
         K2view Spaces

The K2cloud Orchestrator is not installed or operated within the customer's Kubernetes environment. It remains a K2view-managed SaaS component.

The customer-managed environment provides the infrastructure on which the Spaces run.

## Kubernetes and K2cloud

Most K2cloud Self-Hosted customers deploy their runtime environments using cloud-provider-managed Kubernetes services, such as:

- Amazon Elastic Kubernetes Service (EKS),
- Azure Kubernetes Service (AKS),
- or Google Kubernetes Engine (GKE).

Customer-managed Kubernetes environments can also be used where appropriate.

The responsibilities of Kubernetes and K2cloud are different:

> **Kubernetes manages containers and infrastructure resources. K2cloud manages K2view Fabric environments.**

The customer remains responsible for operating its Kubernetes environment and the supporting infrastructure. K2cloud provides the Fabric-aware orchestration layer used to create and operate Spaces within that environment.

This allows Project Managers and Space Owners to work with K2cloud concepts such as Projects, Sites, Space Profiles, Fabric Images, and Spaces rather than directly managing Fabric deployments through Kubernetes.

## Why Organizations Choose Self-Hosted

Organizations typically choose K2cloud Self-Hosted when they require greater control over where and how their runtime infrastructure operates.

This can include requirements for:

- customer-owned cloud infrastructure,
- private networking,
- private ingress,
- customer-managed DNS and certificates,
- regional or data-residency placement,
- customer-controlled identity and access to infrastructure resources,
- customer-managed storage,
- integration with existing infrastructure services,
- or corporate infrastructure and security governance.

Self-Hosted provides this infrastructure control without requiring the customer to build its own orchestration layer for managing K2view Spaces.

## Runtime Placement

Spaces execute within the Kubernetes infrastructure associated with a K2cloud **Site**.

The Site defines the infrastructure boundary and connectivity required for K2cloud to deploy and operate Spaces in that environment.

The customer is responsible for the underlying infrastructure, including areas such as:

- Kubernetes,
- networking,
- ingress infrastructure,
- DNS and certificates,
- infrastructure identity and access,
- storage infrastructure,
- container registry integration,
- infrastructure capacity,
- and infrastructure observability.

K2cloud operates above this infrastructure layer and manages the K2view Space lifecycle.

For more information, see [Sites Overview](/articles/80_k2cloud/05-sites/80_k2cloud_sites_overview.md).

## The K2cloud Operational Model

Once the Self-Hosted infrastructure and Site are configured, the K2cloud operational model remains centered on the same core resources used elsewhere in K2cloud:

**Project + Space Profile + Fabric Image + Site → Space**

The **Project** identifies the K2view implementation and its source repository.

The **Space Profile** defines the deployment topology and runtime configuration.

The **Fabric Image** identifies the Fabric or Fabric-Studio runtime version.

The **Site** identifies where the Space will run.

K2cloud combines these resources to create and manage the Space within the customer's infrastructure.

Customers can then use K2cloud workflows for operations such as:

- creating and deleting Spaces,
- deploying Project content,
- deploying environment definitions,
- restarting Fabric Spaces,
- pausing and resuming Studio Spaces,
- inspecting Kubernetes runtime diagnostics,
- and performing supported Fabric and Studio version upgrades and rollbacks.

The customer operates the runtime infrastructure; it does not have to build and operate its own orchestration layer for that infrastructure.

## Self-Hosted Observability

Because the runtime infrastructure belongs to the customer, infrastructure monitoring and observability are also customer responsibilities.

Self-Hosted customers typically integrate the environment with their existing:

- infrastructure monitoring,
- centralized logging,
- alerting,
- security monitoring,
- and SIEM platforms.

The SaaS monitoring components available to K2cloud SaaS customers are not provided for Self-Hosted Spaces.

K2cloud does, however, provide Kubernetes runtime diagnostics through **Space Details**, including pod information, pod logs, and Kubernetes events.

For more information, see [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md).

## Shared Responsibility

K2cloud Self-Hosted creates a clear operational boundary:

- **K2view** operates the K2cloud Orchestrator SaaS control plane.
- **The customer** operates the Kubernetes runtime infrastructure and its supporting services.
- **K2cloud** provides the Fabric-aware orchestration and lifecycle layer used to manage Spaces within that infrastructure.
- **The customer** remains responsible for its K2view implementation, deployments, configuration, access design, and runtime validation.

The following articles describe this responsibility boundary and the operational model in more detail.

## Related Documentation

- [Self-Hosted Operational Model](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_operational_model.md)
- [Sites and Space Profiles in Self-Hosted Environments](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_sites_and_space_profiles.md)
- [Self-Hosted Runtime Operations](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_runtime_operations.md)
- [Self-Hosted Customer Responsibilities](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_customer_responsibilities.md)
- [Self-Hosted Observability and Support](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_observability_and_support.md)
- [Shared Responsibility Model](/articles/80_k2cloud/09-common-operations/80_k2cloud_common_operations_shared_responsibility_model.md)
