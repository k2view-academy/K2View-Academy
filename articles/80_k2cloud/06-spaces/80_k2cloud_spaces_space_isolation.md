# Space Isolation

## Overview

A **Space** is an operational isolation boundary within K2cloud.

Isolation applies across several aspects of the deployed environment, including:

- runtime workloads,
- Kubernetes resources,
- ingress,
- lifecycle operations,
- runtime access,
- and persistence.

The exact implementation depends on the Space Profile, Site, and K2cloud deployment model.

## Namespace Isolation

Spaces commonly map to dedicated Kubernetes namespaces.

The namespace separates Kubernetes resources associated with the Space, including:

- pods,
- services,
- configuration,
- and events.

The namespace associated with a pod is visible through **Space Details**.

For more information, see [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md).

## Studio and Fabric Isolation

Studio Spaces and Fabric Spaces serve different operational purposes.

A Studio Space commonly includes:

- Fabric,
- Fabric Studio,
- and PostgreSQL.

A Fabric Space is a runtime environment and commonly contains the Fabric runtime without the Studio development environment.

Each Space has its own runtime lifecycle and can be operated independently from other Spaces in the Project.

## Persistence Isolation

Persistence behavior depends on the Space Profile.

With a `managed` profile, the database and storage lifecycle are associated with the Space.

With a `noSdb` profile, persistence is externalized to independently managed database and object storage services.

This distinction is particularly important when deleting and recreating Spaces.

For profile and persistence details, see [K2cloud Versionless Space Profiles](/articles/98_installation_and_upgrade/K2cloud_Space_Profiles/README.md).

## Access Isolation

K2cloud Orchestrator access and runtime Space access are separate.

Project Managers with the `cloud_user` role manage Space lifecycle operations through the K2cloud Orchestrator.

Runtime users access authorized Spaces directly using roles such as:

- `space_admin`,
- `space_user`,
- or customer-defined Fabric roles.

This separates **lifecycle control** from **runtime usage**.

## Site Isolation

Sites provide infrastructure and ingress boundaries for Spaces.

The selected Site affects characteristics such as:

- runtime placement,
- networking,
- ingress,
- and operational exposure.

Separate Sites can therefore represent different Kubernetes clusters, regions, network boundaries, or other infrastructure boundaries.

For more information, see [Sites Overview](/articles/80_k2cloud/05-sites/80_k2cloud_sites_overview.md).

## Related Documentation

- [Sites Overview](/articles/80_k2cloud/05-sites/80_k2cloud_sites_overview.md)
- [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md)
- [Access Fabric Web Studio and Fabric Spaces](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_access_fabric_web_studio_and_fabric_spaces.md)
- [Delete a Space](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_delete_a_space.md)
