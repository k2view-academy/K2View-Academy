# **Fabric Web Services Security** 

## Authentication Methods

Fabric secures and controls Web Service access via an authentication mechanism in which each API call shall be sent with a valid token. 

Fabric supports two main token types, which are sent as  `Authorization: Bearer` header:

1. **API Key** 

2. **JWT** ( JSON Web Tokens), an open industry standard method (RFC 7519) that securely represents claims between two parties. 

   The JWT authentication has 2 variants:

   a. Signed by Fabric 

   b. Signed by the WS client



Note that the JWT can be sent also as a cookie and not as the bearer header.

### API Key

API key authentication is the simplest method because it allows authenticating WS calls just by including a single key, where this simplicity also allows a client to make calls easily from various tools.

The API Key shall be sent as the token value of the `Authorization: Bearer` header, for example: `Authorization: Bearer ABC`, where API Key is "ABC".

See [here](/articles/26_fabric_security/05_fabric_webservices_security.md#generating-api-key) how to generate an API Key (choose the "non secured" key)..

The authorization and permissions are done according to the roles which are assigned to the API Key, and their associated permissions. See [here](/articles/17_fabric_credentials/01_fabric_credentials_overview.md) for more information about API Keys, roles and permissions.

### JWT Signed by Fabric

The authentication flow for this method works as following:

1. Make a first POST call to the Fabric server's endpoint: `<SERVER-HOST>:<SERVER-PORT>/api/authenticate`, where it shall provide in the post body either of the following credentials:
   - user/password, within the pattern of: `{"username": "<USER>", "password": "<PASSWORD>"}`.
   - API Key, within the pattern of: `{"apikey": "<APIKEY>"}`. See [here](/articles/26_fabric_security/05_fabric_webservices_security.md#generating-api-key) how to generate an API Key (choose the "non secured" key).

2. On authentication success, Fabric responds with `{"response": "OK"}` (within 201 response code), along with the JWT which is returned as a cookie.
3. Make the next web services calls by sending this JWT as the token value of the `Authorization: Bearer` header or as cookie, as part of each request (in case requests are done via the browser this cookie is already stored at the browser).  

The authorization and permissions are done according to the credentials that were provided during the first "/api/authenticate" call - either by user or API Key and the roles which are assigned to each of them. See [here](/articles/17_fabric_credentials/01_fabric_credentials_overview.md) for more information about API Keys, roles and permissions.

### JWT Signed by client

This method is considered as the most secured because the secret key is not sent on the transit layer and it is known only to the client. The authentication flow for this method works as following:

1. Create a secured API Key. See [here](/articles/26_fabric_security/05_fabric_webservices_security.md#generating-api-key) for instructions.
2. Generate a JWT, where:
   - It shall include "apk" claim with the value of the API Key, as part of the JWT payload.
   - The secret key, that provided by Fabric during the API Key generation, shall be added to the JWT signature.
   - JWT shall be signed using HMAC-SHA256.

3. Make the web services calls by sending this JWT as the token value of the `Authorization: Bearer` header.
4. Fabric verifies that the JWT is signed with the secret that matches to the "apk".

The authorization and permissions are done according to the roles which are assigned to the API Key, and their associated permissions. See [here](/articles/17_fabric_credentials/01_fabric_credentials_overview.md) for more information about API Keys, roles and permissions.



##### External trusted authentication 

In some cases the client itself - a service in the organization which calls Fabric - already authenticated the user (human or another system). For example the client can be a service that interacts with IDP to authenticate users by using SAML. In such case, client holds the actual user and the groups he is assigned to, and wants that Fabric will acts upon, and use this information, for example for roles permissions.

Fabric supports such delegated authentication:

* The user and groups shall be sent as part of the JWT payload claims ("user" , "groups"). 
* This option requires an extra security verification: the API Key, that is used and sent in the JWT, shall be associated to the "AUTHZ_CLAIMS" role. When verified, Fabric set the session with this user and roles (by taking the groups and setting them as user's roles for this session).



## Generating API Key  

There are two options to generate an API key - via the Admin App or by using a Fabric command, where in either of the methods, you can choose if the API Key shall be secured or not: 

* Admin App: 

  1. Open the **Admin Panel** web page and select **Admin**, **Security** and then click the **API keys** tab.
  2. Click the **Add API Key +** button on the upper right of the window.

  3. Fill in the Name (Mandatory) and choose if it shall be secured (Optional) by using the checkbox, resides on page.

  4. Click  **Save**.

  When the secured option has been selected, the secret key is displayed in a pop-up window and can be copied, for later use. The secret key shall be used to sign the JWT.

  

  For example:

<img src="/articles/26_fabric_security/images/07_fabric_webToken.PNG">

* Fabric command: `CREATE TOKEN <'token_name'> [SECURED]`.  When "SECURED" is being used the secret key is retrieved back.

  For example:

  ```
  create token 'Secured1' SECURED;
  
  |Secretkey                           |
  +------------------------------------+
  |c55a86d1-9de6-4aaa-bf9e-cedf1391c95b|
  ```

  

If the secured option has not been selected,  the token name is used as the token value, for the API Key Authentication method.



## Web Service Authorization & Permissions 

Web services authentication is done either by user or by API key,  where each can be assigned to roles and accordingly to permissions.

Read this [article](/articles/17_fabric_credentials/01_fabric_credentials_overview.md#rbac-in-fabric) for the list of supported roles, and then click [here](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#grant-ws_name-to-role-) to learn how to grant permissions to specific roles.








[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/04_fabric_interfaces_security.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/06_data_masking.md)

