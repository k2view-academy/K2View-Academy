# Roles and Personas

## Overview

K2cloud separates access to the **K2cloud Orchestrator** from access to individual **Spaces**.

A user's responsibilities therefore depend on what they need to manage:

- the K2cloud environment and Space lifecycle,
- the applications developed and deployed within Spaces,
- or the underlying runtime infrastructure in a K2cloud Self-Hosted deployment.

K2cloud roles should not be confused with organizational personas. A persona describes how someone typically works with K2cloud; roles and permissions determine what that user can actually access and perform.

## Project Manager

The **Project Manager** is the primary K2cloud Orchestrator user.

A Project Manager is assigned the `cloud_user` role and can access the K2cloud Orchestrator console at `https://cloud.k2view.com`.

Depending on the Projects and resources available to the user, Project Managers perform activities such as:

- managing Projects,
- creating Spaces,
- selecting Space Profiles, Fabric Images, and Sites,
- managing Space lifecycle operations,
- deploying environments and projects,
- performing supported upgrade operations,
- and viewing Space status and operational information.

The `cloud_user` role provides access to the K2cloud Orchestrator. It does not, by itself, define the user's permissions within a Fabric Space.

## Space Users

Users can access deployed Spaces without having access to the K2cloud Orchestrator.

Space access is controlled by the roles and permissions associated with the Space and its applications.

Common built-in roles include:

- `space_admin`
- `space_user`

Customers can also define Fabric roles and, where applicable, TDM permission groups appropriate to their implementation.

A user who requires access only to a Fabric or Studio Space does not require the `cloud_user` role.

This separation allows organizations to give operational personnel access to the K2cloud Orchestrator while providing developers, application users, administrators, and other users only the runtime access they require.

## Developers

Developers typically work in Studio development Spaces.

Their activities can include:

- developing and configuring K2view applications,
- working with Logical Units and other project artifacts,
- configuring interfaces and environments,
- testing application changes,
- and committing changes to Git.

Git access is managed through the organization's Git platform rather than through K2cloud.

Developers use their own Git credentials or personal access tokens when committing and pushing changes from Studio.

## Runtime Operators and Administrators

Runtime operators and administrators are responsible for operating deployed Fabric environments.

Depending on their assigned permissions and organizational responsibilities, their activities can include:

- accessing Fabric Web Admin,
- monitoring application operation,
- reviewing logs and runtime information,
- activating deployed environments,
- validating deployments,
- and performing authorized Fabric administration.

These users may have Space access without having access to the K2cloud Orchestrator.

Where a user is also responsible for Space lifecycle activities through K2cloud, that user requires the appropriate K2cloud access in addition to the required Space permissions.

## Infrastructure and DevOps Teams

Infrastructure and DevOps responsibilities depend on the deployment model.

With **K2cloud SaaS**, K2view manages and operates the Kubernetes runtime infrastructure. Customer infrastructure teams primarily participate where connectivity, security, integrations, or other customer-managed systems interact with the K2view environment.

With **K2cloud Self-Hosted**, the customer's infrastructure and DevOps teams operate the Kubernetes environment used by K2cloud. Most Self-Hosted deployments use a managed Kubernetes service such as Amazon EKS, Azure AKS, or Google GKE.

Customer responsibilities can include:

- Kubernetes infrastructure,
- networking and connectivity,
- ingress and DNS,
- certificates,
- storage and external platform services,
- infrastructure security,
- monitoring and observability,
- and backup and recovery.

K2cloud provides the Fabric-aware orchestration layer above this infrastructure. Infrastructure teams therefore remain responsible for the health and operation of the Kubernetes environment without having to use Kubernetes as the primary interface for routine Fabric lifecycle management.

## Access Model

The K2cloud access model can be summarized as two distinct access planes:

**K2cloud Orchestrator access**

Used by Project Managers who manage Projects, Spaces, deployments, and lifecycle operations.

**Space access**

Used by developers, administrators, operators, TDM users, and other users who work directly with deployed Studio or Fabric environments.

A user may require access to one or both planes depending on their responsibilities.

Authentication can be provided through K2view-managed identity services or through federation with a customer identity provider. Authorization determines the K2cloud and Space capabilities available to the authenticated user.

Detailed identity, federation, role mapping, and Space access configuration are covered in the K2cloud identity and access documentation.

## Related Documentation

- [Operational Model Overview](/articles/80_k2cloud/01-overview/80_k2cloud_operational_model_overview.md)
- [Shared Responsibility Model](/articles/80_k2cloud/09-common-operations/80_k2cloud_common_operations_shared_responsibility_model.md)
