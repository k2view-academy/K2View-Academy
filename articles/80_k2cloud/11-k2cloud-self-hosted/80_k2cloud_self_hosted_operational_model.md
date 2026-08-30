# Self-Hosted Operational Model

## Overview

K2cloud Self-Hosted separates responsibility for the runtime infrastructure from responsibility for the K2cloud control plane.

In this model:

- **K2view operates the K2cloud Orchestrator as a SaaS service.**
- **The customer operates the Kubernetes infrastructure and supporting services where Spaces run.**
- **K2cloud provides the Fabric-aware orchestration layer used to deploy and manage Spaces within that infrastructure.**
- **The customer manages its K2view implementation, deployments, configuration, and runtime access.**

This allows customers to retain control of their infrastructure while using K2cloud for centralized Space lifecycle and deployment operations.

## Responsibility Boundary

The primary operational responsibilities are divided as follows:

<table>
<thead>
<tr>
<th>Area</th>
<th>Responsibility</th>
</tr>
</thead>
<tbody>
<tr>
<td>K2cloud Orchestrator</td>
<td>K2view</td>
</tr>
<tr>
<td>Kubernetes infrastructure</td>
<td>Customer</td>
</tr>
<tr>
<td>Infrastructure capacity</td>
<td>Customer</td>
</tr>
<tr>
<td>Ingress and networking</td>
<td>Customer</td>
</tr>
<tr>
<td>DNS and certificates</td>
<td>Customer</td>
</tr>
<tr>
<td>Infrastructure identity and access</td>
<td>Customer</td>
</tr>
<tr>
<td>Storage infrastructure</td>
<td>Customer</td>
</tr>
<tr>
<td>Container registry integration</td>
<td>Customer</td>
</tr>
<tr>
<td>Infrastructure monitoring, logging, and alerting</td>
<td>Customer</td>
</tr>
<tr>
<td>Projects and source control</td>
<td>Customer</td>
</tr>
<tr>
<td>Space Profiles and Fabric Image selection</td>
<td>Customer</td>
</tr>
<tr>
<td>Space lifecycle operations</td>
<td>Customer through K2cloud</td>
</tr>
<tr>
<td>Project and environment deployments</td>
<td>Customer through K2cloud</td>
</tr>
<tr>
<td>Runtime authorization</td>
<td>Customer</td>
</tr>
<tr>
<td>K2view application configuration and validation</td>
<td>Customer</td>
</tr>
</tbody>
</table>

This boundary is fundamental to the Self-Hosted model: the customer operates the infrastructure, while K2cloud provides the application-aware control plane for managing K2view Spaces on that infrastructure.

## Infrastructure Operations

The customer is responsible for operating the Kubernetes environment and the infrastructure services required by the K2cloud Site.

This includes maintaining sufficient infrastructure capacity and availability for the Spaces deployed to the Site.

Most Self-Hosted customers use cloud-provider-managed Kubernetes services such as Amazon EKS, Azure AKS, or Google GKE. Although the cloud provider operates portions of the Kubernetes service, the customer remains responsible for its Kubernetes environment and the associated infrastructure configuration.

K2cloud does not replace the customer's infrastructure-management responsibilities.

Instead, it operates above Kubernetes:

> **Kubernetes manages containers and infrastructure resources. K2cloud manages K2view Fabric environments.**

## Space Operations

Project Managers and Space Owners perform supported Space lifecycle operations through the K2cloud Orchestrator rather than directly manipulating Fabric deployments through Kubernetes.

Depending on the type of Space, these operations can include:

- creating Spaces,
- deleting Spaces,
- restarting Fabric Spaces,
- pausing and resuming Studio Spaces,
- deploying environments,
- deploying Project content,
- reviewing Space status,
- inspecting Kubernetes runtime diagnostics,
- and initiating supported Fabric or Studio upgrades and rollbacks.

K2cloud translates these application-level operations into the required actions within the customer-managed Kubernetes environment.

## Space Profiles and Runtime Topology

Space Profiles define the deployment topology, resources, and runtime configuration used when creating Spaces.

The customer is responsible for ensuring that the underlying Site has sufficient infrastructure capacity to support the selected profiles and the workloads deployed to it.

K2cloud uses the selected Space Profile to manage the K2view runtime topology. Customers therefore work with the K2cloud Space model rather than manually constructing and managing the corresponding Fabric runtime topology in Kubernetes.

For more information, see [Sites and Space Profiles in Self-Hosted Environments](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_sites_and_space_profiles.md).

## Deployment Operations

The deployment model remains consistent with the standard K2cloud workflow.

Customers use Git as the source for versioned Project and environment content and use K2cloud to deploy that content to Fabric Spaces.

The basic lifecycle remains:

    Git
      ↓
    Deploy Environment
      ↓
    Activate Environment
      ↓
    Deploy Project
      ↓
    Runtime Validation

The customer owns the implementation and decides what and when to deploy. K2cloud provides the deployment workflow and performs the corresponding orchestration against the target Space.

For more information, see [Deployments and Lifecycle Overview](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_overview.md).

## Monitoring and Diagnostics

Infrastructure observability is a customer responsibility in K2cloud Self-Hosted.

Customers use their own monitoring, centralized logging, alerting, security monitoring, and SIEM capabilities for the infrastructure and runtime environment.

The K2cloud SaaS Metrics and Logs monitoring components are not provided for Self-Hosted Spaces.

K2cloud does provide Kubernetes diagnostics through **Space Details**, allowing authorized users to inspect information such as:

- pods,
- pod status,
- pod details,
- pod logs,
- resource information,
- restart information,
- and Kubernetes events.

This provides useful Space-level Kubernetes diagnostics while leaving infrastructure-wide observability with the customer.

For more information, see [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md).

## Centralized Orchestration

The defining characteristic of K2cloud Self-Hosted is that customer-owned infrastructure remains connected to the centralized K2cloud Orchestrator.

The customer operates the runtime infrastructure.

K2view operates the K2cloud Orchestrator.

K2cloud provides the Fabric-aware lifecycle and deployment abstraction between them.

As a result, customers retain infrastructure control without having to create their own orchestration model for routine K2view Space operations.

This distinguishes K2cloud Self-Hosted from a Fabric air-gapped deployment, where there is no dependency on the K2cloud Orchestrator control plane and the customer assumes responsibility for the corresponding orchestration and lifecycle processes.

## Related Documentation

- [K2cloud Self-Hosted Overview](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_overview.md)
- [Sites and Space Profiles in Self-Hosted Environments](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_sites_and_space_profiles.md)
- [Self-Hosted Runtime Operations](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_runtime_operations.md)
- [Self-Hosted Customer Responsibilities](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_customer_responsibilities.md)
- [Self-Hosted Observability and Support](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_observability_and_support.md)
- [Shared Responsibility Model](/articles/80_k2cloud/09-common-operations/80_k2cloud_common_operations_shared_responsibility_model.md)
