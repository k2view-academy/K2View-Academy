# User IAM Using LDAP

LDAP, the Lightweight Directory Access Protocol, is a mature, flexible, and well-supported standards-based mechanism for interacting with directory servers. It is often used for authentication and storing information about users, groups, and applications; however, it is a fairly general-purpose data store that can be utilized in a wide variety of applications.

The data is stored in a hierarchical structure, and Active Directory searches are done according to this hierarchy.

## Terminology

- **Distinguished Name (DN)** - uniquely identifies an entry and its path. It is comprised of a series of  relative distinguished names (RDNs) separated by commas.  
- **Organization Unit** (**OU**) - part of the hierarchy at the DN.
- **Common name (CN)** - the low level of an entry,  which is actually the user ID.
- **Entry** - a collection of information about an entity.


## How Fabric works with LDAP 

When integrated with LDAP, the login steps for the end user are similar to the steps when authentication is managed by Fabric:

The user attempts to access a Fabric app (1 in the diagram below). One of two results follows: 

   - If the user already has an existing Fabric session (2), he is allowed access and can carry out actions according to the permissions granted to him by Fabric and its apps. 

     ELSE
   - If the user does not have an active session (3), Fabric displays the login page, where the user should enter their credentials (user/password). The following steps are then carried out: 

     1. Fabric sends these credentials to the LDAP server (4).
     2. The LDAP server verifies that the credentials are correct and provides the groups to which the user is associated (5).
     3. Fabric establishes a Fabric session for the user, enabling them to access resources according to the permissions granted to them by Fabric and its apps. The Fabric session provides, via a *UserCode*, information about the user and the roles they are associated with (6).

Below is the logical flow, illustrating these steps: 


<img src="/articles/26_fabric_security_iam/images/15_Fabric LDAP.jpg">

 

For more information and guidelines about setting up Fabric with LDAP, refer to the examples for [Azure AD LDAP](/articles/26_fabric_security_iam/11.1_user_IAM_AD_LDAP.md) and [Azure AD LDAPS](/articles/26_fabric_security_iam/11.2_user_IAM_AD_LDAPS.md). For more information about SAML configuration in Fabric, please read [here](/articles/26_fabric_security_iam/13_user_IAM_configuration.md#ldap--ldaps-configuration).



[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security_iam/10_user_IAM_SAML_Fabric_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security_iam/12_web_login.md)

