# Production Readiness Checklist

## Overview

Before promoting workloads into production, validate operational readiness across:

- Project and Git configuration,
- Space configuration,
- storage and persistence,
- identity and access,
- observability,
- deployment procedures,
- and operational support.

This checklist provides a practical final review before production use.

## Project and Git Readiness

Validate:

- Git repositories are customer-owned and accessible.
- Branch and tag strategies are defined.
- Deployment tags identify reproducible versions.
- Studio users use their personal Git credentials or personal access tokens for commits and pushes.
- The selected deployment workflow has been tested.

Confirm:

- critical implementation artifacts do not exist only inside a Studio Space,
- required changes have been committed and pushed to Git,
- and operational deployment procedures are documented.

## Space Readiness

Validate:

- the production Space uses the correct Site,
- the correct Space Profile is selected,
- the required Fabric Image is associated with the Space Profile,
- ingress and connectivity are validated,
- the Space can be accessed as expected,
- and the correct environment has been deployed and activated.

:

- the appropriate environment designation, such as `prod`, is active,
- required Project content has been deployed,
- and the deployment promotion procedure has been tested.

## Storage and Lifecycle Readiness

Confirm the persistence model used by the Space Profile and understand its lifecycle implications. K2cloud SaaS uses managed persistence; K2cloud Self-Hosted can use managed or noSdb, depending on the Space Profile and architecture.

### `managed`

For a `managed` profile, validate that the team understands:

- the database and storage are associated with the Space,
- their lifecycle is tied to the Space,
- and deleting the Space deletes the associated lifecycle-managed persistence.

### `noSdb`

For a `noSdb` profile, validate:

- the required external database and object storage are configured,
- access to those resources is working,
- the required IAM integration is configured,
- operational ownership is established,
- and backup and recovery responsibilities are understood.

The external persistence lifecycle is independent of the Space. Deleting the Space does not delete the externally managed database or object storage.

## Identity and Access Readiness

Validate:

- the required authentication model is configured,
- SAML or identity federation is functioning where applicable,
- required identity groups are mapped correctly,
- Fabric roles and permissions are configured,
- TDM permission groups are configured where applicable,
- and operational ownership for identity and access is established.

Confirm:

- `cloud_user` is restricted to users who require K2cloud Project and Space lifecycle responsibilities,
- `space_admin` is tightly controlled,
- production users have only the permissions required for their responsibilities,
- and access-review procedures are established.

## Observability Readiness

### K2cloud SaaS

Validate that the operational team understands how to:

- review Space activity,
- use the available Metrics and Logs capabilities,
- review Space status,
- inspect Kubernetes pod diagnostics,
- and review Kubernetes events when troubleshooting.

### K2cloud Self-Hosted

Validate the customer's operational framework for:

- Kubernetes monitoring,
- cluster observability,
- ingress visibility,
- centralized logging,
- operational alerting,
- and backup monitoring.

Self-Hosted customers use their own infrastructure observability, monitoring, and logging framework.

## Deployment Readiness

Validate:

- the deployment promotion procedure,
- Git tagging conventions,
- environment deployment,
- environment activation,
- Project deployment,
- partial LU deployment where required,
- runtime validation,
- and rollback procedures.

Confirm that the deployment workflow can be repeated consistently across the environments used by the implementation, such as:

```text
Development → QA → Staging → Production
```

CI/CD automation is not required to use the K2cloud deployment workflow. Where external CI/CD or API automation is used, validate that integration before production use.

## Operational Support Readiness

Validate:

- escalation paths,
- incident ownership,
- operational contacts,
- support responsibilities,
- maintenance procedures,
- and recovery procedures.

Teams should understand:

- who manages K2cloud lifecycle operations,
- who administers the Space,
- who manages runtime infrastructure where applicable,
- who manages deployments,
- who manages identity and access,
- who manages observability,
- and who owns runtime operations.

## Recommended Validation Exercise

Before production cutover, perform at least one complete operational validation cycle appropriate to the target environment:

```text
Deploy Environment
        ↓
Activate Environment
        ↓
Deploy Project
        ↓
Validate Runtime
        ↓
Review Logs and Diagnostics
        ↓
Validate Access
        ↓
Validate Recovery Procedures
```

For a Fabric runtime Space, also validate restart and recovery procedures.

For Studio Spaces used as part of the development lifecycle, validate pause and resume behavior where applicable.

The objective is to confirm that the operational model works end-to-end before production onboarding.

## Related Documentation

- [K2cloud Spaces](/articles/80_k2cloud/06-spaces/README.md)
- [K2cloud Deployments and Lifecycle](/articles/80_k2cloud/07-deployments-lifecycle/README.md)
- [K2cloud Identity and Access](/articles/80_k2cloud/08-identity-and-access/README.md)
- [Common Operations Overview](/articles/80_k2cloud/09-common-operations/80_k2cloud_common_operations_overview.md)
- [Operational Runbooks](/articles/80_k2cloud/09-common-operations/80_k2cloud_common_operations_operational_runbooks.md)
