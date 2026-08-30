# Operational Runbooks

## Overview

Production K2cloud environments should have defined operational procedures for routine lifecycle activities, deployments, troubleshooting, recovery, and escalation.

The detailed K2cloud procedures are documented throughout the Academy. Rather than duplicating those procedures, this article identifies the operational workflows teams should be prepared to execute and points to the corresponding documentation.

Customer-specific runbooks can then incorporate these procedures together with the organization's own:

- approvals,
- maintenance windows,
- validation requirements,
- escalation paths,
- infrastructure procedures,
- and incident-management processes.

## Space Lifecycle Operations

Operations teams should be prepared to perform the lifecycle actions appropriate to each type of Space.

These can include:

- creating a Space,
- reviewing Space status,
- opening a Space,
- refreshing status,
- pausing and resuming a Studio Space,
- restarting a Fabric Space,
- viewing Space Details,
- and deleting a Space.

Use the procedures in [K2cloud Spaces](/articles/80_k2cloud/06-spaces/README.md).

Deletion procedures should explicitly account for the persistence model because deletion is not recoverable and has different implications for `managed` and `noSdb` profiles.

See [Delete a Space](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_delete_a_space.md).

## Deployment Operations

Deployment runbooks should define how approved content is promoted to runtime Spaces.

The standard K2cloud workflow is:

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

The organization's runbook should additionally define:

- who approves the deployment,
- which Git tag is authorized,
- which target Space is used,
- the required validation,
- rollback criteria,
- and escalation procedures if validation fails.

Use the procedures in [K2cloud Deployments and Lifecycle](/articles/80_k2cloud/07-deployments-lifecycle/README.md).

## Fabric and Studio Upgrades

Upgrade runbooks should define how Fabric and Studio versions are validated and promoted through the organization's environments.

K2cloud provides self-service lifecycle operations for upgrading and rolling back Spaces. The detailed upgrade procedures are maintained with the other K2view installation and upgrade documentation.

See [Upgrading Fabric and Studio with K2cloud](/articles/98_installation_and_upgrade/Upgrade_K2cloud/README.md).

For production upgrades, the organization's runbook should define:

- the target Fabric Image version,
- validation environments,
- maintenance-window requirements,
- application and integration validation,
- rollback criteria,
- and escalation procedures.

A common approach is to validate progressively through:

```text
Development → QA → Staging → Production
```

## Runtime Diagnostics

When a Space does not operate as expected, the first K2cloud diagnostic workflow should establish the runtime state of the Space.

**Space Details** provides Kubernetes-level diagnostics, including:

- pods,
- pod state,
- pod details,
- pod logs,
- resource information,
- pod definitions,
- and Kubernetes events.

Use [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md).

K2cloud SaaS customers can also use the available K2cloud monitoring and log capabilities when investigating runtime behavior.

See [Monitoring and Logs](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_monitoring_and_logs.md).

K2cloud Self-Hosted customers use their own infrastructure monitoring, logging, and observability framework in conjunction with K2cloud Space diagnostics.

## Identity and Access Issues

Access runbooks should distinguish between:

```text
Authentication
      ↓
Identity Federation
      ↓
K2cloud Orchestrator Authorization
      or
Fabric Space Authorization
      ↓
Optional TDM Authorization
```

Do not resolve a Space-access issue simply by granting broader K2cloud privileges.

Use [Identity and Access Troubleshooting](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_troubleshooting.md) to isolate the affected authorization layer.

Customer runbooks should also identify the appropriate IAM team or owner for issues involving:

- enterprise IdP authentication,
- IdP group membership,
- SAML configuration,
- federation mappings,
- Fabric roles,
- and TDM permission groups.

## Self-Hosted Infrastructure Operations

For K2cloud Self-Hosted, customer operational procedures must also cover the customer-managed runtime infrastructure.

Depending on the deployment, this can include:

- Kubernetes cluster operations,
- infrastructure monitoring,
- ingress,
- DNS,
- TLS certificates,
- container registry access,
- external databases,
- object storage,
- networking,
- backup and recovery,
- and infrastructure incident response.

These infrastructure procedures are customer-specific and complement the K2cloud lifecycle procedures.

## Component Runbooks

Operational procedures for underlying K2view and supporting components are maintained separately from the K2cloud operational model.

The Academy's **Installation, Upgrades & Runbooks** documentation contains component-specific runbooks for areas such as:

- Fabric,
- Cassandra,
- and Kafka.

Use those runbooks when the required operation concerns the underlying component rather than the K2cloud lifecycle operation.

## What a Customer Runbook Should Define

For each production operational procedure, define:

- **Purpose** — why the procedure is performed.
- **Applicability** — when and where it applies.
- **Prerequisites** — what must be true before starting.
- **Procedure** — the authoritative operational steps.
- **Validation** — how successful completion is confirmed.
- **Rollback or recovery** — what to do if the procedure fails.
- **Operational considerations** — maintenance windows, dependencies, or expected impact.
- **Escalation** — who to contact when the procedure cannot be completed.
- **Related documentation** — the applicable K2cloud or component documentation.

This provides a consistent operational framework without copying K2cloud procedures into multiple documents.

## Related Documentation

- [K2cloud Spaces](/articles/80_k2cloud/06-spaces/README.md)
- [K2cloud Deployments and Lifecycle](/articles/80_k2cloud/07-deployments-lifecycle/README.md)
- [K2cloud Identity and Access](/articles/80_k2cloud/08-identity-and-access/README.md)
- [Production Readiness Checklist](/articles/80_k2cloud/09-common-operations/80_k2cloud_common_operations_production_readiness_checklist.md)
- [Upgrading Fabric and Studio with K2cloud](/articles/98_installation_and_upgrade/Upgrade_K2cloud/README.md)