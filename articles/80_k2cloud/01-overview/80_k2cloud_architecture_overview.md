# Architecture Overview

## Table of Contents

- [Overview](#overview)
- [K2cloud Control Plane](#k2cloud-control-plane)
- [Runtime Environment](#runtime-environment)
- [Spaces and Runtime Services](#spaces-and-runtime-services)
- [K2cloud and Kubernetes](#k2cloud-and-kubernetes)
- [Connectivity Between the Control Plane and Runtime](#connectivity-between-the-control-plane-and-runtime)
- [Fabric Without the K2cloud Control Plane](#fabric-without-the-k2cloud-control-plane)
- [Related Documentation](#related-documentation)

## Overview

K2cloud separates the management and orchestration of K2view environments from the Kubernetes infrastructure on which Fabric runs.

The architecture consists of two primary layers:

- the **K2cloud control plane**, which provides centralized orchestration and lifecycle management, and
- the **runtime environment**, where Fabric Spaces execute on Kubernetes.

This separation allows K2cloud to provide a consistent operational model whether the runtime infrastructure is operated by K2view or by the customer.

## K2cloud Control Plane

The **K2cloud Orchestrator is a SaaS component that K2view manages and operates.**

It provides the centralized control plane used to manage Projects and the lifecycle of Spaces.

Through the K2cloud Orchestrator, authorized users can perform activities such as:

- creating and managing Projects,
- creating Spaces,
- selecting Space Profiles, Fabric Images, and Sites,
- managing Space lifecycle operations,
- deploying application environments and projects,
- performing supported Fabric upgrades,
- viewing Space status and operational information,
- and accessing deployed Studio and Fabric environments.

The K2cloud Orchestrator therefore provides an application-aware management layer above the underlying Kubernetes infrastructure.

## Runtime Environment

The runtime environment is the Kubernetes infrastructure on which K2view Spaces execute.

A Space is deployed to a **Site**, which represents a runtime deployment target available to K2cloud.

With **K2cloud SaaS**, K2view manages and operates the Kubernetes infrastructure.

With **K2cloud Self-Hosted**, the customer manages and operates the Kubernetes infrastructure. Most Self-Hosted deployments use a managed Kubernetes service from a hyperscaler, such as:

- Amazon Elastic Kubernetes Service (EKS),
- Azure Kubernetes Service (AKS), or
- Google Kubernetes Engine (GKE).

In either K2cloud deployment model, the K2cloud Orchestrator provides the centralized lifecycle management for the Fabric environments running on that infrastructure.

## Spaces and Runtime Services

A **Space** is the primary runtime environment managed by K2cloud.

Depending on its purpose and Space Profile, a Space can contain different runtime services.

A Studio development Space typically includes:

- Fabric,
- Fabric Studio, and
- PostgreSQL.

A Fabric runtime Space provides the Fabric runtime. Depending on the Space Profile and deployment architecture, services such as the system database and blob storage can be external to the Space.

Kubernetes provides the underlying container orchestration, scheduling, networking, and resource management for these services.

K2cloud manages the Fabric environment as a Space rather than requiring users to manage its individual Kubernetes components as the primary operational model.

## K2cloud and Kubernetes

Kubernetes and K2cloud serve different purposes.

**Kubernetes manages containers and infrastructure resources. K2cloud manages K2view Fabric environments.**

K2cloud understands K2view-specific concepts such as:

- Projects,
- Space Profiles,
- Fabric Images,
- Sites,
- Spaces,
- application deployments,
- and Fabric lifecycle operations.

This provides an operational layer above Kubernetes through which K2view environments can be provisioned and managed without making Kubernetes itself the primary interface for routine Fabric lifecycle operations.

In K2cloud Self-Hosted environments, the customer continues to operate and monitor its Kubernetes infrastructure while K2cloud manages the Fabric lifecycle running on that infrastructure.

## Connectivity Between the Control Plane and Runtime

K2cloud Self-Hosted requires connectivity between the K2view-managed K2cloud control plane and the customer-managed runtime environment.

This connectivity enables K2cloud to manage Spaces deployed to the customer Kubernetes infrastructure.

The customer remains responsible for configuring the network, security, and infrastructure controls required for its environment while maintaining the connectivity required by the K2cloud operating model.

Detailed connectivity and infrastructure requirements are covered in the K2cloud Self-Hosted documentation.

## Fabric Without the K2cloud Control Plane

Fabric can also be deployed on Kubernetes without using K2cloud, including in air-gapped environments.

In this architecture, there is no dependency on the K2cloud Orchestrator.

Kubernetes continues to provide container orchestration, but the K2cloud application-aware management layer is absent. The customer must therefore provide the processes and automation required to deploy, upgrade, and manage the Fabric runtime lifecycle.

This is an architectural distinction between **K2cloud Self-Hosted**, where the customer operates Kubernetes while K2cloud manages the Fabric lifecycle, and an **air-gapped deployment without K2cloud**, where the customer assumes responsibility for both.

## Related Documentation

- [Operational Model Overview](/articles/80_k2cloud/01-overview/80_k2cloud_operational_model_overview.md)
- [Deployment Models](/articles/80_k2cloud/01-overview/80_k2cloud_overview_deployment_models.md)
- [Shared Responsibility Model](/articles/80_k2cloud/01-overview/80_k2cloud_overview_shared_responsibility_model.md)
- [K2cloud Self-Hosted Overview](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_overview.md)
- [Air-Gapped Overview](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_overview.md)