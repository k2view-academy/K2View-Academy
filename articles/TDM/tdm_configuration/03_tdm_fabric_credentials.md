# TDM - Fabric Credentials

### Add Fabric Roles on each User's Group

The TDM Portal application is pre-integrated with [Fabric Web Framework](/articles/30_web_framework/02_preintegrated_apps_overview.md).  The user logins into the Fabric Web Framework and **Fabric authenticates the user**.   The TDM Portal gets the **user id** and the user's **Fabric roles** from the user's session. 

**The user groups are defined in the organization's SP and must be defined in advance in Fabric as [Fabric roles](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#create-role)**. 
Each Fabric role must get **ALL_WS** permission to enable the role's users to open and view the TDM windows.

Assuming a **one-to-one relation between a user group and a Fabric role**.

Of course each Fabric role can get different set of permissions. For example, a tester role should not have access to the API that creates or edits TDM [Business Entities (BEs)](/articles/TDM/tdm_overview/03_business_entity_overview.md), since only TDM admin users can create or edit Business Entities.



### Access TDM APIs Outside the TDM Portal

Do the following to access TDM APIs outside the TDM Portal:

1. Create a secured API key in Fabric.
2. Attach a Fabric role with AUTH_CLAIMS permission to the secured API key. This permission is needed to add the user id and the groups to the JWT claim.
3. The external system generates a JWT token signed by the secret key of the secured API. This JWT can be sent as a Bearer token to Fabric APIs including the TDM APIs.
4. The TDM API can get the user and their Fabric roles (=user groups) from the Fabric user session.

Click for more information about [Fabric Web Services Security](/articles/26_fabric_security/05_fabric_webservices_security.md).



[![Previous](/articles/images/Previous.png)](02_tdmdb_general_parameters.md)
