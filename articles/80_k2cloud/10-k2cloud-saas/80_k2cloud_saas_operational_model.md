# SaaS Operational Model

## Table of Contents

- [Overview](#overview)
- [Operational Responsibility](#operational-responsibility)
- [Infrastructure Operations](#infrastructure-operations)
- [Space Operations](#space-operations)
- [Fabric and Studio Version Lifecycle](#fabric-and-studio-version-lifecycle)
- [Deployment Operations](#deployment-operations)
- [Monitoring and Diagnostics](#monitoring-and-diagnostics)
- [Operational Escalation](#operational-escalation)
- [Related Documentation](#related-documentation)

## Overview

K2cloud SaaS separates responsibility for operating the K2cloud platform and runtime infrastructure from responsibility for operating the customer's K2view implementation.

At a high level:

```text
K2view
    ↓
Operates K2cloud and the runtime infrastructure

Customer
    ↓
Manages the K2view implementation through K2cloud
```

K2view operates the Kubernetes-based platform on which customer Spaces run. Customers manage their Projects, Spaces, application configuration, deployments, access, and runtime validation through K2cloud and the applications within their Spaces.

## Operational Responsibility

The primary responsibility boundary is:

<table>
<thead>
<tr>
<th>Area</th>
<th>K2view</th>
<th>Customer</th>
</tr>
</thead>
<tbody>
<tr>
<td>K2cloud Orchestrator</td>
<td>Operates and maintains the SaaS control plane</td>
<td>Uses the Orchestrator to manage authorized Projects and Spaces</td>
</tr>
<tr>
<td>Kubernetes infrastructure</td>
<td>Operates clusters, nodes, and supporting infrastructure</td>
<td>No direct infrastructure operation</td>
</tr>
<tr>
<td>Spaces</td>
<td>Operates the infrastructure on which Spaces run</td>
<td>Creates and manages Spaces through K2cloud</td>
</tr>
<tr>
<td>Projects and application configuration</td>
<td>Operates the supporting platform</td>
<td>Develops and manages the K2view implementation</td>
</tr>
<tr>
<td>Git</td>
<td>Uses configured Project repositories as part of K2cloud deployment workflows</td>
<td>Owns repository content, access, and source-control practices</td>
</tr>
<tr>
<td>Deployments</td>
<td>Operates the platform used to perform deployments</td>
<td>Selects approved versions and deploys environment and Project content</td>
</tr>
<tr>
<td>Fabric and Studio images</td>
<td>Publishes and distributes available images through K2cloud</td>
<td>Selects approved versions and initiates Space upgrades and rollbacks</td>
</tr>
<tr>
<td>Identity federation</td>
<td>Operates the K2cloud federation service and configures required mappings</td>
<td>Manages enterprise identities, IdP groups, and customer-side federation configuration</td>
</tr>
<tr>
<td>Runtime authorization</td>
<td>Operates the platform</td>
<td>Defines and manages required Fabric and TDM permissions</td>
</tr>
<tr>
<td>SaaS monitoring infrastructure</td>
<td>Operates the monitoring and logging platform</td>
<td>Uses the available K2cloud monitoring and log capabilities for its Spaces</td>
</tr>
</tbody>
</table>

For the broader K2cloud responsibility model, see [Shared Responsibility Model](/articles/80_k2cloud/09-common-operations/80_k2cloud_common_operations_shared_responsibility_model.md).


## Infrastructure Operations

K2view is responsible for operating the infrastructure underlying K2cloud SaaS.

Customers therefore do not directly manage:

- Kubernetes clusters,
- Kubernetes nodes,
- cluster lifecycle,
- ingress infrastructure,
- or the infrastructure supporting K2cloud SaaS observability.

This removes the need for customer operations teams to manage the Kubernetes platform directly.

It does not remove the customer's responsibility for its K2view implementation and application operations.

## Space Operations

Authorized customers manage the lifecycle of their Spaces through K2cloud Orchestrator.

Depending on the Space type, these operations can include:

- creating Spaces,
- reviewing Space status,
- pausing and resuming Studio Spaces,
- restarting Fabric Spaces,
- deploying environments,
- deploying Project content,
- reviewing Space Details,
- upgrading or rolling back Fabric and Studio versions,
- and deleting Spaces.

K2view operates the infrastructure required to execute these operations. The customer determines when the corresponding application-level operation should be performed.

## Fabric and Studio Version Lifecycle

K2view manages publication and distribution of Fabric and Studio images for K2cloud SaaS.

Available Fabric Images are published to Projects and can be selected for the applicable Space Profiles.

The operational lifecycle is:

```text
K2view publishes the Fabric Image
        ↓
Project Manager selects the approved version
        ↓
Eligible Space identifies the available upgrade
        ↓
Authorized Space Owner initiates the upgrade
        ↓
Customer validates the application
```

This preserves the SaaS responsibility boundary:

- K2view manages image publication and distribution.
- Customers control when their Spaces move to an available version and validate the resulting application behavior.

For detailed procedures, see [Upgrading Fabric and Studio with K2cloud](/articles/98_installation_and_upgrade/Upgrade_K2cloud/README.md).

## Deployment Operations

Customers control deployment of their K2view implementation.

The built-in deployment workflow is:

```text
Approved Git Tag
      ↓
Deploy Environment
      ↓
Activate Environment
      ↓
Deploy Project
      ↓
Validate Runtime
```

K2view operates the platform on which these deployment operations execute. The customer remains responsible for determining:

- what version is approved,
- when it should be deployed,
- where it should be deployed,
- and whether the resulting application operates correctly.

See [K2cloud Deployments and Lifecycle](/articles/80_k2cloud/07-deployments-lifecycle/README.md).

## Monitoring and Diagnostics

K2cloud SaaS provides integrated monitoring and logging capabilities for customer Spaces.

Customers can use the available K2cloud capabilities to review runtime activity and logs.

Space Details separately provides Kubernetes-level diagnostics such as:

- pod state,
- pod details,
- pod logs,
- and Kubernetes events.

Customers can use these diagnostics without directly operating the underlying Kubernetes infrastructure.

K2view remains responsible for the SaaS platform and infrastructure. Customers remain responsible for understanding and validating the behavior of their K2view implementation.

See [Monitoring and Logs](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_monitoring_and_logs.md) and [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md).

## Operational Escalation

When an issue occurs, the first objective is to determine whether it concerns:

- the customer's K2view implementation,
- application configuration or deployment,
- identity and authorization,
- or the K2cloud SaaS platform and infrastructure.

Customers use the available K2cloud application, monitoring, logging, and diagnostic capabilities to investigate their implementation.

Issues requiring investigation of the underlying SaaS platform or infrastructure are escalated to K2view.

This boundary allows customers to operate their K2view applications while K2view retains responsibility for operating the underlying K2cloud SaaS platform.

## Related Documentation

- [K2cloud SaaS Overview](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_overview.md)
- [Shared Responsibility Model](/articles/80_k2cloud/09-common-operations/80_k2cloud_common_operations_shared_responsibility_model.md)
- [SaaS Runtime Operations](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_runtime_operations.md)
- [SaaS Customer Responsibilities](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_customer_responsibilities.md)
- [SaaS Observability and Support](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_observability_and_support.md)
