# Sign in to K2cloud

## Overview

The K2cloud Orchestrator is accessed at:

```text
https://cloud.k2view.com
```

The K2cloud Orchestrator is the centralized SaaS control plane used to manage K2cloud Projects and the lifecycle of Spaces.

Access to the K2cloud Orchestrator requires the `cloud_user` role.

The K2cloud Orchestrator URL is not the same as a Space URL. Users who do not require Orchestrator access can access authorized Spaces directly using the URL provided for the Space.

## Before You Begin

Before signing in to the K2cloud Orchestrator:

- your user account must be provisioned,
- authentication must be configured for your organization,
- and you must be assigned the `cloud_user` role.

The `cloud_user` role is a highly privileged role intended for Project Managers and other users responsible for managing Projects and the lifecycle of Spaces. It should not normally be granted to developers simply to provide access to a development Space.

## Sign In

1. Open a supported browser.
2. Go to:

   ```text
   https://cloud.k2view.com
   ```

3. Authenticate using the method configured for your organization.

Depending on your organization's identity configuration, authentication may use a K2view-managed identity or be federated to your enterprise Identity Provider.

After successful authentication and authorization, the K2cloud Orchestrator console opens.

![K2cloud Orchestrator sign-in page](/articles/80_k2cloud/images/02_sign_in_orchestrator.png)

## Orchestrator Access and Space Access

Access to the K2cloud Orchestrator and access to a Space are separate.

A developer, for example, does not normally require the `cloud_user` role. A Project Manager can create the development Space and provide the developer with its Space URL. The developer then accesses the Space directly.

A user without the `cloud_user` role who attempts to access `https://cloud.k2view.com` is denied access to the K2cloud Orchestrator. This does not prevent the user from accessing a Space directly when the appropriate Space-level authorization has been granted.

Space access is governed separately using `space_admin`, `space_user`, and customer-defined Fabric roles and permissions.

For customers using SAML federation, Identity Provider groups can also be mapped to customer-defined Fabric roles, allowing Space permissions to be tailored to organizational responsibilities. Roles should be designed around responsibilities rather than individual users; avoid creating a separate role for each developer.

For more information, see [Access Fabric Web Studio and Fabric Spaces](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_access_fabric_web_studio_and_fabric_spaces.md).

## If Access Is Denied

If you cannot access the K2cloud Orchestrator, confirm that:

- your account has been provisioned,
- you are using the correct identity,
- authentication is completing successfully,
- and you have been assigned the `cloud_user` role.

A user who can authenticate successfully but does not have the `cloud_user` role is not authorized to access the K2cloud Orchestrator.

If you only need access to a Space, use the Space URL provided by your Project Manager or administrator rather than `https://cloud.k2view.com`.

## Related Documentation

- [Navigate the K2cloud Orchestrator](/articles/80_k2cloud/02-getting-started/80_k2cloud_getting_started_navigate_the_k2cloud_console.md)
- [Access Fabric Web Studio and Fabric Spaces](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_access_fabric_web_studio_and_fabric_spaces.md)