# SAML Fundamentals, Terminology and Security

Security Assertion Markup Language (SAML) is one of the most widely used open standard for authentication and authorizing between multiple parties. SAML gives users a single sign-on (SSO) experience for applications.  

At its core, Security Assertion Markup Language (SAML) 2.0 is a means to exchange authorization and authentication information between services. SAML is frequently used to implement internal corporate single sign-on (SSO) solutions where the user logs into the IDP (identity provider) - a service that acts as the single source of identity which then grants access to a subset of other internal services.

## Terminology

In addition to *IDP, SP*, and *principals* which are explained [here](/articles/26_fabric_security/07_user_IAM_overview.md),  the following are more commonly used SAML terms:

- **Flows initiation** - SAML supports two types of flows: those initiated by the service provider (*SP-initiated*) and those initiated by the identity provider (*IDP-initiated*). The more common flow is SP-initiated flow. The SP-initiated flow starts when a user try to access the service provider, is then redirected to the identity provider for authentication, and is finally redirected back to the service provider. This flow as operated at Fabric is described [here](/articles/26_fabric_security/10_user_IAM_SAML_Fabric_flow.md). IDP-initiated flow starts when an end user (principal) logs into the Identity Provider's login page and then click to log into the SP's service.
- **Authentication request** (AuthnRequest) - This is a request that is built and then sent by the SP toward the IDP. It consists of an XML file that contains several parameters,  among them:  the ACS URL (reply URL), the SP entity ID (Issuer), and the required name-id (principal id) format.
- **Authentication response** - This is a token or assertion (in XML format) that contains authentication information about the user/principal, such as:
  - **Subject** - A NameID element (the authenticated principal name). Its format can be set at the request stage, although it is also usually provisioned at the IDP. Fabric works with an email format. (note that it is not necessary the actual user's email)
  - **Issuer** - The URL of the IDP that issued the assertion.
  - **conditions** - For example: valid time frame (starting date, expiration date, issuing date).
  - **Attribute statements** - These statements contain a list of attributes in a name-value pairs form, for example the groups/roles that are associated to the user.
- **Assertion consumer service (ACS)**  - The service at the SP which receives the SAML response. The URL of the ACS is provisioned at the IDP settings stage, and is also sent upon authentication request. 

## Security 

SAML provides several methods for certification and trust among the SP and IDP interactions, among them:

* **certification** - SP and IDP provides each other its public key, which is used with their interactions. Their values are reflected and represented at Fabric [SAML configuration](/articles/26_fabric_security/13_user_IAM_configiration.md#saml-configuration) as SP_CERT_ALIAS and IDP_CERT_ALIAS. 

  As part of a pre established trust relationship process between Fabric and the IDP, Fabric  provides to the IDP IT team the signed certificate public key "crt" file to be uploaded at the IDP, and the IDP team sends to the Fabric team the IDP "crt"/"cert" to be located at Fabric.

* **Encryption** - SAML supports several encryption methods where the one most commonly used is SHA-256 (this is also adopted by Fabric).

* **Trust** - SP Entity ID and IDP Entity IDs are transferred upon request and response for trust purposes. In addition, any request is sent with an ID which then can be verified by the SP upon response. 



[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/IAM_SAML/08_user_IAM_SSO_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/IAM_SAML/10_user_IAM_SAML_Fabric_flow.md)

