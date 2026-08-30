# Delete a Space

## Overview

Deleting a Space permanently removes the Space from K2cloud.

**Space deletion is not recoverable.**

Before deleting a Space, understand what will be removed and whether any data or development work must be preserved.

The consequences depend on the type of Space and the persistence model used by its Space Profile.

## Before You Delete a Space

Before proceeding:

- confirm that the correct Space has been selected,
- verify that the Space is no longer required,
- ensure that required development changes have been committed to Git,
- and understand whether persistent services are managed with the Space or externally.

## Delete a Studio Space

A Studio Space contains the development environment used to create and modify the Project.

Before deleting a Studio Space, ensure that all development work that must be retained has been committed and pushed to the Project Git repository.

Committed Project content can be used to recreate the development environment.

**Uncommitted development work in the Space is lost when the Space is deleted.**

## Delete a Fabric Space Using `noSdb`

With a `noSdb` profile, persistent services such as the System Database and object storage are managed externally from the Space.

Deleting the Space removes the Fabric runtime deployment and its K2cloud Space definition.

The external System Database and object storage are **not deleted with the Space** because their lifecycle is independent from the Space.

This allows the runtime Space to be removed or recreated without automatically removing the externally managed persistent data.

## Delete a Space Using a `managed` Profile

With a `managed` profile, the database and underlying blob or object storage are associated with the lifecycle of the Space.

Deleting the Space therefore also deletes the persistence associated with that Space.

**Do not delete a Space using a `managed` profile unless the associated persistent data can also be permanently removed.**

For more information about the available persistence models, see [K2cloud Versionless Space Profiles](/articles/98_installation_and_upgrade/K2cloud_Space_Profiles/README.md).

## Delete the Space

To delete a Space:

1. Open the **Spaces** page.
2. Locate the Space to delete.
3. Open the Space action menu (**...**).
4. Select **Delete Space**.
5. Review the deletion confirmation.
6. Confirm the deletion.

K2cloud begins removing the Space and its lifecycle-managed resources.

## After Deletion

After deletion completes, the Space is no longer available through K2cloud.

What remains depends on the deployment:

- Project content committed to Git remains in the Git repository.
- External persistence used by a `noSdb` Space remains independently managed.
- Persistence associated with a `managed` Space is deleted with the Space.

If the environment is needed again, a new Space must be created and the required Project content and configuration redeployed.

## Related Documentation

- [Space Lifecycle](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_space_lifecycle.md)
- [Operate a Space](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_operate_a_space.md)
- [K2cloud Versionless Space Profiles](/articles/98_installation_and_upgrade/K2cloud_Space_Profiles/README.md)

