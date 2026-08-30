# K2cloud Troubleshooting

## Table of Contents

- [Overview](#overview)
- [Troubleshooting Approach](#troubleshooting-approach)
- [Determine the Operational Layer](#determine-the-operational-layer)
- [Collect Diagnostic Information](#collect-diagnostic-information)
- [K2cloud Orchestrator Access Issues](#k2cloud-orchestrator-access-issues)
- [Self-Hosted Agent and Site Connectivity Issues](#self-hosted-agent-and-site-connectivity-issues)
- [Space Creation Issues](#space-creation-issues)
- [Environment and Project Deployment Issues](#environment-and-project-deployment-issues)
- [Git Issues](#git-issues)
- [Self-Hosted Registry and Image Issues](#self-hosted-registry-and-image-issues)
- [Space Access, Ingress, and DNS Issues](#space-access-ingress-and-dns-issues)
- [Identity and Authorization Issues](#identity-and-authorization-issues)
- [Runtime and Pod Issues](#runtime-and-pod-issues)
- [Resource and Capacity Issues](#resource-and-capacity-issues)
- [Upgrade and Rollback Issues](#upgrade-and-rollback-issues)
- [Escalating to K2view Support](#escalating-to-k2view-support)
- [Related Documentation](#related-documentation)

## Overview

K2cloud troubleshooting begins by identifying which layer of the environment is exhibiting the problem and who is responsible for that layer.

The troubleshooting boundary differs between the two K2cloud deployment models:

- **K2cloud SaaS** — K2view operates the K2cloud Orchestrator and the underlying runtime infrastructure.
- **K2cloud Self-Hosted** — K2view operates the K2cloud Orchestrator, while the customer operates the Kubernetes runtime infrastructure and supporting services.

In both models, the customer remains responsible for its K2view implementation, configuration, deployments, and runtime authorization.

Air-gapped Fabric deployments do not use the K2cloud Orchestrator and are covered separately under [Air-Gapped Fabric Deployments](/articles/80_k2cloud/12-air-gapped/80_k2cloud_air_gapped_overview.md).

## Troubleshooting Approach

When troubleshooting K2cloud, avoid starting with a single component or assuming that an observed application failure identifies the source of the problem.

Instead:

1. Identify the affected Project, Site, and Space as applicable.
2. Identify the operation that failed.
3. Determine the operational layer involved.
4. Review the diagnostic information available for that layer.
5. Check for recent configuration, deployment, infrastructure, identity, or version changes.
6. Determine whether the issue is customer-managed or K2view-managed.
7. Escalate with the relevant diagnostic information when required.

This approach helps distinguish application issues from K2cloud orchestration or infrastructure issues.

## Determine the Operational Layer

A K2cloud operation can involve several layers:

    K2cloud Orchestrator
            ↓
    Space lifecycle / deployment
            ↓
      K2view application
            ↓
      Kubernetes runtime
            ↓
        Infrastructure

The observed symptom may occur at one layer while the underlying cause exists at another.

For example:

- a Space creation failure can result from Site or infrastructure connectivity,
- a Project deployment failure can result from Git or application content,
- an unavailable application can result from a failed pod or ingress problem,
- and a lifecycle operation can fail because the required container image is unavailable.

For K2cloud SaaS, K2view operates the underlying infrastructure.

For K2cloud Self-Hosted, the customer operates the Kubernetes environment, networking, ingress, storage, registry, and supporting infrastructure.

Understanding this boundary is an important first step in troubleshooting.

## Collect Diagnostic Information

Before making changes, collect the information associated with the failure.

Useful information can include:

- affected Project,
- affected Site,
- affected Space,
- time of the failure,
- operation being performed,
- Space status,
- recent deployments,
- recent configuration changes,
- Fabric or Fabric-Studio version,
- pod state,
- pod restart information,
- pod logs,
- Kubernetes events,
- application errors,
- and relevant infrastructure findings.

### K2cloud SaaS

K2cloud SaaS customers can use the monitoring and logging capabilities provided through K2cloud, including Space activity, Metrics, and Logs.

**Space Details** provides additional Kubernetes diagnostics such as pod information, pod logs, and Kubernetes events.

For more information, see:

- [Monitoring and Logs](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_monitoring_and_logs.md)
- [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md)

### K2cloud Self-Hosted

Self-Hosted customers use their own infrastructure monitoring, centralized logging, alerting, security monitoring, and SIEM capabilities.

K2cloud also provides Kubernetes diagnostics for the Space through **Space Details**.

When troubleshooting a Self-Hosted issue, correlate the K2cloud information with the customer's infrastructure observability where appropriate.

For more information, see [Self-Hosted Observability and Support](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_observability_and_support.md).

## K2cloud Orchestrator Access Issues

If a user cannot access `https://cloud.k2view.com`, first determine whether the user is expected to have K2cloud Orchestrator access.

Access to the K2cloud Orchestrator and access to a Space are separate.

A user requires the appropriate K2cloud role, such as `cloud_user`, to access the Orchestrator.

A developer or other runtime user who does not require lifecycle-management capabilities may not have `cloud_user`. That user can still access an authorized Space directly using its Space URL.

If Orchestrator access is expected, verify:

- the user can authenticate,
- the appropriate K2cloud role has been assigned,
- the expected identity or federation mapping is in effect,
- and the user is accessing the correct K2cloud service.

Do not grant `cloud_user` simply to allow a user to access a Studio or Fabric Space. It is a highly privileged K2cloud role.

For more information, see [Identity and Access Overview](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_overview.md).

## Self-Hosted Agent and Site Connectivity Issues

In K2cloud Self-Hosted, the K2cloud Agent provides the connection required for the K2cloud Orchestrator to operate against the customer-managed Site.

If K2cloud cannot perform expected operations against a Self-Hosted Site, verify the customer-managed infrastructure and connectivity required by that Site.

Review areas such as:

- K2cloud Agent availability,
- connectivity between the Site and the K2cloud control plane,
- Kubernetes availability,
- network configuration,
- infrastructure identity and permissions,
- and recent Site or infrastructure changes.

Because the runtime infrastructure is customer-operated, customer infrastructure monitoring should also be reviewed for failures or connectivity changes associated with the affected Site.

For more information, see [Sites Overview](/articles/80_k2cloud/05-sites/80_k2cloud_sites_overview.md).

## Space Creation Issues

If Space creation fails, first verify the resources selected for the Space:

- Project,
- Space Profile,
- Fabric Image,
- and Site.

Then determine whether the failure concerns the K2cloud configuration or the target runtime environment.

Review:

- Space status,
- selected Space Profile,
- selected Fabric Image,
- Site availability,
- pod state,
- Kubernetes events,
- and relevant infrastructure findings.

For Self-Hosted Sites, also verify that the customer-managed Kubernetes environment has sufficient capacity and that the required Fabric or Fabric-Studio image is available in the configured container registry.

Space creation failures involving infrastructure should be investigated according to the deployment-model responsibility boundary.

For more information, see:

- [Create a Space](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_create_a_space.md)
- [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md)

## Environment and Project Deployment Issues

Environment and Project deployment failures are different from Space creation failures.

For an environment deployment issue, review:

- the selected Git tag,
- the environment definition stored in Git,
- whether the deployment completed,
- and whether the intended environment was subsequently selected and activated in Fabric Admin.

For a Project deployment issue, review:

- the selected Git tag,
- whether the full Project or selected Logical Units were deployed,
- deployment results,
- application errors,
- and runtime behavior following the deployment.

A successful deployment does not by itself confirm that the application is operating correctly. Runtime validation remains part of the deployment lifecycle.

For more information, see:

- [Deploy Environments](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_deploy_environments.md)
- [Deploy Project](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_deploy_project.md)

## Git Issues

K2cloud and Studio use Git for different purposes, so first determine which Git operation is failing.

### K2cloud Repository Access

K2cloud uses the Git configuration associated with the Project to access versioned content required for deployment.

If K2cloud cannot access the repository, review:

- Project Git configuration,
- repository location,
- Project Git token,
- repository permissions,
- token validity,
- and required network access.

The Project Git token is an operational credential used by K2cloud and should have the permissions required for its read-only use.

### Studio User Git Access

Studio users use their own Git credentials when performing development operations such as commits and pushes.

If a developer can use the Space but cannot commit or push, review:

- the user's personal Git credentials or PAT,
- repository permissions,
- token validity,
- and repository access.

Do not troubleshoot a developer's push failure by changing the Project Git token unless the K2cloud repository access itself is also failing.

For more information, see [Configure Git](/articles/80_k2cloud/03-projects/80_k2cloud_projects_configure_git.md).

## Self-Hosted Registry and Image Issues

Container registry and image availability are particularly important for K2cloud Self-Hosted.

K2view publishes supported Fabric and Fabric-Studio images, but the customer is responsible for making the required images available in the customer-managed registry configured for the Site.

If a Space cannot be created or upgraded using a selected Fabric Image, verify:

- the required image has been copied to the customer registry,
- the expected image version is available,
- registry connectivity is working,
- required registry permissions are valid,
- and the Site is configured to use the expected registry location.

An image can be available for selection through K2cloud while still being unavailable from the customer registry required by the Self-Hosted Site.

For detailed image preparation and upgrade information, see [Upgrading Fabric and Studio with K2cloud](/articles/98_installation_and_upgrade/Upgrade_K2cloud/README.md).

## Space Access, Ingress, and DNS Issues

If a Space exists but users or applications cannot reach it, determine whether the problem concerns authorization or network access.

For connectivity problems, review:

- Space status,
- Space URL,
- DNS resolution,
- ingress configuration,
- TLS certificates,
- network routing,
- and customer network controls where applicable.

For Self-Hosted Sites, these infrastructure components are customer-managed.

For K2cloud SaaS, K2view operates the underlying SaaS infrastructure.

If the Space is reachable but the user cannot access the application, investigate identity and authorization separately.

For more information, see [Connectivity and Ingress](/articles/80_k2cloud/05-sites/80_k2cloud_sites_connectivity_and_ingress.md).

## Identity and Authorization Issues

Identity troubleshooting should distinguish authentication from authorization.

If a user cannot sign in, investigate authentication and identity federation.

If a user can authenticate but cannot access the K2cloud Orchestrator, verify the K2cloud role.

If a user can authenticate but cannot access a Space, verify the Fabric role and Space authorization.

If the user can access the Space but cannot perform a specific operation, verify the permissions associated with the user's Fabric role and, where applicable, TDM permission group.

Remember:

- Orchestrator access and Space access are separate.
- Creating a Space does not automatically make its creator a Space Admin.
- `space_user` is not automatically bootstrapped into a newly created Space.
- `space_admin` should be tightly controlled.
- custom roles should be based on operational responsibilities rather than individual users.

For a detailed diagnostic workflow, see [Identity and Access Troubleshooting](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_troubleshooting.md).

## Runtime and Pod Issues

When a Space is created but the runtime is not behaving as expected, use **Space Details** to inspect its Kubernetes runtime state.

Review:

- pod phase and readiness,
- pod restart counts,
- CPU and memory information,
- pod details,
- pod logs,
- and Kubernetes events.

Pod logs and Kubernetes events can help identify runtime failures that are not apparent from Space status alone.

For Self-Hosted environments, correlate these diagnostics with the customer's Kubernetes and infrastructure monitoring.

For SaaS environments, K2cloud Metrics and Logs can provide additional runtime information.

For more information, see [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md).

## Resource and Capacity Issues

Space Profiles define the K2view runtime topology and resources used when creating Spaces.

If a runtime exhibits resource or capacity problems, first determine whether the issue concerns the configured runtime or the capacity of the infrastructure supporting it.

For Self-Hosted environments, the customer is responsible for ensuring that the Kubernetes environment has sufficient capacity for the selected Space Profiles and workloads.

Review:

- selected Space Profile,
- pod CPU and memory behavior,
- pod state and restarts,
- Kubernetes events,
- infrastructure capacity,
- and workload behavior.

For K2cloud SaaS, the underlying infrastructure is K2view-operated. Runtime evidence should be collected and escalated when the problem indicates an underlying SaaS infrastructure issue.

## Upgrade and Rollback Issues

When a K2cloud Space upgrade does not complete as expected, review the upgrade from the Space and runtime perspectives.

Check:

1. The intended Fabric Image is selected for the Space Profile.
2. For Self-Hosted Sites, the required image exists in the customer-managed destination registry.
3. The Space upgrade status.
4. Space Details for pod state, pod restarts, logs, and Kubernetes events.
5. Application behavior after the upgrade.

For multi-replica Fabric Spaces, upgrades can use rolling behavior in which runtime pods are replaced progressively.

If an upgrade must be reversed and the previous image remains available, use the supported rollback workflow.

Customers remain responsible for validating their K2view implementation following an upgrade or rollback.

For detailed procedures, see [Upgrading Fabric and Studio with K2cloud](/articles/98_installation_and_upgrade/Upgrade_K2cloud/README.md).

## Escalating to K2view Support

Escalate an issue to K2view when investigation indicates that the problem concerns a K2view-managed component or requires K2view product assistance.

Before escalating, collect the information relevant to the issue.

This can include:

- affected Project,
- Site and Space,
- time of the issue,
- operation being performed,
- observed behavior,
- recent changes,
- Fabric or Fabric-Studio version,
- relevant logs,
- pod state,
- Kubernetes events,
- deployment information,
- and troubleshooting already performed.

For Self-Hosted environments, include relevant customer infrastructure findings where they may affect the K2view runtime or K2cloud operation.

Providing this information helps establish the operational layer involved and reduces the time required to investigate the issue.

## Related Documentation

- [Monitoring and Logs](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_monitoring_and_logs.md)
- [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md)
- [Operate a Space](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_operate_a_space.md)
- [Deployments and Lifecycle Overview](/articles/80_k2cloud/07-deployments-lifecycle/80_k2cloud_deployments_and_lifecycle_overview.md)
- [Identity and Access Troubleshooting](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_troubleshooting.md)
- [K2cloud SaaS Observability and Support](/articles/80_k2cloud/10-k2cloud-saas/80_k2cloud_saas_observability_and_support.md)
- [Self-Hosted Observability and Support](/articles/80_k2cloud/11-k2cloud-self-hosted/80_k2cloud_self_hosted_observability_and_support.md)
- [Upgrading Fabric and Studio with K2cloud](/articles/98_installation_and_upgrade/Upgrade_K2cloud/README.md)