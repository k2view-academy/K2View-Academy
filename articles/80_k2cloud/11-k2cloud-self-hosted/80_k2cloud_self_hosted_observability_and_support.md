# Self-Hosted Observability and Support

## Overview

In K2cloud Self-Hosted, the customer operates the Kubernetes infrastructure where Spaces run and is responsible for the monitoring, logging, and alerting framework associated with that infrastructure.

K2cloud provides Space-level status and Kubernetes diagnostics that complement the customer's observability environment.

This creates two distinct layers of operational visibility:

- **Customer observability** for the Kubernetes infrastructure and runtime environment.
- **K2cloud diagnostics** for investigating the Kubernetes resources associated with a Space.

The K2cloud SaaS Metrics and Logs monitoring components are not provided for Self-Hosted Spaces.

## Customer Observability

Customers should integrate Self-Hosted runtime infrastructure with their existing operational and security monitoring capabilities.

Depending on the customer's environment, this can include:

- Kubernetes monitoring,
- infrastructure metrics,
- centralized logging,
- alerting,
- security monitoring,
- audit collection,
- SIEM integration,
- and incident-management processes.

Because this infrastructure is customer-managed, the customer determines the monitoring technologies, retention policies, alerting rules, and operational procedures used for it.

## K2cloud Space Diagnostics

K2cloud provides Kubernetes-level diagnostics for deployed Spaces through **Space Details**.

Authorized users can inspect information such as:

- pods,
- pod status,
- pod details,
- pod logs,
- resource information,
- restart information,
- and Kubernetes events.

These diagnostics allow users to investigate the Kubernetes resources associated with a Space without requiring routine Space operations to be performed directly through Kubernetes tooling.

For more information, see [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md).

## Troubleshooting a Runtime Issue

Troubleshooting a Self-Hosted Space may require information from both K2cloud and the customer's infrastructure environment.

A typical investigation can move through the following areas:

    Review Space status
            ↓
    Review Space Details
            ↓
    Inspect pod state and pod logs
            ↓
    Review Kubernetes events
            ↓
    Correlate with customer infrastructure
    monitoring and logs

The objective is to determine which operational layer is responsible for the observed behavior.

An issue may originate from:

- the K2view implementation,
- the Space runtime,
- customer-managed Kubernetes infrastructure,
- supporting customer infrastructure,
- or the K2cloud control plane.

## Customer Infrastructure Issues

Issues involving customer-managed infrastructure remain the customer's responsibility.

Examples include problems involving:

- Kubernetes availability or capacity,
- networking,
- ingress,
- DNS,
- certificates,
- storage infrastructure,
- container registry access,
- infrastructure identity and permissions,
- or other customer-managed services.

These issues should be investigated using the customer's infrastructure observability and operational procedures.

K2cloud diagnostics may provide useful evidence of the effect of an infrastructure problem on a Space, but they do not replace infrastructure-level monitoring.

## K2view Implementation Issues

Customers are also responsible for investigating issues involving their K2view implementation.

Examples include:

- application behavior,
- deployed Project content,
- environment configuration,
- integrations,
- runtime authorization,
- and application-specific errors.

Customers should use the available K2view application information, K2cloud diagnostics, and their infrastructure observability as appropriate to determine the source of the issue.

## K2cloud Control Plane Issues

K2view operates the K2cloud Orchestrator SaaS control plane.

When investigation indicates that an issue concerns the K2cloud Orchestrator rather than the customer-managed infrastructure or K2view implementation, the issue should be escalated to K2view Support.

Examples can include problems where expected K2cloud lifecycle or orchestration operations cannot be performed even though the customer-managed Site and its required infrastructure are operating correctly.

## Escalating to K2view

Before escalating a Self-Hosted runtime issue, customers should first use the available information to determine whether the problem is associated with customer-managed infrastructure or the K2view implementation.

When escalating an issue to K2view, provide relevant information such as:

- affected Site and Space,
- time of the issue,
- observed behavior,
- K2cloud operation being performed,
- recent deployment or configuration changes,
- relevant pod state and logs,
- Kubernetes events,
- relevant infrastructure findings,
- and troubleshooting already performed.

Providing information from both K2cloud and the customer infrastructure helps establish the operational boundary of the problem and supports more efficient investigation.

## Related Documentation

- [K2cloud Self-Hosted Overview](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_overview.md)
- [Self-Hosted Operational Model](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_operational_model.md)
- [Self-Hosted Runtime Operations](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_runtime_operations.md)
- [Self-Hosted Customer Responsibilities](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_customer_responsibilities.md)
- [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md)
- [Identity and Access Troubleshooting](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_troubleshooting.md)