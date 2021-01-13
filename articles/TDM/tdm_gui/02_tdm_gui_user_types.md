# TDM GUI - User Types

TDM users must be defined in the LDAP application. The TDM GUI authenticates the user via the LDAP. 

There are three main types of TDM users. Each one has different permissions and can execute different activities.

Note that all TDM windows are available for all users in a view mode. However, only permitted users can update the TDM definitions based on their type and permissions.

## Admin - Super User

The Admin user is permitted to execute all of the activities in the TDM GUI application. Admin users are defined as **admin** in the **config.js** configuration file of the TDM, but the authentication of the user and password is done via LDAP.

The admin user is usually the implementer which defines the [TDM implementation in Fabric](/articles/TDM/tdm_implementation/03_tdm_fabric_implementation_flow.md) as well. The admin user can execute all activities in the TDM GUI and is responsible for the following activities:

- Create, edit or delete [products].
- Create, edit or delete [Business Entities](/articles/TDM/tdm_overview/03_business_entity_overview.md) and attach logical units or [post execution processes] to  each Business Entity.
- Create, edit or delete [environments].
- Attach environment owners to each environment.
- Define [roles] on each  environment and define permissions per each role.

## Environment Owner User

The environment owner user is the owner of a testing environment in the TDM. Admin users can define one or several environment owners on each testing environment. The environment owner user must be defined in LDAP under a dedicated group of users that are eligible to be assigned as environment owners. 

The TDM application identifies users that are allowed to be environment owners by their LDAP group.

The environment owner user can execute the the following activities on their environment:

- Edit the environment details.
- Add or remove [products] from the environment.
- Add or remove [Globals] from the environment.
- Add,  edit or remove [Exclusion List] from the environment.
- Create, edit or delete [TDM roles] on the environment and define permissions per each role.
- Attach users to TDM roles on the environment.

Note that an environment owner user cannot add or delete an environment. Is addition, this user cannot add or remove environment owners from the environment.

## Tester User

A Tester user can create, extract or load tasks based on his role. 





[![Previous](/articles/images/Previous.png)](01_tdm_gui_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_fabric_tdm_library.md)