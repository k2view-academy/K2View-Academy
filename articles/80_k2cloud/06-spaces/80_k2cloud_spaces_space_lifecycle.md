# Space Lifecycle

## Overview

A Space moves through lifecycle states as it is created, operated, deployed, paused, resumed, restarted, and deleted.

The K2cloud Orchestrator coordinates these lifecycle activities and displays the current operational state of the Space.

## Common Lifecycle States

Common Space states include:

- Creating,
- Running,
- Paused,
- Restarting,
- Deploying,
- Stopping,
- Deleting,
- Failed.

The visible state reflects the current operational condition of the Space.

## Status Change

The Space card displays **Status Change**, which identifies the most recent state transition for the Space.

A status change can result from operations such as:

- restart,
- pause,
- resume,
- deployment,
- orchestration jobs,
- or other lifecycle transitions.

**Status Change is not an activity or audit history.** It represents the latest change in the operational state of the Space.

## Studio Space Lifecycle

Studio Spaces support lifecycle operations such as:

- Pause,
- Resume,
- Open,
- Refresh Status,
- View Space Details,
- Delete.

Pausing a Studio Space suspends runtime execution while preserving the Space definition.

When required, the Space can subsequently be resumed.

## Fabric Space Lifecycle

Fabric Spaces are runtime-oriented and do not support Pause and Resume.

Their lifecycle operations include:

- Deploy Environments,
- Deploy Project,
- Restart Space,
- View Space Details,
- Delete Space.

For details about these operations, see [Operate a Space](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_operate_a_space.md).

## Lifecycle Ownership

Project Managers with the `cloud_user` role perform Space lifecycle operations through the K2cloud Orchestrator.

Runtime users can access a Space directly when authorized through roles such as `space_admin`, `space_user`, or customer-defined Fabric roles.

Space authorization alone does not provide access to K2cloud Orchestrator lifecycle operations. A user must also have the `cloud_user` role to perform those operations through the Orchestrator.

## Deleting a Space

Deletion is the final lifecycle operation and is not recoverable.

The effect on persistent data depends on the persistence model used by the Space Profile.

Before deleting a Space, see [Delete a Space](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_delete_a_space.md).

## Related Documentation

- [Operate a Space](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_operate_a_space.md)
- [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md)
- [Delete a Space](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_delete_a_space.md)