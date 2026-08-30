# K2cloud SaaS Overview

## Overview

K2cloud SaaS is the fully managed K2cloud deployment model.

K2view operates the K2cloud SaaS platform and the Kubernetes-based runtime infrastructure used to host customer Spaces. Customers use K2cloud Orchestrator to manage their K2view implementations without directly operating the underlying Kubernetes infrastructure.

The basic model is:

```text
K2view operates the platform and runtime infrastructure
        ↓
Customer manages Projects and Spaces
        ↓
Customer deploys environments and Project content
        ↓
Customer operates and validates its K2view applications
```

K2cloud Orchestrator provides the SaaS control plane through which customers manage the lifecycle of their K2view environments.

## What K2cloud SaaS Manages

In K2cloud SaaS, K2view operates the infrastructure and platform services required to run K2cloud and customer Spaces.

This includes areas such as:

- Kubernetes infrastructure,
- cluster lifecycle,
- node operations,
- ingress infrastructure,
- storage orchestration,
- and the infrastructure required to provide K2cloud SaaS monitoring and observability.

Customers do not directly operate these infrastructure layers.

This is the primary distinction from [K2cloud Self-Hosted](/articles/80_k2cloud/01-overview/80_k2cloud_overview_k2cloud_saas_vs_self_hosted.md), where the customer operates the runtime Kubernetes infrastructure.

## What Customers Manage

K2cloud SaaS does not eliminate customer operational responsibility for the K2view implementation.

Depending on their responsibilities and authorization, customers use K2cloud and the applications within their Spaces to:

- create and configure Projects,
- create and manage Spaces,
- develop K2view applications,
- manage environment configuration,
- deploy environments,
- activate environments,
- deploy Project content,
- manage application access,
- validate deployments,
- and operate their K2view applications.

The distinction is that these activities are performed through K2cloud and K2view application interfaces rather than by directly operating the underlying Kubernetes infrastructure.

## Space-Centric Operations

Customer operations remain centered around Spaces.

A Space is the deployed K2view environment in which Studio, Fabric, TDM, and other applicable K2view capabilities operate.

Customers interact with their environments through interfaces such as:

- the K2cloud Orchestrator console,
- Fabric Web Studio,
- Fabric Admin,
- TDM,
- application and API endpoints,
- Space Details,
- and K2cloud SaaS monitoring and log capabilities.

K2view manages the infrastructure on which those Spaces run.

## Why Use K2cloud SaaS

K2cloud SaaS is appropriate for organizations that want K2view to operate the underlying platform and runtime infrastructure while retaining control of their K2view implementation and application lifecycle.

This reduces the need for customers to operate:

- Kubernetes clusters,
- cluster infrastructure,
- ingress infrastructure,
- node lifecycle,
- and the associated platform observability infrastructure.

At the same time, customers retain direct operational control of their Projects, Spaces, deployments, application configuration, and runtime validation through K2cloud.

## Related Documentation

- [K2cloud SaaS vs Self-Hosted](/articles/80_k2cloud/01-overview/80_k2cloud_overview_k2cloud_saas_vs_self_hosted.md)
- [K2cloud Spaces](/articles/80_k2cloud/06-spaces/README.md)
- [K2cloud Deployments and Lifecycle](/articles/80_k2cloud/07-deployments-lifecycle/README.md)
- [K2cloud Identity and Access](/articles/80_k2cloud/08-identity-and-access/README.md)
- [SaaS Operational Model](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_operational_model.md)