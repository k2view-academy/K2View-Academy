# Access Fabric Web Studio and Fabric Spaces

## Overview

K2cloud Orchestrator access and Space access are separate.

A user does not need access to the K2cloud Orchestrator to access a Space. Once a Space has been created, authorized users can access it directly using its Space URL.

This separation allows developers, testers, operators, and other users to work within the Spaces they need without granting them broader K2cloud Orchestrator privileges.

## Orchestrator Access

The K2cloud Orchestrator is accessed at:

```text
https://cloud.k2view.com
```

Access to the Orchestrator requires the `cloud_user` role.

`cloud_user` is a highly privileged role because it provides access to K2cloud lifecycle operations such as creating, deleting, pausing, resuming, and otherwise managing Spaces.

Developers and other runtime users should not normally be granted `cloud_user` simply because they need access to a development or runtime Space.

## Direct Space Access

Each Space has its own URL.

The Project Manager or another authorized K2cloud user can provide this URL to the users who need to access the Space.

A user who is authorized for the Space can open the URL directly and authenticate without first accessing the K2cloud Orchestrator.

For example:

```text
User
  ↓
Space URL
  ↓
Authentication
  ↓
Fabric authorization
  ↓
Authorized Space applications
```

If a user without `cloud_user` attempts to access the K2cloud Orchestrator, access to the Orchestrator is denied. This does not prevent the user from directly accessing a Space for which they are authorized.

## Accessing a Studio Space

A Studio Space provides access to Fabric Studio and the Fabric applications available within the development environment.

A typical workflow is:

1. Obtain the Studio Space URL from the Project Manager or Space administrator.
2. Open the Space URL.
3. Authenticate using the configured identity provider.
4. Access Fabric Studio and other applications permitted by your Fabric role.

Fabric Studio functionality is documented separately in the Fabric Studio documentation.

## Accessing a Fabric Space

Fabric Spaces are accessed in the same manner:

1. Obtain the Fabric Space URL.
2. Open the URL directly.
3. Authenticate using the configured identity provider.
4. Access the applications and functions permitted by your Fabric role.

The applications available to the user depend on the authorization configured within that Space.

## Space Authorization

Creating a Space does **not** automatically make the person who created it a Space Administrator.

Runtime authorization is managed separately from K2cloud Orchestrator access.

Common built-in Fabric roles include:

- `space_admin` — broad administrative access used for initial administration and configuration.
- `space_user` — standard non-administrative Space access with customer-defined permissions.

Customers can also define custom Fabric roles to align access with specific responsibilities.

For federated environments, customer Identity Provider groups can be mapped through K2cloud Identity Federation to the appropriate Fabric roles.

Access should be designed around responsibilities rather than individual users. Avoid creating a separate role for every developer or assigning `space_admin` simply to provide routine development access.

## Studio and Git Access

Access to a Studio Space and access to the Project Git repository are separate concerns.

A developer can require:

- authorization to the Studio Space, and
- appropriate permissions to the Git repository.

Developers use their own Git credentials or personal access tokens when committing and pushing changes.

The Git token configured for the K2cloud Project is a separate operational credential used by K2cloud to read the repository. It is not the developer's Git credential.

## Related Documentation

- [Spaces Overview](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_space_overview.md)
- [Create a Space](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_create_a_space.md)
- [Identity and Access](/articles/80_k2cloud/08-identity-and-access/README.md)
- [Configure Git](/articles/80_k2cloud/03-projects/80_k2cloud_projects_configure_git.md)