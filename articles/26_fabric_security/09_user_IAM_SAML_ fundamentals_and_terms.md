# SAML Fundamentals, Terminology and Security

Security Assertion Markup Language (SAML) is one of the most widely used open standard for authentication and authorizing between multiple parties. It’s one of the protocol that give users the single sign-on (SSO) experience for applications. The other adopted open standard is OAuth and OpenID. Of the two, SAML 2.0, released in 2005, remains most commonly used in Enterprise SSO space. 

At its core, Security Assertion Markup Language (SAML) 2.0 is a means to exchange authorization and authentication information between services. SAML is frequently used to implement internal corporate single sign-on (SSO) solutions where the user logs into the IDP - a service that acts as the single source of identity which then grants access to a subset of other internal services.



## Terminology

In addition to IDP, SP, and principals which explained [here](/articles/26_fabric_security/07_user_IAM_overview.md),  following are more commonly used SAML terms:

- **Flows initiation** - SAML supports two types of flows: those initiated by the service provider (*SP-initiated*) and those initiated by the identity provider (*IDP-initiated*). The more common flow is SP-initiated flow. The SP-initiated flow starts when user try to access to the service provider, then redirected to the identity provider to authenticate, and finally redirected back to the service provider. This flow at operated at Fabric is described [here](). 
- **Authentication request** (AuthnRequest) - The request that built and sent by the SP toward the IDP. It is XML that contain several parameters among them the ACS URL (reply URL), the SP entity ID (Issuer) and the required name-id (principal id) format.
- **Authentication response** - is a token or assertion in an XML form that contain the authentication information about the user/principal, such as
  - **Subject**, contains a NameID element (the authenticated principal name). Its format can be set at the request, although also usually provisioned at the IDP. Fabric is used to work with email format. (note that it is not necessary the actual user's email)
  - **Issuer**, the URL of the IdP that issued the assertion.
  - **conditions**, for example: valid time frame  - starting date, expiration date, issuing date.
  - **Attribute statements**, contains a list of attributes in a name value pairs form, for example the groups/roles that are associated to the user.
- **Assertion consumer service (ACS)**  - The service at SP which gets the SAML response. The ACS URL is provisioned at the IDP settings as well as send at the authentication request. 



## Security 

The SAML provides several method for certification and trust among the SP and IDP interactions, among them:

* **certification** - SP and IDP provides each other its public key, which is used on their interactions. Their values are reflected and represented at Fabric [SAML configuration](/articles/26_fabric_security/08_user_IAM_configiration.md#saml-configuration) as SP_CERT_ALIAS and IDP_CERT_ALIAS. 

  As part of a pre established trust relationship process between Fabric and the IDP, Fabric  provides to the IDP IT team the signed certificate public key "crt" file to be uploaded at the IDP, and IDP team sends to the Fabric team the IDP "crt"/"cert" to be located at Fabric.

* **Encryption** - SAML supports several encryption method where the commonly used, also adopted by Fabric is SAH-256.

* **Trust** - SP Entity ID and IDP Entity ID are transferred at the request and response for trust purposes. In addition any request is sent with ID which then can be verified by the SP on response. 



[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/08_user_IAM_SSO_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/10_user_IAM_SAML_Fabric_flow.md)

