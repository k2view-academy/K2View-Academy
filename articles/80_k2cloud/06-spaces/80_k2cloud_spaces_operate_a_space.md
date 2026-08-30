# Operate a Space

## Overview

The Space action menu provides the primary operational controls for a deployed Space.

Open the menu using the ellipsis (**...**) on the Space card.

The available actions depend on whether the Space is a **Studio Space** or a **Fabric Space**, as well as the current state of the Space.

## Open

**Open** launches the deployed Space.

The external-link icon next to the Space name performs the same action.

## Refresh Status

**Refresh Status** requests an updated status from the runtime environment.

Use this when a Space is undergoing an operation such as:

- creation,
- pause or resume,
- restart,
- deployment,
- or recovery.

## Pause and Resume

Studio Spaces support **Pause Space** and **Resume Space**.

**Pause Space** suspends the Studio Space. When the Space is paused, the menu provides **Resume Space** to return it to operation.

Fabric Spaces do not provide Pause and Resume operations.

## Deploy Environments

Fabric Spaces provide **Deploy Environments**.

This operation deploys an environment definition stored in Git to the runtime.

The operator selects:

- the Git tag containing the environment definition,
- and the target environment designation, such as `prod`.

The environment designation identifies which environment configuration applies to the runtime.

Detailed environment behavior and deployment workflows are covered separately in the Fabric Environment and K2cloud deployment documentation.

## Deploy Project

Fabric Spaces provide **Deploy Project**.

This operation deploys Project content from Git to the Fabric runtime.

The operator can deploy:

- the entire Project,
- or selected Logical Units.

When deploying selected Logical Units, the LU names are provided as a comma-delimited list.

## Restart Space

Fabric Spaces provide **Restart Space**.

Restart reinitializes the Fabric runtime services and can temporarily interrupt access to the Space.

## View Space Details

**View Space Details** opens the operational diagnostics for the Space.

Space Details provides Kubernetes-level information about the deployed environment, including:

- Pods,
- Events,
- runtime state,
- resource information,
- and other deployment diagnostics.

See [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md).

## Delete Space

**Delete Space** permanently deletes the Space.

Deletion is not recoverable, and the effect on persistent data depends on the persistence model used by the Space Profile.

See [Delete a Space](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_delete_a_space.md) before deleting a Space.

## Related Documentation

- [Spaces Overview](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_space_overview.md)
- [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md)
- [Delete a Space](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_delete_a_space.md)