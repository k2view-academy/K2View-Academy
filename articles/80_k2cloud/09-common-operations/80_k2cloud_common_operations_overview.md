# Common Operations Overview

## Overview

Operating a K2cloud environment involves several areas covered throughout the K2cloud documentation, including:

- Projects,
- Sites,
- Space Profiles,
- Spaces,
- deployments,
- identity and access,
- and monitoring and diagnostics.

This section does not redefine those capabilities or repeat their procedures.

Instead, it brings them together from an operational perspective to help teams prepare for and consistently operate K2cloud environments across development, QA, staging, and production.

## Operational Ownership

K2cloud environments typically involve several operational responsibilities.

<table>
<thead>
<tr>
<th>Responsibility Area</th>
<th>Typical Owner</th>
</tr>
</thead>
<tbody>
<tr>
<td>Project and Space lifecycle</td>
<td>Project Manager (<code>cloud_user</code>)</td>
</tr>
<tr>
<td>Space runtime administration</td>
<td>Space administrators</td>
</tr>
<tr>
<td>Space application access</td>
<td>Authorized Space users</td>
</tr>
<tr>
<td>Git repository management</td>
<td>Customer development teams</td>
</tr>
<tr>
<td>CI/CD integration</td>
<td>Customer DevOps teams</td>
</tr>
<tr>
<td>Identity federation and group membership</td>
<td>Customer IAM / security teams</td>
</tr>
<tr>
<td>Self-Hosted infrastructure and observability</td>
<td>Customer platform and operations teams</td>
</tr>
<tr>
<td>K2cloud SaaS infrastructure and platform operations</td>
<td>K2view</td>
</tr>
</tbody>
</table>

The exact division of responsibility depends on whether the environment uses K2cloud SaaS or K2cloud Self-Hosted.

For the detailed responsibility model, see [Shared Responsibility Model](/articles/80_k2cloud/01-overview/80_k2cloud_overview_shared_responsibility_model.md).

## Common Operational Activities

Common K2cloud operational activities include:

- creating and deleting Spaces,
- pausing and resuming Studio Spaces,
- restarting Fabric Spaces,
- reviewing Space status,
- reviewing Kubernetes pod diagnostics and events,
- deploying and activating environment definitions,
- deploying Project content,
- validating deployments,
- monitoring runtime activity and logs where available,
- and managing identity and access.

The detailed procedures belong to their respective areas of the K2cloud documentation.

### Space Operations

Space lifecycle, diagnostics, monitoring, and other Space operations are covered in [K2cloud Spaces](/articles/80_k2cloud/06-spaces/README.md).

### Deployments

Environment and Project deployment workflows are covered in [K2cloud Deployments and Lifecycle](/articles/80_k2cloud/07-deployments-lifecycle/README.md).

### Identity and Access

Authentication, federation, roles, permissions, and Space access are covered in [K2cloud Identity and Access](/articles/80_k2cloud/08-identity-and-access/README.md).

## Operational Readiness

Before moving an implementation into production, operational responsibilities and procedures should be understood and validated.

This includes confirming:

- infrastructure readiness,
- Space configuration,
- persistence and storage,
- connectivity,
- deployment procedures,
- identity and access,
- monitoring and diagnostics,
- and operational ownership.

Use the [Production Readiness Checklist](/articles/80_k2cloud/09-common-operations/80_k2cloud_common_operations_production_readiness_checklist.md) as a final operational review.

## Operational Runbooks

Organizations should establish operational runbooks for recurring or exceptional activities that require a defined response.

Rather than duplicating the procedures documented throughout K2cloud, the runbook framework identifies the operational procedures teams should have available and points to the corresponding K2cloud documentation.

See [Operational Runbooks](/articles/80_k2cloud/09-common-operations/80_k2cloud_common_operations_operational_runbooks.md).

## Related Documentation

- [K2cloud Spaces](/articles/80_k2cloud/06-spaces/README.md)
- [K2cloud Deployments and Lifecycle](/articles/80_k2cloud/07-deployments-lifecycle/README.md)
- [K2cloud Identity and Access](/articles/80_k2cloud/08-identity-and-access/README.md)
- [Production Readiness Checklist](/articles/80_k2cloud/09-common-operations/80_k2cloud_common_operations_production_readiness_checklist.md)
- [Operational Runbooks](/articles/80_k2cloud/09-common-operations/80_k2cloud_common_operations_operational_runbooks.md)
