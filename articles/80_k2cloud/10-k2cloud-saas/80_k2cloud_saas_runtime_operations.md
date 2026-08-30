# SaaS Runtime Operations

## Overview

In K2cloud SaaS, K2view operates the underlying platform and Kubernetes infrastructure while customers continue to operate their K2view implementations.

Customer runtime activities commonly include:

- managing Studio and Fabric Spaces,
- promoting deployments,
- activating environments,
- validating runtime behavior,
- reviewing monitoring and logs,
- troubleshooting application and deployment issues,
- and managing runtime access.

These operations remain centered around Spaces and are performed through K2cloud and the applications within each Space.

## Space Lifecycle Operations

Authorized customers manage the application-level lifecycle of their Spaces through K2cloud Orchestrator.

Depending on the Space type, operations can include:

- reviewing Space status,
- opening Spaces,
- refreshing status,
- pausing and resuming Studio Spaces,
- restarting Fabric Spaces,
- reviewing Space Details,
- and deleting Spaces.

K2view operates the infrastructure on which these lifecycle operations execute.

For detailed Space operations, see [K2cloud Spaces](/articles/80_k2cloud/06-spaces/README.md).

## Deployment Operations

Customers control promotion of their K2view implementation into runtime Spaces.

The standard deployment sequence is:

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

The customer determines what version should be deployed, where it should be deployed, and whether the resulting application operates correctly.

For detailed deployment procedures, see [K2cloud Deployments and Lifecycle](/articles/80_k2cloud/07-deployments-lifecycle/README.md).

## Runtime Access

Runtime users access authorized Spaces directly through the Space URL.

Depending on the Space and the user's permissions, this can provide access to capabilities such as:

- Fabric Web Studio,
- Fabric Admin,
- TDM,
- APIs,
- Reports,
- and other runtime applications.

Customers remain responsible for defining the appropriate runtime authorization and managing the identity groups used to provide that access.

K2cloud Orchestrator access is separate from Space runtime access.

For more information, see [K2cloud Identity and Access](/articles/80_k2cloud/08-identity-and-access/README.md).

## Runtime Monitoring

K2cloud SaaS provides integrated monitoring and logging capabilities for customer Spaces.

Customers can use these capabilities to review:

- Space activity,
- runtime metrics,
- logs,
- deployment activity,
- and runtime behavior.

These capabilities provide application and operational visibility without requiring customers to operate the underlying monitoring infrastructure.

For more information, see [Monitoring and Logs](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_monitoring_and_logs.md).

## Runtime Diagnostics

When troubleshooting a Space, customers can also use **Space Details** to inspect Kubernetes-level diagnostics exposed through K2cloud.

These include:

- pod state,
- pod details,
- pod logs,
- resource information,
- and Kubernetes events.

A typical investigation can therefore move from application-level visibility to Kubernetes-level diagnostics:

```text
Review Space status
        ↓
Review Metrics and Logs
        ↓
Review Space Details
        ↓
Review pod state and logs
        ↓
Review Kubernetes events
```

Customers can inspect this information without directly administering the underlying Kubernetes infrastructure.

For more information, see [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md).

## Troubleshooting Responsibility

Customers remain responsible for troubleshooting issues related to their K2view implementation, including:

- deployment content,
- environment configuration,
- application behavior,
- integrations,
- runtime authorization,
- and application logic.

K2view remains responsible for the underlying K2cloud SaaS platform and infrastructure.

When investigation indicates that an issue concerns the SaaS platform or infrastructure rather than the customer implementation, the issue should be escalated to K2view.

See [SaaS Observability and Support](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_observability_and_support.md).

## Related Documentation

- [K2cloud SaaS Overview](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_overview.md)
- [SaaS Operational Model](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_operational_model.md)
- [K2cloud Spaces](/articles/80_k2cloud/06-spaces/README.md)
- [K2cloud Deployments and Lifecycle](/articles/80_k2cloud/07-deployments-lifecycle/README.md)
- [Monitoring and Logs](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_monitoring_and_logs.md)
- [SaaS Observability and Support](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_observability_and_support.md)