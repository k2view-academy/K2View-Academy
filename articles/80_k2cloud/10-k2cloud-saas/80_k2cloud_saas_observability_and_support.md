# SaaS Observability and Support

## Overview

K2cloud SaaS provides integrated operational visibility for customer Spaces without requiring customers to operate the underlying Kubernetes platform.

Operational visibility is provided through:

- Space status and activity,
- runtime metrics,
- logs,
- deployment information,
- Kubernetes pod diagnostics,
- and Kubernetes events.

These capabilities help customers investigate their K2view applications while K2view remains responsible for the underlying SaaS platform and infrastructure.

## SaaS Monitoring and Logs

K2cloud SaaS provides monitoring and logging capabilities for customer Spaces.

Customers can use these capabilities to review information such as:

- Space activity,
- API activity,
- mDB reads and writes,
- runtime metrics,
- logs,
- deployment activity,
- and runtime behavior.

The available logs can include:

- INFO,
- DEBUG,
- WARNING,
- ERROR,
- and AUDIT when enabled.

For customers that need to integrate K2cloud SaaS operational information with their own security or monitoring environment, K2view can also provide a service for exporting supported **ERROR** and **AUDIT** log information to a customer-managed destination.

The export capability provides K2view log information for downstream use; the customer remains responsible for its own monitoring, analysis, correlation, alerting, retention, and incident-response processes.

For more information about the monitoring capabilities available within K2cloud, see [Monitoring and Logs](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_monitoring_and_logs.md).

## Kubernetes Diagnostics

**Space Details** provides Kubernetes-level diagnostics for a Space.

Customers can review:

- pod state,
- pod details,
- pod logs,
- resource information,
- pod restart information,
- and Kubernetes events.

This provides visibility into the runtime state of a Space without giving customers responsibility for administering the Kubernetes infrastructure.

For more information, see [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md).

## Troubleshooting a Runtime Issue

A typical SaaS investigation can move through the available layers of visibility:

    Review Space status
            ↓
    Review Metrics and Logs
            ↓
    Review Space Details
            ↓
    Inspect pod state and logs
            ↓
    Review Kubernetes events

The objective is to determine whether the issue concerns the customer's K2view implementation or the underlying K2cloud SaaS platform.

## Shared Troubleshooting Model

Troubleshooting in K2cloud SaaS follows a shared operational model.

Customers investigate issues involving their implementation, including:

- deployments,
- environment activation,
- application behavior,
- integrations,
- identity and authorization,
- and other implementation-specific runtime behavior.

K2view investigates issues involving the underlying SaaS platform and infrastructure, including:

- Kubernetes infrastructure,
- ingress infrastructure,
- platform services,
- storage orchestration,
- and K2cloud SaaS platform health.

This boundary allows customers to troubleshoot their applications using the operational information exposed through K2cloud while K2view retains responsibility for operating the underlying platform.

## Escalating to K2view

When the available monitoring and diagnostic information indicates that an issue concerns the K2cloud SaaS platform or underlying infrastructure, escalate the issue to K2view Support.

When escalating, provide the relevant information available from the investigation, such as:

- affected Space,
- time of the issue,
- observed behavior,
- deployment or configuration changes associated with the issue,
- relevant logs,
- pod state,
- Kubernetes events,
- and steps already taken to investigate the problem.

Providing this context helps distinguish application-level issues from platform-level issues and supports more efficient investigation.

## Related Documentation

- [K2cloud SaaS Overview](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_overview.md)
- [SaaS Operational Model](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_operational_model.md)
- [SaaS Runtime Operations](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_runtime_operations.md)
- [SaaS Customer Responsibilities](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_customer_responsibilities.md)
- [Monitoring and Logs](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_monitoring_and_logs.md)
- [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md)