# IAM Using LDAP

LDAP, the Lightweight Directory Access Protocol, is a mature, flexible, and well supported standards-based mechanism for interacting with directory servers. It’s often used for authentication and storing information about users, groups, and applications, but is a fairly general-purpose data store and can be used in a wide variety of applications.

The data is stored in an hierarchy structure and accordingly the active directory searches are done.

## Terminology

- **Distinguished Name (DN)** -  uniquely identifies an entry and its path. It is comprised of a series of  zero or more relative distinguished names (RDNs) separated by commas.  
- **Organization Unit** (**OU**) part of the  hierarchy at the DN.
- **Common name (CN)** - the low level of an entry,  actual the user id
- **Entry** - a collection of information about an  entity.



## How Fabric works with LDAP 

When integrated with LDAP, the login steps for the end user is similar to those he shall pass when authentication is managed by Fabric:

1.  User attempts to access to a Fabric app.
2. In case user already has an existing Fabric session he is enabled to access and make actions according to the permissions granted to him by Fabric and its apps. 
3. In case user does not have an active session, Fabric shows him the login page, where he shall type his credentials - user/password.
4. Fabric then sends these credentials to the LDAP server to verify they are correct. In addition it gets from the LDAP the groups that the user is associated to.

Following is the logical flow that illustrate these steps: 

<img src="/articles/26_fabric_security/images/15_Fabric LDAP.jpg">

 

See [here]() for more information about user experience login process.



[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/06_data_masking.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/05_fabric_webservices_security.md)